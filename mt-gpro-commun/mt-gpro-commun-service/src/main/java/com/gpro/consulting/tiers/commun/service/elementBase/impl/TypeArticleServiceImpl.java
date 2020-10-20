package com.gpro.consulting.tiers.commun.service.elementBase.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.TypeArticleValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.ITypeArticleDomaine;
import com.gpro.consulting.tiers.commun.service.elementBase.ITypeArticleService;

/**
 * The Class TypeArticleServiceImpl.
 * @author mohamed
 */
@Service
public class TypeArticleServiceImpl implements ITypeArticleService {
 
 /** The type article domaine. */
 @Autowired
   ITypeArticleDomaine typeArticleDomaine;
	
	/* 
	 * ajouter type Article
	 */
	@Override
	public String creerTypeArticle(TypeArticleValue pTypeArticleValue) {
		return typeArticleDomaine.creerTypeArticle(pTypeArticleValue);
	}

	/* 
	 * supprimer type article
	 */
	@Override
	public void supprimerTypeArticle(Long pTypeArticleId) {
        typeArticleDomaine.supprimerTypeArticle(pTypeArticleId);		
	}

	/* 
	 * modifier type article
	 */
	@Override
	public String modifierTypeArticle(TypeArticleValue pTypeArticleValue) {
		return typeArticleDomaine.modifierTypeArticle(pTypeArticleValue);
	}

	/* 
	 * recherche type article by id
	 */
	@Override
	public TypeArticleValue rechercheTypeArticleById(Long pTypeArticleId) {
		return typeArticleDomaine.rechercheTypeArticleById(pTypeArticleId);
	}

	/* 
	 * list type article
	 */
	@Override
	public List<TypeArticleValue> listeTypeArticle() {
		return typeArticleDomaine.listeTypeArticle();
	}

	
	
}
