package com.example.juandie_hua.app;

import org.xutils.x;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;

import com.example.juandie_hua.utils.SharedPreferenceUtils;
import com.meiqia.core.MQManager;
import com.meiqia.core.callback.OnInitCallback;
import com.meiqia.meiqiasdk.util.MQConfig;
import com.mob.MobSDK;
import com.pgyersdk.Pgyer;
import com.pgyersdk.PgyerActivityManager;
import com.pgyersdk.crash.PgyCrashManager;
import com.pgyersdk.crash.PgyerCrashObservable;
import com.pgyersdk.crash.PgyerObserver;

import cn.jpush.android.api.JPushInterface;

public class App extends Application {

    public static App app;

    public static App getInstance() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;

        // 加载系统默认设置，字体不随用户设置变化
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());

        x.Ext.init(this);

        initMeiqiaSDK();
        MQManager.setDebugMode(false);
        CrashHandler catchHandler = CrashHandler.getInstance();
        catchHandler.init(getApplicationContext());

        JPushInterface.setDebugMode(true); // 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);
        SharedPreferenceUtils.setPreference(this, Constant.regid, JPushInterface.getRegistrationID(getApplicationContext()), "S");
        MobSDK.init(this, "239afb3f89c14", "9cd984a02a040c5e4ea031c3680c548c");

        initPgy();
    }

    private Thread.UncaughtExceptionHandler onBlooey = new Thread.UncaughtExceptionHandler() {
        public void uncaughtException(Thread thread, Throwable ex) {
            Log.e("APP", "Uncaught exception  ", ex);
            Log.e("APP", ex.getCause().toString());
        }
    };

    public void initMeiqiaSDK() {
        // MQManager.setDebugMode(true);

        // 替换成自己的key
        String meiqiaKey = "18dd2c1db24ec4034ac9d92fdf0e9cef";
        MQConfig.init(this, meiqiaKey, new OnInitCallback() {
            @Override
            public void onSuccess(String clientId) {
                SharedPreferenceUtils.setPreference(getApplicationContext(), Constant.mq, "true", "S");
            }

            @Override
            public void onFailure(int code, String message) {
                SharedPreferenceUtils.setPreference(getApplicationContext(), Constant.mq, "false", "S");
            }
        });
    }

    private void initPgy() {
        PgyCrashManager.register();
        PgyerCrashObservable.get().attach(new PgyerObserver() {
            @Override
            public void receivedCrash(Thread thread, Throwable throwable) {

            }
        });
        PgyerActivityManager.set(this);
    }

    public boolean isLogin() {
        boolean isLogin = (boolean) SharedPreferenceUtils.getPreference(this, Constant.is_login, "B");
        String uid = (String) SharedPreferenceUtils.getPreference(this, Constant.uid, "S");
        return isLogin && !TextUtils.isEmpty(uid);
    }

    public String getUid() {
        return (String) SharedPreferenceUtils.getPreference(this, Constant.uid, "S");
    }

    public String getRegid() {
        return (String) SharedPreferenceUtils.getPreference(this, Constant.regid, "S");
    }

    public String getMsgQuDao() {
        return (String) SharedPreferenceUtils.getPreference(this, Constant.msg_qudao, "S");
    }

    public String getMsgQuDao1() {
        return (String) SharedPreferenceUtils.getPreference(this, Constant.msg_qudao1, "S");
    }

    public String getOpenId() {
        return (String) SharedPreferenceUtils.getPreference(this, Constant.openid, "S");
    }

    public String getPhpSid() {
        return (String) SharedPreferenceUtils.getPreference(this, Constant.phpsid, "S");
    }


    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        Pgyer.setAppId("3b6a0bca1efa7daa5751d906e73ce666");
    }


}
