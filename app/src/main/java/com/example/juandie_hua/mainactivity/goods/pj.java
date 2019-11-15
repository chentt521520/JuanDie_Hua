package com.example.juandie_hua.mainactivity.goods;

import java.util.ArrayList;
import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.juandie_hua.R;

public class pj extends Fragment {
	View v;
	@ViewInject(R.id.pj_listv)
	ListView listv_v;
	pj_adapter adapter;
	List<pj_adaData> list;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (v == null) {
			v = inflater.inflate(R.layout.pj, container, false);
			x.view().inject(this, v);
			setviewdata();
		}
		return v;
	}

	private void setviewdata() {
		listv_v.setSelector(new ColorDrawable(Color.TRANSPARENT));
		list = new ArrayList<>();
		adapter = new pj_adapter(getActivity(), list);
		listv_v.setAdapter(adapter);
		for (int i = 0; i < 6; i++) {
			String[] a = new String[3];
			list.add(new pj_adaData("", "", "", "", a));
		}
		adapter.notifyDataSetChanged();
	}

	@Override
	public void onHiddenChanged(boolean hidden) {
		// TODO Auto-generated method stub
		super.onHiddenChanged(hidden);
		if (!hidden) {
			// 显示的时候调用方法
		}
	}
}
