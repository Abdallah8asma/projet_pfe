package com.gpro.consulting.tiers.logistique.persistance.gc.achat.echeancier;

import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.echeancier.RechercheMulticritereDetailReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.echeancier.ResultatRechecheDetailReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.echeancier.RechercheMulticritereDetailReglementValue;


/**
 * @author Ameni Berrich
 *
 */
public interface IEcheancierFournisseurPersistance {

	public ResultatRechecheDetailReglementAchatValue rechercherMultiCritere(
			RechercheMulticritereDetailReglementAchatValue request);
	

	public Double getSommeMont(RechercheMulticritereDetailReglementAchatValue requestDetailReglement);


	public Double getMontantPayementsEnCours(RechercheMulticritereDetailReglementAchatValue requestDetailReglement);


	public Double getMontantImpaye(RechercheMulticritereDetailReglementAchatValue requestDetailReglement);
}
