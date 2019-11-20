package com.example.juandie_hua.mycar.orderpay;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.x;
import org.xutils.common.Callback.CancelledException;
import org.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.juandie_hua.R;
import com.example.juandie_hua.app.BaseActivity;
import com.example.juandie_hua.mainactivity.Xutils_Get_Post;
import com.example.juandie_hua.mainactivity.Xutils_Get_Post.XCallBack;
import com.example.juandie_hua.mycar.contact.txl_choose;
import com.example.juandie_hua.mycar.shouhuodizi.area_info;
import com.example.juandie_hua.utils.StatusBarUtils;
import com.umeng.analytics.MobclickAgent;

public class addshdz extends BaseActivity {
    @ViewInject(R.id.addshdz_retop)
    RelativeLayout re_top;
    @ViewInject(R.id.addshdz_imreturn)
    ImageView im_return;

    @ViewInject(R.id.addshdz_resdz)
    RelativeLayout re_dz;
    @ViewInject(R.id.addshdz_reshrnamex)
    RelativeLayout re_shrname;
    @ViewInject(R.id.addshdz_teshrx)
    TextView te_shrname;
    @ViewInject(R.id.addshdz_edshrnamex)
    EditText ed_shrname;

    @ViewInject(R.id.addshdz_reshrphox)
    RelativeLayout re_shrpho;
    @ViewInject(R.id.addshdz_teshrphox)
    TextView te_shrpho;
    @ViewInject(R.id.addshdz_edshrphx)
    EditText ed_shrpho;

    @ViewInject(R.id.addshdz_lintxl)
    LinearLayout lin_txl;
    @ViewInject(R.id.addshdz_imtxl)
    ImageView im_txl;
    @ViewInject(R.id.addshdz_tetxl)
    TextView te_txl;

    @ViewInject(R.id.addshdz_reshrpho)
    RelativeLayout re_shrpho1;
    @ViewInject(R.id.addshdz_reshrname)
    RelativeLayout re_shrname1;

    @ViewInject(R.id.addshdz_reshrdz)
    RelativeLayout re_shrdz;
    @ViewInject(R.id.addshdz_teshrdz)
    TextView te_shrdz;
    @ViewInject(R.id.addshdz_teshrdz1)
    TextView te_shrdz1;

    @ViewInject(R.id.addshdz_reshrxxdz)
    RelativeLayout re_shrxxdz;
    @ViewInject(R.id.addshdz_teshrxxdz)
    TextView te_shrxxdz;
    @ViewInject(R.id.addshdz_edshrxxdz)
    EditText ed_shrdz1;

    @ViewInject(R.id.addshdz_redhrname)
    RelativeLayout re_dhrname;
    @ViewInject(R.id.addshdz_tedhr)
    TextView te_dhrname;
    @ViewInject(R.id.addshdz_eddhrname)
    EditText ed_dhrname;

    @ViewInject(R.id.addshdz_redhrpho)
    RelativeLayout re_dhrpho;
    @ViewInject(R.id.addshdz_tedhrpho)
    TextView te_dhrpho;
    @ViewInject(R.id.addshdz_eddhrph)
    EditText ed_dhrpho;

    @ViewInject(R.id.addshdz_teok)
    TextView te_ok;

    PopupWindow window;

    List<area_info> list_1, list_2, list_3;
    String ssq = "";
    NumberPicker npic1, npic2, npic3;
    LinearLayout lin_dizi;
    public static MyHandler handler;
    String PHONE_PATTERN = "^(1)\\d{10}$";
    String address_id = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addshdz);
        StatusBarUtils.with(this).setBarColor(R.color.white_fff);

        handler = new MyHandler(addshdz.this);
        x.view().inject(this);
        Http_Postarea("0", "0", 1);

        setviwhw();
        setviewlisten();

    }


    private boolean isSoftShowing() {
        //获取当前屏幕内容的高度
        int screenHeight = getWindow().getDecorView().getHeight();
        //获取View可见区域的bottom
        Rect rect = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);

        return screenHeight - rect.bottom != 0;
    }

    private void setviewlisten() {
        im_return.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.push_right_out,
                        R.anim.push_right_in);

            }
        });
        re_shrdz.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (list_1.size() >= 1) {
                    if (isSoftShowing()) {
                        InputMethodManager im = ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE));
                        im.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                    adddz_dialog();
                } else
                    Http_Postarea("0", "0", 1);
            }
        });

        lin_txl.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // Intent i = new Intent();
                // i.setClass(addshdz.this, txl_choose.class);
                // Bundle b = new Bundle();
                // b.putString("type", "1");// 1表示添加地址。
                // i.putExtras(b);
                // startActivity(i);
                // overridePendingTransition(R.anim.push_left_in,
                // R.anim.push_left_out);
                getcon();
            }
        });

        te_ok.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(ed_shrname.getText().toString())) {
                    toast("请填写收货人姓名");
                    return;
                }

                if (!isMatchered(PHONE_PATTERN, ed_shrpho.getText().toString())) {
                    toast("请填写正确的收货人电话");
                    return;
                }
                if (TextUtils.isEmpty(te_shrdz1.getText().toString())) {
                    toast("请选择地址");
                    return;
                }
                if (TextUtils.isEmpty(ed_dhrname.getText().toString())) {
                    toast("请填写订货人姓名");
                    return;
                }
                if (!isMatchered(PHONE_PATTERN, ed_dhrpho.getText().toString())) {
                    toast("请填写正确的订货人电话");
                    return;
                }

                Http_Postsave(ed_shrname.getText().toString(),
                        ed_shrpho.getText().toString(),
                        list_1.get(npic1.getValue()).getId(),
                        list_2.get(npic2.getValue()).getId(),
                        list_3.get(npic3.getValue()).getId(),
                        ed_shrdz1.getText().toString(),
                        ed_dhrname.getText().toString(),
                        ed_dhrpho.getText().toString(),
                        address_id);
            }
        });
    }

    public void getcon() {
        // 版本号的判断
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            Intent i = new Intent();
            i.setClass(addshdz.this, txl_choose.class);
            Bundle b = new Bundle();
            b.putString("type", "1");// 1表示添加地址。
            i.putExtras(b);
            startActivity(i);
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        } else {
            // 权限有三种状态（1、允许 2、提示 3、禁止）
            int permission = ActivityCompat
                    .checkSelfPermission(getApplication(),
                            android.Manifest.permission.READ_CONTACTS);
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 如果设置中权限是禁止的咋返回false;如果是提示咋返回的是true
                boolean is = ActivityCompat
                        .shouldShowRequestPermissionRationale(this,
                                android.Manifest.permission.READ_CONTACTS);
                if (is) {
                    ActivityCompat
                            .requestPermissions(
                                    this,
                                    new String[]{android.Manifest.permission.READ_CONTACTS},
                                    0x01);
                } else {
                    Toast.makeText(addshdz.this, "请开启通讯录权限", Toast.LENGTH_SHORT)
                            .show();
                }

            } else {
                Intent i = new Intent();
                i.setClass(addshdz.this, txl_choose.class);
                Bundle b = new Bundle();
                b.putString("type", "1");// 1表示添加地址。
                i.putExtras(b);
                startActivity(i);
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
            }
        }
    }

    private void setviwhw() {
        lin_txl.setVisibility(View.VISIBLE);
        Bundle bundle = getIntent().getExtras();
        address_id = bundle.getStringArray("address")[0];
        String[] address = bundle.getStringArray("address");
        if (!address_id.equals("0")) {
            this.getTitleView().setTitleText("修改收货地址");
            ed_shrname.setText(address[1]);
            ed_shrpho.setText(address[2]);
            ed_shrdz1.setText(address[3]);
            ed_dhrname.setText(address[4]);
            ed_dhrpho.setText(address[5]);
        } else {
            this.getTitleView().setTitleText("添加收货地址");
        }

        re_shrname1.setVisibility(View.GONE);
        re_shrpho1.setVisibility(View.GONE);

        list_1 = new ArrayList<area_info>();
        list_2 = new ArrayList<area_info>();
        list_3 = new ArrayList<area_info>();

        DisplayMetrics dm = getResources().getDisplayMetrics();
        int w = dm.widthPixels;
        re_top.setBackgroundResource(R.drawable.biankuangxnew);

        setviewhw_re(im_return, (int) (w * 49 / 450.0), (int) (w * 25 / 450.0),
                0, (int) (w * 15 / 450.0), 0, 0);
        im_return.setPadding((int) (w * 12 / 450.0), 0, (int) (w * 12 / 450.0),
                0);

        setviewhw_lin(re_dz, w, (int) (w * 80 / 375.0), 0, 0, 0, 0);
        setviewhw_re(re_shrname, w - (int) (w * 80 / 375.0),
                (int) (w * 40 / 375.0), (int) (w * 14 / 375.0), 0,
                (int) (w * 80 / 375.0), 0);
        setviewhw_re(re_shrpho, w - (int) (w * 80 / 375.0),
                (int) (w * 40 / 375.0), (int) (w * 14 / 375.0),
                (int) (w * 40 / 375.0), (int) (w * 80 / 375.0), 0);
        setviewhw_re(lin_txl, (int) (w * 80 / 375.0), (int) (w * 80 / 375.0),
                (int) (w * 295 / 375.0), 0, 0, 0);
        setviewhw_lin(im_txl, (int) (w * 30 / 375.0), (int) (w * 40 / 375.0),
                (int) (w * 25 / 375.0), (int) (w * 10 / 375.0), 0, 0);
        setviewhw_lin(te_txl, (int) (w * 80 / 375.0), (int) (w * 28 / 375.0),
                0, 0, 0, 0);

        setviewhw_lin(re_shrdz, w, (int) (w * 40 / 375.0),
                (int) (w * 14 / 375.0), 0, (int) (w * 12 / 375.0), 0);
        setviewhw_lin(re_shrxxdz, w, (int) (w * 40 / 375.0),
                (int) (w * 14 / 375.0), 0, (int) (w * 12 / 375.0), 0);
        setviewhw_lin(re_dhrname, w, (int) (w * 40 / 375.0),
                (int) (w * 14 / 375.0), 0, (int) (w * 12 / 375.0), 0);
        setviewhw_lin(re_dhrpho, w, (int) (w * 40 / 375.0),
                (int) (w * 14 / 375.0), 0, (int) (w * 12 / 375.0), 0);

        setviewhw_lin(te_ok, (int) (w * 345 / 375.0), (int) (w * 44 / 375.0),
                (int) (w * 14 / 375.0), (int) (w * 14 / 375.0), 0, 0);

    }

    private void setviewhw_lin(View v, int width, int height, int left,
                               int top, int right, int bottom) {
        LayoutParams lp = new LayoutParams(width, height);
        lp.setMargins(left, top, right, bottom);
        v.setLayoutParams(lp);
    }

    private void setviewhw_re(View v, int width, int height, int left, int top,
                              int right, int bottom) {
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(width,
                height);
        lp.setMargins(left, top, right, bottom);
        v.setLayoutParams(lp);
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
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

    @SuppressWarnings("deprecation")
    public void adddz_dialog() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int w_screen = dm.widthPixels;
        int height = dm.heightPixels;
        View view = LayoutInflater.from(addshdz.this).inflate(
                R.layout.adddz_dialog, null);
        TextView te_ok, te_qx;

        LinearLayout linbot = (LinearLayout) view
                .findViewById(R.id.adddz_linxz);
        int w = dm.widthPixels;
        setviewhw_lin(linbot, w, (int) (w * 50 / 375.0), 0, 0, 0, 0);

        npic1 = (NumberPicker) view.findViewById(R.id.adddzpic1);
        npic2 = (NumberPicker) view.findViewById(R.id.adddzpic2);
        npic3 = (NumberPicker) view.findViewById(R.id.adddzpic3);
        lin_dizi = (LinearLayout) view.findViewById(R.id.adddz_linxz);

        te_ok = (TextView) view.findViewById(R.id.adddz_teok);
        te_qx = (TextView) view.findViewById(R.id.adddz_tequxiao);

        List<String> list = new ArrayList<String>();
        for (int i = 0; i < list_1.size(); i++) {
            list.add(list_1.get(i).getName());
        }
        String[] data_ = new String[list.size()];
        for (int j = 0; j < list.size(); j++) {
            data_[j] = list.get(j);
        }
        list_2.removeAll(list_2);
        list_3.removeAll(list_3);

        npic1.setDisplayedValues(data_);
        npic1.setMinValue(0);
        npic1.setValue(0);
        npic1.setMaxValue(data_.length - 1);
        window = new PopupWindow(view, w_screen,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT);

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
        if (Build.VERSION.SDK_INT >= 24) {
            int[] a = new int[2];
            re_top.getLocationInWindow(a);
            window.showAtLocation(getWindow().getDecorView(),
                    Gravity.NO_GRAVITY, 0, height
                            - (int) (w_screen * 570 / 750));
            window.update();
        } else {
            window.showAtLocation(re_top, Gravity.BOTTOM, 0, 0);
            window.update();
        }
        window.update();
        window.setOnDismissListener(new OnDismissListener() {// pop消失

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });

        te_ok.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (npic1.isShown()) {
                    npic1.setVisibility(View.INVISIBLE);
                    lin_dizi.setVisibility(View.INVISIBLE);
                    set_data(npic2, 1);
                } else {
                    if (npic2.isShown()) {
                        npic2.setVisibility(View.INVISIBLE);
                        lin_dizi.setVisibility(View.INVISIBLE);
                        set_data(npic3, 2);
                    } else {
                        if (list_3.size() >= 1) {
                            Toast.makeText(addshdz.this, "完成选择",
                                    Toast.LENGTH_SHORT).show();
                            window.dismiss();
                            String str = list_1.get(npic1.getValue()).getName()
                                    + " "
                                    + list_2.get(npic2.getValue()).getName()
                                    + " "
                                    + list_3.get(npic3.getValue()).getName();
                            ssq = str;
                            te_shrdz1.setText(str);
                        }
                    }
                }

            }
        });
        te_qx.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });

        npic1.setOnValueChangedListener(new OnValueChangeListener() {

            @Override
            public void onValueChange(NumberPicker picker, int oldVal,
                                      int newVal) {
            }
        });
        npic2.setOnValueChangedListener(new OnValueChangeListener() {

            @Override
            public void onValueChange(NumberPicker picker, int oldVal,
                                      int newVal) {
            }
        });
        npic3.setOnValueChangedListener(new OnValueChangeListener() {

            @Override
            public void onValueChange(NumberPicker picker, int oldVal,
                                      int newVal) {
            }
        });

    }

    /**
     * np1设置数据和np2设置数据
     *
     * @param v
     * @param type
     */
    public void set_data(NumberPicker v, int type) {
        switch (type) {
            case 1:
                if (list_2.size() >= 1) {
                    handler.sendEmptyMessage(0x001);
                } else {
                    Http_Postarea(list_1.get(npic1.getValue()).getId(), "0", 2);
                }
                break;
            case 2:
                if (list_3.size() >= 1) {
                    handler.sendEmptyMessage(0x002);
                } else {
                    Http_Postarea(list_1.get(npic1.getValue()).getId(),
                            list_2.get(npic2.getValue()).getId(), 3);
                }
                break;

            default:
                break;
        }
    }

    String sign = "";

    /**
     * 省市区
     *
     * @param province_id
     * @param city_id
     * @param type
     */
    private void Http_Postarea(String province_id, String city_id,
                               final int type) {

        HashMap<String, String> maps = new HashMap<>();
        maps.put("act", "get_ajax_region");
        maps.put("province_id", province_id);
        maps.put("city_id", city_id);
        String url = "https://app.juandie.com/api_mobile/address.php";
        Xutils_Get_Post.getInstance().post(url, maps, new XCallBack() {

            @Override
            public void onResponse(String result) {
                JSONObject object;
                try {
                    object = new JSONObject(result);
                    int status = object.getInt("status");
                    if (status == 1) {
                        switch (type) {
                            case 1:
                                list_1 = new ArrayList<area_info>();
                                JSONObject jso = object.getJSONObject("data");
                                JSONArray jsa_1 = jso.getJSONArray("province_list");
                                for (int i = 0; i < jsa_1.length(); i++) {
                                    JSONObject jso_1 = jsa_1.getJSONObject(i);
                                    list_1.add(new area_info(jso_1
                                            .getString("region_name"), jso_1
                                            .getString("region_id")));
                                }

                                break;
                            case 2:
                                list_2 = new ArrayList<area_info>();
                                JSONObject jso_2 = object.getJSONObject("data");
                                JSONArray jsa_2 = jso_2.getJSONArray("city_list");
                                for (int i = 0; i < jsa_2.length(); i++) {
                                    JSONObject jso_1 = jsa_2.getJSONObject(i);
                                    list_2.add(new area_info(jso_1
                                            .getString("region_name"), jso_1
                                            .getString("region_id")));
                                }
                                set_data(npic2, 1);
                                break;
                            case 3:
                                list_3 = new ArrayList<area_info>();
                                JSONObject jso_3 = object.getJSONObject("data");
                                JSONArray jsa_3 = jso_3
                                        .getJSONArray("district_list");
                                for (int i = 0; i < jsa_3.length(); i++) {
                                    JSONObject jso_1 = jsa_3.getJSONObject(i);
                                    list_3.add(new area_info(jso_1
                                            .getString("region_name"), jso_1
                                            .getString("region_id")));
                                }
                                set_data(npic3, 2);
                                break;

                            default:
                                break;
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
     * 保存地址
     *
     * @param consignee   收货姓名
     * @param tel         收货人电话
     * @param province_id
     * @param city_id
     * @param district_id
     * @param addres
     * @param buyer_name
     * @param buyer_tel
     */
    private void Http_Postsave(String consignee, String tel,
                               String province_id, String city_id, String district_id,
                               String addres, String buyer_name, String buyer_tel,
                               String address_id) {

        // 要上传的参数
        String params = "act=add_address&" + "consignee=" + consignee + "&tel="
                + tel + "&province_id=" + province_id + "&city_id=" + city_id
                + "&district_id=" + district_id + "&address=" + addres
                + "&buyer_name=" + buyer_name + "&buyer_tel=" + buyer_tel
                + "&address_id=" + address_id;
        HashMap<String, String> maps = new HashMap<>();
        maps.put("act", "add_address");
        maps.put("consignee", consignee);
        maps.put("tel", tel);
        maps.put("province_id", province_id);
        maps.put("city_id", city_id);
        maps.put("district_id", district_id);
        maps.put("address", addres);
        maps.put("buyer_name", buyer_name);
        maps.put("buyer_tel", buyer_tel);
        maps.put("address_id", address_id);
        String url = "https://app.juandie.com/api_mobile/address.php";

        Xutils_Get_Post.getInstance().post(url, maps, new XCallBack() {

            @Override
            public void onResponse(String result) {
                JSONObject object;
                try {
                    object = new JSONObject(result);
                    int status = object.getInt("status");
                    if (status == 1) {
                        Message msg1 = Message.obtain();
                        msg1.what = 0x004;
                        Bundle bundle = new Bundle();
                        String[] dat = {ed_shrname.getText().toString(),
                                ed_shrpho.getText().toString(),
                                ssq + " " + ed_shrdz1.getText().toString(),
                                ed_dhrname.getText().toString(),
                                ed_dhrpho.getText().toString()};

                        bundle.putStringArray("data", dat);
                        msg1.setData(bundle);
                        orderin.handler.sendMessage(msg1);
                        handler.sendEmptyMessage(0x04);

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

    public static class MyHandler extends Handler {
        WeakReference<addshdz> referenceObj;

        public MyHandler(addshdz activity) {
            referenceObj = new WeakReference<addshdz>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            addshdz activity = referenceObj.get();
            switch (msg.what) {
                case 0x001:
                    String[] data_ = new String[activity.list_2.size()];
                    for (int j = 0; j < activity.list_2.size(); j++) {
                        data_[j] = activity.list_2.get(j).getName();
                    }
                    activity.npic2.setDisplayedValues(data_);
                    activity.npic2.setMinValue(0);
                    activity.npic2.setValue(0);
                    activity.npic2.setMaxValue(data_.length - 1);
                    activity.npic1.setVisibility(View.GONE);
                    activity.npic2.setVisibility(View.VISIBLE);
                    activity.lin_dizi.setVisibility(View.VISIBLE);
                    break;
                case 0x002:
                    String[] data_1 = new String[activity.list_3.size()];
                    for (int j = 0; j < activity.list_3.size(); j++) {
                        data_1[j] = activity.list_3.get(j).getName();
                    }
                    activity.npic3.setDisplayedValues(data_1);
                    activity.npic3.setMinValue(0);
                    activity.npic3.setValue(0);
                    activity.npic3.setMaxValue(data_1.length - 1);
                    activity.npic2.setVisibility(View.GONE);
                    activity.npic3.setVisibility(View.VISIBLE);
                    activity.lin_dizi.setVisibility(View.VISIBLE);
                    break;

                case 0x03:
                    Bundle data = msg.getData();
                    String ph = data.getString("pho").replace(" ", "");
                    activity.ed_shrname.setText(data.getString("name"));
                    ph = activity.format(ph);
                    activity.ed_shrpho.setText(ph);
                    break;
                case 0x04:
                    orderin.handler.sendEmptyMessage(0x001);
                    activity.finish();
                    activity.overridePendingTransition(R.anim.push_right_out,
                            R.anim.push_right_in);
                    break;

                default:
                    break;
            }
        }

    }

    public String format(String s) {
        String str = s
                .replaceAll(
                        "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……& amp;*（）——+|{}【】‘；：”“’。，、？|-]",
                        "");
        return str;
    }

    /**
     * 手机正则
     *
     * @param patternStr
     * @param input
     * @return
     */
    public boolean isMatchered(String patternStr, CharSequence input) {
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return true;
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        if (window != null) {
            window.dismiss();
        }
    }
}
