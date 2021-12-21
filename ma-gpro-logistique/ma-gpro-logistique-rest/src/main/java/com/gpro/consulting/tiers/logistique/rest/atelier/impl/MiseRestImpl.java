package com.gpro.consulting.tiers.logistique.rest.atelier.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.ElementRechecheMiseValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.MiseValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.RechercheMulticritereMiseValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.ResultatRechercheMiseValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.TraitementMiseValue;
import com.gpro.consulting.tiers.logistique.persistance.atelier.mise.utilities.MisePersistanceUtilities;
import com.gpro.consulting.tiers.logistique.service.atelier.cache.IGestionnaireLogistiqueCacheService;
import com.gpro.consulting.tiers.logistique.service.atelier.mise.IMiseService;

@Controller
@RequestMapping("/mise")
public class MiseRestImpl {

	@Autowired
	IMiseService vMiseService;

	@Autowired
	private IGestionnaireLogistiqueCacheService gestionnaireLogistiqueCacheService;
	
	
	@Autowired
	private MisePersistanceUtilities vMisePersistanceUtilities;

	public MiseRestImpl() {
		// Constructeur vide
	}

	/*******************************
	 * recherche multicritere Mise
	 *********************************/

	@RequestMapping(value = "/rechercheMiseMulticritere", method = RequestMethod.POST)
	public @ResponseBody ResultatRechercheMiseValue rechercherMiseMultiCritere(
			@RequestBody final RechercheMulticritereMiseValue pRechercheMulticritereMise) {
		
		pRechercheMulticritereMise.setOptimized(vMisePersistanceUtilities.checkForOptimization(pRechercheMulticritereMise));
		
		
		ResultatRechercheMiseValue vResultatRecherche = vMiseService
				.rechercherMiseMultiCritere(pRechercheMulticritereMise);

		// Traitement : transformation de l'Id a sa propre Designation
	/*	for (ElementRechecheMiseValue elementRechecheMise : vResultatRecherche.getListeElementRechecheMiseValeur()) {

			// Client, Produit
			Map<String, String> mapA = gestionnaireLogistiqueCacheService.rechercherDesignationParId(
					elementRechecheMise.getAbreviationClient(), elementRechecheMise.getDesignationProduit(), null,
					null);
			// Client
			elementRechecheMise.setAbreviationClientDesignation(mapA.get("client"));
			// Produit (Tissu)
			elementRechecheMise.setReferenceProduit(mapA.get("produitRef"));

		}*/
		return vResultatRecherche;
	}

	/************* get PhaseOf Mise *************/
	/**
	 * @param id
	 * @return
	 */

	@RequestMapping(value = "/getMiseId:{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody MiseValue getEmploye(@PathVariable Long id) {

		return vMiseService.rechercheMiseParId(id);
	}
	
	@RequestMapping(value = "/rechercheMiseParReference:{ref}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody MiseValue getMiseByReferene(@PathVariable String ref) {

		return vMiseService.rechercheMiseParReference(ref);
	}


	/**********************
	 * Méthode de modification d'un Mise
	 **********************/
	/**
	 * @param pPhaseOfValue
	 * @return
	 */
	@RequestMapping(value = "/modifieMise", method = RequestMethod.POST)
	public @ResponseBody String modifierBonRecption(@RequestBody MiseValue pMiseValue) {
		return this.vMiseService.modifierMise(pMiseValue);

	}

	/**********************
	 * Méthode de modification d'un Mise
	 **********************/

	@RequestMapping(value = "/supprimerMise:{id}", method = RequestMethod.DELETE)
	public void supprimerMise(@PathVariable("id") String id) {
		vMiseService.supprimerMise(Long.valueOf(id));

	}

	@RequestMapping(value = "/creerMise", method = RequestMethod.POST)
	public @ResponseBody String creerMise(@RequestBody MiseValue pMiseValue) {

		gestionnaireLogistiqueCacheService.reloadLogistiqueCache();
		return this.vMiseService.creerMise(pMiseValue);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<MiseValue> viewAllMise() {
		return vMiseService.listerMise();
	}

	@RequestMapping(value = "/getTraitMiseById:{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody TraitementMiseValue getTraitMiseById(@PathVariable("id") Long id) {
		return vMiseService.getTraitementMiseById(id);

	}

	@RequestMapping(value = "/listRefMiseParRefBR", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody String listRefMiseParRefBR(@RequestBody String referenceBR) {
		return vMiseService.listRefMiseParRefBR(referenceBR);

	}
	
	
	@RequestMapping(value = "/getReferenceMise", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<MiseValue> getReferenceMise() {
		return vMiseService.getReferenceMise();
	}

	// //Hajer Amri 02/02/2017
	// @RequestMapping(value = "/listRefMiseParRefBR:{refBonreception}", method
	// = RequestMethod.GET, produces = "application/json")
	// public @ResponseBody String
	// listRefMiseParRefBR(@PathVariable("refBonreception") String
	// refBonreception) {
	// return vMiseService.listRefMiseParRefBR(refBonreception);
	//
	// }

}
