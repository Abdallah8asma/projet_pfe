package com.gpro.consulting.tiers.logistique.persistance.gc.achat.reglement.impl;

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
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.RechercheMulticritereReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.ReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.ResultatRechecheReglementAchatCompletValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.ResultatRechecheReglementAchatElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.ResultatRechecheReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.TypeReglementAchatValue;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reglement.IReglementAchatPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reglement.entity.ReglementAchatEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reglement.entity.TypeReglementAchatEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reglement.utility.ReglementAchatPersistanceUtilities;
import com.gpro.consulting.tiers.logistique.persistance.gc.reglement.IReglementPersistance;

/**
 * Implementation of {@link IReglementPersistance} interface.
 *  
 * @author Wahid Gazzah
 * @since 01/07/2016
 *
 */

@Component
public class ReglementAchatPersistanceImpl extends AbstractPersistance implements IReglementAchatPersistance{

	private static final Logger logger = LoggerFactory.getLogger(ReglementAchatPersistanceImpl.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private String PREDICATE_DATE_REGLEMENT = "date";
	private String PREDICATE_PARTIEINT = "partieIntId";
	private String PREDICATE_GROUPE_CLIENT = "groupeClientId";
	private String PREDICATE_REFERENCE = "reference";
	private String PREDICATE_MONTANT = "montantTotal";
	private String PREDICATE_IDDEPOT = "idDepot";
	private String PREDICATE_BOUTIQUEID = "boutiqueId";
	
	private String PERCENT = "%";
	
	@Override
	public String create(ReglementAchatValue reglement) {

		ReglementAchatEntity entity = (ReglementAchatEntity) this.modifier(ReglementAchatPersistanceUtilities.toEntity(reglement));

	    return entity.getId().toString();
	}

	@Override
	public ReglementAchatValue getById(Long id) {

		ReglementAchatEntity entity = this.rechercherParId(id, ReglementAchatEntity.class);

	    return ReglementAchatPersistanceUtilities.toValue(entity);
	}

	@Override
	public String update(ReglementAchatValue reglement) {

		ReglementAchatEntity entity = (ReglementAchatEntity) this.modifier(ReglementAchatPersistanceUtilities.toEntity(reglement));

	    return entity.getId().toString();
	}

	@Override
	public void delete(Long id) {
		
		this.supprimerEntite(ReglementAchatEntity.class, id);
	}

	@Override
	public List<ReglementAchatValue> getAll() {
		
		List<ReglementAchatEntity> listEntity = this.lister(ReglementAchatEntity.class);
		
		return ReglementAchatPersistanceUtilities.listToValue(listEntity);
	}

	@Override
	public ResultatRechecheReglementAchatValue rechercherMultiCritere(
			RechercheMulticritereReglementAchatValue request) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<ReglementAchatEntity> criteriaQuery = criteriaBuilder.createQuery(ReglementAchatEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<ReglementAchatEntity> root = criteriaQuery.from(ReglementAchatEntity.class);
		
		
		if(request.getBoutiqueId() != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_BOUTIQUEID), request.getBoutiqueId()));
		}
		
	    // Set request.partieIntId on whereClause if not null
		if (request.getPartieIntId()!= null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_PARTIEINT), request.getPartieIntId()));
		}
		
	    // Set request.Groupe client on whereClause if not null
			if (request.getGroupeClientId()!= null) {
				whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_GROUPE_CLIENT), request.getGroupeClientId()));
			}
		
		 // magazin
		if (request.getIdDepot() != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_IDDEPOT), request.getIdDepot()));
		}
		
	    // Set request.reference on whereClause if not null
		if (estNonVide(request.getReference())) {
			Expression<String> path = root.get(PREDICATE_REFERENCE);
			Expression<String> upper =criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getReference().toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));
		}
		
		// Set request.dateReglementMin on whereClause if not null
	    if (request.getDateReglementMin() != null) {
	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE_REGLEMENT), request.getDateReglementMin()));
	    }
	    
	    // Set request.dateReglementMax on whereClause if not null
	    if (request.getDateReglementMax() != null) {
	    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE_REGLEMENT), request.getDateReglementMax()));
	    }
		
		// Set request.montantMin on whereClause if not null
	    if (request.getMontantMin() != null) {
	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Double>get(PREDICATE_MONTANT), request.getMontantMin()));
	    }
	    
		// Set request.montantMax on whereClause if not null
	    if (request.getMontantMax() != null) {
	    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Double>get(PREDICATE_MONTANT), request.getMontantMax()));
	    }
		
		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    List <ReglementAchatEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

	    List<ResultatRechecheReglementAchatElementValue> list = new ArrayList<ResultatRechecheReglementAchatElementValue>();
	    
	    for (ReglementAchatEntity entity : resultatEntite) {
	    	ResultatRechecheReglementAchatElementValue element = ReglementAchatPersistanceUtilities.toResultatRechecheReglementElementValue(entity);
	    	
	    	list.add(element);
	    }

	    ResultatRechecheReglementAchatValue resultat = new ResultatRechecheReglementAchatValue();
	    Collections.sort(list);
	    resultat.setNombreResultaRechercher(Long.valueOf(list.size()));
	    resultat.setList(new TreeSet<>(list));

	    return resultat;
	}

	@Override
	public List<ReglementAchatValue> getByFournisseurId(Long clientId) {

		
		List<ReglementAchatValue> resultat = new ArrayList<ReglementAchatValue>();
		
	    // Set clientId on whereClause if not null
		if (clientId != null) {
			
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			CriteriaQuery<ReglementAchatEntity> criteriaQuery = criteriaBuilder.createQuery(ReglementAchatEntity.class);
			List<Predicate> whereClause = new ArrayList<Predicate>();
			Root<ReglementAchatEntity> root = criteriaQuery.from(ReglementAchatEntity.class);
			
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_PARTIEINT), clientId));
			
			criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		    List <ReglementAchatEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
		    
		    for (ReglementAchatEntity entity : resultatEntite) {
		    	ReglementAchatValue element = ReglementAchatPersistanceUtilities.toValue(entity);
		    	
		    	resultat.add(element);
		    }
		    
		}

	    return resultat;
	}
	
	/******** liste refeglementCache *********/
	@Override
	public List<ReglementAchatValue> listeRefReglementCache() {
		CriteriaBuilder vBuilder = this.entityManager.getCriteriaBuilder();

		CriteriaQuery<ReglementAchatEntity> query = vBuilder
				.createQuery(ReglementAchatEntity.class);
		Root<ReglementAchatEntity> root = query
				.from(ReglementAchatEntity.class);
		
		query.multiselect(root.get("id"), root.get("reference"));
		
		List<ReglementAchatEntity> results = this.entityManager.createQuery(
				query).getResultList();

		/** Conversion de la liste en valeur */
		List<ReglementAchatValue> vListeResultat = new ArrayList<ReglementAchatValue>();
		
		for (ReglementAchatEntity vPartieInteresseeEntite : results) {
			ReglementAchatValue vPiCache = new ReglementAchatValue();
			vPiCache.setId(vPartieInteresseeEntite.getId());
			vPiCache.setReference(vPartieInteresseeEntite.getReference());
			vListeResultat.add(vPiCache);
		}
		return vListeResultat;
	}
	
	@Override
	public ResultatRechecheReglementAchatCompletValue rechercherMultiCritereComplet(
			RechercheMulticritereReglementAchatValue request) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<ReglementAchatEntity> criteriaQuery = criteriaBuilder.createQuery(ReglementAchatEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<ReglementAchatEntity> root = criteriaQuery.from(ReglementAchatEntity.class);
		
	    // Set request.partieIntId on whereClause if not null
		if (request.getPartieIntId() != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_PARTIEINT), request.getPartieIntId()));
		}
		
	    // Set request.reference on whereClause if not null
		if (estNonVide(request.getReference())) {
			Expression<String> path = root.get(PREDICATE_REFERENCE);
			Expression<String> upper =criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getReference().toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));
		}
		
		// Set request.dateReglementMin on whereClause if not null
	    if (request.getDateReglementMin() != null) {
	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE_REGLEMENT), request.getDateReglementMin()));
	    }
	    
	    // Set request.dateReglementMax on whereClause if not null
	    if (request.getDateReglementMax() != null) {
	    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE_REGLEMENT), request.getDateReglementMax()));
	    }
		
		// Set request.montantMin on whereClause if not null
	    if (request.getMontantMin() != null) {
	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Double>get(PREDICATE_MONTANT), request.getMontantMin()));
	    }
	    
		// Set request.montantMax on whereClause if not null
	    if (request.getMontantMax() != null) {
	    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Double>get(PREDICATE_MONTANT), request.getMontantMax()));
	    }
		
		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    List <ReglementAchatEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

	    List<ReglementAchatValue> list = new ArrayList<ReglementAchatValue>();
	    
	    for (ReglementAchatEntity entity : resultatEntite) {
	    	ReglementAchatValue element = ReglementAchatPersistanceUtilities.toValue(entity);
	    	
	    	list.add(element);
	    }

	    ResultatRechecheReglementAchatCompletValue resultat = new ResultatRechecheReglementAchatCompletValue();
	    Collections.sort(list);
	    resultat.setNombreResultaRechercher(Long.valueOf(list.size()));
	    resultat.setList(new TreeSet<>(list));

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

	@Override
	public TypeReglementAchatValue getTypeReglementById(Long typeReglementId) {
		
		TypeReglementAchatEntity entity = this.rechercherParId(typeReglementId, TypeReglementAchatEntity.class);

	    return ReglementAchatPersistanceUtilities.toValue(entity);
	}

	@Override
	public List<ReglementAchatValue> getByGroupeFournisseurId(Long groupeClientId) {
		
		List<ReglementAchatValue> resultat = new ArrayList<ReglementAchatValue>();
		
	    // Set clientId on whereClause if not null
		if (groupeClientId != null) {
			
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			CriteriaQuery<ReglementAchatEntity> criteriaQuery = criteriaBuilder.createQuery(ReglementAchatEntity.class);
			List<Predicate> whereClause = new ArrayList<Predicate>();
			Root<ReglementAchatEntity> root = criteriaQuery.from(ReglementAchatEntity.class);
			
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_GROUPE_CLIENT), groupeClientId));
			
			criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		    List <ReglementAchatEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
		    
		    for (ReglementAchatEntity entity : resultatEntite) {
		    	ReglementAchatValue element = ReglementAchatPersistanceUtilities.toValue(entity);
		    	
		    	resultat.add(element);
		    }
		    
		}

	    return resultat;
	}
	
}
