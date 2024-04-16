package com.gpro.consulting.tiers.logistique.domaine.gs;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RecherecheMulticritereArticleValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.ResultatRechecheArticleEntreeValue;

public interface IArticleEntreeDomaine {
	
	public ResultatRechecheArticleEntreeValue rechercherArticleMultiCritere(
			  RecherecheMulticritereArticleValue pRechercheArticleMulitCritere,Long idMagasin);
	
	public ResultatRechecheArticleEntreeValue rechercherArticleMultiCritereFB(
			  RecherecheMulticritereArticleValue pRechercheArticleMulitCritere);
    
}
