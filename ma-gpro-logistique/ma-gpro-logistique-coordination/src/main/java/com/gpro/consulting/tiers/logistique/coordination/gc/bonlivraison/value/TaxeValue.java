package com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value;

import java.util.Calendar;

/**
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
public class TaxeValue {

	private Long id;
	private String designation;
	private Boolean blSuppression;
	private Calendar dateSuppression;
	private Calendar dateCreation;
	private Calendar dateModification;
	private String version;
	private Boolean tva;
	private Double valeur ;
	public Boolean getTva() {
		return tva;
	}
	public void setTva(Boolean tva) {
		this.tva = tva;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Boolean isBlSuppression() {
		return blSuppression;
	}
	public void setBlSuppression(Boolean blSuppression) {
		this.blSuppression = blSuppression;
	}
	public Calendar getDateSuppression() {
		return dateSuppression;
	}
	public void setDateSuppression(Calendar dateSuppression) {
		this.dateSuppression = dateSuppression;
	}
	public Calendar getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
	}
	public Calendar getDateModification() {
		return dateModification;
	}
	public void setDateModification(Calendar dateModification) {
		this.dateModification = dateModification;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public Double getValeur() {
		return valeur;
	}
	public void setValeur(Double valeur) {
		this.valeur = valeur;
	}
	
	
}
