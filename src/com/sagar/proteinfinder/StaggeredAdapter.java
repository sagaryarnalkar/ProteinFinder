package com.sagar.proteinfinder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;



public class StaggeredAdapter extends ArrayAdapter<Integer> {

	private Context ctx;
	private Integer[] objects;
	
public StaggeredAdapter(Context context, int textViewResourceId,
Integer[] objects) {
super(context, textViewResourceId, objects);
ctx=context;
this.objects = objects;

}

@Override
public View getView(int position, View convertView, ViewGroup parent) {

if (convertView == null) {

convertView =new ImageView(ctx);
}

ImageView iv = (ImageView) convertView;

iv.setImageResource(objects[position]);

return convertView;
}

}