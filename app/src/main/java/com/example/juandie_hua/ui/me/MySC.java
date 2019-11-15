package com.example.juandie_hua.ui.me;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.x;
import org.xutils.common.Callback.CancelledException;
import org.xutils.view.annotation.ViewInject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.juandie_hua.R;
import com.example.juandie_hua.app.BaseActivity;
import com.example.juandie_hua.app.Constant;
import com.example.juandie_hua.helper.UiHelper;
import com.example.juandie_hua.mainactivity.Xutils_Get_Post;
import com.example.juandie_hua.mainactivity.Xutils_Get_Post.XCallBack;
import com.example.juandie_hua.mainactivity.internet_if;
import com.example.juandie_hua.mainactivity.internet_landing;
import com.example.juandie_hua.mainactivity.internet_landing.re_jk;
import com.example.juandie_hua.model.ScanHistory;
import com.example.juandie_hua.percenter.TimerTextView;
import com.example.juandie_hua.ui.me.adapter.CollectionAdapter;
import com.example.juandie_hua.ui.me.adapter.CollectionAdapter.number_change;
import com.example.juandie_hua.utils.SharedPreferenceUtils;
import com.example.juandie_hua.utils.StatusBarUtils;
import com.example.juandie_hua.utils.StrUtils;
import com.example.juandie_hua.utils.ViewUtils;
import com.example.juandie_hua.view.CustomDialog;
import com.example.juandie_hua.view.CustomTitleView;
import com.umeng.analytics.MobclickAgent;

public class MySC extends BaseActivity implements re_jk {

    @ViewInject(R.id.mysc_listv)
    ListView listv_sc;

    @ViewInject(R.id.mysc_nogoods)
    LinearLayout layout_nogoods;
    @ViewInject(R.id.nogoods_imhead)
    ImageView im_no;
    @ViewInject(R.id.nogoods_tecon)
    TextView te_no;
    @ViewInject(R.id.nogoods_tego)
    TextView te_no1;

    List<ScanHistory> list;
    private CollectionAdapter ada;

    PopupWindow window;

    String type = "";

    internet_landing inLanding;
    private CustomDialog dialog;
    private String footPrintStr;
    private CustomTitleView titleView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitleContentView(R.layout.mysc);
        StatusBarUtils.with(this).setBarColor(R.color.white_fff);

        // if (Build.VERSION.SDK_INT >= 21) {
        // Window window = getWindow();
        // // 取消设置透明状态栏,使 ContentView 内容不再沉浸到状态栏下
        // window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // // 需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        // window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        // // 设置状态栏颜色
        // window.setStatusBarColor(Color.parseColor("#f2f3f5"));
        // }

        inLanding = new internet_landing(this);
        inLanding.setonc(this);
        x.view().inject(this);

        Intent i = getIntent();
        type = i.getStringExtra("type");// type=1代表我的收藏，2代表浏览记录
        setviewhw();
        setviewdata();
        setviewlisten();
    }

    private void setviewhw() {
        titleView = this.getTitleView();

        if (type.equals("2")) {
            titleView.setTitleText("我的浏览");
            te_no.setText("您还没有浏览历史");
        } else {
            titleView.setTitleText("我的收藏");
            te_no.setText("您还没有收藏商品");
        }
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int w = dm.widthPixels;

        ViewUtils.setviewhw_lin(layout_nogoods, w, (int) (w * 180 / 375.0), 0,
                (int) (w * 100 / 375.0), 0, 0);

        te_no1.setVisibility(View.GONE);
        im_no.setImageResource(R.drawable.nodata_02);
    }

    private void setviewlisten() {
        titleView.setLeftOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                TimerTextView.isc = false;
                UiHelper.finish(MySC.this);
            }
        });

        ada.setnumber_change(new number_change() {
            @Override
            public void detel(int position, String goods_id) {
                String msg;
                if (type.equals("1")) {
                    msg = "您确定从收藏夹中移除该商品吗？";
                } else
                    msg = "您确定从历史记录中移除该商品吗？";
                deleteDialog(position, goods_id, msg);
            }

            @Override
            public void item(int position, String goods_id) {
                UiHelper.toGoodDetailActivity(MySC.this, goods_id);
            }
        });
    }

    private void setviewdata() {
        list = new ArrayList<>();
        ada = new CollectionAdapter(MySC.this, list);
        listv_sc.setAdapter(ada);

        if (type.equals("1")) {
            if (inLanding.if_inter()) {
                getList("");
            }
        } else {
            //足迹中只保存近20条数据
            String footPrint = (String) SharedPreferenceUtils.getPreference(this, Constant.zhuji, "S");
            if (!TextUtils.isEmpty(footPrint) && footPrint.contains(",")) {
                String[] prints = footPrint.split(",");
                if (prints.length < 20) {
                    footPrintStr = footPrint;
                } else {
                    for (int i = 0; i < 20; i++) {
                        footPrintStr += prints[i] + ",";
                    }
                }
                if (inLanding.if_inter()) {
                    getList(footPrintStr.substring(0, footPrintStr.length() - 1));
                }
            } else {
                //没有足迹
                layout_nogoods.setVisibility(View.VISIBLE);
            }
        }
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

    public void xianshiyanshi() {
        if (StrUtils.listIsEmpty(list)) {
            layout_nogoods.setVisibility(View.VISIBLE);
        } else {
            if (layout_nogoods.isShown()) {
                layout_nogoods.setVisibility(View.GONE);
            }
        }
    }

    private void deleteDialog(final int position, final String goods_id, String msg) {
        dialog = new CustomDialog.Builder(MySC.this)
                .setTitle(msg)
                .setPositiveButton(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (type.equals("1")) {
                            xutils_detel(list.get(position).getGoods_id(), position);
                        } else {
                            list.remove(position);
                            ada.notifyDataSetChanged();

                            deleteRecord(goods_id);
                        }
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(null)
                .create();
        dialog.show();
    }

    private void deleteRecord(String goods_id) {
        String ids = (String) SharedPreferenceUtils.getPreference(MySC.this, Constant.zhuji, "S");

        String result;
        if (!TextUtils.isEmpty(ids) && ids.contains("," + goods_id)) {
            result = ids.replace("," + goods_id, "");
        } else {
            result = ids;
        }

        SharedPreferenceUtils.setPreference(MySC.this, Constant.zhuji, result, "S");
    }

    /**
     * 获取收藏列表，浏览历史
     *
     * @param ids 浏览历史商品id
     */
    private void getList(final String ids) {
        inLanding.showlanding();
        String url;
        if (type.equals("2")) {
            url = "https://app.juandie.com/api_mobile/user.php?act=footmark_list&ids=" + ids;
        } else {
            url = "https://app.juandie.com/api_mobile/user.php?act=collection_list";
        }
        Xutils_Get_Post.getInstance().get(url, new XCallBack() {

            @Override
            public void onResponse(String result) {
                Log.e("~~~~~~~~~", "足迹:" + result);
                inLanding.dismisslanding();

                JSONObject jso1;
                try {
                    jso1 = new JSONObject(result);
                    if (jso1.getString("status").equals("1")) {
                        JSONObject data = jso1.getJSONObject("data");
//                        newGoodsList = JSON.parseArray(data.getString("new_goods_list"), IndexRecommond.class);
                        list = JSON.parseArray(data.getString("goods_list"), ScanHistory.class);
                        ada.refresh(list);
//                        ada.notifyDataSetChanged();
//                        JSONArray data_ = (JSONArray) data.get("goods_list");
//                        for (int i = 0; i < data_.length(); i++) {
//                            JSONObject jso_ = data_.getJSONObject(i);
//                            list.add(new mysc_adaData(jso_
//                                    .getString("goods_id"), jso_
//                                    .getString("goods_thumb"), jso_
//                                    .getString("goods_name"), jso_
//                                    .getString("shop_price"), jso_
//                                    .getString("goods_sale_number"), jso_
//                                    .getString("goods_id"), type));
//                        }
//                        ada.notifyDataSetChanged();
                        xianshiyanshi();

                    } else {
                        toast(jso1.getString("msg"));
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

    /**
     * 删除收藏单条
     *
     * @param collection_id
     */
    private void xutils_detel(final String collection_id, final int position) {
        String url = "https://app.juandie.com/api_mobile/user.php?act=delete_collection&collection_id="
                + collection_id;

        Xutils_Get_Post.getInstance().get(url, new XCallBack() {

            @Override
            public void onResponse(String result) {
                JSONObject jso1;
                try {
                    jso1 = new JSONObject(result);
                    if (jso1.getString("status").equals("1")) {
                        Toast.makeText(MySC.this, "删除收藏成功", Toast.LENGTH_SHORT)
                                .show();
                        list.remove(position);
                        ada.notifyDataSetChanged();
                        xianshiyanshi();
                        window.dismiss();
                    } else {
                        Toast.makeText(MySC.this,
                                jso1.getString("msg").toString(),
                                Toast.LENGTH_SHORT).show();
                    }
                    ada.notifyDataSetChanged();
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail(String result) {
                xutils_detel(collection_id, position);
            }

            @Override
            public void onCancel(CancelledException cex) {
                // TODO Auto-generated method stub

            }
        });

    }


    @Override
    public void re_requestjk(View v) {
        if (new internet_if().isNetworkConnected(this)) {
            inLanding.dismissinter();
            if (type.equals("1")) {
                getList("");
            } else {
                getList(footPrintStr.substring(0, footPrintStr.length() - 1));
            }
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

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        if (window != null) {
            window.dismiss();
        }
    }
}
