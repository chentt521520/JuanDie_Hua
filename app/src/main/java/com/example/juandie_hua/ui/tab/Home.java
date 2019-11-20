package com.example.juandie_hua.ui.tab;

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

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.juandie_hua.R;
import com.example.juandie_hua.app.App;
import com.example.juandie_hua.app.Constant;
import com.example.juandie_hua.mainactivity.adapter.OnGoodListCallback;
import com.example.juandie_hua.percenter.seting.wx_bdgh;
import com.example.juandie_hua.ui.MainActivity;
import com.example.juandie_hua.app.BaseFragment;
import com.example.juandie_hua.http.HttpUrl;
import com.example.juandie_hua.calender.utils.ImageUtils;
import com.example.juandie_hua.helper.UiHelper;
import com.example.juandie_hua.lunbo.ADInfo;
import com.example.juandie_hua.lunbo.CycleViewPager;
import com.example.juandie_hua.lunbo.ViewFactory;
import com.example.juandie_hua.mainactivity.Landing;
import com.example.juandie_hua.mainactivity.Xutils_Get_Post;
import com.example.juandie_hua.mainactivity.adapter.GridMenuAdapter;
import com.example.juandie_hua.mainactivity.adapter.IndexGoodRecAdapter;
import com.example.juandie_hua.mainactivity.internet_if;
import com.example.juandie_hua.model.IndexRecommond;
import com.example.juandie_hua.model.ResIndex;
import com.example.juandie_hua.mainactivity.no_internet;
import com.example.juandie_hua.mainactivity.other_web1;
import com.example.juandie_hua.mainactivity.sye_daojishi;
import com.example.juandie_hua.ui.good.GoodSearchAty;
import com.example.juandie_hua.utils.SharedPreferenceUtils;
import com.example.juandie_hua.utils.StrUtils;
import com.example.juandie_hua.utils.ViewUtils;
import com.example.juandie_hua.view.CustomDialog;
import com.jauker.widget.BadgeView;
import com.example.juandie_hua.mainactivity.Xutils_Get_Post.XCallBack;
import com.example.juandie_hua.mainactivity.no_internet.te_oncl;
import com.example.juandie_hua.ui.good.GoodListAty;
import com.example.juandie_hua.mainactivity.goods.MyGridView;
import com.example.juandie_hua.ui.me.MyCouponActivity;
import com.meiqia.core.MQManager;
import com.meiqia.core.bean.MQMessage;
import com.meiqia.core.callback.OnGetMessageListCallback;

public class Home extends BaseFragment implements te_oncl {
    View v;
    @ViewInject(R.id.sye_refrag)
    RelativeLayout refragment;

    CycleViewPager cycleViewPager;

    @ViewInject(R.id.index_navigation_grid)
    MyGridView gridNavigation;

    @ViewInject(R.id.sye_xinlinz)
    LinearLayout lin_xinz;
    //新品专区
    @ViewInject(R.id.sye_mygridxin)
    MyGridView mgView_xin;
    //热销鲜花
    @ViewInject(R.id.sye_mygridre)
    MyGridView mgView_re;
    // 精选
    @ViewInject(R.id.sye_mygridjinx)
    MyGridView mgView_jinx;
    // 生活
    @ViewInject(R.id.sye_mygridsheng)
    MyGridView mgView_sheng;
    // 开业
    @ViewInject(R.id.sye_mygridkye)
    MyGridView mgView_kye;
    // 绿植盆栽
    @ViewInject(R.id.sye_mygridlvz)
    MyGridView mgView_lvz;
    // 创意礼品
    @ViewInject(R.id.sye_mygridlip)
    MyGridView mgView_lip;

    @ViewInject(R.id.sye_imlog)
    ImageView ima_log;
    @ViewInject(R.id.sye_edit)
    TextView te_edit;
    @ViewInject(R.id.sye_edit1)
    TextSwitcher te_edit1;
    @ViewInject(R.id.sye_imlogin)
    ImageView im_login;

    @ViewInject(R.id.sye_scro)
    ScrollView scr_v;

    @ViewInject(R.id.sye_gotop)
    ImageView im_top;

    @ViewInject(R.id.gwc_swp)
    SwipeRefreshLayout spr;

    @ViewInject(R.id.sye_linhor)
    LinearLayout lin_hor;

    @ViewInject(R.id.sye_xints)
    TextView te_ai;
    @ViewInject(R.id.sye_daojs)
    sye_daojishi daojishi;

    @ViewInject(R.id.index_new_user_welfare)
    ImageView redPackage;

    @ViewInject(R.id.get_red_package)
    RelativeLayout get_red_package;

    @ViewInject(R.id.get_red_package_close)
    ImageView get_red_package_close;

    @ViewInject(R.id.get_red_package_login)
    TextView get_red_package_login;

    @ViewInject(R.id.show_receive_coupon_view)
    LinearLayout show_receive_coupon_view;

    Landing landing;
    com.example.juandie_hua.mainactivity.no_internet no_internet;

    private int indexte = 0;
    public static String[] kw = {"33只红玫瑰"};

    public static MyHandler myHandler;
    BadgeView bd;

    boolean t_ = true;// 等于true表示可以显示加载圈

    String[] hb_lq = {"wu", "", "", "", ""};// 节日领取红包数组，0是否领取过红包，其他是红包参数
    private List<ResIndex.NavTopBean> navList;
    private List<ResIndex.BannerBean> bannerList;
    private List<ResIndex.MenuListBean> menuList;
    private List<IndexRecommond> newGoodsList;
    private List<IndexRecommond> hotGoodsList;
    private List<IndexRecommond> bestGoodsList;
    private List<IndexRecommond> lifeflowerList;
    private List<IndexRecommond> baseketflowerList;
    private List<IndexRecommond> plantsList;
    private List<IndexRecommond> giftList;
    private IndexGoodRecAdapter ada_xin, ada_re, ada_jinx, ada_sheng, ada_kye, ada_lvz, ada_lip;

    private GridMenuAdapter navAdapter;
    private CustomDialog dialog;
    private boolean isShow;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myHandler = new MyHandler(this);
        if (v == null) {
            v = inflater.inflate(R.layout.sye, container, false);

            x.view().inject(this, v);
//            Fengmian.regid = JPushInterface.getRegistrationID(getActivity());
//            SharedPreferenceUtils.setPreference(getActivity(), Constant.regid, JPushInterface.getRegistrationID(getActivity()), "S");
            setviewdata();
            setviewhw();
            setviewlisten();
            landing = new Landing(getActivity(), R.style.CustomDialog);
            no_internet = new no_internet(getActivity(), R.style.CustomDialog);
            no_internet.setonc(this);
            no_internet.setOnKeyListener(new DialogInterface.OnKeyListener() {

                @Override
                public boolean onKey(DialogInterface dialog, int keyCode,
                                     KeyEvent event) {
                    getActivity().finish();
                    return true;
                }
            });
        }

        if (new internet_if().isNetworkConnected(getActivity())) {
            getIndexData();//oncreate
        } else {
            no_internet.show();
        }

        return v;
    }

    private void setviewhw() {
        gridNavigation.setSelector(new ColorDrawable(Color.TRANSPARENT));

        if (StrUtils.is520Day()) {
            ImageUtils.setGifImage(getActivity(), R.drawable.hb_gif, im_login);
        }

        ImageUtils.setGifImage(getActivity(), R.mipmap.index_red_package, redPackage);

        DisplayMetrics dm = getActivity().getResources().getDisplayMetrics();
        int w = dm.widthPixels;

        ViewUtils.setviewhw_lin(refragment, LayoutParams.MATCH_PARENT,
                (int) (w * 450 / 750.0), 0, 0, 0, 0);

        bd = new BadgeView(getActivity());
        isShow = isLogin();
    }


    private void setviewdata() {

        navAdapter = new GridMenuAdapter(getContext(), menuList);
        gridNavigation.setAdapter(navAdapter);

        ada_xin = new IndexGoodRecAdapter(getActivity(), newGoodsList);
        mgView_xin.setAdapter(ada_xin);

        ada_re = new IndexGoodRecAdapter(getActivity(), hotGoodsList);
        mgView_re.setAdapter(ada_re);

        ada_jinx = new IndexGoodRecAdapter(getActivity(), bestGoodsList);
        mgView_jinx.setAdapter(ada_jinx);

        ada_sheng = new IndexGoodRecAdapter(getActivity(), lifeflowerList);
        mgView_sheng.setAdapter(ada_sheng);

        ada_kye = new IndexGoodRecAdapter(getActivity(), baseketflowerList);
        mgView_kye.setAdapter(ada_kye);

        ada_lvz = new IndexGoodRecAdapter(getActivity(), plantsList);
        mgView_lvz.setAdapter(ada_lvz);

        ada_lip = new IndexGoodRecAdapter(getActivity(), giftList);
        mgView_lip.setAdapter(ada_lip);

        te_edit1.setFactory(new android.widget.ViewSwitcher.ViewFactory() {

            @Override
            public View makeView() {
                TextView tv = new TextView(getActivity());
                tv.setTextSize(14);
                tv.setTextColor(getResources().getColor(R.color.grey_999999));
                return tv;
            }
        });

    }


    private void setviewlisten() {

        te_edit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), GoodSearchAty.class);
                i.putExtra("keywords", kw[indexte]);
                UiHelper.toActivity(getActivity(), i);
            }
        });
        im_login.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                if (!isLogin()) {
                    UiHelper.toLoginActivity(getActivity());
                } else {
                    if (StrUtils.is520Day()) {
                        if (hb_lq[0].equals("you")) {
//							showDialog(hb_lq[1], hb_lq[2], hb_lq[3], hb_lq[4]);
                        } else {
                            Intent i = new Intent(getActivity(), MyCouponActivity.class);
                            i.putExtra("type", "0");
                            UiHelper.toActivity(getActivity(), i);
                        }
                    } else {
                        Intent i = new Intent(getActivity(), other_web1.class);
                        i.putExtra("titl", "生日/纪念日提醒");
                        i.putExtra("url", "https://mnosu.juandie.com/user_holiday.html?is_app=1&uid=" + App.getInstance().getUid());
                        UiHelper.toActivity(getActivity(), i);
                    }
                }
            }
        });
        ima_log.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                UiHelper.toChatActivity(getActivity());
//                Intent intent = new MQIntentBuilder(getActivity())
//                        .setCustomizedId(Fengmian.regid)
//                        .setPreSendImageMessage(new File("预发送图片的路径")).build();
//                UiHelper.toActivity(getActivity(), intent);

                MQManager.getInstance(getActivity().getApplicationContext())
                        .getUnreadMessages(new OnGetMessageListCallback() {

                            @Override
                            public void onFailure(int arg0, String arg1) {
                            }

                            @Override
                            public void onSuccess(List<MQMessage> arg0) {
                                Message msg = Message.obtain();
                                msg.what = 0x001;
                                Bundle bd = new Bundle();
                                bd.putInt("number", 0);
                                msg.setData(bd);
                                if (!(Home.myHandler == null)) {
                                    Home.myHandler.sendMessage(msg);
                                }
                            }
                        });
            }
        });

        im_top.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                scr_v.scrollTo(0, 0);
                im_top.setVisibility(View.GONE);
            }
        });

        scr_v.setOnTouchListener(new OnTouchListener() {
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

                // if (scr_v.getScrollY() >= w * 373 / 750.0) {
                // // 说明没有滑动，在屏幕顶部
                // // redh.setBackgroundResource(R.drawable.jianbian1);
                // re_top.setBackgroundColor(Color.parseColor("#ffffffff"));
                // } else {
                // re_top.setBackgroundColor(Color.parseColor("#00ffffff"));
                // }
            }
        });

        spr.setOnRefreshListener(new OnRefreshListener() {

            @Override
            public void onRefresh() {
                t_ = false;
                handlerx.removeCallbacksAndMessages(null);
                getIndexData();//fresh
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        spr.setRefreshing(false);
                    }
                }, 1000);

            }
        });

        redPackage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                get_hb(hb_lq[1]);
            }
        });

        gridNavigation.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String filter_attr = menuList.get(position).getFilter_attr();
                String cid = menuList.get(position).getCid();
                String type = menuList.get(position).getType();
                if (TextUtils.equals(filter_attr, "0") && TextUtils.equals(cid, "0")) {
                    MainActivity.handler.sendEmptyMessage(0x03);
                } else {
                    if (TextUtils.equals(type, "view")) {
                        UiHelper.toWebActivity(getActivity(), menuList.get(position).getTitle(), menuList.get(position).getUrl());
                    } else {
                        if (!TextUtils.isEmpty(cid)) {
                            Intent i = new Intent();
                            Bundle bundle = new Bundle();
                            bundle.putString("cid", cid);
                            bundle.putString("keywords", "");
                            bundle.putString("filter_attr", filter_attr);
                            bundle.putString("order", menuList.get(position).getOrder());
                            bundle.putString("by", menuList.get(position).getBy());

                            String[] dics = filter_attr.split("\\.");
                            String positionget = "";
                            if (dics.length >= 1) {
                                for (int j = 0; j < dics.length; j++) {
                                    String pos = "无";
                                    if (!dics[j].equals("0")) {
                                        pos = "00" + dics[j];
                                    }
                                    positionget += pos + ".";
                                }
                            }
                            String pos = positionget.substring(0, positionget.length() - 1);
                            positionget = pos;
                            bundle.putString("positionget", positionget);

                            i.putExtras(bundle);
                            i.setClass(getActivity(), GoodListAty.class);
                            UiHelper.toActivity(getActivity(), i);
                        }
                    }
                }
            }
        });

        setGridItemClick(ada_xin);
        setGridItemClick(ada_re);
        setGridItemClick(ada_jinx);
        setGridItemClick(ada_sheng);
        setGridItemClick(ada_kye);
        setGridItemClick(ada_lvz);
        setGridItemClick(ada_lip);

        get_red_package_close.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                isShow = true;
                get_red_package.setVisibility(View.GONE);
            }
        });

        get_red_package_login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                UiHelper.toLoginActivity(getActivity());
            }
        });
    }


    private void setGridItemClick(IndexGoodRecAdapter adapter) {
        adapter.setOnGoodListCallback(new OnGoodListCallback<IndexRecommond>() {
            @Override
            public void setOnAddShopCallback(View view, IndexRecommond data) {
                addShopCart(data.getGoods_id());
            }

            @Override
            public void setOnItemClickCallback(View view, IndexRecommond data) {
                if (TextUtils.equals(data.getType(), "goods")) {
                    UiHelper.toGoodDetailActivity(getActivity(), data.getGoods_id());
                } else {
                    UiHelper.toGoodListActivity(getActivity(), data.getCid(), "", data.getFilter_attr(), data.getOrder(), data.getBy(), "");
                }
            }
        });
    }

    int xx = 0, xx1 = 0;

    private void getIndexData() {
        if (isLogin()) {
            get_red_package.setVisibility(View.GONE);
        } else {
            get_red_package.setVisibility(!isShow ? View.VISIBLE : View.GONE);
        }

        if (t_) {
            landing.show();
        }
        String url = HttpUrl.index + "act=" + HttpUrl.index_page;

        Xutils_Get_Post.getInstance().get(url, new XCallBack() {

            @Override
            public void onResponse(String result) {
                try {
                    JSONObject response = new JSONObject(result);
//                    Fengmian.phpsid = response.getString("PHPSESSID");
                    if (response.getString("status").equals("1")) {
                        JSONObject data = response.getJSONObject("data");

                        bannerList = JSON.parseArray(data.getString("banner_list"), ResIndex.BannerBean.class);
                        menuList = JSON.parseArray(data.getString("menu_list"), ResIndex.MenuListBean.class);
                        initialize();

                        if (xx == 0) {
                            navList = JSON.parseArray(data.getString("nav_top"), ResIndex.NavTopBean.class);
                            drawNavView();
                        }
                        navAdapter.refresh(menuList);

                        if (data.getJSONObject("count_down").getBoolean("is_show")) {
                            lin_xinz.setVisibility(View.GONE);
                            te_ai.setVisibility(View.GONE);
                            daojishi.setVisibility(View.VISIBLE);
                            daojishi.starttimer(data.getJSONObject("count_down").getInt("timestamp"),
                                    data.getJSONObject("count_down").getString("title"));
                        } else {
                            daojishi.setVisibility(View.GONE);
                        }
                        newGoodsList = JSON.parseArray(data.getString("new_goods_list"), IndexRecommond.class);
                        hotGoodsList = JSON.parseArray(data.getString("hot_goods_list"), IndexRecommond.class);
                        bestGoodsList = JSON.parseArray(data.getString("best_goods_list"), IndexRecommond.class);
                        lifeflowerList = JSON.parseArray(data.getString("lifeflower_goods_list"), IndexRecommond.class);
                        baseketflowerList = JSON.parseArray(data.getString("baseket_goods_list"), IndexRecommond.class);
                        plantsList = JSON.parseArray(data.getString("plants_goods_list"), IndexRecommond.class);
                        giftList = JSON.parseArray(data.getString("gift_goods_list"), IndexRecommond.class);

                        ada_xin.refresh(newGoodsList);
                        ada_re.refresh(hotGoodsList);
                        ada_jinx.refresh(bestGoodsList);
                        ada_sheng.refresh(lifeflowerList);
                        ada_kye.refresh(baseketflowerList);
                        ada_lvz.refresh(plantsList);
                        ada_lip.refresh(giftList);


                        JSONObject jso_bouns = data.getJSONObject("bonus");
                        if (jso_bouns.getString("is_show_bonus").equals("true")) {
                            show_receive_coupon_view.setVisibility(View.VISIBLE);
                            String title = jso_bouns.getString("title");
                            String subtitle = jso_bouns.getString("subtitle");
                            String amount = jso_bouns.getString("amount");
                            String field = jso_bouns.getString("field");

                            SharedPreferenceUtils.setPreference(getActivity(), Constant.hbcs, field + "," + amount + "," + title + "," + subtitle, "S");
                            if (xx == 0) {
//								showDialog(field, amount, title, subtitle);

                                hb_lq[0] = "you";
                                hb_lq[1] = field;
                                hb_lq[2] = amount;
                                hb_lq[3] = title;
                                hb_lq[4] = subtitle;
                            }
                            xx = 1;
                        } else {
                            show_receive_coupon_view.setVisibility(View.GONE);
                            xx = 1;
                            hb_lq[0] = "wu";
                        }

                        JSONArray jso_keywords = data
                                .getJSONArray("search_keys");
                        kw = new String[jso_keywords.length()];
                        for (int i = 0; i < jso_keywords.length(); i++) {
                            kw[i] = jso_keywords.getString(i);
                        }
                        guanggao();

                        JSONObject jso_config = data.getJSONObject("config");
                        String pho = jso_config.getString("service_tel");
                        if (pho.length() >= 5) {
                            SharedPreferenceUtils.setPreference(getActivity(), Constant.kfpho, pho, "S");
                        }

                    } else {
                        String jsb = response.getString("msg");
                        Toast.makeText(getActivity(), jsb, Toast.LENGTH_SHORT)
                                .show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (t_) {
                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            t_ = true;
                            landing.dismiss();
                        }
                    }, 2000);
                }
            }

            @Override
            public void onFail(String result) {
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        landing.dismiss();
                    }
                }, 2000);
            }

            @Override
            public void onCancel(CancelledException cex) {
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        landing.dismiss();
                    }
                }, 2000);
            }
        });
    }

    /**
     * 绘制顶部导航栏
     */
    private void drawNavView() {
        lin_hor.removeAllViews();
        View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.item_index_nav, null);
        lin_hor.addView(view1);

        for (ResIndex.NavTopBean nav : navList) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_index_nav, null);
            TextView textView = view.findViewById(R.id.sye_hortet);
            View v = view.findViewById(R.id.item_index_tab_indicator);
            textView.setText(nav.getText());
            textView.setTextSize(14f);
            textView.setTextColor(Color.parseColor("#787878"));
            textView.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            v.setVisibility(View.INVISIBLE);
            lin_hor.addView(view);

            if (TextUtils.equals(nav.getType(), "category")) {
                setti_listen(view, false, nav.getCid(), "", nav.getFilter_attr());
            }
        }
    }

    /**
     * 设置精选的点击事件
     */
    public void setti_listen(View v, final boolean isgoods, final String cid, final String goodsid, final String filter_attr) {
        v.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (isgoods) {
                    UiHelper.toGoodDetailActivity(getActivity(), goodsid);
                } else {
                    UiHelper.toGoodListActivity(getActivity(), cid, "", filter_attr, "sort_order", "desc", "");
                }
            }
        });
    }

    /**
     * 轮播图片
     */
    private void initialize() {
        cycleViewPager = (CycleViewPager) getActivity().getFragmentManager().findFragmentById(R.id.sye_viewpagerlunbos);

        List<ImageView> views = new ArrayList<>();
        List<ADInfo> infos = new ArrayList<>();

        for (int i = 0; i < bannerList.size(); i++) {
            ADInfo info = new ADInfo();
            info.setUrl(bannerList.get(i).getImg());
            info.setContent("图片-->" + i);
            infos.add(info);
        }

        // 将最后一个ImageView添加进来
        views.add(ViewFactory.getImageView(v.getContext(), infos.get(infos.size() - 1).getUrl()));
        for (int i = 0; i < infos.size(); i++) {
            views.add(ViewFactory.getImageView(getActivity(), infos.get(i).getUrl()));
        }
        // 将第一个ImageView添加进来
        views.add(ViewFactory.getImageView(getActivity(), infos.get(0).getUrl()));
        // 设置循环，在调用setData方法前调用
        cycleViewPager.setCycle(true);

        // 在加载数据前设置是否循环
        cycleViewPager.setData(views, infos, mAdCycleViewListener);
        // 设置轮播
        cycleViewPager.setWheel(true);

        // 设置轮播时间，默认4500ms
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

                ResIndex.BannerBean bannerBean = bannerList.get(position - 1);

                if (TextUtils.equals(bannerBean.getType(), "view")) {
                    UiHelper.toWebActivity(getActivity(), bannerBean.getTitle(), bannerBean.getUrl());
                } else if (TextUtils.equals(bannerBean.getType(), "category")) {
                    String[] ax = bannerBean.getTitle().split(",");
                    Intent i = new Intent();
                    Bundle bundle = new Bundle();
                    if (ax[0].equals("x")) {
                        bundle.putString("cid", "");
                    } else
                        bundle.putString("cid", ax[0]);
                    bundle.putString("keywords", "");
                    if (ax[1].equals("x")) {
                        bundle.putString("filter_attr", "");
                    } else
                        bundle.putString("filter_attr", ax[1]);
                    bundle.putString("order", "sort_order");
                    bundle.putString("by", "desc");

                    String[] dics = ax[1].split("\\.");
                    String positionget = "";
                    for (int j = 0; j < dics.length; j++) {
                        String pos1 = "无";
                        if (!dics[j].equals("0")) {
                            pos1 = "00" + dics[j];
                        }
                        positionget += pos1 + ".";
                    }
                    String pos1 = positionget.substring(0,
                            positionget.length() - 1);
                    positionget = pos1;
                    bundle.putString("positionget", positionget);

                    i.putExtras(bundle);
                    i.setClass(getActivity(), GoodListAty.class);
                    getActivity().startActivity(i);
                    getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                } else {
                    UiHelper.toGoodDetailActivity(getActivity(), bannerList.get(position).getTitle());
                }
            }
        }
    };


    private void get_hb(String field) {
        landing.show();
        String url = "https://app.juandie.com/api_mobile/index.php?act=receive_bonus&field=" + field;
        Xutils_Get_Post.getInstance().get(url, new XCallBack() {

            @Override
            public void onResponse(String result) {
                try {
                    JSONObject response = new JSONObject(result);
                    Me.handler.sendEmptyMessage(0x003);
                    if (response.getString("status").equals("1")) {
                        Toast.makeText(getActivity(), response.getString("msg"), Toast.LENGTH_SHORT).show();
                        Intent i = new Intent();
                        i.setClass(getActivity(), MyCouponActivity.class);
                        i.putExtra("type", "0");
                        UiHelper.toActivity(getActivity(), i);
                    } else {
                        String jsb = response.getString("msg");

                        if (jsb.contains("登录")) {
                            Toast.makeText(getActivity(), jsb, Toast.LENGTH_SHORT).show();
                            // 1表示首页领取红包登录后自动领取
                            UiHelper.toLoginActivity(getActivity());

                        } else if (jsb.contains("领取")) {
                            Intent i = new Intent();
                            i.setClass(getActivity(), MyCouponActivity.class);
                            i.putExtra("type", "0");
                            UiHelper.toActivity(getActivity(), i);

                        } else {
                            showDialogtis(jsb);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        landing.dismiss();
                    }
                }, 1000);
            }

            @Override
            public void onFail(String result) {
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        landing.dismiss();
                    }
                }, 1000);
            }

            @Override
            public void onCancel(CancelledException cex) {
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        landing.dismiss();
                    }
                }, 1000);
            }
        });
    }

    public void showDialogtis(final String text) {
        dialog = new CustomDialog.Builder(getActivity())
                .setTitle(text)
                .setLineColor(R.color.red)
                .setPositiveButton("立即前往", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        if (text.contains("绑定")) {
                            UiHelper.toActivity(getActivity(), wx_bdgh.class);
                        }
                    }
                })
                .setNegativeBtnShow(false)
                .create();
        dialog.show();
    }

    @Override
    public void set1(View v) {

        Intent intent = null;
        if (android.os.Build.VERSION.SDK_INT > 10) {
            intent = new Intent(android.provider.Settings.ACTION_SETTINGS);
        } else {
            intent = new Intent();
            ComponentName component = new ComponentName("com.android.settings",
                    "com.android.settings.WirelessSettings");
            intent.setComponent(component);
            intent.setAction("android.intent.action.VIEW");
        }
        startActivity(intent);
        toast("跳转到手机设置");
    }

    @Override
    public void re_inter1(View v) {
        if (new internet_if().isNetworkConnected(getActivity())) {
            no_internet.dismiss();
            getIndexData();//reconnect
        } else
            toast("网络连接失败,请设置网络");
    }

    Handler handlerx = new Handler() {
        @Override
        @SuppressLint("HandlerLeak")
        public void handleMessage(Message msg) {
            // 点击会切换图片
            indexte++;
            if (indexte >= kw.length) {
                indexte = 0;
            }
            te_edit1.setText(kw[indexte]);
            handlerx.sendEmptyMessageDelayed(1, 4000);
        }

        ;
    };

    public void guanggao() {
        handlerx.sendEmptyMessageDelayed(1, 2000);
        te_edit1.setText(kw[indexte]);
        te_edit1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent i = new Intent();
                i.putExtra("keywords", kw[indexte]);
                i.setClass(getActivity(), GoodSearchAty.class);
                startActivity(i);
                getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
        // 设置切入动画
        te_edit1.setInAnimation(AnimationUtils.loadAnimation(getActivity(),
                R.anim.slide_in_bottom));
        // 设置切出动画
        te_edit1.setOutAnimation(AnimationUtils.loadAnimation(getActivity(),
                R.anim.slide_out_up));
    }

    public static class MyHandler extends Handler {
        WeakReference<Home> referenceObj;

        public MyHandler(Home activity) {
            referenceObj = new WeakReference<Home>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            Home activity = referenceObj.get();
            switch (msg.what) {
                case 0x001:
                    Bundle bdle = msg.getData();
                    activity.bd.setTextSize(8);
                    if (bdle.getInt("number") == 0) {
                        activity.bd.setVisibility(View.GONE);
                    } else {
                        activity.bd.setVisibility(View.VISIBLE);
                    }
                    activity.bd.setBadgeCount(bdle.getInt("number"));
                    activity.bd.setTargetView(activity.ima_log);
                    break;

                case 0x002:
                    activity.scr_v.scrollTo(0, 0);
                    activity.im_top.setVisibility(View.GONE);
                    break;
                case 0x003://刷新界面
                    activity.getIndexData();
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 加入购物车
     *
     * @param goodId 商品id
     */
    private void addShopCart(String goodId) {

        String url = HttpUrl.flow;
        JSONObject ja = new JSONObject();
        try {
            ja.put("goods_id", goodId);
            ja.put("number", 1);
            ja.put("festival", 0);
            JSONArray jsa = new JSONArray();
            ja.put("spec", jsa);
        } catch (JSONException e2) {
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
                        toast("加入购物车成功");
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
            public void onCancel(CancelledException cex) {
            }
        });
    }
}
