package com.example.juandie_hua.view.pickView.popwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.PopupWindow;

import com.example.juandie_hua.R;
import com.example.juandie_hua.view.pickView.LoopListener;
import com.example.juandie_hua.view.pickView.LoopView;

import java.util.ArrayList;
import java.util.List;

/**
 * PopWindow for Date Pick
 */
public class SingleValuePopWin extends PopupWindow implements OnClickListener {

    public static final int STYLE_DEFAULT = 0;
    private Button cancelBtn;
    private Button confirmBtn;
    private LoopView loopView;
    private View pickerContainerV;
    private View contentView;// root view

    private int position = 0;
    private Context mContext;
    private String textCancel;
    private String textConfirm;
    private String titleTextDescription;
    private int colorCancel;
    private int colorConfirm;
    private int colorTitleText;
    private int btnTextsize;// text btnTextsize of cancel and confirm button
    private int viewTextSize;
    private int viewTitleTextSize;

    private List<String> valueList;

    public static class Builder {

        private List<String> valueList;
        // Required
        private Context context;
        private OnDatePickedListener listener;

        public Builder(Context context, OnDatePickedListener listener) {
            this.context = context;
            this.listener = listener;
            this.textCancel = "取消";
            this.textConfirm = "确认";
        }

        // Option
        private String textCancel;
        private String textConfirm;
        private String titleTextDescription;
        private int colorCancel = Color.parseColor("#666666");
        private int colorConfirm = Color.parseColor("#00a0e9");
        private int colorTitleText = Color.parseColor("#333333");
        private int btnTextSize = 16;// text btnTextsize of cancel and confirm button
        private int viewTextSize = 16;
        private int viewTitleTextSize = 16;
        private int style = STYLE_DEFAULT;

        public Builder setValueList(List<String> valueList) {
            this.valueList = valueList;
            return this;
        }

        public Builder textCancel(String textCancel) {
            this.textCancel = textCancel;
            return this;
        }

        public Builder textConfirm(String textConfirm) {
            this.textConfirm = textConfirm;
            return this;
        }

        public Builder colorCancel(int colorCancel) {
            this.colorCancel = colorCancel;
            return this;
        }

        public Builder colorConfirm(int colorConfirm) {
            this.colorConfirm = colorConfirm;
            return this;
        }

        public Builder colorTitleText(int colorTitleText) {
            this.colorTitleText = colorTitleText;
            return this;
        }

        public Builder style(int style) {
            this.style = style;
            return this;
        }

        /**
         * set btn text btnTextSize
         *
         * @param textSize dp
         */
        public Builder btnTextSize(int textSize) {
            this.btnTextSize = textSize;
            return this;
        }

        public Builder viewTextSize(int textSize) {
            this.viewTextSize = textSize;
            return this;
        }

        public Builder viewTitleText(String titleText) {
            this.titleTextDescription = titleText;
            return this;
        }

        public Builder viewTitleTextSize(int textSize) {
            this.viewTitleTextSize = textSize;
            return this;
        }

        public SingleValuePopWin build() {
            return new SingleValuePopWin(this);
        }
    }

    //将设置属性
    public SingleValuePopWin(Builder builder) {
        this.textCancel = builder.textCancel;
        this.textConfirm = builder.textConfirm;
        this.titleTextDescription = builder.titleTextDescription;
        this.mContext = builder.context;
        this.mListener = builder.listener;
        this.colorCancel = builder.colorCancel;
        this.colorConfirm = builder.colorConfirm;
        this.colorTitleText = builder.colorTitleText;
        this.btnTextsize = builder.btnTextSize;
        this.viewTextSize = builder.viewTextSize;
        this.viewTitleTextSize = builder.viewTitleTextSize;
        this.valueList = builder.valueList;
//        setSelectedDate(builder.dateChose);
        initView();
    }

    private OnDatePickedListener mListener;

    private void initView() {

        contentView = LayoutInflater.from(mContext).inflate(R.layout.layout_single_picker, null);
        cancelBtn = (Button) contentView.findViewById(R.id.btn_cancel);
        confirmBtn = (Button) contentView.findViewById(R.id.btn_confirm);
        loopView = (LoopView) contentView.findViewById(R.id.picker_value);

        pickerContainerV = contentView.findViewById(R.id.container_picker);

        cancelBtn.setText(textCancel);
        confirmBtn.setText(textConfirm);
        cancelBtn.setTextColor(colorCancel);
        confirmBtn.setTextColor(colorConfirm);
        cancelBtn.setTextSize(btnTextsize);
        confirmBtn.setTextSize(btnTextsize);

        loopView.setNotLoop();
        loopView.setTextSize(viewTextSize);
        loopView.setLineSpacingMultiplier(2.5f);
        loopView.setListener(new LoopListener() {
            @Override
            public void onItemSelect(int item) {
                position = item;
            }
        });

        initPickerViews(); // init year and month loop view

        cancelBtn.setOnClickListener(this);
        confirmBtn.setOnClickListener(this);
        contentView.setOnClickListener(this);

        setTouchable(true);
        setFocusable(true);
        // setOutsideTouchable(true);
        setBackgroundDrawable(new BitmapDrawable());
        setAnimationStyle(R.style.FadeInPopWin);
        setContentView(contentView);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
    }

    /**
     * Init year and month loop view,
     * Let the day loop view be handled separately
     */
    private void initPickerViews() {

        loopView.setArrayList((ArrayList) valueList);
        loopView.setInitPosition(position);
    }

    /**
     * set selected date position value when initView.
     */
    private void setSelectedDate(String dateStr) {

        if (!TextUtils.isEmpty(dateStr)) {
            for (int i = 0; i < valueList.size(); i++) {
                if (valueList.get(i).equals(dateStr)) {
                    position = i;
                }
            }
        }
    }

    /**
     * Show date picker popWindow
     *
     * @param activity
     */
    public void showPopWin(Activity activity) {

        if (null != activity) {

            TranslateAnimation trans = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0,
                    Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 1, Animation.RELATIVE_TO_SELF, 0);

            showAtLocation(activity.getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
            trans.setDuration(400);
            trans.setInterpolator(new AccelerateDecelerateInterpolator());

            pickerContainerV.startAnimation(trans);
        }
    }

    /**
     * Dismiss date picker popWindow
     */
    private void dismissPopWin() {

        TranslateAnimation trans = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 1);

        trans.setDuration(400);
        trans.setInterpolator(new AccelerateInterpolator());
        trans.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                dismiss();
            }
        });

        pickerContainerV.startAnimation(trans);
    }

    @Override
    public void onClick(View v) {

        if (v == contentView || v == cancelBtn) {
            dismissPopWin();
        } else if (v == confirmBtn) {
            if (null != mListener) {
                mListener.onDatePickCompleted(position);
            }

            dismissPopWin();
        }
    }

    public interface OnDatePickedListener {

        /**
         * Listener when date has been checked
         *
         * @param pos
         */
        void onDatePickCompleted(int pos);
    }
}
