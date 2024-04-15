package com.gpro.consulting.tiers.commun.rest.elementBase.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.FamilleArticleValue;
import com.gpro.consulting.tiers.commun.service.elementBase.IFamilleArticleService;


@Controller
@RequestMapping("/familleArticle")
public class FamilleArticleRestImpl {
	@Autowired
	IFamilleArticleService familleArticleService ;
	
	/*************get famille By ID*************/
	@RequestMapping(value = "/getId:{id}", method = RequestMethod.GET)
	public @ResponseBody FamilleArticleValue getFamilleArtcile(@PathVariable Long id) {
		return  familleArticleService.rechercheFamilleArticleById(id);
	}

	@RequestMapping(value = "/string", method = RequestMethod.GET)
    public @ResponseBody String testStringPersonne(){
         return "test";
    }
	
	/**********all famille article***********/
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<FamilleArticleValue> viewAllFamille(){
		return familleArticleService.listeFamilleArticle();
    }

	/********** Creer FamilleArticle***********/
	 @RequestMapping(value = "/creerFamilleArticle", method = RequestMethod.POST)
	  public @ResponseBody String creerFamilleArticle(@RequestBody FamilleArticleValue pFamilleArticleArticleValue) {
	      //transformation et transcodage des referenciels 
		  return familleArticleService.creerFamilleArticle(pFamilleArticleArticleValue);
	  }

	 /********** Modifier FamilleArticle***********/
	  @RequestMapping(value = "/modifierFamilleArticle", method = RequestMethod.POST)
	  public @ResponseBody String modifierFamilleArticle(@RequestBody FamilleArticleValue pFamilleArticleArticleValue) {
	    return this.familleArticleService.modifierFamilleArticle(pFamilleArticleArticleValue);
	  }

	  /********** Supprimer FamilleArticle***********/
	  @RequestMapping(value = "/supprimerFamilleArticle:{id}", method = RequestMethod.DELETE)
	  public void supprimerFamilleArticle(@PathVariable("id") String id) {
		  Long idSupp= Long.parseLong(id);
		 
		  familleArticleService.supprimerFamilleArticle(idSupp);

	  }
}
