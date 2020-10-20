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

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.MetrageValue;
import com.gpro.consulting.tiers.commun.service.elementBase.IMetrageService;

@Controller
@RequestMapping("/metrage")
public class MetrageRestImpl {

	@Autowired
	private IMetrageService ebMetrageService;
	
	List<MetrageValue> listeType=new ArrayList<>();
	
	@RequestMapping(value = "/string", method = RequestMethod.GET,produces =  "application/json")
    public @ResponseBody String testStringPersonne(){
         return "testMetrage";
    }

	/*************get Metrage By ID*************/
	@RequestMapping(value = "/getId:{id}", method = RequestMethod.GET, produces =  "application/json")
	public @ResponseBody MetrageValue getMetrage(@PathVariable Long id) {
		MetrageValue pMetrageValue=new MetrageValue();
		pMetrageValue.setId(id);
		return  ebMetrageService.rechercheMetrageParId(pMetrageValue);
	}
	
	/**********all type***********/
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<MetrageValue> viewAllType(){
		return this.listeType= ebMetrageService.listeMetrage();
    }
	
	 /**********************  Méthode de Creation **********************/
	  @RequestMapping(value = "/creerMetrage", method = RequestMethod.POST)
	  public @ResponseBody String creerMetrage(@RequestBody MetrageValue pMetrageValue) {
	    return this.ebMetrageService.creerMetrage(pMetrageValue);
	  }

	  /**********************  Méthode de modification **********************/
	  @RequestMapping(value = "/modifierMetrage", method = RequestMethod.POST)
	  public @ResponseBody String modifierMetrage(@RequestBody MetrageValue pMetrageValue) {
	    return this.ebMetrageService.modifierMetrage(pMetrageValue);
	  }
	  
	  /**********************  Méthode de Suppression  **********************/
	  @RequestMapping(value = "/supprimerMetrage:{id}", method = RequestMethod.DELETE)
	  public void supprimerMetrage(@PathVariable("id") String id) {
	   
	   Long idSupp= Long.parseLong(id);
	   ebMetrageService.supprimerMetrage(Long.valueOf(idSupp));
	  }
	
}
