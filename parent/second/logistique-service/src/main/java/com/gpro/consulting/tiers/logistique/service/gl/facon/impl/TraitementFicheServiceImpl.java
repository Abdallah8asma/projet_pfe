package com.gpro.consulting.tiers.logistique.service.gl.facon.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.logistique.coordination.gl.facon.value.TraitementFicheValue;
import com.gpro.consulting.tiers.logistique.domaine.gl.facon.ITraitementFicheDomaine;
import com.gpro.consulting.tiers.logistique.service.gl.facon.ITraitementFicheService;

/**
 *  
 * @author Zeineb Medimagh
 * @since 27/09/2016
 *
 */

@Component
public class TraitementFicheServiceImpl  extends AbstractPersistance implements ITraitementFicheService {
	
	private static final Logger logger = LoggerFactory.getLogger(TraitementFicheServiceImpl.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private ITraitementFicheDomaine traitementFicheDomaine;
	
	@Override
	public String create(TraitementFicheValue TraitementFicheValue) {
		return traitementFicheDomaine.create(TraitementFicheValue);
	}

	@Override
	public TraitementFicheValue getById(Long id) {
		return traitementFicheDomaine.getById(id);
	}

	@Override
	public String update(TraitementFicheValue TraitementFicheValue) {
	    return traitementFicheDomaine.update(TraitementFicheValue);
	}

	@Override
	public void delete(Long id) {
		traitementFicheDomaine.delete(id);
	}

	@Override
	public List<TraitementFicheValue> getAll() {
		
		return traitementFicheDomaine.getAll();
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public String setTraitementPU(Long id, Double nouveauPU, Long idFiche) {
		return traitementFicheDomaine.setTraitementPU(id, nouveauPU, idFiche);
	}

}
