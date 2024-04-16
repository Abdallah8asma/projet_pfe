package com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author Wahid Gazzah
 * @since 11/12/2015
 *
 */
public class ResultatRechecheEntrepotValue {
	
	private Long nombreResultaRechercher;
	
	private Set<EntrepotValue> list = new TreeSet<EntrepotValue>();

	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	public Set<EntrepotValue> getList() {
		return list;
	}

	public void setList(Set<EntrepotValue> list) {
		this.list = list;
	}
	
	

}
