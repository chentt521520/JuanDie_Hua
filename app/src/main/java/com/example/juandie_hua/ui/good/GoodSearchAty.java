package com.example.juandie_hua.ui.good;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.juandie_hua.R;
import com.example.juandie_hua.app.BaseActivity;
import com.example.juandie_hua.http.HttpUrl;
import com.example.juandie_hua.helper.UiHelper;
import com.example.juandie_hua.mainactivity.Xutils_Get_Post;
import com.example.juandie_hua.mainactivity.fenlei.ss.FlowGroupView_fl;
import com.example.juandie_hua.model.HotWords;
import com.example.juandie_hua.percenter.TimerTextView;
import com.example.juandie_hua.utils.StatusBarUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

public class GoodSearchAty extends BaseActivity {

    private Activity activity;

    @ViewInject(R.id.flss_imreturn)
    ImageView im_return;
    @ViewInject(R.id.flss_edit)
    EditText ed_ss;
    @ViewInject(R.id.flss_tess)
    TextView te_ss;

    @ViewInject(R.id.flss_flow1)
    FlowGroupView_fl flow;

    private List<HotWords> hotWordsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_good_search);
        StatusBarUtils.with(this).setBarColor(R.color.white_fff);

        activity = this;
        x.view().inject(this);

        setviewdata();
        setviewlisten();

        get_ci();

    }

    private void setviewlisten() {
        im_return.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                TimerTextView.isc = false;
                finish();
                overridePendingTransition(R.anim.push_right_out, R.anim.push_right_in);
            }
        });
        te_ss.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                toSearch();
            }
        });

        ed_ss.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    toSearch();
                    return true;
                }
                return false;
            }
        });
    }

    private void toSearch() {
        if (!TextUtils.isEmpty(ed_ss.getText().toString())) {

            String keywords = "";
            try {
                keywords = URLEncoder.encode(ed_ss.getText().toString(), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            UiHelper.toGoodListActivity(activity, "", keywords, "", "sort_order", "desc", "");
        } else {
            toast("请输入关键词");
        }
    }

    private void setviewdata() {
        Intent i = getIntent();
        ed_ss.setText(i.getStringExtra("keywords"));
    }

    private void addTextView() {
        for (int i = 0; i < hotWordsList.size(); i++) {
            DisplayMetrics dm = getResources().getDisplayMetrics();
            int width = dm.widthPixels;
            TextView child = new TextView(this);
            ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(
                    ViewGroup.MarginLayoutParams.WRAP_CONTENT, (int) (width * 60 / 750.0));
            params.setMargins((int) (width * 18 / 750.0),
                    (int) (width * 25 / 750.0), 0, 0);
            child.setBackgroundResource(R.drawable.bg_corner_grey_30);
            child.setPadding((int) (width * 30 / 750.0), 0,
                    (int) (width * 30 / 750.0), 0);

            child.setTextSize(14);
            child.setGravity(Gravity.CENTER);

            child.setTextColor(Color.parseColor("#666666"));

            child.setLayoutParams(params);

            child.setText(hotWordsList.get(i).getText());
            initEvents(child, i);
            flow.addView(child);
            // 务必要加这句
            flow.requestLayout();
        }


    }

    private void initEvents(final TextView tv, final int ix) {
        tv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                String keywords = "";
//                try {
//                    keywords = URLEncoder.encode(tv.getText().toString(), "UTF-8");
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }

                HotWords data = hotWordsList.get(ix);
                UiHelper.toGoodListActivity(activity, data.getCid(), data.getText(), data.getFilter_attr(), data.getOrder(), data.getBy(), "");
            }
        });
    }

    private void get_ci() {

        HashMap<String, String> map = new HashMap<>();
        map.put("act", HttpUrl.search_hot_words);
        String url = HttpUrl.getUrl(HttpUrl.category, map);
//        String url = "https://app.juandie.com/api_mobile/category.php?=search_hot_words";
        Xutils_Get_Post.getInstance().get(url, new Xutils_Get_Post.XCallBack() {

            @Override
            public void onResponse(String result) {
                try {
                    JSONObject response = new JSONObject(result);
                    if (response.getString("status").equals("1")) {
                        JSONObject jso = response.getJSONObject("data");

                        hotWordsList = JSON.parseArray(jso.getString("words_list"), HotWords.class);
                        addTextView();
//                        JSONArray jsa = jso.getJSONArray("words_list");
//                        for (int i = 0; i < jsa.length(); i++) {
//                            JSONObject jso_list = jsa.getJSONObject(i);
//                            list_filid.add(new fenlei_sslistdata(jso_list
//                                    .getString("by"), jso_list
//                                    .getString("text"), jso_list
//                                    .getString("order"), jso_list
//                                    .getString("cid"), jso_list
//                                    .getString("filter_attr")));
//                            addTextView(jso_list.getString("text"), i);
//                        }
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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            finish();
            overridePendingTransition(R.anim.push_right_out,
                    R.anim.push_right_in);
            return false;
        }
        return false;
    }
}
