package com.gpro.consulting.tiers.logistique.persistance.gc.achat.reception.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.logistique.coordination.atelier.IConstanteLogistique;
import com.gpro.consulting.tiers.logistique.coordination.gc.IConstanteCommerciale;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.BonReceptionVue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ReceptionAchatFactureVue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.RechercheMulticritereBonReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ResultatRechecheBonReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.LivraisonVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.chart.value.ResultBestElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.vente.facture.value.BLReportElementRecapValue;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.facture.IFactureAchatPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.facture.entitie.FactureAchatEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reception.IReceptionAchatPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reception.entitie.ProduitReceptionAchatEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reception.entitie.ReceptionAchatEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reception.utilities.ReceptionAchatPersistanceUtilities;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie.DetLivraisonVenteEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie.LivraisonVenteEntity;

/**
 * @author Hamdi Etteieb
 * @since 16/09/2018 *
 */

@Component
public class ReceptionAchatPersistanceImpl extends AbstractPersistance implements IReceptionAchatPersistance {

	private static final Logger logger = LoggerFactory.getLogger(ReceptionAchatPersistanceImpl.class);

	private String PERCENT = "%";
	private String PREDICATE_FACTURE = "facture";
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
	private String PREDICATE_GROUPE_CLIENT = "groupeClientId";

	private String PREDICATE_Observations = "observations";

	private String PREDICATE_REFERENCE_BON_COMMANDE = "refCommande";

	private String PREDICATE_TYPE = "type";
	private String PREDICATE_refAvoirRetour = "refAvoirRetour";
	private String PREDICATE_refBL = "refBL";
	private String PREDICATE_BOUTIQUEID = "boutiqueId";

	private int FIRST_INDEX = 0;

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private ReceptionAchatPersistanceUtilities receptionAchatPersistanceUtilities;

	@Autowired
	private IFactureAchatPersistance factureAchatPersistance;

	@Override
	public String create(ReceptionAchatValue bonReceptionValue) {

		// logger.info("----- bonReception create : persistance layer ----------");

		ReceptionAchatEntity receptionAchatEntity = ReceptionAchatPersistanceUtilities.toEntity(bonReceptionValue);
		ReceptionAchatEntity entity = (ReceptionAchatEntity) this.creer(receptionAchatEntity);

		return entity.getId().toString();
	}

	@Override
	public ReceptionAchatValue getById(Long id) {
		ReceptionAchatEntity receptionAchatEntity = this.rechercherParId(id, ReceptionAchatEntity.class);
		return ReceptionAchatPersistanceUtilities.toValue(receptionAchatEntity);
	}

	@Override
	public String update(ReceptionAchatValue bonReceptionValue) {

		ReceptionAchatEntity entity = (ReceptionAchatEntity) this
				.modifier(ReceptionAchatPersistanceUtilities.toEntity(bonReceptionValue));

		return entity.getId().toString();
	}

	@Override
	public void delete(Long id) {

		this.supprimerEntite(ReceptionAchatEntity.class, id);
	}

	@Override
	public List<ReceptionAchatValue> getAll() {

		List<ReceptionAchatEntity> listEntity = this.lister(ReceptionAchatEntity.class);

		List<ReceptionAchatValue> finalList = new ArrayList<ReceptionAchatValue>();
		for (ReceptionAchatEntity receptionAchatEntity : listEntity) {
			finalList.add(ReceptionAchatPersistanceUtilities.toValue(receptionAchatEntity));
		}

		return finalList;
	}

	@Override
	public ResultatRechecheBonReceptionAchatValue rechercherMultiCritere(
			RechercheMulticritereBonReceptionAchatValue request) {

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<ReceptionAchatEntity> criteriaQuery = criteriaBuilder.createQuery(ReceptionAchatEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();

		Root<ReceptionAchatEntity> root = criteriaQuery.from(ReceptionAchatEntity.class);

		
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

		/* Reference Externe */
		if (estNonVide(request.getRefexterne())) {
			Expression<String> path = root.get(PREDICATE_REFEXTERNE);
			Expression<String> upper = criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper,
					PERCENT + request.getRefexterne().toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));
		}

		/* Client (PartieInteressee) */
		if (estNonVide(request.getPartieInteresseId())) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CLIENT), request.getPartieInteresseId()));
		}

		/* magazin */
		if (request.getIdDepot() != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_IDDEPOT), request.getIdDepot()));
		}

		/* Désignation Produit */
		if ((request.getIdProduitParDesignation()) != null) {
			Join<ReceptionAchatEntity, ProduitReceptionAchatEntity> jointureCmdEnPrdCmd = root
					.join(PREDICATE_PRODUIT_RECEPTIONS);
			whereClause.add(criteriaBuilder.equal(jointureCmdEnPrdCmd.get(PREDICATE_PRODUIT).get(PREDICATE_ID),
					request.getIdProduitParDesignation()));
		}

		/* Réf Produit */
		if ((request.getIdProduitParRef()) != null) {
			Join<ReceptionAchatEntity, ProduitReceptionAchatEntity> jointureCmdEnPrdCmd = root
					.join(PREDICATE_PRODUIT_RECEPTIONS);
			whereClause.add(criteriaBuilder.equal(jointureCmdEnPrdCmd.get(PREDICATE_PRODUIT).get(PREDICATE_ID),
					request.getIdProduitParRef()));
		}

		/* Data Introduction */
		if (request.getDateIntroductionDu() != null) {
			whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE_INTRODUCTION),
					request.getDateIntroductionDu()));
		}

		if (request.getDateIntroductionA() != null) {
			whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE_INTRODUCTION),
					request.getDateIntroductionA()));
		}

		/* Data Livraison */
		if (request.getDateLivraisonDu() != null) {
			whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE_LIVRAISON),
					request.getDateLivraisonDu()));

		}

		if (request.getDateLivraisonA() != null) {
			whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE_LIVRAISON),
					request.getDateLivraisonA()));
		}

		/* quantite */
		if (request.getQuantiteDu() != null) {
			whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Double>get(PREDICATE_QUANTITE),
					request.getQuantiteDu()));
		}

		if (request.getQuantiteA() != null) {
			whereClause.add(
					criteriaBuilder.lessThanOrEqualTo(root.<Double>get(PREDICATE_QUANTITE), request.getQuantiteA()));
		}

		/* Cout */
		if (request.getCoutDu() != null) {
			whereClause
					.add(criteriaBuilder.greaterThanOrEqualTo(root.<Double>get(PREDICATE_COUT), request.getCoutDu()));
		}

		if (request.getCoutA() != null) {
			whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Double>get(PREDICATE_COUT), request.getCoutA()));
		}

		/* type */
		if (estNonVide(request.getType())) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_TYPE), request.getType()));
		}

		/* type */
		if (estNonVide(request.getRefAvoirRetour())) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_refAvoirRetour), request.getRefAvoirRetour()));
		}
		if (estNonVide(request.getDeclare())) {
			Expression<Boolean> expression = root.get("facture");
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
		
		

		/* Reception */
		/*
		 * if (estNonVide(request.getFacture())) { Expression<Boolean> expression =
		 * root.get(PREDICATE_FACTURE); switch (request.getFacture()) { case
		 * IConstanteLogistique.YES:
		 * whereClause.add(criteriaBuilder.isTrue(expression)); break; case
		 * IConstanteLogistique.NO:
		 * whereClause.add(criteriaBuilder.isFalse(expression)); break; case
		 * IConstanteLogistique.ALL: break; default: break; } }
		 */

		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		List<ReceptionAchatEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

		List<ReceptionAchatValue> listReceptionAchatValue = new ArrayList<ReceptionAchatValue>();

		for (ReceptionAchatEntity entity : resultatEntite) {

			ReceptionAchatValue dto = ReceptionAchatPersistanceUtilities.toValue(entity);
			Collections.sort(dto.getListProduitReceptions());
			listReceptionAchatValue.add(dto);
		}

		if (estNonVide(request.getFacture())) {

			List<ReceptionAchatValue> tempList = new ArrayList<ReceptionAchatValue>();

			// Reception facturée

			if (request.getFacture().equals(IConstanteLogistique.YES)) {

				for (ReceptionAchatValue recepValue : listReceptionAchatValue) {
					if (factureAchatPersistance.existeBL(recepValue.getReference())) {
						tempList.add(recepValue);
					}
				}

			}
			// Reception non facturée
			else if (request.getFacture().equals(IConstanteLogistique.NO)) {

				for (ReceptionAchatValue recepValue : listReceptionAchatValue) {
					if (!factureAchatPersistance.existeBL(recepValue.getReference())) {
						tempList.add(recepValue);
					}
				}
			}

			listReceptionAchatValue.clear();
			listReceptionAchatValue = tempList;

		}

		Collections.sort(listReceptionAchatValue);

		ResultatRechecheBonReceptionAchatValue resultat = new ResultatRechecheBonReceptionAchatValue();

		resultat.setNombreResultaRechercher(Long.valueOf(listReceptionAchatValue.size()));

		resultat.setListReceptionAchat(listReceptionAchatValue);

		return resultat;
	}

	private boolean estNonVide(String val) {
		return val != null && !"".equals(val) && !"undefined".equals(val);

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
	 * @param entityManager the entityManager to set
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<BonReceptionVue> getReferenceBRByFournisseurId(Long idFournisseur) {

		List<BonReceptionVue> resultat = new ArrayList<BonReceptionVue>();

		// Set clientId on whereClause if not null
		if (idFournisseur != null) {

			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();

			CriteriaQuery<ReceptionAchatEntity> criteriaQuery = criteriaBuilder.createQuery(ReceptionAchatEntity.class);
			List<Predicate> whereClause = new ArrayList<Predicate>();

			Root<ReceptionAchatEntity> root = criteriaQuery.from(ReceptionAchatEntity.class);

			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CLIENT), idFournisseur));

			criteriaQuery.multiselect(root.get("reference")).where(whereClause.toArray(new Predicate[] {}));

			List<ReceptionAchatEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
			if (resultatEntite != null) {

				for (ReceptionAchatEntity entity : resultatEntite) {

					resultat.add(receptionAchatPersistanceUtilities.toVue(entity));
				}
			}
		}

		return resultat;
	}

	@Override
	public List<ReceptionAchatFactureVue> getListBRByFournisseurId(Long idFournisseur) {

		List<ReceptionAchatFactureVue> resultat = new ArrayList<ReceptionAchatFactureVue>();

		// Set clientId on whereClause if not null
		if (idFournisseur != null) {

			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();

			CriteriaQuery<ReceptionAchatEntity> criteriaQuery = criteriaBuilder.createQuery(ReceptionAchatEntity.class);
			List<Predicate> whereClause = new ArrayList<Predicate>();

			Root<ReceptionAchatEntity> root = criteriaQuery.from(ReceptionAchatEntity.class);

			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CLIENT), idFournisseur));

			criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
			// List <LivraisonVenteEntity> resultatEntite =
			// this.entityManager.createQuery(criteriaQuery).getResultList();

			List<ReceptionAchatEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
			if (resultatEntite != null) {

				for (ReceptionAchatEntity entity : resultatEntite) {

					resultat.add(receptionAchatPersistanceUtilities.toFactureVue(entity));
				}
			}
		}

		return resultat;
	}

	@Override
	public List<ReceptionAchatValue> getProduitElementList(List<String> refBonReceptionList) {
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<ReceptionAchatEntity> criteriaQuery = criteriaBuilder.createQuery(ReceptionAchatEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<ReceptionAchatEntity> root = criteriaQuery.from(ReceptionAchatEntity.class);

		whereClause.add(root.get(PREDICATE_REFERENCE).in(refBonReceptionList));

		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));

		List<ReceptionAchatEntity> entityListResult = this.entityManager.createQuery(criteriaQuery).getResultList();

		List<ReceptionAchatValue> listDetLivraison = new ArrayList<ReceptionAchatValue>();

		for (ReceptionAchatEntity entity : entityListResult) {
			ReceptionAchatValue value = receptionAchatPersistanceUtilities.toValue(entity);
			listDetLivraison.add(value);

		}

		return listDetLivraison;
	}

	@Override
	public List<ReceptionAchatValue> getByFournisseurId(Long clientId) {

		List<ReceptionAchatValue> resultat = new ArrayList<ReceptionAchatValue>();

		// Set clientId on whereClause if not null
		if (clientId != null) {

			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();

			CriteriaQuery<ReceptionAchatEntity> criteriaQuery = criteriaBuilder.createQuery(ReceptionAchatEntity.class);
			List<Predicate> whereClause = new ArrayList<Predicate>();

			Root<ReceptionAchatEntity> root = criteriaQuery.from(ReceptionAchatEntity.class);

			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CLIENT), clientId));

			criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
			List<ReceptionAchatEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

			if (resultatEntite != null) {

				for (ReceptionAchatEntity entity : resultatEntite) {

					resultat.add(receptionAchatPersistanceUtilities.toValue(entity));
				}
			}
		}

		return resultat;
	}

	@Override
	public ReceptionAchatValue getByReference(String reference) {
		ReceptionAchatValue resultat = null;

		// Set reference on whereClause if not empty or null
		if (estNonVide(reference)) {

			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();

			CriteriaQuery<ReceptionAchatEntity> criteriaQuery = criteriaBuilder.createQuery(ReceptionAchatEntity.class);
			List<Predicate> whereClause = new ArrayList<Predicate>();

			Root<ReceptionAchatEntity> root = criteriaQuery.from(ReceptionAchatEntity.class);

			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_REFERENCE), reference));

			/*
			 * Expression<String> path = root.get(PREDICATE_REFERENCE); Expression<String>
			 * upper =criteriaBuilder.upper(path); Predicate predicate =
			 * criteriaBuilder.like(upper, PERCENT + reference.toUpperCase() + PERCENT);
			 * whereClause.add(criteriaBuilder.and(predicate));
			 */
			criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
			List<ReceptionAchatEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

			if (resultatEntite != null)
				if (resultatEntite.size() > 0)
					resultat = receptionAchatPersistanceUtilities.toValue(resultatEntite.get(FIRST_INDEX));

		}

		return resultat;
	}

	@Override
	public List<ReceptionAchatValue> getByGroupeFournisseurId(Long groupeClientId) {

		List<ReceptionAchatValue> resultat = new ArrayList<ReceptionAchatValue>();

		// Set clientId on whereClause if not null
		if (groupeClientId != null) {

			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();

			CriteriaQuery<ReceptionAchatEntity> criteriaQuery = criteriaBuilder.createQuery(ReceptionAchatEntity.class);
			List<Predicate> whereClause = new ArrayList<Predicate>();

			Root<ReceptionAchatEntity> root = criteriaQuery.from(ReceptionAchatEntity.class);

			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_GROUPE_CLIENT), groupeClientId));

			criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
			List<ReceptionAchatEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

			if (resultatEntite != null) {

				for (ReceptionAchatEntity entity : resultatEntite) {

					resultat.add(receptionAchatPersistanceUtilities.toValue(entity));
				}
			}
		}

		return resultat;
	}

	@Override
	public boolean existeBC(String reference) {

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<ReceptionAchatEntity> criteriaQuery = criteriaBuilder.createQuery(ReceptionAchatEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<ReceptionAchatEntity> root = criteriaQuery.from(ReceptionAchatEntity.class);

		Expression<String> path = root.get(PREDICATE_REFERENCE_BON_COMMANDE);
		Expression<String> upper = criteriaBuilder.upper(path);
		Predicate predicate = criteriaBuilder.like(upper, PERCENT + reference.toUpperCase() + PERCENT);

		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		criteriaQuery.where(criteriaBuilder.and(predicate));

		List<ReceptionAchatEntity> entityListResult = this.entityManager.createQuery(criteriaQuery).getResultList();

		if (entityListResult.size() != 0) {
			return true;
		}

		return false;
	}

	@Override
	public BLReportElementRecapValue getDepenseBRbyMonth(RechercheMulticritereBonReceptionAchatValue request) {
		
		Date dateDeb=new Date(request.getDateLivraisonDu().getTime().getYear(), request.getDateLivraisonDu().getTime().getMonth(), request.getDateLivraisonDu().getTime().getDate());
	
		
		Date dateA=new Date(request.getDateLivraisonA().getTime().getYear(), request.getDateLivraisonA().getTime().getMonth(), request.getDateLivraisonA().getTime().getDate());
		
		
		//TODO
		String where="";
		
		if (estNonVide(request.getBoutiqueId())) {
			where=" and rec.boutiqueId="+request.getBoutiqueId();
		}
		
		
		 
		 Query vQuery = this.entityManager.createQuery(
			      "select sum(rec.montantTTC) as montantttc, sum(rec.montantHTaxe) as montantHTaxe"
			      +" from ReceptionAchatEntity rec "
			      + "where rec.dateLivraison>= '"+dateDeb
			      +"' and rec.dateLivraison<='"+dateA
			      +"' "+where );
		 
		// System.out.println("dateDeb ### "+dateDeb+" dateFin "+dateA);
		

			    List<Object[]> vResult = vQuery.getResultList();
			    
			 /*   for(int i=0;i<vResult.size();i++) {
					 System.out.println(" ### "+vResult.get(0)[0]+" }}} "+vResult.get(i)[1]);

			    }*/
			    
			   
			   	
			    	BLReportElementRecapValue resultat=new BLReportElementRecapValue();

			    	resultat.setMontantTTC((Double)vResult.get(0)[0]);

			    	resultat.setMontantHTaxe((Double)vResult.get(0)[1]);
			    	

			   
		return resultat;
	}
	
	
	
	 
	   private boolean estNonVide(Long val) {
			return val != null && !"".equals(val) && !"undefined".equals(val) && !"null".equals(val);
		}

	@Override
	public boolean existeBL(String reference) {
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<ReceptionAchatEntity> criteriaQuery = criteriaBuilder.createQuery(ReceptionAchatEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<ReceptionAchatEntity> root = criteriaQuery.from(ReceptionAchatEntity.class);

	
		whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_TYPE), IConstanteCommerciale.RECEPTION_ACHAT_TYPE_RETOUR));
		whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_refBL), reference));

		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		

		List<ReceptionAchatEntity> entityListResult = this.entityManager.createQuery(criteriaQuery).getResultList();

		if (entityListResult.size() != 0) {
			return true;
		}

		return false;
	}

	@Override
	public Double getSommeMontHT(RechercheMulticritereBonReceptionAchatValue request) {
CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<ReceptionAchatEntity> criteriaQuery = criteriaBuilder.createQuery(ReceptionAchatEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		
		Root<ReceptionAchatEntity> root = criteriaQuery.from(ReceptionAchatEntity.class);
		
		// Set request.partieIntId on whereClause if not null
		if (request.getPartieInteresseId() != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CLIENT), request.getPartieInteresseId()));
		}
		
		// Set request.dateLivraisonMin on whereClause if not null
	    if (request.getDateLivraisonDu() != null) {
	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE_LIVRAISON), request.getDateLivraisonDu()));
	    }
	    
		// Set request.dateLivraisonMax on whereClause if not null
	    if (request.getDateLivraisonA() != null) {
	    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE_LIVRAISON), request.getDateLivraisonA()));
	    }
	    
	    //Hajer Amri 09/02/2017
		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    List <ReceptionAchatEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

	    List<ReceptionAchatValue> list = new ArrayList<ReceptionAchatValue>();
	    
	    for (ReceptionAchatEntity entity : resultatEntite) {
	    	ReceptionAchatValue dto = receptionAchatPersistanceUtilities.toValue(entity);
	    	list.add(dto);
	    }
	    
	    Double sommeMontHT=0D;
	    
	 	if(estNonVide(request.getFacture())){
	 			
	 	
			// Livraison non facturée
			 if(request.getFacture().equals("nonFacture")){
				
				for (ReceptionAchatValue livraisonVenteValue : list) {
					if(!factureAchatPersistance.existeBL(livraisonVenteValue.getReference())){
						
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
	public List<BonReceptionVue> getReferenceBRByFournisseurIdDeclarer(Long idFournisseur) {


		List<BonReceptionVue> resultat = new ArrayList<BonReceptionVue>();

		// Set clientId on whereClause if not null
		if (idFournisseur != null) {

			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();

			CriteriaQuery<ReceptionAchatEntity> criteriaQuery = criteriaBuilder.createQuery(ReceptionAchatEntity.class);
			List<Predicate> whereClause = new ArrayList<Predicate>();

			Root<ReceptionAchatEntity> root = criteriaQuery.from(ReceptionAchatEntity.class);

			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CLIENT), idFournisseur));
			
			whereClause.add(criteriaBuilder.equal(root.get("facture"), true));

			criteriaQuery.multiselect(root.get("reference")).where(whereClause.toArray(new Predicate[] {}));

			List<ReceptionAchatEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
			if (resultatEntite != null) {

				for (ReceptionAchatEntity entity : resultatEntite) {

					resultat.add(receptionAchatPersistanceUtilities.toVue(entity));
				}
			}
		}

		return resultat;
	}

}
