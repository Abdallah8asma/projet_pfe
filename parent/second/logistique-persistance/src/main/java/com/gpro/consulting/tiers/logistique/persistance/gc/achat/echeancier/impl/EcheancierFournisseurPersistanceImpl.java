package com.gpro.consulting.tiers.logistique.persistance.gc.achat.echeancier.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.entity.PartieInteresseEntite;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.echeancier.RechercheMulticritereDetailReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.echeancier.ResultatRechecheDetailReglementAchatElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.echeancier.ResultatRechecheDetailReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.echeancier.RechercheMulticritereDetailReglementValue;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.echeancier.IEcheancierFournisseurPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reglement.entity.DetailsReglementAchatEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reglement.entity.ReglementAchatEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reglement.entity.TypeReglementAchatEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reglement.utility.ReglementAchatPersistanceUtilities;
import com.gpro.consulting.tiers.logistique.persistance.gc.reglement.entity.DetailsReglementEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.reglement.entity.ReglementEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.reglement.entity.TypeReglementEntity;

/**
 * @author Ameni Berrich
 *
 */
@Component
public class EcheancierFournisseurPersistanceImpl extends AbstractPersistance implements IEcheancierFournisseurPersistance {

	private static final Logger logger = LoggerFactory.getLogger(EcheancierFournisseurPersistanceImpl.class);

	private String PREDICATE_GROUPE_CLIENT = "groupeClientId";

	private String JOINTURE_REGLEMENT = "reglement";
	private String COLUMN_DATE_REGLEMENT = "date";

	private String JOINTURE_PARTIE_INT = "partieIntId";
	private String JOINTURE_REGION = "regionId";

	private String COLUMN_PARTIEINT_REGLEMENT = "partieIntId";
	private String COLUMN_REFERENCE_REGLEMENT = "reference";
	private String JOINTURE_TYPE_REGLEMENT = "typeReglement";
	private String COLUMN_TYPE = "id";
	private String COLUMN_NUM_PSC = "numPiece";
	private String COLUMN_REGLE = "regle";

	private String COLUMN_DATE_ECHEANCE = "dateEcheance";
	private String COLUMN_DATE_DEPOT_BANQUE = "dateDepotBanque";
	private String PERCENT = "%";

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public ResultatRechecheDetailReglementAchatValue rechercherMultiCritere(
			RechercheMulticritereDetailReglementAchatValue request) {
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<DetailsReglementAchatEntity> criteriaQuery = criteriaBuilder.createQuery(DetailsReglementAchatEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<DetailsReglementAchatEntity> root = criteriaQuery.from(DetailsReglementAchatEntity.class);

		// critaire list reglement id

		if (request.getReglementsId() != null && request.getReglementsId().size() > 0) {

			Join<DetailsReglementAchatEntity, ReglementAchatEntity> jointureDtReg = root.join(JOINTURE_REGLEMENT);

			whereClause.add(jointureDtReg.get("id").in(request.getReglementsId()));

		}

		// Set request.Groupe client on whereClause if not null
		if (request.getGroupeClientId() != null) {

			Join<DetailsReglementAchatEntity, ReglementAchatEntity> jointure_detailsReglement_reglement = root
					.join(JOINTURE_REGLEMENT);

			whereClause.add(criteriaBuilder.equal(jointure_detailsReglement_reglement.get(PREDICATE_GROUPE_CLIENT),
					request.getGroupeClientId()));
		}

		// Set request.regle on whereClause if not null
		if (request.getRegle() != null) {
			whereClause.add(criteriaBuilder.equal(root.get(COLUMN_REGLE), request.getRegle()));
		}

		// Set request.type on whereClause if not null
		if (request.getTypeReglementId() != null) {
			Join<DetailsReglementAchatEntity, TypeReglementAchatEntity> jointureDtTyp = root.join(JOINTURE_TYPE_REGLEMENT);
			whereClause.add(criteriaBuilder.equal(jointureDtTyp.get(COLUMN_TYPE), request.getTypeReglementId()));
		}

		// Set request.numPiece on whereClause if not null
		if (estNonVide(request.getNumPiece())) {
			whereClause.add(criteriaBuilder.equal(root.get(COLUMN_NUM_PSC), request.getNumPiece()));
		}

		// Set request.partieIntId on whereClause if not null
		if (request.getPartieIntId() != null) {
			Join<DetailsReglementAchatEntity, ReglementAchatEntity> jointureDtReg = root.join(JOINTURE_REGLEMENT);
			whereClause.add(
					criteriaBuilder.equal(jointureDtReg.get(COLUMN_PARTIEINT_REGLEMENT), request.getPartieIntId()));
		}

		// Set request.reference on whereClause if not null
		if (estNonVide(request.getReference())) {
			Join<DetailsReglementAchatEntity, ReglementAchatEntity> jointureDtReg = root.join(JOINTURE_REGLEMENT);
			whereClause
					.add(criteriaBuilder.equal(jointureDtReg.get(COLUMN_REFERENCE_REGLEMENT), request.getReference()));
		}

		// Set request.dateReglementMin on whereClause if not null
		if (request.getDateReglementDu() != null) {
			Join<DetailsReglementAchatEntity, ReglementAchatEntity> jointureDtReg = root.join(JOINTURE_REGLEMENT);
			whereClause.add(criteriaBuilder.greaterThanOrEqualTo(jointureDtReg.<Calendar>get(COLUMN_DATE_REGLEMENT),
					request.getDateReglementDu()));
		}

		// Set request.dateReglementMax on whereClause if not null
		if (request.getDateReglementAu() != null) {
			Join<DetailsReglementAchatEntity, ReglementAchatEntity> jointureDtReg = root.join(JOINTURE_REGLEMENT);
			whereClause.add(criteriaBuilder.lessThanOrEqualTo(jointureDtReg.<Calendar>get(COLUMN_DATE_REGLEMENT),
					request.getDateReglementAu()));
		}

		// Set request.dateEcheanceMin on whereClause if not null
		if (request.getDateEcheanceDu() != null) {
			whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Calendar>get(COLUMN_DATE_ECHEANCE),
					request.getDateEcheanceDu()));
		}

		// Set request.dateEcheanceMax on whereClause if not null
		if (request.getDateEcheanceAu() != null) {
			whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Calendar>get(COLUMN_DATE_ECHEANCE),
					request.getDateEcheanceAu()));
		}

		// Set request.operationId on whereClause if not null
		if (request.getRegionId() != null) {

			Join<DetailsReglementAchatEntity, ReglementAchatEntity> jointureDtReg = root.join(JOINTURE_REGLEMENT);

			Subquery<Long> subQuery = criteriaQuery.subquery(Long.class);
			Root<PartieInteresseEntite> subRoot = subQuery.from(PartieInteresseEntite.class);

			subQuery.select(subRoot.<Long>get("id"));
			subQuery.where(criteriaBuilder.equal(subRoot.get(JOINTURE_REGION), request.getRegionId()));
			whereClause.add(criteriaBuilder.in(jointureDtReg.get(JOINTURE_PARTIE_INT)).value(subQuery));

		}
		// Set request.avecTerme on whereClause if not null
		if (request.getAvecTerme() != null) {

			Join<DetailsReglementAchatEntity, TypeReglementAchatEntity> jointureDtTyp = root.join(JOINTURE_TYPE_REGLEMENT);

			whereClause.add(criteriaBuilder.equal(jointureDtTyp.get("aTerme"), request.getAvecTerme()));
		}
		
		if (estNonVide(request.getReferenceDetReglement())) {
			
			whereClause
					.add(criteriaBuilder.equal(root.get(COLUMN_REFERENCE_REGLEMENT), request.getReferenceDetReglement()));
		}
		
		
		// date depot banque
		if (request.getDateDepotBanqueDe() != null) {
			whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Calendar>get(COLUMN_DATE_DEPOT_BANQUE),
					request.getDateDepotBanqueDe()));
		}

		// Set request.dateEcheanceMax on whereClause if not null
		if (request.getDateDepotBanqueA() != null) {
			whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Calendar>get(COLUMN_DATE_DEPOT_BANQUE),
					request.getDateDepotBanqueA()));
		}
		
		
		

		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		List<DetailsReglementAchatEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

		List<ResultatRechecheDetailReglementAchatElementValue> list = new ArrayList<ResultatRechecheDetailReglementAchatElementValue>();

		for (DetailsReglementAchatEntity entity : resultatEntite) {
			ResultatRechecheDetailReglementAchatElementValue element = ReglementAchatPersistanceUtilities
					.toDetailResultatValue(entity);

			list.add(element);
		}
		// Sort: couche Domaine
		ResultatRechecheDetailReglementAchatValue resultat = new ResultatRechecheDetailReglementAchatValue();
		resultat.setList(list);
		return resultat;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.persistance.gc.echeancier.
	 * IEcheancierPersistance#getSommeMont(com.gpro.consulting.tiers.logistique.
	 * coordination.gc.reglement.echeancier.
	 * RechercheMulticritereDetailReglementValue)
	 */
	@Override
	public Double getSommeMont(RechercheMulticritereDetailReglementAchatValue request) {

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Double> criteriaQuery = criteriaBuilder.createQuery(Double.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<DetailsReglementAchatEntity> root = criteriaQuery.from(DetailsReglementAchatEntity.class);

		// Set request.regle on whereClause if not null
		if (request.getRegle() != null) {
			whereClause.add(criteriaBuilder.equal(root.get(COLUMN_REGLE), request.getRegle()));
		}

		// Set request.type on whereClause if not null
		if (request.getTypeReglementId() != null) {
			Join<DetailsReglementAchatEntity, TypeReglementAchatEntity> jointureDtTyp = root.join(JOINTURE_TYPE_REGLEMENT);
			whereClause.add(criteriaBuilder.equal(jointureDtTyp.get(COLUMN_TYPE), request.getTypeReglementId()));
		}

		// Set request.numPiece on whereClause if not null
		if (estNonVide(request.getNumPiece())) {
			whereClause.add(criteriaBuilder.equal(root.get(COLUMN_NUM_PSC), request.getNumPiece()));
		}

		// Set request.partieIntId on whereClause if not null
		if (request.getPartieIntId() != null) {
			Join<DetailsReglementAchatEntity, ReglementAchatEntity> jointureDtReg = root.join(JOINTURE_REGLEMENT);
			whereClause.add(
					criteriaBuilder.equal(jointureDtReg.get(COLUMN_PARTIEINT_REGLEMENT), request.getPartieIntId()));
		}

		// Set request.reference on whereClause if not null
		if (estNonVide(request.getReference())) {
			Join<DetailsReglementAchatEntity, ReglementAchatEntity> jointureDtReg = root.join(JOINTURE_REGLEMENT);
			whereClause
					.add(criteriaBuilder.equal(jointureDtReg.get(COLUMN_REFERENCE_REGLEMENT), request.getReference()));
		}

		// Set request.dateReglementMin on whereClause if not null
		if (request.getDateReglementDu() != null) {
			Join<DetailsReglementAchatEntity, ReglementAchatEntity> jointureDtReg = root.join(JOINTURE_REGLEMENT);
			whereClause.add(criteriaBuilder.greaterThanOrEqualTo(jointureDtReg.<Calendar>get(COLUMN_DATE_REGLEMENT),
					request.getDateReglementDu()));
		}

		// Set request.dateReglementMax on whereClause if not null
		if (request.getDateReglementAu() != null) {
			Join<DetailsReglementAchatEntity, ReglementAchatEntity> jointureDtReg = root.join(JOINTURE_REGLEMENT);
			whereClause.add(criteriaBuilder.lessThanOrEqualTo(jointureDtReg.<Calendar>get(COLUMN_DATE_REGLEMENT),
					request.getDateReglementAu()));
		}

		// Set request.dateEcheanceMin on whereClause if not null
		if (request.getDateEcheanceDu() != null) {
			whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Calendar>get(COLUMN_DATE_ECHEANCE),
					request.getDateEcheanceDu()));
		}

		// Set request.dateEcheanceMax on whereClause if not null
		if (request.getDateEcheanceAu() != null) {
			whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Calendar>get(COLUMN_DATE_ECHEANCE),
					request.getDateEcheanceAu()));
		}

		// Set request.operationId on whereClause if not null
		if (request.getRegionId() != null) {

			Join<DetailsReglementAchatEntity, ReglementAchatEntity> jointureDtReg = root.join(JOINTURE_REGLEMENT);

			Subquery<Long> subQuery = criteriaQuery.subquery(Long.class);
			Root<PartieInteresseEntite> subRoot = subQuery.from(PartieInteresseEntite.class);

			subQuery.select(subRoot.<Long>get("id"));
			subQuery.where(criteriaBuilder.equal(subRoot.get(JOINTURE_REGION), request.getRegionId()));
			whereClause.add(criteriaBuilder.in(jointureDtReg.get(JOINTURE_PARTIE_INT)).value(subQuery));

		}
		// Set request.avecTerme on whereClause if not null
		if (request.getAvecTerme() != null) {

			Join<DetailsReglementAchatEntity, TypeReglementAchatEntity> jointureDtTyp = root.join(JOINTURE_TYPE_REGLEMENT);

			whereClause.add(criteriaBuilder.equal(jointureDtTyp.get("aTerme"), request.getAvecTerme()));
		}

		criteriaQuery.select(criteriaBuilder.sum(root.get("montant").as(Double.class)))
				.where(whereClause.toArray(new Predicate[] {}));
		Double sommeMont = this.entityManager.createQuery(criteriaQuery).getSingleResult();

		if (sommeMont != null) {
			return sommeMont;
		}
		return 0D;
	}

	
	
	
	
	@Override
	public Double getMontantPayementsEnCours(RechercheMulticritereDetailReglementAchatValue request) {
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Double> criteriaQuery = criteriaBuilder.createQuery(Double.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<DetailsReglementAchatEntity> root = criteriaQuery.from(DetailsReglementAchatEntity.class);
		
		
		
		

		// Set request.partieIntId on whereClause if not null
		if (request.getPartieIntId() != null) {
			Join<DetailsReglementAchatEntity, ReglementAchatEntity> jointureDtReg = root.join(JOINTURE_REGLEMENT);
			whereClause.add(
					criteriaBuilder.equal(jointureDtReg.get(COLUMN_PARTIEINT_REGLEMENT), request.getPartieIntId()));
		}
		
		
		whereClause.add(criteriaBuilder.equal(root.get(COLUMN_REGLE), false));
		
		
		Calendar today = Calendar.getInstance();
		
		whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Calendar>get(COLUMN_DATE_ECHEANCE),today));

		
			
			
		
		
		// Set request.operationId on whereClause if not null
		if (request.getRegionId() != null) {

			Join<DetailsReglementAchatEntity, ReglementAchatEntity> jointureDtReg = root.join(JOINTURE_REGLEMENT);

			Subquery<Long> subQuery = criteriaQuery.subquery(Long.class);
			Root<PartieInteresseEntite> subRoot = subQuery.from(PartieInteresseEntite.class);

			subQuery.select(subRoot.<Long>get("id"));
			subQuery.where(criteriaBuilder.equal(subRoot.get(JOINTURE_REGION), request.getRegionId()));
			whereClause.add(criteriaBuilder.in(jointureDtReg.get(JOINTURE_PARTIE_INT)).value(subQuery));

		}
		
		criteriaQuery.select(criteriaBuilder.sum(root.get("montant").as(Double.class)))
				.where(whereClause.toArray(new Predicate[] {}));
		Double sommeMont = this.entityManager.createQuery(criteriaQuery).getSingleResult();

		if (sommeMont != null) {
			return sommeMont;
		}
		return 0D;
	}

	@Override
	public Double getMontantImpaye(RechercheMulticritereDetailReglementAchatValue request) {
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Double> criteriaQuery = criteriaBuilder.createQuery(Double.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<DetailsReglementAchatEntity> root = criteriaQuery.from(DetailsReglementAchatEntity.class);
		
		
		
		

		// Set request.partieIntId on whereClause if not null
		if (request.getPartieIntId() != null) {
			Join<DetailsReglementAchatEntity, ReglementAchatEntity> jointureDtReg = root.join(JOINTURE_REGLEMENT);
			whereClause.add(
					criteriaBuilder.equal(jointureDtReg.get(COLUMN_PARTIEINT_REGLEMENT), request.getPartieIntId()));
		}
		
		
		whereClause.add(criteriaBuilder.equal(root.get(COLUMN_REGLE), false));
		
		
		Calendar today = Calendar.getInstance();
		
		whereClause.add(criteriaBuilder.lessThan(root.<Calendar>get(COLUMN_DATE_ECHEANCE),today));

		
			
			
		
		
		// Set request.operationId on whereClause if not null
		if (request.getRegionId() != null) {

			Join<DetailsReglementAchatEntity, ReglementAchatEntity> jointureDtReg = root.join(JOINTURE_REGLEMENT);

			Subquery<Long> subQuery = criteriaQuery.subquery(Long.class);
			Root<PartieInteresseEntite> subRoot = subQuery.from(PartieInteresseEntite.class);

			subQuery.select(subRoot.<Long>get("id"));
			subQuery.where(criteriaBuilder.equal(subRoot.get(JOINTURE_REGION), request.getRegionId()));
			whereClause.add(criteriaBuilder.in(jointureDtReg.get(JOINTURE_PARTIE_INT)).value(subQuery));

		}
		
		criteriaQuery.select(criteriaBuilder.sum(root.get("montant").as(Double.class)))
				.where(whereClause.toArray(new Predicate[] {}));
		Double sommeMont = this.entityManager.createQuery(criteriaQuery).getSingleResult();

		if (sommeMont != null) {
			return sommeMont;
		}
		return 0D;
	}



	


}
