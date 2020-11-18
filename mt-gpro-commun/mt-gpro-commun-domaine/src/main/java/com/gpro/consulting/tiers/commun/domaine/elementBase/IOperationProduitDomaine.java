package com.gpro.consulting.tiers.commun.domaine.elementBase;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.OperationProduitValue;

/**
 * The Interface IOperationProduitDomaine.
 * @author mohamed
 */
public interface IOperationProduitDomaine {
	
	/**
	 * Creer unite article.
	 *
	 * @param pOperationProduitValue the unite article value
	 * @return the string
	 */
	public  String creerOperationProduit(OperationProduitValue pOperationProduitValue);
	
	/**
	 * Supprimer unite article.
	 *
	 * @param pOperationProduitId the unite article id
	 */
	public  void supprimerOperationProduit(Long pOperationProduitId);
	
	/**
	 * Modifierunite article.
	 *
	 * @param pOperationProduitValue the unite article value
	 * @return the string
	 */
	public String modifierOperationProduit(OperationProduitValue pOperationProduitValue);
	
	/**
	 * Recherche unite article by id.
	 *
	 * @param pOperationProduitId the unite article id
	 * @return the unite article value
	 */
	public OperationProduitValue rechercheOperationProduitById(Long pOperationProduitId);
	
	/**
	 * Liste unite article.
	 *
	 * @return the list
	 */
	public List<OperationProduitValue> listeOperationProduit();
}
