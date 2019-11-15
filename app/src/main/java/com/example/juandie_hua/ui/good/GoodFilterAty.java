package com.example.juandie_hua.ui.good;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.juandie_hua.R;
import com.example.juandie_hua.app.BaseActivity;
import com.example.juandie_hua.mainactivity.Xutils_Get_Post;
import com.example.juandie_hua.mainactivity.fenlei.ss.fenlei2ji_adaData;
import com.example.juandie_hua.mainactivity.fenlei.ss.fenlei2ji_adapter;
import com.example.juandie_hua.mainactivity.fenlei.ss.fenleizi_adaData;
import com.example.juandie_hua.model.GoodType;
import com.example.juandie_hua.utils.StatusBarUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class GoodFilterAty extends BaseActivity {

    private Activity activity;
    @ViewInject(R.id.shaixuan_listvit)
    ListView listv_v;

    @ViewInject(R.id.shaixuan_tecz)
    TextView te_cz;
    @ViewInject(R.id.shaixuan_teok)
    TextView te_ok;

    fenlei2ji_adapter ada;
    List<fenlei2ji_adaData> list;
    String positionget = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitleContentView(R.layout.shaixuan);

        StatusBarUtils.with(this).setBarColor(R.color.white_fff);

        x.view().inject(this);
        activity = this;
        this.getTitleView().setTitleText("筛选");

        setviewdata();
        setviewlisten();
    }

    private void setviewlisten() {
        te_cz.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                for (int i = 0; i < list.size(); i++) {
                    List<fenleizi_adaData> data = list.get(i).getData();
                    for (int j = 0; j < data.size(); j++) {
                        data.get(j).setSelset(false);
                    }
                }
                ada.notifyDataSetChanged();
            }
        });

        te_ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                List<String> list_get = new ArrayList<String>();
                String filter_attr = "";
                positionget = "";
                for (int i = 0; i < list.size(); i++) {
                    String attr = "0", position = "无";
                    List<fenleizi_adaData> data = list.get(i).getData();
                    for (int j = 0; j < data.size(); j++) {
                        if (data.get(j).isSelset()) {
                            list_get.add(data.get(j).getId());
                            attr = data.get(j).getId();
                            position = j + "";
                        }

                    }
                    filter_attr += attr + ".";
                    positionget += position + ".";
                }
                String pos = positionget;
                if (pos.length() >= 2) {
                    pos = positionget.substring(0, positionget.length() - 1);
                }

                positionget = pos;
                if (filter_attr.length() >= 2) {
                    filter_attr = filter_attr.substring(0,
                            filter_attr.length() - 1);
                }
                Message msg = Message.obtain();
                msg.what = 0x001;
                Bundle bundle = new Bundle();
                bundle.putString("filter_attr", filter_attr);
                bundle.putString("positionget", positionget);

                msg.setData(bundle);
                GoodListAty.handler.sendMessage(msg);
                finish();
                overridePendingTransition(R.anim.push_right_out,
                        R.anim.push_right_in);
            }
        });

    }

    private void setviewdata() {
        list = new ArrayList<>();
        ada = new fenlei2ji_adapter(activity, list);
        listv_v.setAdapter(ada);
        Intent i = getIntent();
        String cid = i.getStringExtra("cid");
        positionget = i.getStringExtra("positionget");
        if (TextUtils.isEmpty(positionget)) {
            positionget = "";
        }
        volley_getfenlei(cid);
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


    private void volley_getfenlei(final String cid) {
        String url = "https://app.juandie.com/api_mobile/category.php?act=get_filter_attr_list&cid="
                + cid;
        Xutils_Get_Post.getInstance().get(url, new Xutils_Get_Post.XCallBack() {

            @Override
            public void onResponse(String result) {
                try {
                    JSONObject response = new JSONObject(result);
                    if (response.getString("status").equals("1")) {
                        JSONObject jso = response.getJSONObject("data");

                        List<GoodType> list = JSON.parseArray(jso.getString("all_attr_list"), GoodType.class);

//                        JSONArray jsa = jso.getJSONArray();
//                        for (int i = 0; i < jsa.length(); i++) {
//                            List<fenleizi_adaData> data = new ArrayList<fenleizi_adaData>();
//
//                            JSONObject data1 = jsa.getJSONObject(i);
//                            JSONArray data1zi = data1.getJSONArray("attr_list");
//
//                            for (int j = 0; j < data1zi.length(); j++) {
//                                JSONObject datazij = data1zi.getJSONObject(j);
//                                data.add(new fenleizi_adaData(datazij
//                                        .getString("filter_attr"), datazij
//                                        .getString("attr_value"), false));
//                            }
//                            list.add(new fenlei2ji_adaData("", data1
//                                    .getString("filter_attr_name"), data));
//                        }
//                        if (!TextUtils.isEmpty(positionget)) {
//
//                            if (positionget.contains(".")) {
//                                int x = -1;
//
//                                String[] daat = positionget.split("\\.");
//                                for (int j = 0; j < daat.length; j++) {
//                                    x++;
//                                    if (daat[j].contains("0")) {
//                                        daat[j].replace("\\.", "");
//                                    }
//                                    if (!daat[j].equals("无")) {
//                                        if (daat[j].contains("00")) {
//                                            for (int k = 0; k < list.get(j).getData().size(); k++) {
//                                                if (daat[j]
//                                                        .equals("00"
//                                                                + list.get(j)
//                                                                .getData()
//                                                                .get(k)
//                                                                .getId())) {
//                                                    daat[j] = k + "";
//                                                }
//                                            }
//                                        }
//                                        int d = Integer.valueOf(daat[j]);
//                                        if (!(d > list.get(x).getData().size())) {
//                                            list.get(x).getData().get(d)
//                                                    .setSelset(true);
//                                        }
//
//                                    }
//
//                                }
//                            } else {
//                                if (!positionget.equals("无")) {
//                                    int d = Integer.valueOf(positionget);
//                                    list.get(0).getData().get(d)
//                                            .setSelset(true);
//                                }
//                            }
//
//                        }
//                        ada.notifyDataSetChanged();

                    } else {
                        String jsb = response.getString("msg");
                        Toast.makeText(activity, jsb, Toast.LENGTH_SHORT)
                                .show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail(String result) {
                volley_getfenlei(cid);
            }

            @Override
            public void onCancel(Callback.CancelledException cex) {
            }
        });
    }
}
