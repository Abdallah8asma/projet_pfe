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

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitSerialisableValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticritereProduitSerialisableValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ResultatRechecheProduitSerialisableValue;
import com.gpro.consulting.tiers.commun.service.cache.IGestionnaireCacheService;
import com.gpro.consulting.tiers.commun.service.cache.impl.GestionnaireCacheServiceImpl;
import com.gpro.consulting.tiers.commun.service.elementBase.IProduitSerialisableService;
import com.gpro.consulting.tiers.commun.service.elementBase.IProduitService;
import com.gpro.consulting.tiers.commun.service.partieInteressee.IPartieInteresseeService;

@Controller
@RequestMapping("/produitSerialisable")
public class ProduitSerialisableRestImpl {
	@Autowired
	IProduitSerialisableService produitSerialisableService;
	
	@Autowired
	IProduitService produitService;

	/** Logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(GestionnaireCacheServiceImpl.class);

	/** Gestionnaire de Cache Service */
	@Autowired
	private IGestionnaireCacheService gestionnaireCacheService;
	  
	@Autowired
    private IPartieInteresseeService  partieInteresseeService ;
	
	//conctructeur
	public ProduitSerialisableRestImpl(){
		
	}


	  // liste produitSerialisable 
	  @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	  public @ResponseBody List < ProduitSerialisableValue > viewAllProduitSerialisable() {
		  List < ProduitSerialisableValue > vProduitSerialisableValue = produitSerialisableService.listeProduitSerialisable();
          //Traitement : transformation de l'Id a sa propre Designation
            for(ProduitSerialisableValue produitSerialisable : vProduitSerialisableValue){
      		 //SousFamille, Famille
            	
            	ProduitValue produit = produitService.rechercheProduitById(produitSerialisable.getProduitId());
          	
            	produitSerialisable.setReference(produit.getReference());
            	produitSerialisable.setDesignation(produit.getDesignation());
            	
            	Map<String, String> mapA = gestionnaireCacheService.rechercherProduitParId(produit.getSousFamilleId(),produit.getSiteId(), produit.getPartieIntersseId());
      		  produitSerialisable.setSousFamilleDesignation(mapA.get("sousFamille"));
      		  produitSerialisable.setFamilleDesignation(mapA.get("famille"));
      		  //Site
      		  produitSerialisable.setSiteEntiteDesignation(mapA.get("site"));
      		  //partieInteressee
      		  produitSerialisable.setPartieIntersseDesignation(mapA.get("partieInteressee"));
      		  
      		  if (produitSerialisable.getFournisseurId() != null)
      			produitSerialisable.setFournisseurAbreviation(partieInteresseeService.getById(produitSerialisable.getFournisseurId()).getAbreviation());
        		  
    		  
      		  
      	
      		  
      		
      	    }
            return vProduitSerialisableValue;
	  }
       // recherche produitSerialisable by id
	  @RequestMapping(value = "/getId:{id}", method = RequestMethod.GET)
		public @ResponseBody ProduitSerialisableValue getProduitSerialisable(@PathVariable Long id) {
		
		  ProduitSerialisableValue vProduitSerialisableRecherche =  produitSerialisableService.rechercheProduitSerialisableById(id);
		 
		 	ProduitValue produit = produitService.rechercheProduitById(vProduitSerialisableRecherche.getProduitId());
		  //Traitement : transformation de l'Id a sa propre Designation
		  Map<String, String> mapA = gestionnaireCacheService.rechercherProduitParId(produit.getSousFamilleId(), produit.getSiteId(), produit.getPartieIntersseId());

		  vProduitSerialisableRecherche.setReference(produit.getReference());
		  vProduitSerialisableRecherche.setDesignation(produit.getDesignation());
		  //sousFamilleProduitSerialisable
		  vProduitSerialisableRecherche.setSousFamilleDesignation(mapA.get("sousFamille"));
		  //Famille
		  vProduitSerialisableRecherche.setFamilleDesignation(mapA.get("famille"));
		  //Site
		  vProduitSerialisableRecherche.setSiteEntiteDesignation(mapA.get("site"));
		  //partieInteressee
		  vProduitSerialisableRecherche.setPartieIntersseDesignation(mapA.get("partieInteressee"));
		  
		  if (vProduitSerialisableRecherche.getFournisseurId() != null)
			  vProduitSerialisableRecherche.setFournisseurAbreviation(partieInteresseeService.getById(vProduitSerialisableRecherche.getFournisseurId()).getAbreviation());
    		  
		  
		  return vProduitSerialisableRecherche;
		}
	  
	  
	  @RequestMapping(value = "/rechercheProduitSerialisableMulticritere", method = RequestMethod.POST)
	  public @ResponseBody ResultatRechecheProduitSerialisableValue rechercherProduitSerialisableMultiCritere(
	    @RequestBody final RechercheMulticritereProduitSerialisableValue pRechercheMultiCritere) {
	    // Création des critères de recherche
	//	LOGGER.info("list des produitSerialisables par Recherche MultiCritères /rechercheProduitSerialisableMulticritere"+pRechercheMultiCritere.getDesignation());
		ResultatRechecheProduitSerialisableValue vResultatRecherche = produitSerialisableService.rechercherProduitSerialisableMultiCritere(pRechercheMultiCritere);
	  
        //Traitement : transformation de l'Id a sa propre Designation
          for(ProduitSerialisableValue produitSerialisable : vResultatRecherche.getProduitSerialisableValues()){
        	  
        	 	ProduitValue produit = produitService.rechercheProduitById(produitSerialisable.getProduitId());
        	 	
        	 	produitSerialisable.setReference(produit.getReference());
            	produitSerialisable.setDesignation(produit.getDesignation());
        		
    		 //SousFamille, Famille
        	  Map<String, String> mapA = gestionnaireCacheService.rechercherProduitParId(produit.getSousFamilleId(), produit.getSiteId(), produitSerialisable.getClientId());

    		  produitSerialisable.setSousFamilleDesignation(mapA.get("sousFamille"));
    		  produitSerialisable.setFamilleDesignation(mapA.get("famille"));
    		  //Site
    		  produitSerialisable.setSiteEntiteDesignation(mapA.get("site"));
    		  //partieInteressee
      		  produitSerialisable.setPartieIntersseDesignation(mapA.get("partieInteressee"));
      		  
      		  if (produitSerialisable.getFournisseurId() != null)
      			produitSerialisable.setFournisseurAbreviation(partieInteresseeService.getById(produitSerialisable.getFournisseurId()).getAbreviation());
      		  
    	    }
	    return vResultatRecherche;
	  }

	  


	  
	  //
	  @RequestMapping(value = "/creerProduitSerialisable", method = RequestMethod.POST)
	  public @ResponseBody String creerProduitSerialisable(@RequestBody ProduitSerialisableValue pProduitSerialisableValue) {
	      //transformation et transcodage des referenciel 
		  
		  return this.produitSerialisableService.creerProduitSerialisable(pProduitSerialisableValue);
	  }

	 
	  @RequestMapping(value = "/modifierProduitSerialisable", method = RequestMethod.POST)
	  public @ResponseBody String modifierProduitSerialisable(@RequestBody ProduitSerialisableValue pProduitSerialisableValue) {
	    return this.produitSerialisableService.modifierProduitSerialisable(pProduitSerialisableValue);
	  }

	  @RequestMapping(value = "/supprimerProduitSerialisable:{id}", method = RequestMethod.DELETE)
	  public void supprimerProduitSerialisable(@PathVariable("id") String id) {
		  if(LOGGER.isDebugEnabled()){
				//LOGGER.debug(" id  produitSerialisable  : =" + id );
			}
		  //LOGGER.info(""+id);
		  Long idSupp= Long.parseLong(id);
		  produitSerialisableService.supprimerProduitSerialisable(Long.valueOf(idSupp));

	  }
  
}
