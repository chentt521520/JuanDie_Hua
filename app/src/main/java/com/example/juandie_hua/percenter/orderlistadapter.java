package com.example.juandie_hua.percenter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.juandie_hua.R;
import com.example.juandie_hua.percenter.myscanddingdan.orderdtals_web;

/**
 * @Author ldlin
 * @Time 2019-1-9 上午11:12:44
 * @Description
 * 
 */

public class orderlistadapter extends BaseAdapter {
	List<orderlistdata> list;
	Context context;
	detelorderstr detelorderstr;

	public orderlistadapter(List<orderlistdata> list, Context context) {
		this.list = list;
		this.context = context;
	}

	public void setorderstrdetel(detelorderstr detelorderstr) {
		this.detelorderstr = detelorderstr;
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
	public View getView(final int position, View view, ViewGroup parent) {
		addview add;
		if (view == null) {
			view = LayoutInflater.from(context).inflate(
					R.layout.orderlistadapter, parent, false);
			add = new addview();
			view.setTag(add);
			add.lin_ = (LinearLayout) view.findViewById(R.id.lin_orderpaysucit);
			add.te_time = (TextView) view
					.findViewById(R.id.tetime_orderpaysucit);
			add.te_numner = (TextView) view
					.findViewById(R.id.tenum_orderpaysucit);
			add.im_detel = (ImageView) view
					.findViewById(R.id.image_orderpaysucit);

		} else
			add = (addview) view.getTag();

		final orderlistdata data = list.get(position);
		add.te_numner.setText(data.getOrdernumber());
		add.te_time.setText(data.getTime());

		add.im_detel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (detelorderstr != null) {
					detelorderstr.detelorderstr(position);
				}
			}
		});

		add.lin_.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent();
				i.putExtra("id", data.getOrdernumber());
				i.setClass(context, orderdtals_web.class);
				context.startActivity(i);
				((Activity) context).overridePendingTransition(
						R.anim.push_left_in, R.anim.push_left_out);
			}
		});

		return view;
	}

	/**
	 * @Author ldlin
	 * @Time 2019-1-9 上午11:14:04
	 * @Description view
	 * 
	 */

	public class addview {
		LinearLayout lin_;
		TextView te_time, te_numner;
		ImageView im_detel;

	}

	public interface detelorderstr {
		// 删除对应位置的订单字符串
		void detelorderstr(int pos);
	}

}
