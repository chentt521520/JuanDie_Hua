package com.example.juandie_hua.lunbo;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 自定义高度的viewpapger
 */
public class BaseViewPager extends ViewPager {
	private boolean scrollable = true;

	public BaseViewPager(Context context) {
		super(context);
	}

	public BaseViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/**
	 * 设置viewpager是否可以滚动
	 * 
	 * @param enable
	 */
	public void setScrollable(boolean enable) {
		scrollable = enable;
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent event) {
		if (scrollable) {
			return super.onInterceptTouchEvent(event);
		} else {
			return false;
		}

		// switch (event.getAction()) {
		// case MotionEvent.ACTION_DOWN:
		// return false;
		// case MotionEvent.ACTION_MOVE: // 表示父类需要
		// return true;
		// case MotionEvent.ACTION_UP:
		// return true;
		// default:
		// break;
		// }
		// return false; // 如果设置拦截，除了down,其他都是父类处理
	}

}