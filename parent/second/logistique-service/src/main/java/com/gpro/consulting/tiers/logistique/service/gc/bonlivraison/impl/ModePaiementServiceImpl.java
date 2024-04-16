package com.gpro.consulting.tiers.logistique.service.gc.bonlivraison.impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.ModePaiementValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.bonlivraison.IModePaiementDomaine;
import com.gpro.consulting.tiers.logistique.service.gc.bonlivraison.IModePaiementService;

/**
 * implementation of {@link IModePaiementService}
 * 
 * @author Wahid Gazzah
 * @since 19/02/2016
 * 
 */

@Service
@Transactional
public class ModePaiementServiceImpl implements IModePaiementService{
	
	private static final Logger logger = LoggerFactory.getLogger(ModePaiementServiceImpl.class);
	
	@Autowired
	private IModePaiementDomaine modePaiementServiceDomaine;
	
	@Override
	public String create(ModePaiementValue modePaiementValue) {
		
		return modePaiementServiceDomaine.create(modePaiementValue);
	}

	@Override
	public ModePaiementValue getById(Long id) {
		
		return modePaiementServiceDomaine.getById(id);
	}

	@Override
	public String update(ModePaiementValue modePaiementValue) {
		
		return modePaiementServiceDomaine.update(modePaiementValue);
	}

	@Override
	public void delete(Long id) {
		
		modePaiementServiceDomaine.delete(id);
	}

	@Override
	public List<ModePaiementValue> getAll() {
		
		//logger.info("--------get all modePaiement");
		
		return modePaiementServiceDomaine.getAll();
	}
	

}
