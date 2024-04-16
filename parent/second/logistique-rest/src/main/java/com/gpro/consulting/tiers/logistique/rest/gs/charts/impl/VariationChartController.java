package com.gpro.consulting.tiers.logistique.rest.gs.charts.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gpro.consulting.tiers.logistique.coordination.gs.chart.variation.value.RechercheMulticritereVariationChartValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.chart.variation.value.ResultatRechecheVariationChartValue;
import com.gpro.consulting.tiers.logistique.service.gs.charts.IVariationChartService;

/**
 * Variation Chart Controller
 * 
 * @author Wahid Gazzah
 * @since 26/04/2016
 *
 */

@RestController
@RequestMapping("/variationChart")
public class VariationChartController {
	
	private static final Logger logger = LoggerFactory.getLogger(VariationChartController.class);
	
	private static final String IDTYPE_FOURNITURE = "1";
	private static final String IDTYPE_TISSU = "2";
	private static final String IDTYPE_FILACOUDRE = "3";
	
	private static final String ARTICLETYPE_FOURNITURE = "FOURNITURE";
	private static final String ARTICLETYPE_TISSU = "TISSU";
	private static final String ARTICLETYPE_FILACOUDRE = "FILACOUDRE";
	
	@Autowired
	IVariationChartService variationChartService;
	
	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST, produces = "application/json")
	public ResultatRechecheVariationChartValue rechercheMulticritere( @RequestBody RechercheMulticritereVariationChartValue request) {
		  
		logger.info("rechercherMultiCritere: Delegating request to Service layer.");
		
		if(request != null){
			
			if(request.getTypeArticle() != null){
				
			    switch(request.getTypeArticle()) {
			        case ARTICLETYPE_FOURNITURE:
			        	request.setTypeArticle(IDTYPE_FOURNITURE);
			        	break;
			        	
					case ARTICLETYPE_TISSU:
						request.setTypeArticle(IDTYPE_TISSU);
						break;
						
					case ARTICLETYPE_FILACOUDRE:
						request.setTypeArticle(IDTYPE_FILACOUDRE);
						break;
					
					default:
						break;
			    }

			}
		}
		  
		return variationChartService.rechercherMultiCritere(request);
	}

}
