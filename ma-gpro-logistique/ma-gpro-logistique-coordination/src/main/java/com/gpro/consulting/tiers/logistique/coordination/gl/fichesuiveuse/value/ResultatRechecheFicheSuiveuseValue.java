package com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.value;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author Wahid Gazzah
 * @since 28/03/2016
 *
 */
public class ResultatRechecheFicheSuiveuseValue {
	
	private Long nombreResultaRechercher;
	
	private Set<ResultatRechecheFicheSuiveuseElementValue> list = new TreeSet <ResultatRechecheFicheSuiveuseElementValue>();

	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	public Set<ResultatRechecheFicheSuiveuseElementValue> getList() {
		return list;
	}

	public void setList(Set<ResultatRechecheFicheSuiveuseElementValue> list) {
		this.list = list;
	}
	
}
