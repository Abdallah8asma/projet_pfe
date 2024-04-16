package com.gpro.consulting.tiers.logistique.coordination.caisse.value;

import java.util.Calendar;

/**
 * The Class RechercheMulticritereCaisseValue.
 *
 * @author Hamdi Etteieb
 */
public class RechercheMulticritereCaisseValue {

	/** Date Cloture de. */
	private Calendar dateClotureDe;

	/** Date Cloture au. */
	private Calendar dateClotureAu;

	/** Reference Magasin. */
	private String reference;

	/** montant Especes De. */
	private Double montantEspecesDe;

	/** montant Especes au. */
	private Double montantEspecesAu;

	/** Montant Cheque De. */
	private Double MontantChequeDe;

	/** Montant Cheque au. */
	private Double MontantChequeAu;

	/** The magasin id. */
	private Long magasinId;

	/**
	 * Gets the date cloture de.
	 *
	 * @return the dateClotureDe
	 */
	public Calendar getDateClotureDe() {
		return dateClotureDe;
	}

	/**
	 * Sets the date cloture de.
	 *
	 * @param dateClotureDe
	 *            the dateClotureDe to set
	 */
	public void setDateClotureDe(Calendar dateClotureDe) {
		this.dateClotureDe = dateClotureDe;
	}

	/**
	 * Gets the date cloture au.
	 *
	 * @return the dateClotureAu
	 */
	public Calendar getDateClotureAu() {
		return dateClotureAu;
	}

	/**
	 * Sets the date cloture au.
	 *
	 * @param dateClotureAu
	 *            the dateClotureAu to set
	 */
	public void setDateClotureAu(Calendar dateClotureAu) {
		this.dateClotureAu = dateClotureAu;
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
	 * Gets the montant especes de.
	 *
	 * @return the montantEspecesDe
	 */
	public Double getMontantEspecesDe() {
		return montantEspecesDe;
	}

	/**
	 * Sets the montant especes de.
	 *
	 * @param montantEspecesDe
	 *            the montantEspecesDe to set
	 */
	public void setMontantEspecesDe(Double montantEspecesDe) {
		this.montantEspecesDe = montantEspecesDe;
	}

	/**
	 * Gets the montant especes au.
	 *
	 * @return the montantEspecesAu
	 */
	public Double getMontantEspecesAu() {
		return montantEspecesAu;
	}

	/**
	 * Sets the montant especes au.
	 *
	 * @param montantEspecesAu
	 *            the montantEspecesAu to set
	 */
	public void setMontantEspecesAu(Double montantEspecesAu) {
		this.montantEspecesAu = montantEspecesAu;
	}

	/**
	 * Gets the montant cheque de.
	 *
	 * @return the montantChequeDe
	 */
	public Double getMontantChequeDe() {
		return MontantChequeDe;
	}

	/**
	 * Sets the montant cheque de.
	 *
	 * @param montantChequeDe
	 *            the montantChequeDe to set
	 */
	public void setMontantChequeDe(Double montantChequeDe) {
		MontantChequeDe = montantChequeDe;
	}

	/**
	 * Gets the montant cheque au.
	 *
	 * @return the montantChequeAu
	 */
	public Double getMontantChequeAu() {
		return MontantChequeAu;
	}

	/**
	 * Sets the montant cheque au.
	 *
	 * @param montantChequeAu
	 *            the montantChequeAu to set
	 */
	public void setMontantChequeAu(Double montantChequeAu) {
		MontantChequeAu = montantChequeAu;
	}

	public Long getMagasinId() {
		return magasinId;
	}

	public void setMagasinId(Long magasinId) {
		this.magasinId = magasinId;
	}

}