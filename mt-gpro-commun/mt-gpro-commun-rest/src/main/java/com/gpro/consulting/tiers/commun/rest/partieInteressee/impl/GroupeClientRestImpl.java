package com.gpro.consulting.tiers.commun.rest.partieInteressee.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.GroupeClientValue;
import com.gpro.consulting.tiers.commun.service.partieInteressee.IGroupeClientService;

@Controller
@RequestMapping("/groupeClient")
public class GroupeClientRestImpl {
	@Autowired
	IGroupeClientService  groupeClientService;
	
	//conctructeur
	public GroupeClientRestImpl(){
		
	}
	
	 @RequestMapping(value = "/string", method = RequestMethod.GET)
	  public @ResponseBody String testStringPI() {
	    return "Test";
	  }

	  // liste Cathegorie 
	  @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	  public @ResponseBody List < GroupeClientValue > viewAllCathegorie() {
           return groupeClientService.listeGroupeClient();
	  }

       // recherche categorie by id
	  @RequestMapping(value = "/getId:{id}", method = RequestMethod.GET)
		public @ResponseBody GroupeClientValue getGroupeClient(@PathVariable Long id) {
			return null;
			//return familleProduitService.rechercheFamilleProduitById(id);
		}

	  @RequestMapping(value = "/creer", method = RequestMethod.POST)
	  public @ResponseBody String creer(@RequestBody GroupeClientValue p) {
	      //transformation et transcodage des referenciel 
		  return groupeClientService.creerGroupeClient(p);
	  }

	  @RequestMapping(value = "/modifier", method = RequestMethod.POST)
	  public @ResponseBody String modifier(@RequestBody GroupeClientValue p) {
	    return groupeClientService.modifierGroupeClient(p);
	  }

	  @RequestMapping(value = "/supprimer:{id}", method = RequestMethod.DELETE)
	  public void supprimer(@PathVariable("id") String id) {
		  Long idSupp= Long.parseLong(id);
		  groupeClientService.supprimerGroupeClient(idSupp);

	  }

}
