package com.example.juandie_hua.mainactivity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;

import com.example.juandie_hua.R;
import com.example.juandie_hua.mainactivity.no_internet.te_oncl;

public class internet_landing implements te_oncl {
	Context context;
	no_internet no_internet;
	Landing landing;
	re_jk reJk;

	public internet_landing(final Context context) {
		this.context = context;

		no_internet = new no_internet(context, R.style.CustomDialog);
		no_internet.setonc(this);
		landing = new Landing(context, R.style.CustomDialog);
		no_internet.setOnKeyListener(new DialogInterface.OnKeyListener() {

			@Override
			public boolean onKey(DialogInterface dialog, int keyCode,
					KeyEvent event) {
				((Activity) context).overridePendingTransition(
						R.anim.push_right_out, R.anim.push_right_in);
				((Activity) context).finish();
				return true;
			}
		});

	}

	public void setonc(re_jk reJk) {
		this.reJk = reJk;
	}

	public boolean if_inter() {
		if (new internet_if().isNetworkConnected(context)) {
			return true;
		} else {
			no_internet.show();
			return false;
		}
	}

	/**
	 * 设置进度圈弹窗显示
	 */
	public void showlanding() {
		landing.show();
	}

	/**
	 * 设置进度圈弹窗消失
	 */
	public void dismisslanding() {
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				landing.dismiss();
			}
		}, 500);
	}
	public void dismisslanding1() {
				landing.dismiss();
	}

	/**
	 * 设置网络弹窗消失
	 */
	public void dismissinter() {
		no_internet.dismiss();
	}

	@Override
	public void set1(View v) {
		Intent intent = null;
		if (android.os.Build.VERSION.SDK_INT > 10) {
			intent = new Intent(android.provider.Settings.ACTION_SETTINGS);
		} else {
			intent = new Intent();
			ComponentName component = new ComponentName("com.android.settings",
					"com.android.settings.WirelessSettings");
			intent.setComponent(component);
			intent.setAction("android.intent.action.VIEW");
		}
		context.startActivity(intent);
	}

	@Override
	public void re_inter1(View v) {
		reJk.re_requestjk(v);
	}

	public interface re_jk {
		public void re_requestjk(View v);
	}

}
