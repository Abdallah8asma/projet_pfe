package com.gpro.consulting.tiers.logistique.persistance.gl.suivi.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.logistique.coordination.gl.suivi.value.PersonnelValue;
import com.gpro.consulting.tiers.logistique.persistance.gl.suivi.IPersonnelPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gl.suivi.entity.PersonnelEntity;
import com.gpro.consulting.tiers.logistique.persistance.gl.suivi.utilities.Persistanceutilities;

/**
 * @author ELAraichi Oussama
 * @since 06/04/2018
 */

@Component
public class PersonnelPersistanceimpl extends AbstractPersistance implements IPersonnelPersistance {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	Persistanceutilities persistanceUtilities;
	
	
	
	@Override
	public PersonnelValue getById(Long id) {
		
		PersonnelEntity entity = this.rechercherParId(id, PersonnelEntity.class);
		
		return persistanceUtilities.toValue(entity);
	}

	@Override
	public List<PersonnelValue> getAll() {
     
		List<PersonnelEntity> listEntity = this.lister(PersonnelEntity.class);
		
		return persistanceUtilities.listToValue(listEntity);
		
	}

	@Override
	public String create(PersonnelValue personnelValue) {
		
		PersonnelEntity entity = (PersonnelEntity) this.creer(persistanceUtilities.toEntity(personnelValue));

	    return entity.getId().toString();
	}

	@Override
	public String update(PersonnelValue personnelValue) {
		
		PersonnelEntity entity = (PersonnelEntity) this.modifier(persistanceUtilities.toEntity(personnelValue));

	    return entity.getId().toString();
	}

	@Override
	public void delete(Long id) {
		this.supprimerEntite(PersonnelEntity.class, id.longValue());
		
	}

	@Override
	public EntityManager getEntityManager() {
		
			return entityManager;
		}
		
		public void setEntityManager(EntityManager entityManager) {
			this.entityManager = entityManager;
		
	}

}
