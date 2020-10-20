package com.gpro.consulting.tiers.logistique.domaine.gs.charts.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.gs.chart.variation.value.RechercheMulticritereVariationChartValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.chart.variation.value.ResultatRechecheVariationChartValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.chart.variation.value.VariationChartValue;
import com.gpro.consulting.tiers.logistique.domaine.gs.charts.IVariationChartDomaine;
import com.gpro.consulting.tiers.logistique.persistance.gs.charts.IVariationChartPersistance;

/**
 * implementation of {@link IVariationChartDomaine}
 * 
 * @author Wahid Gazzah
 * @since 26/04/2016
 *
 */

@Component
public class VariationChartDomaineImpl implements IVariationChartDomaine{
	
	private static final Logger logger = LoggerFactory.getLogger(MouvementStockChartDomaineImpl.class);
	
	private static final String IDTYPE_FOURNITURE = "1";
	private static final String IDTYPE_TISSU = "2";
	private static final String IDTYPE_FILACOUDRE = "3";
	
	private static final String ARTICLETYPE_FOURNITURE = "FOURNITURE";
	private static final String ARTICLETYPE_TISSU = "TISSU";
	private static final String ARTICLETYPE_FILACOUDRE = "FILACOUDRE";

	@Autowired
	IVariationChartPersistance variationChartPersistance;
	
	@Override
	public ResultatRechecheVariationChartValue rechercherMultiCritere(
			RechercheMulticritereVariationChartValue request) {
		
		logger.info("rechercherMultiCritere: Delegating request to Persistence layer.");
		
		ResultatRechecheVariationChartValue result = variationChartPersistance.rechercherMultiCritere(request);
		
		if(result != null){
			
			if(request.getTypeArticle() != null){
				
				for(VariationChartValue variationChart : result.getListVariation()){
					
				    switch(request.getTypeArticle()) {
				        case IDTYPE_FOURNITURE:
				        	variationChart.setTypeArticle(ARTICLETYPE_FOURNITURE);
				        	break;
				        	
						case IDTYPE_TISSU:
							variationChart.setTypeArticle(ARTICLETYPE_TISSU);
							break;
							
						case IDTYPE_FILACOUDRE:
							variationChart.setTypeArticle(ARTICLETYPE_FILACOUDRE);
							break;
						
						default:
							break;
				    }
					
				}
			}

		}
		
		return result;
	}
}
