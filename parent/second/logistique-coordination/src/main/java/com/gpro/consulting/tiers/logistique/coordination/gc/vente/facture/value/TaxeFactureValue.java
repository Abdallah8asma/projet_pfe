package com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value;

import java.util.Calendar;

/**
 * @author Wahid Gazzah
 * @since 29/02/2016
 *
 */
public class TaxeFactureValue {
	
	private Long id;
	private Double pourcentage;
	private Double montant;
	private Long factureVenteId;
	private Long taxeId;
	private Boolean blSuppression;
	private Calendar dateSuppression;
	private Calendar dateCreation;
	private Calendar dateModification;
	private String version;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getPourcentage() {
		return pourcentage;
	}
	public void setPourcentage(Double pourcentage) {
		this.pourcentage = pourcentage;
	}
	public Double getMontant() {
		return montant;
	}
	public void setMontant(Double montant) {
		this.montant = montant;
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
	public Long getFactureVenteId() {
		return factureVenteId;
	}
	public void setFactureVenteId(Long factureVenteId) {
		this.factureVenteId = factureVenteId;
	}
	public Long getTaxeId() {
		return taxeId;
	}
	public void setTaxeId(Long taxeId) {
		this.taxeId = taxeId;
	}
	
}
