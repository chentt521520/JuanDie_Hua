package com.example.juandie_hua.mycar.orderpay;

import java.lang.ref.WeakReference;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.juandie_hua.R;
import com.example.juandie_hua.app.BaseActivity;
import com.example.juandie_hua.utils.StatusBarUtils;
import com.umeng.analytics.MobclickAgent;

public class beizu extends BaseActivity {

    @ViewInject(R.id.heka_reinp)
    RelativeLayout re_inp;
    @ViewInject(R.id.heka_edheka)
    EditText ed_inp;
    @ViewInject(R.id.heka_tezi)
    TextView te_zi;


    @ViewInject(R.id.heka_tequit)
    TextView te_ok;

    public static MyHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitleContentView(R.layout.heka);
        StatusBarUtils.with(this).setBarColor(R.color.white_fff);

        x.view().inject(this);
        handler = new MyHandler(beizu.this);
        setviewhw();
        setviewlisten();
    }

    private void setviewlisten() {
        te_ok.setOnClickListener(onc);

        ed_inp.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                te_zi.setText((200 - ed_inp.getText().toString().length()) + "");
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public static class MyHandler extends Handler {
        WeakReference<beizu> referenceObj;

        public MyHandler(beizu activity) {
            referenceObj = new WeakReference<beizu>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            beizu activity = referenceObj.get();
            switch (msg.what) {
                case 0x001:
                    Bundle data = msg.getData();
                    activity.ed_inp.setText(data.getString("data"));
                    break;

                default:
                    break;
            }
        }
    }

    OnClickListener onc = new OnClickListener() {

        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id) {
                case R.id.heka_tequit:
                    Message msg = Message.obtain();
                    msg.what = 0x004;
                    Bundle bundle = new Bundle();
                    if (TextUtils.isEmpty(ed_inp.getText().toString())) {
                        bundle.putString("data", "");
                    } else
                        bundle.putString("data", ed_inp.getText().toString());
                    msg.setData(bundle);
                    orderin.handler.sendMessage(msg);

                    finish();
                    overridePendingTransition(R.anim.push_right_out,
                            R.anim.push_right_in);
                    break;

                default:
                    break;
            }

        }
    };

    private void setviewhw() {
        ed_inp.setText(getIntent().getExtras().getString("bz"));
        this.getTitleView().setTitleText("填写备注");
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int w = dm.widthPixels;

        setviewhw_lin(re_inp, w, (int) (w * 500 / 750.0), 0, 0, 0, 0);
        setviewhw_re(ed_inp, (int) (w * 702 / 750.0), (int) (w * 480 / 750.0),
                (int) (w * 24 / 750.0), (int) (w * 10 / 750.0),
                (int) (w * 24 / 750.0), (int) (w * 10 / 750.0));
        te_zi.setPadding(0, 0, (int) (w * 30 / 750.0), (int) (w * 15 / 750.0));
        setviewhw_lin(te_ok, w - (int) (w * 48 / 750.0),
                (int) (w * 88 / 750.0), (int) (w * 24 / 750.0), (int) (w * 24 / 750.0),
                (int) (w * 24 / 750.0), 0);

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

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
