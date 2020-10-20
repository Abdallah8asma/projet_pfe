package com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author Wahid Gazzah
 * @since 01/07/2016
 */
public class ResultatRechecheReglementValue {
	
	private Long nombreResultaRechercher;
	private Set<ResultatRechecheReglementElementValue> list = new TreeSet <ResultatRechecheReglementElementValue>();

	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	public Set<ResultatRechecheReglementElementValue> getList() {
		return list;
	}

	public void setList(Set<ResultatRechecheReglementElementValue> list) {
		this.list = list;
	}
	
	
}
