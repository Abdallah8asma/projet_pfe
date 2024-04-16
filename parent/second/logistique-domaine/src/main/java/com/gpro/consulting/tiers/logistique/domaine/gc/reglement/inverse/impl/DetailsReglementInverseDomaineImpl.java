package com.gpro.consulting.tiers.logistique.domaine.gc.reglement.inverse.impl;

import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.gc.chart.value.ResultBestElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.echeancier.RechercheMulticritereDetailReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.DetailsReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.RechercheMulticritereReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ResultatRechecheElementReglementValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.reglement.IReglementDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.reglement.inverse.IDetailsReglementInverseDomaine;
import com.gpro.consulting.tiers.logistique.persistance.gc.reglement.inverse.IDetailsReglementInversePersistance;

/**
 * implementation of {@link IReglementDomaine}
 * 
 * @author Samer Hassen
 * @since 25/03/2020
 *
 */

@Component
public class DetailsReglementInverseDomaineImpl implements IDetailsReglementInverseDomaine{

	private static final Logger logger = LoggerFactory.getLogger(DetailsReglementInverseDomaineImpl.class);
	
	

	
	@Autowired
	private IDetailsReglementInversePersistance detailsReglementPersistance;
	
	@PersistenceContext
	private EntityManager entityManager;
	



	@Override
	public ResultatRechecheElementReglementValue rechercherMultiCritere(RechercheMulticritereReglementValue request) {
		// TODO Auto-generated method stub
		return detailsReglementPersistance.rechercherMultiCritere(request);
	}




	@Override
	public ResultBestElementValue rechercherMultiCritereReglementEchusDuJours(Calendar instance, boolean b,Long boutiqueId) {
		
		return detailsReglementPersistance.rechercherMultiCritereReglementEchusDuJours(instance,  b, boutiqueId);
		
		
	}




	@Override
	public Double getMontantPayer(RechercheMulticritereDetailReglementValue reqDetailReglement) {
		return detailsReglementPersistance.getMontantPayer(reqDetailReglement);
	}




	@Override
	public DetailsReglementValue getById(Long detailReglementId) {
		// TODO Auto-generated method stub
		return detailsReglementPersistance.getById(detailReglementId);
	}




	@Override
	public String update(DetailsReglementValue detailsReglementValue) {
		// TODO Auto-generated method stub
		return detailsReglementPersistance.update(detailsReglementValue);
	}
			    
			    
			    
			  
}
