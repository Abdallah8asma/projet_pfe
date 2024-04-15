package com.gpro.consulting.tiers.commun.domaine.elementBase;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ArticleCacheValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RecherecheMulticritereArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ResultatRechecheArticleValue;


public interface IArticleDomaine {
	/**
	   * Méthode de création d'un article 
	   * @param pArticleValue
	   * @return
	   */
	  public String creerArticle(ArticleValue pArticleValue);

	  /**
	   * Méthode de suppression d'un article par id.
	   * 
	   * @param pId
	   */
	  public void supprimerArticle(Long pId);

	  /**
	   * Méthode de recheche d'un article par id retournant un objet Entité.
	   * 
	   * @param pArticleValue
	   * @return
	   */
	  public String modifierArticle(ArticleValue pArticleValue);

	  /**
	   * Méthode de recherche d'un article par id retournant un objet Valeur.
	   * 
	   * @param pPartieInteresseValue
	   * @return
	   */
	  public ArticleValue rechercheArticleParId(ArticleValue pArticleValue);

	  /**
	   * Méthode de recherche des parties interessees retournant un objet Valeur.
	   * 
	   * @return
	   */
	  public List < ArticleValue > listeArticle();

	  /**
	   * Méthode de recherche des articles par critères
	   * 
	   * @return
	   */
	  public ResultatRechecheArticleValue rechercherArticleMultiCritere(
			  RecherecheMulticritereArticleValue pRechercheArticleMulitCritere);
	
	
		 /**
		 * @return the list article cache(designation,reference,id)
		 */
	  public List < ArticleCacheValue > listeArticleCache();

		public ResultatRechecheArticleValue rechercherArticleMultiCritereClient(
				RecherecheMulticritereArticleValue pRechercheMultiCritere);
		
		

		public ArticleValue rechercheProduitParReference(String reference);
	
	
		  public ArticleValue getArticleParId(Long id);
}
