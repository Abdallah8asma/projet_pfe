package com.gpro.consulting.tiers.logistique.domaine.gs.charts;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gc.chart.value.ResultBestElementValue;
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

	public List<ResultBestElementValue> getBySousFamille(RechercheMulticritereMouvementChartValue request);


	public List<ResultBestElementValue> getActuelleByArticle(RechercheMulticritereMouvementChartValue request);

	public List<ResultBestElementValue> getActuelleBySousFamille(RechercheMulticritereMouvementChartValue request);

	public List<ResultBestElementValue> getByArticle(RechercheMulticritereMouvementChartValue request);

	public List<ResultBestElementValue> getActuelleParRapportSortieAndEntreeBySousFamille(
			RechercheMulticritereMouvementChartValue request);

	public List<ResultBestElementValue> getActuelleParRapportSortieAndEntreeByArticle(
			RechercheMulticritereMouvementChartValue request);

}
