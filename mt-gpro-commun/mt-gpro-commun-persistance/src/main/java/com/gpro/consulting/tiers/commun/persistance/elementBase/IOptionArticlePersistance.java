package com.gpro.consulting.tiers.commun.persistance.elementBase;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.OptionArticleValue;


/**
 * The Interface IUntieArticle.
 * @author $mohamed
 */

public interface IOptionArticlePersistance {
	/**
	 * ajouter    unite Article*.
	 *
	 * @return the string
	 */
	/** create OptionArticle */
	public  String creerOptionArticle(OptionArticleValue pOptionArticleValue);
	/**
	 * supprimer  unite Article*.
	 *
	 */
	public  void supprimerOptionArticle(Long pOptionArticleValueId);
	/**
	 * modifier unite Article*.
	 *
	 * @return the string
	 */
	public String modifierOptionArticle(OptionArticleValue pOptionArticleValue);
	/**
	 *  recherche by id unite Article*.
	 *
	 */
	public OptionArticleValue  rechercheOptionArticleById(Long  pOptionArticleValueId);
	/**
	 * afficher  liste unite   Article*.
	 *
	 * @return the list
	 */
	public List<OptionArticleValue> listeOptionArticle();

}
