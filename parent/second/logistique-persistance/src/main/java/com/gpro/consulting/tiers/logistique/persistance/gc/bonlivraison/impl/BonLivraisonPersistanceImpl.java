package com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.impl;

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
import javax.persistence.criteria.Subquery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.entity.PartieInteresseEntite;
import com.gpro.consulting.tiers.logistique.coordination.atelier.IConstanteLogistique;
import com.gpro.consulting.tiers.logistique.coordination.fn.report.reporting.RechercheMulticritereFnReportingtValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.LivraisonVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.RechercheMulticritereBonLivraisonValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.ResultatRechecheBonLivraisonValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.vue.LivraisonVenteFactureVue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.vue.LivraisonVenteVue;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.IBonLivraisonPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie.LivraisonVenteEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.utilities.BonLivraisonPersistanceUtilities;
import com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture.IFacturePersistance;

/**
 * Implementation of {@link IBonLivraisonPersistance} interface.
 *  
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */

@Component
public class BonLivraisonPersistanceImpl extends AbstractPersistance implements IBonLivraisonPersistance{
	
	private static final Logger logger = LoggerFactory.getLogger(BonLivraisonPersistanceImpl.class);
	
	
	private String PREDICATE_transporteur = "transporteur";
	private String PREDICATE_numTelPassager = "numTelPassager";
	private String PREDICATE_emailPassager = "emailPassager";
	private String PREDICATE_adressePassager = "adressePassager";
	
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
	private String PREDICATE_BOUTIQUEID = "boutiqueId";
	private String PREDICATE_REGLEMENT_ID = "reglementId";
	
	
	
	private String PREDICATE_REFERENCE_BON_COMMANDE = "refCommande";
	
	
	private String PREDICATE_REFERENCE_BL_MANUEL = "referenceBlManuel";
	
	
	private String PERCENT = "%";
	
	private int FIRST_INDEX = 0;
	
	private int MAX_RESULTS = 52;
	
	private String devise = "devise";

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private BonLivraisonPersistanceUtilities bonLivraisonPersistanceUtilities;

	@Autowired
	private IFacturePersistance facturePersistance;
	
	
	@Override
	public ResultatRechecheBonLivraisonValue rechercherMultiCritere(
			RechercheMulticritereBonLivraisonValue request) {
		
		//logger.info("rechercherMultiCritere");
		//System.out.println("rech multi avec stock"+request.getStock());
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		
	//	CriteriaQuery<LivraisonVenteEntity> criteriaQuery = criteriaBuilder.createQuery(LivraisonVenteEntity.class);
		CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
		
		List<Predicate> whereClause = new ArrayList<Predicate>();
		
		Root<LivraisonVenteEntity> root = criteriaQuery.from(LivraisonVenteEntity.class);
		
		if(estNonVide(request.getBoutiqueId())) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_BOUTIQUEID), request.getBoutiqueId()));
		}
		
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
	    if (request.getDateLivraisonMin() != null) {
	    	
	    	//logger.info("getDateLivraisonMin : ",new SimpleDateFormat("dd/MM/yyyy").format(request.getDateLivraisonMin().getTime()) );
	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE), request.getDateLivraisonMin()));
	    }
	    
		// Set request.dateLivraisonMax on whereClause if not null
	    if (request.getDateLivraisonMax() != null) {
	    	
	    	//logger.info("getDateLivraisonMax : ",new SimpleDateFormat("dd/MM/yyyy").format(request.getDateLivraisonMax().getTime()));
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
		
		
		   if (request.getGroupeClientId() != null) {
			   whereClause.add(criteriaBuilder.equal(root.get("groupeClientId"),
					   request.getGroupeClientId()));
		      }
		   
		   
		// Set request.Transporteur
					if (estNonVide(request.getTransporteur())) {
						Expression<String> path = root.get(PREDICATE_transporteur);
						Expression<String> upper =criteriaBuilder.upper(path);
						Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getTransporteur().toUpperCase() + PERCENT);
						whereClause.add(criteriaBuilder.and(predicate));
					}
		   
		// Set request.AdressePassager 
			if (estNonVide(request.getAdressePassager())) {
				Expression<String> path = root.get(PREDICATE_adressePassager);
				Expression<String> upper =criteriaBuilder.upper(path);
				Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getAdressePassager().toUpperCase() + PERCENT);
				whereClause.add(criteriaBuilder.and(predicate));
			}
			
			// Set request.NumTelPassager 
			if (estNonVide(request.getNumTelPassager())) {
				Expression<String> path = root.get(PREDICATE_numTelPassager);
				Expression<String> upper =criteriaBuilder.upper(path);
				Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getNumTelPassager().toUpperCase() + PERCENT);
				whereClause.add(criteriaBuilder.and(predicate));
			}
			
			// Set request.EmailPassager 
			if (estNonVide(request.getEmailPassager())) {
				Expression<String> path = root.get(PREDICATE_emailPassager);
				Expression<String> upper =criteriaBuilder.upper(path);
				Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getEmailPassager().toUpperCase() + PERCENT);
				whereClause.add(criteriaBuilder.and(predicate));
			}
			if (request.getDevise()!= null) {
				whereClause.add(criteriaBuilder.equal(root.get(devise), request.getDevise()));
			}
			
			// Set request.referenceBl on whereClause if not empty or null
			if (estNonVide(request.getReferenceBlManuel())) {
				Expression<String> path = root.get(PREDICATE_REFERENCE_BL_MANUEL);
				Expression<String> upper =criteriaBuilder.upper(path);
				Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getReferenceBlManuel().toUpperCase() + PERCENT);
				whereClause.add(criteriaBuilder.and(predicate));
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
		 			root.get("devise").as(Long.class),
		 			root.get("montantConverti").as(Double.class),
		 			root.get("stock").as(Boolean.class),
		 			root.get("declare").as(Boolean.class),
		 			root.get("infoSortie").as(String.class),
		 			
		 			root.get("referenceBlManuel").as(String.class),
		 			
                   root.get("identifiantLivraison").as(String.class),
		 			
		 			root.get("refCommande").as(String.class)
		 			
		 			
		 			
		 			
					)).where(whereClause.toArray(new Predicate[] {}));
			
			
			
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));

		// If criterias are empty
		if (request.isOptimized()) {
			resultatEntite = this.entityManager.createQuery(criteriaQuery).setMaxResults(MAX_RESULTS).getResultList();

		} else {
			resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
		}
		
		
	

	    List<LivraisonVenteValue> list = new ArrayList<LivraisonVenteValue>();
	    
	    for (Object[] element : resultatEntite) {
	    	
	    	LivraisonVenteEntity entity = new LivraisonVenteEntity();
	    	
	    	
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
	    	
	    	entity.setDevise((Long) element[17]);
	    	entity.setMontantConverti((Double) element[18]);
	    	
	    	entity.setStock((Boolean) element[19]);
	    	entity.setDeclare((Boolean) element[20]);
	    	
	    	entity.setInfoSortie((String) element[21]);
	    	
	    	
	    	entity.setReferenceBlManuel((String) element[22]);
	    	entity.setIdentifiantLivraison((String) element[23]);
	    	entity.setRefCommande((String) element[24]);
	    
	    	
	    	////////////////////////////
	    	LivraisonVenteValue dto = bonLivraisonPersistanceUtilities.toValue(entity);
	    	list.add(dto);
	    }
	    
		
	 	if(estNonVide(request.getAvecFacture())){
	 			
	 		List<LivraisonVenteValue> tempList = new ArrayList<LivraisonVenteValue>();
	 		
	 	// Livraison facturée
	 		
			if(request.getAvecFacture().equals("ouiFacture")){
	 				
				for (LivraisonVenteValue livraisonVenteValue : list) {
					if(facturePersistance.existeBL(livraisonVenteValue.getReference())){
						tempList.add(livraisonVenteValue);
					}
				}
				
 			}
			// Livraison non facturée
			else if(request.getAvecFacture().equals("nonFacture")){
				
				for (LivraisonVenteValue livraisonVenteValue : list) {
					if(!facturePersistance.existeBL(livraisonVenteValue.getReference())){
						tempList.add(livraisonVenteValue);
					}
				}
			}
			
			list.clear();
			list=tempList;
	 			
	 	}

	    ResultatRechecheBonLivraisonValue resultat = new ResultatRechecheBonLivraisonValue();
	    Collections.sort(list);
	    resultat.setNombreResultaRechercher(Long.valueOf(list.size()));
	    resultat.setList(new TreeSet<>(list));

	    return resultat;
	}
	
@Override
	public ResultatRechecheBonLivraisonValue rechercherMultiCritereComplet(
			RechercheMulticritereBonLivraisonValue request) {
		
		//logger.info("rechercherMultiCritere");
		//System.out.println("rech multi avec stock"+request.getStock());
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<LivraisonVenteEntity> criteriaQuery = criteriaBuilder.createQuery(LivraisonVenteEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		
		Root<LivraisonVenteEntity> root = criteriaQuery.from(LivraisonVenteEntity.class);
		
		if(estNonVide(request.getBoutiqueId())) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_BOUTIQUEID), request.getBoutiqueId()));
		}
		
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
		
		
		// Set request.dateLivraisonon whereClause if not null
	    if (request.getDateLivraison() != null) {
	    	
	    	//logger.info("getDateLivraisonMin : ",new SimpleDateFormat("dd/MM/yyyy").format(request.getDateLivraisonMin().getTime()) );
	    	whereClause.add(criteriaBuilder.equal(root.<Calendar>get(PREDICATE_DATE), request.getDateLivraison()));
	    }
		
		// Set request.dateLivraisonMin on whereClause if not null
	    if (request.getDateLivraisonMin() != null) {
	    	
	    	//logger.info("getDateLivraisonMin : ",new SimpleDateFormat("dd/MM/yyyy").format(request.getDateLivraisonMin().getTime()) );
	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE), request.getDateLivraisonMin()));
	    }
	    
		// Set request.dateLivraisonMax on whereClause if not null
	    if (request.getDateLivraisonMax() != null) {
	    	
	    	//logger.info("getDateLivraisonMax : ",new SimpleDateFormat("dd/MM/yyyy").format(request.getDateLivraisonMax().getTime()));
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
		
		
		   if (request.getGroupeClientId() != null) {
			   whereClause.add(criteriaBuilder.equal(root.get("groupeClientId"),
					   request.getGroupeClientId()));
		      }
		
		// Set request.getNatureLivraison on whereClause if not null
//		if (estNonVide(request.getNatureLivraison())) {
//			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_NATURELIVRAISON), request.getNatureLivraison()));
//		}
		
		   List <LivraisonVenteEntity> resultatEntite = null;
		
	    criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));

		// If criterias are empty
		if (request.isOptimized()) {
			resultatEntite = this.entityManager.createQuery(criteriaQuery).setMaxResults(MAX_RESULTS).getResultList();

		} else {
			resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
		}
		
		
	

	    List<LivraisonVenteValue> list = new ArrayList<LivraisonVenteValue>();
	    
	    for (LivraisonVenteEntity entity : resultatEntite) {
	    	LivraisonVenteValue dto = bonLivraisonPersistanceUtilities.toValue(entity);
	    	list.add(dto);
	    }
	    
		
	 	if(estNonVide(request.getAvecFacture())){
	 			
	 		List<LivraisonVenteValue> tempList = new ArrayList<LivraisonVenteValue>();
	 		
	 	// Livraison facturée
	 		
			if(request.getAvecFacture().equals("ouiFacture")){
	 				
				for (LivraisonVenteValue livraisonVenteValue : list) {
					if(facturePersistance.existeBL(livraisonVenteValue.getReference())){
						tempList.add(livraisonVenteValue);
					}
				}
				
 			}
			// Livraison non facturée
			else if(request.getAvecFacture().equals("nonFacture")){
				
				for (LivraisonVenteValue livraisonVenteValue : list) {
					if(!facturePersistance.existeBL(livraisonVenteValue.getReference())){
						tempList.add(livraisonVenteValue);
					}
				}
			}
			
			list.clear();
			list=tempList;
	 			
	 	}

	    ResultatRechecheBonLivraisonValue resultat = new ResultatRechecheBonLivraisonValue();
	    Collections.sort(list);
	    resultat.setNombreResultaRechercher(Long.valueOf(list.size()));
	    resultat.setList(new TreeSet<>(list));

	    return resultat;
	}
	
	

	@Override
	public String createBonLivraison(LivraisonVenteValue bonLivraisonValue) {
		
		LivraisonVenteEntity entity = (LivraisonVenteEntity) this.creer(bonLivraisonPersistanceUtilities.toEntity(bonLivraisonValue));

	    return entity.getId().toString();
	}


	@Override
	public LivraisonVenteValue getBonLivraisonById(Long id) {
		
		LivraisonVenteEntity bonLivraisonEntity = this.rechercherParId(id, LivraisonVenteEntity.class);
	    return bonLivraisonPersistanceUtilities.toValue(bonLivraisonEntity);
	}


	@Override
	public String updateBonLivraison(LivraisonVenteValue bonLivraisonValue) {
		
		LivraisonVenteEntity entity = (LivraisonVenteEntity) this.modifier(bonLivraisonPersistanceUtilities.toEntity(bonLivraisonValue));

	    return entity.getId().toString();
	}


	@Override
	public void deleteBonLivraison(Long id) {
		
		this.supprimerEntite(LivraisonVenteEntity.class, id);
	}

	@Override
	public List<LivraisonVenteValue> getAll() {

		List<LivraisonVenteEntity> listEntity = this.lister(LivraisonVenteEntity.class);
		
		return bonLivraisonPersistanceUtilities.listLivraisonVenteEntitytoValue(listEntity);
	}
	
	
	private boolean estNonVide(String val) {
		return val != null && !"".equals(val) && !"undefined".equals(val) && !"null".equals(val);
	}
	private boolean estNonVide(Long val) {
		return val != null && !"".equals(val) && !"undefined".equals(val) && !"null".equals(val);
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<LivraisonVenteValue> getProduitElementList(List<String> refBonLivraisonList) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
	    CriteriaQuery<LivraisonVenteEntity> criteriaQuery = criteriaBuilder.createQuery(LivraisonVenteEntity.class);
	    List<Predicate> whereClause = new ArrayList<Predicate>();
	    Root<LivraisonVenteEntity> root = criteriaQuery.from(LivraisonVenteEntity.class);
	    
	    whereClause.add(root.get(PREDICATE_REFERENCE).in(refBonLivraisonList));
		
	    criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    
	    List<LivraisonVenteEntity> entityListResult = this.entityManager.createQuery(criteriaQuery).getResultList();

	    List<LivraisonVenteValue> listDetLivraison = new ArrayList<LivraisonVenteValue>();
	    
	    for (LivraisonVenteEntity entity : entityListResult) {
	    	LivraisonVenteValue value = bonLivraisonPersistanceUtilities.toValue(entity);
	    	listDetLivraison.add(value);
	    	
	    }
	    
	    return listDetLivraison;
	    
	}

	@Override
	public LivraisonVenteValue getByReference(String reference) {

		LivraisonVenteValue resultat = null;

		// Set reference on whereClause if not empty or null
		if (estNonVide(reference)) {
			
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			
			CriteriaQuery<LivraisonVenteEntity> criteriaQuery = criteriaBuilder.createQuery(LivraisonVenteEntity.class);
			List<Predicate> whereClause = new ArrayList<Predicate>();
			
			Root<LivraisonVenteEntity> root = criteriaQuery.from(LivraisonVenteEntity.class);
			
			/*Expression<String> path = root.get(PREDICATE_REFERENCE);
			Expression<String> upper =criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper, PERCENT + reference.toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));
			*/
			
			
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_REFERENCE), reference));
			
			

			criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		    List <LivraisonVenteEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
		    
		    if(resultatEntite != null)
		    	if(resultatEntite.size() > 0)
		    		resultat = bonLivraisonPersistanceUtilities.toValue(resultatEntite.get(FIRST_INDEX));
		    	
		}

	    return resultat;
	}

	@Override
	public List<LivraisonVenteValue> getByClientId(Long clientId) {
		
		List<LivraisonVenteValue> resultat = new ArrayList<LivraisonVenteValue>();

		// Set clientId on whereClause if not null
		if (clientId != null) {
			
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			
			CriteriaQuery<LivraisonVenteEntity> criteriaQuery = criteriaBuilder.createQuery(LivraisonVenteEntity.class);
			List<Predicate> whereClause = new ArrayList<Predicate>();
			
			Root<LivraisonVenteEntity> root = criteriaQuery.from(LivraisonVenteEntity.class);
			
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CLIENT), clientId));

			criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		    List <LivraisonVenteEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
		    
		    if(resultatEntite != null){
		    	
		    	for(LivraisonVenteEntity entity : resultatEntite){
		    		
		    		resultat.add(bonLivraisonPersistanceUtilities.toValue(entity));
		    	}
		    }
		}

	    return resultat;
	}
	
	@Override
	public List<LivraisonVenteVue> getReferenceBLByClientId(Long clientId) {
		
	List<LivraisonVenteVue> resultat = new ArrayList<LivraisonVenteVue>();

	// Set clientId on whereClause if not null
	if (clientId != null) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<LivraisonVenteEntity> criteriaQuery = criteriaBuilder.createQuery(LivraisonVenteEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		
		Root<LivraisonVenteEntity> root = criteriaQuery.from(LivraisonVenteEntity.class);
		
		whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CLIENT), clientId));

		criteriaQuery.multiselect(root.get("reference")).where(whereClause.toArray(new Predicate[] {}));
		
	    List <LivraisonVenteEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
	    if(resultatEntite != null){
	    	
	    	for(LivraisonVenteEntity entity : resultatEntite){
	    		
	    		resultat.add(bonLivraisonPersistanceUtilities.toVue(entity));
	    	}
	    }
	}

    return resultat;
	}

	@Override
	public ResultatRechecheBonLivraisonValue getLivraisonByFnReportingRegionDate(
			RechercheMulticritereFnReportingtValue request) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<LivraisonVenteEntity> criteriaQuery = criteriaBuilder.createQuery(LivraisonVenteEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		
		Root<LivraisonVenteEntity> root = criteriaQuery.from(LivraisonVenteEntity.class);

		// Set regionId on whereClause if not empty or null
		if (request.getRegionId() != null) {
			Subquery<Long> subQuery = criteriaQuery.subquery(Long.class);
			Root<PartieInteresseEntite> subRoot = subQuery.from(PartieInteresseEntite.class);
			
			subQuery.select(subRoot.<Long>get("id"));
			subQuery.where(criteriaBuilder.equal(subRoot.get(PREDICATE_REGION), request.getRegionId()));
			whereClause.add(criteriaBuilder.in(root.get(PREDICATE_CLIENT)).value(subQuery));
		}
		
		// Set request.dateLivraisonMin on whereClause if not null
	    if (request.getDateMin() != null) {
		  	  
	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE), request.getDateMin()));
	    }
	    
		// Set request.dateLivraisonMax on whereClause if not null
	    if (request.getDateMax() != null) {
		  	  
	    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE), request.getDateMax()));
	    }

		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    List <LivraisonVenteEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

	    List<LivraisonVenteValue> list = new ArrayList<LivraisonVenteValue>();
	    
	    for (LivraisonVenteEntity entity : resultatEntite) {
	    	LivraisonVenteValue dto = bonLivraisonPersistanceUtilities.toValue(entity);
	    	list.add(dto);
	    }

	    ResultatRechecheBonLivraisonValue resultat = new ResultatRechecheBonLivraisonValue();
	    resultat.setNombreResultaRechercher(Long.valueOf(list.size()));
	    resultat.setList(new TreeSet<>(list));

	    return resultat;
	}
	
	@Override
	public List<LivraisonVenteValue> getTraitementFaconElementList(List<String> refBonLivraisonList) {
		
		// Même traitement que la méthode getProduitElementList 
	    return this.getProduitElementList(refBonLivraisonList);
	    
	}

//	@Override
//	public Double getSommeMontHT(RechercheMulticritereBonLivraisonValue request) {
//		
//		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
//		
//		CriteriaQuery<Double> criteriaQuery = criteriaBuilder.createQuery(Double.class);
//		List<Predicate> whereClause = new ArrayList<Predicate>();
//		
//		Root<LivraisonVenteEntity> root = criteriaQuery.from(LivraisonVenteEntity.class);
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
	public Double getSommeMontHT(RechercheMulticritereBonLivraisonValue request) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<LivraisonVenteEntity> criteriaQuery = criteriaBuilder.createQuery(LivraisonVenteEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		
		Root<LivraisonVenteEntity> root = criteriaQuery.from(LivraisonVenteEntity.class);
		
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
	    List <LivraisonVenteEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

	    List<LivraisonVenteValue> list = new ArrayList<LivraisonVenteValue>();
	    
	    for (LivraisonVenteEntity entity : resultatEntite) {
	    	LivraisonVenteValue dto = bonLivraisonPersistanceUtilities.toValue(entity);
	    	list.add(dto);
	    }
	    
	    Double sommeMontHT=0D;
	    
	 	if(estNonVide(request.getAvecFacture())){
	 			
	 	
			// Livraison non facturée
			 if(request.getAvecFacture().equals("nonFacture")){
				
				for (LivraisonVenteValue livraisonVenteValue : list) {
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
	public Double getSommeMontHTFactures(RechercheMulticritereBonLivraisonValue request) {

	CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<LivraisonVenteEntity> criteriaQuery = criteriaBuilder.createQuery(LivraisonVenteEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		
		Root<LivraisonVenteEntity> root = criteriaQuery.from(LivraisonVenteEntity.class);

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
	    
		List <LivraisonVenteEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
	    
		Double montantBLFactures = 0D;
		
		for (LivraisonVenteEntity livraisonVenteEntity : resultatEntite) {
			
			if(facturePersistance.existeBL(livraisonVenteEntity.getReference())){
				montantBLFactures+=livraisonVenteEntity.getMontantHTaxe();
			}
		}
		
	    return montantBLFactures;
	
	}
	
	
	// Added By Ghazi 07/05/2018
	
	@Override
	public List<LivraisonVenteFactureVue> getListBLByClientId(Long clientId) {
		
	List<LivraisonVenteFactureVue> resultat = new ArrayList<LivraisonVenteFactureVue>();

	// Set clientId on whereClause if not null
	if (clientId != null) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<LivraisonVenteEntity> criteriaQuery = criteriaBuilder.createQuery(LivraisonVenteEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		
		Root<LivraisonVenteEntity> root = criteriaQuery.from(LivraisonVenteEntity.class);
		
		whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CLIENT), clientId));

		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    //List <LivraisonVenteEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

	    List <LivraisonVenteEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
	    if(resultatEntite != null){
	    	
	    	for(LivraisonVenteEntity entity : resultatEntite){
	    		
	    		resultat.add(bonLivraisonPersistanceUtilities.toFactureVue(entity));
	    	}
	    }
	}

    return resultat;
	}

	@Override
	public List<LivraisonVenteVue> getReferenceBLByClientIdAndDeclare(Long clientId) {

		List<LivraisonVenteVue> resultat = new ArrayList<LivraisonVenteVue>();

		// Set clientId on whereClause if not null
		if (clientId != null) {
			
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			
			CriteriaQuery<LivraisonVenteEntity> criteriaQuery = criteriaBuilder.createQuery(LivraisonVenteEntity.class);
			List<Predicate> whereClause = new ArrayList<Predicate>();
			
			Root<LivraisonVenteEntity> root = criteriaQuery.from(LivraisonVenteEntity.class);
			
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CLIENT), clientId));
			
			
			Expression<Boolean> expression_PREDICATE_DECLARE = root.get(PREDICATE_DECLARE);
			whereClause.add(criteriaBuilder.isTrue(expression_PREDICATE_DECLARE));


	    

			criteriaQuery.multiselect(root.get("reference")).where(whereClause.toArray(new Predicate[] {}));
			
		    List <LivraisonVenteEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
		    if(resultatEntite != null){
		    	
		    	for(LivraisonVenteEntity entity : resultatEntite){
		    		
		    		resultat.add(bonLivraisonPersistanceUtilities.toVue(entity));
		    	}
		    }
		}

	    return resultat;
	}

	@Override
	public List<LivraisonVenteValue> getByGroupeClientId(Long groupeClientId) {
		
		List<LivraisonVenteValue> resultat = new ArrayList<LivraisonVenteValue>();

		// Set clientId on whereClause if not null
		if (groupeClientId != null) {
			
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			
			CriteriaQuery<LivraisonVenteEntity> criteriaQuery = criteriaBuilder.createQuery(LivraisonVenteEntity.class);
			List<Predicate> whereClause = new ArrayList<Predicate>();
			
			Root<LivraisonVenteEntity> root = criteriaQuery.from(LivraisonVenteEntity.class);
			
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_GROUPE_CLIENT), groupeClientId));

			criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		    List <LivraisonVenteEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
		    
		    if(resultatEntite != null){
		    	
		    	for(LivraisonVenteEntity entity : resultatEntite){
		    		
		    		resultat.add(bonLivraisonPersistanceUtilities.toValue(entity));
		    	}
		    }
		}

	    return resultat;
	}

	@Override
	public boolean existeBC(String reference) {

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<LivraisonVenteEntity> criteriaQuery = criteriaBuilder.createQuery(LivraisonVenteEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<LivraisonVenteEntity> root = criteriaQuery.from(LivraisonVenteEntity.class);

		Expression<String> path = root.get(PREDICATE_REFERENCE_BON_COMMANDE);
		Expression<String> upper = criteriaBuilder.upper(path);
		Predicate predicate = criteriaBuilder.like(upper, PERCENT + reference.toUpperCase() + PERCENT);

		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		criteriaQuery.where(criteriaBuilder.and(predicate));

		List<LivraisonVenteEntity> entityListResult = this.entityManager.createQuery(criteriaQuery).getResultList();

		if (entityListResult.size() != 0) {
			return true;
		}

		return false;
	}

	@Override
	public List<LivraisonVenteValue> getByIdReglement(Long reglementId) {
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<LivraisonVenteEntity> criteriaQuery = criteriaBuilder.createQuery(LivraisonVenteEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		
		Root<LivraisonVenteEntity> root = criteriaQuery.from(LivraisonVenteEntity.class);
		
		 List<LivraisonVenteValue> list = new ArrayList<LivraisonVenteValue>();
		
		if (reglementId != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_REGLEMENT_ID), reglementId));
			
			
			
			criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		 	
			criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));
			
			
			  List <LivraisonVenteEntity> resultatEntite = null;
			  resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

		

		   
		    
		    for (LivraisonVenteEntity entity : resultatEntite) {
		    	LivraisonVenteValue dto = bonLivraisonPersistanceUtilities.toValue(entity);
		    	list.add(dto);
		    }

		   				
			
			
			
		}
		


	 	
	 	
	 

	    return list;
	}

	@Override
	public LivraisonVenteValue getByInfoSortie(String infoSortie) {


		LivraisonVenteValue resultat = null;

		// Set reference on whereClause if not empty or null
		if (estNonVide(infoSortie)) {
			
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			
			CriteriaQuery<LivraisonVenteEntity> criteriaQuery = criteriaBuilder.createQuery(LivraisonVenteEntity.class);
			List<Predicate> whereClause = new ArrayList<Predicate>();
			
			Root<LivraisonVenteEntity> root = criteriaQuery.from(LivraisonVenteEntity.class);
			
			/*Expression<String> path = root.get(PREDICATE_REFERENCE);
			Expression<String> upper =criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper, PERCENT + reference.toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));
			*/
			
			
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_INFO_SORTIE), infoSortie));
			
			

			criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		    List <LivraisonVenteEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
		    
		    if(resultatEntite != null)
		    	if(resultatEntite.size() > 0)
		    		resultat = bonLivraisonPersistanceUtilities.toValue(resultatEntite.get(FIRST_INDEX));
		    	
		}

	    return resultat;
	}

	
	
	@Override
	public List<LivraisonVenteValue> getByClientIdOptimiser(Long clientId) {
		
		List<LivraisonVenteValue> resultat = new ArrayList<LivraisonVenteValue>();

		// Set clientId on whereClause if not null
		if (clientId != null) {
			
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			
			//CriteriaQuery<LivraisonVenteEntity> criteriaQuery = criteriaBuilder.createQuery(LivraisonVenteEntity.class);
			
			CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
			
			
			List<Predicate> whereClause = new ArrayList<Predicate>();
			
			Root<LivraisonVenteEntity> root = criteriaQuery.from(LivraisonVenteEntity.class);
			
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CLIENT), clientId));

			//criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
			
			
			
			
			criteriaQuery.select(criteriaBuilder.array(
		 			
		 			root.get("id").as(Long.class),
		 			root.get("reference").as(String.class),
		 			root.get("montantTTC").as(Double.class),	 	 		
		 			root.get("date").as(Calendar.class),
		 		    root.get("montantHTaxe").as(Double.class),
		 		   root.get("refCommande").as(String.class)
		 			
					)).where(whereClause.toArray(new Predicate[] {}));
			
			
			
			List<Object[]> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
		    
		    if(resultatEntite != null){
		    	
		    	for(Object[] element : resultatEntite){
		    		
		    		
    	           LivraisonVenteEntity entity = new LivraisonVenteEntity();
			    	
			    	
			    	entity.setId((Long) element[0]);
			    	entity.setReference((String) element[1]);
			    	entity.setMontantTTC((Double) element[2]);
			     	entity.setDate((Calendar) element[3]);
			     	entity.setMontantHTaxe((Double) element[4]);
			     	entity.setRefCommande((String) element[5]);
		    		
		    		resultat.add(bonLivraisonPersistanceUtilities.toValue(entity));
		    	}
		    	
		    	
			    

		    }
		}

	    return resultat;
	}
	
	

	@Override
	public List<LivraisonVenteValue> getByGroupeClientIdOptimiser(Long groupeClientId) {

		
		List<LivraisonVenteValue> resultat = new ArrayList<LivraisonVenteValue>();

		// Set clientId on whereClause if not null
		if (groupeClientId != null) {
			
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			
			CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
			
			
			List<Predicate> whereClause = new ArrayList<Predicate>();
			
			Root<LivraisonVenteEntity> root = criteriaQuery.from(LivraisonVenteEntity.class);
			
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_GROUPE_CLIENT), groupeClientId));

			
			
			
			criteriaQuery.select(criteriaBuilder.array(
		 			
		 			root.get("id").as(Long.class),
		 			root.get("reference").as(String.class),
		 			root.get("montantTTC").as(Double.class),	 	 		
		 			 root.get("date").as(Calendar.class),
		 			 root.get("montantHTaxe").as(Double.class),
		 			 root.get("refCommande").as(String.class)
		 			 
		 			
		 			
					)).where(whereClause.toArray(new Predicate[] {}));
			
			
			
			
			List<Object[]> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
		   
			
		    if(resultatEntite != null){
		    	
		      	for(Object[] element : resultatEntite){
		    		
		    		
	    	           LivraisonVenteEntity entity = new LivraisonVenteEntity();
				    	
				    	
				    	entity.setId((Long) element[0]);
				    	entity.setReference((String) element[1]);
				    	entity.setMontantTTC((Double) element[2]);
				     	entity.setDate((Calendar) element[3]);
				    	entity.setMontantHTaxe((Double) element[4]);
				    	entity.setRefCommande((String) element[5]);
			    		
			    		resultat.add(bonLivraisonPersistanceUtilities.toValue(entity));
			    	}
			    	
		    }
		}

	    return resultat;
	}

}
