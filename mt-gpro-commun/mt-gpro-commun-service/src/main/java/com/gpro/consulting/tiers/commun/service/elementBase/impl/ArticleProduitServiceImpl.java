package com.gpro.consulting.tiers.commun.service.elementBase.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticritereArticleProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ResultatRechecheArticleProduitValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.IArticleProduitDomaine;
import com.gpro.consulting.tiers.commun.service.elementBase.IArticleProduitService;

@Service
@Transactional(rollbackFor = Exception.class)
public class ArticleProduitServiceImpl implements IArticleProduitService{
@Autowired
IArticleProduitDomaine articleProduitDomaine;
	@Override
	public ResultatRechecheArticleProduitValue rechercherProduitArticleMultiCritere(
			RechercheMulticritereArticleProduitValue pRechercheMultiCritere) {
		// TODO Auto-generated method stub
		return articleProduitDomaine.rechercherMultiCritere(pRechercheMultiCritere);
	}

}
