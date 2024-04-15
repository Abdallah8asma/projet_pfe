package com.gpro.consulting.tiers.commun.rest.partieInteressee.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.FamilleValue;
import com.gpro.consulting.tiers.commun.service.partieInteressee.IFamillePartieInteresseeService;
@Controller
@RequestMapping("/famillePI")
public class FamillePartieInteresseRestImpl {
	@Autowired
	IFamillePartieInteresseeService famillePartieInteresseeService;
	
	//conctructeur
	public FamillePartieInteresseRestImpl(){
		
	}
	
	 @RequestMapping(value = "/string", method = RequestMethod.GET)
	  public @ResponseBody String testStringProduit() {
	    return "Test";
	  }

	  // liste famille PI 
	  @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	  public @ResponseBody List < FamilleValue > viewAllFamillePI() {
            return famillePartieInteresseeService.listeFamillePartieInteressee();
	  }

       // recherche famille by id
	  @RequestMapping(value = "/getId:{id}", method = RequestMethod.GET)
		public @ResponseBody FamilleValue getFamillePI(@PathVariable Long id) {
			
			return famillePartieInteresseeService.rechercheFamillePartieInteresseeParId(null);
		}

	  @RequestMapping(value = "/creerFamillePI", method = RequestMethod.POST)
	  public @ResponseBody String creerFamillePI(@RequestBody FamilleValue pFamilleValue) {
	     return famillePartieInteresseeService.creerFamillePartieInteressee(pFamilleValue);
	  }

	  @RequestMapping(value = "/modifierFamillePI", method = RequestMethod.POST)
	  public @ResponseBody String modifierFamillePI(@RequestBody FamilleValue pFamilleValue) {
	    return famillePartieInteresseeService.modifierFamillePartieInteressee(pFamilleValue);
	  }

	  @RequestMapping(value = "/supprimerFamillePI:{id}", method = RequestMethod.DELETE)
	  public void supprimerFamillePI(@PathVariable("id") String id) {
		  Long idSupp= Long.parseLong(id);
		  famillePartieInteresseeService.supprimerFamillePartieInteressee(idSupp);
	  }
	
}
