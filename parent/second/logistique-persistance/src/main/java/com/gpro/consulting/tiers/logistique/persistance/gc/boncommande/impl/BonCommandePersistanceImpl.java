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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.logistique.coordination.atelier.IConstanteLogistique;
import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.CommandeVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.RechercheMulticritereBonCommandeValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.ResultatRechecheBonCommandeValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.LivraisonVenteValue;
import com.gpro.consulting.tiers.logistique.persistance.gc.boncommande.IBonCommandePersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.boncommande.entitie.CommandeVenteEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.boncommande.entitie.ProduitCommandeEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.boncommande.utilities.BonCommandePersistanceUtilities;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.IBonLivraisonPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie.LivraisonVenteEntity;

/**
 * @author Zeineb Medimagh
 * @since 16/11/2016 *
 */

@Component
public class BonCommandePersistanceImpl extends AbstractPersistance implements IBonCommandePersistance {

	private static final Logger logger = LoggerFactory.getLogger(BonCommandePersistanceImpl.class);
	
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
	
	private int FIRST_INDEX = 0;
	private String devise = "devise";
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private BonCommandePersistanceUtilities bonCommandePersistanceUtilities;
	
	@Autowired
	private IBonLivraisonPersistance bonLivraisonPersistance;
	
	

	@Override
	public String create(CommandeVenteValue bonCommandeValue) {

		//logger.info("----- bonCommande create : persistance layer ----------");

		CommandeVenteEntity commandeVenteEntity = BonCommandePersistanceUtilities.toEntity(bonCommandeValue);
		CommandeVenteEntity entity = (CommandeVenteEntity) this.creer(commandeVenteEntity);

		return entity.getId().toString();
	}

	@Override
	public CommandeVenteValue getById(Long id) {
		CommandeVenteEntity commandeVenteEntity = this.rechercherParId(id, CommandeVenteEntity.class);
		return BonCommandePersistanceUtilities.toValue(commandeVenteEntity);
	}

	@Override
	public String update(CommandeVenteValue bonCommandeValue) {

		CommandeVenteEntity entity = (CommandeVenteEntity) this
				.modifier(BonCommandePersistanceUtilities.toEntity(bonCommandeValue));

		return entity.getId().toString();
	}

	@Override
	public void delete(Long id) {

		this.supprimerEntite(CommandeVenteEntity.class, id);
	}

	@Override
	public List<CommandeVenteValue> getAll() {

		List<CommandeVenteEntity> listEntity = this.lister(CommandeVenteEntity.class);

		List<CommandeVenteValue> finalList = new ArrayList<CommandeVenteValue>();
		for (CommandeVenteEntity commandeVenteEntity : listEntity) {
			finalList.add(BonCommandePersistanceUtilities.toValue(commandeVenteEntity));
		}

		return finalList;
	}

	@Override
	public ResultatRechecheBonCommandeValue rechercherMultiCritere(RechercheMulticritereBonCommandeValue request) {

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<CommandeVenteEntity> criteriaQuery = criteriaBuilder.createQuery(CommandeVenteEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();

		Root<CommandeVenteEntity> root = criteriaQuery.from(CommandeVenteEntity.class);
		
		
		if (request.getBoutiqueId() != null) {
			whereClause.add(criteriaBuilder.equal(root.get(boutiqueId), request.getBoutiqueId()));
		}

		
		/* livre */
		
		/*if (estNonVide(request.getLivre())) {
			Expression<Boolean> expression = root.get(PREDICATE_LIVRE);
			switch (request.getLivre()) {
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
			Join<CommandeVenteEntity, ProduitCommandeEntity> jointureCmdEnPrdCmd = root
					.join(PREDICATE_PRODUIT_COMMANDES);
			whereClause.add(criteriaBuilder.equal(jointureCmdEnPrdCmd.get(PREDICATE_PRODUIT).get(PREDICATE_ID),
					request.getIdProduitParDesignation()));
		}

		/* Réf Produit */
		if ((request.getIdProduitParRef()) != null) {
			Join<CommandeVenteEntity, ProduitCommandeEntity> jointureCmdEnPrdCmd = root
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

		if (request.getDevise()!= null) {
			whereClause.add(criteriaBuilder.equal(root.get(devise), request.getDevise()));
		}
 
		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		List<CommandeVenteEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

		List<CommandeVenteValue> listCommandeVenteValue = new ArrayList<CommandeVenteValue>();

		for (CommandeVenteEntity entity : resultatEntite) {

			CommandeVenteValue dto = BonCommandePersistanceUtilities.toValue(entity);
			Collections.sort(dto.getListProduitCommandes());
			listCommandeVenteValue.add(dto);
		}

		
		
		
	 	if(estNonVide(request.getLivre())){
 			
	 		List<CommandeVenteValue> tempList = new ArrayList<CommandeVenteValue>();
	 		
	 	// Reception facturée
	 		
			if(request.getLivre().equals(IConstanteLogistique.YES)){
	 				
				for (CommandeVenteValue recepValue : listCommandeVenteValue) {
					if(bonLivraisonPersistance.existeBC(recepValue.getReference())){
						tempList.add(recepValue);
					}
				}
				
 			}
			// Reception non facturée
			else if(request.getLivre().equals(IConstanteLogistique.NO)){
				
				for (CommandeVenteValue recepValue : listCommandeVenteValue) {
					if(!bonLivraisonPersistance.existeBC(recepValue.getReference())){
						tempList.add(recepValue);
					}
				}
			}
			
			listCommandeVenteValue.clear();
			listCommandeVenteValue=tempList;
	 			
	 	}
		
		
		
		
		
		
		
		
	 	Collections.sort(listCommandeVenteValue);
		

		ResultatRechecheBonCommandeValue resultat = new ResultatRechecheBonCommandeValue();

		resultat.setNombreResultaRechercher(Long.valueOf(listCommandeVenteValue.size()));

		resultat.setListCommandeVente(listCommandeVenteValue);

		return resultat;
	}

	private boolean estNonVide(String val) {
		return val != null && !"".equals(val);

	}

	/*****************************
	 * Getter & Setter
	 ********************************/
	/**
	 * @return the entityManager
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * @param entityManager
	 *            the entityManager to set
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<CommandeVenteValue> getReferenceBCByClientId(Long clientId) {

		//System.out.println("idClient: " + clientId);

		List<CommandeVenteValue> resultat = new ArrayList<CommandeVenteValue>();

		// Set clientId on whereClause if not null
		if (clientId != null) {

			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();

			CriteriaQuery<CommandeVenteEntity> criteriaQuery = criteriaBuilder.createQuery(CommandeVenteEntity.class);
			List<Predicate> whereClause = new ArrayList<Predicate>();

			Root<CommandeVenteEntity> root = criteriaQuery.from(CommandeVenteEntity.class);

			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CLIENT), clientId));

			// criteriaQuery.multiselect(root.get(PREDICATE_REFERENCE)).where(whereClause.toArray(new
			// Predicate[] {}));
			criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
			List<CommandeVenteEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
			if (resultatEntite != null) {

				for (CommandeVenteEntity entity : resultatEntite) {

					resultat.add(bonCommandePersistanceUtilities.toValue(entity));
				}
			}
		}

		return resultat;
	}

	@Override
	public CommandeVenteValue getByReference(String reference) {
		CommandeVenteValue resultat = null;

		// Set reference on whereClause if not empty or null
		if (estNonVide(reference)) {
			
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			
			CriteriaQuery<CommandeVenteEntity> criteriaQuery = criteriaBuilder.createQuery(CommandeVenteEntity.class);
			List<Predicate> whereClause = new ArrayList<Predicate>();
			
			Root<CommandeVenteEntity> root = criteriaQuery.from(CommandeVenteEntity.class);
			
			/*Expression<String> path = root.get(PREDICATE_REFERENCE);
			Expression<String> upper =criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper, PERCENT + reference.toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));
			*/
			
			
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_REFERENCE), reference));
			
			

			criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		    List <CommandeVenteEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
		    
		    if(resultatEntite != null)
		    	if(resultatEntite.size() > 0)
		    		resultat = bonCommandePersistanceUtilities.toValue(resultatEntite.get(FIRST_INDEX));
		    	
		}

	    return resultat;
	}

	@Override
	public List<CommandeVenteValue> getByIdReglement(Long reglementId) {
		
		List<CommandeVenteValue> listCommandeVenteValue = new ArrayList<CommandeVenteValue>();
		
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<CommandeVenteEntity> criteriaQuery = criteriaBuilder.createQuery(CommandeVenteEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();

		Root<CommandeVenteEntity> root = criteriaQuery.from(CommandeVenteEntity.class);
		
	

		if (reglementId != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_REGLEMENT_ID), reglementId));
			
			
			
			
			

			criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
			List<CommandeVenteEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

		

			for (CommandeVenteEntity entity : resultatEntite) {

				CommandeVenteValue dto = BonCommandePersistanceUtilities.toValue(entity);
				Collections.sort(dto.getListProduitCommandes());
				listCommandeVenteValue.add(dto);
			}

			
			
			
		}
		
		
		return listCommandeVenteValue;

	

	}

	@Override
	public List<CommandeVenteValue> getProduitElementList(List<String> refBonCommandesList) {
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
	    CriteriaQuery<CommandeVenteEntity> criteriaQuery = criteriaBuilder.createQuery(CommandeVenteEntity.class);
	    List<Predicate> whereClause = new ArrayList<Predicate>();
	    Root<CommandeVenteEntity> root = criteriaQuery.from(CommandeVenteEntity.class);
	    
	    whereClause.add(root.get(PREDICATE_REFERENCE).in(refBonCommandesList));
		
	    criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    
	    List<CommandeVenteEntity> entityListResult = this.entityManager.createQuery(criteriaQuery).getResultList();

	    List<CommandeVenteValue> listCommandeVente = new ArrayList<CommandeVenteValue>();
	    
	    for (CommandeVenteEntity entity : entityListResult) {
	    	CommandeVenteValue value = BonCommandePersistanceUtilities.toValue(entity);
	    	listCommandeVente.add(value);
	    	
	    }
	    
	    return listCommandeVente;
	}

}
