package com.gpro.consulting.tiers.logistique.persistance.gc.reception.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.logistique.coordination.gc.reception.value.ProduitReceptionValue;
import com.gpro.consulting.tiers.logistique.persistance.gc.reception.IProduitReceptionPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.reception.entitie.ProduitReceptionEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.reception.utilities.ReceptionPersistanceUtilities;

/**
 *  
 * @author Hajer AMRI
 * @since 19/03/2017
 *
 */

@Component
public class ProduitReceptionPersistanceImpl extends AbstractPersistance implements IProduitReceptionPersistance{
	
	private static final Logger logger = LoggerFactory.getLogger(ProduitReceptionPersistanceImpl.class);

	@PersistenceContext
	private EntityManager entityManager;
	
	/* (non-Javadoc)
	 * @see com.gpro.consulting.tiers.logistique.persistance.gc.bonreception.IProduitReceptionPersistance#create(com.gpro.consulting.tiers.logistique.coordination.gc.bonreception.value.ProduitReceptionValue)
	 */
	@Override
	public String create(ProduitReceptionValue produitReceptionValue) {

		ProduitReceptionEntity entity = (ProduitReceptionEntity) this.creer(ReceptionPersistanceUtilities.toEntity(produitReceptionValue));

	    return entity.getId().toString();
	}

	/* (non-Javadoc)
	 * @see com.gpro.consulting.tiers.logistique.persistance.gc.bonreception.IProduitReceptionPersistance#getById(java.lang.Long)
	 */
	@Override
	public ProduitReceptionValue getById(Long id) {
		ProduitReceptionEntity produitReceptionEntity = this.rechercherParId(id, ProduitReceptionEntity.class);
	    return ReceptionPersistanceUtilities.toValue(produitReceptionEntity);
	}

	/* (non-Javadoc)
	 * @see com.gpro.consulting.tiers.logistique.persistance.gc.bonreception.IProduitReceptionPersistance#update(com.gpro.consulting.tiers.logistique.coordination.gc.bonreception.value.ProduitReceptionValue)
	 */
	@Override
	public String update(ProduitReceptionValue produitReceptionValue) {

		ProduitReceptionEntity entity = (ProduitReceptionEntity) this.modifier(ReceptionPersistanceUtilities.toEntity(produitReceptionValue));

	    return entity.getId().toString();
	}

	/* (non-Javadoc)
	 * @see com.gpro.consulting.tiers.logistique.persistance.gc.bonreception.IProduitReceptionPersistance#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) {

		this.supprimerEntite(ProduitReceptionEntity.class, id);
	}

	/* (non-Javadoc)
	 * @see com.gpro.consulting.tiers.logistique.persistance.gc.bonreception.IProduitReceptionPersistance#getAll()
	 */
	@Override
	public List<ProduitReceptionValue> getAll() {
		
		List<ProduitReceptionEntity> listEntity = this.lister(ProduitReceptionEntity.class);
		
		List<ProduitReceptionValue> finalList = new ArrayList<ProduitReceptionValue>();
		for (ProduitReceptionEntity produitReceptionEntity : listEntity) {
			finalList.add(ReceptionPersistanceUtilities.toValue(produitReceptionEntity));
		}
		
		Collections.sort(finalList);
		
		return finalList;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	

}
