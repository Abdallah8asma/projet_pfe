package com.gpro.consulting.tiers.commun.domaine.elementBase.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.TypeArticleValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.ITypeArticleDomaine;
import com.gpro.consulting.tiers.commun.persistance.elementBase.ITypeArticlePersistance;

/**
 * The Class TypeArticleDomaineImpl.
 */
@Component
public class TypeArticleDomaineImpl implements ITypeArticleDomaine {

	@Autowired
	ITypeArticlePersistance typeArticlePersistance;

	/*
	 * ajouter type article
	 */
	@Override
	public String creerTypeArticle(TypeArticleValue pTypeArticleValue) {
		return typeArticlePersistance.creerTypeArticle(pTypeArticleValue);
	}

	/*
	 * supprimer type article
	 */
	@Override
	public void supprimerTypeArticle(Long pTypeArticleId) {
		typeArticlePersistance.supprimerTypeArticle(pTypeArticleId);
	}

	/*
	 * modidier type article
	 */
	@Override
	public String modifierTypeArticle(TypeArticleValue pTypeArticleValue) {
		return typeArticlePersistance.modifierTypeArticle(pTypeArticleValue);
	}

	/*
	 * recherche par id type article
	 */
	@Override
	public TypeArticleValue rechercheTypeArticleById(Long pTypeArticleId) {
		return typeArticlePersistance.rechercheTypeArticleById(pTypeArticleId);
	}

	/*
	 * liste type article
	 */
	@Override
	public List<TypeArticleValue> listeTypeArticle() {
		return typeArticlePersistance.listeTypeArticle();
	}

}
