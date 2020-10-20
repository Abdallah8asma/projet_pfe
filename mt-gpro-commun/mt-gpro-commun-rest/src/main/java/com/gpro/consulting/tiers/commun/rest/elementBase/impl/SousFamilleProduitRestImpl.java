package com.gpro.consulting.tiers.commun.rest.elementBase.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.SousFamilleProduitValue;
import com.gpro.consulting.tiers.commun.service.elementBase.ISousFamilleProduitService;


@Controller
@RequestMapping("/sousFamilleProduit")
public class SousFamilleProduitRestImpl {
	@Autowired
	ISousFamilleProduitService sousFamilleProduitService;
	//conctructeur
	public SousFamilleProduitRestImpl(){
		
	}
	
	 @RequestMapping(value = "/string", method = RequestMethod.GET)
	  public @ResponseBody String testStringProduit() {
	    return "Test";
	  }

	  // liste SOUS FAMIlle produit 
	  @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	  public @ResponseBody List < SousFamilleProduitValue > viewAllSousFamilleProduit() {
            return sousFamilleProduitService.listeSousFamilleProduit();
	  }

       // recherche produit by id
	  @RequestMapping(value = "/getId:{id}", method = RequestMethod.GET)
		public @ResponseBody SousFamilleProduitValue getSOUfamille(@PathVariable Long id) {
			return sousFamilleProduitService.rechercheSousFamilleProduitById(id);
		}

	  /**********************  Méthode de Creation d'une SousFamilleProduit **********************/
	  @RequestMapping(value = "/creerSousFamilleProduit", method = RequestMethod.POST)
	  public @ResponseBody String creerSousFamilleProduit(@RequestBody SousFamilleProduitValue pSousFamilleProduitValue) {
	    return this.sousFamilleProduitService.creerSousFamilleProduit(pSousFamilleProduitValue);
	  }

	  /**********************  Méthode de modification d'une SousFamilleProduit **********************/
	  @RequestMapping(value = "/modifierSousFamilleProduit", method = RequestMethod.POST)
	  public @ResponseBody String modifierSousFamilleProduit(@RequestBody SousFamilleProduitValue pSousFamilleProduitValue) {
	    return this.sousFamilleProduitService.modifierSousFamilleProduit(pSousFamilleProduitValue);
	  }
	  
	  /**********************  Méthode de Suppression d'une SousFamilleProduit **********************/
	  @RequestMapping(value = "/supprimerSousFamilleProduit:{id}", method = RequestMethod.DELETE)
	  public void supprimerSousFamilleProduit(@PathVariable("id") String id) {
	   //System.out.println("id produit =  "+id);
	   
	   Long idSupp= Long.parseLong(id);
	   sousFamilleProduitService.supprimerSousFamilleProduit(Long.valueOf(idSupp));
	  }
	  
}
