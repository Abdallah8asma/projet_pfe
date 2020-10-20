package com.gpro.consulting.tiers.logistique.persistance.atelier.boninventairefini.impl;

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
import com.gpro.consulting.tiers.logistique.coordination.atelier.boninventairefini.value.BonInventaireFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.boninventairefini.value.RechercheMulticritereBonInventaireFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.boninventairefini.value.ResultatRechecheBonInventaireFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.BonSortieFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.RechercheMulticritereBonSortieFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.ResultatRechecheBonSortieFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.fn.report.reporting.RechercheMulticritereFnReportingtValue;
import com.gpro.consulting.tiers.logistique.persistance.atelier.boninventairefini.IBonInventaireFiniPersistance;
import com.gpro.consulting.tiers.logistique.persistance.atelier.boninventairefini.entity.BonInventaireFiniEntity;
import com.gpro.consulting.tiers.logistique.persistance.atelier.boninventairefini.utilities.BonInventaireFiniPersistanceUtilities;
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
public class BonInventaireFiniPersistanceImpl extends AbstractPersistance implements IBonInventaireFiniPersistance{
	
	private static final Logger logger = LoggerFactory.getLogger(BonInventaireFiniPersistanceImpl.class);
	
	private String PREDICATE_REFBS = "reference";
	private String PREDICATE_EMPLACEMENT = "emplacement";
	private String PREDICATE_DATESORTIE = "dateEntree";
	private String PERCENT = "%"; 
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private BonInventaireFiniPersistanceUtilities bonInventaireFiniPersistanceUtilities;
	
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public String createBonInventaireFini(BonInventaireFiniValue bonSortieFiniValue) {
		
		BonInventaireFiniEntity entity = (BonInventaireFiniEntity) this.modifier(bonInventaireFiniPersistanceUtilities.toEntity(bonSortieFiniValue));

	    return entity.getId().toString();
	}

	@Override
	public BonInventaireFiniValue getBonInventaireFiniById(Long id) {
		
		BonInventaireFiniEntity bonSortieFiniEntity = this.rechercherParId(id, BonInventaireFiniEntity.class);

	    return bonInventaireFiniPersistanceUtilities.toValue(bonSortieFiniEntity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ResultatRechecheBonInventaireFiniValue rechercherMultiCritere(
			RechercheMulticritereBonInventaireFiniValue request) {
		
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<BonInventaireFiniEntity> criteriaQuery = criteriaBuilder.createQuery(BonInventaireFiniEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		
		Root<BonInventaireFiniEntity> root = criteriaQuery.from(BonInventaireFiniEntity.class);
		
		// Set request.reference on whereClause if not empty or null
		if (estNonVide(request.getReference())) {
			Expression<String> path = root.get(PREDICATE_REFBS);
			Expression<String> upper =criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getReference().toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));
		}
		
		
		// Set request.type on whereClause if not empty or null
		if (estNonVide(request.getEmplacement())) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_EMPLACEMENT), request.getEmplacement()));
		}
		
		
		
		// Set request.metrageMin on whereClause if not null
	    if (request.getDateSortieMin() != null) {
	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Calendar>get(PREDICATE_DATESORTIE), request.getDateSortieMin()));
	    }
	    
		// Set request.metrageMax on whereClause if not null
	    if (request.getDateSortieMax() != null) {
	    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Calendar>get(PREDICATE_DATESORTIE), request.getDateSortieMax()));
	    }
	    
	
	    /** Lancer la requete */
	    criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));

	    
	    List <BonInventaireFiniEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
	    
	    List<BonInventaireFiniValue> list = new ArrayList<BonInventaireFiniValue>();
	    
	    for (BonInventaireFiniEntity entity : resultatEntite) {
	    	BonInventaireFiniValue dto = bonInventaireFiniPersistanceUtilities.toValue(entity);
	    	list.add(dto);
	    }
	    
	    ResultatRechecheBonInventaireFiniValue resultat = new ResultatRechecheBonInventaireFiniValue();
	    Collections.sort(list);
	    resultat.setNombreResultaRechercher(Long.valueOf(list.size()));
	    resultat.setList(new TreeSet<>(list));
	    
	    return resultat;
		
	}
	
	@Override
	public String updateBonInventaireFini(BonInventaireFiniValue bonInventaireFiniValue) {
		
		BonInventaireFiniEntity entity = (BonInventaireFiniEntity) this.modifier(bonInventaireFiniPersistanceUtilities.toEntity(bonInventaireFiniValue));

	    return entity.getId().toString();
	}


	@Override
	public void deleteBonInventaireFini(Long id) {
		
		this.supprimerEntite(BonInventaireFiniEntity.class, id);
	}
	
	
	
	private boolean estNonVide(String val) {
		return val != null && !"".equals(val);
	}


	@Override
	public List<BonInventaireFiniValue> getAll() {
		
		List<BonInventaireFiniEntity> listEntity = this.lister(BonInventaireFiniEntity.class);
		
		return bonInventaireFiniPersistanceUtilities.toValue(listEntity);
	}


	


}
