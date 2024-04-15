package com.gpro.consulting.tiers.commun.service.elementBase.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.SeuilArticleValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.ISeuilArticleDomaine;
import com.gpro.consulting.tiers.commun.service.elementBase.ISeuilArticleService;

@Service
@Transactional
public class SeuilArticleServiceImpl implements ISeuilArticleService{

	@Autowired
	ISeuilArticleDomaine seuilArticleDomaine;
	
	/************************ Creation Matiere *****************************/
	@Override
	public String creerSeuilArticle(SeuilArticleValue pSeuilArticleValue) {
		return seuilArticleDomaine.creerSeuilArticle(pSeuilArticleValue);
	}

	/************************ suppression Matiere ***************************/
	@Override
	public void supprimerSeuilArticle(Long pId) {
		seuilArticleDomaine.supprimerSeuilArticle(pId);
	}

	/************************ Modification Matiere ***************************/
	@Override
	public String modifierSeuilArticle(SeuilArticleValue pSeuilArticleValue) {
		return seuilArticleDomaine.modifierSeuilArticle(pSeuilArticleValue);
	}

	/************************** Recherche Matiere *****************************/
	@Override
	public SeuilArticleValue rechercheSeuilArticleParId(Long pId) {
		return seuilArticleDomaine.rechercheSeuilArticleById(pId);
	}
	
	/************************** Liste des Matieres *****************************/
	@Override
	public List<SeuilArticleValue> listeSeuilArticle() {
		return seuilArticleDomaine.listeSeuilArticle();
	}

}
