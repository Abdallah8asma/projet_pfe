package com.gpro.consulting.tiers.commun.rest.elementBase.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticritereArticleProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ResultatRechecheArticleProduitValue;
import com.gpro.consulting.tiers.commun.service.elementBase.IArticleProduitService;

@Controller
@RequestMapping("/articleProduit")
public class ArticleProduitRestImpl {
	
	@Autowired
	IArticleProduitService articleProduitService;

	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResultatRechecheArticleProduitValue rechercherMultiCritere(
			@RequestBody RechercheMulticritereArticleProduitValue request) {
		 request.setOptimized(RechercheMulticritereArticleProduitValue.checkForOptimization(request));
		ResultatRechecheArticleProduitValue result = articleProduitService.rechercherProduitArticleMultiCritere(request);

		return result;
	}

}
