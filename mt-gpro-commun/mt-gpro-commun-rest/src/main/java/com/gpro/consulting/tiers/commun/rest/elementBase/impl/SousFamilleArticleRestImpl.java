package com.gpro.consulting.tiers.commun.rest.elementBase.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.SousFamilleArticleValue;
import com.gpro.consulting.tiers.commun.service.elementBase.ISousFamilleArticleService;

@Controller
@RequestMapping("/sousFamilleArticle")
public class SousFamilleArticleRestImpl {
	
	@Autowired
	ISousFamilleArticleService sousFamilleArticleService ;
	
	/*************get sous famille By ID*************/
	@RequestMapping(value = "/getId:{id}", method = RequestMethod.GET)
	public @ResponseBody SousFamilleArticleValue getSousFamilleArtcile(@PathVariable Long id) {
		return  sousFamilleArticleService.rechercheSousFamilleArticleById(id);
	}

	@RequestMapping(value = "/string", method = RequestMethod.GET)
    public @ResponseBody String testStringPersonne(){
         return "test";
    }
	
	/**********all sous famille article***********/
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<SousFamilleArticleValue> viewAllSousFamille(){
		return sousFamilleArticleService.listeSousFamilleArticle();
    }
	 /**********************  Méthode de Creation d'une SousFamilleArticle **********************/
	  @RequestMapping(value = "/creerSousFamilleArticle", method = RequestMethod.POST)
	  public @ResponseBody String creerSousFamilleArticle(@RequestBody SousFamilleArticleValue pSousFamilleArticleValue) {
	    return this.sousFamilleArticleService.creerSousFamilleArticle(pSousFamilleArticleValue);
	  }

	  /**********************  Méthode de modification d'une SousFamilleArticle **********************/
	  @RequestMapping(value = "/modifierSousFamilleArticle", method = RequestMethod.POST)
	  public @ResponseBody String modifierSousFamilleArticle(@RequestBody SousFamilleArticleValue pSousFamilleArticleValue) {
	    return this.sousFamilleArticleService.modifierSousFamilleArticle(pSousFamilleArticleValue);
	  }
	  
	  /**********************  Méthode de Suppression d'une SousFamilleArticle **********************/
	  @RequestMapping(value = "/supprimerSousFamilleArticle:{id}", method = RequestMethod.DELETE)
	  public void supprimerSousFamilleArticle(@PathVariable("id") String id) {
	   
	   Long idSupp= Long.parseLong(id);
	   sousFamilleArticleService.supprimerSousFamilleArticle(Long.valueOf(idSupp));
	  }
	  
	
	/*******getter  sousFamilleArticleService*****/
	public ISousFamilleArticleService getSousFamilleArticleService() {
		return sousFamilleArticleService;
	}

	/*******setter  sousFamilleArticleService*****/
	public void setSousFamilleArticleService(
			ISousFamilleArticleService sousFamilleArticleService) {
		this.sousFamilleArticleService = sousFamilleArticleService;
	}
	

	
	
}
