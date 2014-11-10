package com.android.recylerviewpinterest;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

public class ImageLoader {


	Context context;
	Drawable stub_bitmap;

	public ImageLoader(Context context) {
		this.context = context;
		stub_bitmap = context.getResources().getDrawable(R.drawable.ic_launcher);
	}

	public void displayImage(customPinData pinObj, ImageView imageView) {
		DownloadTask downloadtask = new DownloadTask(pinObj.getImageUrl(), imageView);
		imageView.setImageDrawable(stub_bitmap);
		downloadtask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
	}

	class DownloadTask extends AsyncTask<String, Bitmap, Bitmap> {

		String url;
		ImageView imageview;
		private final WeakReference<ImageView> imageViewReference;

		public DownloadTask(String url, ImageView imageview) {
			imageViewReference = new WeakReference<ImageView>(imageview);
			this.url = url;
		}

		@Override
		protected Bitmap doInBackground(String... params) {
			Bitmap bmp = null;
			try {
				bmp = getBitmapFromUrl(url);
				return bmp;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return bmp;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			if (imageViewReference != null) {
				ImageView imageView = imageViewReference.get();
				if (imageView != null) {
					//					setFadeAnimation(imageView);
					Utils.imageMemoryCacheBitmap.put(url,result);
					imageView.setImageBitmap(result);
				}
			}
		}

		private void setFadeAnimation(ImageView imageview){
			Animation fadeInAnimation = new AlphaAnimation(0, 1);
			fadeInAnimation.setDuration(2000);
			fadeInAnimation.setInterpolator(new DecelerateInterpolator());
			imageview.setAnimation(fadeInAnimation);
		}

		public Bitmap getBitmapFromUrl(String src) throws Exception {
			Bitmap bmp = null;
			URL imageUrl = new URL(src);
			HttpURLConnection connection = (HttpURLConnection) imageUrl
					.openConnection();
			connection.setDoInput(true);
			connection.connect();
			InputStream input = connection.getInputStream();
			bmp = BitmapFactory.decodeStream(input);
			input.close();
			return bmp;
		}
	}


}
