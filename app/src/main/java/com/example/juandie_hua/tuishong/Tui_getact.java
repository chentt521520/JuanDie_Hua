package com.example.juandie_hua.tuishong;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import cn.jpush.android.api.JPushInterface;

import com.example.juandie_hua.R;
import com.example.juandie_hua.ui.MainActivity;

public class Tui_getact extends Activity {

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView tv = new TextView(this);
		tv.setText("用户自定义打开的Activity");
		Intent intent = getIntent();
		if (null != intent) {
			Bundle bundle = getIntent().getExtras();
			String title = null;
			String content = null;
			String type = null;
			if (bundle != null) {
				type = bundle.getString(JPushInterface.EXTRA_EXTRA);
				title = bundle
						.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
				content = bundle.getString(JPushInterface.EXTRA_ALERT);
			}
			// tv.setText("Title : " + title + "  " + "Content : " + content
			// + "  type=" + type + "  "
			// + JPushInterface.getRegistrationID(getApplicationContext()));
			try {
				JSONObject jso = new JSONObject(
						bundle.getString(JPushInterface.EXTRA_EXTRA));
				tv.setText("Title : "
						+ title
						+ "  "
						+ "Content : "
						+ content
						+ "  type="
						+ type
						+ "  "
						+ JPushInterface
								.getRegistrationID(getApplicationContext())
						+ "\n" + jso.getJSONObject("click").toString());

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		addContentView(tv, new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			startActivity(new Intent(Tui_getact.this, MainActivity.class));
			overridePendingTransition(R.anim.push_right_out,
					R.anim.push_right_in);
			finish();
			return false;
		}
		return false;
	}

}
