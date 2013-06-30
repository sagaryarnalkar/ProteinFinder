package com.sagar.proteinfinder;

import uk.co.senab.photoview.PhotoViewAttacher;
import uk.co.senab.photoview.PhotoViewAttacher.OnPhotoTapListener;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.view.View;

public class ActivityProteinDetails   extends ProteinBasicActivity{
	
	String url1;
	String url2;
	private ImageLoader imageCache;
	private PhotoViewAttacher mAttacher;
	private PhotoViewAttacher mAttacher2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_protein_details);
		
		url1 = getIntent().getStringExtra("url1");
		url2 = getIntent().getStringExtra("url2");
		
		imageCache = ImageLoader.getInstance();
		
		final ImageView iv1 = (ImageView)findViewById(R.id.ivDetails1);
		final ImageView iv2 = (ImageView)findViewById(R.id.ivDetails2);
		
		if(null!=url1 && url1.length()>3)
		{
		imageCache.loadImage(url1, new SimpleImageLoadingListener() {
	        @Override
	        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
	            // Do whatever you want with Bitmap
	        	iv1.setImageBitmap(loadedImage);
	        }
	    });
		}
		
		if(null!=url2 && url2.length()>3)
		{
		imageCache.loadImage(url2, new SimpleImageLoadingListener() {
	        @Override
	        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
	            // Do whatever you want with Bitmap
	        	iv2.setImageBitmap(loadedImage);
	        }
	    });
		}
		
		// The MAGIC happens here!
		mAttacher = new PhotoViewAttacher(iv1);
		
		// Lets attach some listeners, not required though!
		mAttacher.setOnPhotoTapListener(new PhotoTapListener());
		
		// The MAGIC happens here!
		mAttacher2 = new PhotoViewAttacher(iv2);
		
		// Lets attach some listeners, not required though!
		mAttacher2.setOnPhotoTapListener(new PhotoTapListener());
		
	}
	
private class PhotoTapListener implements OnPhotoTapListener {
		
		@Override
		public void onPhotoTap(View view, float x, float y) {
		float xPercentage = x * 100f;
		float yPercentage = y * 100f;
		
//		if (null != mCurrentToast) {
//		mCurrentToast.cancel();
//		}
//		
//		mCurrentToast = Toast.makeText(ActivityPhotoView.this,
//		String.format(PHOTO_TAP_TOAST_STRING, xPercentage, yPercentage), Toast.LENGTH_SHORT);
//		mCurrentToast.show();
		}
		}

}
