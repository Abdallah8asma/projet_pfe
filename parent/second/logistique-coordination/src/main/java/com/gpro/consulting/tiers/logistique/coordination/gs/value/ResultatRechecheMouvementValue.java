package com.gpro.consulting.tiers.logistique.coordination.gs.value;

import java.util.Set;
import java.util.TreeSet;

public class ResultatRechecheMouvementValue {

	private Long nombreResultaRechercher;

	private Set<MouvementStockValue> mouvementStock = new TreeSet<MouvementStockValue>();

	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	public Set<MouvementStockValue> getMouvementStock() {
		return mouvementStock;
	}

	public void setMouvementStock(Set<MouvementStockValue> mouvementStock) {
		this.mouvementStock = mouvementStock;
	}

}
