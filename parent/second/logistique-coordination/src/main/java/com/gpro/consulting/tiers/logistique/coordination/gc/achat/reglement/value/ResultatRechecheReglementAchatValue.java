package com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author Wahid Gazzah
 * @since 01/07/2016
 */
public class ResultatRechecheReglementAchatValue {
	
	private Long nombreResultaRechercher;
	private Set<ResultatRechecheReglementAchatElementValue> list = new TreeSet <ResultatRechecheReglementAchatElementValue>();

	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	public Set<ResultatRechecheReglementAchatElementValue> getList() {
		return list;
	}

	public void setList(Set<ResultatRechecheReglementAchatElementValue> list) {
		this.list = list;
	}
	
	
}
