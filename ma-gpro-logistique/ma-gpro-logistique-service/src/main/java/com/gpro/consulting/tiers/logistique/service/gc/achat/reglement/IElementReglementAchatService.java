package com.gpro.consulting.tiers.logistique.service.gc.achat.reglement;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.ElementReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.RechercheMulticritereReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.ResultatRechecheElementReglementAchatValue;

/**
 * Element Reglement Service interface
 * 
 * @author Samer Hassen
 * @since 25/03/2020
 */
public interface IElementReglementAchatService {

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<ElementReglementAchatValue> getByRefernceFacture(String refernceFacture);
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ResultatRechecheElementReglementAchatValue rechercherMultiCritere(
			RechercheMulticritereReglementAchatValue request);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
    public Boolean existElementReglementByReferenceBR(String reference);
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public Boolean existElementReglementByReferenceFacture(String reference);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public Double getSumMontantPayerByReferenceFacture(String reference);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public Double getSumMontantPayerByReferenceBL(String reference);
}
