package com.gpro.consulting.tiers.logistique.coordination.gs.value;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
public class ResultatRechecheBonStockValue {

	private Long nombreResultaRechercher;

	private Set<BonStockValue> list = new TreeSet<BonStockValue>();

	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	public Set<BonStockValue> getList() {
		return list;
	}

	public void setList(Set<BonStockValue> list) {
		this.list = list;
	}

}
