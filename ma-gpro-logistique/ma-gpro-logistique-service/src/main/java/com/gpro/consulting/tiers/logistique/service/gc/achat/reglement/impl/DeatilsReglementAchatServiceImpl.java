package com.gpro.consulting.tiers.logistique.service.gc.achat.reglement.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.DetailsReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.RechercheMulticritereReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.ResultatRechecheElementReglementAchatValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.achat.reglement.IDetailsReglementAchatDomaine;
import com.gpro.consulting.tiers.logistique.service.gc.achat.reglement.IDetailsReglementAchatService;
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
public class DeatilsReglementAchatServiceImpl implements IDetailsReglementAchatService{

	private static final Logger logger = LoggerFactory.getLogger(DeatilsReglementAchatServiceImpl.class);

	@Autowired
	private IDetailsReglementAchatDomaine detailsReglementDomaine;



	@Override
	public ResultatRechecheElementReglementAchatValue rechercherMultiCritere(RechercheMulticritereReglementAchatValue request) {
		// TODO Auto-generated method stub
		return detailsReglementDomaine.rechercherMultiCritere(request);
	}



	@Override
	public String update(DetailsReglementAchatValue detailsReglementValue) {
		// TODO Auto-generated method stub
		return detailsReglementDomaine.update(detailsReglementValue);
	}
	

}
