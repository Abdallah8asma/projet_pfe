package com.gpro.consulting.tiers.logistique.service.gc.reglement.inverse.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ElementReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.RechercheMulticritereReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ResultatRechecheElementReglementValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.reglement.inverse.IElementReglementInverseDomaine;
import com.gpro.consulting.tiers.logistique.service.gc.reglement.IElementReglementService;
import com.gpro.consulting.tiers.logistique.service.gc.reglement.inverse.IElementReglementInverseService;

/**
 * implementation of {@link IElementReglementService}
 * 
 * @author Samer  hassen
 * @since 08/03/2020
 *
 */

@Service
@Transactional
public class ElementReglementInverseServiceImpl implements IElementReglementInverseService{

	private static final Logger logger = LoggerFactory.getLogger(ElementReglementInverseServiceImpl.class);

	@Autowired
	private IElementReglementInverseDomaine elementReglementDomaine;

	@Override
	public List<ElementReglementValue> getByRefernceFacture(String refernceFacture) {
		// TODO Auto-generated method stub
		return elementReglementDomaine.getByRefernceFacture(refernceFacture);
	}

	@Override
	public ResultatRechecheElementReglementValue rechercherMultiCritere(RechercheMulticritereReglementValue request) {
		// TODO Auto-generated method stub
		return elementReglementDomaine.rechercherMultiCritere(request);
	}

	@Override
	public Boolean existElementReglementByReferenceBL(String reference) {
		// TODO Auto-generated method stub
		return elementReglementDomaine.existElementReglementByReferenceBL(reference);
	}

	@Override
	public Boolean existElementReglementByReferenceFacture(String reference) {
		// TODO Auto-generated method stub
		return elementReglementDomaine.existElementReglementByReferenceFacture(reference);
	}

	@Override
	public List<ElementReglementValue> getByRefFactureExact(String refernceFacture) {
		// TODO Auto-generated method stub
		return elementReglementDomaine.getByRefFactureExact(refernceFacture);
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
