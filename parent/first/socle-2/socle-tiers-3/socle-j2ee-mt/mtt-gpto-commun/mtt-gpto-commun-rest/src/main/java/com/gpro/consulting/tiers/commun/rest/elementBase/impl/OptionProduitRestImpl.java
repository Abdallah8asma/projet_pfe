package com.gpro.consulting.tiers.commun.rest.elementBase.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.OptionProduitValue;
import com.gpro.consulting.tiers.commun.rest.elementBase.IOptionProduitRest;
import com.gpro.consulting.tiers.commun.service.elementBase.IOptionProduitService;

/**
 * 
 * @author $Author: Samer $
 * @version $Revision: 0 $
 */
@Controller
@RequestMapping("/optionProduit")
public class OptionProduitRestImpl implements IOptionProduitRest {

  @Autowired
  private IOptionProduitService optionProduitService;

  /**
   * Constructeur de la classe.
   */
  public OptionProduitRestImpl() {
    // Constructeur vide
  }

  

  /******************************* All OptionProduit *********************************/ 
  @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
  public @ResponseBody List < OptionProduitValue > viewAllOptionProduit() {
	  return optionProduitService.listeOptionProduit();
  }
  
  /**********************  Méthode de Creation d'un OptionProduit **********************/
  @RequestMapping(value = "/creerOptionProduit", method = RequestMethod.POST)
  public @ResponseBody String creerOptionProduit(@RequestBody OptionProduitValue pOptionProduitValue) {
    return this.optionProduitService.creerOptionProduit(pOptionProduitValue);
  }

  /**********************  Méthode de modification d'un OptionProduit **********************/
  @RequestMapping(value = "/modifierOptionProduit", method = RequestMethod.POST)
  public @ResponseBody String modifierOptionProduit(@RequestBody OptionProduitValue pOptionProduitValue) {
    return this.optionProduitService.modifierOptionProduit(pOptionProduitValue);
  }
  
  /**********************  Méthode de Suppression d'un OptionProduit **********************/
  @RequestMapping(value = "/supprimerOptionProduit:{id}", method = RequestMethod.DELETE)
  public @ResponseBody String  supprimerOptionProduit(@PathVariable("id") String id) {
   Long idSupp= Long.parseLong(id);
   optionProduitService.supprimerOptionProduit(Long.valueOf(idSupp));
   
   return "OK";
  }
  
}