package com.example.juandie_hua.mainactivity.fenlei.ss;

import java.util.List;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.example.juandie_hua.R;

public class fenleizi_adapter extends BaseAdapter {
	Context context;
	List<fenleizi_adaData> list;

	public fenleizi_adapter(Context context, List<fenleizi_adaData> list) {
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
					R.layout.fenleizi_adapter_cop, null);
			add = new addview();
			add.te_name = (TextView) convertView
					.findViewById(R.id.copflzi_tename);

			DisplayMetrics dm = context.getResources().getDisplayMetrics();
			int w = dm.widthPixels;
			setviewhw_lin(add.te_name, (int) (w * 98 / 375.0),
					(int) (w * 40 / 375.0), (int) (w * 17 / 375.0),
					(int) (w * 12 / 375.0), 0, 0);
			convertView.setTag(add);
		} else
			add = (addview) convertView.getTag();
		fenleizi_adaData data = list.get(position);
		add.te_name.setSelected(data.isSelset());
		add.te_name.setText(data.getName());
		return convertView;
	}

	public class addview {
		TextView te_name;
	}

	private void setviewhw_lin(View v, int width, int height, int left,
			int top, int right, int bottom) {
		LayoutParams lp = new LayoutParams(width, height);
		lp.setMargins(left, top, right, bottom);
		v.setLayoutParams(lp);
	}
}
