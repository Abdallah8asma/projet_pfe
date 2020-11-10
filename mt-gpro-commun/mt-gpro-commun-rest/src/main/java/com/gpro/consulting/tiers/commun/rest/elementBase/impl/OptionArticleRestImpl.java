package com.gpro.consulting.tiers.commun.rest.elementBase.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.OptionArticleValue;
import com.gpro.consulting.tiers.commun.rest.elementBase.IOptionArticleRest;
import com.gpro.consulting.tiers.commun.service.elementBase.IOptionArticleService;

/**
 * 
 * @author $Author: Samer $
 * @version $Revision: 0 $
 */
@Controller
@RequestMapping("/optionArticle")
public class OptionArticleRestImpl implements IOptionArticleRest {

  @Autowired
  private IOptionArticleService optionArticleService;

  /**
   * Constructeur de la classe.
   */
  public OptionArticleRestImpl() {
    // Constructeur vide
  }

  

  /******************************* All OptionArticle *********************************/ 
  @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
  public @ResponseBody List < OptionArticleValue > viewAllOptionArticle() {
	  return optionArticleService.listeOptionArticle();
  }
  
  /**********************  Méthode de Creation d'un OptionArticle **********************/
  @RequestMapping(value = "/creerOptionArticle", method = RequestMethod.POST)
  public @ResponseBody String creerOptionArticle(@RequestBody OptionArticleValue pOptionArticleValue) {
    return this.optionArticleService.creerOptionArticle(pOptionArticleValue);
  }

  /**********************  Méthode de modification d'un OptionArticle **********************/
  @RequestMapping(value = "/modifierOptionArticle", method = RequestMethod.POST)
  public @ResponseBody String modifierOptionArticle(@RequestBody OptionArticleValue pOptionArticleValue) {
    return this.optionArticleService.modifierOptionArticle(pOptionArticleValue);
  }
  
  /**********************  Méthode de Suppression d'un OptionArticle **********************/
  @RequestMapping(value = "/supprimerOptionArticle:{id}", method = RequestMethod.DELETE)
  public void supprimerOptionArticle(@PathVariable("id") String id) {
   Long idSupp= Long.parseLong(id);
   optionArticleService.supprimerOptionArticle(Long.valueOf(idSupp));
  }
  
}