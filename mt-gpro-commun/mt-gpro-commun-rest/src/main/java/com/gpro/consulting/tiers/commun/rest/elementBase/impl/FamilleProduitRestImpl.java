package com.gpro.consulting.tiers.commun.rest.elementBase.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.FamilleProduitValue;
import com.gpro.consulting.tiers.commun.service.elementBase.IFamilleProduitService;

@Controller
@RequestMapping("/famillelProduit")
public class FamilleProduitRestImpl {
	@Autowired
	IFamilleProduitService familleProduitService;
	
	//conctructeur
	public FamilleProduitRestImpl(){
		
	}
	
	 @RequestMapping(value = "/string", method = RequestMethod.GET)
	  public @ResponseBody String testStringProduit() {
	    return "Test";
	  }

	  // liste famille produit 
	  @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	  public @ResponseBody List < FamilleProduitValue > viewAllProduit() {
            return familleProduitService.listeFamilleProduit();
	  }

       // recherche famille by id
	  @RequestMapping(value = "/getId:{id}", method = RequestMethod.GET)
		public @ResponseBody FamilleProduitValue getProduit(@PathVariable Long id) {
			return familleProduitService.rechercheFamilleProduitById(id);
		}

	  @RequestMapping(value = "/creerFamilleProduit", method = RequestMethod.POST)
	  public @ResponseBody String creerFamilleProduit(@RequestBody FamilleProduitValue pFamilleProduitValue) {
	      //transformation et transcodage des referenciel 
		  return familleProduitService.creerFamilleProduit(pFamilleProduitValue);
	  }

	  @RequestMapping(value = "/modifierFamilleProduit", method = RequestMethod.POST)
	  public @ResponseBody String modifierFamilleProduit(@RequestBody FamilleProduitValue pFamilleProduitValue) {
	    return familleProduitService.modifierFamilleProduit(pFamilleProduitValue);
	  }

	  @RequestMapping(value = "/supprimerFamilleProduit:{id}", method = RequestMethod.DELETE)
	  public void supprimerFamilleProduit(@PathVariable("id") String id) {
	   //System.out.println("id produit =  "+id);
	   
	   Long idSupp= Long.parseLong(id);
	   familleProduitService.supprimerFamilleProduit(Long.valueOf(idSupp));
	  }
	  
}
