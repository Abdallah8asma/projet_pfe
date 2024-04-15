package com.gpro.consulting.tiers.commun.domaine.elementBase;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.TypeArticleValue;


/**
 * The Interface ITypeArticleDomaine.
 * @author mohamed
 */
public interface ITypeArticleDomaine {
	
	/**
	 * Creer type article.
	 *
	 * @param pTypeArticleValue the type article value
	 * @return the string
	 */
	public  String creerTypeArticle(TypeArticleValue pTypeArticleValue);
	
	/**
	 * Supprimer type article.
	 *
	 * @param pTypeArticleId the type article id
	 */
	public  void supprimerTypeArticle(Long pTypeArticleId);
	
	/**
	 * Modifier type article.
	 *
	 * @param pTypeArticleValue the type article value
	 * @return the string
	 */
	public String modifierTypeArticle(TypeArticleValue pTypeArticleValue);
	
	/**
	 * Recherche type article by id.
	 *
	 * @param pTypeArticleId the type article id
	 * @return the type article value
	 */
	public TypeArticleValue rechercheTypeArticleById(Long pTypeArticleId);
	
	/**
	 * Liste type article.
	 *
	 * @return the list
	 */
	public List<TypeArticleValue> listeTypeArticle();
}
