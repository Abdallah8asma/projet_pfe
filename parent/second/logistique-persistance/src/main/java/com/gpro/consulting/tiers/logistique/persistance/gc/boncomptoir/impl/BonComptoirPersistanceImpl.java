package com.gpro.consulting.tiers.logistique.persistance.gc.boncomptoir.impl;

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
import com.gpro.consulting.logistique.coordination.gc.boncomptoir.value.ComptoirVenteValue;
import com.gpro.consulting.logistique.coordination.gc.boncomptoir.value.RechercheMulticritereBonComptoirValue;
import com.gpro.consulting.logistique.coordination.gc.boncomptoir.value.ResultatRechecheBonComptoirValue;
import com.gpro.consulting.tiers.logistique.coordination.fn.report.reporting.RechercheMulticritereFnReportingtValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.vue.LivraisonVenteFactureVue;
import com.gpro.consulting.tiers.logistique.persistance.gc.boncomptoir.IBonComptoirPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.boncomptoir.entitie.ComptoirVenteEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.boncomptoir.utilities.BonComptoirPersistanceUtilities;
import com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture.IFacturePersistance;

/**
 * Implementation of {@link IBonComptoirPersistance} interface.
 *  
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */

@Component
public class BonComptoirPersistanceImpl extends AbstractPersistance implements IBonComptoirPersistance{
	
	private static final Logger logger = LoggerFactory.getLogger(BonComptoirPersistanceImpl.class);
	
	private String PREDICATE_REFERENCE = "reference";
	private String PREDICATE_REFEXTERNE = "refexterne";
	private String PREDICATE_STOCK = "stock";
	private String PREDICATE_CLIENT = "partieIntId";
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
	
	private String PERCENT = "%";
	
	private int FIRST_INDEX = 0;

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private BonComptoirPersistanceUtilities bonComptoirPersistanceUtilities;

	@Autowired
	private IFacturePersistance facturePersistance;
	
	@Override
	public ResultatRechecheBonComptoirValue rechercherMultiCritere(
			RechercheMulticritereBonComptoirValue request) {
		
		//logger.info("rechercherMultiCritere");
	//	System.out.println("rech multi avec stock"+request.getStock());
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<ComptoirVenteEntity> criteriaQuery = criteriaBuilder.createQuery(ComptoirVenteEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		
		Root<ComptoirVenteEntity> root = criteriaQuery.from(ComptoirVenteEntity.class);
		
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
		//	System.out.println("###### where clause stock: "+request.getStock());
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_STOCK), request.getStock()));
		}
		
		
		// Set request.partieIntId on whereClause if not null
		if (request.getPartieIntId() != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CLIENT), request.getPartieIntId()));
		}
		
		// Set request.dateLivraisonMin on whereClause if not null
	    if (request.getDateLivraisonMin() != null) {
	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE), request.getDateLivraisonMin()));
	    }
	    
		// Set request.dateLivraisonMax on whereClause if not null
	    if (request.getDateLivraisonMax() != null) {
	    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE), request.getDateLivraisonMax()));
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
		
		// Set request.getNatureLivraison on whereClause if not null
//		if (estNonVide(request.getNatureLivraison())) {
//			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_NATURELIVRAISON), request.getNatureLivraison()));
//		}
		
		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    List <ComptoirVenteEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

	    List<ComptoirVenteValue> list = new ArrayList<ComptoirVenteValue>();
	    
	    for (ComptoirVenteEntity entity : resultatEntite) {
	    	ComptoirVenteValue dto = bonComptoirPersistanceUtilities.toValue(entity);
	    	list.add(dto);
	    }
	    
		
	 	if(estNonVide(request.getAvecFacture())){
	 			
	 		List<ComptoirVenteValue> tempList = new ArrayList<ComptoirVenteValue>();
	 		
	 	// Livraison facturée
	 		
			if(request.getAvecFacture().equals("ouiFacture")){
	 				
				for (ComptoirVenteValue livraisonVenteValue : list) {
					if(facturePersistance.existeBL(livraisonVenteValue.getReference())){
						tempList.add(livraisonVenteValue);
					}
				}
				
 			}
			// Livraison non facturée
			else if(request.getAvecFacture().equals("nonFacture")){
				
				for (ComptoirVenteValue livraisonVenteValue : list) {
					if(!facturePersistance.existeBL(livraisonVenteValue.getReference())){
						tempList.add(livraisonVenteValue);
					}
				}
			}
			
			list.clear();
			list=tempList;
	 			
	 	}

	    ResultatRechecheBonComptoirValue resultat = new ResultatRechecheBonComptoirValue();
	    Collections.sort(list);
	    resultat.setNombreResultaRechercher(Long.valueOf(list.size()));
	    resultat.setList(new TreeSet<>(list));

	    return resultat;
	}

	@Override
	public String createBonComptoir(ComptoirVenteValue bonLivraisonValue) {
		
		ComptoirVenteEntity entity = (ComptoirVenteEntity) this.creer(bonComptoirPersistanceUtilities.toEntity(bonLivraisonValue));

	    return entity.getId().toString();
	}


	@Override
	public ComptoirVenteValue getBonComptoirById(Long id) {
		
		ComptoirVenteEntity bonLivraisonEntity = this.rechercherParId(id, ComptoirVenteEntity.class);
	    return bonComptoirPersistanceUtilities.toValue(bonLivraisonEntity);
	}


	@Override
	public String updateBonComptoir(ComptoirVenteValue bonLivraisonValue) {
		
		ComptoirVenteEntity entity = (ComptoirVenteEntity) this.modifier(bonComptoirPersistanceUtilities.toEntity(bonLivraisonValue));

	    return entity.getId().toString();
	}


	@Override
	public void deleteBonComptoir(Long id) {
		
		this.supprimerEntite(ComptoirVenteEntity.class, id);
	}

	@Override
	public List<ComptoirVenteValue> getAll() {

		List<ComptoirVenteEntity> listEntity = this.lister(ComptoirVenteEntity.class);
		
		return bonComptoirPersistanceUtilities.listComptoirVenteEntitytoValue(listEntity);
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
	public List<ComptoirVenteValue> getProduitElementList(List<String> refBonComptoirList) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
	    CriteriaQuery<ComptoirVenteEntity> criteriaQuery = criteriaBuilder.createQuery(ComptoirVenteEntity.class);
	    List<Predicate> whereClause = new ArrayList<Predicate>();
	    Root<ComptoirVenteEntity> root = criteriaQuery.from(ComptoirVenteEntity.class);
	    
	    whereClause.add(root.get(PREDICATE_REFERENCE).in(refBonComptoirList));
		
	    criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    
	    List<ComptoirVenteEntity> entityListResult = this.entityManager.createQuery(criteriaQuery).getResultList();

	    List<ComptoirVenteValue> listDetLivraison = new ArrayList<ComptoirVenteValue>();
	    
	    for (ComptoirVenteEntity entity : entityListResult) {
	    	ComptoirVenteValue value = bonComptoirPersistanceUtilities.toValue(entity);
	    	listDetLivraison.add(value);
	    	
	    }
	    
	    return listDetLivraison;
	    
	}

	@Override
	public ComptoirVenteValue getByReference(String reference) {

		ComptoirVenteValue resultat = null;

		// Set reference on whereClause if not empty or null
		if (estNonVide(reference)) {
			
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			
			CriteriaQuery<ComptoirVenteEntity> criteriaQuery = criteriaBuilder.createQuery(ComptoirVenteEntity.class);
			List<Predicate> whereClause = new ArrayList<Predicate>();
			
			Root<ComptoirVenteEntity> root = criteriaQuery.from(ComptoirVenteEntity.class);
			
			Expression<String> path = root.get(PREDICATE_REFERENCE);
			Expression<String> upper =criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper, PERCENT + reference.toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));

			criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		    List <ComptoirVenteEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
		    
		    if(resultatEntite != null)
		    	if(resultatEntite.size() > 0)
		    		resultat = bonComptoirPersistanceUtilities.toValue(resultatEntite.get(FIRST_INDEX));
		    	
		}

	    return resultat;
	}

	@Override
	public List<ComptoirVenteValue> getByClientId(Long clientId) {
		
		List<ComptoirVenteValue> resultat = new ArrayList<ComptoirVenteValue>();

		// Set clientId on whereClause if not null
		if (clientId != null) {
			
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			
			CriteriaQuery<ComptoirVenteEntity> criteriaQuery = criteriaBuilder.createQuery(ComptoirVenteEntity.class);
			List<Predicate> whereClause = new ArrayList<Predicate>();
			
			Root<ComptoirVenteEntity> root = criteriaQuery.from(ComptoirVenteEntity.class);
			
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CLIENT), clientId));

			criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		    List <ComptoirVenteEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
		    
		    if(resultatEntite != null){
		    	
		    	for(ComptoirVenteEntity entity : resultatEntite){
		    		
		    		resultat.add(bonComptoirPersistanceUtilities.toValue(entity));
		    	}
		    }
		}

	    return resultat;
	}
	


	
	@Override
	public List<ComptoirVenteValue> getTraitementFaconElementList(List<String> refBonComptoirList) {
		
		// Même traitement que la méthode getProduitElementList 
	    return this.getProduitElementList(refBonComptoirList);
	    
	}

//	@Override
//	public Double getSommeMontHT(RechercheMulticritereBonComptoirValue request) {
//		
//		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
//		
//		CriteriaQuery<Double> criteriaQuery = criteriaBuilder.createQuery(Double.class);
//		List<Predicate> whereClause = new ArrayList<Predicate>();
//		
//		Root<ComptoirVenteEntity> root = criteriaQuery.from(ComptoirVenteEntity.class);
//		
//		// Set request.partieIntId on whereClause if not null
//		if (request.getPartieIntId() != null) {
//			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CLIENT), request.getPartieIntId()));
//		}
//		
//		// Set request.dateLivraisonMin on whereClause if not null
//	    if (request.getDateLivraisonMin() != null) {
//	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE), request.getDateLivraisonMin()));
//	    }
//	    
//		// Set request.dateLivraisonMax on whereClause if not null
//	    if (request.getDateLivraisonMax() != null) {
//	    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE), request.getDateLivraisonMax()));
//	    }
//	    
//		criteriaQuery.select(criteriaBuilder.sum(root.get("montantHTaxe").as(Double.class))).where(whereClause.toArray(new Predicate[] {}));
//	    Double sommeMontHT = this.entityManager.createQuery(criteriaQuery).getSingleResult();
//	    
//	    if(sommeMontHT!=null){
//	    	return sommeMontHT;
//	    }
//	    
//	    return 0D;
//		
//	}
	
	//Hajer Amri 09/02/2017
	
	@Override
	public Double getSommeMontHT(RechercheMulticritereBonComptoirValue request) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<ComptoirVenteEntity> criteriaQuery = criteriaBuilder.createQuery(ComptoirVenteEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		
		Root<ComptoirVenteEntity> root = criteriaQuery.from(ComptoirVenteEntity.class);
		
		// Set request.partieIntId on whereClause if not null
		if (request.getPartieIntId() != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CLIENT), request.getPartieIntId()));
		}
		
		// Set request.dateLivraisonMin on whereClause if not null
	    if (request.getDateLivraisonMin() != null) {
	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE), request.getDateLivraisonMin()));
	    }
	    
		// Set request.dateLivraisonMax on whereClause if not null
	    if (request.getDateLivraisonMax() != null) {
	    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE), request.getDateLivraisonMax()));
	    }
	    
	    //Hajer Amri 09/02/2017
		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    List <ComptoirVenteEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

	    List<ComptoirVenteValue> list = new ArrayList<ComptoirVenteValue>();
	    
	    for (ComptoirVenteEntity entity : resultatEntite) {
	    	ComptoirVenteValue dto = bonComptoirPersistanceUtilities.toValue(entity);
	    	list.add(dto);
	    }
	    
	    Double sommeMontHT=0D;
	    
	 	if(estNonVide(request.getAvecFacture())){
	 			
	 	
			// Livraison non facturée
			 if(request.getAvecFacture().equals("nonFacture")){
				
				for (ComptoirVenteValue livraisonVenteValue : list) {
					if(!facturePersistance.existeBL(livraisonVenteValue.getReference())){
						
						sommeMontHT += livraisonVenteValue.getMontantTTC();
					}
				}
			}
			
	 			
	 	}
	    
	    /*****/
//		criteriaQuery.select(criteriaBuilder.sum(root.get("montantHTaxe").as(Double.class))).where(whereClause.toArray(new Predicate[] {}));
//	    Double sommeMontHT = this.entityManager.createQuery(criteriaQuery).getSingleResult();
	    
	    if(sommeMontHT!=null){
	    	return sommeMontHT;
	    }
	    
	    return 0D;
		
	}


	@Override
	public Double getSommeMontHTFactures(RechercheMulticritereBonComptoirValue request) {

	CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<ComptoirVenteEntity> criteriaQuery = criteriaBuilder.createQuery(ComptoirVenteEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		
		Root<ComptoirVenteEntity> root = criteriaQuery.from(ComptoirVenteEntity.class);

		// Set request.partieIntId on whereClause if not null
		if (request.getPartieIntId() != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CLIENT), request.getPartieIntId()));
		}
		
		// Set request.dateLivraisonMin on whereClause if not null
	    if (request.getDateLivraisonMin() != null) {
	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE), request.getDateLivraisonMin()));
	    }
	    
		// Set request.dateLivraisonMax on whereClause if not null
	    if (request.getDateLivraisonMax() != null) {
	    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE), request.getDateLivraisonMax()));
	    }
	    
		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    
		List <ComptoirVenteEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
	    
		Double montantBLFactures = 0D;
		
		for (ComptoirVenteEntity livraisonVenteEntity : resultatEntite) {
			
			if(facturePersistance.existeBL(livraisonVenteEntity.getReference())){
				montantBLFactures+=livraisonVenteEntity.getMontantHTaxe();
			}
		}
		
	    return montantBLFactures;
	
	}

	@Override
	public ResultatRechecheBonComptoirValue getLivraisonByFnReportingRegionDate(
			RechercheMulticritereFnReportingtValue request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LivraisonVenteFactureVue> getListBLByClientId(Long idClient) {
		// TODO Auto-generated method stub
		return null;
	}


	
	
	// Added By Ghazi 07/05/2018
	

	
	
	

}
