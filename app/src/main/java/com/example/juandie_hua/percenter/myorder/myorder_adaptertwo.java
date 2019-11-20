package com.example.juandie_hua.percenter.myorder;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.juandie_hua.R;
import com.example.juandie_hua.calender.utils.ImageUtils;
import com.example.juandie_hua.helper.UiHelper;

public class myorder_adaptertwo extends BaseAdapter {
    private Context context;
    private List<myorder_adaDatatwo> list;

    public myorder_adaptertwo(Context context, List<myorder_adaDatatwo> list) {
        this.context = context;
        this.list = list;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final addview add;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.ordertwo, null);
            add = new addview();
            setview(add, convertView);
            convertView.setTag(add);
        } else
            add = (addview) convertView.getTag();
        add.lin_zhong.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                UiHelper.toGoodDetailActivity((Activity) context, list.get(position).getgoods_id());
            }
        });

        myorder_adaDatatwo data = list.get(position);

        String url = data.getUrl();

        ImageUtils.setImage(context.getApplicationContext(), url, add.im_tp);
        add.te_title.setText(data.getGoodsName());
        if (TextUtils.isEmpty(data.getGoodSpec())) {
            add.te_spec.setText("");
        } else {
            add.te_spec.setText("已选" + data.getGoodSpec());
        }

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
        TextView te_title, te_spec, te_price, te_number;
    }

    private void setview(addview add, View convertView) {
        add.lin_zhong = (LinearLayout) convertView.findViewById(R.id.myorderitem_lincon);
        add.im_tp = (ImageView) convertView.findViewById(R.id.myorderitem_imtp);
        add.te_title = (TextView) convertView.findViewById(R.id.myorderitem_tetit);
        add.te_spec = (TextView) convertView.findViewById(R.id.myorderitem_spec);
        add.te_price = (TextView) convertView.findViewById(R.id.myorderitem_teprice);
        add.te_number = (TextView) convertView.findViewById(R.id.myorderitem_tenumber);

    }

}
