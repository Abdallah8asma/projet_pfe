package com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author Wahid Gazzah
 * @since 17/08/2016
 */

public class ResultatRechecheReglementCompletValue {
	
	private Long nombreResultaRechercher;
	private Set<ReglementValue> list = new TreeSet <ReglementValue>();

	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	public Set<ReglementValue> getList() {
		return list;
	}

	public void setList(Set<ReglementValue> list) {
		this.list = list;
	}


}
