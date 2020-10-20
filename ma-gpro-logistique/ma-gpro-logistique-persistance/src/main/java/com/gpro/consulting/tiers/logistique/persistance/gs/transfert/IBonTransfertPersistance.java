package com.gpro.consulting.tiers.logistique.persistance.gs.transfert;

import com.gpro.consulting.tiers.logistique.coordination.gs.transfert.value.BonTransfertValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.transfert.value.RechercheMulticritereBonTransfertValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.transfert.value.ResultatRechecheBonTransfertValue;

/**
 * BonTransfert Persistance interface
 * 
 * @author Samer Hassen
 * @since 21/05/2020
 *
 */
public interface IBonTransfertPersistance {

	public ResultatRechecheBonTransfertValue rechercherMultiCritere(
			RechercheMulticritereBonTransfertValue request);

	public String createBonTransfert(BonTransfertValue bonTransfertValue);

	public BonTransfertValue getBonTransfertById(Long id);

	public String updateBonTransfert(BonTransfertValue bonTransfertValue);

	public void deleteBonTransfert(Long id);

	public BonTransfertValue getBonTransfertByReference(String reference);

	public BonTransfertValue validerBTsortieByReference(String reference);
	
	
 
	public BonTransfertValue getBonTransfertByReferenceSortie(String reference);
 	
 	





}
