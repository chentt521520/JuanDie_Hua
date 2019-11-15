package com.example.juandie_hua.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.juandie_hua.R;
import com.example.juandie_hua.mainactivity.goods.MyGridView;
import com.example.juandie_hua.mainactivity.goods.SpecsItemAdapter;
import com.example.juandie_hua.model.GoodSpecs;

import java.util.List;

/**
 * 商品所有的规格
 */
public class GoodSpecsAdapter extends BaseAdapter {
    private List<GoodSpecs> list;
    private Context context;

    public GoodSpecsAdapter(List<GoodSpecs> list, Context context) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        addview add;
        if (convertView == null) {
            add = new addview();
            convertView = LayoutInflater.from(context).inflate(R.layout.spggada, null);

            add.te_gg = (TextView) convertView.findViewById(R.id.gg_te);
            add.gr = (MyGridView) convertView.findViewById(R.id.gg_listvx);
            convertView.setTag(add);

        } else
            add = (addview) convertView.getTag();

        GoodSpecs data = list.get(position);

        if (list.size() == 1) {
            add.te_gg.setVisibility(View.GONE);
        }
        add.te_gg.setText(data.getTitle());
        List<GoodSpecs.ItemsBean> listzi = list.get(position).getItems();
        add.gr.setHorizontalSpacing(20);
        SpecsItemAdapter ada = new SpecsItemAdapter(listzi, context);
        ada.setOnSpecsChange(new SpecsItemAdapter.OnSpecsItemCallback() {
            @Override
            public void onSpecsChange() {
                callback.onSpecsChange();
            }
        });
        add.gr.setAdapter(ada);

        return convertView;
    }

    public class addview {
        TextView te_gg;
        MyGridView gr;
    }

    private OnSpecsChangeCallback callback;

    public interface OnSpecsChangeCallback {
        void onSpecsChange();
    }

    public void setOnSpecsChange(OnSpecsChangeCallback callback) {
        this.callback = callback;
    }
}
