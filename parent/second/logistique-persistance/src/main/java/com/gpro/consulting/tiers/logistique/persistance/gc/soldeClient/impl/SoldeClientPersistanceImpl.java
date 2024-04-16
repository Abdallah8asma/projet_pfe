package com.gpro.consulting.tiers.logistique.persistance.gc.soldeClient.impl;

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
import com.gpro.consulting.tiers.logistique.coordination.gc.soldeClient.value.RechercheMulticritereSoldeClientValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.soldeClient.value.ResultatRechecheSoldeClientValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.soldeClient.value.SoldeClientValue;
import com.gpro.consulting.tiers.logistique.persistance.gc.reglement.entity.DetailsReglementEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.reglement.entity.ReglementEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.soldeClient.ISoldeClientPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.soldeClient.entite.SoldeClientEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.soldeClient.utilities.SoldeClientPersistanceUtilities;

/**
 * @author Ameni Berrich
 *
 */
@Component
public class SoldeClientPersistanceImpl extends AbstractPersistance implements ISoldeClientPersistance {

	private static final Logger logger = LoggerFactory.getLogger(SoldeClientPersistanceImpl.class);

	@PersistenceContext
	private EntityManager entityManager;
	
	private String COLUMN_PARTIEINT = "partIntId";
	private String COLUMN_BLOQUE = "bloque";
	private String PREDICATE_DATE_REGLEMENT = "dateIntroduction";
	
	@Override
	public ResultatRechecheSoldeClientValue rechercherMultiCritere(
			RechercheMulticritereSoldeClientValue request) {

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<SoldeClientEntity> criteriaQuery = criteriaBuilder.createQuery(SoldeClientEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<SoldeClientEntity> root = criteriaQuery.from(SoldeClientEntity.class);
		
		
		if (request.getPartieIntFamilleId() != null) {
		
			Subquery<Long> subQuery = criteriaQuery.subquery(Long.class);
			Root<PartieInteresseEntite> subRoot = subQuery.from(PartieInteresseEntite.class);

			subQuery.select(subRoot.<Long>get("id"));
			subQuery.where(criteriaBuilder.equal(subRoot.get("famillePartieInteressee"), request.getPartieIntFamilleId()));
			whereClause.add(criteriaBuilder.in(root.get(COLUMN_PARTIEINT)).value(subQuery));
			
		}
		
		
		if (request.getDeviseId() != null) {
			
			Subquery<Long> subQuery = criteriaQuery.subquery(Long.class);
			Root<PartieInteresseEntite> subRoot = subQuery.from(PartieInteresseEntite.class);

			subQuery.select(subRoot.<Long>get("id"));
			subQuery.where(criteriaBuilder.equal(subRoot.get("deviseId"), request.getDeviseId()));
			whereClause.add(criteriaBuilder.in(root.get(COLUMN_PARTIEINT)).value(subQuery));
			
		}
		
		
		
	    // Set request.partieIntId on whereClause if not null
		if (request.getPartieIntId() != null) {
			//System.out.println("***1");
			whereClause.add(criteriaBuilder.equal(root.get(COLUMN_PARTIEINT), request.getPartieIntId()));
		}
		
		// Set request.bloque on whereClause if not null
		if (request.getBloque() != null) {
			//System.out.println("***2");
			whereClause.add(criteriaBuilder.equal(root.get(COLUMN_BLOQUE), request.getBloque()));
		}
		
		// Set request.dateMin on whereClause if not null
//	    if (request.getDateMin() != null) {
//	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE_REGLEMENT), request.getDateMin()));
//	    }
		
		
		
		//TODO Modifie par Ghazi on 15/05/2020 - pas besoin de rechercher par date pour les soldes 
//	    
	    // Set request.dateReglementMax on whereClause if not null
//	    if (request.getDateMax() != null) {
//	    	System.out.println("***3");
//	    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE_REGLEMENT), request.getDateMax()));
//	    }
		
		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    List <SoldeClientEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

	    List<SoldeClientValue> list = new ArrayList<SoldeClientValue>();
	    
	    for (SoldeClientEntity entity : resultatEntite) {
	    	SoldeClientValue element = SoldeClientPersistanceUtilities.toValue(entity);
	    	
	    	list.add(element);
	    }

	    ResultatRechecheSoldeClientValue resultat = new ResultatRechecheSoldeClientValue();
	    resultat.setNombreResultaRechercher(Long.valueOf(list.size()));
	    resultat.setList(list);

	    return resultat;
	}

	@Override
	public SoldeClientValue getById(Long id) {
		SoldeClientEntity SoldeEntity = this.rechercherParId(id, SoldeClientEntity.class);

	    return SoldeClientPersistanceUtilities.toValue(SoldeEntity);
	}

	@Override
	public String update(SoldeClientValue soldeClientValue) {
		SoldeClientEntity entity = (SoldeClientEntity) this.modifier(SoldeClientPersistanceUtilities.toEntity(soldeClientValue));

	    return entity.getId().toString();
	}

	@Override
	public String create(SoldeClientValue soldeClientValue) {
		SoldeClientEntity entity = (SoldeClientEntity) this.creer(SoldeClientPersistanceUtilities.toEntity(soldeClientValue));

	    return entity.getId().toString();
	}

	@Override
	public void delete(Long id) {
		this.supprimerEntite(SoldeClientEntity.class, id);
		
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public SoldeClientValue getByClientId(Long clientId) {
		
		SoldeClientValue resultat = null;
		
		if (clientId != null) {
			
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			CriteriaQuery<SoldeClientEntity> criteriaQuery = criteriaBuilder.createQuery(SoldeClientEntity.class);
			List<Predicate> whereClause = new ArrayList<Predicate>();
			Root<SoldeClientEntity> root = criteriaQuery.from(SoldeClientEntity.class);
			
			whereClause.add(criteriaBuilder.equal(root.get(COLUMN_PARTIEINT), clientId));
			
			criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		    List <SoldeClientEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
		    
		    if(resultatEntite != null){
		    	
		    	if(resultatEntite.size() > 0){
		    		
		    		resultat = SoldeClientPersistanceUtilities.toValue(resultatEntite.get(0));
		    	}
		    }
			
		}
		
	    return resultat;
	}


	
}
