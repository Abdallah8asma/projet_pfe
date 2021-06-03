package com.gpro.consulting.tiers.logistique.domaine.gc.achat.reglement.inverse;

import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.DetailsReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.RechercheMulticritereReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.ResultatRechecheElementReglementAchatValue;

/**
 * Element Reglement Domaine interface
 * 
 * @author Samer Hassen
 * @since 25/03/2020
 *
 */
public interface IDetailsReglementInverseAchatDomaine {



	public ResultatRechecheElementReglementAchatValue rechercherMultiCritere(
			RechercheMulticritereReglementAchatValue request);

	public String update(DetailsReglementAchatValue detailsReglementValue);


}
