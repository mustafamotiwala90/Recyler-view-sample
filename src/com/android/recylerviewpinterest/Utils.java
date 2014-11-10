package com.android.recylerviewpinterest;

import java.util.HashMap;
import java.util.Map;

import android.graphics.Bitmap;

public class Utils {

	public static String jsonPinImages = "images";
	public static String jsonPinData = "data";
	public static String jsonPinPins = "pins";
	public static String jsonPinDescription = "description";
	public static String jsonPin237x = "237x";
	public static String jsonPinImageHeigh ="height";
	public static String jsonPinImageWidth = "width";
	public static String jsonPinUser = "user";
	public static String jsonPinUserFullName = "full_name";
	public static String jsonPinImageUrl = "url";
	public static String jsonPinPinner = "pinner";
	public static String jsonPinUserImageUrl = "image_small_url";
	public static String KEY_PIN_DATA = "pin_data";


	static Map<String,Bitmap> imageMemoryCacheBitmap = new HashMap<String, Bitmap>();
	public static String userImageProfileUrl;
	static Bitmap userProfileImageBitmap;
}
