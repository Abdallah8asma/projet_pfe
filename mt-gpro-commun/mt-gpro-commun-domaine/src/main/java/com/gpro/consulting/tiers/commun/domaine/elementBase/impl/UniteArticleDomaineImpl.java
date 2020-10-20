package com.gpro.consulting.tiers.commun.domaine.elementBase.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.UniteArticleValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.IUniteArticleDomaine;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IUniteArticlePersistance;


/**
 * The Class UniteArticleDomaineImpl.
 * @author mohamed
 */
@Component
public class UniteArticleDomaineImpl implements IUniteArticleDomaine{
	
	@Autowired
	IUniteArticlePersistance  uniteArticlePersistance ;
	
	/* ajouter unite */
	@Override
	public String creerUniteArticle(UniteArticleValue pUniteArticleValue) {
		return uniteArticlePersistance.creerUniteArticle(pUniteArticleValue);
	}

	/* supprimer unite
	 */
	@Override
	public void supprimerUniteArticle(Long pUniteArticleId) {
	 uniteArticlePersistance.supprimerUniteArticle(pUniteArticleId);
	}

	/* modifier unite
	 */
	@Override
	public String modifieruniteArticle(UniteArticleValue pUniteArticleValue) {
		return uniteArticlePersistance.modifierUniteArticle(pUniteArticleValue);
	}

	@Override
	public UniteArticleValue rechercheUniteArticleById(Long pUniteArticleId) {
		return uniteArticlePersistance.rechercheUniteArticleById(pUniteArticleId);
	}

	@Override
	public List<UniteArticleValue> listeUniteArticle() {
		return uniteArticlePersistance.listeUniteArticle();
	}

}
