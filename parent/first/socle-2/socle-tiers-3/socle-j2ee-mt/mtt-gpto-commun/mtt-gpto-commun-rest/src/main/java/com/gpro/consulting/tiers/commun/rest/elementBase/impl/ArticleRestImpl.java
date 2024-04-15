package com.gpro.consulting.tiers.commun.rest.elementBase.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ArticleCacheValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RecherecheMulticritereArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ResultatRechecheArticleValue;
import com.gpro.consulting.tiers.commun.rest.elementBase.IArticleRest;
import com.gpro.consulting.tiers.commun.service.cache.IGestionnaireCacheService;
import com.gpro.consulting.tiers.commun.service.elementBase.IArticleService;

/**
 * 
 * @author $Author: Ghazi $
 * @version $Revision: 0 $
 */
@Controller
@RequestMapping("/article")
public class ArticleRestImpl implements IArticleRest {

  @Autowired
  private IArticleService articleService;
  
  /** Gestionnaire de Cache Service */
	@Autowired
	private IGestionnaireCacheService gestionnaireCacheService;
	  

  /**
   * Constructeur de la classe.
   */
  public ArticleRestImpl() {
    // Constructeur vide
  }

  @RequestMapping(value = "/string", method = RequestMethod.GET)
  public @ResponseBody String testStringPersonne() {

    return "Test";
  }
  
  /*************get Article By ID*************/
	@RequestMapping(value = "/getId:{id}", method = RequestMethod.GET, produces =  "application/json")
	public @ResponseBody ArticleValue getArticle(@PathVariable Long id) {
		ArticleValue pArticleValue=new ArticleValue();
		pArticleValue.setId(id);
		return  articleService.rechercheArticleParId(pArticleValue);
	}

  /******************************* All article *********************************/ 
  @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
  public @ResponseBody List < ArticleValue > viewAllArticle() {
	  List < ArticleValue > vArticleValue = articleService.listeArticle();
	//Traitement : transformation de l'Id a sa propre Designation

      for(ArticleValue article : vArticleValue){
		 //SousFamille, Famille, Type
    	  Map<String, String> mapA = gestionnaireCacheService.rechercherArticleParId(article.getSousFamilleArtEntite(),article.getSiteEntite());

		  article.setSousFamilleArtEntiteDesignation(mapA.get("sousFamille"));
		  article.setFamilleArticleDesignation(mapA.get("famille"));
		  article.setTypeArticleDesignation(mapA.get("type"));
		  
		  //Site
		  article.setSiteEntiteDesignation(mapA.get("site"));

	    }
    return vArticleValue;
  }
  
  
  /******************************* All article cache*********************************/ 
  @RequestMapping(value = "/articleCache", method = RequestMethod.GET, produces = "application/json")
  public @ResponseBody List < ArticleCacheValue > viewArticleCache() {
	 return articleService.listeArticleCache();
  }
  
  /******************** Méthode de recherche multicritères **********************/
  @RequestMapping(value = "/rechercheArticleMulticritere", method = RequestMethod.POST)
  public @ResponseBody ResultatRechecheArticleValue rechercherArticleMultiCritere(
    @RequestBody final RecherecheMulticritereArticleValue pRechercheMultiCritere) {
   
	  ResultatRechecheArticleValue vResultatRecherche = articleService.rechercherArticleMultiCritere(pRechercheMultiCritere);
    //Traitement : transformation de l'Id sousFamille a sa propre Designation pour chaque Article
    
    for(ArticleValue article : vResultatRecherche.getArticleValues()){
    	
    	Map<String, String> mapA = gestionnaireCacheService.rechercherArticleParId(article.getSousFamilleArtEntite(), article.getSiteEntite());
    	//SousFamille, Famille, Type
    	article.setSousFamilleArtEntiteDesignation(mapA.get("sousFamille"));
		article.setFamilleArticleDesignation(mapA.get("famille"));
		article.setTypeArticleDesignation(mapA.get("type"));
		  
		//Site
		article.setSiteEntiteDesignation(mapA.get("site"));
		
	
		
    }
    return vResultatRecherche;
  }
  
  /**********************  Méthode de Creation d'un Article **********************/
  @RequestMapping(value = "/creerArticle", method = RequestMethod.POST)
  public @ResponseBody String creerArticle(@RequestBody ArticleValue pArticleValue) {
    return this.articleService.creerArticle(pArticleValue);
  }

  /**********************  Méthode de modification d'un Article **********************/
  @RequestMapping(value = "/modifierArticle", method = RequestMethod.POST)
  public @ResponseBody String modifierArticle(@RequestBody ArticleValue pArticleValue) {
    return this.articleService.modifierArticle(pArticleValue);
  }
  
  /**********************  Méthode de Suppression d'un Article **********************/
  @RequestMapping(value = "/supprimerArticle:{id}", method = RequestMethod.DELETE)
  public void supprimerArticle(@PathVariable("id") String id) {
   
   Long idSupp= Long.parseLong(id);
   articleService.supprimerArticle(Long.valueOf(idSupp));
  }
  
  
  
  @RequestMapping(value = "/rechercheArticleMulticritereClient", method = RequestMethod.POST)
  public @ResponseBody ResultatRechecheArticleValue rechercherArticleMultiCritereClient(
    @RequestBody final RecherecheMulticritereArticleValue pRechercheMultiCritere) {
   
	  ResultatRechecheArticleValue vResultatRecherche = articleService.rechercherArticleMultiCritereClient(pRechercheMultiCritere);
    //Traitement : transformation de l'Id sousFamille a sa propre Designation pour chaque Article
    
    for(ArticleValue article : vResultatRecherche.getArticleValues()){
    	
    	Map<String, String> mapA = gestionnaireCacheService.rechercherArticleParId(article.getSousFamilleArtEntite(), article.getSiteEntite());
    	//SousFamille, Famille, Type
    	article.setSousFamilleArtEntiteDesignation(mapA.get("sousFamille"));
		article.setFamilleArticleDesignation(mapA.get("famille"));
		article.setTypeArticleDesignation(mapA.get("type"));
		  
		//Site
		article.setSiteEntiteDesignation(mapA.get("site"));
		
	
		
    }
    return vResultatRecherche;
  }
  
  
  
  
}
