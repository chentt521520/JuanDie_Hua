package com.example.juandie_hua.ui.me.adapter;

import java.util.List;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.example.juandie_hua.R;
import com.example.juandie_hua.calender.utils.ImageUtils;
import com.example.juandie_hua.model.ScanHistory;

public class CollectionAdapter extends BaseAdapter {
    private Context context;
    private List<ScanHistory> list;
    private number_change change;

    public CollectionAdapter(Context context, List<ScanHistory> list) {
        this.context = context;
        this.list = list;
    }

    public void refresh(List<ScanHistory> list) {
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
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.mysc_adapter, null);
            add = new addview();
            add.view = (LinearLayout) convertView
                    .findViewById(R.id.mycar_view);
            add.lin1 = (LinearLayout) convertView
                    .findViewById(R.id.myscada_lin1);
            add.im_head = (ImageView) convertView
                    .findViewById(R.id.myscada_imhead);
            add.te_title = (TextView) convertView
                    .findViewById(R.id.myscada_tetit);
            add.te_price = (TextView) convertView
                    .findViewById(R.id.myscada_teprice);
            add.im_dele = (ImageView) convertView
                    .findViewById(R.id.myscada_imdetel);
            add.te_number = (TextView) convertView
                    .findViewById(R.id.myscada_texiaoshou);
            add.v_xuxian = convertView.findViewById(R.id.myscada_xuxian);

            DisplayMetrics dm = context.getResources().getDisplayMetrics();
            int w_screen = dm.widthPixels;
            setviewhw_lin(add.lin1, w_screen, (int) (w_screen * 80 / 375.0),
                    (int) (w_screen * 10 / 375.0),
                    (int) (w_screen * 10 / 375.0),
                    (int) (w_screen * 10 / 375.0),
                    (int) (w_screen * 10 / 375.0));

            setviewhw_lin(add.im_head, (int) (w_screen * 76 / 375.0),
                    (int) (w_screen * 76 / 375.0), 0, 0,
                    (int) (w_screen * 55 / 375.0), 0);
            setviewhw_lin(add.im_dele, (int) (w_screen * 38 / 375.0),
                    (int) (w_screen * 25 / 375.0), 0,
                    (int) (w_screen * 51 / 375.0),
                    (int) (w_screen * 13 / 375.0), 0);
            add.im_dele.setPadding(0, 0, (int) (w_screen * 13 / 375.0), 0);
            setviewhw_lin(add.te_number, (int) (w_screen * 96 / 375.0),
                    (int) (w_screen * 26 / 375.0), 0, 0, 0, 0);

            convertView.setTag(add);
        } else
            add = (addview) convertView.getTag();

        final ScanHistory data = list.get(position);

        if (list.size() - 1 == position) {
            add.v_xuxian.setVisibility(View.GONE);
        } else
            add.v_xuxian.setVisibility(View.VISIBLE);
        if (data.getShop_price().contains("￥")) {
            add.te_price.setText(data.getShop_price());
        } else
            add.te_price.setText("￥" + data.getShop_price());


        ImageUtils.setImage(context.getApplicationContext(), data.getGoods_thumb(), add.im_head);
        add.te_number.setText("已售：" + data.getGoods_sale_number());
        add.te_title.setText(data.getGoods_name());

        add.view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                change.item(position, data.getGoods_id());
            }
        });
        add.im_dele.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                change.detel(position, data.getGoods_id());
            }
        });
        return convertView;
    }

    public class addview {
        LinearLayout lin1, view;
        ImageView im_head;
        TextView te_title, te_price, te_number;
        ImageView im_dele;
        View v_xuxian;
    }

    public interface number_change {

        public void detel(int position, String goods_id);

        public void item(int position, String goods_id);

    }

    private void setviewhw_lin(View v, int width, int height, int left,
                               int top, int right, int bottom) {
        LayoutParams lp = new LayoutParams(width, height);
        lp.setMargins(left, top, right, bottom);
        v.setLayoutParams(lp);
    }

}
