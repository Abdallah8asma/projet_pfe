package com.gpro.consulting.tiers.logistique.persistance.gs.charts;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gc.chart.value.ResultBestElementValue;
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

	List<ResultBestElementValue> getBySousFamille(RechercheMulticritereMouvementChartValue request);

	List<ResultBestElementValue> getByArticle(RechercheMulticritereMouvementChartValue request);

	List<ResultBestElementValue> getActuelleByArticle(RechercheMulticritereMouvementChartValue request);

	List<ResultBestElementValue> getActuelleBySousFamille(RechercheMulticritereMouvementChartValue request);

	List<ResultBestElementValue> getActuelleParRapportSortieAndEntreeBySousFamille(
			RechercheMulticritereMouvementChartValue request);

	List<ResultBestElementValue> getActuelleParRapportSortieAndEntreeByArticle(RechercheMulticritereMouvementChartValue request);

}
