package com.gpro.consulting.tiers.commun.rest.elementBase.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.CompteComptableValue;
import com.gpro.consulting.tiers.commun.rest.elementBase.ICompteComptableRest;
import com.gpro.consulting.tiers.commun.service.elementBase.ICompteComptableService;

/**
 * 
 * @author $Author: Samer $
 * @version $Revision: 0 $
 */
@Controller
@RequestMapping("/compteComptable")
public class CompteComptableRestImpl implements ICompteComptableRest {

  @Autowired
  private ICompteComptableService compteComptableService;

  /**
   * Constructeur de la classe.
   */
  public CompteComptableRestImpl() {
    // Constructeur vide
  }

  

  /******************************* All CompteComptable *********************************/ 
  @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
  public @ResponseBody List < CompteComptableValue > viewAllCompteComptable() {
	  return compteComptableService.listeCompteComptable();
  }
  
  /**********************  Méthode de Creation d'un CompteComptable **********************/
  @RequestMapping(value = "/creerCompteComptable", method = RequestMethod.POST)
  public @ResponseBody String creerCompteComptable(@RequestBody CompteComptableValue pCompteComptableValue) {
    return this.compteComptableService.creerCompteComptable(pCompteComptableValue);
  }

  /**********************  Méthode de modification d'un CompteComptable **********************/
  @RequestMapping(value = "/modifierCompteComptable", method = RequestMethod.POST)
  public @ResponseBody String modifierCompteComptable(@RequestBody CompteComptableValue pCompteComptableValue) {
    return this.compteComptableService.modifierCompteComptable(pCompteComptableValue);
  }
  
  /**********************  Méthode de Suppression d'un CompteComptable **********************/
  @RequestMapping(value = "/supprimerCompteComptable:{id}", method = RequestMethod.DELETE)
  public void supprimerCompteComptable(@PathVariable("id") String id) {
   Long idSupp= Long.parseLong(id);
   compteComptableService.supprimerCompteComptable(Long.valueOf(idSupp));
  }
  
}