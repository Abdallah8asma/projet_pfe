package com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value;

import java.util.Calendar;
import java.util.List;

/**
 * The Class ListTraitFaconAchatElementValue.
 *
 * @author Hamdi Etteieb
 * @since 23/09/2018
 */
public class ListTraitFaconAchatElementValue {

	/** The nombre resulta rechercher. */
	private int nombreResultaRechercher;

	/** The date livraison. */
	private Calendar dateLivraison;

	/** The partie int id. */
	private Long partieIntId;

	/** The list det facture achat. */
	private List<DetFactureAchatValue> listDetFactureAchat;

	/**
	 * Gets the nombre resulta rechercher.
	 *
	 * @return the nombre resulta rechercher
	 */
	public int getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	/**
	 * Sets the nombre resulta rechercher.
	 *
	 * @param nombreResultaRechercher
	 *            the new nombre resulta rechercher
	 */
	public void setNombreResultaRechercher(int nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	/**
	 * Gets the date livraison.
	 *
	 * @return the date livraison
	 */
	public Calendar getDateLivraison() {
		return dateLivraison;
	}

	/**
	 * Sets the date livraison.
	 *
	 * @param dateLivraison
	 *            the new date livraison
	 */
	public void setDateLivraison(Calendar dateLivraison) {
		this.dateLivraison = dateLivraison;
	}

	/**
	 * Gets the list det facture achat.
	 *
	 * @return the list det facture achat
	 */
	public List<DetFactureAchatValue> getListDetFactureAchat() {
		return listDetFactureAchat;
	}

	/**
	 * Sets the list det facture achat.
	 *
	 * @param listDetFactureAchat
	 *            the new list det facture achat
	 */
	public void setListDetFactureAchat(List<DetFactureAchatValue> listDetFactureAchat) {
		this.listDetFactureAchat = listDetFactureAchat;
	}

	/**
	 * Gets the partie int id.
	 *
	 * @return the partie int id
	 */
	public Long getPartieIntId() {
		return partieIntId;
	}

	/**
	 * Sets the partie int id.
	 *
	 * @param partieIntId
	 *            the new partie int id
	 */
	public void setPartieIntId(Long partieIntId) {
		this.partieIntId = partieIntId;
	}

}
