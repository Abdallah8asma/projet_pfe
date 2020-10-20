package com.gpro.consulting.tiers.commun.domaine.elementBase;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.FamilleArticleValue;

/**
 * The Interface IFamilleArticle.
 * @author $mohamed
 */
public interface IFamilleArticleDomaine {

	/**
	 * ajouter    Famille Article*.
	 *
	 * @param pFamilleArticleValue the famille article value
	 * @return the string
	 */
	public  String creerFamilleArticle(FamilleArticleValue pFamilleArticleValue);
	
	/**
	 * supprimer    Famille Article*.
	 *
	 * @param pFamilleArticleValue the famille article value
	 */
	public  void supprimerFamilleArticle(Long pFamilleArticleId);
	
	
	/**
	 * modifier    Famille Article*.
	 *
	 * @param pFamilleArticleValue the famille article value
	 * @return the string
	 */
	public String modifierFamilleArticle(FamilleArticleValue pFamilleArticleValue);
	
	/**
	 *  recherche by id   Famille Article*.
	 *
	 * @param pFamilleArticleValue the famille article value
	 * @return the famille article value
	 */
	public FamilleArticleValue rechercheFamilleArticleById(Long pFamilleArticleId);
	
	/**
	 * afficher  liste  Famille Article*.
	 *
	 * @return the list
	 */
	public List<FamilleArticleValue> listeFamilleArticle();
}
