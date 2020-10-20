package com.gpro.consulting.tiers.commun.service.elementBase.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.FamilleArticleValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.IFamilleArticleDomaine;
import com.gpro.consulting.tiers.commun.service.elementBase.IFamilleArticleService;

/**
 * The Class FamilleArticleImpl service.
 * @author mohamed
 */
@Service
@Transactional
public class FamilleArticleServiceImpl   implements  IFamilleArticleService  {
	
	@Autowired
	IFamilleArticleDomaine familleArticleDomaine;
	
			/**ajouter  famille article**/
	@Override
	public String creerFamilleArticle(FamilleArticleValue pFamilleArticleValue) {
		return familleArticleDomaine.creerFamilleArticle(pFamilleArticleValue);
	}

	/**supprimer   famille article**/

	@Override
	public void supprimerFamilleArticle(Long pFamilleArticleId) {
		familleArticleDomaine.supprimerFamilleArticle(pFamilleArticleId);
	}

	
	/**modifier  famille article**/
	@Override
	public String modifierFamilleArticle(FamilleArticleValue pFamilleArticleValue) {
		return familleArticleDomaine.modifierFamilleArticle(pFamilleArticleValue);
	}

	/**recherche by id  famille article**/
	@Override
	public FamilleArticleValue rechercheFamilleArticleById(Long pFamilleArticleId) {
		return familleArticleDomaine.rechercheFamilleArticleById(pFamilleArticleId);
	}


	/**list  famille article**/
	@Override
	public List<FamilleArticleValue> listeFamilleArticle() {
		return familleArticleDomaine.listeFamilleArticle();
	}
	
}
