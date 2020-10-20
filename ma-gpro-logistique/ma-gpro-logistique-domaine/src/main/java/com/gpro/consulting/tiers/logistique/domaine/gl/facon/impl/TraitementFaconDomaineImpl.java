package com.gpro.consulting.tiers.logistique.domaine.gl.facon.impl;

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
import com.gpro.consulting.tiers.logistique.domaine.gl.facon.ITraitementFicheDomaine;
import com.gpro.consulting.tiers.logistique.persistance.gl.facon.ITraitementFaconPersistance;

/**
 *  
 * @author Zeineb Medimagh
 * @since 27/09/2016
 *
 */

@Component
public class TraitementFaconDomaineImpl  extends AbstractPersistance implements ITraitementFaconDomaine {
	
	private static final Logger logger = LoggerFactory.getLogger(TraitementFaconDomaineImpl.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private ITraitementFaconPersistance traitementFaconPersistance;
	
	@Autowired
	private ITraitementFicheDomaine traitementFicheDomaine;
	
	@Override
	public String create(TraitementFaconValue TraitementFaconValue) {
		return traitementFaconPersistance.create(TraitementFaconValue);
	}

	@Override
	public TraitementFaconValue getById(Long id) {
		return traitementFaconPersistance.getById(id);
	}

	@Override
	public String update(TraitementFaconValue TraitementFaconValue) {
	    return traitementFaconPersistance.update(TraitementFaconValue);
	}

	@Override
	public void delete(Long id) {
		traitementFaconPersistance.delete(id);
	}

	@Override
	public List<TraitementFaconValue> getAll() {
		
		return traitementFaconPersistance.getAll();
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
