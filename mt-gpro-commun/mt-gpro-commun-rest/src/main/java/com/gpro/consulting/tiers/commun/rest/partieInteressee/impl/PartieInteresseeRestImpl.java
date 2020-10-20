package com.gpro.consulting.tiers.commun.rest.partieInteressee.impl;

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

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.GroupeClientValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseCacheValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.RechercheMulticriterePartieInteresseeValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.ResultatRechechePartieInteresseeValue;
import com.gpro.consulting.tiers.commun.rest.partieInteressee.IPartieInteresseeRest;
import com.gpro.consulting.tiers.commun.service.cache.IGestionnaireCacheService;
import com.gpro.consulting.tiers.commun.service.partieInteressee.IGroupeClientService;
import com.gpro.consulting.tiers.commun.service.partieInteressee.IPartieInteresseeService;

/**
 * 
 * @author $Author: DELL $
 * @version $Revision: 0 $
 */
@Controller
@RequestMapping("/partieInteressee")
public class PartieInteresseeRestImpl implements IPartieInteresseeRest {

  @Autowired
  private IPartieInteresseeService partieInteresseeService;

  
  @Autowired
  private IGroupeClientService groupeClientService;
  
  
  @Autowired
  private IGestionnaireCacheService gestionnaireCacheService;
	private static final Logger LOGGER = LoggerFactory.getLogger(PartieInteresseeRestImpl.class);

  
  /**
   * Constructeur de la classe.
   */
  public PartieInteresseeRestImpl() {
    // Constructeur vide
  }
  /*************get Partie Interessee By ID*************/
	@RequestMapping(value = "/getId:{id}", method = RequestMethod.GET, produces =  "application/json")
	public @ResponseBody PartieInteresseValue getInteresseValue(@PathVariable Long id) {
		PartieInteresseValue pPartieInteresseValue=new PartieInteresseValue();
		pPartieInteresseValue.setId(id.longValue());
		return  partieInteresseeService.recherchePartieInteresseeParId(pPartieInteresseValue);
	}
	

/*************get Partie Interessee By ID*************/
	@RequestMapping(value = "/listePiCache", method = RequestMethod.GET, produces =  "application/json")
	public @ResponseBody List< PartieInteresseCacheValue> getPartieInteresseValue() {
		//LOGGER.info("this pi");
		return  partieInteresseeService.listePartieInteresseeCache();
	}
	
	
  /**
   * Méthode permettant de retourner une liste des Parties Interessées
   * 
   * @return
   */
  @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
  public @ResponseBody List < PartieInteresseValue > viewAllPartieInteressee() {
	 
	  List < PartieInteresseValue > vPiValue = partieInteresseeService.listePartieInteressee();
	  for(PartieInteresseValue partieInteressee : vPiValue){
			 //Famille, Categorie, Type PI
	    	  Map<String, String> mapA = gestionnaireCacheService.rechercherPartieInteresseeParId(partieInteressee.getFamillePartieInteressee(), partieInteressee.getCategoriePartieInteressee(), partieInteressee.getTypePartieInteressee() );
	    	  partieInteressee.setFamillePartieInteresseeDesignation(mapA.get("famillePi"));
	    	  partieInteressee.setCategoriePartieInteresseeDesignation(mapA.get("categoriePi"));
	    	  partieInteressee.setTypePartieInteresseeDesignation(mapA.get("typePi"));
	  }

    return vPiValue; 
  }

  /**
   * Méthode permettant la recherche multicritére d'une partie interessée
   * 
   * @param pRecherchePartieInteresseMulitCritere
   * @return
   */

  @RequestMapping(value = "/recherchePIMulticritere", method = RequestMethod.POST)
  public @ResponseBody ResultatRechechePartieInteresseeValue rechercherPartieInteresseMultiCritere(
    @RequestBody final RechercheMulticriterePartieInteresseeValue pRechercheMultiCritere) {
  
	// Création des critères de recherche
    ResultatRechechePartieInteresseeValue vResultatRecherche = partieInteresseeService
      .rechercherPartieInteresseMultiCritere(pRechercheMultiCritere);
    
    for(PartieInteresseValue partieInteressee : vResultatRecherche.getPartieInteresseValues()){
	  //Famille, Categorie, Type PI
   	  Map<String, String> mapA = gestionnaireCacheService.rechercherPartieInteresseeParId(partieInteressee.getFamillePartieInteressee(), partieInteressee.getCategoriePartieInteressee(), partieInteressee.getTypePartieInteressee() );

   	  partieInteressee.setFamillePartieInteresseeDesignation(mapA.get("famillePi"));
   	  partieInteressee.setCategoriePartieInteresseeDesignation(mapA.get("categoriePi"));
   	  partieInteressee.setTypePartieInteresseeDesignation(mapA.get("typePi"));
   	  
   	  if(partieInteressee.getGroupeClientId() != null) {
   		partieInteressee.setGroupeClientDesignation(groupeClientService.rechercheGroupeClientParId(new GroupeClientValue(partieInteressee.getGroupeClientId())).getDesignation());
   	  }
   		
   	  
   	  
 }

    return vResultatRecherche;
  }

  /**
   * Méthode de création d'une partie Interessée
   * 
   * @param pPartieInteresseValue
   * @return
   */
  @RequestMapping(value = "/creerPartieInteressee", method = RequestMethod.POST)
  public @ResponseBody String creerPartieInteressee(@RequestBody PartieInteresseValue pPartieInteresseValue) {
    return this.partieInteresseeService.creerPartieInteressee(pPartieInteresseValue);
  }

  /**
   * Méthode de modification d'une partie Interessée
   * 
   * @param pPartieInteresseValue
   * @return
   */
  @RequestMapping(value = "/modifierPartieInteressee", method = RequestMethod.POST)
  public @ResponseBody String modifierPartieInteressee(@RequestBody PartieInteresseValue pPartieInteresseValue) {
    return this.partieInteresseeService.modifierPartieInteressee(pPartieInteresseValue);
  }
  @RequestMapping(value = "/supprimerPartieInteressee:{id}", method = RequestMethod.DELETE)
  public void supprimerPartieInteressee(@PathVariable("id") String id) {
    partieInteresseeService.supprimerPartieInteressee(Long.valueOf(id));

  }
  
  
  /*************get Partie Interessee By ID*************/
 	@RequestMapping(value = "/getCurrentReferenceByFamille:{id}", method = RequestMethod.GET, produces =  "application/json")
 	public @ResponseBody String getCurrentReferenceByFamille(@PathVariable Long id) {
 		
 		return  partieInteresseeService.getCurrentReferenceByFamille(id,false);
 	}

}
