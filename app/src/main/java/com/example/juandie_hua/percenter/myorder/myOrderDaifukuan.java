package com.example.juandie_hua.percenter.myorder;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.x;
import org.xutils.common.Callback.CancelledException;
import org.xutils.view.annotation.ViewInject;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.juandie_hua.R;
import com.example.juandie_hua.mainactivity.CircularProgressView;
import com.example.juandie_hua.mainactivity.Xutils_Get_Post;
import com.example.juandie_hua.mainactivity.Xutils_Get_Post.XCallBack;
import com.example.juandie_hua.mainactivity.internet_if;
import com.example.juandie_hua.mainactivity.internet_landing;
import com.example.juandie_hua.mainactivity.internet_landing.re_jk;

public class myOrderDaifukuan extends Fragment implements re_jk {
	View v;

	myorder_adapter ada;
	List<myorder_adaData> list;

	@ViewInject(R.id.morderdfk_listv)
	ListView listv_v;

	@ViewInject(R.id.morderdfk_nogoods)
	LinearLayout layout_nogoods;
	@ViewInject(R.id.nogoods_imhead)
	ImageView im_no;
	@ViewInject(R.id.nogoods_tecon)
	TextView te_no;
	@ViewInject(R.id.nogoods_tego)
	TextView te_no1;
	SharedPreferences preferences;
	static MyHandler handler;

	internet_landing inLanding;

	// 尾部加载更多
	TextView temore;
	LinearLayout linfoot;
	CircularProgressView cir_view;
	View vfoot;
	boolean tfalse = true;
	@ViewInject(R.id.gdlist_swp)
	SwipeRefreshLayout spr;
	int pagex = 1;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (v == null) {
			v = inflater.inflate(R.layout.myorderdaifukuan, container, false);
			x.view().inject(this, v);
			handler = new MyHandler(myOrderDaifukuan.this);
			preferences = PreferenceManager
					.getDefaultSharedPreferences(getActivity());
			inLanding = new internet_landing(getActivity());
			inLanding.setonc(this);
			if (inLanding.if_inter()) {
				xutils_getorderall(pagex);
			}
			setviewhw();
			setviewdata();
			setviewlisten();
		}
		return v;
	}

	private void setviewlisten() {
		spr.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
				pagex = 1;
				list.removeAll(list);
				ada.notifyDataSetChanged();

				showview_foot();

				xutils_getorderall(pagex);
				// 停止刷新动画
				spr.setRefreshing(false);
				Toast.makeText(getActivity(), "刷新成功", 1).show();

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
						// Log.d("ListView", "##### 滚动到顶部 #####");
					}
				} else if ((firstVisibleItem + visibleItemCount) == totalItemCount) {
					if (tfalse == false) {
						View lastVisibleItemView = listv_v.getChildAt(listv_v
								.getChildCount() - 1);
						if (lastVisibleItemView != null
								&& lastVisibleItemView.getBottom() == listv_v
										.getHeight()) {
							// Log.d("ListView", "##### 滚动到底部 ######");
							pagex++;
							xutils_getorderall(pagex);

						}
					}
				}
			}

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				if (scrollState == SCROLL_STATE_IDLE) {
					tfalse = true;
				} else
					tfalse = false;
			}

		});

	}

	private void setviewdata() {
		list = new ArrayList<myorder_adaData>();
		ada = new myorder_adapter(getActivity(), list);
		listv_v.setAdapter(ada);
		ada.notifyDataSetChanged();

	}

	private void setviewhw() {
		te_no.setText("您还没有相关订单");
		te_no1.setVisibility(View.GONE);

		spr.setColorSchemeResources(R.color.xin);
		vfoot = LayoutInflater.from(getActivity()).inflate(
				R.layout.textv_landmore, null);
		temore = (TextView) vfoot.findViewById(R.id.textv_landmore);
		linfoot = (LinearLayout) vfoot.findViewById(R.id.linv_addmore);
		cir_view = (CircularProgressView) vfoot
				.findViewById(R.id.cirprogre_view);
		listv_v.addFooterView(vfoot);

		DisplayMetrics dm = getResources().getDisplayMetrics();
		int w_screen = dm.widthPixels;
		setviewhw_lin(layout_nogoods, w_screen, (int) (w_screen * 180 / 375.0),
				0, (int) (w_screen * 100 / 375.0), 0, 0);
	}

	private void setviewhw_lin(View v, int width, int height, int left,
			int top, int right, int bottom) {
		LayoutParams lp = new LayoutParams(width, height);
		lp.setMargins(left, top, right, bottom);
		v.setLayoutParams(lp);
	}

	private void xians() {
		if (list.size() >= 1) {
			layout_nogoods.setVisibility(View.GONE);
		} else
			layout_nogoods.setVisibility(View.VISIBLE);
		if (list.size() < 10 & list.size() >= 1) {
			if (cir_view.isShown())
				cir_view.setVisibility(View.GONE);
			if (!temore.isShown())
				temore.setVisibility(View.VISIBLE);
			if (!linfoot.isShown()) {
				linfoot.setVisibility(View.VISIBLE);
			}
			temore.setText("-我是有底线的-");
		}
	}

	String sign = "";

	private void xutils_getorderall(final int page) {
		inLanding.showlanding();
		String url = "https://app.juandie.com/api_mobile/user.php?act=order_list&order_status=0&page="+page;
		
		Xutils_Get_Post.getInstance().get(url, new XCallBack() {
			
			@Override
			public void onResponse(String result) {
				JSONObject jso1;
				try {
					jso1 = new JSONObject(result);
					if (jso1.getString("status").equals("1")) {
						JSONObject data = jso1.getJSONObject("data");
						JSONArray data_ = data.getJSONArray("order_list");
						if (data_.length() == 0) {
							if (page == 1) {
								if (vfoot.isShown())
									vfoot.setVisibility(View.GONE);
								temore.setText("-我是有底线的-");

								vfoot.setPadding(0, -vfoot.getHeight(), 0, 0);
								Toast.makeText(getActivity(), "暂时没有更多数据~~",
										Toast.LENGTH_SHORT).show();
								layout_nogoods.setVisibility(View.VISIBLE);
								// lin_tj.setVisibility(View.GONE);
								spr.setVisibility(View.GONE);

							} else {
								if (cir_view.isShown())
									cir_view.setVisibility(View.GONE);
								if (!temore.isShown())
									temore.setVisibility(View.VISIBLE);
								if (!linfoot.isShown()) {
									linfoot.setVisibility(View.VISIBLE);
								}
								temore.setText("-我是有底线的-");
								Toast.makeText(getActivity(), "暂时没有更多数据~~",
										Toast.LENGTH_SHORT).show();
							}

						} else {
							for (int i = 0; i < data_.length(); i++) {
								JSONObject data_1 = data_.getJSONObject(i);
								JSONArray data_2 = data_1
										.getJSONArray("goods_list");
								List<myorder_adaDatatwo> list2 = new ArrayList<>();
								for (int j = 0; j < data_2.length(); j++) {
									JSONObject data_j = data_2.getJSONObject(j);
									list2.add(new myorder_adaDatatwo(data_j
											.getString("goods_id"), data_j
											.getString("goods_number"), data_j
											.getString("goods_thumb"), data_j
											.getString("goods_name"), data_j
											.getString("goods_price")));
								}
								String order_status = data_1
										.getString("order_status");
								if (order_status.equals("0")) {
									order_status = "1";
								} else if (order_status.equals("1")) {
									order_status = "2";
								} else if (order_status.equals("2")) {
									order_status = "3";
								}
								list.add(new myorder_adaData(order_status,
										data_1.getString("order_id"), data_1
												.getString("order_sn"), list2,
										"1"));

							}
							if (!vfoot.isShown()) {
								vfoot.setVisibility(View.VISIBLE);
							}
							if (layout_nogoods.isShown()) {
								layout_nogoods.setVisibility(View.GONE);
							}
							if (page == 1) {
								if (data_.length() <= 4) {
									vfoot.setVisibility(View.GONE);
								}
							}
						}

					} else {
						Toast.makeText(getActivity(),
								jso1.getString("msg").toString(),
								Toast.LENGTH_SHORT).show();
					}
					ada.notifyDataSetChanged();
					inLanding.dismisslanding();
					xians();

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			
			@Override
			public void onFail(String result) {
				inLanding.dismisslanding();
			}
			
			@Override
			public void onCancel(CancelledException cex) {
				inLanding.dismisslanding();
			}
		});
	
	}

	public static class MyHandler extends Handler {
		WeakReference<myOrderDaifukuan> referenceObj;

		public MyHandler(myOrderDaifukuan activity) {
			referenceObj = new WeakReference<myOrderDaifukuan>(activity);
		}

		@Override
		public void handleMessage(Message msg) {
			final myOrderDaifukuan activity = referenceObj.get();
			switch (msg.what) {
			case 0x01:
				activity.list.removeAll(activity.list);
				activity.xutils_getorderall(1);
				break;
			default:
				break;
			}
		}
	}

	@Override
	public void re_requestjk(View v) {
		if (new internet_if().isNetworkConnected(getActivity())) {
			inLanding.dismissinter();
			xutils_getorderall(pagex);
		} else {
			Toast.makeText(getActivity(), "网络连接失败,请设置网络", Toast.LENGTH_SHORT)
					.show();
		}

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
}
