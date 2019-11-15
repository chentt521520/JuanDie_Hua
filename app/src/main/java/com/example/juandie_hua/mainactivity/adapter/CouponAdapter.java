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

public class CouponAdapter extends BaseAdapter {

    private Context context;
    private List<Coupon.BonusListBean> couponList;

    public CouponAdapter(Context context, List<Coupon.BonusListBean> couponList) {
        this.context = context;
        this.couponList = couponList;
    }

    public void refresh(List<Coupon.BonusListBean> couponList){
        this.couponList = couponList;
        notifyDataSetChanged();
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
        convertView = LayoutInflater.from(context).inflate(R.layout.item_coupon_show, null);

        TextView coupon = convertView.findViewById(R.id.item_coupon);
        coupon.setText(couponList.get(position).getType_name());

        return convertView;
    }
}
