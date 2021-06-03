package com.gpro.consulting.tiers.logistique.persistance.gc.achat.reglement.inverse.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Set;
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
import com.gpro.consulting.tiers.logistique.coordination.atelier.IConstanteLogistique;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.RechercheMulticritereReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.ReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.ResultatRechecheReglementAchatCompletValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.ResultatRechecheReglementAchatElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.ResultatRechecheReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.TypeReglementAchatValue;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reglement.entity.TypeReglementAchatEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reglement.inverse.IReglementInverseAchatPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reglement.inverse.entity.ReglementInverseAchatEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reglement.inverse.utility.ReglementInverseAchatPersistanceUtilities;
import com.gpro.consulting.tiers.logistique.persistance.gc.reglement.IReglementPersistance;

/**
 * Implementation of {@link IReglementPersistance} interface.
 *  
 * @author Wahid Gazzah
 * @since 01/07/2016
 *
 */

@Component
public class ReglementInverseAchatPersistanceImpl extends AbstractPersistance implements IReglementInverseAchatPersistance{

	private static final Logger logger = LoggerFactory.getLogger(ReglementInverseAchatPersistanceImpl.class);
	
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
	
	private String PREDICATE_DECLARE = "declarer";
	
	
	@Override
	public String create(ReglementAchatValue reglement) {

		ReglementInverseAchatEntity entity = (ReglementInverseAchatEntity) this.modifier(ReglementInverseAchatPersistanceUtilities.toEntity(reglement));

	    return entity.getId().toString();
	}

	@Override
	public ReglementAchatValue getById(Long id) {

		ReglementInverseAchatEntity entity = this.rechercherParId(id, ReglementInverseAchatEntity.class);

	    return ReglementInverseAchatPersistanceUtilities.toValue(entity);
	}

	@Override
	public String update(ReglementAchatValue reglement) {

		ReglementInverseAchatEntity entity = (ReglementInverseAchatEntity) this.modifier(ReglementInverseAchatPersistanceUtilities.toEntity(reglement));

	    return entity.getId().toString();
	}

	@Override
	public void delete(Long id) {
		
		this.supprimerEntite(ReglementInverseAchatEntity.class, id);
	}

	@Override
	public List<ReglementAchatValue> getAll() {
		
		List<ReglementInverseAchatEntity> listEntity = this.lister(ReglementInverseAchatEntity.class);
		
		return ReglementInverseAchatPersistanceUtilities.listToValue(listEntity);
	}

	@Override
	public ResultatRechecheReglementAchatValue rechercherMultiCritere(
			RechercheMulticritereReglementAchatValue request) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<ReglementInverseAchatEntity> criteriaQuery = criteriaBuilder.createQuery(ReglementInverseAchatEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<ReglementInverseAchatEntity> root = criteriaQuery.from(ReglementInverseAchatEntity.class);
		
		
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
	    
	    
		if (estNonVide(request.getDeclarerRech())) {
			Expression<Boolean> expression = root.get(PREDICATE_DECLARE);
			switch (request.getDeclarerRech()) {
				case IConstanteLogistique.YES:
					whereClause.add(criteriaBuilder.isTrue(expression));
					break;
				case IConstanteLogistique.NO:
					whereClause.add(criteriaBuilder.isFalse(expression));
					break;
				case IConstanteLogistique.ALL:
					break;
				default:
					break;
			}
		}
		
		if (estNonVide(request.getHasElementReglement())) {
			switch (request.getHasElementReglement()) {
				case IConstanteLogistique.YES:
					whereClause.add(criteriaBuilder.gt(criteriaBuilder.size(root.<Set>get("listElementReglement")), 0));
					break;
				case IConstanteLogistique.NO:
					whereClause.add(criteriaBuilder.equal(criteriaBuilder.size(root.<Set>get("listElementReglement")), 0));
					break;
				case IConstanteLogistique.ALL:
					break;
				default:
					break;
			}
		}
	    
		
		if (estNonVide(request.getHasDetailReglement())) {
			switch (request.getHasDetailReglement()) {
				case IConstanteLogistique.YES:
					whereClause.add(criteriaBuilder.gt(criteriaBuilder.size(root.<Set>get("listDetailsReglement")), 0));
					break;
				case IConstanteLogistique.NO:
					whereClause.add(criteriaBuilder.equal(criteriaBuilder.size(root.<Set>get("listDetailsReglement")), 0));
					break;
				case IConstanteLogistique.ALL:
					break;
				default:
					break;
			}
		}
		
		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    List <ReglementInverseAchatEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

	    List<ResultatRechecheReglementAchatElementValue> list = new ArrayList<ResultatRechecheReglementAchatElementValue>();
	    
	    for (ReglementInverseAchatEntity entity : resultatEntite) {
	    	ResultatRechecheReglementAchatElementValue element = ReglementInverseAchatPersistanceUtilities.toResultatRechecheReglementElementValue(entity);
	    	
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
			CriteriaQuery<ReglementInverseAchatEntity> criteriaQuery = criteriaBuilder.createQuery(ReglementInverseAchatEntity.class);
			List<Predicate> whereClause = new ArrayList<Predicate>();
			Root<ReglementInverseAchatEntity> root = criteriaQuery.from(ReglementInverseAchatEntity.class);
			
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_PARTIEINT), clientId));
			
			criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		    List <ReglementInverseAchatEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
		    
		    for (ReglementInverseAchatEntity entity : resultatEntite) {
		    	ReglementAchatValue element = ReglementInverseAchatPersistanceUtilities.toValue(entity);
		    	
		    	resultat.add(element);
		    }
		    
		}

	    return resultat;
	}
	
	/******** liste refeglementCache *********/
	@Override
	public List<ReglementAchatValue> listeRefReglementCache() {
		CriteriaBuilder vBuilder = this.entityManager.getCriteriaBuilder();

		CriteriaQuery<ReglementInverseAchatEntity> query = vBuilder
				.createQuery(ReglementInverseAchatEntity.class);
		Root<ReglementInverseAchatEntity> root = query
				.from(ReglementInverseAchatEntity.class);
		
		query.multiselect(root.get("id"), root.get("reference"));
		
		List<ReglementInverseAchatEntity> results = this.entityManager.createQuery(
				query).getResultList();

		/** Conversion de la liste en valeur */
		List<ReglementAchatValue> vListeResultat = new ArrayList<ReglementAchatValue>();
		
		for (ReglementInverseAchatEntity vPartieInteresseeEntite : results) {
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
		CriteriaQuery<ReglementInverseAchatEntity> criteriaQuery = criteriaBuilder.createQuery(ReglementInverseAchatEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<ReglementInverseAchatEntity> root = criteriaQuery.from(ReglementInverseAchatEntity.class);
		
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
	    
	    
	    
		if (estNonVide(request.getDeclarerRech())) {
			Expression<Boolean> expression = root.get(PREDICATE_DECLARE);
			switch (request.getDeclarerRech()) {
				case IConstanteLogistique.YES:
					whereClause.add(criteriaBuilder.isTrue(expression));
					break;
				case IConstanteLogistique.NO:
					whereClause.add(criteriaBuilder.isFalse(expression));
					break;
				case IConstanteLogistique.ALL:
					break;
				default:
					break;
			}
		}
		
		if (estNonVide(request.getHasElementReglement())) {
			switch (request.getHasElementReglement()) {
				case IConstanteLogistique.YES:
					whereClause.add(criteriaBuilder.gt(criteriaBuilder.size(root.<Set>get("listElementReglement")), 0));
					break;
				case IConstanteLogistique.NO:
					whereClause.add(criteriaBuilder.equal(criteriaBuilder.size(root.<Set>get("listElementReglement")), 0));
					break;
				case IConstanteLogistique.ALL:
					break;
				default:
					break;
			}
		}
	    
		
		if (estNonVide(request.getHasDetailReglement())) {
			switch (request.getHasDetailReglement()) {
				case IConstanteLogistique.YES:
					whereClause.add(criteriaBuilder.gt(criteriaBuilder.size(root.<Set>get("listDetailsReglement")), 0));
					break;
				case IConstanteLogistique.NO:
					whereClause.add(criteriaBuilder.equal(criteriaBuilder.size(root.<Set>get("listDetailsReglement")), 0));
					break;
				case IConstanteLogistique.ALL:
					break;
				default:
					break;
			}
		}
		
		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    List <ReglementInverseAchatEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

	    List<ReglementAchatValue> list = new ArrayList<ReglementAchatValue>();
	    
	    for (ReglementInverseAchatEntity entity : resultatEntite) {
	    	ReglementAchatValue element = ReglementInverseAchatPersistanceUtilities.toValue(entity);
	    	
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

	    return ReglementInverseAchatPersistanceUtilities.toValue(entity);
	}

	@Override
	public List<ReglementAchatValue> getByGroupeFournisseurId(Long groupeClientId) {
		
		List<ReglementAchatValue> resultat = new ArrayList<ReglementAchatValue>();
		
	    // Set clientId on whereClause if not null
		if (groupeClientId != null) {
			
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			CriteriaQuery<ReglementInverseAchatEntity> criteriaQuery = criteriaBuilder.createQuery(ReglementInverseAchatEntity.class);
			List<Predicate> whereClause = new ArrayList<Predicate>();
			Root<ReglementInverseAchatEntity> root = criteriaQuery.from(ReglementInverseAchatEntity.class);
			
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_GROUPE_CLIENT), groupeClientId));
			
			criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		    List <ReglementInverseAchatEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
		    
		    for (ReglementInverseAchatEntity entity : resultatEntite) {
		    	ReglementAchatValue element = ReglementInverseAchatPersistanceUtilities.toValue(entity);
		    	
		    	resultat.add(element);
		    }
		    
		}

	    return resultat;
	}
	
}
