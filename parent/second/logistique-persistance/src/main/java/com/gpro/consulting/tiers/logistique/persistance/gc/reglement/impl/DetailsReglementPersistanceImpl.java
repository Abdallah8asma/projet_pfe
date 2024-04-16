package com.gpro.consulting.tiers.logistique.persistance.gc.reglement.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.GroupeClientValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.IGroupeClientPersistance;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.IPartieInteresseePersistance;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.entity.PartieInteresseEntite;
import com.gpro.consulting.tiers.logistique.coordination.gc.chart.value.ResultBestElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.echeancier.RechercheMulticritereDetailReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.DetailsReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.RechercheMulticritereReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ResultatRechecheElementReglementElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ResultatRechecheElementReglementValue;
import com.gpro.consulting.tiers.logistique.persistance.gc.reglement.IDetailsReglementPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.reglement.IElementReglementPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.reglement.entity.DetailsReglementEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.reglement.entity.ReglementEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.reglement.entity.TypeReglementEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.reglement.utility.ReglementPersistanceUtilities;

/**
 * Implementation of {@link IElementReglementPersistance} interface.
 *  
 * @author Wahid Gazzah
 * @since 22/07/2016
 *
 */

@Component
public class DetailsReglementPersistanceImpl extends AbstractPersistance implements IDetailsReglementPersistance{

	private static final Logger logger = LoggerFactory.getLogger(DetailsReglementPersistanceImpl.class);
	
	
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
	
	
	
	
	private String JOINTURE_REGLEMENT = "reglement";
	private String COLUMN_DATE_REGLEMENT = "date";

	private String JOINTURE_PARTIE_INT = "partieIntId";
	private String JOINTURE_REGION = "regionId";

	private String COLUMN_PARTIEINT_REGLEMENT = "partieIntId";
	private String COLUMN_REFERENCE_REGLEMENT = "reference";
	private String JOINTURE_TYPE_REGLEMENT = "typeReglement";
	private String COLUMN_TYPE = "id";
	private String COLUMN_NUM_PSC = "numPiece";
	private String COLUMN_REGLE = "regle";

	private String COLUMN_DATE_ECHEANCE = "dateEcheance";
	
	
	private String PERCENT = "%";
	private int MAX_RESULTS =52;
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
	public ResultatRechecheElementReglementValue rechercherMultiCritere(RechercheMulticritereReglementValue request) {
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<DetailsReglementEntity> criteriaQuery = criteriaBuilder.createQuery(DetailsReglementEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<DetailsReglementEntity> root = criteriaQuery.from(DetailsReglementEntity.class);
		
		
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
				
				Join<DetailsReglementEntity,ReglementEntity> jointure_detailsReglement_reglement = root.join(PREDICATE_REGLEMENT);
				
				Expression<String> path = jointure_detailsReglement_reglement.get(PREDICATE_REFERENCE);
				Expression<String> upper =criteriaBuilder.upper(path);
				Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getReference().toUpperCase() + PERCENT);
				whereClause.add(criteriaBuilder.and(predicate));
			}
			
			
			// Set request.dateReglementMin on whereClause if not null
		    if (request.getDateReglementMin() != null) {
		    	Join<DetailsReglementEntity,ReglementEntity> jointure_detailsReglement_reglement = root.join(PREDICATE_REGLEMENT);
					
		    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(jointure_detailsReglement_reglement.<Calendar>get(PREDICATE_DATE_REGLEMENT), request.getDateReglementMin()));
		    }
		    
		    // Set request.dateReglementMax on whereClause if not null
		    if (request.getDateReglementMax() != null) {
		    	Join<DetailsReglementEntity,ReglementEntity> jointure_detailsReglement_reglement = root.join(PREDICATE_REGLEMENT);
					
		    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(jointure_detailsReglement_reglement.<Calendar>get(PREDICATE_DATE_REGLEMENT), request.getDateReglementMax()));
		    }
		    
		    
		
	    // Set request.partieIntId on whereClause if not null
		if (request.getPartieIntId()!= null) {
			Join<DetailsReglementEntity,ReglementEntity> jointure_detailsReglement_reglement = root.join(PREDICATE_REGLEMENT);
			
			whereClause.add(criteriaBuilder.equal(jointure_detailsReglement_reglement.get(PREDICATE_PARTIEINT), request.getPartieIntId()));
		}
		
	    // Set request.Groupe client on whereClause if not null
			if (request.getGroupeClientId()!= null) {
				
				Join<DetailsReglementEntity,ReglementEntity> jointure_detailsReglement_reglement = root.join(PREDICATE_REGLEMENT);
				
				whereClause.add(criteriaBuilder.equal(jointure_detailsReglement_reglement.get(PREDICATE_GROUPE_CLIENT), request.getGroupeClientId()));
			}
		
		 // magazin
		if (request.getIdDepot() != null) {
			Join<DetailsReglementEntity,ReglementEntity> jointure_detailsReglement_reglement = root.join(PREDICATE_REGLEMENT);
				
			whereClause.add(criteriaBuilder.equal(jointure_detailsReglement_reglement.get(PREDICATE_IDDEPOT), request.getIdDepot()));
		}
		
	
		
	
		
		// Set request.montantMin on whereClause if not null
	    if (request.getMontantMin() != null) {
	    	Join<DetailsReglementEntity,ReglementEntity> jointure_detailsReglement_reglement = root.join(PREDICATE_REGLEMENT);
				
	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(jointure_detailsReglement_reglement.<Double>get(PREDICATE_MONTANT), request.getMontantMin()));
	    }
	    
		// Set request.montantMax on whereClause if not null
	    if (request.getMontantMax() != null) {
	    	Join<DetailsReglementEntity,ReglementEntity> jointure_detailsReglement_reglement = root.join(PREDICATE_REGLEMENT);
			
	    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(jointure_detailsReglement_reglement.<Double>get(PREDICATE_MONTANT), request.getMontantMax()));
	    }
		
		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    List <DetailsReglementEntity> resultatEntite= null;
		
		if(request.isOptimized())
		{
			resultatEntite = this.entityManager.createQuery(criteriaQuery).setMaxResults(MAX_RESULTS).getResultList();
		}
		
		else
			
		{
			resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
		}


	    List<ResultatRechecheElementReglementElementValue> list = new ArrayList<ResultatRechecheElementReglementElementValue>();
	    
	    for (DetailsReglementEntity entity : resultatEntite) {
	    	ResultatRechecheElementReglementElementValue element = ReglementPersistanceUtilities.toValueDetailsReglementAffichage(entity);
	    	
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
	public ResultBestElementValue rechercherMultiCritereReglementEchusDuJours(Calendar instance, boolean b,
			Long boutiqueId) {
		Date date=new Date(instance.getTime().getYear(), instance.getTime().getMonth(), instance.getTime().getDate());
	
		
		
		//TODO
		String where="";
		
		if (estNonVide(boutiqueId)) {
			where=" and det.reglement.boutiqueId="+boutiqueId;
		}
		
		
		 
		
		
		

		 Query vQuery = this.entityManager.createQuery(
			      "select count(det.id), sum(det.montant)"
			      +" from DetailsReglementEntity det "
			      + "where det.dateEcheance= '"+date
			      +"' and det.regle is "+b
			      + where
			      );
		 
		

			    List<Object[]> vResult = vQuery.getResultList();
			    
			    ResultBestElementValue result =new ResultBestElementValue();
			  
			    	
			    	
			    result.setNbTotal((Long)vResult.get(0)[0]);	
			    
			    result.setMontant(vResult.get(0)[1]);
			    
			    return result;
	}
			    
			    
			    private boolean estNonVide(Long val) {
					return val != null && !"".equals(val) && !"undefined".equals(val) && !"null".equals(val);
				}

				@Override
				public Double getMontantPayer(RechercheMulticritereDetailReglementValue request) {
					CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
					CriteriaQuery<Double> criteriaQuery = criteriaBuilder.createQuery(Double.class);
					List<Predicate> whereClause = new ArrayList<Predicate>();
					Root<DetailsReglementEntity> root = criteriaQuery.from(DetailsReglementEntity.class);
					
					
					// Set request.partieIntId on whereClause if not null
					if (request.getReglementId() != null) {
						Join<DetailsReglementEntity, ReglementEntity> jointureDtReg = root.join(JOINTURE_REGLEMENT);
						whereClause.add(
								criteriaBuilder.equal(jointureDtReg.get("id"), request.getReglementId()));
					}

					// Set request.regle on whereClause if not null
					if (request.getRegle() != null) {
						whereClause.add(criteriaBuilder.equal(root.get(COLUMN_REGLE), request.getRegle()));
					}

					// Set request.type on whereClause if not null
					if (request.getTypeReglementId() != null) {
						Join<DetailsReglementEntity, TypeReglementEntity> jointureDtTyp = root.join(JOINTURE_TYPE_REGLEMENT);
						whereClause.add(criteriaBuilder.equal(jointureDtTyp.get(COLUMN_TYPE), request.getTypeReglementId()));
					}

					// Set request.numPiece on whereClause if not null
					if (estNonVide(request.getNumPiece())) {
						whereClause.add(criteriaBuilder.equal(root.get(COLUMN_NUM_PSC), request.getNumPiece()));
					}

					// Set request.partieIntId on whereClause if not null
					if (request.getPartieIntId() != null) {
						Join<DetailsReglementEntity, ReglementEntity> jointureDtReg = root.join(JOINTURE_REGLEMENT);
						whereClause.add(
								criteriaBuilder.equal(jointureDtReg.get(COLUMN_PARTIEINT_REGLEMENT), request.getPartieIntId()));
					}

					// Set request.reference on whereClause if not null
					if (estNonVide(request.getReference())) {
						Join<DetailsReglementEntity, ReglementEntity> jointureDtReg = root.join(JOINTURE_REGLEMENT);
						whereClause
								.add(criteriaBuilder.equal(jointureDtReg.get(COLUMN_REFERENCE_REGLEMENT), request.getReference()));
					}

					// Set request.dateReglementMin on whereClause if not null
					if (request.getDateReglementDu() != null) {
						Join<DetailsReglementEntity, ReglementEntity> jointureDtReg = root.join(JOINTURE_REGLEMENT);
						whereClause.add(criteriaBuilder.greaterThanOrEqualTo(jointureDtReg.<Calendar>get(COLUMN_DATE_REGLEMENT),
								request.getDateReglementDu()));
					}

					// Set request.dateReglementMax on whereClause if not null
					if (request.getDateReglementAu() != null) {
						Join<DetailsReglementEntity, ReglementEntity> jointureDtReg = root.join(JOINTURE_REGLEMENT);
						whereClause.add(criteriaBuilder.lessThanOrEqualTo(jointureDtReg.<Calendar>get(COLUMN_DATE_REGLEMENT),
								request.getDateReglementAu()));
					}

					// Set request.dateEcheanceMin on whereClause if not null
					if (request.getDateEcheanceDu() != null) {
						whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Calendar>get(COLUMN_DATE_ECHEANCE),
								request.getDateEcheanceDu()));
					}

					// Set request.dateEcheanceMax on whereClause if not null
					if (request.getDateEcheanceAu() != null) {
						whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Calendar>get(COLUMN_DATE_ECHEANCE),
								request.getDateEcheanceAu()));
					}

					// Set request.operationId on whereClause if not null
					if (request.getRegionId() != null) {

						Join<DetailsReglementEntity, ReglementEntity> jointureDtReg = root.join(JOINTURE_REGLEMENT);

						Subquery<Long> subQuery = criteriaQuery.subquery(Long.class);
						Root<PartieInteresseEntite> subRoot = subQuery.from(PartieInteresseEntite.class);

						subQuery.select(subRoot.<Long>get("id"));
						subQuery.where(criteriaBuilder.equal(subRoot.get(JOINTURE_REGION), request.getRegionId()));
						whereClause.add(criteriaBuilder.in(jointureDtReg.get(JOINTURE_PARTIE_INT)).value(subQuery));

					}
					// Set request.avecTerme on whereClause if not null
					if (request.getAvecTerme() != null) {

						Join<DetailsReglementEntity, TypeReglementEntity> jointureDtTyp = root.join(JOINTURE_TYPE_REGLEMENT);

						whereClause.add(criteriaBuilder.equal(jointureDtTyp.get("aTerme"), request.getAvecTerme()));
					}

					criteriaQuery.select(criteriaBuilder.sum(root.get("montant").as(Double.class)))
							.where(whereClause.toArray(new Predicate[] {}));
					Double sommeMont = this.entityManager.createQuery(criteriaQuery).getSingleResult();

					if (sommeMont != null) {
						return sommeMont;
					}
					return 0D;
				}

				@Override
				public DetailsReglementValue getById(Long detailReglementId) {
					DetailsReglementEntity entity = this.rechercherParId(detailReglementId, DetailsReglementEntity.class);
					
					
					if(entity == null) return null;

				    return ReglementPersistanceUtilities.toValue(entity);
				}

				@Override
				public String update(DetailsReglementValue detailsReglementValue) {
					DetailsReglementEntity entity = (DetailsReglementEntity) this.modifier(ReglementPersistanceUtilities.toEntity(detailsReglementValue));

				    return entity.getId().toString();
				}
}
