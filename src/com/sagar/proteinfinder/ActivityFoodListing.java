package com.sagar.proteinfinder;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.haarman.listviewanimations.swinginadapters.prepared.SwingBottomInAnimationAdapter;

public class ActivityFoodListing   extends ProteinBasicActivity  implements
OnItemClickListener
{
	private ListView mListView;
	
	private StandardDBObject dbObj;
	
	private String where;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mListView = (ListView) findViewById(R.id.optionList);
		
		where = getIntent().getStringExtra("whereClause");
		
		String message = getIntent().getStringExtra("synopsis");
			
		if(null == dbObj)
		{
			dbObj = new StandardDBObject(this);
		}
		
		dbObj.open();
		
		ArrayList<FoodBean> values = dbObj.getFoodArray(where);
		
		if(null!= message && message.length()>5)
		{
			FoodBean synopsisBn = new FoodBean();
			
			synopsisBn.setType(5);
			synopsisBn.title = message;
			
			values.add(0, synopsisBn);
		}
		
		FoodAdapter adapter = new FoodAdapter(this,
				  R.layout.generic_list_item, values);	
		
		adapter.setShowMoreDet(true);
		
		SwingBottomInAnimationAdapter swingBottomInAnimationAdapter = new SwingBottomInAnimationAdapter(adapter);
		swingBottomInAnimationAdapter.setListView(mListView);
		mListView.setAdapter(swingBottomInAnimationAdapter); 
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}
	
}