package com.gpro.consulting.tiers.logistique.coordination.gs.transfert.value;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author Samer
 * @since 04/09/2020
 *
 */
public class ResultatRechecheBonTransfertValue {

	private Long nombreResultaRechercher;

	private Set<BonTransfertValue> list = new TreeSet<BonTransfertValue>();

	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	public Set<BonTransfertValue> getList() {
		return list;
	}

	public void setList(Set<BonTransfertValue> list) {
		this.list = list;
	}

}
