package com.gpro.consulting.tiers.commun.domaine.elementBase.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.SeuilArticleValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.ISeuilArticleDomaine;
import com.gpro.consulting.tiers.commun.persistance.elementBase.ISeuilArticlePersistance;


/**
 * The Class SeuilArticleDomaineImpl
 * @author mohamed.
 */
@Component
public class SeuilArticleDomaineImpl implements ISeuilArticleDomaine{

	@Autowired
	ISeuilArticlePersistance seuilArticlePersistance ;
	/*
	 * ajouter seuil article
	 */
	@Override
	public String creerSeuilArticle(SeuilArticleValue pSeuilArticleValue) {
		return seuilArticlePersistance.creerSeuilArticle(pSeuilArticleValue);
	}

	/* 
	 * supprimer  seuil article 
	 */
	@Override
	public void supprimerSeuilArticle(Long pSeuilArticleId) {
		seuilArticlePersistance.supprimerSeuilArticle(pSeuilArticleId);
	}

	/* 
	 * modifier seuil article
	 */
	@Override
	public String modifierSeuilArticle(SeuilArticleValue pSeuilArticleValue) {
		return seuilArticlePersistance.modifierSeuilArticle(pSeuilArticleValue);
	}

	/* 
	 * recherche seuil article
	 */
	@Override
	public SeuilArticleValue rechercheSeuilArticleById(Long pSeuilArticleId) {
		return seuilArticlePersistance.rechercheSeuilArticleById(pSeuilArticleId);
	}

	/* 
	 * liste seuil article
	 */
	@Override
	public List<SeuilArticleValue> listeSeuilArticle() {
		return seuilArticlePersistance.listeSeuilArticle();
	}

}
