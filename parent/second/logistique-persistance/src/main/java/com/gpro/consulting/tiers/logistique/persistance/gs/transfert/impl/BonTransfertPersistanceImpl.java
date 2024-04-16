package com.gpro.consulting.tiers.logistique.persistance.gs.transfert.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.LivraisonVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.transfert.value.BonTransfertValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.transfert.value.RechercheMulticritereBonTransfertValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.transfert.value.ResultatRechecheBonTransfertValue;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.IBonLivraisonPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie.LivraisonVenteEntity;
import com.gpro.consulting.tiers.logistique.persistance.gs.transfert.IBonTransfertPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gs.transfert.entite.BonTransfertEntity;
import com.gpro.consulting.tiers.logistique.persistance.gs.transfert.utilities.PersistanceTransfertUtilities;

/**
 * Implementation of {@link IBonLivraisonPersistance} interface.
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */

@Component
public class BonTransfertPersistanceImpl extends AbstractPersistance implements IBonTransfertPersistance {

	private static final Logger logger = LoggerFactory.getLogger(BonTransfertPersistanceImpl.class);

	private String PREDICATE_IDDEPOT_ORIGINE = "idDepotOrigine";
	private String PREDICATE_IDDEPOT_DESTINATION = "idDepotDestination";
	private String PREDICATE_REFERENCE_SORTIE = "referenceSortie";
	private String PREDICATE_REFERENCE_RECEPTION = "referenceReception";
	private String PREDICATE_STATUS = "status";
	private String PREDICATE_STATUS_AUTO = "statusAuto";

	private String PREDICATE_DECLARE = "declare";

	private String PREDICATE_OBSERVATION = "observations";
	private String PREDICATE_REFERENCE = "reference";
	private String PREDICATE_VERSION = "version";
	private String PREDICATE_TYPE = "type";
	private String PREDICATE_DATE = "date";
	private String PREDICATE_METRAGE = "metrageTotal";

	private String PREDICATE_BOUTIQUE_ID = "boutiqueId";

	private String PERCENT = "%";

	private int FIRST_INDEX = 0;

	private int MAX_RESULTS = 52;

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private PersistanceTransfertUtilities persistanceTransfertUtilities;

	@Override
	public ResultatRechecheBonTransfertValue rechercherMultiCritere(RechercheMulticritereBonTransfertValue request) {

		// logger.info("rechercherMultiCritere");
		// System.out.println("rech multi avec Transfert"+request.getTransfert());

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();

		// CriteriaQuery<BonTransfertEntity> criteriaQuery =
		// criteriaBuilder.createQuery(BonTransfertEntity.class);
		CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);

		List<Predicate> whereClause = new ArrayList<Predicate>();

		Root<BonTransfertEntity> root = criteriaQuery.from(BonTransfertEntity.class);

		if (estNonVide(request.getObservations())) {
			Expression<String> path = root.get(PREDICATE_OBSERVATION);
			Expression<String> upper = criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper,
					PERCENT + request.getObservations().toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));
		}

		// REcherche REF
		if (estNonVide(request.getReference())) {
			Expression<String> path = root.get(PREDICATE_REFERENCE);
			Expression<String> upper = criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getReference().toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));
		}
		// REcherche Version
		if (estNonVide(request.getVersion())) {
			Expression<String> path = root.get(PREDICATE_VERSION);
			Expression<String> upper = criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getVersion().toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));
		}

		// REcherche Type
		if (estNonVide(request.getType())) {
			Expression<String> path = root.get(PREDICATE_TYPE);
			Expression<String> upper = criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getType().toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));
		}

		// Set request.dateDe on whereClause if not null
		if (request.getDateDe() != null) {

			whereClause
					.add(criteriaBuilder.greaterThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE), request.getDateDe()));
		}

		// Set request.dateA on whereClause if not null
		if (request.getDateA() != null) {

			whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE), request.getDateA()));
		}
		if (request.getMetrageMin() != null) {
			whereClause.add(
					criteriaBuilder.greaterThanOrEqualTo(root.<Double>get(PREDICATE_METRAGE), request.getMetrageMin()));
		}

		// Set request.metrageMax on whereClause if not null
		if (request.getMetrageMax() != null) {
			whereClause.add(
					criteriaBuilder.lessThanOrEqualTo(root.<Double>get(PREDICATE_METRAGE), request.getMetrageMax()));
		}

		// Set request.idDepot on whereClause if not null
		if (request.getIdDepotDestination() != null) {
			whereClause.add(
					criteriaBuilder.equal(root.get(PREDICATE_IDDEPOT_DESTINATION), request.getIdDepotDestination()));
		}

		// Set request.idDepot on whereClause if not null
		if (request.getIdDepotOrigine() != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_IDDEPOT_ORIGINE), request.getIdDepotOrigine()));
		}

		if (request.getBoutiqueId() != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_BOUTIQUE_ID), request.getBoutiqueId()));
		}

		if (estNonVide(request.getReferenceSortie())) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_REFERENCE_SORTIE), request.getReferenceSortie()));
		}
		
		if (estNonVide(request.getReferenceReception())) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_REFERENCE_RECEPTION), request.getReferenceReception()));
		}


		if (estNonVide(request.getStatus())) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_STATUS), request.getStatus()));
		}
		
		if (estNonVide(request.getStatusAuto())) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_STATUS_AUTO), request.getStatusAuto()));
		}

		List<Object[]> resultatEntite = null;

		// criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));

		criteriaQuery.select(criteriaBuilder.array(

				root.get("id").as(Long.class), root.get("reference").as(String.class),
				root.get("referenceSortie").as(String.class), root.get("idDepotOrigine").as(Long.class),
				root.get("idDepotDestination").as(Long.class),

				root.get("date").as(Calendar.class),

				root.get("observations").as(String.class),

				root.get("type").as(String.class),

				root.get("boutiqueId").as(Long.class),

				root.get("metrageTotal").as(Double.class),

				root.get("version").as(String.class),
				
				root.get("referenceReception").as(String.class),
				root.get("status").as(String.class),				
				root.get("statusAuto").as(String.class)

		)).where(whereClause.toArray(new Predicate[] {}));

		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));

		// If criterias are empty
		if (request.isOptimized()) {
			resultatEntite = this.entityManager.createQuery(criteriaQuery).setMaxResults(MAX_RESULTS).getResultList();

		} else {
			resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
		}

		List<BonTransfertValue> list = new ArrayList<BonTransfertValue>();

		for (Object[] element : resultatEntite) {

			BonTransfertEntity entity = new BonTransfertEntity();

			entity.setId((Long) element[0]);
			entity.setReference((String) element[1]);
			entity.setReferenceSortie((String) element[2]);

			entity.setIdDepotOrigine((Long) element[3]);
			entity.setIdDepotDestination((Long) element[4]);
			entity.setDate((Calendar) element[5]);

			entity.setObservations((String) element[6]);
			entity.setType((String) element[7]);
			entity.setBoutiqueId((Long) element[8]);
			entity.setMetrageTotal((Double) element[9]);
			entity.setVersion((String) element[10]);
			
			entity.setReferenceReception((String) element[11]);
			entity.setStatus((String) element[12]);
			entity.setStatusAuto((String) element[13]);

			BonTransfertValue dto = persistanceTransfertUtilities.toValue(entity);
			list.add(dto);
		}

		ResultatRechecheBonTransfertValue resultat = new ResultatRechecheBonTransfertValue();
		Collections.sort(list);
		resultat.setNombreResultaRechercher(Long.valueOf(list.size()));
		resultat.setList(new TreeSet<>(list));

		return resultat;
	}

	@Override
	public String createBonTransfert(BonTransfertValue bonTransfertValue) {

		BonTransfertEntity entity = (BonTransfertEntity) this
				.creer(persistanceTransfertUtilities.toEntity(bonTransfertValue));

		return entity.getId().toString();
	}

	@Override
	public BonTransfertValue getBonTransfertById(Long id) {

		BonTransfertEntity bonLivraisonEntity = this.rechercherParId(id, BonTransfertEntity.class);
		return persistanceTransfertUtilities.toValue(bonLivraisonEntity);
	}

	@Override
	public String updateBonTransfert(BonTransfertValue bonTransfertValue) {

		BonTransfertEntity entity = (BonTransfertEntity) this
				.modifier(persistanceTransfertUtilities.toEntity(bonTransfertValue));

		return entity.getId().toString();
	}

	@Override
	public void deleteBonTransfert(Long id) {

		this.supprimerEntite(BonTransfertEntity.class, id);
	}

	private boolean estNonVide(String val) {
		return val != null && !"".equals(val);
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public BonTransfertValue getBonTransfertByReference(String reference) {
		BonTransfertValue resultat = null;

		// Set reference on whereClause if not empty or null
		if (estNonVide(reference)) {

			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();

			CriteriaQuery<BonTransfertEntity> criteriaQuery = criteriaBuilder.createQuery(BonTransfertEntity.class);
			List<Predicate> whereClause = new ArrayList<Predicate>();

			Root<BonTransfertEntity> root = criteriaQuery.from(BonTransfertEntity.class);

			/*
			 * Expression<String> path = root.get(PREDICATE_REFERENCE); Expression<String>
			 * upper =criteriaBuilder.upper(path); Predicate predicate =
			 * criteriaBuilder.like(upper, PERCENT + reference.toUpperCase() + PERCENT);
			 * whereClause.add(criteriaBuilder.and(predicate));
			 */

			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_REFERENCE), reference));

			criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
			List<BonTransfertEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

			if (resultatEntite != null)
				if (resultatEntite.size() > 0)
					resultat = persistanceTransfertUtilities.toValue(resultatEntite.get(FIRST_INDEX));

		}

		return resultat;
	}

	@Override
	public BonTransfertValue validerBTsortieByReference(String reference) {
		BonTransfertValue resultat = null;

		// Set reference on whereClause if not empty or null
		if (estNonVide(reference)) {

			BonTransfertValue btReception = getBonTransfertByReferenceSortie(reference);

			if (btReception == null) {

				CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();

				CriteriaQuery<BonTransfertEntity> criteriaQuery = criteriaBuilder.createQuery(BonTransfertEntity.class);
				List<Predicate> whereClause = new ArrayList<Predicate>();

				Root<BonTransfertEntity> root = criteriaQuery.from(BonTransfertEntity.class);

				/*
				 * Expression<String> path = root.get(PREDICATE_REFERENCE); Expression<String>
				 * upper =criteriaBuilder.upper(path); Predicate predicate =
				 * criteriaBuilder.like(upper, PERCENT + reference.toUpperCase() + PERCENT);
				 * whereClause.add(criteriaBuilder.and(predicate));
				 */

				whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_REFERENCE), reference));

				criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
				List<BonTransfertEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

				if (resultatEntite != null)
					if (resultatEntite.size() > 0)
						resultat = persistanceTransfertUtilities.toValue(resultatEntite.get(FIRST_INDEX));

			}

		}

		return resultat;
	}

	@Override
	public BonTransfertValue getBonTransfertByReferenceSortie(String reference) {
		BonTransfertValue resultat = null;

		// Set reference on whereClause if not empty or null
		if (estNonVide(reference)) {

			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();

			CriteriaQuery<BonTransfertEntity> criteriaQuery = criteriaBuilder.createQuery(BonTransfertEntity.class);
			List<Predicate> whereClause = new ArrayList<Predicate>();

			Root<BonTransfertEntity> root = criteriaQuery.from(BonTransfertEntity.class);

			/*
			 * Expression<String> path = root.get(PREDICATE_REFERENCE); Expression<String>
			 * upper =criteriaBuilder.upper(path); Predicate predicate =
			 * criteriaBuilder.like(upper, PERCENT + reference.toUpperCase() + PERCENT);
			 * whereClause.add(criteriaBuilder.and(predicate));
			 */

			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_REFERENCE_SORTIE), reference));

			criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
			List<BonTransfertEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

			if (resultatEntite != null)
				if (resultatEntite.size() > 0)
					resultat = persistanceTransfertUtilities.toValue(resultatEntite.get(FIRST_INDEX));

		}

		return resultat;
	}

}
