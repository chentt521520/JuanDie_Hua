package com.example.juandie_hua.mycar.orderpay;

import java.lang.ref.WeakReference;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.juandie_hua.R;
import com.example.juandie_hua.app.BaseActivity;
import com.example.juandie_hua.mycar.shouhuodizi.tuijianyu;
import com.example.juandie_hua.utils.StatusBarUtils;
import com.umeng.analytics.MobclickAgent;

public class heka extends BaseActivity {

    @ViewInject(R.id.heka_reinp)
    RelativeLayout re_inp;
    @ViewInject(R.id.heka_edheka)
    EditText ed_inp;
    @ViewInject(R.id.heka_tezi)
    TextView te_zi;

    @ViewInject(R.id.ordqr_linheka)
    LinearLayout lin_heka;
    @ViewInject(R.id.ordqr_teheka)
    TextView te_heka;
    @ViewInject(R.id.ordqr_teheka1)
    TextView te_heka1;
    @ViewInject(R.id.ordqr_imhekago)
    ImageView im_hekago;

    @ViewInject(R.id.heka_tequit)
    TextView te_ok;

    public static MyHandler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitleContentView(R.layout.heka);
        StatusBarUtils.with(this).setBarColor(R.color.white_fff);

        x.view().inject(this);
        handler = new MyHandler(heka.this);
        setviewhw();
        setviewlisten();
    }

    private void setviewlisten() {
        te_heka1.setOnClickListener(onc);
        te_ok.setOnClickListener(onc);
        te_heka1.setOnClickListener(onc);

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
        WeakReference<heka> referenceObj;

        public MyHandler(heka activity) {
            referenceObj = new WeakReference<heka>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            heka activity = referenceObj.get();
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
                case R.id.ordqr_teheka1:
                    startActivity(new Intent(heka.this, tuijianyu.class));
                    overridePendingTransition(R.anim.push_left_in,
                            R.anim.push_left_out);
                    break;
                case R.id.heka_tequit:
                    Message msg = Message.obtain();
                    msg.what = 0x003;
                    Bundle bundle = new Bundle();
                    bundle.putString("data", ed_inp.getText().toString());
                    msg.setData(bundle);
                    if (orderin.handler != null) {
                        orderin.handler.sendMessage(msg);
                    }
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

        this.getTitleView().setTitleText("贺卡留言");
        lin_heka.setVisibility(View.VISIBLE);
        ed_inp.setText(getIntent().getExtras().getString("hk"));
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int w = dm.widthPixels;

        setviewhw_lin(re_inp, w, (int) (w * 600 / 750.0), 0, 0, 0, 0);
        setviewhw_re(ed_inp, (int) (w * 702 / 750.0), (int) (w * 580 / 750.0),
                (int) (w * 24 / 750.0), (int) (w * 10 / 750.0),
                (int) (w * 24 / 750.0), (int) (w * 10 / 750.0));
        te_zi.setPadding(0, 0, (int) (w * 30 / 750.0), (int) (w * 15 / 750.0));
        setviewhw_lin(te_ok, w - (int) (w * 48 / 750.0),
                (int) (w * 88 / 750.0), (int) (w * 24 / 750.0), 0,
                (int) (w * 24 / 750.0), 0);

        setviewhw_lin(lin_heka, w - (int) (w * 26 / 375.0),
                (int) (w * 40 / 375.0), (int) (w * 14 / 375.0), 0,
                (int) (w * 12 / 375.0), 0);

        setviewhw_lin(te_heka1, LayoutParams.WRAP_CONTENT,
                (int) (w * 40 / 375.0), 0, 0, (int) (w * 6 / 375.0), 0);
        setviewhw_lin(im_hekago, (int) (w * 10 / 375.0),
                (int) (w * 40 / 375.0), (int) (w * 4 / 375.0),
                (int) (w * 1 / 375.0), 0, 0);

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
