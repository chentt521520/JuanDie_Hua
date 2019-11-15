package com.example.juandie_hua.mycar.orderpay;

import java.util.List;

import android.content.Context;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

//import com.bumptech.glide.Glide;
import com.example.juandie_hua.R;
import com.example.juandie_hua.calender.utils.ImageUtils;

public class order_spada extends BaseAdapter {
	Context context;
	List<order_spadadata> list;

	public order_spada(Context context, List<order_spadadata> list) {
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
					R.layout.order_spada, null);
			add = new addview();
			add.im_head = (ImageView) convertView.findViewById(R.id.odspimhead);
			add.te_name = (TextView) convertView.findViewById(R.id.odsptename);
			add.te_number = (TextView) convertView
					.findViewById(R.id.odsptenumber);
			add.te_jr = (TextView) convertView.findViewById(R.id.odsppricejr);
			add.te_price = (TextView) convertView.findViewById(R.id.odspprice);
			add.lin_ = (LinearLayout) convertView.findViewById(R.id.odsplin);
			add.v_ = convertView.findViewById(R.id.odspv);
			
			add.te_gg=(TextView) convertView.findViewById(R.id.odsptegg);

			DisplayMetrics dm = context.getResources().getDisplayMetrics();
			int w = dm.widthPixels;
			setviewhw_lin(add.lin_, w - (int) (w * 48 / 450.0),
					(int) (w * 110 / 450.0), (int) (w * 24 / 450.0), 0,
					(int) (w * 24 / 450.0), 0);
			setviewhw_lin(add.im_head, (int) (w * 90 / 450.0),
					LayoutParams.MATCH_PARENT, 0, 0, (int) (w * 24 / 450.0), 0);
			convertView.setTag(add);
		} else
			add = (addview) convertView.getTag();
		order_spadadata data = list.get(position);
		ImageUtils.setImage(context.getApplicationContext(),data.getUrl(),add.im_head);
//		Glide.with(context.getApplicationContext()).load(data.getUrl()).into(add.im_head);
		add.te_name.setText(data.getName());
		add.te_number.setText("x" + data.getNumber());
		add.te_price.setText("￥" + data.getPrice());
		
		add.te_gg.setText(data.getGuige());
		if (TextUtils.isEmpty(data.getGuige())) {
			add.te_gg.setVisibility(View.GONE);
		}else
			add.te_gg.setVisibility(View.VISIBLE);
		
		
		if (data.getJieri().equals("1")) {
			add.te_jr.setVisibility(View.VISIBLE);
		} else
			add.te_jr.setVisibility(View.GONE);
		if (position == list.size() - 1) {
			add.v_.setVisibility(View.GONE);
		}
		return convertView;
	}

	public class addview {
		LinearLayout lin_;
		ImageView im_head;
		TextView te_name, te_number, te_price, te_jr,te_gg;
		View v_;
	}

	private void setviewhw_lin(View v, int width, int height, int left,
			int top, int right, int bottom) {
		LayoutParams lp = new LayoutParams(width, height);
		lp.setMargins(left, top, right, bottom);
		v.setLayoutParams(lp);
	}
}
