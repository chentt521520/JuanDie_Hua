package com.example.juandie_hua.mainactivity.goods;

import java.util.List;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

//import com.bumptech.glide.Glide;
import com.example.juandie_hua.R;
import com.example.juandie_hua.calender.utils.ImageUtils;

public class gdlist_adapter extends BaseAdapter {
	Context context;
	List<gdlist_adaData> list;

	public gdlist_adapter(Context context, List<gdlist_adaData> list) {
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
	public View getView(int position, View convertView, ViewGroup parent) {
		addview add;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.gdlist_adapter1, null);
			add = new addview();
			add.im_spin = (ImageView) convertView.findViewById(R.id.gdlistitem_imsp1);
			add.te_price = (TextView) convertView.findViewById(R.id.gdlistitem_teprice1);
			add.te_xl = (TextView) convertView.findViewById(R.id.gdlistitem_tename1);
			add.tecon = (TextView) convertView.findViewById(R.id.gdlistitem_tecon);

			DisplayMetrics dm = context.getResources().getDisplayMetrics();
			int w = dm.widthPixels;
			setviewhw_lin(add.im_spin, LayoutParams.MATCH_PARENT,
					(int) (w * 185 / 375.0), 0, 0, 0, 0);
			setviewhw_lin(add.te_price, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT, (int) (w * 14 / 375.0), 0, 0,
					(int) (w * 10 / 375.0));
			setviewhw_lin(add.te_price, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT, (int) (w * 14 / 375.0), 0, 0,
					(int) (w * 10 / 375.0));

			add.tecon.setPadding((int) (w * 14 / 375.0), (int) (w * 5 / 375.0),
					(int) (w * 14 / 375.0), (int) (w * 7 / 375.0));

			convertView.setTag(add);
		} else
			add = (addview) convertView.getTag();

		gdlist_adaData data = list.get(position);
		ImageUtils.setImage(context.getApplicationContext(),data.getUrl(),add.im_spin);
		add.tecon.setText(data.getName());
		add.te_price.setText(data.getPrice());
		add.te_xl.setText("已售 " + data.getSale_number() + "件");
		return convertView;
	}

	public class addview {
		ImageView im_spin;
		TextView te_price, te_xl, tecon;
	}

	private void setviewhw_lin(View v, int width, int height, int left,
			int top, int right, int bottom) {
		LayoutParams lp = new LayoutParams(width, height);
		lp.setMargins(left, top, right, bottom);
		v.setLayoutParams(lp);
	}
}
