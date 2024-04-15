package com.gpro.consulting.tiers.commun.persistance.elementBase;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.UniteArticleValue;


/**
 * The Interface IUntieArticle.
 * @author $mohamed
 */

public interface IUniteArticlePersistance {
	/**
	 * ajouter    unite Article*.
	 *
	 * @return the string
	 */
	/** create unitearticle */
	public  String creerUniteArticle(UniteArticleValue pUniteArticleValue);
	/**
	 * supprimer  unite Article*.
	 *
	 */
	public  void supprimerUniteArticle(Long pUniteArticleValueId);
	/**
	 * modifier unite Article*.
	 *
	 * @return the string
	 */
	public String modifierUniteArticle(UniteArticleValue pUniteArticleValue);
	/**
	 *  recherche by id unite Article*.
	 *
	 */
	public UniteArticleValue  rechercheUniteArticleById(Long  pUniteArticleValueId);
	/**
	 * afficher  liste unite   Article*.
	 *
	 * @return the list
	 */
	public List<UniteArticleValue> listeUniteArticle();

}
