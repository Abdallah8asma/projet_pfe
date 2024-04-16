package com.gpro.consulting.tiers.logistique.persistance.gc.echeancier.inverse;

import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.echeancier.RechercheMulticritereDetailReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.echeancier.ResultatRechecheDetailReglementValue;


/**
 * @author Ameni Berrich
 *
 */
public interface IEcheancierInversePersistance {

	public ResultatRechecheDetailReglementValue rechercherMultiCritere(
			RechercheMulticritereDetailReglementValue request);
	
	public Double  getSommeMont(
			RechercheMulticritereDetailReglementValue request);


	public Double getMontantPayementsEnCours(RechercheMulticritereDetailReglementValue requestDetailReglement);

	public Double getMontantImpaye(RechercheMulticritereDetailReglementValue requestDetailReglement);
}
