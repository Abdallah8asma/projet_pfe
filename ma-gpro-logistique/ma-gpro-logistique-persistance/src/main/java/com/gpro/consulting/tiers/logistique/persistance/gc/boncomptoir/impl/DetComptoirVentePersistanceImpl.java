package com.gpro.consulting.tiers.logistique.persistance.gc.boncomptoir.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.logistique.coordination.gc.boncomptoir.value.DetComptoirVenteValue;
import com.gpro.consulting.logistique.coordination.gc.boncomptoir.value.RechercheMulticritereDetComptoirValue;
import com.gpro.consulting.logistique.coordination.gc.boncomptoir.value.ResultatRechecheDetBonComptoirValue;
import com.gpro.consulting.logistique.coordination.gc.boncomptoir.value.ResultatRechecheDetailBonComptoirValue;
import com.gpro.consulting.tiers.logistique.coordination.fn.report.reporting.RechercheMulticritereFnReportingtValue;
import com.gpro.consulting.tiers.logistique.persistance.gc.boncomptoir.IDetComptoirVentePersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.boncomptoir.entitie.DetComptoirVenteEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.boncomptoir.utilities.BonComptoirPersistanceUtilities;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.IBonLivraisonPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie.LivraisonVenteEntity;

/**
 * Implementation of {@link IBonLivraisonPersistance} interface.
 *  
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */

@Component
public class DetComptoirVentePersistanceImpl extends AbstractPersistance implements IDetComptoirVentePersistance{
	
	private String PREDICATE_PRODUIT = "produitId";
	private String PREDICATE_LIVRAISON_VENTE = "livraisonVente";
	private String PREDICATE_DATE = "date";
	private String PREDICATE_CLIENT = "partieIntId";
	private String PREDICATE_TRAITEMENTFACONID = "traitementFaconId";
	private String PREDICATE_FICHEID = "ficheId";
	private String PREDICATE_CHOIX = "choix";
	//
	private String PREDICATE_IDCLIENT= "idClient";
	//private String PREDICATE_DATE= "date";

	private String PREDICATE_PRIX= "prixTotalHT";
	private String PREDICATE_QTE= "quantite";
	
	private String PREDICATE_IDPRODUIT="produitId";
	private String PREDICATE_IDDEPOT="iddepot";
	
	
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private BonComptoirPersistanceUtilities bonComptoirPersistanceUtilities;
	
	@Override
	public String create(DetComptoirVenteValue detLivraisonVenteValue) {
		
		DetComptoirVenteEntity entity = (DetComptoirVenteEntity) this.creer(bonComptoirPersistanceUtilities.toEntity(detLivraisonVenteValue));

	    return entity.getId().toString();
	}

	@Override
	public DetComptoirVenteValue getById(Long id) {
		
		DetComptoirVenteEntity detLivraisonVenteEntity = this.rechercherParId(id, DetComptoirVenteEntity.class);

	    return bonComptoirPersistanceUtilities.toValue(detLivraisonVenteEntity);
	}

	@Override
	public String update(DetComptoirVenteValue detLivraisonVenteValue) {
		
		DetComptoirVenteEntity entity = (DetComptoirVenteEntity) this.modifier(bonComptoirPersistanceUtilities.toEntity(detLivraisonVenteValue));

	    return entity.getId().toString();
	}

	@Override
	public void delete(Long id) {
		
		this.supprimerEntite(DetComptoirVenteEntity.class, id);
	}
	

	public EntityManager getEntityManager() {
		return entityManager;
	}

	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}


	@Override
	public DetComptoirVenteValue getBylivraisonVenteAndProduit(
			Long livraisonVenteId, Long produitId, String choix) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<DetComptoirVenteEntity> criteriaQuery = criteriaBuilder.createQuery(DetComptoirVenteEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		
		Root<DetComptoirVenteEntity> root = criteriaQuery.from(DetComptoirVenteEntity.class);

		// Set livraisonVenteId on whereClause if not empty or null
		if (livraisonVenteId != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_LIVRAISON_VENTE), livraisonVenteId));
		}
		
		// Set produitId on whereClause if not null
		if (produitId != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_PRODUIT), produitId));
		}
		
		// Set produitId on whereClause if not null
		if (choix != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CHOIX), choix));
		}

		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    List <DetComptoirVenteEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

	    if(resultatEntite.size() >0){
	    	return bonComptoirPersistanceUtilities.toValue(resultatEntite.get(0));
	    }
	    else return null;

	}

	@Override
	public ResultatRechecheDetailBonComptoirValue getDetailComptoirByFnReportingClientProduitDate(
			RechercheMulticritereFnReportingtValue request) {

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<DetComptoirVenteEntity> criteriaQuery = criteriaBuilder.createQuery(DetComptoirVenteEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		
		Root<DetComptoirVenteEntity> root = criteriaQuery.from(DetComptoirVenteEntity.class);

		// Set clientId on whereClause if not empty or null
		if (request.getClientId() != null) {
			Subquery<Long> subQuery = criteriaQuery.subquery(Long.class);
			Root<LivraisonVenteEntity> subRoot = subQuery.from(LivraisonVenteEntity.class);
			
			subQuery.select(subRoot.<Long>get("id"));
			subQuery.where(criteriaBuilder.equal(subRoot.get(PREDICATE_CLIENT), request.getClientId()));
			whereClause.add(criteriaBuilder.in(root.get(PREDICATE_LIVRAISON_VENTE)).value(subQuery));
		}
		
		// Set produitId on whereClause if not null
		if (request.getProduitId() != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_PRODUIT), request.getProduitId()));
		}
				
		// Set request.dateLivraisonMin on whereClause if not null
	    if (request.getDateMin() != null) {
	    	Join<DetComptoirVenteEntity, LivraisonVenteEntity> jointuredetLiv = root.join(PREDICATE_LIVRAISON_VENTE);
		  	  
	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(jointuredetLiv.<Calendar>get(PREDICATE_DATE), request.getDateMin()));
	    }
	    
		// Set request.dateLivraisonMax on whereClause if not null
	    if (request.getDateMax() != null) {
	    	Join<DetComptoirVenteEntity, LivraisonVenteEntity> jointuredetLiv = root.join(PREDICATE_LIVRAISON_VENTE);
		  	  
	    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(jointuredetLiv.<Calendar>get(PREDICATE_DATE), request.getDateMax()));
	    }

		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    List <DetComptoirVenteEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

	    List<DetComptoirVenteValue> list = new ArrayList<DetComptoirVenteValue>();
	    
	    for (DetComptoirVenteEntity entity : resultatEntite) {
	    	DetComptoirVenteValue dto = bonComptoirPersistanceUtilities.toValue(entity);
	    	list.add(dto);
	    }

	    ResultatRechecheDetailBonComptoirValue resultat = new ResultatRechecheDetailBonComptoirValue();
	    resultat.setNombreResultaRechercher(Long.valueOf(list.size()));
	    resultat.setListDetailComptoire(list);
	    

	    return resultat;

	}

	@Override
	public void setTraitementPU(Long idTraitementFacon, Double nouveauPU, Long idFiche) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<DetComptoirVenteEntity> criteriaQuery = criteriaBuilder.createQuery(DetComptoirVenteEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		
		Root<DetComptoirVenteEntity> root = criteriaQuery.from(DetComptoirVenteEntity.class);
		//System.out.println("------- set PU traitement detLivraison-------");
		//System.out.println("--- idTraitementFacon ---"+ idTraitementFacon);
	//	System.out.println("--- idFiche ---"+ idFiche);
		
		// Set traitementFaconId on whereClause if not null
		if (idTraitementFacon != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_TRAITEMENTFACONID), idTraitementFacon));
		}
				
		// Set ficheId on whereClause if not null
		if (idTraitementFacon != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_FICHEID), idFiche));
		}

		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    List <DetComptoirVenteEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

	//    System.out.println("------- resultatEntite.size() ---------"+ resultatEntite.size());
	    for (DetComptoirVenteEntity entity : resultatEntite) {
	    	DetComptoirVenteValue detLivraisonVenteValue = bonComptoirPersistanceUtilities.toValue(entity);
	  //  	System.out.println("------ detLivraisonVenteValue.toString() :before change-------"+ detLivraisonVenteValue.toString());
	    	detLivraisonVenteValue.setPrixUnitaireHT(nouveauPU);
	    	if(detLivraisonVenteValue.getQuantite() != null){
	    		detLivraisonVenteValue.setPrixTotalHT(nouveauPU * detLivraisonVenteValue.getQuantite());
	    	}
	    	
	   // 	System.out.println("------ detLivraisonVenteValue.toString() after change -------"+ detLivraisonVenteValue.toString());
	    	this.update(detLivraisonVenteValue);
	    }

	}
	
	
	 /******************************************/	
	   @Override
	   public ResultatRechecheDetBonComptoirValue getResultatRechercheMcDetComptoirValue(
						RechercheMulticritereDetComptoirValue request) {
					
					
		 //  System.out.println("inn persistance rech");
					CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
						
						CriteriaQuery<DetComptoirVenteEntity> criteriaQuery = criteriaBuilder.createQuery(DetComptoirVenteEntity.class);
						List<Predicate> whereClause = new ArrayList<Predicate>();
						
						Root<DetComptoirVenteEntity> root = criteriaQuery.from(DetComptoirVenteEntity.class);

//					
						
						
						if (request.getPartieIntId() != null) {
							Subquery<Long> subQuery = criteriaQuery.subquery(Long.class);
							Root<LivraisonVenteEntity> subRoot = subQuery.from(LivraisonVenteEntity.class);
							
							subQuery.select(subRoot.<Long>get("id"));
							subQuery.where(criteriaBuilder.equal(subRoot.get(PREDICATE_CLIENT), request.getPartieIntId()));
							whereClause.add(criteriaBuilder.in(root.get(PREDICATE_LIVRAISON_VENTE)).value(subQuery));
						}
						
						
						 if (request.getDateDe() != null) {
						    	Join<DetComptoirVenteEntity, LivraisonVenteEntity> jointuredetLiv = root.join(PREDICATE_LIVRAISON_VENTE);
							  	  
						    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(jointuredetLiv.<Calendar>get(PREDICATE_DATE), request.getDateDe()));
						    }
						    
							// Set request.dateLivraisonMax on whereClause if not null
						    if (request.getDateA() != null) {
						    	Join<DetComptoirVenteEntity, LivraisonVenteEntity> jointuredetLiv = root.join(PREDICATE_LIVRAISON_VENTE);
							  	  
						    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(jointuredetLiv.<Calendar>get(PREDICATE_DATE), request.getDateA()));
						    }
						
						    if (request.getIdDepot() != null) {
						    	Join<DetComptoirVenteEntity, LivraisonVenteEntity> jointuredetLiv = root.join(PREDICATE_LIVRAISON_VENTE);
							  	  
						    	whereClause.add(criteriaBuilder.equal(jointuredetLiv.<Long>get(PREDICATE_IDDEPOT), request.getIdDepot()));
						    }
							
												
						
						// Set request.QteDe on whereClause if not null
						if (request.getQteDe() != null) {
							whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Double>get(PREDICATE_QTE), request.getQteDe()));
						}
											
						// Set request.QteA on whereClause if not null
						if (request.getQteA() != null) {
						    whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Double>get(PREDICATE_QTE), request.getQteA()));
						    
						}
						
						// Set request.iddepot on whereClause if not null
									
					
						// Set request.idproduit on whereClause if not null
						if (request.getIdProduit()!= null) {
						    whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_IDPRODUIT), request.getIdProduit()));
						    
						}	
						
						
						// Set request.prixde on whereClause if not null
						if (request.getPrixDe()!= null) {
							
					//		System.out.println("inn prix de"+request.getPrixDe());
						   whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Double>get(PREDICATE_PRIX), request.getPrixDe()));
									    
						}							
									
						// Set request.prixA on whereClause if not null
							if (request.getPrixA()!= null) {
							   whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Double>get(PREDICATE_PRIX), request.getPrixA()));
									    
						}

						criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
					    List <DetComptoirVenteEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

					    List<DetComptoirVenteValue> list = new ArrayList<DetComptoirVenteValue>();
					    
					    for (DetComptoirVenteEntity entity : resultatEntite) {
					    	DetComptoirVenteValue dto = bonComptoirPersistanceUtilities.toValue(entity);
					    	list.add(dto);
					    }

					    ResultatRechecheDetBonComptoirValue resultat = new ResultatRechecheDetBonComptoirValue();
					    resultat.setNombreResultaRechercher(Long.valueOf(list.size()));
					    resultat.setListDetailComptoir(list);
					    
					  //  System.out.println("inn persistance rech resultat: "+resultat.getNombreResultaRechercher());

				
		    return resultat;
			
			
		   }
			

	

	
}
