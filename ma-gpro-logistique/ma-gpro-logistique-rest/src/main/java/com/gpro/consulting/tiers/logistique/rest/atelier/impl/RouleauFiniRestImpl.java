package com.gpro.consulting.tiers.logistique.rest.atelier.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.RechercheMulticritereRouleauFiniStandardValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.RechercheMulticritereRouleauFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.ResultatRechecheRouleauFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.RouleauFiniValue;
import com.gpro.consulting.tiers.logistique.persistance.atelier.rouleaufini.utilities.RouleauFiniPersistanceUtilities;
import com.gpro.consulting.tiers.logistique.rest.utilities.ApiResponse;
import com.gpro.consulting.tiers.logistique.service.atelier.cache.IGestionnaireLogistiqueCacheService;
import com.gpro.consulting.tiers.logistique.service.atelier.rouleaufini.IRouleauFiniService;

/**
 * RouleauFini Controller
 * 
 * @author Wahid Gazzah
 * @since 11/12/2015
 *
 */
@Controller
@RequestMapping("/rouleaufini")
public class RouleauFiniRestImpl{
	
	private static final Logger logger = LoggerFactory.getLogger(RouleauFiniRestImpl.class);
	
	@Autowired
	IRouleauFiniService rouleauFiniService;
	@Autowired
	private IGestionnaireLogistiqueCacheService gestionnaireLogistiqueCacheService;
	
	@Autowired
	private RouleauFiniPersistanceUtilities persistanceUtilities;
	
	
	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResultatRechecheRouleauFiniValue rechercherMultiCritere(@RequestBody RechercheMulticritereRouleauFiniValue request) {
		 
		//logger.info("RechercheMulticritere: Delegating request {} to service layer.", request);
		
		request.setOptimized(persistanceUtilities.checkForOptimization(request));
		 
		ResultatRechecheRouleauFiniValue result = rouleauFiniService.rechercherMultiCritere(request);
		// Traitement : transformation de l'Id a sa propre Designation
	    
		for (RouleauFiniValue elementRouleauFini : result.getList()) {

			// Client, Produit
			Map<String, String> mapA = gestionnaireLogistiqueCacheService
					.rechercherDesignationParId(elementRouleauFini.getPartieInteresseeId(), elementRouleauFini.getProduitId(), elementRouleauFini.getEntrepot(), elementRouleauFini.getChoix());

			// Client
			elementRouleauFini.setPartieInteresseeIdDesignation(mapA.get("client"));
			// Produit (Tissu): designation, reference
			elementRouleauFini.setProduitIdDesignation(mapA.get("produit"));
			elementRouleauFini.setProduitReference(mapA.get("produitRef"));
			// Entrepot
			elementRouleauFini.setEntrepotDesignation(mapA.get("entrepot"));
			// Choix
			elementRouleauFini.setChoixDesignation(mapA.get("choix"));

		}
		
		return result;
	 }
	
	@RequestMapping(value = "/rechercheMulticritereStandard", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResultatRechecheRouleauFiniValue rechercherMultiCritereStandard(@RequestBody RechercheMulticritereRouleauFiniStandardValue request) {
		 
		//logger.info("RechercheMulticritereStandard: Delegating request {} to service layer.", request);
		
		request.setOptimized(persistanceUtilities.checkForOptimization(request));
		 
		ResultatRechecheRouleauFiniValue result = rouleauFiniService.rechercherMultiCritereStandard(request);
		// Traitement : transformation de l'Id a sa propre Designation
	    
		for (RouleauFiniValue elementRouleauFini : result.getList()) {

			// Client, Produit
			Map<String, String> map = gestionnaireLogistiqueCacheService
					.rechercherDesignationParId(elementRouleauFini.getPartieInteresseeId(), elementRouleauFini.getProduitId(), elementRouleauFini.getEntrepot(), elementRouleauFini.getChoix());

			// Client
			elementRouleauFini.setPartieInteresseeIdDesignation(map.get("client"));
			// Produit (Tissu)
			elementRouleauFini.setProduitReference(map.get("produitRef"));
			// Produit (Type)
			elementRouleauFini.setProduitIdSousFamille(map.get("produitType"));
			// Produit (Composition)
			elementRouleauFini.setProduitIdComposition(map.get("produitComposition"));
			// Entrepot
			elementRouleauFini.setEntrepotDesignation(map.get("entrepot"));
			// Choix
			elementRouleauFini.setChoixDesignation(map.get("choix"));

		}
		return result;
	 }
	 
	@RequestMapping(value = "/getRouleauFiniById:{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody RouleauFiniValue getRouleauFiniById(@PathVariable Long id) {
		  
		//logger.info("GetRouleauFiniById: Delegating request id {} to service layer.", id);
		  
		return rouleauFiniService.getRouleauFiniById(id);
	}
		  
	@RequestMapping(value = "/updateRouleauFini", method = RequestMethod.POST)
	public @ResponseBody String updateRouleauFini(@RequestBody RouleauFiniValue request) {
	    
		//logger.info("UpdateRouleauFini: Delegating request {} to service layer.", request);
		
		return this.rouleauFiniService.updateRouleauFini(request);
	}
	  
	@RequestMapping(value = "/deleteRouleauFini:{id}", method = RequestMethod.DELETE)
	public void deleteRouleauFini(@PathVariable("id") String id) {
		  
		//logger.info("deleteRouleauFini: Delegating request id {} to service layer.", id);
		  
		rouleauFiniService.deleteRouleauFini(Long.valueOf(id));
	}
	  
	@RequestMapping(value = "/createRouleauFini", method = RequestMethod.POST)
	public @ResponseBody String createRouleauFini(@RequestBody RouleauFiniValue request) {
		  
		//logger.info("createRouleauFini: Delegating request {} to service layer.", request.getEntrepot());
	
	    return this.rouleauFiniService.createRouleauFini(request);
	}
	
	@RequestMapping(value = "/getRouleauFiniByInfoModif:{infoModif}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ResultatRechecheRouleauFiniValue getRouleauFiniById(@PathVariable("infoModif") String infoModif) {
		  
		//logger.info("GetRouleauFiniById: Delegating request infoModif {} to service layer.", infoModif);
		  
		return rouleauFiniService.getRouleauFiniByInfoModif(infoModif);
	}

	@RequestMapping(value = "/getListRefMiseRouleauNonSortie", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<String> getListRefMiseRouleauNonSortie() {
		 
		//logger.info("getListRefMiseRouleauNonSortie: Delegating request {} to service layer.");
		 
		List<String> result = rouleauFiniService.getListRefMiseRouleauNonSortie();
		
		return result;
	 }
	
	@RequestMapping(value = "/getListCodeBarreByRefMiseAndIdBSisNull:{refMise}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<String> getListCodeBarreByRefMiseAndIdBSisNull(@PathVariable("refMise") String refMise) {
		 
		//logger.info("getListCodeBarreByRefMiseAndIdBSisNull: Delegating request {} to service layer.");
		 
		List<String> result = rouleauFiniService.getListCodeBarreByRefMiseAndIdBSisNull(refMise);
		
		return  result;
	 }
	
	
	@RequestMapping(value = "/getListCodeBarreByRefMiseAndIdBSisNull2", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<String> getListCodeBarreByRefMiseAndIdBSisNull2(
			@RequestParam("refMise") String refMise) {
		 
		//logger.info("getListCodeBarreByRefMiseAndIdBSisNull: Delegating request {} to service layer.");
		 
		List<String> result = rouleauFiniService.getListCodeBarreByRefMiseAndIdBSisNull(refMise);
		
		return  result;
	 }
	
	
	@RequestMapping(value = "/deleteRouleauFiniMultiple", method = RequestMethod.POST)
	public @ResponseBody ApiResponse deleteRouleauFiniMultiple(@RequestBody List<Long> listIds) {
		  
		//logger.info("deleteRouleauFini: Delegating request id {} to service layer.", id);
		  for(Long id : listIds) {
			  
			  rouleauFiniService.deleteRouleauFini(id);
		  }
		  
		  return new ApiResponse("OK");
		
	}
}
