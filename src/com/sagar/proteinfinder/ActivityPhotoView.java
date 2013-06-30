package com.sagar.proteinfinder;

import uk.co.senab.photoview.PhotoViewAttacher;
import uk.co.senab.photoview.PhotoViewAttacher.OnPhotoTapListener;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityPhotoView  extends ProteinBasicActivity{

	static final String PHOTO_TAP_TOAST_STRING = "Photo Tap! X: %.2f %% Y:%.2f %%";

	private ImageView mImageView;
	private TextView mCurrMatrixTv;
	
	private PhotoViewAttacher mAttacher;
	
	private Toast mCurrentToast;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_photo_view);
	
	Intent caller = getIntent();
	
	int image=R.drawable.image13;
	
	if(null!=caller)
	{
		image = caller.getIntExtra("picId", R.drawable.image13);
	}
	
	mImageView = (ImageView) findViewById(R.id.iv_photo);
	mCurrMatrixTv = (TextView) findViewById(R.id.tv_current_matrix);
	
	Drawable bitmap = getResources().getDrawable(image);
	mImageView.setImageDrawable(bitmap);
	
	// The MAGIC happens here!
	mAttacher = new PhotoViewAttacher(mImageView);
	
	// Lets attach some listeners, not required though!
	mAttacher.setOnPhotoTapListener(new PhotoTapListener());
	}

	
	@Override
	public void onDestroy() {
	super.onDestroy();
	
	// Need to call clean-up
	mAttacher.cleanup();
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
