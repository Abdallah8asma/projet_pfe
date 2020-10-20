package com.gpro.consulting.tiers.logistique.service.gc.reglement;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.echeancier.RechercheMulticritereDetailReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.RechercheMulticritereReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ResultatRechecheElementReglementValue;

/**
 * Element Reglement Service interface
 * 
 * @author Samer Hassen
 * @since 25/03/2020
 */
public interface IDetailsReglementService {


	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ResultatRechecheElementReglementValue rechercherMultiCritere(
			RechercheMulticritereReglementValue request);
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public Double getMontantPayer(RechercheMulticritereDetailReglementValue reqDetailReglement);




}
