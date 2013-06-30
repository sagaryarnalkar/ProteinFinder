package com.sagar.proteinfinder;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.inmobi.androidsdk.IMAdRequest;
import com.inmobi.androidsdk.IMAdView;

public class ActivityProteinFinder extends ProteinBasicActivity {
	private int genderSelection;
	ImageButton ib1;
	ImageButton ib2;
	RadioButton r1;
	RadioButton r2;
	RadioGroup genderRadio;
	private int goalSelection;
	ImageButton ib3;
	ImageButton ib4;
	RadioButton r3;
	RadioButton r4;
	RadioGroup goalRadio;
	CircularSeekBar csb;
	private double[] mValueArray = new double[7];
	private String ARGS_VALUE = "value";
	Button findResults;
	private IMAdView mIMAdView;
	public static final String inMobiId = "56cf9ece44ab4d3ca37d869306aeabef";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_protein_finder);

		mIMAdView = (IMAdView) findViewById(R.id.imAdview1);

		IMAdRequest adRequest = new IMAdRequest();
		//adRequest.setTestMode(true);
		mIMAdView.setIMAdRequest(adRequest);

		mIMAdView.loadNewAd(adRequest);

		init();

		if (savedInstanceState != null) // restoring previous state
		{
			double value = savedInstanceState.getDouble(ARGS_VALUE);
			csb.setSelectedStepForValue(value);
		} else
			// setting default value
			csb.setSelectedStepForValue(1);

		// Formatting the value into a string representation
		CircularSeekBar.Formatter f = new CircularSeekBar.Formatter() {

			@Override
			public String format(double value) {
				return String.valueOf(new Double(value).intValue());
			}

		};
		csb.setFormatter(f);

		// Sets how many steps should be changed when pressing + and - buttons
		csb.setButtonChangeInterval(1);
	}

	private void init() {
		ib1 = (ImageButton) findViewById(R.id.ib1);
		ib2 = (ImageButton) findViewById(R.id.ib2);
		r1 = (RadioButton) findViewById(R.id.radioMale);
		r2 = (RadioButton) findViewById(R.id.radioFemale);
		r1.setOnClickListener(this);
		r2.setOnClickListener(this);
		genderRadio = (RadioGroup) findViewById(R.id.radioSex);
		ib1.setOnClickListener(this);
		ib2.setOnClickListener(this);
		ib3 = (ImageButton) findViewById(R.id.ib3);
		ib4 = (ImageButton) findViewById(R.id.ib4);
		r3 = (RadioButton) findViewById(R.id.radioFL);
		r4 = (RadioButton) findViewById(R.id.radioMB);
		r3.setOnClickListener(this);
		r4.setOnClickListener(this);
		goalRadio = (RadioGroup) findViewById(R.id.radioGoal);
		ib3.setOnClickListener(this);
		ib4.setOnClickListener(this);

		csb = (CircularSeekBar) findViewById(R.id.circularSeekBar);

		// Setting the colors of the circle
		csb.setEmptyCircleColor(Color.GRAY);
		csb.setSelectedCircleColor(getResources().getColor(R.color.app_blue));
		csb.setSeekBarThumsColor(Color.BLACK);
		csb.setButtonPushedColor(Color.LTGRAY);

		// Setting the values that seek bar will iterate through
		generateValueArray();
		csb.setValueArray(mValueArray);

		findResults = (Button) findViewById(R.id.findResults);

		findResults.setOnClickListener(this);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		double selectedValue = csb.getSelectedValue();
		outState.putDouble(ARGS_VALUE, selectedValue);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);

		if (R.id.ib1 == v.getId() || r1.getId() == v.getId()) {
			genderSelection = 0;
			r1.setChecked(true);
			r2.setChecked(false);
		} else if (R.id.ib2 == v.getId() || r2.getId() == v.getId()) {
			genderSelection=1;
			r2.setChecked(true);
			r1.setChecked(false);
		} else if (R.id.ib3 == v.getId() || r3.getId() == v.getId()) {
			// goalRadio.check(r3.getId());
			goalSelection = 0;
			r3.setChecked(true);
			r4.setChecked(false);
		} else if (R.id.ib4 == v.getId() || r4.getId() == v.getId()) {
			// goalRadio.check(r4.getId());
			goalSelection = 1;
			r3.setChecked(false);
			r4.setChecked(true);
		} else if (R.id.findResults == v.getId()) {
			AnalyticsFactory.getAnalyticsClient().tagEvent(this,
					"Protein Finder Invoked");

			Intent intnt = new Intent(this, ActivityProteinListing.class);

			intnt.putExtra("whereClause", getWhereClause()+(genderSelection==0?" OR Factor = 21":" OR Factor = 22"));

			intnt.putExtra("synopsis", getSynopsis());

			startActivity(intnt);

			finish();
		}

	}

	private String getWhereClause() {
		Double frd = csb.getSelectedValue();
		int freq = frd.intValue();
		int baseFactor = 0;
		frd = null;
		if (goalSelection == 0) {
			if (freq >= 5) {
				return "Factor < 8";
			}
			else if(freq>=3 && freq < 5)
			{
				return "Factor < 5";
			}
			else
			{
				return "Factor < 3";
			}
		} else if (goalSelection == 1) {
			if (freq >= 5) {
				return "Factor < 15 AND Factor >7";
			}
			else if(freq>=3 && freq < 5)
			{
				return "Factor < 12 AND Factor >7";
			}
			else
			{
				return "Factor < 10 AND Factor>7";
			}
		}

		return null;
	}
	private static final String greeting = "Great, we have found the right plan for you. ";
	private String getSynopsis() {
		Double frd = csb.getSelectedValue();
		int freq = frd.intValue();
		frd = null;
		if (goalSelection == 0) {
			if (freq >= 5) {
				return greeting
						+ "You have a high workout frequency, it would be better if you take recovery aiding products to give yourself the extra edge.";
			}
			else if(freq>=3 && freq < 5)
			{
				return greeting+"Your have a moderate workout frequency, a basic protein plan would do well and add a bit of vitamins.";
			}
			else
			{
				return greeting+"Your have a low workout frequency, it would be better for you to use supplements if you increase your workout frequency. A natural protein rich diet should be enough for you but vitamins should help.";
			}
		} else if (goalSelection == 1) {
			if (freq >= 5) {
				return greeting
						+ "You have a high workout frequency, you should be taking regular protein supplementation and recovery aids. A Whey and Mass Gainer combo would be the best.";
			}
			else if(freq>=3 && freq < 5)
			{
				return greeting+"Your have a moderate workout frequency, a mass gainer supplement is what you need along with pre workout products which will give you an advantage.";
			}
			else
			{
				return greeting+"Your have a low workout frequency, it would be better for you to use supplements if you increase your workout frequency. A natural protein rich diet should be enough for you but vitamins should help.";
			}
		}

		return null;
	}

	/** Generates the values in the valueArray **/
	private void generateValueArray() {
		int arrayIndex = 0;
		double arrayValue = 1;

		while (arrayValue < 8) {
			mValueArray[arrayIndex] = arrayValue;
			arrayValue += 1;
			arrayIndex++;
		}
	}

}
