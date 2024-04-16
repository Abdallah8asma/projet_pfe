package com.gpro.consulting.tiers.logistique.service.gs.charts.impl;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.logistique.coordination.gs.chart.variation.value.RechercheMulticritereVariationChartValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.chart.variation.value.ResultatRechecheVariationChartValue;
import com.gpro.consulting.tiers.logistique.domaine.gs.charts.IVariationChartDomaine;
import com.gpro.consulting.tiers.logistique.service.gs.charts.IVariationChartService;

/**
 * implementation of {@link IVariationChartService}
 *  
 * @author Wahid Gazzah
 * @since 26/04/2016
 */

@Service
@Transactional
public class VariationChartServiceImpl implements IVariationChartService{
	
	private static final Logger logger = LoggerFactory.getLogger(MouvementStockChartServiceImpl.class);
	
    @Autowired
    IVariationChartDomaine variationChartDomaine;

	@Override
	public ResultatRechecheVariationChartValue rechercherMultiCritere(
			RechercheMulticritereVariationChartValue request) {
		
		//logger.info("rechercherMultiCritere: Delegating request to Domaine layer.");
		
		return variationChartDomaine.rechercherMultiCritere(request);
	}


}
