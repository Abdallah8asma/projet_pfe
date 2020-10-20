package com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.value;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author Wahid Gazzah
 * @since 28/03/2016
 *
 */
public class ResultatRechecheFicheSuiveuseCoutValue {
	
	private Long nombreResultaRechercher;
	
	private Set<ResultatRechecheFicheSuiveuseElemCoutValue> list = new TreeSet <ResultatRechecheFicheSuiveuseElemCoutValue>();

	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	public Set<ResultatRechecheFicheSuiveuseElemCoutValue> getList() {
		return list;
	}

	public void setList(Set<ResultatRechecheFicheSuiveuseElemCoutValue> list) {
		this.list = list;
	}
	
	
	
}
