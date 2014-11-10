package com.android.recylerviewpinterest;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;

public class FadingItemAnimator extends RecyclerView.ItemAnimator{


	public FadingItemAnimator(){
		setAddDuration(2000);
	}

	@Override
	public boolean animateAdd(ViewHolder holder) {
		Animation fadeInAnimation = new AlphaAnimation(0, 1);
		fadeInAnimation.setInterpolator(new DecelerateInterpolator());
		holder.itemView.setAnimation(fadeInAnimation);
		return false;
	}

	@Override
	public boolean animateChange(ViewHolder arg0, ViewHolder arg1, int arg2,
			int arg3, int arg4, int arg5) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean animateMove(ViewHolder arg0, int arg1, int arg2, int arg3,
			int arg4) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean animateRemove(ViewHolder arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void endAnimation(ViewHolder arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void endAnimations() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isRunning() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void runPendingAnimations() {
		// TODO Auto-generated method stub
	}

}
