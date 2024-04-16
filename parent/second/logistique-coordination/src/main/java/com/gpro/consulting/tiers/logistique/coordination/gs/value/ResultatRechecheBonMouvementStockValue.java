package com.gpro.consulting.tiers.logistique.coordination.gs.value;

import java.util.Set;
import java.util.TreeSet;

public class ResultatRechecheBonMouvementStockValue {

	private Long nombreResultaRechercher;

	private Set<BonMouvementStockValue> bonMouvementStock = new TreeSet<BonMouvementStockValue>();

	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	public Set<BonMouvementStockValue> getBonMouvementStock() {
		return bonMouvementStock;
	}

	public void setBonMouvementStock(Set<BonMouvementStockValue> bonMouvementStock) {
		this.bonMouvementStock = bonMouvementStock;
	}

}
