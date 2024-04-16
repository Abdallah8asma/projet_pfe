package com.gpro.consulting.tiers.logistique.persistance.gc.typeReglement.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.TypeReglementValue;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie.LivraisonVenteEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.reglement.entity.ReglementEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.reglement.entity.TypeReglementEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.reglement.utility.ReglementPersistanceUtilities;
import com.gpro.consulting.tiers.logistique.persistance.gc.typeReglement.ITypeReglementPersistance;

/**
 * @author Ameni Berrich
 *
 */

@Component
public class TypeReglementPersistanceImpl extends AbstractPersistance implements ITypeReglementPersistance{
	
private static final Logger logger = LoggerFactory.getLogger(TypeReglementPersistanceImpl.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<TypeReglementValue> getAll() {

		List<TypeReglementEntity> listEntity = this.lister(TypeReglementEntity.class);
		
		return ReglementPersistanceUtilities.toListValue(listEntity);
	}

	@Override
	public TypeReglementValue getById(Long id) {
		
		TypeReglementEntity entity = this.rechercherParId(id, TypeReglementEntity.class);

	    return ReglementPersistanceUtilities.toValue(entity);
	}
	
	@Override
	public String create(TypeReglementValue value) {
		TypeReglementEntity entity = (TypeReglementEntity) this.creer(ReglementPersistanceUtilities.toEntity(value));

	    return entity.getId().toString();
	}

	@Override
	public String update(TypeReglementValue value) {
		TypeReglementEntity entity = (TypeReglementEntity) this.modifier(ReglementPersistanceUtilities.toEntity(value));

	    return entity.getId().toString();
	}

	@Override
	public void delete(Long id) {
		this.supprimerEntite(TypeReglementEntity.class, id);
		
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	

}
