package com.example.juandie_hua.ui.order;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.juandie_hua.R;
import com.example.juandie_hua.app.BaseActivity;
import com.example.juandie_hua.helper.UiHelper;
import com.example.juandie_hua.mainactivity.Landing;
import com.example.juandie_hua.mainactivity.Xutils_Get_Post;
import com.example.juandie_hua.mainactivity.Xutils_Get_Post.XCallBack;
import com.example.juandie_hua.mainactivity.goods.MyListView;
import com.example.juandie_hua.model.UserAddress;
import com.example.juandie_hua.mycar.orderpay.SendDateSelect;
import com.example.juandie_hua.mycar.orderpay.beizu;
import com.example.juandie_hua.mycar.orderpay.dayfei_;
import com.example.juandie_hua.mycar.orderpay.gonhao;
import com.example.juandie_hua.mycar.orderpay.heka;
import com.example.juandie_hua.mycar.orderpay.order_spada;
import com.example.juandie_hua.mycar.orderpay.order_spadadata;
import com.example.juandie_hua.mycar.orderpay.pay_choose;
import com.example.juandie_hua.mycar.orderpay.youhuiquan;
import com.example.juandie_hua.mycar.shouhuodizi.gridv_adaData;
import com.example.juandie_hua.utils.StatusBarUtils;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback.CancelledException;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class CreateOrder extends BaseActivity {

    private CreateOrder activity;
    @ViewInject(R.id.ui_order_create_address)
    TextView orderAddress;

    @ViewInject(R.id.orderin_tedzts)
    TextView te_dzts;
    @ViewInject(R.id.orderin_tepsts)
    TextView te_psts;// 快点配送提示

    @ViewInject(R.id.orderin_teps)
    TextView te_ps;

    @ViewInject(R.id.orderin_yhq)
    LinearLayout lin_yhq;
    @ViewInject(R.id.orderin_teyhq)
    TextView te_yhq;

    @ViewInject(R.id.orderin_tehk)
    TextView te_hk;
    @ViewInject(R.id.orderin_tehkxx)
    TextView te_hkxx;

    @ViewInject(R.id.orderin_bz)
    LinearLayout lin_bz;
    @ViewInject(R.id.orderin_tebz)
    TextView te_bz;
    @ViewInject(R.id.orderin_tebzxx)
    TextView te_bzxx;

    @ViewInject(R.id.orderin_tekf)
    TextView te_kf;

    @ViewInject(R.id.orderin_mylisv)
    MyListView mylistv;

    @ViewInject(R.id.orderin_price)
    TextView te_price;
    @ViewInject(R.id.orderin_ok)
    TextView te_ok;

    @ViewInject(R.id.orderin_useyhq)
    RelativeLayout lin_useyhq;
    @ViewInject(R.id.orderin_useyhqtep)
    TextView te_useyhqp;

    @ViewInject(R.id.orderin_usepsf)
    RelativeLayout lin_usepsf;
    @ViewInject(R.id.orderin_usepsftep)
    TextView te_usepsfp;

    @ViewInject(R.id.orderin_usedsf)
    RelativeLayout lin_usedsf;
    @ViewInject(R.id.orderin_usedsftep)
    TextView te_usedsfp;

    @ViewInject(R.id.orderin_botq)
    // 新增点击获取折扣布局
            LinearLayout lin_botq;
    @ViewInject(R.id.orderin_check)
    CheckBox cheBox;
    @ViewInject(R.id.orderin_zkou)
    TextView te_zkou;
    @ViewInject(R.id.orderin_zkjian)
    TextView te_zkounum;

    @ViewInject(R.id.orderin_viewhen1)
    View view_hen;

    order_spada ada;
    List<order_spadadata> list;
    public static MyHandler handler;

    String address_id = "0";

    Landing landing;
    String[] address = new String[10];// 下标6代表地址id
    ArrayList<String> list_yhqstr = new ArrayList<>();
    ArrayList<String> list_gh = new ArrayList<>();
    List<gridv_adaData> list_ghi = new ArrayList<>();

    String[] yunfei_str = {"0", "0", "0"};// 保存运费接口选择的参数,0配送费，1定时费

    /**
     * 0收花人姓名,1收花人手机号,2固定值:1,3具体街道地址,4固定值:1,
     * 5订花人姓名,6订花人手机,7优惠券号码,8910省市区id,11配送费
     * 12定时费,13贺卡14备注15工号,16配送日期,17配送时段类型 例如:“时段” 或者“定时”
     * 18time_hour,19定时配送分钟,20配送费,21是否定时0不定时1定时 ,
     * 22配送具体时段23order_type=4,24订单总价
     */
    String[] order_c = new String[25];

    // 0，1,2表示省市区id；3表示配送费下标；4时段数据，5表示定时数据；6表示选择的时间7表示选择的日期
    String[] riqi = {0 + "", 0 + "", 0 + "", 1 + "", 0 + "", 0 + "", "0", "0", "0"};
    int w = 0;
    String jieri = "0";// 1的时候是节日价格需要提示

    String year = "", mouth = "", day = "";
    String use_flow_use_rank_discount = "0";
    int gonghao = 999;// 判断工号选中位置 999代表默认客服


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitleContentView(R.layout.activity_create_order);
        StatusBarUtils.with(this).setBarColor(R.color.white_fff);

        activity = this;
        x.view().inject(this);
        handler = new MyHandler(CreateOrder.this);
        landing = new Landing(CreateOrder.this, R.style.CustomDialog);
        setviewhw();
        setviewdata();
        setviewlisten();
    }

    private void setviewlisten() {

        cheBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    use_flow_use_rank_discount = "1";
                    xutils_getinfo();
                } else {
                    use_flow_use_rank_discount = "0";
                    xutils_getinfo();
                }
            }
        });
    }

    public void createOrderClick(View view) {
        switch (view.getId()) {
            case R.id.ui_order_create_address_view://配送地址选择
                UiHelper.toAddressActivity(activity, address1);

                break;
            case R.id.ui_order_create_send_time_view://配送时间选择
                UiHelper.toSendTimeSelect(activity);
                break;
            case R.id.ui_order_create_coupon_view://优惠券选择
                // 传递list_yhqstr
                UiHelper.toCouponActivity(activity);
                break;
            case R.id.ui_order_create_message_view://贺卡留言
                UiHelper.toMessageActivity(activity);

                break;
            case R.id.ui_order_create_remark_view://备注
                UiHelper.toRamarkActivity(activity);

                break;
            case R.id.ui_order_create_service_number_view://选择客服编号
                Intent ix_kf = new Intent();
                ix_kf.setClass(CreateOrder.this, gonhao.class);
                ix_kf.putStringArrayListExtra("kf", list_gh);
                ix_kf.putExtra("pos", gonghao);
                startActivity(ix_kf);
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;

            case R.id.orderin_ok://提交订单
                if (TextUtils.isEmpty(order_c[8])) {
                    Toast.makeText(CreateOrder.this, "请填写配送信息", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    if (te_ps.getText().toString().equals("请填写配送时间信息(必填)")) {
                        Toast.makeText(CreateOrder.this, "请填写配送时间",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Intent ix_pay = new Intent();
                        Bundle bundlepay = new Bundle();
                        bundlepay.putStringArray("data", order_c);
                        ix_pay.putExtras(bundlepay);
                        ix_pay.setClass(CreateOrder.this, pay_choose.class);
                        startActivity(ix_pay);
                        overridePendingTransition(R.anim.push_left_in,
                                R.anim.push_left_out);
                    }
                }
                break;

        }
    }

    private void setviewdata() {
        list = new ArrayList<>();
        ada = new order_spada(CreateOrder.this, list);
        mylistv.setAdapter(ada);

        xutils_getinfo();

        order_c[13] = "";
        order_c[14] = "";
    }

    private void setviewhw() {
        this.getTitleView().setTitleText("确认订单");

        Calendar c = Calendar.getInstance();
        mouth = (c.get(Calendar.MONTH) + 1) + "";// 获取当前月份
        day = c.get(Calendar.DAY_OF_MONTH) + "";// 获取当日期
        year = c.get(Calendar.YEAR) + "";//
        if (mouth.length() == 1) {
            mouth = "0" + mouth;
        }
        if (day.length() == 1) {
            day = "0" + day;
        }

        for (int i = 0; i < order_c.length; i++) {
            order_c[i] = "";
        }
        order_c[24] = "4";
        order_c[15] = " ";
        te_hkxx.setVisibility(View.GONE);
        te_bzxx.setVisibility(View.GONE);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        w = dm.widthPixels;

        te_dzts.setPadding((int) (w * 88 / 750.0), (int) (w * 8 / 750.0),
                (int) (w * 24 / 750.0), (int) (w * 8 / 750.0));
        te_psts.setPadding((int) (w * 88 / 750.0), (int) (w * 8 / 750.0),
                (int) (w * 24 / 750.0), (int) (w * 8 / 750.0));

        te_bzxx.setPadding((int) (w * 88 / 750.0), 0, (int) (w * 24 / 750.0),
                (int) (w * 8 / 750.0));

        te_ok.setPadding(0, 0, (int) (w * 24 / 750.0), 0);


    }

    private void setviewhw_lin(View v, int width, int height, int left,
                               int top, int right, int bottom) {
        LayoutParams lp = new LayoutParams(width, height);
        lp.setMargins(left, top, right, bottom);
        v.setLayoutParams(lp);
    }

    public static class MyHandler extends Handler {
        WeakReference<CreateOrder> referenceObj;

        public MyHandler(CreateOrder activity) {
            referenceObj = new WeakReference<CreateOrder>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            final CreateOrder activity = referenceObj.get();
            switch (msg.what) {
                case 0x001:
                    activity.xutils_getinfo();
                    break;
                case 0x002:
                    activity.xutils_getinfo();
                    Bundle data = msg.getData();
                    activity.order_c[7] = data.getString("yhq");
                    break;
                case 0x003:
                    activity.xutils_getinfo();
                    Bundle hk = msg.getData();
                    activity.order_c[13] = hk.getString("data");
                    if (TextUtils.isEmpty(activity.order_c[13])) {
                        activity.order_c[13] = "";
                        activity.te_hkxx.setVisibility(View.GONE);
                    } else {
                        activity.te_hk.setTextColor(Color.parseColor("#666666"));
                        activity.te_hkxx.setVisibility(View.VISIBLE);
                        activity.te_hkxx.setText(activity.order_c[13]);
                    }
                    break;
                case 0x004:
                    activity.xutils_getinfo();
                    Bundle bz = msg.getData();
                    activity.order_c[14] = bz.getString("data");
                    if (TextUtils.isEmpty(activity.order_c[14])) {
                        activity.order_c[14] = "";
                        activity.te_bzxx.setVisibility(View.GONE);
                    } else {
                        activity.te_bz.setTextColor(Color.parseColor("#666666"));
                        activity.te_bzxx.setVisibility(View.VISIBLE);
                        activity.te_bzxx.setText(activity.order_c[14]);
                    }
                    break;
                case 0x005:
                    activity.xutils_getinfo();
                    Bundle kf = msg.getData();
                    activity.order_c[15] = kf.getString("data");
                    if (!TextUtils.isEmpty(activity.order_c[15])) {
                        if (activity.order_c[15].equals("默认客服")) {
                            activity.te_kf.setTextColor(Color.parseColor("#666666"));
                            activity.te_kf.setText("已选择客服工号:" + kf.getString("data"));
                            activity.order_c[15] = "0";
                            activity.gonghao = 999;

                        } else {
                            for (int i = 0; i < activity.list_gh.size(); i++) {
                                if (activity.order_c[15].equals(activity.list_ghi
                                        .get(i).getTitle())) {
                                    activity.gonghao = i;
                                    activity.te_kf.setTextColor(Color.parseColor("#666666"));
                                    activity.te_kf.setText("已选择客服工号:" + kf.getString("data"));
                                    activity.order_c[15] = activity.list_ghi.get(i)
                                            .getId();
                                }
                            }
                        }
                    }
                    break;
                case 0x006:
                    activity.xutils_getinfo();
                    Bundle rq = msg.getData();
                    activity.riqi = rq.getStringArray("riqi");
                    activity.setviewhw_lin(activity.te_ps,
                            (int) (activity.w * 594 / 750.0),
                            (int) (activity.w * 175 / 750.0), 0,
                            (int) (activity.w * 5 / 750.0), 0, 0);

                    String ps = "",
                            time = "";

                    activity.order_c[16] = activity.riqi[7];

                    if (activity.riqi[3].equals("1")) {
                        activity.order_c[20] = "0";
                        ps = "配送区域:市区";
                    } else if (activity.riqi[3].equals("2")) {
                        ps = "配送区域:郊区(+￥30.00)";
                        activity.order_c[20] = "30";
                    } else if (activity.riqi[3].equals("3")) {
                        ps = "配送区域:远区(+￥50.00)";
                        activity.order_c[20] = "50";
                    }
                    if (!activity.riqi[5].equals("0")) {
                        activity.order_c[17] = "定时";
                        // time = "定时配送:" + activity.riqi[6];
                        activity.order_c[18] = activity.riqi[6].split(":")[0];
                        activity.order_c[19] = activity.riqi[6].split(":")[1];
                        activity.order_c[21] = "1";
                        activity.order_c[22] = "定时";
                        time = "定时配送:" + activity.riqi[8];
                    } else {
                        time = "配送时间:" + activity.riqi[6];
                        activity.order_c[17] = "时段";
                        activity.order_c[18] = "";
                        activity.order_c[19] = "";
                        activity.order_c[21] = "0";
                        activity.order_c[22] = activity.riqi[6];
                    }
                    activity.te_ps.setTextColor(Color.parseColor("#666666"));
                    activity.te_ps.setText(ps + "\n" + "配送日期:" + activity.riqi[7] + "\n" + time);
                    activity.httpPost_getdatprice(activity.riqi[7], "1");
                    break;

                default:
                    break;
            }
        }
    }

    /**
     * 获取商品信息
     */
    String sign = "";

    private void xutils_getinfo() {
        landing.show();
        final String url = "https://app.juandie.com/api_mobile/flow.php?act=get_order_config&use_flow_use_rank_discount="
                + use_flow_use_rank_discount;

        Xutils_Get_Post.getInstance().get(url, new XCallBack() {

            @Override
            public void onResponse(String result) {
                landing.dismiss();
                try {
                    JSONObject response = new JSONObject(result);
                    if (response.getString("status").equals("1")) {
                        JSONObject jso = response.getJSONObject("data");

                        JSONObject jso_adress = jso
                                .getJSONObject("address_default_info");
                        if (jso_adress.getString("addr_info").length() >= 1) {

                            address_id = jso_adress.getString("address_id");
                            address[0] = address_id;


                            String phone1 = "收货人：" + address1.getReceiverName() + "" + "<font color = \"#333333\">" + address1.getReceiverPhone() + "</font>";
                            String address = "收货地址：" + address1.getCities() + address1.getDetail();
                            String phone2 = "订购人：" + address1.getOrderName() + "<font color = \"#333333\">" + address1.getOrderPhone() + "</font>";

                            orderAddress.setText(Html.fromHtml(phone1 + "\\n" + address + "\\n" + phone2));

                            address1.setReceiverName(jso_adress.getString("consignee"));
                            address1.setReceiverPhone(jso_adress.getString("tel"));
                            address1.setDetail(jso_adress.getString("address"));
                            address1.setOrderName(jso_adress.getString("buyer_name"));
                            address1.setOrderPhone(jso_adress.getString("buyer_tel"));
                            address1.setProvince(jso_adress.getString("province"));
                            address1.setCity(jso_adress.getString("city"));
                            address1.setDistrict(jso_adress.getString("district"));

                            address1.setCityId(jso_adress.getString("province") + "" + jso_adress.getString("city") + "" + jso_adress.getString("district"));

                            // 0收花人姓名,1收花人手机号,2固定值:1,3具体街道地址,4固定值:1,5订花人姓名,6订花人手机,7优惠券代码,8910省市区id
                            order_c[0] = jso_adress.getString("consignee");
                            order_c[1] = jso_adress.getString("tel");
                            order_c[2] = "1";
                            order_c[3] = jso_adress.getString("address");
                            order_c[4] = "1";
                            order_c[5] = jso_adress.getString("buyer_name");
                            order_c[6] = jso_adress.getString("buyer_tel");
                            order_c[8] = jso_adress.getString("province");
                            order_c[9] = jso_adress.getString("city");
                            order_c[10] = jso_adress.getString("district");

                            riqi[0] = order_c[8];
                            riqi[1] = order_c[9];
                            riqi[2] = order_c[10];

                        } else {
                            address_id = "0";
                            address[0] = address_id;
                            orderAddress.setText("");
                        }

                        JSONObject jso_price = jso.getJSONObject("total");
                        String jso_huiyuan = jso
                                .getString("user_rank_discount_number");

                        // 11配送费12定时费,13贺卡14备注
                        order_c[11] = jso_price.getString("shipping_fee");
                        order_c[12] = jso_price.getString("distinct_time_service_fee_fee");
                        if (order_c[11].contains(".")) {
                            te_usepsfp.setText("+￥" + order_c[11]);
                        } else
                            te_usepsfp.setText("+￥" + order_c[11] + ".00");

                        if (!order_c[11].equals("0")) {
                            lin_usepsf.setVisibility(View.VISIBLE);
                        } else
                            lin_usepsf.setVisibility(View.GONE);
                        if (!order_c[12].equals("0")) {
                            lin_usedsf.setVisibility(View.VISIBLE);
                        } else
                            lin_usedsf.setVisibility(View.GONE);

                        if (order_c[12].contains(".")) {
                            te_usedsfp.setText("+￥" + order_c[12]);
                        } else
                            te_usedsfp.setText("+￥" + order_c[12] + ".00");
                        String yhq_je = jso_price.getString("bonus");
                        if (yhq_je.contains(".")) {
                            te_useyhqp.setText("-￥" + yhq_je);
                        } else
                            te_useyhqp.setText("-￥" + yhq_je + ".00");

                        String gp = jso_price.getString("amount");
                        if (!gp.contains(".")) {
                            gp = gp + ".00";
                        }
                        String canuseyhq = jso.getString("is_show_discount");// 1can
                        if (canuseyhq.equals("1")) {
                            lin_yhq.setVisibility(View.VISIBLE);
                            view_hen.setVisibility(View.VISIBLE);
                            if (jso_huiyuan.length() >= 1) {
                                lin_botq.setVisibility(View.VISIBLE);
                                te_zkou.setText(jso_huiyuan + "折优惠");
                                te_zkounum.setText("￥"
                                        + jso.getString("user_rank_discount_amount"));
                                te_price.setText("总计:￥ " + gp);
                            } else {
                                te_price.setText("总计:￥ " + gp);
                                lin_botq.setVisibility(View.GONE);
                                use_flow_use_rank_discount = "0";
                            }
                        } else {
                            te_price.setText("总计:￥ " + gp);
                            lin_yhq.setVisibility(View.GONE);
                            view_hen.setVisibility(View.GONE);
                        }
                        order_c[24] = gp;

                        JSONArray jsa_yhq = jso.getJSONArray("bonus_list");
                        list_yhqstr.removeAll(list_yhqstr);
                        if (!yhq_je.equals("0")) {
                            lin_useyhq.setVisibility(View.VISIBLE);
                            te_yhq.setTextColor(Color.parseColor("#666666"));
                            te_yhq.setText("使用优惠券：优惠金额￥" + yhq_je);
                        } else
                            lin_useyhq.setVisibility(View.GONE);

                        for (int i = 0; i < jsa_yhq.length(); i++) {
                            JSONObject data_yhq = jsa_yhq.getJSONObject(i);
                            list_yhqstr.add("1,"
                                    + data_yhq.getString("type_money") + ","
                                    + data_yhq.getString("bonus_sn") + ","
                                    + data_yhq.getString("use_time_deadline")
                                    + "," + data_yhq.getString("type_name")
                                    + ","
                                    + data_yhq.getString("type_category_name"));
                        }

                        String sanbei_price = jso
                                .getString("delivery_reminder_msg");
                        if (sanbei_price.length() >= 2) {
                            te_dzts.setVisibility(View.VISIBLE);
                            te_dzts.setText(sanbei_price);
                        } else
                            te_dzts.setVisibility(View.GONE);

                        String jso_psts = jso
                                .getString("delivery_express_goods_tip");
                        if (jso_psts.length() >= 1) {
                            te_psts.setText(jso_psts);
                            te_psts.setVisibility(View.VISIBLE);
                        } else
                            te_psts.setVisibility(View.GONE);

                        String is_ps = jso.getString("is_delivery");
                        if (is_ps.equals("false")) {
                            te_ok.setEnabled(false);
                            te_ok.setBackgroundColor(Color.parseColor("#999999"));
                            Toast.makeText(CreateOrder.this, "抱歉，该地址不支持配送,请重新选择地址",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            te_ok.setEnabled(true);
                            te_ok.setBackgroundResource(R.drawable.bg_gradient);
                        }

                        // can_order = jso.getString("is_delivery");
                        // cannot_strcannot = sanbei_price;
                        list.removeAll(list);
                        ada.notifyDataSetChanged();
                        JSONObject jso_spx = jso.getJSONObject("cart_info");
                        JSONArray sp_list = jso_spx
                                .getJSONArray("cart_goods_list");
                        for (int i = 0; i < sp_list.length(); i++) {
                            JSONObject jso_sp = sp_list.getJSONObject(i);
                            jieri = jso_sp.getString("is_festival");
                            list.add(new order_spadadata(jso_sp
                                    .getString("goods_thumb"), jso_sp
                                    .getString("goods_number"), jso_sp
                                    .getString("goods_price"), jso_sp
                                    .getString("goods_name"), jieri, jso_sp
                                    .getString("goods_attr")));
                        }
                        ada.notifyDataSetChanged();

                        JSONArray jsa_gh = jso
                                .getJSONArray("customer_service_no_list");
                        list_gh.removeAll(list_gh);
                        list_ghi.removeAll(list_ghi);
                        for (int i = 0; i < jsa_gh.length(); i++) {
                            JSONObject jso_gh = jsa_gh.getJSONObject(i);
                            list_gh.add(jso_gh.getString("no"));
                            list_ghi.add(new gridv_adaData(jso_gh
                                    .getString("emp_id"), jso_gh
                                    .getString("no"), false));
                        }

                    } else {
                        String str = response.getString("msg");
                        if (str.contains("购物车")) {
                            Toast.makeText(CreateOrder.this, "购物车为空,请重新选择商品",
                                    Toast.LENGTH_SHORT).show();
                            finish();
                            overridePendingTransition(R.anim.push_right_out,
                                    R.anim.push_right_in);
                        }
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail(String result) {
                landing.dismiss();
                xutils_getinfo();
            }

            @Override
            public void onCancel(CancelledException cex) {
                landing.dismiss();
            }
        });

    }

    /**
     * 获取节日价格
     *
     * @param delivery_date
     */
    public void httpPost_getdatprice(String delivery_date, final String type) {

        String url = "https://app.juandie.com/api_mobile/flow.php";
        HashMap<String, String> maps = new HashMap<>();
        maps.put("act", "get_cart_list_by_delivery_date");
        maps.put("delivery_date", delivery_date);

        Xutils_Get_Post.getInstance().post(url, maps, new XCallBack() {

            @Override
            public void onResponse(String result) {
                JSONObject object;
                try {
                    object = new JSONObject(result);
                    int status = object.getInt("status");
                    if (status == 1) {
                        JSONObject jso = object.getJSONObject("data");
                        JSONArray jsa = jso.getJSONArray("cart_list");
                        JSONObject jso_ = jsa.getJSONObject(0);
                        jieri = jso_.getString("is_festival");
                        if (type.equals("1")) {
                            // if (jieri.equals("1")) {
                            handler.sendEmptyMessage(0x001);
                            // }
                        } else if (type.equals("3")) {
                            handler.sendEmptyMessage(0x001);
                        } else {
                            finish();
                            overridePendingTransition(R.anim.push_right_out,
                                    R.anim.push_right_in);
                        }

                    } else {
                        if (status == 0) {
                            // handler.sendEmptyMessage(0x006);
                        }
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

    private UserAddress address1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == UiHelper.chooseAddress) {
                address1 = (UserAddress) data.getSerializableExtra("address");
                if (address1 != null) {
                    String phone1 = "收货人：" + address1.getReceiverName() + "" + "<font color = \"#333333\">" + address1.getReceiverPhone() + "</font>";
                    String address = "收货地址：" + address1.getCities() + address1.getDetail();
                    String phone2 = "订购人：" + address1.getOrderName() + "<font color = \"#333333\">" + address1.getOrderPhone() + "</font>";

                    orderAddress.setText(Html.fromHtml(phone1 + "\\n" + address + "\\n" + phone2));

                } else {
                    orderAddress.setText("");
                }
            } else if (requestCode == UiHelper.chooseSenfTime) {
                address1 = (UserAddress) data.getSerializableExtra("address");

            } else if (requestCode == UiHelper.leaveMessage) {
                address1 = (UserAddress) data.getSerializableExtra("address");

            } else if (requestCode == UiHelper.coupon) {
                address1 = (UserAddress) data.getSerializableExtra("address");

            } else if (requestCode == UiHelper.remark) {
                address1 = (UserAddress) data.getSerializableExtra("address");

            } else if (requestCode == UiHelper.serviceNumber) {
                address1 = (UserAddress) data.getSerializableExtra("address");

            }

        }
    }
}
