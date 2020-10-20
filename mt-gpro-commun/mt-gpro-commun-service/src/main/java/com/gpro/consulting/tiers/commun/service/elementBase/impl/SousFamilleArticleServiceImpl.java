package com.gpro.consulting.tiers.commun.service.elementBase.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.SousFamilleArticleValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.ISousFamilleArticleDomaine;
import com.gpro.consulting.tiers.commun.service.elementBase.ISousFamilleArticleService;

/**
 * The Class SousFamilleArticleServiceImpl.
 * @author mohamed
 */
@Service
@Transactional
public class SousFamilleArticleServiceImpl implements ISousFamilleArticleService {

	   /** The sous famille article domaine. */
   	@Autowired
	   ISousFamilleArticleDomaine sousFamilleArticleDomaine;
	
	/* 
	 * ajouter sous famille
	 */
	@Override
	public String creerSousFamilleArticle(SousFamilleArticleValue pSousFamilleArticleValue) {
		return sousFamilleArticleDomaine.creerSousFamilleArticle(pSousFamilleArticleValue);
	}

	/* 
	 * supprimer sous famille
	 */
	@Override
	public void supprimerSousFamilleArticle(Long pSousFamilleArticleId) {
       sousFamilleArticleDomaine.supprimerSousFamilleArticle(pSousFamilleArticleId);		
	}

	/* 
	 * modifier sous famille
	 */
	@Override
	public String modifierSousFamilleArticle(
			SousFamilleArticleValue pSousFamilleArticleValue) {
		return sousFamilleArticleDomaine.modifierSousFamilleArticle(pSousFamilleArticleValue);
	}

	/* 
	 * recherche par id sous famille 
	 */
	@Override
	public SousFamilleArticleValue rechercheSousFamilleArticleById(
			Long pSousFamilleArticleId) {
	     return sousFamilleArticleDomaine.rechercheSousFamilleArticleById(pSousFamilleArticleId);
	} 

	/* 
	 * liste sous famille
	 */
	@Override
	public List<SousFamilleArticleValue> listeSousFamilleArticle() {
		return sousFamilleArticleDomaine.listeSousFamilleArticle();
	}

}
