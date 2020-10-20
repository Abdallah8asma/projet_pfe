package com.gpro.consulting.tiers.commun.rest.partieInteressee.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.CategorieValue;
import com.gpro.consulting.tiers.commun.domaine.partieInteressee.ICategoriePartieInteresseeDomaine;
import com.gpro.consulting.tiers.commun.service.partieInteressee.ICategoriePartieInteresseeService;

@Controller
@RequestMapping("/cathegoriePI")
public class CathegoriePartieInteresseRestImpl {
	@Autowired
	ICategoriePartieInteresseeService  categoriePartieInteresseeService;
	
	//conctructeur
	public CathegoriePartieInteresseRestImpl(){
		
	}
	
	 @RequestMapping(value = "/string", method = RequestMethod.GET)
	  public @ResponseBody String testStringPI() {
	    return "Test";
	  }

	  // liste Cathegorie 
	  @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	  public @ResponseBody List < CategorieValue > viewAllCathegorie() {
           return categoriePartieInteresseeService.listeCategoriePartieInteressee();
	  }

       // recherche categorie by id
	  @RequestMapping(value = "/getId:{id}", method = RequestMethod.GET)
		public @ResponseBody CategorieValue getCathegorie(@PathVariable Long id) {
			return null;
			//return familleProduitService.rechercheFamilleProduitById(id);
		}

	  @RequestMapping(value = "/creerCathegoriePI", method = RequestMethod.POST)
	  public @ResponseBody String creerCathegoriePI(@RequestBody CategorieValue pCathegoriePI) {
	      //transformation et transcodage des referenciel 
		  return categoriePartieInteresseeService.creerCategoriePartieInteressee(pCathegoriePI);
	  }

	  @RequestMapping(value = "/modifierCathegoriePI", method = RequestMethod.POST)
	  public @ResponseBody String modifierCathegoriePI(@RequestBody CategorieValue pCathegoriePI) {
	    return categoriePartieInteresseeService.modifierCategoriePartieInteressee(pCathegoriePI);
	  }

	  @RequestMapping(value = "/supprimerCathegoriePI:{id}", method = RequestMethod.DELETE)
	  public void supprimerCathegoriePI(@PathVariable("id") String id) {
		  Long idSupp= Long.parseLong(id);
		  categoriePartieInteresseeService.supprimerCategoriePartieInteressee(idSupp);

	  }

}
