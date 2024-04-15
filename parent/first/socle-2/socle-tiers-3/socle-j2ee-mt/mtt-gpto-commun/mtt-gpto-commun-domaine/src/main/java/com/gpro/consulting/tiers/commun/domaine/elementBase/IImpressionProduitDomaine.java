package com.gpro.consulting.tiers.commun.domaine.elementBase;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ImpressionProduitValue;

/**
 * The Interface IImpressionProduitDomaine.
 * @author mohamed
 */
public interface IImpressionProduitDomaine {
	
	/**
	 * Creer unite article.
	 *
	 * @param pImpressionProduitValue the unite article value
	 * @return the string
	 */
	public  String creerImpressionProduit(ImpressionProduitValue pImpressionProduitValue);
	
	/**
	 * Supprimer unite article.
	 *
	 * @param pImpressionProduitId the unite article id
	 */
	public  void supprimerImpressionProduit(Long pImpressionProduitId);
	
	/**
	 * Modifierunite article.
	 *
	 * @param pImpressionProduitValue the unite article value
	 * @return the string
	 */
	public String modifieruniteArticle(ImpressionProduitValue pImpressionProduitValue);
	
	/**
	 * Recherche unite article by id.
	 *
	 * @param pImpressionProduitId the unite article id
	 * @return the unite article value
	 */
	public ImpressionProduitValue rechercheImpressionProduitById(Long pImpressionProduitId);
	
	/**
	 * Liste unite article.
	 *
	 * @return the list
	 */
	public List<ImpressionProduitValue> listeImpressionProduit();
}
