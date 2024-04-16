package com.gpro.consulting.tiers.logistique.persistance.gs.impl;

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
import com.gpro.consulting.tiers.logistique.coordination.gs.value.BonInventaireValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.RechercheMulticritereBonInventaireValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.ResultatRechecheBonInventaireValue;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.IBonLivraisonPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gs.IBonInventairePersistance;
import com.gpro.consulting.tiers.logistique.persistance.gs.entite.BonInventaireEntity;
import com.gpro.consulting.tiers.logistique.persistance.gs.utilities.PersistanceUtilitiesGs;

/**
 * Implementation of {@link IBonLivraisonPersistance} interface.
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */

@Component
public class BonInventairePersistanceImpl extends AbstractPersistance implements IBonInventairePersistance {

	private static final Logger logger = LoggerFactory.getLogger(BonInventairePersistanceImpl.class);

	private String PREDICATE_DECLARE = "declare";

	private String PREDICATE_OBSERVATION = "observations";
	private String PREDICATE_REFERENCE = "reference";
	private String PREDICATE_VERSION = "version";
	private String PREDICATE_TYPE = "type";
	private String PREDICATE_DATE = "date";
	private String PREDICATE_METRAGE = "metrageTotal";

	private String PREDICATE_Inventaire = "Inventaire";
	private String PREDICATE_CLIENT = "partieIntId";
	private String PREDICATE_GROUPE_CLIENT = "groupeClientId";
//	private String PREDICATE_AVEC_FACTURE= "date";
	private String PREDICATE_ETAT = "etat";
	private String PREDICATE_PRIX = "montantTTC";
	private String PREDICATE_INFO_SORTIE = "infoSortie";
	private String PREDICATE_MARCHE = "marcheId";
	private String PREDICATE_REGION = "regionId";
	private String PREDICATE_NATURELIVRAISON = "natureLivraison";
	private String PREDICATE_INFO_LIVRAISON = "infoLivraison";
	private String PREDICATE_IDDEPOT = "idDepot";
	private String PREDICATE_BOUTIQUE_ID = "boutiqueId";

	private String PREDICATE_REFERENCE_BON_COMMANDE = "refCommande";

	private String PERCENT = "%";

	private int FIRST_INDEX = 0;

	private int MAX_RESULTS = 52;

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private PersistanceUtilitiesGs persistanceUtilitiesGs;

	@Override
	public ResultatRechecheBonInventaireValue rechercherMultiCritere(RechercheMulticritereBonInventaireValue request) {

		// logger.info("rechercherMultiCritere");
		// System.out.println("rech multi avec Inventaire"+request.getInventaire());

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();

		// CriteriaQuery<BonInventaireEntity> criteriaQuery =
		// criteriaBuilder.createQuery(BonInventaireEntity.class);
		CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);

		List<Predicate> whereClause = new ArrayList<Predicate>();

		Root<BonInventaireEntity> root = criteriaQuery.from(BonInventaireEntity.class);

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
		if (request.getIdDepot() != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_IDDEPOT), request.getIdDepot()));
		}
		
		if (request.getBoutiqueId() != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_BOUTIQUE_ID), request.getBoutiqueId()));
		}

		List<Object[]> resultatEntite = null;

		// criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));

		criteriaQuery.select(criteriaBuilder.array(

				root.get("id").as(Long.class), root.get("reference").as(String.class),
				root.get("observations").as(String.class), root.get("date").as(Calendar.class),
				root.get("metrageTotal").as(Double.class),

				root.get("version").as(String.class), root.get("idDepot").as(Long.class),

				root.get("type").as(String.class),

				root.get("boutiqueId").as(Long.class)

		)).where(whereClause.toArray(new Predicate[] {}));

		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));

		// If criterias are empty
		if (request.isOptimized()) {
			resultatEntite = this.entityManager.createQuery(criteriaQuery).setMaxResults(MAX_RESULTS).getResultList();

		} else {
			resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
		}

		List<BonInventaireValue> list = new ArrayList<BonInventaireValue>();

		for (Object[] element : resultatEntite) {

			BonInventaireEntity entity = new BonInventaireEntity();

			entity.setId((Long) element[0]);
			entity.setReference((String) element[1]);
			entity.setObservations((String) element[2]);
			entity.setDate((Calendar) element[3]);
			entity.setMetrageTotal((Double) element[4]);
			entity.setVersion((String) element[5]);

			entity.setIdDepot((Long) element[6]);

			entity.setType((String) element[7]);
			entity.setBoutiqueId((Long) element[8]);

			BonInventaireValue dto = persistanceUtilitiesGs.toValue(entity);
			list.add(dto);
		}

		ResultatRechecheBonInventaireValue resultat = new ResultatRechecheBonInventaireValue();
		Collections.sort(list);
		resultat.setNombreResultaRechercher(Long.valueOf(list.size()));
		resultat.setList(new TreeSet<>(list));

		return resultat;
	}

	@Override
	public String createBonInventaire(BonInventaireValue bonInventaireValue) {

		BonInventaireEntity entity = (BonInventaireEntity) this
				.creer(persistanceUtilitiesGs.toEntity(bonInventaireValue));

		return entity.getId().toString();
	}

	@Override
	public BonInventaireValue getBonInventaireById(Long id) {

		BonInventaireEntity bonLivraisonEntity = this.rechercherParId(id, BonInventaireEntity.class);
		return persistanceUtilitiesGs.toValue(bonLivraisonEntity);
	}

	@Override
	public String updateBonInventaire(BonInventaireValue bonInventaireValue) {

		BonInventaireEntity entity = (BonInventaireEntity) this
				.modifier(persistanceUtilitiesGs.toEntity(bonInventaireValue));

		return entity.getId().toString();
	}

	@Override
	public void deleteBonInventaire(Long id) {

		this.supprimerEntite(BonInventaireEntity.class, id);
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

}
