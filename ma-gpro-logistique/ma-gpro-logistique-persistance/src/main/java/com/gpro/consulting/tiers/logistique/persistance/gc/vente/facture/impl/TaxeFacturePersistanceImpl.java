package com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture.impl;

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
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.TaxeFactureValue;
import com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture.ITaxeFacturePersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture.entitie.DetFactureVenteEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture.entitie.TaxeFactureEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture.utilities.FacturePersistanceUtilities;

/**
 * Implementation of {@link ITaxeFacturePersistance} interface.
 *  
 * @author Wahid Gazzah
 * @since 29/02/2016
 *
 */

@Component
public class TaxeFacturePersistanceImpl extends AbstractPersistance implements ITaxeFacturePersistance{

	private String PREDICATE_FACTURE_VENTE = "factureVente";
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private FacturePersistanceUtilities facturePersistanceUtilities;
	
	@Override
	public String create(TaxeFactureValue taxeFactureValue) {
		
		TaxeFactureEntity entity = (TaxeFactureEntity) this.creer(facturePersistanceUtilities.toEntity(taxeFactureValue));

	    return entity.getId().toString();
	}

	@Override
	public TaxeFactureValue getById(Long id) {
		
		TaxeFactureEntity taxeFactureEntity = this.rechercherParId(id, TaxeFactureEntity.class);

	    return facturePersistanceUtilities.toValue(taxeFactureEntity);
	}

	@Override
	public String update(TaxeFactureValue taxeFactureValue) {
		
		TaxeFactureEntity entity = (TaxeFactureEntity) this.modifier(facturePersistanceUtilities.toEntity(taxeFactureValue));

	    return entity.getId().toString();
	}

	@Override
	public void delete(Long id) {
		
		this.supprimerEntite(DetFactureVenteEntity.class, id);
	}
	
	@Override
	public List<TaxeFactureValue> getAllByFactureId(Long FactureId) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
	    CriteriaQuery<TaxeFactureEntity> criteriaQuery = criteriaBuilder.createQuery(TaxeFactureEntity.class);
	    List<Predicate> whereClause = new ArrayList<Predicate>();
	    Root<TaxeFactureEntity> root = criteriaQuery.from(TaxeFactureEntity.class);
		
	    if(FactureId != null){
	    	whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_FACTURE_VENTE), FactureId));
	    }
		
	    criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    
	    List<TaxeFactureEntity> entityListResult = this.entityManager.createQuery(criteriaQuery).getResultList();

	    List<TaxeFactureValue> listTaxeFacture = new ArrayList<TaxeFactureValue>();
	    
	    for (TaxeFactureEntity entity : entityListResult) {
	    	TaxeFactureValue value = facturePersistanceUtilities.toValue(entity);
	    	listTaxeFacture.add(value);
	    }
	    
	    return listTaxeFacture;
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
