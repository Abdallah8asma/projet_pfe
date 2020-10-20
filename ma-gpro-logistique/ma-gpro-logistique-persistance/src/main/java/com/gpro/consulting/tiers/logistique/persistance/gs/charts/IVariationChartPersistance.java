package com.gpro.consulting.tiers.logistique.persistance.gs.charts;

import com.gpro.consulting.tiers.logistique.coordination.gs.chart.variation.value.RechercheMulticritereVariationChartValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.chart.variation.value.ResultatRechecheVariationChartValue;

public interface IVariationChartPersistance {
	
	public ResultatRechecheVariationChartValue rechercherMultiCritere(
			RechercheMulticritereVariationChartValue request);
}
