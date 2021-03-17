package com.gpro.consulting.tiers.commun.rest.elementBase.impl;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticritereProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ResultatRechecheProduitValue;
import com.gpro.consulting.tiers.commun.service.cache.IGestionnaireCacheService;
import com.gpro.consulting.tiers.commun.service.cache.impl.GestionnaireCacheServiceImpl;
import com.gpro.consulting.tiers.commun.service.elementBase.IProduitService;

@Controller
@RequestMapping("/produit")
public class ProduitRestImpl {
	@Autowired
	IProduitService produitService;

	/** Logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(GestionnaireCacheServiceImpl.class);

	/** Gestionnaire de Cache Service */
	@Autowired
	private IGestionnaireCacheService gestionnaireCacheService;
	  
	
	//conctructeur
	public ProduitRestImpl(){
		
	}
	 @RequestMapping(value = "/string", method = RequestMethod.GET)
	  public @ResponseBody String testStringProduit() {
	    return "Test";
	  }

	  // liste produit 
	  @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	  public @ResponseBody List < ProduitValue > viewAllProduit() {
		  List < ProduitValue > vProduitValue = produitService.listeProduit();
          //Traitement : transformation de l'Id a sa propre Designation
            for(ProduitValue produit : vProduitValue){
      		 //SousFamille, Famille
          	  Map<String, String> mapA = gestionnaireCacheService.rechercherProduitParId(produit.getSousFamilleId(),produit.getSiteId(), produit.getPartieIntersseId());
      		  produit.setSousFamilleDesignation(mapA.get("sousFamille"));
      		  produit.setFamilleDesignation(mapA.get("famille"));
      		  //Site
      		  produit.setSiteEntiteDesignation(mapA.get("site"));
      		  //partieInteressee
      		  produit.setPartieIntersseDesignation(mapA.get("partieInteressee"));
      		  
      		
      	    }
            return vProduitValue;
	  }
       // recherche produit by id
	  @RequestMapping(value = "/getId:{id}", method = RequestMethod.GET)
		public @ResponseBody ProduitValue getProduit(@PathVariable Long id) {
		
		  ProduitValue vProduitRecherche =  produitService.rechercheProduitById(id);
		 
		  //Traitement : transformation de l'Id a sa propre Designation
		  Map<String, String> mapA = gestionnaireCacheService.rechercherProduitParId(vProduitRecherche.getSousFamilleId(), vProduitRecherche.getSiteId(), vProduitRecherche.getPartieIntersseId());

		  //sousFamilleProduit
		  vProduitRecherche.setSousFamilleDesignation(mapA.get("sousFamille"));
		  //Famille
		  vProduitRecherche.setFamilleDesignation(mapA.get("famille"));
		  //Site
		  vProduitRecherche.setSiteEntiteDesignation(mapA.get("site"));
		  //partieInteressee
		  vProduitRecherche.setPartieIntersseDesignation(mapA.get("partieInteressee"));
		  
		  return vProduitRecherche;
		}
	  
	  
	  @RequestMapping(value = "/rechercheProduitMulticritere", method = RequestMethod.POST)
	  public @ResponseBody ResultatRechecheProduitValue rechercherProduitMultiCritere(
	    @RequestBody final RechercheMulticritereProduitValue pRechercheMultiCritere) {
	    // Création des critères de recherche
		//LOGGER.info("list des produits par Recherche MultiCritères /rechercheProduitMulticritere"+pRechercheMultiCritere.getDesignation());
		ResultatRechecheProduitValue vResultatRecherche = produitService.rechercherProduitMultiCritere(pRechercheMultiCritere);
	  
        //Traitement : transformation de l'Id a sa propre Designation
          for(ProduitValue produit : vResultatRecherche.getProduitValues()){
    		 //SousFamille, Famille
        	  Map<String, String> mapA = gestionnaireCacheService.rechercherProduitParId(produit.getSousFamilleId(), produit.getSiteId(), produit.getPartieIntersseId());

    		  produit.setSousFamilleDesignation(mapA.get("sousFamille"));
    		  
    		  produit.setFamilleDesignation(mapA.get("famille"));
    		  produit.setSuperFamilleDesignation(mapA.get("superFamille"));
    		  //Site
    		  produit.setSiteEntiteDesignation(mapA.get("site"));
    		  //partieInteressee
      		  produit.setPartieIntersseDesignation(mapA.get("partieInteressee"));
      		  
    	    }
	    return vResultatRecherche;
	  }

	  
	  
	  //rech multi client
	  @RequestMapping(value = "/rechercheProduitMulticritereClient", method = RequestMethod.POST)
	  public @ResponseBody ResultatRechecheProduitValue rechercherProduitMultiCritereClient(
	    @RequestBody final RechercheMulticritereProduitValue pRechercheMultiCritere) {
	    // Création des critères de recherche
		//LOGGER.info("list des produits par Recherche MultiCritères /rechercheProduitMulticritere"+pRechercheMultiCritere.getDesignation());
		ResultatRechecheProduitValue vResultatRecherche = produitService.rechercherProduitMultiCritereClient(pRechercheMultiCritere);
	  
        //Traitement : transformation de l'Id a sa propre Designation
          for(ProduitValue produit : vResultatRecherche.getProduitValues()){
    		 //SousFamille, Famille
        	  Map<String, String> mapA = gestionnaireCacheService.rechercherProduitParId(produit.getSousFamilleId(), produit.getSiteId(), produit.getPartieIntersseId());

    		  produit.setSousFamilleDesignation(mapA.get("sousFamille"));
    		  produit.setFamilleDesignation(mapA.get("famille"));
    		  //Site
    		  produit.setSiteEntiteDesignation(mapA.get("site"));
    		  //partieInteressee
      		  produit.setPartieIntersseDesignation(mapA.get("partieInteressee"));
      		  
    	    }
	    return vResultatRecherche;
	  }

	  
	  //
	  @RequestMapping(value = "/creerProduit", method = RequestMethod.POST)
	  public @ResponseBody String creerProduit(@RequestBody ProduitValue pProduitValue) {
	      //transformation et transcodage des referenciel 
		  
		  return this.produitService.creerProduit(pProduitValue);
	  }

	 
	  @RequestMapping(value = "/modifierProduit", method = RequestMethod.POST)
	  public @ResponseBody String modifierProduit(@RequestBody ProduitValue pProduitValue) {
	    return this.produitService.modifierProduit(pProduitValue);
	  }

	  @RequestMapping(value = "/supprimerProduit:{id}", method = RequestMethod.DELETE)
	  public @ResponseBody String supprimerProduit(@PathVariable("id") String id) {
		  if(LOGGER.isDebugEnabled()){
				//LOGGER.debug(" id  produit  : =" + id );
			}
		  //LOGGER.info(""+id);
		  Long idSupp= Long.parseLong(id);
		  produitService.supprimerProduit(Long.valueOf(idSupp));
		  
		  
		  return "OK";

	  }
	  
	  
	  @RequestMapping(value = "/all/retour", method = RequestMethod.GET, produces = "application/json")
	  public @ResponseBody List < ProduitValue > viewAllProduitFinancier() {
		  List < ProduitValue > vProduitValue = produitService.rechercheProduitFinance();
          //Traitement : transformation de l'Id a sa propre Designation
            for(ProduitValue produit : vProduitValue){
      		 //SousFamille, Famille
          	  Map<String, String> mapA = gestionnaireCacheService.rechercherProduitParId(produit.getSousFamilleId(),produit.getSiteId(), produit.getPartieIntersseId());
      		  produit.setSousFamilleDesignation(mapA.get("sousFamille"));
      		  produit.setFamilleDesignation(mapA.get("famille"));
      		  //Site
      		  produit.setSiteEntiteDesignation(mapA.get("site"));
      		  //partieInteressee
      		  produit.setPartieIntersseDesignation(mapA.get("partieInteressee"));
      		  
      		
      	    }
            return vProduitValue;
	  }
	  
	  @RequestMapping(value = "/all/non-retour", method = RequestMethod.GET, produces = "application/json")
	  public @ResponseBody List < ProduitValue > viewAllProduitNonFinancier() {
		  List < ProduitValue > vProduitValue = produitService.rechercheProduitNonFinance();
          //Traitement : transformation de l'Id a sa propre Designation
            for(ProduitValue produit : vProduitValue){
      		 //SousFamille, Famille
          	  Map<String, String> mapA = gestionnaireCacheService.rechercherProduitParId(produit.getSousFamilleId(),produit.getSiteId(), produit.getPartieIntersseId());
      		  produit.setSousFamilleDesignation(mapA.get("sousFamille"));
      		  produit.setFamilleDesignation(mapA.get("famille"));
      		  //Site
      		  produit.setSiteEntiteDesignation(mapA.get("site"));
      		  //partieInteressee
      		  produit.setPartieIntersseDesignation(mapA.get("partieInteressee"));
      		  
      		
      	    }
            return vProduitValue;
	  }
	  
	  
  
}
