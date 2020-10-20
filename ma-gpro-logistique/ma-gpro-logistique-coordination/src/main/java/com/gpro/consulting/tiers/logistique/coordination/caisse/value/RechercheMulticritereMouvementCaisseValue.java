package com.gpro.consulting.tiers.logistique.coordination.caisse.value;

import java.util.Calendar;

/**
 * The Class RechercheMulticritereMouvementCaisseValue.
 *
 * @author Hamdi Etteieb
 */
public class RechercheMulticritereMouvementCaisseValue {

	/** Date Cloture de. */
	private Calendar dateDe;

	/** Date Cloture au. */
	private Calendar dateAu;

	/** Reference Magasin. */
	private String reference;

	/** The destinataire. */
	private String destinataire;

	/** The type mouvement. */
	private String typeMouvement;

	/** The caisse id. */
	private Long caisseId;

	/**
	 * Gets the date de.
	 *
	 * @return the dateDe
	 */
	public Calendar getDateDe() {
		return dateDe;
	}

	/**
	 * Sets the date de.
	 *
	 * @param dateDe
	 *            the dateDe to set
	 */
	public void setDateDe(Calendar dateDe) {
		this.dateDe = dateDe;
	}

	/**
	 * Gets the date au.
	 *
	 * @return the dateAu
	 */
	public Calendar getDateAu() {
		return dateAu;
	}

	/**
	 * Sets the date au.
	 *
	 * @param dateAu
	 *            the dateAu to set
	 */
	public void setDateAu(Calendar dateAu) {
		this.dateAu = dateAu;
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
	 * Gets the destinataire.
	 *
	 * @return the destinataire
	 */
	public String getDestinataire() {
		return destinataire;
	}

	/**
	 * Sets the destinataire.
	 *
	 * @param destinataire
	 *            the destinataire to set
	 */
	public void setDestinataire(String destinataire) {
		this.destinataire = destinataire;
	}

	/**
	 * Gets the type mouvement.
	 *
	 * @return the typeMouvement
	 */
	public String getTypeMouvement() {
		return typeMouvement;
	}

	/**
	 * Sets the type mouvement.
	 *
	 * @param typeMouvement
	 *            the typeMouvement to set
	 */
	public void setTypeMouvement(String typeMouvement) {
		this.typeMouvement = typeMouvement;
	}

	public Long getCaisseId() {
		return caisseId;
	}

	public void setCaisseId(Long caisseId) {
		this.caisseId = caisseId;
	}

}