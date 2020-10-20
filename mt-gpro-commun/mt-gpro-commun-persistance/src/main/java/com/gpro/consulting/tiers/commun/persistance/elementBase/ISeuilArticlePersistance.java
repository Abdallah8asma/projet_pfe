package com.gpro.consulting.tiers.commun.persistance.elementBase;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.SeuilArticleValue;

/**
* The Interface IFamilleArticle.
* @author $mohamed
*/
public interface ISeuilArticlePersistance{

	/**
	 * ajouter    seuil Article*.
	 *
	 * @return the string
	 */
	public  String creerSeuilArticle(SeuilArticleValue pSeuilValue);
	
	/**
	 * supprimer    suil Article*.
	 *
	 */
	public  void supprimerSeuilArticle(Long pSeuilValueId);
	
	
	/**
	 * modifier    se Auilrticle*.
	 *
	 * @return the string
	 */
	public String modifierSeuilArticle(SeuilArticleValue pSeuilValue);
	
	/**
	 *  recherche by id   seuil Article*.
	 *
	 * @return the famille article value
	 */
	public SeuilArticleValue rechercheSeuilArticleById(Long pFamilleArticleId);
	
	/**
	 * afficher  liste  seuil Article*.
	 *
	 * @return the list
	 */
	public List<SeuilArticleValue> listeSeuilArticle();
}
