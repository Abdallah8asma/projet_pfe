package com.gpro.consulting.tiers.logistique.domaine.gs.transfert;

import java.util.Calendar;

import com.gpro.consulting.tiers.logistique.coordination.gs.transfert.value.BonTransfertValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.transfert.value.RechercheMulticritereBonTransfertValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.transfert.value.ResultatRechecheBonTransfertValue;

/**
 * Bon Transfert Domaine interface
 * 
 * @author Samer 
 * @since 27/01/2016
 *
 */
public interface IBonTransfertDomaine {

	public ResultatRechecheBonTransfertValue rechercherMultiCritere(
			RechercheMulticritereBonTransfertValue request);

	public String createBonTransfert(BonTransfertValue bonTransfertValue);

	public BonTransfertValue getBonTransfertById(Long id);

	public String updateBonTransfert(BonTransfertValue bonTransfertValue);

	public void deleteBonTransfert(Long id);

	

	public String getCurrentReference(String type,Calendar instance, boolean b);

	public BonTransfertValue getBonTransfertByReference(String reference);

	public BonTransfertValue validerBTsortieByReference(String reference);

}
