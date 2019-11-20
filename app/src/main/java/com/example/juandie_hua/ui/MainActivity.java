package com.example.juandie_hua.ui;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import org.xutils.common.util.DensityUtil;
import org.xutils.x;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;

import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import cn.jpush.android.api.JPushInterface;

import com.example.juandie_hua.R;
import com.example.juandie_hua.percenter.seting.seting;
import com.example.juandie_hua.ui.tab.AboutAs;
import com.example.juandie_hua.ui.tab.Home;
import com.example.juandie_hua.ui.tab.Me;
import com.example.juandie_hua.app.App;
import com.example.juandie_hua.app.BaseActivity;
import com.example.juandie_hua.app.Constant;
import com.example.juandie_hua.helper.UiHelper;
import com.example.juandie_hua.mainactivity.Fengmian;
import com.example.juandie_hua.ui.tab.ShopCart;
import com.example.juandie_hua.ui.tab.Category;
import com.example.juandie_hua.mainactivity.internet_if;
import com.example.juandie_hua.mainactivity.no_internet;
import com.example.juandie_hua.utils.SharedPreferenceUtils;
import com.example.juandie_hua.utils.StatusBarUtils;
import com.example.juandie_hua.utils.TextViewUtils;
import com.meiqia.core.MQManager;
import com.meiqia.core.bean.MQMessage;
import com.meiqia.core.callback.OnGetMessageListCallback;
import com.umeng.analytics.MobclickAgent;

public class MainActivity extends BaseActivity {

    private List<Fragment> fragment = new ArrayList<>();
    @ViewInject(R.id.mainact_vp)
    ViewPager vpage;

    @ViewInject(R.id.mainact_tesy)
    TextView te_sy;
    @ViewInject(R.id.mainact_tefl)
    TextView te_fl;
    @ViewInject(R.id.mainact_tekf)
    TextView te_kf;
    @ViewInject(R.id.mainact_tegwc)
    TextView te_gwc;
    @ViewInject(R.id.mainact_tewd)
    TextView te_wd;

    private long exitTime = 0;
    public static MyHandler handler;
    int currentId = 0;

    @SuppressWarnings("unused")
    private static final char[] wJ = "0123456789abcdef".toCharArray();
    public static String imsi = "204046330839890";
    public static String p = "0";
    public static String keyword = "电话";
    public static String tranlateKeyword = "%E7%94%B5%E8%AF%9D";

    com.example.juandie_hua.mainactivity.no_internet no_internet;
    String gx_content = "", type = "1", android_url = "", goods_id = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        JPushInterface.init(getApplicationContext());
        super.onCreate(savedInstanceState);

        setContentView(R.layout.mainactivity);
        StatusBarUtils.with(this).setBarColor(R.color.white_fff);

        handler = new MyHandler(MainActivity.this);
        x.view().inject(MainActivity.this);
        setviewdata();

        no_internet = new no_internet(MainActivity.this);
        no_internet.setonc(new no_internet.te_oncl() {
            @Override
            public void set1(View v) {
                Intent intent = null;
                if (android.os.Build.VERSION.SDK_INT > 10) {
                    intent = new Intent(android.provider.Settings.ACTION_SETTINGS);
                } else {
                    intent = new Intent();
                    ComponentName component = new ComponentName("com.android.settings",
                            "com.android.settings.WirelessSettings");
                    intent.setComponent(component);
                    intent.setAction("android.intent.action.VIEW");
                }
                startActivity(intent);
                toast("跳转到手机设置");
            }

            @Override
            public void re_inter1(View v) {
                if (new internet_if().isNetworkConnected(MainActivity.this)) {
                    no_internet.dismiss();
                } else {
                    toast("网络连接失败,请设置网络");
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

        Intent i_ = getIntent();
        if (i_.getExtras() != null) {
            goods_id = i_.getStringExtra("goods_id");
            if (goods_id.length() >= 1) {
                handler.sendEmptyMessage(0x006);
            }
        }
    }


    @SuppressWarnings("deprecation")
    private void setviewdata() {
        fragment.add(new Home());
        fragment.add(new Category());
        fragment.add(new AboutAs());
        fragment.add(new ShopCart());
        fragment.add(new Me());

        te_sy.setSelected(true);
        int width = DensityUtil.dip2px(25);
        int padding = DensityUtil.dip2px(5);
        TextViewUtils.setImage(this, te_sy, R.drawable.selector_sy, 2, padding, width, width);
        TextViewUtils.setImage(this, te_fl, R.drawable.selector_fl, 2, padding, width, width);
        TextViewUtils.setImage(this, te_kf, R.drawable.selector_kfl, 2, padding, width, width);
        TextViewUtils.setImage(this, te_gwc, R.drawable.selector_gwc, 2, padding, width, width);
        TextViewUtils.setImage(this, te_wd, R.drawable.selector_per, 2, padding, width, width);

        vpage.setAdapter(new FragmentPagerAdapter(MainActivity.this
                .getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return fragment.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                return fragment.get(arg0);
            }
        });
        vpage.setOffscreenPageLimit(4);
        vpage.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                currentId = arg0;
                changeTab(arg0);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
        String mq = (String) SharedPreferenceUtils.getPreference(MainActivity.this, Constant.mq, "S");
        if (TextUtils.equals(mq, "true")) {
            MQManager.getInstance(getApplicationContext()).getUnreadMessages(
                    new OnGetMessageListCallback() {

                        @Override
                        public void onFailure(int arg0, String arg1) {
                        }

                        @Override
                        public void onSuccess(List<MQMessage> arg0) {
                            Message msg = Message.obtain();
                            msg.what = 0x001;
                            Bundle bd = new Bundle();
                            bd.putInt("number", arg0.size());
                            msg.setData(bd);
                            if (!(Home.myHandler == null)) {
                                Home.myHandler.sendMessage(msg);
                            }
                        }
                    });
        }
    }

    public void MainOnClick(View v) {
        changeTab(v.getId());
    }

    private void changeTab(int id) {
        switch (id) {
            case R.id.mainact_tesy:
                String mq = (String) SharedPreferenceUtils.getPreference(MainActivity.this, Constant.mq, "S");
                if (TextUtils.equals(mq, "true")) {
                    MQManager.getInstance(getApplicationContext())
                            .getUnreadMessages(new OnGetMessageListCallback() {

                                @Override
                                public void onFailure(int arg0, String arg1) {
                                }

                                @Override
                                public void onSuccess(List<MQMessage> arg0) {
                                    Message msg = Message.obtain();
                                    msg.what = 0x001;
                                    Bundle bd = new Bundle();
                                    bd.putInt("number", arg0.size());
                                    msg.setData(bd);
                                    Home.myHandler.sendMessage(msg);
                                }
                            });
                } else {
                    App.getInstance().initMeiqiaSDK();
                }
                currentId = 0;
                vpage.setCurrentItem(0, false);
                StatusBarUtils.with(this).setBarColor(R.color.white_fff);
                break;
            case R.id.mainact_tefl:
                currentId = 1;
                vpage.setCurrentItem(1, false);
                StatusBarUtils.with(this).setBarColor(R.color.white_fff);
                break;
            case R.id.mainact_tekf:
                currentId = 2;
                vpage.setCurrentItem(2, false);
                StatusBarUtils.with(this).setBarColor(R.color.white_fff);
                break;
            case R.id.mainact_tegwc:
                currentId = 3;
                vpage.setCurrentItem(3, false);
//                ShopCart.myHandler.sendEmptyMessage(0x001);
                StatusBarUtils.with(this).setBarColor(R.color.white_fff);
                break;
            case R.id.mainact_tewd:
                currentId = 4;
                vpage.setCurrentItem(4, false);
                Me.handler.sendEmptyMessage(0x003);
                StatusBarUtils.with(this).setBarColor(R.color.grey_f2f2f2);
                break;
            default:
                break;
        }
        setTabSelect();
    }

    private void setTabSelect() {
        te_sy.setSelected(currentId == 0);
        te_fl.setSelected(currentId == 1);
        te_kf.setSelected(currentId == 2);
        te_gwc.setSelected(currentId == 3);
        te_wd.setSelected(currentId == 4);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次，退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }

    public static class MyHandler extends Handler {
        WeakReference<MainActivity> referenceObj;

        public MyHandler(MainActivity activity) {
            referenceObj = new WeakReference<MainActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            MainActivity activity = referenceObj.get();
            switch (msg.what) {
                case 0x001:
                    activity.changeTab(activity.te_sy.getId());
                    Home.myHandler.sendEmptyMessage(0x002);
                    break;
                case 0x002:
                    activity.changeTab(activity.te_gwc.getId());
                    break;
                case 0x003:
                    activity.changeTab(activity.te_fl.getId());
                    break;
                case 0x004:
                    activity.changeTab(activity.te_wd.getId());
                    break;
                case 0x005:
                    activity.changeTab(activity.te_kf.getId());
                    break;

                case 0x006:
                    UiHelper.toGoodDetailActivity(activity, activity.goods_id);
                    break;

                case 0x007:
                    Bundle b = msg.getData();
                    String str = b.getString("openid");
                    activity.getopid(str);
                    break;
                default:
                    break;
            }
        }
    }

//    @Override
//    public void set(View v) {
//        // 暂不更新
//        if (type.equals("1")) {
//        } else {
//            finish();
//        }
//    }
//
//    @Override
//    public void re_inter(View v) {
//        // 立即更新
//        setDownLoad(android_url);
//    }

    private ProgressDialog progressDialog;

    protected void setDownLoad(String downloadurl) {
        progressDialog = new ProgressDialog(MainActivity.this,
                R.style.CustomDialog);
        // TODO Auto-generated method stub
        RequestParams params = new RequestParams(downloadurl);
        params.setAutoRename(true);// 断点下载
        params.setSaveFilePath("/mnt/sdcard/demo.apk");
        x.http().get(params, new Callback.ProgressCallback<File>() {
            @Override
            public void onCancelled(CancelledException arg0) {
            }

            @Override
            public void onError(Throwable arg0, boolean arg1) {
                // TODO Auto-generated method stub
                if (progressDialog != null && progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFinished() {
                // TODO Auto-generated method stub
            }

            @Override
            public void onSuccess(File arg0) {
                // TODO Auto-generated method stub
                if (progressDialog != null && progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setDataAndType(Uri.fromFile(new File(Environment
                                .getExternalStorageDirectory(), "demo.apk")),
                        "application/vnd.android.package-archive");
                startActivity(intent);
            }

            @Override
            public void onLoading(long arg0, long arg1, boolean arg2) {
                // TODO Auto-generated method stub
                progressDialog.setMax((int) arg0);
                progressDialog.setProgress((int) arg1);
            }

            @Override
            public void onStarted() {
                // TODO Auto-generated method stub
                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog
                        .setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);// 设置为水平进行条
                progressDialog.setMessage("正在下载中...");
                progressDialog.setProgress(0);
                progressDialog.show();
            }

            @Override
            public void onWaiting() {
                // TODO Auto-generated method stub
            }
        });
    }

    public void getopid(String opid) {
        SharedPreferenceUtils.setPreference(MainActivity.this, Constant.openid, opid, "S");
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
    protected void onRestart() {
        super.onRestart();

    }
}
