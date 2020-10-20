package com.gpro.consulting.tiers.commun.domaine.elementBase;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitSerialisableValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticritereProduitSerialisableValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ResultatRechecheProduitSerialisableValue;

/**
 * The Interface IProduitSerialisablePersistance.
 * @author med
 */
public interface IProduitSerialisableDomaine {

	/**
	 * Creer produit.
	 *
	 * @param pProduitSerialisableValue the produit value
	 * @return the string
	 */
	public  String creerProduitSerialisable(ProduitSerialisableValue pProduitSerialisableValue);
	
	/**
	 * Supprimer produit.
	 *
	 * @param pProduitSerialisableId the produit id
	 */
	public  void supprimerProduitSerialisable(Long pProduitSerialisableId);
	
	/**
	 * Modifier produit.
	 *
	 * @param pProduitSerialisableValue the produit value
	 * @return the string
	 */
	public String modifierProduitSerialisable(ProduitSerialisableValue pProduitSerialisableValue);
	
	/**
	 * Recherche produit by id.
	 *
	 * @param pProduitSerialisableId the produit id
	 * @return the produit value
	 */
	public ProduitSerialisableValue rechercheProduitSerialisableById(Long pProduitSerialisableId);
	
	/**
	 * Liste produit.
	 *
	 * @return the list 
	 */
	public List<ProduitSerialisableValue> listeProduitSerialisable();
	
	//recherche multi criteres
	  public ResultatRechecheProduitSerialisableValue rechercherProduitSerialisableMultiCritere(RechercheMulticritereProduitSerialisableValue pRechercheProduitSerialisableMulitCritere);

	
}
