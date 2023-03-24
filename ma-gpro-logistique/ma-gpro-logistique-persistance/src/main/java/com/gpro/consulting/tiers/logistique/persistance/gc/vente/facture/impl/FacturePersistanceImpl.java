package com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture.impl;

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
import com.gpro.consulting.tiers.logistique.coordination.gc.IConstanteCommerciale;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.FactureVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.FactureVenteVue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.RechercheMulticritereFactureValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ResultatRechecheFactureValue;
import com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture.IFacturePersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture.entitie.FactureVenteEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture.utilities.FacturePersistanceUtilities;

/**
 * Implementation of {@link IFacturePersistance} interface.
 *  
 * @author Wahid Gazzah
 * @since 29/02/2016
 *
 */

@Component
public class FacturePersistanceImpl extends AbstractPersistance implements IFacturePersistance{
	
	private static final Logger logger = LoggerFactory.getLogger(FacturePersistanceImpl.class);
	
	private String PREDICATE_refCommande = "refCommande";
	private String PREDICATE_ID_DEPOT = "idDepot";
	private String PREDICATE_NATURE = "nature";
	private String PREDICATE_OBSERVATIONS = "observations";
	
	private String PREDICATE_RAISON = "raison";
	private String PREDICATE_REFERENCE = "reference";
	private String PREDICATE_CLIENT = "partieIntId";
	private String PREDICATE_GROUPE_CLIENT = "groupeClientId";
	private String PREDICATE_DATE= "date";
	private String PREDICATE_DATE_INTRO = "dateIntroduction";
	private String PREDICATE_METRAGE = "metrageTotal";
	private String PREDICATE_PRIX = "montantTTC";
	private String PREDICATE_INFO_LIVRAISON = "infoLivraison";
	private String PREDICATE_TYPE = "type";
	private String PREDICATE_NATURELIVRAISON = "natureLivraison";
	private String PERCENT = "%";
	private String PREDICATE_MARCHE = "idMarche";
	private int FIRST_INDEX = 0;
	
	private String PREDICATE_BOUTIQUEID = "boutiqueId";
	private String PREDICATE_REGLEMENT_ID = "reglementId";

	
	private String PREDICATE_INFO_LIVRAISON_EXTERNE = "infoLivraisonExterne";
	
	
	private int MAX_RESULTS = 52;
	private String devise = "devise";
	
	private String PREDICATE_DECLARE = "declarer";
	private String PREDICATE_ForcerCalculMontant = "forcerCalculMontant";
	
	
	
	
	private String PREDICATE_MODALITE_PAIEMENT = "modalitePaiement";
	private String PREDICATE_DATE_ECHEANCE = "dateEcheance";
	
	private String PREDICATE_IDENTIFIANT = "identifiant";
	
	
	
	
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private FacturePersistanceUtilities FacturePersistanceUtilities;

	
	
	@Override
	public ResultatRechecheFactureValue rechercherMultiCritere(
			RechercheMulticritereFactureValue request) {
		
		//logger.info("rechercherMultiCritere");
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		
		
		CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		
		Root<FactureVenteEntity> root = criteriaQuery.from(FactureVenteEntity.class);
		
		if(estNonVide(request.getBoutiqueId())) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_BOUTIQUEID), request.getBoutiqueId()));
		}
		
		
		if (request.getDateIntroductionDe() != null) {
			whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Calendar> get(PREDICATE_DATE_INTRO),
					request.getDateIntroductionDe()));
		}

		if (request.getDateIntroductionA() != null) {
			whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Calendar> get(PREDICATE_DATE_INTRO),
					request.getDateIntroductionA()));
		}
		
		// Set request.referenceFacture on whereClause if not empty or null
		if (estNonVide(request.getReferenceFacture())) {
			Expression<String> path = root.get(PREDICATE_REFERENCE);
			Expression<String> upper =criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getReferenceFacture().toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));
		}
		
		// Set request.referenceBl on whereClause if not empty or null
		if (estNonVide(request.getReferenceBl())) {
			Expression<String> path = root.get(PREDICATE_INFO_LIVRAISON);
			Expression<String> upper =criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getReferenceBl().toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));
		}
		
		
		// Added By Ghazi on 23/05/2018
		
		
		if (estNonVide(request.getReferenceBlExterne())) {
			Expression<String> path = root.get(PREDICATE_INFO_LIVRAISON_EXTERNE);
			Expression<String> upper =criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getReferenceBlExterne().toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));
		}
		
		// Set request.partieIntId on whereClause if not null
		if (request.getPartieIntId() != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CLIENT), request.getPartieIntId()));
		}
		
		// Set request.dateFactureMin on whereClause if not null
	    if (request.getDateFactureMin() != null) {
	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE), request.getDateFactureMin()));
	    }
	    
		// Set request.dateFactureMax on whereClause if not null
	    if (request.getDateFactureMax() != null) {
	    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE), request.getDateFactureMax()));
	    }
	    
		// Set request.metrageMin on whereClause if not null
	    if (request.getMetrageMin() != null) {
	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Double>get(PREDICATE_METRAGE), request.getMetrageMin()));
	    }
	    
		// Set request.metrageMax on whereClause if not null
	    if (request.getMetrageMax() != null) {
	    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Double>get(PREDICATE_METRAGE), request.getMetrageMax()));
	    }
	    
		// Set request.prixMin on whereClause if not null
	    if (request.getPrixMin() != null) {
	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Double>get(PREDICATE_PRIX), request.getPrixMin()));
	    }
	    
		// Set request.prixMax on whereClause if not null
	    if (request.getPrixMax() != null) {
	    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Double>get(PREDICATE_PRIX), request.getPrixMax()));
	    }
	    
	    // Set request.type on whereClause if not null
	    if (estNonVide(request.getType() )) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_TYPE), request.getType()));
		}
	    
	 // Set request.natureLivraison on whereClause if not empty or null
	 	if (estNonVide(request.getNatureLivraison())) {
	 			Expression<String> path = root.get(PREDICATE_NATURELIVRAISON);
	 			Expression<String> upper =criteriaBuilder.upper(path);
	 			Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getNatureLivraison().toUpperCase() + PERCENT);
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
		   
		   // Set request.Raison on whereClause if not null
		    if (estNonVide(request.getRaison() )) {
				whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_RAISON), request.getRaison()));
			}
		    
		    //observations
		    
			if (estNonVide(request.getObservations())) {
				Expression<String> path = root.get(PREDICATE_OBSERVATIONS);
				Expression<String> upper =criteriaBuilder.upper(path);
				Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getObservations().toUpperCase() + PERCENT);
				whereClause.add(criteriaBuilder.and(predicate));
			}
			
			  // Set nature
		    if (estNonVide(request.getNature() )) {
				whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_NATURE), request.getNature()));
			}
		    
		    
			// Set request.PREDICATE_ID_DEPOT
			if (request.getIdDepot() != null) {
				whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_ID_DEPOT), request.getIdDepot()));
			}
	 	
	 	
			if (request.getDevise() != null) {
				whereClause.add(criteriaBuilder.equal(root.get(devise), request.getDevise()));
			}
	 	
			
			
			if (estNonVide(request.getDeclarerecherche())) {
				Expression<Boolean> expression = root.get(PREDICATE_DECLARE);
				switch (request.getDeclarerecherche()) {
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
			
			if (estNonVide(request.getForcerCalculMontantRech())) {
				Expression<Boolean> expression = root.get(PREDICATE_ForcerCalculMontant);
				switch (request.getForcerCalculMontantRech()) {
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
			
			
			
			if (request.getDateEcheanceDe() != null) {
				whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Calendar> get(PREDICATE_DATE_ECHEANCE),
						request.getDateEcheanceDe()));
			}

			if (request.getDateEcheanceA() != null) {
				whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Calendar> get(PREDICATE_DATE_ECHEANCE),
						request.getDateEcheanceA()));
			}
			
			
		    if (estNonVide(request.getModalitePaiement() )) {
				whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_MODALITE_PAIEMENT), request.getModalitePaiement()));
			}
		    
		    
			if (estNonVide(request.getInfoLivraison())) {
				Expression<String> path = root.get(PREDICATE_INFO_LIVRAISON);
				Expression<String> upper =criteriaBuilder.upper(path);
				Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getInfoLivraison().toUpperCase() + PERCENT);
				whereClause.add(criteriaBuilder.and(predicate));
			}
			
			

			  // Set nature
		    if (estNonVide(request.getRefCommande() )) {
				whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_refCommande), request.getRefCommande()));
			}
		    
		    
		    if (estNonVide(request.getIdentifiant() )) {
				whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_IDENTIFIANT), request.getIdentifiant()));
			}
			
	 //	criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	 	
	 	
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
	 			root.get("infoLivraison").as(String.class),
	 			root.get("metrageTotal").as(Double.class),
	 			root.get("etat").as(String.class),
	 			root.get("natureLivraison").as(String.class),
	 			root.get("modepaiementId").as(Long.class),	
	 			root.get("type").as(String.class),
	 			root.get("totalPourcentageRemise").as(Double.class),	
	 			root.get("typePartieInteressee").as(Long.class),
	 			root.get("groupeClientId").as(Long.class),
	 			root.get("raison").as(String.class),
	 			root.get("dateIntroduction").as(Calendar.class),	
	 			root.get("idDepot").as(Long.class),
	 			root.get("reglementId").as(Long.class),
	 			root.get("devise").as(Long.class),
	 			root.get("montantConverti").as(Double.class),
	 			root.get("declarer").as(boolean.class),
	 			root.get("forcerCalculMontant").as(boolean.class),
	 			
	 			root.get("modalitePaiement").as(String.class),
	 			root.get("dateEcheance").as(Calendar.class),
	 			root.get("refFactures").as(String.class),
	 			
	 			root.get("refCommande").as(String.class),
	 			
	 			root.get("identifiant").as(String.class)
	 			
	 			
	 			
				
				)).where(whereClause.toArray(new Predicate[] {}));
	 	
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));
		
		
		List<Object[]> resultatEntite = null;

		// If criterias are empty
		if (request.isOptimized()) {
			resultatEntite = this.entityManager.createQuery(criteriaQuery).setMaxResults(MAX_RESULTS).getResultList();

		} else {
			resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
		}
		
		

	    List<FactureVenteValue> list = new ArrayList<FactureVenteValue>();
	    
	    for (Object[] element : resultatEntite) {
	    	
	    	FactureVenteEntity entity = new FactureVenteEntity();
	    	
	    	entity.setId((Long) element[0]);
	    	entity.setPartieIntId((Long) element[1]);
	    	entity.setReference((String) element[2]);
	    	entity.setDate((Calendar) element[3]);
	    	entity.setMontantHTaxe((Double) element[4]);
	    	entity.setMontantTTC((Double) element[5]);
	    	entity.setMontantTaxe((Double) element[6]);
	    	entity.setMontantRemise((Double) element[7]);
	    	entity.setObservations((String) element[8]);
	    	entity.setInfoLivraison((String) element[9]);
	    	entity.setMetrageTotal((Double) element[10]);
	    	entity.setEtat((String) element[11]);
	    	entity.setNatureLivraison((String) element[12]);
	    	entity.setModepaiementId((Long) element[13]);
	    	entity.setType((String) element[14]);
	    	entity.setTotalPourcentageRemise((Double) element[15]);
	    	entity.setTypePartieInteressee((Long) element[16]);
	    	entity.setGroupeClientId((Long) element[17]);
	    	entity.setRaison((String) element[18]);
	    	entity.setDateIntroduction((Calendar) element[19]);
	    	
	    	entity.setIdDepot((Long) element[20]);
	    	entity.setReglementId((Long) element[21]);
	    	entity.setDevise((Long) element[22]);
	    	entity.setMontantConverti((Double) element[23]);
	    entity.setDeclarer((boolean) element[24]);
	    
	    entity.setForcerCalculMontant((boolean) element[25]);
	    
	    entity.setModalitePaiement((String) element[26]);
	    entity.setDateEcheance((Calendar)element[27]);
	    
	    entity.setRefFactures((String) element[28]);
	    
		entity.setRefCommande((String) element[29]);
		
		entity.setIdentifiant((String) element[30]);
		
	    	
	    	FactureVenteValue dto = FacturePersistanceUtilities.toValue(entity);
	    	list.add(dto);
	    }

	    ResultatRechecheFactureValue resultat = new ResultatRechecheFactureValue();
	    Collections.sort(list);
	    resultat.setNombreResultaRechercher(Long.valueOf(list.size()));
	    resultat.setList(new TreeSet<>(list));

	    return resultat;
	}
	
	
	
	@Override
	public ResultatRechecheFactureValue rechercherMultiCritereAvecDetail(
			RechercheMulticritereFactureValue request) {
		
		//logger.info("rechercherMultiCritere");
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<FactureVenteEntity> criteriaQuery = criteriaBuilder.createQuery(FactureVenteEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		
		Root<FactureVenteEntity> root = criteriaQuery.from(FactureVenteEntity.class);
		
		if(estNonVide(request.getBoutiqueId())) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_BOUTIQUEID), request.getBoutiqueId()));
		}
		
		
		if (request.getDateIntroductionDe() != null) {
			whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Calendar> get(PREDICATE_DATE_INTRO),
					request.getDateIntroductionDe()));
		}

		if (request.getDateIntroductionA() != null) {
			whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Calendar> get(PREDICATE_DATE_INTRO),
					request.getDateIntroductionA()));
		}
		
		// Set request.referenceFacture on whereClause if not empty or null
		if (estNonVide(request.getReferenceFacture())) {
			Expression<String> path = root.get(PREDICATE_REFERENCE);
			Expression<String> upper =criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getReferenceFacture().toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));
		}
		
		// Set request.referenceBl on whereClause if not empty or null
		if (estNonVide(request.getReferenceBl())) {
			Expression<String> path = root.get(PREDICATE_INFO_LIVRAISON);
			Expression<String> upper =criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getReferenceBl().toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));
		}
		
		
		// Added By Ghazi on 23/05/2018
		
		
		if (estNonVide(request.getReferenceBlExterne())) {
			Expression<String> path = root.get(PREDICATE_INFO_LIVRAISON_EXTERNE);
			Expression<String> upper =criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getReferenceBlExterne().toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));
		}
		
		// Set request.partieIntId on whereClause if not null
		if (request.getPartieIntId() != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CLIENT), request.getPartieIntId()));
		}
		
		// Set request.dateFactureMin on whereClause if not null
	    if (request.getDateFactureMin() != null) {
	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE), request.getDateFactureMin()));
	    }
	    
		// Set request.dateFactureMax on whereClause if not null
	    if (request.getDateFactureMax() != null) {
	    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE), request.getDateFactureMax()));
	    }
	    
		// Set request.metrageMin on whereClause if not null
	    if (request.getMetrageMin() != null) {
	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Double>get(PREDICATE_METRAGE), request.getMetrageMin()));
	    }
	    
		// Set request.metrageMax on whereClause if not null
	    if (request.getMetrageMax() != null) {
	    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Double>get(PREDICATE_METRAGE), request.getMetrageMax()));
	    }
	    
		// Set request.prixMin on whereClause if not null
	    if (request.getPrixMin() != null) {
	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Double>get(PREDICATE_PRIX), request.getPrixMin()));
	    }
	    
		// Set request.prixMax on whereClause if not null
	    if (request.getPrixMax() != null) {
	    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Double>get(PREDICATE_PRIX), request.getPrixMax()));
	    }
	    
	    // Set request.type on whereClause if not null
	    if (estNonVide(request.getType() )) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_TYPE), request.getType()));
		}
	    
	 // Set request.natureLivraison on whereClause if not empty or null
	 	if (estNonVide(request.getNatureLivraison())) {
	 			Expression<String> path = root.get(PREDICATE_NATURELIVRAISON);
	 			Expression<String> upper =criteriaBuilder.upper(path);
	 			Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getNatureLivraison().toUpperCase() + PERCENT);
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
		   
		   // Set request.Raison on whereClause if not null
		    if (estNonVide(request.getRaison() )) {
				whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_RAISON), request.getRaison()));
			}
		    
		    //observations
		    
			if (estNonVide(request.getObservations())) {
				Expression<String> path = root.get(PREDICATE_OBSERVATIONS);
				Expression<String> upper =criteriaBuilder.upper(path);
				Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getObservations().toUpperCase() + PERCENT);
				whereClause.add(criteriaBuilder.and(predicate));
			}
			
			  // Set nature
		    if (estNonVide(request.getNature() )) {
				whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_NATURE), request.getNature()));
			}
		    
		    
			// Set request.PREDICATE_ID_DEPOT
			if (request.getIdDepot() != null) {
				whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_ID_DEPOT), request.getIdDepot()));
			}
	 	
	 	
			if (request.getDevise() != null) {
				whereClause.add(criteriaBuilder.equal(root.get(devise), request.getDevise()));
			}
	 	
			
			
			if (estNonVide(request.getDeclarerecherche())) {
				Expression<Boolean> expression = root.get(PREDICATE_DECLARE);
				switch (request.getDeclarerecherche()) {
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
			
			if (estNonVide(request.getForcerCalculMontantRech())) {
				Expression<Boolean> expression = root.get(PREDICATE_ForcerCalculMontant);
				switch (request.getForcerCalculMontantRech()) {
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
			
			
			if (request.getDateEcheanceDe() != null) {
				whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Calendar> get(PREDICATE_DATE_ECHEANCE),
						request.getDateEcheanceDe()));
			}

			if (request.getDateEcheanceA() != null) {
				whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Calendar> get(PREDICATE_DATE_ECHEANCE),
						request.getDateEcheanceA()));
			}
			
			
		    if (estNonVide(request.getModalitePaiement() )) {
				whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_MODALITE_PAIEMENT), request.getModalitePaiement()));
			}
		    
		    
		    if (estNonVide(request.getIdentifiant() )) {
						whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_IDENTIFIANT), request.getIdentifiant()));
				}
					
	 	
	 	
	 	criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	 	
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));
		
		
		  List <FactureVenteEntity> resultatEntite = null;

		// If criterias are empty
		if (request.isOptimized()) {
			resultatEntite = this.entityManager.createQuery(criteriaQuery).setMaxResults(MAX_RESULTS).getResultList();

		} else {
			resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
		}
		
		

	    List<FactureVenteValue> list = new ArrayList<FactureVenteValue>();
	    
	    for (FactureVenteEntity entity : resultatEntite) {
	    	FactureVenteValue dto = FacturePersistanceUtilities.toValue(entity);
	    	list.add(dto);
	    }

	    ResultatRechecheFactureValue resultat = new ResultatRechecheFactureValue();
	    Collections.sort(list);
	    resultat.setNombreResultaRechercher(Long.valueOf(list.size()));
	    resultat.setList(new TreeSet<>(list));

	    return resultat;
	}
	
	
	

	@Override
	public String createFacture(FactureVenteValue FactureValue) {
		
	    if(!existeNumero(FactureValue))
		{FactureVenteEntity entity = (FactureVenteEntity) this.creer(FacturePersistanceUtilities.toEntity(FactureValue));

	    return entity.getId().toString();
		}
	    else 
	    	return "existe";
	    
	}


	@Override
	public FactureVenteValue getFactureById(Long id) {
		
		FactureVenteEntity bonSortieFiniEntity = this.rechercherParId(id, FactureVenteEntity.class);

	    return FacturePersistanceUtilities.toValue(bonSortieFiniEntity);
	}


	@Override
	public String updateFacture(FactureVenteValue FactureValue) {
		if(!existeNumero(FactureValue)){
		FactureVenteEntity entity = (FactureVenteEntity) this.modifier(FacturePersistanceUtilities.toEntity(FactureValue));

	    return entity.getId().toString();
		}
		else 
		{
			return "existe";
		}
	}


	@Override
	public void deleteFacture(Long id) {
		
		this.supprimerEntite(FactureVenteEntity.class, id);
	}

	@Override
	public List<FactureVenteValue> getAll() {

		List<FactureVenteEntity> listEntity = this.lister(FactureVenteEntity.class);
		
		return FacturePersistanceUtilities.listFactureVenteEntitytoValue(listEntity);
	}
	
	
	private boolean estNonVide(String val) {
		return val != null && !"".equals(val) && !"undefined".equals(val) && !"null".equals(val);
	}
	private boolean estNonVide(Long val) {
		return val != null && !"".equals(val) && !"undefined".equals(val);
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public FactureVenteValue getFactureByReference(String reference) {
		
		FactureVenteValue resultat = null;

		// Set reference on whereClause if not empty or null
		if (estNonVide(reference)) {
			
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			
			CriteriaQuery<FactureVenteEntity> criteriaQuery = criteriaBuilder.createQuery(FactureVenteEntity.class);
			List<Predicate> whereClause = new ArrayList<Predicate>();
			
			Root<FactureVenteEntity> root = criteriaQuery.from(FactureVenteEntity.class);
			
		
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_REFERENCE), reference));
		
		    
		    
			/*Expression<String> path = root.get(PREDICATE_REFERENCE);
			Expression<String> upper =criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper, PERCENT + reference.toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));*/

			criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		    List <FactureVenteEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
		    
		    if(resultatEntite != null && resultatEntite.size()>0 )
		    	resultat = FacturePersistanceUtilities.toValue(resultatEntite.get(FIRST_INDEX));
		    
		    
		    
		}

	    return resultat;
	}

	@Override
	public List<FactureVenteValue> getByClientId(Long clientId) {
		
		List<FactureVenteValue> resultat = new ArrayList<FactureVenteValue>();

		// Set clientId on whereClause if not null
		if (clientId != null) {
			
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			
			CriteriaQuery<FactureVenteEntity> criteriaQuery = criteriaBuilder.createQuery(FactureVenteEntity.class);
			List<Predicate> whereClause = new ArrayList<Predicate>();
			
			Root<FactureVenteEntity> root = criteriaQuery.from(FactureVenteEntity.class);
			
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CLIENT), clientId));
		
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_TYPE),IConstanteCommerciale.FACTURE_TYPE_NORMAL));

			criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		    List <FactureVenteEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
		    
		    if(resultatEntite != null){
		    	
		    	for(FactureVenteEntity entity : resultatEntite){
		    		
		    		resultat.add(FacturePersistanceUtilities.toValue(entity));
		    	}
		    }
		}

	    return resultat;
	}
	
	@Override
	public List<FactureVenteVue> getRefFactureByClientId(Long clientId) {
		
		List<FactureVenteVue> resultat = new ArrayList<FactureVenteVue>();

		// Set clientId on whereClause if not null
		if (clientId != null) {
			
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			
			CriteriaQuery<FactureVenteEntity> criteriaQuery = criteriaBuilder.createQuery(FactureVenteEntity.class);
			List<Predicate> whereClause = new ArrayList<Predicate>();
			
			Root<FactureVenteEntity> root = criteriaQuery.from(FactureVenteEntity.class);
			
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CLIENT), clientId));
			
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_TYPE), IConstanteCommerciale.FACTURE_TYPE_NORMAL));

			criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		    List <FactureVenteEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
		    
		    if(resultatEntite != null){
		    	
		    	for(FactureVenteEntity entity : resultatEntite){
		    		
		    		resultat.add(FacturePersistanceUtilities.toVue(entity));
		    	}
		    }
		}

	    return resultat;
	}
	
	
	@Override
	public List<FactureVenteVue> getRefFactureByClientIdAll() {
		
		List<FactureVenteVue> resultat = new ArrayList<FactureVenteVue>();

		// Set clientId on whereClause if not null
	//	if (clientId != null) {
			
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			
			CriteriaQuery<FactureVenteEntity> criteriaQuery = criteriaBuilder.createQuery(FactureVenteEntity.class);
			List<Predicate> whereClause = new ArrayList<Predicate>();
			
			Root<FactureVenteEntity> root = criteriaQuery.from(FactureVenteEntity.class);
			
			//whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CLIENT), clientId));
			
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_TYPE), IConstanteCommerciale.FACTURE_TYPE_NORMAL));

			criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		    List <FactureVenteEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
		    
		    if(resultatEntite != null){
		    	
		    	for(FactureVenteEntity entity : resultatEntite){
		    		
		    		resultat.add(FacturePersistanceUtilities.toVue(entity));
		    	}
		    }
	//	}

	    return resultat;
	}
	
	
	
	
	
	
	
	@Override
	public List<FactureVenteValue> getProduitElementList(List<String> refFactureList) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
	    CriteriaQuery<FactureVenteEntity> criteriaQuery = criteriaBuilder.createQuery(FactureVenteEntity.class);
	    List<Predicate> whereClause = new ArrayList<Predicate>();
	    Root<FactureVenteEntity> root = criteriaQuery.from(FactureVenteEntity.class);
	    
	    whereClause.add(root.get(PREDICATE_REFERENCE).in(refFactureList));
		
	    criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    
	    List<FactureVenteEntity> entityListResult = this.entityManager.createQuery(criteriaQuery).getResultList();

	    List<FactureVenteValue> listDetLivraison = new ArrayList<FactureVenteValue>();
	    
	    for (FactureVenteEntity entity : entityListResult) {
	    	FactureVenteValue value = FacturePersistanceUtilities.toValue(entity);
	    	listDetLivraison.add(value);
	    }
	    
	    return listDetLivraison;
	    
	}

	@Override
	public boolean existeBL(String referenceBL) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
	    CriteriaQuery<FactureVenteEntity> criteriaQuery = criteriaBuilder.createQuery(FactureVenteEntity.class);
	    List<Predicate> whereClause = new ArrayList<Predicate>();
	    Root<FactureVenteEntity> root = criteriaQuery.from(FactureVenteEntity.class);
	    
	    Expression<String> path = root.get(PREDICATE_INFO_LIVRAISON);
		Expression<String> upper =criteriaBuilder.upper(path);
		Predicate predicate = criteriaBuilder.like(upper, PERCENT + referenceBL.toUpperCase() + PERCENT);
		
	    criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    criteriaQuery.where(criteriaBuilder.and(predicate));
	    
	    List<FactureVenteEntity> entityListResult = this.entityManager.createQuery(criteriaQuery).getResultList();
	    
	    if(entityListResult.size()!=0){
	    	return true;
	    }
	    
		return false;
	}
	
	@Override
	public Double getSommeMontHT(Long PIId , Calendar dateMin, Calendar dateMax) {

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Double> criteriaQuery = criteriaBuilder.createQuery(Double.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		
		Root<FactureVenteEntity> root = criteriaQuery.from(FactureVenteEntity.class);

		if (PIId != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CLIENT), PIId));
		}
		
	    if (dateMin != null && dateMax!=null) {
	    	whereClause.add(criteriaBuilder.between(root.<Calendar>get(PREDICATE_DATE), dateMin, dateMax));
	    }
		
		criteriaQuery.select(criteriaBuilder.sum(root.get("montantTTC").as(Double.class))).where(whereClause.toArray(new Predicate[] {}));
	    
		Double sommeMontHT = this.entityManager.createQuery(criteriaQuery).getSingleResult();
	    
	    if(sommeMontHT!=null){
	    	return sommeMontHT;
	    }
	    
	    return 0D;
	
	}

	@Override
	public Double getSommeMontHTFactAvoir(Long PIId, Calendar dateMin, Calendar dateMax) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Double> criteriaQuery = criteriaBuilder.createQuery(Double.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		
		Root<FactureVenteEntity> root = criteriaQuery.from(FactureVenteEntity.class);

		if (PIId != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CLIENT), PIId));
		}
		
	    if (dateMin != null && dateMax!=null) {
	    	whereClause.add(criteriaBuilder.between(root.<Calendar>get(PREDICATE_DATE), dateMin, dateMax));
	    }

	    //TODO : Vérifier la syntaxe du type "AVOIR"
	    whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_TYPE), "AVOIR"));
		criteriaQuery.select(criteriaBuilder.sum(root.get("montantTTC").as(Double.class))).where(whereClause.toArray(new Predicate[] {}));
	    
		Double sommeMontHT = this.entityManager.createQuery(criteriaQuery).getSingleResult();
	    
	    if(sommeMontHT!=null){
	    	return sommeMontHT;
	    }
	    
	    return 0D;
	    
	}
	
public boolean existeNumero(FactureVenteValue pFacture) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
	    CriteriaQuery<FactureVenteEntity> criteriaQuery = criteriaBuilder.createQuery(FactureVenteEntity.class);
	    List<Predicate> whereClause = new ArrayList<Predicate>();
	    Root<FactureVenteEntity> root = criteriaQuery.from(FactureVenteEntity.class);
	    
	    Expression<String> path = root.get(PREDICATE_REFERENCE);
		Expression<String> upper =criteriaBuilder.upper(path);
		Predicate predicate = criteriaBuilder.like(upper,pFacture.getReference().toUpperCase());
		whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_TYPE),pFacture.getType()));
	    whereClause.add(predicate);
		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    //criteriaQuery.where(criteriaBuilder.and(predicate));
	    
	    List<FactureVenteEntity> entityListResult = this.entityManager.createQuery(criteriaQuery).getResultList();
	    
	    if(entityListResult.size()!=0){
	    	
	    	Long idFacture=entityListResult.get(0).getId();
	    	
	    	if(pFacture.getId()==null || idFacture.equals(pFacture.getId()))
	    	     return false;
	    	    
	    	
	    	return true;
	    }
	    
		return false;
	}

@Override
public List<FactureVenteValue> getByGroupeClientId(Long groupeClientId) {
	List<FactureVenteValue> resultat = new ArrayList<FactureVenteValue>();

	// Set clientId on whereClause if not null
	if (groupeClientId != null) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<FactureVenteEntity> criteriaQuery = criteriaBuilder.createQuery(FactureVenteEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		
		Root<FactureVenteEntity> root = criteriaQuery.from(FactureVenteEntity.class);
		
		whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_GROUPE_CLIENT), groupeClientId));
		whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_TYPE),IConstanteCommerciale.FACTURE_TYPE_NORMAL));

		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    List <FactureVenteEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
	    
	    if(resultatEntite != null){
	    	
	    	for(FactureVenteEntity entity : resultatEntite){
	    		
	    		resultat.add(FacturePersistanceUtilities.toValue(entity));
	    	}
	    }
	}

    return resultat;
}


@Override
public List<FactureVenteValue> getAllInfoLivraisonByClientIdAndType(Long idClient, String factureTypeNormal) {
	//logger.info("rechercherMultiCritere");
	
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			
			
			CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
			List<Predicate> whereClause = new ArrayList<Predicate>();
			
			Root<FactureVenteEntity> root = criteriaQuery.from(FactureVenteEntity.class);
			
			
			// Set request.partieIntId on whereClause if not null
			if (idClient != null) {
				whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CLIENT), idClient));
			}
			

		    // Set request.type on whereClause if not null
		    if (estNonVide(factureTypeNormal)) {
				whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_TYPE), factureTypeNormal));
			}
		    
	
		 	
		 //	criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		 	
		 	
		 	criteriaQuery.select(criteriaBuilder.array(
		 			
		 			root.get("id").as(Long.class),
		 			root.get("reference").as(String.class),
		 			root.get("infoLivraison").as(String.class)	
		 			
					)).where(whereClause.toArray(new Predicate[] {}));
		 	
			
			
			List<Object[]> 	resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

		
			
			
		
			

		    List<FactureVenteValue> list = new ArrayList<FactureVenteValue>();
		    
		    for (Object[] element : resultatEntite) {
		    	
		    	FactureVenteEntity entity = new FactureVenteEntity();
		    	
		    	entity.setId((Long) element[0]);
		    	entity.setReference((String) element[1]);
		    	entity.setInfoLivraison((String) element[2]);

		    	
		    	FactureVenteValue dto = FacturePersistanceUtilities.toValue(entity);
		    	list.add(dto);
		    }

		 

		    return list;
}


@Override
public boolean existeBLexacte(String referenceBL) {
	CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
    CriteriaQuery<FactureVenteEntity> criteriaQuery = criteriaBuilder.createQuery(FactureVenteEntity.class);
    List<Predicate> whereClause = new ArrayList<Predicate>();
    Root<FactureVenteEntity> root = criteriaQuery.from(FactureVenteEntity.class);
    
  
	
	whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_INFO_LIVRAISON), referenceBL));
	
    criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
   
    
    List<FactureVenteEntity> entityListResult = this.entityManager.createQuery(criteriaQuery).getResultList();
    
    if(entityListResult.size()!=0){
    	return true;
    }
    
	return false;
}


@Override
public List<FactureVenteValue> getByIdReglement(Long reglementId) {
	CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
	
	CriteriaQuery<FactureVenteEntity> criteriaQuery = criteriaBuilder.createQuery(FactureVenteEntity.class);
	List<Predicate> whereClause = new ArrayList<Predicate>();
	
	Root<FactureVenteEntity> root = criteriaQuery.from(FactureVenteEntity.class);
	
	 List<FactureVenteValue> list = new ArrayList<FactureVenteValue>();
	
	if (reglementId != null) {
		whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_REGLEMENT_ID), reglementId));
		
		
		
		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	 	
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));
		
		
		  List <FactureVenteEntity> resultatEntite = null;
		  resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

	

	   
	    
	    for (FactureVenteEntity entity : resultatEntite) {
	    	FactureVenteValue dto = FacturePersistanceUtilities.toValue(entity);
	    	list.add(dto);
	    }

	   				
		
		
		
	}
	


    return list;
}


@Override
public List<FactureVenteValue> getByClientIdOptimiser(Long clientId) {

	
	List<FactureVenteValue> resultat = new ArrayList<FactureVenteValue>();

	// Set clientId on whereClause if not null
	if (clientId != null) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		
		
		CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
		//CriteriaQuery<FactureVenteEntity> criteriaQuery = criteriaBuilder.createQuery(FactureVenteEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		
		Root<FactureVenteEntity> root = criteriaQuery.from(FactureVenteEntity.class);
		
		whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CLIENT), clientId));
	
		whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_TYPE),IConstanteCommerciale.FACTURE_TYPE_NORMAL));

		//criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    
	     criteriaQuery.select(criteriaBuilder.array(
	 			
	 			root.get("id").as(Long.class),
	 			root.get("reference").as(String.class),
	 			root.get("montantTTC").as(Double.class),
	 			root.get("infoLivraison").as(String.class),
	 			 root.get("date").as(Calendar.class),
	 			root.get("montantHTaxe").as(Double.class)
	 		
	 			
	 			
	 		
	 				
				
				)).where(whereClause.toArray(new Predicate[] {}));
		
		
		//List <FactureVenteEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
	    
		List<Object[]>resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
	    

		
	    if(resultatEntite != null){
	    	
	    	for(Object[] element : resultatEntite){
	    		
	    		
	    		FactureVenteEntity entity = new FactureVenteEntity();
		    	
		    	entity.setId((Long) element[0]);
		    	entity.setReference((String) element[1]);
		    	entity.setMontantTTC((Double) element[2]);
		    	entity.setInfoLivraison((String) element[3]);
		    	entity.setDate((Calendar) element[4]);
		    	

		    	entity.setMontantHTaxe((Double) element[5]);
	    		
	    		
	    		resultat.add(FacturePersistanceUtilities.toValue(entity));
	    	}
	    }
	}

    return resultat;
}

@Override
public List<FactureVenteValue> getByGroupeClientIdOptimiser(Long groupeClientId) {
	List<FactureVenteValue> resultat = new ArrayList<FactureVenteValue>();

	// Set clientId on whereClause if not null
	if (groupeClientId != null) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();     
		
		//CriteriaQuery<FactureVenteEntity> criteriaQuery = criteriaBuilder.createQuery(FactureVenteEntity.class);
		
		CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
		
		
		List<Predicate> whereClause = new ArrayList<Predicate>();
		
		Root<FactureVenteEntity> root = criteriaQuery.from(FactureVenteEntity.class);
		
		whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_GROUPE_CLIENT), groupeClientId));
		whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_TYPE),IConstanteCommerciale.FACTURE_TYPE_NORMAL));


		
	     criteriaQuery.select(criteriaBuilder.array(
		 			
	 			root.get("id").as(Long.class),
	 			root.get("reference").as(String.class),
	 			root.get("montantTTC").as(Double.class),
	 			root.get("infoLivraison").as(String.class),
	 			 root.get("date").as(Calendar.class),
	 			root.get("montantHTaxe").as(Double.class)
	 			
	 			
	 		
	 				
				
				)).where(whereClause.toArray(new Predicate[] {}));
		
		
		
		
	 	List<Object[]>resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
	       if(resultatEntite != null){
	    	
	    	   for(Object[] element : resultatEntite){
		    		
		    		
		    		FactureVenteEntity entity = new FactureVenteEntity();
			    	
			    	entity.setId((Long) element[0]);
			    	entity.setReference((String) element[1]);
			    	entity.setMontantTTC((Double) element[2]);
			    	entity.setInfoLivraison((String) element[3]);
			    	entity.setDate((Calendar) element[4]);
			    	
			    	entity.setMontantHTaxe((Double) element[5]);
		    		
		    		
		    		resultat.add(FacturePersistanceUtilities.toValue(entity));
		    	}
	    }
	}

    return resultat;
}



@Override
public List<FactureVenteValue> getByClientAvoirIdOptimiser(Long clientId) {
	// TODO Auto-generated method stub

	List<FactureVenteValue> resultat = new ArrayList<FactureVenteValue>();

	// Set clientId on whereClause if not null
	if (clientId != null) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		
		
		CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
		//CriteriaQuery<FactureVenteEntity> criteriaQuery = criteriaBuilder.createQuery(FactureVenteEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		
		Root<FactureVenteEntity> root = criteriaQuery.from(FactureVenteEntity.class);
		
		whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CLIENT), clientId));
	
		whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_TYPE),IConstanteCommerciale.FACTURE_TYPE_AVOIR));

		//criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    
	     criteriaQuery.select(criteriaBuilder.array(
	 			
	 			root.get("id").as(Long.class),
	 			root.get("reference").as(String.class),
	 			root.get("montantTTC").as(Double.class),
	 			root.get("infoLivraison").as(String.class),
	 			 root.get("date").as(Calendar.class),
	 			root.get("montantHTaxe").as(Double.class)
	 		
	 			
	 			
	 		
	 				
				
				)).where(whereClause.toArray(new Predicate[] {}));
		
		
		//List <FactureVenteEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
	    
		List<Object[]>resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
	    

		
	    if(resultatEntite != null){
	    	
	    	for(Object[] element : resultatEntite){
	    		
	    		
	    		FactureVenteEntity entity = new FactureVenteEntity();
		    	
		    	entity.setId((Long) element[0]);
		    	entity.setReference((String) element[1]);
		    	entity.setMontantTTC((Double) element[2]);
		    	entity.setInfoLivraison((String) element[3]);
		    	entity.setDate((Calendar) element[4]);
		    	

		    	entity.setMontantHTaxe((Double) element[5]);
	    		
	    		
	    		resultat.add(FacturePersistanceUtilities.toValue(entity));
	    	}
	    }
	}

    return resultat;
}



@Override
public List<FactureVenteValue> getByGroupeClientAvoirIdOptimiser(Long groupeClientId) {
	List<FactureVenteValue> resultat = new ArrayList<FactureVenteValue>();

	// Set clientId on whereClause if not null
	if (groupeClientId != null) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();     
		
		//CriteriaQuery<FactureVenteEntity> criteriaQuery = criteriaBuilder.createQuery(FactureVenteEntity.class);
		
		CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
		
		
		List<Predicate> whereClause = new ArrayList<Predicate>();
		
		Root<FactureVenteEntity> root = criteriaQuery.from(FactureVenteEntity.class);
		
		whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_GROUPE_CLIENT), groupeClientId));
		whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_TYPE),IConstanteCommerciale.FACTURE_TYPE_AVOIR));


		
	     criteriaQuery.select(criteriaBuilder.array(
		 			
	 			root.get("id").as(Long.class),
	 			root.get("reference").as(String.class),
	 			root.get("montantTTC").as(Double.class),
	 			root.get("infoLivraison").as(String.class),
	 			 root.get("date").as(Calendar.class),
	 			root.get("montantHTaxe").as(Double.class)
	 			
	 			
	 		
	 				
				
				)).where(whereClause.toArray(new Predicate[] {}));
		
		
		
		
	 	List<Object[]>resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
	       if(resultatEntite != null){
	    	
	    	   for(Object[] element : resultatEntite){
		    		
		    		
		    		FactureVenteEntity entity = new FactureVenteEntity();
			    	
			    	entity.setId((Long) element[0]);
			    	entity.setReference((String) element[1]);
			    	entity.setMontantTTC((Double) element[2]);
			    	entity.setInfoLivraison((String) element[3]);
			    	entity.setDate((Calendar) element[4]);
			    	
			    	entity.setMontantHTaxe((Double) element[5]);
		    		
		    		
		    		resultat.add(FacturePersistanceUtilities.toValue(entity));
		    	}
	    }
	}

    return resultat;
	
	
}

}
