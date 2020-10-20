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
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.DetFactureAchatValue;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.facture.IDetFactureAchatPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.facture.entitie.DetFactureAchatEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.facture.utilities.FactureAchatPersistanceUtilities;

/**
 * Implementation of {@link IBonFacturePersistance} interface.
 * 
 * @author Wahid Gazzah
 * @since 29/02/2016
 *
 */

@Component
public class DetFactureAchatPersistanceImpl extends AbstractPersistance implements IDetFactureAchatPersistance {

	private String PREDICATE_PRODUIT = "produitId";
	private String PREDICATE_FACTURE_Achat = "factureAchat";
	private String PREDICATE_CHOIX = "choix";

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private FactureAchatPersistanceUtilities facturePersistanceUtilities;

	@Override
	public String create(DetFactureAchatValue detFactureAchatValue) {

		DetFactureAchatEntity entity = (DetFactureAchatEntity) this
				.creer(facturePersistanceUtilities.toEntity(detFactureAchatValue));

		return entity.getId().toString();
	}

	@Override
	public DetFactureAchatValue getById(Long id) {

		DetFactureAchatEntity detFactureAchatEntity = this.rechercherParId(id, DetFactureAchatEntity.class);

		return facturePersistanceUtilities.toValue(detFactureAchatEntity);
	}

	@Override
	public String update(DetFactureAchatValue detFactureAchatValue) {

		DetFactureAchatEntity entity = (DetFactureAchatEntity) this
				.modifier(facturePersistanceUtilities.toEntity(detFactureAchatValue));

		return entity.getId().toString();
	}

	@Override
	public void delete(Long id) {

		this.supprimerEntite(DetFactureAchatEntity.class, id);
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public DetFactureAchatValue getByFactureAchatAndProduit(Long factureAchatId, Long produitId, String choix) {

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();

		CriteriaQuery<DetFactureAchatEntity> criteriaQuery = criteriaBuilder.createQuery(DetFactureAchatEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();

		Root<DetFactureAchatEntity> root = criteriaQuery.from(DetFactureAchatEntity.class);

		// Set FactureAchatId on whereClause if not empty or null
		if (factureAchatId != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_FACTURE_Achat), factureAchatId));
		}

		// Set produitId on whereClause if not null
		if (produitId != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_PRODUIT), produitId));
		}

		// Set choix on whereClause if not empty or null
		if (estNonVide(choix)) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CHOIX), choix));
		}

		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		List<DetFactureAchatEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

		if (resultatEntite.size() > 0) {
			return facturePersistanceUtilities.toValue(resultatEntite.get(0));
		} else
			return null;

	}

	private boolean estNonVide(String val) {
		return val != null && !"".equals(val);
	}
}
