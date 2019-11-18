package com.example.juandie_hua.ui.good;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.juandie_hua.R;
import com.example.juandie_hua.app.BaseActivity;
import com.example.juandie_hua.app.HttpUrl;
import com.example.juandie_hua.calender.utils.ImageUtils;
import com.example.juandie_hua.helper.UiHelper;
import com.example.juandie_hua.lunbo.ADInfo;
import com.example.juandie_hua.lunbo.CycleViewPager;
import com.example.juandie_hua.lunbo.ViewFactory;
import com.example.juandie_hua.app.Constant;
import com.example.juandie_hua.mainactivity.Fengmian;
import com.example.juandie_hua.mainactivity.Landing;
import com.example.juandie_hua.mainactivity.adapter.OnGoodListCallback;
import com.example.juandie_hua.mainactivity.adapter.ShopCartFavorAdapter;
import com.example.juandie_hua.mainactivity.goods.MyGridView;
import com.example.juandie_hua.mainactivity.goods.MyListView;
import com.example.juandie_hua.mainactivity.goods.check_pic;
import com.example.juandie_hua.mainactivity.goods.pj_adaData;
import com.example.juandie_hua.mainactivity.goods.pj_adapter;
import com.example.juandie_hua.mainactivity.goods.pj_more;
import com.example.juandie_hua.model.Coupon;
import com.example.juandie_hua.model.GoodSpecs;
import com.example.juandie_hua.model.ShopCartFavor;
import com.example.juandie_hua.percenter.seting.wx_bdgh;
import com.example.juandie_hua.ui.MainActivity;
import com.example.juandie_hua.ui.ShopCatActivity;
import com.example.juandie_hua.mainactivity.Xutils_Get_Post;
import com.example.juandie_hua.mainactivity.adapter.CouponAdapter;
import com.example.juandie_hua.mainactivity.adapter.CouponReceiveAdapter;
import com.example.juandie_hua.mainactivity.goods.recommend.recommendAdapter;
import com.example.juandie_hua.mainactivity.goods.recommend.recommendData;
import com.example.juandie_hua.mainactivity.internet_if;
import com.example.juandie_hua.mainactivity.no_internet;
import com.example.juandie_hua.onekeyshare.OnekeyShare;
import com.example.juandie_hua.ui.adapter.GoodSpecsAdapter;
import com.example.juandie_hua.ui.me.MySC;
import com.example.juandie_hua.utils.DecimalUtil;
import com.example.juandie_hua.utils.SharedPreferenceUtils;
import com.example.juandie_hua.utils.StatusBarUtils;
import com.example.juandie_hua.utils.StrUtils;
import com.example.juandie_hua.utils.TextViewUtils;
import com.example.juandie_hua.utils.ViewUtils;
import com.example.juandie_hua.view.CusPopWindow;
import com.example.juandie_hua.view.CustomDialog;
import com.jauker.widget.BadgeView;
import com.meiqia.meiqiasdk.util.MQIntentBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.common.util.DensityUtil;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class GoodDetailsAty extends BaseActivity implements recommendAdapter.gotobuy {

    @ViewInject(R.id.frist_refrag)
    RelativeLayout refragment;

    CycleViewPager cycleViewPager;
    private String[] imageUrls = {
            "http://imgcdn.vpxh.com/images/201710/goods_img/964_P_1508744769926.jpg",
            "http://imgcdn.vpxh.com/images/201711/goods_img/418_P_1511419671905.jpg",
            "http://imgcdn.vpxh.com/images/201710/goods_img/964_P_1508744769926.jpg"};// 轮播图test
    @ViewInject(R.id.sp_scr)
    ScrollView scr;

    @ViewInject(R.id.sp_tename)
    TextView te_name;

    /**
     * 单价格时显示
     */
    @ViewInject(R.id.single_price_view)
    RelativeLayout single_price_view;
    /**
     * 双价格时显示
     */
    @ViewInject(R.id.double_price_view)
    LinearLayout double_price_view;


    @ViewInject(R.id.normal_day_price_view)
    RelativeLayout normal_day_price_view;
    @ViewInject(R.id.normal_day_price_title)
    TextView normal_price_title;
    @ViewInject(R.id.normal_day_price)
    TextView normal_price;
    @ViewInject(R.id.normal_day_orgprice)
    TextView normal_orgprice;

    @ViewInject(R.id.festival_day_price_view)
    RelativeLayout festival_day_price_view;
    @ViewInject(R.id.festival_day_price_title)
    TextView festival_price_title;
    @ViewInject(R.id.festival_day_price)
    TextView festival_price;
    @ViewInject(R.id.festival_day_orgprice)
    TextView festival_orgprice;


    @ViewInject(R.id.sp_teprice)
    TextView te_price;
    @ViewInject(R.id.sp_tepricesc)
    TextView te_pricesc;
    @ViewInject(R.id.sp_tepricexl)
    TextView te_pricexl;

    @ViewInject(R.id.sp_imsc)
    ImageView im_sc;
    @ViewInject(R.id.good_remark)
    LinearLayout good_remark;
    /**
     * 适合对象
     */
    @ViewInject(R.id.sp_teduix)
    TextView te_duix;
    /**
     * 适合用途
     */
    @ViewInject(R.id.sp_teyontu)
    TextView te_yontu;
    /**
     * 货号
     */
    @ViewInject(R.id.item_good_specs_value)
    TextView te_hhao1;
    /**
     * 推荐热卖
     */
    @ViewInject(R.id.sp_linreimai)
    LinearLayout lin_remai;
    @ViewInject(R.id.sp_gridv)
    GridView grv_list;
//    gdlist_adapter recommendAdapter;
//    List<gdlist_adaData> list;

    /**
     * 商品详情
     */
    @ViewInject(R.id.xq_wb)
    WebView wb;

    /**
     * 头部Tab
     */
    @ViewInject(R.id.goods_retop)
    LinearLayout re_top;
    @ViewInject(R.id.goods_sp)
    TextView te_sp;
    @ViewInject(R.id.goods_xq)
    TextView te_xq;
    @ViewInject(R.id.goods_pj)
    TextView te_pj;
    @ViewInject(R.id.goods_sp_view)
    View view_sp;
    @ViewInject(R.id.goods_xq_view)
    View view_xq;
    @ViewInject(R.id.goods_pj_view)
    View view_pj;

    @ViewInject(R.id.frist_linhe)
    LinearLayout lin_gethe;

    /**
     * 评价列表
     */
    @ViewInject(R.id.pj_listv)
    MyListView listv_v;
    pj_adapter commentAdapter;
    List<pj_adaData> list_pj;
    /**
     * 评价数量
     */
    @ViewInject(R.id.sp_ted1)
    TextView te_pjnumber;
    /**
     * 更多评价
     */
    @ViewInject(R.id.sp_temore)
    TextView te_getmore;


    /**
     * 返回顶部按钮
     */
    @ViewInject(R.id.sp_gotop)
    ImageView im_top;

    @ViewInject(R.id.sp_linggx)
    LinearLayout lin_ggx;// 动态加载规格材料等等数据

    /**
     * 优惠券
     */
    @ViewInject(R.id.goods_detail_coupon_grid)
    MyGridView couponGrid;
    CouponAdapter couponAdapter;
    @ViewInject(R.id.goods_detail_coupon)
    LinearLayout couponView;
    List<Coupon.BonusListBean> couponList = new ArrayList<>();

    public String goods_id = "";
    public String goods_desc = "";
    String goods_num = "";

    boolean canonc = false;

    int numb_gwc = 0;

    public static MyHandler handler;

    Landing landing;
    no_internet no_internet;


    BadgeView bv1;

    String guige = "-2";// -2表示没有规格 -1表示有规格但是没有选择，其他表示选择了规格

    String goodName = "", goodUrl = "", goodPrice = "";

    String minnum = "1";

    // 组合推荐
    @ViewInject(R.id.recommend_title)
    TextView te_recommend;
    @ViewInject(R.id.recommend_subtitle)
    TextView te_recommend1;

    @ViewInject(R.id.sp_listvrecommend)
    MyListView listv_recommend;
    recommendAdapter ada_recommend;
    List<recommendData> list_recomend = new ArrayList<>();
    @ViewInject(R.id.sp_linrecommend)
    LinearLayout lin_recommend;
    /**
     * 优惠券
     */
    private Coupon coupon;
    @ViewInject(R.id.goods_detail_shopcart)
    private TextView im_gwc;
    private ArrayList<GoodSpecs> specsList;
    private String is_festival = "";
    private CusPopWindow popSpecs;
    private CustomDialog dialog;
    private List<ShopCartFavor> favorList;
    private ShopCartFavorAdapter favorAdapter;
    private CusPopWindow popCoupon;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_good_detail);
        StatusBarUtils.with(this).setBarColor(R.color.white_fff);
        x.view().inject(this);
        setviewhw();
        handler = new MyHandler(this);

        Intent i_ = getIntent();
        goods_id = i_.getStringExtra("goods_id");

        addFootPrint();
        landing = new Landing(GoodDetailsAty.this, R.style.CustomDialog);

        if (new internet_if().isNetworkConnected(GoodDetailsAty.this)) {
            xutils_getsp(goods_id);//页面初始化
            geShopCartCount();
        } else
            no_internet.show();

        setviewdata();
        setviewlisten();
    }

    /**
     * 添加到足迹
     */
    private void addFootPrint() {
        String footPrint = (String) SharedPreferenceUtils.getPreference(this, Constant.zhuji, "S");
        if (!TextUtils.isEmpty(footPrint)) {
            if (!footPrint.contains(goods_id)) {
                SharedPreferenceUtils.setPreference(this, Constant.zhuji, goods_id + "," + footPrint, "S");
            }
        } else {
            SharedPreferenceUtils.setPreference(this, Constant.zhuji, goods_id, "S");
        }
    }

    public void reload() {
        Intent i_ = getIntent();
        goods_id = i_.getStringExtra("goods_id");

        addFootPrint();
        xutils_getsp(goods_id);//reload
        geShopCartCount();
        scr.scrollTo(0, 0);
    }

    /**
     * 点击事件
     *
     * @param v view
     */
    public void Click(View v) {
        switch (v.getId()) {
            case R.id.goods_imreturn:
                finish();
                overridePendingTransition(R.anim.push_right_out, R.anim.push_right_in);
                break;

            case R.id.sp_gotop:
                scr.scrollTo(0, 0);
                setselect(1);
                im_top.setVisibility(View.GONE);
                break;

            case R.id.goods_detail_online_service_view://客服聊天
                Intent intent1 = new MQIntentBuilder(GoodDetailsAty.this)
                        .setPreSendImageMessage(new File("预发送图片的路径"))
                        .setCustomizedId(Fengmian.regid).build();
                UiHelper.toActivity(GoodDetailsAty.this, intent1);
                break;

            case R.id.goods_detail_service_phone_view://客服电话
                String pho = Constant.PHONE;
                Intent intent2 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + pho));
                intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                UiHelper.toActivity(GoodDetailsAty.this, intent2);
                break;

            case R.id.goods_detail_shopcart_view://购物车
                toShopCart();
                break;

            case R.id.goods_detail_add_shopcart://加入购物车
                if (guige.equals("-2")) {
                    addInShopCart(goods_id, 0);
                } else {
                    showSpecs(0);
                }
                break;

            case R.id.goods_detail_buy_now://立即购买
                if (guige.equals("-2")) {
                    addInShopCart(goods_id, 1);
                } else {
                    showSpecs(1);
                }
                break;

            case R.id.goods_sp://商品Tab
                im_top.setVisibility(View.GONE);
                scr.smoothScrollTo(0, 0);
                setselect(1);
                break;

            case R.id.goods_xq://详情Tab
                im_top.setVisibility(View.GONE);
                if (canonc) {
                    int he1 = lin_gethe.getMeasuredHeight();
                    scr.smoothScrollTo(0, he1);
                    setselect(2);
                }
                break;

            case R.id.goods_pj://评价tab
                im_top.setVisibility(View.VISIBLE);
                if (canonc) {
                    int he1 = lin_gethe.getMeasuredHeight();
                    int he2 = wb.getMeasuredHeight();
                    scr.smoothScrollTo(0, he1 + he2);
                    setselect(3);
                }
                break;

            case R.id.goods_immore://右上角menu
                setMenu();
                break;
            case R.id.sp_ted1://评价数量
            case R.id.sp_temore://查看更多评价
                Intent intent3 = new Intent(GoodDetailsAty.this, pj_more.class);
                intent3.putExtra("goods_id", goods_id);
                intent3.putExtra("goods_num", goods_num);
                UiHelper.toActivity(GoodDetailsAty.this, intent3);
                break;

            case R.id.normal_day_price_view://正常日价格
                if (normal_day_price_view.isSelected()) {
                    return;
                }
                is_festival = "0";
                goodPrice = normal_price.getText().toString();
                normal_day_price_view.setSelected(true);
                festival_day_price_view.setSelected(false);
                break;

            case R.id.festival_day_price_view://节日价格
                if (festival_day_price_view.isSelected()) {
                    return;
                }
                is_festival = "1";
                goodPrice = festival_price.getText().toString();
                normal_day_price_view.setSelected(false);
                festival_day_price_view.setSelected(true);
                break;

            case R.id.sp_imsc://收藏按钮
                if (isLogin()) {
                    collectGood(goods_id);
                } else {
                    toast("请先登录");
                    UiHelper.toLoginActivity(GoodDetailsAty.this);
                }
                break;

            case R.id.goods_detail_coupon:
                if (isLogin()) {
                    showCoupon();
                } else {
                    toast("请先登录");
                    UiHelper.toLoginActivity(GoodDetailsAty.this);
                }
                break;
        }
    }

    private void toShopCart() {
        UiHelper.toActivity(GoodDetailsAty.this, new Intent(GoodDetailsAty.this, ShopCatActivity.class));
    }


    private void setviewlisten() {

        favorAdapter.setOnGoodListCallback(new OnGoodListCallback<ShopCartFavor>() {
            @Override
            public void setOnAddShopCallback(View view, ShopCartFavor data) {
                addInShopCart(data.getId(), 0);
            }

            @Override
            public void setOnItemClickCallback(View view, ShopCartFavor data) {
                im_top.setVisibility(View.GONE);
                goods_id = data.getId();
                xutils_getsp(data.getId());//推荐的商品
                scr.scrollTo(0, 0);
                setselect(1);
                canonc = false;
            }
        });

        scr.setOnTouchListener(new View.OnTouchListener() {
            private int lastY = 0;
            private int touchEventId = -9983761;
            Handler handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    View scroller = (View) msg.obj;
                    if (msg.what == touchEventId) {
                        if (lastY == scroller.getScrollY()) {
                            handleStop(scroller);
                        } else {
                            handler.sendMessageDelayed(handler.obtainMessage(
                                    touchEventId, scroller), 5);
                            lastY = scroller.getScrollY();
                        }
                        if (lastY >= 1200) {
                            im_top.setVisibility(View.VISIBLE);
                        } else
                            im_top.setVisibility(View.GONE);
                    }
                }
            };

            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    handler.sendMessageDelayed(
                            handler.obtainMessage(touchEventId, v), 5);
                }
                return false;
            }

            // 处理真正的事件
            private void handleStop(Object view) {

                /**
                 * 1.getScrollY()获取滑动的距离 <br>
                 * 2.getHeight()获取在屏幕上显示的高度 <br>
                 * 3.getMeasureHeight()获取控件的真实高度(包含屏幕外的高度) <br>
                 */
                int he1 = lin_gethe.getMeasuredHeight();
                int he2 = wb.getMeasuredHeight();

                if (scr.getScrollY() <= he1) {
                    if (!te_sp.isSelected()) {
                        setselect(1);
                    }

                } else if ((he1 + he2) > scr.getScrollY()
                        && scr.getScrollY() > he1) {
                    setselect(2);
                } else {
                    setselect(3);
                }
            }
        });


        wb.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                canonc = true;
            }
        });

        couponGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (isLogin()) {
                    showCoupon();
                } else {
                    Toast.makeText(GoodDetailsAty.this, "请先登录", Toast.LENGTH_SHORT).show();
                    UiHelper.toLoginActivity(GoodDetailsAty.this);
                }
            }
        });
    }

    private void setviewdata() {
        setselect(1);

//        list = new ArrayList<>();
        grv_list.setSelector(new ColorDrawable(Color.TRANSPARENT));
//        recommendAdapter = new gdlist_adapter(GoodDetailsAty.this, list);
        favorAdapter = new ShopCartFavorAdapter(GoodDetailsAty.this, favorList);
        grv_list.setAdapter(favorAdapter);

        listv_v.setSelector(new ColorDrawable(Color.TRANSPARENT));
        list_pj = new ArrayList<>();
        commentAdapter = new pj_adapter(GoodDetailsAty.this, list_pj);
        listv_v.setAdapter(commentAdapter);

        ada_recommend = new recommendAdapter(list_recomend, GoodDetailsAty.this);
        listv_recommend.setAdapter(ada_recommend);
        listv_recommend.setSelector(new ColorDrawable(Color.TRANSPARENT));
        ada_recommend.setbuyinterface(this);

        couponGrid.setSelector(new ColorDrawable(Color.TRANSPARENT));
        couponAdapter = new CouponAdapter(GoodDetailsAty.this, couponList);
        couponGrid.setAdapter(couponAdapter);
    }

    private void setviewhw() {
        TextViewUtils.setTextAddLine(te_pricesc);
        TextViewUtils.setTextAddLine(normal_orgprice);
        TextViewUtils.setTextAddLine(festival_orgprice);
        normal_day_price_view.setSelected(true);

        DisplayMetrics dm = getResources().getDisplayMetrics();
        int w = dm.widthPixels;
        ViewUtils.setviewhw_lin(refragment, w, (int) (w * 375 / 375.0), 0, 0, 0, 0);

        bv1 = new BadgeView(GoodDetailsAty.this);
        bv1.setTargetView(im_gwc);
        bv1.setBadgeCount(0);
        bv1.setTextSize(8);
    }

    /**
     * 轮播图片
     */
    private void initialize() {
        cycleViewPager = (CycleViewPager) getFragmentManager().findFragmentById(R.id.frist_viewpagerlunbos);

        List<ImageView> views = new ArrayList<>();
        List<ADInfo> infos = new ArrayList<>();

        for (int i = 0; i < imageUrls.length; i++) {
            ADInfo info = new ADInfo();
            info.setUrl(imageUrls[i]);

            info.setContent("图片-->" + i);
            infos.add(info);
        }
        // 将最后一个ImageView添加进来
        views.add(ViewFactory.getImageView(GoodDetailsAty.this, infos.get(infos.size() - 1).getUrl()));
        // 将第一个ImageView添加进来
        views.add(ViewFactory.getImageView(GoodDetailsAty.this, infos.get(0).getUrl()));
        for (int i = 0; i < infos.size(); i++) {
            views.add(ViewFactory.getImageView(GoodDetailsAty.this, infos.get(i).getUrl()));
        }

        // 设置循环，在调用setData方法前调用
        cycleViewPager.setCycle(true);

        // 在加载数据前设置是否循环
        cycleViewPager.setData(views, infos, mAdCycleViewListener);
        // 设置轮播
        cycleViewPager.setWheel(true);

        // 设置轮播时间，默认5000ms
        cycleViewPager.setTime(4000);
        // 设置圆点指示图标组居中显示，默认靠右
        cycleViewPager.setIndicatorCenter();

    }

    /**
     * 轮播点击监听
     */
    private CycleViewPager.ImageCycleViewListener mAdCycleViewListener = new CycleViewPager.ImageCycleViewListener() {

        @Override
        public void onImageClick(ADInfo info, int position, View imageView) {
            if (cycleViewPager.isCycle()) {
                position = position - 1;
                String urls = "";
                for (int i = 0; i < imageUrls.length; i++) {
                    urls += imageUrls[i] + ",";
                }
                urls = urls.substring(0, urls.length() - 1);
                Intent i = new Intent();
                i.setClass(GoodDetailsAty.this, check_pic.class);
                i.putExtra("pos", position + "");
                i.putExtra("urls", urls);
                UiHelper.toActivity(GoodDetailsAty.this, i);
            }

        }

    };

    /**
     * 获取商品详情
     */
    private void xutils_getsp(final String goods_id) {
//        landing.show();

        String url = HttpUrl.good + "act=" + HttpUrl.good_detail;
        if (TextUtils.isEmpty(is_festival)) {
            url += "&goods_id=" + goods_id;
        } else {
            url += "&goods_id=" + goods_id + "&is_festival=" + is_festival;
        }

        Xutils_Get_Post.getInstance().get(url, new Xutils_Get_Post.XCallBack() {

            @Override
            public void onResponse(String result) {

                Log.e("~~~~~~~~", result + "");
                JSONObject jso1;
                try {
                    jso1 = new JSONObject(result);
                    if (jso1.getString("status").equals("1")) {
                        JSONObject jso = jso1.getJSONObject("data");
                        JSONObject data = jso.getJSONObject("goods");
                        minnum = data.getString("min_number");

                        specsList = JSON.parseObject(data.getString("specification_sel"), new TypeReference<ArrayList<GoodSpecs>>() {
                        });
//                        List<GoodSpecs> list = JSON.parseArray(data.getString("specification_sel"), GoodSpecs.class);

                        guige = StrUtils.listIsEmpty(specsList) ? "-2" : "-1";

                        im_sc.setImageResource(TextUtils.equals(data.getString("is_collect"), "1") ? R.mipmap.collect : R.mipmap.uncollect);

                        /**
                         * 节日价
                         */
                        JSONObject jsoc = data.getJSONObject("festival_price_info");
                        if (jsoc.getString("festival_open").equals("true")) {
                            is_festival = "0";

                            JSONObject jso_psj = jsoc.getJSONObject("festival_price_now");
                            JSONObject jso_jrj = jsoc.getJSONObject("festival_price_old");

                            single_price_view.setVisibility(View.GONE);
                            double_price_view.setVisibility(View.VISIBLE);

                            normal_price_title.setText(jso_psj.getString("title"));
                            festival_price_title.setText(jso_jrj.getString("title"));
                            normal_price.setText("￥" + jso_psj.getString("price"));
                            festival_price.setText("￥" + jso_jrj.getString("price"));
                            normal_orgprice.setText("￥" + jso_psj.getString("market_price"));
                            festival_orgprice.setText("￥" + jso_jrj.getString("market_price"));

                            goodPrice = "￥" + jso_psj.getString("price");
                        } else {
                            is_festival = "";
                            single_price_view.setVisibility(View.VISIBLE);
                            double_price_view.setVisibility(View.GONE);
                            goodPrice = "￥" + data.getString("shop_price");
                        }

                        goods_desc = data.getString("goods_desc");
                        setviewweb();
                        JSONArray jsa = data.getJSONArray("gallery");
                        imageUrls = new String[0];
                        imageUrls = new String[jsa.length()];
                        for (int i = 0; i < jsa.length(); i++) {
                            JSONObject jsob = jsa.getJSONObject(i);
                            imageUrls[i] = jsob.getString("img_url");
                            if (i == 0) {
                                goodUrl = jsob.getString("img_url");
                            }
                        }
                        initialize();
                        te_name.setText(data.getString("goods_name"));
                        goodName = data.getString("goods_name");
                        te_price.setText("￥" + data.getString("shop_price"));

                        te_pricesc.setText(data.getString("market_price") + ".00");
                        te_pricexl.setText("已售 " + data.getString("goods_sale_number") + "件");


                        //适用人群
                        String people = data.getString("suitable_object_value");
                        String function = data.getString("purpose_value");

                        if (TextUtils.isEmpty(people) && TextUtils.isEmpty(function)) {
                            good_remark.setVisibility(View.GONE);
                        } else {
                            good_remark.setVisibility(View.VISIBLE);
                            if (TextUtils.isEmpty(people)) {
                                te_duix.setVisibility(View.GONE);
                            } else {
                                te_duix.setVisibility(View.VISIBLE);
                                te_duix.setText("适合对象：" + people);
                            }
                            if (TextUtils.isEmpty(function)) {
                                te_yontu.setVisibility(View.GONE);
                            } else {
                                te_yontu.setVisibility(View.VISIBLE);
                                te_yontu.setText("适合用途：" + function);
                            }
                        }

                        /**
                         * 商品详情说明
                         */
                        JSONObject jsb = data.getJSONObject("properties");
                        Iterator<String> keys = jsb.keys();
                        while (keys.hasNext()) {
                            String key = String.valueOf(keys.next());
                            JSONObject jso_ = jsb.getJSONObject(key);
                            Iterator<String> keys2 = jso_.keys();
                            while (keys2.hasNext()) {
                                String key2 = String.valueOf(keys2.next());
                                JSONObject jso2 = jso_.getJSONObject(key2);

                                View view = LayoutInflater.from(GoodDetailsAty.this).inflate(R.layout.item_good_specs, null);

                                TextView te_xx = view.findViewById(R.id.item_good_specs_name);
                                TextView te_xx1 = view.findViewById(R.id.item_good_specs_value);
                                te_xx.setText(jso2.getString("name"));
                                te_xx1.setText(jso2.getString("value"));
                                lin_ggx.addView(view);
                            }
                        }
                        te_hhao1.setText(data.getString("goods_sn"));
                        JSONArray jsa_list = data.getJSONArray("goods_like_list");

                        favorList = JSON.parseArray(data.getString("goods_like_list"), ShopCartFavor.class);

                        if (StrUtils.listIsEmpty(favorList)) {
                            lin_remai.setVisibility(View.GONE);
                        } else {
                            lin_remai.setVisibility(View.VISIBLE);
                            favorAdapter.refresh(favorList);
                        }
//                        if (jsa_list.length() >= 1) {
//                            lin_remai.setVisibility(View.VISIBLE);
//                            list.removeAll(list);
//                            recommendAdapter.notifyDataSetChanged();
//                            for (int i = 0; i < jsa_list.length(); i++) {
//                                JSONObject jso_list = jsa_list.getJSONObject(i);
//                                list.add(new gdlist_adaData(jso_list
//                                        .getString("id"), jso_list
//                                        .getString("goods_thumb"), jso_list
//                                        .getString("shop_price"), jso_list
//                                        .getString("name"), jso_list
//                                        .getString("sell_number")));
//                            }
//                            recommendAdapter.notifyDataSetChanged();
//                        } else {
//                            lin_remai.setVisibility(View.GONE);
//                        }

                        te_pjnumber.setText("共" + data.getString("comment_count") + "个评价");
                        goods_num = data.getString("comment_count");
                        te_getmore.setText("查看更多评论(" + goods_num + ")");
                        JSONArray jso_pj = data.getJSONArray("comment_list");
                        list_pj.removeAll(list_pj);
                        if (jso_pj.length() >= 1) {
                            for (int i = 0; i < jso_pj.length(); i++) {
                                JSONObject dat_pj = jso_pj.getJSONObject(i);
                                String url_head = dat_pj
                                        .getString("goods_thumb");
                                String[] urls = {"无"};
                                if (dat_pj.getString("images").equals("null")
                                        || dat_pj.getString("images").isEmpty()
                                        || dat_pj.getString("images").length() < 1) {
                                    urls[0] = "无";
                                } else {
                                    JSONArray jsourl = dat_pj
                                            .getJSONArray("images");
                                    if (jsourl.length() > 0) {
                                        urls = new String[jsourl.length()];
                                        for (int j = 0; j < jsourl.length(); j++) {
                                            urls[j] = jsourl.getString(j);
                                        }
                                    } else
                                        urls[0] = "无";
                                }
                                list_pj.add(new pj_adaData(url_head, dat_pj
                                        .getString("comment_rank"), dat_pj
                                        .getString("user_name"), dat_pj
                                        .getString("content"), urls));
                            }
                            commentAdapter.notifyDataSetChanged();
                        }


                        coupon = JSON.parseObject(jso.getString("bonus"), Coupon.class);
                        couponView.setVisibility(coupon.isIs_show_bonus() ? View.VISIBLE : View.GONE);
                        couponList = coupon.getBonus_list();
                        couponAdapter.refresh(couponList);

                        // 组合推荐
                        if (TextUtils.isEmpty(jso.getString("group_title"))) {
                            list_recomend.removeAll(list_recomend);
                            ada_recommend.notifyDataSetChanged();
                            te_recommend.setVisibility(View.GONE);
                            lin_recommend.setVisibility(View.GONE);
                        } else {
                            te_recommend.setVisibility(View.VISIBLE);
                            lin_recommend.setVisibility(View.VISIBLE);
                            JSONArray jsarr = jso.getJSONArray("group_list");
                            list_recomend.removeAll(list_recomend);
                            for (int i = 0; i < jsarr.length(); i++) {
                                JSONObject jsorecom = jsarr.getJSONObject(i);
                                list_recomend.add(new recommendData(
                                        jsorecom.getString("group_shop_price"),
                                        jsorecom.getString("group_sale_price"),
                                        jsorecom.getString("group_sale_number"),
                                        jsorecom.getJSONArray("goods_list")));
                            }
                            ada_recommend.notifyDataSetChanged();
//                            te_recommend.setText(jso.getString("group_title"));
//                            te_recommend1.setText(jso.getString("group_subtitle1") + jso.getString("group_subtitle2"));
                        }

                    } else {
                        Toast.makeText(GoodDetailsAty.this,
                                jso1.getString("msg").toString(),
                                Toast.LENGTH_SHORT).show();
                    }
//                    recommendAdapter.notifyDataSetChanged();
//                    new Handler().postDelayed(new Runnable() {
//
//                        @Override
//                        public void run() {
//                            landing.dismiss();
//                        }
//                    }, 1000);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }

            @Override
            public void onFail(String result) {
//                landing.dismiss();
            }

            @Override
            public void onCancel(Callback.CancelledException cex) {
//                landing.dismiss();
            }
        });
    }

    /**
     * 获取购物车商品数量
     */
    private void geShopCartCount() {
        String url = HttpUrl.flow + "act=" + HttpUrl.shop_cart;

        Xutils_Get_Post.getInstance().get(url, new Xutils_Get_Post.XCallBack() {

            @Override
            public void onResponse(String result) {
                try {
                    JSONObject response = new JSONObject(result);
                    if (response.getString("status").equals("1")) {
                        JSONObject jso = response.getJSONObject("data");
                        numb_gwc = Integer.valueOf(jso.getString("cart_goods_num")).intValue();
                        bv1.setBadgeCount(numb_gwc);
                    } else {
                        String jsb = response.getString("msg");
                        Toast.makeText(GoodDetailsAty.this, jsb, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }

            @Override
            public void onFail(String result) {
                geShopCartCount();
            }

            @Override
            public void onCancel(Callback.CancelledException cex) {
                // TODO Auto-generated method stub

            }
        });
    }

    private void setviewweb() {
        String st = "<style type=\"text/css\"> img{ width: 100%; height: auto; display: block; padding:0;margin:0;} p{padding:0;margin:0;} </style> ";
        wb.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        WebSettings settings = wb.getSettings();
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        if (Build.VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        wb.getSettings().setDomStorageEnabled(true);
        wb.getSettings().setJavaScriptEnabled(true);
        wb.getSettings().setBlockNetworkImage(false);
        wb.setVerticalScrollBarEnabled(false);
        wb.setHorizontalScrollBarEnabled(false);
        wb.getSettings().setLoadWithOverviewMode(true);
        wb.loadDataWithBaseURL(null, st + goods_desc, "text/html", "UTF-8",
                null);
    }

    public void setselect(int i) {
        te_sp.setSelected(i == 1);
        view_sp.setVisibility(i == 1 ? View.VISIBLE : View.GONE);
        te_xq.setSelected(i == 2);
        view_xq.setVisibility(i == 2 ? View.VISIBLE : View.GONE);
        te_pj.setSelected(i == 3);
        view_pj.setVisibility(i == 3 ? View.VISIBLE : View.GONE);
    }

    /**
     * 加入购物车
     *
     * @param goods_id
     * @param type
     */
    public void addInShopCart(String goods_id, final int type) {
        String url = HttpUrl.flow;
        JSONObject ja = new JSONObject();
        try {

            ja.put("goods_id", goods_id);
            ja.put("number", minnum);
            ja.put("festival", is_festival);

            if (guige.equals("-2")) {
                JSONArray jsa = new JSONArray();
                ja.put("spec", jsa);
            } else {
                String[] ids = guige.split(",");
                JSONArray jsa = new JSONArray(ids);
                ja.put("spec", jsa);
            }

        } catch (JSONException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }

        HashMap<String, String> maps = new HashMap<>();
        maps.put("act", "add_to_cart");
        maps.put("goods", ja.toString());

        Xutils_Get_Post.getInstance().post(url, maps, new Xutils_Get_Post.XCallBack() {

            @Override
            public void onResponse(String result) {
                JSONObject object;
                try {
                    object = new JSONObject(result);
                    int status = object.getInt("status");
                    if (status == 1) {
                        JSONObject jso = object.getJSONObject("data");
                        numb_gwc = DecimalUtil.string2Int(jso.getString("goods_number"));
                        bv1.setBadgeCount(numb_gwc);
                        if (type == 1) {
                            toShopCart();
                        } else {
                            toast("加入购物车成功");
                        }
                    } else {
                        toast("加入购物车失败");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail(String result) {
            }

            @Override
            public void onCancel(Callback.CancelledException cex) {
            }
        });
    }

    public static class MyHandler extends Handler {
        WeakReference<GoodDetailsAty> referenceObj;

        public MyHandler(GoodDetailsAty activity) {
            referenceObj = new WeakReference(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            GoodDetailsAty activity = referenceObj.get();
            switch (msg.what) {
                case 0x01:
//                    activity.toShopCart();
                    break;
                case 0x02:
                    activity.reload();
                    break;
                default:
                    break;
            }
        }
    }

    //收藏商品
    private void collectGood(final String goods_id) {
        String url = "https://app.juandie.com/api_mobile/user.php?act=collect&goods_id=" + goods_id;

        Xutils_Get_Post.getInstance().get(url, new Xutils_Get_Post.XCallBack() {

            @Override
            public void onResponse(String result) {
                JSONObject jso1;
                try {
                    jso1 = new JSONObject(result);
                    if (jso1.getString("status").equals("1")) {
                        im_sc.setImageResource(R.mipmap.collect);
                        toast("收藏成功，可以去收藏夹查看~！");
                    } else {
                        String msg = jso1.getString("msg");
                        if (msg.contains("已经存在")) {
                            toast("收藏成功，可以去收藏夹查看~！");
                        }
                    }

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail(String result) {
                collectGood(goods_id);
            }

            @Override
            public void onCancel(Callback.CancelledException cex) {
                // TODO Auto-generated method stub

            }
        });
    }

    public void setMenu() {
        View view = LayoutInflater.from(GoodDetailsAty.this).inflate(R.layout.pop_good_detail_menu, null);

        final CusPopWindow popMenu = new CusPopWindow.PopupWindowBuilder(GoodDetailsAty.this)
                .setView(view)
                .size(DensityUtil.dip2px(160), -2)
                .enableBackgroundDark(true)
                .create();

        popMenu.showAsDropDown(re_top, -DensityUtil.dip2px(15), -DensityUtil.dip2px(10), Gravity.END);

//        if (Build.VERSION.SDK_INT >= 24) {
//            popMenu.showAtLocation(GoodDetailsAty.this.getWindow().getDecorView(), Gravity.NO_GRAVITY, (int) (w_screen * 450 / 750.0), (int) (w_screen * 110 / 750.0));
//        } else {
//            popMenu.showAtLocation(re_top, Gravity.NO_GRAVITY, (int) (w_screen * 450 / 750.0), (int) (w_screen * 110 / 750.0));
//        }
//        popMenu.showAtLocation(GoodDetailsAty.this.findViewById(R.id.good_detail_view), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

        view.findViewById(R.id.item_menu_index).setOnClickListener(onc);
        view.findViewById(R.id.item_menu_search).setOnClickListener(onc);
        view.findViewById(R.id.item_menu_collection).setOnClickListener(onc);
        view.findViewById(R.id.item_menu_share).setOnClickListener(onc);
        view.findViewById(R.id.item_menu_browsing_history).setOnClickListener(onc);
        view.findViewById(R.id.item_menu_me).setOnClickListener(onc);
    }

    //menu点击事件
    View.OnClickListener onc = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.item_menu_index://首页
                    UiHelper.toActivity(GoodDetailsAty.this, MainActivity.class);
                    MainActivity.handler.sendEmptyMessage(0x01);
                    break;
                case R.id.item_menu_search://搜索
                    UiHelper.toActivity(GoodDetailsAty.this, new Intent(GoodDetailsAty.this, GoodSearchAty.class));
                    break;
                case R.id.item_menu_collection://收藏
                    if (isLogin()) {
                        Intent i = new Intent();
                        i.putExtra("type", "1");
                        i.setClass(GoodDetailsAty.this, MySC.class);
                        UiHelper.toActivity(GoodDetailsAty.this, i);
                    } else {
                        UiHelper.toLoginActivity(GoodDetailsAty.this);
                    }
                    break;
                case R.id.item_menu_share:
                    showShare();
                    break;
                case R.id.item_menu_browsing_history:
                    Intent i = new Intent();
                    i.putExtra("type", "2");
                    i.setClass(GoodDetailsAty.this, MySC.class);
                    UiHelper.toActivity(GoodDetailsAty.this, i);
                    break;
                case R.id.item_menu_me:
                    UiHelper.toActivity(GoodDetailsAty.this, MainActivity.class);
                    MainActivity.handler.sendEmptyMessage(0x04);
                    break;
                default:
                    break;
            }
        }
    };


    /**
     * 分享
     */
    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        // 关闭sso授权
        oks.disableSSOWhenAuthorize();
        oks.setTitle(te_name.getText().toString());
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("https://m.juandie.com/goods/" + goods_id + ".html");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("娟蝶鲜花-同城花店订鲜花生日蛋糕速递APP");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        // oks.setImagePath("/sdcard/test.jpg");// 确保SDcard下面存在此张图片
        oks.setImageUrl(imageUrls[0]);
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("https://m.juandie.com/goods/" + goods_id + ".html");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("https://m.juandie.com/goods/" + goods_id + ".html");

        // imageUrls[0]

        // 启动分享GUI
        oks.show(GoodDetailsAty.this);

        // 避免微信登录与分享冲突
        SharedPreferenceUtils.setPreference(GoodDetailsAty.this, Constant.isfenx, "true", "S");
    }

    /**
     * 领取优惠券
     *
     * @param field
     */
    private void receiveCoupon(String field) {
        landing.show();
        String url = "https://app.juandie.com/api_mobile/index.php?act=receive_bonus&field=" + field;
        Xutils_Get_Post.getInstance().get(url, new Xutils_Get_Post.XCallBack() {

            @Override
            public void onResponse(String result) {
                try {
                    JSONObject response = new JSONObject(result);
                    popCoupon.dismiss();
                    if (response.getString("status").equals("1")) {
                        toast(response.getString("msg") + "您可以在 个人中心—我的优惠券 查看领取情况");
                        collectGood(goods_id);
                    } else {
                        String jsb = response.getString("msg");
                        if (jsb.contains("登录")) {
                            UiHelper.toLoginActivity(GoodDetailsAty.this);
                        } else if (jsb.contains("绑定")) {
                            String iswxbd = (String) SharedPreferenceUtils.getPreference(GoodDetailsAty.this, Constant.iswxbd, "S");

                            Intent i = new Intent();
                            i.setClass(GoodDetailsAty.this, wx_bdgh.class);
                            i.putExtra("type", iswxbd);
                            UiHelper.toActivity(GoodDetailsAty.this, i);
                        }
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        landing.dismiss();
                    }
                }, 100);
            }

            @Override
            public void onFail(String result) {
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        landing.dismiss();
                    }
                }, 100);

            }

            @Override
            public void onCancel(Callback.CancelledException cex) {
                // TODO Auto-generated method stub

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        wb.removeAllViews();
        wb.destroy();
    }

    TextView te_pricegg;


    /**
     * 选择规格弹框
     *
     * @param type 0：加入购物车；1：直接购买
     */
    private void showSpecs(final int type) {
        View view = LayoutInflater.from(GoodDetailsAty.this).inflate(R.layout.pop_good_specs, null);

        popSpecs = new CusPopWindow.PopupWindowBuilder(GoodDetailsAty.this)
                .setView(view)
                .size(DensityUtil.getScreenWidth(), DensityUtil.getScreenHeight() / 2)
                .enableBackgroundDark(true)
                .create();

        popSpecs.showAtLocation(GoodDetailsAty.this.findViewById(R.id.good_detail_view), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);


        ListView liView = (ListView) view.findViewById(R.id.gg_listv);
        ImageView imageView = (ImageView) view.findViewById(R.id.gg_imhead);
        TextView te_name = (TextView) view.findViewById(R.id.gg_tetit);
        te_pricegg = (TextView) view.findViewById(R.id.gg_teprice);
        final TextView te_qr = (TextView) view.findViewById(R.id.gg_teqr);

        ImageUtils.setImage(GoodDetailsAty.this, goodUrl, imageView);
        te_name.setText(goodName);
        te_pricegg.setText(goodPrice);

        for (GoodSpecs specs : specsList) {
            for (GoodSpecs.ItemsBean itemsBean : specs.getItems()) {
                itemsBean.setCheck(false);
            }
        }
        specsList.get(0).getItems().get(0).setCheck(true);
        GoodSpecsAdapter adapter = new GoodSpecsAdapter(specsList, GoodDetailsAty.this);
        liView.setAdapter(adapter);

        adapter.setOnSpecsChange(new GoodSpecsAdapter.OnSpecsChangeCallback() {
            @Override
            public void onSpecsChange() {
                loadSpecs(goods_id);
            }
        });

        te_qr.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                te_qr.setEnabled(false);
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        te_qr.setEnabled(true);
                    }
                }, 500);

                guige = getIds();

                if (TextUtils.isEmpty(guige)) {
                    toast("请选择规格");
                } else {
                    addInShopCart(goods_id, type);
                    popSpecs.dismiss();
                }
            }
        });
        view.findViewById(R.id.gg_teqx).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                popSpecs.dismiss();
            }
        });
    }

    private String getIds() {
        String ids = "";

        for (GoodSpecs specs : specsList) {
            for (GoodSpecs.ItemsBean itemsBean : specs.getItems()) {
                if (itemsBean.isCheck()) {
                    ids += itemsBean.getId() + ",";
                }
            }
        }

        if (ids.contains(",")) {
            ids = ids.substring(0, ids.length() - 1);
        }
        return ids;
    }

    /**
     * 选择规格
     *
     * @param goods_id 规格Id
     */
    private void loadSpecs(final String goods_id) {
        landing.show();
        guige = getIds();

        String url;
        if (TextUtils.isEmpty(is_festival)) {
            url = "https://app.juandie.com/api_mobile/goods.php?act=index&goods_id=" + goods_id + "&spec_ids=" + guige;
        } else {
            url = "https://app.juandie.com/api_mobile/goods.php?act=index&goods_id=" + goods_id + "&spec_ids=" + guige + "&is_festival=" + is_festival;
        }

        Xutils_Get_Post.getInstance().get(url, new Xutils_Get_Post.XCallBack() {

            @Override
            public void onResponse(String result) {
                JSONObject jso1;
                try {
                    jso1 = new JSONObject(result);
                    if (jso1.getString("status").equals("1")) {

                        JSONObject jso = jso1.getJSONObject("data");
                        JSONObject data = jso.getJSONObject("goods");

                        JSONObject jsoc = data.getJSONObject("festival_price_info");
                        if (jsoc.getString("festival_open").equals("true")) {

                            JSONObject jso_psj = jsoc.getJSONObject("festival_price_now");

                            JSONObject jso_jrj = jsoc.getJSONObject("festival_price_old");
                            if (TextUtils.equals(is_festival, "0")) {
                                te_pricegg.setText("￥" + jso_psj.getString("price"));

                            } else {
                                te_pricegg.setText("￥" + jso_jrj.getString("price"));
                            }

                        } else {
                            te_pricegg.setText("￥" + data.getString("shop_price"));
                        }

                        landing.dismiss();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail(String result) {
                landing.dismiss();
            }

            @Override
            public void onCancel(Callback.CancelledException cex) {
                landing.dismiss();
            }
        });
    }

    /*
     * @see
     * com.example.juandie_hua.mainactivity.goods.recommend.recommendAdapter.gotobuy#gotobuy(int)
     */
    @Override
    public void gotobuy(int pos) {
        if (pos <= list_recomend.size() - 1) {
            httpPost_initgMycar(pos);
        }

    }

    /**
     * 甜蜜组合购买
     *
     * @param pos pos
     */
    public void httpPost_initgMycar(int pos) {
        String url = HttpUrl.flow;
        JSONArray arrayx = new JSONArray();
        try {
            JSONArray array1 = list_recomend.get(pos).getJsaArray();
            for (int i = 0; i < array1.length(); i++) {
                JSONObject ja = new JSONObject();
                JSONObject ja1 = array1.getJSONObject(i);
                ja.put("goods_id", ja1.getString("id"));
                ja.put("number", "1");
                ja.put("festival", "0");

                arrayx.put(ja);
            }

        } catch (JSONException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }

        HashMap<String, String> maps = new HashMap<>();
        maps.put("act", "add_group_goods_to_cart");
        maps.put("group_goods_list", arrayx.toString());

        Xutils_Get_Post.getInstance().post(url, maps, new Xutils_Get_Post.XCallBack() {

            @Override
            public void onResponse(String result) {
                JSONObject object;
                try {
                    object = new JSONObject(result);
                    int status = object.getInt("status");
                    if (status == 1) {
                        JSONObject jso = object.getJSONObject("data");
                        numb_gwc = Integer.valueOf(jso.getString("goods_number")).intValue();
                        bv1.setBadgeCount(numb_gwc);
                        toast("加入购物车成功");
                        toShopCart();
                    } else {
                        toast("加入购物车失败");
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
            public void onCancel(Callback.CancelledException cex) {
                // TODO Auto-generated method stub

            }
        });
    }

    /**
     * 领取优惠券
     */
    private void showCoupon() {
        View view = LayoutInflater.from(GoodDetailsAty.this).inflate(R.layout.pop_coupon, null);

        popCoupon = new CusPopWindow.PopupWindowBuilder(GoodDetailsAty.this)
                .setView(view)
                .size(DensityUtil.getScreenWidth(), -2)
                .enableBackgroundDark(true)
                .create();

        popCoupon.showAtLocation(GoodDetailsAty.this.findViewById(R.id.good_detail_view), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

        GridView gridView = view.findViewById(R.id.pop_coupon_grid);
        gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        TextView btnGetCoupon = view.findViewById(R.id.pop_get_coupon_btn);
        CouponReceiveAdapter adapter = new CouponReceiveAdapter(GoodDetailsAty.this, couponList);
        gridView.setAdapter(adapter);

        btnGetCoupon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                receiveCoupon(coupon.getField());
            }
        });
    }
}
