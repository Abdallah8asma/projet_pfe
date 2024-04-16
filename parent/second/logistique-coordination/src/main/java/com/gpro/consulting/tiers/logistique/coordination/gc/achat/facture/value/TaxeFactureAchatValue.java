package com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value;

import java.util.Calendar;

/**
 * The Class TaxeFactureAchatValue.
 *
 * @author Hamdi Etteieb
 * @since 23/09/2018
 */
public class TaxeFactureAchatValue {

	/** The id. */
	private Long id;

	/** The pourcentage. */
	private Double pourcentage;

	/** The montant. */
	private Double montant;

	/** The facture achat id. */
	private Long factureAchatId;

	/** The taxe id. */
	private Long taxeId;

	/** The bl suppression. */
	private Boolean blSuppression;

	/** The date suppression. */
	private Calendar dateSuppression;

	/** The date creation. */
	private Calendar dateCreation;

	/** The date modification. */
	private Calendar dateModification;

	/** The version. */
	private String version;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the pourcentage.
	 *
	 * @return the pourcentage
	 */
	public Double getPourcentage() {
		return pourcentage;
	}

	/**
	 * Sets the pourcentage.
	 *
	 * @param pourcentage
	 *            the new pourcentage
	 */
	public void setPourcentage(Double pourcentage) {
		this.pourcentage = pourcentage;
	}

	/**
	 * Gets the montant.
	 *
	 * @return the montant
	 */
	public Double getMontant() {
		return montant;
	}

	/**
	 * Sets the montant.
	 *
	 * @param montant
	 *            the new montant
	 */
	public void setMontant(Double montant) {
		this.montant = montant;
	}

	/**
	 * Checks if is bl suppression.
	 *
	 * @return the boolean
	 */
	public Boolean isBlSuppression() {
		return blSuppression;
	}

	/**
	 * Sets the bl suppression.
	 *
	 * @param blSuppression
	 *            the new bl suppression
	 */
	public void setBlSuppression(Boolean blSuppression) {
		this.blSuppression = blSuppression;
	}

	/**
	 * Gets the date suppression.
	 *
	 * @return the date suppression
	 */
	public Calendar getDateSuppression() {
		return dateSuppression;
	}

	/**
	 * Sets the date suppression.
	 *
	 * @param dateSuppression
	 *            the new date suppression
	 */
	public void setDateSuppression(Calendar dateSuppression) {
		this.dateSuppression = dateSuppression;
	}

	/**
	 * Gets the date creation.
	 *
	 * @return the date creation
	 */
	public Calendar getDateCreation() {
		return dateCreation;
	}

	/**
	 * Sets the date creation.
	 *
	 * @param dateCreation
	 *            the new date creation
	 */
	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * Gets the date modification.
	 *
	 * @return the date modification
	 */
	public Calendar getDateModification() {
		return dateModification;
	}

	/**
	 * Sets the date modification.
	 *
	 * @param dateModification
	 *            the new date modification
	 */
	public void setDateModification(Calendar dateModification) {
		this.dateModification = dateModification;
	}

	/**
	 * Gets the version.
	 *
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * Sets the version.
	 *
	 * @param version
	 *            the new version
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * Gets the facture achat id.
	 *
	 * @return the facture achat id
	 */
	public Long getFactureAchatId() {
		return factureAchatId;
	}

	/**
	 * Sets the facture achat id.
	 *
	 * @param factureAchatId
	 *            the new facture achat id
	 */
	public void setFactureAchatId(Long factureAchatId) {
		this.factureAchatId = factureAchatId;
	}

	/**
	 * Gets the taxe id.
	 *
	 * @return the taxe id
	 */
	public Long getTaxeId() {
		return taxeId;
	}

	/**
	 * Sets the taxe id.
	 *
	 * @param taxeId
	 *            the new taxe id
	 */
	public void setTaxeId(Long taxeId) {
		this.taxeId = taxeId;
	}

}
