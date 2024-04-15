package com.gpro.consulting.tiers.commun.rest.elementBase.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ImpressionProduitValue;
import com.gpro.consulting.tiers.commun.rest.elementBase.IImpressionProduitRest;
import com.gpro.consulting.tiers.commun.service.elementBase.IImpressionProduitService;

/**
 * 
 * @author $Author: Samer $
 * @version $Revision: 0 $
 */
@Controller
@RequestMapping("/impressionProduit")
public class ImpressionProduitRestImpl implements IImpressionProduitRest {

  @Autowired
  private IImpressionProduitService impressionProduitService;

  /**
   * Constructeur de la classe.
   */
  public ImpressionProduitRestImpl() {
    // Constructeur vide
  }

  

  /******************************* All ImpressionProduit *********************************/ 
  @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
  public @ResponseBody List < ImpressionProduitValue > viewAllImpressionProduit() {
	  return impressionProduitService.listeImpressionProduit();
  }
  
  /**********************  Méthode de Creation d'un ImpressionProduit **********************/
  @RequestMapping(value = "/creerImpressionProduit", method = RequestMethod.POST)
  public @ResponseBody String creerImpressionProduit(@RequestBody ImpressionProduitValue pImpressionProduitValue) {
    return this.impressionProduitService.creerImpressionProduit(pImpressionProduitValue);
  }

  /**********************  Méthode de modification d'un ImpressionProduit **********************/
  @RequestMapping(value = "/modifierImpressionProduit", method = RequestMethod.POST)
  public @ResponseBody String modifierImpressionProduit(@RequestBody ImpressionProduitValue pImpressionProduitValue) {
    return this.impressionProduitService.modifierImpressionProduit(pImpressionProduitValue);
  }
  
  /**********************  Méthode de Suppression d'un ImpressionProduit **********************/
  @RequestMapping(value = "/supprimerImpressionProduit:{id}", method = RequestMethod.DELETE)
  public @ResponseBody String supprimerImpressionProduit(@PathVariable("id") String id) {
   Long idSupp= Long.parseLong(id);
   impressionProduitService.supprimerImpressionProduit(Long.valueOf(idSupp));
   
   return "OK";
  }
  
}