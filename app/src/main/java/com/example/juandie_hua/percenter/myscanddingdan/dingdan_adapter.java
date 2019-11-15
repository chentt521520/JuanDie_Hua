package com.example.juandie_hua.percenter.myscanddingdan;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.juandie_hua.R;
import com.example.juandie_hua.mainactivity.goods.MyListView;
import com.example.juandie_hua.percenter.myorder.myorder_adaDatatwo;
import com.example.juandie_hua.percenter.myorder.myorder_adaptertwo;

public class dingdan_adapter extends BaseAdapter {
	Context context;
	List<dingdan_adaData> list;

	public dingdan_adapter(Context context, List<dingdan_adaData> list) {
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
					R.layout.dingdan_adapter, null);
			add = new addview();
			findvid(add, convertView);
			setviewhw(add);
			convertView.setTag(add);
		} else
			add = (addview) convertView.getTag();
		final dingdan_adaData data = list.get(position);
		add.te_ddh1.setText(data.getOrder_bh());
		add.te_ddzt1.setText(data.getOrder_status());
		add.te_ddprice1.setText("￥"+data.getOrder_price());
		List<myorder_adaDatatwo> data_two = data.getData();
		myorder_adaptertwo ada = new myorder_adaptertwo(context, data_two);
		add.listView.setAdapter(ada);

		add.te_ddxq.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent();
				i.putExtra("id", data.getOrder_bh());
				i.setClass(context, orderdtals_web.class);
				context.startActivity(i);
				((Activity) context).overridePendingTransition(
						R.anim.push_left_in, R.anim.push_left_out);
			}
		});
		return convertView;
	}

	public class addview {
		TextView te_ddh, te_ddh1, te_ddzt, te_ddzt1, te_ddprice, te_ddprice1,
				te_ddxq;
		RelativeLayout re_top, re_zhong, re_bot;
		MyListView listView;

	}

	private void findvid(addview add, View view) {
		add.re_top = (RelativeLayout) view.findViewById(R.id.ddcx_rename);
		add.te_ddh = (TextView) view.findViewById(R.id.ddcx_tename);
		add.te_ddh1 = (TextView) view.findViewById(R.id.ddcx_tename1);
		add.re_zhong = (RelativeLayout) view.findViewById(R.id.ddcx_rezh);
		add.te_ddzt = (TextView) view.findViewById(R.id.ddcx_tezt);
		add.te_ddzt1 = (TextView) view.findViewById(R.id.ddcx_tezt1);
		add.te_ddprice = (TextView) view.findViewById(R.id.ddcx_teprice);
		add.te_ddprice1 = (TextView) view.findViewById(R.id.ddcx_teprice1);
		add.te_ddxq = (TextView) view.findViewById(R.id.ddcx_texq);
		add.listView = (MyListView) view.findViewById(R.id.ddcx_mylistv);
	}

	private void setviewhw(addview add) {
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		int w = dm.widthPixels;
		setviewhw_lin(add.re_top, (int) (w * 351 / 375.0),
				(int) (w * 45 / 375.0), (int) (w * 12 / 375.0), 0,
				(int) (w * 12 / 375.0), 0);
		setviewhw_lin(add.re_zhong, (int) (w * 351 / 375.0),
				(int) (w * 66 / 375.0), (int) (w * 12 / 375.0), 0,
				(int) (w * 12 / 375.0), 0);
		setviewhw_re(add.te_ddzt,
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				(int) (w * 33 / 375.0), 0, 0, 0, 0);
		setviewhw_re(add.te_ddprice,
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				(int) (w * 33 / 375.0), 0, (int) (w * 33 / 375.0), 0, 0);
		setviewhw_re(add.te_ddxq, (int) (w * 100 / 375.0),
				(int) (w * 35 / 375.0), (int) (w * 251 / 375.0),
				(int) (w * 16 / 375.0), 0, (int) (w * 15 / 375.0));
	}

	private void setviewhw_lin(View v, int width, int height, int left,
			int top, int right, int bottom) {
		LayoutParams lp = new LayoutParams(width, height);
		lp.setMargins(left, top, right, bottom);
		v.setLayoutParams(lp);
	}

	private void setviewhw_re(View v, int width, int height, int left, int top,
			int right, int bottom) {
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(width,
				height);
		lp.setMargins(left, top, right, bottom);
		v.setLayoutParams(lp);
	}

}
