package com.gpro.consulting.tiers.commun.domaine.elementBase;

import java.util.List;
import java.util.Map;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticritereProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ResultatRechecheProduitValue;

/**
 * The Interface IProduitPersistance.
 * @author med
 */
public interface IProduitDomaine {

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
//recherche multi client
	public ResultatRechecheProduitValue rechercherProduitMultiCritereClient(RechercheMulticritereProduitValue pRechercheProduitMulitCritere);

	public ProduitValue rechercheProduitParReference(String reference);

	public Map<Long, ProduitValue> mapProduitById();

	public List<ProduitValue> rechercheProduitFinance();

	public List<ProduitValue> rechercheProduitNonFinance();
}
