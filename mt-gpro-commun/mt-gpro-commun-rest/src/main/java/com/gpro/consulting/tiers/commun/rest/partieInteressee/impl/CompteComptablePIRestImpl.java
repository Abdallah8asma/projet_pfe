package com.gpro.consulting.tiers.commun.rest.partieInteressee.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.CompteComptablePIValue;
import com.gpro.consulting.tiers.commun.rest.partieInteressee.ICompteComptablePIRest;
import com.gpro.consulting.tiers.commun.service.partieInteressee.ICompteComptablePIService;

/**
 * 
 * @author $Author: Samer $
 * @version $Revision: 0 $
 */
@Controller
@RequestMapping("/compteComptablePI")
public class CompteComptablePIRestImpl implements ICompteComptablePIRest {

  @Autowired
  private ICompteComptablePIService compteComptableService;

  /**
   * Constructeur de la classe.
   */
  public CompteComptablePIRestImpl() {
    // Constructeur vide
  }

  

  /******************************* All CompteComptablePI *********************************/ 
  @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
  public @ResponseBody List < CompteComptablePIValue > viewAllCompteComptablePI() {
	  return compteComptableService.listeCompteComptablePI();
  }
  
  /**********************  Méthode de Creation d'un CompteComptablePI **********************/
  @RequestMapping(value = "/creerCompteComptablePI", method = RequestMethod.POST)
  public @ResponseBody String creerCompteComptablePI(@RequestBody CompteComptablePIValue pCompteComptablePIValue) {
    return this.compteComptableService.creerCompteComptablePI(pCompteComptablePIValue);
  }

  /**********************  Méthode de modification d'un CompteComptablePI **********************/
  @RequestMapping(value = "/modifierCompteComptablePI", method = RequestMethod.POST)
  public @ResponseBody String modifierCompteComptablePI(@RequestBody CompteComptablePIValue pCompteComptablePIValue) {
    return this.compteComptableService.modifierCompteComptablePI(pCompteComptablePIValue);
  }
  
  /**********************  Méthode de Suppression d'un CompteComptablePI **********************/
  @RequestMapping(value = "/supprimerCompteComptablePI:{id}", method = RequestMethod.DELETE)
  public void supprimerCompteComptablePI(@PathVariable("id") String id) {
   Long idSupp= Long.parseLong(id);
   compteComptableService.supprimerCompteComptablePI(Long.valueOf(idSupp));
  }
  
}