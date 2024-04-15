package com.gpro.consulting.tiers.commun.rest.elementBase.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.PrixClientValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RecherchePrixClientValue;
import com.gpro.consulting.tiers.commun.rest.elementBase.IPrixClientRest;
import com.gpro.consulting.tiers.commun.service.cache.IGestionnaireCacheService;
import com.gpro.consulting.tiers.commun.service.elementBase.IPrixClientService;

/**
 * 
 * @author $Author: Ghazi $
 * @version $Revision: 0 $
 */
@Controller
@RequestMapping("/prixClient")
public class PrixClientRestImpl implements IPrixClientRest {

  @Autowired
  private IPrixClientService prixclientService;
  
  /** Gestionnaire de Cache Service */
	@Autowired
	private IGestionnaireCacheService gestionnaireCacheService;
	  

  /**
   * Constructeur de la classe.
   */
  public PrixClientRestImpl() {
    // Constructeur vide
  }

  @RequestMapping(value = "/string", method = RequestMethod.GET)
  public @ResponseBody String testStringPersonne() {

    return "Test";
  }
  
  /*************get Article By ID*************/
	@RequestMapping(value = "/getId:{id}", method = RequestMethod.GET, produces =  "application/json")
	public @ResponseBody PrixClientValue getPrixClient(@PathVariable Long id) {
		PrixClientValue pPrixClientValue=new PrixClientValue();
		pPrixClientValue.setId(id);
		return  prixclientService.recherchePrixClientParId(id);
	}

  /******************************* Get Prix Client Par produit*********************************/
	//@RequestMapping(value="/inventaire", method = RequestMethod.GET)
	//public void genererInventaireReport(
			
	@RequestMapping(value = "/PrixClientProduit", method = RequestMethod.POST)
	public @ResponseBody PrixClientValue getPrixClientProduit(
			 @RequestBody final RecherchePrixClientValue pRecherchePrixClientValue
			
			) {
		RecherchePrixClientValue pPrixClientValue=new RecherchePrixClientValue();
		pPrixClientValue.setIdClient(pRecherchePrixClientValue.getIdClient());
		pPrixClientValue.setIdProduit(pRecherchePrixClientValue.getIdProduit());
		
		//System.out.println("idClient"+pRecherchePrixClientValue.getIdClient()+"---idProduit:"+pRecherchePrixClientValue.getIdProduit());
		//System.out.println("objet retourne: "+ prixclientService.rechercherPrixClientValue(pRecherchePrixClientValue).getId());
		return  prixclientService.rechercherPrixClientValue(pRecherchePrixClientValue);
		//return null;
	}
	
	
	@RequestMapping(value = "/ListPrixClientProduit", method = RequestMethod.POST)
	public @ResponseBody List<PrixClientValue> getListPrixClientProduit(
			 @RequestBody final RecherchePrixClientValue pRecherchePrixClientValue
			
			) {
		RecherchePrixClientValue pPrixClientValue=new RecherchePrixClientValue();
		pPrixClientValue.setIdClient(pRecherchePrixClientValue.getIdClient());
		pPrixClientValue.setIdProduit(pRecherchePrixClientValue.getIdProduit());
		
		//System.out.println("idClient"+pRecherchePrixClientValue.getIdClient()+"---idProduit:"+pRecherchePrixClientValue.getIdProduit());
		//System.out.println("objet retourne: "+ prixclientService.rechercherPrixClientValue(pRecherchePrixClientValue).getId());
		return  prixclientService.rechchercheMultiCriterePrixClient(pRecherchePrixClientValue);
		//return null;
	}
	
	/***************************/
  
  @RequestMapping(value = "/PrixClientProduit", method = RequestMethod.GET, produces = "application/json")
  public @ResponseBody List < PrixClientValue > viewPrixClientPooduit() {
	  List < PrixClientValue > vPrixClientValue = prixclientService.listePrixClient();
	
    return vPrixClientValue;
  }
  
  
  /******************************* All article cache*********************************/ 
 /* @RequestMapping(value = "/articleCache", method = RequestMethod.GET, produces = "application/json")
  public @ResponseBody List < ArticleCacheValue > viewArticleCache() {
	 return articleService.listeArticleCache();
  }*/
  
  /******************** Méthode de recherche multicritères **********************/
 /* @RequestMapping(value = "/recherchePrixClientMulticritere", method = RequestMethod.POST)
  public @ResponseBody ResultatRechechePrixClientValue rechercherPrixClientMultiCritere(
    @RequestBody final RecherecheMulticriterePrixClientValue pRechercheMultiCritere) {
   
	  ResultatRechechePrixClientValue vResultatRecherche = prixclientService.rechercherPrixClientMultiCritere(pRechercheMultiCritere);
    //Traitement : transformation de l'Id sousFamille a sa propre Designation pour chaque Article
    
    /*for(ArticleValue article : vResultatRecherche.getArticleValues()){
    	
    	Map<String, String> mapA = gestionnaireCacheService.rechercherArticleParId(article.getSousFamilleArtEntite(), article.getSiteEntite());
    	//SousFamille, Famille, Type
    	article.setSousFamilleArtEntiteDesignation(mapA.get("sousFamille"));
		article.setFamilleArticleDesignation(mapA.get("famille"));
		article.setTypeArticleDesignation(mapA.get("type"));
		  
		//Site
		article.setSiteEntiteDesignation(mapA.get("site"));
    }
    return vResultatRecherche;
  }*/
  
  /**********************  Méthode de Creation d'un PrixClient **********************/
  /*@RequestMapping(value = "/creerPrixClient", method = RequestMethod.POST)
  public @ResponseBody String creerPrixClient(@RequestBody PrixClientValue pPrixClientValue) {
    return this.prixclientService.creerPrixClient(pPrixClientValue);
  }*/
  
  
  @RequestMapping(value = "/creerPrixClient", method = RequestMethod.POST)
  public @ResponseBody String creerPrixClient(@RequestBody List <ProduitValue> pProduitValue ) {
	  //System.out.println(" inn create prix client id"+id);
	//  System.out.println("list: "+pProduitValue.size());
	  
	  
	//  System.out.println("######   ID Client :  "+pProduitValue.get(0).getPartieIntersseId());
	//  System.out.println("######   Prix Client :  "+pProduitValue.get(0).getPrixSpecial());
	  //return null;
    return this.prixclientService.creerPrixClient(pProduitValue);
  }

  /**********************  Méthode de modification d'un PrixClient **********************/
  @RequestMapping(value = "/modifierPrixClient", method = RequestMethod.POST)
  public @ResponseBody String modifierPrixClient(@RequestBody PrixClientValue pPrixClientValue) {
    return this.prixclientService.modifierPrixClient(pPrixClientValue);
  }
  
  /**********************  Méthode de Suppression d'un PrixClient **********************/
  @RequestMapping(value = "/supprimerPrixClient:{id}", method = RequestMethod.DELETE)
  public void supprimerPrixClient(@PathVariable("id") String id) {
   
   Long idSupp= Long.parseLong(id);
   prixclientService.supprimerPrixClient(Long.valueOf(idSupp));
  }
  
  
  
  
  @RequestMapping(value = "/creerPrixArticleClient", method = RequestMethod.POST)
  public @ResponseBody String creerPrixArticleClient(@RequestBody List <ArticleValue> pProduitValue ) {
	  //System.out.println(" inn create prix client id"+id);
	//  System.out.println("list: "+pProduitValue.size());
	  
	  
	//  System.out.println("######   ID Client :  "+pProduitValue.get(0).getPartieIntersseId());
	//  System.out.println("######   Prix Client :  "+pProduitValue.get(0).getPrixSpecial());
	  //return null;
    return this.prixclientService.creerPrixArticleClient(pProduitValue);
  }
  
}
