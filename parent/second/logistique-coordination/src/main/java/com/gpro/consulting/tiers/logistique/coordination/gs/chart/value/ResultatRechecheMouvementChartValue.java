package com.gpro.consulting.tiers.logistique.coordination.gs.chart.value;

import java.util.List;

/**
 * @author Wahid Gazzah
 * @since 13/04/2016
 */

public class ResultatRechecheMouvementChartValue {
	
	private Long nombreResultaRechercher;
	private List<MouvementStockChartValue> listMouvementStock;

	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	public List<MouvementStockChartValue> getListMouvementStock() {
		return listMouvementStock;
	}

	public void setListMouvementStock(
			List<MouvementStockChartValue> listMouvementStock) {
		this.listMouvementStock = listMouvementStock;
	}

}
