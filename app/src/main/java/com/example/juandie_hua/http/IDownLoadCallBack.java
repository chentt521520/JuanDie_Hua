package com.example.juandie_hua.http;

import java.io.File;

public interface IDownLoadCallBack {
    void onstart();

    void onLoading(long total, long current, boolean isDownloading);

    void onSuccess(File result);

    void onFail(String result);

    void onFinished();
}
