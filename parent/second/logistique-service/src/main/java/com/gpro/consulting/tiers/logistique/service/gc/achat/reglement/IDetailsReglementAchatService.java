package com.gpro.consulting.tiers.logistique.service.gc.achat.reglement;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.DetailsReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.RechercheMulticritereReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.ResultatRechecheElementReglementAchatValue;

/**
 * Element Reglement Service interface
 * 
 * @author Samer Hassen
 * @since 25/03/2020
 */
public interface IDetailsReglementAchatService {


	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ResultatRechecheElementReglementAchatValue rechercherMultiCritere(
			RechercheMulticritereReglementAchatValue request);

	

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String update(DetailsReglementAchatValue detailsReglementValue);




}
