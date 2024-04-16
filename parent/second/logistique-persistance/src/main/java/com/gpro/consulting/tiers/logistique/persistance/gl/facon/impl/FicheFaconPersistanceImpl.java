package com.gpro.consulting.tiers.logistique.persistance.gl.facon.impl;

import java.util.ArrayList;
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
import com.gpro.consulting.tiers.logistique.coordination.gl.facon.value.FicheFaconValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.facon.value.RechercheMulticritereFicheFaconValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.facon.value.ResultatRechecheFicheFaconElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.facon.value.ResultatRechecheFicheFaconValue;
import com.gpro.consulting.tiers.logistique.persistance.gl.facon.IFicheFaconPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gl.facon.entity.FicheFaconEntity;
import com.gpro.consulting.tiers.logistique.persistance.gl.facon.utilities.FaconPersistanceUtilities;

/**
 *
 * @author Zeineb Medimagh
 * @since 27/09/2016
 *
 */

@Component
public class FicheFaconPersistanceImpl  extends AbstractPersistance implements IFicheFaconPersistance {
	
	private static final Logger logger = LoggerFactory.getLogger(FicheFaconPersistanceImpl.class);
	
	private String PREDICATE_CLIENT = "partieIntId";
	private String PREDICATE_PRODUIT = "produitId";
	private String PREDICATE_REF_BON_RECEPTION = "refBonReception";
	private String PERCENT = "%";
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public String create(FicheFaconValue request) {

		FicheFaconEntity entity = (FicheFaconEntity) this.creer(FaconPersistanceUtilities.toEntity(request));

	    return entity.getId().toString();
	}

	@Override
	public void delete(Long id) {
		
		this.supprimerEntite(FicheFaconEntity.class, id.longValue());
	}
	
	@Override
	public String update(FicheFaconValue request) {
		
		FicheFaconEntity entity = (FicheFaconEntity) this.modifier(FaconPersistanceUtilities.toEntity(request));

	    return entity.getId().toString();
	}

	@Override
	public FicheFaconValue getById(Long id) {
		
		FicheFaconEntity entity = this.rechercherParId(id, FicheFaconEntity.class);

	    return FaconPersistanceUtilities.toValue(entity);
	}
	
	@Override
	public List<FicheFaconValue> getAll() {
		
		List<FicheFaconEntity> listEntity = this.lister(FicheFaconEntity.class);
		
		List<FicheFaconValue> listDTO = new ArrayList<FicheFaconValue>();
		
		for(FicheFaconEntity entity : listEntity){
			
			listDTO.add(FaconPersistanceUtilities.toValue(entity));
		}
		
		return listDTO;
	}

	private boolean estNonVide(String val) {
		return val != null && !"".equals(val);
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public ResultatRechecheFicheFaconValue rechercherMultiCritere(RechercheMulticritereFicheFaconValue request) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<FicheFaconEntity> criteriaQuery = criteriaBuilder.createQuery(FicheFaconEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<FicheFaconEntity> root = criteriaQuery.from(FicheFaconEntity.class);
		
		// Set request.partieIntId on whereClause if not null
		if (request.getPartieIntId() != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CLIENT), request.getPartieIntId()));
		}
		
		// Set request.produitId on whereClause if not null
		if (request.getProduitId() != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_PRODUIT), request.getProduitId()));
		}
		
		// Set request.numMise on whereClause if not empty or null
		if (estNonVide(request.getRefBonReception())) {
			Expression<String> path = root.get(PREDICATE_REF_BON_RECEPTION);
			Expression<String> upper =criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getRefBonReception().toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));
		}
		
		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    List <FicheFaconEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

	    List<ResultatRechecheFicheFaconElementValue> list = new ArrayList<ResultatRechecheFicheFaconElementValue>();
	    
	    for (FicheFaconEntity ficheFaconEntity : resultatEntite) {
	    	ResultatRechecheFicheFaconElementValue ficheFaconValue = FaconPersistanceUtilities.toResultatRechecheFicheFaconElementValue(ficheFaconEntity);
	    	list.add(ficheFaconValue);
	    }

	    ResultatRechecheFicheFaconValue result = new ResultatRechecheFicheFaconValue();
	    Collections.sort(list);
	    result.setNombreResultaRechercher(Long.valueOf(list.size()));
	    result.setList(new TreeSet<>(list));
	    
	    return result;

	}
	
	@Override
	public List<FicheFaconValue> getFicheByRefBonReception(String refBonReception) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<FicheFaconEntity> criteriaQuery = criteriaBuilder.createQuery(FicheFaconEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<FicheFaconEntity> root = criteriaQuery.from(FicheFaconEntity.class);
		
		//System.out.println("---- refBonReception ---" + refBonReception);
		
		// Set referenceMise on whereClause if not null
		if (estNonVide(refBonReception)) {
				Expression<String> path = root.get(PREDICATE_REF_BON_RECEPTION);
				Expression<String> upper =criteriaBuilder.upper(path);
				Predicate predicate = criteriaBuilder.like(upper, PERCENT + refBonReception.toUpperCase() + PERCENT);
				whereClause.add(criteriaBuilder.and(predicate));
		}
		
		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		List<FicheFaconEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

		//System.out.println("---- get fiche by refBonReception ---" + resultatEntite.size());
		
	    List<FicheFaconValue> list = new ArrayList<FicheFaconValue>();
	    
	    for (FicheFaconEntity entity : resultatEntite) {
	    	FicheFaconValue dto = FaconPersistanceUtilities.toValue(entity);
	    	list.add(dto);
	    }

	    
	    return list;

	}

}
