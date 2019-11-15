package com.example.juandie_hua.view.pickView.popwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.PopupWindow;

import com.example.juandie_hua.R;
import com.example.juandie_hua.view.pickView.LoopListener;
import com.example.juandie_hua.view.pickView.LoopView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @author chentt on 2017/3/30.
 */
public class TimePickerPopWin extends PopupWindow implements View.OnClickListener {

    private Button cancelBtn;
    private Button confirmBtn;
    private LoopView hourLoopView;
    private LoopView minuteLoopView;
    private View pickerContainerV;
    private View contentView;// root view

    private static final int MAX_MINUTE = 59;
    private static final int MAX_HOUR = 23;
    private static final int MIN_MINUTE = 0;
    private static final int MIN_HOUR = 0;
    private int hour = 0;
    private int minute = 0;

    private Context mContext;
    private String textCancel;
    private String textConfirm;
    private int colorCancel;
    private int colorConfirm;
    private int btnTextsize;// text btnTextsize of cancel and confirm button
    private int viewTextSize;

    private List<String> hourList = new ArrayList<>();
    private List<String> minuteList = new ArrayList<>();

    public static class Builder {
        // Required
        private Context context;
        private OnTimePickedListener listener;

        public Builder(Context context, OnTimePickedListener listener) {
            this.context = context;
            this.listener = listener;
            this.textCancel = "取消";
            this.textConfirm = "确定";
        }

        // Option
        private String timeChose = getStrDate();
        private String textCancel;
        private String textConfirm;
        private int colorCancel = Color.parseColor("#00a0e9");
        private int colorConfirm = Color.parseColor("#00a0e9");
        private int btnTextSize = 18;// text btnTextsize of cancel and confirm button
        private int viewTextSize = 18;

        public Builder textCancel(String textCancel) {
            this.textCancel = textCancel;
            return this;
        }

        public Builder textConfirm(String textConfirm) {
            this.textConfirm = textConfirm;
            return this;
        }

        public Builder timeChose(String timeChose) {
            this.timeChose = timeChose;
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

        public Builder btnTextSize(int btnTextSize) {
            this.btnTextSize = btnTextSize;
            return this;
        }

        public Builder viewTextSize(int viewTextSize) {
            this.viewTextSize = viewTextSize;
            return this;
        }

        public TimePickerPopWin build() {
            return new TimePickerPopWin(this);
        }
    }

    private TimePickerPopWin(Builder builder) {

        this.mContext = builder.context;
        this.mListener = builder.listener;
        this.colorCancel = builder.colorCancel;
        this.colorConfirm = builder.colorConfirm;
        this.textCancel = builder.textCancel;
        this.textConfirm = builder.textConfirm;
        this.btnTextsize = builder.btnTextSize;
        this.viewTextSize = builder.viewTextSize;
        setSelectedTime(builder.timeChose);
        initView();
    }

    private void initView() {

        contentView = LayoutInflater.from(mContext).inflate(R.layout.layout_time_picker, null);
        cancelBtn = (Button) contentView.findViewById(R.id.btn_cancel);
        confirmBtn = (Button) contentView.findViewById(R.id.btn_confirm);
        hourLoopView = (LoopView) contentView.findViewById(R.id.picker_hour);
        minuteLoopView = (LoopView) contentView.findViewById(R.id.picker_minute);
        pickerContainerV = contentView.findViewById(R.id.container_picker);

        cancelBtn.setText(textCancel);
        confirmBtn.setText(textConfirm);
        cancelBtn.setTextColor(colorCancel);
        confirmBtn.setTextColor(colorConfirm);
        cancelBtn.setTextSize(btnTextsize);
        confirmBtn.setTextSize(btnTextsize);

        hourLoopView.setNotLoop();
        minuteLoopView.setNotLoop();

        hourLoopView.setTextSize(viewTextSize);
        minuteLoopView.setTextSize(viewTextSize);

        hourLoopView.setLineSpacingMultiplier(2.5f);
        minuteLoopView.setLineSpacingMultiplier(2.5f);

        hourLoopView.setListener(new LoopListener() {
            @Override
            public void onItemSelect(int item) {
                hour = item;
            }
        });
        minuteLoopView.setListener(new LoopListener() {
            @Override
            public void onItemSelect(int item) {
                minute = item;
            }
        });
        initPickerViews();

        cancelBtn.setOnClickListener(this);
        confirmBtn.setOnClickListener(this);
        contentView.setOnClickListener(this);

        setTouchable(true);
        setFocusable(true);

        setBackgroundDrawable(new BitmapDrawable());
        setAnimationStyle(R.style.FadeInPopWin);
        setContentView(contentView);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);

    }

    private void initPickerViews() {

        for (int i = MIN_HOUR; i <= MAX_HOUR; i++) {
            hourList.add(format2LenStr(i));
        }

        hourLoopView.setArrayList((ArrayList) hourList);
        hourLoopView.setInitPosition(hour);

        for (int j = MIN_MINUTE; j <= MAX_MINUTE; j++) {
            minuteList.add(format2LenStr(j));
        }

        minuteLoopView.setArrayList((ArrayList) minuteList);
        minuteLoopView.setInitPosition(minute);
    }

    /**
     * Show date picker popWindow
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
        trans.setAnimationListener(new Animation.AnimationListener() {

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

    private OnTimePickedListener mListener;

    /**
     * set selected date position value when initView.
     */
    private void setSelectedTime(String dateStr) {

        if (!TextUtils.isEmpty(dateStr)) {
            long milliseconds = getLongFromHHmm(dateStr);
            Calendar calendar = Calendar.getInstance(Locale.CHINA);
            if (milliseconds != -1) {
                calendar.setTimeInMillis(milliseconds);
                hour = calendar.get(Calendar.HOUR_OF_DAY);
                minute = calendar.get(Calendar.MINUTE);
            }
        }
    }

    private static String format2LenStr(int num) {

        return (num < 10) ? "0" + num : String.valueOf(num);
    }


    /**
     * get long from yyyy-MM-dd
     */
    private static long getLongFromHHmm(String date) {
        SimpleDateFormat mFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        Date parse = null;
        try {
            parse = mFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (parse != null) {
            return parse.getTime();
        } else {
            return -1;
        }
    }

    private static String getStrDate() {
        SimpleDateFormat dd = new SimpleDateFormat("HH:mm", Locale.CHINA);
        return dd.format(new Date());
    }

    public interface OnTimePickedListener {

        void onTimePickerComplete(int hour, int minute, String timeDesc);
    }

    @Override
    public void onClick(View view) {

        if (view == contentView || view == cancelBtn) {

            dismissPopWin();
        } else if (view == confirmBtn) {

            if (null != mListener) {

                StringBuffer buf = new StringBuffer();

                buf.append(format2LenStr(hour));
                buf.append(":");
                buf.append(format2LenStr(minute));
                mListener.onTimePickerComplete(hour, minute, buf.toString());
            }

            dismissPopWin();
        }

    }
}
