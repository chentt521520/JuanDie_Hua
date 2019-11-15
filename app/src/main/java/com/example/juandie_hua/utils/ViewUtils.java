package com.example.juandie_hua.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import org.xutils.common.util.DensityUtil;

public class ViewUtils {

    public static void setViewLin(View v, int width, int height, int left, int top, int right, int bottom) {
        int w = width > 0 ? DensityUtil.dip2px(width) : width;
        int h = height > 0 ? DensityUtil.dip2px(height) : height;
        int l = left > 0 ? DensityUtil.dip2px(left) : left;
        int r = right > 0 ? DensityUtil.dip2px(right) : right;
        int t = top > 0 ? DensityUtil.dip2px(top) : top;
        int b = bottom > 0 ? DensityUtil.dip2px(bottom) : bottom;

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(w, h);
        lp.setMargins(l, t, r, b);
        v.setLayoutParams(lp);
    }

    public static void setViewRe(View v, int width, int height, int left, int top, int right, int bottom) {
        int w = width > 0 ? DensityUtil.dip2px(width) : width;
        int h = height > 0 ? DensityUtil.dip2px(height) : height;
        int l = left > 0 ? DensityUtil.dip2px(left) : left;
        int r = right > 0 ? DensityUtil.dip2px(right) : right;
        int t = top > 0 ? DensityUtil.dip2px(top) : top;
        int b = bottom > 0 ? DensityUtil.dip2px(bottom) : bottom;

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(w, h);
        lp.setMargins(l, t, r, b);
        v.setLayoutParams(lp);
    }

    public static void setviewhw_lin(View v, int width, int height, int left, int top, int right, int bottom) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, height);
        lp.setMargins(left, top, right, bottom);
        v.setLayoutParams(lp);
    }

    public static void setviewhw_re(View v, int width, int height, int left, int top, int right, int bottom) {
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(width, height);
        lp.setMargins(left, top, right, bottom);
        v.setLayoutParams(lp);
    }

    public static int getSize(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int w = dm.widthPixels;
        return (int) (w / 512.0);
    }
}
