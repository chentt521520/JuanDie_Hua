package com.example.juandie_hua.mycar.shouhuodizi;

import java.util.ArrayList;
import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.juandie_hua.R;
import com.example.juandie_hua.mycar.orderpay.addshdz;
import com.example.juandie_hua.utils.StatusBarUtils;

public class shouhuodz extends Activity {

	@ViewInject(R.id.shouhuodzlb_retop)
	RelativeLayout re_top;
	@ViewInject(R.id.shouhuodzlb_imreturn)
	ImageView im_return;

	@ViewInject(R.id.shouhuodzlb_tenodz)
	TextView te_adddz;
	@ViewInject(R.id.shouhuodzlb_listv)
	ListView listv_v;

	shouhuodz_adapter ada;
	List<shouhuodz_adaData> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shouhuodz);
		StatusBarUtils.with(this).setBarColor(R.color.white_fff);

		x.view().inject(this);
		setviwhw();
		setviewdata();
		setviewlisten();
	}

	private void setviewlisten() {
		im_return.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
				overridePendingTransition(R.anim.push_right_out,
						R.anim.push_right_in);
				
			}
		});
		te_adddz.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(shouhuodz.this, addshdz.class));
				overridePendingTransition(R.anim.push_left_in,
						R.anim.push_left_out);
			}
		});
	}

	private void setviewdata() {
		list = new ArrayList<>();
		ada = new shouhuodz_adapter(shouhuodz.this, list);
		listv_v.setAdapter(ada);
		for (int i = 0; i < 11; i++) {
			list.add(new shouhuodz_adaData(i + "1", "大小", "1356566562", "防辐射的",
					"发送134545135311", "大是大非", "134566531"));
		}
		ada.notifyDataSetChanged();

	}

	private void setviwhw() {
		re_top.setFocusable(true);
		re_top.setFocusableInTouchMode(true);

		DisplayMetrics dm = getResources().getDisplayMetrics();
		int w = dm.widthPixels;
		setviewhw_lin(re_top, w, (int) (w * 55 / 450.0), 0, 0, 0, 0);
		re_top.setBackgroundResource(R.drawable.biankuangxnew);

		setviewhw_re(im_return, (int) (w * 49 / 450.0), (int) (w * 25 / 450.0),
				0, (int) (w * 15 / 450.0), 0, 0);
		im_return.setPadding((int) (w * 12 / 450.0), 0, (int) (w * 12 / 450.0), 0);


		setviewhw_lin(te_adddz, w, (int) (w * 90 / 375.0), 0, 0, 0, 0);
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
			finish();
			overridePendingTransition(R.anim.push_right_out,
					R.anim.push_right_in);
			
			return false;
		}
		return false;
	}
}
