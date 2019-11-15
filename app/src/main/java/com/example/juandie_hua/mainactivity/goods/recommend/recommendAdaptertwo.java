package com.example.juandie_hua.mainactivity.goods.recommend;

import java.util.List;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.example.juandie_hua.R;
import com.example.juandie_hua.calender.utils.ImageUtils;

/**
 * @Author ldlin
 * @Time 2019-1-21 下午3:32:02
 * @Description
 */

public class recommendAdaptertwo extends BaseAdapter {

    List<recommendDatatwo> list;

    public recommendAdaptertwo(List<recommendDatatwo> list) {
        this.list = list;
    }

    /*
     * @see android.widget.Adapter#getCount()
     */
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list == null ? 0 : list.size();
    }

    /*
     * @see android.widget.Adapter#getItem(int)
     */
    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    /*
     * @see android.widget.Adapter#getItemId(int)
     */
    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    /*
     * @see android.widget.Adapter#getView(int, android.view.View,
     * android.view.ViewGroup)
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        addview add;
        if (convertView == null) {
            add = new addview();
            convertView = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.recommendadaptertwo, parent, false);

            add.imagev = (ImageView) convertView.findViewById(R.id.impic);
            add.imapic =  convertView.findViewById(R.id.imagadd);
            add.lin_ = (LinearLayout) convertView.findViewById(R.id.rec_lin);

            DisplayMetrics dm = parent.getContext().getResources()
                    .getDisplayMetrics();
            int w = dm.widthPixels;
            setviewhw_lin(add.lin_, (int) (w * 267 / 720.0),
                    (int) (w * 302 / 720.0), 0, 0, 0, 0);
            setviewhw_lin(add.imagev, (int) (w * 192 / 720.0),
                    (int) (w * 192 / 720.0), (int) (w * 20 / 720.0),
                    (int) (w * 12 / 720.0), (int) (w * 0 / 720.0), 0);
            setviewhw_lin(add.imapic, (int) (w * 24 / 720.0),
                    (int) (w * 24 / 720.0), 0, (int) (w * 100 / 720.0), 0, 0);

            convertView.setTag(add);
        } else
            add = (addview) convertView.getTag();

        recommendDatatwo data = list.get(position);
        if (list.size() - 1 == position) {
            add.imapic.setVisibility(View.GONE);
        } else
            add.imapic.setVisibility(View.VISIBLE);

        ImageUtils.setImage(parent.getContext(), data.getUrl(), add.imagev);

        return convertView;
    }

    public class addview {
        ImageView imagev;
        TextView imapic;
        LinearLayout lin_;
    }

    private void setviewhw_lin(View v, int width, int height, int left,
                               int top, int right, int bottom) {
        LayoutParams lp = new LayoutParams(width, height);
        lp.setMargins(left, top, right, bottom);
        v.setLayoutParams(lp);
    }

}
