package com.gpro.consulting.tiers.commun.persistance.elementBase;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.OperationProduitValue;


/**
 * The Interface IUntieProduit.
 * @author $mohamed
 */

public interface IOperationProduitPersistance {
	/**
	 * ajouter    unite Produit*.
	 *
	 * @return the string
	 */
	/** create OperationProduit */
	public  String creerOperationProduit(OperationProduitValue pOperationProduitValue);
	/**
	 * supprimer  unite Produit*.
	 *
	 */
	public  void supprimerOperationProduit(Long pOperationProduitValueId);
	/**
	 * modifier unite Produit*.
	 *
	 * @return the string
	 */
	public String modifierOperationProduit(OperationProduitValue pOperationProduitValue);
	/**
	 *  recherche by id unite Produit*.
	 *
	 */
	public OperationProduitValue  rechercheOperationProduitById(Long  pOperationProduitValueId);
	/**
	 * afficher  liste unite   Produit*.
	 *
	 * @return the list
	 */
	public List<OperationProduitValue> listeOperationProduit();

}
