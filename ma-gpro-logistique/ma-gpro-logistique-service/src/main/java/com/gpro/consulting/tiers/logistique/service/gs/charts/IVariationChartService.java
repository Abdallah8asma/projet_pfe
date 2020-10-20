package com.gpro.consulting.tiers.logistique.service.gs.charts;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gs.chart.variation.value.RechercheMulticritereVariationChartValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.chart.variation.value.ResultatRechecheVariationChartValue;

/**
 * @author Wahid Gazzah
 * @since 26/04/2016
 */
public interface IVariationChartService {

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ResultatRechecheVariationChartValue rechercherMultiCritere(
			RechercheMulticritereVariationChartValue request);
}
