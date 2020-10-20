package com.gpro.consulting.tiers.logistique.service.gl.facon.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.logistique.coordination.gl.facon.value.TraitementFaconValue;
import com.gpro.consulting.tiers.logistique.domaine.gl.facon.ITraitementFaconDomaine;
import com.gpro.consulting.tiers.logistique.service.gl.facon.ITraitementFaconService;

/**
 *  
 * @author Zeineb Medimagh
 * @since 27/09/2016
 *
 */

@Component
public class TraitementFaconServiceImpl  extends AbstractPersistance implements ITraitementFaconService {
	
	private static final Logger logger = LoggerFactory.getLogger(TraitementFaconServiceImpl.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private ITraitementFaconDomaine traitementFaconDomaine;
	
	@Override
	public String create(TraitementFaconValue TraitementFaconValue) {
		return traitementFaconDomaine.create(TraitementFaconValue);
	}

	@Override
	public TraitementFaconValue getById(Long id) {
		return traitementFaconDomaine.getById(id);
	}

	@Override
	public String update(TraitementFaconValue TraitementFaconValue) {
	    return traitementFaconDomaine.update(TraitementFaconValue);
	}

	@Override
	public void delete(Long id) {
		traitementFaconDomaine.delete(id);
	}

	@Override
	public List<TraitementFaconValue> getAll() {
		
		return traitementFaconDomaine.getAll();
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
