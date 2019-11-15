package com.example.juandie_hua.mainactivity.fenlei;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.juandie_hua.R;
import com.example.juandie_hua.calender.utils.ImageUtils;

public class fenlei_adapter extends BaseAdapter {
    private Context context;
    private List<fenlei_adaData> list;

    public fenlei_adapter(Context context, List<fenlei_adaData> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.fenlei_adapter, null);
            add = new addview();
            add.view = convertView.findViewById(R.id.sort_item_view);
            add.te_name = (TextView) convertView.findViewById(R.id.fenlei_tename);
            add.v_shu = convertView.findViewById(R.id.fenle_vshu);
            add.im_t = (ImageView) convertView.findViewById(R.id.fenle_imt);

            convertView.setTag(add);
        } else
            add = (addview) convertView.getTag();

        fenlei_adaData data = list.get(position);

        add.view.setSelected(data.isSelect());// boolean类型的get用is代替

        add.te_name.setText(data.getName());

        ImageUtils.setImage(context.getApplicationContext(), data.getUrl(), add.im_t);

        if (data.isSelect()) {
            add.v_shu.setVisibility(View.VISIBLE);
        } else
            add.v_shu.setVisibility(View.INVISIBLE);

        return convertView;
    }

    public class addview {
        TextView te_name;
        View v_shu;
        ImageView im_t;
        LinearLayout view;
    }
}
