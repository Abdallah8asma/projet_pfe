package com.gpro.consulting.tiers.logistique.rest.atelier.impl;

import java.util.ArrayList;
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

import com.gpro.consulting.tiers.commun.service.partieInteressee.IPartieInteresseeService;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.BonSortieFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.RechercheMulticritereBonSortieFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.ResultatRechecheBonSortieFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.RouleauFiniValue;
import com.gpro.consulting.tiers.logistique.persistance.atelier.bonsortiefini.utilities.BonSortieFiniPersistanceUtilities;
import com.gpro.consulting.tiers.logistique.service.atelier.bonsortiefini.IBonSortieFiniService;
import com.gpro.consulting.tiers.logistique.service.atelier.cache.IGestionnaireLogistiqueCacheService;

/**
 * BonSortieFini Controller
 * 
 * @author Wahid Gazzah
 * @since 08/01/2015
 *
 */
@Controller
@RequestMapping("/bonsortiefini")
public class BonSortieFiniRestImpl {
	
	private static final Logger logger = LoggerFactory.getLogger(BonSortieFiniRestImpl.class);
	
	@Autowired
	IBonSortieFiniService bonSortieFiniService;
	
	@Autowired
	IPartieInteresseeService partieInteresseeService;
	
	@Autowired
	private IGestionnaireLogistiqueCacheService gestionnaireLogistiqueCacheService;
	
	@Autowired
	private BonSortieFiniPersistanceUtilities bonSortieFiniPersistanceUtilities;
	
	@RequestMapping(value = "/getAvailableListBonSortieFiniRef", method = RequestMethod.GET , produces = "application/json")
	public @ResponseBody List<String> getAvailableBonSortieFiniRef() {
		
		//logger.info("getListBonSortieFiniRef");
		
		return bonSortieFiniService.getListBonSortieFiniRef();
	}
	
	@RequestMapping(value = "/getAvailableListBonSortieFiniRefByClientId:{clientId}", method = RequestMethod.GET , produces = "application/json")
	public @ResponseBody List<String> getAvailableBonSortieFiniRefByClientId(@PathVariable Long clientId) {
		
		if(clientId == null) {
			return new ArrayList<String>() ;
		}
		//logger.info("getListBonSortieFiniRef");
		
		return bonSortieFiniService.getListBonSortieFiniRefByClientId(clientId);
	}
	
	// Prepare the list of RouleauFiniValue 
	@RequestMapping(value = "/validateBonSortieFini", method = RequestMethod.POST)
	public @ResponseBody List<RouleauFiniValue> validateBonSortieFini(@RequestBody List<String> barreCodeList,
			@RequestParam(value = "id", required = false) Long id) {
		
		//logger.info("validateBonSortieFini");
		
		List<RouleauFiniValue> listRouleauFini=new ArrayList<RouleauFiniValue>();
		if(barreCodeList!=null && barreCodeList.size()>0){
			listRouleauFini = bonSortieFiniService.validateBonSortieFini(barreCodeList, id);
		}
		
		return listRouleauFini;
	}
	
	@RequestMapping(value = "/createBonSortieFini", method = RequestMethod.POST)
	public @ResponseBody String createBonSortieFini(@RequestBody BonSortieFiniValue bonSortieFiniValue) {
		
		//logger.info("createBonSortieFini: Delegating request to Service layer.");
		
		String id = this.bonSortieFiniService.createBonSortieFini(bonSortieFiniValue);
		
	    return id;
	}
	
	
	@RequestMapping(value = "/getBonSortieFiniById:{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody BonSortieFiniValue getBonSortieFiniById(@PathVariable Long id) {
		  
		//logger.info("getBonSortieFiniById: Delegating request id {} to service layer.", id);
		  
		return bonSortieFiniService.getBonSortieFiniById(id);
	}
	
	
	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResultatRechecheBonSortieFiniValue rechercherMultiCritereBonSortieFini(@RequestBody RechercheMulticritereBonSortieFiniValue request) {
		 
		//logger.info("rechercheMulticritere: Delegating request {} to service layer.", request);
		
		request.setOptimized(bonSortieFiniPersistanceUtilities.checkForOptimization(request));
		 
		ResultatRechecheBonSortieFiniValue vResultatRecherche = bonSortieFiniService.rechercherMultiCritere(request);
		
		// Traitement : transformation de l'Id a sa propre Designation
		for (BonSortieFiniValue elementRechercheBonSortie : vResultatRecherche.getList()) {

			// Client, TypeBS
		/*	Map<String, String> mapA = gestionnaireLogistiqueCacheService
					.rechercherDesignationParId(elementRechercheBonSortie.getPartieInt(),null, null, null);*/
			
			
			if(elementRechercheBonSortie.getPartieInt() != null) {
				
				elementRechercheBonSortie.setPartieIntDesignation(partieInteresseeService.getById(elementRechercheBonSortie.getPartieInt()).getAbreviation());
				
			}
			
			// Client
		// TypeBS

		}
		
		return vResultatRecherche;
		
		
	 }
	
	@RequestMapping(value = "/updateBonSortieFini", method = RequestMethod.POST)
	public @ResponseBody String updateBonSortieFini(@RequestBody BonSortieFiniValue bonSortieFiniValue) {
	    
		//logger.info("UpdateRouleauFini: Delegating request {} to service layer.", bonSortieFiniValue.getId());
		
		return this.bonSortieFiniService.updateBonSortieFini(bonSortieFiniValue);
	}
	  
	@RequestMapping(value = "/deleteBonSortieFini:{id}", method = RequestMethod.DELETE)
	public void deleteBonSortieFini(@PathVariable("id") String id) {
		  
		//logger.info("deleteBonSortieFini: Delegating request id {} to service layer.", id);
		  
		bonSortieFiniService.deleteBonSortieFini(Long.valueOf(id));
	}

}
