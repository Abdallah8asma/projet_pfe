package com.gpro.consulting.tiers.commun.domaine.elementBase;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.OptionArticleValue;

/**
 * The Interface IOptionArticleDomaine.
 * @author mohamed
 */
public interface IOptionArticleDomaine {
	
	/**
	 * Creer unite article.
	 *
	 * @param pOptionArticleValue the unite article value
	 * @return the string
	 */
	public  String creerOptionArticle(OptionArticleValue pOptionArticleValue);
	
	/**
	 * Supprimer unite article.
	 *
	 * @param pOptionArticleId the unite article id
	 */
	public  void supprimerOptionArticle(Long pOptionArticleId);
	
	/**
	 * Modifierunite article.
	 *
	 * @param pOptionArticleValue the unite article value
	 * @return the string
	 */
	public String modifieruniteArticle(OptionArticleValue pOptionArticleValue);
	
	/**
	 * Recherche unite article by id.
	 *
	 * @param pOptionArticleId the unite article id
	 * @return the unite article value
	 */
	public OptionArticleValue rechercheOptionArticleById(Long pOptionArticleId);
	
	/**
	 * Liste unite article.
	 *
	 * @return the list
	 */
	public List<OptionArticleValue> listeOptionArticle();
}
