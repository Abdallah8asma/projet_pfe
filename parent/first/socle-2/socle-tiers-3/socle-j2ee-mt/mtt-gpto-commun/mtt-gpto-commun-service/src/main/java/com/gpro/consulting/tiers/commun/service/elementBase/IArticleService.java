package com.gpro.consulting.tiers.commun.service.elementBase;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ArticleCacheValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RecherecheMulticritereArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ResultatRechecheArticleValue;


public interface IArticleService {
	/**
	   * Méthode de création d'un article 
	   * @param pArticleValue
	   * @return
	   */
	  @Transactional(readOnly = false, rollbackFor = Exception.class)
	  public String creerArticle(ArticleValue pArticleValue);

	  /**
	   * Méthode de suppression d'un article par id.
	   * 
	   * @param long1
	   */
	  @Transactional(readOnly = false, rollbackFor = Exception.class)
	  public void supprimerArticle(Long long1);

	  /**
	   * Méthode de recheche d'un article par id retournant un objet Entité.
	   * 
	   * @param pArticleValue
	   * @return
	   */
	  @Transactional(readOnly = false, rollbackFor = Exception.class)
	  public String modifierArticle(ArticleValue pArticleValue);

	  /**
	   * Méthode de recherche d'un article par id retournant un objet Valeur.
	   * 
	   * @param pArticleValue
	   * @return
	   */
	  @Transactional(readOnly = true, rollbackFor = Exception.class)
	  public ArticleValue rechercheArticleParId(ArticleValue pArticleValue);
	  /**
	   * Méthode de recherche des parties interessees retournant un objet Valeur.
	   * 
	   * @return
	   */
	  @Transactional(readOnly = true, rollbackFor = Exception.class)
	  public List < ArticleValue > listeArticle();

	  /**
	   * Méthode de recherche des articles par critères
	   * 
	   * @return
	   */
	  @Transactional(readOnly = true, rollbackFor = Exception.class)
	  public ResultatRechecheArticleValue rechercherArticleMultiCritere(
			  RecherecheMulticritereArticleValue pRechercheArticleMulitCritere);
	

		 /**
		 * @return the list article cache(designation,reference,id)
		 */
		@Transactional(readOnly = true, rollbackFor = Exception.class)
		public List<ArticleCacheValue> listeArticleCache();

		
		@Transactional(readOnly = true, rollbackFor = Exception.class)
		public ResultatRechecheArticleValue rechercherArticleMultiCritereClient(
				RecherecheMulticritereArticleValue pRechercheMultiCritere);
		
		@Transactional(readOnly = true, rollbackFor = Exception.class)
		public ArticleValue rechercheProduitParReference(String reference);
		
		
		@Transactional(readOnly = true, rollbackFor = Exception.class)
		public ArticleValue getArticleParId(Long id);

	
}
