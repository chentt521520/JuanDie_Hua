package com.example.juandie_hua.percenter.seting;

import java.lang.ref.WeakReference;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.x;
import org.xutils.common.Callback.CancelledException;
import org.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
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
import com.example.juandie_hua.app.Constant;
import com.example.juandie_hua.helper.UiHelper;
import com.example.juandie_hua.mainactivity.Fengmian;
import com.example.juandie_hua.ui.MainActivity;
import com.example.juandie_hua.ui.tab.Me;
import com.example.juandie_hua.mainactivity.Xutils_Get_Post;
import com.example.juandie_hua.mainactivity.Xutils_Get_Post.XCallBack;
import com.example.juandie_hua.percenter.TimerTextView;
import com.example.juandie_hua.utils.SharedPreferenceUtils;
import com.example.juandie_hua.utils.StatusBarUtils;
import com.example.juandie_hua.utils.StrUtils;

public class wx_bdgh extends Activity {
    @ViewInject(R.id.wx_retop)
    RelativeLayout re_top;
    @ViewInject(R.id.wx_imreturn)
    ImageView im_return;
    @ViewInject(R.id.wx_tetit)
    TextView te_tit;

    @ViewInject(R.id.wx_linsjh)
    LinearLayout lin_wxsj;
    @ViewInject(R.id.wx_etsjf)
    TextView te_phfu;
    @ViewInject(R.id.wx_edpho)
    EditText ed_phfu;

    @ViewInject(R.id.wx_linsjh01)
    LinearLayout lin_wxyzm;
    @ViewInject(R.id.wx_etsjf01)
    TextView te_yzm;
    @ViewInject(R.id.wx_edpho01)
    EditText ed_yzm;
    @ViewInject(R.id.wx_tegetyzm)
    TimerTextView te_getyzm;

    @ViewInject(R.id.wx_linsjh1)
    LinearLayout lin_wxsj2;
    @ViewInject(R.id.wx_etsjf1)
    TextView te_phfu2;
    @ViewInject(R.id.wx_edpho1)
    EditText ed_phfu2;

    @ViewInject(R.id.wx_linsjh02)
    LinearLayout lin_wxyzm2;
    @ViewInject(R.id.wx_etsjf02)
    TextView te_yzm2;
    @ViewInject(R.id.wx_edpho02)
    EditText ed_yzm2;
    @ViewInject(R.id.wx_tegetyzm1)
    TimerTextView te_getyzm2;

    @ViewInject(R.id.wx_next)
    TextView te_next;

    @ViewInject(R.id.view1)
    View v1;
    @ViewInject(R.id.view2)
    View v2;
    @ViewInject(R.id.view3)
    View v3;
    @ViewInject(R.id.view4)
    View v4;

    String type = "", pho = "", typeqd = "";

    MyHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wx_bdgh);
        StatusBarUtils.with(this).setBarColor(R.color.white_fff);

        x.view().inject(this);
        handler = new MyHandler(wx_bdgh.this);
        setviewhw();
        setviewlisten();
    }

    private void setviewlisten() {
        im_return.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                TimerTextView.isc = false;
                finish();
                overridePendingTransition(R.anim.push_right_out,
                        R.anim.push_right_in);
            }
        });
        te_getyzm2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(ed_phfu2.getText().toString())) {
                    if (StrUtils.isMatchered(ed_phfu2.getText().toString())) {
                        if (type.equals("2")) {
                            httpPost_getcodex(ed_phfu2.getText().toString());
                        } else
                            httpPost_getcode(ed_phfu2.getText().toString());
                    } else {
                        Toast.makeText(wx_bdgh.this, "请输入正确的手机号", Toast.LENGTH_LONG).show();
                    }
                } else
                    Toast.makeText(wx_bdgh.this, "请输入手机号", Toast.LENGTH_LONG).show();
            }
        });

        te_getyzm.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                TimerTextView.isc = true;
                String phone = (String) SharedPreferenceUtils.getPreference(wx_bdgh.this, Constant.pho1, "S");
                httpPost_getcode0(phone);
            }
        });

        te_next.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (type.equals("2")) {// 绑定
                    if (!TextUtils.isEmpty(ed_phfu2.getText().toString())) {

                        if (!TextUtils.isEmpty(ed_yzm2.getText().toString())) {
                            httpPost_longin2(ed_phfu2.getText().toString(),
                                    ed_yzm2.getText().toString());
                        } else
                            Toast.makeText(wx_bdgh.this, "请输入6位验证码",
                                    Toast.LENGTH_LONG).show();
                    } else
                        Toast.makeText(wx_bdgh.this, "请输入正确的手机号",
                                Toast.LENGTH_LONG).show();
                } else {
                    String phone = (String) SharedPreferenceUtils.getPreference(wx_bdgh.this, Constant.pho1, "S");

                    if (te_next.getText().toString().equals("下一步")) {
                        if (!TextUtils.isEmpty(ed_yzm.getText().toString())) {
                            httpPost_longin0(phone, ed_yzm.getText().toString());

                        } else
                            Toast.makeText(wx_bdgh.this, "请输入6位验证码",
                                    Toast.LENGTH_LONG).show();
                    } else {
                        if (!TextUtils.isEmpty(ed_phfu2.getText().toString())) {

                            if (!TextUtils.isEmpty(ed_yzm2.getText().toString())) {
                                if (TextUtils.equals(phone, ed_phfu2.getText().toString())) {
                                    Toast.makeText(wx_bdgh.this,
                                            "不能使用原来绑定的手机号", Toast.LENGTH_SHORT)
                                            .show();
                                } else
                                    httpPost_longinx(ed_phfu2.getText()
                                            .toString(), ed_yzm2.getText()
                                            .toString());
                            } else
                                Toast.makeText(wx_bdgh.this, "请输入6位验证码",
                                        Toast.LENGTH_LONG).show();
                        } else
                            Toast.makeText(wx_bdgh.this, "请输入正确的手机号",
                                    Toast.LENGTH_LONG).show();

                    }

                }
            }
        });

    }

    private void setviewhw() {
        Intent i = getIntent();
        type = i.getStringExtra("type");
//        typeqd = i.getStringExtra("typeqd");

        typeqd = (String) SharedPreferenceUtils.getPreference(wx_bdgh.this, Constant.typeqd, "S");
        if (type.equals("2")) {// 绑定
            te_tit.setText("绑定手机号");
            lin_wxsj.setVisibility(View.GONE);
            lin_wxyzm.setVisibility(View.GONE);
            v1.setVisibility(View.GONE);
            v2.setVisibility(View.GONE);
            te_next.setText("确定");
        } else {
            lin_wxsj2.setVisibility(View.GONE);
            lin_wxyzm2.setVisibility(View.GONE);
            v3.setVisibility(View.GONE);
            v4.setVisibility(View.GONE);
        }
        pho = (String) SharedPreferenceUtils.getPreference(wx_bdgh.this, Constant.pho, "S");
        String pho1 = pho.substring(3, 8);
        pho = pho.replace(pho1, "***");
        ed_phfu.setText(pho);

        DisplayMetrics dm = getResources().getDisplayMetrics();
        int w = dm.widthPixels;
        setviewhw_lin(re_top, w, (int) (w * 55 / 450.0), 0, 0, 0, 0);
        re_top.setBackgroundResource(R.drawable.biankuangxnew);

        setviewhw_re(im_return, (int) (w * 49 / 450.0), (int) (w * 25 / 450.0),
                0, (int) (w * 15 / 450.0), 0, 0);
        im_return.setPadding((int) (w * 12 / 450.0), 0, (int) (w * 12 / 450.0),
                0);

        setviewhw_lin(lin_wxsj, w, (int) (w * 88 / 750.0), 0,
                (int) (w * 0 / 750.0), 0, 0);
        setviewhw_lin(te_phfu, (int) (w * 200 / 750.0), (int) (w * 88 / 750.0),
                0, 0, 0, 0);
        setviewhw_lin(lin_wxyzm, w, (int) (w * 88 / 750.0), 0,
                (int) (w * 0 / 750.0), 0, 0);
        setviewhw_lin(te_yzm, (int) (w * 200 / 750.0), (int) (w * 88 / 750.0),
                0, 0, 0, 0);
        setviewhw_lin(ed_yzm, (int) (w * 300 / 750.0), (int) (w * 88 / 750.0),
                0, 0, 0, 0);
        setviewhw_lin(te_getyzm, (int) (w * 200 / 750.0),
                (int) (w * 60 / 750.0), 0, 0, (int) (w * 60 / 750.0),
                (int) (w * 14 / 750.0));

        setviewhw_lin(lin_wxsj2, w, (int) (w * 88 / 750.0), 0,
                (int) (w * 0 / 750.0), 0, 0);
        setviewhw_lin(te_phfu2, (int) (w * 200 / 750.0),
                (int) (w * 88 / 750.0), 0, 0, 0, 0);
        setviewhw_lin(lin_wxyzm2, w, (int) (w * 88 / 750.0), 0,
                (int) (w * 0 / 750.0), 0, 0);
        setviewhw_lin(te_yzm2, (int) (w * 200 / 750.0), (int) (w * 88 / 750.0),
                0, 0, 0, 0);
        setviewhw_lin(ed_yzm2, (int) (w * 300 / 750.0), (int) (w * 88 / 750.0),
                0, 0, 0, 0);
        setviewhw_lin(te_getyzm2, (int) (w * 200 / 750.0),
                (int) (w * 60 / 750.0), 0, 0, (int) (w * 60 / 750.0),
                (int) (w * 14 / 750.0));

        setviewhw_lin(te_next, w - (int) (w * 120 / 750.0),
                (int) (w * 80 / 750.0), (int) (w * 60 / 750.0),
                (int) (w * 30 / 750.0), 0, (int) (w * 30 / 750.0));

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

    public static class MyHandler extends Handler {
        WeakReference<wx_bdgh> referenceObj;

        public MyHandler(wx_bdgh loginphowx) {
            referenceObj = new WeakReference<wx_bdgh>(loginphowx);
        }

        @Override
        public void handleMessage(Message msg) {
            wx_bdgh activity = referenceObj.get();
            switch (msg.what) {
                case 0x001:
                    activity.te_next.setText("确定");
                    activity.lin_wxsj.setVisibility(View.GONE);
                    activity.lin_wxyzm.setVisibility(View.GONE);
                    activity.lin_wxsj2.setVisibility(View.VISIBLE);
                    activity.lin_wxyzm2.setVisibility(View.VISIBLE);
                    activity.v3.setVisibility(View.GONE);
                    activity.v4.setVisibility(View.GONE);

                    break;
                default:
                    break;
            }
        }
    }

    String seeid = "";
    String sign = "";

    /**
     * 发送短信验证码
     *
     * @param phonenumber
     */
    public void httpPost_getcode(String phonenumber) {
        HashMap<String, String> maps = new HashMap<>();
        if ("dsf".equals(typeqd)) {
            maps.put("act", "app_ajax_third_change_binding");
        } else
            maps.put("act", "app_send_sms_wechat_change_binding");
        maps.put("mobile_phone", phonenumber);

        String url = "https://app.juandie.com/api_mobile/user.php";
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
                        SharedPreferenceUtils.setPreference(wx_bdgh.this, Constant.uid, uid, "S");
                        Fengmian.uid = uid;
                        seeid = object.getString("PHPSESSID");

                        Toast.makeText(wx_bdgh.this, "验证码已发送到手机，请注意查收", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(wx_bdgh.this, object.getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
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

    public void httpPost_getcodex(String phonenumber) {
        HashMap<String, String> maps = new HashMap<>();

        if (typeqd != null) {
            if (typeqd.equals("dsf")) {
                maps.put("act", "app_send_sms_third_binding");
            } else
                maps.put("act", "app_send_sms_wechat_binding");
        }
        maps.put("mobile_phone", phonenumber);
        String url = "https://app.juandie.com/api_mobile/user.php";
        Xutils_Get_Post.getInstance().post(url, maps, new XCallBack() {

            @Override
            public void onResponse(String result) {
                JSONObject object;
                try {
                    object = new JSONObject(result);
                    int status = object.getInt("status");
                    if (status == 1) {
                        String uid = object.getString("uid");
                        SharedPreferenceUtils.setPreference(wx_bdgh.this, Constant.uid, uid, "S");
                        Fengmian.uid = uid;
                        seeid = object.getString("PHPSESSID");

                        Toast.makeText(wx_bdgh.this, "验证码已发送到手机，请注意查收",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(wx_bdgh.this, object.getString("msg"),
                                Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
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

    /**
     * 短信验证码登录
     *
     * @param phonenumber
     */
    public void httpPost_longin2(String phonenumber, String captcha) {
        HashMap<String, String> maps = new HashMap<>();

        if (typeqd.equals("dsf")) {
            maps.put("act", "third_binding");
        } else
            maps.put("act", "app_ajax_wechat_binding");
        maps.put("mobile_phone", phonenumber);
        maps.put("captcha", captcha);
        String url = "https://app.juandie.com/api_mobile/user.php";
        Xutils_Get_Post.getInstance().post(url, maps, new XCallBack() {

            @Override
            public void onResponse(String result) {
                JSONObject object;
                try {
                    object = new JSONObject(result);
                    int status = object.getInt("status");
                    if (status == 1) {
                        Toast.makeText(wx_bdgh.this, object.getString("msg"), Toast.LENGTH_SHORT).show();

                        String uid = object.getString("uid");
                        SharedPreferenceUtils.setPreference(wx_bdgh.this, Constant.uid, uid, "S");
                        Fengmian.uid = uid;
                        SharedPreferenceUtils.setPreference(wx_bdgh.this, Constant.cook, object.getString("PHPSESSID"), "S");
                        SharedPreferenceUtils.setPreference(wx_bdgh.this, Constant.iswxbd, "1", "S");//已绑定

                        MainActivity.handler.sendEmptyMessage(0x004);
                        Me.handler.sendEmptyMessage(0x003);
                        startActivity(new Intent(wx_bdgh.this,
                                MainActivity.class));
                        overridePendingTransition(R.anim.push_right_out,
                                R.anim.push_right_in);
                        finish();
                    } else {
                        Toast.makeText(wx_bdgh.this, object.getString("msg"),
                                Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
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

    public void httpPost_longinx(String phonenumber, String captcha) {
        HashMap<String, String> maps = new HashMap<>();
        if ("dsf".equals(typeqd)) {
            maps.put("act", "app_send_sms_thrid_change_binding");
        } else
            maps.put("act", "app_ajax_wechat_change_binding");
        maps.put("mobile_phone", phonenumber);
        maps.put("captcha", captcha);
        String url = "https://app.juandie.com/api_mobile/user.php";
        Xutils_Get_Post.getInstance().post(url, maps, new XCallBack() {

            @Override
            public void onResponse(String result) {
                JSONObject object;
                try {
                    object = new JSONObject(result);
                    int status = object.getInt("status");
                    if (status == 1) {
                        Toast.makeText(wx_bdgh.this, object.getString("msg"), Toast.LENGTH_SHORT).show();

                        String uid = object.getString("uid");
                        SharedPreferenceUtils.setPreference(wx_bdgh.this, Constant.uid, uid, "S");
                        Fengmian.uid = uid;
                        SharedPreferenceUtils.setPreference(wx_bdgh.this, Constant.cook, object.getString("PHPSESSID"), "S");
                        SharedPreferenceUtils.setPreference(wx_bdgh.this, Constant.iswxbd, "1", "S");//已绑定

                        MainActivity.handler.sendEmptyMessage(0x004);
                        Me.handler.sendEmptyMessage(0x003);

                        UiHelper.toMainActivity(wx_bdgh.this);
                    } else {
                        Toast.makeText(wx_bdgh.this, object.getString("msg"),
                                Toast.LENGTH_SHORT).show();
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

    public void httpPost_getcode0(String phonenumber) {

        HashMap<String, String> maps = new HashMap<>();
        maps.put("act", "app_send_sms_login");
        maps.put("mobile_phone", phonenumber);

        String url = "https://app.juandie.com/api_mobile/user.php";

        Xutils_Get_Post.getInstance().post(url, maps, new XCallBack() {

            @Override
            public void onResponse(String result) {
                JSONObject object;
                try {
                    object = new JSONObject(result);
                    int status = object.getInt("status");
                    if (status == 1) {
                        String uid = object.getString("uid");
                        SharedPreferenceUtils.setPreference(wx_bdgh.this, Constant.uid, uid, "S");
                        Fengmian.uid = uid;
                        seeid = object.getString("PHPSESSID");

                        Toast.makeText(wx_bdgh.this, "验证码已发送到手机，请注意查收",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(wx_bdgh.this, object.getString("msg"),
                                Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
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

    /**
     * 短信验证码登录
     *
     * @param phonenumber
     */
    public void httpPost_longin0(String phonenumber, String captcha) {
        HashMap<String, String> maps = new HashMap<>();
        maps.put("act", "app_ajax_quick_login");
        maps.put("mobile_phone", phonenumber);
        maps.put("captcha", captcha);

        String url = "https://app.juandie.com/api_mobile/user.php";
        Xutils_Get_Post.getInstance().post(url, maps, new XCallBack() {

            @Override
            public void onResponse(String result) {
                JSONObject object;
                try {
                    object = new JSONObject(result);
                    int status = object.getInt("status");
                    if (status == 1) {
                        String uid = object.getString("uid");
                        SharedPreferenceUtils.setPreference(wx_bdgh.this, Constant.uid, uid, "S");
                        Fengmian.uid = uid;

                        handler.sendEmptyMessage(0x01);
                    } else {
                        Toast.makeText(wx_bdgh.this, object.getString("msg"), Toast.LENGTH_SHORT).show();
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
}
