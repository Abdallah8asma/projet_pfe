package com.gpro.consulting.logistique.coordination.gc.boncomptoir.value;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
public class ResultatRechecheBonComptoirValue {
	
	private Long nombreResultaRechercher;
	
	private Set<ComptoirVenteValue> list = new TreeSet <ComptoirVenteValue>();

	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	public Set<ComptoirVenteValue> getList() {
		return list;
	}

	public void setList(Set<ComptoirVenteValue> list) {
		this.list = list;
	}

}
