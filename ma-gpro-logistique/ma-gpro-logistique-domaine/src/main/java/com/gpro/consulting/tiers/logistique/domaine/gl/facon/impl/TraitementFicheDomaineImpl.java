package com.gpro.consulting.tiers.logistique.domaine.gl.facon.impl;

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
import com.gpro.consulting.tiers.logistique.persistance.gl.facon.ITraitementFichePersistance;

/**
 *  
 * @author Zeineb Medimagh
 * @since 27/09/2016
 *
 */

@Component
public class TraitementFicheDomaineImpl  extends AbstractPersistance implements ITraitementFicheDomaine {
	
	private static final Logger logger = LoggerFactory.getLogger(TraitementFicheDomaineImpl.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private ITraitementFichePersistance traitementFichePersistance;
	
	@Override
	public String create(TraitementFicheValue TraitementFicheValue) {
		return traitementFichePersistance.create(TraitementFicheValue);
	}

	@Override
	public TraitementFicheValue getById(Long id) {
		return traitementFichePersistance.getById(id);
	}

	@Override
	public String update(TraitementFicheValue TraitementFicheValue) {
	    return traitementFichePersistance.update(TraitementFicheValue);
	}

	@Override
	public void delete(Long id) {
		traitementFichePersistance.delete(id);
	}

	@Override
	public List<TraitementFicheValue> getAll() {
		
		return traitementFichePersistance.getAll();
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public String setTraitementPU(Long id, Double nouveauPU, Long idFiche) {
		return traitementFichePersistance.setTraitementPU(id, nouveauPU, idFiche);
		
	}

}
