package com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author Wahid Gazzah
 * @since 12/01/2016
 *
 */
public class ResultatRechecheBonSortieFiniValue {
	
	private Long nombreResultaRechercher;
	
	private Set<BonSortieFiniValue> list = new TreeSet <BonSortieFiniValue>();

	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	public Set<BonSortieFiniValue> getList() {
		return list;
	}

	public void setList(Set<BonSortieFiniValue> list) {
		this.list = list;
	}

}
