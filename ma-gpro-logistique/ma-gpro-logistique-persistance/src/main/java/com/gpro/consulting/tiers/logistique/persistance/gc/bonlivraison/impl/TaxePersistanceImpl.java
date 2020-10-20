package com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javassist.expr.NewArray;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.erp.socle.j2ee.mt.socle.utilities.StringUtils;
import com.gpro.consulting.tiers.logistique.coordination.atelier.IConstanteLogistique;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.CritereRechercheRouleauStandardValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.RouleauFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.TaxeValue;
import com.gpro.consulting.tiers.logistique.persistance.atelier.rouleaufini.entity.RouleauFiniEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.IBonLivraisonPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.ITaxePersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie.DetLivraisonVenteEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie.TaxeEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.utilities.BonLivraisonPersistanceUtilities;

/**
 * Implementation of {@link IBonLivraisonPersistance} interface.
 *  
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */

@Component
public class TaxePersistanceImpl extends AbstractPersistance implements ITaxePersistance{
	
	private static final Logger logger = LoggerFactory.getLogger(TaxePersistanceImpl.class);

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private BonLivraisonPersistanceUtilities bonLivraisonPersistanceUtilities;
	
	
	
	
	@Override
	public List<TaxeValue> getAll() {
		
		List<TaxeEntity> listEntity = this.lister(TaxeEntity.class);
		
		return bonLivraisonPersistanceUtilities.toValue(listEntity);
	}
	
	@Override
	public String create(TaxeValue taxeValue) {
		
		TaxeEntity entity = (TaxeEntity) this.creer(bonLivraisonPersistanceUtilities.toEntity(taxeValue));

	    return entity.getId().toString();
	}

	@Override
	public TaxeValue getById(Long id) {
		
		TaxeEntity taxeEntity = this.rechercherParId(id, TaxeEntity.class);

	    return bonLivraisonPersistanceUtilities.toValue(taxeEntity);
	}

	@Override
	public String update(TaxeValue taxeValue) {
		
		DetLivraisonVenteEntity entity = (DetLivraisonVenteEntity) this.modifier(bonLivraisonPersistanceUtilities.toEntity(taxeValue));

	    return entity.getId().toString();
	}

	@Override
	public void delete(Long id) {
		
		this.supprimerEntite(DetLivraisonVenteEntity.class, id);
	}
	

	public EntityManager getEntityManager() {
		return entityManager;
	}

	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

//	@Override
//	public List<TaxeValue> getTVA() {
//				
//	List<TaxeEntity> listEntity = this.lister(TaxeEntity.class);
//	List<TaxeEntity> listEntity2=new ArrayList<TaxeEntity>(); 
//	for(TaxeEntity i:listEntity){
//		if(i.getTva()==true){
//			listEntity2.add(i);
//		}
//			
//	}
//	
//	
//		
//		return bonLivraisonPersistanceUtilities.toValue(listEntity2);
//	}
	@Override
	public List<TaxeValue> getTVA() {
			  
			  
		    /** Création de la squelette de la requete */
		    CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();

		    CriteriaQuery<TaxeEntity> criteriaQuery = criteriaBuilder.createQuery(TaxeEntity.class);
		    
		    List<Predicate> whereClause = new ArrayList<Predicate>();
		    
		    Root<TaxeEntity> root = criteriaQuery.from(TaxeEntity.class);
		    
		   
		    
		    
		    Expression<Boolean> expression = root.get("tva");
			
					whereClause.add(criteriaBuilder.isTrue(expression));
							    
		    criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		    List < TaxeEntity > resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

		    List<RouleauFiniValue> listRouleau = new ArrayList<RouleauFiniValue>();
		    
		   
		    
//		    System.out.println("----listRouleau"+listRouleau);
		    return bonLivraisonPersistanceUtilities.toValue(resultatEntite);

		  }



}
