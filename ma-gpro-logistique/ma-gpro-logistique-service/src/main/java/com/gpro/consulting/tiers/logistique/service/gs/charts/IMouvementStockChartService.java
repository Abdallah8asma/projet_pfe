package com.gpro.consulting.tiers.logistique.service.gs.charts;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gc.chart.value.ResultBestElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.chart.value.RechercheMulticritereMouvementChartValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.chart.value.ResultatRechecheMouvementChartValue;

/**
 * @author Wahid Gazzah
 * @since 14/04/2016
 */
public interface IMouvementStockChartService {

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ResultatRechecheMouvementChartValue rechercherMultiCritere(
			RechercheMulticritereMouvementChartValue request);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<ResultBestElementValue> getBySousFamille(RechercheMulticritereMouvementChartValue request);


	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<ResultBestElementValue> getByArticle(RechercheMulticritereMouvementChartValue request);


	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<ResultBestElementValue> getActuelleBySousFamille(RechercheMulticritereMouvementChartValue request);


	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<ResultBestElementValue> getActuelleByArticle(RechercheMulticritereMouvementChartValue request);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<ResultBestElementValue> getActuelleParRapportSortieAndEntreeBySousFamille(
			RechercheMulticritereMouvementChartValue request);
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<ResultBestElementValue> getActuelleParRapportSortieAndEntreeByArticle(
			RechercheMulticritereMouvementChartValue request);

}
