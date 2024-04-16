package com.gpro.consulting.tiers.logistique.persistance.gc.achat.reglement;

import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.DetailsReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.RechercheMulticritereReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.ResultatRechecheElementReglementAchatValue;

/**
 * Details Reglement Persistance interface
 * 
 * @author Samer Hassen
 * @since 27/03/2020
 *
 */
public interface IDetailsReglementAchatPersistance {



	public ResultatRechecheElementReglementAchatValue rechercherMultiCritere(
			RechercheMulticritereReglementAchatValue request);

	public String update(DetailsReglementAchatValue detailsReglementValue);

}
