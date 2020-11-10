package com.gpro.consulting.tiers.commun.service.elementBase.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.OptionArticleValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.IOptionArticleDomaine;
import com.gpro.consulting.tiers.commun.service.elementBase.IOptionArticleService;

@Service
@Transactional
public class OptionArticleServiceImpl  implements IOptionArticleService{
	@Autowired
	IOptionArticleDomaine optionArticleDomaine;

	@Override
	public OptionArticleValue rechercheOptionArticleParId(Long pOptionArticleValue) {
		return optionArticleDomaine.rechercheOptionArticleById(pOptionArticleValue);
	}

	@Override
	public List<OptionArticleValue> listeOptionArticle() {
		return optionArticleDomaine.listeOptionArticle();
	}
	
	/************************ Creation Matiere *****************************/
	@Override
	public String creerOptionArticle(OptionArticleValue pOptionArticleValue) {
		return optionArticleDomaine.creerOptionArticle(pOptionArticleValue);
	}

	/************************ suppression Matiere ***************************/
	@Override
	public void supprimerOptionArticle(Long pId ) {
		optionArticleDomaine.supprimerOptionArticle(pId);
	}

	/************************ Modification Matiere ***************************/
	@Override
	public String modifierOptionArticle(OptionArticleValue pOptionArticleValue) {
		return optionArticleDomaine.modifieruniteArticle(pOptionArticleValue);
	}

}
