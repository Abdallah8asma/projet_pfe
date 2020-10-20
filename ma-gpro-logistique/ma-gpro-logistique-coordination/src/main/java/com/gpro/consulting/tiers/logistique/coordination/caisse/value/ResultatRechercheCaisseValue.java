package com.gpro.consulting.tiers.logistique.coordination.caisse.value;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author Hamdi Etteieb
 *
 */
public class ResultatRechercheCaisseValue {
	/** Liste des objets retournées */

	Set<CaisseValue> liste = new HashSet<CaisseValue>();

	/**
	 * @return the liste
	 */
	public Set<CaisseValue> getListe() {
		return liste;
	}

	/**
	 * @param liste
	 *            the liste to set
	 */
	public void setListe(Set<CaisseValue> liste) {
		this.liste = liste;
	}

}
