package com.example.juandie_hua.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.widget.TextView;

public class TextViewUtils {

    /**
     * textview设置图片
     *
     * @param context  上下文
     * @param textView textView
     * @param imageId  image
     * @param flag     1~4：左上右下
     */
    public static void setImage(Context context, TextView textView, int imageId, int flag) {
        setImage(context, textView, imageId, flag, 0);
    }

    /**
     * textview设置图片
     *
     * @param context  上下文
     * @param textView textView
     * @param imageId  image
     * @param flag     1~4：左上右下
     * @param space    间距
     */
    public static void setImage(Context context, TextView textView, int imageId, int flag, int space) {
        setImage(context, textView, imageId, flag, space, 0, 0);
    }

    public static void setImage(Context context, TextView textView, int imageId, int flag, int space, int width, int height) {
        Drawable drawable = context.getResources().getDrawable(imageId);
        if (width == 0 && height == 0) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        } else {
            drawable.setBounds(0, 0, width, height);
        }
        switch (flag) {
            case 1:
                textView.setCompoundDrawables(drawable, null, null, null);
                break;
            case 2:
                textView.setCompoundDrawables(null, drawable, null, null);
                break;
            case 3:
                textView.setCompoundDrawables(null, null, drawable, null);
                break;
            case 4:
                textView.setCompoundDrawables(null, null, null, drawable);
                break;
        }
        textView.setCompoundDrawablePadding(space);
    }

    /**
     * textview设置图片
     *
     * @param context       上下文
     * @param textView      textView
     * @param imageIdLeft   左图片ID
     * @param imageIdTop    上图片ID
     * @param imageIdRight  右图片ID
     * @param imageIdBottom 下图片ID
     */
    public static void setImage(Context context, TextView textView, int imageIdLeft, int imageIdTop, int imageIdRight, int imageIdBottom) {
        setImage(context, textView, imageIdLeft, imageIdTop, imageIdRight, imageIdBottom, 0, 0);
    }

    public static void setImage(Context context, TextView textView, int imageIdLeft, int imageIdTop, int imageIdRight, int imageIdBottom, int width, int height) {
        Drawable drawableLeft = imageIdLeft == 0 ? null : context.getResources().getDrawable(imageIdLeft);  //左
        Drawable drawableTop = imageIdTop == 0 ? null : context.getResources().getDrawable(imageIdTop); //上
        Drawable drawableRight = imageIdRight == 0 ? null : context.getResources().getDrawable(imageIdRight);   //右
        Drawable drawableBottom = imageIdBottom == 0 ? null : context.getResources().getDrawable(imageIdBottom);    //下

        if (drawableLeft != null) {
            if (width == 0 && height == 0) {
                drawableLeft.setBounds(0, 0, drawableLeft.getMinimumWidth(), drawableLeft.getMinimumHeight());
            } else {
                drawableLeft.setBounds(0, 0, width, height);
            }
        }
        if (drawableTop != null) {
            if (width == 0 && height == 0) {
                drawableTop.setBounds(0, 0, drawableLeft.getMinimumWidth(), drawableLeft.getMinimumHeight());
            } else {
                drawableTop.setBounds(0, 0, width, height);
            }
        }
        if (drawableRight != null) {
            if (width == 0 && height == 0) {
                drawableRight.setBounds(0, 0, drawableLeft.getMinimumWidth(), drawableLeft.getMinimumHeight());
            } else {
                drawableRight.setBounds(0, 0, width, height);
            }
        }
        if (drawableBottom != null) {
            if (width == 0 && height == 0) {
                drawableBottom.setBounds(0, 0, drawableLeft.getMinimumWidth(), drawableLeft.getMinimumHeight());
            } else {
                drawableBottom.setBounds(0, 0, width, height);
            }
        }
        textView.setCompoundDrawables(drawableLeft, drawableTop, drawableRight, drawableBottom);
    }

    //textview取消图片
    public static void setImage(TextView textView) {
        textView.setCompoundDrawables(null, null, null, null);
    }

    /**
     * 设置字体粗体
     *
     * @param textView textView
     * @param b        ==true设置加粗
     */
    public static void setTextBold(TextView textView, boolean b) {
        textView.setTypeface(b ? Typeface.defaultFromStyle(Typeface.BOLD) : Typeface.defaultFromStyle(Typeface.NORMAL));
    }

    /**
     * 设置字体大小
     *
     * @param textView textView
     * @param b
     * @param tSize    b==tuue时的大小sp
     * @param fSize    b==false时的大小sp
     */
    public static void setTextSize(TextView textView, boolean b, float tSize, float fSize) {
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, b ? tSize : fSize);
    }

    /**
     * 设置字体颜色
     *
     * @param textView TextView
     * @param b
     * @param tColor   b == true时的颜色
     * @param fColor   b == false时的颜色
     */
    public static void setTextColor(TextView textView, boolean b, String tColor, String fColor) {
        textView.setTextColor(Color.parseColor(b ? tColor : fColor));
    }

    /**
     * 设置字体加删除线
     *
     * @param textView TectView
     */
    public static void setTextAddLine(TextView textView) {
        textView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }
}
