package com.gpro.consulting.tiers.commun.domaine.elementBase;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.CompteComptableValue;

/**
 * The Interface ICompteComptableDomaine.
 * @author mohamed
 */
public interface ICompteComptableDomaine {
	
	/**
	 * Creer unite article.
	 *
	 * @param pCompteComptableValue the unite article value
	 * @return the string
	 */
	public  String creerCompteComptable(CompteComptableValue pCompteComptableValue);
	
	/**
	 * Supprimer unite article.
	 *
	 * @param pCompteComptableId the unite article id
	 */
	public  void supprimerCompteComptable(Long pCompteComptableId);
	
	/**
	 * Modifierunite article.
	 *
	 * @param pCompteComptableValue the unite article value
	 * @return the string
	 */
	public String modifieruniteArticle(CompteComptableValue pCompteComptableValue);
	
	/**
	 * Recherche unite article by id.
	 *
	 * @param pCompteComptableId the unite article id
	 * @return the unite article value
	 */
	public CompteComptableValue rechercheCompteComptableById(Long pCompteComptableId);
	
	/**
	 * Liste unite article.
	 *
	 * @return the list
	 */
	public List<CompteComptableValue> listeCompteComptable();
}
