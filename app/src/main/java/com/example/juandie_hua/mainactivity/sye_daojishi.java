package com.example.juandie_hua.mainactivity;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.juandie_hua.R;

public class sye_daojishi extends LinearLayout {
	private long time = 90010;// 倒计时
	TextView tetim1, tetim2, tetim3, tetim4, tetit;
	TextView tetim11, tetim21, tetim31, tetim41;
	LinearLayout lin;
	int ix = 0;

	public sye_daojishi(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.daojishi, this);
		tetim1 = (TextView) findViewById(R.id.daojis_tetian);
		tetim2 = (TextView) findViewById(R.id.daojis_texshi);
		tetim3 = (TextView) findViewById(R.id.daojis_tefen);
		tetim4 = (TextView) findViewById(R.id.daojis_temiao);

		tetim11 = (TextView) findViewById(R.id.daojis_tetian1);
		tetim21 = (TextView) findViewById(R.id.daojis_texiaos1);
		tetim31 = (TextView) findViewById(R.id.daojis_tefen1);
		tetim41 = (TextView) findViewById(R.id.daojis_temiao1);

		tetit = (TextView) findViewById(R.id.daojis_te);
		lin = (LinearLayout) findViewById(R.id.daojis_lin);

		DisplayMetrics dm = getResources().getDisplayMetrics();
		int w = dm.widthPixels;
		setviewhw_lin(tetit, w, (int) (w * 70 / 750.0), 0, 0, 0, 0);
		setviewhw_lin(lin, w, (int) (w * 80 / 750.0), 0, 0, 0,
				(int) (w * 10 / 750.0));
		setviewhw_lin(tetim1, (int) (w * 88 / 750.0), (int) (w * 80 / 750.0),
				(int) (w * 100 / 750.0), 0, 0, 0);
		setviewhw_lin(tetim11, (int) (w * 62 / 750.0), (int) (w * 80 / 750.0),
				0, 0, 0, 0);

		setviewhw_lin(tetim2, (int) (w * 88 / 750.0), (int) (w * 80 / 750.0),
				0, 0, 0, 0);
		setviewhw_lin(tetim21, (int) (w * 62 / 750.0), (int) (w * 80 / 750.0),
				0, 0, 0, 0);

		setviewhw_lin(tetim3, (int) (w * 88 / 750.0), (int) (w * 80 / 750.0),
				0, 0, 0, 0);
		setviewhw_lin(tetim31, (int) (w * 62 / 750.0), (int) (w * 80 / 750.0),
				0, 0, 0, 0);
		setviewhw_lin(tetim4, (int) (w * 88 / 750.0), (int) (w * 80 / 750.0),
				0, 0, 0, 0);
		setviewhw_lin(tetim41, (int) (w * 62 / 750.0), (int) (w * 80 / 750.0),
				0, 0, 0, 0);

	}

	public void starttimer(int time1, String tit) {
		if (ix == 0) {
			ix = 1;
			time = time1;
			tetit.setText(tit);
			handler.postDelayed(runnable, 1000);// 启动倒计时
		}
	}

	public String formatLongToTimeStr(Long l) {
		int day = 0;
		int hour = 0;
		int minute = 0;
		int second = 0;
		second = l.intValue();
		if (second > 60) {
			minute = second / 60; // 取整
			second = second % 60; // 取余
		}

		if (minute > 60) {
			hour = minute / 60;
			minute = minute % 60;
		}
		if (hour >= 24) {
			day = hour / 24;
			hour = hour % 24;
		}
		String strtime = day + "：" + hour + "：" + minute + "：" + second;
		return strtime;

	}

	Handler handler = new Handler();
	Runnable runnable = new Runnable() {
		@Override
		public void run() {
			time--;
			String formatLongToTimeStr = formatLongToTimeStr(time);
			String[] split = formatLongToTimeStr.split("：");
			for (int i = 0; i < split.length; i++) {
				if (i == 0) {
					if (split[0].length() == 1) {
						tetim1.setText("0" + split[0]);
					} else
						tetim1.setText(split[0]);
				}
				if (i == 1) {
					if (split[1].length() == 1) {
						tetim2.setText("0" + split[1]);
					} else
						tetim2.setText(split[1]);
				}
				if (i == 2) {
					if (split[2].length() == 1) {
						tetim3.setText("0" + split[2]);
					} else
						tetim3.setText(split[2]);
				}
				if (i == 3) {
					if (split[3].length() == 1) {
						tetim4.setText("0" + split[3]);
					} else
						tetim4.setText(split[3]);
				}

			}
			if (time > 0) {
				handler.postDelayed(this, 1000);
			}
		}
	};

	private void setviewhw_lin(View v, int width, int height, int left,
			int top, int right, int bottom) {
		LayoutParams lp = new LayoutParams(width, height);
		lp.setMargins(left, top, right, bottom);
		v.setLayoutParams(lp);
	}

}
