package com.gpro.consulting.tiers.logistique.domaine.gc.reglement.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ElementReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.RechercheMulticritereReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ResultatRechecheElementReglementValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.reglement.IElementReglementDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.reglement.IReglementDomaine;
import com.gpro.consulting.tiers.logistique.persistance.gc.reglement.IElementReglementPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.reglement.IReglementPersistance;

/**
 * implementation of {@link IReglementDomaine}
 * 
 * @author Samer Hassen
 * @since 25/03/2020
 *
 */

@Component
public class ElementReglementDomaineImpl implements IElementReglementDomaine{

	private static final Logger logger = LoggerFactory.getLogger(ElementReglementDomaineImpl.class);
	
	
	@Autowired
	private IReglementPersistance reglementPersistance;
	
	
	@Autowired
	private IElementReglementPersistance elementReglementPersistance;
	


	@Override
	public List<ElementReglementValue> getByRefernceFacture(String refernceFacture) {
		// TODO Auto-generated method stub
		return elementReglementPersistance.getByRefernceFacture(refernceFacture);
	}

	@Override
	public ResultatRechecheElementReglementValue rechercherMultiCritere(RechercheMulticritereReglementValue request) {
		// TODO Auto-generated method stub
		return elementReglementPersistance.rechercherMultiCritere(request);
	}

	@Override
	public Boolean existElementReglementByReferenceBL(String reference) {
		// TODO Auto-generated method stub
		return elementReglementPersistance.existElementReglementByReferenceBL(reference);
	}

	@Override
	public Boolean existElementReglementByReferenceFacture(String reference) {
		// TODO Auto-generated method stub
		return elementReglementPersistance.existElementReglementByReferenceFacture(reference);
	}

	@Override
	public List<ElementReglementValue> getByRefFactureExact(String refernceFacture) {
		// TODO Auto-generated method stub
		return elementReglementPersistance.getByRefFactureExact(refernceFacture);
	}

	@Override
	public List<ElementReglementValue> getByRefBLExact(String refernceFacture) {
		return elementReglementPersistance.getByRefBLExact(refernceFacture);
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
