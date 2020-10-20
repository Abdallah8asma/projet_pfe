package com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class ResultatRechecheBonReceptionAchatValue.
 *
 * @author Hamdi Etteieb
 * @since 23/09/2018
 */
public class ResultatRechecheProduitReceptionAchatValue {

	/** The nombre resulta rechercher. */
	private Long nombreResultaRechercher;

	/** The list ProduitReceptionAchatValue reception achat. */
	private List<ProduitReceptionAchatValue> listProduitReceptionAchat = new ArrayList<ProduitReceptionAchatValue>();

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
	 * @param nombreResultaRechercher the new nombre resulta rechercher
	 */
	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	public List<ProduitReceptionAchatValue> getListProduitReceptionAchat() {
		return listProduitReceptionAchat;
	}

	public void setListProduitReceptionAchat(List<ProduitReceptionAchatValue> listProduitReceptionAchat) {
		this.listProduitReceptionAchat = listProduitReceptionAchat;
	}

}
