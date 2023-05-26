package com.gpro.consulting.tiers.commun.service.elementBase;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticritereArticleProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ResultatRechecheArticleProduitValue;


public interface IArticleProduitService {
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ResultatRechecheArticleProduitValue rechercherProduitArticleMultiCritere(
			RechercheMulticritereArticleProduitValue pRechercheMultiCritere);

}
