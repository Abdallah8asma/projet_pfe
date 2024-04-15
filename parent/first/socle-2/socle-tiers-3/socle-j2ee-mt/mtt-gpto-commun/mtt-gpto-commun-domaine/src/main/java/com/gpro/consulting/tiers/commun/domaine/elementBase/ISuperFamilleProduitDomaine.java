package com.gpro.consulting.tiers.commun.domaine.elementBase;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.SuperFamilleProduitValue;
/**
 * The Interface IProduitPersistance.
 * @author med
 */

public interface ISuperFamilleProduitDomaine {

	/**
	 * Creer famille produit.
	 *
	 * @param pSuperFamilleProduitValue the famille produit value
	 * @return the string
	 */
	public  String creerSuperFamilleProduit(SuperFamilleProduitValue pSuperFamilleProduitValue);
	
	/**
	 * Supprimer sous famille produit.
	 *
	 * @param pSuperFamilleProduitId the famille produit id
	 */
	public  void supprimerSuperFamilleProduit(Long pSuperFamilleProduitId);
	
	/**
	 * Modifier famille produit.
	 *
	 * @param pSuperFamilleProduitValue the famille produit value
	 * @return the string
	 */
	public String modifierSuperFamilleProduit(SuperFamilleProduitValue pSuperFamilleProduitValue);
	
	/**
	 * Recherche famille produit by id.
	 *
	 * @param pSuperFamilleProduitId the famille produit id
	 * @return the famille produit value
	 */
	public SuperFamilleProduitValue rechercheSuperFamilleProduitById(Long pSuperFamilleProduitId);
	
	/**
	 * Liste famille produit.
	 *
	 * @return the list
	 */
	public List<SuperFamilleProduitValue> listeSuperFamilleProduit();
}
