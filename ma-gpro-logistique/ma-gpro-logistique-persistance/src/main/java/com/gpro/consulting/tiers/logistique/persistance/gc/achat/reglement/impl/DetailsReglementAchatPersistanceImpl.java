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
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.GroupeClientValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.IGroupeClientPersistance;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.IPartieInteresseePersistance;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.DetailsReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.RechercheMulticritereReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.ResultatRechecheElementReglementAchatElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.ResultatRechecheElementReglementAchatValue;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reglement.IDetailsReglementAchatPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reglement.entity.DetailsReglementAchatEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reglement.entity.ReglementAchatEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reglement.utility.ReglementAchatPersistanceUtilities;
import com.gpro.consulting.tiers.logistique.persistance.gc.reglement.IElementReglementPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.reglement.entity.DetailsReglementEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.reglement.utility.ReglementPersistanceUtilities;

/**
 * Implementation of {@link IElementReglementPersistance} interface.
 *  
 * @author Wahid Gazzah
 * @since 22/07/2016
 *
 */

@Component
public class DetailsReglementAchatPersistanceImpl extends AbstractPersistance implements IDetailsReglementAchatPersistance{

	private static final Logger logger = LoggerFactory.getLogger(DetailsReglementAchatPersistanceImpl.class);
	
	private String PREDICATE_reference_det_reg = "reference";
	
	private String PREDICATE_NUM_PIECE = "numPiece";
	private String PREDICATE_BANQUE = "banque";
	
	private String PREDICATE_REGLEMENT = "reglement";
	
	private String PREDICATE_REFERENCE_FACURE = "refFacture";

	
	private String PREDICATE_DATE_REGLEMENT = "date";
	private String PREDICATE_PARTIEINT = "partieIntId";
	private String PREDICATE_GROUPE_CLIENT = "groupeClientId";
	private String PREDICATE_REFERENCE = "reference";
	private String PREDICATE_MONTANT = "montantTotal";
	private String PREDICATE_IDDEPOT = "idDepot";
	
	private String PERCENT = "%";
	
	  @Autowired
	  private IPartieInteresseePersistance partieInteresseePersistance ;
	
	  
	  @Autowired
	  private IGroupeClientPersistance groupeClientPersistance ;
	
	@PersistenceContext
	private EntityManager entityManager;
	

	
	
	
	
	
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
	public ResultatRechecheElementReglementAchatValue rechercherMultiCritere(RechercheMulticritereReglementAchatValue request) {
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<DetailsReglementAchatEntity> criteriaQuery = criteriaBuilder.createQuery(DetailsReglementAchatEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<DetailsReglementAchatEntity> root = criteriaQuery.from(DetailsReglementAchatEntity.class);
		
		
		
		
		//reference detail reglement

		if (estNonVide(request.getReferenceDetailReglement())) {
			
			
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_reference_det_reg), request.getReferenceDetailReglement()));
		}
		
		
		//numPiece

		if (estNonVide(request.getNumPiece())) {
			
			
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_NUM_PIECE), request.getNumPiece()));
		}
		
		
		//banque

		if (estNonVide(request.getBanque())) {
			
			
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_BANQUE), request.getBanque()));
		}
		
		
		// reference facture 
		
	    // Set request.reference on whereClause if not null
			if (estNonVide(request.getRefFacture())) {
				Expression<String> path = root.get(PREDICATE_REFERENCE_FACURE);
				Expression<String> upper =criteriaBuilder.upper(path);
				Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getRefFacture().toUpperCase() + PERCENT);
				whereClause.add(criteriaBuilder.and(predicate));
			}
		
	
		
			
		//reference reglement
		
			if (estNonVide(request.getReference())) {
				
				Join<DetailsReglementAchatEntity,ReglementAchatEntity> jointure_detailsReglement_reglement = root.join(PREDICATE_REGLEMENT);
				
				Expression<String> path = jointure_detailsReglement_reglement.get(PREDICATE_REFERENCE);
				Expression<String> upper =criteriaBuilder.upper(path);
				Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getReference().toUpperCase() + PERCENT);
				whereClause.add(criteriaBuilder.and(predicate));
			}
			
			
			// Set request.dateReglementMin on whereClause if not null
		    if (request.getDateReglementMin() != null) {
		    	Join<DetailsReglementAchatEntity,ReglementAchatEntity> jointure_detailsReglement_reglement = root.join(PREDICATE_REGLEMENT);
					
		    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(jointure_detailsReglement_reglement.<Calendar>get(PREDICATE_DATE_REGLEMENT), request.getDateReglementMin()));
		    }
		    
		    // Set request.dateReglementMax on whereClause if not null
		    if (request.getDateReglementMax() != null) {
		    	Join<DetailsReglementAchatEntity,ReglementAchatEntity> jointure_detailsReglement_reglement = root.join(PREDICATE_REGLEMENT);
					
		    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(jointure_detailsReglement_reglement.<Calendar>get(PREDICATE_DATE_REGLEMENT), request.getDateReglementMax()));
		    }
		    
		    
		
	    // Set request.partieIntId on whereClause if not null
		if (request.getPartieIntId()!= null) {
			Join<DetailsReglementAchatEntity,ReglementAchatEntity> jointure_detailsReglement_reglement = root.join(PREDICATE_REGLEMENT);
			
			whereClause.add(criteriaBuilder.equal(jointure_detailsReglement_reglement.get(PREDICATE_PARTIEINT), request.getPartieIntId()));
		}
		
	    // Set request.Groupe client on whereClause if not null
			if (request.getGroupeClientId()!= null) {
				
				Join<DetailsReglementAchatEntity,ReglementAchatEntity> jointure_detailsReglement_reglement = root.join(PREDICATE_REGLEMENT);
				
				whereClause.add(criteriaBuilder.equal(jointure_detailsReglement_reglement.get(PREDICATE_GROUPE_CLIENT), request.getGroupeClientId()));
			}
		
		 // magazin
		if (request.getIdDepot() != null) {
			Join<DetailsReglementAchatEntity,ReglementAchatEntity> jointure_detailsReglement_reglement = root.join(PREDICATE_REGLEMENT);
				
			whereClause.add(criteriaBuilder.equal(jointure_detailsReglement_reglement.get(PREDICATE_IDDEPOT), request.getIdDepot()));
		}
		
	
		
	
		
		// Set request.montantMin on whereClause if not null
	    if (request.getMontantMin() != null) {
	    	Join<DetailsReglementAchatEntity,ReglementAchatEntity> jointure_detailsReglement_reglement = root.join(PREDICATE_REGLEMENT);
				
	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(jointure_detailsReglement_reglement.<Double>get(PREDICATE_MONTANT), request.getMontantMin()));
	    }
	    
		// Set request.montantMax on whereClause if not null
	    if (request.getMontantMax() != null) {
	    	Join<DetailsReglementAchatEntity,ReglementAchatEntity> jointure_detailsReglement_reglement = root.join(PREDICATE_REGLEMENT);
			
	    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(jointure_detailsReglement_reglement.<Double>get(PREDICATE_MONTANT), request.getMontantMax()));
	    }
		
		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    List <DetailsReglementAchatEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

	    List<ResultatRechecheElementReglementAchatElementValue> list = new ArrayList<ResultatRechecheElementReglementAchatElementValue>();
	    
	    for (DetailsReglementAchatEntity entity : resultatEntite) {
	    	ResultatRechecheElementReglementAchatElementValue element = ReglementAchatPersistanceUtilities.toValueDetailsReglementAffichage(entity);
	    	
	    	if(element.getPartieIntId() != null) {
	    		
	    		PartieInteresseValue pi = partieInteresseePersistance.getById(element.getPartieIntId());
	    		element.setPartieIntAbreviation(pi.getAbreviation());
	    		if(pi.getGroupeClientId() != null) {
	    		
	    			element.setGroupeClientDesignation(groupeClientPersistance.rechercheGroupeClientParId(new GroupeClientValue(pi.getGroupeClientId())).getDesignation());
	    			
	    		}
	    	}else if(element.getGroupeClientId() != null) {
	    		
	    		
	    		element.setGroupeClientDesignation(groupeClientPersistance.rechercheGroupeClientParId(new GroupeClientValue(element.getGroupeClientId())).getDesignation());
	    	}
	    		
	    	
	    	
	    	
	    
	    	
	    	
	    	list.add(element);
	    }

	    ResultatRechecheElementReglementAchatValue resultat = new ResultatRechecheElementReglementAchatValue();
	    Collections.sort(list);
	    resultat.setNombreResultaRechercher(Long.valueOf(list.size()));
	    resultat.setList(new TreeSet<>(list));

	    return resultat;
	}

	@Override
	public String update(DetailsReglementAchatValue detailsReglementValue) {
		DetailsReglementAchatEntity entity = (DetailsReglementAchatEntity) this.modifier(ReglementAchatPersistanceUtilities.toEntity(detailsReglementValue));

	    return entity.getId().toString();
	}
}
