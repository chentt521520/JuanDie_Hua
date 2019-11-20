package com.example.juandie_hua.ui.good;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.x;
import org.xutils.common.Callback.CancelledException;
import org.xutils.view.annotation.ViewInject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.juandie_hua.R;
import com.example.juandie_hua.app.BaseActivity;
import com.example.juandie_hua.http.HttpUrl;
import com.example.juandie_hua.helper.UiHelper;
import com.example.juandie_hua.mainactivity.CircularProgressView;
import com.example.juandie_hua.mainactivity.Xutils_Get_Post;
import com.example.juandie_hua.mainactivity.Xutils_Get_Post.XCallBack;
import com.example.juandie_hua.mainactivity.adapter.OnGoodListCallback;
import com.example.juandie_hua.mainactivity.fenlei.ss.MyGridViewHeadFoot;
import com.example.juandie_hua.mainactivity.fenlei.ss.gdlist_adaData;
import com.example.juandie_hua.mainactivity.fenlei.ss.shaixuan;
import com.example.juandie_hua.mainactivity.internet_if;
import com.example.juandie_hua.mainactivity.internet_landing;
import com.example.juandie_hua.mainactivity.internet_landing.re_jk;
import com.example.juandie_hua.ui.adapter.GoodListAdapter;
import com.example.juandie_hua.utils.StatusBarUtils;
import com.example.juandie_hua.utils.ViewUtils;
import com.umeng.analytics.MobclickAgent;

public class GoodListAty extends BaseActivity implements re_jk {

    @ViewInject(R.id.gdlist_lintjian)
    LinearLayout lin_tj;
    @ViewInject(R.id.gdlist_lintj1)
    LinearLayout lin_th1;
    @ViewInject(R.id.gdlist_tetj11)
    TextView te_tj1;
    @ViewInject(R.id.gdlist_imjg)
    ImageView im_tj1;// 综合

    @ViewInject(R.id.gdlist_tetj2)
    TextView te_xl;

    @ViewInject(R.id.gdlist_lintj3)
    LinearLayout lin_jge;
    @ViewInject(R.id.gdlist_tetj31)
    TextView te_jg;
    @ViewInject(R.id.gdlist_imzjg)
    ImageView im_jg;

    @ViewInject(R.id.gdlist_lintj4)
    LinearLayout lin_shaix;
    @ViewInject(R.id.gdlist_imsx)
    ImageView im_shaix;

    @ViewInject(R.id.gdlist_recyc)
    MyGridViewHeadFoot listv_v;

    @ViewInject(R.id.gdlist_linzh1)
    LinearLayout lin_zh1;
    @ViewInject(R.id.gdlist_tezhz1)
    TextView te_zh1;
    @ViewInject(R.id.gdlist_imzhz1)
    ImageView im_zh1;

    @ViewInject(R.id.gdlist_linzh2)
    LinearLayout lin_zh2;
    @ViewInject(R.id.gdlist_tezhz2)
    TextView te_zh2;
    @ViewInject(R.id.gdlist_imzhz2)
    ImageView im_zh2;

    @ViewInject(R.id.gdlist_linzh3)
    LinearLayout lin_zh3;
    @ViewInject(R.id.gdlist_tezhz3)
    TextView te_zh3;
    @ViewInject(R.id.gdlist_imzhz3)
    ImageView im_zh3;

    @ViewInject(R.id.gdlist_linzh)
    LinearLayout lin_;

    @ViewInject(R.id.gdlist_vkbai)
    View v_kb;

    @ViewInject(R.id.gdlist_nogoods)
    LinearLayout layout_nogoods;
    @ViewInject(R.id.nogoods_tego)
    TextView te_nogoods;
    @ViewInject(R.id.nogoods_tecon)
    TextView te_nogoods1;

    @ViewInject(R.id.gdlist_gotop)
    ImageView im_top;

    GoodListAdapter adapter;
    List<gdlist_adaData> list;

    String cid, keywords, filter_attr, order, by, positionget = "无";
    int page = 1;

    // 尾部加载更多
    TextView temore;
    LinearLayout linfoot;
    CircularProgressView cir_view;
    View vfoot;
    boolean tfalse = true;
    @ViewInject(R.id.gdlist_swp)
    SwipeRefreshLayout spr;

    boolean price_control = false;// false从低到高
    public static MyHandler handler;
    SharedPreferences preferences;

    internet_landing inLanding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitleContentView(R.layout.goodslist);
        StatusBarUtils.with(this).setBarColor(R.color.white_fff);

        x.view().inject(this);
        handler = new MyHandler(GoodListAty.this);
        inLanding = new internet_landing(this);
        inLanding.setonc(this);
        preferences = PreferenceManager.getDefaultSharedPreferences(GoodListAty.this);
        setviewhw();
        setviewdata();
        setviewlisten();
    }

    private void setviewlisten() {
        lin_th1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                setselect(1);
                if (lin_.isShown()) {
                    lin_.setVisibility(View.GONE);
                    lin_.startAnimation(AnimationUtils.loadAnimation(
                            GoodListAty.this, R.anim.onc2));
                    im_tj1.setSelected(false);
                } else {
                    lin_.setVisibility(View.VISIBLE);
                    lin_.startAnimation(AnimationUtils.loadAnimation(
                            GoodListAty.this, R.anim.onc1));
                    im_tj1.setSelected(true);
                }
            }
        });
        te_xl.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                setselect(2);
                page = 1;
                list.removeAll(list);
                adapter.notifyDataSetChanged();
                showview_foot();
                order = "sales_count";
                xutils_getlist(cid, keywords, filter_attr, order, by, page);
            }
        });
        lin_jge.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                setselect(3);
                page = 1;
                list.removeAll(list);
                adapter.notifyDataSetChanged();
                showview_foot();
                if (price_control) {// dsc从高到低
                    by = "desc";
                    price_control = false;
                    im_jg.setImageResource(R.drawable.jgx);
                } else {
                    by = "asc";
                    price_control = true;
                    im_jg.setImageResource(R.drawable.jgs);
                }
                order = "shop_price";
                xutils_getlist(cid, keywords, filter_attr, order, by, page);
            }
        });
        lin_shaix.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                setselect(4);
                Intent i = new Intent();
                i.putExtra("cid", cid);
                i.putExtra("positionget", positionget);
                i.putExtra("filter_attr", filter_attr);
                i.setClass(GoodListAty.this, shaixuan.class);
                startActivity(i);
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
            }
        });
        v_kb.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (lin_.isShown()) {
                    lin_.setVisibility(View.GONE);
                }
            }
        });
        lin_zh1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                setselect_tj(1);
                im_tj1.setSelected(false);
                page = 1;
                list.clear();
                adapter.notifyDataSetChanged();
                showview_foot();
                order = "sort_order";
                xutils_getlist(cid, keywords, filter_attr, order, by, page);
            }
        });
        lin_zh2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                setselect_tj(2);
                im_tj1.setSelected(false);
                page = 1;
                list.clear();
                adapter.notifyDataSetChanged();
                showview_foot();
                order = "goods_id";
                xutils_getlist(cid, keywords, filter_attr, order, by, page);
            }
        });
        lin_zh3.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                setselect_tj(3);
                im_tj1.setSelected(false);
                page = 1;
                list.clear();
                adapter.notifyDataSetChanged();
                showview_foot();
                order = "sales_count";
                xutils_getlist(cid, keywords, filter_attr, order, by, page);

            }
        });

        im_top.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                listv_v.setSelection(0);
                im_top.setVisibility(View.GONE);
            }
        });

        listv_v.setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                if (firstVisibleItem == 0) {
                    View firstVisibleItemView = listv_v.getChildAt(0);
                    if (firstVisibleItemView != null && firstVisibleItemView.getTop() == 0) {
                    }
                } else if ((firstVisibleItem + visibleItemCount) == totalItemCount) {
                    if (!tfalse) {
                        View lastVisibleItemView = listv_v.getChildAt(listv_v.getChildCount() - 1);
                        if (lastVisibleItemView != null && lastVisibleItemView.getBottom() <= listv_v.getHeight()) {
                            page++;
                            xutils_getlist(cid, keywords, filter_attr, order, by, page);
                        }
                    }
                }
                if (firstVisibleItem >= 10) {
                    im_top.setVisibility(View.VISIBLE);
                } else
                    im_top.setVisibility(View.GONE);
            }

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == SCROLL_STATE_IDLE) {
                    tfalse = true;
                } else
                    tfalse = false;
            }

        });

        spr.setOnRefreshListener(new OnRefreshListener() {

            @Override
            public void onRefresh() {
                page = 1;
                list.clear();
                adapter.notifyDataSetChanged();

                showview_foot();

                xutils_getlist(cid, keywords, filter_attr, order, by, page);
                // 停止刷新动画
                spr.setRefreshing(false);
                toast("刷新成功");
            }
        });

        adapter.setOnGoodListCallback(new OnGoodListCallback<gdlist_adaData>() {
            @Override
            public void setOnAddShopCallback(View view, gdlist_adaData data) {
                addShopCart(data.getId());
            }

            @Override
            public void setOnItemClickCallback(View view, gdlist_adaData data) {
                UiHelper.toGoodDetailActivity(GoodListAty.this, data.getId());
            }
        });
    }

    private void setviewdata() {
        list = new ArrayList<gdlist_adaData>();
        adapter = new GoodListAdapter(GoodListAty.this, list);
        listv_v.setAdapter(adapter);

        Intent i = getIntent();
        Bundle bundle = i.getExtras();

        cid = bundle.getString("cid");
        keywords = bundle.getString("keywords");
        filter_attr = bundle.getString("filter_attr");
        order = bundle.getString("order");
        by = bundle.getString("by");
        positionget = bundle.getString("positionget");

        if (inLanding.if_inter()) {
            xutils_getfenlei(cid);
            xutils_getlist(cid, keywords, filter_attr, order, by, page);
        }

    }

    private void setviewhw() {
        this.getTitleView().setTitleText("商品列表");
        te_nogoods.setVisibility(View.GONE);
        te_nogoods1.setText("抱歉，没有找到符合条件的商品！");

        spr.setColorSchemeResources(R.color.xin);
        vfoot = LayoutInflater.from(GoodListAty.this).inflate(R.layout.textv_landmore, null);
        temore = (TextView) vfoot.findViewById(R.id.textv_landmore);
        linfoot = (LinearLayout) vfoot.findViewById(R.id.linv_addmore);
        cir_view = (CircularProgressView) vfoot.findViewById(R.id.cirprogre_view);
        listv_v.addFooterView(vfoot);

        te_tj1.setSelected(true);
        te_zh1.setSelected(true);
        listv_v.setSelector(new ColorDrawable(Color.TRANSPARENT));
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int w = dm.widthPixels;

        ViewUtils.setviewhw_lin(lin_tj, w, (int) (w * 40 / 375.0), 0, 0, 0, 0);
        ViewUtils.setviewhw_lin(im_tj1, (int) (w * 12 / 375.0), (int) (w * 35 / 375.0),
                (int) (w * 5 / 375.0), (int) (w * 2 / 375.0), 0, 0);
        ViewUtils.setviewhw_lin(im_jg, (int) (w * 18 / 375.0), (int) (w * 40 / 375.0),
                (int) (w * 2 / 375.0), 0, 0, 0);
        ViewUtils.setviewhw_lin(im_shaix, (int) (w * 15 / 375.0), (int) (w * 40 / 375.0),
                (int) (w * 2 / 375.0), 0, 0, 0);

        ViewUtils.setviewhw_lin(lin_zh1, w, (int) (w * 40 / 375.0), 0, 0, 0, 0);
        ViewUtils.setviewhw_lin(lin_zh2, w, (int) (w * 40 / 375.0), 0, 0, 0, 0);
        ViewUtils.setviewhw_lin(lin_zh3, w, (int) (w * 40 / 375.0), 0, 0, 0, 0);
        te_zh1.setPadding((int) (w * 30 / 375.0), 0, 0, 0);
        te_zh2.setPadding((int) (w * 30 / 375.0), 0, 0, 0);
        te_zh3.setPadding((int) (w * 30 / 375.0), 0, 0, 0);

        ViewUtils.setviewhw_lin(im_zh1, (int) (w * 20 / 375.0), (int) (w * 30 / 375.0),
                0, (int) (w * 10 / 375.0), (int) (w * 30 / 375.0), 0);
        ViewUtils. setviewhw_lin(im_zh2, (int) (w * 20 / 375.0), (int) (w * 30 / 375.0),
                0, (int) (w * 10 / 375.0), (int) (w * 30 / 375.0), 0);
        ViewUtils.setviewhw_lin(im_zh3, (int) (w * 20 / 375.0), (int) (w * 30 / 375.0),
                0, (int) (w * 10 / 375.0), (int) (w * 30 / 375.0), 0);

        ViewUtils.setviewhw_re(layout_nogoods, w, (int) (w * 180 / 375.0), 0,
                (int) (w * 200 / 375.0), 0, 0);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            System.gc();
            finish();
            overridePendingTransition(R.anim.push_right_out,
                    R.anim.push_right_in);

            return false;
        }
        return false;
    }

    private void setselect(int i) {
        switch (i) {
            case 1:
                te_tj1.setSelected(true);
                te_xl.setSelected(false);
                te_jg.setSelected(false);
                im_jg.setImageResource(R.drawable.jg);
                break;
            case 2:
                te_tj1.setSelected(false);
                te_xl.setSelected(true);
                te_jg.setSelected(false);
                im_jg.setImageResource(R.drawable.jg);
                if (lin_.isShown()) {
                    lin_.setVisibility(View.GONE);
                    lin_.startAnimation(AnimationUtils.loadAnimation(
                            GoodListAty.this, R.anim.onc2));
                    im_tj1.setSelected(false);
                }
                break;
            case 3:
                te_tj1.setSelected(false);
                te_xl.setSelected(false);
                te_jg.setSelected(true);

                if (lin_.isShown()) {
                    lin_.setVisibility(View.GONE);
                    lin_.startAnimation(AnimationUtils.loadAnimation(
                            GoodListAty.this, R.anim.onc2));
                    im_tj1.setSelected(false);
                }
                break;
            case 4:
                if (lin_.isShown()) {
                    lin_.setVisibility(View.GONE);
                    lin_.startAnimation(AnimationUtils.loadAnimation(
                            GoodListAty.this, R.anim.onc2));
                    im_tj1.setSelected(false);
                }
                break;

            default:
                break;
        }
    }

    private void setselect_tj(int i) {
        switch (i) {
            case 1:
                te_zh1.setSelected(true);
                te_zh2.setSelected(false);
                te_zh3.setSelected(false);

                te_tj1.setText("综合");

                im_zh1.setVisibility(View.VISIBLE);
                im_zh2.setVisibility(View.GONE);
                im_zh3.setVisibility(View.GONE);
                if (lin_.isShown()) {
                    lin_.setVisibility(View.GONE);
                    lin_.startAnimation(AnimationUtils.loadAnimation(
                            GoodListAty.this, R.anim.onc2));
                }
                break;
            case 2:
                te_zh1.setSelected(false);
                te_zh2.setSelected(true);
                te_zh3.setSelected(false);

                te_tj1.setText("新品");

                im_zh1.setVisibility(View.GONE);
                im_zh2.setVisibility(View.VISIBLE);
                im_zh3.setVisibility(View.GONE);
                if (lin_.isShown()) {
                    lin_.setVisibility(View.GONE);
                    lin_.startAnimation(AnimationUtils.loadAnimation(
                            GoodListAty.this, R.anim.onc2));
                }
                break;
            case 3:
                te_zh1.setSelected(false);
                te_zh2.setSelected(false);
                te_zh3.setSelected(true);

                te_tj1.setText("人气");

                im_zh1.setVisibility(View.GONE);
                im_zh2.setVisibility(View.GONE);
                im_zh3.setVisibility(View.VISIBLE);
                if (lin_.isShown()) {
                    lin_.setVisibility(View.GONE);
                    lin_.startAnimation(AnimationUtils.loadAnimation(
                            GoodListAty.this, R.anim.onc2));
                }
                break;
            default:
                break;
        }
    }

    /**
     * 获取商品列表分类
     */
    String sign = "";

    private void xutils_getfenlei(final String cid) {
        String url = "https://app.juandie.com/api_mobile/category.php?act=get_filter_attr_list&cid="
                + cid;

        Xutils_Get_Post.getInstance().get(url, new XCallBack() {

            @Override
            public void onResponse(String result) {
                try {
                    JSONObject response = new JSONObject(result);
                    if (response.getString("status").equals("1")) {
                        JSONObject jso = response.getJSONObject("data");
                        JSONArray jsa = jso.getJSONArray("all_attr_list");
                        if (jsa.length() >= 1) {
                            lin_shaix.setVisibility(View.VISIBLE);
                        } else {
                            lin_shaix.setVisibility(View.GONE);
                        }

                    } else {
                        String jsb = response.getString("msg");
                        Toast.makeText(GoodListAty.this, jsb, Toast.LENGTH_SHORT)
                                .show();
                    }

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }

            @Override
            public void onFail(String result) {
                xutils_getfenlei(cid);
            }

            @Override
            public void onCancel(CancelledException cex) {
                // TODO Auto-generated method stub

            }
        });
    }

    /**
     * 获取商品列表数据
     */
    private void xutils_getlist(final String cid, final String keywords,
                                final String filter_attr, final String order, final String by,
                                final int page1) {
        layout_nogoods.setVisibility(View.GONE);

        String url = "https://app.juandie.com/api_mobile/category.php?act=goods_list&cid="
                + cid
                + "&keywords="
                + keywords
                + "&filter_attr="
                + filter_attr
                + "&order=" + order + "&by=" + by + "&page=" + page1;
        Xutils_Get_Post.getInstance().get(url, new XCallBack() {

            @Override
            public void onResponse(String result) {
                try {
                    JSONObject response = new JSONObject(result);
                    if (response.getString("status").equals("1")) {
                        JSONObject jso = response.getJSONObject("data");
                        if (jso.getString("goods_count").equals("0")) {
                            if (vfoot.isShown())
                                vfoot.setVisibility(View.GONE);
                            toast("暂时没有更多数据~~");
                        } else {
                            JSONArray jsa = jso.getJSONArray("goods_list");
                            if (jsa.length() == 0) {
                                if (page1 == 1) {
                                    if (vfoot.isShown())
                                        vfoot.setVisibility(View.GONE);
                                    temore.setText("-我是有底线的-");

                                    vfoot.setPadding(0, -vfoot.getHeight(), 0,
                                            0);
                                    toast("暂时没有更多数据~~");
                                    layout_nogoods.setVisibility(View.VISIBLE);
                                } else {
                                    if (cir_view.isShown())
                                        cir_view.setVisibility(View.GONE);
                                    if (!temore.isShown())
                                        temore.setVisibility(View.VISIBLE);
                                    if (!linfoot.isShown()) {
                                        linfoot.setVisibility(View.VISIBLE);
                                    }
                                    temore.setText("-我是有底线的-");
                                    toast("暂时没有更多数据~~");
                                }
                            } else {
                                for (int i = 0; i < jsa.length(); i++) {
                                    JSONObject data = jsa.getJSONObject(i);
                                    list.add(new gdlist_adaData(data.getString("goods_id"),
                                            data.getString("goods_thumb"),
                                            data.getString("goods_name"),
                                            data.getString("shop_price"),
                                            data.getString("goods_sale_number"),
                                            data.getString("market_price")));
                                }
                                adapter.notifyDataSetChanged();
                                if (!vfoot.isShown()) {
                                    vfoot.setVisibility(View.VISIBLE);
                                }
                                if (layout_nogoods.isShown()) {
                                    layout_nogoods.setVisibility(View.GONE);
                                    lin_tj.setVisibility(View.VISIBLE);
                                }
                                if (page1 == 1) {
                                    if (jsa.length() <= 6) {
                                        vfoot.setVisibility(View.GONE);
                                    }
                                }
                            }
                        }
                    } else {
                        toast(response.getString("msg"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail(String result) {
                xutils_getlist(cid, keywords, filter_attr, order, by, page1);
            }

            @Override
            public void onCancel(CancelledException cex) {
            }
        });
    }

    public void showview_foot() {
        vfoot.setPadding(0, 0, 0, 0);
        if (!vfoot.isShown()) {
            vfoot.setVisibility(View.VISIBLE);
        }
        if (!cir_view.isShown())
            cir_view.setVisibility(View.VISIBLE);
        if (!temore.isShown())
            temore.setVisibility(View.VISIBLE);
        if (!temore.getText().toString().equals("    加载中....."))
            temore.setText("    加载中.....");

    }

    public static class MyHandler extends Handler {
        WeakReference<GoodListAty> referenceObj;

        public MyHandler(GoodListAty activity) {
            referenceObj = new WeakReference<GoodListAty>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            GoodListAty activity = referenceObj.get();
            switch (msg.what) {
                case 0x001:
                    Bundle bundle = msg.getData();
                    activity.filter_attr = bundle.getString("filter_attr");
                    activity.positionget = bundle.getString("positionget");
                    activity.list.clear();
                    activity.adapter.notifyDataSetChanged();
                    activity.page = 1;
                    activity.xutils_getlist(activity.cid, activity.keywords,
                            activity.filter_attr, activity.order, activity.by,
                            activity.page);
                    break;

                default:
                    break;
            }
        }
    }

    @Override
    public void re_requestjk(View v) {
        if (new internet_if().isNetworkConnected(this)) {
            inLanding.dismissinter();
            xutils_getlist(cid, keywords, filter_attr, order, by, page);
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
    }

    private void addShopCart(String goodId) {
//        String url = "https://app.juandie.com/api_mobile/flow.php";
        String url = HttpUrl.flow;
        JSONObject ja = new JSONObject();
        try {
            ja.put("goods_id", goodId);
            ja.put("number", 1);
            ja.put("festival", 0);
            JSONArray jsa = new JSONArray();
            ja.put("spec", jsa);
        } catch (JSONException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }

        HashMap<String, String> maps = new HashMap<>();
        maps.put("act", HttpUrl.add_shop_cart);
        maps.put("goods", ja.toString());

        System.out.println(ja.toString());
        Xutils_Get_Post.getInstance().post(url, maps, new XCallBack() {

            @Override
            public void onResponse(String result) {
                JSONObject object;
                try {
                    object = new JSONObject(result);
                    int status = object.getInt("status");
                    if (status == 1) {
                        Toast.makeText(GoodListAty.this, "加入购物车成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(GoodListAty.this, "加入购物车失败", Toast.LENGTH_SHORT).show();
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
}
