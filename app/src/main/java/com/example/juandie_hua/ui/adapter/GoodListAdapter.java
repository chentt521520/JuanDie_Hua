package com.example.juandie_hua.ui.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.juandie_hua.R;
import com.example.juandie_hua.calender.utils.ImageUtils;
import com.example.juandie_hua.mainactivity.adapter.OnGoodListCallback;
import com.example.juandie_hua.mainactivity.fenlei.ss.gdlist_adaData;
import com.example.juandie_hua.utils.DecimalUtil;
import com.example.juandie_hua.utils.TextViewUtils;
import com.example.juandie_hua.utils.ViewUtils;

import org.xutils.common.util.DensityUtil;

public class GoodListAdapter extends BaseAdapter {
    private Context context;
    private List<gdlist_adaData> list;

    public GoodListAdapter(Context context, List<gdlist_adaData> list) {
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
        addview add;
        if (convertView == null) {
            add = new addview();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_goods_list_index, parent, false);

            add.image = convertView.findViewById(R.id.item_good_image);
            add.addShopCart = convertView.findViewById(R.id.item_add_shoping_car);
            add.goodName = convertView.findViewById(R.id.item_good_name);
            add.goodType = convertView.findViewById(R.id.item_good_type);
            add.goodPrice = convertView.findViewById(R.id.item_good_price);
            add.goodOrgPrice = convertView.findViewById(R.id.item_good_orgprice);
            add.view = convertView.findViewById(R.id.item_goods_view);

            int with = DensityUtil.getScreenWidth() - DensityUtil.dip2px(20);
            ViewUtils.setviewhw_lin(add.image, with / 2, with / 2, 0, 0, 0, 0);
            convertView.setTag(add);
        } else
            add = (addview) convertView.getTag();

        final gdlist_adaData data = list.get(position);

        ImageUtils.setImage(context.getApplicationContext(), data.getUrl(), add.image, R.mipmap.pic_loading);
        String name = list.get(position).getName();
        if (name.contains("-")) {
            add.goodType.setText(name.split("-")[0]);
            add.goodName.setText(name.split("-")[1]);
        } else {
            add.goodType.setText(name);
            add.goodName.setText("");
        }
        add.goodPrice.setText(DecimalUtil.priceAddDecimal(data.getPrice()));
        add.goodOrgPrice.setText(DecimalUtil.priceAddDecimal(data.getMarketPrice()));
        TextViewUtils.setTextAddLine(add.goodOrgPrice);

        /*
         * 接口回调方式实现监听事件
         */
        add.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (callback != null) {
                    callback.setOnItemClickCallback(view, data);
                }
            }
        });

        add.addShopCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback != null) {
                    callback.setOnAddShopCallback(v, data);
                }
            }
        });

        return convertView;
    }

    public class addview {
        ImageView image, addShopCart;
        TextView goodName, goodType, goodPrice, goodOrgPrice;
        RelativeLayout view;
    }

    private OnGoodListCallback<gdlist_adaData> callback;

    public void setOnGoodListCallback(OnGoodListCallback<gdlist_adaData> callback) {
        this.callback = callback;
    }
}
