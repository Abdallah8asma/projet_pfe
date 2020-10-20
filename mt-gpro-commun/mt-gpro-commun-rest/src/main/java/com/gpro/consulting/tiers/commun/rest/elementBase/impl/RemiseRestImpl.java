package com.gpro.consulting.tiers.commun.rest.elementBase.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticritereRemiseValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RemiseValue;
import com.gpro.consulting.tiers.commun.service.elementBase.IProduitService;
import com.gpro.consulting.tiers.commun.service.elementBase.IRemiseService;

@Controller
@RequestMapping("/remise")
public class RemiseRestImpl {

	@Autowired
	private IRemiseService ebRemiseService;
	
	@Autowired
	IProduitService produitService;
	
	//@Autowired
	//private IRemiseService RemiseService; 	

	
	/*************get Remise By ID*************/
	@RequestMapping(value = "/getId:{id}", method = RequestMethod.GET, produces =  "application/json")
	public @ResponseBody RemiseValue getRemise(@PathVariable Long id) {
		RemiseValue pRemiseValue=new RemiseValue();
		pRemiseValue.setId(id);
		return  ebRemiseService.rechercheRemiseParId(pRemiseValue);
	}
	
	/**********all ***********/
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<RemiseValue> viewAllType(){
		return ebRemiseService.listeRemise();
    }
	
	
	 @RequestMapping(value = "/creerRemise", method = RequestMethod.POST)
	  public @ResponseBody String creerRemise(@RequestBody RemiseValue pRemiseValue) {
	      //transformation et transcodage des referenciel 
		  return ebRemiseService.creerRemise(pRemiseValue);
	  }

	  @RequestMapping(value = "/modifierRemise", method = RequestMethod.POST)
	  public @ResponseBody String modifierRemise(@RequestBody RemiseValue pRemiseValue) {
	    return this.ebRemiseService.modifierRemise(pRemiseValue);
	  }

	  @RequestMapping(value = "/supprimerRemise:{id}", method = RequestMethod.DELETE)
	  public @ResponseBody String supprimerRemise(@PathVariable("id") String id) {
		  Long idSupp= Long.parseLong(id);
		  RemiseValue color= new RemiseValue();
		  color.setId(idSupp);
		  ebRemiseService.supprimerRemise(idSupp);
		  
		  return "OK";
	  }
	  
	  @RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST)
	  public @ResponseBody List<RemiseValue> rechercheMulticritere(
	    @RequestBody final RechercheMulticritereRemiseValue pRechercheMultiCritere) {

		  List<RemiseValue> list =  ebRemiseService.rechercheMulticritere(pRechercheMultiCritere);
		  
		  for(RemiseValue remise : list)
		  {
			  
			  if(remise.getProduitId() != null) {
				  
				  ProduitValue prod = produitService.rechercheProduitById(remise.getProduitId());
				  
				  if(prod != null) {
					  
					  remise.setReferenceProduit(prod.getReference());
					  remise.setDesignationProduit(prod.getDesignation());
					  
				  }
				
				  
				  
			  }
			
			  
			  
		  }

	    return list;
	  }
	  
}
