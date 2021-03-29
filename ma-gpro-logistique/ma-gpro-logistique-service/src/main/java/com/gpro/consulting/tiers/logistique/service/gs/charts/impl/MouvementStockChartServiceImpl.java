package com.gpro.consulting.tiers.logistique.service.gs.charts.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.logistique.coordination.gc.chart.value.ResultBestElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.chart.value.RechercheMulticritereMouvementChartValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.chart.value.ResultatRechecheMouvementChartValue;
import com.gpro.consulting.tiers.logistique.domaine.gs.charts.IMouvementStockChartDomaine;
import com.gpro.consulting.tiers.logistique.service.gs.charts.IMouvementStockChartService;

/**
 * implementation of {@link IMouvementStockChartService}
 *  
 * @author Wahid Gazzah
 * @since 14/04/2016
 */

@Service
@Transactional
public class MouvementStockChartServiceImpl implements IMouvementStockChartService{
	
	private static final Logger logger = LoggerFactory.getLogger(MouvementStockChartServiceImpl.class);
	
    @Autowired
    IMouvementStockChartDomaine mouvementStockChartDomaine;

	@Override
	public ResultatRechecheMouvementChartValue rechercherMultiCritere(
			RechercheMulticritereMouvementChartValue request) {
		
		//logger.info("rechercherMultiCritere: Delegating request to Domaine layer.");
		
		return mouvementStockChartDomaine.rechercherMultiCritere(request);
	}

	@Override
	public List<ResultBestElementValue> getBySousFamille(RechercheMulticritereMouvementChartValue request) {
		// TODO Auto-generated method stub
		return mouvementStockChartDomaine.getBySousFamille(request);
	}

	@Override
	public List<ResultBestElementValue> getByArticle(RechercheMulticritereMouvementChartValue request) {
		// TODO Auto-generated method stub
		return mouvementStockChartDomaine.getByArticle(request);
	}



	@Override
	public List<ResultBestElementValue> getActuelleBySousFamille(RechercheMulticritereMouvementChartValue request) {
		// TODO Auto-generated method stub
		return mouvementStockChartDomaine.getActuelleBySousFamille(request);
	}

	@Override
	public List<ResultBestElementValue> getActuelleByArticle(RechercheMulticritereMouvementChartValue request) {
		// TODO Auto-generated method stub
		return mouvementStockChartDomaine.getActuelleByArticle(request);
	}

	@Override
	public List<ResultBestElementValue> getActuelleParRapportSortieAndEntreeBySousFamille(
			RechercheMulticritereMouvementChartValue request) {
		// TODO Auto-generated method stub
		return mouvementStockChartDomaine.getActuelleParRapportSortieAndEntreeBySousFamille(request) ;
	}

	@Override
	public List<ResultBestElementValue> getActuelleParRapportSortieAndEntreeByArticle(
			RechercheMulticritereMouvementChartValue request) {
		// TODO Auto-generated method stub
		return mouvementStockChartDomaine.getActuelleParRapportSortieAndEntreeByArticle(request) ;
	}

}
