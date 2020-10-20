package com.gpro.consulting.tiers.logistique.service.gc.boncomptoir.impl;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.logistique.coordination.gc.boncomptoir.value.DetComptoirVenteValue;
import com.gpro.consulting.logistique.coordination.gc.boncomptoir.value.RechercheMulticritereDetComptoirValue;
import com.gpro.consulting.logistique.coordination.gc.boncomptoir.value.ResultatRechecheDetBonComptoirValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.boncomptoir.IDetComptoirVenteDomaine;
import com.gpro.consulting.tiers.logistique.service.gc.boncomptoir.IDetComptoirVenteService;

/**
 * implementation of {@link IDetComptoirVenteService}
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
@Service
@Transactional
public class DetComptoirVenteServiceImpl implements IDetComptoirVenteService{
	
	private static final Logger logger = LoggerFactory.getLogger(DetComptoirVenteServiceImpl.class);

	@Autowired
	private IDetComptoirVenteDomaine detComptoirVenteDomaine;

	@Override
	public String create(DetComptoirVenteValue detComptoirVenteValue) {
		
		return detComptoirVenteDomaine.create(detComptoirVenteValue);
	}

	@Override
	public DetComptoirVenteValue getById(Long id) {
		
		return detComptoirVenteDomaine.getById(id);
	}

	@Override
	public String update(DetComptoirVenteValue detComptoirVenteValue) {
		
		return detComptoirVenteDomaine.update(detComptoirVenteValue);
	}

	@Override
	public void delete(Long id) {
		
		detComptoirVenteDomaine.delete(id);
	}
	
	@Override
	public ResultatRechecheDetBonComptoirValue rechercherMultiCritereDetComptoir(
			RechercheMulticritereDetComptoirValue request) {
		//logger.info("rechercheMulticritereDetComptoir: Delegating request {} to Domaine layer.", request);
		return detComptoirVenteDomaine.rechercherMultiCritereDetComptoir(request);
	}

}
