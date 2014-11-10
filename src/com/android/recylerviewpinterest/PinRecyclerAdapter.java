package com.android.recylerviewpinterest;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.recylerviewpinterest.PinRecyclerAdapter.PinsListViewHolder;

public class PinRecyclerAdapter extends RecyclerView.Adapter<PinsListViewHolder>{

	ArrayList<customPinData> pinDataList;
	ImageLoader imageLoader;
	Context context;

	public PinRecyclerAdapter(ArrayList<customPinData> abeerPinList,Context cont){
		this.pinDataList = abeerPinList;
		this.context = cont;
		imageLoader = new ImageLoader(context);
	}

	@Override
	public int getItemCount() {
		return pinDataList.size();
	}

	@Override
	public void onBindViewHolder(PinsListViewHolder pinViewHolder, final int position) {
		customPinData pinData = pinDataList.get(position);
		pinViewHolder.pinDescriptionTextView.setText(pinData.getDescription());
		pinViewHolder.pinUserTextView.setText(createItalicSpanUserName(pinData));
		pinViewHolder.pinImageView.getLayoutParams().height = convertPixelsToDp(pinData.getHeight());
		pinViewHolder.pinImageView.getLayoutParams().width = convertPixelsToDp(pinData.getWidth());

		if(imageViewReused(pinData)) {
			pinViewHolder.pinImageView.setImageBitmap(getBitmapFromMap(pinData));
		}
		else {
			imageLoader.displayImage(pinDataList.get(position), pinViewHolder.pinImageView);
		}

		pinViewHolder.pinImageView.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				removeItemFromList(position);
			}
		});
	}

	public SpannableString createItalicSpanUserName(customPinData pinData){
		SpannableString spanString = new SpannableString(pinData.getUserName());
		spanString.setSpan(new StyleSpan(Typeface.ITALIC), 0, spanString.length(), 0);
		return spanString;
	}

	void removeItemFromList(int position){
		pinDataList.remove(position);
		//		notifyItemRemoved(position);
		notifyDataSetChanged();
	}

	private int convertPixelsToDp(int value){
		Resources r = context.getResources();
		float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, r.getDisplayMetrics());
		return (int) px;
	}


	boolean imageViewReused(customPinData customObj) {
		Bitmap bmp = Utils.imageMemoryCacheBitmap.get(customObj.getImageUrl());
		if (bmp == null) {
			return false;
		}
		return true;
	}

	Bitmap getBitmapFromMap(customPinData pinDataObj){
		return Utils.imageMemoryCacheBitmap.get(pinDataObj.getImageUrl());
	}

	@Override
	public PinsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_pins_item, parent, false);
		PinsListViewHolder holder = new PinsListViewHolder(view);
		return holder;
	}

	public static class PinsListViewHolder extends RecyclerView.ViewHolder {

		ImageView pinImageView;
		TextView pinDescriptionTextView;
		TextView pinUserTextView;
		ImageView userProfileImageView;

		public PinsListViewHolder(View view){
			super(view);
			pinImageView = (ImageView) view.findViewById(R.id.customPinImageView);
			pinDescriptionTextView = (TextView) view.findViewById(R.id.customPinDescriptionText);
			pinUserTextView = (TextView) view.findViewById(R.id.customUserTextView);
			userProfileImageView = (ImageView) view.findViewById(R.id.customUserImageView);
		}
	}

}
