package com.example.juandie_hua.ui.tab;

import android.annotation.SuppressLint;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.common.util.DensityUtil;
import org.xutils.x;
import org.xutils.common.Callback.CancelledException;
import org.xutils.view.annotation.ViewInject;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.juandie_hua.R;
import com.example.juandie_hua.mainactivity.adapter.OnGoodListCallback;
import com.example.juandie_hua.model.GoodSpecs;
import com.example.juandie_hua.ui.MainActivity;
import com.example.juandie_hua.app.BaseFragment;
import com.example.juandie_hua.http.HttpUrl;
import com.example.juandie_hua.calender.utils.ImageUtils;
import com.example.juandie_hua.helper.UiHelper;
import com.example.juandie_hua.mainactivity.Landing;
import com.example.juandie_hua.mainactivity.Xutils_Get_Post;
import com.example.juandie_hua.mainactivity.Xutils_Get_Post.XCallBack;
import com.example.juandie_hua.mainactivity.adapter.OnListItemClickListener;
import com.example.juandie_hua.mainactivity.adapter.ShopCartAdapter;
import com.example.juandie_hua.mainactivity.adapter.ShopCartFavorAdapter;
import com.example.juandie_hua.mainactivity.adapter.ShopCartRecommondAdapter;
import com.example.juandie_hua.model.ShopCartFavor;
import com.example.juandie_hua.model.ShopCartGood;
import com.example.juandie_hua.model.ShopCartRecommond;
import com.example.juandie_hua.mycar.orderpay.orderin;
import com.example.juandie_hua.ui.adapter.GoodSpecsAdapter;
import com.example.juandie_hua.ui.login.LoginAty;
import com.example.juandie_hua.utils.DecimalUtil;
import com.example.juandie_hua.utils.StrUtils;
import com.example.juandie_hua.view.CusPopWindow;
import com.example.juandie_hua.view.CustomDialog;


public class ShopCart extends BaseFragment {

    View v;
    @ViewInject(R.id.back_page)
    ImageView im_return;
    @ViewInject(R.id.title_text)
    TextView title_text;
    /**
     * 购物车列表
     */
    @ViewInject(R.id.mycar_listv)
    ListView listv_car;

    /**
     * 推荐标题
     */
    @ViewInject(R.id.mycar_recommend_title)
    TextView te_titui;
    /**
     * 推荐列表
     */
    @ViewInject(R.id.ui_shop_cart_recommend_list)
    RecyclerView RecyRecommad;

    /**
     * 结算栏
     */
    @ViewInject(R.id.mycar_rejieshuan)
    LinearLayout re_jiesuan;
    /**
     * 总价
     */
    @ViewInject(R.id.mycar_totalprice)
    TextView te_price;
    /**
     * 支付按钮
     */
    @ViewInject(R.id.mycar_pay)
    TextView te_pay;

    /**
     * 无数据显示
     */
    @ViewInject(R.id.nogoods_view)
    LinearLayout layout_nogoods;
    /**
     * 返回首页
     */
    @ViewInject(R.id.nogoods_tego)
    TextView te_go;
    /**
     * 猜你喜欢列表
     */
    @ViewInject(R.id.shop_cart_favor_grid)
    GridView favor_grid;
    /**
     * 下拉刷新
     */
    @ViewInject(R.id.gwc_swp)
    SwipeRefreshLayout spr;

    private ShopCartAdapter shopCartAdapter;
    private ArrayList<ShopCartGood> goodList;
    private ShopCartRecommondAdapter shopCartRecommondAdapter;
    private ArrayList<ShopCartRecommond> recommondList;
    private ShopCartFavorAdapter shopCartFavorAdapter;
    private ArrayList<ShopCartFavor> favorList;

    double totalPrice = 0.00;

    PopupWindow window;

    Landing landing;
    private List<GoodSpecs> specsList;
    private ShopCartGood shopCartGood;
    int intentFlag;
    private CustomDialog deleteDialog, loginDialog;

    public ShopCart() {
    }

    @SuppressLint("ValidFragment")
    public ShopCart(int intentFlag) {
        this.intentFlag = intentFlag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (v == null) {
            v = inflater.inflate(R.layout.gwuche, container, false);

            x.view().inject(this, v);
            landing = new Landing(getActivity(), R.style.CustomDialog);

            setviewhw();
            setviewdata();
            setviewlisten();
        }
        return v;
    }

    private void setviewlisten() {
        im_return.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        te_pay.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!isLogin()) {
                    loginDialog();
                } else {
                    commitOrder();
                }
            }
        });
        //刷新
        spr.setOnRefreshListener(new OnRefreshListener() {

            @Override
            public void onRefresh() {
                getShopCartList(0);
                spr.setRefreshing(false);
                toast("刷新成功");
            }
        });
        //随便逛逛
        te_go.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                MainActivity.handler.sendEmptyMessage(0x01);
            }
        });

        shopCartRecommondAdapter.setOnViewClickListener(new OnListItemClickListener() {
            @Override
            public void onItemClickListener(int position) {
                UiHelper.toGoodDetailActivity(getActivity(), recommondList.get(position).getId());
            }

            @Override
            public void onAddCartClickListener(int position) {
                addShopCart(recommondList.get(position).getId());
            }
        });

        shopCartFavorAdapter.setOnGoodListCallback(new OnGoodListCallback<ShopCartFavor>() {
            @Override
            public void setOnAddShopCallback(View view, ShopCartFavor data) {
                addShopCart(data.getId());
            }

            @Override
            public void setOnItemClickCallback(View view, ShopCartFavor data) {
                UiHelper.toGoodDetailActivity(getActivity(), data.getId());
            }
        });

        shopCartAdapter.setnumber_change(new ShopCartAdapter.number_change() {
            @Override
            public void item(int position, View view) {
                UiHelper.toGoodDetailActivity(getActivity(), goodList.get(position).getGoods_id());
            }

            @Override
            public void jia(int position, View view) {
                ShopCartGood data = goodList.get(position);
                int number = Integer.valueOf(data.getGoods_number());
                if (number >= 99) {
                    number = 99;
                } else
                    number++;
                if (number <= 98) {
                    xutils_getdecress(goodList.get(position).getRec_id(), number + "");
                }
                data.setGoods_number(number + "");
                ((EditText) view).setText(number + "");
                jishuan();
            }

            @Override
            public void jian(int position, View view) {
                ShopCartGood data = goodList.get(position);
                int number = Integer.valueOf(data.getGoods_number());
                int numbermin = Integer.valueOf(data.getMin_number());
                if (number == numbermin) {
                    number = numbermin;
                    toast("不能再减少啦~");
                } else
                    number--;

                if (number >= 1) {
                    xutils_getdecress(goodList.get(position).getRec_id(), number + "");
                }
                data.setGoods_number(number + "");
                ((EditText) view).setText(number + "");
                jishuan();
            }

            @Override
            public void ed_change(int position, View view, int number) {

            }

            @Override
            public void detel(String id) {
                deleteDialog(id);
            }

            @Override
            public void selectSpecs(int pos) {
                shopCartGood = goodList.get(pos);
                getSpecsList();
            }
        });// 注册

    }

    private void setviewdata() {
        //购物车列表
        shopCartAdapter = new ShopCartAdapter(getActivity(), goodList);
        listv_car.setAdapter(shopCartAdapter);

        // 推荐的商品
        LinearLayoutManager layoutmanager = new LinearLayoutManager(getContext());
        layoutmanager.setOrientation(0);
        //设置RecyclerView 布局
        RecyRecommad.setLayoutManager(layoutmanager);
        shopCartRecommondAdapter = new ShopCartRecommondAdapter(getContext(), recommondList);
        RecyRecommad.setAdapter(shopCartRecommondAdapter);

        //无商品时的猜你喜欢
        shopCartFavorAdapter = new ShopCartFavorAdapter(getContext(), favorList);
        favor_grid.setAdapter(shopCartFavorAdapter);
    }

    private void setviewhw() {
        if (intentFlag == UiHelper.fromGoodDetail) {
            im_return.setVisibility(View.VISIBLE);
//            myHandler.sendEmptyMessage(0x001);
        } else {
            im_return.setVisibility(View.GONE);
        }

        title_text.setText("购物车");
        favor_grid.setSelector(new ColorDrawable(Color.TRANSPARENT));
    }


    /**
     * 计算总价
     */
    public void jishuan() {
        totalPrice = 0.00;
        for (int i = 0; i < goodList.size(); i++) {
            int num = Integer.valueOf(goodList.get(i).getGoods_number());
            int dou = (int) Double.parseDouble(goodList.get(i).getGoods_price());
            int price = Integer.valueOf(dou);
            totalPrice += num * price;
        }
        String pr = totalPrice + "";
        if (pr.substring(pr.indexOf(".")).equals(".0")) {
            pr = pr + "0";
        }
        String price = "合计：" + "<font color = \"#C22222\"><big>" + "¥" + pr + "<big></font>";
        te_price.setText(Html.fromHtml(price));
    }


    /**
     * 删除单件商品
     *
     * @param id 商品id
     */
    private void deleteDialog(final String id) {
        deleteDialog = new CustomDialog.Builder(getActivity())
                .setTitle("您确定要移除该商品吗？~")
                .setPositiveButton(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        xutils_getdetel(id);
                        deleteDialog.dismiss();
                    }
                })
                .create();
        deleteDialog.show();
    }


    /**
     * 判断是否去登录
     */
    private void loginDialog() {
        if (loginDialog == null) {
            loginDialog = new CustomDialog.Builder(getActivity())
                    .setTitle("是否去登录后购买？~")
                    .setPositiveButton("直接购买", R.color.black_000000, new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            commitOrder();
                            loginDialog.dismiss();
                        }
                    })
                    .setNegativeButton("登录", R.color.red, new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            UiHelper.toActivity(getActivity(), new Intent(getActivity(), LoginAty.class));
                            loginDialog.dismiss();
                        }
                    })
                    .create();
        }
        loginDialog.show();
    }

    private void commitOrder() {
        UiHelper.toActivity(getActivity(), new Intent(getActivity(), orderin.class));
//        myHandler.sendEmptyMessage(0x001);
    }


//    public static class MyHandler extends Handler {
//        WeakReference<ShopCart> referenceObj;
//
//        public MyHandler(ShopCart activity) {
//            referenceObj = new WeakReference<ShopCart>(activity);
//        }
//
//        @Override
//        public void handleMessage(Message msg) {
//            ShopCart activity = referenceObj.get();
//            switch (msg.what) {
//                case 0x001:
//                    activity.getShopCartList(0);
//                    break;
//                case 0x002:
//                    break;
//                default:
//                    break;
//            }
//        }
//    }

    @Override
    public void onResume() {
        super.onResume();
        getShopCartList(0);
    }

    //判断界面是否可见
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {//界面可见时
            if (v != null) {
                getShopCartList(0);
            }
        }
    }


    /**
     * 获取购物车列表
     */
    private void getShopCartList(final int type) {
//        landing.show();
//        String url = "https://app.juandie.com/api_mobile/flow.php?act=cart";

        String url = HttpUrl.flow + "act=" + HttpUrl.shop_cart;

        Xutils_Get_Post.getInstance().get(url, new XCallBack() {

            @Override
            public void onResponse(String result) {
                try {
                    JSONObject response = new JSONObject(result);
                    if (response.getString("status").equals("1")) {

                        JSONObject jso = response.getJSONObject("data");

                        goodList = JSON.parseObject(jso.getString("cart_goods_list"), new TypeReference<ArrayList<ShopCartGood>>() {
                        });
                        recommondList = JSON.parseObject(jso.getString("cart_recomend_goods_list"), new TypeReference<ArrayList<ShopCartRecommond>>() {
                        });
                        favorList = JSON.parseObject(jso.getString("cake_cart_null_recommend_goods_list"), new TypeReference<ArrayList<ShopCartFavor>>() {
                        });

                        showContent(StrUtils.listIsEmpty(goodList));
//
//                        if (type == 1) {
//                            if (!StrUtils.listIsEmpty(goodList)) {
//                                String to_pr = jso.getString("total_cart_price");
//                                if (!to_pr.contains(".")) {
//                                    to_pr = to_pr + ".00";
//                                }
//                                te_price.setText("合计:￥" + to_pr);
//                                if (!isLogin()) {
//                                    loginDialog();
//                                } else {
//                                    UiHelper.toActivity(getActivity(), orderin.class);
//                                }
//                            } else {
//                                Toast.makeText(getActivity(), "购物车没有商品，请添加商品", Toast.LENGTH_SHORT).show();
//                            }
//                        }
                    } else {
                        toast(response.getString("msg"));
                    }
//                    landing.dismiss();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail(String result) {
//                landing.dismiss();
            }

            @Override
            public void onCancel(CancelledException cex) {
//                landing.dismiss();
            }
        });

    }

    /**
     * 移除单个商品
     */

    private void xutils_getdetel(final String id) {
        String url = HttpUrl.flow + "act=" + HttpUrl.drop_goods + "&rec_id=" + id;
//        String url = "https://app.juandie.com/api_mobile/flow.php?act=drop_goods&rec_id=" + id;

        Xutils_Get_Post.getInstance().get(url, new XCallBack() {

            @Override
            public void onResponse(String result) {
                try {
                    JSONObject response = new JSONObject(result);
                    if (response.getString("status").equals("1")) {
                        Toast.makeText(getActivity(), "移除成功", Toast.LENGTH_SHORT).show();
                        getShopCartList(0);
//                        myHandler.sendEmptyMessage(0x001);
                    } else {
                        String jsb = response.getString("msg");
                        Toast.makeText(getActivity(), jsb, Toast.LENGTH_SHORT).show();
                    }
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
            public void onCancel(CancelledException cex) {
//                landing.dismiss();
            }
        });

    }

    /**
     * 显示是否有数据
     */
    private void showContent(boolean isEmpty) {
        if (isEmpty) {
            //无数据
            layout_nogoods.setVisibility(View.VISIBLE);
            re_jiesuan.setVisibility(View.GONE);
            shopCartFavorAdapter.refresh(favorList);

        } else {
            //有数据
            layout_nogoods.setVisibility(View.GONE);
            re_jiesuan.setVisibility(View.VISIBLE);
            jishuan();
        }

        shopCartAdapter.refresh(goodList);
        shopCartRecommondAdapter.freshList(recommondList);
        if (StrUtils.listIsEmpty(recommondList)) {
            te_titui.setVisibility(View.GONE);
        } else {
            te_titui.setVisibility(View.VISIBLE);
        }

    }

    /**
     * 商品数量加
     *
     * @param goods_id
     */
    public void httpPost_initgwc(String goods_id) {

        String url = "https://app.juandie.com/api_mobile/flow.php";
        JSONObject ja = new JSONObject();
        try {

            ja.put("goods_id", goods_id);
            ja.put("number", "1");
        } catch (JSONException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }
        HashMap<String, String> maps = new HashMap<String, String>();
        maps.put("act", "add_to_cart");
        maps.put("goods", ja.toString());

        Xutils_Get_Post.getInstance().post(url, maps, new XCallBack() {

            @Override
            public void onResponse(String result) {
                JSONObject object;
                try {
                    object = new JSONObject(result);
                    int status = object.getInt("status");
                    if (status == 1) {
                    }
                } catch (JSONException e) {
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
     * 商品数量减少
     */
    private void xutils_getdecress(final String id, final String number) {
        String url = "https://app.juandie.com/api_mobile/flow.php?act=ajax_update_cart&rec_id="
                + id + "&goods_number=" + number;

        Xutils_Get_Post.getInstance().get(url, new XCallBack() {

            @Override
            public void onResponse(String result) {
                try {
                    JSONObject response = new JSONObject(result);
                    if (response.getString("status").equals("1")) {
                    } else {
                        String jsb = response.getString("msg");
                        Toast.makeText(getActivity(), jsb, Toast.LENGTH_SHORT)
                                .show();
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
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
     * 加入购物车
     *
     * @param goods_id 商品id
     */
    public void addShopCart(String goods_id) {

        String url = HttpUrl.flow;
        JSONObject ja = new JSONObject();
        try {
            ja.put("goods_id", goods_id);
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

        Xutils_Get_Post.getInstance().post(url, maps, new XCallBack() {

            @Override
            public void onResponse(String result) {
                JSONObject object;
                try {
                    object = new JSONObject(result);
                    int status = object.getInt("status");
                    if (status == 1) {
                        getShopCartList(0);
//                        myHandler.sendEmptyMessage(0x001);
                    } else {
                        Toast.makeText(getActivity(), "加入购物车失败",
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

    TextView te_pricegg;

    /**
     * 选择规格弹框
     */
    private void showSpecs() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.pop_good_specs, null);

        final CusPopWindow popSpecs = new CusPopWindow.PopupWindowBuilder(getContext())
                .setView(view)
                .size(DensityUtil.getScreenWidth(), -2)
                .enableBackgroundDark(true)
                .create();

        popSpecs.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

        ListView liView = (ListView) view.findViewById(R.id.gg_listv);
        ImageView imageView = (ImageView) view.findViewById(R.id.gg_imhead);
        TextView te_name = (TextView) view.findViewById(R.id.gg_tetit);
        te_pricegg = (TextView) view.findViewById(R.id.gg_teprice);
        final TextView te_qr = (TextView) view.findViewById(R.id.gg_teqr);

        ImageUtils.setImage(getContext(), shopCartGood.getGoods_thumb(), imageView);
        te_name.setText(shopCartGood.getGoods_name());
        te_pricegg.setText(DecimalUtil.priceAddDecimal(shopCartGood.getGoods_price()));

        for (GoodSpecs specs : specsList) {
            for (GoodSpecs.ItemsBean itemsBean : specs.getItems()) {
                if (TextUtils.equals(itemsBean.getId(), shopCartGood.getGoods_attr_id())) {
                    itemsBean.setCheck(true);
                } else {
                    itemsBean.setCheck(false);
                }
            }
        }

        GoodSpecsAdapter adapter = new GoodSpecsAdapter(specsList, getContext());
        liView.setAdapter(adapter);

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

                String specsId = getIds();

                if (TextUtils.isEmpty(specsId)) {
                    toast("请选择规格");
                } else {
                    changGoodSpecs(specsId);
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

        adapter.setOnSpecsChange(new GoodSpecsAdapter.OnSpecsChangeCallback() {
            @Override
            public void onSpecsChange() {
                loadSpecs();
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

    String specs;

    /**
     * 获取规格列表
     */
    private void getSpecsList() {
        String url;
        if (TextUtils.isEmpty(shopCartGood.getIs_festival())) {
            url = "https://app.juandie.com/api_mobile/goods.php?act=index&goods_id=" + shopCartGood.getGoods_id();
        } else {
            url = "https://app.juandie.com/api_mobile/goods.php?act=index&goods_id=" + shopCartGood.getGoods_id() + "&is_festival=" + shopCartGood.getIs_festival();
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

                        specsList = JSON.parseObject(data.getString("specification_sel"), new TypeReference<ArrayList<GoodSpecs>>() {
                        });
                        showSpecs();
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
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

    /**
     * 选择规格
     */
    private void loadSpecs() {
        specs = getIds();
        String url;
        if (TextUtils.isEmpty(shopCartGood.getIs_festival())) {
            url = "https://app.juandie.com/api_mobile/goods.php?act=index&goods_id=" + shopCartGood.getGoods_id() + "&spec_ids=" + specs;
        } else {
            url = "https://app.juandie.com/api_mobile/goods.php?act=index&goods_id=" + shopCartGood.getGoods_id() + "&spec_ids=" + specs + "&is_festival=" + shopCartGood.getIs_festival();
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
                            if (TextUtils.equals(shopCartGood.getIs_festival(), "0")) {
                                if (te_pricegg != null) {
                                    te_pricegg.setText("￥" + jso_psj.getString("price"));
                                }
                            } else {
                                if (te_pricegg != null) {
                                    te_pricegg.setText("￥" + jso_jrj.getString("price"));
                                }
                            }
                        } else {
                            if (te_pricegg != null) {
                                te_pricegg.setText("￥" + data.getString("shop_price"));
                            }
                        }
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
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

    private void changGoodSpecs(String specsId) {
        String url = HttpUrl.flow;

        HashMap<String, String> maps = new HashMap<>();
        maps.put("act", HttpUrl.update_shop_cart);
        maps.put("rec_id", shopCartGood.getRec_id());
        maps.put("goods_attr_id", specsId);

        Xutils_Get_Post.getInstance().post(url, maps, new Xutils_Get_Post.XCallBack() {

            @Override
            public void onResponse(String result) {
                JSONObject object;
                try {
                    object = new JSONObject(result);
                    int status = object.getInt("status");
                    if (status == 1) {
                        getShopCartList(0);
//                        myHandler.sendEmptyMessage(0x001);
                    } else {
                        toast("修改商品规格失败");
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (window != null) {
            window.dismiss();
        }
    }
}
