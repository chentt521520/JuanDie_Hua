package com.example.juandie_hua.mainactivity;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.util.DensityUtil;
import org.xutils.x;
import org.xutils.common.Callback;
import org.xutils.common.Callback.CancelledException;
import org.xutils.http.RequestParams;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.juandie_hua.R;
import com.example.juandie_hua.ui.MainActivity;
import com.example.juandie_hua.app.BaseActivity;
import com.example.juandie_hua.app.Constant;
import com.example.juandie_hua.http.HttpUrl;
import com.example.juandie_hua.calender.utils.ImageUtils;
import com.example.juandie_hua.helper.UiHelper;
import com.example.juandie_hua.mainactivity.Xutils_Get_Post.XCallBack;
import com.example.juandie_hua.mainactivity.no_internet.te_oncl;
import com.example.juandie_hua.utils.PackageUtils;
import com.example.juandie_hua.utils.SharedPreferenceUtils;
import com.example.juandie_hua.utils.StatusBarUtils;
import com.example.juandie_hua.utils.StrUtils;
import com.example.juandie_hua.utils.ViewUtils;
import com.example.juandie_hua.view.CusPopWindow;
import com.example.juandie_hua.view.CustomDialog;
import com.example.juandie_hua.welcom.BasePagerAdapter;
import com.tendcloud.tenddata.TCAgent;

public class Fengmian extends BaseActivity {
    no_internet no_internet;

    String gx_content = "", type = "1", android_url = "";

    private ImageView startButton;
    private ImageView[] indicators = null;

    ImageView ima_ydy;
    private RelativeLayout view_pager_view;
    TextView te_bfb, te_jd;
    ProgressBar pbBar;

    CustomDialog updateDialog;

    String[] goods_id = {"324", "338"};
    private int versionCode;
    private CusPopWindow popWindow;
    private boolean first;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        StatusBarUtils.with(this).setBarColor(R.color.white_fff);

        initView();
        initData();
    }

    private void initView() {
        //第二次进入
        ima_ydy = findViewById(R.id.fngmian_imaydy);
        view_pager_view = findViewById(R.id.view_pager_view);

        //首次进入
        int[] images;
        if (StrUtils.is520Day()) {//520专属图片
            images = new int[]{R.drawable.y1_jr, R.drawable.y2_jr, R.drawable.y3_jr};
        } else {
            images = new int[]{R.drawable.y1, R.drawable.y2, R.drawable.y3};
        }
        // 实例化视图控件
        ViewPager viewPager = findViewById(R.id.viewpage);
        startButton = findViewById(R.id.start_Button);

        /*平日引导页，进入主页按钮*/
        startButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                UiHelper.toActivity(Fengmian.this, MainActivity.class);
                finish();
            }
        });
        LinearLayout indicatorLayout = findViewById(R.id.indicator);
        ArrayList<View> views = new ArrayList<>();
        indicators = new ImageView[images.length]; // 定义指示器数组大小
        for (int i = 0; i < images.length; i++) {
            final int index = i;
            // 循环加入图片
            ImageView imageView = new ImageView(Fengmian.this);
            imageView.setScaleType(ScaleType.CENTER_CROP);
            ImageUtils.setImage(getApplicationContext(), images[i], imageView);
            views.add(imageView);
            // 循环加入指示器
            indicators[i] = new ImageView(Fengmian.this);
            indicators[i].setBackgroundResource(R.drawable.indicators_default);

            ViewUtils.setviewhw_lin(indicators[i], -2, -2, 10, 0, 10, 0);

            if (i == 0) {
                indicators[i].setBackgroundResource(R.drawable.indicators_now);
            }
            indicatorLayout.addView(indicators[i]);

            //520时点击引导图片进入对应的商品详情
            imageView.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (StrUtils.is520Day()) {
                        if (index == 2) {//第三张图片点击进入主界面
                            UiHelper.toActivity(Fengmian.this, MainActivity.class);
                            finish();
                        } else {//前两张图片
                            Intent i = new Intent(Fengmian.this, MainActivity.class);
                            i.putExtra("goods_id", goods_id[index]);
                            UiHelper.toActivity(Fengmian.this, i);
                            finish();
                        }
                    }
                }
            });

        }
        PagerAdapter pagerAdapter = new BasePagerAdapter(views);
        viewPager.setAdapter(pagerAdapter); // 设置适配器

        viewPager.addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (StrUtils.is520Day()) {
                    //520 专属图片有进入主页按钮
                    startButton.setVisibility(View.GONE);
                } else {
                    if (position == indicators.length - 1) {
                        startButton.setVisibility(View.VISIBLE);
                    } else {
                        startButton.setVisibility(View.INVISIBLE);
                    }
                }

                // 更改指示器图片
                for (int i = 0; i < indicators.length; i++) {
                    indicators[position].setBackgroundResource(R.drawable.indicators_now);
                    if (position != i) {
                        indicators[i].setBackgroundResource(R.drawable.indicators_default);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initData() {
        versionCode = PackageUtils.getVersionCode(this);

        String id = (String) SharedPreferenceUtils.getPreference(this, Constant.openid, "S");
        String openid;
        if (!TextUtils.isEmpty(id) && !TextUtils.equals(id, "0")) {
            openid = id;
        } else {
            openid = "" + StrUtils.getopenid();
            SharedPreferenceUtils.setPreference(this, Constant.openid, openid, "S");
        }

        if (openid.contains("-")) {
            openid = openid.replace("-", "");
        }

        //获取当前版本是否一致
        String bbh = (String) SharedPreferenceUtils.getPreference(this, Constant.bbh, "S");

        first = (boolean) SharedPreferenceUtils.getPreference(this, Constant.first, "B");
        if (first) {
            //首次登陆
            view_pager_view.setVisibility(View.VISIBLE);
            ima_ydy.setVisibility(View.GONE);

            //版本不一致
            if (TextUtils.isEmpty(bbh) || !TextUtils.equals(bbh, versionCode + "")) {
                //检测APP版本
                if (new internet_if().isNetworkConnected(this)) {
                    checkVersion();
                } else {
                    checkNet();
                }
            }
        } else {
            view_pager_view.setVisibility(View.GONE);
            ima_ydy.setVisibility(View.VISIBLE);

            //版本不一致
            if (TextUtils.isEmpty(bbh) || !TextUtils.equals(bbh, versionCode + "")) {
                //检测APP版本
                if (new internet_if().isNetworkConnected(this)) {
                    checkVersion();
                } else {
                    checkNet();
                }
            }

//            new Handler().postDelayed(new Runnable() {
//
//                @Override
//                public void run() {
//                    UiHelper.toActivity(Fengmian.this, MainActivity.class);
//                    finish();
//                }
//            }, 3000);
        }
//        SharedPreferenceUtils.setPreference(Fengmian.this, Constant.regid, JPushInterface.getRegistrationID(Fengmian.this), "S");

        try {
            ApplicationInfo appInfo = this.getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            String msg_qudao = "&channel=" + appInfo.metaData.getString("UMENG_CHANNEL");
            String msg_qudao1 = appInfo.metaData.getString("UMENG_CHANNEL");

            SharedPreferenceUtils.setPreference(Fengmian.this, Constant.msg_qudao, msg_qudao, "S");
            SharedPreferenceUtils.setPreference(Fengmian.this, Constant.msg_qudao1, msg_qudao1, "S");

            TCAgent.init(this.getApplicationContext(), "A374F8C601874548AD3DF8809D730E5B", msg_qudao1);

            TCAgent.setReportUncaughtExceptions(true);

        } catch (
                NameNotFoundException e) {
            e.printStackTrace();
        }

        String ss = Build.BRAND + "";
        if (ss.toUpperCase().

                equals("OPPO")) {// 解决oppo回收超时bug
            try {
                Class<?> clazz = Class.forName("java.lang.Daemons$FinalizerWatchdogDaemon");
                Method method = clazz.getSuperclass().getDeclaredMethod("stop");
                method.setAccessible(true);
                Field field = clazz.getDeclaredField("INSTANCE");
                field.setAccessible(true);
                method.invoke(field.get(null));
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }

    }

    private void checkNet() {
        if (no_internet == null) {

            no_internet = new no_internet(Fengmian.this, R.style.CustomDialog);
            no_internet.setonc(new te_oncl() {
                @Override
                public void set1(View v) {
                    Intent intent;
                    if (Build.VERSION.SDK_INT > 10) {
                        intent = new Intent(android.provider.Settings.ACTION_SETTINGS);
                    } else {
                        intent = new Intent();
                        ComponentName component = new ComponentName("com.android.settings", "com.android.settings.WirelessSettings");
                        intent.setComponent(component);
                        intent.setAction("android.intent.action.VIEW");
                    }
                    startActivity(intent);
                    toast(getResources().getString(R.string.jump_to_setting));
                }

                @Override
                public void re_inter1(View v) {
                    if (new internet_if().isNetworkConnected(Fengmian.this)) {
                        no_internet.dismiss();
                        checkVersion();
                    } else {
                        toast(getResources().getString(R.string.net_connect_error));
                    }
                }
            });
            no_internet.setOnKeyListener(new DialogInterface.OnKeyListener() {

                @Override
                public boolean onKey(DialogInterface dialog, int keyCode,
                                     KeyEvent event) {
                    finish();
                    return true;
                }
            });
        }
        no_internet.show();
    }

    /**
     * 检查app版本
     */
    private void checkVersion() {
//        String url = "https://app.juandie.com/api_mobile/index.php?act=app_version";
        String url = HttpUrl.index + "act=" + HttpUrl.app_version;

        Xutils_Get_Post.getInstance().get(url, new XCallBack() {

            @Override
            public void onResponse(String result) {
                try {
                    JSONObject response = new JSONObject(result);
                    String phpsid = response.getString("PHPSESSID");

                    if (response.getString("status").equals("1")) {
                        JSONObject data = response.getJSONObject("data");
                        String code = data.getString("versionCode");

                        //不用更新
                        if (versionCode >= data.getInt("versionCode")) {
                            toMainPage(code);
                        }
                        //可更新
                        else {
                            JSONObject versionInfo = data.getJSONObject("android_new_version_info");
                            String str = versionInfo.getString("content").replace("br", "\n");
                            str = str.replace("nbsp", "  ");
                            gx_content = str;
                            type = data.getString("type");// 1:不用强制更新，2：需要强制更新

                            android_url = data.getString("android_url");
                            showUpdate(type, versionInfo.getString("title"), str);
                        }

                    } else {
                        toast(response.getString("msg"));
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

    private void toMainPage(String code) {
        if (first) {
            SharedPreferenceUtils.setPreference(Fengmian.this, Constant.first, false, "B");
            if (!TextUtils.isEmpty(code)) {
                SharedPreferenceUtils.setPreference(Fengmian.this, Constant.bbh, code, "S");
            }
        } else {
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    UiHelper.toActivity(Fengmian.this, MainActivity.class);
                    finish();
                }
            }, 3000);
        }
    }

    /**
     * app新版本下载
     *
     * @param downloadurl url
     */
    protected void setDownLoad(String downloadurl) {
        RequestParams params = new RequestParams(downloadurl);
        params.setAutoRename(true);// 断点下载
        String path = Constant.PATH + Constant.SETUP + File.separator;
        String apkName = "JuanDie.apk";
        params.setSaveFilePath(path + apkName);//"/mnt/sdcard/JuanDie.apk"
        x.http().get(params, new Callback.ProgressCallback<File>() {
            @Override
            public void onCancelled(CancelledException arg0) {
            }

            @Override
            public void onError(Throwable arg0, boolean arg1) {
                popWindow.dismiss();
            }

            @Override
            public void onFinished() {
            }

            @Override
            public void onSuccess(File arg0) {
                popWindow.dismiss();

                Uri uri = null;
                Intent intent = new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                try {
                    if (Build.VERSION.SDK_INT >= 24) {//7.0 Android N
                        intent.setAction(Intent.ACTION_INSTALL_PACKAGE);
                        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);//7.0以后，系统要求授予临时uri读取权限，安装完毕以后，系统会自动收回权限，该过程没有用户交互
                        uri = FileProvider.getUriForFile(Fengmian.this, "com.example.juandie_hua.fileprovider", arg0);
                    } else {//7.0以下
                        intent.setAction(Intent.ACTION_VIEW);
                        uri = Uri.fromFile(arg0);
                    }
                    intent.setDataAndType(uri, "application/vnd.android.package-archive");
                    startActivity(intent);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (ActivityNotFoundException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onLoading(long arg0, long arg1, boolean arg2) {
                int size = (int) arg0;
                int pro = (int) arg1;
                pbBar.setMax(size);
                pbBar.setProgress(pro);

                te_bfb.setText(pro * 100 / size + "%");
                te_jd.setText(pro * 100 / size + "/100");
            }

            @Override
            public void onStarted() {
                pbBar.setProgress(0);
            }

            @Override
            public void onWaiting() {
            }
        });
    }


    private void requestPromis() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, INSTALL_PACKAGES_REQUEST_CODE);
            } else {
                pop_gx();
            }
        }
    }

    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    int INSTALL_PACKAGES_REQUEST_CODE = 0x001;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean grant = false;
        if (requestCode == INSTALL_PACKAGES_REQUEST_CODE) {
            for (int i = 0; i < permissions.length; i++) {
                Log.i("haha", "申请的权限为：" + permissions[i] + ",申请结果：" + grantResults[i]);
                if (grantResults[i] == -1) {
                    grant = false;
                    break;
                } else {
                    grant = true;
                }
            }
            if (grant) {//申请权限通过
                pop_gx();
            } else {//申请权限拒绝
                toMainPage("");
            }
        }
    }

    /**
     * 新版本提示dialog
     *
     * @param type  是否强制更新
     * @param title 版本名称
     * @param msg   更新内容
     */
    private void showUpdate(String type, String title, String msg) {
        CustomDialog.Builder builder = new CustomDialog.Builder(Fengmian.this);
        builder.setTitle(getResources().getString(R.string.new_version_update)).setMessage(msg);
        if (TextUtils.equals(type, "2")) {
            builder.setPositiveButton(getResources().getString(R.string.update_now), new OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateDialog.dismiss();
                    if (new internet_if().isNetworkConnected(Fengmian.this)) {
                        requestPromis();
                    } else {
                        toast(getResources().getString(R.string.net_connect_error));
                    }
                }
            }).setNegativeBtnShow(false);
        } else {
            builder.setPositiveButton(getResources().getString(R.string.update_now), new OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateDialog.dismiss();
                    if (new internet_if().isNetworkConnected(Fengmian.this)) {
                        requestPromis();
                    } else {
                        toast(getResources().getString(R.string.net_connect_error));
                    }
                }
            }).setNegativeButton(getResources().getString(R.string.ignore), new OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateDialog.dismiss();
                    UiHelper.toActivity(Fengmian.this, MainActivity.class);
                }
            });
        }
        builder.setCancelable(false);
        updateDialog = builder.create();
        updateDialog.show();
    }

    /**
     * 下载popupWindow
     */
    public void pop_gx() {
        View view = LayoutInflater.from(Fengmian.this).inflate(R.layout.bbgx, null);

        popWindow = new CusPopWindow.PopupWindowBuilder(this)
                .setView(view)
                .size(DensityUtil.getScreenWidth() / 4 * 3, -2)
                .enableBackgroundDark(true)
                .enableOutsideTouchableDissmiss(false)
                .setOnDissmissListener(null)
                .create();

        popWindow.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);

        pbBar = view.findViewById(R.id.preview_progressBar);

        te_bfb = view.findViewById(R.id.bbgxte_bfb);
        te_jd = view.findViewById(R.id.bbgxte_jd);

        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                return true;
            }
        });
        setDownLoad(android_url);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (popWindow != null) {
            popWindow.dismiss();
        }
    }
}
