package com.example.juandie_hua.ui.login;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.x;
import org.xutils.common.Callback.CancelledException;
import org.xutils.view.annotation.ViewInject;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.juandie_hua.R;
import com.example.juandie_hua.ui.tab.Home;
import com.example.juandie_hua.ui.tab.Me;
import com.example.juandie_hua.app.BaseActivity;
import com.example.juandie_hua.app.Constant;
import com.example.juandie_hua.app.HttpUrl;
import com.example.juandie_hua.calender.utils.ImageUtils;
import com.example.juandie_hua.mainactivity.Fengmian;
import com.example.juandie_hua.ui.tab.ShopCart;
import com.example.juandie_hua.mainactivity.Xutils_Get_Post;
import com.example.juandie_hua.percenter.TimerTextView;
import com.example.juandie_hua.utils.SharedPreferenceUtils;
import com.example.juandie_hua.utils.StatusBarUtils;
import com.example.juandie_hua.utils.StrUtils;

public class Loginpho extends BaseActivity {
    @ViewInject(R.id.loginp_retop)
    RelativeLayout re_top;
    @ViewInject(R.id.loginp_imreturn)
    ImageView im_turn;
    @ViewInject(R.id.loginp_teclose)
    TextView te_close;

    @ViewInject(R.id.loginp_teph)
    TextView te_titlog;

    @ViewInject(R.id.loginp_lteph)
    LinearLayout lin_pho;
    @ViewInject(R.id.loginp_edph)
    EditText edit_pho;

    @ViewInject(R.id.loginp_linyzm)
    LinearLayout lin_yzm;
    @ViewInject(R.id.loginp_edyzm)
    EditText edit_yzm;
    @ViewInject(R.id.loginp_tegetyzm)
    TimerTextView te_yzm;

    @ViewInject(R.id.loginp_teok)
    TextView te_ok;

    @ViewInject(R.id.loginp_hen1)
    View v_hen1;
    @ViewInject(R.id.loginp_hen2)
    View v_hen2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        setContentView(R.layout.loginpho1);
        StatusBarUtils.with(this).fullScreen();

        x.view().inject(this);
        setviewhw();
        setviewlisten();

    }

    private void setviewlisten() {
        im_turn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                TimerTextView.isc = false;
                finish();
                overridePendingTransition(R.anim.push_right_out,
                        R.anim.push_right_in);
            }
        });
        te_yzm.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (StrUtils.isMatchered(edit_pho.getText().toString())) {
                    TimerTextView.isc = true;
                    httpPost_getcode(edit_pho.getText().toString());
                } else {
                    toast("请输入正确的手机号");
                }
            }
        });
        te_ok.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (StrUtils.isMatchered(edit_pho.getText().toString())) {
                    TimerTextView.isc = true;
                    edit_pho.setEnabled(false);
                    if (edit_yzm.getText().toString().length() == 6) {
                        httpPost_longin2(edit_pho.getText().toString(),
                                edit_yzm.getText().toString());
                    } else {
                        toast("请输入6位验证码");
                    }
                } else {
                    toast("请输入正确的手机号");
                }
            }
        });
        te_close.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // Loginphowx.myHandler.sendEmptyMessage(0x001);
                finish();
                overridePendingTransition(R.anim.push_right_out,
                        R.anim.push_right_in);
            }
        });
    }

    String seeid = "";

    /**
     * 发送短信验证码
     *
     * @param phonenumber
     */
    public void httpPost_getcode(String phonenumber) {
//		String url = "https://app.juandie.com/api_mobile/user.php";
        String url = HttpUrl.user;

        HashMap<String, String> maps = new HashMap<>();
        maps.put("act", HttpUrl.login_send_msg);
        maps.put("mobile_phone", phonenumber);

        Xutils_Get_Post.getInstance().post(url, maps, new Xutils_Get_Post.XCallBack() {

            @Override
            public void onResponse(String result) {
                JSONObject object;
                try {
                    object = new JSONObject(result);
                    int status = object.getInt("status");
                    if (status == 1) {
                        String uid = object.getString("uid");
                        SharedPreferenceUtils.setPreference(Loginpho.this, Constant.uid, uid, "S");
                        Fengmian.uid = uid;
                        seeid = object.getString("PHPSESSID");

                        toast("验证码已发送到手机，请注意查收");
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
                // TODO Auto-generated method stub

            }
        });

    }

    /**
     * 短信验证码登录
     *
     * @param phonenumber
     */
    public void httpPost_longin2(String phonenumber, String captcha) {

//		String url = "https://app.juandie.com/api_mobile/user.php";
        String url = HttpUrl.user;
        HashMap<String, String> maps = new HashMap<>();
        maps.put("act", HttpUrl.phone_login);
        maps.put("mobile_phone", phonenumber);
        maps.put("captcha", captcha);

        Xutils_Get_Post.getInstance().post(url, maps, new Xutils_Get_Post.XCallBack() {

            @Override
            public void onResponse(String result) {
                JSONObject object;
                try {
                    object = new JSONObject(result);
                    int status = object.getInt("status");
                    if (status == 1) {
                        toast(object.getString("msg"));
                        String uid = object.getString("uid");
                        SharedPreferenceUtils.setPreference(Loginpho.this, Constant.uid, uid, "S");
                        SharedPreferenceUtils.setPreference(Loginpho.this, Constant.cook, object.getString("PHPSESSID"), "S");
                        SharedPreferenceUtils.setPreference(Loginpho.this, Constant.is_login, true, "B");
                        Fengmian.uid = uid;

                        Home.myHandler.sendEmptyMessage(0x004);
                        ShopCart.myHandler.sendEmptyMessage(0x001);
                        Me.handler.sendEmptyMessage(0x003);
                        Loginphowx.myHandler.sendEmptyMessage(0x001);
                        overridePendingTransition(R.anim.push_right_out, R.anim.push_right_in);
                        finish();

//						if (preferences.getString("sye_dl", "0").equals("1")) {
//							editor = preferences.edit();
//							editor.putString("sye_dl", "0");
//							editor.commit();
//							startActivity(new Intent(Loginpho.this,
//									haha.class));
//							Home.myHandler.sendEmptyMessage(0x004);
//						} else {
//							Me.handler.sendEmptyMessage(0x003);
//							Loginphowx.myHandler.sendEmptyMessage(0x001);
//							finish();
//							overridePendingTransition(R.anim.push_right_out,
//									R.anim.push_right_in);
//						}
                    } else {
                        Toast.makeText(Loginpho.this, object.getString("msg"), Toast.LENGTH_SHORT).show();
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

    private void setviewhw() {

        DisplayMetrics dm = getResources().getDisplayMetrics();
        int w = dm.widthPixels;

        setviewhw_lin(re_top, w, (int) (w * 55 / 450.0), 0,
                (int) (w * 90 / 750.0), 0, 0);
        setviewhw_re(im_turn, (int) (w * 35 / 450.0), (int) (w * 25 / 450.0),
                (int) (w * 12 / 375.0), (int) (w * 15 / 450.0), 0, 0);
        im_turn.setPadding((int) (w * 10 / 450.0), 0, 0, 0);

        setviewhw_lin(te_titlog, w, LayoutParams.WRAP_CONTENT, 0,
                (int) (w * 180 / 750.0), 0, 0);

        setviewhw_lin(lin_pho, w - (int) (w * 100 / 750.0),
                (int) (w * 85 / 750.0), (int) (w * 50 / 750.0),
                (int) (w * 90 / 750.0), 0, 0);
        setviewhw_lin(lin_yzm, w - (int) (w * 100 / 750.0),
                (int) (w * 95 / 750.0), (int) (w * 50 / 750.0),
                (int) (w * 45 / 750.0), 0, 0);
        setviewhw_lin(te_ok, w - (int) (w * 100 / 750.0),
                (int) (w * 82 / 750.0), (int) (w * 50 / 750.0),
                (int) (w * 60 / 750.0), (int) (w * 50 / 750.0),
                (int) (w * 60 / 750.0));
        setviewhw_lin(v_hen1, w - (int) (w * 100 / 750.0),
                (int) (w * 2 / 750.0), (int) (w * 50 / 750.0),
                (int) (w * 0 / 750.0), (int) (w * 50 / 750.0),
                (int) (w * 0 / 750.0));
        setviewhw_lin(v_hen2, w - (int) (w * 100 / 750.0),
                (int) (w * 2 / 750.0), (int) (w * 50 / 750.0),
                (int) (w * 0 / 750.0), (int) (w * 50 / 750.0),
                (int) (w * 0 / 750.0));

    }

    private void setviewhw_lin(View v, int width, int height, int left,
                               int top, int right, int bottom) {
        LayoutParams lp = new LayoutParams(width, height);
        lp.setMargins(left, top, right, bottom);
        v.setLayoutParams(lp);
    }

    private void setviewhw_re(View v, int width, int height, int left, int top,
                              int right, int bottom) {
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(width,
                height);
        lp.setMargins(left, top, right, bottom);
        v.setLayoutParams(lp);
    }

    /**
     * 手机正则
     *
     * @param patternStr
     * @param input
     * @return
     */
    public boolean isMatchered(String patternStr, CharSequence input) {
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            TimerTextView.isc = false;
            finish();
            overridePendingTransition(R.anim.push_right_out,
                    R.anim.push_right_in);
            return false;
        }
        return false;
    }

}
