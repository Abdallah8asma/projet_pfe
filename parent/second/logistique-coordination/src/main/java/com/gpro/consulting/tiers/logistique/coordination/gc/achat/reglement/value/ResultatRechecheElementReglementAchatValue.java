package com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author Wahid Gazzah
 * @since 01/07/2016
 */
public class ResultatRechecheElementReglementAchatValue {

	private Long nombreResultaRechercher;
	private Set<ResultatRechecheElementReglementAchatElementValue> list = new TreeSet<ResultatRechecheElementReglementAchatElementValue>();

	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	public Set<ResultatRechecheElementReglementAchatElementValue> getList() {
		return list;
	}

	public void setList(Set<ResultatRechecheElementReglementAchatElementValue> list) {
		this.list = list;
	}

}
