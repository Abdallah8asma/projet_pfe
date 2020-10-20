package com.gpro.consulting.tiers.logistique.persistance.gs.charts;

import com.gpro.consulting.tiers.logistique.coordination.gs.chart.value.RechercheMulticritereMouvementChartValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.chart.value.ResultatRechecheMouvementChartValue;

/**
 * MouvementStock Chart Persistance interface
 * 
 * @author Wahid Gazzah
 * @since 14/04/2016
 *
 */
public interface IMouvementStockChartPersistance {

	ResultatRechecheMouvementChartValue rechercherMultiCritere(
			RechercheMulticritereMouvementChartValue request);

}
