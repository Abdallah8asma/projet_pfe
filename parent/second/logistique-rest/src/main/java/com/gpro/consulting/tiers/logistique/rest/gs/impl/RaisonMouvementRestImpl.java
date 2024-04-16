package com.gpro.consulting.tiers.logistique.rest.gs.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.logistique.coordination.gs.value.RaisonMouvementStockValue;
import com.gpro.consulting.tiers.logistique.service.gs.IRaisonMouvementService;

@Controller
@RequestMapping("/raisonMouvement")
public class RaisonMouvementRestImpl{

	@Autowired
	private IRaisonMouvementService raisonMouvementService;


	/******************************* All raison *********************************/ 
	  @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	  public @ResponseBody List < RaisonMouvementStockValue > viewAllRaisonMouvement() {
	    return raisonMouvementService.listeRaisonMouvementStock();
	  }
	  
	 
	  
	  /**********************  Méthode de Creation d'un Raison **********************/
	  @RequestMapping(value = "/creerRaisonMouvement", method = RequestMethod.POST)
	  public @ResponseBody String creerRaisonMouvement(@RequestBody RaisonMouvementStockValue pRaisonMouvementStock) {
	    return raisonMouvementService.creerRaisonMouvementStock(pRaisonMouvementStock);
	  }

	  /**********************  Méthode de modification d'un Raison **********************/
	  @RequestMapping(value = "/modifierRaisonMouvement", method = RequestMethod.POST)
	  public @ResponseBody String modifierRaison(@RequestBody RaisonMouvementStockValue pRaisonMouvementStockValue) {
	    return raisonMouvementService.modifierRaisonMouvementStock(pRaisonMouvementStockValue);
	    		
	  }
	  
	  /**********************  Méthode de Suppression d'un raison **********************/
	  @RequestMapping(value = "/supprimerRaison:{id}", method = RequestMethod.DELETE)
	  public void supprimerRaisonMouvement(@PathVariable("id") String id) {
		  Long idSupp= Long.parseLong(id);
	      raisonMouvementService.supprimerRaisonMouvementStock(idSupp);
	  }
	  

}
