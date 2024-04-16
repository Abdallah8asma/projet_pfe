package com.gpro.consulting.tiers.logistique.persistance.gc.reglement.inverse.impl;

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
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ElementReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.RechercheMulticritereReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ResultatRechecheElementReglementElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ResultatRechecheElementReglementValue;
import com.gpro.consulting.tiers.logistique.persistance.gc.reglement.IElementReglementPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.reglement.entity.ReglementEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.reglement.inverse.IElementReglementInversePersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.reglement.inverse.entity.ElementReglementInverseEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.reglement.inverse.utility.ReglementInversePersistanceUtilities;

/**
 * Implementation of {@link IElementReglementPersistance} interface.
 *  
 * @author Wahid Gazzah
 * @since 22/07/2016
 *
 */

@Component
public class ElementReglementInversePersistanceImpl extends AbstractPersistance implements IElementReglementInversePersistance{

	private static final Logger logger = LoggerFactory.getLogger(ElementReglementInversePersistanceImpl.class);
	
	
	private String PREDICATE_REGLEMENT = "reglement";
	
	private String PREDICATE_REFERENCE_FACURE = "refFacture";
	private String PREDICATE_REFERENCE_BL = "refBL";
	
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
	
	@Override
	public List<ElementReglementValue> getByRefernceFacture(String refernceFacture) {

		
		List<ElementReglementValue> resultat = new ArrayList<ElementReglementValue>();
		
	    // Set refernceFacture on whereClause if not null or empty
		if (estNonVide(refernceFacture)) {
			
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			CriteriaQuery<ElementReglementInverseEntity> criteriaQuery = criteriaBuilder.createQuery(ElementReglementInverseEntity.class);
			List<Predicate> whereClause = new ArrayList<Predicate>();
			Root<ElementReglementInverseEntity> root = criteriaQuery.from(ElementReglementInverseEntity.class);
			
		/*	Expression<String> path = root.get(PREDICATE_REFERENCE_FACURE);
			Expression<String> upper =criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper, PERCENT + refernceFacture.toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));
			
			*/
			
			criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		    List <ElementReglementInverseEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
		    
		    for (ElementReglementInverseEntity entity : resultatEntite) {
		    	ElementReglementValue element = ReglementInversePersistanceUtilities.toValue(entity);
		    	
		    	resultat.add(element);
		    }
		    
		}

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
	public ResultatRechecheElementReglementValue rechercherMultiCritere(RechercheMulticritereReglementValue request) {
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<ElementReglementInverseEntity> criteriaQuery = criteriaBuilder.createQuery(ElementReglementInverseEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<ElementReglementInverseEntity> root = criteriaQuery.from(ElementReglementInverseEntity.class);
		
		
		
		// reference facture 
		
	    // Set request.reference on whereClause if not null
			if (estNonVide(request.getRefFacture())) {
				Expression<String> path = root.get(PREDICATE_REFERENCE_FACURE);
				Expression<String> upper =criteriaBuilder.upper(path);
				Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getRefFacture().toUpperCase() + PERCENT);
				whereClause.add(criteriaBuilder.and(predicate));
			}
		
		//reference bl
		
			if (estNonVide(request.getRefBL())) {
				Expression<String> path = root.get(PREDICATE_REFERENCE_BL);
				Expression<String> upper =criteriaBuilder.upper(path);
				Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getRefBL().toUpperCase() + PERCENT);
				whereClause.add(criteriaBuilder.and(predicate));
			}
		
			
		//reference reglement
		
			if (estNonVide(request.getReference())) {
				
				Join<ElementReglementInverseEntity,ReglementEntity> jointure_elementReglement_reglement = root.join(PREDICATE_REGLEMENT);
				
				Expression<String> path = jointure_elementReglement_reglement.get(PREDICATE_REFERENCE);
				Expression<String> upper =criteriaBuilder.upper(path);
				Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getReference().toUpperCase() + PERCENT);
				whereClause.add(criteriaBuilder.and(predicate));
			}
			
			
			// Set request.dateReglementMin on whereClause if not null
		    if (request.getDateReglementMin() != null) {
		    	Join<ElementReglementInverseEntity,ReglementEntity> jointure_elementReglement_reglement = root.join(PREDICATE_REGLEMENT);
				
		    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(jointure_elementReglement_reglement.<Calendar>get(PREDICATE_DATE_REGLEMENT), request.getDateReglementMin()));
		    }
		    
		    // Set request.dateReglementMax on whereClause if not null
		    if (request.getDateReglementMax() != null) {
		    	Join<ElementReglementInverseEntity,ReglementEntity> jointure_elementReglement_reglement = root.join(PREDICATE_REGLEMENT);
				
		    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(jointure_elementReglement_reglement.<Calendar>get(PREDICATE_DATE_REGLEMENT), request.getDateReglementMax()));
		    }
		    
		    
		
	    // Set request.partieIntId on whereClause if not null
		if (request.getPartieIntId()!= null) {
		   	Join<ElementReglementInverseEntity,ReglementEntity> jointure_elementReglement_reglement = root.join(PREDICATE_REGLEMENT);
			
			whereClause.add(criteriaBuilder.equal(jointure_elementReglement_reglement.get(PREDICATE_PARTIEINT), request.getPartieIntId()));
		}
		
	    // Set request.Groupe client on whereClause if not null
			if (request.getGroupeClientId()!= null) {
				
			 	Join<ElementReglementInverseEntity,ReglementEntity> jointure_elementReglement_reglement = root.join(PREDICATE_REGLEMENT);
				
				whereClause.add(criteriaBuilder.equal(jointure_elementReglement_reglement.get(PREDICATE_GROUPE_CLIENT), request.getGroupeClientId()));
			}
		
		 // magazin
		if (request.getIdDepot() != null) {
			Join<ElementReglementInverseEntity,ReglementEntity> jointure_elementReglement_reglement = root.join(PREDICATE_REGLEMENT);
			
			whereClause.add(criteriaBuilder.equal(jointure_elementReglement_reglement.get(PREDICATE_IDDEPOT), request.getIdDepot()));
		}
		
	
		
	
		
		// Set request.montantMin on whereClause if not null
	    if (request.getMontantMin() != null) {
	    	Join<ElementReglementInverseEntity,ReglementEntity> jointure_elementReglement_reglement = root.join(PREDICATE_REGLEMENT);
			
	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(jointure_elementReglement_reglement.<Double>get(PREDICATE_MONTANT), request.getMontantMin()));
	    }
	    
		// Set request.montantMax on whereClause if not null
	    if (request.getMontantMax() != null) {
	    	Join<ElementReglementInverseEntity,ReglementEntity> jointure_elementReglement_reglement = root.join(PREDICATE_REGLEMENT);
    	
	    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(jointure_elementReglement_reglement.<Double>get(PREDICATE_MONTANT), request.getMontantMax()));
	    }
		
		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    List <ElementReglementInverseEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

	    List<ResultatRechecheElementReglementElementValue> list = new ArrayList<ResultatRechecheElementReglementElementValue>();
	    
	    for (ElementReglementInverseEntity entity : resultatEntite) {
	    	ResultatRechecheElementReglementElementValue element = ReglementInversePersistanceUtilities.toValueElementReglementAffichage(entity);
	    	
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

	    ResultatRechecheElementReglementValue resultat = new ResultatRechecheElementReglementValue();
	    Collections.sort(list);
	    resultat.setNombreResultaRechercher(Long.valueOf(list.size()));
	    resultat.setList(new TreeSet<>(list));

	    return resultat;
	}







	@Override
	public List<Long> getReglementIdDistinct(RechercheMulticritereReglementValue request) {

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<ElementReglementInverseEntity> criteriaQuery = criteriaBuilder.createQuery(ElementReglementInverseEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<ElementReglementInverseEntity> root = criteriaQuery.from(ElementReglementInverseEntity.class);
		
		
		
		// reference facture 
		
	    // Set request.reference on whereClause if not null
			if (estNonVide(request.getRefFacture())) {
				Expression<String> path = root.get(PREDICATE_REFERENCE_FACURE);
				Expression<String> upper =criteriaBuilder.upper(path);
				Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getRefFacture().toUpperCase() + PERCENT);
				whereClause.add(criteriaBuilder.and(predicate));
			}
		
		//reference bl
		
			if (estNonVide(request.getRefBL())) {
				Expression<String> path = root.get(PREDICATE_REFERENCE_BL);
				Expression<String> upper =criteriaBuilder.upper(path);
				Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getRefBL().toUpperCase() + PERCENT);
				whereClause.add(criteriaBuilder.and(predicate));
			}
		
			
		//reference reglement
		
			if (estNonVide(request.getReference())) {
				
				Join<ElementReglementInverseEntity,ReglementEntity> jointure_elementReglement_reglement = root.join(PREDICATE_REGLEMENT);
				
				Expression<String> path = jointure_elementReglement_reglement.get(PREDICATE_REFERENCE);
				Expression<String> upper =criteriaBuilder.upper(path);
				Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getReference().toUpperCase() + PERCENT);
				whereClause.add(criteriaBuilder.and(predicate));
			}
			
			
			// Set request.dateReglementMin on whereClause if not null
		    if (request.getDateReglementMin() != null) {
		    	Join<ElementReglementInverseEntity,ReglementEntity> jointure_elementReglement_reglement = root.join(PREDICATE_REGLEMENT);
				
		    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(jointure_elementReglement_reglement.<Calendar>get(PREDICATE_DATE_REGLEMENT), request.getDateReglementMin()));
		    }
		    
		    // Set request.dateReglementMax on whereClause if not null
		    if (request.getDateReglementMax() != null) {
		    	Join<ElementReglementInverseEntity,ReglementEntity> jointure_elementReglement_reglement = root.join(PREDICATE_REGLEMENT);
				
		    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(jointure_elementReglement_reglement.<Calendar>get(PREDICATE_DATE_REGLEMENT), request.getDateReglementMax()));
		    }
		    
		    
		
	    // Set request.partieIntId on whereClause if not null
		if (request.getPartieIntId()!= null) {
		   	Join<ElementReglementInverseEntity,ReglementEntity> jointure_elementReglement_reglement = root.join(PREDICATE_REGLEMENT);
			
			whereClause.add(criteriaBuilder.equal(jointure_elementReglement_reglement.get(PREDICATE_PARTIEINT), request.getPartieIntId()));
		}
		
	    // Set request.Groupe client on whereClause if not null
			if (request.getGroupeClientId()!= null) {
				
			 	Join<ElementReglementInverseEntity,ReglementEntity> jointure_elementReglement_reglement = root.join(PREDICATE_REGLEMENT);
				
				whereClause.add(criteriaBuilder.equal(jointure_elementReglement_reglement.get(PREDICATE_GROUPE_CLIENT), request.getGroupeClientId()));
			}
		
		 // magazin
		if (request.getIdDepot() != null) {
			Join<ElementReglementInverseEntity,ReglementEntity> jointure_elementReglement_reglement = root.join(PREDICATE_REGLEMENT);
			
			whereClause.add(criteriaBuilder.equal(jointure_elementReglement_reglement.get(PREDICATE_IDDEPOT), request.getIdDepot()));
		}
		
	
		
	
		
		// Set request.montantMin on whereClause if not null
	    if (request.getMontantMin() != null) {
	    	Join<ElementReglementInverseEntity,ReglementEntity> jointure_elementReglement_reglement = root.join(PREDICATE_REGLEMENT);
			
	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(jointure_elementReglement_reglement.<Double>get(PREDICATE_MONTANT), request.getMontantMin()));
	    }
	    
		// Set request.montantMax on whereClause if not null
	    if (request.getMontantMax() != null) {
	    	Join<ElementReglementInverseEntity,ReglementEntity> jointure_elementReglement_reglement = root.join(PREDICATE_REGLEMENT);
    	
	    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(jointure_elementReglement_reglement.<Double>get(PREDICATE_MONTANT), request.getMontantMax()));
	    }
		
		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    List <ElementReglementInverseEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

	    List<Long> list = new ArrayList<Long>();
	    
	    for (ElementReglementInverseEntity entity : resultatEntite) {
	    	
	    	
	    	ElementReglementValue element = ReglementInversePersistanceUtilities.toValue(entity);
		    
	    	
	    	if(!list.contains(element.getReglementId()))
	    	      list.add(element.getReglementId());
	    }


	    return list;
	}







	@Override
	public Boolean existElementReglementByReferenceBL(String reference) {

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<ElementReglementInverseEntity> criteriaQuery = criteriaBuilder.createQuery(ElementReglementInverseEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<ElementReglementInverseEntity> root = criteriaQuery.from(ElementReglementInverseEntity.class);
		
		
		
		   // reference facture 
		

			if (estNonVide(reference)) {
				
				whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_REFERENCE_BL), reference));
				
			}
			else
				
			{
				return false;
			}
		
		  
		
		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    List <ElementReglementInverseEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

	   
	    if(resultatEntite.size()> 0) return true;
	    
	    

	    return false;
	}







	@Override
	public Boolean existElementReglementByReferenceFacture(String reference) {
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<ElementReglementInverseEntity> criteriaQuery = criteriaBuilder.createQuery(ElementReglementInverseEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<ElementReglementInverseEntity> root = criteriaQuery.from(ElementReglementInverseEntity.class);
		
		
		
		   // reference facture 
		

			if (estNonVide(reference)) {
				
				whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_REFERENCE_FACURE), reference));
				
			}
			else
				
			{
				return false;
			}
		
		  
		
		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    List <ElementReglementInverseEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

	   
	    if(resultatEntite.size()> 0) return true;
	    
	    

	    return false;
	}







	@Override
	public void deleteElementReglementById(Long id) {
		this.supprimerEntite(ElementReglementInverseEntity.class, id);
		
	}







	@Override
	public List<ElementReglementValue> getByRefBLExact(String refernceBL) {
		List<ElementReglementValue> resultat = new ArrayList<ElementReglementValue>();
		
	    // Set refernceFacture on whereClause if not null or empty
		if (estNonVide(refernceBL)) {
			
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			CriteriaQuery<ElementReglementInverseEntity> criteriaQuery = criteriaBuilder.createQuery(ElementReglementInverseEntity.class);
			List<Predicate> whereClause = new ArrayList<Predicate>();
			Root<ElementReglementInverseEntity> root = criteriaQuery.from(ElementReglementInverseEntity.class);
			
			
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_REFERENCE_BL), refernceBL));
			
			criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		    List <ElementReglementInverseEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
		    
		    for (ElementReglementInverseEntity entity : resultatEntite) {
		    	ElementReglementValue element = ReglementInversePersistanceUtilities.toValue(entity);
		    	
		    	resultat.add(element);
		    }
		    
		}

	    return resultat;
	}







	@Override
	public List<ElementReglementValue> getByRefFactureExact(String refernceFacture) {
		
		List<ElementReglementValue> resultat = new ArrayList<ElementReglementValue>();
		
	    // Set refernceFacture on whereClause if not null or empty
		if (estNonVide(refernceFacture)) {
			
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			CriteriaQuery<ElementReglementInverseEntity> criteriaQuery = criteriaBuilder.createQuery(ElementReglementInverseEntity.class);
			List<Predicate> whereClause = new ArrayList<Predicate>();
			Root<ElementReglementInverseEntity> root = criteriaQuery.from(ElementReglementInverseEntity.class);
			
			
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_REFERENCE_FACURE), refernceFacture));
			
			criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		    List <ElementReglementInverseEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
		    
		    for (ElementReglementInverseEntity entity : resultatEntite) {
		    	ElementReglementValue element = ReglementInversePersistanceUtilities.toValue(entity);
		    	
		    	resultat.add(element);
		    }
		    
		}

	    return resultat;
	}






	@Override
	public Double getSumMontantPayerByReferenceFacture(String refernceFacture) {
		List<ElementReglementValue> resultat = new ArrayList<ElementReglementValue>();

		// Set refernceFacture on whereClause if not null or empty
		if (estNonVide(refernceFacture)) {

			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();

			CriteriaQuery<Double> criteriaQuery = criteriaBuilder.createQuery(Double.class);

			List<Predicate> whereClause = new ArrayList<Predicate>();
			Root<ElementReglementInverseEntity> root = criteriaQuery.from(ElementReglementInverseEntity.class);

			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_REFERENCE_FACURE), refernceFacture));

			criteriaQuery.select(criteriaBuilder.sum(root.get("montantDemande").as(Double.class)))
					.where(whereClause.toArray(new Predicate[] {}));
			Double sommeMont = this.entityManager.createQuery(criteriaQuery).getSingleResult();

			if (sommeMont != null) {
				return sommeMont;
			}
			return 0D;

		}

		return 0D;
	}


	@Override
	public Double getSumMontantPayerByReferenceBL(String reference) {

		// Set refernceFacture on whereClause if not null or empty
		if (estNonVide(reference)) {

			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();

			CriteriaQuery<Double> criteriaQuery = criteriaBuilder.createQuery(Double.class);

			List<Predicate> whereClause = new ArrayList<Predicate>();
			Root<ElementReglementInverseEntity> root = criteriaQuery.from(ElementReglementInverseEntity.class);

			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_REFERENCE_BL), reference));

			criteriaQuery.select(criteriaBuilder.sum(root.get("montantDemande").as(Double.class)))
					.where(whereClause.toArray(new Predicate[] {}));
			Double sommeMont = this.entityManager.createQuery(criteriaQuery).getSingleResult();

			if (sommeMont != null) {
				return sommeMont;
			}
			return 0D;

		}

		return 0D;
	}
}
