package com.gpro.consulting.tiers.logistique.rest.gs.charts.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.RechercheMulticritereDetLivraisonValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.chart.value.ResultBestElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.chart.value.RechercheMulticritereMouvementChartValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.chart.value.ResultatRechecheMouvementChartValue;
import com.gpro.consulting.tiers.logistique.service.gs.charts.IMouvementStockChartService;

/**
 * MouvementStock Chart Controller
 * 
 * @author Wahid Gazzah
 * @since 13/04/2016
 *
 */

@RestController
@RequestMapping("/mouvementStockChart")
public class MouvementStockChartController {
	
	private static final Logger logger = LoggerFactory.getLogger(MouvementStockChartController.class);
	
	@Autowired
	IMouvementStockChartService mouvementStockChartService;
	
	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST, produces = "application/json")
	public ResultatRechecheMouvementChartValue rechercheMulticritere( @RequestBody RechercheMulticritereMouvementChartValue request) {
		  
		logger.info("rechercherMultiCritere: Delegating request to Service layer.");
		  
		return mouvementStockChartService.rechercherMultiCritere(request);
	}
	
	
	@RequestMapping(value = "/getBySousFamille", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody List<ResultBestElementValue> getBySousFamille(
			@RequestBody RechercheMulticritereMouvementChartValue request) {
		
		
		return mouvementStockChartService.getBySousFamille(request);
	}
	
	@RequestMapping(value = "/getByArticle", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody List<ResultBestElementValue> getByArticle(
			@RequestBody RechercheMulticritereMouvementChartValue request) {
		
		
		return mouvementStockChartService.getByArticle(request);
	}
	
	
	@RequestMapping(value = "/getActuelleBySousFamille", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody List<ResultBestElementValue> getActuelleBySousFamille(
			@RequestBody RechercheMulticritereMouvementChartValue request) {
		
		
		return mouvementStockChartService.getActuelleBySousFamille(request);
	}
	
	@RequestMapping(value = "/getActuelleByArticle", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody List<ResultBestElementValue> getActuelleByArticle(
			@RequestBody RechercheMulticritereMouvementChartValue request) {
		
		
		return mouvementStockChartService.getActuelleByArticle(request);
	}
	
	
	
	
	@RequestMapping(value = "/getActuelleParRapportSortieAndEntreeBySousFamille", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody List<ResultBestElementValue> getActuelleParRapportSortieAndEntreeBySousFamille(
			@RequestBody RechercheMulticritereMouvementChartValue request) {
		
		
		return mouvementStockChartService.getActuelleParRapportSortieAndEntreeBySousFamille(request);
	}
	
	@RequestMapping(value = "/getActuelleParRapportSortieAndEntreeByArticle", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody List<ResultBestElementValue> getActuelleParRapportSortieAndEntreeByArticle(
			@RequestBody RechercheMulticritereMouvementChartValue request) {
		
		
		return mouvementStockChartService.getActuelleParRapportSortieAndEntreeByArticle(request);
	}

}
