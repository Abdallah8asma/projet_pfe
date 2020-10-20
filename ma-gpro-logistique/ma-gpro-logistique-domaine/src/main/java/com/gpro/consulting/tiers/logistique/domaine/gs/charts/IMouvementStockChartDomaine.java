package com.gpro.consulting.tiers.logistique.domaine.gs.charts;

import com.gpro.consulting.tiers.logistique.coordination.gs.chart.value.RechercheMulticritereMouvementChartValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.chart.value.ResultatRechecheMouvementChartValue;

/**
 * MouvementStockChart Domaine interface
 * 
 * @author Wahid Gazzah
 * @since 14/04/2016
 *
 */
public interface IMouvementStockChartDomaine {

	public ResultatRechecheMouvementChartValue rechercherMultiCritere(
			RechercheMulticritereMouvementChartValue request);

}
