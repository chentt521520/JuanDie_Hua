package com.example.juandie_hua.percenter.seting;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.x;
import org.xutils.common.Callback.CancelledException;
import org.xutils.view.annotation.ViewInject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.juandie_hua.R;
import com.example.juandie_hua.ui.tab.Home;
import com.example.juandie_hua.ui.tab.Me;
import com.example.juandie_hua.app.BaseActivity;
import com.example.juandie_hua.app.Constant;
import com.example.juandie_hua.helper.UiHelper;
import com.example.juandie_hua.mainactivity.Fengmian;
import com.example.juandie_hua.mainactivity.Xutils_Get_Post;
import com.example.juandie_hua.mainactivity.Xutils_Get_Post.XCallBack;
import com.example.juandie_hua.mainactivity.internet_if;
import com.example.juandie_hua.mainactivity.internet_landing;
import com.example.juandie_hua.mainactivity.internet_landing.re_jk;
import com.example.juandie_hua.mainactivity.other_web1;
import com.example.juandie_hua.onekeyshare.OnekeyShare;
import com.example.juandie_hua.utils.PackageUtils;
import com.example.juandie_hua.utils.SharedPreferenceUtils;
import com.example.juandie_hua.utils.StatusBarUtils;
import com.example.juandie_hua.view.CustomDialog;
import com.umeng.analytics.MobclickAgent;

public class seting extends BaseActivity implements re_jk {

    @ViewInject(R.id.set_linmm)
    RelativeLayout lin_mm;

    @ViewInject(R.id.set_lingx)
    RelativeLayout lin_gx;
    @ViewInject(R.id.set_tegx1)
    TextView te_gxbb;

    @ViewInject(R.id.set_clean)
    RelativeLayout lin_clean;

    @ViewInject(R.id.set_linwx)
    RelativeLayout lin_wx;
    @ViewInject(R.id.set_tewx)
    TextView te_wx1;
    @ViewInject(R.id.set_tewxset)
    TextView te_wx;

    @ViewInject(R.id.set_fx)
    RelativeLayout lin_fx;
    @ViewInject(R.id.set_tequit)
    TextView te_quit;

    @ViewInject(R.id.set_abuus)
    RelativeLayout lin_abuus;

    internet_landing inLanding;
    private CustomDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitleContentView(R.layout.seting);
        StatusBarUtils.with(this).setBarColor(R.color.white_fff);

        x.view().inject(this);

        inLanding = new internet_landing(this);
        inLanding.setonc(this);

        setviewhw();
        setviewlisten();
    }

    private void setviewlisten() {
        lin_mm.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                if (!isLogin()) {
                    UiHelper.toLoginActivity(seting.this);
                } else {
                    UiHelper.toActivity(seting.this, setmm.class);
                }
            }
        });

        lin_clean.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                inLanding.showlanding();
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        Glide.get(getApplication()).clearDiskCache();
                    }
                }).start();
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        inLanding.dismisslanding();
                        Toast.makeText(seting.this, "清除缓存成功",
                                Toast.LENGTH_SHORT).show();
                    }
                }, 2000);
            }
        });
        te_quit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                loginOut();
            }
        });
        lin_gx.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (inLanding.if_inter()) {
                    xutils_bb();
                }
            }
        });

        lin_wx.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String iswxbd = (String) SharedPreferenceUtils.getPreference(seting.this, Constant.iswxbd, "S");
                Intent i = new Intent();
                i.setClass(seting.this, wx_bdgh.class);
                i.putExtra("type", iswxbd);
                UiHelper.toActivity(seting.this, i);
            }
        });

        lin_fx.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                showShare();
            }
        });

        lin_abuus.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.putExtra("titl", "关于我们");
                i.putExtra("url", "https://m.juandie.com/help-aboutus.html?is_app=1" + Fengmian.uid);
                i.setClass(seting.this, other_web1.class);
                startActivity(i);
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
            }
        });
    }

    private void setviewhw() {

        this.getTitleView().setTitleText("设置");
        te_quit.setVisibility(isLogin() ? View.VISIBLE : View.GONE);
        String iswxbd = (String) SharedPreferenceUtils.getPreference(seting.this, Constant.iswxbd, "S");
        if (TextUtils.equals(iswxbd, "1")) {
            te_wx1.setText((String) SharedPreferenceUtils.getPreference(seting.this, Constant.pho, "S"));
            te_wx.setText("更换");
        } else if (TextUtils.equals(iswxbd, "2")) {
            te_wx1.setText((String) SharedPreferenceUtils.getPreference(seting.this, Constant.pho, "S"));
            te_wx.setText("去绑定");
            lin_wx.setVisibility(View.VISIBLE);
        } else {
            lin_wx.setVisibility(View.GONE);
        }

        te_gxbb.setText("当前版本v" + PackageUtils.getVersionName(seting.this));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            finish();
            overridePendingTransition(R.anim.push_right_out, R.anim.push_right_in);
            return false;
        }
        return false;
    }

    private void loginOut() {
        dialog = new CustomDialog.Builder(seting.this)
                .setTitle("您确定要退出吗?")
                .setPositiveButton(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        xutils_quit();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(null)
                .create();
        dialog.show();
    }

    private void xutils_quit() {
        String url = "https://app.juandie.com/api_mobile/user.php?act=logout";

        Xutils_Get_Post.getInstance().get(url, new XCallBack() {

            @Override
            public void onResponse(String result) {
                JSONObject jso1;
                try {
                    jso1 = new JSONObject(result);
                    if (jso1.getString("status").equals("1")) {

                        SharedPreferenceUtils.setPreference(seting.this, Constant.uid, "", "S");
                        SharedPreferenceUtils.setPreference(seting.this, Constant.openid, "", "S");
                        SharedPreferenceUtils.setPreference(seting.this, Constant.cook, "", "S");
                        SharedPreferenceUtils.setPreference(seting.this, Constant.iswxbd, "", "S");
                        SharedPreferenceUtils.setPreference(seting.this, Constant.typeqd, "", "S");
                        SharedPreferenceUtils.setPreference(seting.this, Constant.pho, "", "S");
                        SharedPreferenceUtils.setPreference(seting.this, Constant.pho1, "", "S");
                        toast("退出成功");

                        Fengmian.uid = "";
                        Home.myHandler.sendEmptyMessage(0x003);
                        Me.handler.sendEmptyMessage(0x003);

                        finish();
                        overridePendingTransition(R.anim.push_right_out, R.anim.push_right_in);
                    } else {
                        toast(jso1.getString("msg"));
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }

            @Override
            public void onFail(String result) {
                xutils_quit();
            }

            @Override
            public void onCancel(CancelledException cex) {
                // TODO Auto-generated method stub

            }
        });
    }

    private void xutils_bb() {
        inLanding.showlanding();
        String url = "https://app.juandie.com/api_mobile/index.php?act=app_version";

        Xutils_Get_Post.getInstance().get(url, new XCallBack() {

            @Override
            public void onResponse(String result) {
                try {
                    JSONObject response = new JSONObject(result);
                    if (response.getString("status").equals("1")) {
                        JSONObject data = response.getJSONObject("data");
                        int versionCode = PackageUtils.getVersionCode(seting.this);
                        if (versionCode >= data.getInt("versionCode")) {
                            Toast.makeText(seting.this, "当前已是最新版本，不需要更新",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(seting.this, "当前不是最新版本，建议更新到最新版本",
                                    Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        String jsb = response.getString("msg");
                        Toast.makeText(seting.this, jsb, Toast.LENGTH_SHORT)
                                .show();
                    }
                    inLanding.dismisslanding();
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail(String result) {
                inLanding.dismisslanding();
            }

            @Override
            public void onCancel(CancelledException cex) {
                inLanding.dismisslanding();
            }
        });
    }

    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        // 关闭sso授权
        oks.disableSSOWhenAuthorize();
        oks.setTitle("娟蝶鲜花app");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("https://www.juandie.com/help-download_app.html");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("娟蝶鲜花-同城花店订鲜花生日蛋糕速递APP");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        // oks.setImagePath("/sdcard/test.jpg");// 确保SDcard下面存在此张图片
        oks.setImageUrl("https://m.juandie.com/themes/5lux/assets/meilele/images/app_logo.png");
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("https://www.juandie.com/help-download_app.html");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("https://www.juandie.com/help-download_app.html");

        // 启动分享GUI
        oks.show(this);

        // 避免微信登录与分享冲突
        SharedPreferenceUtils.setPreference(seting.this, Constant.isfenx, "true", "S");
    }

    @Override
    public void re_requestjk(View v) {
        if (new internet_if().isNetworkConnected(this)) {
            inLanding.dismissinter();
            xutils_bb();
        } else {
            Toast.makeText(this, "网络连接失败,请设置网络", Toast.LENGTH_SHORT).show();
        }
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
