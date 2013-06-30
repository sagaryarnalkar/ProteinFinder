package com.sagar.proteinfinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.haarman.listviewanimations.swinginadapters.prepared.SwingBottomInAnimationAdapter;

public class ActivityProteinBrowseMain extends ProteinBasicActivity implements
OnItemClickListener{
	
	private ListView mListView;
	
	String[] values = new String[] { "Whey Protein","Mass Gainer","Soy Protein" ,"Creatine" ,"Glutamine" ,"MultiVitamins" ,"Nitric Oxide"  };

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
		
		Intent intnt = new Intent(this, ActivityProteinListing.class);
		
		if(arg2 == 0)
		{
			intnt.putExtra("whereClause", "category = '1'");
			
			intnt.putExtra("synopsis", getString(R.string.whey));
			
			startActivity(intnt);
		}
		else if(arg2 == 1)
		{
			intnt.putExtra("whereClause", "category = '2'");
			
			intnt.putExtra("synopsis", getString(R.string.massg));
			
			startActivity(intnt);
		}
		else if(arg2 == 2)
		{
			intnt.putExtra("whereClause", "category = '3'");
			
			intnt.putExtra("synopsis", getString(R.string.soy));
			
			startActivity(intnt);
		}
		else if(arg2 == 3)
		{
			intnt.putExtra("whereClause", "category = '4'");
			
			intnt.putExtra("synopsis", getString(R.string.creatine));
			
			startActivity(intnt);
		}
		else if(arg2 == 4)
		{
			intnt.putExtra("whereClause", "category = '5'");
			
			intnt.putExtra("synopsis", getString(R.string.glut));
			
			startActivity(intnt);
		}
		else if(arg2 == 5)
		{
			intnt.putExtra("whereClause", "category = '6'");
			
			intnt.putExtra("synopsis", getString(R.string.multivit));
			
			startActivity(intnt);
		}
		else if(arg2 == 6)
		{
			intnt.putExtra("whereClause", "category = '7'");
			
			intnt.putExtra("synopsis", getString(R.string.nos));
			
			startActivity(intnt);
		}
	}


}
