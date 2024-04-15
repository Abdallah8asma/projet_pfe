package com.gpro.consulting.tiers.commun.rest.elementBase.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.SocieteValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticritereSocieteValue;
import com.gpro.consulting.tiers.commun.service.elementBase.ISocieteService;

@Controller
@RequestMapping("/societe")
public class SocieteRestImpl {

	@Autowired
	private ISocieteService ebSocieteService;
	
	//@Autowired
	//private ISocieteService SocieteService; 	

	
	/*************get Societe By ID*************/
	@RequestMapping(value = "/getId:{id}", method = RequestMethod.GET, produces =  "application/json")
	public @ResponseBody SocieteValue getSociete(@PathVariable Long id) {
		SocieteValue pSocieteValue=new SocieteValue();
		pSocieteValue.setId(id);
		return  ebSocieteService.rechercheSocieteParId(pSocieteValue);
	}
	
	/**********all ***********/
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<SocieteValue> viewAllType(){
		return ebSocieteService.listeSociete();
    }
	
	
	 @RequestMapping(value = "/creerSociete", method = RequestMethod.POST)
	  public @ResponseBody String creerSociete(@RequestBody SocieteValue pSocieteValue) {
	      //transformation et transcodage des referenciel 
		  return ebSocieteService.creerSociete(pSocieteValue);
	  }

	  @RequestMapping(value = "/modifierSociete", method = RequestMethod.POST)
	  public @ResponseBody String modifierSociete(@RequestBody SocieteValue pSocieteValue) {
	    return this.ebSocieteService.modifierSociete(pSocieteValue);
	  }

	  @RequestMapping(value = "/supprimerSociete:{id}", method = RequestMethod.DELETE)
	  public void supprimerSociete(@PathVariable("id") String id) {
		  Long idSupp= Long.parseLong(id);
		  SocieteValue color= new SocieteValue();
		  color.setId(idSupp);
		  ebSocieteService.supprimerSociete(idSupp);
	  }
	  
	  @RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST)
	  public @ResponseBody List<SocieteValue> rechercheMulticritere(
	    @RequestBody final RechercheMulticritereSocieteValue pRechercheMultiCritere) {


	    return ebSocieteService.rechercheMulticritere(pRechercheMultiCritere);
	  }
	  
}
