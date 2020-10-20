package com.gpro.consulting.tiers.logistique.coordination.gl.reporting;

import java.util.Calendar;

/**
 * @author Ameni Berrich
 *
 */
public class RechercheMulticritereLogistiqueReportingtValue {

	private Calendar dateMin;
	private Calendar dateMax;
	private String typeRapport;
	
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
	public String getTypeRapport() {
		return typeRapport;
	}
	public void setTypeRapport(String typeRapport) {
		this.typeRapport = typeRapport;
	}
	
}
