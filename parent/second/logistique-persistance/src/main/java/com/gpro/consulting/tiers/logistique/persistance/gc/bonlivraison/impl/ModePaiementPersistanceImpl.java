package com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.ModePaiementValue;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.IModePaiementPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie.ModePaiementEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.utilities.BonLivraisonPersistanceUtilities;

/**
 * @author Wahid Gazzah
 * @since 19 févr. 2016
 */

@Component
public class ModePaiementPersistanceImpl extends AbstractPersistance implements IModePaiementPersistance {
	
	private static final Logger logger = LoggerFactory.getLogger(ModePaiementPersistanceImpl.class);

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private BonLivraisonPersistanceUtilities bonLivraisonPersistanceUtilities;
	
	@Override
	public List<ModePaiementValue> getAll() {
		
		List<ModePaiementEntity> listEntity = this.lister(ModePaiementEntity.class);
		
		return bonLivraisonPersistanceUtilities.listModePaiementEntityToValue(listEntity);
	}
	
	@Override
	public String create(ModePaiementValue modePaiementValue) {
		
		ModePaiementEntity entity = (ModePaiementEntity) this.creer(bonLivraisonPersistanceUtilities.toEntity(modePaiementValue));

	    return entity.getId().toString();
	}

	@Override
	public ModePaiementValue getById(Long id) {
		
		ModePaiementEntity ModePaiementEntity = this.rechercherParId(id, ModePaiementEntity.class);

	    return bonLivraisonPersistanceUtilities.toValue(ModePaiementEntity);
	}

	@Override
	public String update(ModePaiementValue modePaiementValue) {
		
		ModePaiementEntity entity = (ModePaiementEntity) this.modifier(bonLivraisonPersistanceUtilities.toEntity(modePaiementValue));

	    return entity.getId().toString();
	}

	@Override
	public void delete(Long id) {
		
		this.supprimerEntite(ModePaiementEntity.class, id);
	}
	

	public EntityManager getEntityManager() {
		return entityManager;
	}

	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
