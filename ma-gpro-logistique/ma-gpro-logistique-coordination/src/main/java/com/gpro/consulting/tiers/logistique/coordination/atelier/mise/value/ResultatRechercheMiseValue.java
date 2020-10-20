package com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value;

import java.util.HashSet;
import java.util.Set;

public class ResultatRechercheMiseValue {
	 /** Liste des objets retournées */

	  Set < ElementRechecheMiseValue > listeElementRechecheMiseValeur = new HashSet < ElementRechecheMiseValue >();

	/**
	 * @return the listeElementRechecheMiseValeur
	 */
	public Set<ElementRechecheMiseValue> getListeElementRechecheMiseValeur() {
		return listeElementRechecheMiseValeur;
	}

	/**
	 * @param listeElementRechecheMiseValeur the listeElementRechecheMiseValeur to set
	 */
	public void setListeElementRechecheMiseValeur(
			Set<ElementRechecheMiseValue> listeElementRechecheMiseValeur) {
		this.listeElementRechecheMiseValeur = listeElementRechecheMiseValeur;
	}

}
