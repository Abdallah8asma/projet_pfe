package com.gpro.consulting.tiers.logistique.domaine.gs;

import java.util.Calendar;
import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ListProduitElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ListTraitFaconElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.BonInventaireValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.RechercheMulticritereBonInventaireValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.ResultatRechecheBonInventaireValue;

/**
 * BonInventaire Domaine interface
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
public interface IBonInventaireDomaine {

	public ResultatRechecheBonInventaireValue rechercherMultiCritere(
			RechercheMulticritereBonInventaireValue request);

	public String createBonInventaire(BonInventaireValue bonInventaireValue);

	public BonInventaireValue getBonInventaireById(Long id);

	public String updateBonInventaire(BonInventaireValue bonInventaireValue);

	public void deleteBonInventaire(Long id);

	

	public String getCurrentReference(Calendar instance, boolean b);

}
