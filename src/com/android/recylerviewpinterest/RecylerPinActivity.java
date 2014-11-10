package com.android.recylerviewpinterest;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;

public class RecylerPinActivity extends Activity implements DownloadPinDataTaskListener {


	public static String assumebusy_pinterest_url = "https://api.pinterest.com/v3/pidgets/users/assumebusy/pins/";
	public static final int number_grid_count = 2;
	RecyclerView pinRecycleView;
	private LayoutManager layoutManager;
	ArrayList<customPinData> pinDataList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recyler_pin);
		pinRecycleView = (RecyclerView) findViewById(R.id.recycleview);
		pinRecycleView.setClickable(true);
		pinRecycleView.setHasFixedSize(true);
		layoutManager = new StaggeredGridLayoutManager(number_grid_count, StaggeredGridLayoutManager.VERTICAL);
		pinRecycleView.setLayoutManager(layoutManager);
		pinRecycleView.setItemAnimator(new FadingItemAnimator());
		if(savedInstanceState==null) {
			DownloadPinDataTask downloadTask = new DownloadPinDataTask(assumebusy_pinterest_url);
			downloadTask.setListener(this);
			downloadTask.execute();
		}
		else{
			pinDataList = savedInstanceState.getParcelableArrayList(Utils.KEY_PIN_DATA);
			pinRecycleView.setAdapter(new PinRecyclerAdapter(pinDataList,this));
		}
		//		pinRecycleView.addOnItemTouchListener(new RecyclerItemClickListener(this,new RecyclerItemClickListener.OnItemClickListener(){
		//
		//			@Override
		//			public void onItemClick(View view, int position) {
		//				Intent intent = new Intent()
		//				startActivity();
		//			}
		//		}));

	}

	@Override
	public void sendPinData(ArrayList<customPinData> pinList) {
		//initialize adapter here 
		pinDataList = pinList;
		pinRecycleView.setAdapter(new PinRecyclerAdapter(pinList,getApplicationContext()));
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putParcelableArrayList(Utils.KEY_PIN_DATA,pinDataList);
	}

}
