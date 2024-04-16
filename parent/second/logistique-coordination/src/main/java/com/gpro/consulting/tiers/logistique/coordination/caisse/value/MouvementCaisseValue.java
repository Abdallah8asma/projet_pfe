/**
 * 
 */
package com.gpro.consulting.tiers.logistique.coordination.caisse.value;

import java.util.Calendar;

// TODO: Auto-generated Javadoc
/**
 * The Class MouvementCaisseValue.
 *
 * @author Hamdi Etteieb
 */
public class MouvementCaisseValue {

	/** the technical id. */
	private Long id;

	/** the date mouvenet. */
	private Calendar date;

	/** the destinataire. */
	private String destinataire;

	/** the caisse id. */
	private Long caisseId;

	/** The caisse reference. */
	private String caisseReference;

	/** The type mouvement. */
	private String typeMouvement;

	/** The montant mouvement. */
	private Double montantMouvement;

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
	 * Gets the caisse id.
	 *
	 * @return the CaisseId
	 */
	public Long getCaisseId() {
		return caisseId;
	}

	/**
	 * Sets the caisse id.
	 *
	 * @param CaisseId
	 *            the CaisseId to set
	 */
	public void setCaisseId(Long CaisseId) {
		this.caisseId = CaisseId;
	}

	/**
	 * Gets the caisse reference.
	 *
	 * @return the caisse reference
	 */
	public String getCaisseReference() {
		return caisseReference;
	}

	/**
	 * Sets the caisse reference.
	 *
	 * @param caisseReference
	 *            the new caisse reference
	 */
	public void setCaisseReference(String caisseReference) {
		this.caisseReference = caisseReference;
	}

	/**
	 * Gets the type mouvement.
	 *
	 * @return the type mouvement
	 */
	public String getTypeMouvement() {
		return typeMouvement;
	}

	/**
	 * Sets the type mouvement.
	 *
	 * @param typeMouvement
	 *            the new type mouvement
	 */
	public void setTypeMouvement(String typeMouvement) {
		this.typeMouvement = typeMouvement;
	}

	/**
	 * Gets the montant mouvement.
	 *
	 * @return the montant mouvement
	 */
	public Double getMontantMouvement() {
		return montantMouvement;
	}

	/**
	 * Sets the montant mouvement.
	 *
	 * @param montantMouvement
	 *            the new montant mouvement
	 */
	public void setMontantMouvement(Double montantMouvement) {
		this.montantMouvement = montantMouvement;
	}

}
