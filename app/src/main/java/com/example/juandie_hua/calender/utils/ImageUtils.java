package com.example.juandie_hua.calender.utils;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

//import com.bumptech.glide.Priority;
//import com.bumptech.glide.request.RequestOptions;
import com.example.juandie_hua.R;
import com.example.juandie_hua.mainactivity.GlideCircleTransform;

import com.bumptech.glide.Glide;

public class ImageUtils {

    public static void setImage(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).asBitmap().into(imageView);
    }

    public static void setImage(Context context, int res, ImageView imageView) {
        Glide.with(context).load(res).asBitmap().into(imageView);
    }

    public static void setImage(Context context, int res, ImageView imageView, int holderRes) {
        Glide.with(context).load(res).placeholder(holderRes).into(imageView);
    }

    public static void setImage(Context context, String url, ImageView imageView, int holderRes) {
        Glide.with(context).load(url).placeholder(holderRes).into(imageView);
    }

    public static void setGifImage(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).asGif().into(imageView);
    }

    public static void setGifImage(Context context, int res, ImageView imageView) {
        Glide.with(context).load(res).asGif().into(imageView);
    }


    public static void setCircleImage(Context context, int res, ImageView imageView) {
//        RequestOptions options = new RequestOptions()
//                .centerCrop()
//                .priority(Priority.HIGH)
//                .transform(new GlideCircleTransform());
        Glide.with(context).load(res).transform(new GlideCircleTransform(context)).into(imageView);
//                .apply(options)
//                .into(imageView);
    }
}
