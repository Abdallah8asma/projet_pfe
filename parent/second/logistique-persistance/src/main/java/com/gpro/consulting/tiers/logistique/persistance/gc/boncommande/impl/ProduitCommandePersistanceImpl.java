package com.gpro.consulting.tiers.logistique.persistance.gc.boncommande.impl;

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

import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.logistique.coordination.atelier.IConstanteLogistique;
import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.CommandeVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.ProduitCommandeValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.RechercheMulticritereProduitBonCommandeValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.ResultatRechecheBonCommandeValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.ResultatRechecheProduitBonCommandeValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.DetFactureVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ResultatRechecheDetFactureVenteValue;
import com.gpro.consulting.tiers.logistique.persistance.gc.boncommande.IProduitCommandePersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.boncommande.entitie.CommandeVenteEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.boncommande.entitie.ProduitCommandeEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.boncommande.utilities.BonCommandePersistanceUtilities;
import com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture.entitie.DetFactureVenteEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture.entitie.FactureVenteEntity;

/**
 * 
 * @author Zeineb Medimagh
 * @since 16/11/2016
 *
 */

@Component
public class ProduitCommandePersistanceImpl extends AbstractPersistance implements IProduitCommandePersistance {
	private String PREDICATE_LIVRE = "livre";

	private String PERCENT = "%";
	private String PREDICATE_REFERENCE = "reference";
	private String PREDICATE_CLIENT = "partieIntersseId";
	private String PREDICATE_PRODUIT_COMMANDES = "listProduitCommandes";
	private String PREDICATE_DATE_INTRODUCTION = "dateIntroduction";
	private String PREDICATE_DATE_LIVRAISON = "dateLivraison";
	private String PREDICATE_PRODUIT = "produit";
	private String PREDICATE_ID = "id";
	private String PREDICATE_QUANTITE = "quantite";
	private String PREDICATE_COUT = "prixTotal";
	private String PREDICATE_TYPE = "type";
	
	private String PREDICATE_REGLEMENT_ID = "reglementId";
	
	private String boutiqueId = "boutiqueId";
	private String PREDICAE_REMISE="remise";
	private String PREDICAE_TAXEID= "taxeId";
	private String PREDICAE_PRIX="prixUnitaire";
	
	private int FIRST_INDEX = 0;
	private String devise = "devise";
	private int MAX_RESULTS =52;
	@PersistenceContext
	private EntityManager entityManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.persistance.gc.boncommande.
	 * IProduitCommandePersistance#create(com.gpro.consulting.tiers.logistique.
	 * coordination.gc.boncommande.value.ProduitCommandeValue)
	 */
	@Override
	public String create(ProduitCommandeValue produitCommandeValue) {

		ProduitCommandeEntity entity = (ProduitCommandeEntity) this
				.creer(BonCommandePersistanceUtilities.toEntity(produitCommandeValue));

		return entity.getId().toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.persistance.gc.boncommande.
	 * IProduitCommandePersistance#getById(java.lang.Long)
	 */
	@Override
	public ProduitCommandeValue getById(Long id) {
		ProduitCommandeEntity produitCommandeEntity = this.rechercherParId(id, ProduitCommandeEntity.class);
		return BonCommandePersistanceUtilities.toValue(produitCommandeEntity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.persistance.gc.boncommande.
	 * IProduitCommandePersistance#update(com.gpro.consulting.tiers.logistique.
	 * coordination.gc.boncommande.value.ProduitCommandeValue)
	 */
	@Override
	public String update(ProduitCommandeValue produitCommandeValue) {

		ProduitCommandeEntity entity = (ProduitCommandeEntity) this
				.modifier(BonCommandePersistanceUtilities.toEntity(produitCommandeValue));

		return entity.getId().toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.persistance.gc.boncommande.
	 * IProduitCommandePersistance#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) {

		this.supprimerEntite(ProduitCommandeEntity.class, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.persistance.gc.boncommande.
	 * IProduitCommandePersistance#getAll()
	 */
	@Override
	public List<ProduitCommandeValue> getAll() {

		List<ProduitCommandeEntity> listEntity = this.lister(ProduitCommandeEntity.class);

		List<ProduitCommandeValue> finalList = new ArrayList<ProduitCommandeValue>();
		for (ProduitCommandeEntity produitCommandeEntity : listEntity) {
			finalList.add(BonCommandePersistanceUtilities.toValue(produitCommandeEntity));
		}

		Collections.sort(finalList);

		return finalList;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public ResultatRechecheProduitBonCommandeValue rechercherMultiCritere(
			RechercheMulticritereProduitBonCommandeValue request) {
		// TODO Auto-generated method stub
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<ProduitCommandeEntity> criteriaQuery = criteriaBuilder.createQuery(ProduitCommandeEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();

		Root<ProduitCommandeEntity> root = criteriaQuery.from(ProduitCommandeEntity.class);
		
		
		if (estNonVide(request.getReference())) {
			Join<ProduitCommandeEntity,CommandeVenteEntity> jointProduitCommande = root.join("commandeVente");

			whereClause.add(criteriaBuilder.equal(jointProduitCommande.get(PREDICATE_REFERENCE), request.getReference()));
		}
		/* Client (PartieInteressee) */
		if (estNonVide(request.getPartieInteresseId())) {
			Join<ProduitCommandeEntity,CommandeVenteEntity> jointProduitCommande = root.join("commandeVente");

			whereClause.add(criteriaBuilder.equal(jointProduitCommande.get(PREDICATE_CLIENT), request.getPartieInteresseId()));
		}
		//datecommande 
		if (request.getDateIntroductionDu() != null) {
			Join<ProduitCommandeEntity,CommandeVenteEntity> jointProduitCommande = root.join("commandeVente");

			whereClause.add(criteriaBuilder.greaterThanOrEqualTo(jointProduitCommande.<Calendar> get(PREDICATE_DATE_INTRODUCTION),
					request.getDateIntroductionDu()));
		}
		//datecommande 

		if (request.getDateIntroductionA() != null) {
			Join<ProduitCommandeEntity,CommandeVenteEntity> jointProduitCommande = root.join("commandeVente");

			whereClause.add(criteriaBuilder.lessThanOrEqualTo(jointProduitCommande.<Calendar> get(PREDICATE_DATE_INTRODUCTION),
					request.getDateIntroductionA()));
		}

		/* Data Livraison */
		if (request.getDateLivraisonDu() != null) {
			Join<ProduitCommandeEntity,CommandeVenteEntity> jointProduitCommande = root.join("commandeVente");

			whereClause.add(criteriaBuilder.greaterThanOrEqualTo(jointProduitCommande.<Calendar> get(PREDICATE_DATE_LIVRAISON),
					request.getDateLivraisonDu()));

		}
        //date livrraison
		if (request.getDateLivraisonA() != null) {
			Join<ProduitCommandeEntity,CommandeVenteEntity> jointProduitCommande = root.join("commandeVente");

			whereClause.add(criteriaBuilder.lessThanOrEqualTo(jointProduitCommande.<Calendar> get(PREDICATE_DATE_LIVRAISON),
					request.getDateLivraisonA()));
		}
		if (request.getQuantiteDu() != null) {
			whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Double> get(PREDICATE_QUANTITE),
					request.getQuantiteDu()));
		}

		if (request.getQuantiteA() != null) {
			whereClause.add(
					criteriaBuilder.lessThanOrEqualTo(root.<Double> get(PREDICATE_QUANTITE), request.getQuantiteA()));
		}

		
		/*  Produit */
		if ((request.getIdProduit()) != null) {
			
			whereClause.add(criteriaBuilder.equal( root.get(PREDICATE_PRODUIT), request.getIdProduit()));

		}

		/* Cout */
		if (request.getCoutDu() != null) {
			Join<ProduitCommandeEntity,CommandeVenteEntity> jointProduitCommande = root.join("commandeVente");

			whereClause.add(criteriaBuilder.greaterThanOrEqualTo(jointProduitCommande.<Double> get(PREDICATE_COUT), request.getCoutDu()));
		}

		if (request.getCoutA() != null) 
		{
			Join<ProduitCommandeEntity,CommandeVenteEntity> jointProduitCommande = root.join("commandeVente");

			whereClause.add(criteriaBuilder.lessThanOrEqualTo(jointProduitCommande.<Double> get(PREDICATE_COUT), request.getCoutA()));
		}

		if (request.getDevise()!= null) {
			Join<ProduitCommandeEntity,CommandeVenteEntity> jointProduitCommande = root.join("commandeVente");
			whereClause.add(criteriaBuilder.equal(jointProduitCommande.get(devise), request.getDevise()));
		}
		if(request.getRemise()!=null)
		{
			whereClause.add(criteriaBuilder.equal(root.get(PREDICAE_REMISE), request.getRemise()));

		}
		if(request.getTaxeId()!=null)
		{
			whereClause.add(criteriaBuilder.equal(root.get(PREDICAE_REMISE), request.getRemise()));

		}
		if(request.getPrixUnitaireDe()!=null)
		{
			whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Double> get(PREDICAE_PRIX), request.getPrixUnitaireDe()));

		}
		if(request.getPrixUnitaireA()!=null)
		{
			whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Double> get(PREDICAE_PRIX), request.getPrixUnitaireA()));

		}
			
 
		 criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
			
			criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));
			
			
	        List<ProduitCommandeEntity> resultatEntite = null;
			
			if(request.isOptimized())
			{
				resultatEntite = this.entityManager.createQuery(criteriaQuery).setMaxResults(MAX_RESULTS).getResultList();
			}
			
			else
				
			{
				resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
			}

			
			
			List<ProduitCommandeValue> list = new ArrayList<ProduitCommandeValue>();

			for (ProduitCommandeEntity entity : resultatEntite) {
				ProduitCommandeValue dto =BonCommandePersistanceUtilities.toValueEnrichi(entity);
				list.add(dto);
			}
			
			

			ResultatRechecheProduitBonCommandeValue result = new ResultatRechecheProduitBonCommandeValue();
			result.setNombreResultaRechercher(Long.valueOf(list.size()));
			Collections.sort(list);
			result.setListProduitCommandeValues(list);
		
			return result;

		
	}
	private boolean estNonVide(String val) {
		return val != null && !"".equals(val);

	}

}
