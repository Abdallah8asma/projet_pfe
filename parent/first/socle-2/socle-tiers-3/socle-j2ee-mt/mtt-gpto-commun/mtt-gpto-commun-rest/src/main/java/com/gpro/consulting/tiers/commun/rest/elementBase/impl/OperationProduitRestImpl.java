package com.gpro.consulting.tiers.commun.rest.elementBase.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.OperationProduitValue;
import com.gpro.consulting.tiers.commun.rest.elementBase.IOperationProduitRest;
import com.gpro.consulting.tiers.commun.service.elementBase.IOperationProduitService;

/**
 * 
 * @author $Author: Samer $
 * @version $Revision: 0 $
 */
@Controller
@RequestMapping("/operationProduit")
public class OperationProduitRestImpl implements IOperationProduitRest {

  @Autowired
  private IOperationProduitService optionProduitService;

  /**
   * Constructeur de la classe.
   */
  public OperationProduitRestImpl() {
    // Constructeur vide
  }

  

  /******************************* All OperationProduit *********************************/ 
  @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
  public @ResponseBody List < OperationProduitValue > viewAllOperationProduit() {
	  return optionProduitService.listeOperationProduit();
  }
  
  /**********************  Méthode de Creation d'un OperationProduit **********************/
  @RequestMapping(value = "/creerOperationProduit", method = RequestMethod.POST)
  public @ResponseBody String creerOperationProduit(@RequestBody OperationProduitValue pOperationProduitValue) {
    return this.optionProduitService.creerOperationProduit(pOperationProduitValue);
  }

  /**********************  Méthode de modification d'un OperationProduit **********************/
  @RequestMapping(value = "/modifierOperationProduit", method = RequestMethod.POST)
  public @ResponseBody String modifierOperationProduit(@RequestBody OperationProduitValue pOperationProduitValue) {
    return this.optionProduitService.modifierOperationProduit(pOperationProduitValue);
  }
  
  /**********************  Méthode de Suppression d'un OperationProduit **********************/
  @RequestMapping(value = "/supprimerOperationProduit:{id}", method = RequestMethod.DELETE)
  public @ResponseBody String  supprimerOperationProduit(@PathVariable("id") String id) {
   Long idSupp= Long.parseLong(id);
   optionProduitService.supprimerOperationProduit(Long.valueOf(idSupp));
   
   return "OK";
  }
  
}