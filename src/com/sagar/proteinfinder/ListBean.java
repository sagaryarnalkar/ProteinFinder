package com.sagar.proteinfinder;

public class ListBean 
{
	
	public ListBean(String ttle,String des,int dr)
	{
		title = ttle;
		desc=des;
		draw=dr;
	}
	public ListBean(String ttle,String des,int dr,int factD)
	{
		title = ttle;
		desc=des;
		draw=dr;
		setFactor(factD);
	}
	
	public ListBean()
	{
		
	}
	
	public String title;
	public String desc;
	public int draw;
	private double rating;
	private String category;
	private int factor;
	private String url1;
	private String url2;
	
	
	
	
	public String getUrl1() {
		return url1;
	}
	public void setUrl1(String url1) {
		this.url1 = url1;
	}
	public String getUrl2() {
		return url2;
	}
	public void setUrl2(String url2) {
		this.url2 = url2;
	}
	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getFactor() {
		return factor;
	}

	public void setFactor(int factor) {
		this.factor = factor;
	}
	
	
}
