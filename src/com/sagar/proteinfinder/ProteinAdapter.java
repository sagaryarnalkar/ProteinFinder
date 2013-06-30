package com.sagar.proteinfinder;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ProteinAdapter extends com.haarman.listviewanimations.ArrayAdapter<ListBean>
{
	Context context;
	private boolean showMoreDet = false;
	
	public boolean isShowMoreDet() {
		return showMoreDet;
	}

	public void setShowMoreDet(boolean showMoreDet) {
		this.showMoreDet = showMoreDet;
	}

	public ProteinAdapter(Context context, int resourceId,
            ArrayList<ListBean> items) {
        super(items);
        this.context = context;
    }
 
    /*private view holder class*/
    public class ViewHolder {
        ImageView imageView;
        TextView txtTitle;
        TextView txtCat;
        TextView txtDesc;
        String url1;
        String url2;
    }
    
    private View getMessage(ListBean rowItem,View convertView,LayoutInflater mInflater )
    {
    	if (convertView == null) {
    		convertView = mInflater.inflate(R.layout.synopsis_list_item, null);
        } 

    	TextView syn = (TextView) convertView.findViewById(R.id.tvSyn);
    	if(null!=syn)
    		syn.setText(rowItem.desc);
    	else
    	{
    		convertView = mInflater.inflate(R.layout.synopsis_list_item, null);
    		syn = (TextView) convertView.findViewById(R.id.tvSyn);
    		syn.setText(rowItem.desc);
    	}
    	
    	return convertView;
    	
    	
    }
    
    private String getCategoryText(String cat)
    {
    	if(cat.startsWith("1"))
    	{
    		return "Whey Protein";
    	}
    	else if(cat.startsWith("2"))
    	{
    		return "Mass Gainer";
    	}
    	else if(cat.startsWith("3"))
    	{
    		return "Soy Protein";
    	}
    	else if(cat.startsWith("4"))
    	{
    		return "Creatine";
    	}
    	else if(cat.startsWith("5"))
    	{
    		return "Glutamine";
    	}
    	else if(cat.startsWith("6"))
    	{
    		return "MultiVitamin";
    	}
    	else if(cat.startsWith("7"))
    	{
    		return "Nitric Oxide";
    	}
    	else
    		return "";
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        ListBean rowItem = getItem(position);
        
        
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if(rowItem.getCategory() !=null && ("synopsis").equals(rowItem.getCategory()))
		{
			return getMessage(rowItem,convertView,mInflater);
		}
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.generic_list_item, null);
            holder = new ViewHolder();
            holder.txtDesc = (TextView) convertView.findViewById(R.id.tvOptionDesc);
            holder.txtTitle = (TextView) convertView.findViewById(R.id.tvOption);
            holder.imageView = (ImageView) convertView.findViewById(R.id.ivStockIcon);
            holder.txtCat = (TextView) convertView.findViewById(R.id.tvCat);
            convertView.setTag(holder);
        } else{
        	holder = (ViewHolder) convertView.getTag();
        	if(holder == null)
        	{
        		convertView = mInflater.inflate(R.layout.generic_list_item, null);
        		holder = new ViewHolder();
                holder.txtDesc = (TextView) convertView.findViewById(R.id.tvOptionDesc);
                holder.txtTitle = (TextView) convertView.findViewById(R.id.tvOption);
                holder.imageView = (ImageView) convertView.findViewById(R.id.ivStockIcon);
                holder.txtCat = (TextView) convertView.findViewById(R.id.tvCat);
                convertView.setTag(holder);
        	}
        }
            
 
        if(rowItem.desc !=null && rowItem.desc.length()>0)
        {
        	holder.txtDesc.setText(rowItem.desc);
        	holder.txtDesc.setMaxLines(4);
        }
        
        holder.txtTitle.setText(rowItem.title);
        
        if(rowItem.draw > -1)
        	holder.imageView.setImageResource(rowItem.draw);
        else
        	holder.imageView.setVisibility(View.GONE);
        
        if(showMoreDet)
        {
        	holder.txtCat.setVisibility(View.VISIBLE);
        	holder.txtCat.setText(getCategoryText(rowItem.getCategory())+" - This product is rated: "+rowItem.getRating());
        }
        
        if(rowItem.getFactor() == 93)
        {
        	holder.txtCat.setVisibility(View.VISIBLE);
        	holder.txtCat.setText("+ Click to expand");
        }
 
        
        holder.url1 = rowItem.getUrl1();
        holder.url2 = rowItem.getUrl2();
        
        return convertView;
    }
    
}
