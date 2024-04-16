package com.gpro.consulting.tiers.logistique.rest.gs.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RecherecheMulticritereArticleValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.ResultatRechecheArticleEntreeValue;
import com.gpro.consulting.tiers.logistique.service.gs.IArticleEntreeService;

@Controller
@RequestMapping("/articleEntree")
public class ArticleEntreeRestImpl {

	@Autowired
	private IArticleEntreeService articleEntreeService;

	/*******************************
	 * Article Entree Stock
	 *********************************/
	@RequestMapping(value = "/rechercheArticleEntreeMultiCritere", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResultatRechecheArticleEntreeValue rechercheArticleEntreeValue(
			@RequestBody RecherecheMulticritereArticleValue pArticle) {

		if (pArticle.getIdMAgasin() == null) {
			pArticle.setIdMAgasin(1L);
		}

		ResultatRechecheArticleEntreeValue resultatRecherche = articleEntreeService
				.rechercherArticleMultiCritere(pArticle, pArticle.getIdMAgasin());
		return resultatRecherche;
	}

	/*******************************
	 * Article Entree Stock FB
	 *********************************/
	@RequestMapping(value = "/rechercheArticleEntreeMultiCritereFB", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResultatRechecheArticleEntreeValue rechercheArticleEntreeValueFB(
			@RequestBody RecherecheMulticritereArticleValue pArticle) {

		ResultatRechecheArticleEntreeValue resultatRecherche = articleEntreeService
				.rechercherArticleMultiCritereFB(pArticle);
		return resultatRecherche;
	}

}
