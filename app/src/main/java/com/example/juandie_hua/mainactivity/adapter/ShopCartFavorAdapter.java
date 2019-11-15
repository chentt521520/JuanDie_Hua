package com.example.juandie_hua.mainactivity.adapter;

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
import com.example.juandie_hua.model.ShopCartFavor;
import com.example.juandie_hua.utils.DecimalUtil;
import com.example.juandie_hua.utils.TextViewUtils;
import com.example.juandie_hua.utils.ViewUtils;

import org.xutils.common.util.DensityUtil;

import java.util.List;

public class ShopCartFavorAdapter extends BaseAdapter {

    private Context context;
    private List<ShopCartFavor> favorList;

    public ShopCartFavorAdapter(Context context, List<ShopCartFavor> favorList) {
        this.context = context;
        this.favorList = favorList;
    }

    public void refresh(List<ShopCartFavor> favorList) {
        this.favorList = favorList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return favorList == null ? 0 : favorList.size();
    }

    @Override
    public Object getItem(int position) {
        return favorList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_goods_list_index, parent, false);

            holder.image = convertView.findViewById(R.id.item_good_image);
            holder.addShopCart = convertView.findViewById(R.id.item_add_shoping_car);
            holder.goodName = convertView.findViewById(R.id.item_good_name);
            holder.goodType = convertView.findViewById(R.id.item_good_type);
            holder.goodPrice = convertView.findViewById(R.id.item_good_price);
            holder.goodOrgPrice = convertView.findViewById(R.id.item_good_orgprice);
            holder.view = convertView.findViewById(R.id.item_goods_view);

            int with = DensityUtil.getScreenWidth() - DensityUtil.dip2px(20);
            ViewUtils.setviewhw_lin(holder.image, with / 2, with / 2, 0, 0, 0, 0);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final int i = position;

        final ShopCartFavor data = favorList.get(i);
        ImageUtils.setImage(context, data.getThumb(), holder.image);
        String name = data.getName();
        if (name.contains("-")) {
            holder.goodType.setText(name.split("-")[0]);
            holder.goodName.setText(name.split("-")[1]);
            holder.goodName.setVisibility(View.VISIBLE);
        } else {
            holder.goodType.setText(name);
            holder.goodName.setVisibility(View.INVISIBLE);
        }
        holder.goodPrice.setText(DecimalUtil.priceAddDecimal(data.getShop_price()));
        holder.goodOrgPrice.setText(DecimalUtil.priceAddDecimal(data.getMarket_price()));
        TextViewUtils.setTextAddLine(holder.goodOrgPrice);
        /*
         * 接口回调方式实现监听事件
         */
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (callback != null) {
                    callback.setOnItemClickCallback(view, data);
                }
            }
        });

        holder.addShopCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback != null) {
                    callback.setOnAddShopCallback(v, data);
                }
            }
        });
        return convertView;
    }

    private class ViewHolder {
        ImageView image, addShopCart;
        TextView goodName, goodType, goodPrice, goodOrgPrice;
        RelativeLayout view;
    }

    private OnGoodListCallback<ShopCartFavor> callback;

    public void setOnGoodListCallback(OnGoodListCallback<ShopCartFavor> callback) {
        this.callback = callback;
    }
}
