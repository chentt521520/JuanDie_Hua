package com.example.juandie_hua.ui.me;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.x;
import org.xutils.common.Callback.CancelledException;
import org.xutils.view.annotation.ViewInject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.juandie_hua.R;
import com.example.juandie_hua.ui.MainActivity;
import com.example.juandie_hua.ui.me.adapter.MyCouponAdapter;
import com.example.juandie_hua.app.BaseActivity;
import com.example.juandie_hua.app.HttpUrl;
import com.example.juandie_hua.mainactivity.Xutils_Get_Post;
import com.example.juandie_hua.mainactivity.Xutils_Get_Post.XCallBack;
import com.example.juandie_hua.model.MyCoupon;
import com.example.juandie_hua.utils.StatusBarUtils;
import com.example.juandie_hua.utils.StrUtils;
import com.example.juandie_hua.utils.ViewUtils;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的优惠券
 */
public class MyCouponActivity extends BaseActivity {

    @ViewInject(R.id.wodyhq_linte)
    LinearLayout lin_te;
    @ViewInject(R.id.wodyhq_tewsy)
    TextView te_wsy;
    @ViewInject(R.id.wodyhq_teysy)
    TextView te_ysy;
    @ViewInject(R.id.wodyhq_teygq)
    TextView te_ygq;

    @ViewInject(R.id.wodyhq__hen1)
    View view_wsy;
    @ViewInject(R.id.wodyhq__hen2)
    View view_ysy;
    @ViewInject(R.id.wodyhq__hen3)
    View view_ygq;

    @ViewInject(R.id.wodyhq_fragment)
    FrameLayout frg_lauout;

    MyCouponAdapter ada;
    List<MyCoupon> list;
    @ViewInject(R.id.yhqfr1_listv)
    ListView listv_v;

    @ViewInject(R.id.nogoods_lintt)
    LinearLayout layout_nogoodsl;
    @ViewInject(R.id.yhqfr1_nogoods)
    LinearLayout layout_nogoods;
    @ViewInject(R.id.nogoods_tecon)
    TextView te_no;
    @ViewInject(R.id.nogoods_tego)
    TextView te_no1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitleContentView(R.layout.activity_my_coupon);
        StatusBarUtils.with(this).setBarColor(R.color.white_fff);

        x.view().inject(this);
        setviewhw();
        setviewlisten();
    }

    private void setviewhw() {
        setSelect(1);

        this.getTitleView().setTitleText("我的优惠券");

        DisplayMetrics dm = getResources().getDisplayMetrics();
        int w = dm.widthPixels;

        ViewUtils.setviewhw_lin(lin_te, w, (int) (w * 44 / 375.0), 0, 0, 0, 0);
        ViewUtils.setviewhw_lin(layout_nogoodsl, w, (int) (w * 180 / 375.0), 0, (int) (w * 100 / 375.0), 0, 0);
        list = new ArrayList<>();
        ada = new MyCouponAdapter(this, list, 1);
        listv_v.setAdapter(ada);
        te_no.setText("暂时还没有优惠券");
        te_no1.setText("获取优惠券");
        te_no1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                MainActivity.handler.sendEmptyMessage(0x001);
                finish();
                overridePendingTransition(R.anim.push_right_out, R.anim.push_right_in);
            }
        });
    }

    private void setviewlisten() {
        te_wsy.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!te_wsy.isSelected()) {
                    setSelect(1);
                }
            }
        });
        te_ysy.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!te_ysy.isSelected()) {
                    setSelect(2);
                }
            }
        });
        te_ygq.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!te_ygq.isSelected()) {
                    setSelect(3);
                }
            }
        });
    }

    private void replaceFrg(int type, Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(frg_lauout.getId(), fragment);
        ft.commit();
        setSelect(type);
    }

    private void setSelect(int i) {
        te_wsy.setSelected(i == 1);
        view_wsy.setSelected(i == 1);
        te_ysy.setSelected(i == 2);
        view_ysy.setSelected(i == 2);
        te_ygq.setSelected(i == 3);
        view_ygq.setSelected(i == 3);

        getMyCoupon(i);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            finish();
            overridePendingTransition(R.anim.push_right_out,
                    R.anim.push_right_in);
            return false;
        }
        return false;
    }

    private void getMyCoupon(final int type) {
//        String url = "https://app.juandie.com/api_mobile/user.php?act=bonus&status=" + type;
        String url = HttpUrl.user + "act=" + HttpUrl.bonus + "&status=" + type;

        Xutils_Get_Post.getInstance().get(url, new XCallBack() {

            @Override
            public void onResponse(String result) {
                JSONObject jso1;
                try {
                    jso1 = new JSONObject(result);
                    if (jso1.getString("status").equals("1")) {
                        JSONObject data = jso1.getJSONObject("data");
                        te_wsy.setText(String.format(getResources().getString(R.string.unuse_coupon), data.getString("count1")));
                        te_ysy.setText(getString((R.string.used_coupon), data.getString("count2")));
                        te_ygq.setText(getString((R.string.out_date_coupon), data.getString("count3")));

                        list = JSON.parseArray(data.getString("bonus_list"), MyCoupon.class);
                    } else {
                        toast(jso1.getString("msg"));
                    }
                    ada.refresh(list, type);
                    showContant();
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

    /**
     * 没有商品的显示与反之关闭
     */
    public void showContant() {
        layout_nogoods.setVisibility(StrUtils.listIsEmpty(list) ? View.VISIBLE : View.GONE);
    }


    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
