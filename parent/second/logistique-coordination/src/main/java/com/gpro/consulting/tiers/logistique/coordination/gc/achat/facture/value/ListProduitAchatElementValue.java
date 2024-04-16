package com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value;

import java.util.Calendar;
import java.util.List;

/**
 * The Class ListProduitAchatElementValue.
 *
 * @author Hamdi Etteieb
 * @since 23/09/2018
 */
public class ListProduitAchatElementValue {

	/** The nombre resulta rechercher. */
	private int nombreResultaRechercher;

	/** The date livrison. */
	private Calendar dateLivrison;

	/** The partie int id. */
	private Long partieIntId;

	/** The id marche. */
	private Long idMarche;

	/** The id marche. */
	private Long idDepot;

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
	 * @param nombreResultaRechercher the new nombre resulta rechercher
	 */
	public void setNombreResultaRechercher(int nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
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
	 * @param listDetFactureAchat the new list det facture achat
	 */
	public void setListDetFactureAchat(List<DetFactureAchatValue> listDetFactureAchat) {
		this.listDetFactureAchat = listDetFactureAchat;
	}

	/**
	 * Gets the date livrison.
	 *
	 * @return the date livrison
	 */
	public Calendar getDateLivrison() {
		return dateLivrison;
	}

	/**
	 * Sets the date livrison.
	 *
	 * @param dateLivrison the new date livrison
	 */
	public void setDateLivrison(Calendar dateLivrison) {
		this.dateLivrison = dateLivrison;
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
	 * @param partieIntId the new partie int id
	 */
	public void setPartieIntId(Long partieIntId) {
		this.partieIntId = partieIntId;
	}

	/**
	 * Gets the id marche.
	 *
	 * @return the id marche
	 */
	public Long getIdMarche() {
		return idMarche;
	}

	/**
	 * Sets the id marche.
	 *
	 * @param idMarche the new id marche
	 */
	public void setIdMarche(Long idMarche) {
		this.idMarche = idMarche;
	}

	public Long getIdDepot() {
		return idDepot;
	}

	public void setIdDepot(Long idDepot) {
		this.idDepot = idDepot;
	}

}
