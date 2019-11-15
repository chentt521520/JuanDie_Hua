package com.example.juandie_hua.mycar.orderpay;

import java.util.ArrayList;
import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;

import com.example.juandie_hua.R;
import com.example.juandie_hua.app.BaseActivity;
import com.example.juandie_hua.mycar.shouhuodizi.gridv_adaData;
import com.example.juandie_hua.mycar.shouhuodizi.gridv_adapter;
import com.example.juandie_hua.utils.StatusBarUtils;
import com.umeng.analytics.MobclickAgent;

public class gonhao extends BaseActivity {

    @ViewInject(R.id.gd_temoren)
    TextView te_moren;

    @ViewInject(R.id.gh_grdv)
    GridView gdv;

    gridv_adapter gdvada;
    List<gridv_adaData> gdlist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitleContentView(R.layout.gonhao);
        StatusBarUtils.with(this).setBarColor(R.color.white_fff);

        x.view().inject(this);
        setviewhw();
        setviewdata();
        setviewlisten();
    }

    private void setviewdata() {
        gdv.setSelector(new ColorDrawable(Color.TRANSPARENT));
        gdlist = new ArrayList<>();
        gdvada = new gridv_adapter(gonhao.this, gdlist);

        gdv.setAdapter(gdvada);

        List<String> gh = getIntent().getStringArrayListExtra("kf");
        int gonghao = getIntent().getIntExtra("pos", 999);
        if (gonghao == 999) {
            te_moren.setSelected(true);
            for (int i = 0; i < gh.size(); i++) {
                gdlist.add(new gridv_adaData(i + "", gh.get(i), false));
            }
        } else {
            te_moren.setSelected(false);
            for (int i = 0; i < gh.size(); i++) {
                if (gonghao == i) {
                    gdlist.add(new gridv_adaData(i + "", gh.get(i), true));
                } else
                    gdlist.add(new gridv_adaData(i + "", gh.get(i), false));
            }
        }

        gdvada.notifyDataSetChanged();
        gdv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Message msg = Message.obtain();
                msg.what = 0x005;
                Bundle bundle = new Bundle();
                bundle.putString("data", gdlist.get(position).getTitle());
                bundle.putInt("pos", position);
                msg.setData(bundle);
                orderin.handler.sendMessage(msg);

                finish();
                overridePendingTransition(R.anim.push_right_out,
                        R.anim.push_right_in);
            }
        });
    }

    private void setviewlisten() {
        te_moren.setOnClickListener(onc);
    }

    OnClickListener onc = new OnClickListener() {

        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id) {
                case R.id.gd_temoren:

                    Message msg = Message.obtain();
                    msg.what = 0x005;
                    Bundle bundle = new Bundle();
                    bundle.putString("data", "默认客服");
                    bundle.putInt("pos", 999);
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
        this.getTitleView().setTitleText("选择客服工号");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            finish();
            overridePendingTransition(R.anim.push_right_out, R.anim.push_right_in);
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
