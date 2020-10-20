package com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.reglement;

import java.util.Calendar;

/**
 * 
 * @author Hajer Amri
 * @since 30/01/2017
 *
 */
public class ReglementReportParRefElementEltReglementValue {

	// private Long id;
	private String reference;
	private Calendar date;
	private Double montantTotal;

	// private Long partieIntId;
	// private String partieIntAbreviation;
	// private String partieIntReference;
	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public Double getMontantTotal() {
		return montantTotal;
	}

	public void setMontantTotal(Double montantTotal) {
		this.montantTotal = montantTotal;
	}

}
