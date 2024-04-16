package com.gpro.consulting.tiers.logistique.rest.gs.impl;

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

import com.gpro.consulting.tiers.logistique.coordination.gs.value.EntiteStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.RechercheMulticritereEntiteStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.ResultatRechecheEntiteStockStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.ResultatRechercheEntiteStockMouvementValue;
import com.gpro.consulting.tiers.logistique.service.gs.IEntiteStockService;

@Controller
@RequestMapping("/entiteStock")
public class EntiteStockRestImpl {

	private static final Logger logger = LoggerFactory.getLogger(EntiteStockRestImpl.class);
	
	@Autowired
	private IEntiteStockService entiteStockService;


	/******************************* Entite Stock for mouvement *********************************/ 
  @RequestMapping(value = "/rechercheEntiteStockMouvement", method = RequestMethod.POST, produces = "application/json")
  public @ResponseBody  ResultatRechercheEntiteStockMouvementValue  rechercheEntiteStockMouvement(@RequestBody final RechercheMulticritereEntiteStockValue pEntiteStockMouvement) {
	  
	  logger.info("rechercheEntiteStockMouvement : "+ pEntiteStockMouvement.toString());  
	  
     ResultatRechercheEntiteStockMouvementValue resultatRecherche= entiteStockService.rechercherEntiteStockMouvement(pEntiteStockMouvement);
     logger.info("Resultat de recherche Entite Stock :"+resultatRecherche.toString());
     return resultatRecherche;
  }

  
  /******************************* Entite Stock *********************************/ 
  @RequestMapping(value = "/rechercheParCritere", method = RequestMethod.POST, produces = "application/json")
  public @ResponseBody  ResultatRechecheEntiteStockStockValue  rechercheParCritereEntiteStock( @RequestBody final RechercheMulticritereEntiteStockValue pEntiteStock) {
	  
	  logger.info("rechercheParCritere: "+ pEntiteStock.toString());
	  
     ResultatRechecheEntiteStockStockValue resultatRecherche= entiteStockService.rechercherEntiteStockMultiCritere(pEntiteStock);
     logger.info("Resultat de recherche Entite Stock :"+resultatRecherche.toString());
     return resultatRecherche;
  }

  /*************get entite stock  By ID*************/
	@RequestMapping(value = "/getId:{id}", method = RequestMethod.GET, produces =  "application/json")
	public @ResponseBody EntiteStockValue getEntiteStockParId(@PathVariable Long id) {
		return  entiteStockService.rechercheEntiteStockParId(id);
	}
	
  /******************************* All mouvement stock *********************************/ 
  @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
  public @ResponseBody List < EntiteStockValue > viewAllEntiteStock() {
	  List < EntiteStockValue > vEs =entiteStockService.listeEntiteStock();
	//TODO : cache
    return vEs;
  }
  
  /**********************  Méthode de Creation d'un entite stock **********************/
  @RequestMapping(value = "/creerEntiteStock", method = RequestMethod.POST)
  public @ResponseBody String creerEntiteStock(@RequestBody EntiteStockValue pEntiteStock) {
     return entiteStockService.creerEntiteStock(pEntiteStock);
  }

  /**********************  Méthode de modification d'un entite stock **********************/
  @RequestMapping(value = "/modifierEntieStock", method = RequestMethod.POST)
  public @ResponseBody String modifieEntiteStock(@RequestBody EntiteStockValue pEntiteStock) {
     return entiteStockService.modifierEntiteStock(pEntiteStock);
  }
  
  /**********************  Méthode de Suppression d'un entite stock **********************/
  @RequestMapping(value = "/supprimerEntiteStock:{id}", method = RequestMethod.DELETE)
  public void supprimerEntiteStock(@PathVariable String id) {
   Long idSupp= Long.parseLong(id);
   entiteStockService.supprimerEntiteStock(idSupp);
  }


}
