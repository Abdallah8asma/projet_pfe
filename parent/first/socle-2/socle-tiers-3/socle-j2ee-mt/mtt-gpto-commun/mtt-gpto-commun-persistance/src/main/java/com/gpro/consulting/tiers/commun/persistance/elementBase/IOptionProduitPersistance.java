package com.gpro.consulting.tiers.commun.persistance.elementBase;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.OptionProduitValue;


/**
 * The Interface IUntieProduit.
 * @author $mohamed
 */

public interface IOptionProduitPersistance {
	/**
	 * ajouter    unite Produit*.
	 *
	 * @return the string
	 */
	/** create OptionProduit */
	public  String creerOptionProduit(OptionProduitValue pOptionProduitValue);
	/**
	 * supprimer  unite Produit*.
	 *
	 */
	public  void supprimerOptionProduit(Long pOptionProduitValueId);
	/**
	 * modifier unite Produit*.
	 *
	 * @return the string
	 */
	public String modifierOptionProduit(OptionProduitValue pOptionProduitValue);
	/**
	 *  recherche by id unite Produit*.
	 *
	 */
	public OptionProduitValue  rechercheOptionProduitById(Long  pOptionProduitValueId);
	/**
	 * afficher  liste unite   Produit*.
	 *
	 * @return the list
	 */
	public List<OptionProduitValue> listeOptionProduit();

}
