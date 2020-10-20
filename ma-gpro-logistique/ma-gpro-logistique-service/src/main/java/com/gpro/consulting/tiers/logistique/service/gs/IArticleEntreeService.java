package com.gpro.consulting.tiers.logistique.service.gs;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RecherecheMulticritereArticleValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.ResultatRechecheArticleEntreeValue;

public interface IArticleEntreeService {
	
	 
	  @Transactional(readOnly = true, rollbackFor = Exception.class)
	  public ResultatRechecheArticleEntreeValue rechercherArticleMultiCritere(
			  RecherecheMulticritereArticleValue pRechercheArticleMulitCritere,Long idMagasin);
	
	  @Transactional(readOnly = true, rollbackFor = Exception.class)
	public ResultatRechecheArticleEntreeValue rechercherArticleMultiCritereFB(
				  RecherecheMulticritereArticleValue pRechercheArticleMulitCritere);
	
}
