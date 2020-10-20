package com.gpro.consulting.tiers.logistique.domaine.gc.bonlivraison.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.ModePaiementValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.bonlivraison.IModePaiementDomaine;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.IModePaiementPersistance;

/**
 * Implementation of {@link IModePaiementDomaine}
 * 
 * @author Wahid Gazzah
 * @since 19/02/2016
 *
 */

@Component
public class ModePaiementDomaineImpl implements IModePaiementDomaine{
	
	private static final Logger logger = LoggerFactory.getLogger(ModePaiementDomaineImpl.class);
	
	@Autowired
	private IModePaiementPersistance modePaiementPersistance;


	@Override
	public String create(ModePaiementValue ModePaiementValue) {
		
		return modePaiementPersistance.create(ModePaiementValue);
	}

	@Override
	public ModePaiementValue getById(Long id) {
		
		return modePaiementPersistance.getById(id);
	}

	@Override
	public String update(ModePaiementValue ModePaiementValue) {
		
		return modePaiementPersistance.update(ModePaiementValue);
	}

	@Override
	public void delete(Long id) {
		
		modePaiementPersistance.delete(id);
	}

	@Override
	public List<ModePaiementValue> getAll() {
		
		return modePaiementPersistance.getAll();
	}
	
}
