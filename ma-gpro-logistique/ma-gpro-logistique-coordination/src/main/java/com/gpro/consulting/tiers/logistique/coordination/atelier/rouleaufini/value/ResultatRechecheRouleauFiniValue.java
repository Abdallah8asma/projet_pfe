package com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author Wahid Gazzah
 * @since 11/12/2015
 *
 */
public class ResultatRechecheRouleauFiniValue {
	
	private Long nombreResultaRechercher;
	
	Set < RouleauFiniValue > list = new TreeSet < RouleauFiniValue >();
	  

	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	/** Accesseur en lecture de l'attribut list.
	 * @return the list
	 */
	public Set<RouleauFiniValue> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(Set<RouleauFiniValue> list) {
		this.list = list;
	}

	
	
	

}
