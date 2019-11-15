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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * PopWindow for Date Pick
 */
public class DatePickerPopWin extends PopupWindow implements OnClickListener {

    public static final int STYLE_DEFAULT = 0;
    public static final int STYLE_YEAR = 1;
    public static final int STYLE_YEAR_AND_MONTH = 2;
    private static final int DEFAULT_MIN_YEAR = 1900;
    private static final int DEFAULT_MAX_YEAR = 2050;
    private Button cancelBtn;
    private Button confirmBtn;
    private LoopView yearLoopView;
    private LoopView monthLoopView;
    private LoopView dayLoopView;
    private View pickerContainerV;
    private View contentView;// root view

    private int minYear; // min year
    private int maxYear; // max year
    private int yearPos = 0;
    private int monthPos = 0;
    private int dayPos = 0;
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

    private List<String> yearList = new ArrayList<>();
    private List<String> monthList = new ArrayList<>();
    private List<String> dayList = new ArrayList<>();
    private boolean isShow = false;

    public static class Builder {

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
        private int minYear = DEFAULT_MIN_YEAR;
        private int maxYear = DEFAULT_MAX_YEAR;
        private String textCancel;
        private String textConfirm;
        private String titleTextDescription;
        private String dateChose = getStrDate();
        private int colorCancel = Color.parseColor("#00a0e9");
        private int colorConfirm = Color.parseColor("#00a0e9");
        private int colorTitleText = Color.parseColor("#333333");
        private int btnTextSize = 18;// text btnTextsize of cancel and confirm button
        private int viewTextSize = 18;
        private int viewTitleTextSize = 18;
        private boolean isShow;//中间标题是否显示，默认不显示
        private int style = STYLE_DEFAULT;

        public Builder minYear(int minYear) {
            this.minYear = minYear;
            return this;
        }

        public Builder maxYear(int maxYear) {
            this.maxYear = maxYear;
            return this;
        }

        public Builder isTitilShow(boolean isShow) {
            this.isShow = isShow;
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

        public Builder dateChose(String dateChose) {
            this.dateChose = dateChose;
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

        public DatePickerPopWin build() {
            if (minYear > maxYear) {
                throw new IllegalArgumentException();
            }
            return new DatePickerPopWin(this);
        }
    }

    //将设置属性
    public DatePickerPopWin(Builder builder) {
        this.minYear = builder.minYear;
        this.maxYear = builder.maxYear;
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
        this.isShow = builder.isShow;
        setSelectedDate(builder.dateChose);
        initView(builder.style);
    }

    private OnDatePickedListener mListener;

    private void initView(int style) {

        contentView = LayoutInflater.from(mContext).inflate(R.layout.layout_date_picker, null);
        cancelBtn = (Button) contentView.findViewById(R.id.btn_cancel);
        confirmBtn = (Button) contentView.findViewById(R.id.btn_confirm);
        yearLoopView = (LoopView) contentView.findViewById(R.id.picker_year);
        monthLoopView = (LoopView) contentView.findViewById(R.id.picker_month);
        dayLoopView = (LoopView) contentView.findViewById(R.id.picker_day);

        if (style == STYLE_YEAR) {
            monthLoopView.setVisibility(View.GONE);
            dayLoopView.setVisibility(View.GONE);
        } else if (style == STYLE_YEAR_AND_MONTH) {
            dayLoopView.setVisibility(View.GONE);
        }

        pickerContainerV = contentView.findViewById(R.id.container_picker);

        cancelBtn.setText(textCancel);
        confirmBtn.setText(textConfirm);
        cancelBtn.setTextColor(colorCancel);
        confirmBtn.setTextColor(colorConfirm);
        cancelBtn.setTextSize(btnTextsize);
        confirmBtn.setTextSize(btnTextsize);

        // do not loop,default can loop
        yearLoopView.setNotLoop();
        monthLoopView.setNotLoop();
        dayLoopView.setNotLoop();

        // set loopview text btnTextsize
        yearLoopView.setTextSize(viewTextSize);
        monthLoopView.setTextSize(viewTextSize);
        dayLoopView.setTextSize(viewTextSize);

        yearLoopView.setLineSpacingMultiplier(2.5f);
        monthLoopView.setLineSpacingMultiplier(2.5f);
        dayLoopView.setLineSpacingMultiplier(2.5f);

        // set checked listen
        yearLoopView.setListener(new LoopListener() {
            @Override
            public void onItemSelect(int item) {
                yearPos = item;
                initDayPickerView();
            }
        });
        monthLoopView.setListener(new LoopListener() {
            @Override
            public void onItemSelect(int item) {
                monthPos = item;
                initDayPickerView();
            }
        });
        dayLoopView.setListener(new LoopListener() {
            @Override
            public void onItemSelect(int item) {
                dayPos = item;
            }
        });

        initPickerViews(); // init year and month loop view
        initDayPickerView(); // init day loop view

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

        int yearCount = maxYear - minYear;

        for (int i = 0; i < yearCount; i++) {
            yearList.add(format2LenStr(minYear + i));
        }

        for (int j = 0; j < 12; j++) {
            monthList.add(format2LenStr(j + 1));
        }

        yearLoopView.setArrayList((ArrayList) yearList);
        yearLoopView.setInitPosition(yearPos);

        monthLoopView.setArrayList((ArrayList) monthList);
        monthLoopView.setInitPosition(monthPos);
    }

    /**
     * Init day item
     */
    private void initDayPickerView() {

        int dayMaxInMonth;
        Calendar calendar = Calendar.getInstance();
        dayList = new ArrayList<>();

        calendar.set(Calendar.YEAR, minYear + yearPos);
        calendar.set(Calendar.MONTH, monthPos);

        // get max day in month
        dayMaxInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (int i = 0; i < dayMaxInMonth; i++) {
            dayList.add(format2LenStr(i + 1));
        }

        dayLoopView.setArrayList((ArrayList) dayList);
        // 判断位置，否则选择10月31日后，无法选择9月30日和9月1日
        if (dayPos + 1 > dayMaxInMonth) {
            dayPos = dayMaxInMonth - 1;
        }
        dayLoopView.setInitPosition(dayPos);
    }

    /**
     * set selected date position value when initView.
     */
    private void setSelectedDate(String dateStr) {

        if (!TextUtils.isEmpty(dateStr)) {

            long milliseconds = getLongFromyyyyMMdd(dateStr);
            Calendar calendar = Calendar.getInstance(Locale.CHINA);

            if (milliseconds != -1) {

                calendar.setTimeInMillis(milliseconds);
                yearPos = calendar.get(Calendar.YEAR) - minYear;
                monthPos = calendar.get(Calendar.MONTH);
                dayPos = calendar.get(Calendar.DAY_OF_MONTH) - 1;
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
                int year = minYear + yearPos;
                int month = Integer.parseInt(monthList.get(monthPos));

                int day;
                //当天集合大于当前天所在的位置时，取当前显示
                if (dayList.size() > dayPos) {
                    day = Integer.parseInt(dayList.get(dayPos));
                }
                //当天集合小于当前天所在的位置时，取最后一天
                else {
                    day = Integer.parseInt(dayList.get(dayList.size() - 1));
                }

                StringBuffer sb = new StringBuffer();

                sb.append(String.valueOf(year));
                sb.append("-");
                sb.append(format2LenStr(month));
                sb.append("-");
                sb.append(format2LenStr(day));
                mListener.onDatePickCompleted(year, month, day, sb.toString());
            }

            dismissPopWin();
        }
    }

    /**
     * get long from yyyy-MM-dd
     */
    private static long getLongFromyyyyMMdd(String date) {
        SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
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
        SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        return dd.format(new Date());
    }

    /**
     * Transform int to String with prefix "0" if less than 10
     */
    private static String format2LenStr(int num) {

        return (num < 10) ? "0" + num : String.valueOf(num);
    }

    public static int spToPx(Context context, int spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public interface OnDatePickedListener {

        /**
         * Listener when date has been checked
         *
         * @param year
         * @param month
         * @param day
         * @param dateDesc yyyy-MM-dd
         */
        void onDatePickCompleted(int year, int month, int day, String dateDesc);
    }
}
