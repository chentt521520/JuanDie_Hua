package com.example.juandie_hua.mycar.shouhuodizi;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.juandie_hua.R;

public class gridv_adapter extends BaseAdapter {
    Context context;
    List<gridv_adaData> list;

    public gridv_adapter(Context context, List<gridv_adaData> list) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        addview add;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.gridv_adapter, null);
            add = new addview();
            add.tetit = convertView.findViewById(R.id.gd_tetit);
            convertView.setTag(add);
        } else
            add = (addview) convertView.getTag();

        gridv_adaData data = list.get(position);

        add.tetit.setText(data.getTitle());
        if (data.isIsselect()) {
            add.tetit.setSelected(true);
        } else
            add.tetit.setSelected(false);
        return convertView;
    }

    public class addview {
        TextView tetit;
    }
}
