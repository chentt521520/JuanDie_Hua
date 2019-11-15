package com.example.juandie_hua.mainactivity;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.module.GlideModule;

public class GlideConfiguration implements GlideModule {

	@Override
	public void applyOptions(Context context, GlideBuilder builder) {
		builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);// glide框架背景边绿色
	}

	@Override
	public void registerComponents(Context arg0, Glide arg1) {
		// TODO Auto-generated method stub

	}

}
