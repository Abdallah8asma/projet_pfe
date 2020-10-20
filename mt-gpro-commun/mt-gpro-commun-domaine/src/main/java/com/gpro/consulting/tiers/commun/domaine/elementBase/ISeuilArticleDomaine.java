package com.gpro.consulting.tiers.commun.domaine.elementBase;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.SeuilArticleValue;


// TODO: Auto-generated Javadoc
/**
 * The Interface ISeuilArticleDomaine.
 * @author mohamed
 */
public interface ISeuilArticleDomaine {
	
	/**
	 * Creer seuil article.
	 *
	 * @param pSeuilArticleValue the seuil article value
	 * @return the string
	 */
	public  String creerSeuilArticle(SeuilArticleValue pSeuilArticleValue);
	
	/**
	 * Supprimer seuil article.
	 *
	 * @param pSeuilArticleId the seuil article id
	 */
	public  void supprimerSeuilArticle(Long pSeuilArticleId);
	
	/**
	 * Modifier seuil article.
	 *
	 * @param pSeuilArticleValue the seuil article value
	 * @return the string
	 */
	public String modifierSeuilArticle(SeuilArticleValue pSeuilArticleValue);
	
	/**
	 * Recherche seuil article by id.
	 *
	 * @param pSeuilArticleId the seuil article id
	 * @return the seuil article value
	 */
	public SeuilArticleValue rechercheSeuilArticleById(Long pSeuilArticleId);
	
	/**
	 * Liste seuil article.
	 *
	 * @return the list
	 */
	public List<SeuilArticleValue> listeSeuilArticle();



}
