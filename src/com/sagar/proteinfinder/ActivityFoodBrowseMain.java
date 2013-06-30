package com.sagar.proteinfinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.haarman.listviewanimations.swinginadapters.prepared.SwingBottomInAnimationAdapter;

public class ActivityFoodBrowseMain extends ProteinBasicActivity implements
OnItemClickListener{
	
	private ListView mListView;
	
	String[] values = new String[] { "Vegetarian Foods","Non-Vegetarian Foods"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mListView = (ListView) findViewById(R.id.optionList);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				  R.layout.browse_list_item, R.id.tvOption, values);
		
		
		SwingBottomInAnimationAdapter swingBottomInAnimationAdapter = new SwingBottomInAnimationAdapter(adapter);
		swingBottomInAnimationAdapter.setListView(mListView);
		mListView.setAdapter(swingBottomInAnimationAdapter); 
		//mListView.setAdapter(adapter); 
		
		mListView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		
		Intent intnt = new Intent(this, ActivityFoodListing.class);
		
		if(arg2 == 0)
		{
			intnt.putExtra("whereClause", "Type = 1");
			
			intnt.putExtra("synopsis", getString(R.string.vg));
			
			startActivity(intnt);
		}
		else if(arg2 == 1)
		{
			intnt.putExtra("whereClause", "Type = 2");
			
			intnt.putExtra("synopsis", getString(R.string.nvg));
			
			startActivity(intnt);
		}		
	}


}