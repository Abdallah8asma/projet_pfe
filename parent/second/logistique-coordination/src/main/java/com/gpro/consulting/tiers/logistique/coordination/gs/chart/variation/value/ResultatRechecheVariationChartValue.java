package com.gpro.consulting.tiers.logistique.coordination.gs.chart.variation.value;

import java.util.List;

/**
 * @author Wahid Gazzah
 * @since 26/04/2016
 */
public class ResultatRechecheVariationChartValue {

	private Long nombreResultaRechercher;
	private List<VariationChartValue> listVariation;
	
	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}
	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}
	public List<VariationChartValue> getListVariation() {
		return listVariation;
	}
	public void setListVariation(List<VariationChartValue> listVariation) {
		this.listVariation = listVariation;
	}

	
}
