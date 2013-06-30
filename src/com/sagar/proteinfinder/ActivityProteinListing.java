package com.sagar.proteinfinder;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.haarman.listviewanimations.swinginadapters.prepared.SwingBottomInAnimationAdapter;
import com.sagar.proteinfinder.ProteinAdapter.ViewHolder;

public class ActivityProteinListing  extends ProteinBasicActivity  implements
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
		
		ArrayList<ListBean> values = dbObj.getCategoryArray(where);
		
		if(null!= message && message.length()>5)
		{
			ListBean synopsisBn = new ListBean();
			
			synopsisBn.setCategory("synopsis");
			synopsisBn.desc = message;
			
			values.add(0, synopsisBn);
		}
		
		ProteinAdapter adapter = new ProteinAdapter(this,
				  R.layout.generic_list_item, values);	
		
		adapter.setShowMoreDet(true);
		
		SwingBottomInAnimationAdapter swingBottomInAnimationAdapter = new SwingBottomInAnimationAdapter(adapter);
		swingBottomInAnimationAdapter.setListView(mListView);
		mListView.setAdapter(swingBottomInAnimationAdapter); 
		
		mListView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View v, int arg2, long arg3) {
		if(v.getTag() != null){
		
			ViewHolder vholder = (ViewHolder)v.getTag();
			
			if((null!=vholder.url1 && vholder.url1.length()>1) || (null!=vholder.url2 && vholder.url2.length()>1))
			{
				Intent calldetails =  new Intent(this,ActivityProteinDetails.class);
				calldetails.putExtra("url1", vholder.url1);
				calldetails.putExtra("url2", vholder.url2);
				startActivity(calldetails);
			}
		}
		
	}
	
}
