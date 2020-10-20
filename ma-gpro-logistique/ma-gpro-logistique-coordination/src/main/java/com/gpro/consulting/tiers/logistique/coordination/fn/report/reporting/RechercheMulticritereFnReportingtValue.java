package com.gpro.consulting.tiers.logistique.coordination.fn.report.reporting;

import java.util.Calendar;

/**
 * @author Ameni Berrich
 *
 */
public class RechercheMulticritereFnReportingtValue {

	private Long clientId;
	private Long produitId;
	private Long regionId;
	private Calendar dateMin;
	private Calendar dateMax;
	private String typeRapport;
	
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	public Long getProduitId() {
		return produitId;
	}
	public void setProduitId(Long produitId) {
		this.produitId = produitId;
	}
	public Long getRegionId() {
		return regionId;
	}
	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}
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
