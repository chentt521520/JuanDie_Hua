package com.example.juandie_hua.percenter.myorder;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;

//import com.bumptech.glide.Glide;
import com.example.juandie_hua.R;
import com.example.juandie_hua.calender.utils.ImageUtils;
import com.example.juandie_hua.ui.good.GoodDetailsAty;

public class myorder_adaptertwo extends BaseAdapter {
    Context context;
    List<myorder_adaDatatwo> list;
    PopupWindow window;

    public myorder_adaptertwo(Context context, List<myorder_adaDatatwo> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final addview add;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.ordertwo, null);
            add = new addview();
            setview(add, convertView);

            setviewhw(add);

            convertView.setTag(add);
        } else
            add = (addview) convertView.getTag();
        add.te_title.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.putExtra("goods_id", list.get(position).getgoods_id());
                i.setClass(context, GoodDetailsAty.class);
                context.startActivity(i);
                ((Activity) context).overridePendingTransition(
                        R.anim.push_left_in, R.anim.push_left_out);
            }
        });
        add.im_tp.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.putExtra("goods_id", list.get(position).getgoods_id());
                i.setClass(context, GoodDetailsAty.class);
                context.startActivity(i);
                ((Activity) context).overridePendingTransition(
                        R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        myorder_adaDatatwo data = list.get(position);

        String url = data.getUrl();

        ImageUtils.setImage(context.getApplicationContext(), url, add.im_tp);
//		Glide.with(context.getApplicationContext()).load(url).into(add.im_tp);
        add.te_title.setText(data.getGoodsName());

        String price = data.getPrice();
        if (!price.equals("无")) {
            if (!price.contains("￥")) {
                price = "￥ " + data.getPrice();
            }
            add.te_price.setText(price);
        } else
            add.te_price.setText("");
        add.te_number.setText("X" + data.getnumber());

        return convertView;
    }

    public class addview {
        LinearLayout lin_zhong;
        ImageView im_tp;
        TextView te_title, te_price, te_number;
    }

    private void setviewhw_lin(View v, int width, int height, int left,
                               int top, int right, int bottom) {
        LayoutParams lp = new LayoutParams(width, height);
        lp.setMargins(left, top, right, bottom);
        v.setLayoutParams(lp);
    }

    private void setviewhw(addview add) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int w = dm.widthPixels;

        setviewhw_lin(add.lin_zhong, w, (int) (w * 107 / 375.0), 0, 0, 0, 0);
        setviewhw_lin(add.im_tp, (int) (w * 77 / 375.0),
                (int) (w * 77 / 375.0), (int) (w * 14 / 375.0),
                (int) (w * 15 / 375.0), (int) (w * 50 / 375.0),
                (int) (w * 15 / 375.0));

        setviewhw_lin(add.te_title, LayoutParams.MATCH_PARENT,
                (int) (w * 50 / 375.0), 0, 0, (int) (w * 12 / 375.0), 0);

    }

    private void setview(addview add, View convertView) {
        add.lin_zhong = (LinearLayout) convertView
                .findViewById(R.id.myorderitem_lincon);

        add.im_tp = (ImageView) convertView.findViewById(R.id.myorderitem_imtp);

        add.te_title = (TextView) convertView
                .findViewById(R.id.myorderitem_tetit);
        add.te_price = (TextView) convertView
                .findViewById(R.id.myorderitem_teprice);
        add.te_number = (TextView) convertView
                .findViewById(R.id.myorderitem_tenumber);

    }

}
