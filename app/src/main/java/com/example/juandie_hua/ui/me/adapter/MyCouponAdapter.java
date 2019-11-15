package com.example.juandie_hua.ui.me.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.example.juandie_hua.R;
import com.example.juandie_hua.R.drawable;
import com.example.juandie_hua.model.MyCoupon;

public class MyCouponAdapter extends BaseAdapter {
    private Context context;
    private List<MyCoupon> list;
    /**
     * 1，未使用；2，已使用；3已过期
     */
    private int type;

    public MyCouponAdapter(Context context, List<MyCoupon> list, int type) {
        this.context = context;
        this.list = list;
        this.type = type;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        addview add;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_my_coupon, null);
            add = new addview();

            add.lin1 = (LinearLayout) convertView.findViewById(R.id.yhqitem_lin);
            add.lin2 = (LinearLayout) convertView.findViewById(R.id.yhqitem_lin1);
            add.lin3 = (LinearLayout) convertView.findViewById(R.id.yhqadalin3);
            add.te_item2 = (TextView) convertView.findViewById(R.id.yhqitem_te2);
            add.te_item3 = (TextView) convertView.findViewById(R.id.yhqitem_te3);
            add.te_yuse = (TextView) convertView.findViewById(R.id.yhqitem_teyusetime);
            add.te_bianhao = (TextView) convertView.findViewById(R.id.yhqitem_teyhqfw1);
            add.lin3 = (LinearLayout) convertView.findViewById(R.id.yhqadalin3);
            add.te_use1 = (TextView) convertView.findViewById(R.id.yhqitem_teyusetime1);
            add.leibie = (TextView) convertView.findViewById(R.id.yhqitem_tebianhao);

            DisplayMetrics dm = context.getResources().getDisplayMetrics();
            int w = dm.widthPixels;
            setviewhw_lin(add.lin1, w, (int) (w * 210 / 800.0), 0, 0, 0,
                    (int) (w * 40 / 800.0));
            setviewhw_lin(add.lin2, (int) (w * 276 / 800.0),
                    (int) (w * 210 / 800.0), (int) (w * 24 / 800.0), 0, 0, 0);
            setviewhw_lin(add.lin3, (int) (w * 486 / 800.0),
                    (int) (w * 210 / 800.0), 0, 0, (int) (w * 14 / 800.0), 0);

            setviewhw_lin(add.te_item2, (int) (w * 276 / 800.0),
                    (int) (w * 112 / 800.0), 0, (int) (w * 30 / 800.0), 0, 0);
            setviewhw_lin(add.te_item3, (int) (w * 276 / 800.0),
                    (int) (w * 68 / 800.0), 0, (int) (w * 0 / 800.0), 0, 0);

            convertView.setTag(add);
        } else
            add = (addview) convertView.getTag();
        MyCoupon data = list.get(position);
        add.te_item3.setText(data.getType_name());
        switch (type) {
            case 1:
                add.lin2.setBackgroundResource(drawable.wsy1);
                add.lin3.setBackgroundResource(drawable.wsy2);
                add.te_yuse.setVisibility(View.VISIBLE);
                break;
            case 2:
                add.leibie.setBackgroundResource(drawable.roundbackground_yhqf1);
                add.lin2.setBackgroundResource(drawable.ysy1);
                add.lin3.setBackgroundResource(drawable.ysy2);
                add.te_item3.setTextColor(Color.parseColor("#ffffff"));
                add.te_use1.setVisibility(View.GONE);
                break;
            case 3:
                add.leibie.setBackgroundResource(drawable.roundbackground_yhqf1);
                add.lin2.setBackgroundResource(drawable.ysy1);
                add.lin3.setBackgroundResource(drawable.ygq2);
                add.te_item3.setTextColor(Color.parseColor("#ffffff"));
                add.te_use1.setVisibility(View.GONE);
                break;
        }
        add.te_bianhao.setText("优惠码: " + data.getBonus_sn());
        add.te_item2.setText("￥" + data.getType_money());
        Spannable span = new SpannableString(add.te_item2.getText());
        span.setSpan(new AbsoluteSizeSpan(30), 0, 1,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        add.te_item2.setText(span);
        if (data.getUse_time_deadline().contains("：")) {
//			String str = data
//					.getUse_time_deadline()
//					.substring(data.getUse_time_deadline().indexOf("：") + 1, data.getUse_time_deadline().length()-1).replace(" ", "");
//			add.te_yuse.setText(str);
            add.te_yuse.setText(data.getUse_time_deadline().split("：")[1]);
        } else
            add.te_yuse.setText(data.getUse_time_deadline());
        add.leibie.setText(data.getType_category_name());

        // add.te_use1.setOnClickListener(new OnClickListener() {
        //
        // @Override
        // public void onClick(View v) {
        // context.startActivity(new Intent(context, haha.class));
        // ((Activity) context).overridePendingTransition(
        // R.anim.push_left_in, R.anim.push_left_out);
        // haha.handler.sendEmptyMessage(0x01);
        // }
        // });

        return convertView;
    }

    public void refresh(List<MyCoupon> list, int type) {
        this.list = list;
        this.type = type;
        notifyDataSetChanged();
    }

    public class addview {
        LinearLayout lin1, lin2, lin3;
        TextView te_item2, te_item3, te_bianhao, te_yuse;
        TextView te_fw, te_use, te_lx, te_use1, leibie;
    }

    private void setviewhw_lin(View v, int width, int height, int left,
                               int top, int right, int bottom) {
        LayoutParams lp = new LayoutParams(width, height);
        lp.setMargins(left, top, right, bottom);
        v.setLayoutParams(lp);
    }
}
