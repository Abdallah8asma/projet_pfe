/**
 * 
 */
package com.gpro.consulting.tiers.logistique.persistance.atelier.bonReception.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.TraitementValue;
import com.gpro.consulting.tiers.logistique.persistance.atelier.bonReception.ITraitementPersistance;
import com.gpro.consulting.tiers.logistique.persistance.atelier.bonReception.entity.TraitementEntity;
import com.gpro.consulting.tiers.logistique.persistance.atelier.bonReception.utilities.BonReceptionPersistanceUtilities;

/**
 * Implémentation des méthodes CRUD du Traitement
 * 
 * @author Ameni
 * 
 */

@Component
public class TraitementPersistanceImpl extends AbstractPersistance implements
		ITraitementPersistance {

	/** EntityManager. */
	@PersistenceContext
	private EntityManager entityManager;
	
	/** Utilitaire de persistance */
	@Autowired
	private BonReceptionPersistanceUtilities vPersistanceUtilities;

	/** Logger */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(TraitementPersistanceImpl.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String creerTraitement(TraitementValue pTraitementValue) {
		TraitementEntity vBonReceptionEntity = (TraitementEntity) this
				.creer(vPersistanceUtilities.toEntity(pTraitementValue));

		return vBonReceptionEntity.getId().toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void supprimerTraitement(Long pId) {
		this.supprimerEntite(TraitementEntity.class, pId.longValue());

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String modifierTraitement(TraitementValue pTraitementValue) {
		TraitementEntity vBonReceptionEntity = (TraitementEntity) this
				.modifier(vPersistanceUtilities.toEntity(pTraitementValue));

		return vBonReceptionEntity.getId().toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<TraitementValue> listeTraitement() {
		List<TraitementEntity> vListeTraitementEntity = this.lister(TraitementEntity.class);
	    List <TraitementValue> vListTraitementValue=new ArrayList < TraitementValue >();
	    for (TraitementEntity vTraitementEntity : vListeTraitementEntity) {
	    	vListTraitementValue.add(BonReceptionPersistanceUtilities.toValue(vTraitementEntity));
	     }
	    return vListTraitementValue;
	}
	
	
	  @Override
	  public TraitementValue getTraitementParId(Long id) {
		  
		  TraitementEntity entity = this.rechercherParId(id, TraitementEntity.class);

		  TraitementValue value = vPersistanceUtilities.toValue(entity);
		  return value;
	  }
	
	/**
	 * @return the entityManager
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * @param entityManager the entityManager to set
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
