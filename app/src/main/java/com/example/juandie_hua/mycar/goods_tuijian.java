package com.example.juandie_hua.mycar;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

//import com.bumptech.glide.Glide;
import com.example.juandie_hua.R;
import com.example.juandie_hua.calender.utils.ImageUtils;

public class goods_tuijian extends BaseAdapter {
	List<goods_tuijiandata> list;
	Context context;

	public goods_tuijian(List<goods_tuijiandata> list, Context context) {
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
	public View getView(int position, View view, ViewGroup parent) {
		addview add;
		if (view == null) {
			view = LayoutInflater.from(context).inflate(R.layout.goods_tuijian,
					null);
			add = new addview();
			add.ima = (ImageView) view.findViewById(R.id.gdtj_imsp);
			add.te_name = (TextView) view.findViewById(R.id.gdtj_tename);
			add.te_price = (TextView) view.findViewById(R.id.gdtj_teprice);

			view.setTag(add);
		} else
			add = (addview) view.getTag();
		goods_tuijiandata data = list.get(position);
		ImageUtils.setImage(context.getApplicationContext(),data.getUrl(),add.ima);
//		Glide.with(context.getApplicationContext()).load(data.getUrl()).into(add.ima);
		add.te_name.setText(data.getName());
		add.te_price.setText(data.getPrice());
		return view;
	}

	public class addview {
		ImageView ima;
		TextView te_name, te_price;
	}
}
