package com.gpro.consulting.tiers.logistique.coordination.gc.reglement.validate.value;

import java.util.Calendar;

public class RefLivraisonNonRegleValue implements Comparable<RefLivraisonNonRegleValue> {

	private Long id;
	private String numBL;

	private Calendar date;
	private Double montantBL;

	@Override
	public int compareTo(RefLivraisonNonRegleValue element) {
		return (element.getId().compareTo(id));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumBL() {
		return numBL;
	}

	public void setNumBL(String numBL) {
		this.numBL = numBL;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public Double getMontantBL() {
		return montantBL;
	}

	public void setMontantBL(Double montantBL) {
		this.montantBL = montantBL;
	}

}
