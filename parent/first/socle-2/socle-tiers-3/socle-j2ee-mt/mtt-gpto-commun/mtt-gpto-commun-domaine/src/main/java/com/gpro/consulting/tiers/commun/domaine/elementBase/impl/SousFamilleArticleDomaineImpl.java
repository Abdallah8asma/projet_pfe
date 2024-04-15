package com.gpro.consulting.tiers.commun.domaine.elementBase.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.SousFamilleArticleValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.ISousFamilleArticleDomaine;
import com.gpro.consulting.tiers.commun.persistance.elementBase.ISousFamilleArticlePersistance;

/**
 * The Class SousFamilleArticleDomaineImpl.
 * @author mohamed
 */
@Component
public class SousFamilleArticleDomaineImpl implements ISousFamilleArticleDomaine {

	@Autowired
	ISousFamilleArticlePersistance sousFamilleArticlePersistance ;
	
	/* ajouter sous famille
	 */
	@Override
	public String creerSousFamilleArticle(SousFamilleArticleValue pSousFamilleArticleValue) {
		return sousFamilleArticlePersistance.creerSousFamilleArticle(pSousFamilleArticleValue);
	}

	/* 
	 * supprimer sous famille article
	 */
	@Override
	public void supprimerSousFamilleArticle(Long pSousFamilleArticleId) {
		sousFamilleArticlePersistance.supprimerSousFamilleArticle(pSousFamilleArticleId);
	}

	/* 
	 * modifier sous famille 
	 */
	@Override
	public String modifierSousFamilleArticle(SousFamilleArticleValue pSousFamilleArticleValue) {
		return sousFamilleArticlePersistance.modifierSousFamilleArticle(pSousFamilleArticleValue);
	}

	/* 
	 * recherche par id sous famille 
	 */
	@Override
	public SousFamilleArticleValue rechercheSousFamilleArticleById(Long pSousFamilleArticleID) {
		return sousFamilleArticlePersistance.rechercheSousFamilleArticleById(pSousFamilleArticleID);
	}

	/* 
	 * liste sous famille article
	 */
	@Override
	public List<SousFamilleArticleValue> listeSousFamilleArticle() {
		return sousFamilleArticlePersistance.listeSousFamilleArticle();
	}

}
