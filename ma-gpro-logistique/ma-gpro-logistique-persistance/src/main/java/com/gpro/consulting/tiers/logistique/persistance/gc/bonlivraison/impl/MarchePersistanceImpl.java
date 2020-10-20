package com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.impl;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.CommandeVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.RechercheMulticritereBonCommandeValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.ResultatRechecheBonCommandeValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.MarcheValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.RechercheMulticritereMarcheValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.ResultatRechecheMarcheValue;
import com.gpro.consulting.tiers.logistique.persistance.gc.boncommande.entitie.CommandeVenteEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.boncommande.entitie.ProduitCommandeEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.boncommande.utilities.BonCommandePersistanceUtilities;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.IMarchePersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie.MarcheEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.utilities.BonLivraisonPersistanceUtilities;

/**
 * @author Wahid Gazzah
 * @since 19 févr. 2016
 */

@Component
public class MarchePersistanceImpl extends AbstractPersistance implements IMarchePersistance {
	
	private static final Logger logger = LoggerFactory.getLogger(MarchePersistanceImpl.class);

	@PersistenceContext
	private EntityManager entityManager;
	
	private String PERCENT = "%";
	private String PREDICATE_DESIGNATION="designation" ;
	private String PREDICATE_CLIENT="partieInteresseId";
	
	@Autowired
	private BonLivraisonPersistanceUtilities bonLivraisonPersistanceUtilities;
	
	@Override
	public List<MarcheValue> getAll() {
		
		List<MarcheEntity> listEntity = this.lister(MarcheEntity.class);
		
		return bonLivraisonPersistanceUtilities.listMarcheEntityToValue(listEntity);
	}
	
	@Override
	public String create(MarcheValue marcheValue) {
		
		MarcheEntity entity = (MarcheEntity) this.creer(bonLivraisonPersistanceUtilities.toEntity(marcheValue));

	    return entity.getId().toString();
	}

	@Override
	public MarcheValue getById(Long id) {
		
		MarcheEntity MarcheEntity = this.rechercherParId(id, MarcheEntity.class);

	    return bonLivraisonPersistanceUtilities.toValue(MarcheEntity);
	}

	@Override
	public String update(MarcheValue marcheValue) {
		
		MarcheEntity entity = (MarcheEntity) this.modifier(bonLivraisonPersistanceUtilities.toEntity(marcheValue));

	    return entity.getId().toString();
	}

	@Override
	public void delete(Long id) {
		
		this.supprimerEntite(MarcheEntity.class, id);
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
	public ResultatRechecheMarcheValue rechercherMultiCritere(RechercheMulticritereMarcheValue request) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<MarcheEntity> criteriaQuery = criteriaBuilder.createQuery(MarcheEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();

		Root<MarcheEntity> root = criteriaQuery.from(MarcheEntity.class);
		
		/* Designation */
		if (estNonVide(request.getDesignation())) {
			Expression<String> path = root.get(PREDICATE_DESIGNATION);
			Expression<String> upper =criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getDesignation().toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));
		}
		
	
		/* Client (PartieInteressee) */
		if (estNonVide(request.getPartieInteresseId())) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CLIENT), request.getPartieInteresseId()));
		}
		
		

	    criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    List <MarcheEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

	    List<MarcheValue> listMarcheValue = new ArrayList <MarcheValue>();
	    
	    for (MarcheEntity entity : resultatEntite) {
	    	
	    	MarcheValue dto = bonLivraisonPersistanceUtilities.toValue(entity);
	    	
	    	listMarcheValue.add(dto);
	    }
	    

	    //Collections.sort(listMarcheValue);
	    
	    ResultatRechecheMarcheValue resultat = new ResultatRechecheMarcheValue();

	    resultat.setNombreResultaRechercher(Long.valueOf(listMarcheValue.size()));

	    resultat.setListMarche(listMarcheValue);

	    return resultat;
	}

	
	@Override
	public List<MarcheValue> getListById(Long id) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<MarcheEntity> criteriaQuery = criteriaBuilder.createQuery(MarcheEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();

		Root<MarcheEntity> root = criteriaQuery.from(MarcheEntity.class);
		
		
		
		/* Client (PartieInteressee) */
		if (id!=null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CLIENT), id));
		}
		
		

	    criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    List <MarcheEntity> listEntity = this.entityManager.createQuery(criteriaQuery).getResultList();

	    
		
		
		
		
		
		
		return bonLivraisonPersistanceUtilities.listMarcheEntityToValue(listEntity);
	}
	
	
}
