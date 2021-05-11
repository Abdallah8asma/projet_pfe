package com.gpro.consulting.tiers.logistique.domaine.gc.achat.reglement.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.ElementReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.RechercheMulticritereReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.ResultatRechecheElementReglementAchatValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.achat.reglement.IElementReglementAchatDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.reglement.IReglementDomaine;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reglement.IElementReglementAchatPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reglement.IReglementAchatPersistance;

/**
 * implementation of {@link IReglementDomaine}
 * 
 * @author Samer Hassen
 * @since 25/03/2020
 *
 */

@Component
public class ElementReglementAchatDomaineImpl implements IElementReglementAchatDomaine{

	private static final Logger logger = LoggerFactory.getLogger(ElementReglementAchatDomaineImpl.class);
	
	
	@Autowired
	private IReglementAchatPersistance reglementAchatPersistance;
	
	
	@Autowired
	private IElementReglementAchatPersistance elementReglementPersistance;
	


	@Override
	public List<ElementReglementAchatValue> getByRefernceFacture(String refernceFacture) {
		// TODO Auto-generated method stub
		return elementReglementPersistance.getByRefernceFacture(refernceFacture);
	}

	@Override
	public ResultatRechecheElementReglementAchatValue rechercherMultiCritere(RechercheMulticritereReglementAchatValue request) {
		// TODO Auto-generated method stub
		return elementReglementPersistance.rechercherMultiCritere(request);
	}

	@Override
	public Boolean existElementReglementByReferenceBR(String reference) {
		// TODO Auto-generated method stub
		return elementReglementPersistance.existElementReglementByReferenceBR(reference);
	}

	@Override
	public Boolean existElementReglementByReferenceFacture(String reference) {
		// TODO Auto-generated method stub
		return elementReglementPersistance.existElementReglementByReferenceFacture(reference);
	}

	@Override
	public List<ElementReglementAchatValue> getByRefFactureExact(String refernceFacture) {
		// TODO Auto-generated method stub
		return elementReglementPersistance.getByRefFactureExact(refernceFacture);
	}

	@Override
	public List<ElementReglementAchatValue> getByRefBLExact(String refernceBL) {
		// TODO Auto-generated method stub
		return elementReglementPersistance.getByRefBLExact(refernceBL);
	}

	@Override
	public void deleteElementReglementById(Long id) {
		elementReglementPersistance.deleteElementReglementById(id);
		
	}

	@Override
	public Double getSumMontantPayerByReferenceFacture(String reference) {
		// TODO Auto-generated method stub
		return elementReglementPersistance.getSumMontantPayerByReferenceFacture(reference);
	}

	@Override
	public Double getSumMontantPayerByReferenceBL(String reference) {
		// TODO Auto-generated method stub
		return elementReglementPersistance.getSumMontantPayerByReferenceBL(reference);
	}
}
