package com.example.juandie_hua.ui.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.juandie_hua.R;
import com.example.juandie_hua.model.CategoryList;
import com.example.juandie_hua.helper.UiHelper;
import com.example.juandie_hua.ui.good.GoodListAty;

public class SubCategoryAdapter extends BaseAdapter {
    private Context context;
    private CategoryList category;
    private List<CategoryList.AttrList> list;

    public SubCategoryAdapter(Context context, CategoryList category) {
        this.context = context;
        this.category = category;
        this.list = category.getAll_attr_list();
    }

    public void refresh(CategoryList category) {
        this.category = category;
        this.list = category.getAll_attr_list();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        addview add;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.fenlei2ji_adapter, null);
            add = new addview();
            add.te_name = (TextView) convertView.findViewById(R.id.fl2ji_tename);
            add.gridv_zi = (GridView) convertView.findViewById(R.id.fl2ji_gridv);
            add.lin_top = (LinearLayout) convertView.findViewById(R.id.fl2ji_lin);

            add.gridv_zi.setSelector(new ColorDrawable(Color
                    .parseColor("#ffffff")));

            convertView.setTag(add);
        } else
            add = (addview) convertView.getTag();
        final CategoryList.AttrList data = list.get(position);
        add.te_name.setText(data.getFilter_attr_name());

        list.get(position);
        final List<CategoryList.AttrList.AttrListBean> attrList = list.get(position).getAttr_list();

        SubCategoryItemAdapter ada = new SubCategoryItemAdapter(context, attrList);
        add.gridv_zi.setAdapter(ada);

        add.gridv_zi.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position1, long id) {
                String positionget = "";
                for (int j = 0; j < list.size(); j++) {
                    String pos = "无";
                    if (position == j) {
                        for (int k = 0; k < attrList.size(); k++) {
                            if (k == position1) {
                                pos = position1 + "";
                            }
                        }
                    }
                    positionget += pos + ".";
                }
                String pos = positionget.substring(0, positionget.length() - 1);
                positionget = pos;

                CategoryList.AttrList.AttrListBean attrListBean = attrList.get(position1);

//                UiHelper.toGoodListActivity((Activity) context, category.getCat_id(), attrListBean.getAttr_value(), attrListBean.getFilter_attr(), attrListBean.getOrder(), attrListBean.getBy(), positionget);
                UiHelper.toGoodListActivity((Activity) context, category.getCat_id(), "", attrListBean.getFilter_attr(), attrListBean.getOrder(), attrListBean.getBy(), positionget);
            }
        });

        return convertView;
    }

    public class addview {
        TextView te_name;
        GridView gridv_zi;
        LinearLayout lin_top;
    }
}
