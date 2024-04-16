package com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author Wahid Gazzah
 * @since 17/08/2016
 */

public class ResultatRechecheReglementAchatCompletValue {
	
	private Long nombreResultaRechercher;
	private Set<ReglementAchatValue> list = new TreeSet <ReglementAchatValue>();

	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	public Set<ReglementAchatValue> getList() {
		return list;
	}

	public void setList(Set<ReglementAchatValue> list) {
		this.list = list;
	}


}
