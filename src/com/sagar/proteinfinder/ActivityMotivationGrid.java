package com.sagar.proteinfinder;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;

import com.origamilabs.library.views.StaggeredGridView;
import com.origamilabs.library.views.StaggeredGridView.OnItemClickListener;

public class ActivityMotivationGrid  extends ProteinBasicActivity implements OnItemClickListener{

	private Integer urls[];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_motivation_grid);
		
		StaggeredGridView gridView = (StaggeredGridView) this.findViewById(R.id.staggeredGridView1);
		
		int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
		
		gridView.setItemMargin(margin); // set the GridView margin 

		gridView.setPadding(margin, 0, margin, 0); // have the margin on the sides as well
		
		urls = new Integer[]{R.drawable.image6,
				R.drawable.image7,
				R.drawable.image8,
				R.drawable.image9,
				R.drawable.image10,
				R.drawable.image11,
				R.drawable.image12,
				R.drawable.image13,
				R.drawable.image14,
				R.drawable.image15,
				R.drawable.image16,
				R.drawable.image17,
				R.drawable.image18,
				R.drawable.image19,
				R.drawable.image20,
				R.drawable.image21,
				R.drawable.image22,
				R.drawable.image23,
				R.drawable.image24,
				R.drawable.image25,
				R.drawable.image26,
				R.drawable.image30,
				R.drawable.image27,
				R.drawable.image28,
				R.drawable.image29,
				R.drawable.image31,
				R.drawable.image32,
				R.drawable.image33,
				R.drawable.image34,
				R.drawable.image35,
				R.drawable.image36,
				R.drawable.image37,
				R.drawable.image38,
				R.drawable.image39,
				R.drawable.image40,
				R.drawable.image41
				};

		StaggeredAdapter adapter = new StaggeredAdapter(this, R.id.findResults, urls);

		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(this);
		adapter.notifyDataSetChanged();
	}

	@Override
	public void onItemClick(StaggeredGridView parent, View view, int position,
			long id) {
		Intent intnt = new Intent(this,ActivityPhotoView.class);
		intnt.putExtra("picId", urls[position]);
		startActivity(intnt);
	}
}
