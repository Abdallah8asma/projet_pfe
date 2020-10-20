package com.gpro.consulting.tiers.commun.rest.elementBase.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.BoutiqueValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticritereBoutiqueValue;
import com.gpro.consulting.tiers.commun.service.elementBase.IBoutiqueService;

@Controller
@RequestMapping("/boutique")
public class BoutiqueRestImpl {

	@Autowired
	private IBoutiqueService ebBoutiqueService;
	
	//@Autowired
	//private IBoutiqueService BoutiqueService; 	

	
	/*************get Boutique By ID*************/
	@RequestMapping(value = "/getId:{id}", method = RequestMethod.GET, produces =  "application/json")
	public @ResponseBody BoutiqueValue getBoutique(@PathVariable Long id) {
		BoutiqueValue pBoutiqueValue=new BoutiqueValue();
		pBoutiqueValue.setId(id);
		return  ebBoutiqueService.rechercheBoutiqueParId(pBoutiqueValue);
	}
	
	/**********all ***********/
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<BoutiqueValue> viewAllType(){
		return ebBoutiqueService.listeBoutique();
    }
	
	
	 @RequestMapping(value = "/creerBoutique", method = RequestMethod.POST)
	  public @ResponseBody String creerBoutique(@RequestBody BoutiqueValue pBoutiqueValue) {
	      //transformation et transcodage des referenciel 
		  return ebBoutiqueService.creerBoutique(pBoutiqueValue);
	  }

	  @RequestMapping(value = "/modifierBoutique", method = RequestMethod.POST)
	  public @ResponseBody String modifierBoutique(@RequestBody BoutiqueValue pBoutiqueValue) {
	    return this.ebBoutiqueService.modifierBoutique(pBoutiqueValue);
	  }

	  @RequestMapping(value = "/supprimerBoutique:{id}", method = RequestMethod.DELETE)
	  public void supprimerBoutique(@PathVariable("id") String id) {
		  Long idSupp= Long.parseLong(id);
		  BoutiqueValue color= new BoutiqueValue();
		  color.setId(idSupp);
		  ebBoutiqueService.supprimerBoutique(idSupp);
	  }
	  
	  @RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST)
	  public @ResponseBody List<BoutiqueValue> rechercheMulticritere(
	    @RequestBody final RechercheMulticritereBoutiqueValue pRechercheMultiCritere) {


	    return ebBoutiqueService.rechercheMulticritere(pRechercheMultiCritere);
	  }
	  
}
