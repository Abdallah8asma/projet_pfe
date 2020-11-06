package com.gpro.consulting.tiers.commun.service.elementBase.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.UniteArticleValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.IUniteArticleDomaine;
import com.gpro.consulting.tiers.commun.service.elementBase.IUniteArticleService;

@Service
@Transactional
public class UniteArticleServiceImpl  implements IUniteArticleService{
	@Autowired
	IUniteArticleDomaine uniteArticleDomaine;

	@Override
	public UniteArticleValue rechercheUniteArticleParId(Long pUniteArticleValue) {
		return uniteArticleDomaine.rechercheUniteArticleById(pUniteArticleValue);
	}

	@Override
	public List<UniteArticleValue> listeUniteArticle() {
		return uniteArticleDomaine.listeUniteArticle();
	}
	
	/************************ Creation Matiere *****************************/
	@Override
	public String creerUniteArticle(UniteArticleValue pUniteArticleValue) {
		return uniteArticleDomaine.creerUniteArticle(pUniteArticleValue);
	}

	/************************ suppression Matiere ***************************/
	@Override
	public void supprimerUniteArticle(Long pId ) {
		uniteArticleDomaine.supprimerUniteArticle(pId);
	}

	/************************ Modification Matiere ***************************/
	@Override
	public String modifierUniteArticle(UniteArticleValue pUniteArticleValue) {
		return uniteArticleDomaine.modifieruniteArticle(pUniteArticleValue);
	}

}
