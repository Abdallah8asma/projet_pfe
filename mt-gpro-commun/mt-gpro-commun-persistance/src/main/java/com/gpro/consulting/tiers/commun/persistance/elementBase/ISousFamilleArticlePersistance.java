package com.gpro.consulting.tiers.commun.persistance.elementBase;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.SousFamilleArticleValue;


/**
 * The Interface ISousFamilleArticle.
 * @author $mohamed
 */

public interface ISousFamilleArticlePersistance  {
	
	/**
	 * ajouter    Famille Article*.
	 *
	 * @return the string
	 */
	
	/** create sous famille article */
	public  String creerSousFamilleArticle(SousFamilleArticleValue pSousFamilleArticleValue);
	
	/**
	 * supprimer   sous  Famille Article*.
	 *
	 */
	public  void supprimerSousFamilleArticle(Long pSousFamilleArticleId);
	
	
	/**
	 * modifier    sous Famille Article*.
	 *
	 * @return the string
	 */
	public String modifierSousFamilleArticle(SousFamilleArticleValue pSousFamilleArticleValue);
	
	/**
	 *  recherche by id sous  Famille Article*.
	 *
	 */
	public SousFamilleArticleValue rechercheSousFamilleArticleById(Long pSousFamilleArticleId);
	
	/**
	 * afficher  liste sous  Famille Article*.
	 *
	 * @return the list
	 */
	public List<SousFamilleArticleValue> listeSousFamilleArticle();

}
