package com.sagar.proteinfinder;

import com.avocarrot.avocarrotsdk.Avocarrot;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import android.app.Application;

public class App  extends Application {
	
	@Override
	public void onCreate() {
		super.onCreate();
		Avocarrot.init(this, "91e153e6a883fe21316c78100856905aab59eac0");
		
		// Create global configuration and initialize ImageLoader with this configuration
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
        .cacheInMemory()
        .cacheOnDisc()
        .build();
        
        
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
        .defaultDisplayImageOptions(defaultOptions)
            .build();
        
        ImageLoader.getInstance().init(config);
		
	}

}
