package com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.situation.value;

import java.util.Calendar;

/**
 * @author Ameni Berrich
 *
 */
public class RechercheMulticritereSituationReportingValue {

	private Long regionId;
	private Long partieIntId;
	private Calendar dateMin;
	private Calendar dateMax;
	private Double soldeMin;
	private Double soldeMax;

	
	private Long deviseId;
	
	
	public Long getDeviseId() {
		return deviseId;
	}

	public void setDeviseId(Long deviseId) {
		this.deviseId = deviseId;
	}

	public Long getRegionId() {
		return regionId;
	}

	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}

	public Long getPartieIntId() {
		return partieIntId;
	}

	public void setPartieIntId(Long partieIntId) {
		this.partieIntId = partieIntId;
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

	public Double getSoldeMin() {
		return soldeMin;
	}

	public void setSoldeMin(Double soldeMin) {
		this.soldeMin = soldeMin;
	}

	public Double getSoldeMax() {
		return soldeMax;
	}

	public void setSoldeMax(Double soldeMax) {
		this.soldeMax = soldeMax;
	}

}
