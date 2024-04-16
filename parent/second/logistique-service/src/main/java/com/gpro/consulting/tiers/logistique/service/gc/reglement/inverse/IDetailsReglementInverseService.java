package com.gpro.consulting.tiers.logistique.service.gc.reglement.inverse;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.echeancier.RechercheMulticritereDetailReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.DetailsReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.RechercheMulticritereReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ResultatRechecheElementReglementValue;

/**
 * Element Reglement Service interface
 * 
 * @author Samer Hassen
 * @since 25/03/2020
 */
public interface IDetailsReglementInverseService {


	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ResultatRechecheElementReglementValue rechercherMultiCritere(
			RechercheMulticritereReglementValue request);
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public Double getMontantPayer(RechercheMulticritereDetailReglementValue reqDetailReglement);
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public DetailsReglementValue getById(Long detailReglementId);
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String update(DetailsReglementValue detailsReglementValue);




}
