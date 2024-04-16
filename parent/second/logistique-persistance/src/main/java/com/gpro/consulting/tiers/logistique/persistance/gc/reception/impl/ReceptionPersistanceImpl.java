package com.gpro.consulting.tiers.logistique.persistance.gc.reception.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

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
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.logistique.coordination.gc.reception.value.ReceptionVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reception.value.RechercheMulticritereBonReceptionValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reception.value.ResultatRechecheBonReceptionValue;
import com.gpro.consulting.tiers.logistique.persistance.gc.reception.IReceptionPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.reception.entitie.ProduitReceptionEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.reception.entitie.ReceptionVenteEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.reception.utilities.ReceptionPersistanceUtilities;

/**
* @author Hajer AMRI
 * @since 19/03/2017 *
 */

@Component
public class ReceptionPersistanceImpl extends AbstractPersistance implements IReceptionPersistance{
	
	private static final Logger logger = LoggerFactory.getLogger(ReceptionPersistanceImpl.class);
	
	private String PERCENT = "%";
	private String PREDICATE_REFERENCE="reference" ;
	private String PREDICATE_REFEXTERNE="refexterne" ;
	private String PREDICATE_CLIENT="partieIntersseId";
	private String PREDICATE_PRODUIT_RECEPTIONS="listProduitReceptions";
	private String PREDICATE_DATE_INTRODUCTION="dateIntroduction";
	private String PREDICATE_DATE_LIVRAISON="dateLivraison";
	private String PREDICATE_PRODUIT="produit";
	private String PREDICATE_ID="id";
	private String PREDICATE_QUANTITE = "quantite";
	private String PREDICATE_COUT = "prixTotal";
	private String PREDICATE_IDDEPOT="idDepot";
	

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public String create(ReceptionVenteValue bonReceptionValue) {

		//logger.info("----- bonReception create : persistance layer ----------") ;
		
		ReceptionVenteEntity receptionVenteEntity = ReceptionPersistanceUtilities.toEntity(bonReceptionValue);
		ReceptionVenteEntity entity = (ReceptionVenteEntity) this.creer(receptionVenteEntity);

	    return entity.getId().toString();
	}

	@Override
	public ReceptionVenteValue getById(Long id) {
		ReceptionVenteEntity receptionVenteEntity = this.rechercherParId(id, ReceptionVenteEntity.class);
	    return ReceptionPersistanceUtilities.toValue(receptionVenteEntity);
	}

	@Override
	public String update(ReceptionVenteValue bonReceptionValue) {

		ReceptionVenteEntity entity = (ReceptionVenteEntity) this.modifier(ReceptionPersistanceUtilities.toEntity(bonReceptionValue));

	    return entity.getId().toString();
	}

	@Override
	public void delete(Long id) {

		this.supprimerEntite(ReceptionVenteEntity.class, id);
	}

	@Override
	public List<ReceptionVenteValue> getAll() {
		
		List<ReceptionVenteEntity> listEntity = this.lister(ReceptionVenteEntity.class);
		
		List<ReceptionVenteValue> finalList = new ArrayList<ReceptionVenteValue>();
		for (ReceptionVenteEntity receptionVenteEntity : listEntity) {
			finalList.add(ReceptionPersistanceUtilities.toValue(receptionVenteEntity));
		}
		
		return finalList;
	}

	
	@Override
	public ResultatRechecheBonReceptionValue rechercherMultiCritere(RechercheMulticritereBonReceptionValue request) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<ReceptionVenteEntity> criteriaQuery = criteriaBuilder.createQuery(ReceptionVenteEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();

		Root<ReceptionVenteEntity> root = criteriaQuery.from(ReceptionVenteEntity.class);
		
		/* Reference */
		if (estNonVide(request.getReference())) {
			Expression<String> path = root.get(PREDICATE_REFERENCE);
			Expression<String> upper =criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getReference().toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));
		}
		
		/* Reference Externe*/
		if (estNonVide(request.getRefexterne())) {
			Expression<String> path = root.get(PREDICATE_REFEXTERNE);
			Expression<String> upper =criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getRefexterne().toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));
		}
		
		/* Client (PartieInteressee) */
		if (estNonVide(request.getPartieInteresseId())) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CLIENT), request.getPartieInteresseId()));
		}
		
		/* magazin  */
		if (request.getIdDepot()!=null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_IDDEPOT), request.getIdDepot()));
		}
		
		
		/* Désignation Produit */
		 if ((request.getIdProduitParDesignation()) != null) {
			 Join<ReceptionVenteEntity, ProduitReceptionEntity> jointureCmdEnPrdCmd = root.join(PREDICATE_PRODUIT_RECEPTIONS);
			 whereClause.add(criteriaBuilder.equal(jointureCmdEnPrdCmd.get(PREDICATE_PRODUIT).get(PREDICATE_ID), request.getIdProduitParDesignation()));
	    }
		 
		 /* Réf Produit */
		 if ((request.getIdProduitParRef()) != null) {
			 Join<ReceptionVenteEntity, ProduitReceptionEntity> jointureCmdEnPrdCmd = root.join(PREDICATE_PRODUIT_RECEPTIONS);
			 whereClause.add(criteriaBuilder.equal(jointureCmdEnPrdCmd.get(PREDICATE_PRODUIT).get(PREDICATE_ID), request.getIdProduitParRef()));
	    }
		
		/* Data Introduction */
	    if(request.getDateIntroductionDu()!=null) {
	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(
	    			root.<Calendar>get(PREDICATE_DATE_INTRODUCTION), request.getDateIntroductionDu()));
	    }

		if (request.getDateIntroductionA() != null) {		
			whereClause.add(criteriaBuilder.lessThanOrEqualTo(
					root.<Calendar>get(PREDICATE_DATE_INTRODUCTION), request.getDateIntroductionA()));
		}
		
		/* Data Livraison */
	    if(request.getDateLivraisonDu()!=null){
	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(
	    			root.<Calendar>get(PREDICATE_DATE_LIVRAISON), request.getDateLivraisonDu()));
	    	
	    }

	    if (request.getDateLivraisonA() != null) {
	    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(
					root.<Calendar>get(PREDICATE_DATE_LIVRAISON), request.getDateLivraisonA()));
		}
	    
		/* quantite */
	    if(request.getQuantiteDu()!=null) {
	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(
	    			root.<Double>get(PREDICATE_QUANTITE), request.getQuantiteDu()));
	    }

		if (request.getQuantiteA() != null) {		
			whereClause.add(criteriaBuilder.lessThanOrEqualTo(
					root.<Double>get(PREDICATE_QUANTITE), request.getQuantiteA()));
		}
		
		/* Cout */
	    if(request.getCoutDu()!=null) {
	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(
	    			root.<Double>get(PREDICATE_COUT), request.getCoutDu()));
	    }

		if (request.getCoutA() != null) {		
			whereClause.add(criteriaBuilder.lessThanOrEqualTo(
					root.<Double>get(PREDICATE_COUT), request.getCoutA()));
		}

	    criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    List <ReceptionVenteEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

	    List<ReceptionVenteValue> listReceptionVenteValue = new ArrayList <ReceptionVenteValue>();
	    
	    for (ReceptionVenteEntity entity : resultatEntite) {
	    	
	    	ReceptionVenteValue dto = ReceptionPersistanceUtilities.toValue(entity);
	    	Collections.sort(dto.getListProduitReceptions());
	    	listReceptionVenteValue.add(dto);
	    }
	    

	    Collections.sort(listReceptionVenteValue);
	    
	    ResultatRechecheBonReceptionValue resultat = new ResultatRechecheBonReceptionValue();

	    resultat.setNombreResultaRechercher(Long.valueOf(listReceptionVenteValue.size()));

	    resultat.setListReceptionVente(listReceptionVenteValue);

	    return resultat;
	}
	
	
	private boolean estNonVide(String val) {
		return val != null && !"".equals(val);

	}

	
	
	/***************************** Getter & Setter ********************************/
	/**
	 * @return the entityManager
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * @param entityManager
	 *            the entityManager to set
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}


}
