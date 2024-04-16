package com.gpro.consulting.tiers.logistique.persistance.gc.achat.boncommande.impl;

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
import com.gpro.consulting.tiers.logistique.coordination.atelier.IConstanteLogistique;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.boncommande.value.CommandeAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.boncommande.value.RechercheMulticritereBonCommandeAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.boncommande.value.ResultatRechecheBonCommandeAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.boncommande.IBonCommandeAchatPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.boncommande.entitie.CommandeAchatEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.boncommande.utilities.BonCommandeAchatPersistanceUtilities;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reception.IReceptionAchatPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.boncommande.entitie.ProduitCommandeEntity;

/**
 * The Class BonCommandePersistanceImpl.
 * 
 * @author Hamdi Etteieb
 */
@Component
public class BonCommandeAchatPersistanceImpl extends AbstractPersistance implements IBonCommandeAchatPersistance {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(BonCommandeAchatPersistanceImpl.class);
	
	private String PREDICATE_RECEPTION = "reception";

	/** The percent. */
	private String PERCENT = "%";

	/** The predicate reference. */
	private String PREDICATE_REFERENCE = "reference";

	/** The predicate client. */
	private String PREDICATE_CLIENT = "partieIntersseId";

	/** The predicate produit commandes. */
	private String PREDICATE_PRODUIT_COMMANDES = "listProduitCommandes";

	/** The predicate date introduction. */
	private String PREDICATE_DATE_INTRODUCTION = "dateIntroduction";

	/** The predicate date livraison. */
	private String PREDICATE_DATE_LIVRAISON = "dateLivraison";

	/** The predicate produit. */
	private String PREDICATE_PRODUIT = "produit";

	/** The predicate id. */
	private String PREDICATE_ID = "id";

	/** The predicate quantite. */
	private String PREDICATE_QUANTITE = "quantite";

	/** The predicate cout. */
	private String PREDICATE_COUT = "prixTotal";

	/** The predicate type. */
	private String PREDICATE_TYPE = "type";
	
	private String PREDICATE_BOUTIQUEID = "boutiqueId";

	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;

	/** The bon commande persistance utilities. */
	@Autowired
	private BonCommandeAchatPersistanceUtilities bonCommandePersistanceUtilities;
	
	@Autowired 
	private IReceptionAchatPersistance receptionAchatPersistance;

	/**
	 * Creates the.
	 *
	 * @param bonCommandeValue
	 *            the bon commande value
	 * @return the string
	 */
	@Override
	public String create(CommandeAchatValue bonCommandeValue) {

		//logger.info("----- bonCommande create : persistance layer ----------");

		CommandeAchatEntity commandeAchatEntity = BonCommandeAchatPersistanceUtilities.toEntity(bonCommandeValue);
		CommandeAchatEntity entity = (CommandeAchatEntity) this.creer(commandeAchatEntity);

		return entity.getId().toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.persistance.gc.boncommande.
	 * IBonCommandePersistance#getById(java.lang.Long)
	 */
	@Override
	public CommandeAchatValue getById(Long id) {
		CommandeAchatEntity commandeAchatEntity = this.rechercherParId(id, CommandeAchatEntity.class);
		return BonCommandeAchatPersistanceUtilities.toValue(commandeAchatEntity);
	}

	/**
	 * Update.
	 *
	 * @param bonCommandeValue
	 *            the bon commande value
	 * @return the string
	 */
	@Override
	public String update(CommandeAchatValue bonCommandeValue) {

		CommandeAchatEntity entity = (CommandeAchatEntity) this
				.modifier(BonCommandeAchatPersistanceUtilities.toEntity(bonCommandeValue));

		return entity.getId().toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.persistance.gc.boncommande.
	 * IBonCommandePersistance#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) {

		this.supprimerEntite(CommandeAchatEntity.class, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.persistance.gc.boncommande.
	 * IBonCommandePersistance#getAll()
	 */
	@Override
	public List<CommandeAchatValue> getAll() {

		List<CommandeAchatEntity> listEntity = this.lister(CommandeAchatEntity.class);

		List<CommandeAchatValue> finalList = new ArrayList<CommandeAchatValue>();
		for (CommandeAchatEntity commandeAchatEntity : listEntity) {
			finalList.add(BonCommandeAchatPersistanceUtilities.toValue(commandeAchatEntity));
		}

		return finalList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.persistance.gc.boncommande.
	 * IBonCommandePersistance#rechercherMultiCritere(com.gpro.consulting.tiers.
	 * logistique.coordination.gc.boncommande.value.
	 * RechercheMulticritereBonCommandeValue)
	 */
	@Override
	public ResultatRechecheBonCommandeAchatValue rechercherMultiCritere(
			RechercheMulticritereBonCommandeAchatValue request) {

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<CommandeAchatEntity> criteriaQuery = criteriaBuilder.createQuery(CommandeAchatEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();

		Root<CommandeAchatEntity> root = criteriaQuery.from(CommandeAchatEntity.class);
		
		if(request.getBoutiqueId() != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_BOUTIQUEID), request.getBoutiqueId()));
		}
		

		/* Reference */
		if (estNonVide(request.getReference())) {
			Expression<String> path = root.get(PREDICATE_REFERENCE);
			Expression<String> upper = criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getReference().toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));
		}

		/* Type */
		if (estNonVide(request.getType())) {
			Expression<String> path = root.get(PREDICATE_TYPE);
			Expression<String> upper = criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getType().toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));
		}
		/* Client (PartieInteressee) */
		if (estNonVide(request.getPartieInteresseId())) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CLIENT), request.getPartieInteresseId()));
		}

		/* Désignation Produit */
		if ((request.getIdProduitParDesignation()) != null) {
			Join<CommandeAchatEntity, ProduitCommandeEntity> jointureCmdEnPrdCmd = root
					.join(PREDICATE_PRODUIT_COMMANDES);
			whereClause.add(criteriaBuilder.equal(jointureCmdEnPrdCmd.get(PREDICATE_PRODUIT).get(PREDICATE_ID),
					request.getIdProduitParDesignation()));
		}

		/* Réf Produit */
		if ((request.getIdProduitParRef()) != null) {
			Join<CommandeAchatEntity, ProduitCommandeEntity> jointureCmdEnPrdCmd = root
					.join(PREDICATE_PRODUIT_COMMANDES);
			whereClause.add(criteriaBuilder.equal(jointureCmdEnPrdCmd.get(PREDICATE_PRODUIT).get(PREDICATE_ID),
					request.getIdProduitParRef()));
		}

		/* Data Introduction */
		if (request.getDateIntroductionDu() != null) {
			whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Calendar> get(PREDICATE_DATE_INTRODUCTION),
					request.getDateIntroductionDu()));
		}

		if (request.getDateIntroductionA() != null) {
			whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Calendar> get(PREDICATE_DATE_INTRODUCTION),
					request.getDateIntroductionA()));
		}

		/* Data Livraison */
		if (request.getDateLivraisonDu() != null) {
			whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Calendar> get(PREDICATE_DATE_LIVRAISON),
					request.getDateLivraisonDu()));

		}

		if (request.getDateLivraisonA() != null) {
			whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Calendar> get(PREDICATE_DATE_LIVRAISON),
					request.getDateLivraisonA()));
		}

		/* quantite */
		if (request.getQuantiteDu() != null) {
			whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Double> get(PREDICATE_QUANTITE),
					request.getQuantiteDu()));
		}

		if (request.getQuantiteA() != null) {
			whereClause.add(
					criteriaBuilder.lessThanOrEqualTo(root.<Double> get(PREDICATE_QUANTITE), request.getQuantiteA()));
		}

		/* Cout */
		if (request.getCoutDu() != null) {
			whereClause
					.add(criteriaBuilder.greaterThanOrEqualTo(root.<Double> get(PREDICATE_COUT), request.getCoutDu()));
		}

		if (request.getCoutA() != null) {
			whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Double> get(PREDICATE_COUT), request.getCoutA()));
		}
		
		
		
		/* Reception */
		
/*		if (estNonVide(request.getReception())) {
			Expression<Boolean> expression = root.get(PREDICATE_RECEPTION);
			switch (request.getReception()) {
				case IConstanteLogistique.YES:
					whereClause.add(criteriaBuilder.isTrue(expression));
					break;
				case IConstanteLogistique.NO:
					whereClause.add(criteriaBuilder.isFalse(expression));
					break;
				case IConstanteLogistique.ALL:
					break;
				default:
					break;
			}
		}*/

		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		List<CommandeAchatEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

		List<CommandeAchatValue> listCommandeAchatValue = new ArrayList<CommandeAchatValue>();

		for (CommandeAchatEntity entity : resultatEntite) {

			CommandeAchatValue dto = BonCommandeAchatPersistanceUtilities.toValue(entity);
			Collections.sort(dto.getListProduitCommandes());
			listCommandeAchatValue.add(dto);
		}


		
		
		
	 	if(estNonVide(request.getReception())){
 			
	 		List<CommandeAchatValue> tempList = new ArrayList<CommandeAchatValue>();
	 		
	 	// Reception facturée
	 		
			if(request.getReception().equals(IConstanteLogistique.YES)){
	 				
				for (CommandeAchatValue commandeValue : listCommandeAchatValue) {
					if(receptionAchatPersistance.existeBC(commandeValue.getReference())){
						tempList.add(commandeValue);
					}
				}
				
 			}
			// Reception non facturée
			else if(request.getReception().equals(IConstanteLogistique.NO)){
				
				for (CommandeAchatValue commandeValue : listCommandeAchatValue) {
					if(!receptionAchatPersistance.existeBC(commandeValue.getReference())){
						tempList.add(commandeValue);
					}
				}
			}
			
			listCommandeAchatValue.clear();
			listCommandeAchatValue=tempList;
	 			
	 	}
		
		
		
		
		Collections.sort(listCommandeAchatValue);
		

		ResultatRechecheBonCommandeAchatValue resultat = new ResultatRechecheBonCommandeAchatValue();

		resultat.setNombreResultaRechercher(Long.valueOf(listCommandeAchatValue.size()));

		resultat.setListCommandeAchat(listCommandeAchatValue);

		return resultat;
	}

	/**
	 * Est non vide.
	 *
	 * @param val
	 *            the val
	 * @return true, if successful
	 */
	private boolean estNonVide(String val) {
		return val != null && !"".equals(val) && !"undefined".equals(val);

	}

	/**
	 * *************************** Getter & Setter
	 * ******************************.
	 *
	 * @return the entity manager
	 */
	/**
	 * @return the entityManager
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * Sets the entity manager.
	 *
	 * @param entityManager
	 *            the entityManager to set
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.persistance.gc.boncommande.
	 * IBonCommandePersistance#getReferenceBCByClientId(java.lang.Long)
	 */
	@Override
	public List<CommandeAchatValue> getReferenceBCByClientId(Long clientId) {

		//System.out.println("idClient: " + clientId);

		List<CommandeAchatValue> resultat = new ArrayList<CommandeAchatValue>();

		// Set clientId on whereClause if not null
		if (clientId != null) {

			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();

			CriteriaQuery<CommandeAchatEntity> criteriaQuery = criteriaBuilder.createQuery(CommandeAchatEntity.class);
			List<Predicate> whereClause = new ArrayList<Predicate>();

			Root<CommandeAchatEntity> root = criteriaQuery.from(CommandeAchatEntity.class);

			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CLIENT), clientId));

			// criteriaQuery.multiselect(root.get(PREDICATE_REFERENCE)).where(whereClause.toArray(new
			// Predicate[] {}));
			criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
			List<CommandeAchatEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
			if (resultatEntite != null) {

				for (CommandeAchatEntity entity : resultatEntite) {

					resultat.add(bonCommandePersistanceUtilities.toValue(entity));
				}
			}
		}

		return resultat;
	}
	
	@Override
	public List<CommandeAchatValue> getProduitElementList(List<String> refBonCommandesList) {
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
	    CriteriaQuery<CommandeAchatEntity> criteriaQuery = criteriaBuilder.createQuery(CommandeAchatEntity.class);
	    List<Predicate> whereClause = new ArrayList<Predicate>();
	    Root<CommandeAchatEntity> root = criteriaQuery.from(CommandeAchatEntity.class);
	    
	    whereClause.add(root.get(PREDICATE_REFERENCE).in(refBonCommandesList));
		
	    criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    
	    List<CommandeAchatEntity> entityListResult = this.entityManager.createQuery(criteriaQuery).getResultList();

	    List<CommandeAchatValue> listCommande = new ArrayList<CommandeAchatValue>();
	    
	    for (CommandeAchatEntity entity : entityListResult) {
	    	CommandeAchatValue value = BonCommandeAchatPersistanceUtilities.toValue(entity);
	    	listCommande.add(value);
	    	
	    }
	    
	    return listCommande;
	}

}
