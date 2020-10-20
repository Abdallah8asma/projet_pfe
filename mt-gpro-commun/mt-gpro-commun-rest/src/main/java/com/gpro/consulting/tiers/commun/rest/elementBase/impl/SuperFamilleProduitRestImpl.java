package com.gpro.consulting.tiers.commun.rest.elementBase.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.SuperFamilleProduitValue;
import com.gpro.consulting.tiers.commun.service.elementBase.ISuperFamilleProduitService;

@Controller
@RequestMapping("/superFamillelProduit")
public class SuperFamilleProduitRestImpl {
	@Autowired
	ISuperFamilleProduitService superFamilleProduitService;
	
	//conctructeur
	public SuperFamilleProduitRestImpl(){
		
	}
	

	  // liste famille produit 
	  @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	  public @ResponseBody List < SuperFamilleProduitValue > viewAllSuperFamilleProduit() {
            return superFamilleProduitService.listeSuperFamilleProduit();
	  }

       // recherche famille by id
	  @RequestMapping(value = "/getId:{id}", method = RequestMethod.GET)
		public @ResponseBody SuperFamilleProduitValue getSuperFamilleProduit(@PathVariable Long id) {
			return superFamilleProduitService.rechercheSuperFamilleProduitById(id);
		}

	  @RequestMapping(value = "/creerSuperFamilleProduit", method = RequestMethod.POST)
	  public @ResponseBody String creerSuperFamilleProduit(@RequestBody SuperFamilleProduitValue pSuperFamilleProduitValue) {
	      //transformation et transcodage des referenciel 
		  return superFamilleProduitService.creerSuperFamilleProduit(pSuperFamilleProduitValue);
	  }

	  @RequestMapping(value = "/modifierSuperFamilleProduit", method = RequestMethod.POST)
	  public @ResponseBody String modifierSuperFamilleProduit(@RequestBody SuperFamilleProduitValue pSuperFamilleProduitValue) {
	    return superFamilleProduitService.modifierSuperFamilleProduit(pSuperFamilleProduitValue);
	  }

	  @RequestMapping(value = "/supprimerSuperFamilleProduit:{id}", method = RequestMethod.DELETE)
	  public void supprimerSuperFamilleProduit(@PathVariable("id") String id) {
	   //System.out.println("id produit =  "+id);
	   
	   Long idSupp= Long.parseLong(id);
	   superFamilleProduitService.supprimerSuperFamilleProduit(Long.valueOf(idSupp));
	  }
	  
}
