package com.example.juandie_hua.mainactivity.goods;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class MyScrollview extends ScrollView {
	private ScrollViewListenner listenner;
	private MyScrollview currentView;

	public MyScrollview(Context context) {
		super(context);
	}

	public MyScrollview(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyScrollview(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		currentView = this;
		return super.onTouchEvent(ev);
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		// TODO Auto-generated method stub
		if (null != listenner) {
			this.listenner.onScrollChanged(currentView, l, t, oldl, oldt);
		}
		super.onScrollChanged(l, t, oldl, oldt);
	}

	public interface ScrollViewListenner {
		public void onScrollChanged(MyScrollview view, int l,
                                    int t, int oldl, int oldt);
	}

	public void setScrollViewListenner(ScrollViewListenner listenner) {
		this.listenner = listenner;
	}

	/**
	 * 
	 * 阻尼：1000为将惯性滚动速度缩小1000倍，近似drag操作。
	 **/
	@Override
	public void fling(int velocity) {
		super.fling(velocity / 1000);
	}
}
