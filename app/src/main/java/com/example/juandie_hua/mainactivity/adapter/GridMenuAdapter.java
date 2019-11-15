package com.example.juandie_hua.mainactivity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.juandie_hua.R;
import com.example.juandie_hua.calender.utils.ImageUtils;
import com.example.juandie_hua.model.ResIndex;

import java.util.List;

public class GridMenuAdapter extends BaseAdapter {

    private Context context;
    private List<ResIndex.MenuListBean> list;

    public GridMenuAdapter(Context context, List<ResIndex.MenuListBean> list) {
        this.context = context;
        this.list = list;
    }

    public void refresh(List<ResIndex.MenuListBean> list) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item_index_menu, null);
        ImageView image = convertView.findViewById(R.id.item_index_menu_image);
        TextView text = convertView.findViewById(R.id.item_index_menu_text);

        ImageUtils.setImage(context, list.get(position).getImg(), image);
        text.setText(list.get(position).getText());
        return convertView;
    }
}
