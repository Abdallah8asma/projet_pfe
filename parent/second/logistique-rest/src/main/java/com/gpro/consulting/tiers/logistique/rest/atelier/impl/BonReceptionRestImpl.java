package com.gpro.consulting.tiers.logistique.rest.atelier.impl;

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

import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.BonReceptionValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.ElementRechecheBonReceptionValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.RechercheMulticritereBonReceptionValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.ResultatRechecheBonReceptionValue;
import com.gpro.consulting.tiers.logistique.service.atelier.bonReception.IBonReceptionService;
import com.gpro.consulting.tiers.logistique.service.atelier.cache.IGestionnaireLogistiqueCacheService;

@Controller
@RequestMapping("/bonreception")
public class BonReceptionRestImpl {

	@Autowired
	private IBonReceptionService bonReceptionService;

	@Autowired
	private IGestionnaireLogistiqueCacheService gestionnaireLogistiqueCacheService;
	
  private static final Logger LOGGER = LoggerFactory.getLogger(BonReceptionRestImpl.class);

  public BonReceptionRestImpl() {
    // Constructeur vide
  }

  /******************************* recherche multicritere BonReception *********************************/

  @RequestMapping(value = "/rechercheBonReceptionMulticritere", method = RequestMethod.POST)
  public @ResponseBody ResultatRechecheBonReceptionValue rechercherBonReceptionMultiCritere(
    @RequestBody final RechercheMulticritereBonReceptionValue pRechercheMulticritereBonReception) {
    ResultatRechecheBonReceptionValue vResultatRecherche = bonReceptionService
      .rechercherBonReceptionMultiCritere(pRechercheMulticritereBonReception);
    
	// Traitement : transformation de l'Id a sa propre Designation
	for (ElementRechecheBonReceptionValue elementRechercheBonReception : vResultatRecherche
			.getListeElementRechecheBonReceptionValeur()) {

		// Client, Produit
		Map<String, String> mapA = gestionnaireLogistiqueCacheService
				.rechercherDesignationParId(elementRechercheBonReception.getAbreviationClient(), elementRechercheBonReception.getDesignationProduit(),null, null);
		// Client
		elementRechercheBonReception.setAbreviationClientDesignation(mapA.get("client"));
		// Produit (Tissu)
		elementRechercheBonReception.setDesignationProduitDesignation(mapA.get("produit"));
		elementRechercheBonReception.setReferenceProduit(mapA.get("produitRef"));

	}
	
	return vResultatRecherche;

  }

  /************* get PhaseOf BonReception *************/
  /**
   * @param id
   * @return
   */

  @RequestMapping(value = "/getBonReceptionId:{id}", method = RequestMethod.GET, produces = "application/json")
  public @ResponseBody BonReceptionValue getEmploye(@PathVariable Long id) {
    BonReceptionValue pBonReceptionValue = new BonReceptionValue();
    pBonReceptionValue.setId(id);
    return bonReceptionService.rechercheBonReceptionParId(pBonReceptionValue);
  }

  /********************** Méthode de modification d'un BonReception **********************/
  /**
   * @param pPhaseOfValue
   * @return
   */
  @RequestMapping(value = "/modifieBonReception", method = RequestMethod.POST)
  public @ResponseBody String modifierBonRecption(@RequestBody BonReceptionValue pBonReceptionValue) {
    return this.bonReceptionService.modifierBonReception(pBonReceptionValue);

  }

  @RequestMapping(value = "/supprimerBonReception:{id}", method = RequestMethod.DELETE)
  public void supprimerBonReception(@PathVariable("id") String id) {
    bonReceptionService.supprimerBonReception(Long.valueOf(id));

  }

  @RequestMapping(value = "/creerOrdreBonReception", method = RequestMethod.POST)
  public @ResponseBody String creerBonReception(@RequestBody BonReceptionValue pBonReceptionValue) {

    return this.bonReceptionService.creerBonReception(pBonReceptionValue);
  }
  
  @RequestMapping(value = "/getBonReceptionByReference:{reference}", method = RequestMethod.GET, produces = "application/json")
  public @ResponseBody BonReceptionValue getBonReceptionByReference(@PathVariable String reference) {
	  
	  LOGGER.info("getBonReceptionByReference: Delegating reference request {} to service layer.", reference);
	  
	  return bonReceptionService.rechercheBonReceptionByReference(reference);
  }
  
  @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "application/json")
  public @ResponseBody List<BonReceptionValue> getAll() {
	  
	  LOGGER.info("getAll bon reception: Delegating reference request {} to service layer.");
	  
	  return bonReceptionService.getAll();
  }
  
}
