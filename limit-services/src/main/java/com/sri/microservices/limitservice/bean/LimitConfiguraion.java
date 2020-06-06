package com.sri.microservices.limitservice.bean;

public class LimitConfiguraion {
	
	private int maximum;
	private int minimum;

	public LimitConfiguraion(int maximum, int minium) {
		super();
		this.maximum = maximum;
		this.minimum = minium;
	}
	protected LimitConfiguraion() {
		super();
		this.maximum = maximum;
		this.minimum = minimum;
	}
	public int getMaximum() {
		return maximum;
	}
	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}
	public int getMinimum() {
		return minimum;
	}
	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}
	
	

}
