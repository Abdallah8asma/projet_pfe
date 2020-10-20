package com.gpro.consulting.tiers.commun.rest.elementBase.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.GrosseurValue;
import com.gpro.consulting.tiers.commun.service.elementBase.IGrosseurService;

@Controller
@RequestMapping("/grosseur")
public class GrosseurRestImpl {

	@Autowired
	private IGrosseurService ebGrosseurService;
	
	List<GrosseurValue> listeType=new ArrayList<>();
	
	/*************get Grosseur By ID*************/
	/**
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getId:{id}", method = RequestMethod.GET, produces =  "application/json")
	public @ResponseBody GrosseurValue getGrosseur(@PathVariable Long id) {
		GrosseurValue pGrosseurValue=new GrosseurValue();
		pGrosseurValue.setId(id);
		return  ebGrosseurService.rechercheGrosseurParId(pGrosseurValue);
	}

	/**********all type***********/
	/**
	 * @return
	 */
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<GrosseurValue> viewAllType(){
		return this.listeType= ebGrosseurService.listeGrosseur();
    }
	
	 /**********************  Méthode de Creation **********************/
	  @RequestMapping(value = "/creerGrosseur", method = RequestMethod.POST)
	  public @ResponseBody String creerGrosseur(@RequestBody GrosseurValue pGrosseurValue) {
	    return this.ebGrosseurService.creerGrosseur(pGrosseurValue);
	  }

	  /**********************  Méthode de modification **********************/
	  @RequestMapping(value = "/modifierGrosseur", method = RequestMethod.POST)
	  public @ResponseBody String modifierGrosseur(@RequestBody GrosseurValue pGrosseurValue) {
	    return this.ebGrosseurService.modifierGrosseur(pGrosseurValue);
	  }
	  
	  /**********************  Méthode de Suppression  **********************/
	  @RequestMapping(value = "/supprimerGrosseur:{id}", method = RequestMethod.DELETE)
	  public void supprimerGrosseur(@PathVariable("id") String id) {
	   
	   Long idSupp= Long.parseLong(id);
	   ebGrosseurService.supprimerGrosseur(Long.valueOf(idSupp));
	  }
	
}
