package com.gpro.consulting.tiers.logistique.persistance.gc.boncommande.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.ProduitCommandeValue;
import com.gpro.consulting.tiers.logistique.persistance.gc.boncommande.IProduitCommandePersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.boncommande.entitie.ProduitCommandeEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.boncommande.utilities.BonCommandePersistanceUtilities;

/**
 * 
 * @author Zeineb Medimagh
 * @since 16/11/2016
 *
 */

@Component
public class ProduitCommandePersistanceImpl extends AbstractPersistance implements IProduitCommandePersistance {

	@PersistenceContext
	private EntityManager entityManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.persistance.gc.boncommande.
	 * IProduitCommandePersistance#create(com.gpro.consulting.tiers.logistique.
	 * coordination.gc.boncommande.value.ProduitCommandeValue)
	 */
	@Override
	public String create(ProduitCommandeValue produitCommandeValue) {

		ProduitCommandeEntity entity = (ProduitCommandeEntity) this
				.creer(BonCommandePersistanceUtilities.toEntity(produitCommandeValue));

		return entity.getId().toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.persistance.gc.boncommande.
	 * IProduitCommandePersistance#getById(java.lang.Long)
	 */
	@Override
	public ProduitCommandeValue getById(Long id) {
		ProduitCommandeEntity produitCommandeEntity = this.rechercherParId(id, ProduitCommandeEntity.class);
		return BonCommandePersistanceUtilities.toValue(produitCommandeEntity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.persistance.gc.boncommande.
	 * IProduitCommandePersistance#update(com.gpro.consulting.tiers.logistique.
	 * coordination.gc.boncommande.value.ProduitCommandeValue)
	 */
	@Override
	public String update(ProduitCommandeValue produitCommandeValue) {

		ProduitCommandeEntity entity = (ProduitCommandeEntity) this
				.modifier(BonCommandePersistanceUtilities.toEntity(produitCommandeValue));

		return entity.getId().toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.persistance.gc.boncommande.
	 * IProduitCommandePersistance#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) {

		this.supprimerEntite(ProduitCommandeEntity.class, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.persistance.gc.boncommande.
	 * IProduitCommandePersistance#getAll()
	 */
	@Override
	public List<ProduitCommandeValue> getAll() {

		List<ProduitCommandeEntity> listEntity = this.lister(ProduitCommandeEntity.class);

		List<ProduitCommandeValue> finalList = new ArrayList<ProduitCommandeValue>();
		for (ProduitCommandeEntity produitCommandeEntity : listEntity) {
			finalList.add(BonCommandePersistanceUtilities.toValue(produitCommandeEntity));
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
