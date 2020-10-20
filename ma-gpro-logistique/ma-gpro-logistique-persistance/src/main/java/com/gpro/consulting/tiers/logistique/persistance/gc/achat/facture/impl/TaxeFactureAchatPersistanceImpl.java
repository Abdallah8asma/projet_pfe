package com.gpro.consulting.tiers.logistique.persistance.gc.achat.facture.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.TaxeFactureAchatValue;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.facture.ITaxeFactureAchatPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.facture.entitie.DetFactureAchatEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.facture.entitie.TaxeFactureAchatEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.facture.utilities.FactureAchatPersistanceUtilities;

/**
 * The Class TaxeFactureAchatPersistanceImpl.
 * 
 * @author Hamdi Etteieb
 * @since 23/09/2018
 */
@Component
public class TaxeFactureAchatPersistanceImpl extends AbstractPersistance implements ITaxeFactureAchatPersistance {

	/** The PREDICAT E FACTUR E achat. */
	private String PREDICATE_FACTURE_Achat = "factureAchat";

	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;

	/** The facture persistance utilities. */
	@Autowired
	private FactureAchatPersistanceUtilities facturePersistanceUtilities;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.persistance.gc.achat.facture.
	 * ITaxeFactureAchatPersistance#create(com.gpro.consulting.tiers.logistique.
	 * coordination.gc.achat.facture.value.TaxeFactureAchatValue)
	 */
	@Override
	public String create(TaxeFactureAchatValue TaxeFactureAchatValue) {

		TaxeFactureAchatEntity entity = (TaxeFactureAchatEntity) this
				.creer(facturePersistanceUtilities.toEntity(TaxeFactureAchatValue));

		return entity.getId().toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.persistance.gc.achat.facture.
	 * ITaxeFactureAchatPersistance#getById(java.lang.Long)
	 */
	@Override
	public TaxeFactureAchatValue getById(Long id) {

		TaxeFactureAchatEntity TaxeFactureAchatEntity = this.rechercherParId(id, TaxeFactureAchatEntity.class);

		return facturePersistanceUtilities.toValue(TaxeFactureAchatEntity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.persistance.gc.achat.facture.
	 * ITaxeFactureAchatPersistance#update(com.gpro.consulting.tiers.logistique.
	 * coordination.gc.achat.facture.value.TaxeFactureAchatValue)
	 */
	@Override
	public String update(TaxeFactureAchatValue TaxeFactureAchatValue) {

		TaxeFactureAchatEntity entity = (TaxeFactureAchatEntity) this
				.modifier(facturePersistanceUtilities.toEntity(TaxeFactureAchatValue));

		return entity.getId().toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.persistance.gc.achat.facture.
	 * ITaxeFactureAchatPersistance#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) {

		this.supprimerEntite(DetFactureAchatEntity.class, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.persistance.gc.achat.facture.
	 * ITaxeFactureAchatPersistance#getAllByFactureId(java.lang.Long)
	 */
	@Override
	public List<TaxeFactureAchatValue> getAllByFactureId(Long FactureId) {

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<TaxeFactureAchatEntity> criteriaQuery = criteriaBuilder.createQuery(TaxeFactureAchatEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<TaxeFactureAchatEntity> root = criteriaQuery.from(TaxeFactureAchatEntity.class);

		if (FactureId != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_FACTURE_Achat), FactureId));
		}

		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));

		List<TaxeFactureAchatEntity> entityListResult = this.entityManager.createQuery(criteriaQuery).getResultList();

		List<TaxeFactureAchatValue> listTaxeFacture = new ArrayList<TaxeFactureAchatValue>();

		for (TaxeFactureAchatEntity entity : entityListResult) {
			TaxeFactureAchatValue value = facturePersistanceUtilities.toValue(entity);
			listTaxeFacture.add(value);
		}

		return listTaxeFacture;
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
