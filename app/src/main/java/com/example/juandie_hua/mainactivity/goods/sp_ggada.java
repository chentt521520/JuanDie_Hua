package com.example.juandie_hua.mainactivity.goods;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.juandie_hua.R;
import com.example.juandie_hua.mainactivity.goods.sp_ggada1.shuaxin;

import java.util.List;

public class sp_ggada extends BaseAdapter implements shuaxin {
	List<sp_ggadadadata> list;
	Context context;
	xxshua xxs;

	public sp_ggada(List<sp_ggadadadata> list, Context context) {
		this.list = list;
		this.context = context;
	}

	public void setshaxin(xxshua xx) {
		this.xxs = xx;
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
			add = new addview();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.spggada, null);

			add.te_gg = (TextView) convertView.findViewById(R.id.gg_te);
			add.gr = (MyGridView) convertView.findViewById(R.id.gg_listvx);
			convertView.setTag(add);

		} else
			add = (addview) convertView.getTag();

		sp_ggadadadata data = list.get(position);
		add.te_gg.setText(data.getText());
		List<sp_ggadadadata1> listzi = list.get(position).getList();
		sp_ggada1 ada = new sp_ggada1(listzi, context);
		ada.setinterfa(this);
		add.gr.setAdapter(ada);

		return convertView;
	}

	public class addview {
		TextView te_gg;
		MyGridView gr;
	}

	@Override
	public void shuaxin() {
		xxs.shuaxin();
	}

	public interface xxshua {
		void shuaxin();
	}

}
