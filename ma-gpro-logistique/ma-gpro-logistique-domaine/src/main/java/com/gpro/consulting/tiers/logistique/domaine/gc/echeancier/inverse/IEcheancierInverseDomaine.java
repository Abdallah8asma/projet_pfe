package com.gpro.consulting.tiers.logistique.domaine.gc.echeancier.inverse;

import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.echeancier.RechercheMulticritereDetailReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.echeancier.ResultatRechecheDetailReglementValue;

/**
 * @author Ameni Berrich
 *
 */
public interface IEcheancierInverseDomaine {

	public ResultatRechecheDetailReglementValue rechercherMultiCritere(
			RechercheMulticritereDetailReglementValue request);
}
