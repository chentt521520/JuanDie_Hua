package com.example.juandie_hua.mycar.shouhuodizi;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;

import com.example.juandie_hua.R;
import com.example.juandie_hua.mycar.orderpay.addshdz;

public class shouhuodz_adapter extends BaseAdapter {
	Context context;
	List<shouhuodz_adaData> list;

	public shouhuodz_adapter(Context context, List<shouhuodz_adaData> list) {
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		addview add;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.shouhuodz_adapter, null);
			add = new addview();
			findv(add, convertView);
			setviewhw(add, convertView);

			convertView.setTag(add);
		} else
			add = (addview) convertView.getTag();
		final shouhuodz_adaData data = list.get(position);
		add.te_xiugai.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(context, addshdz.class);
				shouhuodz_adaData data = list.get(position);
				String[] dat = { data.getName_shr(), data.getPho_srh(),
						data.getDizi(), data.getDztis(), data.getName_dhr(),
						data.getPho_dhr() };
				Bundle b = new Bundle();
				b.putStringArray("data", dat);
				i.putExtras(b);
				context.startActivity(i);
				((Activity) context).overridePendingTransition(
						R.anim.push_left_in, R.anim.push_left_out);
			}
		});
		add.te_detel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				detel_dialog(position);
			}
		});
		add.te_sdzl.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Message msg = Message.obtain();
				msg.what = 0x004;
				Bundle bundle = new Bundle();
				String[] dat = { data.getName_shr(), data.getPho_srh(),
						data.getDizi(), data.getName_dhr(), data.getPho_dhr() };

				bundle.putStringArray("data", dat);
				msg.setData(bundle);
				((Activity) context).finish();
				((Activity) context).overridePendingTransition(
						R.anim.push_right_out, R.anim.push_right_in);
			}
		});

		return convertView;
	}

	public class addview {
		LinearLayout lin_shr, lin_dhr, lin_xz;
		TextView te_shrname, te_shrpho, te_dhrname, te_dhrpho, te_dz, te_dzts,
				te_sdzl, te_xiugai, te_detel;
	}

	private void findv(addview add, View convertView) {
		add.lin_shr = (LinearLayout) convertView
				.findViewById(R.id.shdzit_linshr);
		add.lin_dhr = (LinearLayout) convertView
				.findViewById(R.id.shdzit_lindhr);
		add.lin_xz = (LinearLayout) convertView.findViewById(R.id.shdzit_linxz);

		add.te_shrname = (TextView) convertView
				.findViewById(R.id.shdzit_tesrrname);
		add.te_shrpho = (TextView) convertView
				.findViewById(R.id.shdzit_tesrhph);
		add.te_dhrname = (TextView) convertView
				.findViewById(R.id.shdzit_tedrrname);
		add.te_dhrpho = (TextView) convertView
				.findViewById(R.id.shdzit_tedrhph);
		add.te_dz = (TextView) convertView.findViewById(R.id.shdzit_tedizi);
		add.te_dzts = (TextView) convertView
				.findViewById(R.id.shdzit_tedizitis);

		add.te_sdzl = (TextView) convertView.findViewById(R.id.shdzit_tesdzl);
		add.te_xiugai = (TextView) convertView.findViewById(R.id.shdzit_texgai);
		add.te_detel = (TextView) convertView.findViewById(R.id.shdzit_tedel);
	}

	private void setviewhw(addview add, View convertView) {
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		int w = dm.widthPixels;
		setviewhw_lin(add.lin_shr, w - (int) (w * 28 / 375.0),
				(int) (w * 35 / 375.0), (int) (w * 14 / 375.0), 0,
				(int) (w * 14 / 375.0), 0);
		setviewhw_lin(add.te_dz, w - (int) (w * 28 / 375.0),
				LayoutParams.WRAP_CONTENT, (int) (w * 14 / 375.0), 0,
				(int) (w * 14 / 375.0), (int) (w * 6 / 375.0));

		setviewhw_lin(add.te_dzts, w - (int) (w * 28 / 375.0),
				LayoutParams.WRAP_CONTENT, (int) (w * 14 / 375.0), 0,
				(int) (w * 14 / 375.0), (int) (w * 6 / 375.0));
		setviewhw_lin(add.lin_dhr, w - (int) (w * 28 / 375.0),
				(int) (w * 35 / 375.0), (int) (w * 14 / 375.0), 0,
				(int) (w * 14 / 375.0), 0);

		setviewhw_lin(add.lin_xz, w - (int) (w * 28 / 375.0),
				(int) (w * 30 / 375.0), (int) (w * 14 / 375.0),
				(int) (w * 5 / 375.0), (int) (w * 14 / 375.0),
				(int) (w * 10 / 375.0));
	}

	private void setviewhw_lin(View v, int width, int height, int left,
			int top, int right, int bottom) {
		LayoutParams lp = new LayoutParams(width, height);
		lp.setMargins(left, top, right, bottom);
		v.setLayoutParams(lp);
	}

	PopupWindow window;

	public void detel_dialog(final int position) {
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		int w_screen = dm.widthPixels;
		int height = dm.heightPixels;
		View view = LayoutInflater.from(context).inflate(R.layout.quit_comm1,
				null);

		TextView tetitl = (TextView) view.findViewById(R.id.comment_quitte);
		TextView textv_ok = (TextView) view.findViewById(R.id.gotocom_quitok);
		TextView textv_no = (TextView) view.findViewById(R.id.gotocom_quitno);
		LinearLayout lin = (LinearLayout) view
				.findViewById(R.id.comment_quitteq);

		tetitl.setText("您确定删除该地址吗？");
		textv_ok.setText("确定");
		textv_ok.setTextColor(Color.parseColor("#da0000"));
		textv_no.setText("取消");

		setviewhw_lin(tetitl, (int) (w_screen * 400 / 750.0),
				(int) (w_screen * 120 / 750), 0, 0, 0, 0);
		setviewhw_lin(lin, (int) (w_screen * 400 / 750.0),
				(int) (w_screen * 80 / 750), 0, 0, 0, 0);

		tetitl.setPadding((int) (w_screen * 20 / 750.0), 0,
				(int) (w_screen * 20 / 750.0), 0);

		// 设置弹出框的宽高
		window = new PopupWindow(view, (int) (w_screen * 400 / 750.0),
				(int) (w_screen * 200 / 750.0));
		ColorDrawable cd = new ColorDrawable(0x000000);// 设置背景变暗
		window.setBackgroundDrawable(cd);
		WindowManager.LayoutParams lp = ((Activity) context).getWindow()
				.getAttributes();
		lp.alpha = 0.9f;
		((Activity) context).getWindow().setAttributes(lp);
		// 设置背景
		// window.setBackgroundDrawable(context.getResources().getDrawable(
		// R.drawable.baisebj));
		window.setClippingEnabled(false);
		// 设置透明度
		// window.getBackground().setAlpha(200);
		// 设置动画,从底部出来
		window.setAnimationStyle(android.R.style.Animation_Dialog);
		// 点击空白区域消失
		window.setOutsideTouchable(true);

		// 设置焦点
		window.setFocusable(true);
		// 可以被触摸
		window.setTouchable(true);
		// 设置软键盘
		// window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
		// 显示的位置,从底部显示
		// 设置popwindow显示位置
		if (android.os.Build.VERSION.SDK_INT >= 24) {
			int[] a = new int[2];
			tetitl.getLocationInWindow(a);
			window.showAtLocation(((Activity) context).getWindow().getDecorView(),
					Gravity.NO_GRAVITY, 0,
					(height - (int) (w_screen * 200 / 750)) / 2);
			window.update();
		} else {
			window.showAtLocation(tetitl, Gravity.CENTER, 0, 0);
			window.update();
		}
		
		window.update();
		window.setOnDismissListener(new OnDismissListener() {// pop消失

			@Override
			public void onDismiss() {
				WindowManager.LayoutParams lp = ((Activity) context)
						.getWindow().getAttributes();
				lp.alpha = 1f;
				((Activity) context).getWindow().setAttributes(lp);
			}
		});
		textv_ok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				list.remove(position);
				notifyDataSetChanged();
				window.dismiss();
			}
		});
		textv_no.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				window.dismiss();
			}
		});
	}

}
