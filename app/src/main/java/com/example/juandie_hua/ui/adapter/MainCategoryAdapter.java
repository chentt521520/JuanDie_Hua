package com.example.juandie_hua.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.juandie_hua.R;
import com.example.juandie_hua.model.CategoryList;
import com.example.juandie_hua.calender.utils.ImageUtils;

import java.util.List;


public class MainCategoryAdapter extends BaseAdapter {

    private Context context;
    private List<CategoryList> lists;

    public MainCategoryAdapter(Context context, List<CategoryList> lists) {
        this.context = context;
        this.lists = lists;
    }

    public void refresh(List<CategoryList> lists) {
        this.lists = lists;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return lists == null ? 0 : lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CategoryHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.fenlei_adapter, null);
            holder = new CategoryHolder();
            holder.view = convertView.findViewById(R.id.sort_item_view);
            holder.categoryName = (TextView) convertView.findViewById(R.id.fenlei_tename);
            holder.indicator = convertView.findViewById(R.id.fenle_vshu);
            holder.categoryImage = (ImageView) convertView.findViewById(R.id.fenle_imt);

            convertView.setTag(holder);
        } else
            holder = (CategoryHolder) convertView.getTag();

        CategoryList data = lists.get(position);

        holder.view.setSelected(data.isCheck());// boolean类型的get用is代替

        holder.categoryName.setText(data.getCat_name());

        ImageUtils.setImage(context.getApplicationContext(), data.getCat_icon(), holder.categoryImage);

        if (data.isCheck()) {
            holder.indicator.setVisibility(View.VISIBLE);
        } else
            holder.indicator.setVisibility(View.INVISIBLE);

        return convertView;
    }

    public class CategoryHolder {
        TextView categoryName;
        View indicator;
        ImageView categoryImage;
        LinearLayout view;
    }
}
