package com.example.juandie_hua.ui.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.juandie_hua.R;
import com.example.juandie_hua.model.CategoryList;

public class SubCategoryItemAdapter extends BaseAdapter {
	private Context context;
	private List<CategoryList.AttrList.AttrListBean> list;

	public SubCategoryItemAdapter(Context context, List<CategoryList.AttrList.AttrListBean> list) {
		this.context = context;
		this.list = list;
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
			convertView = LayoutInflater.from(context).inflate(R.layout.fenleizi_adapter, null);
			add = new addview();
			add.te_name = (TextView) convertView.findViewById(R.id.flzi_tename);
			convertView.setTag(add);
		} else
			add = (addview) convertView.getTag();
		CategoryList.AttrList.AttrListBean data = list.get(position);
		add.te_name.setText(data.getAttr_value());

//        add.te_name.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String positionget = "";
//                for (int j = 0; j < list.size(); j++) {
//                    String pos = "无";
////                    if (position == j) {
//                        for (int k = 0; k < list.size(); k++) {
//                            if (k == position) {
//                                pos = position + "";
//                            }
////                        }
//                    }
//                    positionget += pos + ".";
//                }
//                String pos = positionget.substring(0, positionget.length() - 1);
//                positionget = pos;
//
//                CategoryList.AttrList.AttrListBean attrListBean = list.get(position);
//
//                UiHelper.toGoodListActivity((Activity) context, attrListBean.getGoods_id(), "", "", "", attrListBean.getBy(), positionget);
//
//            }
//        });
		return convertView;
	}

	public class addview {
		TextView te_name;
	}
}
