package com.gpro.consulting.tiers.commun.persistance.elementBase;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.PrixClientValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RecherchePrixClientValue;

public interface IPrixClientPersistance {
	
public  String creerPrixClient(PrixClientValue pPrixClient);
	
	/**
	 * Supprimer Prixclient.
	 *
	 * @param pFamilleProduitId the PrixClient id
	 */
	public  void supprimerPrixClient(Long pPrixClientID);
	
	/**
	 * Modifier PrixClient.
	 *
	 * @param pPrixClientValue to the Prixclient  value
	 * @return the string
	 */
	public String modifierPrixClient(PrixClientValue pPrixClientValue);
	
	/**
	 * Recherche PrixClient by id.
	 *
	 * @param pPrixClientId the PrixClient id
	 * @return the PrixClient value
	 */
	public PrixClientValue recherchePrixClientById(Long pPrixClientId);
	
	
	/**
	 * Liste PrixClient.
	 *
	 * @return the list
	 */
	public List<PrixClientValue> listePrixClient();

	

	public PrixClientValue rechcherchePrixClientMC(
			RecherchePrixClientValue pRecherchePrixClientValue);
	
	public List<PrixClientValue> rechchercheMultiCriterePrixClient(
			RecherchePrixClientValue pRecherchePrixClientValue);

}
