package com.example.juandie_hua.mainactivity.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.juandie_hua.R;
import com.example.juandie_hua.calender.utils.ImageUtils;
import com.example.juandie_hua.model.ShopCartRecommond;

import java.util.List;

public class ShopCartRecommondAdapter extends RecyclerView.Adapter<ShopCartRecommondAdapter.MyViewHolder> {


    private OnListItemClickListener onListItemClickListener;

    public void setOnViewClickListener(OnListItemClickListener onListItemClickListener) {
        this.onListItemClickListener = onListItemClickListener;
    }

    /*
     *
     * 属性一般俩一个是数据源
     * 另一个是上下问对象
     *
     * */
    private Context context;
    private List<ShopCartRecommond> strings;

    /*
     *
     * 构造方法初始化数据
     *
     * */
    public ShopCartRecommondAdapter(Context context, List<ShopCartRecommond> strings) {
        this.context = context;
        this.strings = strings;
    }

    public void freshList(List<ShopCartRecommond> strings) {
        this.strings = strings;
        notifyDataSetChanged();
    }

    /*
     * 创建viewHolder
     * 这个主要作用就是找到item布局
     * 把布局里面有用的控件与Viewholder进行关联
     * */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_shop_cart_recommond, parent, false);
        return new MyViewHolder(view);
    }

    /*
     * 绑定viewholder
     * 主要是数据源与viewHolder的绑定
     * 这里可以实现监听事件
     * */
    @Override
    public void onBindViewHolder(@NonNull ShopCartRecommondAdapter.MyViewHolder myViewHolder, final int i) {
        ImageUtils.setImage(context, strings.get(i).getThumb(), myViewHolder.image);
        myViewHolder.goodName.setText(strings.get(i).getName());
        myViewHolder.goodPrice.setText(strings.get(i).getShop_price());
        myViewHolder.goodOrgPrice.setText(strings.get(i).getMarket_price());
        /*
         * 接口回调方式实现监听事件
         * */
        myViewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onListItemClickListener != null) {
                    onListItemClickListener.onItemClickListener(i);
                }
            }
        });

        myViewHolder.addShopCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onListItemClickListener != null) {
                    onListItemClickListener.onAddCartClickListener(i);
                }
            }
        });
    }

    /*
     * 获取数据源条数
     * */
    @Override
    public int getItemCount() {
        return strings == null ? 0 : strings.size();
    }

    /*
     * viewholder与布局中的控件关联
     * view代表一个item
     * */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout view;
        private ImageView image, addShopCart;
        private TextView goodName, goodPrice, goodOrgPrice;

        public MyViewHolder(View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.item_view);
            image = itemView.findViewById(R.id.item_shop_cart_recommond_good_image);
            addShopCart = itemView.findViewById(R.id.item_shop_cart_recommond_add_shoping_car);
            goodName = itemView.findViewById(R.id.item_shop_cart_recommond_good_name);
            goodPrice = itemView.findViewById(R.id.item_shop_cart_recommond_good_price);
            goodOrgPrice = itemView.findViewById(R.id.item_shop_cart_recommond_good_orgprice);

//            ViewUtils.setviewhw_lin(view, DensityUtil.getScreenWidth()/3, DensityUtil.dip2px(200), 0, 0, 0, 0);
        }
    }
}
