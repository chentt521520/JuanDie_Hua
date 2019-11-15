package com.example.juandie_hua.mainactivity.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.juandie_hua.R;
import com.example.juandie_hua.calender.utils.ImageUtils;
import com.example.juandie_hua.model.IndexRecommond;
import com.example.juandie_hua.utils.DecimalUtil;
import com.example.juandie_hua.utils.TextViewUtils;
import com.example.juandie_hua.utils.ViewUtils;

import org.xutils.common.util.DensityUtil;

import java.util.List;


public class IndexGoodRecAdapter extends BaseAdapter {
    private Context context;
    private List<IndexRecommond> list;

    public IndexGoodRecAdapter(Context context, List<IndexRecommond> list) {
        this.context = context;
        this.list = list;
    }

    public void refresh(List<IndexRecommond> list) {
        this.list = list;
        notifyDataSetChanged();
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

            add.goodView = convertView.findViewById(R.id.item_show_good);
            add.image = convertView.findViewById(R.id.item_good_image);
            add.sortImage = convertView.findViewById(R.id.item_good_sort_image);
            add.addShopCart = convertView.findViewById(R.id.item_add_shoping_car);
            add.goodName = convertView.findViewById(R.id.item_good_name);
            add.goodType = convertView.findViewById(R.id.item_good_type);
            add.goodPrice = convertView.findViewById(R.id.item_good_price);
            add.goodOrgPrice = convertView.findViewById(R.id.item_good_orgprice);
            add.view = convertView.findViewById(R.id.item_goods_view);

            int height = (DensityUtil.getScreenWidth() - DensityUtil.dip2px(30)) / 2;
            ViewUtils.setviewhw_lin(add.image, height, height, 0, 0, 0, 0);

            int Kw = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            int Kh = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);

            add.goodView.measure(Kw, Kh);
            int Kheight = add.goodView.getMeasuredHeight();
            int Kwidth = add.goodView.getMeasuredWidth();

            ViewUtils.setviewhw_re(add.sortImage, -1, Kheight, 0, 0, 0, 0);

            convertView.setTag(add);
        } else
            add = (addview) convertView.getTag();

        final IndexRecommond data = list.get(position);

        if (TextUtils.equals(data.getType(), "goods")) {
            add.sortImage.setVisibility(View.GONE);
            add.goodView.setVisibility(View.VISIBLE);

            String name = data.getName();
            if (name.contains("-")) {
                add.goodType.setText(name.split("-")[0]);
                add.goodName.setText(name.split("-")[1]);
            } else {
                add.goodType.setText(name);
            }
            ImageUtils.setImage(context, data.getGoods_thumb(), add.image);
            add.goodPrice.setText(DecimalUtil.priceAddDecimal(data.getShop_price()));
            add.goodOrgPrice.setText(DecimalUtil.priceAddDecimal(data.getMarket_price()));
            TextViewUtils.setTextAddLine(add.goodOrgPrice);

        } else {//category
            add.sortImage.setVisibility(View.VISIBLE);
            add.goodView.setVisibility(View.GONE);
            ImageUtils.setImage(context, data.getImg(), add.sortImage);
        }

        add.addShopCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.setOnAddShopCallback(v, data);
            }
        });

        add.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.setOnItemClickCallback(v, data);
            }
        });
        return convertView;
    }

    private OnGoodListCallback<IndexRecommond> callback;

    public void setOnGoodListCallback(OnGoodListCallback<IndexRecommond> callback) {
        this.callback = callback;
    }

    public class addview {
        ImageView image, addShopCart, sortImage;
        TextView goodName, goodType, goodPrice, goodOrgPrice;
        LinearLayout goodView;
        RelativeLayout view;
    }

}
