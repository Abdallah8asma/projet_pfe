package com.gpro.consulting.tiers.commun.domaine.elementBase;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.FamilleProduitValue;
/**
 * The Interface IProduitPersistance.
 * @author med
 */

public interface IFamilleProduitDomaine {

	/**
	 * Creer famille produit.
	 *
	 * @param pFamilleProduitValue the famille produit value
	 * @return the string
	 */
	public  String creerFamilleProduit(FamilleProduitValue pFamilleProduitValue);
	
	/**
	 * Supprimer sous famille produit.
	 *
	 * @param pFamilleProduitId the famille produit id
	 */
	public  void supprimerSousFamilleProduit(Long pFamilleProduitId);
	
	/**
	 * Modifier famille produit.
	 *
	 * @param pFamilleProduitValue the famille produit value
	 * @return the string
	 */
	public String modifierFamilleProduit(FamilleProduitValue pFamilleProduitValue);
	
	/**
	 * Recherche famille produit by id.
	 *
	 * @param pFamilleProduitId the famille produit id
	 * @return the famille produit value
	 */
	public FamilleProduitValue rechercheFamilleProduitById(Long pFamilleProduitId);
	
	/**
	 * Liste famille produit.
	 *
	 * @return the list
	 */
	public List<FamilleProduitValue> listeFamilleProduit();
}
