package com.gpro.consulting.tiers.logistique.coordination.atelier.report.bonsortiefini.value;

import java.util.Calendar;


/**
 * 
 * @author Wahid Gazzah
 * @since 01/03/2016
 *
 */
public class BonSortieReportElementValue {
	
	private String reference;
	private Calendar dateSortie;
	private String observation;
	private Integer nbColis; 
	private Double metrageTotal;
	private String type; 
	private String partieIntDesignation;
	
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public Calendar getDateSortie() {
		return dateSortie;
	}
	public void setDateSortie(Calendar dateSortie) {
		this.dateSortie = dateSortie;
	}
	public String getObservation() {
		return observation;
	}
	public void setObservation(String observation) {
		this.observation = observation;
	}
	public Integer getNbColis() {
		return nbColis;
	}
	public void setNbColis(Integer nbColis) {
		this.nbColis = nbColis;
	}
	public Double getMetrageTotal() {
		return metrageTotal;
	}
	public void setMetrageTotal(Double metrageTotal) {
		this.metrageTotal = metrageTotal;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPartieIntDesignation() {
		return partieIntDesignation;
	}
	public void setPartieIntDesignation(String partieIntDesignation) {
		this.partieIntDesignation = partieIntDesignation;
	}
	
	
	
}
