package com.gpro.consulting.tiers.logistique.coordination.gc.reglement.validate.value;

import java.util.Calendar;

/**
 * @author Wahid Gazzah
 * @since 12/07/2016
 *
 */
public class FactureNonRegleValue implements Comparable<FactureNonRegleValue> {

	private Long id;
	private String numFacture;
	private Calendar date;
	private Double montantFacture;
	private Double montantRegle;
	
	public int compareTo(FactureNonRegleValue element) {
		return (element.getId().compareTo(id));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumFacture() {
		return numFacture;
	}

	public void setNumFacture(String numFacture) {
		this.numFacture = numFacture;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public Double getMontantFacture() {
		return montantFacture;
	}

	public void setMontantFacture(Double montantFacture) {
		this.montantFacture = montantFacture;
	}

	public Double getMontantRegle() {
		return montantRegle;
	}

	public void setMontantRegle(Double montantRegle) {
		this.montantRegle = montantRegle;
	}


}
