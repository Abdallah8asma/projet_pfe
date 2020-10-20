package com.gpro.consulting.tiers.logistique.coordination.caisse.value;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author Hamdi Etteieb
 *
 */
public class ResultatRechercheMouvementCaisseValue {
	/** Liste des objets retournées */

	Set<MouvementCaisseValue> liste = new HashSet<MouvementCaisseValue>();

	/**
	 * @return the liste
	 */
	public Set<MouvementCaisseValue> getListe() {
		return liste;
	}

	/**
	 * @param liste
	 *            the liste to set
	 */
	public void setListe(Set<MouvementCaisseValue> liste) {
		this.liste = liste;
	}

}
