package com.sagar.proteinfinder;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class StandardDBObject {
    protected SQLiteDatabase mdatabase;
    protected DatabaseOpenHelper mdbOpenHelper;
    private Context ctx;

    public StandardDBObject(Context context) {
    	ctx = context;
    	mdbOpenHelper = DatabaseOpenHelper.getInstance(context);
        }
    
    public void open() throws SQLException {
	// open database in reading/writing mode
	if (null == mdatabase || !mdatabase.isOpen())
	    mdatabase = mdbOpenHelper.getWritableDatabase();
    }

    public void close() {
	if (mdatabase != null)
	    mdatabase.close();
    }
    
    public ArrayList<ListBean> getCategoryArray(String where){
    	Cursor result = mdatabase.query("proteins_tbl", null, where, null,
    			null, null, "Name");
    	
    	ArrayList<ListBean> arr=new ArrayList<ListBean> ();
    	
    	ListBean lbn = new ListBean();
    	
    	for (boolean hasItem = result.moveToFirst(); hasItem; hasItem = result
    			.moveToNext()) {
    		
    		lbn = new ListBean();
    		
    		lbn.title = result.getString(result.getColumnIndex("Name"));
    		
    		lbn.desc = result.getString(result.getColumnIndex("shortDesc"));
    		
    		lbn.setRating( result.getDouble(result.getColumnIndex("Rating")));
    		
    		lbn.setFactor( result.getInt(result.getColumnIndex("Factor")));
    		
    		lbn.setCategory( result.getString(result.getColumnIndex("category")));
    		
    		lbn.setUrl1( result.getString(result.getColumnIndex("url1")));
    		
    		lbn.setUrl2( result.getString(result.getColumnIndex("url2")));
    		
    		lbn.draw =  ctx.getResources().getIdentifier(result.getString(result.getColumnIndex("Img")), "drawable", ctx.getPackageName());;
    		
    		arr.add(lbn);
    		}
    	
    	return arr;
    }
    
    public ArrayList<FoodBean> getFoodArray(String where){
    	Cursor result = mdatabase.query("foods_tbl", null, where, null,
    			null, null, "Name");
    	
    	ArrayList<FoodBean> arr=new ArrayList<FoodBean> ();
    	
    	FoodBean lbn = new FoodBean();
    	
    	for (boolean hasItem = result.moveToFirst(); hasItem; hasItem = result
    			.moveToNext()) {
    		
    		lbn = new FoodBean();
    		
    		lbn.title = result.getString(result.getColumnIndex("Name"));
    		
    		lbn.qserv = result.getString(result.getColumnIndex("qserving"));
    		
    		lbn.prot = result.getString(result.getColumnIndex("protein"));
    		
    		lbn.cal = result.getString(result.getColumnIndex("cals"));
    		
    		lbn.pperc = result.getString(result.getColumnIndex("pperc"));
    		
    		lbn.setType( result.getInt(result.getColumnIndex("Type")));
    		
    		lbn.draw =  ctx.getResources().getIdentifier(result.getString(result.getColumnIndex("Img")), "drawable", ctx.getPackageName());;
    		
    		arr.add(lbn);
    		}
    	
    	return arr;
    }
}
