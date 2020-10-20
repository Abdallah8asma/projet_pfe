package com.gpro.consulting.tiers.logistique.service.gc.echancier;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.echeancier.RechercheMulticritereDetailReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.echeancier.ResultatRechecheDetailReglementValue;

/**
 * @author Ameni Berrich
 *
 */
public interface IEchancierService {
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ResultatRechecheDetailReglementValue rechercherMultiCritere(
			RechercheMulticritereDetailReglementValue request);
}
