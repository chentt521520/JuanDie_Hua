package com.example.juandie_hua.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;

public class CusPopWindow extends PopupWindow implements PopupWindow.OnDismissListener {

    private static final String TAG = "CommonPopWindow";
    private static final float DEFAULT_ALPHA = 0.7F;
    private Context mContext;
    private int mWidth;
    private int mHeight;
    private boolean mIsFocusable;
    private boolean mIsOutside;
    private int mResLayoutId;
    private View mContentView;
    private PopupWindow mPopupWindow;
    private int mAnimationStyle;
    private boolean mClippEnable;
    private boolean mIgnoreCheekPress;
    private int mInputMode;
    private PopupWindow.OnDismissListener mOnDismissListener;
    private int mSoftInputMode;
    private boolean mTouchable;
    private View.OnTouchListener mOnTouchListener;
    private Window mWindow;
    private boolean mIsBackgroundDark;
    private float mBackgroundDrakValue;
    private boolean enableOutsideTouchDisMiss;

    private CusPopWindow(Context context) {
        this.mIsFocusable = true;
        this.mIsOutside = true;
        this.mResLayoutId = -1;
        this.mAnimationStyle = -1;
        this.mClippEnable = true;
        this.mIgnoreCheekPress = false;
        this.mInputMode = -1;
        this.mSoftInputMode = -1;
        this.mTouchable = true;
        this.mIsBackgroundDark = false;
        this.mBackgroundDrakValue = 0.0F;
        this.enableOutsideTouchDisMiss = true;
        this.mContext = context;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public void showAsDropDown(View anchor, int xOff, int yOff) {
        if(this.mPopupWindow != null) {
            this.mPopupWindow.showAsDropDown(anchor, xOff, yOff);
        }
    }

    public void showAsDropDown(View anchor) {
        if(this.mPopupWindow != null) {
            this.mPopupWindow.showAsDropDown(anchor);
        }
    }

    @RequiresApi(api = 19)
    public void showAsDropDown(View anchor, int xOff, int yOff, int gravity) {
        if(this.mPopupWindow != null) {
            this.mPopupWindow.showAsDropDown(anchor, xOff, yOff, gravity);
        }
    }

    public void showAtLocation(View parent, int gravity, int x, int y) {
        if(this.mPopupWindow != null) {
            this.mPopupWindow.showAtLocation(parent, gravity, x, y);
        }
    }

    private void apply(PopupWindow popupWindow) {
        popupWindow.setClippingEnabled(this.mClippEnable);
        if(this.mIgnoreCheekPress) {
            popupWindow.setIgnoreCheekPress();
        }

        if(this.mInputMode != -1) {
            popupWindow.setInputMethodMode(this.mInputMode);
        }

        if(this.mSoftInputMode != -1) {
            popupWindow.setSoftInputMode(this.mSoftInputMode);
        }

        if(this.mOnDismissListener != null) {
            popupWindow.setOnDismissListener(this.mOnDismissListener);
        }

        if(this.mOnTouchListener != null) {
            popupWindow.setTouchInterceptor(this.mOnTouchListener);
        }

        popupWindow.setTouchable(this.mTouchable);
    }

    private PopupWindow build() {
        if(this.mContentView == null) {
            this.mContentView = LayoutInflater.from(this.mContext).inflate(this.mResLayoutId, null);
        }

        Activity activity = (Activity)this.mContentView.getContext();
        if(activity != null && this.mIsBackgroundDark) {
            float alpha = this.mBackgroundDrakValue > 0.0F && this.mBackgroundDrakValue < 1.0F?this.mBackgroundDrakValue:0.7F;
            this.mWindow = activity.getWindow();
            WindowManager.LayoutParams params = this.mWindow.getAttributes();
            params.alpha = alpha;
            this.mWindow.addFlags(2);
            this.mWindow.setAttributes(params);
        }

        if(this.mWidth != 0 && this.mHeight != 0) {
            this.mPopupWindow = new PopupWindow(this.mContentView, this.mWidth, this.mHeight);
        } else {
            this.mPopupWindow = new PopupWindow(this.mContentView, -2, -2);
        }

        if(this.mAnimationStyle != -1) {
            this.mPopupWindow.setAnimationStyle(this.mAnimationStyle);
        }

        this.apply(this.mPopupWindow);
        if(this.mWidth == 0 || this.mHeight == 0) {
            this.mPopupWindow.getContentView().measure(0, 0);
            this.mWidth = this.mPopupWindow.getContentView().getMeasuredWidth();
            this.mHeight = this.mPopupWindow.getContentView().getMeasuredHeight();
        }

        this.mPopupWindow.setOnDismissListener(this);
        if(!this.enableOutsideTouchDisMiss) {
            this.mPopupWindow.setFocusable(true);
            this.mPopupWindow.setOutsideTouchable(false);
            this.mPopupWindow.setBackgroundDrawable((Drawable)null);
            this.mPopupWindow.getContentView().setFocusable(true);
            this.mPopupWindow.getContentView().setFocusableInTouchMode(true);
            this.mPopupWindow.getContentView().setOnKeyListener(new View.OnKeyListener() {
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if(keyCode == 4) {
                        CusPopWindow.this.mPopupWindow.dismiss();
                        return true;
                    } else {
                        return false;
                    }
                }
            });
            this.mPopupWindow.setTouchInterceptor(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    int x = (int)event.getX();
                    int y = (int)event.getY();
                    if(event.getAction() != 0 || x >= 0 && x < CusPopWindow.this.mWidth && y >= 0 && y < CusPopWindow.this.mHeight) {
                        if(event.getAction() == 4) {
                            Log.e("CusPopWindow", "out side ...");
                            return true;
                        } else {
                            return false;
                        }
                    } else {
                        Log.e("CusPopWindow", "out side ");
                        Log.e("CusPopWindow", "width:" + CusPopWindow.this.mPopupWindow.getWidth() + "height:" + CusPopWindow.this.mPopupWindow.getHeight() + " x:" + x + " y  :" + y);
                        return true;
                    }
                }
            });
        } else {
            this.mPopupWindow.setFocusable(this.mIsFocusable);
            this.mPopupWindow.setBackgroundDrawable(new ColorDrawable(0));
            this.mPopupWindow.setOutsideTouchable(this.mIsOutside);
        }

        this.mPopupWindow.update();
        return this.mPopupWindow;
    }

    public void onDismiss() {
        this.dismiss();
    }

    public void dismiss() {
        if(this.mOnDismissListener != null) {
            this.mOnDismissListener.onDismiss();
        }

        if(this.mWindow != null) {
            WindowManager.LayoutParams params = this.mWindow.getAttributes();
            params.alpha = 1.0F;
            this.mWindow.setAttributes(params);
        }

        if(this.mPopupWindow != null && this.mPopupWindow.isShowing()) {
            this.mPopupWindow.dismiss();
        }

    }

    public PopupWindow getPopupWindow() {
        return this.mPopupWindow;
    }

    public static class PopupWindowBuilder {
        private CusPopWindow mCommonPopWindow;

        public PopupWindowBuilder(Context context) {
            this.mCommonPopWindow = new CusPopWindow(context);
        }

        public CusPopWindow.PopupWindowBuilder size(int width, int height) {
            this.mCommonPopWindow.mWidth = width;
            this.mCommonPopWindow.mHeight = height;
            return this;
        }

        public CusPopWindow.PopupWindowBuilder setFocusable(boolean focusable) {
            this.mCommonPopWindow.mIsFocusable = focusable;
            return this;
        }

        public CusPopWindow.PopupWindowBuilder setView(int resLayoutId) {
            this.mCommonPopWindow.mResLayoutId = resLayoutId;
            this.mCommonPopWindow.mContentView = null;
            return this;
        }

        public CusPopWindow.PopupWindowBuilder setView(View view) {
            this.mCommonPopWindow.mContentView = view;
            this.mCommonPopWindow.mResLayoutId = -1;
            return this;
        }

        public CusPopWindow.PopupWindowBuilder setOutsideTouchable(boolean outsideTouchable) {
            this.mCommonPopWindow.mIsOutside = outsideTouchable;
            return this;
        }

        public CusPopWindow.PopupWindowBuilder setAnimationStyle(int animationStyle) {
            this.mCommonPopWindow.mAnimationStyle = animationStyle;
            return this;
        }

        public CusPopWindow.PopupWindowBuilder setClippingEnable(boolean enable) {
            this.mCommonPopWindow.mClippEnable = enable;
            return this;
        }

        public CusPopWindow.PopupWindowBuilder setIgnoreCheekPress(boolean ignoreCheekPress) {
            this.mCommonPopWindow.mIgnoreCheekPress = ignoreCheekPress;
            return this;
        }

        public CusPopWindow.PopupWindowBuilder setInputMethodMode(int mode) {
            this.mCommonPopWindow.mInputMode = mode;
            return this;
        }

        public CusPopWindow.PopupWindowBuilder setOnDissmissListener(PopupWindow.OnDismissListener onDissmissListener) {
            this.mCommonPopWindow.mOnDismissListener = onDissmissListener;
            return this;
        }

        public CusPopWindow.PopupWindowBuilder setSoftInputMode(int softInputMode) {
            this.mCommonPopWindow.mSoftInputMode = softInputMode;
            return this;
        }

        public CusPopWindow.PopupWindowBuilder setTouchable(boolean touchable) {
            this.mCommonPopWindow.mTouchable = touchable;
            return this;
        }

        public CusPopWindow.PopupWindowBuilder setTouchIntercepter(View.OnTouchListener touchIntercepter) {
            this.mCommonPopWindow.mOnTouchListener = touchIntercepter;
            return this;
        }

        public CusPopWindow.PopupWindowBuilder enableBackgroundDark(boolean isDark) {
            this.mCommonPopWindow.mIsBackgroundDark = isDark;
            return this;
        }

        public CusPopWindow.PopupWindowBuilder setBgDarkAlpha(float darkValue) {
            this.mCommonPopWindow.mBackgroundDrakValue = darkValue;
            return this;
        }

        public CusPopWindow.PopupWindowBuilder enableOutsideTouchableDissmiss(boolean disMiss) {
            this.mCommonPopWindow.enableOutsideTouchDisMiss = disMiss;
            return this;
        }

        public CusPopWindow create() {
            this.mCommonPopWindow.build();
            return this.mCommonPopWindow;
        }
    }
}
