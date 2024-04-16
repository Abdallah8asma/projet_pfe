package com.gpro.consulting.tiers.logistique.service.gc.vente.facture.impl;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.DetFactureVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.RechercheMulticritereDetFactureVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ResultatRechecheDetFactureVenteValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.vente.facture.IDetFactureVenteDomaine;
import com.gpro.consulting.tiers.logistique.service.gc.vente.facture.IDetFactureVenteService;

/**
 * implementation of {@link IDetFactureVenteService}
 * 
 * @author Wahid Gazzah
 * @since 29/02/2016
 *
 */
@Service
@Transactional
public class DetFactureVenteServiceImpl implements IDetFactureVenteService{
	
	private static final Logger logger = LoggerFactory.getLogger(DetFactureVenteServiceImpl.class);

	@Autowired
	private IDetFactureVenteDomaine detFactureVenteDomaine;

	@Override
	public String create(DetFactureVenteValue detFactureVenteValue) {
		
		return detFactureVenteDomaine.create(detFactureVenteValue);
	}

	@Override
	public DetFactureVenteValue getById(Long id) {
		
		return detFactureVenteDomaine.getById(id);
	}

	@Override
	public String update(DetFactureVenteValue detFactureVenteValue) {
		
		return detFactureVenteDomaine.update(detFactureVenteValue);
	}

	@Override
	public void delete(Long id) {
		
		detFactureVenteDomaine.delete(id);
	}

	@Override
	public ResultatRechecheDetFactureVenteValue rechercherMultiCritere(
			RechercheMulticritereDetFactureVenteValue request) {
		// TODO Auto-generated method stub
		return  detFactureVenteDomaine.rechercherMultiCritere(request);
	}

}
