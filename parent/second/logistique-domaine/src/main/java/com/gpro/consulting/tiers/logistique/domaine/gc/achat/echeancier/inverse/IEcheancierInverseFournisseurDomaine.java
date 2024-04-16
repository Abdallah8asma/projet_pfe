package com.gpro.consulting.tiers.logistique.domaine.gc.achat.echeancier.inverse;

import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.echeancier.RechercheMulticritereDetailReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.echeancier.ResultatRechecheDetailReglementAchatValue;

/**
 * @author Ameni Berrich
 *
 */
public interface IEcheancierInverseFournisseurDomaine {

	public ResultatRechecheDetailReglementAchatValue rechercherMultiCritere(
			RechercheMulticritereDetailReglementAchatValue request);
}
