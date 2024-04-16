package com.gpro.consulting.tiers.logistique.coordination.gc.achat.boncommande.value;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class ResultatRechecheBonCommandeAchatValue.
 * 
 * @author Hamdi Etteieb
 * @since 16/09/2018
 */
public class ResultatRechecheBonCommandeAchatValue {

	/** The nombre resulta rechercher. */
	private Long nombreResultaRechercher;

	/** The list commande Achat. */
	private List<CommandeAchatValue> listCommandeAchat = new ArrayList<CommandeAchatValue>();

	/**
	 * Gets the nombre resulta rechercher.
	 *
	 * @return the nombre resulta rechercher
	 */
	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	/**
	 * Sets the nombre resulta rechercher.
	 *
	 * @param nombreResultaRechercher
	 *            the new nombre resulta rechercher
	 */
	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	/**
	 * Gets the list commande Achat.
	 *
	 * @return the list commande Achat
	 */
	public List<CommandeAchatValue> getListCommandeAchat() {
		return listCommandeAchat;
	}

	/**
	 * Sets the list commande Achat.
	 *
	 * @param listCommandeAchat
	 *            the new list commande Achat
	 */
	public void setListCommandeAchat(List<CommandeAchatValue> listCommandeAchat) {
		this.listCommandeAchat = listCommandeAchat;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ResultatRechecheBonCommandeValue [nombreResultaRechercher=" + nombreResultaRechercher
				+ ", listCommandeAchat=" + listCommandeAchat + "]";
	}

}
