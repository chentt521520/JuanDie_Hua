package com.example.juandie_hua.view.citychoosedialog.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.juandie_hua.R;
import com.example.juandie_hua.view.citychoosedialog.entity.CityInfo;

import java.util.List;


//城市适配器
public class CityAdapter extends BaseAdapter {

    Context context;
    List<CityInfo> list;
    int index;  //选择的下标

    public CityAdapter(Context context, List<CityInfo> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        if (list != null)
            return list.size();
        return 0;
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
    public View getView(int position, View view, ViewGroup parent) {
        Info info;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_text, null);
            info = new Info();
            info.textView = view.findViewById(R.id.item_text_dialogitem);
            view.setTag(info);
        }
        info = (Info) view.getTag();
        info.textView.setText(list.get(position).getRegion_name());
        if (position == index)
            info.textView.setTextColor(Color.parseColor("#C22222"));
        else
            info.textView.setTextColor(Color.parseColor("#0f0f0f"));
        return view;
    }

    class Info {
        TextView textView;
    }

    //设置选择的下标
    public void setChoose(List<CityInfo> list, int index) {
        this.list = list;
        this.index = index;
        notifyDataSetChanged();
    }
}
