package com.gpro.consulting.tiers.commun.domaine.elementBase.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ArticleCacheValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.PrixClientValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RecherchePrixClientValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RecherecheMulticritereArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ResultatRechecheArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ResultatRechecheProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.IArticleDomaine;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IArticlePersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IPrixClientPersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IProduitPersistance;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.IGroupeClientPersistance;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.IPartieInteresseePersistance;

@Component
public class ArticleDomaineImpl implements IArticleDomaine{
   
	@Autowired
    IArticlePersistance articlePersistance;
	

	/** The produit persistance. */
	@Autowired
	IPrixClientPersistance prixClientPersistance;

	@Autowired
	IGroupeClientPersistance groupeClientPersistance;

	@Autowired
	IPartieInteresseePersistance partieInteresseePersistance;
    
	@Override
	public String creerArticle(ArticleValue pArticleValue) {
		
		return articlePersistance.creerArticle(pArticleValue);
	}

	@Override
	public void supprimerArticle(Long pId) {
		articlePersistance.supprimerArticle(pId);
		
	}
	
	@Override
	public String modifierArticle(ArticleValue pArticleValue) {
		
		return articlePersistance.modifierArticle(pArticleValue);
	}

	@Override
	public ArticleValue rechercheArticleParId(ArticleValue pArticleValue) {
		
		return articlePersistance.rechercheArticleParId(pArticleValue);
	}

	@Override
	public List<ArticleValue> listeArticle() {
		
		  List<ArticleValue> list = articlePersistance.listeArticle();
		  Collections.sort(list);
		  
		  return list;
	}

	@Override
	public ResultatRechecheArticleValue rechercherArticleMultiCritere(
			RecherecheMulticritereArticleValue pRechercheArticleMulitCritere) {
		
		return articlePersistance.rechercherArticleMultiCritere(pRechercheArticleMulitCritere);
	}


	@Override
	public List<ArticleCacheValue> listeArticleCache() {
		return articlePersistance.listeArticleCache();
	}

	@Override
	public ResultatRechecheArticleValue rechercherArticleMultiCritereClient(
			RecherecheMulticritereArticleValue pRechercheProduitMulitCritere) {


		Long piId = Long.parseLong(pRechercheProduitMulitCritere.getPartieInteressee());

		ResultatRechecheArticleValue result = null;

		if (piId != null) {

			PartieInteresseValue pi = partieInteresseePersistance.getPartieInteresseById(piId);

			result = articlePersistance.rechercherArticleMultiCritere(pRechercheProduitMulitCritere);
			Set<ArticleValue> produitList = new HashSet<ArticleValue>();

			// System.out.println("###########
			// pRechercheProduitMulitCritere.getPartieInteressee()) :
			// "+pRechercheProduitMulitCritere.getPartieInteressee());

			for (ArticleValue prod : result.getArticleValues()) {

				prod.setPartieIntersseId(new Long(pRechercheProduitMulitCritere.getPartieInteressee()));

			/*	if (pi.getGroupeClientId() != null && pRechercheProduitMulitCritere.getGroupeClientId() != null
						&& pi.getGroupeClientId().equals(pRechercheProduitMulitCritere.getGroupeClientId())) {
					prod.setGroupeClientId(pi.getGroupeClientId());
				}
				
				*/

				RecherchePrixClientValue vRecherchePrixClientMulitCritere = new RecherchePrixClientValue();
				vRecherchePrixClientMulitCritere.setIdClient(prod.getPartieIntersseId());
				vRecherchePrixClientMulitCritere.setIdProduit(prod.getId());
				PrixClientValue vPrixClient = prixClientPersistance
						.rechcherchePrixClientMC(vRecherchePrixClientMulitCritere);

				if (vPrixClient != null) {
					prod.setPrixSpecial(vPrixClient.getPrixvente());
					prod.setRemise(vPrixClient.getRemise());
				}

				else {
					prod.setPrixSpecial(new Double(0));
					prod.setRemise(new Double(0));
				}

				produitList.add(prod);
			}

			result.setArticleValues(produitList);

		}

		return result;
	}

	@Override
	public ArticleValue rechercheProduitParReference(String reference) {
		
		ArticleValue article = articlePersistance.rechercheProduitParReference(reference);
		return article;
	}

	@Override
	public ArticleValue getArticleParId(Long id) {
		// TODO Auto-generated method stub
		return articlePersistance.getArticleParId(id);
	}



}
