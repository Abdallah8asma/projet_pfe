package com.gpro.consulting.tiers.commun.rest.partieInteressee.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.BanqueValue;
import com.gpro.consulting.tiers.commun.service.partieInteressee.IBanquePartieInteresseeService;

@Controller
@RequestMapping("/banquePI")
public class BanquePartieInteresseRestImpl {
	@Autowired
	IBanquePartieInteresseeService  banquePartieInteresseeService;
	
	//conctructeur
	public BanquePartieInteresseRestImpl(){
		
	}
	
	 @RequestMapping(value = "/string", method = RequestMethod.GET)
	  public @ResponseBody String testStringPI() {
	    return "Test";
	  }

	  // liste Banque 
	  @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	  public @ResponseBody List < BanqueValue > viewAllBanque() {
           return banquePartieInteresseeService.listeBanquePartieInteressee();
	  }

       // recherche banque by id
	  @RequestMapping(value = "/getId:{id}", method = RequestMethod.GET)
		public @ResponseBody BanqueValue getBanque(@PathVariable Long id) {
			return null;
			//return familleProduitService.rechercheFamilleProduitById(id);
		}

	  @RequestMapping(value = "/creerBanquePI", method = RequestMethod.POST)
	  public @ResponseBody String creerBanquePI(@RequestBody BanqueValue pBanquePI) {
	      //transformation et transcodage des referenciel 
		  return banquePartieInteresseeService.creerBanquePartieInteressee(pBanquePI);
	  }

	  @RequestMapping(value = "/modifierBanquePI", method = RequestMethod.POST)
	  public @ResponseBody String modifierBanquePI(@RequestBody BanqueValue pBanquePI) {
	    return banquePartieInteresseeService.modifierBanquePartieInteressee(pBanquePI);
	  }

	  @RequestMapping(value = "/supprimerBanquePI:{id}", method = RequestMethod.DELETE)
	  public @ResponseBody String supprimerBanquePI(@PathVariable("id") String id) {
		  Long idSupp= Long.parseLong(id);
		  banquePartieInteresseeService.supprimerBanquePartieInteressee(idSupp);
		  
		  return "OK";

	  }

}
