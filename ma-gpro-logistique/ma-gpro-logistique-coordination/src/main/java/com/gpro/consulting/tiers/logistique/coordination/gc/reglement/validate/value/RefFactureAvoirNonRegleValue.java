package com.gpro.consulting.tiers.logistique.coordination.gc.reglement.validate.value;

import java.util.Calendar;

public class RefFactureAvoirNonRegleValue implements Comparable<RefFactureAvoirNonRegleValue> {

	private Long id;
	private String numFactureAvoir;
	private Calendar dateAvoir;
	private Double montantFactureAvoir;


	@Override
	public int compareTo(RefFactureAvoirNonRegleValue element) {
		return (element.getId().compareTo(id));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumFactureAvoir() {
		return numFactureAvoir;
	}

	public void setNumFactureAvoir(String numFactureAvoir) {
		this.numFactureAvoir = numFactureAvoir;
	}

	public Calendar getDateAvoir() {
		return dateAvoir;
	}

	public void setDateAvoir(Calendar dateAvoir) {
		this.dateAvoir = dateAvoir;
	}

	public Double getMontantFactureAvoir() {
		return montantFactureAvoir;
	}

	public void setMontantFactureAvoir(Double montantFactureAvoir) {
		this.montantFactureAvoir = montantFactureAvoir;
	}

	

	
}
