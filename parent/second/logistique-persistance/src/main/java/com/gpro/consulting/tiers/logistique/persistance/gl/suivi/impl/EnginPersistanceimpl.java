package com.gpro.consulting.tiers.logistique.persistance.gl.suivi.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.logistique.coordination.gl.suivi.value.EnginValue;
import com.gpro.consulting.tiers.logistique.persistance.gl.suivi.IEnginPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gl.suivi.entity.EnginEntity;
import com.gpro.consulting.tiers.logistique.persistance.gl.suivi.utilities.Persistanceutilities;

/**
 * @author ELAraichi Oussama
 * @since 06/04/2018
 */

@Component
public class EnginPersistanceimpl extends AbstractPersistance implements IEnginPersistance {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	Persistanceutilities persistanceUtilities;
	
	
	@Override
	public EnginValue getById(Long id) {
		
		
       EnginEntity entity = this.rechercherParId(id, EnginEntity.class);
		
		return persistanceUtilities.toValue(entity);
	}

	@Override
	public List<EnginValue> getAll() {
         
		List<EnginEntity> listEntity = this.lister(EnginEntity.class);
		
		return persistanceUtilities.listtovalue(listEntity); 
	}

	@Override
	public String create(EnginValue enginValue) {
		
		EnginEntity entity = (EnginEntity) this.creer(persistanceUtilities.toEntity(enginValue));

	    return entity.getId().toString();
		
	}

	@Override
	public String update(EnginValue enginValue) {
		
		EnginEntity entity = (EnginEntity) this.modifier(persistanceUtilities.toEntity(enginValue));

	    return entity.getId().toString();
	}

	@Override
	public void delete(Long id) {
		
		this.supprimerEntite(EnginEntity.class, id.longValue());
		
	}

	@Override
	public EntityManager getEntityManager() {
		
			return entityManager;
		}
		
		public void setEntityManager(EntityManager entityManager) {
			this.entityManager = entityManager;
		
	}
	

}
