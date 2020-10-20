package com.gpro.consulting.tiers.commun.persistance.elementBase;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.TypeArticleValue;

/**
 * The Interface ITypeArticle.
 * @author $mohamed
 */

public interface ITypeArticlePersistance {
	/**
	 * ajouter  Type Article*.
	 *
	 * @return the string
	 */
	/** create Typearticle */
	public  String creerTypeArticle(TypeArticleValue pTypeArticleValue);
	/**
	 * supprimer  Type Article*.
	 *
	 */
	public  void supprimerTypeArticle(Long pTypeArticleValueId);
	/**
	 * modifier Type Article*.
	 *
	 * @return the string
	 */
	public String modifierTypeArticle(TypeArticleValue pTypeArticleValue);
	/**
	 *  recherche by id Type Article*.
	 *
	 */
	public TypeArticleValue  rechercheTypeArticleById(Long  pTypeArticleValueId);
	/**
	 * afficher  liste Type   Article*.
	 *
	 * @return the list
	 */
	public List<TypeArticleValue> listeTypeArticle();

}