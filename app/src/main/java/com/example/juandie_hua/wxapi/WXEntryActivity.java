package com.example.juandie_hua.wxapi;

import java.lang.ref.WeakReference;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback.CancelledException;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Window;
import android.widget.Toast;

import cn.sharesdk.wechat.utils.WXAppExtendObject;
import cn.sharesdk.wechat.utils.WXMediaMessage;
import cn.sharesdk.wechat.utils.WechatHandlerActivity;

import com.example.juandie_hua.R;
import com.example.juandie_hua.app.Constant;
import com.example.juandie_hua.ui.tab.Home;
import com.example.juandie_hua.mainactivity.Fengmian;
import com.example.juandie_hua.ui.tab.Me;
import com.example.juandie_hua.mainactivity.Xutils_Get_Post;
import com.example.juandie_hua.ui.login.Loginpho_bd;
import com.example.juandie_hua.ui.login.LoginAty;
import com.example.juandie_hua.ui.tab.ShopCart;
import com.example.juandie_hua.utils.SharedPreferenceUtils;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXEntryActivity extends WechatHandlerActivity implements IWXAPIEventHandler {
    private IWXAPI api;

    MyHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wxentryactivity);
        String isfenx = (String) SharedPreferenceUtils.getPreference(this, Constant.isfenx, "S");
        if (TextUtils.equals(isfenx, "false")) {
            api = WXAPIFactory.createWXAPI(this, "wx0a9fcf0dc4ee88b0", false);
            handler = new MyHandler(WXEntryActivity.this);
            api.handleIntent(getIntent(), this);
        }

    }

    @Override
    public void onReq(BaseReq arg0) {

    }

    @Override
    public void onResp(final BaseResp arg0) {
        String isfenx = (String) SharedPreferenceUtils.getPreference(this, Constant.isfenx, "S");
        if (TextUtils.equals(isfenx, "true")) {
            // 避免微信登录与分享冲突
            SharedPreferenceUtils.setPreference(this, Constant.isfenx, "false", "S");
            finish();
        } else {
            // 避免微信登录与分享冲突
            SharedPreferenceUtils.setPreference(this, Constant.isfenx, "false", "S");
            if (arg0.errCode == 0) {

                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        final String code = ((SendAuth.Resp) arg0).code;
                        final String state = ((SendAuth.Resp) arg0).state;
                        httpPost_getcode(code, state);
                    }
                }).start();

            } else
                finish();
        }

    }

    /**
     * 处理微信发出的向第三方应用请求app message
     * <p>
     * 在微信客户端中的聊天页面有“添加工具”，可以将本应用的图标添加到其中 此后点击图标，下面的代码会被执行。Demo仅仅只是打开自己而已，但你可
     * 做点其他的事情，包括根本不打开任何页面
     */
    public void onGetMessageFromWXReq(WXMediaMessage msg) {
        if (msg != null) {
            Intent iLaunchMyself = getPackageManager()
                    .getLaunchIntentForPackage(getPackageName());
            startActivity(iLaunchMyself);
        }
    }

    /**
     * 处理微信向第三方应用发起的消息
     * <p>
     * 此处用来接收从微信发送过来的消息，比方说本demo在wechatpage里面分享
     * 应用时可以不分享应用文件，而分享一段应用的自定义信息。接受方的微信 客户端会通过这个方法，将这个信息发送回接收方手机上的本demo中，当作 回调。
     * <p>
     * 本Demo只是将信息展示出来，但你可做点其他的事情，而不仅仅只是Toast
     */
    public void onShowMessageFromWXReq(WXMediaMessage msg) {
        if (msg != null && msg.mediaObject != null
                && (msg.mediaObject instanceof WXAppExtendObject)) {
            WXAppExtendObject obj = (WXAppExtendObject) msg.mediaObject;
            Toast.makeText(this, obj.extInfo, Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * 请求登录
     */
    public void httpPost_getcode(String code, String state) {

        String url = "https://app.juandie.com/api_mobile/user.php";
        HashMap<String, String> maps = new HashMap<>();
        maps.put("act", "app_wechat_login_authorize");
        maps.put("code", code);
        maps.put("state", state);
        Xutils_Get_Post.getInstance().post(url, maps, new Xutils_Get_Post.XCallBack() {

            @Override
            public void onResponse(String result) {
                JSONObject object;
                try {
                    object = new JSONObject(result);
                    int status = object.getInt("status");
                    if (status == 1) {
                        Toast.makeText(WXEntryActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        // 避免微信登录与分享冲突
                        SharedPreferenceUtils.setPreference(WXEntryActivity.this, Constant.cook, object.getString("PHPSESSID"), "S");
                        SharedPreferenceUtils.setPreference(WXEntryActivity.this, Constant.uid, object.getString("uid"), "S");
                        Fengmian.uid = object.getString("uid");
                        JSONObject jso = object.getJSONObject("data");

                        //刷新首页，隐藏领红包按钮
                        if (Home.myHandler != null) {
                            Home.myHandler.sendEmptyMessage(0x003);
                        }
                        if (ShopCart.myHandler != null) {
                            ShopCart.myHandler.sendEmptyMessage(0x001);
                        }
                        if (Me.handler != null) {
                            Me.handler.sendEmptyMessage(0x003);
                        }

                        //未绑定手机号
                        if (!jso.getString("is_binding_account").equals("true")) {
                            Intent i = new Intent();
                            i.setClass(WXEntryActivity.this, Loginpho_bd.class);
                            i.putExtra("type", "wx");
                            startActivity(i);
                        }
                        //销毁上一个页面
                        LoginAty.myHandler.sendEmptyMessage(0x001);
                    }
                    //销毁当前页面
                    handler.sendEmptyMessage(0x001);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFail(String result) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onCancel(CancelledException cex) {
                // TODO Auto-generated method stub

            }
        });
    }

    public static class MyHandler extends Handler {
        WeakReference<WXEntryActivity> referenceObj;

        public MyHandler(WXEntryActivity loginphowx) {
            referenceObj = new WeakReference<WXEntryActivity>(loginphowx);
        }

        @Override
        public void handleMessage(Message msg) {
            final WXEntryActivity activity = referenceObj.get();
            switch (msg.what) {
                case 0x001:
                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            activity.finish();
                            activity.overridePendingTransition(
                                    R.anim.push_right_out, R.anim.push_right_in);
                        }
                    }, 1000);

                    break;

                default:
                    break;
            }
        }
    }

}
