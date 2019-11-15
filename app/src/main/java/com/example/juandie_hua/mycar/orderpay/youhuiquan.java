package com.example.juandie_hua.mycar.orderpay;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.x;
import org.xutils.common.Callback.CancelledException;
import org.xutils.view.annotation.ViewInject;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.juandie_hua.R;
import com.example.juandie_hua.ui.me.adapter.MyCouponAdapter;
import com.example.juandie_hua.app.BaseActivity;
import com.example.juandie_hua.mainactivity.Landing;
import com.example.juandie_hua.mainactivity.Xutils_Get_Post;
import com.example.juandie_hua.mainactivity.Xutils_Get_Post.XCallBack;
import com.example.juandie_hua.model.MyCoupon;
import com.example.juandie_hua.utils.DecimalUtil;
import com.example.juandie_hua.utils.StatusBarUtils;
import com.example.juandie_hua.utils.StrUtils;
import com.example.juandie_hua.utils.ViewUtils;
import com.umeng.analytics.MobclickAgent;

public class youhuiquan extends BaseActivity {

    @ViewInject(R.id.yhqn_lininput)
    LinearLayout lin_input;
    @ViewInject(R.id.yhqn_edit)
    EditText ed_yhq;
    @ViewInject(R.id.yhqn_tess)
    TextView te_ok;

    @ViewInject(R.id.yhqn_listv)
    ListView listv;
    private MyCouponAdapter ada;
    private List<MyCoupon> list;

    private String bus = "";// 优惠券号码
    private Landing landing;
    private int type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitleContentView(R.layout.youhuiquan);
        StatusBarUtils.with(this).setBarColor(R.color.white_fff);

        landing = new Landing(this, R.style.CustomDialog);
        x.view().inject(this);
        setviewhw();
        setviewdata();
        setviewlisten();
    }

    private void setviewdata() {
        Bundle data = getIntent().getExtras();
        ArrayList<String> list1 = data.getStringArrayList("yhq");
        if (!StrUtils.listIsEmpty(list1)) {
            for (int i = 0; i < list1.size(); i++) {
                String[] str = list1.get(i).split(",");
                type = DecimalUtil.string2Int(str[0]);
                MyCoupon coupon = new MyCoupon();
                coupon.setBonus_sn(str[2]);
                coupon.setType_category_name(str[5]);
                coupon.setType_money(str[1]);
                coupon.setUse_time_deadline(str[3]);
                coupon.setType_name(str[4]);
                list.add(coupon);
//                list.add(new yhq_adaData(str[0], str[1], str[2], str[3], str[4], str[5]));
            }
            ada.refresh(list, type);
//            ada.notifyDataSetChanged();
        }

    }

    private void setviewlisten() {
        listv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                useyhq_get(list.get(position).getBonus_sn());
            }
        });
        te_ok.setOnClickListener(onc);
    }

    private void setviewhw() {
        this.getTitleView().setTitleText("使用优惠券");
        listv.setSelector(new ColorDrawable(Color.TRANSPARENT));
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int w = dm.widthPixels;
        ViewUtils.setviewhw_lin(lin_input, w, (int) (w * 88 / 750.0), 0, 0, 0, 0);
        ViewUtils.setviewhw_lin(ed_yhq, (int) (w * 500 / 750.0), (int) (w * 68 / 750.0),
                (int) (w * 24 / 750.0), (int) (w * 10 / 750.0),
                (int) (w * 24 / 750.0), (int) (w * 10 / 750.0));
        ViewUtils.setviewhw_lin(te_ok, (int) (w * 168 / 750.0), (int) (w * 68 / 750.0),
                0, (int) (w * 10 / 750.0), (int) (w * 24 / 750.0),
                (int) (w * 10 / 750.0));

        list = new ArrayList<>();
        ada = new MyCouponAdapter(youhuiquan.this, list, type);
        listv.setAdapter(ada);
        // xutils_getyhq();
    }


    OnClickListener onc = new OnClickListener() {

        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id) {
                case R.id.yhqn_tess:
                    if (TextUtils.isEmpty(ed_yhq.getText().toString())) {
                        Toast.makeText(youhuiquan.this, "请输入优惠券",
                                Toast.LENGTH_SHORT).show();
                    } else
                        useyhq_get(ed_yhq.getText().toString());
                    break;

                default:
                    break;
            }
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
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


    /**
     * 使用优惠券 传入优惠券号码
     */
    private void useyhq_get(final String bonus_sn) {
        landing.show();
        String url = "https://app.juandie.com/api_mobile/flow.php?act=validate_bonus&bonus_sn=" + bonus_sn;
        Xutils_Get_Post.getInstance().get(url, new XCallBack() {

            @Override
            public void onResponse(String result) {
                landing.dismiss();
                JSONObject jso1;
                try {
                    jso1 = new JSONObject(result);
                    if (jso1.getString("status").equals("1")) {
                        bus = bonus_sn;
                        JSONObject jso = jso1.getJSONObject("data");
                        int yhq = jso.getInt("bonus");
                        Toast.makeText(youhuiquan.this,
                                "使用优惠券成功 ,优惠券金额￥" + yhq + ".00",
                                Toast.LENGTH_SHORT).show();
                        Message msg = Message.obtain();
                        msg.what = 0x002;
                        Bundle data = new Bundle();
                        data.putString("yhq", bonus_sn);
                        msg.setData(data);
                        orderin.handler.sendMessage(msg);
                        finish();
                        overridePendingTransition(R.anim.push_right_out,
                                R.anim.push_right_in);
                    } else {
                        Toast.makeText(youhuiquan.this, jso1.getString("msg"),
                                Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }

            @Override
            public void onFail(String result) {
                landing.dismiss();
            }

            @Override
            public void onCancel(CancelledException cex) {
                landing.dismiss();
            }
        });
    }

}
