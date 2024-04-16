package com.gpro.consulting.tiers.logistique.service.gc.achat.echancier.inverse;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.echeancier.RechercheMulticritereDetailReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.echeancier.ResultatRechecheDetailReglementAchatValue;

public interface IEcheancierInverseFournisseurService {
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ResultatRechecheDetailReglementAchatValue rechercherMultiCritere(
			RechercheMulticritereDetailReglementAchatValue request);

}
