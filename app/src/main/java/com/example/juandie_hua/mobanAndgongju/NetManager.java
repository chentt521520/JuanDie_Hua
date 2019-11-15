package com.example.juandie_hua.mobanAndgongju;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;

public class NetManager {
	Context context;

	public NetManager(Context context) {
		this.context = context;
	}

	// 判断网络是否可用的方法
	public boolean isConnectingToInternet() {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		// 新版本调用方法获取网络状态
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			Network[] networks = connectivityManager.getAllNetworks();
			NetworkInfo networkInfo;
			for (Network mNetwork : networks) {
				networkInfo = connectivityManager.getNetworkInfo(mNetwork);
				if (networkInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
					return true;
				}
			}
		} else {
			// 否则调用旧版本方法
			if (connectivityManager != null) {
				@SuppressWarnings("deprecation")
				NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
				if (info != null) {
					for (NetworkInfo anInfo : info) {
						if (anInfo.getState() == NetworkInfo.State.CONNECTED) {
							Log.d("Network",
									"NETWORKNAME: " + anInfo.getTypeName());
							return true;
						}
					}
				}
			}
		}
		return false;
	}

}
