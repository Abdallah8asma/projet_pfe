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

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.UtilsValue;
import com.gpro.consulting.tiers.commun.service.elementBase.IUtilsEntiteService;

@Controller
@RequestMapping("/utilsEntite")
public class UtilsEntiteRestImpl {

	@Autowired
	private IUtilsEntiteService ebUtilsService;
	
	//@Autowired
	//private IProduitUtilsService produitUtilsService; 	
	 
	List<UtilsValue> listeType=new ArrayList<>();
	
	/*************get Utils By ID*************/
	@RequestMapping(value = "/getId:{id}", method = RequestMethod.GET, produces =  "application/json")
	public @ResponseBody UtilsValue getUtils(@PathVariable Long id) {
		UtilsValue pUtilsValue=new UtilsValue();
		pUtilsValue.setId(id);
		return  ebUtilsService.rechercheUtilsParId(pUtilsValue);
	}
	
	/**********all ***********/
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<UtilsValue> viewAllType(){
		return this.listeType= ebUtilsService.listeUtils();
    }
	
	
	 @RequestMapping(value = "/creerUtils", method = RequestMethod.POST)
	  public @ResponseBody String creerUtilsProduit(@RequestBody UtilsValue pUtilsValue) {
	      //transformation et transcodage des referenciel 
		  return ebUtilsService.creerUtils(pUtilsValue);
	  }

	  @RequestMapping(value = "/modifierUtils", method = RequestMethod.POST)
	  public @ResponseBody String modifierUtilsProduit(@RequestBody UtilsValue pUtilsValue) {
	    return this.ebUtilsService.modifierUtils(pUtilsValue);
	  }

	  @RequestMapping(value = "/supprimerUtils:{id}", method = RequestMethod.DELETE)
	  public void supprimerUtils(@PathVariable("id") String id) {
		  Long idSupp= Long.parseLong(id);
		  UtilsValue color= new UtilsValue();
		  color.setId(idSupp);
		  ebUtilsService.supprimerUtils(idSupp);
	  }
	  
	  
		@RequestMapping(value = "/getAllByType:{type}", method = RequestMethod.GET, produces =  "application/json")
		public @ResponseBody List<UtilsValue> getAllByType(@PathVariable String type) {
		
			return  ebUtilsService.listeUtilsParType(type);
		}
	  
}
