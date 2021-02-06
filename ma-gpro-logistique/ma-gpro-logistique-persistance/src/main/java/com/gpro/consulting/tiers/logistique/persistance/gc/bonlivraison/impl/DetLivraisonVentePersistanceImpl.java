package com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.GroupeClientValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IProduitPersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.ProduitEntity;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.IGroupeClientPersistance;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.IPartieInteresseePersistance;
import com.gpro.consulting.tiers.logistique.coordination.fn.report.reporting.RechercheMulticritereFnReportingtValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.DetLivraisonVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.RechercheMulticritereBonLivraisonValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.RechercheMulticritereDetLivraisonValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.ResultatRechecheDetBonLivraisonValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.ResultatRechecheDetailBonLivraisonValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.chart.value.ResultBestElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ResultatRechecheReglementValue;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.IBonLivraisonPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.IDetLivraisonVentePersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie.DetLivraisonVenteEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie.LivraisonVenteEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.utilities.BonLivraisonPersistanceUtilities;

/**
 * Implementation of {@link IBonLivraisonPersistance} interface.
 *  
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */

@Component
public class DetLivraisonVentePersistanceImpl extends AbstractPersistance implements IDetLivraisonVentePersistance{

	private String PREDICATE_transporteur = "transporteur";
	private String PREDICATE_numTelPassager = "numTelPassager";
	private String PREDICATE_emailPassager = "emailPassager";
	private String PREDICATE_adressePassager = "adressePassager";
	
	
	private String PREDICATE_PRODUIT = "produitId";
	private String PREDICATE_numeroOF = "numeroOF";
	
	
	
	
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
	
	private String numeroProduit="numero";
	
	private String referenceFournisseurProduit="referenceFournisseur";
	
	private int MAX_RESULTS = 52;
	
	private String PERCENT = "%";
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private BonLivraisonPersistanceUtilities bonLivraisonPersistanceUtilities;

	@Autowired
	private IProduitPersistance produitPersistance;
	
	@Autowired
	private IPartieInteresseePersistance partieInteresseePersistance;
	
	@Autowired
	private IGroupeClientPersistance groupeClientPersistance;
	
	@Override
	public String create(DetLivraisonVenteValue detLivraisonVenteValue) {
		
		DetLivraisonVenteEntity entity = (DetLivraisonVenteEntity) this.creer(bonLivraisonPersistanceUtilities.toEntity(detLivraisonVenteValue));

	    return entity.getId().toString();
	}

	@Override
	public DetLivraisonVenteValue getById(Long id) {
		
		DetLivraisonVenteEntity detLivraisonVenteEntity = this.rechercherParId(id, DetLivraisonVenteEntity.class);

	    return bonLivraisonPersistanceUtilities.toValue(detLivraisonVenteEntity);
	}

	@Override
	public String update(DetLivraisonVenteValue detLivraisonVenteValue) {
		
		DetLivraisonVenteEntity entity = (DetLivraisonVenteEntity) this.modifier(bonLivraisonPersistanceUtilities.toEntity(detLivraisonVenteValue));

	    return entity.getId().toString();
	}

	@Override
	public void delete(Long id) {
		
		this.supprimerEntite(DetLivraisonVenteEntity.class, id);
	}
	

	public EntityManager getEntityManager() {
		return entityManager;
	}

	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}


	@Override
	public DetLivraisonVenteValue getBylivraisonVenteAndProduit(
			Long livraisonVenteId, Long produitId, String choix) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<DetLivraisonVenteEntity> criteriaQuery = criteriaBuilder.createQuery(DetLivraisonVenteEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		
		Root<DetLivraisonVenteEntity> root = criteriaQuery.from(DetLivraisonVenteEntity.class);

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
	    List <DetLivraisonVenteEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

	    if(resultatEntite.size() >0){
	    	return bonLivraisonPersistanceUtilities.toValue(resultatEntite.get(0));
	    }
	    else return null;

	}

	@Override
	public ResultatRechecheDetailBonLivraisonValue getDetailLivraisonByFnReportingClientProduitDate(
			RechercheMulticritereFnReportingtValue request) {

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<DetLivraisonVenteEntity> criteriaQuery = criteriaBuilder.createQuery(DetLivraisonVenteEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		
		Root<DetLivraisonVenteEntity> root = criteriaQuery.from(DetLivraisonVenteEntity.class);

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
	    	Join<DetLivraisonVenteEntity, LivraisonVenteEntity> jointuredetLiv = root.join(PREDICATE_LIVRAISON_VENTE);
		  	  
	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(jointuredetLiv.<Calendar>get(PREDICATE_DATE), request.getDateMin()));
	    }
	    
		// Set request.dateLivraisonMax on whereClause if not null
	    if (request.getDateMax() != null) {
	    	Join<DetLivraisonVenteEntity, LivraisonVenteEntity> jointuredetLiv = root.join(PREDICATE_LIVRAISON_VENTE);
		  	  
	    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(jointuredetLiv.<Calendar>get(PREDICATE_DATE), request.getDateMax()));
	    }

		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    List <DetLivraisonVenteEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

	    List<DetLivraisonVenteValue> list = new ArrayList<DetLivraisonVenteValue>();
	    
	    for (DetLivraisonVenteEntity entity : resultatEntite) {
	    	DetLivraisonVenteValue dto = bonLivraisonPersistanceUtilities.toValue(entity);
	    	list.add(dto);
	    }

	    ResultatRechecheDetailBonLivraisonValue resultat = new ResultatRechecheDetailBonLivraisonValue();
	    resultat.setNombreResultaRechercher(Long.valueOf(list.size()));
	    resultat.setListDetailLivraison(list);
	    

	    return resultat;

	}

	@Override
	public void setTraitementPU(Long idTraitementFacon, Double nouveauPU, Long idFiche) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<DetLivraisonVenteEntity> criteriaQuery = criteriaBuilder.createQuery(DetLivraisonVenteEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		
		Root<DetLivraisonVenteEntity> root = criteriaQuery.from(DetLivraisonVenteEntity.class);
		//System.out.println("------- set PU traitement detLivraison-------");
		//System.out.println("--- idTraitementFacon ---"+ idTraitementFacon);
		//System.out.println("--- idFiche ---"+ idFiche);
		
		// Set traitementFaconId on whereClause if not null
		if (idTraitementFacon != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_TRAITEMENTFACONID), idTraitementFacon));
		}
				
		// Set ficheId on whereClause if not null
		if (idTraitementFacon != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_FICHEID), idFiche));
		}

		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    List <DetLivraisonVenteEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

	    //System.out.println("------- resultatEntite.size() ---------"+ resultatEntite.size());
	    for (DetLivraisonVenteEntity entity : resultatEntite) {
	    	DetLivraisonVenteValue detLivraisonVenteValue = bonLivraisonPersistanceUtilities.toValue(entity);
	    	System.out.println("------ detLivraisonVenteValue.toString() :before change-------"+ detLivraisonVenteValue.toString());
	    	detLivraisonVenteValue.setPrixUnitaireHT(nouveauPU);
	    	if(detLivraisonVenteValue.getQuantite() != null){
	    		detLivraisonVenteValue.setPrixTotalHT(nouveauPU * detLivraisonVenteValue.getQuantite());
	    	}
	    	
	    	//System.out.println("------ detLivraisonVenteValue.toString() after change -------"+ detLivraisonVenteValue.toString());
	    	this.update(detLivraisonVenteValue);
	    }

	}
	
	
	 /******************************************/	
	   @Override
	   public ResultatRechecheDetBonLivraisonValue getResultatRechercheMcDetLivraisonValue(
						RechercheMulticritereDetLivraisonValue request) {
					
					
		   //System.out.println("inn persistance rech");
					CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
						
						CriteriaQuery<DetLivraisonVenteEntity> criteriaQuery = criteriaBuilder.createQuery(DetLivraisonVenteEntity.class);
						List<Predicate> whereClause = new ArrayList<Predicate>();
						
						Root<DetLivraisonVenteEntity> root = criteriaQuery.from(DetLivraisonVenteEntity.class);

//							
						if (estNonVide(request.getNumeroProduit())) {
							
							Subquery<Long> subQuery = criteriaQuery.subquery(Long.class);
							Root<ProduitEntity> subRoot = subQuery.from(ProduitEntity.class);

							subQuery.select(subRoot.<Long>get("id"));
							subQuery.where(criteriaBuilder.equal(subRoot.get(numeroProduit), request.getNumeroProduit()));
							whereClause.add(criteriaBuilder.in(root.get(PREDICATE_IDPRODUIT)).value(subQuery));
							
						}
						
						
						
						if (estNonVide(request.getReferenceFournisseurProduit())) {
							
							Subquery<Long> subQuery = criteriaQuery.subquery(Long.class);
							Root<ProduitEntity> subRoot = subQuery.from(ProduitEntity.class);

							subQuery.select(subRoot.<Long>get("id"));
							subQuery.where(criteriaBuilder.equal(subRoot.get(referenceFournisseurProduit), request.getReferenceFournisseurProduit()));
							whereClause.add(criteriaBuilder.in(root.get(PREDICATE_IDPRODUIT)).value(subQuery));
							
						}
						
						
						
						if (request.getPartieIntId() != null) {
							Subquery<Long> subQuery = criteriaQuery.subquery(Long.class);
							Root<LivraisonVenteEntity> subRoot = subQuery.from(LivraisonVenteEntity.class);
							
							subQuery.select(subRoot.<Long>get("id"));
							subQuery.where(criteriaBuilder.equal(subRoot.get(PREDICATE_CLIENT), request.getPartieIntId()));
							whereClause.add(criteriaBuilder.in(root.get(PREDICATE_LIVRAISON_VENTE)).value(subQuery));
						}
						
						
						 if (request.getDateDe() != null) {
						    	Join<DetLivraisonVenteEntity, LivraisonVenteEntity> jointuredetLiv = root.join(PREDICATE_LIVRAISON_VENTE);
							  	  
						    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(jointuredetLiv.<Calendar>get(PREDICATE_DATE), request.getDateDe()));
						    }
						    
							// Set request.dateLivraisonMax on whereClause if not null
						    if (request.getDateA() != null) {
						    	Join<DetLivraisonVenteEntity, LivraisonVenteEntity> jointuredetLiv = root.join(PREDICATE_LIVRAISON_VENTE);
							  	  
						    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(jointuredetLiv.<Calendar>get(PREDICATE_DATE), request.getDateA()));
						    }
						    
						    if (request.getDateStrA() != null) {
						    	Join<DetLivraisonVenteEntity, LivraisonVenteEntity> jointuredetLiv = root.join(PREDICATE_LIVRAISON_VENTE);
							  	  
						    	whereClause.add(criteriaBuilder.lessThan(jointuredetLiv.<Calendar>get(PREDICATE_DATE), request.getDateStrA()));
						    }
						
						    if (request.getIdDepot() != null) {
						    	Join<DetLivraisonVenteEntity, LivraisonVenteEntity> jointuredetLiv = root.join(PREDICATE_LIVRAISON_VENTE);
							  	  
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
							
							//System.out.println("inn prix de"+request.getPrixDe());
						   whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Double>get(PREDICATE_PRIX), request.getPrixDe()));
									    
						}							
									
						// Set request.prixA on whereClause if not null
							if (request.getPrixA()!= null) {
							   whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Double>get(PREDICATE_PRIX), request.getPrixA()));
									    
						}
							
							
							// Set request.Transporteur
							if (estNonVide(request.getTransporteur())) {
								
								Join<DetLivraisonVenteEntity, LivraisonVenteEntity> jointuredetLiv = root.join(PREDICATE_LIVRAISON_VENTE);
							  	  
								
								Expression<String> path = jointuredetLiv.get(PREDICATE_transporteur);
								Expression<String> upper =criteriaBuilder.upper(path);
								Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getTransporteur().toUpperCase() + PERCENT);
								whereClause.add(criteriaBuilder.and(predicate));
							}
				   
				// Set request.AdressePassager 
					if (estNonVide(request.getAdressePassager())) {
						
						Join<DetLivraisonVenteEntity, LivraisonVenteEntity> jointuredetLiv = root.join(PREDICATE_LIVRAISON_VENTE);
					  	 
						
						Expression<String> path = jointuredetLiv.get(PREDICATE_adressePassager);
						Expression<String> upper =criteriaBuilder.upper(path);
						Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getAdressePassager().toUpperCase() + PERCENT);
						whereClause.add(criteriaBuilder.and(predicate));
					}
					
					// Set request.NumTelPassager 
					if (estNonVide(request.getNumTelPassager())) {
						
						Join<DetLivraisonVenteEntity, LivraisonVenteEntity> jointuredetLiv = root.join(PREDICATE_LIVRAISON_VENTE);
					  	 
						
						Expression<String> path = jointuredetLiv.get(PREDICATE_numTelPassager);
						Expression<String> upper =criteriaBuilder.upper(path);
						Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getNumTelPassager().toUpperCase() + PERCENT);
						whereClause.add(criteriaBuilder.and(predicate));
					}
					
					// Set request.EmailPassager 
					if (estNonVide(request.getEmailPassager())) {
						
						Join<DetLivraisonVenteEntity, LivraisonVenteEntity> jointuredetLiv = root.join(PREDICATE_LIVRAISON_VENTE);
					  	
						
						Expression<String> path = jointuredetLiv.get(PREDICATE_emailPassager);
						Expression<String> upper =criteriaBuilder.upper(path);
						Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getEmailPassager().toUpperCase() + PERCENT);
						whereClause.add(criteriaBuilder.and(predicate));
					}
							
					//REcherche REF.EXTERNE
					if (estNonVide(request.getNumOF())) {
						Expression<String> path = root.get(PREDICATE_numeroOF);
						Expression<String> upper =criteriaBuilder.upper(path);
						Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getNumOF().toUpperCase() + PERCENT);
						whereClause.add(criteriaBuilder.and(predicate));
					}
							
							

						criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
						
					    List <DetLivraisonVenteEntity> resultatEntite ;
					    //List <DetLivraisonVenteEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
					    
						// If criterias are empty
						if (request.isOptimized()) {
							resultatEntite = this.entityManager.createQuery(criteriaQuery).setMaxResults(MAX_RESULTS).getResultList();

						} else {
							resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
						}
						
					    
					    
					    
					    

					    List<DetLivraisonVenteValue> list = new ArrayList<DetLivraisonVenteValue>();
					    
					    for (DetLivraisonVenteEntity entity : resultatEntite) {
					    	DetLivraisonVenteValue dto = bonLivraisonPersistanceUtilities.toValue(entity);
					    	list.add(dto);
					    }

					    ResultatRechecheDetBonLivraisonValue resultat = new ResultatRechecheDetBonLivraisonValue();
					    resultat.setNombreResultaRechercher(Long.valueOf(list.size()));
					    resultat.setListDetailLivraison(list);
					    
					    //System.out.println("inn persistance rech resultat: "+resultat.getNombreResultaRechercher());

				
		    return resultat;
			
			
		   }
			
	   private boolean estNonVide(String val) {
			return val != null && !"".equals(val) && !"undefined".equals(val) && !"null".equals(val);
		}
	   private boolean estNonVide(Long val) {
			return val != null && !"".equals(val) && !"undefined".equals(val) && !"null".equals(val);
		}

	@Override
	public List<ProduitValue> rechercherTop10Article(RechercheMulticritereDetLivraisonValue request) {
		
		List<ProduitValue> resultList=new ArrayList<ProduitValue>();
		
		Date dateDeb=new Date(request.getDateDe().getTime().getYear(), request.getDateDe().getTime().getMonth(), request.getDateDe().getTime().getDate());
	
		
		Date dateA=new Date(request.getDateA().getTime().getYear(), request.getDateA().getTime().getMonth(), request.getDateA().getTime().getDate());
		
		
		//TODO
		String where="";
		
		if (estNonVide(request.getBoutiqueId())) {
			where=" and det.livraisonVente.boutiqueId="+request.getBoutiqueId();
		}
		
		
	

		 Query vQuery = this.entityManager.createQuery(
			      "select det.produitId, sum(det.quantite)"
			      +" from DetLivraisonVenteEntity det "
			      + "where det.livraisonVente.date>= '"+dateDeb
			      +"' and det.livraisonVente.date<='"+dateA
			      +"'"+where
			      +" group by det.produitId "
			      + "order by sum(det.quantite) desc" );
		 
		

			    List<Object[]> vResult = vQuery.getResultList();
			    
			    int nbResult=10;
			    
			    if(vResult.size()<10)
			    	nbResult=vResult.size();
			    
			    for (int i=0;i<nbResult;i++) {
			    	
			    	ProduitValue produit=new ProduitValue();
			    	produit.setId((Long)vResult.get(i)[0]);	
			    	produit.setDesignation(produitPersistance.rechercheDesignationProduitById((Long)vResult.get(i)[0]));
			    	
			    	produit.setQuantite((Double)vResult.get(i)[1]);
			    	
			    	resultList.add(produit);

			    }
		return resultList;
	}

	@Override
	public List<ResultBestElementValue> rechercherTop10Client(RechercheMulticritereDetLivraisonValue request) {
		
		List<ResultBestElementValue> resultList=new ArrayList<ResultBestElementValue>();
		
		Date dateDeb=new Date(request.getDateDe().getTime().getYear(), request.getDateDe().getTime().getMonth(), request.getDateDe().getTime().getDate());
	
		
		Date dateA=new Date(request.getDateA().getTime().getYear(), request.getDateA().getTime().getMonth(), request.getDateA().getTime().getDate());
		
		
		//TODO
		String where="";
		
		if (estNonVide(request.getBoutiqueId())) {
			where=" and det.livraisonVente.boutiqueId="+request.getBoutiqueId();
		}
		
		
	

		 Query vQuery = this.entityManager.createQuery(
			      "select det.livraisonVente.partieIntId, sum(det.quantite)"
			      +" from DetLivraisonVenteEntity det "
			      + "where det.livraisonVente.date>= '"+dateDeb
			      +"' and det.livraisonVente.date<='"+dateA
			      +"'"+where
			      +" group by det.livraisonVente.partieIntId "
			      + "order by sum(det.quantite) desc" );
		 
		

			    List<Object[]> vResult = vQuery.getResultList();
			    
			    int nbResult=10;
			    
			    if(vResult.size()<10)
			    	nbResult=vResult.size();
			    
			    for (int i=0;i<nbResult;i++) {
			    	
			    	ResultBestElementValue client=new ResultBestElementValue();
			    	client.setId((Long)vResult.get(i)[0]);	
			    	client.setAbreviation(partieInteresseePersistance.rechercheAbreviationPartieInteresseeParId((Long)vResult.get(i)[0]));
			    	
			    	client.setQuantite((Double)vResult.get(i)[1]);
			    	
			    	resultList.add(client);

			    }
		return resultList;
	}

	@Override
	public List<ResultBestElementValue> rechercheChiffreAffaireByClient(
			RechercheMulticritereDetLivraisonValue request) {
		
		
		List<ResultBestElementValue> resultList=new ArrayList<ResultBestElementValue>();
		
		Date dateDeb=new Date(request.getDateDe().getTime().getYear(), request.getDateDe().getTime().getMonth(), request.getDateDe().getTime().getDate());
	
		
		Date dateA=new Date(request.getDateA().getTime().getYear(), request.getDateA().getTime().getMonth(), request.getDateA().getTime().getDate());
		
		
		//TODO
		String where="";
		
		if (estNonVide(request.getBoutiqueId())) {
			where=" and liv.boutiqueId="+request.getBoutiqueId();
		}
		
		
	
		 

		 Query vQuery = this.entityManager.createQuery(
			      "select liv.partieIntId, sum(liv.montantTTC), sum(liv.montantHTaxe)"
			      +" from LivraisonVenteEntity liv "
			      + "where liv.date>= '"+dateDeb
			      +"' and liv.date<='"+dateA
			      +"'"+where
			      +" group by liv.partieIntId ");
		 
		

			    List<Object[]> vResult = vQuery.getResultList();
			    
			   
			    for (int i=0;i<vResult.size();i++) {
			    	
			    	ResultBestElementValue client=new ResultBestElementValue();
			    	client.setId((Long)vResult.get(i)[0]);	
			    	client.setAbreviation(partieInteresseePersistance.rechercheAbreviationPartieInteresseeParId((Long)vResult.get(i)[0]));
			    	
			    	client.setMontantTTC((Double)vResult.get(i)[1]);
			    	client.setMontantHTaxe((Double)vResult.get(i)[2]);
			    	
			    	resultList.add(client);

			    }
		return resultList;
	}

	@Override
	public List<ResultBestElementValue> rechercheChiffreAffaireByCSousFamille(
			RechercheMulticritereDetLivraisonValue request) {
		List<ResultBestElementValue> resultList=new ArrayList<ResultBestElementValue>();
		
		Date dateDeb=new Date(request.getDateDe().getTime().getYear(), request.getDateDe().getTime().getMonth(), request.getDateDe().getTime().getDate());
	
		
		Date dateA=new Date(request.getDateA().getTime().getYear(), request.getDateA().getTime().getMonth(), request.getDateA().getTime().getDate());
		
		
		//TODO
		String where="";
		
		if (estNonVide(request.getBoutiqueId())) {
			where=" and liv.boutiqueId="+request.getBoutiqueId();
		}
		
		
	
	
		 Query vQuery = this.entityManager.createNativeQuery(
			      "select sf.id, sf.designation, sum(liv.montantttc) as montantttc"
			      +" from gc_livraisonvente liv,gc_detlivraisonvente det, eb_produit prod,eb_sousfamilleprod sf "
			      + "where liv.id=det.gc_livvente_id"
			      + " and prod.id=det.eb_produit_id"
			      + " and prod.eb_sfprod_id=sf.id"
			      + " and liv.date>= '"+dateDeb
			      +"' and liv.date<='"+dateA
			      +"'"+where
			      +" group by sf.id, sf.designation ");
		 
		

			    List<Object[]> vResult = vQuery.getResultList();
			    
			   
			    for (int i=0;i<vResult.size();i++) {
			    	
			    	
			    	ResultBestElementValue sousFamille=new ResultBestElementValue();
			    	sousFamille.setId(((Number)vResult.get(i)[0]).longValue());	
			    	//sousFamille.setAbreviation(partieInteresseePersistance.rechercheAbreviationPartieInteresseeParId(((Number)vResult.get(i)[0]).longValue()));
			    	
			    	sousFamille.setAbreviation(((String)vResult.get(i)[1]).toString());
			    	
			    	sousFamille.setMontant(vResult.get(i)[2]);
			    	
			    	//Object ttc= vResult.get(i)[1];
			    	//Object htaxe= vResult.get(i)[2];
			    	
			    	//client.setMontantTTC(((Double) vResult.get(i)[1]));
			    	//client.setMontantHTaxe(((Double)vResult.get(i)[2]));
			    	
			    	resultList.add(sousFamille);

			    }
		return resultList;

	}

	@Override
	public List<ResultBestElementValue> rechercheChiffreAffaireByFamille(
			RechercheMulticritereDetLivraisonValue request) {
		List<ResultBestElementValue> resultList=new ArrayList<ResultBestElementValue>();
		
		Date dateDeb=new Date(request.getDateDe().getTime().getYear(), request.getDateDe().getTime().getMonth(), request.getDateDe().getTime().getDate());
	
		
		Date dateA=new Date(request.getDateA().getTime().getYear(), request.getDateA().getTime().getMonth(), request.getDateA().getTime().getDate());
		
		
		//TODO
		String where="";
		
		if (estNonVide(request.getBoutiqueId())) {
			where=" and liv.boutiqueId="+request.getBoutiqueId();
		}
		
		
	
	

		 Query vQuery = this.entityManager.createNativeQuery(
			      "select fm.id, fm.designation, sum(liv.montantttc) as montantttc"
			      +" from gc_livraisonvente liv,gc_detlivraisonvente det, eb_produit prod,eb_sousfamilleprod sf,eb_familleprod fm "
			      + "where liv.id=det.gc_livvente_id"
			      + " and sf.eb_familleprod_id=fm.id"
			      + " and prod.id=det.eb_produit_id"
			      + " and prod.eb_sfprod_id=sf.id"
			      + " and liv.date>= '"+dateDeb
			      +"' and liv.date<='"+dateA
			      +"'"+where
			      +" group by fm.id, fm.designation ");
		 
		

			    List<Object[]> vResult = vQuery.getResultList();
			    
			   
			    for (int i=0;i<vResult.size();i++) {
			    	
			    	
			    	ResultBestElementValue sousFamille=new ResultBestElementValue();
			    	sousFamille.setId(((Number)vResult.get(i)[0]).longValue());	
			    	
			    	sousFamille.setAbreviation(((String)vResult.get(i)[1]).toString());
			    	
			    	sousFamille.setMontant(vResult.get(i)[2]);
			    	
			    	
			    	resultList.add(sousFamille);

			    }
		return resultList;
	}
	
	
	@Override
	public List<ResultBestElementValue> rechercheChiffreAffaireByGroupe(
			RechercheMulticritereDetLivraisonValue request) {
List<ResultBestElementValue> resultList=new ArrayList<ResultBestElementValue>();
		
		Date dateDeb=new Date(request.getDateDe().getTime().getYear(), request.getDateDe().getTime().getMonth(), request.getDateDe().getTime().getDate());
	
		
		Date dateA=new Date(request.getDateA().getTime().getYear(), request.getDateA().getTime().getMonth(), request.getDateA().getTime().getDate());
		
		
		//TODO
		String where="";
		
		if (estNonVide(request.getBoutiqueId())) {
			where=" and liv.boutiqueId="+request.getBoutiqueId();
		}
		
		
	
		 

		 Query vQuery = this.entityManager.createQuery(
			      "select liv.groupeClientId, sum(liv.montantTTC), sum(liv.montantHTaxe)"
			      +" from LivraisonVenteEntity liv "
			      + "where liv.date>= '"+dateDeb
			      +"' and liv.date<='"+dateA
			      +"' group by liv.groupeClientId ");
		 
		

			    List<Object[]> vResult = vQuery.getResultList();
			    
			    
				    
			   
			    for (int i=0;i<vResult.size();i++) {
			    	
			    	 
			    	
			    	ResultBestElementValue groupe=new ResultBestElementValue();
			    	groupe.setId((Long)vResult.get(i)[0]);	
			    	
			    	
			    	if(vResult.get(i)[0]!=null)
			    	{	 
			    		GroupeClientValue pGroupeClientValue=new GroupeClientValue();
			    		pGroupeClientValue.setId((Long)vResult.get(i)[0]);
			    		groupe.setAbreviation(groupeClientPersistance.rechercheGroupeClientParId(pGroupeClientValue).getDesignation());
			    	}
			    	groupe.setMontantTTC((Double)vResult.get(i)[1]);
			    	groupe.setMontantHTaxe((Double)vResult.get(i)[2]);
			    	
			    	resultList.add(groupe);

			    }
		return resultList;
	}

	@Override
	public List<ResultBestElementValue> rechercherTop10Groupe(RechercheMulticritereDetLivraisonValue request) {
		
		List<ResultBestElementValue> resultList=new ArrayList<ResultBestElementValue>();
		
		Date dateDeb=new Date(request.getDateDe().getTime().getYear(), request.getDateDe().getTime().getMonth(), request.getDateDe().getTime().getDate());
	
		
		Date dateA=new Date(request.getDateA().getTime().getYear(), request.getDateA().getTime().getMonth(), request.getDateA().getTime().getDate());
		
		
		//TODO
		String where="";
		
		if (estNonVide(request.getBoutiqueId())) {
			where=" and det.boutiqueId="+request.getBoutiqueId();
		}
		
		
	

		 Query vQuery = this.entityManager.createQuery(
			      "select det.livraisonVente.groupeClientId, sum(det.quantite)"
			      +" from DetLivraisonVenteEntity det "
			      + "where det.livraisonVente.date>= '"+dateDeb
			      +"' and det.livraisonVente.date<='"+dateA
			      +"' group by det.livraisonVente.groupeClientId "
			      + "order by sum(det.quantite) desc" );
		 
		

			    List<Object[]> vResult = vQuery.getResultList();
			    
			    int nbResult=10;
			    
			    if(vResult.size()<10)
			    	nbResult=vResult.size();
			    
			    for (int i=0;i<nbResult;i++) {
			    	
			    	ResultBestElementValue groupe=new ResultBestElementValue();
			    	if(vResult.get(i)[0]!=null)
			    	{	 
			    		GroupeClientValue pGroupeClientValue=new GroupeClientValue();
			    		pGroupeClientValue.setId((Long)vResult.get(i)[0]);
			    		groupe.setAbreviation(groupeClientPersistance.rechercheGroupeClientParId(pGroupeClientValue).getDesignation());
			    	}
			    	groupe.setQuantite((Double)vResult.get(i)[1]);
			    	
			    	resultList.add(groupe);

			    }
		return resultList;
	}

	@Override
	public DetLivraisonVenteValue getBylivraisonVenteAndOF(Long livraisonVenteId, String numeroOF, String choix) {

		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<DetLivraisonVenteEntity> criteriaQuery = criteriaBuilder.createQuery(DetLivraisonVenteEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		
		Root<DetLivraisonVenteEntity> root = criteriaQuery.from(DetLivraisonVenteEntity.class);

		// Set livraisonVenteId on whereClause if not empty or null
		if (livraisonVenteId != null && estNonVide(numeroOF) && estNonVide(choix)) {
			
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_LIVRAISON_VENTE), livraisonVenteId));
			
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_numeroOF), numeroOF));
			
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CHOIX), choix));
			
			
			
			
			

			criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		    List <DetLivraisonVenteEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

		    if(resultatEntite.size() >0){
		    	return bonLivraisonPersistanceUtilities.toValue(resultatEntite.get(0));
		    }
			
		}
		
	
	     return null;

	}
	
	
	
	
	
	

	
}
