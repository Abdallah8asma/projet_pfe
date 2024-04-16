package com.gpro.consulting.tiers.logistique.domaine.gs.charts;

import com.gpro.consulting.tiers.logistique.coordination.gs.chart.variation.value.RechercheMulticritereVariationChartValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.chart.variation.value.ResultatRechecheVariationChartValue;

/**
 * Variation Chart Domaine interface
 * 
 * @author Wahid Gazzah
 * @since 26/04/2016
 *
 */
public interface IVariationChartDomaine {
	
	public ResultatRechecheVariationChartValue rechercherMultiCritere(
			RechercheMulticritereVariationChartValue request);

}
