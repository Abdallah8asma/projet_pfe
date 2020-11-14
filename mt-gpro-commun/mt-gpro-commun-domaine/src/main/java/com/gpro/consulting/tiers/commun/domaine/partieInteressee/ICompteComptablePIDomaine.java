package com.gpro.consulting.tiers.commun.domaine.partieInteressee;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.CompteComptablePIValue;

/**
 * The Interface ICompteComptablePIDomaine.
 * @author mohamed
 */
public interface ICompteComptablePIDomaine {
	
	/**
	 * Creer unite article.
	 *
	 * @param pCompteComptablePIValue the unite article value
	 * @return the string
	 */
	public  String creerCompteComptablePI(CompteComptablePIValue pCompteComptablePIValue);
	
	/**
	 * Supprimer unite article.
	 *
	 * @param pCompteComptablePIId the unite article id
	 */
	public  void supprimerCompteComptablePI(Long pCompteComptablePIId);
	
	/**
	 * Modifierunite article.
	 *
	 * @param pCompteComptablePIValue the unite article value
	 * @return the string
	 */
	public String modifieruniteArticle(CompteComptablePIValue pCompteComptablePIValue);
	
	/**
	 * Recherche unite article by id.
	 *
	 * @param pCompteComptablePIId the unite article id
	 * @return the unite article value
	 */
	public CompteComptablePIValue rechercheCompteComptablePIById(Long pCompteComptablePIId);
	
	/**
	 * Liste unite article.
	 *
	 * @return the list
	 */
	public List<CompteComptablePIValue> listeCompteComptablePI();

	public CompteComptablePIValue rechercheCompteComptablePIParDesignation(String designation);
}
