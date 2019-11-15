package com.example.juandie_hua.mainactivity.goods;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.x;
import org.xutils.common.Callback.CancelledException;
import org.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.juandie_hua.R;
import com.example.juandie_hua.mainactivity.CircularProgressView;
import com.example.juandie_hua.mainactivity.Xutils_Get_Post;
import com.example.juandie_hua.mainactivity.Xutils_Get_Post.XCallBack;
import com.example.juandie_hua.percenter.TimerTextView;

public class pj_more extends Activity {
	View v;
	@ViewInject(R.id.pj_listv)
	ListView listv_v;
	pj_adapter adapter;
	List<pj_adaData> list;

	@ViewInject(R.id.pj_retop)
	RelativeLayout re_top;
	@ViewInject(R.id.pj_imreturn)
	ImageView im_return;
	int pagex = 0;

	// 尾部加载更多
	TextView temore;
	LinearLayout linfoot;
	CircularProgressView cir_view;
	View vfoot;
	boolean tfalse = true;
	@ViewInject(R.id.gdlist_swp)
	SwipeRefreshLayout spr;

	@ViewInject(R.id.gdlist_nogoods)
	LinearLayout layout_nogoods;
	@ViewInject(R.id.nogoods_tego)
	TextView te_nogoods;
	@ViewInject(R.id.nogoods_tecon)
	TextView te_nogoods1;

	@ViewInject(R.id.pj_tetitnum)
	TextView te_titnum;

	@ViewInject(R.id.pj_gotop)
	ImageView im_top;

	String goods_id;
	SharedPreferences preferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pj);
		x.view().inject(this);
		preferences = PreferenceManager
				.getDefaultSharedPreferences(pj_more.this);
		setviewhw();
		setviewdata();
		setviewlisten();

	}

	private void setviewlisten() {
		im_return.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				TimerTextView.isc = false;
				finish();
				overridePendingTransition(R.anim.push_right_out,
						R.anim.push_right_in);

			}
		});
		im_top.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				listv_v.setSelection(0);
				im_top.setVisibility(View.GONE);
			}
		});
		listv_v.setOnScrollListener(new OnScrollListener() {
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {

				if (firstVisibleItem == 0) {
					View firstVisibleItemView = listv_v.getChildAt(0);
					if (firstVisibleItemView != null
							&& firstVisibleItemView.getTop() == 0) {
					}
				} else if ((firstVisibleItem + visibleItemCount) == totalItemCount) {
					if (tfalse == false) {
						View lastVisibleItemView = listv_v.getChildAt(listv_v
								.getChildCount() - 1);
						if (lastVisibleItemView != null
								&& lastVisibleItemView.getBottom() == listv_v
										.getHeight()) {
							pagex++;
							volley_getsp(goods_id, pagex + "");
						}
					}
				}
				if (firstVisibleItem >= 20) {
					im_top.setVisibility(View.VISIBLE);
				} else
					im_top.setVisibility(View.GONE);
			}

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				if (scrollState == SCROLL_STATE_IDLE) {
					tfalse = true;
				} else
					tfalse = false;
			}

		});
		spr.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
				pagex = 0;
				list.removeAll(list);
				adapter.notifyDataSetChanged();

				showview_foot();

				volley_getsp(goods_id, pagex + "");
				// 停止刷新动画
				spr.setRefreshing(false);
				Toast.makeText(pj_more.this, "刷新成功", 1).show();

			}
		});

	}

	private void setviewhw() {
		DisplayMetrics dm = getResources().getDisplayMetrics();
		int w = dm.widthPixels;
		setviewhw_lin(re_top, w, (int) (w * 55 / 450.0), 0, 0, 0, 0);
		re_top.setBackgroundResource(R.drawable.biankuangxnew);

		setviewhw_re(im_return, (int) (w * 49 / 450.0), (int) (w * 25 / 450.0),
				0, (int) (w * 15 / 450.0), 0, 0);
		im_return.setPadding((int) (w * 12 / 450.0), 0, (int) (w * 12 / 450.0),
				0);

	}

	private void setviewdata() {
		Intent ix = getIntent();
		goods_id = ix.getStringExtra("goods_id");
		te_titnum.setText("(" + ix.getStringExtra("goods_num") + ")");
		te_nogoods.setVisibility(View.GONE);
		te_nogoods1.setText("抱歉，没有找到更多的评价！");

		spr.setColorSchemeResources(R.color.xin);
		vfoot = LayoutInflater.from(pj_more.this).inflate(
				R.layout.textv_landmore, null);
		temore = (TextView) vfoot.findViewById(R.id.textv_landmore);
		linfoot = (LinearLayout) vfoot.findViewById(R.id.linv_addmore);
		cir_view = (CircularProgressView) vfoot
				.findViewById(R.id.cirprogre_view);

		listv_v.addFooterView(vfoot);

		listv_v.setSelector(new ColorDrawable(Color.TRANSPARENT));
		list = new ArrayList<>();
		adapter = new pj_adapter(pj_more.this, list);
		listv_v.setAdapter(adapter);

		volley_getsp(goods_id, pagex + "");
	}

	private void volley_getsp(final String goods_id, final String page) {
		String url = "https://app.juandie.com/api_mobile/goods.php?act=comment_list&goods_id="
				+ goods_id + "&page=" + page;
		Xutils_Get_Post.getInstance().get(url, new XCallBack() {

			@Override
			public void onResponse(String result) {
				try {
					JSONObject response = new JSONObject(result);
					if (response.getString("status").equals("1")) {
						JSONObject jso1 = response.getJSONObject("data");
						JSONArray jso = jso1.getJSONArray("comment_list");

						if (jso.length() >= 1) {
							for (int i = 0; i < jso.length(); i++) {
								JSONObject dat_pj = jso.getJSONObject(i);
								String url_head = dat_pj
										.getString("goods_thumb");

								String[] urls = { "无" };
								JSONArray jsourl = dat_pj
										.getJSONArray("comment_images");
								if (jsourl.length() < 1) {
									urls[0] = "无";
								} else {
									urls = new String[jsourl.length()];
									for (int j = 0; j < jsourl.length(); j++) {
										urls[j] = jsourl.getString(j);
									}
								}
								list.add(new pj_adaData(url_head, dat_pj
										.getString("comment_rank"), dat_pj
										.getString("user_name"), dat_pj
										.getString("content"), urls));
							}
							adapter.notifyDataSetChanged();
							if (!vfoot.isShown()) {
								vfoot.setVisibility(View.VISIBLE);
							}
							if (layout_nogoods.isShown()) {
								layout_nogoods.setVisibility(View.GONE);
							}
							if (page.equals("1")) {
								if (jso.length() <= 6) {
									vfoot.setVisibility(View.GONE);
								}
							}
						} else {
							if (page.equals("1")) {
								if (vfoot.isShown())
									vfoot.setVisibility(View.GONE);
								temore.setText("-我是有底线的-");

								vfoot.setPadding(0, -vfoot.getHeight(), 0, 0);
								Toast.makeText(pj_more.this, "暂时没有更多数据~~",
										Toast.LENGTH_SHORT).show();
								layout_nogoods.setVisibility(View.VISIBLE);

							} else {
								if (cir_view.isShown())
									cir_view.setVisibility(View.GONE);
								if (!temore.isShown())
									temore.setVisibility(View.VISIBLE);
								if (!linfoot.isShown()) {
									linfoot.setVisibility(View.VISIBLE);
								}
								temore.setText("-我是有底线的-");
								Toast.makeText(pj_more.this, "暂时没有更多数据~~",
										Toast.LENGTH_SHORT).show();
							}
						}
					}

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			@Override
			public void onFail(String result) {
				volley_getsp(goods_id, page);
			}

			@Override
			public void onCancel(CancelledException cex) {
				// TODO Auto-generated method stub

			}
		});
	}

	public void showview_foot() {
		vfoot.setPadding(0, 0, 0, 0);
		if (!vfoot.isShown()) {
			vfoot.setVisibility(View.VISIBLE);
		}
		if (!cir_view.isShown())
			cir_view.setVisibility(View.VISIBLE);
		if (!temore.isShown())
			temore.setVisibility(View.VISIBLE);
		if (!temore.getText().toString().equals("    加载中....."))
			temore.setText("    加载中.....");

	}

	private void setviewhw_lin(View v, int width, int height, int left,
			int top, int right, int bottom) {
		LayoutParams lp = new LayoutParams(width, height);
		lp.setMargins(left, top, right, bottom);
		v.setLayoutParams(lp);
	}

	private void setviewhw_re(View v, int width, int height, int left, int top,
			int right, int bottom) {
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(width,
				height);
		lp.setMargins(left, top, right, bottom);
		v.setLayoutParams(lp);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			TimerTextView.isc = false;
			finish();
			overridePendingTransition(R.anim.push_right_out,
					R.anim.push_right_in);

			return false;
		}
		return false;
	}
}
