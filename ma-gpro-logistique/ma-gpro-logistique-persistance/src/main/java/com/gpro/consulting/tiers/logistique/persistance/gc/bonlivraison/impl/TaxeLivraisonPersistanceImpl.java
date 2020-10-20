package com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.TaxeLivraisonValue;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.IBonLivraisonPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.ITaxeLivraisonPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie.DetLivraisonVenteEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie.TaxeLivraisonEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.utilities.BonLivraisonPersistanceUtilities;

/**
 * Implementation of {@link IBonLivraisonPersistance} interface.
 *  
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */

@Component
public class TaxeLivraisonPersistanceImpl extends AbstractPersistance implements ITaxeLivraisonPersistance{

	private String PREDICATE_LIVRAISON_VENTE = "livraisonVente";
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private BonLivraisonPersistanceUtilities bonLivraisonPersistanceUtilities;
	
	@Override
	public String create(TaxeLivraisonValue taxeLivraisonValue) {
		
		TaxeLivraisonEntity entity = (TaxeLivraisonEntity) this.creer(bonLivraisonPersistanceUtilities.toEntity(taxeLivraisonValue));

	    return entity.getId().toString();
	}

	@Override
	public TaxeLivraisonValue getById(Long id) {
		
		TaxeLivraisonEntity taxeLivraisonEntity = this.rechercherParId(id, TaxeLivraisonEntity.class);

	    return bonLivraisonPersistanceUtilities.toValue(taxeLivraisonEntity);
	}

	@Override
	public String update(TaxeLivraisonValue taxeLivraisonValue) {
		
		TaxeLivraisonEntity entity = (TaxeLivraisonEntity) this.modifier(bonLivraisonPersistanceUtilities.toEntity(taxeLivraisonValue));

	    return entity.getId().toString();
	}

	@Override
	public void delete(Long id) {
		
		this.supprimerEntite(DetLivraisonVenteEntity.class, id);
	}
	
	@Override
	public List<TaxeLivraisonValue> getAllByLivraisonId(Long livraisonId) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
	    CriteriaQuery<TaxeLivraisonEntity> criteriaQuery = criteriaBuilder.createQuery(TaxeLivraisonEntity.class);
	    List<Predicate> whereClause = new ArrayList<Predicate>();
	    Root<TaxeLivraisonEntity> root = criteriaQuery.from(TaxeLivraisonEntity.class);
		
	    if(livraisonId != null){
	    	whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_LIVRAISON_VENTE), livraisonId));
	    }
		
	    criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    
	    List<TaxeLivraisonEntity> entityListResult = this.entityManager.createQuery(criteriaQuery).getResultList();

	    List<TaxeLivraisonValue> listTaxeLivraison = new ArrayList<TaxeLivraisonValue>();
	    
	    for (TaxeLivraisonEntity entity : entityListResult) {
	    	TaxeLivraisonValue value = bonLivraisonPersistanceUtilities.toValue(entity);
	    	listTaxeLivraison.add(value);
	    }
	    
	    return listTaxeLivraison;
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
