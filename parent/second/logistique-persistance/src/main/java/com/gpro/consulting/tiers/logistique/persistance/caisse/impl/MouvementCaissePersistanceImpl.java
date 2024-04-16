package com.gpro.consulting.tiers.logistique.persistance.caisse.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.logistique.coordination.caisse.value.CaisseValue;
import com.gpro.consulting.tiers.logistique.coordination.caisse.value.MouvementCaisseValue;
import com.gpro.consulting.tiers.logistique.coordination.caisse.value.RechercheMulticritereMouvementCaisseValue;
import com.gpro.consulting.tiers.logistique.coordination.caisse.value.ResultatRechercheMouvementCaisseValue;
import com.gpro.consulting.tiers.logistique.persistance.caisse.ICaissePersistance;
import com.gpro.consulting.tiers.logistique.persistance.caisse.IMouvementCaissePersistance;
import com.gpro.consulting.tiers.logistique.persistance.caisse.entity.MouvementCaisseEntity;
import com.gpro.consulting.tiers.logistique.persistance.caisse.utilities.MouvementCaissePersistanceUtilities;

// TODO: Auto-generated Javadoc
/**
 * Implementation of {@link MouvementCaissePersistanceImpl} interface.
 * 
 * @author Hamdi Etteieb
 * @since 25/08/2018
 *
 */

@Component
public class MouvementCaissePersistanceImpl extends AbstractPersistance implements IMouvementCaissePersistance {

	/** The Constant PREDICATE_CAISSSE_ID. */
	private static final String PREDICATE_CAISSSE_ID = "caisseId";

	/** The Constant PREDICATE_DATE. */
	private static final String PREDICATE_DATE = "date";

	/** The Constant PREDICATE_DESTINATAIRE. */
	private static final String PREDICATE_DESTINATAIRE = "destinataire";

	/** The Constant PREDICATE_TYPE_MOUVEMENT. */
	private static final String PREDICATE_TYPE_MOUVEMENT = "typeMouvement";

	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;

	/** The mouvement caisse persistance utilities. */
	@Autowired
	private MouvementCaissePersistanceUtilities mouvementCaissePersistanceUtilities;

	/** The caisse persistance. */
	@Autowired
	ICaissePersistance caissePersistance;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.persistance.caisse.
	 * IMouvementCaissePersistance#create(com.gpro.consulting.tiers.logistique.
	 * coordination.caisse.value.MouvementCaisseValue)
	 */
	@Override
	public String create(MouvementCaisseValue MouvementCaisseValue) {

		MouvementCaisseEntity entity = (MouvementCaisseEntity) this
				.creer(mouvementCaissePersistanceUtilities.toEntity(MouvementCaisseValue));

		return entity.getId().toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.persistance.caisse.
	 * IMouvementCaissePersistance#getById(java.lang.Long)
	 */
	@Override
	public MouvementCaisseValue getById(Long id) {

		MouvementCaisseEntity MouvementCaisseEntity = this.rechercherParId(id, MouvementCaisseEntity.class);

		return mouvementCaissePersistanceUtilities.toValue(MouvementCaisseEntity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.persistance.caisse.
	 * IMouvementCaissePersistance#update(com.gpro.consulting.tiers.logistique.
	 * coordination.caisse.value.MouvementCaisseValue)
	 */
	@Override
	public String update(MouvementCaisseValue mouvementCaisseValue) {

		MouvementCaisseEntity entity = (MouvementCaisseEntity) this
				.modifier(mouvementCaissePersistanceUtilities.toEntity(mouvementCaisseValue));

		return entity.getId().toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.persistance.caisse.
	 * IMouvementCaissePersistance#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) {

		this.supprimerEntite(MouvementCaisseEntity.class, id);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.persistance.caisse.
	 * IMouvementCaissePersistance#rechercheMutlicritere(com.gpro.consulting.
	 * tiers.logistique.coordination.caisse.value.
	 * RechercheMulticritereMouvementCaisseValue)
	 */
	@Override
	public ResultatRechercheMouvementCaisseValue rechercheMutlicritere(
			RechercheMulticritereMouvementCaisseValue request) {

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();

		CriteriaQuery<MouvementCaisseEntity> criteriaQuery = criteriaBuilder.createQuery(MouvementCaisseEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();

		Root<MouvementCaisseEntity> root = criteriaQuery.from(MouvementCaisseEntity.class);

		if (request.getDestinataire() != null && request.getDestinataire() != "") {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_DESTINATAIRE), request.getDestinataire()));
		}
		if (request.getDateDe() != null) {
			whereClause.add(
					criteriaBuilder.greaterThanOrEqualTo(root.<Calendar> get(PREDICATE_DATE), request.getDateDe()));
		}

		if (request.getDateAu() != null) {
			whereClause
					.add(criteriaBuilder.lessThanOrEqualTo(root.<Calendar> get(PREDICATE_DATE), request.getDateAu()));
		}
		// Set request.type on whereClause if not empty or null
		if (request.getCaisseId() != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CAISSSE_ID), request.getCaisseId()));
		}
		if (request.getTypeMouvement() != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_TYPE_MOUVEMENT), request.getTypeMouvement()));
		}
		/** Lancer la requete */
		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));

		List<MouvementCaisseEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

		Set<MouvementCaisseValue> list = new HashSet<MouvementCaisseValue>();

		for (MouvementCaisseEntity entity : resultatEntite) {
			MouvementCaisseValue dto = mouvementCaissePersistanceUtilities.toValue(entity);
			
			CaisseValue caisse = caissePersistance.getById(dto.getCaisseId());
			
			if(caisse != null)
				dto.setCaisseReference(caisse.getReference());
			
			list.add(dto);
		}

		ResultatRechercheMouvementCaisseValue resultat = new ResultatRechercheMouvementCaisseValue();
		resultat.setListe(list);

		return resultat;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.persistance.caisse.
	 * IMouvementCaissePersistance#getAll()
	 */
	@Override
	public List<MouvementCaisseValue> getAll() {

		List<MouvementCaisseEntity> mvtCaisseEntity = this.lister(MouvementCaisseEntity.class);

		return mouvementCaissePersistanceUtilities.toValue(mvtCaisseEntity);
	}
}
