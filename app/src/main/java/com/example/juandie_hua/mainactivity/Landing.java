/**
 * 网络请求加载进度条
 */
package com.example.juandie_hua.mainactivity;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.example.juandie_hua.R;

public class Landing extends Dialog {
	public Landing(Context context) {
		super(context);
		setLoadingDialog();
	}

	public Landing(Context context, int theme) {
		super(context, theme);
		setLoadingDialog();
	}

	protected Landing(Context context, boolean cancelable,
			OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
		setLoadingDialog();
	}

	private void setLoadingDialog() {
		View dialog_view = LayoutInflater.from(getContext()).inflate(
				R.layout.dialog_view, null);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.setContentView(dialog_view);
		this.setCanceledOnTouchOutside(false);
	}
}
