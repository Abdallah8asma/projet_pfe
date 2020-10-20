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
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.DetFactureVenteValue;
import com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture.IDetFactureVentePersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture.entitie.DetFactureVenteEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture.utilities.FacturePersistanceUtilities;


/**
 * Implementation of {@link IBonFacturePersistance} interface.
 *  
 * @author Wahid Gazzah
 * @since 29/02/2016
 *
 */

@Component
public class DetFacturePersistanceImpl extends AbstractPersistance implements IDetFactureVentePersistance{
	
	private String PREDICATE_PRODUIT = "produitId";
	private String PREDICATE_FACTURE_VENTE = "factureVente";
	private String PREDICATE_CHOIX = "choix";

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private FacturePersistanceUtilities facturePersistanceUtilities;
	
	@Override
	public String create(DetFactureVenteValue detFactureVenteValue) {
		
		DetFactureVenteEntity entity = (DetFactureVenteEntity) this.creer(facturePersistanceUtilities.toEntity(detFactureVenteValue));

	    return entity.getId().toString();
	}

	@Override
	public DetFactureVenteValue getById(Long id) {
		
		DetFactureVenteEntity detFactureVenteEntity = this.rechercherParId(id, DetFactureVenteEntity.class);

	    return facturePersistanceUtilities.toValue(detFactureVenteEntity);
	}

	@Override
	public String update(DetFactureVenteValue detFactureVenteValue) {
		
		DetFactureVenteEntity entity = (DetFactureVenteEntity) this.modifier(facturePersistanceUtilities.toEntity(detFactureVenteValue));

	    return entity.getId().toString();
	}

	@Override
	public void delete(Long id) {
		
		this.supprimerEntite(DetFactureVenteEntity.class, id);
	}
	

	public EntityManager getEntityManager() {
		return entityManager;
	}

	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}


	@Override
	public DetFactureVenteValue getByFactureVenteAndProduit(
			Long factureVenteId, Long produitId, String choix) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<DetFactureVenteEntity> criteriaQuery = criteriaBuilder.createQuery(DetFactureVenteEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		
		Root<DetFactureVenteEntity> root = criteriaQuery.from(DetFactureVenteEntity.class);

		// Set FactureVenteId on whereClause if not empty or null
		if (factureVenteId != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_FACTURE_VENTE), factureVenteId));
		}
		
		// Set produitId on whereClause if not null
		if (produitId != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_PRODUIT), produitId));
		}
		
		// Set choix on whereClause if not empty or null
		if (estNonVide(choix)) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CHOIX), choix));
		}

		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    List <DetFactureVenteEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

	    if(resultatEntite.size() >0){
	    	return facturePersistanceUtilities.toValue(resultatEntite.get(0));
	    }
	    else return null;

	}
	
	private boolean estNonVide(String val) {
		return val != null && !"".equals(val);
	}
}
