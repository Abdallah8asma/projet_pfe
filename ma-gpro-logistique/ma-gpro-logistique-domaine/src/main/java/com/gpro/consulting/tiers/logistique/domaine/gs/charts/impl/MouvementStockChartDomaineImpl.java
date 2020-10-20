package com.gpro.consulting.tiers.logistique.domaine.gs.charts.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
	
	@Override
	public ResultatRechecheMouvementChartValue rechercherMultiCritere(
			RechercheMulticritereMouvementChartValue request) {
		
		logger.info("rechercherMultiCritere: Delegating request to Persistence layer.");
		
		return mouvementStockChartPersistance.rechercherMultiCritere(request);
	}

}
