package com.example.juandie_hua.mainactivity.goods;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.juandie_hua.R;

public class sp_ggada1 extends BaseAdapter {
	List<sp_ggadadadata1> list;
	Context context;
	shuaxin shuaxin;

	public sp_ggada1(List<sp_ggadadadata1> list, Context context) {
		this.list = list;
		this.context = context;
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

	public void setinterfa(shuaxin shua) {
		this.shuaxin = shua;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final addview add;
		if (convertView == null) {
			add = new addview();
			convertView = LayoutInflater.from(context).inflate(R.layout.spggada1, null);
			add.te = (TextView) convertView.findViewById(R.id.spggxz);
			convertView.setTag(add);

		} else
			add = (addview) convertView.getTag();

		final sp_ggadadadata1 data = list.get(position);
		add.te.setText(data.getText());
		add.te.setSelected(data.isT_sele());
		add.te.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!add.te.isSelected()) {
					for (int i = 0; i < list.size(); i++) {
						if (i == position) {
							list.get(position).setT_sele(true);
						} else {
							list.get(i).setT_sele(false);
						}
					}
					notifyDataSetChanged();
					shuaxin.shuaxin();
				}
			}
		});
		return convertView;
	}

	public class addview {

		TextView te;
	}

	public interface shuaxin {
		void shuaxin();
	}
}
