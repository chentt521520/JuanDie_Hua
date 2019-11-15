package com.example.juandie_hua.percenter;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.x;
import org.xutils.common.Callback.CancelledException;
import org.xutils.view.annotation.ViewInject;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.juandie_hua.R;
import com.example.juandie_hua.app.BaseActivity;
import com.example.juandie_hua.mainactivity.Xutils_Get_Post;
import com.example.juandie_hua.mainactivity.Xutils_Get_Post.XCallBack;
import com.example.juandie_hua.mainactivity.internet_if;
import com.example.juandie_hua.mainactivity.internet_landing;
import com.example.juandie_hua.mainactivity.internet_landing.re_jk;
import com.example.juandie_hua.utils.StatusBarUtils;
import com.umeng.analytics.MobclickAgent;

public class yiJian extends BaseActivity implements re_jk {

    @ViewInject(R.id.yijian_reiterm)
    RelativeLayout re_yijian;
    @ViewInject(R.id.yijian_tecon)
    TextView te_yijian;
    @ViewInject(R.id.yijian_linitem1)
    LinearLayout lin_item1;
    @ViewInject(R.id.yijian_teit1)
    TextView te_it1;
    @ViewInject(R.id.yijian_teit2)
    TextView te_it2;
    @ViewInject(R.id.yijian_teit3)
    TextView te_it3;
    @ViewInject(R.id.yijian_linitem2)
    LinearLayout lin_item2;
    @ViewInject(R.id.yijian_teit21)
    TextView te_it21;
    @ViewInject(R.id.yijian_teit22)
    TextView te_it22;
    @ViewInject(R.id.yijian_teit23)
    TextView te_it23;

    @ViewInject(R.id.yijian_reyoux)
    RelativeLayout re_youx;
    @ViewInject(R.id.yijian_teyoux)
    TextView te_youx;
    @ViewInject(R.id.yijian_edyoux)
    EditText ed_youx;

    @ViewInject(R.id.yijian_resjh)
    RelativeLayout re_sjh;
    @ViewInject(R.id.yijian_tesjh)
    TextView te_sjh;
    @ViewInject(R.id.yijian_edsjh)
    EditText ed_sjh;

    @ViewInject(R.id.yijian_reyj)
    RelativeLayout re_yj;
    @ViewInject(R.id.yijian_teyj)
    TextView te_yj;
    @ViewInject(R.id.yijian_edyj)
    EditText ed_yj;

    @ViewInject(R.id.yijian_tetjiao)
    TextView te_tj;

    String PHONE_PATTERN = "^(1)\\d{10}$";

    internet_landing inLanding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setTitleContentView(R.layout.yijian);
        StatusBarUtils.with(this).setBarColor(R.color.white_fff);

        x.view().inject(this);

        inLanding = new internet_landing(this);
        inLanding.setonc(this);
        setviewhw();
        setviewlisten();
    }

    private void setviewlisten() {
        te_it1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!te_it1.isSelected()) {
                    setselect_view(1);
                }
            }
        });
        te_it2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!te_it2.isSelected()) {
                    setselect_view(2);
                }
            }
        });
        te_it3.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!te_it3.isSelected()) {
                    setselect_view(3);
                }
            }
        });
        te_it21.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!te_it21.isSelected()) {
                    setselect_view(4);
                }
            }
        });
        te_it22.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!te_it22.isSelected()) {
                    setselect_view(5);
                }
            }
        });
        te_it23.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!te_it23.isSelected()) {
                    setselect_view(6);
                }
            }
        });

        te_tj.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(ed_yj.getText().toString())) {
                    boolean t = true, tx = true;
                    if (!TextUtils.isEmpty(ed_youx.getText().toString())) {

                        if (isEmail(ed_youx.getText().toString())) {
                            // youx = ed_youx.getText().toString();
                            t = true;
                        } else {
                            Toast.makeText(yiJian.this, "邮箱格式错误",
                                    Toast.LENGTH_SHORT).show();
                            // youx = "空";
                            t = false;
                        }
                    }
                    if (!TextUtils.isEmpty(ed_sjh.getText().toString())) {
                        if (isMatchered(PHONE_PATTERN, ed_sjh.getText()
                                .toString())) {
                            // sjh = ed_sjh.getText().toString();
                            tx = true;
                        } else {
                            Toast.makeText(yiJian.this, "手机号错误",
                                    Toast.LENGTH_SHORT).show();
                            // sjh = "空";
                            tx = false;
                        }
                    } else {
                        tx = false;
                        Toast.makeText(yiJian.this, "请输入手机号",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (tx) {
                        inLanding.showlanding();
                        if (t) {
                            tj(ed_youx.getText().toString(), ed_sjh.getText()
                                            .toString(), ed_yj.getText().toString(),
                                    getitemstr());
                        } else {
                            tj("", ed_sjh.getText().toString(), ed_yj.getText()
                                    .toString(), getitemstr());
                        }
                    }
                } else
                    Toast.makeText(yiJian.this, "请输入反馈内容", Toast.LENGTH_SHORT)
                            .show();

            }
        });

    }

    private void tj(String email, String mobile, String content, String type) {
        String url = "https://app.juandie.com/api_mobile/user.php";
        Map<String, String> maps = new HashMap<>();
        maps.put("act", "feedback_add");
        maps.put("email", email);
        maps.put("mobile", mobile);
        maps.put("content", content);
        maps.put("type", type);

        Xutils_Get_Post.getInstance().post(url, maps, new XCallBack() {

            @Override
            public void onResponse(String result) {
                inLanding.dismisslanding();
                JSONObject response;
                try {
                    response = new JSONObject(result);
                    if (response.getString("status").equals("1")) {
                        // TODO Auto-generated method stub
                        Toast.makeText(yiJian.this, "意见反馈已提交，感谢你的建议",
                                Toast.LENGTH_SHORT).show();
                        finish();
                        overridePendingTransition(R.anim.push_right_out,
                                R.anim.push_right_in);
                    }
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

    private void setviewhw() {
        this.getTitleView().setTitleText("意见反馈");
        te_it1.setSelected(true);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int w = dm.widthPixels;
        setviewhw_lin(re_yijian, w, (int) (w * 130 / 375.0), 0, 0, 0, 0);
        re_yijian.setPadding((int) (w * 15 / 375.0), (int) (w * 15 / 375.0),
                (int) (w * 12 / 375.0), (int) (w * 12 / 375.0));
        te_yijian.setPadding(0, (int) (w * 5 / 375.0), (int) (w * 12 / 375.0),
                0);
        setviewhw_re(lin_item1, (int) (w * 255 / 375.0),
                (int) (w * 35 / 375.0), (int) (w * 70 / 375.0), 0, 0,
                (int) (w * 10 / 375.0));
        setviewhw_re(lin_item2, (int) (w * 255 / 375.0),
                (int) (w * 35 / 375.0), (int) (w * 70 / 375.0),
                (int) (w * 50 / 375.0), 0, (int) (w * 8 / 375.0));

        setviewhw_lin(re_youx, w, (int) (w * 38 / 375.0), 0, 0, 0, 0);
        te_youx.setPadding((int) (w * 14 / 375.0), 0, 0, 0);
        setviewhw_lin(re_sjh, w, (int) (w * 38 / 375.0), 0, 0, 0, 0);
        te_sjh.setPadding((int) (w * 14 / 375.0), 0, 0, 0);

        setviewhw_lin(re_yj, w, (int) (w * 78 / 375.0), 0, 0, 0, 0);
        re_yj.setPadding(0, (int) (w * 14 / 375.0), 0, 0);
        te_yj.setPadding((int) (w * 14 / 375.0), 0, 0, 0);

        setviewhw_lin(te_tj, (int) (w * 345 / 375.0), (int) (w * 40 / 375.0),
                (int) (w * 14 / 375.0), (int) (w * 14 / 375.0), 0, 0);
    }

    public static boolean isEmail(String email) {
        if (null == email || "".equals(email))
            return false;
        // Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //简单匹配
        Pattern p = Pattern
                .compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");// 复杂匹配
        Matcher m = p.matcher(email);
        return m.matches();
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

    private String getitemstr() {
        String str = "";
        if (te_it1.isSelected()) {
            str = "1";
        } else if (te_it2.isSelected()) {
            str = "2";
        } else if (te_it3.isSelected()) {
            str = "3";
        } else if (te_it21.isSelected()) {
            str = "4";
        } else if (te_it22.isSelected()) {
            str = "5";
        } else if (te_it23.isSelected()) {
            str = "6";
        }
        return str;
    }

    public void setselect_view(int i) {
        switch (i) {
            case 1:
                te_it1.setSelected(true);
                te_it2.setSelected(false);
                te_it3.setSelected(false);
                te_it21.setSelected(false);
                te_it22.setSelected(false);
                te_it23.setSelected(false);
                break;
            case 2:
                te_it2.setSelected(true);
                te_it1.setSelected(false);
                te_it3.setSelected(false);
                te_it21.setSelected(false);
                te_it22.setSelected(false);
                te_it23.setSelected(false);
                break;
            case 3:
                te_it3.setSelected(true);
                te_it2.setSelected(false);
                te_it1.setSelected(false);
                te_it21.setSelected(false);
                te_it22.setSelected(false);
                te_it23.setSelected(false);
                break;

            case 4:
                te_it21.setSelected(true);
                te_it2.setSelected(false);
                te_it3.setSelected(false);
                te_it1.setSelected(false);
                te_it22.setSelected(false);
                te_it23.setSelected(false);
                break;
            case 5:
                te_it22.setSelected(true);
                te_it2.setSelected(false);
                te_it3.setSelected(false);
                te_it21.setSelected(false);
                te_it1.setSelected(false);
                te_it23.setSelected(false);
                break;
            case 6:
                te_it23.setSelected(true);
                te_it2.setSelected(false);
                te_it3.setSelected(false);
                te_it21.setSelected(false);
                te_it22.setSelected(false);
                te_it1.setSelected(false);
                break;

            default:
                break;
        }
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

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    public void re_requestjk(View v) {
        if (new internet_if().isNetworkConnected(this)) {
            inLanding.dismissinter();
        } else {
            Toast.makeText(this, "网络连接失败,请设置网络", Toast.LENGTH_SHORT).show();
        }
    }

}
