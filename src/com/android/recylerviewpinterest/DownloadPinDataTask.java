package com.android.recylerviewpinterest;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

public class DownloadPinDataTask extends AsyncTask<String, ArrayList<customPinData>, ArrayList<customPinData>>{

	DownloadPinDataTaskListener listener;
	ArrayList<customPinData> pinDataList;
	String pin_url;
	JSONArray pinDataArr;

	public DownloadPinDataTask(String pinterest_url){
		this.pin_url = pinterest_url;
	}

	public void setListener(DownloadPinDataTaskListener downloadDataListner){
		listener = downloadDataListner;
	}

	@Override
	protected ArrayList<customPinData> doInBackground(String... params) {
		HttpClient client = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(pin_url);
		ResponseHandler<String> responsehandle = new BasicResponseHandler();
		String responseBody="";
		JSONObject jsonObj=null;
		try{
			responseBody = client.execute(httpget,responsehandle);
			jsonObj = new JSONObject(responseBody);
			pinDataArr = jsonObj.getJSONObject(Utils.jsonPinData).getJSONArray(Utils.jsonPinPins);

		}catch(IOException er){
			er.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		pinDataList = parseJson(pinDataArr);
		return pinDataList;
	}

	public ArrayList<customPinData> parseJson(JSONArray data) {
		ArrayList<customPinData> tempPinDataArr = new ArrayList<customPinData>();

		try {
			Utils.userImageProfileUrl = getUserImageProfileUrl(data.getJSONObject(0));
			for (int index = 0; index < data.length(); index++) {
				JSONObject pinDataObject = data.getJSONObject(index);
				String icon_url = getIconUrlFromJSON(pinDataObject);
				String description_text = pinDataObject.getString(Utils.jsonPinDescription);
				String user_name = getUserNameFromJSON(pinDataObject);
				customPinData current = new customPinData(description_text, icon_url,user_name);
				current.setSizeImage(getImageSize(pinDataObject));
				tempPinDataArr.add(current);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}


		return tempPinDataArr;
	}

	private static String getUserImageProfileUrl(JSONObject jsonObj){
		String userUrl = "";
		try{
			userUrl = jsonObj.getJSONObject(Utils.jsonPinPinner).getString(Utils.jsonPinUserImageUrl);
		} catch(JSONException er) {
			er.printStackTrace();
		}
		return userUrl;
	}

	private static String getUserNameFromJSON(JSONObject currentObj){
		String username ="";
		try{
			username = currentObj.getJSONObject(Utils.jsonPinPinner).getString(Utils.jsonPinUserFullName);
		} catch(JSONException er){
			er.printStackTrace();
		}
		return username;
	}

	private static ArrayList<Integer> getImageSize(JSONObject current){
		ArrayList<Integer> sizeImage = new ArrayList<Integer>();
		int height = 0;
		int width=0;
		try {
			height = current.getJSONObject(Utils.jsonPinImages).getJSONObject(Utils.jsonPin237x).getInt(Utils.jsonPinImageHeigh);
			width = current.getJSONObject(Utils.jsonPinImages).getJSONObject(Utils.jsonPin237x).getInt(Utils.jsonPinImageWidth);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		sizeImage.add(Integer.valueOf(height));
		sizeImage.add(Integer.valueOf(width));
		return sizeImage;
	}

	private static String getIconUrlFromJSON(JSONObject currentObj){
		String icon_url = "";
		try {
			icon_url = currentObj.getJSONObject(Utils.jsonPinImages).getJSONObject(Utils.jsonPin237x).getString(Utils.jsonPinImageUrl);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return icon_url;
	}

	@Override
	protected void onPostExecute(ArrayList<customPinData> result) {
		listener.sendPinData(result);
		super.onPostExecute(result);
	}
}
