package com.example.juandie_hua.mainactivity;

import java.util.List;

import android.content.Context;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

//import com.bumptech.glide.Glide;
import com.example.juandie_hua.R;
import com.example.juandie_hua.calender.utils.ImageUtils;
import com.example.juandie_hua.utils.ViewUtils;

public class syeada_gridlist extends BaseAdapter {
    Context context;
    List<syeada_gridlistData> list;

    public syeada_gridlist(Context context, List<syeada_gridlistData> list) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        addview add;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.syeada_gridlist, null);
            add = new addview();
            findview(add, convertView);
            setviewhw(add, convertView);
            convertView.setTag(add);
        } else
            add = (addview) convertView.getTag();
        if (position == list.size() - 1) {
            if (!add.lin_part2.isShown()) {
                add.lin_part2.setVisibility(View.VISIBLE);
                add.lin_part1.setVisibility(View.GONE);
            }
        } else {
            if (!add.lin_part1.isShown()) {
                add.lin_part1.setVisibility(View.VISIBLE);
                add.lin_part2.setVisibility(View.GONE);
            }
        }
        final syeada_gridlistData data = list.get(position);
        if (position == list.size() - 1) {
            ImageUtils.setImage(context, data.getUrl(), add.im_tp2);
        } else {
            ImageUtils.setImage(context, data.getUrl(), add.im_tp);
            add.te_name.setText(data.getGoods_name());
            if (!data.getPrice().contains(".")) {
                add.te_prce.setText("￥" + data.getPrice() + ".00");
            } else
                add.te_prce.setText("￥" + data.getPrice());

            if (!data.getPricesc().contains(".")) {
                add.te_pricesc.setText(data.getPricesc() + ".00");
            } else
                add.te_pricesc.setText(data.getPricesc());
            add.te_context.setText(data.getText());
        }

        add.add_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.setOnClickListener(v, data);
            }
        });
        return convertView;
    }

    public OnIndexGoodListener listener;

    public interface OnIndexGoodListener {
        void setOnClickListener(View view, syeada_gridlistData data);
    }

    public void setOnClickListener(OnIndexGoodListener listener) {
        this.listener = listener;
    }

    public class addview {
        LinearLayout lin_part1, lin_part2;
        ImageView im_tp, im_sc, im_tp2, add_shop;
        TextView te_name, te_context, te_prce, te_pricesc;
        RelativeLayout re_tp, re_bot;

    }

    public void findview(addview add, View v) {
        add.lin_part1 = (LinearLayout) v.findViewById(R.id.syeada_part1);
        add.lin_part2 = (LinearLayout) v.findViewById(R.id.syeada_linpart2);
        add.re_tp = (RelativeLayout) v.findViewById(R.id.syeada_retp);
        add.im_tp = (ImageView) v.findViewById(R.id.syeada_imsp);
        add.im_tp2 = (ImageView) v.findViewById(R.id.syeada_imsp2);
        add.im_sc = (ImageView) v.findViewById(R.id.syeada_imsc);

        add.te_name = (TextView) v.findViewById(R.id.syeada_tename);
        add.te_context = (TextView) v.findViewById(R.id.syeada_tecon);
        add.te_prce = (TextView) v.findViewById(R.id.syeada_teprice1);
        add.te_pricesc = (TextView) v.findViewById(R.id.syeada_teprice1sc);
        add.te_pricesc.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        add.re_bot = (RelativeLayout) v.findViewById(R.id.syeada_repr);
        add.add_shop = v.findViewById(R.id.item_index_add_shop_cart);
    }

    public void setviewhw(addview add, View v) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int w = dm.widthPixels;
		ViewUtils.setviewhw_lin(add.re_tp, LayoutParams.MATCH_PARENT, (int) (w * 245 / 512.0), 0, 0, 0, 0);
        ViewUtils.setviewhw_re(add.im_tp, (int) (w * 242 / 512.0), (int) (w * 245 / 512.0), 0, 0, 0, 0);
        ViewUtils.setviewhw_re(add.im_sc, (int) (w * 24 / 512.0), (int) (w * 24 / 512.0), (int) (w * 30 / 512.0), (int) (w * 195 / 512.0), 0, 0);
        ViewUtils.setviewhw_lin(add.te_name, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, (int) (w * 10 / 512.0), (int) (w * 7 / 512.0), 0, (int) (w * 7 / 512.0));
        ViewUtils.setviewhw_lin(add.te_context, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, (int) (w * 10 / 512.0), (int) (w * 0 / 512.0), 0, (int) (w * 7 / 512.0));
        ViewUtils.setviewhw_lin(add.lin_part2, w, (int) (w * 365 / 512.0), 0, 0, 0, 0);

		add.re_bot.setPadding((int) (w * 7 / 512.0), (int) (w * 5 / 512.0), 0, (int) (w * 5 / 512.0));

        ViewUtils.setviewhw_lin(add.im_tp2, (int) (w * 242 / 512.0), (int) (w * 316 / 512.0), 0, 0, 0, 0);

		add.re_bot.setPadding((int) (w * 7 / 512.0), (int) (w * 5 / 512.0), 0, (int) (w * 5 / 512.0));

    }
}
