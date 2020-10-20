package com.gpro.consulting.tiers.logistique.coordination.gs.value;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ameni Berrich
 *
 */
public class ResultatRechecheDetailBonStockValue {

	private Long nombreResultaRechercher;

	private List<DetBonStockValue> listDetailBonStock = new ArrayList<DetBonStockValue>();

	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	public List<DetBonStockValue> getListDetailBonStock() {
		return listDetailBonStock;
	}

	public void setListDetailBonStock(List<DetBonStockValue> listDetailBonStock) {
		this.listDetailBonStock = listDetailBonStock;
	}

}
