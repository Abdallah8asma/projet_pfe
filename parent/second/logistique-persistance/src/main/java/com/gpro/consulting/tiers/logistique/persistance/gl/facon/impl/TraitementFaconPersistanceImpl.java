package com.gpro.consulting.tiers.logistique.persistance.gl.facon.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.logistique.coordination.gl.facon.value.TraitementFaconValue;
import com.gpro.consulting.tiers.logistique.persistance.gl.facon.ITraitementFaconPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gl.facon.ITraitementFichePersistance;
import com.gpro.consulting.tiers.logistique.persistance.gl.facon.entity.TraitementFaconEntity;
import com.gpro.consulting.tiers.logistique.persistance.gl.facon.utilities.FaconPersistanceUtilities;
import com.gpro.consulting.tiers.logistique.persistance.gl.fichesuiveuse.IFicheSuiveusePersistance;

/**
 * Implementation of {@link IFicheSuiveusePersistance} interface.
 *  
 * @author Wahid Gazzah
 * @since 28/03/2016
 *
 */

@Component
public class TraitementFaconPersistanceImpl  extends AbstractPersistance implements ITraitementFaconPersistance {
	
	private static final Logger logger = LoggerFactory.getLogger(TraitementFaconPersistanceImpl.class);
	
	@Autowired
	private ITraitementFichePersistance traitementFichePersistance;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public String create(TraitementFaconValue TraitementFaconValue) {
		TraitementFaconEntity entity = (TraitementFaconEntity) this.creer(FaconPersistanceUtilities.toEntity(TraitementFaconValue));
	    return entity.getId().toString();
	}

	@Override
	public TraitementFaconValue getById(Long id) {
		TraitementFaconEntity entity = this.rechercherParId(id, TraitementFaconEntity.class);

	    return FaconPersistanceUtilities.toValue(entity);
	}

	@Override
	public String update(TraitementFaconValue TraitementFaconValue) {
		TraitementFaconEntity entity = (TraitementFaconEntity) this.modifier(FaconPersistanceUtilities.toEntity(TraitementFaconValue));
	    return entity.getId().toString();
	}

	@Override
	public void delete(Long id) {
		this.supprimerEntite(TraitementFaconEntity.class, id.longValue());
	}

	@Override
	public List<TraitementFaconValue> getAll() {
		
		List<TraitementFaconEntity> listEntity = this.lister(TraitementFaconEntity.class);
		
		List<TraitementFaconValue> listValue = new ArrayList<TraitementFaconValue>();
		
		for(TraitementFaconEntity entity : listEntity){
			
			listValue.add(FaconPersistanceUtilities.toValue(entity));
		}
		
		return listValue;
	}

	
}
