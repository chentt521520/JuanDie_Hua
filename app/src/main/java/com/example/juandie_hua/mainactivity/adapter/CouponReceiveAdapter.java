package com.example.juandie_hua.mainactivity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.juandie_hua.R;
import com.example.juandie_hua.model.Coupon;

import java.util.List;

public class CouponReceiveAdapter extends BaseAdapter {

    private Context context;
    private List<Coupon.BonusListBean> couponList;

    public CouponReceiveAdapter(Context context, List<Coupon.BonusListBean> couponList) {
        this.context = context;
        this.couponList = couponList;
    }


    @Override
    public int getCount() {
        return couponList == null ? 0 : couponList.size();
    }

    @Override
    public Object getItem(int position) {
        return couponList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item_coupon_receive, null);

        TextView coupon = convertView.findViewById(R.id.item_coupon);
        TextView coupon_detail = convertView.findViewById(R.id.item_coupon_detail);
        Coupon.BonusListBean bonusListBean = couponList.get(position);
        if (bonusListBean.getType_money().contains(".00")) {
            coupon.setText(bonusListBean.getType_money().split("\\.")[0]);
            coupon.setTextSize(40);
        } else {
            coupon.setText(bonusListBean.getType_money());
            coupon.setTextSize(20);
        }
        coupon_detail.setText(couponList.get(position).getType_name());

        return convertView;
    }
}
