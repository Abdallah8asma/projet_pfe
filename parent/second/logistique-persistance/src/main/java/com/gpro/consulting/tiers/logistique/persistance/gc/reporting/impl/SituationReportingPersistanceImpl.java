package com.gpro.consulting.tiers.logistique.persistance.gc.reporting.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.situation.value.RechercheMulticritereSituationReportingValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.situation.value.ResultatRechecheSituationReportingValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.situation.value.SituationReportingValue;
import com.gpro.consulting.tiers.logistique.persistance.gc.reporting.ISituationReportingPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.soldeClient.entite.SoldeClientEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.soldeClient.utilities.SoldeClientPersistanceUtilities;

/**
 * @author Ameni Berrich
 *
 */
@Component
public class SituationReportingPersistanceImpl extends AbstractPersistance
		implements ISituationReportingPersistance {

	private static final Logger logger = LoggerFactory.getLogger(SituationReportingPersistanceImpl.class);
	
	@PersistenceContext
	private EntityManager entityManager;


	private String PREDICATE_DATE_REGLEMENT = "dateIntroduction";
	private String PREDICATE_PARTIEINT = "partIntId";
	//private String PREDICATE_MONTANT = "montantTotal";
	
	@Override
	public ResultatRechecheSituationReportingValue rechercherMultiCritere(
			RechercheMulticritereSituationReportingValue request) {
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<SoldeClientEntity> criteriaQuery = criteriaBuilder.createQuery(SoldeClientEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		
		Root<SoldeClientEntity> root = criteriaQuery.from(SoldeClientEntity.class);
		
	    // Set request.partieIntId on whereClause if not null
		if (request.getPartieIntId() != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_PARTIEINT), request.getPartieIntId()));
		}
		
		// Set request.dateMin on whereClause if not null
	    if (request.getDateMin() != null) {
	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE_REGLEMENT), request.getDateMin()));
	    }
	    
	    // Set request.dateReglementMax on whereClause if not null
	    if (request.getDateMax() != null) {
	    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE_REGLEMENT), request.getDateMax()));
	    }
		
//		// Set request.montantMin on whereClause if not null
//	    if (request.getSoldeMin() != null) {
//	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Double>get(PREDICATE_MONTANT), request.getSoldeMin()));
//	    }
//	    
//		// Set request.montantMax on whereClause if not null
//	    if (request.getSoldeMax() != null) {
//	    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Double>get(PREDICATE_MONTANT), request.getSoldeMax()));
//	    }
		
		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    List <SoldeClientEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

	    List<SituationReportingValue> list = new ArrayList<SituationReportingValue>();
	    
	    for (SoldeClientEntity entity : resultatEntite) {
	    	SituationReportingValue element = SoldeClientPersistanceUtilities.toSituationReportingValue(entity);
	    	
	    	list.add(element);
	    }

	    ResultatRechecheSituationReportingValue resultat = new ResultatRechecheSituationReportingValue();
	    resultat.setNombreResultaRechercher(Long.valueOf(list.size()));
	    resultat.setListSituationReporting(list);

	    return resultat;
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}


	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}


	private boolean estNonVide(String val) {

		return val != null && !"".equals(val);
	}

}
