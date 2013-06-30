package com.sagar.proteinfinder;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class FoodAdapter extends com.haarman.listviewanimations.ArrayAdapter<FoodBean>
{
	Context context;
	private boolean showMoreDet = false;
	
	public boolean isShowMoreDet() {
		return showMoreDet;
	}

	public void setShowMoreDet(boolean showMoreDet) {
		this.showMoreDet = showMoreDet;
	}

	public FoodAdapter(Context context, int resourceId,
            ArrayList<FoodBean> items) {
        super(items);
        this.context = context;
    }
 
    
    
    private View getMessage(FoodBean rowItem,View convertView,LayoutInflater mInflater )
    {
    	if (convertView == null) {
    		convertView = mInflater.inflate(R.layout.synopsis_list_item, null);
        } 

    	TextView syn = (TextView) convertView.findViewById(R.id.tvSyn);
    	if(null!=syn)
    		syn.setText(rowItem.title);
    	else
    	{
    		convertView = mInflater.inflate(R.layout.synopsis_list_item, null);
    		syn = (TextView) convertView.findViewById(R.id.tvSyn);
    		syn.setText(rowItem.title);
    	}
    	
    	return convertView;
    	
    	
    }
    
    /*private view holder class*/
    private class ViewHolder {
        ImageView imageView;
        TextView txtTitle;
        TextView txtqserv;
        TextView txtprot;
        TextView txtcal;
        TextView txtppercal;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        FoodBean rowItem = getItem(position);
        
        
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if(rowItem.getType() ==5)
		{
			return getMessage(rowItem,convertView,mInflater);
		}
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.food_list_item, null);
            holder = new ViewHolder();
            holder.txtqserv = (TextView) convertView.findViewById(R.id.tvqserv);
            holder.txtTitle = (TextView) convertView.findViewById(R.id.tvOption);
            holder.imageView = (ImageView) convertView.findViewById(R.id.ivStockIcon);
            holder.txtprot = (TextView) convertView.findViewById(R.id.tvproteins);
            holder.txtcal = (TextView) convertView.findViewById(R.id.tvcals);
            holder.txtppercal = (TextView) convertView.findViewById(R.id.tvppercals);
            convertView.setTag(holder);
        } else{
        	holder = (ViewHolder) convertView.getTag();
        	if(holder == null)
        	{
        		convertView = mInflater.inflate(R.layout.food_list_item, null);
        		holder = new ViewHolder();
        		 holder.txtqserv = (TextView) convertView.findViewById(R.id.tvqserv);
                 holder.txtTitle = (TextView) convertView.findViewById(R.id.tvOption);
                 holder.imageView = (ImageView) convertView.findViewById(R.id.ivStockIcon);
                 holder.txtprot = (TextView) convertView.findViewById(R.id.tvproteins);
                 holder.txtcal = (TextView) convertView.findViewById(R.id.tvcals);
                 holder.txtppercal = (TextView) convertView.findViewById(R.id.tvppercals);
                convertView.setTag(holder);
        	}
        }
            
 
//        if(rowItem.desc !=null && rowItem.desc.length()>0)
//        {
//        	holder.txtDesc.setText(rowItem.desc);
//        	holder.txtDesc.setMaxLines(4);
//        }
        
        holder.txtTitle.setText(rowItem.title);
        holder.txtqserv.setText("Quantity per Serving:"+rowItem.getQserv());
        holder.txtprot.setText("Protein content:"+rowItem.getProt());
        holder.txtcal.setText("Calories:"+rowItem.getCal());
        holder.txtppercal.setText("Protein per 100 calories:"+rowItem.getPperc());
        
        if(rowItem.draw > -1)
        	holder.imageView.setImageResource(rowItem.draw);
        else
        	holder.imageView.setVisibility(View.GONE);
        
//        if(showMoreDet)
//        {
//        	holder.txtCat.setVisibility(View.VISIBLE);
//        	holder.txtCat.setText(getCategoryText(rowItem.getCategory())+" - This product is rated: "+rowItem.getRating());
//        }
//        
//        if(rowItem.getFactor() == 93)
//        {
//        	holder.txtCat.setVisibility(View.VISIBLE);
//        	holder.txtCat.setText("+ Click to expand");
//        }
 
        return convertView;
    }
    
}
