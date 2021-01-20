package com.gpro.consulting.tiers.commun.domaine.elementBase;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.PrixClientValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RecherchePrixClientValue;

/**
 * The Interface IProduitPersistance.
 * @author EL Araichi Oussama
 */
public interface IPrixClientDomaine {
	
	/**
	 * Creer PrixClient.
	 *
	 * @param pPrixClientValue the PrixClient value
	 * @return the string
	 */
	public  String creerPrixClient(PrixClientValue pPrixClientValue);
	
	
	
	/**
	 * Creer PrixClient.
	 *
	 * @param pPrixClientValue the PrixClient value
	 * @return the string
	 */
	public  String creerPrixClientListe(List <ProduitValue> pProduitValue);
	
	
	
	
	/**
	 * Supprimer PrixClient.
	 *
	 * @param pPrixClientId the PrixClientid
	 */
	public  void supprimerPrixClient(Long pPrixClientID);
	
	/**
	 * Modifier PrixClient.
	 *
	 * @param pPrixClientValue the PrixClient value
	 * @return the string
	 */
	public String modifierPrixClient(PrixClientValue prixClientValue);
	
	/**
	 * Recherche PrixClient by id.
	 *
	 * @param pPrixClientId the PrixClient id
	 * @return the PrixClientValue
	 */
	public PrixClientValue recherchePrixClientById(Long pPrixClientID);
	
	/**
	 * Liste PrixClient.
	 *
	 * @return the list
	 */
	public List<PrixClientValue> listPrixClientValue();
	
	/**recherche multicriteres ClientId,Produitid*/
	
	public PrixClientValue rechercherPrixClientValue(RecherchePrixClientValue pRecherchePrixClientMulitCritere);
	
	public List<PrixClientValue> rechchercheMultiCriterePrixClient(
				RecherchePrixClientValue pRecherchePrixClientMulitCritere);



	public String creerPrixArticleClient(List<ArticleValue> pProduitValue);



}



