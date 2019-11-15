package com.example.juandie_hua.view;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.juandie_hua.R;
import com.example.juandie_hua.helper.UiHelper;
import com.example.juandie_hua.utils.TextViewUtils;

import org.xutils.common.util.DensityUtil;


/**
 * 自定义标题栏
 *
 * @author chentt on 2017/12/5.
 */

public class CustomTitleView extends RelativeLayout {

    private Activity mActivity;

    /**
     * 标题
     */
    protected TextView titleText;

    /**
     * 左按钮Image
     */
    protected ImageView leftImage;
    /**
     * 左按钮Text
     */
    protected TextView leftText;
    /**
     * 右按钮Text
     */
    protected TextView rightText;
    /**
     * 右按钮Image
     */
    protected ImageView rightImage;

    private LinearLayout titleTextLayout;
    protected LinearLayout rightLayout;
    protected LinearLayout leftLayout;

    private Context context;
    public int mTitleViewId = 0;
    private View view;

    private int titleHeight;

    public CustomTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        iniTitleBar(context);
    }

    public CustomTitleView(Context context) {
        this(context, null);
    }

    @SuppressLint("ClickableViewAccessibility")
    public void iniTitleBar(Context context) {
        mActivity = (Activity) context;
        titleHeight = DensityUtil.dip2px(48f);
        setId(this.mTitleViewId);
        setBackgroundColor(Color.WHITE);

        //titleText大小
        LayoutParams titleTextLayoutParams = new LayoutParams(-2, -1);

        //leftButton大小
        LayoutParams leftViewLayoutParams = new LayoutParams(titleHeight, -1);

        //rightButton大小
        LayoutParams rightViewLayoutParams = new LayoutParams(titleHeight, -1);

/***************************************titleText布局*******************************************/

        titleTextLayout = new LinearLayout(context);
        titleTextLayout.setGravity(Gravity.CENTER);

        titleText = new TextView(context);
        titleText.setTextColor(context.getResources().getColor(R.color.black_8c8c8c));
        titleText.setTextSize(18);
        TextViewUtils.setTextBold(titleText,true);
        titleText.setSingleLine();
        titleText.setEllipsize(TextUtils.TruncateAt.END);
        titleTextLayout.addView(titleText, titleTextLayoutParams);

/************************************左按钮布局*************************************************/

        leftLayout = new LinearLayout(context);
        leftLayout.setVisibility(View.VISIBLE);
        leftLayout.setGravity(Gravity.CENTER_VERTICAL);
        leftLayout.setPadding(0, DensityUtil.dip2px(10f), 0, DensityUtil.dip2px(10f));

        leftImage = new ImageView(context);
        leftImage.setVisibility(View.VISIBLE);
        leftImage.setImageResource(R.drawable.fh);
        leftLayout.addView(leftImage, leftViewLayoutParams);

        leftText = new TextView(context);
        leftText.setVisibility(View.GONE);
        leftText.setGravity(Gravity.CENTER_VERTICAL);
        leftText.setTextColor(context.getResources().getColor(R.color.grey_999999));
        leftText.setTextSize(16f);
        leftText.setSingleLine();
        leftLayout.addView(leftText, leftViewLayoutParams);

/***********************************右按钮布局**********************************************/

        rightLayout = new LinearLayout(context);
        rightLayout.setGravity(Gravity.CENTER_VERTICAL);
        rightLayout.setVisibility(View.VISIBLE);
        rightLayout.setPadding(0, DensityUtil.dip2px(10f), 0, DensityUtil.dip2px(10f));

        rightImage = new ImageView(context);
        rightImage.setVisibility(View.GONE);
        rightLayout.addView(rightImage, rightViewLayoutParams);

        rightText = new TextView(context);
        rightText.setVisibility(View.GONE);
        rightText.setGravity(Gravity.CENTER_VERTICAL);
        rightText.setTextColor(context.getResources().getColor(R.color.grey_999999));
        rightText.setTextSize(16f);
        rightText.setSingleLine();
        rightLayout.addView(rightText, rightViewLayoutParams);

        view = new View(context);
        view.setBackgroundColor(context.getResources().getColor(R.color.grey_f4f4f4));

/***********************************设置按钮在titleView中的位置***************************************/

        //设置title在父布局中的位置居中
        LayoutParams layoutParamsWW1 = new LayoutParams(-2, -2);
        layoutParamsWW1.addRule(RelativeLayout.CENTER_HORIZONTAL);
        layoutParamsWW1.addRule(RelativeLayout.CENTER_VERTICAL);
        addView(titleTextLayout, layoutParamsWW1);

        //设置左按钮布局leftLayout在父布局中的位置
        LayoutParams layoutParamsWW2 = new LayoutParams(-2, titleHeight);
        layoutParamsWW2.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        layoutParamsWW2.addRule(RelativeLayout.CENTER_VERTICAL);
        addView(leftLayout, layoutParamsWW2);

        //设置右按钮布局rightLayout在父布局中的位置
        LayoutParams layoutParamsWW3 = new LayoutParams(-2, titleHeight);
        layoutParamsWW3.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        layoutParamsWW3.addRule(RelativeLayout.CENTER_VERTICAL);
        addView(rightLayout, layoutParamsWW3);


        //设置title在父布局中的位置居中
        LayoutParams layoutParamsWW4 = new LayoutParams(-1, DensityUtil.dip2px(1f));
        layoutParamsWW4.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        addView(view, layoutParamsWW4);

        leftImage.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                UiHelper.finish(mActivity);
            }
        });

        leftText.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                UiHelper.finish(mActivity);
            }
        });

        leftLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                UiHelper.finish(mActivity);
            }
        });

        //按钮点击效果
        rightText.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    view.setAlpha(0.5f);
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    view.setAlpha(1f);
                }

                return false;
            }
        });

        leftText.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    view.setAlpha(0.5f);
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    view.setAlpha(1f);
                }

                return false;
            }
        });

    }

    /**
     * 当按钮内容过长，或标题内容过长时设置title的padding值
     */
    public void setTitleTextPadding() {

        measureView(leftLayout);
        measureView(rightLayout);
        measureView(titleText);
        int leftWidth = leftLayout.getMeasuredWidth();
        int rightWidth = rightLayout.getMeasuredWidth();
        int titleWidth = titleText.getMeasuredWidth();
        int screenWidth = DensityUtil.getScreenWidth();

        if (leftWidth > rightWidth) {
            if ((leftWidth + titleWidth / 2) > screenWidth / 2) {
                titleTextLayout.setPadding(leftWidth, 0, leftWidth, 0);
            } else {
                titleTextLayout.setGravity(Gravity.CENTER);
            }
        } else {
            if ((rightWidth + titleWidth / 2) > screenWidth / 2) {
                titleTextLayout.setPadding(rightWidth, 0, rightWidth, 0);
            } else {
                titleTextLayout.setGravity(Gravity.CENTER);
            }
        }

    }

    public void setTitleTextSize(int titleTextSize) {
        this.titleText.setTextSize((float) titleTextSize);
    }

    //设置title加粗
    public void setTitleTextBold(boolean bold) {
        TextPaint paint = this.titleText.getPaint();
        if (bold) {
            paint.setFakeBoldText(true);
        } else {
            paint.setFakeBoldText(false);
        }
    }

    public void setTitleText(int resId) {
        this.setTitleText(context.getResources().getString(resId));
    }

    public void setTitleText(String text) {
        this.titleText.setText(text);
        setTitleTextPadding();
    }

    public void setLeftText(int resId) {
        this.setLeftText(context.getResources().getString(resId));
    }

    public void setLeftText(String text) {
        leftText.setVisibility(VISIBLE);
        leftImage.setVisibility(GONE);
        leftText.setText(text);
        setTitleTextPadding();
    }

    public void setLeftTextSize(float size) {
        leftText.setTextSize(size);
    }

    public void setLeftImage(Drawable drawable) {
        leftImage.setVisibility(VISIBLE);
        leftText.setVisibility(GONE);
        leftImage.setImageDrawable(drawable);
        setTitleTextPadding();
    }

    public void setRightText(int resId) {
        this.setRightText(context.getResources().getString(resId));
    }

    public void setRightText(String text) {
        rightText.setVisibility(View.VISIBLE);
        rightImage.setVisibility(GONE);
        rightText.setText(text);
        setTitleTextPadding();
    }

    public void setRightTextSize(float size) {
        rightText.setTextSize(size);
    }

    public void setRightImage(int resId) {
        this.setRightImage(context.getResources().getDrawable(resId));
    }

    public void setRightImage(Drawable drawable) {
        rightText.setVisibility(GONE);
        rightImage.setVisibility(VISIBLE);
        rightImage.setImageDrawable(drawable);
        setTitleTextPadding();
    }

    //设置titleBar的背景
    public void setTitleBarBackground(int res) {
        this.setBackgroundResource(res);
    }

    public void setTitleBarBackground(Drawable drawable) {
        this.setBackgroundDrawable(drawable);
    }

    //设置左边按钮是否隐藏
    public void setLeftViewVisible(int visible) {
        leftText.setVisibility(visible);
        leftImage.setVisibility(visible);
        leftLayout.setVisibility(visible);
    }

    //设置分割线是否隐藏
    public void setLineVisible(int visible) {
        view.setVisibility(visible);
    }

    //设置右边按钮是否隐藏
    public void setRightViewVisible(int visible) {
        rightText.setVisibility(visible);
        rightImage.setVisibility(visible);
        rightLayout.setVisibility(visible);
    }

    //左边按钮点击事件,不确定是text或Image
    public void setLeftOnClickListener(OnClickListener mOnClickListener) {
        this.leftText.setOnClickListener(mOnClickListener);
        this.leftImage.setOnClickListener(mOnClickListener);
        this.leftLayout.setOnClickListener(mOnClickListener);
    }

    //右边按钮点击事件,不确定是text或Image
    public void setRightOnClickListener(OnClickListener mOnClickListener) {
        this.rightImage.setOnClickListener(mOnClickListener);
        this.rightText.setOnClickListener(mOnClickListener);
        this.rightLayout.setOnClickListener(mOnClickListener);
    }

    public void setLeftBtnEnable(boolean enable) {
        this.leftImage.setEnabled(enable);
        this.leftText.setEnabled(enable);
        this.leftLayout.setAlpha(enable ? 1.0f : 0.5f);
    }

    public boolean isLeftBtnEnable() {
        return leftImage.isEnabled() || leftText.isEnabled();
    }

    public void setRightBtnEnable(boolean enable) {
        this.rightImage.setEnabled(enable);
        this.rightText.setEnabled(enable);
        this.rightLayout.setAlpha(enable ? 1.0f : 0.5f);
    }

    public boolean isRightBtnEnable() {
        return rightImage.isEnabled() || rightText.isEnabled();
    }

    public void measureView(View v) {
        if (v != null) {
            int w = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
            int h = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
            v.measure(w, h);
        }
    }

}
