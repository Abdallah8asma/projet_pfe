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


import com.gpro.consulting.tiers.logistique.coordination.atelier.PrepMoule.value.PrepMouleValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.PrepMoule.value.RechercheMulticriterePrepMouleValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.PrepMoule.value.ResultatRecherchePrepMouleValue;

import com.gpro.consulting.tiers.logistique.service.atelier.cache.IGestionnaireLogistiqueCacheService;
import com.gpro.consulting.tiers.logistique.service.atelier.prepMoule.IPrepMouleService;

@Controller
@RequestMapping("/prep-moule")
public class PrepMouleRestImpl {

	@Autowired
	IPrepMouleService vPrepMouleService;

	@Autowired
	private IGestionnaireLogistiqueCacheService gestionnaireLogistiqueCacheService;

	private static final Logger LOGGER = LoggerFactory.getLogger(PrepMouleRestImpl.class);

	public PrepMouleRestImpl() {
		// Constructeur vide
	}

	/*******************************
	 * recherche multicritere PrepMoule
	 *********************************/

	@RequestMapping(value = "/recherchePrepMouleMulticritere", method = RequestMethod.POST)
	public @ResponseBody ResultatRecherchePrepMouleValue rechercherPrepMouleMultiCritere(
			@RequestBody final RechercheMulticriterePrepMouleValue pRechercheMulticriterePrepMoule) {


		ResultatRecherchePrepMouleValue vResultatRecherche = vPrepMouleService
				.rechercherPrepMouleMultiCritere(pRechercheMulticriterePrepMoule);

	
		return vResultatRecherche;
	}

	/************* get PhaseOf PrepMoule *************/
	/**
	 * @param id
	 * @return
	 */

	@RequestMapping(value = "/getPrepMouleId:{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody PrepMouleValue getPrepMouleParId(@PathVariable Long id) {

		return vPrepMouleService.recherchePrepMouleParId(id);
	}

	@RequestMapping(value = "/getPrepMouleByRef:{reference}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody PrepMouleValue getPrepMouleByReference(@PathVariable Long reference) {

	   return vPrepMouleService.recherchePrepMouleParReference(reference);
	}

	/**********************
	 * Méthode de modification d'un PrepMoule
	 **********************/
	/**
	 * @param pPhaseOfValue
	 * @return
	 */
	@RequestMapping(value = "/modifiePrepMoule", method = RequestMethod.POST)
	public @ResponseBody String modifierBonRecption(@RequestBody PrepMouleValue pPrepMouleValue) {
		return this.vPrepMouleService.modifierPrepMoule(pPrepMouleValue);

	}

	/**********************
	 * Méthode de modification d'un PrepMoule
	 **********************/

	@RequestMapping(value = "/supprimerPrepMoule:{id}", method = RequestMethod.DELETE)
	public void supprimerPrepMoule(@PathVariable("id") String id) {
		vPrepMouleService.supprimerPrepMoule(Long.valueOf(id));

	}

	@RequestMapping(value = "/creerPrepMoule", method = RequestMethod.POST)
	public @ResponseBody String creerPrepMoule(@RequestBody PrepMouleValue pPrepMouleValue) {

		return this.vPrepMouleService.creerPrepMoule(pPrepMouleValue);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<PrepMouleValue> viewAllPrepMoule() {
		return vPrepMouleService.listerPrepMoule();
	}



	@RequestMapping(value = "/listRefPrepMouleParRefBR", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody String listRefPrepMouleParRefBR(@RequestBody String referenceBR) {
		return vPrepMouleService.listRefPrepMouleParRefBR(referenceBR);

	}



	@RequestMapping(value = "/getReferencePrepMoule", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<PrepMouleValue> getReferencePrepMoule() {
		return vPrepMouleService.getReferencePrepMoule();
	}

}
