package com.gpro.consulting.tiers.commun.domaine.elementBase.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ArticleCacheValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RecherecheMulticritereArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ResultatRechecheArticleValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.IArticleDomaine;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IArticlePersistance;

@Component
public class ArticleDomaineImpl implements IArticleDomaine{
   
	@Autowired
    IArticlePersistance articlePersistance;
    
	@Override
	public String creerArticle(ArticleValue pArticleValue) {
		
		return articlePersistance.creerArticle(pArticleValue);
	}

	@Override
	public void supprimerArticle(Long pId) {
		articlePersistance.supprimerArticle(pId);
		
	}
	
	@Override
	public String modifierArticle(ArticleValue pArticleValue) {
		
		return articlePersistance.modifierArticle(pArticleValue);
	}

	@Override
	public ArticleValue rechercheArticleParId(ArticleValue pArticleValue) {
		
		return articlePersistance.rechercheArticleParId(pArticleValue);
	}

	@Override
	public List<ArticleValue> listeArticle() {
		
		  List<ArticleValue> list = articlePersistance.listeArticle();
		  Collections.sort(list);
		  
		  return list;
	}

	@Override
	public ResultatRechecheArticleValue rechercherArticleMultiCritere(
			RecherecheMulticritereArticleValue pRechercheArticleMulitCritere) {
		
		return articlePersistance.rechercherArticleMultiCritere(pRechercheArticleMulitCritere);
	}


	@Override
	public List<ArticleCacheValue> listeArticleCache() {
		return articlePersistance.listeArticleCache();
	}



}
