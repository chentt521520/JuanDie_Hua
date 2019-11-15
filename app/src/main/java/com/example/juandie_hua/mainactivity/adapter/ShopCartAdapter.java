package com.example.juandie_hua.mainactivity.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.juandie_hua.R;
import com.example.juandie_hua.calender.utils.ImageUtils;
import com.example.juandie_hua.model.ShopCartGood;
import com.example.juandie_hua.utils.TextViewUtils;

import java.util.List;

public class ShopCartAdapter extends BaseAdapter {
    private Context context;
    private List<ShopCartGood> list;
    private number_change change;

    public ShopCartAdapter(Context context, List<ShopCartGood> list) {
        this.context = context;
        this.list = list;
    }

    public void refresh(List<ShopCartGood> list) {
        this.list = list;
        notifyDataSetChanged();
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

    public void setnumber_change(number_change modifyCountInterface) {
        this.change = modifyCountInterface;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final addview add;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_shop_cart, null);
            add = new addview();
            add.view = convertView.findViewById(R.id.item_shop_cart_view);
            add.good_image = convertView.findViewById(R.id.item_shop_cart_good_image);
            add.good_name = convertView.findViewById(R.id.item_shop_cart_good_name);
            add.good_type = convertView.findViewById(R.id.item_shop_cart_good_type);
            add.good_specs = convertView.findViewById(R.id.item_shop_cart_good_specs);
            add.good_count_de = convertView.findViewById(R.id.item_shop_cart_good_count_de);
            add.good_count_increase = convertView.findViewById(R.id.item_shop_cart_good_count_increase);
            add.good_count = convertView.findViewById(R.id.item_shop_cart_good_count);
            add.good_delete = convertView.findViewById(R.id.item_shop_cart_good_delete);
            add.good_price = convertView.findViewById(R.id.item_shop_cart_good_price);
            add.good_orgprice = convertView.findViewById(R.id.item_shop_cart_good_orgprice);

            convertView.setTag(add);
        } else
            add = (addview) convertView.getTag();

        final ShopCartGood data = list.get(position);

        if (TextUtils.equals(data.getIs_festival(), "1")) {
            add.good_price.setText("￥" + data.getGoods_price() + "(节日价)");
        } else
            add.good_price.setText("￥" + data.getGoods_price());

        TextViewUtils.setTextAddLine(add.good_orgprice);
        add.good_orgprice.setText("￥" + data.getMarket_price());

        String name = data.getGoods_name();
        if (name.contains("-")) {
            add.good_type.setText(name.split("-")[0]);
            add.good_name.setText(name.split("-")[1]);
            add.good_name.setVisibility(View.VISIBLE);
        } else {
            add.good_type.setText(data.getGoods_name());
            add.good_name.setVisibility(View.GONE);
        }

        ImageUtils.setImage(context.getApplicationContext(), data.getGoods_thumb(), add.good_image);

        add.good_count.setText(data.getGoods_number());
        if (list.get(position).getGoods_number().equals("1")) {
            add.good_count_de.setTextColor(0xff999999);
        } else
            add.good_count_de.setTextColor(0xff666666);

        add.good_specs.setVisibility(TextUtils.isEmpty(data.getGoods_attr()) ? View.GONE : View.VISIBLE);
        add.good_specs.setText(data.getGoods_attr().trim());

        add.good_specs.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                change.selectSpecs(position);
            }
        });

        add.good_count_de.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                change.jian(position, add.good_count);
                if (add.good_count.getText().toString().equals("1")) {
                    add.good_count_de.setTextColor(0xff999999);
                } else
                    add.good_count_de.setTextColor(0xff666666);
            }
        });
        add.good_count_increase.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                change.jia(position, add.good_count);
                if (add.good_count.getText().toString().equals("1")) {
                    add.good_count_de.setTextColor(0xff999999);
                } else
                    add.good_count_de.setTextColor(0xff666666);
            }
        });
        add.good_delete.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                change.detel(data.getRec_id());
            }
        });

        add.view.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                change.item(position, add.view);
//                Intent i = new Intent();
//                i.putExtra("goods_id", data.getGoods_id());
//                i.setClass(context, GoodDetailsAty.class);
//                context.startActivity(i);
//                ((Activity) context).overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
        add.good_count.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (Integer.parseInt(charSequence.toString()) > 99) {
                    Toast.makeText(context, "数量不能超过99", Toast.LENGTH_SHORT).show();
                    add.good_count.setText("99");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        return convertView;
    }

    public class addview {
        LinearLayout view;
        ImageView good_image, good_delete;
        TextView good_name, good_type, good_specs, good_price, good_orgprice;
        Button good_count_increase, good_count_de;
        EditText good_count;
    }

    public interface number_change {
        void item(int position, View view);

        void jia(int position, View view);

        void jian(int position, View view);

        void ed_change(int position, View view, int number);

        void detel(String id);

        void selectSpecs(int pos);

    }

}
