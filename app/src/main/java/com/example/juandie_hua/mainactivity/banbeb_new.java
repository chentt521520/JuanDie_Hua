/**
 * 版本更新
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

public class banbeb_new extends Dialog {
	TextView te_sz, te_cs, te_con, te_tit;
	te_oncl teOncl;
	Context context;

	public banbeb_new(Context context) {
		super(context);
		this.context = context;
		setLoadingDialog();
	}

	public banbeb_new(Context context, int theme) {
		super(context, theme);
		this.context = context;
		setLoadingDialog();
	}

	public void setonc1(te_oncl teonc1) {
		this.teOncl = teonc1;
	}

	protected banbeb_new(Context context, boolean cancelable,
			OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
		setLoadingDialog();
	}

	private void setLoadingDialog() {
		View dialog_view = LayoutInflater.from(getContext()).inflate(
				R.layout.banbeb_new, null);
		te_tit = (TextView) dialog_view.findViewById(R.id.babbeb_tit);
		te_sz = (TextView) dialog_view.findViewById(R.id.tuichu);
		te_cs = (TextView) dialog_view.findViewById(R.id.reintc);
		te_con = (TextView) dialog_view.findViewById(R.id.bbgx_neiron);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.setContentView(dialog_view);
		this.setCanceledOnTouchOutside(false);

		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		int w_screen = dm.widthPixels;

		setviewhw_lin(te_tit, (int) (w_screen * 500 / 750.0),
				(int) (w_screen * 80 / 750.0), (int) (w_screen * 0 / 750.0),
				(int) (w_screen * 35 / 750.0), 0, (int) (w_screen * 0 / 750.0));

		setviewhw_lin(te_sz, (int) (w_screen * 150 / 750.0),
				(int) (w_screen * 80 / 750.0), (int) (w_screen * 60 / 750.0),
				(int) (w_screen * 10 / 750.0), (int) (w_screen * 60 / 750.0),
				(int) (w_screen * 45 / 750.0));
		setviewhw_lin(te_con, LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT, 0, 0, 0, 0);
		setviewhw_lin(te_cs, (int) (w_screen * 150 / 750.0),
				(int) (w_screen * 80 / 750.0), (int) (w_screen * 0 / 750.0), 0,
				(int) (w_screen * 60 / 750.0), 0);
		te_cs.setPadding(0, (int) (w_screen * 10 / 750.0), 0,
				(int) (w_screen * 10 / 750.0));

		te_sz.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				teOncl.set(te_sz);
			}
		});
		te_cs.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				teOncl.re_inter(te_cs);
			}
		});
	}

	public interface te_oncl {
		public void set(View v);

		public void re_inter(View v);
	}

	public void set_str(String str) {
		te_con.setText(str);
	}

	public void set_vis() {
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		int w_screen = dm.widthPixels;
		te_sz.setVisibility(View.GONE);
		setviewhw_lin(te_cs, (int) (w_screen * 200 / 750.0),
				(int) (w_screen * 80 / 750.0), (int) (w_screen * 150 / 750.0),
				0, (int) (w_screen * 150 / 750.0), 0);
		te_cs.setPadding(0, (int) (w_screen * 10 / 750.0), 0,
				(int) (w_screen * 10 / 750.0));
	}

	private void setviewhw_lin(View v, int width, int height, int left,
			int top, int right, int bottom) {
		LayoutParams lp = new LayoutParams(width, height);
		lp.setMargins(left, top, right, bottom);
		v.setLayoutParams(lp);
	}

}
