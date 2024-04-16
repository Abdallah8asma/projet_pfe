package com.gpro.consulting.tiers.logistique.persistance.gc.achat.reception.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitSerialisableValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticritereProduitSerialisableValue;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IArticlePersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IProduitPersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IProduitSerialisablePersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.ProduitEntity;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ProduitReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.RechercheMulticritereProduitReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ResultatRechecheBonReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ResultatRechecheProduitReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.RechercheMulticritereDetLivraisonValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.chart.value.ResultBestElementValue;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reception.IProduitReceptionAchatPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reception.entitie.ProduitReceptionAchatEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reception.entitie.ReceptionAchatEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reception.utilities.ReceptionAchatPersistanceUtilities;

/**
 * The Class ProduitReceptionAchatPersistanceImpl.
 * 
 * @author Hamdi Etteieb
 */
@Component
public class ProduitReceptionAchatPersistanceImpl extends AbstractPersistance
		implements IProduitReceptionAchatPersistance {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(ProduitReceptionAchatPersistanceImpl.class);

	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;
	
	private static final String SEPARATOR_NUMERO_SERIE = "&";

	@Autowired
	private IProduitPersistance produitPersistance;
	@Autowired
	private IArticlePersistance articlePersistance;
	

	@Autowired
	private IProduitSerialisablePersistance produitSerialisablePersistance;
	
	private String PERCENT = "%";
	private String PREDICATE_TYPE = "type";
	private String PREDICATE_REFERENCE = "reference";
	private String PREDICATE_REFEXTERNE = "refexterne";
	private String PREDICATE_CLIENT = "partieIntersseId";
	private String PREDICATE_PRODUIT_RECEPTIONS = "listProduitReceptions";
	private String PREDICATE_DATE_INTRODUCTION = "dateIntroduction";
	private String PREDICATE_DATE_LIVRAISON = "dateLivraison";
	private String PREDICATE_PRODUIT = "produit";
	private String PREDICATE_ID = "id";
	private String PREDICATE_QUANTITE = "quantite";
	private String PREDICATE_COUT = "prixTotal";
	private String PREDICATE_IDDEPOT = "idDepot";
	
	
	private String PREDICATE_receptionAchat = "receptionAchat";
	
	
	private String numeroProduit="numero";
	
	private String referenceFournisseurProduit="referenceFournisseur";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.persistance.gc.bonreception.
	 * IProduitReceptionPersistance#create(com.gpro.consulting.tiers.logistique.
	 * coordination.gc.bonreception.value.ProduitReceptionAchatValue)
	 */
	@Override
	public String create(ProduitReceptionAchatValue ProduitReceptionAchatValue) {

		ProduitReceptionAchatEntity entity = (ProduitReceptionAchatEntity) this
				.creer(ReceptionAchatPersistanceUtilities.toEntity(ProduitReceptionAchatValue));

		return entity.getId().toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.persistance.gc.bonreception.
	 * IProduitReceptionPersistance#getById(java.lang.Long)
	 */
	@Override
	public ProduitReceptionAchatValue getById(Long id) {
		ProduitReceptionAchatEntity ProduitReceptionAchatEntity = this.rechercherParId(id,
				ProduitReceptionAchatEntity.class);
		return ReceptionAchatPersistanceUtilities.toValue(ProduitReceptionAchatEntity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.persistance.gc.bonreception.
	 * IProduitReceptionPersistance#update(com.gpro.consulting.tiers.logistique.
	 * coordination.gc.bonreception.value.ProduitReceptionAchatValue)
	 */
	@Override
	public String update(ProduitReceptionAchatValue ProduitReceptionAchatValue) {

		ProduitReceptionAchatEntity entity = (ProduitReceptionAchatEntity) this
				.modifier(ReceptionAchatPersistanceUtilities.toEntity(ProduitReceptionAchatValue));

		return entity.getId().toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.persistance.gc.bonreception.
	 * IProduitReceptionPersistance#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) {

		this.supprimerEntite(ProduitReceptionAchatEntity.class, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.persistance.gc.bonreception.
	 * IProduitReceptionPersistance#getAll()
	 */
	@Override
	public List<ProduitReceptionAchatValue> getAll() {

		List<ProduitReceptionAchatEntity> listEntity = this.lister(ProduitReceptionAchatEntity.class);

		List<ProduitReceptionAchatValue> finalList = new ArrayList<ProduitReceptionAchatValue>();
		for (ProduitReceptionAchatEntity ProduitReceptionAchatEntity : listEntity) {
			finalList.add(ReceptionAchatPersistanceUtilities.toValue(ProduitReceptionAchatEntity));
		}

		Collections.sort(finalList);

		return finalList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance#
	 * getEntityManager()
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * Sets the entity manager.
	 *
	 * @param entityManager
	 *            the new entity manager
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public ResultatRechecheProduitReceptionAchatValue rechercherMultiCritere(
			RechercheMulticritereProduitReceptionAchatValue request) {
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<ProduitReceptionAchatEntity> criteriaQuery = criteriaBuilder.createQuery(ProduitReceptionAchatEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();

		Root<ProduitReceptionAchatEntity> root = criteriaQuery.from(ProduitReceptionAchatEntity.class);

		
			
			Join<ProduitReceptionAchatEntity, ReceptionAchatEntity> jointure_detailRec_BonRecep = root.join(PREDICATE_receptionAchat);
			
			
			
			if (estNonVide(request.getNumeroProduit())) {
				
				Subquery<Long> subQuery = criteriaQuery.subquery(Long.class);
				Root<ProduitEntity> subRoot = subQuery.from(ProduitEntity.class);

				subQuery.select(subRoot.<Long>get("id"));
				subQuery.where(criteriaBuilder.equal(subRoot.get(numeroProduit), request.getNumeroProduit()));
				whereClause.add(criteriaBuilder.in(root.get(PREDICATE_PRODUIT)).value(subQuery));
				
			}
			
			
			
			if (estNonVide(request.getReferenceFournisseurProduit())) {
				
				Subquery<Long> subQuery = criteriaQuery.subquery(Long.class);
				Root<ProduitEntity> subRoot = subQuery.from(ProduitEntity.class);

				subQuery.select(subRoot.<Long>get("id"));
				subQuery.where(criteriaBuilder.equal(subRoot.get(referenceFournisseurProduit), request.getReferenceFournisseurProduit()));
				whereClause.add(criteriaBuilder.in(root.get(PREDICATE_PRODUIT)).value(subQuery));
				
			}
			
			/* type*/
			if (estNonVide(request.getType())) {
				whereClause.add(criteriaBuilder.equal(jointure_detailRec_BonRecep.get(PREDICATE_TYPE), request.getType()));
			}
			
			
			/* Reference */
			if (estNonVide(request.getReference())) {
				Expression<String> path = jointure_detailRec_BonRecep.get(PREDICATE_REFERENCE);
				Expression<String> upper = criteriaBuilder.upper(path);
				Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getReference().toUpperCase() + PERCENT);
				whereClause.add(criteriaBuilder.and(predicate));
			}

			/* Reference Externe */
			if (estNonVide(request.getRefexterne())) {
				Expression<String> path = jointure_detailRec_BonRecep.get(PREDICATE_REFEXTERNE);
				Expression<String> upper = criteriaBuilder.upper(path);
				Predicate predicate = criteriaBuilder.like(upper,
						PERCENT + request.getRefexterne().toUpperCase() + PERCENT);
				whereClause.add(criteriaBuilder.and(predicate));
			}

			/* Client (PartieInteressee) */
			if (estNonVide(request.getPartieInteresseId())) {
				whereClause.add(criteriaBuilder.equal(jointure_detailRec_BonRecep.get(PREDICATE_CLIENT), request.getPartieInteresseId()));
			}

			/* magazin */
			if (request.getIdDepot() != null) {
				whereClause.add(criteriaBuilder.equal(jointure_detailRec_BonRecep.get(PREDICATE_IDDEPOT), request.getIdDepot()));
			}

			/*  Produit */
			if ((request.getProduitId()) != null) {
			
				whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_PRODUIT),
						request.getProduitId()));
			}

		
		
			
			

			/* Data Introduction */
			if (request.getDateIntroductionDu() != null) {
				whereClause.add(criteriaBuilder.greaterThanOrEqualTo(jointure_detailRec_BonRecep.<Calendar> get(PREDICATE_DATE_INTRODUCTION),
						request.getDateIntroductionDu()));
			}

			if (request.getDateIntroductionA() != null) {
				whereClause.add(criteriaBuilder.lessThanOrEqualTo(jointure_detailRec_BonRecep.<Calendar> get(PREDICATE_DATE_INTRODUCTION),
						request.getDateIntroductionA()));
			}
			
			
			if (request.getDateIntroductionStrA() != null) {
				whereClause.add(criteriaBuilder.lessThan(jointure_detailRec_BonRecep.<Calendar> get(PREDICATE_DATE_INTRODUCTION),
						request.getDateIntroductionStrA()));
			}

			/* Data Livraison */
			if (request.getDateLivraisonDu() != null) {
				whereClause.add(criteriaBuilder.greaterThanOrEqualTo(jointure_detailRec_BonRecep.<Calendar> get(PREDICATE_DATE_LIVRAISON),
						request.getDateLivraisonDu()));

			}

			if (request.getDateLivraisonA() != null) {
				whereClause.add(criteriaBuilder.lessThanOrEqualTo(jointure_detailRec_BonRecep.<Calendar> get(PREDICATE_DATE_LIVRAISON),
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
						.add(criteriaBuilder.greaterThanOrEqualTo(jointure_detailRec_BonRecep.<Double> get(PREDICATE_COUT), request.getCoutDu()));
			}

			if (request.getCoutA() != null) {
				whereClause.add(criteriaBuilder.lessThanOrEqualTo(jointure_detailRec_BonRecep.<Double> get(PREDICATE_COUT), request.getCoutA()));
			}
			
			
			
		
	
		
		
		
		
		

		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		List<ProduitReceptionAchatEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

		List<ProduitReceptionAchatValue> listProduitReceptionAchatValue = new ArrayList<ProduitReceptionAchatValue>();

		for (ProduitReceptionAchatEntity entity : resultatEntite) {

			ProduitReceptionAchatValue dto = ReceptionAchatPersistanceUtilities.toValueRecherche(entity);
			
			if(dto.getProduitId() != null) {
				
				dto.setArticleValue(articlePersistance.getArticleParId(dto.getProduitId()));
				
				if (dto.isSerialisable() && dto.getReceptionAchatValue() != null) {
					/*RechercheMulticritereProduitSerialisableValue pRechercheProduitSerialisableMulitCritere = new RechercheMulticritereProduitSerialisableValue();
					pRechercheProduitSerialisableMulitCritere.setProduitId(dto.getProduitId());
					pRechercheProduitSerialisableMulitCritere.setNumBonReception(dto.getReceptionAchatValue().getReference());

					dto.setProduitsSerialisable(produitSerialisablePersistance
							.rechercherProduitSerialisableMultiCritere(pRechercheProduitSerialisableMulitCritere)
							.getProduitSerialisableValues());*/
					
					
					dto.setProduitsSerialisable(getListProduitSerialisableParNumerSeries(dto.getNumeroSeries(),dto.getProduitId()));
						
					
				}
			}
		
			
			
		
			
			
			//Collections.sort(dto.getListProduitReceptions());
			listProduitReceptionAchatValue.add(dto);
		}

		Collections.sort(listProduitReceptionAchatValue);

		ResultatRechecheProduitReceptionAchatValue resultat = new ResultatRechecheProduitReceptionAchatValue();

		resultat.setNombreResultaRechercher(Long.valueOf(listProduitReceptionAchatValue.size()));

		resultat.setListProduitReceptionAchat(listProduitReceptionAchatValue);

		return resultat;
	}
	
	private boolean estNonVide(String val) {
		return val != null && !"".equals(val) &&   !"undefined".equals(val) &&  !"null".equals(val);

	}
	
	private Set<ProduitSerialisableValue> getListProduitSerialisableParNumerSeries(String numeroSeries,Long produitId) {
		
		Set<ProduitSerialisableValue>  list = new HashSet<>();
		
		if(numeroSeries != null) {
			String numero[] = numeroSeries.split(SEPARATOR_NUMERO_SERIE);

			List<String> listNumeroSeries = new ArrayList<>();

			Collections.addAll(listNumeroSeries, numero);

			list = produitSerialisablePersistance.getByNumeroSerieList(listNumeroSeries,produitId);
		}
		
	  return list;
		
	}

	@Override
	public List<ResultBestElementValue> getTop10ArticleAchat(RechercheMulticritereProduitReceptionAchatValue request) {
		List<ResultBestElementValue> resultList=new ArrayList<ResultBestElementValue>();
		
		Date dateDeb=new Date(request.getDateLivraisonDu().getTime().getYear(), request.getDateLivraisonDu().getTime().getMonth(), request.getDateLivraisonDu().getTime().getDate());
	
		
		Date dateA=new Date(request.getDateLivraisonA().getTime().getYear(), request.getDateLivraisonA().getTime().getMonth(), request.getDateLivraisonA().getTime().getDate());
		
		
		//TODO
		String where="";
		
		if (estNonVide(request.getBoutiqueId())) {
			where=" and prod.boutiqueId="+request.getBoutiqueId();
		}
		
		
		 Query vQuery = this.entityManager.createNativeQuery(
			      "select prod.id, prod.designation, sum(rec.quantite) as quantite"
			      +" from gc_produitreceptionachat rec, eb_produit prod "
			      + "where rec.eb_produit_id=prod.id"
			      + " and rec.date_livraison>= '"+dateDeb
			      +"' and rec.date_livraison<='"+dateA
			      +"'"+where
			      + " group by prod.id, prod.designation "
			      + "order by sum(rec.quantite) desc" );
		 
		 
	
		
		

			    List<Object[]> vResult = vQuery.getResultList();
			    
			    int nbResult=10;
			    
			    if(vResult.size()<10)
			    	nbResult=vResult.size();
			    
			    for (int i=0;i<nbResult;i++) {
			    	
			    	ResultBestElementValue produit=new ResultBestElementValue();
			    	produit.setIdProd(vResult.get(i)[0]);	
			    	produit.setDesignation(vResult.get(i)[1]);
			    	
			    	produit.setQte(vResult.get(i)[2]);
			    	
			    	resultList.add(produit);

			    }
		return resultList;
	}
	
	  private boolean estNonVide(Long val) {
			return val != null && !"".equals(val) && !"undefined".equals(val) && !"null".equals(val);
		}

	@Override
	public List<ResultBestElementValue> getTop3Fournisseur(RechercheMulticritereProduitReceptionAchatValue request) {
		
List<ResultBestElementValue> resultList=new ArrayList<ResultBestElementValue>();
		
		Date dateDeb=new Date(request.getDateLivraisonDu().getTime().getYear(), request.getDateLivraisonDu().getTime().getMonth(), request.getDateLivraisonDu().getTime().getDate());
	
		
		Date dateA=new Date(request.getDateLivraisonA().getTime().getYear(), request.getDateLivraisonA().getTime().getMonth(), request.getDateLivraisonA().getTime().getDate());
		
		
		 
		String where="";
		
		if (estNonVide(request.getBoutiqueId())) {
			where=" and frs.boutiqueId="+request.getBoutiqueId();
		}
		
		
		
		
		
		 Query vQuery = this.entityManager.createNativeQuery(
			      "select frs.id, frs.abreviation, sum(rachat.quantite) as quantite"
			      +" from gc_produitreceptionachat rec, gc_receptionachat rachat,pi_partieint frs"
			      + " where frs.id=rachat.pi_pi_id"
			      + " and rec.gc_receptionachat_id=rachat.id"
			      + " and rachat.date_livraison>= '"+dateDeb
			      +"' and rachat.date_livraison<='"+dateA
			      +"'"+where
			      +" group by frs.id, frs.abreviation "
			      + "order by sum(rachat.quantite) desc" );
		 
		 
	
		
		

			    List<Object[]> vResult = vQuery.getResultList();
			    
			    int nbResult=3;
			    
			    if(vResult.size()<3)
			    	nbResult=vResult.size();
			    
			    for (int i=0;i<nbResult;i++) {
			    	
			    	ResultBestElementValue produit=new ResultBestElementValue();
			    	produit.setIdProd(vResult.get(i)[0]);	
			    	produit.setDesignation(vResult.get(i)[1]);
			    	
			    	produit.setQte(vResult.get(i)[2]);
			    	
			    	resultList.add(produit);

			    }
		return resultList;
	}

	@Override
	public List<ResultBestElementValue> getDepenseFournisseur(RechercheMulticritereProduitReceptionAchatValue request) {
		
		List<ResultBestElementValue> resultList=new ArrayList<ResultBestElementValue>();
		
		Date dateDeb=new Date(request.getDateLivraisonDu().getTime().getYear(), request.getDateLivraisonDu().getTime().getMonth(), request.getDateLivraisonDu().getTime().getDate());
	
		
		Date dateA=new Date(request.getDateLivraisonA().getTime().getYear(), request.getDateLivraisonA().getTime().getMonth(), request.getDateLivraisonA().getTime().getDate());
		
		
		//TODO
		String where="";
		
		if (estNonVide(request.getBoutiqueId())) {
			where=" and frs.boutiqueId="+request.getBoutiqueId();
		}
		
		
		
		
		
		 Query vQuery = this.entityManager.createNativeQuery(
			      "select frs.id, frs.abreviation, sum(rachat.montantttc) as montantttc"
			      +" from gc_receptionachat rachat,pi_partieint frs"
			      + " where frs.id=rachat.pi_pi_id"
			      + " and rachat.date_livraison>= '"+dateDeb
			      +"' and rachat.date_livraison<='"+dateA
			      +"'"+where
			      + " group by frs.id, frs.abreviation ");
		 
		 
	
		
		

			    List<Object[]> vResult = vQuery.getResultList();
			    
			   
			    
			    for (int i=0;i<vResult.size();i++) {
			    	
			    	ResultBestElementValue produit=new ResultBestElementValue();
			    	produit.setIdProd(vResult.get(i)[0]);	
			    	produit.setDesignation(vResult.get(i)[1]);
			    	
			    	produit.setMontant(vResult.get(i)[2]);
			    	
			    	resultList.add(produit);

			    }
		return resultList;
	}

}
