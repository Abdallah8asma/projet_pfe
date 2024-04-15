package com.gpro.consulting.tiers.commun.persistance.elementBase;
import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticritereProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ResultatRechecheProduitValue;

/**
 * The Interface IProduitPersistance.
 * @author med
 */
public interface IProduitPersistance {
	
	/**
	 * Creer produit.
	 *
	 * @param pProduitValue the produit value
	 * @return the string
	 */
	public  String creerProduit(ProduitValue pProduitValue);
	
	/**
	 * Supprimer produit.
	 *
	 * @param pProduitId the produit id
	 */
	public  void supprimerProduit(Long pProduitId);
	
	/**
	 * Modifier produit.
	 *
	 * @param pProduitValue the produit value
	 * @return the string
	 */
	public String modifierProduit(ProduitValue pProduitValue);
	
	/**
	 * Recherche produit by id.
	 *
	 * @param pProduitId the produit id
	 * @return the produit value
	 */
	public ProduitValue rechercheProduitById(Long pProduitId);
	
	/**
	 * Liste produit.
	 *
	 * @return the list 
	 */
	public List<ProduitValue> listeProduit();
	
	
	
	
	//recherche multi criteres
	
	 public ResultatRechecheProduitValue rechercherProduitMultiCritere(RechercheMulticritereProduitValue pRechercheProduitMulitCritere);
	 
	 public String rechercheDesignationProduitById(Long pProduitId);
	 
	 public ProduitValue rechercheProduitParReference(String reference);
	 public List<ProduitValue> rechercheProduitFinance();

	public List<ProduitValue> rechercheProduitNonFinance();
}
