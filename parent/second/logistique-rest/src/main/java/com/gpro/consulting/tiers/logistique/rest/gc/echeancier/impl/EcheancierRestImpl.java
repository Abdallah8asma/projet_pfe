package com.gpro.consulting.tiers.logistique.rest.gc.echeancier.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.echeancier.RechercheMulticritereDetailReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.echeancier.ResultatRechecheDetailReglementValue;
import com.gpro.consulting.tiers.logistique.service.gc.echancier.IEchancierService;

/**
 * @author Ameni Berrich
 *
 */
@RestController
@RequestMapping("/echeancierClient")
public class EcheancierRestImpl {
	
	private static final Logger logger = LoggerFactory.getLogger(EcheancierRestImpl.class);
	
	@Autowired
	private IEchancierService echeancierService;
		
	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST, produces = "application/json")
	public ResultatRechecheDetailReglementValue rechercherMultiCritere(@RequestBody RechercheMulticritereDetailReglementValue request) {
		
		//logger.info(" Delegating request to service layer.");
		
		ResultatRechecheDetailReglementValue result = echeancierService.rechercherMultiCritere(request);
    	
		return result;
	}
}
