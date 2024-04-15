package com.gpro.consulting.tiers.commun.domaine.elementBase.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ArticleProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ImpressionProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticritereArticleProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ResultatRechecheArticleProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.SousFamilleArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.IArticleProduitDomaine;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IArticleProduitPersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IImpressionProduitPersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.ISousFamilleArticlePersistance;

@Component
public class ArticleProduitDomaineImpl implements IArticleProduitDomaine {
	@Autowired
	IArticleProduitPersistance articleProduitPersistance;
	@Autowired
	IImpressionProduitPersistance impressionProduitPersistance;
	@Autowired
	ISousFamilleArticlePersistance sousFamilleArticlePersistance;

	@Override
	public ResultatRechecheArticleProduitValue rechercherMultiCritere(
			RechercheMulticritereArticleProduitValue request) {
		ResultatRechecheArticleProduitValue result = articleProduitPersistance.rechercherMultiCritere(request);
		
		for(ArticleProduitValue articleProduitValue : result.getList()){
			
			
			
			if(articleProduitValue.getImpressionProduitId()!=null)
			{
			ImpressionProduitValue impressionProduitValue=	impressionProduitPersistance.rechercheImpressionProduitById(articleProduitValue.getImpressionProduitId());
			articleProduitValue.setImpressionDesignation(impressionProduitValue.getDesignation());
			
			
		
			}
			if(articleProduitValue.getSousFamilleArticleId()!=null)
			{
				SousFamilleArticleValue sousFamilleArticleValue= sousFamilleArticlePersistance.rechercheSousFamilleArticleById(articleProduitValue.getSousFamilleArticleId());
				articleProduitValue.setReferenceFamilleArticle(sousFamilleArticleValue.getDesignation());
			}
			
		}
			
		
		return result;
	
		
	}
	
	

}
