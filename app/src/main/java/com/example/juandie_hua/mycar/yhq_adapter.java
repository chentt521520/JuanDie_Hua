package com.example.juandie_hua.mycar;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.juandie_hua.R;

public class yhq_adapter extends BaseAdapter {
	Context context;
	List<yhq_adaData> list;

	public yhq_adapter(Context context, List<yhq_adaData> list) {
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
					R.layout.yhq_adapter, null);
			add = new addview();
			add.te_price = (TextView) convertView
					.findViewById(R.id.odyhq_teje1);
			add.te_sn = (TextView) convertView.findViewById(R.id.odyhq_tebh);
			convertView.setTag(add);
		} else
			add = (addview) convertView.getTag();
		yhq_adaData data = list.get(position);
		add.te_price.setText("￥" + data.getPrice());
		add.te_sn.setText(data.getYhqhm());

		return convertView;
	}

	public class addview {
		TextView te_price, te_sn;
	}
}
