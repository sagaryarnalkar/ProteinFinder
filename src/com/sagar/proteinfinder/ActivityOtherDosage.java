package com.sagar.proteinfinder;

import android.os.Bundle;
import android.widget.TextView;

public class ActivityOtherDosage  extends ProteinBasicActivity
{
	private TextView tvInfo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_other_dosage);
		
		tvInfo = (TextView) findViewById(R.id.tvInfo);
		
		String type = getIntent().getStringExtra("type");
		
		writeInfo(type);
		
	}
	
	private void writeInfo(String type)
	{
		if("C".equals(type))
		{
			tvInfo.setText(getString(R.string.c_dosage));
		}
		else if("G".equals(type))
		{
			tvInfo.setText(getString(R.string.g_dosage));
		}
		else if("B".equals(type))
		{
			tvInfo.setText(getString(R.string.b_dosage));
		}
	}
}
