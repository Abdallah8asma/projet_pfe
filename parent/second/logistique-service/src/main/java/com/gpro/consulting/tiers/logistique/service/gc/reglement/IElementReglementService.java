package com.gpro.consulting.tiers.logistique.service.gc.reglement;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ElementReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.RechercheMulticritereReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ResultatRechecheElementReglementValue;

/**
 * Element Reglement Service interface
 * 
 * @author Samer Hassen
 * @since 25/03/2020
 */
public interface IElementReglementService {

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<ElementReglementValue> getByRefernceFacture(String refernceFacture);
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ResultatRechecheElementReglementValue rechercherMultiCritere(
			RechercheMulticritereReglementValue request);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
    public Boolean existElementReglementByReferenceBL(String reference);
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public Boolean existElementReglementByReferenceFacture(String reference);
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<ElementReglementValue> getByRefFactureExact(String refernceFacture);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public Double getSumMontantPayerByReferenceFacture(String reference);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public Double getSumMontantPayerByReferenceBL(String reference);

}
