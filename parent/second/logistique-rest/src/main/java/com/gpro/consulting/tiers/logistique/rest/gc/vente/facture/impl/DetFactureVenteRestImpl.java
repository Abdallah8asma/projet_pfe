package com.gpro.consulting.tiers.logistique.rest.gc.vente.facture.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.ResultatRechecheDetBonLivraisonValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.DetFactureVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.RechercheMulticritereDetFactureVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ResultatRechecheDetFactureVenteValue;
import com.gpro.consulting.tiers.logistique.service.gc.vente.facture.IDetFactureVenteService;

/**
 * DetFacture Vente Controller
 * 
 * @author Wahid Gazzah
 * @since 29/02/2016
 *
 */

@Controller
@RequestMapping("/detfacturevente")
public class DetFactureVenteRestImpl {
	
	private static final Logger logger = LoggerFactory.getLogger(DetFactureVenteRestImpl.class);
	
	@Autowired
	private IDetFactureVenteService detFactureVenteService;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody String create(@RequestBody DetFactureVenteValue detFactureVenteValue) {
		
		//logger.info("create: Delegating request to Service layer.");
		
		return this.detFactureVenteService.create(detFactureVenteValue);
	}
	
	@RequestMapping(value = "/getById:{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody DetFactureVenteValue getById(@PathVariable Long id) {
		  
		//logger.info("getById: Delegating request id {} to service layer.", id);
		  
		return detFactureVenteService.getById(id);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody String update(@RequestBody DetFactureVenteValue detFactureVenteValue) {
	    
		//logger.info("Update: Delegating request to service layer.");
		
		return this.detFactureVenteService.update(detFactureVenteValue);
	}
	  
	@RequestMapping(value = "/delete:{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		  
		//logger.info("delete: Delegating request id {} to service layer.", id);
		  
		detFactureVenteService.delete(id);
	}
	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResultatRechecheDetFactureVenteValue rechercherMultiCritere(
			@RequestBody RechercheMulticritereDetFactureVenteValue request) {
		request.setOptimized(RechercheMulticritereDetFactureVenteValue.checkForOptimization(request));
		ResultatRechecheDetFactureVenteValue result = detFactureVenteService
				.rechercherMultiCritere(request);

		
		return  result;
	}

}
