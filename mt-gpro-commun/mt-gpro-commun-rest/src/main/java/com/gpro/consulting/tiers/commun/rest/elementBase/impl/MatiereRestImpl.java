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

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.MatiereArticleValue;
import com.gpro.consulting.tiers.commun.service.elementBase.IMatiereService;

@Controller
@RequestMapping("/matiereArticle")
public class MatiereRestImpl {
	
	@Autowired
	private IMatiereService ebMatiereService;
	
	List<MatiereArticleValue> listeType=new ArrayList<>();
	
	
	/*************get Matiere By ID*************/
	@RequestMapping(value = "/getId:{id}", method = RequestMethod.GET, produces =  "application/json")
	public @ResponseBody MatiereArticleValue getMatiereArticle(@PathVariable Long id) {
		MatiereArticleValue pMatiereArticleValue=new MatiereArticleValue();
		pMatiereArticleValue.setId(id);
		return  ebMatiereService.rechercheMatiereParId(pMatiereArticleValue);
	}
	
	/**********all type***********/
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<MatiereArticleValue> viewAllType(){
		return this.listeType= ebMatiereService.listeMatiere();
    }
	
	 /**********************  Méthode de Creation **********************/
	  @RequestMapping(value = "/creerMatiereArticle", method = RequestMethod.POST)
	  public @ResponseBody String creerMatiereArticle(@RequestBody MatiereArticleValue pMatiereArticleValue) {
	    return this.ebMatiereService.creerMatiere(pMatiereArticleValue);
	  }

	  /**********************  Méthode de modification **********************/
	  @RequestMapping(value = "/modifierMatiereArticle", method = RequestMethod.POST)
	  public @ResponseBody String modifierMatiereArticle(@RequestBody MatiereArticleValue pMatiereArticleValue) {
	    return this.ebMatiereService.modifierMatiere(pMatiereArticleValue);
	  }
	  
	  /**********************  Méthode de Suppression  **********************/
	  @RequestMapping(value = "/supprimerMatiereArticle:{id}", method = RequestMethod.DELETE)
	  public void supprimerMatiereArticle(@PathVariable("id") String id) {
	   
	   Long idSupp= Long.parseLong(id);
	   ebMatiereService.supprimerMatiere(Long.valueOf(idSupp));
	  }
	
}
