package com.example.juandie_hua.mobanAndgongju;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.juandie_hua.R;

public class xx_adapter extends BaseAdapter {
	Context context;
	List<xx_adaData> list;

	public xx_adapter(Context context, List<xx_adaData> list) {
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list == null ? 0 : list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		addview add;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.fengmian, null);
			add = new addview();
			convertView.setTag(add);
		} else
			add = (addview) convertView.getTag();
		return convertView;
	}

	public class addview {

	}
}
