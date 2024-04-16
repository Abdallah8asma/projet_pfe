package com.gpro.consulting.tiers.logistique.rest.gs.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseCacheValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.EmplacementValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.MagasinValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.RaisonMouvementStockValue;
import com.gpro.consulting.tiers.logistique.service.gs.IGestionnaireGSCacheService;

@Controller
@RequestMapping("/gestionnaireCache")
public class GestionnaireCacheRestImpl {
	
	private static final Logger logger = LoggerFactory.getLogger(GestionnaireCacheRestImpl.class);
	
	@Autowired
	private IGestionnaireGSCacheService gestionnaireGSCacheService;


	/**
	 * all magasin
	 * @return
	 */
	@RequestMapping(value = "/listeMagasinCache", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<MagasinValue> viewAllMagasin(){
		//logger.info("Entrer Cache magasin");
		return gestionnaireGSCacheService.getListeMagasin();
    }

	/***liste emplacement*/
	@RequestMapping(value = "/listEmplacementCache", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<EmplacementValue> viewAllEmplacement(){
		//logger.info("Entrer Cache emplacement");
		return gestionnaireGSCacheService.getListeEmplacement();
    }
	
	/******liste raison*******/
	@RequestMapping(value = "/listeRaisonCache", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<RaisonMouvementStockValue> viewAllRaison(){
		//logger.info("Entrer Cache raison");
		return gestionnaireGSCacheService.getListeRaison();
    }
	
	@RequestMapping(value = "/listeFournisseurCache", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<PartieInteresseCacheValue> viewFournisseur(){
		//logger.info("Entrer Cache fournisseur");
		return gestionnaireGSCacheService.getListeFournisseur();
    }

	
	@RequestMapping(value = "/listeClientCache", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<PartieInteresseCacheValue> viewClient(){
		//logger.info("Entrer Cache client");
		return gestionnaireGSCacheService.getListeClient();
    }

	@RequestMapping(value = "/listeSousTraitantCache", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<PartieInteresseCacheValue> viewListSousTraitant(){
		//logger.info("Entrer Cache sous traitant ");
		return gestionnaireGSCacheService.getListeSousTraitant();
    }
	
	/**
	 * Refrech du cashe
	 * @return
	 */
	@RequestMapping(value = "/reloadGs", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody void reloadGestionStock(){
		//logger.info("reloadStock");
		gestionnaireGSCacheService.reloadGestionStock();
    }

}
