package com.gpro.consulting.tiers.logistique.service.gc.achat.reglement.inverse.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.ElementReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.RechercheMulticritereReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.ResultatRechecheElementReglementAchatValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.achat.reglement.inverse.IElementReglementInverseAchatDomaine;
import com.gpro.consulting.tiers.logistique.service.gc.achat.reglement.inverse.IElementReglementInverseAchatService;
import com.gpro.consulting.tiers.logistique.service.gc.reglement.IElementReglementService;

/**
 * implementation of {@link IElementReglementService}
 * 
 * @author Samer  hassen
 * @since 08/03/2020
 *
 */

@Service
@Transactional
public class ElementReglementInverseAchatServiceImpl implements IElementReglementInverseAchatService{

	private static final Logger logger = LoggerFactory.getLogger(ElementReglementInverseAchatServiceImpl.class);

	@Autowired
	private IElementReglementInverseAchatDomaine elementReglementDomaine;

	@Override
	public List<ElementReglementAchatValue> getByRefernceFacture(String refernceFacture) {
		// TODO Auto-generated method stub
		return elementReglementDomaine.getByRefernceFacture(refernceFacture);
	}

	@Override
	public ResultatRechecheElementReglementAchatValue rechercherMultiCritere(RechercheMulticritereReglementAchatValue request) {
		// TODO Auto-generated method stub
		return elementReglementDomaine.rechercherMultiCritere(request);
	}

	@Override
	public Boolean existElementReglementByReferenceBR(String reference) {
		// TODO Auto-generated method stub
		return elementReglementDomaine.existElementReglementByReferenceBR(reference);
	}

	@Override
	public Boolean existElementReglementByReferenceFacture(String reference) {
		// TODO Auto-generated method stub
		return elementReglementDomaine.existElementReglementByReferenceFacture(reference);
	}

	@Override
	public Double getSumMontantPayerByReferenceFacture(String reference) {
		// TODO Auto-generated method stub
		return elementReglementDomaine.getSumMontantPayerByReferenceFacture(reference);
	}

	@Override
	public Double getSumMontantPayerByReferenceBL(String reference) {
		// TODO Auto-generated method stub
		return elementReglementDomaine.getSumMontantPayerByReferenceBL(reference);
	}
	

}
