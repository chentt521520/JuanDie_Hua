package com.example.juandie_hua.ui.tab;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.x;
import org.xutils.common.Callback.CancelledException;
import org.xutils.view.annotation.ViewInject;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.jpush.android.api.JPushInterface;

import com.alibaba.fastjson.JSON;
import com.example.juandie_hua.R;
import com.example.juandie_hua.app.BaseFragment;
import com.example.juandie_hua.app.Constant;
import com.example.juandie_hua.model.CategoryList;
import com.example.juandie_hua.app.HttpUrl;
import com.example.juandie_hua.helper.UiHelper;
import com.example.juandie_hua.mainactivity.Xutils_Get_Post;
import com.example.juandie_hua.mainactivity.Xutils_Get_Post.XCallBack;
import com.example.juandie_hua.ui.adapter.SubCategoryAdapter;
import com.example.juandie_hua.ui.adapter.MainCategoryAdapter;
import com.example.juandie_hua.ui.good.GoodSearchAty;
import com.example.juandie_hua.utils.SharedPreferenceUtils;
import com.example.juandie_hua.utils.StrUtils;
import com.example.juandie_hua.utils.ViewUtils;

public class Category extends BaseFragment {
    View v;
    @ViewInject(R.id.fenlei_retop)
    RelativeLayout re_top;
    @ViewInject(R.id.fenlei_edit)
    TextView ed_ss;
    @ViewInject(R.id.fenlei_imss)
    ImageView im_ss;

    @ViewInject(R.id.fenlei_listv)
    ListView listv_v;

    @ViewInject(R.id.fenlei_listvit)
    ListView listv_v2;


    private List<CategoryList> mainCategory;
    private MainCategoryAdapter mainAdapter;
    private CategoryList subCategory;
    private SubCategoryAdapter subAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (v == null) {
            v = inflater.inflate(R.layout.flei, container, false);

            x.view().inject(this, v);
            SharedPreferenceUtils.setPreference(getActivity(), Constant.regid, JPushInterface.getRegistrationID(getActivity()), "S");
            setviewhw();
            setviewdata();
            setviewlisten();

            xutils_getfenlei();

        }
        return v;
    }

    private void setviewlisten() {
        listv_v.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (CategoryList category : mainCategory) {
                    category.setCheck(false);
                }
                mainCategory.get(position).setCheck(true);

                mainAdapter.refresh(mainCategory);
                subCategory = mainCategory.get(position);
                if (StrUtils.listIsEmpty(subCategory.getAll_attr_list())) {
                    UiHelper.toGoodListActivity(getActivity(), mainCategory.get(position).getCat_id(),
                            mainCategory.get(position).getCat_name(), "", "sort_order", "desc", "");

                } else {
                    subAdapter.refresh(subCategory);
                }
            }
        });
        ed_ss.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.putExtra("keywords", "");
                i.setClass(getActivity(), GoodSearchAty.class);
                startActivity(i);
                getActivity().overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
            }
        });
    }

    private void setviewdata() {

        mainAdapter = new MainCategoryAdapter(getActivity(), mainCategory);
        listv_v.setAdapter(mainAdapter);

        subCategory = new CategoryList();
        subAdapter = new SubCategoryAdapter(getActivity(), subCategory);
        listv_v2.setAdapter(subAdapter);
    }

    private void setviewhw() {
        listv_v.setSelector(new ColorDrawable(Color.TRANSPARENT));
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int w = dm.widthPixels;
        int h = dm.heightPixels;
        ViewUtils.setviewhw_lin(listv_v, (int) (w * 120 / 375.0), h, 0, 0, 0, 0);

        ViewUtils.setviewhw_lin(re_top, w, (int) (w * 55 / 450.0), 0, 0, 0, 0);

        ViewUtils.setviewhw_re(ed_ss, w - (int) (w * 40 / 450.0), (int) (w * 37 / 450.0),
                (int) (w * 20 / 450.0), (int) (w * 9 / 450.0),
                (int) (w * 20 / 450.0), (int) (w * 9 / 450.0));
        ViewUtils.setviewhw_re(im_ss, (int) (w * 20 / 450.0), (int) (w * 25 / 450.0),
                (int) (w * 35 / 450.0), (int) (w * 15 / 450.0), 0, 0);
        ed_ss.setPadding((int) (w * 52 / 450.0), 0, 0, 0);

    }

    private void xutils_getfenlei() {
        String url = HttpUrl.category + "act=" + HttpUrl.category_page;

        Xutils_Get_Post.getInstance().get(url, new XCallBack() {

            @Override
            public void onResponse(String result) {
                try {
                    JSONObject response = new JSONObject(result);
                    if (response.getString("status").equals("1")) {
                        JSONObject data = response.getJSONObject("data");

                        mainCategory = JSON.parseArray(data.getString("catgory_list"), CategoryList.class);

                        if (StrUtils.listIsEmpty(mainCategory)) {
                            //无数据
                        } else {
                            mainCategory.get(0).setCheck(true);
                        }
                        subCategory = mainCategory.get(0);
                        mainAdapter.refresh(mainCategory);
                        subAdapter.refresh(subCategory);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail(String result) {
                xutils_getfenlei();
            }

            @Override
            public void onCancel(CancelledException cex) {
            }
        });
    }

}
