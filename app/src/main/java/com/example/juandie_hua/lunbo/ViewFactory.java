package com.example.juandie_hua.lunbo;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

//import com.bumptech.glide.Glide;
import com.example.juandie_hua.R;
import com.example.juandie_hua.calender.utils.ImageUtils;

/**
 * ImageView创建工厂
 */
public class ViewFactory {

	/**
	 * 获取ImageView视图的同时加载显示url
	 * 
	 * @param text
	 * @return
	 */
	public static ImageView getImageView(Context context, String url) {
		ImageView imageView = (ImageView) LayoutInflater.from(context).inflate(R.layout.view_banner, null);
		imageView.setScaleType(ScaleType.FIT_XY);

		ImageUtils.setImage(context.getApplicationContext(),url,imageView);
		return imageView;
	}
}
