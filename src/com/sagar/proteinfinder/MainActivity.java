package com.sagar.proteinfinder;

import java.util.ArrayList;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ProteinBasicActivity implements
OnItemClickListener{
	
	private ListView mListView;
	
	//String[] values = new String[] { "Find my Protein !!! ", "Protein Browser" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mListView = (ListView) findViewById(R.id.optionList);
		
//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//				  R.layout.generic_list_item, R.id.tvOption, values);
		
		ArrayList<ListBean> listObjs = new ArrayList<ListBean>();
		
		listObjs.add(new ListBean("Find Supplements !","Find what your body needs.",R.drawable.find));
		
		listObjs.add(new ListBean("Supplement Browser","Browse all the options yourself.",R.drawable.browse));
		
		listObjs.add(new ListBean("Understand your Supplements","A glossary of common terms and more vital info.",R.drawable.understand));
		
		listObjs.add(new ListBean("Dosage Calculator","Find how much protein you need.",R.drawable.dosage));
		
		listObjs.add(new ListBean("Natural Foods","Browse natural protein sources.",R.drawable.naturalicon));
		
		listObjs.add(new ListBean("Motivation Center","Dont wanna go to gym today ???",R.drawable.motivation));
		
		listObjs.add(new ListBean("Rate the app !","Give us feedback.",R.drawable.rateus));
		
		ProteinAdapter adapter = new ProteinAdapter(this,
				  R.layout.generic_list_item, listObjs);
		
		mListView.setAdapter(adapter); 
		mListView.setOnItemClickListener(this);
		
		
	}
	
	@Override
	public void setContentView(int resId)
	{
		super.setContentView(resId);
	        
	        icon.setVisibility(View.GONE);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		
		if(arg2 == 0)
		{
			Intent intnt = new Intent(this,ActivityProteinFinder.class);
			startActivity(intnt);
		}
		else if(arg2 == 1)
		{
			Intent intnt = new Intent(this,ActivityProteinBrowseMain.class);
			startActivity(intnt);
		}
		else if(arg2 == 2)
		{
			Intent intnt = new Intent(this,ActivityUnderstand.class);
			startActivity(intnt);
		}
		else if(arg2 == 3)
		{
			Intent intnt = new Intent(this,ActivityProteinCalculator.class);
			startActivity(intnt);
		}
		else if(arg2 == 4)
		{
			Intent intnt = new Intent(this,ActivityFoodBrowseMain.class);
			startActivity(intnt);
		}
		else if(arg2 == 5)
		{
			Intent intnt = new Intent(this,ActivityMotivationGrid.class);
			startActivity(intnt);
		}
		else if(arg2 == 6)
		{
			//final Uri uri = Uri.parse("http://www.amazon.com/gp/mas/dl/android?p=" + getApplicationContext().getPackageName());
			final Uri uri = Uri.parse("market://details?id=" + getApplicationContext().getPackageName());
			final Intent rateAppIntent = new Intent(Intent.ACTION_VIEW, uri);

			if (getPackageManager().queryIntentActivities(rateAppIntent, 0).size() > 0)
			{
				AnalyticsFactory.getAnalyticsClient().tagEvent(this,
						"Rate Us Clicked.");
				
			    startActivity(rateAppIntent);
			}
			else
			{
				Toast mCurrentToast = Toast.makeText(this,
						"Store not used to download.", Toast.LENGTH_SHORT);
						mCurrentToast.show();
			}
		}
		
	}


}
