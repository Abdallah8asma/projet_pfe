package com.gpro.consulting.tiers.logistique.domaine.gc.achat.echeancier;

import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.echeancier.RechercheMulticritereDetailReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.echeancier.ResultatRechecheDetailReglementAchatValue;

/**
 * @author Ameni Berrich
 *
 */
public interface IEcheancierFournisseurDomaine {

	public ResultatRechecheDetailReglementAchatValue rechercherMultiCritere(
			RechercheMulticritereDetailReglementAchatValue request);
}
