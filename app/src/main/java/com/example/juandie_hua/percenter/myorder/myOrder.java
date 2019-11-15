package com.example.juandie_hua.percenter.myorder;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.juandie_hua.R;
import com.example.juandie_hua.ui.tab.Me;
import com.example.juandie_hua.utils.StatusBarUtils;
import com.umeng.analytics.MobclickAgent;

public class myOrder extends FragmentActivity {
	@ViewInject(R.id.myorder_retop)
	RelativeLayout re_top;
	@ViewInject(R.id.myorder_imreturn)
	ImageView im_return;

	@ViewInject(R.id.myorder_linte)
	LinearLayout lin_te;
	@ViewInject(R.id.myorder_teall)
	TextView te_all;
	@ViewInject(R.id.myorder_tedfkuan)
	TextView te_dfkuan;
	@ViewInject(R.id.myorder_tedshuo)
	TextView te_dshuo;
	@ViewInject(R.id.myorder_tedpjia)
	TextView te_dpjia;

	@ViewInject(R.id.myorder_hen1)
	View view_wsy;
	@ViewInject(R.id.myorder_hen2)
	View view_ysy;
	@ViewInject(R.id.myorder_hen3)
	View view_ygq;
	@ViewInject(R.id.myorder_hen4)
	View view_dpj;

	@ViewInject(R.id.myorder_fragment)
	FrameLayout frg_lauout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myorder);
		StatusBarUtils.with(this).setBarColor(R.color.white_fff);

		x.view().inject(this);
		setviewhw();
		setviewdata();
		setviewlisten();

	}

	private void setviewdata() {
		Intent i = getIntent();
		String type = i.getStringExtra("type");
		if (type.equals("1")) {
			FragmentTransaction ft = getSupportFragmentManager()
					.beginTransaction();
			ft.replace(frg_lauout.getId(), new myOrderAll());
			ft.commit();
		} else if (type.equals("2")) {
			if (!te_dfkuan.isSelected()) {
				FragmentTransaction ft = getSupportFragmentManager()
						.beginTransaction();
				ft.replace(frg_lauout.getId(), new myOrderDaifukuan());
				ft.commit();
				setselect(2);
			}

		} else if (type.equals("3")) {
			if (!te_dshuo.isSelected()) {
				FragmentTransaction ft = getSupportFragmentManager()
						.beginTransaction();
				ft.replace(frg_lauout.getId(), new myOrderDaishuo());
				ft.commit();
				setselect(3);
			}
		} else if (type.equals("4")) {
			if (!te_dpjia.isSelected()) {
				FragmentTransaction ft = getSupportFragmentManager()
						.beginTransaction();
				ft.replace(frg_lauout.getId(), new myOrderDaipjia());
				ft.commit();
				setselect(4);
			}
		}
	}

	private void setviewlisten() {
		im_return.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!(Me.handler == null)) {
					Me.handler.sendEmptyMessage(0x003);
				}

				finish();
				overridePendingTransition(R.anim.push_right_out,
						R.anim.push_right_in);
			}
		});
		te_all.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!te_all.isSelected()) {
					FragmentTransaction ft = getSupportFragmentManager()
							.beginTransaction();
					ft.replace(frg_lauout.getId(), new myOrderAll());
					ft.commit();
					setselect(1);
				}
			}
		});
		te_dfkuan.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!te_dfkuan.isSelected()) {
					FragmentTransaction ft = getSupportFragmentManager()
							.beginTransaction();
					ft.replace(frg_lauout.getId(), new myOrderDaifukuan());
					ft.commit();
					setselect(2);
				}
			}
		});
		te_dshuo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!te_dshuo.isSelected()) {
					FragmentTransaction ft = getSupportFragmentManager()
							.beginTransaction();
					ft.replace(frg_lauout.getId(), new myOrderDaishuo());
					ft.commit();
					setselect(3);
				}
			}
		});
		te_dpjia.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!te_dpjia.isSelected()) {
					FragmentTransaction ft = getSupportFragmentManager()
							.beginTransaction();
					ft.replace(frg_lauout.getId(), new myOrderDaipjia());
					ft.commit();
					setselect(4);
				}
			}
		});
	}

	private void setviewhw() {
		te_all.setSelected(true);
		view_wsy.setSelected(true);
		DisplayMetrics dm = getResources().getDisplayMetrics();
		int w = dm.widthPixels;
		setviewhw_lin(re_top, w, (int) (w * 55 / 450.0), 0, 0, 0, 0);
		re_top.setBackgroundResource(R.drawable.biankuangxnew);

		setviewhw_re(im_return, (int) (w * 49 / 450.0), (int) (w * 25 / 450.0),
				0, (int) (w * 15 / 450.0), 0, 0);
		im_return.setPadding((int) (w * 12 / 450.0), 0, (int) (w * 12 / 450.0),
				0);
		setviewhw_lin(lin_te, w, (int) (w * 44 / 375.0), 0, 0, 0, 0);

	}

	private void setselect(int i) {
		switch (i) {
		case 1:
			te_all.setSelected(true);
			view_wsy.setSelected(true);

			te_dfkuan.setSelected(false);
			view_ysy.setSelected(false);

			te_dshuo.setSelected(false);
			view_ygq.setSelected(false);

			te_dpjia.setSelected(false);
			view_dpj.setSelected(false);

			break;
		case 2:
			te_dfkuan.setSelected(true);
			view_ysy.setSelected(true);

			te_all.setSelected(false);
			view_wsy.setSelected(false);

			te_dshuo.setSelected(false);
			view_ygq.setSelected(false);

			te_dpjia.setSelected(false);
			view_dpj.setSelected(false);

			break;
		case 3:
			te_dshuo.setSelected(true);
			view_ygq.setSelected(true);

			te_dfkuan.setSelected(false);
			view_ysy.setSelected(false);

			te_all.setSelected(false);
			view_wsy.setSelected(false);

			te_dpjia.setSelected(false);
			view_dpj.setSelected(false);

			break;
		case 4:
			te_dshuo.setSelected(false);
			view_ygq.setSelected(false);

			te_dfkuan.setSelected(false);
			view_ysy.setSelected(false);

			te_all.setSelected(false);
			view_wsy.setSelected(false);

			te_dpjia.setSelected(true);
			view_dpj.setSelected(true);

			break;

		default:
			break;
		}
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

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			Me.handler.sendEmptyMessage(0x003);
			finish();
			overridePendingTransition(R.anim.push_right_out,
					R.anim.push_right_in);
			return false;
		}
		return false;
	}

	public void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
	}

	public void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}
}
