package com.gpro.consulting.tiers.commun.domaine.elementBase.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.OptionArticleValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.IOptionArticleDomaine;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IOptionArticlePersistance;


/**
 * The Class OptionArticleDomaineImpl.
 * @author mohamed
 */
@Component
public class OptionArticleDomaineImpl implements IOptionArticleDomaine{
	
	@Autowired
	IOptionArticlePersistance  optionArticlePersistance ;
	
	/* ajouter unite */
	@Override
	public String creerOptionArticle(OptionArticleValue pOptionArticleValue) {
		return optionArticlePersistance.creerOptionArticle(pOptionArticleValue);
	}

	/* supprimer unite
	 */
	@Override
	public void supprimerOptionArticle(Long pOptionArticleId) {
	 optionArticlePersistance.supprimerOptionArticle(pOptionArticleId);
	}

	/* modifier unite
	 */
	@Override
	public String modifieruniteArticle(OptionArticleValue pOptionArticleValue) {
		return optionArticlePersistance.modifierOptionArticle(pOptionArticleValue);
	}

	@Override
	public OptionArticleValue rechercheOptionArticleById(Long pOptionArticleId) {
		return optionArticlePersistance.rechercheOptionArticleById(pOptionArticleId);
	}

	@Override
	public List<OptionArticleValue> listeOptionArticle() {
		return optionArticlePersistance.listeOptionArticle();
	}

}
