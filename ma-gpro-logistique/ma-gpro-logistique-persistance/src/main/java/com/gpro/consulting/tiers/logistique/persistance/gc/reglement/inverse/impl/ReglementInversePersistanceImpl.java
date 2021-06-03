package com.gpro.consulting.tiers.logistique.persistance.gc.reglement.inverse.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.IConstanteLogistique;
import com.gpro.consulting.tiers.logistique.coordination.gc.chart.value.ResultBestElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.RechercheMulticritereReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ResultatRechecheReglementCompletValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ResultatRechecheReglementElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ResultatRechecheReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.TypeReglementValue;
import com.gpro.consulting.tiers.logistique.persistance.gc.reglement.IReglementPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.reglement.entity.TypeReglementEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.reglement.inverse.IReglementInversePersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.reglement.inverse.entity.ReglementInverseEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.reglement.inverse.utility.ReglementInversePersistanceUtilities;

/**
 * Implementation of {@link IReglementPersistance} interface.
 *  
 * @author Wahid Gazzah
 * @since 01/07/2016
 *
 */

@Component
public class ReglementInversePersistanceImpl extends AbstractPersistance implements IReglementInversePersistance{

	private static final Logger logger = LoggerFactory.getLogger(ReglementInversePersistanceImpl.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private String PREDICATE_DATE_REGLEMENT = "date";
	private String PREDICATE_PARTIEINT = "partieIntId";
	private String PREDICATE_GROUPE_CLIENT = "groupeClientId";
	private String PREDICATE_REFERENCE = "reference";
	private String PREDICATE_MONTANT = "montantTotal";
	private String PREDICATE_IDDEPOT = "idDepot";
	private String PREDICATE_BOUTIQUEID = "boutiqueId";

	private String PREDICATE_DECLARE = "declarer";
	private String PERCENT = "%";
	
	@Override
	public String create(ReglementValue reglement) {

		ReglementInverseEntity entity = (ReglementInverseEntity) this.modifier(ReglementInversePersistanceUtilities.toEntity(reglement));

	    return entity.getId().toString();
	}

	@Override
	public ReglementValue getById(Long id) {

		ReglementInverseEntity entity = this.rechercherParId(id, ReglementInverseEntity.class);
		
		
		if(entity == null) return null;

	    return ReglementInversePersistanceUtilities.toValue(entity);
	}

	@Override
	public String update(ReglementValue reglement) {

		ReglementInverseEntity entity = (ReglementInverseEntity) this.modifier(ReglementInversePersistanceUtilities.toEntity(reglement));

	    return entity.getId().toString();
	}

	@Override
	public void delete(Long id) {
		
		this.supprimerEntite(ReglementInverseEntity.class, id);
	}

	@Override
	public List<ReglementValue> getAll() {
		
		List<ReglementInverseEntity> listEntity = this.lister(ReglementInverseEntity.class);
		
		return ReglementInversePersistanceUtilities.listToValue(listEntity);
	}

	@Override
	public ResultatRechecheReglementValue rechercherMultiCritere(
			RechercheMulticritereReglementValue request) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<ReglementInverseEntity> criteriaQuery = criteriaBuilder.createQuery(ReglementInverseEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<ReglementInverseEntity> root = criteriaQuery.from(ReglementInverseEntity.class);
		
		if(estNonVide(request.getBoutiqueId())) {
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
	    List <ReglementInverseEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

	    List<ResultatRechecheReglementElementValue> list = new ArrayList<ResultatRechecheReglementElementValue>();
	    
	    for (ReglementInverseEntity entity : resultatEntite) {
	    	ResultatRechecheReglementElementValue element = ReglementInversePersistanceUtilities.toResultatRechecheReglementElementValue(entity);
	    	
	    	list.add(element);
	    }

	    ResultatRechecheReglementValue resultat = new ResultatRechecheReglementValue();
	    Collections.sort(list);
	    resultat.setNombreResultaRechercher(Long.valueOf(list.size()));
	    resultat.setList(new TreeSet<>(list));

	    return resultat;
	}

	@Override
	public List<ReglementValue> getByClientId(Long clientId) {

		
		List<ReglementValue> resultat = new ArrayList<ReglementValue>();
		
	    // Set clientId on whereClause if not null
		if (clientId != null) {
			
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			CriteriaQuery<ReglementInverseEntity> criteriaQuery = criteriaBuilder.createQuery(ReglementInverseEntity.class);
			List<Predicate> whereClause = new ArrayList<Predicate>();
			Root<ReglementInverseEntity> root = criteriaQuery.from(ReglementInverseEntity.class);
			
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_PARTIEINT), clientId));
			
			criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		    List <ReglementInverseEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
		    
		    for (ReglementInverseEntity entity : resultatEntite) {
		    	ReglementValue element = ReglementInversePersistanceUtilities.toValue(entity);
		    	
		    	resultat.add(element);
		    }
		    
		}

	    return resultat;
	}
	
	/******** liste refeglementCache *********/
	@Override
	public List<ReglementValue> listeRefReglementCache() {
		CriteriaBuilder vBuilder = this.entityManager.getCriteriaBuilder();

		CriteriaQuery<ReglementInverseEntity> query = vBuilder
				.createQuery(ReglementInverseEntity.class);
		Root<ReglementInverseEntity> root = query
				.from(ReglementInverseEntity.class);
		
		query.multiselect(root.get("id"), root.get("reference"));
		
		List<ReglementInverseEntity> results = this.entityManager.createQuery(
				query).getResultList();

		/** Conversion de la liste en valeur */
		List<ReglementValue> vListeResultat = new ArrayList<ReglementValue>();
		
		for (ReglementInverseEntity vPartieInteresseeEntite : results) {
			ReglementValue vPiCache = new ReglementValue();
			vPiCache.setId(vPartieInteresseeEntite.getId());
			vPiCache.setReference(vPartieInteresseeEntite.getReference());
			vListeResultat.add(vPiCache);
		}
		return vListeResultat;
	}
	
	@Override
	public ResultatRechecheReglementCompletValue rechercherMultiCritereComplet(
			RechercheMulticritereReglementValue request) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<ReglementInverseEntity> criteriaQuery = criteriaBuilder.createQuery(ReglementInverseEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<ReglementInverseEntity> root = criteriaQuery.from(ReglementInverseEntity.class);
		
		if(estNonVide(request.getBoutiqueId())) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_BOUTIQUEID), request.getBoutiqueId()));
		}
		
	    // Set request.partieIntId on whereClause if not null
		if (request.getPartieIntId() != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_PARTIEINT), request.getPartieIntId()));
		}
		
		// Set request.Groupe client on whereClause if not null
		if (request.getGroupeClientId()!= null) {
				whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_GROUPE_CLIENT), request.getGroupeClientId()));
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
	    List <ReglementInverseEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

	    List<ReglementValue> list = new ArrayList<ReglementValue>();
	    
	    for (ReglementInverseEntity entity : resultatEntite) {
	    	ReglementValue element = ReglementInversePersistanceUtilities.toValue(entity);
	    	
	    	list.add(element);
	    }

	    ResultatRechecheReglementCompletValue resultat = new ResultatRechecheReglementCompletValue();
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
	public TypeReglementValue getTypeReglementById(Long typeReglementId) {
		
		TypeReglementEntity entity = this.rechercherParId(typeReglementId, TypeReglementEntity.class);

	    return ReglementInversePersistanceUtilities.toValue(entity);
	}

	@Override
	public List<ReglementValue> getByGroupeClientId(Long groupeClientId) {
		
		List<ReglementValue> resultat = new ArrayList<ReglementValue>();
		
	    // Set clientId on whereClause if not null
		if (groupeClientId != null) {
			
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			CriteriaQuery<ReglementInverseEntity> criteriaQuery = criteriaBuilder.createQuery(ReglementInverseEntity.class);
			List<Predicate> whereClause = new ArrayList<Predicate>();
			Root<ReglementInverseEntity> root = criteriaQuery.from(ReglementInverseEntity.class);
			
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_GROUPE_CLIENT), groupeClientId));
			
			criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		    List <ReglementInverseEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
		    
		    for (ReglementInverseEntity entity : resultatEntite) {
		    	ReglementValue element = ReglementInversePersistanceUtilities.toValue(entity);
		    	
		    	resultat.add(element);
		    }
		    
		}

	    return resultat;
	}
	
	
	@Override
	public ResultBestElementValue getReglementByMonth(RechercheMulticritereReglementValue request) {
		
		List<ProduitValue> resultList=new ArrayList<ProduitValue>();
		
		Date dateDeb=new Date(request.getDateReglementMin().getTime().getYear(), request.getDateReglementMin().getTime().getMonth(), request.getDateReglementMin().getTime().getDate());
	
		
		Date dateA=new Date(request.getDateReglementMax().getTime().getYear(), request.getDateReglementMax().getTime().getMonth(), request.getDateReglementMax().getTime().getDate());
		
		
		String where="";
		
		if (estNonVide(request.getBoutiqueId())) {
			where=" and reg.boutiqueId="+request.getBoutiqueId();
		}
		
		 Query vQuery = this.entityManager.createQuery(
			      "select  sum(reg.montantTotal),count(reg.id)"
			      +" from ReglementInverseEntity reg "
			      + "where reg.date>= '"+dateDeb
			      +"' and reg.date<='"+dateA
			      +"'"+where );
		 
		

			    List<Object[]> vResult = vQuery.getResultList();
			    
			   
			    
			    ResultBestElementValue result=new ResultBestElementValue();
			    if(vResult.size()>0)
			    result.setMontant(vResult.get(0)[0]);
				
				result.setMois(request.getDateReglementMin().getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.FRANCE));
		
		return result;
	}
	
	
	 private boolean estNonVide(Long val) {
			return val != null && !"".equals(val) && !"undefined".equals(val) && !"null".equals(val);
		}

	
}
