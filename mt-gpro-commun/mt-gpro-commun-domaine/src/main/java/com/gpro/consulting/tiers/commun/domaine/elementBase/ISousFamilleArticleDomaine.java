package com.gpro.consulting.tiers.commun.domaine.elementBase;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.SousFamilleArticleValue;


// TODO: Auto-generated Javadoc
/**
 * The Interface ISousFamilleArticleDomaine.
 * @author mohamed
 */
public interface ISousFamilleArticleDomaine {
	
	/**
	 * Creer sous famille article.
	 *
	 * @param pSousFamilleArticleValue the sous famille article value
	 * @return the string
	 */
	public  String creerSousFamilleArticle(SousFamilleArticleValue pSousFamilleArticleValue);
	
	/**
	 * Supprimer sous famille article.
	 *
	 * @param pSousFamilleArticleId the sous famille article id
	 */
	public  void supprimerSousFamilleArticle(Long pSousFamilleArticleId);
	
	/**
	 * Modifier sous famille article.
	 *
	 * @param pSousFamilleArticleValue the sous famille article value
	 * @return the string
	 */
	public String modifierSousFamilleArticle(SousFamilleArticleValue pSousFamilleArticleValue);
	
	/**
	 * Recherche sous famille article by id.
	 *
	 * @param pSousFamilleArticleID the sous famille article id
	 * @return the sous famille article value
	 */
	public SousFamilleArticleValue rechercheSousFamilleArticleById(Long pSousFamilleArticleID);
	
	/**
	 * Liste sous famille article.
	 *
	 * @return the list
	 */
	public List<SousFamilleArticleValue> listeSousFamilleArticle();
}
