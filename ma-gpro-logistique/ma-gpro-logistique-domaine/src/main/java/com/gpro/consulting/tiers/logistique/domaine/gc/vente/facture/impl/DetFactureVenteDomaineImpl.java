package com.gpro.consulting.tiers.logistique.domaine.gc.vente.facture.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.DetFactureVenteValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.vente.facture.IDetFactureVenteDomaine;
import com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture.IDetFactureVentePersistance;

/**
 * Implementation of {@link IDetFactureVenteDomaine}
 * 
 * @author Wahid Gazzah
 * @since 29/02/2016
 *
 */

@Component
public class DetFactureVenteDomaineImpl implements IDetFactureVenteDomaine{
	
	private static final Logger logger = LoggerFactory.getLogger(DetFactureVenteDomaineImpl.class);
	
	@Autowired
	private IDetFactureVentePersistance detFactureVentePersistance;

	
	@Override
	public String create(DetFactureVenteValue bonFactureValue) {
		
		return detFactureVentePersistance.create(bonFactureValue);
	}

	@Override
	public DetFactureVenteValue getById(Long id) {
		
		return detFactureVentePersistance.getById(id);
	}

	@Override
	public String update(DetFactureVenteValue bonFactureValue) {
		
		return detFactureVentePersistance.update(bonFactureValue);
	}

	@Override
	public void delete(Long id) {
		
		detFactureVentePersistance.delete(id);
	}

}
