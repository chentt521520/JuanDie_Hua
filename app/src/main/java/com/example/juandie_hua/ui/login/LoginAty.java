package com.example.juandie_hua.ui.login;

import java.lang.ref.WeakReference;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.x;
import org.xutils.common.Callback.CancelledException;
import org.xutils.common.util.MD5;
import org.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;

import com.example.juandie_hua.R;
import com.example.juandie_hua.app.App;
import com.example.juandie_hua.app.BaseActivity;
import com.example.juandie_hua.app.Constant;
import com.example.juandie_hua.helper.UiHelper;
import com.example.juandie_hua.mainactivity.Fengmian;
import com.example.juandie_hua.mainactivity.Xutils_Get_Post;
import com.example.juandie_hua.mainactivity.Xutils_Get_Post.XCallBack;
import com.example.juandie_hua.ui.tab.Home;
import com.example.juandie_hua.ui.tab.Me;
import com.example.juandie_hua.utils.SharedPreferenceUtils;
import com.example.juandie_hua.utils.StatusBarUtils;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class LoginAty extends BaseActivity {
    @ViewInject(R.id.loginph_imreturn)
    ImageView im_turn;
    @ViewInject(R.id.loginph_teph)
    TextView te_ph;

    @ViewInject(R.id.loginph_im31)
    ImageView imView_wx;

    @ViewInject(R.id.loginph_im32)
    ImageView imView_xlwb;

    @ViewInject(R.id.loginph_im33)
    ImageView imView_qq;

    public static MyHandler myHandler;
    IWXAPI api;

    String san = "qq";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        StatusBarUtils.with(this).fullScreen();

        x.view().inject(this);
        myHandler = new MyHandler(this);
        api = WXAPIFactory.createWXAPI(LoginAty.this, "wx0a9fcf0dc4ee88b0");
        setviewlisten();

    }

    private void setviewlisten() {
        im_turn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.push_right_out,
                        R.anim.push_right_in);
            }
        });

        imView_wx.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!api.isWXAppInstalled()) {
                    toast("您还未安装微信客户端");
                    return;
                }
                // 避免微信登录与分享冲突
                SharedPreferenceUtils.setPreference(LoginAty.this, Constant.isfenx, "false", "S");

                String state = MD5.md5("wechat_login" + App.getInstance().getOpenId());
                final SendAuth.Req req = new SendAuth.Req();
                req.scope = "snsapi_userinfo";
                req.state = state;
                api.sendReq(req);
            }
        });
        te_ph.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                UiHelper.toActivity(LoginAty.this, Loginpho.class);
            }
        });

        imView_qq.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                san = "qq";
                loginThirdPart(1);
            }
        });
        imView_xlwb.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                san = "SinaWeibo";
                loginThirdPart(2);
            }
        });

    }

    public static class MyHandler extends Handler {
        WeakReference<LoginAty> referenceObj;

        public MyHandler(LoginAty loginphowx) {
            referenceObj = new WeakReference<>(loginphowx);
        }

        @Override
        public void handleMessage(Message msg) {
            LoginAty activity = referenceObj.get();
            switch (msg.what) {
                case 0x001://销毁界面
                    if (activity != null) {
                        activity.finish();
                        activity.overridePendingTransition(R.anim.push_right_out, R.anim.push_right_in);
                    }
                    break;
                case 0x002://登录成功，刷新页面

                    if (Home.myHandler != null) {
                        Home.myHandler.sendEmptyMessage(0x003);
                    }
                    if (Me.handler != null) {
                        Me.handler.sendEmptyMessage(0x003);
                    }
                    if (activity != null) {
                        activity.toast("登录成功");

                        activity.finish();
                        activity.overridePendingTransition(R.anim.push_right_out, R.anim.push_right_in);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            finish();
            overridePendingTransition(R.anim.push_right_out, R.anim.push_right_in);
            return false;
        }
        return false;
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 1:
                    toast("授权登陆成功");

                    Platform platform = (Platform) msg.obj;
                    String userId = platform.getDb().getUserId();// 获取用户账号
                    System.out.println(platform.getDb().getUserId() + "     xxxxxx");
                    httpPost_longinsan(userId);
                    break;
                case 2:
                    toast("授权登陆失败");
                    break;
                case 3:
                    toast("授权登陆取消");
                    break;
            }
        }
    };

    /**
     * qq登陆
     */
    private void loginThirdPart(int flag) {
        String name;
        if (flag == 1) {
            name = QQ.NAME;
        } else {
            name = SinaWeibo.NAME;
        }
        Platform platform = ShareSDK.getPlatform(name);
        platform.SSOSetting(false);
        if (!platform.isClientValid()) {
            if (flag == 1) {
                toast("QQ未安装,请先安装QQ");
            } else {
                toast("新浪微博未安装,请先安装新浪微博");
            }
        }

        if (platform.isAuthValid()) { // 如果授权就删除授权资料
            platform.removeAccount(true);
        }

        platform.showUser(null); // 授权并获取用户信息


        platform.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                Message message = Message.obtain();
                message.what = 1;
                message.obj = platform;
                mHandler.sendMessage(message);
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                Message message = Message.obtain();
                message.what = 2;
                message.obj = platform;
                mHandler.sendMessage(message);
            }

            @Override
            public void onCancel(Platform platform, int i) {
                Message message = Message.obtain();
                message.what = 3;
                message.obj = platform;
                mHandler.sendMessage(message);
            }
        });
    }


    /**
     * QQ，微博登录
     *
     * @param userid
     */
    public void httpPost_longinsan(String userid) {
        String url = "https://app.juandie.com/api_mobile/user.php";
        HashMap<String, String> maps = new HashMap<>();
        maps.put("act", "third_login");
        maps.put("third_login_platform_name", san);
        maps.put("third_login_user_id", userid);

        Xutils_Get_Post.getInstance().post(url, maps, new XCallBack() {

            @Override
            public void onResponse(String result) {
                JSONObject object;
                try {
                    object = new JSONObject(result);
                    int status = object.getInt("status");
                    if (status == 1) {
                        SharedPreferenceUtils.setPreference(LoginAty.this, Constant.cook, object.getString("PHPSESSID"), "S");
                        SharedPreferenceUtils.setPreference(LoginAty.this, Constant.uid, object.getString("uid"), "S");
                        SharedPreferenceUtils.setPreference(LoginAty.this, Constant.is_login, true, "B");
                        JSONObject jso = object.getJSONObject("data");
                        //未绑定手机号
                        if (!jso.getString("is_binding_account").equals("true")) {
                            Intent i = new Intent();
                            i.setClass(LoginAty.this, Loginpho_bd.class);
                            i.putExtra("type", "dsf");
                            startActivity(i);
                        }
                        LoginAty.myHandler.sendEmptyMessage(0x002);
                    } else {
                        toast(object.getString("msg"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail(String result) {
            }

            @Override
            public void onCancel(CancelledException cex) {
            }
        });
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == Activity.RESULT_OK) {
//            if (requestCode == UiHelper.LoginQQ) {
//
//            } else if (requestCode == UiHelper.LoginWX) {
//
//            }
//            if (requestCode == UiHelper.LoginPhone) {
//
//            }
//        }
//    }
}
