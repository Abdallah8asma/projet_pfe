package com.gpro.consulting.tiers.logistique.persistance.gl.suivi.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.logistique.coordination.gl.suivi.value.RemorqueValue;
import com.gpro.consulting.tiers.logistique.persistance.gl.suivi.IRemorquePersistance;
import com.gpro.consulting.tiers.logistique.persistance.gl.suivi.entity.RemorqueEntity;
import com.gpro.consulting.tiers.logistique.persistance.gl.suivi.utilities.Persistanceutilities;

/**
 * @author ELAraichi Oussama
 * @since 06/04/2018
 */

@Component
public class RemorquePersistanceimpl extends AbstractPersistance implements IRemorquePersistance {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	Persistanceutilities persistanceUtilities;

	@Override
	public RemorqueValue getById(Long id) {
		
		RemorqueEntity entity = this.rechercherParId(id,RemorqueEntity.class);
		
		return persistanceUtilities.toValue(entity);
	}

	@Override
	public List<RemorqueValue> getAll() {
		
		List<RemorqueEntity> listEntity = this.lister(RemorqueEntity.class);
		
		return persistanceUtilities.listtoValue(listEntity);
	}

	@Override
	public String create(RemorqueValue remorqueValue) {
		
		RemorqueEntity entity = (RemorqueEntity) this.creer(persistanceUtilities.toEntity(remorqueValue));
		
		return entity.getId().toString();
	}

	@Override
	public String update(RemorqueValue remorqueValue) {
		
		RemorqueEntity entity = (RemorqueEntity) this.modifier(persistanceUtilities.toEntity(remorqueValue));

	    return entity.getId().toString();
	}

	@Override
	public void delete(Long id) {
		
		this.supprimerEntite(RemorqueEntity.class, id.longValue());
		
	}

	@Override
	public EntityManager getEntityManager() {
		
			return entityManager;
		}
		
		public void setEntityManager(EntityManager entityManager) {
			this.entityManager = entityManager;
		
	}

}
