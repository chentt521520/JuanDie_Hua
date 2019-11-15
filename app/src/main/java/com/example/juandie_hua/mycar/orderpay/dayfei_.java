package com.example.juandie_hua.mycar.orderpay;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.juandie_hua.R;
import com.example.juandie_hua.app.BaseActivity;
import com.example.juandie_hua.app.HttpUrl;
import com.example.juandie_hua.calender.utils.MonthDateView;
import com.example.juandie_hua.mainactivity.Xutils_Get_Post;
import com.example.juandie_hua.mainactivity.Xutils_Get_Post.XCallBack;
import com.example.juandie_hua.mycar.QNumberPicker;
import com.example.juandie_hua.utils.StatusBarUtils;
import com.example.juandie_hua.utils.ViewUtils;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback.CancelledException;
import org.xutils.common.util.DensityUtil;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class dayfei_ extends BaseActivity {

    @ViewInject(R.id.iv_left)
    ImageView iv_left;
    @ViewInject(R.id.iv_right)
    ImageView iv_right;
    @ViewInject(R.id.date_text)
    TextView tv_date;
    @ViewInject(R.id.monthDateView)
    MonthDateView monthDateView;

    @ViewInject(R.id.day_tepsf)
    TextView te_psf;
    @ViewInject(R.id.day_linpsh)
    LinearLayout lin_psf;
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

    @ViewInject(R.id.day_tequit)
    TextView te_okq;

	PopupWindow window, window1;
	TextView te_quxiao, te_ok;
	NumberPicker np1;
	int minPrice = 25, maxPrice = 75;
	int year = 100, mouth = 100, day = 100, hour = 100;
	String[] rq;


    List<String> list_shiduan = new ArrayList<>();
    List<String> list_dingshi1 = new ArrayList<>();
    List<String> list_dingshi1_ = new ArrayList<>();
    List<String> list_dingshi2 = new ArrayList<>();
    List<String> list_dingshi2_ = new ArrayList<>();
    private Dialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setTitleContentView(R.layout.dayfei_);
        StatusBarUtils.with(this).setBarColor(R.color.white_fff);

        x.view().inject(this);

        Calendar c = Calendar.getInstance();
        mouth = c.get(Calendar.MONTH) + 1;// 获取当前月份
        day = c.get(Calendar.DAY_OF_MONTH);// 获取当日期
        year = c.get(Calendar.YEAR);//

        setviewhw();
        setview();
        setviewlisten();
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

        te_pssj.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

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
                    xutils_getdat_time(monthDateView.getmSelYear() + "-" + mon + "-" + day, 1);
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

        te_okq.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
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
                        xutils_getdat_time(monthDateView.getmSelYear() + "-"
                                + mon + "-" + day, 1);
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
                        toast("请选择正确的日期");
                    } else
                        yunfei_get(shippingfee, dsfwf);
                }
            }
        });
    }

    private void setview() {
        this.getTitleView().setTitleText("选择配送日期");
        Bundle riqi = getIntent().getExtras();
        rq = riqi.getStringArray("riqi");
        setpsf(Integer.valueOf(rq[3]));
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
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int w = dm.widthPixels;
        ViewUtils.setviewhw_lin(monthDateView, w, (int) (w * 450 / 750.0), 0,
                (int) (w * 20 / 750.0), 0, 0);

        ViewUtils.setviewhw_lin(te_psf, w, (int) (w * 50 / 750.0),
                (int) (w * 25 / 750.0), (int) (w * 20 / 750.0), 0, 0);
        ViewUtils.setviewhw_lin(lin_psf, w, (int) (w * 50 / 375.0), 0, 0, 0, 0);
        ViewUtils.setviewhw_lin(te_pssq, (int) (w * 100 / 375.0), (int) (w * 30 / 375.0),
                (int) (w * 14 / 375.0), (int) (w * 10 / 375.0),
                (int) (w * 14 / 375.0), 0);
        ViewUtils.setviewhw_lin(te_psjq, (int) (w * 100 / 375.0), (int) (w * 30 / 375.0),
                0, (int) (w * 10 / 375.0), (int) (w * 14 / 375.0), 0);
        ViewUtils.setviewhw_lin(te_psyj, (int) (w * 100 / 375.0), (int) (w * 30 / 375.0),
                0, (int) (w * 10 / 375.0), (int) (w * 14 / 375.0), 0);

        ViewUtils.setviewhw_lin(te_pssj, LayoutParams.WRAP_CONTENT,
                (int) (w * 50 / 750.0), (int) (w * 25 / 750.0),
                (int) (w * 20 / 750.0), (int) (w * 25 / 750.0), 0);
        ViewUtils.setviewhw_lin(te_dsts, LayoutParams.WRAP_CONTENT,
                (int) (w * 50 / 750.0), 0, (int) (w * 20 / 750.0),
                (int) (w * 24 / 750.0), 0);

        ViewUtils.setviewhw_lin(te_okq, w, (int) (w * 88 / 750.0), 0, 0, 0, 0);
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

	@SuppressWarnings("deprecation")
	public void shiduan_dialog(final String[] data) {
		View view = LayoutInflater.from(dayfei_.this).inflate(R.layout.numberpic_layout, null);

		te_ok = (TextView) view.findViewById(R.id.numberpic_teok);
		te_quxiao = (TextView) view.findViewById(R.id.numberpic_tequxiao);

		np1 = (NumberPicker) view.findViewById(R.id.numberpic);

		np1.setDisplayedValues(data);
		np1.setMinValue(0);
		np1.setMaxValue(data.length - 1);
		np1.setOnValueChangedListener(new OnValueChangeListener() {

            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                minPrice = newVal;
            }
        });

		// 设置弹出框的宽高
		window = new PopupWindow(view, DensityUtil.getScreenWidth(), -2);

		ColorDrawable cd = new ColorDrawable(0x000000);// 设置背景变暗
		window.setBackgroundDrawable(cd);
		WindowManager.LayoutParams lp = getWindow().getAttributes();
		lp.alpha = 0.6f;
		getWindow().setAttributes(lp);
		// 设置背景
		window.setBackgroundDrawable(getResources()
				.getDrawable(R.drawable.bsbj));
		window.setClippingEnabled(false);
		// 设置透明度
		window.getBackground().setAlpha(200);
		// 设置动画,从底部出来
		window.setAnimationStyle(android.R.style.Animation_Dialog);
		// 点击空白区域消失
		window.setOutsideTouchable(true);

		// 设置焦点
		window.setFocusable(true);
		// 可以被触摸
		window.setTouchable(true);
		// 设置软键盘
		// window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
		// 显示的位置,从底部显示
		// 设置popwindow显示位置
//		if (android.os.Build.VERSION.SDK_INT >= 24) {
//			int[] a = new int[2];
//			re_top.getLocationInWindow(a);
//			window.showAtLocation(getWindow().getDecorView(),
//					Gravity.NO_GRAVITY, 0, height
//							- (int) (w_screen * 440 / 750));
//			window.update();
//		} else {
//			window.showAtLocation(re_top, Gravity.BOTTOM, 0, 0);
//			window.update();
//		}
        window.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
		window.update();
		window.setOnDismissListener(new PopupWindow.OnDismissListener() {// pop消失

			@Override
			public void onDismiss() {
				WindowManager.LayoutParams lp = getWindow().getAttributes();
				lp.alpha = 1f;
				getWindow().setAttributes(lp);
			}
		});

        te_quxiao.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });
        te_ok.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                int pos = np1.getValue();
                window.dismiss();
                if (!data[pos].equals("定时")) {
                    te_pssj.setText("已选择配送时间段:" + data[pos]);
                    te_dsts.setVisibility(View.GONE);
                    rq[4] = "111";
                    rq[5] = "0";
                    rq[6] = data[pos];
                    if (data[pos].equals("全天配送")) {
                        String mon = (monthDateView.getmSelMonth() + 1) + "";
                        String day = monthDateView.getmSelDay() + "";
                        if (mon.length() == 1) {
                            mon = "0" + mon;
                        }
                        xutils_quantianps(monthDateView.getmSelYear() + "-"
                                + mon + "-" + day);
                    }

                } else {
                    // dingshi();
                    if (list_dingshi1.size() >= 1) {
                        String[] ds1 = new String[list_dingshi1.size()];
                        String[] ds2 = new String[list_dingshi2.size()];
                        for (int i = 0; i < list_dingshi1.size(); i++) {
                            ds1[i] = list_dingshi1.get(i);
                        }
                        for (int i = 0; i < list_dingshi2.size(); i++) {
                            ds2[i] = list_dingshi2.get(i);
                        }
                        dingshi_dialog(ds1, ds2);
                    } else
                        toast("当前时间不支持定时配送，请选择其他日期");
                }

            }
        });
    }

    int[] dsd = new int[2];// 记录定时选择的小时和分钟数

	@SuppressWarnings("deprecation")
	public void dingshi_dialog(final String[] data, final String[] data1) {
		View view = LayoutInflater.from(dayfei_.this).inflate(R.layout.dingshi_dialog, null);

		TextView te_ok, te_quxiao;
		final NumberPicker np1;
		final NumberPicker np2;
		te_ok = (TextView) view.findViewById(R.id.dingshi_teok);
		te_quxiao = (TextView) view.findViewById(R.id.dingshi_tequxiao);

        np1 = (QNumberPicker) view.findViewById(R.id.dingshi_np1);
        np2 = (QNumberPicker) view.findViewById(R.id.dingshi_np2);

        np1.setDisplayedValues(data);
        np1.setMinValue(0);
        np1.setMaxValue(data.length - 1);
        np1.setOnValueChangedListener(new OnValueChangeListener() {

            @Override
            public void onValueChange(NumberPicker picker, int oldVal,
                                      int newVal) {
                dsd[0] = newVal;
            }

        });
        np2.setDisplayedValues(data1);
        np2.setMinValue(0);
        np2.setMaxValue(data1.length - 1);
        np2.setOnValueChangedListener(new OnValueChangeListener() {

			@Override
			public void onValueChange(NumberPicker picker, int oldVal,
					int newVal) {
				dsd[1] = newVal;
			}
		});
		// 设置弹出框的宽高
        window1 = new PopupWindow(view, DensityUtil.getScreenWidth(), -2);

		ColorDrawable cd = new ColorDrawable(0x000000);// 设置背景变暗
		window1.setBackgroundDrawable(cd);
		WindowManager.LayoutParams lp = getWindow().getAttributes();
		lp.alpha = 0.6f;
		getWindow().setAttributes(lp);
		// 设置背景
		window1.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.bsbj));
		window1.setClippingEnabled(false);
		// 设置透明度
		window1.getBackground().setAlpha(200);
		// 设置动画,从底部出来
		window1.setAnimationStyle(android.R.style.Animation_Dialog);
		// 点击空白区域消失
		window1.setOutsideTouchable(true);

		// 设置焦点
		window1.setFocusable(true);
		// 可以被触摸
		window1.setTouchable(true);
		// 设置软键盘
		// window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
		// 显示的位置,从底部显示
		// 设置popwindow显示位置
        window1.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
//		if (android.os.Build.VERSION.SDK_INT >= 24) {
//			int[] a = new int[2];
//			re_top.getLocationInWindow(a);
//			window1.showAtLocation(getWindow().getDecorView(),
//					Gravity.NO_GRAVITY, 0, height
//							- (int) (w_screen * 440 / 750.0));
//			window1.update();
//		} else {
//			window1.showAtLocation(re_top, Gravity.BOTTOM, 0, 0);
//			window1.update();
//		}
		window1.update();
		window1.setOnDismissListener(new PopupWindow.OnDismissListener() {// pop消失

			@Override
			public void onDismiss() {
				WindowManager.LayoutParams lp = getWindow().getAttributes();
				lp.alpha = 1f;
				getWindow().setAttributes(lp);
			}
		});

        te_quxiao.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                window1.dismiss();
            }
        });
        te_ok.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                int pos1 = np1.getValue();
                int pos2 = np2.getValue();

                te_pssj.setText("已选择定时配送: " + data[pos1] + data1[pos2]);
                te_dsts.setVisibility(View.VISIBLE);

                rq[5] = "111";
                rq[4] = "0";
                rq[6] = list_dingshi1_.get(pos1) + ":"
                        + list_dingshi2_.get(pos2);
                rq[8] = data[pos1] + data1[pos2];
                window1.dismiss();
            }
        });

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
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
     * 获取配送时间范围
     * @param dat_time
     * @param type
     */
    private void xutils_getdat_time(String dat_time, final int type) {

        HashMap<String, String> map=new HashMap<>();
        map.put("act",HttpUrl.get_delivery_date_change);
        map.put("delivery_date",dat_time);
        String url = HttpUrl.getUrl(HttpUrl.flow, map);

//        String url = "https://app.juandie.com/api_mobile/flow.php?act=get_delivery_date_change&delivery_date=" + dat_time;
        Xutils_Get_Post.getInstance().get(url, new XCallBack() {

            @Override
            public void onResponse(String result) {
                JSONObject jso1;
                try {
                    jso1 = new JSONObject(result);
                    if (jso1.getString("status").equals("1")) {
                        JSONObject jso_ = jso1.getJSONObject("data");
                        JSONArray jsa_shiduan = jso_
                                .getJSONArray("delivery_timer_range");
                        JSONArray jsa_dingshi1 = jso_
                                .getJSONArray("delivery_timing_hour");
                        JSONArray jsa_dingshi2 = jso_
                                .getJSONArray("delivery_timing_minute");

                        list_shiduan.removeAll(list_shiduan);
                        list_dingshi1.removeAll(list_dingshi1);
                        list_dingshi1_.removeAll(list_dingshi1_);
                        list_dingshi2.removeAll(list_dingshi2);
                        list_dingshi2_.removeAll(list_dingshi2_);

                        for (int i = 0; i < jsa_shiduan.length(); i++) {
                            JSONObject jso_sd = jsa_shiduan.getJSONObject(i);
                            list_shiduan.add(jso_sd.getString("value"));
                        }
                        for (int i = 0; i < jsa_dingshi1.length(); i++) {
                            JSONObject jso_sd = jsa_dingshi1.getJSONObject(i);
                            list_dingshi1.add(jso_sd.getString("value"));
                            list_dingshi1_.add(jso_sd.getString("key"));
                        }
                        for (int i = 0; i < jsa_dingshi2.length(); i++) {
                            JSONObject jso_sd = jsa_dingshi2.getJSONObject(i);
                            list_dingshi2.add(jso_sd.getString("value"));
                            list_dingshi2_.add(jso_sd.getString("key"));
                        }
                        if (type == 1) {
                            if (list_shiduan.size() >= 1) {
                                String[] sd = new String[list_shiduan.size()];
                                for (int i = 0; i < list_shiduan.size(); i++) {
                                    sd[i] = list_shiduan.get(i);
                                }
                                shiduan_dialog(sd);
                            } else
                                toast("当前时间不支持定时配送，请选择其他日期");
                        }
                    }

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail(String result) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onCancel(CancelledException cex) {
                // TODO Auto-generated method stub

            }
        });
    }

    /**
     * 获取定时区间范围
     * @param dat_time
     */
    private void xutils_quantianps(String dat_time) {
        HashMap<String, String> map=new HashMap<>();
        map.put("act",HttpUrl.get_delivery_timer_range_change);
        map.put("date",dat_time);
        String url = HttpUrl.getUrl(HttpUrl.flow, map);
//        String url = "https://app.juandie.com/api_mobile/flow.php?act=get_delivery_timer_range_change&date=" + dat_time;
        Xutils_Get_Post.getInstance().get(url, new XCallBack() {

            @Override
            public void onResponse(String result) {
                JSONObject jso1;
                try {
                    jso1 = new JSONObject(result);
                    if (jso1.getString("status").equals("1")) {
                        JSONObject data = jso1.getJSONObject("data");
                        if (!TextUtils.isEmpty(data.getString("delivery_timer_range_tip"))) {
                            showDialog(data.getString("delivery_timer_range_tip"));
                        }
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail(String result) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onCancel(CancelledException cex) {
                // TODO Auto-generated method stub

            }
        });
    }

    public void showDialog(String txt) {

        DisplayMetrics dm = getResources().getDisplayMetrics();
        int w_screen = dm.widthPixels;

        // 1.创建一个Dialog对象，如果是AlertDialog对象的话，弹出的自定义布局四周会有一些阴影，效果不好
        mDialog = new Dialog(this);
        // 去除标题栏
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 2.填充布局
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.pop_qtps, null);

        TextView tetitl = (TextView) dialogView
                .findViewById(R.id.comment_quitte);
        TextView textv_ok = (TextView) dialogView
                .findViewById(R.id.gotocom_quitok);

        ViewUtils.setviewhw_lin(tetitl, (int) (w_screen * 500 / 750.0),
                (int) (w_screen * 200 / 750), 0, 0, 0,
                (int) (w_screen * 20 / 750));

        tetitl.setPadding((int) (w_screen * 20 / 750.0), 0,
                (int) (w_screen * 20 / 750.0), 0);
        ViewUtils.setviewhw_lin(textv_ok, (int) (w_screen * 500 / 750.0),
                (int) (w_screen * 60 / 750.0), 0, 0, 0, 0);

        tetitl.setText(txt);

        // 将自定义布局设置进去
        mDialog.setContentView(dialogView);
        // 3.设置指定的宽高,如果不设置的话，弹出的对话框可能不会显示全整个布局，当然在布局中写死宽高也可以
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = mDialog.getWindow();
        lp.copyFrom(window.getAttributes());
        lp.width = (int) (w_screen * 500 / 750.0);
        lp.height = (int) (w_screen * 300 / 750.0);
        // 注意要在Dialog show之后，再将宽高属性设置进去，才有效果
        mDialog.show();
        window.setAttributes(lp);

        mDialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent);
        // 设置点击其它地方不让消失弹窗
        mDialog.setCancelable(false);
        textv_ok.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {

            @Override
            public void onDismiss(DialogInterface dialog) {
                mDialog.dismiss();
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);

            }
        });
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
//        if (popWindow != null) {
//            popWindow.dismiss();
//        }
//        if (popWindow1 != null) {
//            popWindow1.dismiss();
//        }
    }
}
