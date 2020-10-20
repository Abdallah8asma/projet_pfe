package com.gpro.consulting.tiers.logistique.persistance.gc.achat.boncommande.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.boncommande.value.ProduitCommandeAchatValue;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.boncommande.IProduitCommandeAchatPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.boncommande.entitie.ProduitCommandeAchatEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.boncommande.utilities.BonCommandeAchatPersistanceUtilities;

/**
 * The Class ProduitCommandePersistanceImpl.
 *
 * @author Hamdi Etteieb
 * @since 16/11/2016
 */

@Component
public class ProduitCommandeAchatPersistanceImpl extends AbstractPersistance implements IProduitCommandeAchatPersistance {

	/** The entity manager. */
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
	public String create(ProduitCommandeAchatValue produitCommandeValue) {

		ProduitCommandeAchatEntity entity = (ProduitCommandeAchatEntity) this
				.creer(BonCommandeAchatPersistanceUtilities.toEntity(produitCommandeValue));

		return entity.getId().toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.persistance.gc.boncommande.
	 * IProduitCommandePersistance#getById(java.lang.Long)
	 */
	@Override
	public ProduitCommandeAchatValue getById(Long id) {
		ProduitCommandeAchatEntity ProduitCommandeAchatEntity = this.rechercherParId(id,
				ProduitCommandeAchatEntity.class);
		return BonCommandeAchatPersistanceUtilities.toValue(ProduitCommandeAchatEntity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.persistance.gc.boncommande.
	 * IProduitCommandePersistance#update(com.gpro.consulting.tiers.logistique.
	 * coordination.gc.boncommande.value.ProduitCommandeValue)
	 */
	@Override
	public String update(ProduitCommandeAchatValue produitCommandeValue) {

		ProduitCommandeAchatEntity entity = (ProduitCommandeAchatEntity) this
				.modifier(BonCommandeAchatPersistanceUtilities.toEntity(produitCommandeValue));

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

		this.supprimerEntite(ProduitCommandeAchatEntity.class, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.persistance.gc.boncommande.
	 * IProduitCommandePersistance#getAll()
	 */
	@Override
	public List<ProduitCommandeAchatValue> getAll() {

		List<ProduitCommandeAchatEntity> listEntity = this.lister(ProduitCommandeAchatEntity.class);

		List<ProduitCommandeAchatValue> finalList = new ArrayList<ProduitCommandeAchatValue>();
		for (ProduitCommandeAchatEntity ProduitCommandeAchatEntity : listEntity) {
			finalList.add(BonCommandeAchatPersistanceUtilities.toValue(ProduitCommandeAchatEntity));
		}

		Collections.sort(finalList);

		return finalList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance#
	 * getEntityManager()
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * Sets the entity manager.
	 *
	 * @param entityManager
	 *            the new entity manager
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
