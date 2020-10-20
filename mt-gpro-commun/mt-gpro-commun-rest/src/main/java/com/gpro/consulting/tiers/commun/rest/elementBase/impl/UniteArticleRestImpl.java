package com.gpro.consulting.tiers.commun.rest.elementBase.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.UniteArticleValue;
import com.gpro.consulting.tiers.commun.rest.elementBase.IUniteArticleRest;
import com.gpro.consulting.tiers.commun.service.elementBase.IUniteArticleService;

/**
 * 
 * @author $Author: Ameni $
 * @version $Revision: 0 $
 */
@Controller
@RequestMapping("/uniteArticle")
public class UniteArticleRestImpl implements IUniteArticleRest {

  @Autowired
  private IUniteArticleService uniteArticleService;

  /**
   * Constructeur de la classe.
   */
  public UniteArticleRestImpl() {
    // Constructeur vide
  }

  @RequestMapping(value = "/string", method = RequestMethod.GET)
  public @ResponseBody String testStringPersonne() {

    return "Test";
  }

  /******************************* All UniteArticle *********************************/ 
  @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
  public @ResponseBody List < UniteArticleValue > viewAllUniteArticle() {
	  return uniteArticleService.listeUniteArticle();
  }
  
  /**********************  Méthode de Creation d'un UniteArticle **********************/
  @RequestMapping(value = "/creerUniteArticle", method = RequestMethod.POST)
  public @ResponseBody String creerUniteArticle(@RequestBody UniteArticleValue pUniteArticleValue) {
    return this.uniteArticleService.creerUniteArticle(pUniteArticleValue);
  }

  /**********************  Méthode de modification d'un UniteArticle **********************/
  @RequestMapping(value = "/modifierUniteArticle", method = RequestMethod.POST)
  public @ResponseBody String modifierUniteArticle(@RequestBody UniteArticleValue pUniteArticleValue) {
    return this.uniteArticleService.modifierUniteArticle(pUniteArticleValue);
  }
  
  /**********************  Méthode de Suppression d'un UniteArticle **********************/
  @RequestMapping(value = "/supprimerUniteArticle:{id}", method = RequestMethod.DELETE)
  public void supprimerUniteArticle(@PathVariable("id") String id) {
   Long idSupp= Long.parseLong(id);
   uniteArticleService.supprimerUniteArticle(Long.valueOf(idSupp));
  }
  
}