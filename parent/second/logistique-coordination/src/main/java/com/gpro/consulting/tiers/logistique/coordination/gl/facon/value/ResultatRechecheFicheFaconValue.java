package com.gpro.consulting.tiers.logistique.coordination.gl.facon.value;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author Zeineb Medimagh
 * @since 28/09/2016
 *
 */
public class ResultatRechecheFicheFaconValue {
	
	private Long nombreResultaRechercher;
	
	private Set<ResultatRechecheFicheFaconElementValue> list = new TreeSet <ResultatRechecheFicheFaconElementValue>();

	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	public Set<ResultatRechecheFicheFaconElementValue> getList() {
		return list;
	}

	public void setList(Set<ResultatRechecheFicheFaconElementValue> list) {
		this.list = list;
	}

}
