package com.sagar.proteinfinder;

import android.content.Context;

import com.google.analytics.tracking.android.GAServiceManager;
import com.google.analytics.tracking.android.GoogleAnalytics;
import com.google.analytics.tracking.android.Tracker;

public class GAnalytics {
	

	
	
	private Tracker mGaTracker;
    
    private GoogleAnalytics mGaInstance;
    
    private static final String analyticsKey = "UA-40658054-1";
    
    private static final String defaultCategory = "Protein Finder";
    
    private static final String defaultAction = "user_interaction";
    
    public void tagScreen(Context ctx,String message)
    {
	if(null == mGaInstance || null == mGaTracker)
	{
	    
	this.mGaInstance = GoogleAnalytics.getInstance(
		ctx  // Context used to access device resources
		); 
	
	mGaTracker=mGaInstance.getTracker(analyticsKey);
	
	}
	
	mGaTracker.sendView(message);
	
	GAServiceManager.getInstance().dispatch();
	
    }
    
    public void tagEvent(Context ctx,String message)
    {
	if(null == mGaInstance || null == mGaTracker)
	{
	    
	this.mGaInstance = GoogleAnalytics.getInstance(
		ctx  // Context used to access device resources
		); 
	
	mGaTracker=mGaInstance.getTracker(analyticsKey);
	
	}
	
	mGaTracker.sendEvent(defaultCategory, defaultAction, message, null);
	
	GAServiceManager.getInstance().dispatch();
	
    }
}
