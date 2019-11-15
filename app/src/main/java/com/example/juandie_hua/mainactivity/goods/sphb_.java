package com.example.juandie_hua.mainactivity.goods;

import java.util.List;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.example.juandie_hua.R;

public class sphb_ extends BaseAdapter {
	List<sphb_data> list;
	Context context;

	public sphb_(List<sphb_data> list, Context context) {
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
			add = new addview();
			convertView = LayoutInflater.from(context).inflate(R.layout.sphb_,
					null);
			add.lin_zhu = (LinearLayout) convertView
					.findViewById(R.id.sphbada_lin);
			add.lin_head = (LinearLayout) convertView
					.findViewById(R.id.sphbada_lin1);
			add.te_price = (TextView) convertView
					.findViewById(R.id.sphbada_te2);
			add.te_context = (TextView) convertView
					.findViewById(R.id.sphbada_te3);
			add.te_name = (TextView) convertView
					.findViewById(R.id.sphbada_tename);

			DisplayMetrics dm = context.getResources().getDisplayMetrics();
			int w = dm.widthPixels;
			setviewhw_lin(add.lin_zhu, (int) (w * 335 / 375.0),
					(int) (w * 80 / 375.0), (int) (w * 20 / 375.0),
					(int) (w * 14 / 375.0), 0, 0);
			setviewhw_lin(add.lin_head, (int) (w * 120 / 375.0),
					(int) (w * 80 / 375.0), 0, 0, 0, 0);
			setviewhw_lin(add.te_price, LayoutParams.MATCH_PARENT,
					(int) (w * 33 / 375.0), 0, (int) (w * 5 / 375.0), 0, 0);
			setviewhw_lin(add.te_context, LayoutParams.MATCH_PARENT,
					(int) (w * 33 / 375.0), 0, 0, 0, (int) (w * 5 / 375.0));
			setviewhw_lin(add.te_name, LayoutParams.MATCH_PARENT,
					(int) (w * 80 / 375.0), 0, 0, 0, 0);

			convertView.setTag(add);
		} else
			add = (addview) convertView.getTag();

		sphb_data data = list.get(position);
		add.te_price.setText("￥"+data.getPrice());
		Spannable span = new SpannableString(add.te_price.getText());
		span.setSpan(new AbsoluteSizeSpan(40), 0, 1,
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		add.te_price.setText(span);
		add.te_context.setText(data.getContext());
		add.te_name.setText(data.getName());
		return convertView;
	}

	public class addview {
		LinearLayout lin_zhu, lin_head;
		TextView te_price, te_context, te_name;
	}

	private void setviewhw_lin(View v, int width, int height, int left,
			int top, int right, int bottom) {
		LayoutParams lp = new LayoutParams(width, height);
		lp.setMargins(left, top, right, bottom);
		v.setLayoutParams(lp);
	}
}
