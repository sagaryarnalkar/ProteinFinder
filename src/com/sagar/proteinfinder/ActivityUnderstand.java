package com.sagar.proteinfinder;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.haarman.listviewanimations.swinginadapters.prepared.SwingBottomInAnimationAdapter;

public class ActivityUnderstand extends ProteinBasicActivity implements
		OnItemClickListener {
	private ListView mListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mListView = (ListView) findViewById(R.id.optionList);

		ArrayList<ListBean> listObjs = new ArrayList<ListBean>();

		listObjs.add(new ListBean(
				"Isolate",
				"An isolated protein source is one that has been chemically purified to remove most everything other then the actual protein source. Generally isolates are 90%+ pure protein.",
				-1,93));

		listObjs.add(new ListBean(
				"Concentrate",
				"A concentrated protein source is not as pure as an isolate, and generally contains 70 to 85% of the protein source. Concentrates contain more fats, carbohydrates, and in the case of whey protein, more lactose.",
				-1,93));

		listObjs.add(new ListBean(
				"Blends",
				"A protein blend is a combination of various protein sources and purity levels. A blend can be more cost effective then a pure isolate, and can also offer the benefit of having both fast digesting and slow digesting protein sources.",
				-1,93));
		
		listObjs.add(new ListBean(
				"Amino Acids",
				"Amino acids are the building blocks of protein. While there are over 100 total amino acids, only 20 amino acids are considered standard. These standard amino acids are separated into two categories: essential and non-essential amino acids. Essential amino acids cannot be created in the human body, and must be obtained from food. Non-essential amino acids can be synthesized, or created in the human body.",
				-1,93));
		
		listObjs.add(new ListBean(
				"BCAA-Branched chain amino acids",
				"BCAA is a term that refers to a chain  of the three essential amino acids leucine, isoleucine and valine. The combination of these 3 essential amino acids make up over one-third the skeletal muscle in the body, and play a vital role in protein synthesis.",
				-1,93));
		
		listObjs.add(new ListBean(
				"Mass Gainer",
				"Mass gainers are high calorie protein powders meant to assist bodybuilders and athletes who are in need of rapid weight gain. They can be used as meals on the go, or in between meals as a means of adding extra daily calories.",
				-1,93));
		
		listObjs.add(new ListBean(
				"Meal Replacements",
				"Meal replacement protein products are considered to be entire meals in and of themselves. They contain a formulated nutritional and macronutrient blend that provides not only enough protein, but also an appropriate amount of carbs, healthy fats and vitamins and minerals.",
				-1,93));
		
		listObjs.add(new ListBean(
				"Recovery",
				"A recovery blend is a protein supplement that contains any number of additional, non-protein supplement products meant to aid in post-workout recovery. These supplements range from creatine to multivitamin and minerals, and larger doses of glutamine and taurine.",
				-1,93));
		
		listObjs.add(new ListBean(
				"Whey Isolate",
				"Whey isolate is a more expensive version of whey protein. It is a higher quality protein source with a higher biological value, and contains less fat and lactose per serving then whey concentrate. Whey isolate generally contains 90 to 98 percent protein.",
				-1,93));
		
		listObjs.add(new ListBean(
				"Whey Concentrate",
				"Whey concentrate is a more cost-effective member of the whey family. It requires less processing time, but also contains more fat and lactose. Whey concentrate is 70 to 85 percent protein.",
				-1,93));
		
		listObjs.add(new ListBean(
				"Whey Protein Blend",
				"Whey protein blends are specialized protein formulas that contain both whey Isolates and whey concentrates. Whey protein blends are generally more cost effective then whey isolate, and have a higher protein percentage ratio then whey concentrates.",
				-1,93));
		
		listObjs.add(new ListBean(
				"Casein Protein",
				"Casein proteins account for 80% of the protein in milk.Casein is a slow digesting protein that is isolated from milk. It is 92 percent protein, and has a very “thick” taste. Because of this, it is a very popular protein in weight gainers. Casein, although it has a lower BV value then whey, is more efficiently used to build muscle. Because casein protein is used by the body to build muscle, and less is used as a energy source, casein supplementation encourages the body to use carbs and stored fat for energy. Casein is also very high in the popular bodybuilding supplement glutamine.",
				-1,93));
		
		listObjs.add(new ListBean(
				"Soy Protein",
				"Soy protein is high quality, but not as efficient as milk or casein protein, It is a fast digesting protein source that has an average amino acid profile. Because of this, it is not the most desirable protein source for those looking to build muscle.",
				-1,93));

		ProteinAdapter adapter = new ProteinAdapter(this,
				R.layout.generic_list_item, listObjs);

		SwingBottomInAnimationAdapter swingBottomInAnimationAdapter = new SwingBottomInAnimationAdapter(
				adapter);
		swingBottomInAnimationAdapter.setListView(mListView);
		mListView.setAdapter(swingBottomInAnimationAdapter);

		mListView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

		TextView descView = (TextView) arg1.findViewById(R.id.tvOptionDesc);
		TextView expView=(TextView) arg1.findViewById(R.id.tvCat);
		if (descView != null) {
			String tagged = (String) descView.getTag();
			if (("Y").equals(tagged)) {
				descView.setTag(null);

				expView.setText("+ Click to expand");
				
				descView.setMaxLines(4);
			} else {
				descView.setTag("Y");
				
				expView.setText("- Click to collapse");

				descView.setMaxLines(100);
			}

		}

	}
}
