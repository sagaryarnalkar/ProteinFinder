package com.sagar.proteinfinder;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.webkit.WebView;

public class ActivitySplashScreen extends Activity{
	
	 private static final String TAG = ActivitySplashScreen.class
			    .getSimpleName();

		    private static final int DELAY = 2000;		    

		    private DelayLoader mLoader = null;
		    
		    @Override
		    protected void onCreate(Bundle savedInstanceState) {

			super.onCreate(savedInstanceState);
			
			//this.requestWindowFeature(Window.FEATURE_NO_TITLE);

			setContentView(R.layout.activity_splash_screen);
			
			//WebView gif = (WebView) findViewById(R.id.webViewgif123);
			
			//gif.loadUrl("file:///android_asset/new.html");

			if (savedInstanceState == null) {


				mLoader = new DelayLoader();

				mLoader.execute((String) null);
			}
		    }

		    @Override
		    protected void onPause() {

			if (mLoader != null && !mLoader.isCancelled()) {

			    mLoader.cancel(true);
			}

			super.onPause();
		    }

		    private class DelayLoader extends AsyncTask<String, Void, Boolean> {
			
			private String mUserName;
			
			@Override
			protected Boolean doInBackground(String... data) {

			    boolean result = false;

			    try {
			    	
			    	Thread.sleep(DELAY);
			    }
			    catch(Exception e)
			    {
			    	if (BuildConfig.DEBUG) {

						Log.d(TAG,"exception",e);
					    }
			    }

			    return result;
			}

			@Override
			protected void onPostExecute(Boolean result) {

			    if (BuildConfig.DEBUG) {

				System.out.println("onPostExecute");
			    }

				Intent intent = new Intent(ActivitySplashScreen.this,
					MainActivity.class);

				startActivity(intent);

				finish();


			   
			}

		    }

}
