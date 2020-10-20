package com.gpro.consulting.tiers.logistique.coordination.gs.chart.value;

import java.util.Calendar;

/**
 * @author Wahid Gazzah
 * @since 14/04/2016
 */
public class RechercheMulticritereMouvementChartValue {
	
	//type "ENTREE" or "SORTIE"
	private String type;
	private Calendar dateFrom;
	private Calendar dateTo;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Calendar getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(Calendar dateFrom) {
		this.dateFrom = dateFrom;
	}
	public Calendar getDateTo() {
		return dateTo;
	}
	public void setDateTo(Calendar dateTo) {
		this.dateTo = dateTo;
	}
	
	

}
