package com.gpro.consulting.tiers.commun.service.elementBase.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ArticleCacheValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RecherecheMulticritereArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ResultatRechecheArticleValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.IArticleDomaine;
import com.gpro.consulting.tiers.commun.service.elementBase.IArticleService;

@Service
@Transactional(rollbackFor = Exception.class)
public class ArticleServiceImpl implements IArticleService {

	@Autowired
	IArticleDomaine articleDomaine;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String creerArticle(ArticleValue pArticleValue) {

		return articleDomaine.creerArticle(pArticleValue);
	}

	@Override
	public void supprimerArticle(Long pId) {
		articleDomaine.supprimerArticle(pId);

	}

	@Override
	public String modifierArticle(ArticleValue pArticleValue) {

		return articleDomaine.modifierArticle(pArticleValue);
	}

	@Override
	public ArticleValue rechercheArticleParId(ArticleValue pArticleValue) {

		return articleDomaine.rechercheArticleParId(pArticleValue);
	}

	@Override
	public List<ArticleValue> listeArticle() {

		return articleDomaine.listeArticle();
	}

	@Override
	public ResultatRechecheArticleValue rechercherArticleMultiCritere(
			RecherecheMulticritereArticleValue pRechercheArticleMulitCritere) {

		return articleDomaine
				.rechercherArticleMultiCritere(pRechercheArticleMulitCritere);
	}

	@Override
	public List<ArticleCacheValue> listeArticleCache() {
		return articleDomaine.listeArticleCache();
	}

	@Override
	public ResultatRechecheArticleValue rechercherArticleMultiCritereClient(
			RecherecheMulticritereArticleValue pRechercheMultiCritere) {
		// TODO Auto-generated method stub
		return articleDomaine
				.rechercherArticleMultiCritereClient(pRechercheMultiCritere);
	}

	@Override
	public ArticleValue rechercheProduitParReference(String reference) {
		
		return articleDomaine.rechercheProduitParReference(reference);
	}
	
	
	@Override
	public ArticleValue getArticleParId(Long id) {
		
		
		return articleDomaine.getArticleParId(id);
	}

}
