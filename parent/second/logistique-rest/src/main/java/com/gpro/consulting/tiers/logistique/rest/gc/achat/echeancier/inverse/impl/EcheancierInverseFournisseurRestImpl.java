package com.gpro.consulting.tiers.logistique.rest.gc.achat.echeancier.inverse.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.echeancier.RechercheMulticritereDetailReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.echeancier.ResultatRechecheDetailReglementAchatValue;
import com.gpro.consulting.tiers.logistique.service.gc.achat.echancier.inverse.IEcheancierInverseFournisseurService;

/**
 * @author Samer Hassen
 *
 */
@RestController
@RequestMapping("/echeancierInverseFournisseur")
public class EcheancierInverseFournisseurRestImpl {
	
	private static final Logger logger = LoggerFactory.getLogger(EcheancierInverseFournisseurRestImpl.class);
	
	@Autowired
	private IEcheancierInverseFournisseurService echeancierService;
		
	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST, produces = "application/json")
	public ResultatRechecheDetailReglementAchatValue rechercherMultiCritere(@RequestBody RechercheMulticritereDetailReglementAchatValue request) {
		
		//logger.info(" Delegating request to service layer.");
		
		ResultatRechecheDetailReglementAchatValue result = echeancierService.rechercherMultiCritere(request);
    	
		return result;
	}
}
