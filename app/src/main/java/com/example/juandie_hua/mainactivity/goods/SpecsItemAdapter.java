package com.example.juandie_hua.mainactivity.goods;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.juandie_hua.R;
import com.example.juandie_hua.model.GoodSpecs;

import java.util.List;

public class SpecsItemAdapter extends BaseAdapter {
    private List<GoodSpecs.ItemsBean> list;
    private Context context;

    public SpecsItemAdapter(List<GoodSpecs.ItemsBean> list, Context context) {
        this.list = list;
        this.context = context;
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
        final addview add;
        if (convertView == null) {
            add = new addview();
            convertView = LayoutInflater.from(context).inflate(R.layout.spggada1, null);
            add.te = (TextView) convertView.findViewById(R.id.spggxz);
            convertView.setTag(add);

        } else
            add = (addview) convertView.getTag();

        final GoodSpecs.ItemsBean data = list.get(position);


        add.te.setText(data.getLabel());
        add.te.setSelected(data.isCheck());
        add.te.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!add.te.isSelected()) {
                    for (int i = 0; i < list.size(); i++) {
                        if (i == position) {
                            list.get(position).setCheck(true);
                        } else {
                            list.get(i).setCheck(false);
                        }
                    }
                    notifyDataSetChanged();
                    callback.onSpecsChange();
                }
            }
        });
        return convertView;
    }

    public class addview {

        TextView te;
    }


    private OnSpecsItemCallback callback;

    public interface OnSpecsItemCallback {
        void onSpecsChange();
    }

    public void setOnSpecsChange(OnSpecsItemCallback callback) {
        this.callback = callback;
    }
}
