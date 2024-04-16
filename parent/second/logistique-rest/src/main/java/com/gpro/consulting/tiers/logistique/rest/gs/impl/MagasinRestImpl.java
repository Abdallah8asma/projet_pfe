package com.gpro.consulting.tiers.logistique.rest.gs.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.BoutiqueValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.MagasinValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.RechercheMulticritereMagasinValue;
import com.gpro.consulting.tiers.logistique.service.gs.IMagasinService;


@Controller
@RequestMapping("/magasin")
public class MagasinRestImpl {

	@Autowired
	private IMagasinService magasinService;
	
	
	/*************get magasin By ID*************/
	@RequestMapping(value = "/getId:{id}", method = RequestMethod.GET, produces =  "application/json")
	public @ResponseBody MagasinValue getBoutique(@PathVariable Long id) {
		MagasinValue pBoutiqueValue=new MagasinValue();
		pBoutiqueValue.setId(id);
		return  magasinService.rechercheMagasinParId(pBoutiqueValue);
	}


	  /**********************  Méthode reheche  magasin **********************/
	  @RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST)
	  public @ResponseBody  List < MagasinValue > rechercheMulticritere(@RequestBody RechercheMulticritereMagasinValue pMagasinValue) {
	    return magasinService.rechercheMulticritere(pMagasinValue);
	  }

	
	  /******************************* All magasin *********************************/ 
	  @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	  public @ResponseBody List < MagasinValue > viewAllMagasin() {
	    return magasinService.listeMagasin();
	  }
	  
	 
	  
	  /**********************  Méthode de Creation  magasin **********************/
	  @RequestMapping(value = "/creerMagasin", method = RequestMethod.POST)
	  public @ResponseBody String creerMagasin(@RequestBody MagasinValue pMagasinValue) {
	    return magasinService.creerMagasin(pMagasinValue);
	  }

	  /**********************  Méthode de modification magasin **********************/
	  @RequestMapping(value = "/modifierMagasin", method = RequestMethod.POST)
	  public @ResponseBody String modifierMagasin(@RequestBody MagasinValue pMagasinValue) {
	    return magasinService.modifierMagasin(pMagasinValue);
	  }
	  
	  /**********************  Méthode de Suppression  magasin **********************/
	  @RequestMapping(value = "/supprimerMagasin:{id}", method = RequestMethod.DELETE)
	  public @ResponseBody String supprimerEmplacement(@PathVariable("id") String id) {
		  Long idSupp= Long.parseLong(id);
		  magasinService.supprimerMagasin(idSupp);
		  
		  
		  return "OK";
	  }

	  /******************************* All Depot *********************************/ 
	  @RequestMapping(value = "/depots", method = RequestMethod.GET, produces = "application/json")
	  public @ResponseBody List < MagasinValue > viewAllDepot() {
	    return magasinService.listeDepot();
	  }
	  /******************************* All PDV *********************************/ 
	  @RequestMapping(value = "/pdv", method = RequestMethod.GET, produces = "application/json")
	  public @ResponseBody List < MagasinValue > viewAllPDV() {
	    return magasinService.listePDV();
	  }
}
