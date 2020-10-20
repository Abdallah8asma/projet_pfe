package com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author Wahid Gazzah
 * @since 01/07/2016
 */
public class ResultatRechecheElementReglementValue {

	private Long nombreResultaRechercher;
	private Set<ResultatRechecheElementReglementElementValue> list = new TreeSet<ResultatRechecheElementReglementElementValue>();

	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	public Set<ResultatRechecheElementReglementElementValue> getList() {
		return list;
	}

	public void setList(Set<ResultatRechecheElementReglementElementValue> list) {
		this.list = list;
	}

}
