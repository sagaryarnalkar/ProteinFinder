package com.sagar.proteinfinder;



public class AnalyticsFactory {

    private static GAnalytics mAnalysticsClient;
    
    public static GAnalytics getAnalyticsClient()
    {
	if (null == mAnalysticsClient) {
	    mAnalysticsClient = new GAnalytics();

	    return mAnalysticsClient;

	} else
	    return mAnalysticsClient;
    }
}
