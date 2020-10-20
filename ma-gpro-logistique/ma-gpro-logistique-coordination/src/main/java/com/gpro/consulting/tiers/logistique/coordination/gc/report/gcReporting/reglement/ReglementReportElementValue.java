package com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.reglement;

import java.util.Calendar;

/**
 * 
 * @author Hajer Amri
 * @since 30/01/2017
 *
 */
public class ReglementReportElementValue {

	private String refReglement;
	private String ClientReference ;
	private String ClientAbreviation;
	private Double montant;
	private Calendar date;
	
	
	public String getRefReglement() {
		return refReglement;
	}
	public void setRefReglement(String refReglement) {
		this.refReglement = refReglement;
	}
	public String getClientReference() {
		return ClientReference;
	}
	public void setClientReference(String clientReference) {
		ClientReference = clientReference;
	}
	public String getClientAbreviation() {
		return ClientAbreviation;
	}
	public void setClientAbreviation(String clientAbreviation) {
		ClientAbreviation = clientAbreviation;
	}
	public Double getMontant() {
		return montant;
	}
	public void setMontant(Double montant) {
		this.montant = montant;
	}
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}


	
	
	
}
