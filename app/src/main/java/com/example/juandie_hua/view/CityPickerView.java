package com.example.juandie_hua.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.juandie_hua.R;
import com.example.juandie_hua.mycar.QNumberPicker;
import com.example.juandie_hua.view.citychoosedialog.entity.CityInfo;

import java.util.List;

public class CityPickerView extends PopupWindow {

    private Context context;
    private View background;
    private TextView confirm;
    private TextView cancel;
    private QNumberPicker picker_province;
    private QNumberPicker picker_district;
    private QNumberPicker picker_city;
    private OnCitySelectListener mListener;
    private List<CityInfo> list;
    private LinearLayout container;
    private int index;
    private StringBuffer addressStrs;


    public static class Builder {
        private Context context;
        OnCitySelectListener listener;
        List<CityInfo> list;


        public Builder(Context context) {
            this.context = context;
        }

        public CityPickerView build() {
            return new CityPickerView(this);
        }

        public Builder setListener(OnCitySelectListener listener) {
            this.listener = listener;
            return this;
        }

        public Builder setList(List<CityInfo> list) {
            this.list = list;
            return this;
        }
    }

    public void reFreshData(int pos, List<CityInfo> list) {
        this.list = list;
        initPicker(pos);
    }

    //将设置属性
    private CityPickerView(Builder builder) {
        this.context = builder.context;
        this.mListener = builder.listener;
        this.list = builder.list;
        initView();
    }

    private void initView() {
        View popView = LayoutInflater.from(context).inflate(R.layout.pop_city_selector, null, true);
        container = popView.findViewById(R.id.pop_picker_container);
        picker_province = popView.findViewById(R.id.pop_picker_province);
        picker_city = popView.findViewById(R.id.pop_picker_city);
        picker_district = popView.findViewById(R.id.pop_picker_district);
        confirm = popView.findViewById(R.id.pop_picker_confirm);
        cancel = popView.findViewById(R.id.pop_picker_cancel);
        background = popView.findViewById(R.id.back_top);


        addressStrs = new StringBuffer();
        setTouchable(true);
        setFocusable(true);
        setOutsideTouchable(true);
        setBackgroundDrawable(new BitmapDrawable());

        setContentView(popView);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);

        confirm.setOnClickListener(listener);
        cancel.setOnClickListener(listener);

        initPicker(0);
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.pop_picker_cancel:
                    dismissPopWin();

                    break;
                case R.id.pop_picker_confirm:
                    String id;
                    if (index == 0) {
                        addressStrs.append(list.get(picker_province.getValue()).getRegion_name()).append(" ");
                        id = list.get(picker_province.getValue()).getRegion_id();
                    } else if (index == 1) {
                        addressStrs.append(list.get(picker_city.getValue()).getRegion_name()).append(" ");
                        id = list.get(picker_city.getValue()).getRegion_id();
                    } else {
                        addressStrs.append(list.get(picker_district.getValue()).getRegion_name());
                        id = list.get(picker_district.getValue()).getRegion_id();
                    }
                    index++;
                    mListener.onConfirmListener(index, id, addressStrs.toString().trim());
                    if (index == 3) {
                        dismissPopWin();
                    }

                    break;
            }
        }
    };


    private void initPicker(int pos) {
        String[] data = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            data[i] = list.get(i).getRegion_name();
        }
        if (pos == 0) {
            picker_province.setDisplayedValues(data);
            picker_province.setMinValue(0);
            picker_province.setMaxValue(data.length - 1);
            picker_province.setValue(0);
            setNumberPickerDividerColor(picker_province);
            picker_province.setVisibility(View.VISIBLE);
            picker_city.setVisibility(View.GONE);
            picker_district.setVisibility(View.GONE);
        } else if (pos == 1) {
            picker_city.setDisplayedValues(data);
            setNumberPickerDividerColor(picker_city);
            picker_city.setMinValue(0);
            picker_city.setMaxValue(data.length - 1);
            picker_city.setValue(0);
            picker_province.setVisibility(View.GONE);
            picker_city.setVisibility(View.VISIBLE);
            picker_district.setVisibility(View.GONE);
        } else {
            picker_district.setDisplayedValues(data);
            setNumberPickerDividerColor(picker_district);
            picker_district.setMinValue(0);
            picker_district.setMaxValue(data.length - 1);
            picker_district.setValue(0);
            picker_province.setVisibility(View.GONE);
            picker_city.setVisibility(View.GONE);
            picker_district.setVisibility(View.VISIBLE);
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

            trans.setAnimationListener(new Animation.AnimationListener() {

                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    background.setBackgroundColor(Color.BLACK);
                    background.setAlpha(0.5f);
                }
            });
            container.startAnimation(trans);
        }
    }

    /**
     * Dismiss date picker popWindow
     */
    private void dismissPopWin() {
        background.setAlpha(0.0f);
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

        container.startAnimation(trans);
    }

    private void setNumberPickerDividerColor(NumberPicker numberPicker) {
        java.lang.reflect.Field[] pickerFields = NumberPicker.class
                .getDeclaredFields();
        for (java.lang.reflect.Field pf : pickerFields) {
            if (pf.getName().equals("mSelectionDivider")) {
                pf.setAccessible(true);
                try {
                    // 设置分割线的颜色值 透明
                    pf.set(numberPicker,
                            new ColorDrawable(Color.parseColor("#0894ec")));
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    public interface OnCitySelectListener {
        void onConfirmListener(int pos, String id, String addressStr);
    }


}
