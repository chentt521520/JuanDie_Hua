/**
 * 网络连接失败
 */
package com.example.juandie_hua.mainactivity;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.example.juandie_hua.R;

public class no_internet extends Dialog {
	TextView te_sz, te_cs, te_tit;
	te_oncl teOncl;
	Context context;

	public no_internet(Context context) {
		super(context);
		this.context = context;
		setLoadingDialog();
	}

	public no_internet(Context context, int theme) {
		super(context, theme);
		this.context = context;
		setLoadingDialog();
	}

	public void setonc(te_oncl teonc1) {
		this.teOncl = teonc1;
	}

	protected no_internet(Context context, boolean cancelable,
			OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
		setLoadingDialog();
	}

	private void setLoadingDialog() {
		View dialog_view = LayoutInflater.from(getContext()).inflate(
				R.layout.no_internet, null);
		te_sz = (TextView) dialog_view.findViewById(R.id.tuichu);
		te_cs = (TextView) dialog_view.findViewById(R.id.reintc);
		te_tit = (TextView) dialog_view.findViewById(R.id.teinter_tit);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.setContentView(dialog_view);
		this.setCanceledOnTouchOutside(false);

		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		int w_screen = dm.widthPixels;

		setviewhw_lin(te_tit, (int) (w_screen * 370 / 750.0),
				(int) (w_screen * 80 / 750.0), (int) (w_screen * 0 / 750.0),
				(int) (w_screen * 15 / 750.0), 0, (int) (w_screen * 15 / 750.0));
		setviewhw_lin(te_sz, (int) (w_screen * 150 / 750.0),
				(int) (w_screen * 60 / 750.0), (int) (w_screen * 20 / 750.0),
				(int) (w_screen * 0 / 750.0), (int) (w_screen * 20 / 750.0),
				(int) (w_screen * 20 / 750.0));
		setviewhw_lin(te_cs, (int) (w_screen * 150 / 750.0),
				(int) (w_screen * 60 / 750.0), (int) (w_screen * 0 / 750.0),
				(int) (w_screen * 0 / 750.0), (int) (w_screen * 20 / 750.0),
				(int) (w_screen * 20 / 750.0));

		te_sz.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				teOncl.set1(te_sz);
			}
		});
		te_cs.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				teOncl.re_inter1(te_cs);
			}
		});
	}

	public interface te_oncl {
		public void set1(View v);

		public void re_inter1(View v);
	}

	private void setviewhw_lin(View v, int width, int height, int left,
			int top, int right, int bottom) {
		LayoutParams lp = new LayoutParams(width, height);
		lp.setMargins(left, top, right, bottom);
		v.setLayoutParams(lp);
	}
}
