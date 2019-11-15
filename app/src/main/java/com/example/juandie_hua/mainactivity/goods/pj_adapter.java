package com.example.juandie_hua.mainactivity.goods;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

//import com.bumptech.glide.Glide;
import com.example.juandie_hua.R;
import com.example.juandie_hua.calender.utils.ImageUtils;

public class pj_adapter extends BaseAdapter {
	Context context;
	List<pj_adaData> list;

	public pj_adapter(Context context, List<pj_adaData> list) {
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
					R.layout.pj_adapter, null);
			add = new addview();
			find_v(convertView, add);

			DisplayMetrics dm = context.getResources().getDisplayMetrics();
			int w = dm.widthPixels;
			setviewhw_lin(add.im_head, (int) (w * 30 / 375.0),
					(int) (w * 30 / 375.0), (int) (w * 14 / 375.0),
					(int) (w * 10 / 375.0), (int) (w * 4 / 375.0), 0);
			setviewhw_lin(add.lin_xin, (int) (w * 95 / 375.0),
					(int) (w * 20 / 375.0), (int) (w * 6 / 375.0),
					(int) (w * 15 / 375.0), 0, 0);
			setviewhw_lin(add.im_xin1, (int) (w * 15 / 375.0),
					(int) (w * 20 / 375.0), 0, 0, (int) (w * 5 / 375.0), 0);
			setviewhw_lin(add.im_xin2, (int) (w * 15 / 375.0),
					(int) (w * 20 / 375.0), 0, 0, (int) (w * 5 / 375.0), 0);
			setviewhw_lin(add.im_xin3, (int) (w * 15 / 375.0),
					(int) (w * 20 / 375.0), 0, 0, (int) (w * 5 / 375.0), 0);
			setviewhw_lin(add.im_xin4, (int) (w * 15 / 375.0),
					(int) (w * 20 / 375.0), 0, 0, (int) (w * 5 / 375.0), 0);
			setviewhw_lin(add.im_xin5, (int) (w * 15 / 375.0),
					(int) (w * 20 / 375.0), 0, 0, 0, 0);

			add.te_con.setPadding((int) (w * 14 / 375.0),
					(int) (w * 10 / 375.0), (int) (w * 14 / 375.0),
					(int) (w * 10 / 375.0));
			setviewhw_lin(add.lin2, (int) (w * 200 / 375.0),
					(int) (w * 60 / 375.0), (int) (w * 14 / 375.0), 0,
					(int) (w * 14 / 375.0), (int) (w * 10 / 375.0));

			setviewhw_lin(add.im_tp1, (int) (w * 60 / 375.0),
					(int) (w * 60 / 375.0), 0, 0, (int) (w * 10 / 375.0), 0);
			setviewhw_lin(add.im_tp2, (int) (w * 60 / 375.0),
					(int) (w * 60 / 375.0), 0, 0, (int) (w * 10 / 375.0), 0);
			setviewhw_lin(add.im_tp3, (int) (w * 60 / 375.0),
					(int) (w * 60 / 375.0), 0, 0, 0, 0);

			add.te_name.setPadding(0, 0, (int) (w * 14 / 375.0), 0);

			setviewhw_lin(add.lin1, w, (int) (w * 40 / 375.0), 0, 0, 0, 0);

			convertView.setTag(add);
		} else
			add = (addview) convertView.getTag();


		final pj_adaData data = list.get(position);

		add.te_name.setText(data.getName());
//		Glide.with(context).load(data.getUrl_head()).into(add.im_head);
		ImageUtils.setImage(context,data.getUrl_head(),add.im_head);
		add.te_con.setText(data.getStr_pj());
		int number = Integer.valueOf(data.getNumber_xin()).intValue();
		selector_xin(add, number);

		if (!data.getUrl_tp()[0].equals("无")) {
			add.lin2.setVisibility(View.VISIBLE);
			if (data.getUrl_tp().length == 1) {
				ImageUtils.setImage(context,data.getUrl_tp()[0],add.im_tp1);
//				Glide.with(context).load(data.getUrl_tp()[0]).into(add.im_tp1);
				add.im_tp2.setVisibility(View.GONE);
				add.im_tp3.setVisibility(View.GONE);
			} else if (data.getUrl_tp().length == 2) {
				ImageUtils.setImage(context,data.getUrl_tp()[0],add.im_tp1);
				ImageUtils.setImage(context,data.getUrl_tp()[1],add.im_tp2);
//				Glide.with(context).load(data.getUrl_tp()[0]).into(add.im_tp1);
//				Glide.with(context).load(data.getUrl_tp()[1]).into(add.im_tp2);
				add.im_tp2.setVisibility(View.VISIBLE);
				add.im_tp3.setVisibility(View.GONE);
			} else if (data.getUrl_tp().length == 3) {
				ImageUtils.setImage(context,data.getUrl_tp()[0],add.im_tp1);
				ImageUtils.setImage(context,data.getUrl_tp()[1],add.im_tp2);
				ImageUtils.setImage(context,data.getUrl_tp()[2],add.im_tp3);
//				Glide.with(context).load(data.getUrl_tp()[0]).into(add.im_tp1);
//				Glide.with(context).load(data.getUrl_tp()[1]).into(add.im_tp2);
//				Glide.with(context).load(data.getUrl_tp()[2]).into(add.im_tp3);
				add.im_tp2.setVisibility(View.VISIBLE);
				add.im_tp3.setVisibility(View.VISIBLE);
			}
		} else {
			add.lin2.setVisibility(View.GONE);
		}

		add.im_tp1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				String urls = "";
				for (int ix = 0; ix < data.getUrl_tp().length; ix++) {
					urls += data.getUrl_tp()[ix] + ",";
				}
				urls = urls.substring(0, urls.length() - 1);
				Intent i = new Intent();
				i.setClass(context, check_pic.class);
				i.putExtra("pos", 0 + "");
				i.putExtra("urls", urls);

				context.startActivity(i);
				((Activity) context).overridePendingTransition(
						R.anim.push_left_in, R.anim.push_left_out);
			}
		});
		add.im_tp2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				String urls = "";
				for (int ix = 0; ix < data.getUrl_tp().length; ix++) {
					urls += data.getUrl_tp()[ix] + ",";
				}
				urls = urls.substring(0, urls.length() - 1);
				Intent i = new Intent();
				i.setClass(context, check_pic.class);
				i.putExtra("pos", 1 + "");
				i.putExtra("urls", urls);

				context.startActivity(i);
				((Activity) context).overridePendingTransition(
						R.anim.push_left_in, R.anim.push_left_out);
			}
		});
		add.im_tp3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				String urls = "";
				for (int ix = 0; ix < data.getUrl_tp().length; ix++) {
					urls += data.getUrl_tp()[ix] + ",";
				}
				urls = urls.substring(0, urls.length() - 1);
				Intent i = new Intent();
				i.setClass(context, check_pic.class);
				i.putExtra("pos", 2 + "");
				i.putExtra("urls", urls);

				context.startActivity(i);
				((Activity) context).overridePendingTransition(
						R.anim.push_left_in, R.anim.push_left_out);
			}
		});
		return convertView;
	}

	public class addview {
		ImageView im_head;
		ImageView im_xin1, im_xin2, im_xin3, im_xin4, im_xin5;
		TextView te_name, te_con;
		ImageView im_tp1, im_tp2, im_tp3;
		LinearLayout lin1, lin2, lin_xin;

	}

	private void find_v(View convertView, addview add) {
		add.im_head = (ImageView) convertView.findViewById(R.id.pjada_imhead);
		add.im_xin1 = (ImageView) convertView.findViewById(R.id.pjada_xin1);
		add.im_xin2 = (ImageView) convertView.findViewById(R.id.pjada_xin2);
		add.im_xin3 = (ImageView) convertView.findViewById(R.id.pjada_xin3);
		add.im_xin4 = (ImageView) convertView.findViewById(R.id.pjada_xin4);
		add.im_xin5 = (ImageView) convertView.findViewById(R.id.pjada_xin5);
		add.te_name = (TextView) convertView.findViewById(R.id.pjada_tename);
		add.te_con = (TextView) convertView.findViewById(R.id.pjada_tepj);

		add.im_tp1 = (ImageView) convertView.findViewById(R.id.pjada_imtp1);
		add.im_tp2 = (ImageView) convertView.findViewById(R.id.pjada_imtp2);
		add.im_tp3 = (ImageView) convertView.findViewById(R.id.pjada_imtp3);

		add.lin_xin = (LinearLayout) convertView
				.findViewById(R.id.pjada_linwuxin);
		add.lin2 = (LinearLayout) convertView.findViewById(R.id.pjada_lintp);

		add.lin1 = (LinearLayout) convertView.findViewById(R.id.pjada_lin);
	}

	private void setviewhw_lin(View v, int width, int height, int left,
			int top, int right, int bottom) {
		LayoutParams lp = new LayoutParams(width, height);
		lp.setMargins(left, top, right, bottom);
		v.setLayoutParams(lp);
	}

	private void selector_xin(addview add, int id) {
		switch (id) {
		case 1:
			add.im_xin1.setSelected(true);
			add.im_xin2.setSelected(false);
			add.im_xin3.setSelected(false);
			add.im_xin4.setSelected(false);
			add.im_xin5.setSelected(false);

			add.im_xin2.setVisibility(View.GONE);
			add.im_xin3.setVisibility(View.GONE);
			add.im_xin4.setVisibility(View.GONE);
			add.im_xin5.setVisibility(View.GONE);

			break;
		case 2:
			add.im_xin1.setSelected(true);
			add.im_xin2.setSelected(true);
			add.im_xin3.setSelected(false);
			add.im_xin4.setSelected(false);
			add.im_xin5.setSelected(false);
			add.im_xin2.setVisibility(View.VISIBLE);
			add.im_xin3.setVisibility(View.GONE);
			add.im_xin4.setVisibility(View.GONE);
			add.im_xin5.setVisibility(View.GONE);
			break;
		case 3:
			add.im_xin1.setSelected(true);
			add.im_xin2.setSelected(true);
			add.im_xin3.setSelected(true);
			add.im_xin4.setSelected(false);
			add.im_xin5.setSelected(false);
			add.im_xin2.setVisibility(View.VISIBLE);
			add.im_xin3.setVisibility(View.VISIBLE);
			add.im_xin4.setVisibility(View.GONE);
			add.im_xin5.setVisibility(View.GONE);
			break;
		case 4:
			add.im_xin1.setSelected(true);
			add.im_xin2.setSelected(true);
			add.im_xin3.setSelected(true);
			add.im_xin4.setSelected(true);
			add.im_xin5.setSelected(false);
			add.im_xin2.setVisibility(View.VISIBLE);
			add.im_xin3.setVisibility(View.VISIBLE);
			add.im_xin4.setVisibility(View.VISIBLE);
			add.im_xin5.setVisibility(View.GONE);
			break;
		case 5:
			add.im_xin1.setSelected(true);
			add.im_xin2.setSelected(true);
			add.im_xin3.setSelected(true);
			add.im_xin4.setSelected(true);
			add.im_xin5.setSelected(true);
			add.im_xin2.setVisibility(View.VISIBLE);
			add.im_xin3.setVisibility(View.VISIBLE);
			add.im_xin4.setVisibility(View.VISIBLE);
			add.im_xin5.setVisibility(View.VISIBLE);
			break;
		default:
			break;
		}
	}

}
