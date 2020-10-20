package com.gpro.consulting.tiers.logistique.persistance.gs.impl;

import java.util.ArrayList;
import java.util.Calendar;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.logistique.coordination.atelier.IConstanteLogistique;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.BonStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.RechercheMulticritereBonStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.ResultatRechecheBonStockValue;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.IBonLivraisonPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gs.IBonStockPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gs.entite.BonStockEntity;
import com.gpro.consulting.tiers.logistique.persistance.gs.utilities.PersistanceUtilitiesGs;

/**
 * Implementation of {@link IBonLivraisonPersistance} interface.
 *  
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */

@Component
public class BonStockPersistanceImpl extends AbstractPersistance implements IBonStockPersistance{
	
	private static final Logger logger = LoggerFactory.getLogger(BonStockPersistanceImpl.class);
	
	
	private String PREDICATE_DECLARE = "declare";
	
	private String PREDICATE_REFERENCE = "reference";
	private String PREDICATE_REFEXTERNE = "refexterne";
	private String PREDICATE_STOCK = "stock";
	private String PREDICATE_CLIENT = "partieIntId";
	private String PREDICATE_GROUPE_CLIENT = "groupeClientId";
	private String PREDICATE_DATE= "date";
//	private String PREDICATE_AVEC_FACTURE= "date";
	private String PREDICATE_METRAGE = "metrageTotal";
	private String PREDICATE_ETAT = "etat";
	private String PREDICATE_PRIX = "montantTTC";
	private String PREDICATE_INFO_SORTIE = "infoSortie";
	private String PREDICATE_MARCHE = "marcheId";
	private String PREDICATE_REGION = "regionId";
	private String PREDICATE_NATURELIVRAISON = "natureLivraison";
	private String PREDICATE_INFO_LIVRAISON = "infoLivraison";
	private String PREDICATE_IDDEPOT = "idDepot";
	
	
	private String PREDICATE_REFERENCE_BON_COMMANDE = "refCommande";
	
	
	private String PERCENT = "%";
	
	private int FIRST_INDEX = 0;
	
	private int MAX_RESULTS = 52;

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private PersistanceUtilitiesGs persistanceUtilitiesGs;


	
	
	@Override
	public ResultatRechecheBonStockValue rechercherMultiCritere(
			RechercheMulticritereBonStockValue request) {
		
		//logger.info("rechercherMultiCritere");
		//System.out.println("rech multi avec stock"+request.getStock());
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		
	//	CriteriaQuery<BonStockEntity> criteriaQuery = criteriaBuilder.createQuery(BonStockEntity.class);
		CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
		
		List<Predicate> whereClause = new ArrayList<Predicate>();
		
		Root<BonStockEntity> root = criteriaQuery.from(BonStockEntity.class);
		
		// Set request.referenceBl on whereClause if not empty or null
		if (estNonVide(request.getReferenceBl())) {
			Expression<String> path = root.get(PREDICATE_REFERENCE);
			Expression<String> upper =criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getReferenceBl().toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));
		}
		
		//REcherche REF.EXTERNE
		if (estNonVide(request.getRefexterne())) {
			Expression<String> path = root.get(PREDICATE_REFEXTERNE);
			Expression<String> upper =criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getRefexterne().toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));
		}
		
		// Set request.idDepot on whereClause if not null
		if (request.getIdDepot() != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_IDDEPOT), request.getIdDepot()));
		}
		
		//System.out.println(" rech stock: "+request.getStock());
		//Recherche SELON STOCK:boolean
		if (request.getStock() != null) {
			//System.out.println("###### where clause stock: "+request.getStock());
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_STOCK), request.getStock()));
		}
		
		if (estNonVide(request.getDeclare())) {
			Expression<Boolean> expression = root.get(PREDICATE_DECLARE);
			switch (request.getDeclare()) {
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
		}
		
		
		// Set request.partieIntId on whereClause if not null
		if (request.getPartieIntId() != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CLIENT), request.getPartieIntId()));
		}
		
		// Set request.dateLivraisonMin on whereClause if not null
	    if (request.getDateIntroductionMin() != null) {
	    	
	    	//logger.info("getDateLivraisonMin : ",new SimpleDateFormat("dd/MM/yyyy").format(request.getDateLivraisonMin().getTime()) );
	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE), request.getDateIntroductionMin()));
	    }
	    
		// Set request.dateLivraisonMax on whereClause if not null
	    if (request.getDateIntroductionMax() != null) {
	    	
	    	//logger.info("getDateLivraisonMax : ",new SimpleDateFormat("dd/MM/yyyy").format(request.getDateLivraisonMax().getTime()));
	    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE), request.getDateIntroductionMax()));
	    }
	    
		// Set request.metrageMin on whereClause if not null
	    if (request.getMetrageMin() != null) {
	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Double>get(PREDICATE_METRAGE), request.getMetrageMin()));
	    }
	    
		// Set request.metrageMax on whereClause if not null
	    if (request.getMetrageMax() != null) {
	    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Double>get(PREDICATE_METRAGE), request.getMetrageMax()));
	    }
	    
		// Set request.etat on whereClause if not empty or null
		if (estNonVide(request.getEtat())) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_ETAT), request.getEtat()));
		}
		
		// Set request.prixMin on whereClause if not null
	    if (request.getPrixMin() != null) {
	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Double>get(PREDICATE_PRIX), request.getPrixMin()));
	    }
	    
		// Set request.prixMax on whereClause if not null
	    if (request.getPrixMax() != null) {
	    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Double>get(PREDICATE_PRIX), request.getPrixMax()));
	    }
	    
		// Set request.referenceBs on whereClause if not empty or null
		if (estNonVide(request.getReferenceBs())) {
			Expression<String> path = root.get(PREDICATE_INFO_SORTIE);
			Expression<String> upper =criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getReferenceBs().toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));
		}
		
		// Set request.marcheId on whereClause if not null
		if (request.getMarcheId() != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_MARCHE), request.getMarcheId()));
		}
		
		
		   if (request.getGroupeClientId() != null) {
			   whereClause.add(criteriaBuilder.equal(root.get("groupeClientId"),
					   request.getGroupeClientId()));
		      }
		   
		   
		   
			// refAvoirRetour
			if (estNonVide(request.getRefAvoirRetour())) {
				whereClause.add(criteriaBuilder.equal(root.get("refAvoirRetour"), request.getRefAvoirRetour()));
			}
			
			// refFacture
			if (estNonVide(request.getRefFacture())) {
				whereClause.add(criteriaBuilder.equal(root.get("refFacture"), request.getRefFacture()));
			}
			
			// refBr
						if (estNonVide(request.getRefBR())) {
							whereClause.add(criteriaBuilder.equal(root.get("refBR"), request.getRefBR()));
						}
					   
		   
		   
		   
		
		// Set request.getNatureLivraison on whereClause if not null
//		if (estNonVide(request.getNatureLivraison())) {
//			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_NATURELIVRAISON), request.getNatureLivraison()));
//		}
		
			List<Object[]> resultatEntite = null;
		
	 //   criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
			
			
			criteriaQuery.select(criteriaBuilder.array(
		 			
		 			root.get("id").as(Long.class),
		 			root.get("partieIntId").as(Long.class),
		 			root.get("reference").as(String.class),
		 			 root.get("date").as(Calendar.class),	 			 
		 			root.get("montantHTaxe").as(Double.class),
		 			
		 			root.get("montantTTC").as(Double.class),
		 			root.get("montantTaxe").as(Double.class),
		 			root.get("montantRemise").as(Double.class),
		 			root.get("observations").as(String.class),
		 	
		 			root.get("metrageTotal").as(Double.class),
		 			root.get("etat").as(String.class),
		 			root.get("natureLivraison").as(String.class),
		 			root.get("modepaiementId").as(Long.class),	
		 	
		 			root.get("totalPourcentageRemise").as(Double.class),	
		 			root.get("typePartieInteressee").as(Long.class),
		 			root.get("groupeClientId").as(Long.class),
		 			root.get("idDepot").as(Long.class),
		 			root.get("dateConcernee").as(Calendar.class),
		 			
		 			root.get("type").as(String.class),
		 			root.get("refAvoirRetour").as(String.class),
		 			root.get("refBR").as(String.class),
		 			root.get("refFacture").as(String.class)
		 			
		 			
		 			
					)).where(whereClause.toArray(new Predicate[] {}));
			
			
			
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));

		// If criterias are empty
		if (request.isOptimized()) {
			resultatEntite = this.entityManager.createQuery(criteriaQuery).setMaxResults(MAX_RESULTS).getResultList();

		} else {
			resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
		}
		
		
	

	    List<BonStockValue> list = new ArrayList<BonStockValue>();
	    
	    for (Object[] element : resultatEntite) {
	    	
	    	BonStockEntity entity = new BonStockEntity();
	    	
	    	
	    	entity.setId((Long) element[0]);
	    	entity.setPartieIntId((Long) element[1]);
	    	entity.setReference((String) element[2]);
	    	entity.setDate((Calendar) element[3]);
	    	entity.setMontantHTaxe((Double) element[4]);
	    	entity.setMontantTTC((Double) element[5]);
	    	entity.setMontantTaxe((Double) element[6]);
	    	entity.setMontantRemise((Double) element[7]);
	    	entity.setObservations((String) element[8]);
	    	
	    	entity.setMetrageTotal((Double) element[9]);
	    	entity.setEtat((String) element[10]);
	    	entity.setNatureLivraison((String) element[11]);
	    	entity.setModepaiementId((Long) element[12]);

	    	entity.setTotalPourcentageRemise((Double) element[13]);
	    	entity.setTypePartieInteressee((Long) element[14]);
	    	entity.setGroupeClientId((Long) element[15]);
	    	
	    	entity.setIdDepot((Long) element[16]);
	    	
	    	entity.setDateConcernee((Calendar) element[17]);
	    	
	    	entity.setType((String) element[18]);
	    	entity.setRefAvoirRetour((String) element[19]);
	    	entity.setRefBR((String) element[20]);
	    	entity.setRefFacture((String) element[21]);
	    	
	    	
	    	BonStockValue dto = persistanceUtilitiesGs.toValue(entity);
	    	list.add(dto);
	    }
	    
		/*
	 	if(estNonVide(request.getAvecFacture())){
	 			
	 		List<BonStockValue> tempList = new ArrayList<BonStockValue>();
	 		
	 	// Livraison facturée
	 		
			if(request.getAvecFacture().equals("ouiFacture")){
	 				
				for (BonStockValue livraisonVenteValue : list) {
					if(facturePersistance.existeBL(livraisonVenteValue.getReference())){
						tempList.add(livraisonVenteValue);
					}
				}
				
 			}
			// Livraison non facturée
			else if(request.getAvecFacture().equals("nonFacture")){
				
				for (BonStockValue livraisonVenteValue : list) {
					if(!facturePersistance.existeBL(livraisonVenteValue.getReference())){
						tempList.add(livraisonVenteValue);
					}
				}
			}
			
			list.clear();
			list=tempList;
	 			
	 	}*/

	    ResultatRechecheBonStockValue resultat = new ResultatRechecheBonStockValue();
	    Collections.sort(list);
	    resultat.setNombreResultaRechercher(Long.valueOf(list.size()));
	    resultat.setList(new TreeSet<>(list));

	    return resultat;
	}
	


	@Override
	public String createBonStock(BonStockValue bonLivraisonValue) {
		
		BonStockEntity entity = (BonStockEntity) this.creer(persistanceUtilitiesGs.toEntity(bonLivraisonValue));

	    return entity.getId().toString();
	}


	@Override
	public BonStockValue getBonStockById(Long id) {
		
		BonStockEntity bonLivraisonEntity = this.rechercherParId(id, BonStockEntity.class);
	    return persistanceUtilitiesGs.toValue(bonLivraisonEntity);
	}


	@Override
	public String updateBonStock(BonStockValue bonLivraisonValue) {
		
		BonStockEntity entity = (BonStockEntity) this.modifier(persistanceUtilitiesGs.toEntity(bonLivraisonValue));

	    return entity.getId().toString();
	}


	@Override
	public void deleteBonStock(Long id) {
		
		this.supprimerEntite(BonStockEntity.class, id);
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
	public List<BonStockValue> getProduitElementList(List<String> refBonStockList) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
	    CriteriaQuery<BonStockEntity> criteriaQuery = criteriaBuilder.createQuery(BonStockEntity.class);
	    List<Predicate> whereClause = new ArrayList<Predicate>();
	    Root<BonStockEntity> root = criteriaQuery.from(BonStockEntity.class);
	    
	    whereClause.add(root.get(PREDICATE_REFERENCE).in(refBonStockList));
		
	    criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    
	    List<BonStockEntity> entityListResult = this.entityManager.createQuery(criteriaQuery).getResultList();

	    List<BonStockValue> listDetLivraison = new ArrayList<BonStockValue>();
	    
	    for (BonStockEntity entity : entityListResult) {
	    	BonStockValue value = persistanceUtilitiesGs.toValue(entity);
	    	listDetLivraison.add(value);
	    	
	    }
	    
	    return listDetLivraison;
	    
	}

	@Override
	public BonStockValue getByReference(String reference) {

		BonStockValue resultat = null;

		// Set reference on whereClause if not empty or null
		if (estNonVide(reference)) {
			
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			
			CriteriaQuery<BonStockEntity> criteriaQuery = criteriaBuilder.createQuery(BonStockEntity.class);
			List<Predicate> whereClause = new ArrayList<Predicate>();
			
			Root<BonStockEntity> root = criteriaQuery.from(BonStockEntity.class);
			
			Expression<String> path = root.get(PREDICATE_REFERENCE);
			Expression<String> upper =criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper, PERCENT + reference.toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));

			criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		    List <BonStockEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
		    
		    if(resultatEntite != null)
		    	if(resultatEntite.size() > 0)
		    		resultat = persistanceUtilitiesGs.toValue(resultatEntite.get(FIRST_INDEX));
		    	
		}

	    return resultat;
	}

	@Override
	public List<BonStockValue> getByClientId(Long clientId) {
		
		List<BonStockValue> resultat = new ArrayList<BonStockValue>();

		// Set clientId on whereClause if not null
		if (clientId != null) {
			
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			
			CriteriaQuery<BonStockEntity> criteriaQuery = criteriaBuilder.createQuery(BonStockEntity.class);
			List<Predicate> whereClause = new ArrayList<Predicate>();
			
			Root<BonStockEntity> root = criteriaQuery.from(BonStockEntity.class);
			
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CLIENT), clientId));

			criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		    List <BonStockEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
		    
		    if(resultatEntite != null){
		    	
		    	for(BonStockEntity entity : resultatEntite){
		    		
		    		resultat.add(persistanceUtilitiesGs.toValue(entity));
		    	}
		    }
		}

	    return resultat;
	}
	



	
	@Override
	public List<BonStockValue> getTraitementFaconElementList(List<String> refBonStockList) {
		
		// Même traitement que la méthode getProduitElementList 
	    return this.getProduitElementList(refBonStockList);
	    
	}




}
