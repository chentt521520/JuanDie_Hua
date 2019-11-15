package com.example.juandie_hua.mycar;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.juandie_hua.R;

public class spinner_adapter extends BaseAdapter {
	Context context;
	List<spinner_adaData> list;

	public spinner_adapter(Context context, List<spinner_adaData> list) {
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
					R.layout.spinner_adapter, null);
			add = new addview();
			add.te = (TextView) convertView.findViewById(R.id.spada_tebh);
			convertView.setTag(add);
		} else
			add = (addview) convertView.getTag();
		spinner_adaData data = list.get(position);
		add.te.setText(data.getBianhao());
		return convertView;
	}

	public class addview {
		TextView te;
	}
}
