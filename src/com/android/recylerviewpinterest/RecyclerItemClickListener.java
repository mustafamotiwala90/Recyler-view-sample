package com.android.recylerviewpinterest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener{

	private OnItemClickListener mListener;
	public interface OnItemClickListener {
		public void onItemClick(View view, int position);
	}

	public RecyclerItemClickListener(Context context,OnItemClickListener listener){
		mListener=listener;
	}

	@Override
	public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent event) {
		View childView = view.findChildViewUnder(event.getX(), event.getY());
		//		if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
		//			mListener.onItemClick(childView, view.getChildPosition(childView));
		//		}
		return false;
	}

	@Override
	public void onTouchEvent(RecyclerView arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub

	}

}
