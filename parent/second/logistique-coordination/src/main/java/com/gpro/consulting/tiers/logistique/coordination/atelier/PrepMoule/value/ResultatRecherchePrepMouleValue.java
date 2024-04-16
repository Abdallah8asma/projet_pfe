package com.gpro.consulting.tiers.logistique.coordination.atelier.PrepMoule.value;

import java.util.HashSet;
import java.util.Set;

public class ResultatRecherchePrepMouleValue {
	 /** Liste des objets retournées */

	  Set < ElementRechechePrepMouleValue > listeElementRechechePrepMouleValeur = new HashSet < ElementRechechePrepMouleValue >();

	public Set<ElementRechechePrepMouleValue> getListeElementRechechePrepMouleValeur() {
		return listeElementRechechePrepMouleValeur;
	}

	public void setListeElementRechechePrepMouleValeur(
			Set<ElementRechechePrepMouleValue> listeElementRechechePrepMouleValeur) {
		this.listeElementRechechePrepMouleValeur = listeElementRechechePrepMouleValeur;
	}


}
