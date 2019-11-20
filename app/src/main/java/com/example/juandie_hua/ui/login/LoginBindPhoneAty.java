package com.example.juandie_hua.ui.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.juandie_hua.R;
import com.example.juandie_hua.app.BaseActivity;
import com.example.juandie_hua.app.Constant;
import com.example.juandie_hua.http.HttpUrl;
import com.example.juandie_hua.helper.UiHelper;
import com.example.juandie_hua.mainactivity.Xutils_Get_Post;
import com.example.juandie_hua.mainactivity.Xutils_Get_Post.XCallBack;
import com.example.juandie_hua.percenter.TimerTextView;
import com.example.juandie_hua.utils.SharedPreferenceUtils;
import com.example.juandie_hua.utils.StatusBarUtils;
import com.example.juandie_hua.utils.StrUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback.CancelledException;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.HashMap;

public class LoginBindPhoneAty extends BaseActivity {

    @ViewInject(R.id.ui_login_title)
    TextView te_titlog;

    @ViewInject(R.id.ui_phone_number)
    EditText phoneNumber;
    @ViewInject(R.id.ui_verify_code)
    EditText verifyCode;

    @ViewInject(R.id.ui_get_verify_code)
    TimerTextView te_yzm;

    @ViewInject(R.id.ui_login_in)
    TextView te_ok;

    int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_bind_phone);
        StatusBarUtils.with(this).fullScreen();

        x.view().inject(this);
        setviewhw();
    }


    public void loginClick(View view) {
        String phone = phoneNumber.getText().toString();

        switch (view.getId()) {
            case R.id.ui_login_back:
            case R.id.ui_login_close:
                TimerTextView.isc = false;
                loginResult();
                break;
            case R.id.ui_get_verify_code:
                if (StrUtils.isMatchered(phone)) {
                    phoneNumber.setEnabled(false);
                    getVerifyCode(phone);
                } else {
                    toast("请输入正确的手机号");
                }
                break;
            case R.id.ui_login_in:
                String code = verifyCode.getText().toString();
                if (StrUtils.isMatchered(phone)) {
                    if (code.length() == 6) {
                        bindPhone(phone, code);
                    } else {
                        toast("请输入6位验证码");
                    }
                } else {
                    toast("请输入正确的手机号");
                }
                break;

        }
    }

    /**
     * 微信登录发送短信验证码
     *
     * @param phone
     */
    private void getVerifyCode(String phone) {
//        String url = "https://app.juandie.com/api_mobile/user.php";
        String url = HttpUrl.user;
        HashMap<String, String> maps = new HashMap<>();
        if (flag == 1) {
            maps.put("act", "app_send_sms_wechat_binding");//微信
        } else {
            maps.put("act", "app_send_sms_third_binding");//QQ，微博
        }
        maps.put("mobile_phone", phone);

        Xutils_Get_Post.getInstance().post(url, maps, new XCallBack() {

            @Override
            public void onResponse(String result) {
                JSONObject object;
                try {
                    object = new JSONObject(result);
                    int status = object.getInt("status");
                    if (status == 1) {
                        TimerTextView.isc = true;
                        String uid = object.getString("uid");
                        SharedPreferenceUtils.setPreference(LoginBindPhoneAty.this, Constant.uid, uid, "S");

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

            }
        });
    }


    private void bindPhone(String phone, String code) {
        String url = "https://app.juandie.com/api_mobile/user.php";
        HashMap<String, String> maps = new HashMap<>();
        if (flag == 1) {
            maps.put("act", "app_ajax_wechat_binding");//微信
        } else {
            maps.put("act", "third_binding");//QQ，微博
        }
        maps.put("mobile_phone", phone);
        maps.put("captcha", code);

        Xutils_Get_Post.getInstance().post(url, maps, new XCallBack() {

            @Override
            public void onResponse(String result) {
                JSONObject object;
                try {
                    object = new JSONObject(result);
                    int status = object.getInt("status");
                    if (status == 1) {
                        String uid = object.getString("uid");
                        String PHPSESSID = object.getString("PHPSESSID");
                        SharedPreferenceUtils.setPreference(LoginBindPhoneAty.this, Constant.uid, uid, "S");
                        SharedPreferenceUtils.setPreference(LoginBindPhoneAty.this, Constant.cook, PHPSESSID, "S");

                        loginResult();
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

    //绑定手机号结果
    private void loginResult() {
        //刷新首页
        UiHelper.refresh();

        //销毁当前页面
        overridePendingTransition(R.anim.push_right_out, R.anim.push_right_in);
        finish();
    }


    private void setviewhw() {
        String type = getIntent().getStringExtra("type");
        if (TextUtils.equals(type, "def")) {
            flag = 2;
        } else if (TextUtils.equals(type, "wx")) {
            flag = 1;
        }
        te_titlog.setText("绑定手机号");
        phoneNumber.setHint("请输入要绑定的手机号");
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            TimerTextView.isc = false;
            loginResult();
            return false;
        }
        return false;
    }

}
