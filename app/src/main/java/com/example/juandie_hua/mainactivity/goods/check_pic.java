package com.example.juandie_hua.mainactivity.goods;

import java.util.ArrayList;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

//import com.bumptech.glide.Glide;
//import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.juandie_hua.R;
import com.example.juandie_hua.calender.utils.ImageUtils;

public class check_pic extends Activity {
	@ViewInject(R.id.check_retop)
	RelativeLayout re_top;
	@ViewInject(R.id.check_imreturn)
	ImageView im_return;
	@ViewInject(R.id.check_tetitl)
	TextView te_tile;
	@ViewInject(R.id.check_vipg)
	ViewPager vpg;

	private String[] images;
	private ArrayList<ImageView> views;
	private PagerAdapter pagerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.check_pic);
		x.view().inject(this);
		setviewhw();
		setviewdata();
		setviewlisten();
	}

	@SuppressWarnings("deprecation")
	private void setviewlisten() {
		im_return.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
				overridePendingTransition(R.anim.push_right_out,
						R.anim.push_right_in);

			}
		});
		vpg.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				te_tile.setText((arg0 + 1) + "/" + images.length);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	private void setviewdata() {
		Intent i_ = getIntent();
		int pos = Integer.valueOf(i_.getStringExtra("pos")).intValue();

		String[] urls = i_.getStringExtra("urls").split(",");
		images = new String[urls.length];
		for (int i = 0; i < urls.length; i++) {
			if (urls[i].contains(",")) {
				images[i] = urls[i].replace("", "");
			} else {
				images[i] = urls[i];
			}
		}
		views = new ArrayList<ImageView>();
		for (int i = 0; i < images.length; i++) {
			// 循环加入图片
			ImageView imageView = new ImageView(check_pic.this);

//			Glide.with(check_pic.this).load(images[i]).asBitmap().fitCenter()
//					.diskCacheStrategy(DiskCacheStrategy.RESULT)
//					.into(imageView);

			ImageUtils.setImage(check_pic.this,images[i],imageView);

//			Glide.with(check_pic.this).load(images[i]).into(imageView);
			imageView.setScaleType(ScaleType.FIT_CENTER);
			views.add(imageView);
		}
		pagerAdapter = new BasePagerAdapter(views,check_pic.this);
		vpg.setAdapter(pagerAdapter); // 设置适配器

		vpg.setCurrentItem(pos);
		te_tile.setText((pos + 1) + "/" + images.length);

	}

	private void setviewhw() {
		DisplayMetrics dm = getResources().getDisplayMetrics();
		int w = dm.widthPixels;

		setviewhw_lin(re_top, w, (int) (w * 55 / 450.0), 0, 0, 0, 0);
		re_top.setBackgroundResource(R.drawable.biankuangxnew);

		setviewhw_re(im_return, (int) (w * 49 / 450.0), (int) (w * 25 / 450.0),
				0, (int) (w * 15 / 450.0), 0, 0);
		im_return.setPadding((int) (w * 12 / 450.0), 0, (int) (w * 12 / 450.0), 0);
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
