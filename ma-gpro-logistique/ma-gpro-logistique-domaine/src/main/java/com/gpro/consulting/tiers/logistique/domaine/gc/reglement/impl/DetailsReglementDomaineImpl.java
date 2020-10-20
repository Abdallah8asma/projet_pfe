package com.gpro.consulting.tiers.logistique.domaine.gc.reglement.impl;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.chart.value.ResultBestElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.echeancier.RechercheMulticritereDetailReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.RechercheMulticritereReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ResultatRechecheElementReglementValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.reglement.IDetailsReglementDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.reglement.IReglementDomaine;
import com.gpro.consulting.tiers.logistique.persistance.gc.reglement.IDetailsReglementPersistance;

/**
 * implementation of {@link IReglementDomaine}
 * 
 * @author Samer Hassen
 * @since 25/03/2020
 *
 */

@Component
public class DetailsReglementDomaineImpl implements IDetailsReglementDomaine{

	private static final Logger logger = LoggerFactory.getLogger(DetailsReglementDomaineImpl.class);
	
	

	
	@Autowired
	private IDetailsReglementPersistance detailsReglementPersistance;
	
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
			    
			    
			    
			  
}
