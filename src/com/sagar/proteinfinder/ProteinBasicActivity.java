package com.sagar.proteinfinder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.avocarrot.avocarrotsdk.Avocarrot;
import com.avocarrot.avocarrotsdk.StoryObject;

public class ProteinBasicActivity extends Activity implements OnClickListener
{
	protected ImageView title;
    protected ImageView icon;
    public static int screens = 0;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		
		AnalyticsFactory.getAnalyticsClient().tagScreen(this,
				this.getClass().getName());
		
		screens++;
		
		if(screens>5)
		{
			
			try{
				screens = -5;
			StoryObject obj = new StoryObject("Protein");
			//obj.addProperty("priority", "high");
			//obj.addProperty("has_alarm", true);
			obj.addProperty("send_reward", 1);

			Avocarrot.getInstance().createStoryWithPopup(this, "discover", obj, "Thanks for using our app !", new Avocarrot.PopupDismissedListener() {
				
				@Override
				public void onDismiss() {
					// Popup Dismissed!
					
					AnalyticsFactory.getAnalyticsClient().tagEvent(ProteinBasicActivity.this,
							"Reward Shown and dismissed");
					
					Log.d("Avocarrot", "it workssssss");
					//alert("Hi Girls, I am you reward !!!");
				}
			});
			
			}
			catch(Exception e)
			{
				AnalyticsFactory.getAnalyticsClient().tagEvent(ProteinBasicActivity.this,
						"Exception encountered:"+e.getMessage());
			}
		}
		      
	}
	
	@Override
	public void setContentView(int resId)
	{
		super.setContentView(resId);
		
		 getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);
		 
	        title = (ImageView) findViewById(R.id.title);
	        icon  = (ImageView) findViewById(R.id.icon);
	        
	        icon.setOnClickListener(this);
	}
	
	
	 @Override
	    public void onBackPressed() {
	      super.onBackPressed();
	      overridePendingTransition(R.anim.incomingbackward, R.anim.outgoingbackward);
	    }
	    
	    @Override
	    public void finish()
	    {
		super.finish();
		overridePendingTransition(R.anim.incomingbackward, R.anim.outgoingbackward);
	    }
	    
	    @Override
	    public void startActivity(Intent intent)
	    {	
		super.startActivity(intent);
		overridePendingTransition(R.anim.incoming, R.anim.outgoing);
	    }

		@Override
		public void onClick(View v) {
			if(R.id.icon == v.getId())
			{
				finish();
			}
			
		}
		
		public void alert(String text)
		{
		    Toast.makeText(this, 
		    		text, 
		      Toast.LENGTH_LONG).show();
		}
}
