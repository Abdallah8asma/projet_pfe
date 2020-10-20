package com.gpro.consulting.tiers.logistique.rest.gc.vente.facture.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.RaisonFactureValue;
import com.gpro.consulting.tiers.logistique.service.gc.vente.facture.IRaisonFactureService;

@Controller
@RequestMapping("/raisonFacture")
public class RaisonFactureRestImpl{

	@Autowired
	private IRaisonFactureService raisonFactureService;


	/******************************* All raison *********************************/ 
	  @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	  public @ResponseBody List < RaisonFactureValue > viewAllRaisonMouvement() {
	    return raisonFactureService.listeRaisonFacture();
	  }
	  
	 
	  
	  /**********************  Méthode de Creation d'un Raison **********************/
	  @RequestMapping(value = "/creer", method = RequestMethod.POST)
	  public @ResponseBody String creerRaisonMouvement(@RequestBody RaisonFactureValue pRaisonFacture) {
	    return raisonFactureService.creerRaisonFacture(pRaisonFacture);
	  }

	  /**********************  Méthode de modification d'un Raison **********************/
	  @RequestMapping(value = "/modifier", method = RequestMethod.POST)
	  public @ResponseBody String modifierRaison(@RequestBody RaisonFactureValue pRaisonFactureValue) {
	    return raisonFactureService.modifierRaisonFacture(pRaisonFactureValue);
	    		
	  }
	  
	  /**********************  Méthode de Suppression d'un raison **********************/
	  @RequestMapping(value = "/supprimer:{id}", method = RequestMethod.DELETE)
	  public void supprimerRaisonMouvement(@PathVariable("id") String id) {
		  Long idSupp= Long.parseLong(id);
		  raisonFactureService.supprimerRaisonFacture(idSupp);
	  }
	  

}
