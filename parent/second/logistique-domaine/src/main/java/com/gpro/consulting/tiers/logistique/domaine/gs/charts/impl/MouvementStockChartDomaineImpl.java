package com.gpro.consulting.tiers.logistique.domaine.gs.charts.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.domaine.elementBase.IArticleDomaine;
import com.gpro.consulting.tiers.commun.domaine.elementBase.ISousFamilleArticleDomaine;
import com.gpro.consulting.tiers.logistique.coordination.gc.chart.value.ResultBestElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.chart.value.RechercheMulticritereMouvementChartValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.chart.value.ResultatRechecheMouvementChartValue;
import com.gpro.consulting.tiers.logistique.domaine.gs.charts.IMouvementStockChartDomaine;
import com.gpro.consulting.tiers.logistique.persistance.gs.charts.IMouvementStockChartPersistance;

/**
 * implementation of {@link IMouvementStockChartDomaine}
 * 
 * @author Wahid Gazzah
 * @since 14/04/2016
 *
 */

@Component
public class MouvementStockChartDomaineImpl implements IMouvementStockChartDomaine{
	
	private static final Logger logger = LoggerFactory.getLogger(MouvementStockChartDomaineImpl.class);

	@Autowired
	IMouvementStockChartPersistance mouvementStockChartPersistance;
	
	@Autowired
	IArticleDomaine articleDomaine;
	
	@Autowired
	ISousFamilleArticleDomaine sousFamilleArticleDomaine;
	
	@Override
	public ResultatRechecheMouvementChartValue rechercherMultiCritere(
			RechercheMulticritereMouvementChartValue request) {
		
		logger.info("rechercherMultiCritere: Delegating request to Persistence layer.");
		
		return mouvementStockChartPersistance.rechercherMultiCritere(request);
	}

	@Override
	public List<ResultBestElementValue> getBySousFamille(RechercheMulticritereMouvementChartValue request) {
		// TODO Auto-generated method stub
		return mouvementStockChartPersistance.getBySousFamille(request);
	}

	@Override
	public List<ResultBestElementValue> getByArticle(RechercheMulticritereMouvementChartValue request) {
		// TODO Auto-generated method stub
		return mouvementStockChartPersistance.getByArticle(request);
	}

	@Override
	public List<ResultBestElementValue> getActuelleByArticle(RechercheMulticritereMouvementChartValue request) {
		// TODO Auto-generated method stub
		return mouvementStockChartPersistance.getActuelleByArticle(request);
	}

	@Override
	public List<ResultBestElementValue> getActuelleBySousFamille(RechercheMulticritereMouvementChartValue request) {
		// TODO Auto-generated method stub
		return mouvementStockChartPersistance.getActuelleBySousFamille(request);
	}

	@Override
	public List<ResultBestElementValue> getActuelleParRapportSortieAndEntreeBySousFamille(
			RechercheMulticritereMouvementChartValue request) {
		// TODO Auto-generated method stub
		 List<ResultBestElementValue> list =  mouvementStockChartPersistance.getActuelleParRapportSortieAndEntreeBySousFamille(request);
	
		 
		 for(ResultBestElementValue element : list) {
			 
			 if(element.getId() != null)
                 element.setAbreviation(sousFamilleArticleDomaine.rechercheSousFamilleArticleById(element.getId()).getDesignation());			 
		 }
	
	
	  return list ;
	}

	@Override
	public List<ResultBestElementValue> getActuelleParRapportSortieAndEntreeByArticle(
			RechercheMulticritereMouvementChartValue request) {
		// TODO Auto-generated method stub
		 List<ResultBestElementValue> list = mouvementStockChartPersistance.getActuelleParRapportSortieAndEntreeByArticle(request);
	
		 
		 for(ResultBestElementValue element : list) {
			 
			 if(element.getId() != null)
                 element.setAbreviation(articleDomaine.getArticleParId(element.getId()).getDesignation());			 
		 }
	
		 
		 
		  return list ;
	}

	

}
