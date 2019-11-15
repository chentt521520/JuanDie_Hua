package com.example.juandie_hua.percenter.myorder;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.x;
import org.xutils.common.Callback.CancelledException;
import org.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.juandie_hua.R;
import com.example.juandie_hua.mainactivity.Xutils_Get_Post;
import com.example.juandie_hua.mainactivity.Xutils_Get_Post.XCallBack;
import com.example.juandie_hua.mainactivity.internet_if;
import com.example.juandie_hua.mainactivity.internet_landing;
import com.example.juandie_hua.mainactivity.internet_landing.re_jk;
import com.example.juandie_hua.mycar.orderpay.addshdz;
import com.example.juandie_hua.utils.StatusBarUtils;

public class myOrder_pinjia extends Activity implements re_jk {
	@ViewInject(R.id.pinjia_retop)
	RelativeLayout re_top;
	@ViewInject(R.id.pinjia_imreturn)
	ImageView im_return;

	@ViewInject(R.id.pinjia_tecon)
	TextView te_con;

	@ViewInject(R.id.pinjia_ddhao)
	RelativeLayout re_ddhao;
	@ViewInject(R.id.pinjia_teddhao)
	TextView te_ddhao;
	@ViewInject(R.id.pinjia_teddhao1)
	TextView te_ddhao1;

	@ViewInject(R.id.pinjia_fuwu)
	RelativeLayout re_fuwu;
	@ViewInject(R.id.pinjia_tefuwu)
	TextView te_fuwu;
	@ViewInject(R.id.pinjia_linfuwu)
	LinearLayout lin_fuwu;

	@ViewInject(R.id.pinjia_kfxin1)
	ImageView im_fuwuxin1;
	@ViewInject(R.id.pinjia_kfxin2)
	ImageView im_fuwuxin2;
	@ViewInject(R.id.pinjia_kfxin3)
	ImageView im_fuwuxin3;
	@ViewInject(R.id.pinjia_kfxin4)
	ImageView im_fuwuxin4;
	@ViewInject(R.id.pinjia_kfxin5)
	ImageView im_fuwuxin5;

	@ViewInject(R.id.pinjia_peishong)
	RelativeLayout re_ps;
	@ViewInject(R.id.pinjia_tepeishong)
	TextView te_ps;
	@ViewInject(R.id.pinjia_linpeishong)
	LinearLayout lin_ps;

	@ViewInject(R.id.pinjia_ps1)
	ImageView im_ps1;
	@ViewInject(R.id.pinjia_ps2)
	ImageView im_ps2;
	@ViewInject(R.id.pinjia_ps3)
	ImageView im_ps3;
	@ViewInject(R.id.pinjia_ps4)
	ImageView im_ps4;
	@ViewInject(R.id.pinjia_ps5)
	ImageView im_ps5;

	@ViewInject(R.id.pinjia_zl)
	RelativeLayout re_zl;
	@ViewInject(R.id.pinjia_tezl)
	TextView te_zl;
	@ViewInject(R.id.pinjia_linzl)
	LinearLayout lin_zl;

	@ViewInject(R.id.pinjia_zl1)
	ImageView im_zl1;
	@ViewInject(R.id.pinjia_zl2)
	ImageView im_zl2;
	@ViewInject(R.id.pinjia_zl3)
	ImageView im_zl3;
	@ViewInject(R.id.pinjia_zl4)
	ImageView im_zl4;
	@ViewInject(R.id.pinjia_zl5)
	ImageView im_zl5;

	@ViewInject(R.id.pinjia_lintuwen)
	LinearLayout lin_addtuwen;

	@ViewInject(R.id.pinjia_editcom)
	EditText ed_con;
	@ViewInject(R.id.pinjia_retp1)
	RelativeLayout re_tp1;
	@ViewInject(R.id.pinjia_imtp1)
	ImageView im_tp1;
	@ViewInject(R.id.pinjia_imtp1de)
	ImageView im_tp1de;

	@ViewInject(R.id.pinjia_retp2)
	RelativeLayout re_tp2;
	@ViewInject(R.id.pinjia_imtp2)
	ImageView im_tp2;
	@ViewInject(R.id.pinjia_imtp2de)
	ImageView im_tp2de;

	@ViewInject(R.id.pinjia_retp3)
	RelativeLayout re_tp3;
	@ViewInject(R.id.pinjia_imtp3)
	ImageView im_tp3;
	@ViewInject(R.id.pinjia_imtp3de)
	ImageView im_tp3de;

	@ViewInject(R.id.pinjia_imadd)
	ImageView im_addpic;

	@ViewInject(R.id.pinjia_linyzm)
	LinearLayout lin_yzm;
	@ViewInject(R.id.pinjia_teyzm)
	TextView te_yzm;
	@ViewInject(R.id.pinjia_edyzm)
	TextView ed_yzm;
	@ViewInject(R.id.pinjia_code)
	ImageView image_code;

	@ViewInject(R.id.pinjia_tjiao)
	TextView te_tijiao;

	VerifyCode codeUtils;

	int select = 0, select1 = 0, select2 = 0;

	List<String> url = new ArrayList<>();

	private static final int REQUEST_OPEN_GALLERY = 0x022;
	private static final int REQUEST_CROP_PHOTO = 0x033;

	// 裁剪图像 路径
	private static String imgPathCrop;
	// 裁剪图像 URI
	private Uri imgUriCrop;

	SharedPreferences preferences;
	SharedPreferences.Editor editor;

	String order_id, type;
	int xx = 0;

	internet_landing inLanding;
	String comment_content = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myorder_pinjia);
		StatusBarUtils.with(this).setBarColor(R.color.white_fff);

		x.view().inject(this);
		inLanding = new internet_landing(this);
		inLanding.setonc(this);
		preferences = PreferenceManager
				.getDefaultSharedPreferences(myOrder_pinjia.this);
		setviewhw();
		setviewdata();
		setviewlisten();
		xianyin(0);
	}

	private void setviewdata() {
		Intent i = getIntent();
		order_id = i.getStringExtra("id");
		type = i.getStringExtra("type");
		te_ddhao1.setText(i.getStringExtra("id1"));
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
		image_code.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				image_code.setImageBitmap(codeUtils.createBitmap());
			}
		});

		fuwu(im_fuwuxin1, 1);
		fuwu(im_fuwuxin2, 2);
		fuwu(im_fuwuxin3, 3);
		fuwu(im_fuwuxin4, 4);
		fuwu(im_fuwuxin5, 5);

		ps(im_ps1, 1);
		ps(im_ps2, 2);
		ps(im_ps3, 3);
		ps(im_ps4, 4);
		ps(im_ps5, 5);

		zl(im_zl1, 1);
		zl(im_zl2, 2);
		zl(im_zl3, 3);
		zl(im_zl4, 4);
		zl(im_zl5, 5);

		te_tijiao.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (ed_yzm.getText().toString().toLowerCase()
						.equals(codeUtils.getCode().toLowerCase())) {
					if (!TextUtils.isEmpty(ed_con.getText().toString())) {
						if (inLanding.if_inter()) {
							comment_content = ed_con.getText().toString();
							xutils_pj(ed_con.getText().toString());
						}
					} else {
						Toast.makeText(myOrder_pinjia.this, "请输入评论内容",
								Toast.LENGTH_SHORT).show();
					}

				} else {
					Toast.makeText(myOrder_pinjia.this, "验证码有误",
							Toast.LENGTH_SHORT).show();
					image_code.setImageBitmap(codeUtils.createBitmap());
				}
			}
		});

		im_addpic.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// if (url.size() < 3) {
				// openGallery();
				// } else
				// Toast.makeText(myOrder_pinjia.this, "最多只能上传3张图片",
				// Toast.LENGTH_SHORT).show();
				getpic();
			}
		});
		im_tp1de.setOnClickListener(onc);
		im_tp2de.setOnClickListener(onc);
		im_tp3de.setOnClickListener(onc);
	}

	public void getpic() {
		// 版本号的判断
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
			if (url.size() < 3) {
				openGallery();
			} else
				Toast.makeText(myOrder_pinjia.this, "最多只能上传3张图片",
						Toast.LENGTH_SHORT).show();
		} else {
			// 权限有三种状态（1、允许 2、提示 3、禁止）
			int permission = ActivityCompat.checkSelfPermission(
					getApplication(),
					android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
			if (permission != PackageManager.PERMISSION_GRANTED) {
				// 如果设置中权限是禁止的咋返回false;如果是提示咋返回的是true
				boolean is = ActivityCompat
						.shouldShowRequestPermissionRationale(
								this,
								android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
				if (is) {
					ActivityCompat
							.requestPermissions(
									this,
									new String[] { android.Manifest.permission.WRITE_EXTERNAL_STORAGE },
									0x01);
				} else {
					Toast.makeText(myOrder_pinjia.this, "请打开相册权限",
							Toast.LENGTH_SHORT).show();
				}

			} else {
				if (url.size() < 3) {
					openGallery();
				} else
					Toast.makeText(myOrder_pinjia.this, "最多只能上传3张图片",
							Toast.LENGTH_SHORT).show();
			}
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode,
			String[] permissions, int[] grantResults) {
		// TODO Auto-generated method stub
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		switch (requestCode) {
		case 0x01:
			if (grantResults[0] == 0) {
				if (url.size() < 3) {
					openGallery();
				} else
					Toast.makeText(myOrder_pinjia.this, "最多只能上传3张图片",
							Toast.LENGTH_SHORT).show();
			} else {

			}
			break;
		default:
			break;

		}

	}

	/**
	 * 根据数组url 长度来判断显隐
	 */
	private void xianyin(int i) {
		switch (i) {
		case 0:
			re_tp2.setVisibility(View.INVISIBLE);
			re_tp3.setVisibility(View.INVISIBLE);
			re_tp1.setVisibility(View.INVISIBLE);

			break;
		case 1:
			im_tp1.setTag(url.get(0));
			re_tp2.setVisibility(View.INVISIBLE);
			re_tp3.setVisibility(View.INVISIBLE);
			re_tp1.setVisibility(View.VISIBLE);
			im_tp1.setVisibility(View.VISIBLE);
			ImageUtils.imageViewSetPic(im_tp1, url.get(0));

			break;
		case 2:
			im_tp1.setTag(url.get(0));
			im_tp2.setTag(url.get(1));
			re_tp3.setVisibility(View.INVISIBLE);
			re_tp2.setVisibility(View.VISIBLE);
			re_tp1.setVisibility(View.VISIBLE);
			ImageUtils.imageViewSetPic(im_tp1, url.get(0));
			ImageUtils.imageViewSetPic(im_tp2, url.get(1));
			break;
		case 3:
			im_tp1.setTag(url.get(0));
			im_tp2.setTag(url.get(1));
			im_tp3.setTag(url.get(2));
			re_tp2.setVisibility(View.VISIBLE);
			re_tp3.setVisibility(View.VISIBLE);
			re_tp1.setVisibility(View.VISIBLE);
			ImageUtils.imageViewSetPic(im_tp1, url.get(0));
			ImageUtils.imageViewSetPic(im_tp2, url.get(1));
			ImageUtils.imageViewSetPic(im_tp3, url.get(2));
			break;

		default:
			break;
		}
	}

	private void setviewhw() {
		DisplayMetrics dm = getResources().getDisplayMetrics();
		int w = dm.widthPixels;
		setviewhw_lin(re_top, w, (int) (w * 85 / 750.0), 0, 0, 0, 0);
		re_top.setBackgroundResource(R.drawable.biankuangxnew);

		setviewhw_re(im_return, (int) (w * 49 / 450.0), (int) (w * 25 / 450.0),
				0, (int) (w * 15 / 450.0), 0, 0);
		im_return.setPadding((int) (w * 12 / 450.0), 0, (int) (w * 12 / 450.0),
				0);

		setviewhw_lin(te_con, w - (int) (w * 74 / 375.0),
				LayoutParams.WRAP_CONTENT, (int) (w * 14 / 375.0),
				(int) (w * 18 / 375.0), (int) (w * 60 / 375.0),
				(int) (w * 10 / 375.0));

		setviewhw_lin(re_ddhao, w, (int) (w * 50 / 375.0),
				(int) (w * 14 / 375.0), 0, 0, 0);
		setviewhw_lin(re_fuwu, w, (int) (w * 50 / 375.0),
				(int) (w * 14 / 375.0), 0, 0, 0);
		setviewhw_lin(im_fuwuxin1, (int) (w * 20 / 375.0),
				(int) (w * 20 / 375.0), 0, (int) (w * 15 / 375.0),
				(int) (w * 10 / 375.0), (int) (w * 15 / 375.0));
		setviewhw_lin(im_fuwuxin2, (int) (w * 20 / 375.0),
				(int) (w * 20 / 375.0), 0, (int) (w * 15 / 375.0),
				(int) (w * 10 / 375.0), (int) (w * 15 / 375.0));
		setviewhw_lin(im_fuwuxin3, (int) (w * 20 / 375.0),
				(int) (w * 20 / 375.0), 0, (int) (w * 15 / 375.0),
				(int) (w * 10 / 375.0), (int) (w * 15 / 375.0));
		setviewhw_lin(im_fuwuxin4, (int) (w * 20 / 375.0),
				(int) (w * 20 / 375.0), 0, (int) (w * 15 / 375.0),
				(int) (w * 10 / 375.0), (int) (w * 15 / 375.0));
		setviewhw_lin(im_fuwuxin5, (int) (w * 20 / 375.0),
				(int) (w * 20 / 375.0), 0, (int) (w * 15 / 375.0),
				(int) (w * 10 / 375.0), (int) (w * 15 / 375.0));

		setviewhw_lin(re_ps, w, (int) (w * 50 / 375.0), (int) (w * 14 / 375.0),
				0, 0, 0);
		setviewhw_lin(im_ps1, (int) (w * 20 / 375.0), (int) (w * 20 / 375.0),
				0, (int) (w * 15 / 375.0), (int) (w * 10 / 375.0),
				(int) (w * 15 / 375.0));
		setviewhw_lin(im_ps2, (int) (w * 20 / 375.0), (int) (w * 20 / 375.0),
				0, (int) (w * 15 / 375.0), (int) (w * 10 / 375.0),
				(int) (w * 15 / 375.0));
		setviewhw_lin(im_ps3, (int) (w * 20 / 375.0), (int) (w * 20 / 375.0),
				0, (int) (w * 15 / 375.0), (int) (w * 10 / 375.0),
				(int) (w * 15 / 375.0));
		setviewhw_lin(im_ps4, (int) (w * 20 / 375.0), (int) (w * 20 / 375.0),
				0, (int) (w * 15 / 375.0), (int) (w * 10 / 375.0),
				(int) (w * 15 / 375.0));
		setviewhw_lin(im_ps5, (int) (w * 20 / 375.0), (int) (w * 20 / 375.0),
				0, (int) (w * 15 / 375.0), (int) (w * 10 / 375.0),
				(int) (w * 15 / 375.0));

		setviewhw_lin(re_zl, w, (int) (w * 50 / 375.0), (int) (w * 14 / 375.0),
				0, 0, 0);
		setviewhw_lin(im_zl1, (int) (w * 20 / 375.0), (int) (w * 20 / 375.0),
				0, (int) (w * 15 / 375.0), (int) (w * 10 / 375.0),
				(int) (w * 15 / 375.0));
		setviewhw_lin(im_zl2, (int) (w * 20 / 375.0), (int) (w * 20 / 375.0),
				0, (int) (w * 15 / 375.0), (int) (w * 10 / 375.0),
				(int) (w * 15 / 375.0));
		setviewhw_lin(im_zl3, (int) (w * 20 / 375.0), (int) (w * 20 / 375.0),
				0, (int) (w * 15 / 375.0), (int) (w * 10 / 375.0),
				(int) (w * 15 / 375.0));
		setviewhw_lin(im_zl4, (int) (w * 20 / 375.0), (int) (w * 20 / 375.0),
				0, (int) (w * 15 / 375.0), (int) (w * 10 / 375.0),
				(int) (w * 15 / 375.0));
		setviewhw_lin(im_zl5, (int) (w * 20 / 375.0), (int) (w * 20 / 375.0),
				0, (int) (w * 15 / 375.0), (int) (w * 10 / 375.0),
				(int) (w * 15 / 375.0));

		setviewhw_lin(lin_addtuwen, w - (int) (w * 30 / 375.0),
				(int) (w * 160 / 375.0), (int) (w * 15 / 375.0),
				(int) (w * 10 / 375.0), (int) (w * 15 / 375.0),
				(int) (w * 10 / 375.0));
		setviewhw_lin(ed_con, LayoutParams.MATCH_PARENT,
				(int) (w * 70 / 375.0), (int) (w * 10 / 375.0),
				(int) (w * 10 / 375.0), (int) (w * 10 / 375.0),
				(int) (w * 5 / 375.0));
		setviewhw_lin(re_tp1, (int) (w * 70 / 375.0), (int) (w * 70 / 375.0),
				(int) (w * 10 / 375.0), 0, 0, (int) (w * 5 / 375.0));
		setviewhw_lin(re_tp2, (int) (w * 70 / 375.0), (int) (w * 70 / 375.0),
				(int) (w * 10 / 375.0), 0, 0, (int) (w * 5 / 375.0));
		setviewhw_lin(re_tp3, (int) (w * 70 / 375.0), (int) (w * 70 / 375.0),
				(int) (w * 10 / 375.0), 0, 0, (int) (w * 5 / 375.0));
		setviewhw_lin(im_addpic, (int) (w * 40 / 375.0),
				(int) (w * 70 / 375.0), (int) (w * 10 / 375.0), 0, 0,
				(int) (w * 5 / 375.0));

		setviewhw_lin(lin_yzm, w, (int) (w * 50 / 375.0),
				(int) (w * 14 / 375.0), (int) (w * 5 / 375.0), 0, 0);
		setviewhw_lin(image_code, (int) (w * 140 / 375.0),
				(int) (w * 38 / 375.0), 0, (int) (w * 6 / 375.0),
				(int) (w * 14 / 375.0), (int) (w * 6 / 375.0));

		setviewhw_lin(te_tijiao, (int) (w * 345 / 375.0),
				(int) (w * 40 / 375.0), (int) (w * 14 / 375.0),
				(int) (w * 14 / 375.0), 0, (int) (w * 20 / 375.0));

		codeUtils = VerifyCode.getInstance();
		Bitmap bitmap = codeUtils.createBitmap();
		image_code.setImageBitmap(bitmap);

		selector_fuwu(5);
		selector_ps(5);
		selector_zl(5);
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

	OnClickListener onc = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.pinjia_imtp1de:
				for (int i = 0; i < url.size(); i++) {
					if (url.get(i).equals(im_tp1.getTag())) {
						url.remove(i);
						break;
					}
				}
				xianyin(url.size());
				break;
			case R.id.pinjia_imtp2de:
				for (int i = 0; i < url.size(); i++) {
					if (url.get(i).equals(im_tp2.getTag())) {
						url.remove(i);
						break;
					}
				}
				xianyin(url.size());
				break;
			case R.id.pinjia_imtp3de:
				for (int i = 0; i < url.size(); i++) {
					if (url.get(i).equals(im_tp3.getTag())) {
						url.remove(i);
						break;
					}
				}
				xianyin(url.size());
				break;

			default:
				break;
			}
		}
	};

	private void fuwu(View v, final int id) {
		v.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				switch (id) {
				case 1:
					selector_fuwu(1);
					break;
				case 2:
					selector_fuwu(2);
					break;
				case 3:
					selector_fuwu(3);
					break;
				case 4:
					selector_fuwu(4);
					break;
				case 5:
					selector_fuwu(5);
					break;

				default:
					break;
				}

			}
		});

	}

	private void ps(View v, final int id) {
		v.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				switch (id) {
				case 1:
					selector_ps(1);
					break;
				case 2:
					selector_ps(2);
					break;
				case 3:
					selector_ps(3);
					break;
				case 4:
					selector_ps(4);
					break;
				case 5:
					selector_ps(5);
					break;

				default:
					break;
				}

			}
		});

	}

	private void zl(View v, final int id) {
		v.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				switch (id) {
				case 1:
					selector_zl(1);
					break;
				case 2:
					selector_zl(2);
					break;
				case 3:
					selector_zl(3);
					break;
				case 4:
					selector_zl(4);
					break;
				case 5:
					selector_zl(5);
					break;

				default:
					break;
				}
			}
		});

	}

	private void selector_fuwu(int id) {
		switch (id) {
		case 1:
			im_fuwuxin1.setSelected(true);
			im_fuwuxin2.setSelected(false);
			im_fuwuxin3.setSelected(false);
			im_fuwuxin4.setSelected(false);
			im_fuwuxin5.setSelected(false);
			select = 1;
			break;
		case 2:
			im_fuwuxin1.setSelected(true);
			im_fuwuxin2.setSelected(true);
			im_fuwuxin3.setSelected(false);
			im_fuwuxin4.setSelected(false);
			im_fuwuxin5.setSelected(false);
			select = 2;
			break;
		case 3:
			im_fuwuxin1.setSelected(true);
			im_fuwuxin2.setSelected(true);
			im_fuwuxin3.setSelected(true);
			im_fuwuxin4.setSelected(false);
			im_fuwuxin5.setSelected(false);
			select = 3;
			break;
		case 4:
			im_fuwuxin1.setSelected(true);
			im_fuwuxin2.setSelected(true);
			im_fuwuxin3.setSelected(true);
			im_fuwuxin4.setSelected(true);
			im_fuwuxin5.setSelected(false);
			select = 4;
			break;
		case 5:
			im_fuwuxin1.setSelected(true);
			im_fuwuxin2.setSelected(true);
			im_fuwuxin3.setSelected(true);
			im_fuwuxin4.setSelected(true);
			im_fuwuxin5.setSelected(true);
			select = 5;
			break;
		default:
			break;
		}
	}

	private void selector_ps(int id) {
		switch (id) {
		case 1:
			im_ps1.setSelected(true);
			im_ps2.setSelected(false);
			im_ps3.setSelected(false);
			im_ps4.setSelected(false);
			im_ps5.setSelected(false);
			select1 = 1;
			break;
		case 2:
			im_ps1.setSelected(true);
			im_ps2.setSelected(true);
			im_ps3.setSelected(false);
			im_ps4.setSelected(false);
			im_ps5.setSelected(false);
			select1 = 2;
			break;
		case 3:
			im_ps1.setSelected(true);
			im_ps2.setSelected(true);
			im_ps3.setSelected(true);
			im_ps4.setSelected(false);
			im_ps5.setSelected(false);
			select1 = 3;
			break;
		case 4:
			im_ps1.setSelected(true);
			im_ps2.setSelected(true);
			im_ps3.setSelected(true);
			im_ps4.setSelected(true);
			im_ps5.setSelected(false);
			select1 = 4;
			break;
		case 5:
			im_ps1.setSelected(true);
			im_ps2.setSelected(true);
			im_ps3.setSelected(true);
			im_ps4.setSelected(true);
			im_ps5.setSelected(true);
			select1 = 5;
			break;
		default:
			break;
		}
	}

	private void selector_zl(int id) {
		switch (id) {
		case 1:
			im_zl1.setSelected(true);
			im_zl2.setSelected(false);
			im_zl3.setSelected(false);
			im_zl4.setSelected(false);
			im_zl5.setSelected(false);
			select2 = 1;
			break;
		case 2:
			im_zl1.setSelected(true);
			im_zl2.setSelected(true);
			im_zl3.setSelected(false);
			im_zl4.setSelected(false);
			im_zl5.setSelected(false);
			select2 = 2;
			break;
		case 3:
			im_zl1.setSelected(true);
			im_zl2.setSelected(true);
			im_zl3.setSelected(true);
			im_zl4.setSelected(false);
			im_zl5.setSelected(false);
			select2 = 3;
			break;
		case 4:
			im_zl1.setSelected(true);
			im_zl2.setSelected(true);
			im_zl3.setSelected(true);
			im_zl4.setSelected(true);
			im_zl5.setSelected(false);
			select2 = 4;
			break;
		case 5:
			im_zl1.setSelected(true);
			im_zl2.setSelected(true);
			im_zl3.setSelected(true);
			im_zl4.setSelected(true);
			im_zl5.setSelected(true);
			select2 = 5;
			break;
		default:
			break;
		}
	}

	private void openGallery() {
		Intent intent = new Intent();
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
		} else {
			intent.setAction(Intent.ACTION_GET_CONTENT);
			// intent.setAction(Intent.ACTION_PICK);
		}
		intent.setType("image/*");
		startActivityForResult(intent, REQUEST_OPEN_GALLERY);
	}

	/**
	 * 裁剪图片
	 * 
	 * @param uri
	 *            需要 裁剪图像的Uri
	 */
	public void cropPhoto(Uri uri) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		File cropPhotoFile = null;
		try {
			cropPhotoFile = createCropImageFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (cropPhotoFile != null) {
			imgUriCrop = Uri.fromFile(cropPhotoFile);

			intent.setDataAndType(uri, "image/*");
			intent.putExtra("crop", true);
			intent.putExtra("aspectX", 1);
			intent.putExtra("aspectY", 1);
			intent.putExtra("outputX", 300);
			intent.putExtra("outputY", 300);
			intent.putExtra("return-data", false);

			intent.putExtra("scale", true);
			intent.putExtra("scaleUpIfNeeded", true);

			intent.putExtra(MediaStore.EXTRA_OUTPUT, imgUriCrop);
			intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION
					| Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
			startActivityForResult(intent, REQUEST_CROP_PHOTO);

			// Log.i("TAG", "cropPhoto_imgPathCrop:" + imgPathCrop.toString());
			// Log.i("TAG", "cropPhoto_imgUriCrop:" + imgUriCrop.toString());
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode != RESULT_OK) {
			return;
		}
		// data的返回值根据
		switch (requestCode) {
		case REQUEST_OPEN_GALLERY:
			if (data != null) {
				Uri imgUriSel = data.getData();
				cropPhoto(imgUriSel);
				Log.i("TAG", "openGalleryResult_imgUriSel:" + imgUriSel);
			}
			break;
		case REQUEST_CROP_PHOTO:
			addPicToGallery(imgPathCrop);
			url.add(imgPathCrop);
			if (url.size() == 1) {
				re_tp1.setVisibility(View.VISIBLE);
			}
			xianyin(url.size());
			revokeUriPermission(imgUriCrop,
					Intent.FLAG_GRANT_READ_URI_PERMISSION
							| Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

			break;
		}
	}

	/**
	 * 创建裁剪图像保存的文件
	 * 
	 * @return
	 * @throws IOException
	 */
	private File createCropImageFile() throws IOException {
		String imgNameCrop = "HomePic_"
				+ new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		File pictureDirCrop = new File(getExternalFilesDir(
				Environment.DIRECTORY_PICTURES).getAbsolutePath()
				+ "/CropPicture");
		if (!pictureDirCrop.exists()) {
			pictureDirCrop.mkdirs();
		}
		File image = File.createTempFile(imgNameCrop, /* prefix */
				".jpg", /* suffix */
				pictureDirCrop /* directory */
		);
		imgPathCrop = image.getAbsolutePath();
		return image;
	}

	/**
	 * 把图像添加进系统相册
	 * 
	 * @param imgPath
	 *            图像路径
	 */
	private void addPicToGallery(String imgPath) {
		Intent mediaScanIntent = new Intent(
				Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
		File f = new File(imgPath);
		Uri contentUri = Uri.fromFile(f);
		mediaScanIntent.setData(contentUri);
		sendBroadcast(mediaScanIntent);
	}

	String sign = "";

	private void xutils_pj(final String comment_content) {
		inLanding.showlanding();
		String url = "https://app.juandie.com/api_mobile/user.php";

		Map<String, String> maps = new HashMap<>();
		maps.put("act", "ajax_order_comment");
		maps.put("order_id", order_id);
		maps.put("comment_content", comment_content);

		Xutils_Get_Post.getInstance().post(url, maps, new XCallBack() {

			@Override
			public void onResponse(String result) {
				inLanding.dismisslanding1();
				JSONObject jso1;
				try {
					jso1 = new JSONObject(result);
					if (jso1.getString("status").equals("1")) {
						if (type.equals("0")) {
							myOrderAll.handler.sendEmptyMessage(0x01);
						} else
							myOrderDaipjia.handler.sendEmptyMessage(0x01);
						Toast.makeText(myOrder_pinjia.this,
								jso1.getString("msg").toString(),
								Toast.LENGTH_SHORT).show();
						finish();
						overridePendingTransition(R.anim.push_right_out,
								R.anim.push_right_in);
					} else {
						Toast.makeText(myOrder_pinjia.this,
								jso1.getString("msg").toString(),
								Toast.LENGTH_SHORT).show();
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			@Override
			public void onFail(String result) {
				inLanding.dismisslanding();

			}

			@Override
			public void onCancel(CancelledException cex) {
				inLanding.dismisslanding();
			}
		});
	}

	@Override
	public void re_requestjk(View v) {
		if (new internet_if().isNetworkConnected(this)) {
			inLanding.dismissinter();
			xutils_pj(comment_content);
		} else {
			Toast.makeText(this, "网络连接失败,请设置网络", Toast.LENGTH_SHORT).show();
		}
	}

}
