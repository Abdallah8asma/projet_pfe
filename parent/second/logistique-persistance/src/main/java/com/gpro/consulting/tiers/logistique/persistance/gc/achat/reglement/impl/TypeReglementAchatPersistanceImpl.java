package com.gpro.consulting.tiers.logistique.persistance.gc.achat.reglement.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.TypeReglementAchatValue;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reglement.ITypeReglementAchatPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reglement.entity.TypeReglementAchatEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reglement.utility.ReglementAchatPersistanceUtilities;

/**
 * @author Ameni Berrich
 *
 */

@Component
public class TypeReglementAchatPersistanceImpl extends AbstractPersistance implements ITypeReglementAchatPersistance{
	
private static final Logger logger = LoggerFactory.getLogger(TypeReglementAchatPersistanceImpl.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<TypeReglementAchatValue> getAll() {

		List<TypeReglementAchatEntity> listEntity = this.lister(TypeReglementAchatEntity.class);
		
		return ReglementAchatPersistanceUtilities.toListValue(listEntity);
	}

	@Override
	public TypeReglementAchatValue getById(Long id) {
		
		TypeReglementAchatEntity entity = this.rechercherParId(id, TypeReglementAchatEntity.class);

	    return ReglementAchatPersistanceUtilities.toValue(entity);
	}
	
	@Override
	public String create(TypeReglementAchatValue value) {
		TypeReglementAchatEntity entity = (TypeReglementAchatEntity) this.creer(ReglementAchatPersistanceUtilities.toEntity(value));

	    return entity.getId().toString();
	}

	@Override
	public String update(TypeReglementAchatValue value) {
		TypeReglementAchatEntity entity = (TypeReglementAchatEntity) this.modifier(ReglementAchatPersistanceUtilities.toEntity(value));

	    return entity.getId().toString();
	}

	@Override
	public void delete(Long id) {
		this.supprimerEntite(TypeReglementAchatEntity.class, id);
		
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	

}
