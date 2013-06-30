package com.sagar.proteinfinder;

import java.text.DecimalFormat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class ActivityProteinCalculator  extends ProteinBasicActivity implements OnSeekBarChangeListener,OnClickListener{
	
	private SeekBar weightBar;
	private TextView tvWtIndicator;
	private ImageView ivWtPicture;
	DecimalFormat nfPound;
	private int weight;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calculator);
		
		weightBar = (SeekBar) findViewById(R.id.weight_slider1);
		tvWtIndicator = (TextView) findViewById(R.id.tvWtIndicator1);
		ivWtPicture = (ImageView) findViewById(R.id.ivWtPicture1);
		
		weightBar.setOnSeekBarChangeListener(this);
		
		Button btnFindDosage = (Button) findViewById(R.id.findDosage1);
		
		btnFindDosage.setOnClickListener(this);
		
		nfPound = new DecimalFormat("#");
		
		LinearLayout ll = (LinearLayout) findViewById(R.id.lldosCreatine);
		
		ll.setOnClickListener(this);
		
		ll = (LinearLayout) findViewById(R.id.llDosGlutamine);
		
		ll.setOnClickListener(this);
		
		ll = (LinearLayout) findViewById(R.id.llDosbcaa);
		
		ll.setOnClickListener(this);
		
		if(savedInstanceState==null)
		{
			weight = 35;
		}
		else
		{
			weight = savedInstanceState.getInt("protWt");
		}
		
	}
	
	@Override
	public void onClick(View v) {
		super.onClick(v);
		
		if(v.getId() == R.id.findDosage1)
		{
			weightBar.setVisibility(View.GONE);
			ivWtPicture.setVisibility(View.GONE);
			Button btnFindDosage = (Button) findViewById(R.id.findDosage1);
			btnFindDosage.setVisibility(View.GONE);
			DecimalFormat nf = new DecimalFormat("#.0");
			tvWtIndicator.setMaxLines(100);
			tvWtIndicator.setText("Recommended daily Protein consumption is "+nf.format(weight*3.1)+"gms (You need this much only if you workout regularly, or its just being wasted.)");
			
		}
		else if(v.getId() == R.id.lldosCreatine)
		{
			Intent intnt = new Intent(this,ActivityOtherDosage.class);
			intnt.putExtra("type", "C");
			startActivity(intnt);
		}
		else if(v.getId() == R.id.llDosGlutamine)
		{
			Intent intnt = new Intent(this,ActivityOtherDosage.class);
			intnt.putExtra("type", "G");
			startActivity(intnt);
			
		}
		else if(v.getId() == R.id.llDosbcaa)
		{
			Intent intnt = new Intent(this,ActivityOtherDosage.class);
			intnt.putExtra("type", "B");
			startActivity(intnt);
		}
		
		
		
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
		super.onSaveInstanceState(outState);
		outState.putInt("protWt", weight);
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		if(progress>125)
        {
			seekBar.setProgress(125);
        }
		else if(progress<10)
        {
			seekBar.setProgress(10);
        }
		else
		{
			int weight = progress+25;
			tvWtIndicator.setText(weight+"kg"+" ("+nfPound.format(weight*2.2046)+"lbs)");
			
			if(weight<58 && this.weight >=58)
			{
				ivWtPicture.setImageDrawable(getResources().getDrawable(R.drawable.image1));
			}
			else if(weight>=58 && weight<81 && (this.weight >=81 || this.weight <58))
			{
				ivWtPicture.setImageDrawable(getResources().getDrawable(R.drawable.image2));
			}
			else if(weight>=81 && weight<104 && (this.weight >=104 || this.weight <81))
			{
				ivWtPicture.setImageDrawable(getResources().getDrawable(R.drawable.image3));
			}
			else if(weight>=104 && weight<127 && (this.weight >=127 || this.weight <104))
			{
				ivWtPicture.setImageDrawable(getResources().getDrawable(R.drawable.image4));
			}
			else if(weight>=127 && (this.weight <127))
			{
				ivWtPicture.setImageDrawable(getResources().getDrawable(R.drawable.image5));
			}
			
			this.weight = weight;
			
		}
		
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}
	
}
