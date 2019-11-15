package com.example.juandie_hua.mycar.contact;

import java.util.List;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.example.juandie_hua.R;

public class SortAdapter extends BaseAdapter implements SectionIndexer {
	private List<SortModel> list = null;
	private Context mContext;

	public SortAdapter(Context mContext, List<SortModel> list) {
		this.mContext = mContext;
		this.list = list;
	}

	public void updateListView(List<SortModel> list) {
		this.list = list;
		notifyDataSetChanged();
	}

	public int getCount() {
		return this.list.size();
	}

	public Object getItem(int position) {
		return list.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(final int position, View view, ViewGroup arg2) {
		ViewHolder viewHolder = null;
		final SortModel mContent = list.get(position);
		if (view == null) {
			viewHolder = new ViewHolder();
			view = LayoutInflater.from(mContext).inflate(R.layout.item, null);
			viewHolder.tvTitle = (TextView) view.findViewById(R.id.item_title);
			viewHolder.te_ph = (TextView) view.findViewById(R.id.item_title1);
			viewHolder.tvLetter = (TextView) view.findViewById(R.id.catalog);
			viewHolder.lin = (LinearLayout) view.findViewById(R.id.itemlin);

			DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
			int w_screen = dm.widthPixels;

			setviewhw_lin(viewHolder.tvTitle, LayoutParams.WRAP_CONTENT,
					(int) (w_screen * 100 / 750.0), 0, 0, 0, 0);

			setviewhw_lin(viewHolder.tvLetter, w_screen,
					(int) (w_screen * 76 / 750.0),
					(int) (w_screen * 0 / 750.0), 0, 0, 0);
			viewHolder.tvLetter.setPadding((int) (w_screen * 30 / 750.0),
					(int) (w_screen * 4 / 750.0), 0, 0);

			setviewhw_lin(viewHolder.lin, (int) (w_screen * 720 / 750.0),
					(int) (w_screen * 100 / 750.0),
					(int) (w_screen * 30 / 750.0), 0, 0, 0);

			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}

		int section = getSectionForPosition(position);

		if (position == getPositionForSection(section)) {
			viewHolder.tvLetter.setVisibility(View.VISIBLE);
			viewHolder.tvLetter.setText(mContent.getSortLetters());

		} else {
			viewHolder.tvLetter.setVisibility(View.GONE);
		}

		viewHolder.tvTitle.setText(this.list.get(position).getName());
		viewHolder.te_ph.setText(this.list.get(position).getPhonenum());

		return view;

	}

	final static class ViewHolder {
		TextView tvLetter;
		TextView tvTitle, te_ph;

		LinearLayout lin;
	}

	public int getSectionForPosition(int position) {
		return list.get(position).getSortLetters().charAt(0);
	}

	public int getPositionForSection(int section) {
		for (int i = 0; i < getCount(); i++) {
			String sortStr = list.get(i).getSortLetters();
			char firstChar = sortStr.toUpperCase().charAt(0);
			if (firstChar == section) {
				return i;
			}
		}

		return -1;
	}

	@Override
	public Object[] getSections() {
		return null;
	}

	private void setviewhw_lin(View v, int width, int height, int left,
			int top, int right, int bottom) {
		LayoutParams lp = new LayoutParams(width, height);
		lp.setMargins(left, top, right, bottom);
		v.setLayoutParams(lp);
	}

}