package com.gpro.consulting.tiers.logistique.rest.atelier.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.EntrepotValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.RechercheMulticritereEntrepotValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.ResultatRechecheEntrepotValue;
import com.gpro.consulting.tiers.logistique.service.atelier.rouleaufini.IEntrepotService;

/**
 * Entrepot Controller
 * 
 * @author Wahid Gazzah
 * @since 11/12/2015
 *
 */
@Controller
@RequestMapping("/entrepot")
public class EntrepotRestImpl {
	
	private static final Logger logger = LoggerFactory.getLogger(EntrepotRestImpl.class);
	
	@Autowired
	IEntrepotService entrepotService;

	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResultatRechecheEntrepotValue rechercherMultiCritere(@RequestBody RechercheMulticritereEntrepotValue request) {
		 
		//logger.info("RechercheMulticritere: Delegating request {} to service layer.", request);
		 
		ResultatRechecheEntrepotValue result = entrepotService.rechercherMultiCritere(request);
		return result;
	 }
	 
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<EntrepotValue> viewAllEntrepot() {
		//logger.info("ListeEntrepot: Delegating request {} to service layer.");
		
		return entrepotService.listeEntrepot();
	}
	
	@RequestMapping(value = "/getEntrepotById:{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody EntrepotValue getEntrepotById(@PathVariable Long id) {
		  
		//logger.info("GetEntrepotById: Delegating request id {} to service layer.", id);
		  
		return entrepotService.getEntrepotById(id);
	}
	  
	@RequestMapping(value = "/updateEntrepot", method = RequestMethod.POST)
	public @ResponseBody String updateEntrepot(@RequestBody EntrepotValue request) {
	    
		//logger.info("UpdateEntrepot: Delegating request {} to service layer.", request);
		
		return this.entrepotService.updateEntrepot(request);
	}
	  
	@RequestMapping(value = "/deleteEntrepot:{id}", method = RequestMethod.DELETE)
	public void deleteEntrepot(@PathVariable("id") String id) {
		  
		//logger.info("deleteEntrepot: Delegating request id {} to service layer.", id);
		  
		entrepotService.deleteEntrepot(Long.valueOf(id));
	}
	  
	@RequestMapping(value = "/createEntrepot", method = RequestMethod.POST)
	public @ResponseBody String createEntrepot(@RequestBody EntrepotValue request) {
		  
		//logger.info("createEntrepot: Delegating request {} to service layer.", request);

	    return this.entrepotService.createEntrepot(request);
	}
	
}
