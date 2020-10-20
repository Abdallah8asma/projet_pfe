package com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class ResultatRechecheBonReceptionAchatValue.
 *
 * @author Hamdi Etteieb
 * @since 23/09/2018
 */
public class ResultatRechecheBonReceptionAchatValue {

	/** The nombre resulta rechercher. */
	private Long nombreResultaRechercher;

	/** The list reception achat. */
	private List<ReceptionAchatValue> listReceptionAchat = new ArrayList<ReceptionAchatValue>();

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
	 * Gets the list reception achat.
	 *
	 * @return the list reception achat
	 */
	public List<ReceptionAchatValue> getListReceptionAchat() {
		return listReceptionAchat;
	}

	/**
	 * Sets the list reception achat.
	 *
	 * @param listReceptionAchat
	 *            the new list reception achat
	 */
	public void setListReceptionAchat(List<ReceptionAchatValue> listReceptionAchat) {
		this.listReceptionAchat = listReceptionAchat;
	}

}
