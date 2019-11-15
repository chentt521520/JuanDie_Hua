package com.example.juandie_hua.mainactivity.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.juandie_hua.R;
import com.example.juandie_hua.calender.utils.ImageUtils;
import com.example.juandie_hua.mainactivity.goods.recommend.recommendDatatwo;
import com.example.juandie_hua.utils.ViewUtils;

import org.xutils.common.util.DensityUtil;

import java.util.List;

public class GoodRecommondAdapter extends RecyclerView.Adapter<GoodRecommondAdapter.MyViewHolder> {


    private OnListItemClickListener onListItemClickListener;

    public void setOnViewClickListener(OnListItemClickListener onListItemClickListener) {
        this.onListItemClickListener = onListItemClickListener;
    }

    /*
     *
     * 属性一般俩一个是数据源
     * 另一个是上下问对象
     *
     * */
    private Context context;
    private List<recommendDatatwo> strings;

    /*
     *
     * 构造方法初始化数据
     *
     * */
    public GoodRecommondAdapter(Context context, List<recommendDatatwo> strings) {
        this.context = context;
        this.strings = strings;
    }

    public void freshList(List<recommendDatatwo> strings) {
        this.strings = strings;
        notifyDataSetChanged();
    }

    /*
     * 创建viewHolder
     * 这个主要作用就是找到item布局
     * 把布局里面有用的控件与Viewholder进行关联
     * */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_image, parent, false);
        return new MyViewHolder(view);
    }

    /*
     * 绑定viewholder
     * 主要是数据源与viewHolder的绑定
     * 这里可以实现监听事件
     * */
    @Override
    public void onBindViewHolder(@NonNull GoodRecommondAdapter.MyViewHolder myViewHolder, final int i) {
        ImageUtils.setImage(context, strings.get(i).getUrl(), myViewHolder.image);
        if (i == strings.size() - 1) {
            myViewHolder.add.setVisibility(View.GONE);
        } else {
            myViewHolder.add.setVisibility(View.VISIBLE);
        }
        /*
         * 接口回调方式实现监听事件
         * */
        myViewHolder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onListItemClickListener != null) {
                    onListItemClickListener.onItemClickListener(i);
                }
            }
        });

    }

    /*
     * 获取数据源条数
     * */
    @Override
    public int getItemCount() {
        return strings == null ? 0 : strings.size();
    }

    /*
     * viewholder与布局中的控件关联
     * view代表一个item
     * */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView add;
        RelativeLayout view;

        public MyViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.item_image);
            add = itemView.findViewById(R.id.item_add);
            view = itemView.findViewById(R.id.item_recommond);
            int width = (DensityUtil.getScreenWidth() - DensityUtil.dip2px(200)) / 2;
            ViewUtils.setviewhw_re(image, width, width, 0, 0, 0, 0);
            ViewUtils.setviewhw_re(view, -2, width, 0, 0, 0, 0);
        }
    }
}
