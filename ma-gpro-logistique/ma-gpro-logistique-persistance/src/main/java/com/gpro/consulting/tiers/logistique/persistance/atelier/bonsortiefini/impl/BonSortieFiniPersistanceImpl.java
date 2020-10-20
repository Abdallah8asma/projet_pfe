package com.gpro.consulting.tiers.logistique.persistance.atelier.bonsortiefini.impl;

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
import javax.persistence.criteria.Subquery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.entity.PartieInteresseEntite;
import com.gpro.consulting.tiers.logistique.coordination.atelier.IConstanteLogistique;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.BonSortieFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.RechercheMulticritereBonSortieFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.ResultatRechecheBonSortieFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.fn.report.reporting.RechercheMulticritereFnReportingtValue;
import com.gpro.consulting.tiers.logistique.persistance.atelier.bonsortiefini.IBonSortieFiniPersistance;
import com.gpro.consulting.tiers.logistique.persistance.atelier.bonsortiefini.entity.BonSortieFiniEntity;
import com.gpro.consulting.tiers.logistique.persistance.atelier.bonsortiefini.utilities.BonSortieFiniPersistanceUtilities;

/**
 * Implementation of {@link IBonSortieFiniPersistance} interface.
 * 
 * @author Wahid Gazzah
 * @since 08/01/2016
 *
 */
@Component
public class BonSortieFiniPersistanceImpl extends AbstractPersistance implements IBonSortieFiniPersistance{
	
	private static final Logger logger = LoggerFactory.getLogger(BonSortieFiniPersistanceImpl.class);
	
	private String PREDICATE_REFBS = "reference";
	private String PREDICATE_TYPE = "type";
	private String PREDICATE_CLIENT = "partieInt";
	private String PREDICATE_REGION = "regionId";
	private String PREDICATE_DATESORTIE = "dateSortie";
	private String PREDICATE_FINI = "fini";
	private String PERCENT = "%"; 
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private BonSortieFiniPersistanceUtilities bonSortieFiniPersistanceUtilities;
	
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public String createBonSortieFini(BonSortieFiniValue bonSortieFiniValue) {
		
		BonSortieFiniEntity entity = (BonSortieFiniEntity) this.modifier(bonSortieFiniPersistanceUtilities.toEntity(bonSortieFiniValue));

	    return entity.getId().toString();
	}

	@Override
	public BonSortieFiniValue getBonSortieFiniById(Long id) {
		
		BonSortieFiniEntity bonSortieFiniEntity = this.rechercherParId(id, BonSortieFiniEntity.class);

	    return bonSortieFiniPersistanceUtilities.toValue(bonSortieFiniEntity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ResultatRechecheBonSortieFiniValue rechercherMultiCritere(
			RechercheMulticritereBonSortieFiniValue request) {
		
		//logger.info("rechercherMultiCritere");
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<BonSortieFiniEntity> criteriaQuery = criteriaBuilder.createQuery(BonSortieFiniEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		
		Root<BonSortieFiniEntity> root = criteriaQuery.from(BonSortieFiniEntity.class);
		
		// Set request.reference on whereClause if not empty or null
		if (estNonVide(request.getReference())) {
			Expression<String> path = root.get(PREDICATE_REFBS);
			Expression<String> upper =criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getReference().toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));
		}
		
		
		// Set request.type on whereClause if not empty or null
		if (estNonVide(request.getType())) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_TYPE), request.getType()));
		}
		
		// Set request.partieInt on whereClause if not null
		if (request.getPartieInt() != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CLIENT), request.getPartieInt()));
		}
		
		// Set request.metrageMin on whereClause if not null
	    if (request.getDateSortieMin() != null) {
	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Calendar>get(PREDICATE_DATESORTIE), request.getDateSortieMin()));
	    }
	    
		// Set request.metrageMax on whereClause if not null
	    if (request.getDateSortieMax() != null) {
	    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Calendar>get(PREDICATE_DATESORTIE), request.getDateSortieMax()));
	    }
	    
		/** Set request.fini on whereClause if not empty or null.
			if fini = oui  --> cherche only RouleauFini that FINI = true.
			if fini = non  --> cherche only RouleauFini that FINI = false.
			if fini = tous --> cherche on all RouleauFini.
		 */
		if (estNonVide(request.getFini())) {
			Expression<Boolean> expression = root.get(PREDICATE_FINI);
			switch (request.getFini()) {
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
		
		if (estNonVide(request.getRempli())) {
			switch (request.getRempli()) {
				case IConstanteLogistique.YES:
					whereClause.add(criteriaBuilder.gt(criteriaBuilder.size(root.<Set>get("listeRouleauFini")), 0));
					break;
				case IConstanteLogistique.NO:
					whereClause.add(criteriaBuilder.equal(criteriaBuilder.size(root.<Set>get("listeRouleauFini")), 0));
					break;
				case IConstanteLogistique.ALL:
					break;
				default:
					break;
			}
		}
	    
		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    List <BonSortieFiniEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
	    
	    List<BonSortieFiniValue> list = new ArrayList<BonSortieFiniValue>();
	    
	    for (BonSortieFiniEntity entity : resultatEntite) {
	    	BonSortieFiniValue dto = bonSortieFiniPersistanceUtilities.toValue(entity);
	    	list.add(dto);
	    }
	    
	    ResultatRechecheBonSortieFiniValue resultat = new ResultatRechecheBonSortieFiniValue();
	    Collections.sort(list);
	    resultat.setNombreResultaRechercher(Long.valueOf(list.size()));
	    resultat.setList(new TreeSet<>(list));
	    
	    return resultat;
		
	}
	
	@Override
	public String updateBonSortieFini(BonSortieFiniValue bonSortieFiniValue) {
		
		BonSortieFiniEntity entity = (BonSortieFiniEntity) this.modifier(bonSortieFiniPersistanceUtilities.toEntity(bonSortieFiniValue));

	    return entity.getId().toString();
	}


	@Override
	public void deleteBonSortieFini(Long id) {
		
		this.supprimerEntite(BonSortieFiniEntity.class, id);
	}
	
	@Override
	public List<BonSortieFiniValue> getListByBonSortieList(List<String> refBonSortieList) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
	    CriteriaQuery<BonSortieFiniEntity> criteriaQuery = criteriaBuilder.createQuery(BonSortieFiniEntity.class);
	    List<Predicate> whereClause = new ArrayList<Predicate>();
	    Root<BonSortieFiniEntity> root = criteriaQuery.from(BonSortieFiniEntity.class);
		
	    whereClause.add(root.get(PREDICATE_REFBS).in(refBonSortieList));
		
	    criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    
	    List<BonSortieFiniEntity> entityListResult = this.entityManager.createQuery(criteriaQuery).getResultList();

	    List<BonSortieFiniValue> listBonSortieFini = new ArrayList<BonSortieFiniValue>();
	    
	    for (BonSortieFiniEntity entity : entityListResult) {
	    	BonSortieFiniValue value = bonSortieFiniPersistanceUtilities.toValue(entity);
	    	listBonSortieFini.add(value);
	    }
	    
	    return listBonSortieFini;
	}
	
	private boolean estNonVide(String val) {
		return val != null && !"".equals(val);
	}


	@Override
	public List<BonSortieFiniValue> getAll() {
		
		List<BonSortieFiniEntity> listEntity = this.lister(BonSortieFiniEntity.class);
		
		return bonSortieFiniPersistanceUtilities.toValue(listEntity);
	}


	@Override
	public ResultatRechecheBonSortieFiniValue getBSByFnReportingRegionDate(
			RechercheMulticritereFnReportingtValue request) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<BonSortieFiniEntity> criteriaQuery = criteriaBuilder.createQuery(BonSortieFiniEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		
		Root<BonSortieFiniEntity> root = criteriaQuery.from(BonSortieFiniEntity.class);
		
		// Set regionId on whereClause if not empty or null
		if (request.getRegionId() != null) {
			Subquery<Long> subQuery = criteriaQuery.subquery(Long.class);
			Root<PartieInteresseEntite> subRoot = subQuery.from(PartieInteresseEntite.class);
			
			subQuery.select(subRoot.<Long>get("id"));
			subQuery.where(criteriaBuilder.equal(subRoot.get(PREDICATE_REGION), request.getRegionId()));
			whereClause.add(criteriaBuilder.in(root.get(PREDICATE_CLIENT)).value(subQuery));
		}
				
		// Set request.dateMin on whereClause if not null
	    if (request.getDateMin() != null) {
	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Calendar>get(PREDICATE_DATESORTIE), request.getDateMin()));
	    }
	    
		// Set request.dateMax on whereClause if not null
	    if (request.getDateMax() != null) {
	    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Calendar>get(PREDICATE_DATESORTIE), request.getDateMax()));
	    }
	    
		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    List <BonSortieFiniEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
	    
	    List<BonSortieFiniValue> list = new ArrayList<BonSortieFiniValue>();
	    
	    for (BonSortieFiniEntity entity : resultatEntite) {
	    	BonSortieFiniValue dto = bonSortieFiniPersistanceUtilities.toValue(entity);
	    	list.add(dto);
	    }
	    
	    ResultatRechecheBonSortieFiniValue resultat = new ResultatRechecheBonSortieFiniValue();
	    Collections.sort(list);
	    resultat.setNombreResultaRechercher(Long.valueOf(list.size()));
	    resultat.setList(new TreeSet<>(list));
	    
	    return resultat;
	}


}
