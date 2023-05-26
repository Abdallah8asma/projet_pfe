package com.gpro.consulting.tiers.logistique.persistance.gc.achat.facture.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.DetFactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.RechercheMulticritereDetFactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.ResultatRechecheDetFactureAchatValue;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.facture.IDetFactureAchatPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.facture.entitie.DetFactureAchatEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.facture.entitie.FactureAchatEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.facture.utilities.FactureAchatPersistanceUtilities;

/**
 * Implementation of {@link IBonFacturePersistance} interface.
 * 
 * @author Wahid Gazzah
 * @since 29/02/2016
 *
 */

@Component
public class DetFactureAchatPersistanceImpl extends AbstractPersistance implements IDetFactureAchatPersistance {

	private String PREDICATE_PRODUIT = "produitId";
	private String PREDICATE_FACTURE_Achat = "factureAchat";
	private String PREDICATE_CHOIX = "choix";
	private String qantite="quantite";
	private String produitId="produitId";
	private String PREDICATE_REFERENCE = "reference";
	private String PREDICATE_CLIENT="partieIntId";
	private String PREDICAD_date="date";
	private String PREDICATE_INFO_LIVRAISON_EXTERNE = "refexterne";
	private String PREDICATE_INFO_LIVRAISON = "infoLivraison";
	private String PREDICATE_prixUnitaireHT="prixUnitaireHT";
	private String PREDICATE_produitId="produitId";
	private int MAX_RESULTS =52;

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private FactureAchatPersistanceUtilities facturePersistanceUtilities;

	@Override
	public String create(DetFactureAchatValue detFactureAchatValue) {

		DetFactureAchatEntity entity = (DetFactureAchatEntity) this
				.creer(facturePersistanceUtilities.toEntity(detFactureAchatValue));

		return entity.getId().toString();
	}

	@Override
	public DetFactureAchatValue getById(Long id) {

		DetFactureAchatEntity detFactureAchatEntity = this.rechercherParId(id, DetFactureAchatEntity.class);

		return facturePersistanceUtilities.toValue(detFactureAchatEntity);
	}

	@Override
	public String update(DetFactureAchatValue detFactureAchatValue) {

		DetFactureAchatEntity entity = (DetFactureAchatEntity) this
				.modifier(facturePersistanceUtilities.toEntity(detFactureAchatValue));

		return entity.getId().toString();
	}

	@Override
	public void delete(Long id) {

		this.supprimerEntite(DetFactureAchatEntity.class, id);
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public DetFactureAchatValue getByFactureAchatAndProduit(Long factureAchatId, Long produitId, String choix) {

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();

		CriteriaQuery<DetFactureAchatEntity> criteriaQuery = criteriaBuilder.createQuery(DetFactureAchatEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();

		Root<DetFactureAchatEntity> root = criteriaQuery.from(DetFactureAchatEntity.class);

		// Set FactureAchatId on whereClause if not empty or null
		if (factureAchatId != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_FACTURE_Achat), factureAchatId));
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
		List<DetFactureAchatEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

		if (resultatEntite.size() > 0) {
			return facturePersistanceUtilities.toValue(resultatEntite.get(0));
		} else
			return null;

	}

	private boolean estNonVide(String val) {
		return val != null && !"".equals(val);
	}

	@Override
	public ResultatRechecheDetFactureAchatValue rechercherMultiCritere(
			RechercheMulticritereDetFactureAchatValue request) {
		// TODO Auto-generated method stub
		

		//logger.info("rechercherMultiCritere");

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();

		CriteriaQuery<DetFactureAchatEntity> criteriaQuery = criteriaBuilder.createQuery(DetFactureAchatEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();

		Root<DetFactureAchatEntity> root = criteriaQuery.from(DetFactureAchatEntity.class);
		
		if(estNonVide(request.getFactureReference())){
			Join<DetFactureAchatEntity,FactureAchatEntity> jointFactureAchat = root.join("factureAchat");
			whereClause.add(criteriaBuilder.equal(jointFactureAchat.get(PREDICATE_REFERENCE), request.getFactureReference()));
		}
		if(request.getPartieIntId()!=null)
		{
			Join<DetFactureAchatEntity,FactureAchatEntity> jointFactureAchat = root.join("factureAchat");
			whereClause.add(criteriaBuilder.equal(jointFactureAchat.get(PREDICATE_CLIENT), request.getPartieIntId()));
		}
	
		if (request.getDateFactureDe() != null) {
			Join<DetFactureAchatEntity,FactureAchatEntity> jointFactureAchat = root.join("factureAchat");

			whereClause.add(criteriaBuilder.greaterThanOrEqualTo(jointFactureAchat.<Calendar> get(PREDICAD_date),
					request.getDateFactureDe()));
		}

		if (request.getDateFactureA() != null) {
			Join<DetFactureAchatEntity,FactureAchatEntity> jointFactureAchat = root.join("factureAchat");

			whereClause.add(criteriaBuilder.lessThanOrEqualTo(jointFactureAchat.<Calendar> get(PREDICAD_date),
					request.getDateFactureA()));
		}

	
		if(estNonVide(request.getInfoLivraison())){
			Join<DetFactureAchatEntity,FactureAchatEntity> jointFactureAchat = root.join("factureAchat");
			whereClause.add(criteriaBuilder.equal(jointFactureAchat.get(PREDICATE_INFO_LIVRAISON), request.getInfoLivraison()));
		}
		if(estNonVide(request.getReferenceBlExterne()))
		{
			Join<DetFactureAchatEntity,FactureAchatEntity> jointFactureAchat = root.join("factureAchat");
			whereClause.add(criteriaBuilder.equal(jointFactureAchat.get(PREDICATE_INFO_LIVRAISON_EXTERNE), request.getReferenceBlExterne()));

		}
		if(request.getQuantiteDe()!=null)
		{
			whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Double>get(qantite), request.getQuantiteDe()));

		}
		if(request.getQunatiteA()!=null)
		{
			whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Double>get(qantite), request.getQunatiteA()));

		}
		if(request.getProduitId()!=null)
		{
			whereClause.add(criteriaBuilder.equal( root.get(produitId), request.getProduitId()));

		}
		if(request.getPrixMax()!=null)
		{
			whereClause.add(criteriaBuilder.greaterThanOrEqualTo( root.<Double>get(PREDICATE_prixUnitaireHT), request.getPrixMin()));

		}
		if(request.getPrixMin()!=null)
		{
			whereClause.add(criteriaBuilder.lessThanOrEqualTo( root.<Double>get(PREDICATE_prixUnitaireHT), request.getPrixMax()));

		}

		
	
	
		

		

		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
			
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));
			
			
	        List<DetFactureAchatEntity> resultatEntite = null;
			
			if(request.isOptimized())
			{
				resultatEntite = this.entityManager.createQuery(criteriaQuery).setMaxResults(MAX_RESULTS).getResultList();
			}
			
			else
				
			{
				resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
			}

			
			
			List<DetFactureAchatValue> list = new ArrayList<DetFactureAchatValue>();

			for (DetFactureAchatEntity entity : resultatEntite) {
				DetFactureAchatValue dto =facturePersistanceUtilities.toValueEnrichi(entity);
				list.add(dto);
			}
			
			

			ResultatRechecheDetFactureAchatValue result = new ResultatRechecheDetFactureAchatValue();
			result.setNombreResultaRechercher(Long.valueOf(list.size()));
			//Collections.sort(list);
			result.setList(list);
		
			
			
		


			return result;
	}

}
