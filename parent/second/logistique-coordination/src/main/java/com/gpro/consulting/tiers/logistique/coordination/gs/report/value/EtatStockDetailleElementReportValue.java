package com.gpro.consulting.tiers.logistique.coordination.gs.report.value;

import java.util.Calendar;

/**
 * 
 * @author Zeineb Medimagh
 * @since 30/10/2016
 *
 */
public class EtatStockDetailleElementReportValue extends EtatStockGlobalElementReportValue {
	
	private String refLot;
	private Calendar dateEntree;
	
	public String getRefLot() {
		return refLot;
	}
	public void setRefLot(String refLot) {
		this.refLot = refLot;
	}
	public Calendar getDateEntree() {
		return dateEntree;
	}
	public void setDateEntree(Calendar dateEntree) {
		this.dateEntree = dateEntree;
	}
	
}
