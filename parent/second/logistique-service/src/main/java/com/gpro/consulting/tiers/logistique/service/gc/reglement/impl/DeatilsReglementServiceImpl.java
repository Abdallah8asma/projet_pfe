package com.gpro.consulting.tiers.logistique.service.gc.reglement.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.echeancier.RechercheMulticritereDetailReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.DetailsReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.RechercheMulticritereReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ResultatRechecheElementReglementValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.reglement.IDetailsReglementDomaine;
import com.gpro.consulting.tiers.logistique.service.gc.reglement.IDetailsReglementService;
import com.gpro.consulting.tiers.logistique.service.gc.reglement.IReglementService;

/**
 * implementation of {@link IReglementService}
 * 
 * @author Wahid Gazzah
 * @since 01/07/2016
 *
 */

@Service
@Transactional
public class DeatilsReglementServiceImpl implements IDetailsReglementService{

	private static final Logger logger = LoggerFactory.getLogger(DeatilsReglementServiceImpl.class);

	@Autowired
	private IDetailsReglementDomaine detailsReglementDomaine;



	@Override
	public ResultatRechecheElementReglementValue rechercherMultiCritere(RechercheMulticritereReglementValue request) {
		// TODO Auto-generated method stub
		return detailsReglementDomaine.rechercherMultiCritere(request);
	}



	@Override
	public Double getMontantPayer(RechercheMulticritereDetailReglementValue reqDetailReglement) {
		// TODO Auto-generated method stub
		return detailsReglementDomaine.getMontantPayer(reqDetailReglement);
	}



	@Override
	public DetailsReglementValue getById(Long detailReglementId) {
		// TODO Auto-generated method stub
		return detailsReglementDomaine.getById(detailReglementId);
	}



	@Override
	public String update(DetailsReglementValue detailsReglementValue) {
		
		return detailsReglementDomaine.update(detailsReglementValue);
		
	}
	

}
