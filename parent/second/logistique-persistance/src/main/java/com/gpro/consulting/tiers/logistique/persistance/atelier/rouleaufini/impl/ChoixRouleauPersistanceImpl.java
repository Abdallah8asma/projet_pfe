package com.gpro.consulting.tiers.logistique.persistance.atelier.rouleaufini.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.ChoixRouleauValue;
import com.gpro.consulting.tiers.logistique.persistance.atelier.rouleaufini.IChoixRouleauPersistance;
import com.gpro.consulting.tiers.logistique.persistance.atelier.rouleaufini.entity.ChoixRouleauEntity;
import com.gpro.consulting.tiers.logistique.persistance.atelier.rouleaufini.utilities.RouleauFiniPersistanceUtilities;

/**
 * Implementation of {@link IChoixRouleauPersistance} interface.
 * 
 * @author Wahid Gazzah
 * @since 08/01/2016
 *
 */
@Component
public class ChoixRouleauPersistanceImpl extends AbstractPersistance implements IChoixRouleauPersistance {
	private static final Logger logger = LoggerFactory.getLogger(ChoixRouleauPersistanceImpl.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private RouleauFiniPersistanceUtilities persistanceUtilities;
	
	@Override
	public ChoixRouleauValue getChoixRouleauById(Long id) {
		
		//logger.info("ChoixRouleauPersistanceImpl: persistance to get a ChoixRouleauEntity by id: "+id);
		
		ChoixRouleauEntity entity = this.rechercherParId(id, ChoixRouleauEntity.class);

	    return persistanceUtilities.toValue(entity);
	}

	@Override
	public List<ChoixRouleauValue> listeChoixRouleau() {
		//logger.info("RouleauFiniPersistanceImpl --listeChoixRouleau");
		List<ChoixRouleauEntity> vListeChoixRouleauEntite = this.lister(ChoixRouleauEntity.class);
		List<ChoixRouleauValue> vListChoixRouleauValues = new ArrayList<ChoixRouleauValue>();
		for (ChoixRouleauEntity vChoixRouleauEntity : vListeChoixRouleauEntite) {
			vListChoixRouleauValues.add(persistanceUtilities.toValue(vChoixRouleauEntity));
		}
		return vListChoixRouleauValues;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
