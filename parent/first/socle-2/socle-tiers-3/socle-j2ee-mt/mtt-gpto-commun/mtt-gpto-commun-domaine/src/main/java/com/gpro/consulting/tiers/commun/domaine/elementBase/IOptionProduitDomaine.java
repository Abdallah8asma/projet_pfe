package com.gpro.consulting.tiers.commun.domaine.elementBase;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.OptionProduitValue;

/**
 * The Interface IOptionProduitDomaine.
 * @author mohamed
 */
public interface IOptionProduitDomaine {
	
	/**
	 * Creer unite article.
	 *
	 * @param pOptionProduitValue the unite article value
	 * @return the string
	 */
	public  String creerOptionProduit(OptionProduitValue pOptionProduitValue);
	
	/**
	 * Supprimer unite article.
	 *
	 * @param pOptionProduitId the unite article id
	 */
	public  void supprimerOptionProduit(Long pOptionProduitId);
	
	/**
	 * Modifierunite article.
	 *
	 * @param pOptionProduitValue the unite article value
	 * @return the string
	 */
	public String modifieruniteProduit(OptionProduitValue pOptionProduitValue);
	
	/**
	 * Recherche unite article by id.
	 *
	 * @param pOptionProduitId the unite article id
	 * @return the unite article value
	 */
	public OptionProduitValue rechercheOptionProduitById(Long pOptionProduitId);
	
	/**
	 * Liste unite article.
	 *
	 * @return the list
	 */
	public List<OptionProduitValue> listeOptionProduit();
}
