package com.gpro.consulting.tiers.logistique.domaine.gc.achat.reglement.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.DetailsReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.RechercheMulticritereReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.ResultatRechecheElementReglementAchatValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.achat.reglement.IDetailsReglementAchatDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.reglement.IReglementDomaine;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reglement.IDetailsReglementAchatPersistance;

/**
 * implementation of {@link IReglementDomaine}
 * 
 * @author Samer Hassen
 * @since 25/03/2020
 *
 */

@Component
public class DetailsReglementAchatDomaineImpl implements IDetailsReglementAchatDomaine{

	private static final Logger logger = LoggerFactory.getLogger(DetailsReglementAchatDomaineImpl.class);
	
	

	
	@Autowired
	private IDetailsReglementAchatPersistance detailsReglementAchatPersistance;
	



	@Override
	public ResultatRechecheElementReglementAchatValue rechercherMultiCritere(RechercheMulticritereReglementAchatValue request) {
		// TODO Auto-generated method stub
		return detailsReglementAchatPersistance.rechercherMultiCritere(request);
	}




	@Override
	public String update(DetailsReglementAchatValue detailsReglementValue) {
		// TODO Auto-generated method stub
		return detailsReglementAchatPersistance.update(detailsReglementValue);
	}
}
