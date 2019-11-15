package com.example.juandie_hua.mainactivity.fenlei.ss;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.example.juandie_hua.R;

public class fenlei2ji_adapter extends BaseAdapter {
	Context context;
	List<fenlei2ji_adaData> list;

	public fenlei2ji_adapter(Context context, List<fenlei2ji_adaData> list) {
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
			convertView = LayoutInflater.from(context).inflate(
					R.layout.fenlei2ji_adapter_cop, null);
			add = new addview();
			add.im_dian = (ImageView) convertView
					.findViewById(R.id.copfl2ji_imdian);
			add.te_name = (TextView) convertView
					.findViewById(R.id.copfl2ji_tename);
			add.gridv_zi = (GridView) convertView
					.findViewById(R.id.copfl2ji_gridv);
			add.lin_top = (LinearLayout) convertView
					.findViewById(R.id.copfl2ji_lin);

			add.gridv_zi.setSelector(new ColorDrawable(Color
					.parseColor("#ffffff")));
			DisplayMetrics dm = context.getResources().getDisplayMetrics();
			int w = dm.widthPixels;
			setviewhw_lin(add.lin_top, w, (int) (w * 40 / 375.0), 0, 0, 0, 0);
			setviewhw_lin(add.im_dian, (int) (w * 37 / 375.0),
					(int) (w * 40 / 375.0), 0, 0, 0, 0);
			add.im_dian.setPadding((int) (w * 5 / 375.0), 0,
					(int) (w * 0 / 375.0), 0);

			convertView.setTag(add);
		} else
			add = (addview) convertView.getTag();
		final List<fenleizi_adaData> data_zi = list.get(position).getData();
		final fenleizi_adapter ada = new fenleizi_adapter(context, data_zi);
		fenlei2ji_adaData data = list.get(position);

		add.te_name.setText(data.getName());
		add.gridv_zi.setAdapter(ada);

		add.gridv_zi.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				for (int i = 0; i < data_zi.size(); i++) {
					if (i == position) {
						data_zi.get(i).setSelset(true);
					} else {
						if (data_zi.get(i).isSelset()) {
							data_zi.get(i).setSelset(false);
						}
					}

				}
				ada.notifyDataSetChanged();
			}
		});

		return convertView;
	}

	public class addview {
		ImageView im_dian;
		TextView te_name;
		GridView gridv_zi;
		LinearLayout lin_top;
	}

	private void setviewhw_lin(View v, int width, int height, int left,
			int top, int right, int bottom) {
		LayoutParams lp = new LayoutParams(width, height);
		lp.setMargins(left, top, right, bottom);
		v.setLayoutParams(lp);
	}
}
