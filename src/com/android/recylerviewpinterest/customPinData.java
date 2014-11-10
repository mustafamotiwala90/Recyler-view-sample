package com.android.recylerviewpinterest;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class customPinData implements Parcelable{

	String description;
	String imageUrl;
	String userName;
	ArrayList<Integer> sizeImage;
	String userImageUrl;

	public customPinData(String descriptionData,String imageUrl,String userData){
		this.description=descriptionData;
		this.imageUrl = imageUrl;
		this.userName = userData;
		sizeImage = new ArrayList<Integer>();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public int getWidth() {
		return sizeImage.get(1).intValue();
	}

	public int getHeight() {
		return sizeImage.get(0).intValue();
	}

	public void setSizeImage(ArrayList<Integer> imageSize) {
		this.sizeImage = imageSize;
	}

	public void setUserImageUrl(String userImgUrl){
		this.userImageUrl = userImgUrl;
	}

	public String getUserImageUrl(){
		return userImageUrl;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(description);
		dest.writeString(imageUrl);
	}

	public String getDescription() {
		return description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public String getUserName(){
		return userName;
	}

}
