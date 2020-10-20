package com.gpro.consulting.tiers.logistique.coordination.caisse.value;

import java.util.Calendar;

// TODO: Auto-generated Javadoc
/**
 * The Class CaisseValue.
 *
 * @author Hamdi Etteieb
 */
public class CaisseValue {

	/** The id. */
	private Long id;

	/** The date. */
	private Calendar date;

	/** The reference. */
	private String reference;

	/** The date cloture. */
	private Calendar dateCloture;

	/** The user id. */
	private Long userId;

	/** The magasin id. */
	private Long magasinId;

	/** The montant especes. */
	private Double montantEspeces;

	/** The Montant cheque. */
	private Double MontantCheque;

	/** The magasin reference. */
	private String magasinReference;

	/** The cloture. */
	private boolean cloture;

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
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public Calendar getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date
	 *            the date to set
	 */
	public void setDate(Calendar date) {
		this.date = date;
	}

	/**
	 * Gets the reference.
	 *
	 * @return the reference
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * Sets the reference.
	 *
	 * @param reference
	 *            the reference to set
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/**
	 * Gets the date cloture.
	 *
	 * @return the dateCloture
	 */
	public Calendar getDateCloture() {
		return dateCloture;
	}

	/**
	 * Sets the date cloture.
	 *
	 * @param dateCloture
	 *            the dateCloture to set
	 */
	public void setDateCloture(Calendar dateCloture) {
		this.dateCloture = dateCloture;
	}

	/**
	 * Gets the user id.
	 *
	 * @return the user_id
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId
	 *            the new user id
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * Gets the magasin id.
	 *
	 * @return the magasin_id
	 */
	public Long getMagasinId() {
		return magasinId;
	}

	/**
	 * Sets the magasin id.
	 *
	 * @param magasinId
	 *            the new magasin id
	 */
	public void setMagasinId(Long magasinId) {
		this.magasinId = magasinId;
	}

	/**
	 * Gets the montant especes.
	 *
	 * @return the montantEspeces
	 */
	public Double getMontantEspeces() {
		return montantEspeces;
	}

	/**
	 * Sets the montant especes.
	 *
	 * @param montantEspeces
	 *            the montantEspeces to set
	 */
	public void setMontantEspeces(Double montantEspeces) {
		this.montantEspeces = montantEspeces;
	}

	/**
	 * Gets the montant cheque.
	 *
	 * @return the montantCheque
	 */
	public Double getMontantCheque() {
		return MontantCheque;
	}

	/**
	 * Sets the montant cheque.
	 *
	 * @param montantCheque
	 *            the montantCheque to set
	 */
	public void setMontantCheque(Double montantCheque) {
		MontantCheque = montantCheque;
	}

	/**
	 * Gets the magasin reference.
	 *
	 * @return the magasin reference
	 */
	public String getMagasinReference() {
		return magasinReference;
	}

	/**
	 * Sets the magasin reference.
	 *
	 * @param magasinReference
	 *            the new magasin reference
	 */
	public void setMagasinReference(String magasinReference) {
		this.magasinReference = magasinReference;
	}

	/**
	 * Checks if is cloture.
	 *
	 * @return true, if is cloture
	 */
	public boolean isCloture() {
		return cloture;
	}

	/**
	 * Sets the cloture.
	 *
	 * @param cloture
	 *            the new cloture
	 */
	public void setCloture(boolean cloture) {
		this.cloture = cloture;
	}

}
