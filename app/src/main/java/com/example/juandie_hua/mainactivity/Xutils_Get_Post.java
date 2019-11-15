package com.example.juandie_hua.mainactivity;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.x;
import org.xutils.common.Callback;
import org.xutils.common.util.MD5;
import org.xutils.http.RequestParams;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;

import com.example.juandie_hua.ui.MainActivity;

public class Xutils_Get_Post {
    String Result = "";

    private volatile static Xutils_Get_Post instance;
    private Handler handler;

    public Xutils_Get_Post() {
        handler = new Handler(Looper.getMainLooper());
    }

    /**
     * 单列模式
     *
     * @return
     */
    public static Xutils_Get_Post getInstance() {
        if (instance == null) {
            synchronized (Xutils_Get_Post.class) {
                if (instance == null) {
                    instance = new Xutils_Get_Post();
                }
            }
        }
        return instance;
    }

    public String initData(Context context, String url) {
        RequestParams params = new RequestParams(url);
        params.addHeader("head", "android"); // 为当前请求添加一个头
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Result = result;
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Result = "error";
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Result = "cancell";
            }

            @Override
            public void onFinished() {
            }
        });
        return Result;
    }

    /**
     * 异步get请求返回结果,json字符串
     *
     * @param result
     * @param callBack
     */
    private void onSuccessResponse(final String result, final XCallBack callBack) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    callBack.onResponse(result);
                }
            }
        });
    }

    // 接口回调
    public interface XCallBack {
        void onResponse(String result);

        void onFail(String result);

        void onCancel(Callback.CancelledException cex);
    }

    /**
     * 普通get请求
     *
     * @param url
     * @param callback
     */
    public void get(String url, final XCallBack callback) {
        String time = String.valueOf(System.currentTimeMillis() / 1000);

        if (TextUtils.isEmpty(Fengmian.openid)) {
            Fengmian.openid = getopenid();
            Bundle b = new Bundle();
            b.putString("openid", Fengmian.openid);
            Message msMessage = new Message();
            msMessage.what = 0x007;
            msMessage.setData(b);
            if (MainActivity.handler != null) {
                MainActivity.handler.sendMessage(msMessage);
            }

        }

        String url1 = "&timestamp=" + time + "&sign="
                + MD5.md5("wHljJNEhrNdLGTZX" + time) + "&openid="
                + Fengmian.openid + "&uid=" + Fengmian.uid
                + "&jpush_registration_id=" + Fengmian.regid + "&app_type="
                + "android" + Fengmian.msg_qudao + "&app_version=5.6.8";
        url = url + url1;

        RequestParams params = new RequestParams(url);
        if (TextUtils.isEmpty(Fengmian.phpsid)) {
            params.addHeader("Cookie", "PHPSESSID=" + Fengmian.openid);
        } else
            params.addHeader("Cookie", "PHPSESSID=" + Fengmian.phpsid);

        x.http().get(params, new Callback.CommonCallback<String>() {
            private boolean hasError = false;
            private String result = null;

            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    this.result = result;
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                hasError = true;
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
                if (!hasError && result != null) {
                    onSuccessResponse(result, callback);
                }
            }
        });
    }

    /**
     * 异步post请求
     *
     * @param url
     * @param maps
     * @param callback
     */
    public void post(String url, Map<String, String> maps, final XCallBack callback) {
        RequestParams params = new RequestParams(url);
        if (null != maps && !maps.isEmpty()) {
            for (Map.Entry<String, String> entry : maps.entrySet()) {
                params.addBodyParameter(entry.getKey(), entry.getValue());
            }
        }
        String time = String.valueOf(System.currentTimeMillis() / 1000);
        params.addParameter("timestamp", time);
        params.addParameter("sign", MD5.md5("wHljJNEhrNdLGTZX" + time));
        if (TextUtils.isEmpty(Fengmian.openid)) {
            Fengmian.openid = getopenid();
            params.addParameter("openid", Fengmian.openid);
            Bundle b = new Bundle();
            b.putString("openid", Fengmian.openid);
            Message msMessage = new Message();
            msMessage.what = 0x007;
            msMessage.setData(b);
            if (MainActivity.handler != null) {
                MainActivity.handler.sendMessage(msMessage);
            }
        } else
            params.addParameter("openid", Fengmian.openid);
        params.addParameter("uid", Fengmian.uid);
        params.addParameter("jpush_registration_id", Fengmian.regid);
        params.addParameter("app_type", "android");
        params.addParameter("app_version", "5.6.8");
        params.addParameter("channel", Fengmian.msg_qudao1);

        // System.out.println(Fengmian.openid);
        if (TextUtils.isEmpty(Fengmian.phpsid)) {
            params.addHeader("Cookie", "PHPSESSID=" + Fengmian.openid);
        } else
            params.addHeader("Cookie", "PHPSESSID=" + Fengmian.phpsid);
        x.http().post(params, new Callback.CommonCallback<String>() {
            private boolean hasError = false;
            private String result = null;

            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    this.result = result;
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                hasError = true;
                onFailResponse(ex.getMessage(), callback);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                onCancelResponse(cex, callback);
            }

            @Override
            public void onFinished() {
                if (!hasError && result != null) {
                    onSuccessResponse(result, callback);
                }
            }
        });
    }

    public void posttest(String url, Map<String, String> maps,
                         final XCallBack callback) {
        RequestParams params = new RequestParams(url);
        if (null != maps && !maps.isEmpty()) {
            for (Map.Entry<String, String> entry : maps.entrySet()) {
                params.addBodyParameter(entry.getKey(), entry.getValue());
            }
        }
        x.http().post(params, new Callback.CommonCallback<String>() {
            private boolean hasError = false;
            private String result = null;

            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    this.result = result;
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                hasError = true;
                onFailResponse(ex.getMessage(), callback);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                onCancelResponse(cex, callback);
            }

            @Override
            public void onFinished() {
                if (!hasError && result != null) {
                    onSuccessResponse(result, callback);
                }
            }
        });
    }

    private void onFailResponse(final String result, final XCallBack callBack) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    callBack.onFail(result);
                }
            }
        });
    }

    private void onCancelResponse(final Callback.CancelledException cex,
                                  final XCallBack callBack) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    callBack.onCancel(cex);
                }
            }
        });
    }

    public interface XDownLoadCallBack {
        void onstart();

        void onLoading(long total, long current, boolean isDownloading);

        void onSuccess(File result);

        void onFail(String result);

        void onFinished();
    }

    /**
     * 下载文件
     *
     * @param url
     * @param filePath
     * @param callback
     */
    public void downFile(String url, String filePath,
                         final XDownLoadCallBack callback) {
        RequestParams params = new RequestParams(url);
        params.setSaveFilePath(filePath);
        params.setAutoRename(true);
        x.http().get(params, new Callback.ProgressCallback<File>() {
            @Override
            public void onSuccess(final File result) {
                // 下载完成会走该方法
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (callback != null) {
                            callback.onSuccess(result);
                        }
                    }
                });
            }

            @Override
            public void onError(final Throwable ex, boolean isOnCallback) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (null != callback) {
                            callback.onFail(ex.getMessage());
                        }
                    }
                });
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (callback != null) {
                            callback.onFinished();
                        }
                    }
                });
            }

            // 网络请求之前回调
            @Override
            public void onWaiting() {
            }

            // 网络请求开始的时候回调
            @Override
            public void onStarted() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (null != callback) {
                            callback.onstart();
                        }
                    }
                });
            }

            // 下载的时候不断回调的方法
            @Override
            public void onLoading(final long total, final long current,
                                  final boolean isDownloading) {
                // 当前进度和文件总大小
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (callback != null) {
                            callback.onLoading(total, current, isDownloading);
                        }
                    }
                });
            }
        });
    }

    /**
     * 文件上传
     *
     * @param url
     * @param maps
     * @param file
     * @param callback
     */
    public void upLoadFile(String url, Map<String, String> maps,
                           Map<String, File> file, final XCallBack callback) {
        RequestParams params = new RequestParams(url);
        if (maps != null && !maps.isEmpty()) {
            for (Map.Entry<String, String> entry : maps.entrySet()) {
                params.addBodyParameter(entry.getKey(), entry.getValue());
            }
        }
        if (file != null) {
            for (Map.Entry<String, File> entry : file.entrySet()) {
                params.addBodyParameter(entry.getKey(), entry.getValue()
                        .getAbsoluteFile());
            }
        }
        // 有上传文件时使用multipart表单, 否则上传原始文件流.
        params.setMultipart(true);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                onSuccessResponse(result, callback);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {
                onCancelResponse(cex, callback);
            }

            @Override
            public void onFinished() {

            }
        });

    }

    /**
     * 上传Json串到服务器
     *
     * @param url
     * @param maps 将需要传的各个参数放在Map集合里面
     */
    public void upLoadJson(String url, Map<String, String> maps,
                           final XCallBack callback) {
        JSONObject js_request = new JSONObject();// 服务器需要传参的json对象
        try {
            for (Map.Entry<String, String> entry : maps.entrySet()) {
                js_request.put(entry.getKey(), entry.getValue());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestParams params = new RequestParams(url);
        params.setAsJsonContent(true);
        params.setBodyContent(js_request.toString());

        x.http().post(params, new Callback.CommonCallback<String>() {// 发起传参为json的post请求，
            // Callback.CacheCallback<String>的泛型为后台返回数据的类型，
            // 根据实际需求更改
            private boolean hasError = false;
            private String result = null;

            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    this.result = result;
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ex.printStackTrace();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                onCancelled(cex);
            }

            @Override
            public void onFinished() {
                if (!hasError && result != null && callback != null) {
                    onSuccessResponse(result, callback);
                }
            }
        });
    }

    public String getopenid() {
        String str = "";
        for (int i = 0; i < 20; i++) {
            str = str + (char) (Math.random() * 26 + 'a');
        }
        return md5(str + String.valueOf(System.currentTimeMillis() / 1000));
    }

    public String md5(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            StringBuilder result = new StringBuilder();
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result.append(temp);
            }
            return result.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
