package com.example.juandie_hua.mycar.orderpay;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.x;
import org.xutils.common.Callback.CancelledException;
import org.xutils.view.annotation.ViewInject;

import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.juandie_hua.R;
import com.example.juandie_hua.app.BaseActivity;
import com.example.juandie_hua.calender.utils.MonthDateView;
import com.example.juandie_hua.mainactivity.Xutils_Get_Post;
import com.example.juandie_hua.mainactivity.Xutils_Get_Post.XCallBack;
import com.example.juandie_hua.model.SendDate;
import com.example.juandie_hua.utils.StatusBarUtils;
import com.example.juandie_hua.utils.StrUtils;
import com.example.juandie_hua.utils.ViewUtils;
import com.example.juandie_hua.view.CustomDialog;
import com.example.juandie_hua.view.pickView.popwindow.CouplePickerPopWin;
import com.example.juandie_hua.view.pickView.popwindow.SingleValuePopWin;
import com.umeng.analytics.MobclickAgent;

public class SendDateSelect extends BaseActivity {


    @ViewInject(R.id.iv_left)
    ImageView iv_left;
    @ViewInject(R.id.iv_right)
    ImageView iv_right;
    @ViewInject(R.id.date_text)
    TextView tv_date;
    @ViewInject(R.id.monthDateView)
    MonthDateView monthDateView;

    @ViewInject(R.id.day_tesq)
    TextView te_pssq;
    @ViewInject(R.id.day_tejq)
    TextView te_psjq;
    @ViewInject(R.id.day_teyj)
    TextView te_psyj;

    @ViewInject(R.id.day_tepssj)
    TextView te_pssj;
    @ViewInject(R.id.day_tedsts)
    TextView te_dsts;

    int year = 100, mouth = 100, day = 100, hour = 100;
    String[] rq;

    private CustomDialog dialog;
    private SingleValuePopWin popWin;
    private CouplePickerPopWin popWin1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitleContentView(R.layout.activity_send_time_select);
        StatusBarUtils.with(this).setBarColor(R.color.white_fff);

        x.view().inject(this);

        Calendar c = Calendar.getInstance();
        mouth = c.get(Calendar.MONTH) + 1;// 获取当前月份
        day = c.get(Calendar.DAY_OF_MONTH);// 获取当日期
        year = c.get(Calendar.YEAR);//

        setviewhw();
        setview();
//        setviewlisten();
    }

    private void setviewlisten() {

        monthDateView.setDateClick(new MonthDateView.DateClick() {

            @Override
            public void onClickOnDate() {
                if ((year == monthDateView.getmSelYear())
                        && (mouth == monthDateView.getmSelMonth() + 1)
                        && (day > monthDateView.getmSelDay())) {
                    toast("当前日期已过期,请重新选择");
                } else {
                    rq[4] = "0";
                    rq[5] = "0";
                    te_pssj.setText("请选择配送时间");
                }
            }
        });

        te_pssq.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                setpsf(1);
            }
        });
        te_psjq.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                setpsf(2);
            }
        });
        te_psyj.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                setpsf(3);
            }
        });
    }

    private void setview() {

        Bundle riqi = getIntent().getExtras();
        rq = riqi.getStringArray("riqi");
        setpsf(Integer.valueOf(rq[3]).intValue());
        if (!rq[6].equals("0")) {
            if (!rq[5].equals("0")) {
                te_pssj.setText("已选择定时配送:" + rq[6]);
                te_dsts.setVisibility(View.VISIBLE);
            } else
                te_pssj.setText("已选择时间段配送:" + rq[6]);
        }

        List<Integer> list = new ArrayList<Integer>();
        list.add(20160810);
        list.add(20160812);
        list.add(20160815);
        list.add(20160816);
        monthDateView.setTextView(tv_date);
        monthDateView.setDaysHasThingList(list);
        if (rq[7].contains("-")) {
            monthDateView.setSelectYearMonth(
                    Integer.valueOf(rq[7].split("-")[0]),
                    Integer.valueOf(rq[7].split("-")[1]) - 1,
                    Integer.valueOf(rq[7].split("-")[2]));
        }
        monthDateView.setDateClick(new MonthDateView.DateClick() {

            @Override
            public void onClickOnDate() {
            }
        });

        monthDateView.setDateTouch(new MonthDateView.DateTouch() {

            @Override
            public void onTouchOnDate(String touch) {
                if (touch.equals("L")) {
                    monthDateView.onRightClick();
                }
                if (touch.equals("R")) {
                    monthDateView.onLeftClick();
                }
            }
        });
        setOnlistener();
    }

    private void setviewhw() {
        this.getTitleView().setTitleText("选择配送日期");
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int w = dm.widthPixels;
        ViewUtils.setviewhw_lin(monthDateView, w, (int) (w * 450 / 750.0), 0,
                (int) (w * 20 / 750.0), 0, 0);
    }

    public void onSendClick(View view) {
        switch (view.getId()) {
            case R.id.day_tesq:
                setpsf(1);
                break;
            case R.id.day_tejq:
                setpsf(2);
                break;
            case R.id.day_teyj:
                setpsf(3);
                break;
            case R.id.day_tepssj://选择配送时间按钮
                if ((year == monthDateView.getmSelYear())
                        && (mouth == monthDateView.getmSelMonth() + 1)
                        && (day > monthDateView.getmSelDay())) {
                    toast("请选择正确的日期");
                } else {
                    String mon = (monthDateView.getmSelMonth() + 1) + "";
                    String day = monthDateView.getmSelDay() + "";
                    if (mon.length() == 1) {
                        mon = "0" + mon;
                    }
                    if (day.length() == 1) {
                        day = "0" + day;
                    }
                    getDeliveryDate(monthDateView.getmSelYear() + "-" + mon + "-" + day, 1);
                }
                break;
            case R.id.day_tequit://完成按钮
                if (rq[4].equals("0") && rq[5].equals("0")) {
                    if ((year == monthDateView.getmSelYear())
                            && (mouth == monthDateView.getmSelMonth() + 1)
                            && (day > monthDateView.getmSelDay())) {
                        toast("请选择正确的日期");
                    } else {
                        String mon = (monthDateView.getmSelMonth() + 1) + "";
                        String day = monthDateView.getmSelDay() + "";
                        if (mon.length() == 1) {
                            mon = "0" + mon;
                        }
                        if (day.length() == 1) {
                            day = "0" + day;
                        }
                        getDeliveryDate(monthDateView.getmSelYear() + "-" + mon + "-" + day, 1);
                    }
                } else {
                    String shippingfee = "0";
                    if (rq[3].equals("2")) {
                        shippingfee = "30";
                    } else if (rq[3].equals("3")) {
                        shippingfee = "50";
                    } else {
                        shippingfee = "0";
                    }
                    String dsfwf = "0";
                    if (!rq[5].equals("0")) {
                        dsfwf = "1";
                    }
                    if ((year == monthDateView.getmSelYear())
                            && (mouth == monthDateView.getmSelMonth() + 1)
                            && (day > monthDateView.getmSelDay())) {
                        Toast.makeText(SendDateSelect.this, "请选择正确的日期",
                                Toast.LENGTH_SHORT).show();
                    } else
                        yunfei_get(shippingfee, dsfwf);
                }
                break;
        }
    }

    private void setOnlistener() {

        iv_left.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                monthDateView.onLeftClick();
            }
        });

        iv_right.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                monthDateView.onRightClick();
            }
        });

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            finish();
            overridePendingTransition(R.anim.push_right_out,
                    R.anim.push_right_in);
            return false;
        }
        return false;
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
        if (popWin != null && popWin.isShowing()) {
            popWin.dismiss();
        }
    }

    /**
     * 运费选择
     *
     * @param i
     */
    private void setpsf(int i) {
        te_pssq.setSelected(i == 1);
        te_psjq.setSelected(i == 2);
        te_psyj.setSelected(i == 3);
        te_pssq.setText(i == 1 ? "√市区(免费)" : "市区(免费)");
        te_psjq.setText(i == 2 ? "√郊区(+30元)" : "郊区(+30元)");
        te_psyj.setText(i == 3 ? "√远郊(+50元)" : "远郊(+50元)");
        rq[3] = "" + i;
    }

    String sign = "";

    private void yunfei_get(final String shippingfee, final String dsfwf) {
        String url = "";
        if (TextUtils.isEmpty(rq[0])) {
            url = "https://app.juandie.com/api_mobile/flow.php?act=ajax_add_distinct_time_service_fee_fee&"
                    + "shippingfee=" + shippingfee + "&dsfwf=" + dsfwf;
        } else {
            url = "https://app.juandie.com/api_mobile/flow.php?act=ajax_add_distinct_time_service_fee_fee&"
                    + "shippingfee="
                    + shippingfee
                    + "&dsfwf="
                    + dsfwf
                    + "&province="
                    + rq[0]
                    + "&city="
                    + rq[1]
                    + "&district="
                    + rq[2];
        }

        Xutils_Get_Post.getInstance().get(url, new XCallBack() {

            @Override
            public void onResponse(String result) {
                try {
                    JSONObject object = new JSONObject(result);
                    int status = object.getInt("status");
                    if (status == 1) {
                        Message msg = Message.obtain();
                        msg.what = 0x006;
                        Bundle bundle = new Bundle();

                        int day = monthDateView.getmSelDate();
                        String year1 = day / 10000 + "";
                        String year2 = day % 10000 / 100 + "";
                        if (year2.length() == 1) {
                            year2 = "0" + year2;
                        }
                        String year3 = day % 10000 % 100 + "";
                        if (year3.length() == 1) {
                            year3 = "0" + year3;
                        }
                        String year = year1 + "-" + year2 + "-" + year3;
                        rq[7] = year;
                        bundle.putStringArray("riqi", rq);
                        msg.setData(bundle);
                        orderin.handler.sendMessage(msg);

                        finish();
                        overridePendingTransition(R.anim.push_right_out,
                                R.anim.push_right_in);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail(String result) {
            }

            @Override
            public void onCancel(CancelledException cex) {
            }
        });
    }

    /**
     * 获取配送时间
     *
     * @param dat_time
     * @param type
     */
    private void getDeliveryDate(String dat_time, final int type) {
        String url = "https://app.juandie.com/api_mobile/flow.php?act=get_delivery_date_change&delivery_date="
                + dat_time;
        Xutils_Get_Post.getInstance().get(url, new XCallBack() {

            @Override
            public void onResponse(String result) {
                JSONObject jso1;
                try {
                    jso1 = new JSONObject(result);
                    if (jso1.getString("status").equals("1")) {
                        JSONObject jso_ = jso1.getJSONObject("data");

                        List<SendDate.DateValue> delivery_timer_range = JSON.parseArray(jso_.getString("delivery_timer_range"), SendDate.DateValue.class);
                        List<SendDate.DateValue> delivery_timing_hour = JSON.parseArray(jso_.getString("delivery_timing_hour"), SendDate.DateValue.class);
                        List<SendDate.DateValue> delivery_timing_minute = JSON.parseArray(jso_.getString("delivery_timing_minute"), SendDate.DateValue.class);

                        List<String> timer_range = new ArrayList<>();
                        for (SendDate.DateValue value : delivery_timer_range) {
                            timer_range.add(value.getValue());
                        }
                        List<String> timing_hour = new ArrayList<>();
                        for (SendDate.DateValue value : delivery_timing_hour) {
                            timing_hour.add(value.getValue());
                        }
                        List<String> timing_minute = new ArrayList<>();
                        for (SendDate.DateValue value : delivery_timing_minute) {
                            timing_minute.add(value.getValue());
                        }

                        if (type == 1) {
                            if (!StrUtils.listIsEmpty(delivery_timer_range)) {
                                selectDate(timer_range, timing_hour, timing_minute);
                            } else
                                Toast.makeText(SendDateSelect.this,
                                        "当前时间不支持配送，请选择其他日期", Toast.LENGTH_SHORT)
                                        .show();
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail(String result) {
            }

            @Override
            public void onCancel(CancelledException cex) {
            }
        });
    }

    private void selectDate(final List<String> list, final List<String> hourList, final List<String> minList) {
        if (popWin == null) {

            popWin = new SingleValuePopWin.Builder(SendDateSelect.this, new SingleValuePopWin.OnDatePickedListener() {
                @Override
                public void onDatePickCompleted(int pos) {
                    String value = list.get(pos);
                    if (value.equals("定时")) {//定时

                        if (!StrUtils.listIsEmpty(hourList)) {
                            showSelectTime(hourList, minList);
                        } else {
                            toast("当前时间不支持定时配送，请选择其他日期");
                        }
                    } else {
                        te_pssj.setText("已选择配送时间段：" + value);
                        te_dsts.setVisibility(View.GONE);

                        if (value.equals("全天配送")) {
                            String mon = (monthDateView.getmSelMonth() + 1) + "";
                            String day = monthDateView.getmSelDay() + "";
                            if (mon.length() == 1) {
                                mon = "0" + mon;
                            }
                            xutils_quantianps(monthDateView.getmSelYear() + "-" + mon + "-" + day);
                        }
                    }
                }
            }).setValueList(list).build();
        }

        popWin.showPopWin(SendDateSelect.this);
    }

    private void showSelectTime(final List<String> hourList, final List<String> minList) {
        if (popWin1 == null) {
            popWin1 = new CouplePickerPopWin.Builder(SendDateSelect.this, new CouplePickerPopWin.OnTimePickedListener() {
                @Override
                public void onTimePickerComplete(int hour, int minute) {

                    te_pssj.setText("已选择定时配送: " + hourList.get(hour) + minList.get(minute));
                    te_dsts.setVisibility(View.VISIBLE);

                }
            }).hourList(hourList).minuteList(minList).build();
        }
        popWin1.showPopWin(SendDateSelect.this);
    }

    private void xutils_quantianps(String dat_time) {
        String url = "https://app.juandie.com/api_mobile/flow.php?act=get_delivery_timer_range_change&date="
                + dat_time;
        Xutils_Get_Post.getInstance().get(url, new XCallBack() {

            @Override
            public void onResponse(String result) {
                JSONObject jso1;
                try {
                    jso1 = new JSONObject(result);
                    if (jso1.getString("status").equals("1")) {
                        JSONObject data = jso1.getJSONObject("data");
                        if (!TextUtils.isEmpty(data
                                .getString("delivery_timer_range_tip"))) {
                            showDialog(data
                                    .getString("delivery_timer_range_tip"));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail(String result) {
            }

            @Override
            public void onCancel(CancelledException cex) {
            }
        });
    }

    public void showDialog(String txt) {

        if (dialog == null) {
            dialog = new CustomDialog.Builder(SendDateSelect.this)
                    .setTitle(txt)
                    .setPositiveButton(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    })
                    .setNegativeBtnShow(false)
                    .create();
        }

        dialog.show();

//		DisplayMetrics dm = getResources().getDisplayMetrics();
//		int w_screen = dm.widthPixels;
//
//		// 1.创建一个Dialog对象，如果是AlertDialog对象的话，弹出的自定义布局四周会有一些阴影，效果不好
//		mDialog = new Dialog(this);
//		// 去除标题栏
//		mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//		// 2.填充布局
//		LayoutInflater inflater = LayoutInflater.from(this);
//		View dialogView = inflater.inflate(R.layout.pop_qtps, null);
//
//		TextView tetitl = (TextView) dialogView
//				.findViewById(R.id.comment_quitte);
//		TextView textv_ok = (TextView) dialogView
//				.findViewById(R.id.gotocom_quitok);
//
//		setviewhw_lin(tetitl, (int) (w_screen * 500 / 750.0),
//				(int) (w_screen * 200 / 750), 0, 0, 0,
//				(int) (w_screen * 20 / 750));
//
//		tetitl.setPadding((int) (w_screen * 20 / 750.0), 0,
//				(int) (w_screen * 20 / 750.0), 0);
//		setviewhw_lin(textv_ok, (int) (w_screen * 500 / 750.0),
//				(int) (w_screen * 60 / 750.0), 0, 0, 0, 0);
//
//		tetitl.setText(txt);
//
//		// 将自定义布局设置进去
//		mDialog.setContentView(dialogView);
//		// 3.设置指定的宽高,如果不设置的话，弹出的对话框可能不会显示全整个布局，当然在布局中写死宽高也可以
//		WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
//		Window window = mDialog.getWindow();
//		lp.copyFrom(window.getAttributes());
//		lp.width = (int) (w_screen * 500 / 750.0);
//		lp.height = (int) (w_screen * 300 / 750.0);
//		// 注意要在Dialog show之后，再将宽高属性设置进去，才有效果
//		mDialog.show();
//		window.setAttributes(lp);
//
//		mDialog.getWindow().setBackgroundDrawableResource(
//				android.R.color.transparent);
//		// 设置点击其它地方不让消失弹窗
//		mDialog.setCancelable(false);
//		textv_ok.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				mDialog.dismiss();
//			}
//		});
//		mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
//
//			@Override
//			public void onDismiss(DialogInterface dialog) {
//				mDialog.dismiss();
//				WindowManager.LayoutParams lp = getWindow().getAttributes();
//				lp.alpha = 1f;
//				getWindow().setAttributes(lp);
//
//			}
//		});
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
