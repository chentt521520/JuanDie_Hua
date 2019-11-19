package com.example.juandie_hua.ui;

import java.io.File;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.juandie_hua.R;
import com.example.juandie_hua.helper.UiHelper;
import com.example.juandie_hua.mainactivity.Fengmian;
import com.meiqia.meiqiasdk.util.MQIntentBuilder;

public class kefu extends Fragment {
	View v;
	@ViewInject(R.id.kefu_tetitl)
	TextView te_titl;
	@ViewInject(R.id.kefu_imhead)
	ImageView im_head;

	@ViewInject(R.id.kefu_imzx)
	ImageView im_zx;
	@ViewInject(R.id.kefu_impho)
	ImageView im_phpo;

	@ViewInject(R.id.kefu_rezx)
	RelativeLayout re_zx;
	@ViewInject(R.id.kefu_repho)
	RelativeLayout re_pho;

	@ViewInject(R.id.kefu_tets)
	TextView te_ts;
	SharedPreferences preferences;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (v == null) {
			v = inflater.inflate(R.layout.kefu, container, false);
			x.view().inject(this, v);
			setviewhw();
			preferences=PreferenceManager.getDefaultSharedPreferences(getActivity());
			setviewlisten();
		}
		return v;
	}

	private void setviewlisten() {
		re_pho.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				String pho = "4006089178";
				
				if (!preferences.getString("kfpho", "0").equals("0")) {
					pho = preferences.getString("kfpho", "0").replace("-", "");
				}
				Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"
						+ pho));
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
				getActivity().overridePendingTransition(R.anim.push_left_in,
						R.anim.push_left_out);
			}
		});
		re_zx.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
                UiHelper.toChatActivity(getActivity());
//				Intent intent = new MQIntentBuilder(getActivity())
//						.setPreSendImageMessage(new File("预发送图片的路径")).setCustomizedId(Fengmian.regid).build();
//				getActivity().startActivity(intent);
//				getActivity().overridePendingTransition(R.anim.push_left_in,
//						R.anim.push_left_out);
			}
		});
	}

	private void setviewhw() {
		DisplayMetrics dm = getResources().getDisplayMetrics();
		int w_screen = dm.widthPixels;
		setviewhw_lin(te_titl, w_screen, (int) (w_screen * 45 / 375.0), 0, 0,
				0, 0);
		setviewhw_lin(im_head, w_screen, (int) (w_screen * 220 / 375.0), 0, 0,
				0, 0);
		setviewhw_lin(te_ts, w_screen, (int) (w_screen * 50 / 375.0), 0, 0, 0,
				0);
		te_ts.setPadding((int) (w_screen * 6 / 375.0), 0, 0, 0);
		setviewhw_re(im_zx, LayoutParams.WRAP_CONTENT,
				(int) (w_screen * 160 / 720.0), (int) (w_screen * 120 / 720.0),
				(int) (w_screen * 24 / 720.0), (int) (w_screen * 30 / 720.0),
				(int) (w_screen * 24 / 720.0));
		setviewhw_re(im_phpo, LayoutParams.WRAP_CONTENT,
				(int) (w_screen * 160 / 720.0), (int) (w_screen * 120 / 720.0),
				(int) (w_screen * 24 / 720.0), (int) (w_screen * 30 / 720.0),
				(int) (w_screen * 24 / 720.0));

		setviewhw_lin(re_zx, w_screen - (int) (w_screen * 120 / 720.0),
				(int) (w_screen * 160 / 720.0), (int) (w_screen * 60 / 720.0),
				(int) (w_screen * 70 / 720.0), (int) (w_screen * 60 / 720.0),
				(int) (w_screen * 18 / 720.0));
		setviewhw_lin(re_pho, w_screen - (int) (w_screen * 120 / 720.0),
				(int) (w_screen * 160 / 720.0), (int) (w_screen * 60 / 720.0),
				(int) (w_screen * 18 / 720.0), (int) (w_screen * 60 / 720.0),
				(int) (w_screen * 18 / 720.0));

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
