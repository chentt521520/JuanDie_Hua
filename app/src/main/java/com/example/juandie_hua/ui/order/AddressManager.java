package com.example.juandie_hua.ui.order;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.juandie_hua.R;
import com.example.juandie_hua.app.BaseActivity;
import com.example.juandie_hua.http.HttpUrl;
import com.example.juandie_hua.helper.UiHelper;
import com.example.juandie_hua.mainactivity.Xutils_Get_Post;
import com.example.juandie_hua.mainactivity.Xutils_Get_Post.XCallBack;
import com.example.juandie_hua.model.UserAddress;
import com.example.juandie_hua.mycar.QNumberPicker;
import com.example.juandie_hua.mycar.contact.txl_choose;
import com.example.juandie_hua.utils.StatusBarUtils;
import com.example.juandie_hua.utils.StrUtils;
import com.example.juandie_hua.view.CityPickerView;
import com.example.juandie_hua.view.citychoosedialog.DialogCityChooseOnClick;
import com.example.juandie_hua.view.citychoosedialog.MyDialogCityChoose;
import com.example.juandie_hua.view.citychoosedialog.entity.CityInfo;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback.CancelledException;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddressManager extends BaseActivity {

    @ViewInject(R.id.addshdz_edshrnamex)
    EditText ed_shrname;

    @ViewInject(R.id.addshdz_edshrphx)
    EditText ed_shrpho;

    @ViewInject(R.id.addshdz_lintxl)
    LinearLayout lin_txl;

    @ViewInject(R.id.addshdz_teshrdz1)
    TextView te_shrdz1;

    @ViewInject(R.id.addshdz_edshrxxdz)
    EditText ed_shrdz1;

    @ViewInject(R.id.addshdz_eddhrname)
    EditText ed_dhrname;

    @ViewInject(R.id.addshdz_eddhrph)
    EditText ed_dhrpho;

    @ViewInject(R.id.addshdz_teok)
    TextView te_ok;

    PopupWindow window;

    List<CityInfo> list_1, list_2, list_3;
    String ssq = "";
    LinearLayout lin_dizi;
    public static MyHandler handler;
    String PHONE_PATTERN = "^(1)\\d{10}$";
    String address_id = "0";
    private MyDialogCityChoose dialogCity;
    private UserAddress address;
    private QNumberPicker npic1;
    private QNumberPicker npic2;
    private QNumberPicker npic3;
    private CityPickerView pickerView;
    private String provinceId = "0";
    private String districtId = "0";
    private String cityId = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitleContentView(R.layout.activity_address);
        StatusBarUtils.with(this).setBarColor(R.color.white_fff);

        handler = new MyHandler(AddressManager.this);
        x.view().inject(this);
        Http_Postarea("0", "0", 0);

        address = (UserAddress) getIntent().getSerializableExtra("address");

        initData();
        setviwhw();
        setviewlisten();

    }

    private void initData() {

    }

    private void setviewlisten() {
        te_shrdz1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!StrUtils.listIsEmpty(list_1)) {
//                    showCityDialog();
                    showDialog();
                } else
                    Http_Postarea("0", "0", 0);
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
                UserAddress address = new UserAddress();
                String name1 = ed_shrname.getText().toString();
                if (TextUtils.isEmpty(name1)) {
                    toast("请填写收货人姓名");
                    return;
                } else {
                    address.setReceiverName(name1);
                }

                String phone1 = ed_shrpho.getText().toString();
                if (!isMatchered(PHONE_PATTERN, phone1)) {
                    toast("请填写正确的收货人电话");
                    return;
                } else {
                    address.setReceiverPhone(phone1);
                }

                String city = te_shrdz1.getText().toString();
                if (TextUtils.isEmpty(city)) {
                    toast("请选择地址");
                    return;
                } else {
                    address.setCities(city);
                    address.setIdStr(provinceId + "," + cityId + "," + districtId);
                    address.setProvince(city.split(" ")[0]);
                    address.setProvinceId(provinceId);
                    address.setCity(city.split(" ")[1]);
                    address.setCityId(cityId);
                    address.setDistrict(city.split(" ")[2]);
                    address.setDistrictId(districtId);
                }

                String detail = ed_shrdz1.getText().toString();
                if (TextUtils.isEmpty(detail)) {
                    toast("请填写详细地址");
                    return;
                } else if (StrUtils.getLength(detail) < 5) {
                    toast("至少输入5个字符");
                    return;
                } else {
                    address.setDetail(detail);
                }

                String name2 = ed_dhrname.getText().toString();
                if (TextUtils.isEmpty(name2)) {
                    toast("请填写订货人姓名");
                    return;
                } else {
                    address.setOrderName(name2);
                }

                String phone2 = ed_dhrpho.getText().toString();
                if (!isMatchered(PHONE_PATTERN, phone2)) {
                    toast("请填写正确的订货人电话");
                    return;
                } else {
                    address.setOrderPhone(phone2);
                }


//                Http_Postsave(ed_shrname.getText().toString(),
//                        ed_shrpho.getText().toString(),
//                        cityId.split(",")[0], cityId.split(",")[1], cityId.split(",")[2],
//                        ed_shrdz1.getText().toString(),
//                        ed_dhrname.getText().toString(),
//                        ed_dhrpho.getText().toString(),
//                        address_id);
                Http_Postsave(address);
            }
        });
    }

    private void showCityDialog() {

        if (dialogCity == null) {
            dialogCity = new MyDialogCityChoose(AddressManager.this, 0, list_1, new DialogCityChooseOnClick() {
                @Override
                public void complete(String ids, String cities) {
                    Log.e("~~~~~~~~~", ids + ",," + cities);
                    cityId = ids;
                    te_shrdz1.setText(cities.replace(",", " "));
                }

                @Override
                public void onTabSelect(int pos, String provinceId, String cityId) {
                    Http_Postarea(provinceId, cityId, pos);
                }

                @Override
                public void onItemSelect(int pos, String provinceId, String cityId) {
                    Http_Postarea(provinceId, cityId, pos);
                }
            });
        }
        dialogCity.show();
    }

    private void showDialog() {
        pickerView = new CityPickerView.Builder(AddressManager.this)
                .setList(list_1)
                .setListener(new CityPickerView.OnCitySelectListener() {
                    @Override
                    public void onConfirmListener(int pos, String id, String addressStr) {
                        if (pos == 1) {
                            provinceId = id;
                            Http_Postarea(id, cityId, pos);
                        } else if (pos == 2) {
                            cityId = id;
                            Http_Postarea(provinceId, id, pos);
                        } else if (pos == 3) {
                            districtId = id;
                            te_shrdz1.setText(addressStr);
                        }
                    }
                }).build();

        pickerView.showPopWin(AddressManager.this);
    }

    public void getcon() {
        // 版本号的判断
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            showtxl_choose();
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
                    Toast.makeText(AddressManager.this, "请开启通讯录权限", Toast.LENGTH_SHORT)
                            .show();
                }

            } else {
                showtxl_choose();
            }
        }
    }

    private void showtxl_choose() {
        Intent i = new Intent();
        i.setClass(AddressManager.this, txl_choose.class);
        Bundle b = new Bundle();
        b.putString("type", "1");// 1表示添加地址。
        i.putExtras(b);
        startActivity(i);
        overridePendingTransition(R.anim.push_left_in,
                R.anim.push_left_out);
    }

    private void setviwhw() {
        lin_txl.setVisibility(View.VISIBLE);

        if (address == null) {
            getTitleView().setTitleText("添加收货地址");
        } else {
            getTitleView().setTitleText("修改收货地址");
            ed_shrname.setText(address.getReceiverName());
            ed_shrpho.setText(address.getReceiverPhone());
            te_shrdz1.setText(address.getCities());
            ed_shrdz1.setText(address.getDetail());
            ed_dhrname.setText(address.getOrderName());
            ed_dhrpho.setText(address.getOrderPhone());
        }
//        Bundle bundle = getIntent().getExtras();
//        address_id = bundle.getStringArray("address")[0];
//        String[] address = bundle.getStringArray("address");
//        if (!address_id.equals("0")) {
//            getTitleView().setTitleText("修改收货地址");
//            ed_shrname.setText(address[1]);
//            ed_shrpho.setText(address[2]);
//            ed_shrdz1.setText(address[3]);
//            ed_dhrname.setText(address[4]);
//            ed_dhrpho.setText(address[5]);
//        } else {
//            getTitleView().setTitleText("添加收货地址");
//        }

//        re_shrname1.setVisibility(View.GONE);
//        re_shrpho1.setVisibility(View.GONE);

        list_1 = new ArrayList<>();
        list_2 = new ArrayList<>();
        list_3 = new ArrayList<>();
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
            overridePendingTransition(R.anim.push_right_out, R.anim.push_right_in);

            return false;
        }
        return false;
    }

    /**
     * 获取省市区
     *
     * @param province_id
     * @param city_id
     * @param type
     */
    private void Http_Postarea(String province_id, String city_id, final int type) {

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
                        JSONObject jso = object.getJSONObject("data");
                        switch (type) {
                            case 0:
                                list_1 = JSON.parseArray(jso.getString("province_list"), CityInfo.class);
                                break;
                            case 1:
                                list_2 = JSON.parseArray(jso.getString("city_list"), CityInfo.class);
                                dialogCity.fresh(1, list_2);
                                break;
                            case 2:
                                list_3 = JSON.parseArray(jso.getString("district_list"), CityInfo.class);
                                dialogCity.fresh(2, list_3);
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
     * <p>
     * //     * @param consignee   收货姓名
     * ////     * @param tel         收货人电话
     * ////     * @param province_id
     * ////     * @param city_id
     * ////     * @param district_id
     * ////     * @param addres
     * ////     * @param buyer_name
     * ////     * @param buyer_tel
     */
//    private void Http_Postsave(String consignee, String tel,
//                               String province_id, String city_id, String district_id,
//                               String addres, String buyer_name, String buyer_tel,
//                               String address_id) {
    private void Http_Postsave(final UserAddress address) {

//        // 要上传的参数
//        String params = "act=add_address&" + "consignee=" + consignee + "&tel="
//                + tel + "&province_id=" + province_id + "&city_id=" + city_id
//                + "&district_id=" + district_id + "&address=" + addres
//                + "&buyer_name=" + buyer_name + "&buyer_tel=" + buyer_tel
//                + "&address_id=" + address_id;
//        HashMap<String, String> maps = new HashMap<>();
//        maps.put("act", "add_address");
//        maps.put("consignee", consignee);
//        maps.put("tel", tel);
//        maps.put("province_id", province_id);
//        maps.put("city_id", city_id);
//        maps.put("district_id", district_id);
//        maps.put("address", addres);
//        maps.put("buyer_name", buyer_name);
//        maps.put("buyer_tel", buyer_tel);
//        maps.put("address_id", address_id);
//        String url = "https://app.juandie.com/api_mobile/address.php";
        String url = HttpUrl.address;

        HashMap<String, String> maps = new HashMap<>();
        maps.put("act", "add_address");
        maps.put("consignee", address.getReceiverName());
        maps.put("tel", address.getReceiverPhone());
        maps.put("province_id", address.getProvinceId());
        maps.put("city_id", address.getCityId());
        maps.put("district_id", address.getDistrictId());
        maps.put("address", address.getDetail());
        maps.put("buyer_name", address.getOrderName());
        maps.put("buyer_tel", address.getOrderPhone());
        maps.put("address_id", address_id);

        Xutils_Get_Post.getInstance().post(url, maps, new XCallBack() {

            @Override
            public void onResponse(String result) {
                JSONObject object;
                try {
                    object = new JSONObject(result);
                    int status = object.getInt("status");
                    if (status == 1) {
//                        Message msg1 = Message.obtain();
//                        msg1.what = 0x004;
//                        Bundle bundle = new Bundle();
//                        String[] dat = {ed_shrname.getText().toString(),
//                                ed_shrpho.getText().toString(),
//                                ssq + " " + ed_shrdz1.getText().toString(),
//                                ed_dhrname.getText().toString(),
//                                ed_dhrpho.getText().toString()};
//
//                        bundle.putStringArray("data", dat);
//                        bundle.putSerializable("addressInfo", address);
//                        msg1.setData(bundle);
//                        orderin.handler.sendMessage(msg1);
//                        handler.sendEmptyMessage(0x04);

                        Intent intent = new Intent();
                        intent.putExtra("address", address);
                        setResult(Activity.RESULT_OK, intent);
                        UiHelper.finish(AddressManager.this);

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

    public static class MyHandler extends Handler {
        WeakReference<AddressManager> referenceObj;

        public MyHandler(AddressManager activity) {
            referenceObj = new WeakReference<AddressManager>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            AddressManager activity = referenceObj.get();
            switch (msg.what) {
                case 0x001:
                    String[] data_ = new String[activity.list_2.size()];
                    for (int j = 0; j < activity.list_2.size(); j++) {
                        data_[j] = activity.list_2.get(j).getRegion_name();
                    }
//                    activity.npic2.setDisplayedValues(data_);
//                    activity.npic2.setMinValue(0);
//                    activity.npic2.setValue(0);
//                    activity.npic2.setMaxValue(data_.length - 1);
//                    activity.npic1.setVisibility(View.GONE);
//                    activity.npic2.setVisibility(View.VISIBLE);
                    activity.lin_dizi.setVisibility(View.VISIBLE);
                    break;
                case 0x002:
                    String[] data_1 = new String[activity.list_3.size()];
                    for (int j = 0; j < activity.list_3.size(); j++) {
                        data_1[j] = activity.list_3.get(j).getRegion_name();
                    }
//                    activity.npic3.setDisplayedValues(data_1);
//                    activity.npic3.setMinValue(0);
//                    activity.npic3.setValue(0);
//                    activity.npic3.setMaxValue(data_1.length - 1);
//                    activity.npic2.setVisibility(View.GONE);
//                    activity.npic3.setVisibility(View.VISIBLE);
                    activity.lin_dizi.setVisibility(View.VISIBLE);
                    break;

                case 0x03:
                    Bundle data = msg.getData();
                    String ph = data.getString("pho").replace(" ", "");
                    activity.ed_shrname.setText(data.getString("name"));
                    ph = activity.format(ph);
                    activity.ed_shrpho.setText(ph);
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
