package com.gpro.consulting.tiers.commun.coordination.utils.value;

import java.util.Calendar;

public class DateUtilsValue {
	
	private Calendar dateMin ; 
	private Calendar dateMax;
	
	
	public Calendar getDateMin() {
		return dateMin;
	}
	public void setDateMin(Calendar dateMin) {
		this.dateMin = dateMin;
	}
	public Calendar getDateMax() {
		return dateMax;
	}
	public void setDateMax(Calendar dateMax) {
		this.dateMax = dateMax;
	}
	public DateUtilsValue(Calendar dateMin, Calendar dateMax) {
		super();
		this.dateMin = dateMin;
		this.dateMax = dateMax;
	}
	
	
	

}
