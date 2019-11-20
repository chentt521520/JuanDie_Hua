package com.example.juandie_hua.http;

import org.xutils.common.Callback;

public interface IRequestCallback {
    void onResponse(String result);

    void onFail(String result);

    void onCancel(Callback.CancelledException cex);
}
