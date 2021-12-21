package com.gpro.consulting.tiers.logistique.persistance.gc.achat.facture.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.FactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.FactureAchatVue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.RechercheMulticritereFactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.ResultatRechecheFactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.RechercheMulticritereBonReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.vente.facture.value.BLReportElementRecapValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.FactureVenteValue;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.facture.IFactureAchatPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.facture.entitie.FactureAchatEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.facture.utilities.FactureAchatPersistanceUtilities;
import com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture.entitie.FactureVenteEntity;

/**
 * Implementation of {@link IFactureAchatPersistance} interface.
 * 
 * @author Hamdi Etteieb
 * @since 23/09/2018
 *
 */

@Component
public class FactureAchatPersistanceImpl extends AbstractPersistance implements IFactureAchatPersistance {

	private static final Logger logger = LoggerFactory.getLogger(FactureAchatPersistanceImpl.class);

	
	private String PREDICATE_ID_DEPOT = "idDepot";
	private String PREDICATE_NATURE = "nature";
	private String PREDICATE_OBSERVATIONS = "observations";
	
	private String PREDICATE_RAISON = "raison";
	
	
	private String PREDICATE_REFERENCE = "reference";
	private String PREDICATE_CLIENT = "partieIntId";
	private String PREDICATE_DATE = "date";
	private String PREDICATE_DATE_INTRO = "dateIntroduction";
	private String PREDICATE_METRAGE = "metrageTotal";
	private String PREDICATE_PRIX = "montantTTC";
	private String PREDICATE_INFO_LIVRAISON = "infoLivraison";
	private String PREDICATE_TYPE = "type";
	private String PREDICATE_NATURELIVRAISON = "natureLivraison";
	private String PERCENT = "%";
	private String PREDICATE_MARCHE = "idMarche";
	private int FIRST_INDEX = 0;

	private String PREDICATE_INFO_LIVRAISON_EXTERNE = "infoLivraisonExterne";
	
	private String PREDICATE_GROUPE_CLIENT = "groupeClientId";
	private String PREDICATE_BOUTIQUEID = "boutiqueId";
	private String PREDICATE_DECLARE = "declarer";
	private String PREDICATE_ForcerCalculMontant = "forcerCalculMontant";
	
	
	

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private FactureAchatPersistanceUtilities FacturePersistanceUtilities;

	@Override
	public ResultatRechecheFactureAchatValue rechercherMultiCritere(RechercheMulticritereFactureAchatValue request) {

		//logger.info("rechercherMultiCritere");

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();

		CriteriaQuery<FactureAchatEntity> criteriaQuery = criteriaBuilder.createQuery(FactureAchatEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();

		Root<FactureAchatEntity> root = criteriaQuery.from(FactureAchatEntity.class);
		
		if(request.getBoutiqueId() != null) {
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
			Expression<String> upper = criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper,
					PERCENT + request.getReferenceFacture().toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));
		}

		// Set request.referenceBl on whereClause if not empty or null
		if (estNonVide(request.getReferenceBl())) {
			Expression<String> path = root.get(PREDICATE_INFO_LIVRAISON);
			Expression<String> upper = criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper,
					PERCENT + request.getReferenceBl().toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));
		}

		// Added By Ghazi on 23/05/2018

		if (estNonVide(request.getReferenceBlExterne())) {
			Expression<String> path = root.get(PREDICATE_INFO_LIVRAISON_EXTERNE);
			Expression<String> upper = criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper,
					PERCENT + request.getReferenceBlExterne().toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));
		}

		// Set request.partieIntId on whereClause if not null
		if (request.getPartieIntId() != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CLIENT), request.getPartieIntId()));
		}

		// Set request.dateFactureMin on whereClause if not null
		if (request.getDateFactureMin() != null) {
			whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Calendar> get(PREDICATE_DATE),
					request.getDateFactureMin()));
		}

		// Set request.dateFactureMax on whereClause if not null
		if (request.getDateFactureMax() != null) {
			whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Calendar> get(PREDICATE_DATE),
					request.getDateFactureMax()));
		}

		// Set request.metrageMin on whereClause if not null
		if (request.getMetrageMin() != null) {
			whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Double> get(PREDICATE_METRAGE),
					request.getMetrageMin()));
		}

		// Set request.metrageMax on whereClause if not null
		if (request.getMetrageMax() != null) {
			whereClause.add(
					criteriaBuilder.lessThanOrEqualTo(root.<Double> get(PREDICATE_METRAGE), request.getMetrageMax()));
		}

		// Set request.prixMin on whereClause if not null
		if (request.getPrixMin() != null) {
			whereClause
					.add(criteriaBuilder.greaterThanOrEqualTo(root.<Double> get(PREDICATE_PRIX), request.getPrixMin()));
		}

		// Set request.prixMax on whereClause if not null
		if (request.getPrixMax() != null) {
			whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Double> get(PREDICATE_PRIX), request.getPrixMax()));
		}

		// Set request.type on whereClause if not null
		if (request.getType() != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_TYPE), request.getType()));
		}

		// Set request.natureLivraison on whereClause if not empty or null
		if (estNonVide(request.getNatureLivraison())) {
			Expression<String> path = root.get(PREDICATE_NATURELIVRAISON);
			Expression<String> upper = criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper,
					PERCENT + request.getNatureLivraison().toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));
		}

		// Set request.marcheId on whereClause if not null
		if (request.getMarcheId() != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_MARCHE), request.getMarcheId()));
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
 	
		
		//recherche declarer 
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
		
		
		//recherche forcer calcul montant 
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
		
		

		// Set request.referenceBl on whereClause if not empty or null
		if (estNonVide(request.getInfoLivraison())) {
			Expression<String> path = root.get(PREDICATE_INFO_LIVRAISON);
			Expression<String> upper = criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper,
					PERCENT + request.getInfoLivraison().toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));
		}

		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		List<FactureAchatEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

		List<FactureAchatValue> list = new ArrayList<FactureAchatValue>();

		for (FactureAchatEntity entity : resultatEntite) {
			FactureAchatValue dto = FacturePersistanceUtilities.toValue(entity);
			list.add(dto);
		}

		ResultatRechecheFactureAchatValue resultat = new ResultatRechecheFactureAchatValue();
		Collections.sort(list);
		resultat.setNombreResultaRechercher(Long.valueOf(list.size()));
		resultat.setList(new TreeSet<>(list));

		return resultat;
	}

	@Override
	public String createFacture(FactureAchatValue FactureValue) {

		if (!existeNumero(FactureValue)) {
			FactureAchatEntity entity = (FactureAchatEntity) this
					.creer(FacturePersistanceUtilities.toEntity(FactureValue));

			return entity.getId().toString();
		} else
			return "existe";

	}

	@Override
	public FactureAchatValue getFactureById(Long id) {

		FactureAchatEntity bonSortieFiniEntity = this.rechercherParId(id, FactureAchatEntity.class);

		return FacturePersistanceUtilities.toValue(bonSortieFiniEntity);
	}

	@Override
	public String updateFacture(FactureAchatValue FactureValue) {
	
			FactureAchatEntity entity = (FactureAchatEntity) this
					.modifier(FacturePersistanceUtilities.toEntity(FactureValue));

			return entity.getId().toString();
		
	}

	@Override
	public void deleteFacture(Long id) {

		this.supprimerEntite(FactureAchatEntity.class, id);
	}

	@Override
	public List<FactureAchatValue> getAll() {

		List<FactureAchatEntity> listEntity = this.lister(FactureAchatEntity.class);

		return FacturePersistanceUtilities.listFactureAchatEntitytoValue(listEntity);
	}

	private boolean estNonVide(String val) {
		return val != null && !"".equals(val) && !"undefined".equals(val) && !"null".equals(val);
	}
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public FactureAchatValue getFactureByReference(String reference) {

		FactureAchatValue resultat = null;

		// Set reference on whereClause if not empty or null
		if (estNonVide(reference)) {

			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();

			CriteriaQuery<FactureAchatEntity> criteriaQuery = criteriaBuilder.createQuery(FactureAchatEntity.class);
			List<Predicate> whereClause = new ArrayList<Predicate>();

			Root<FactureAchatEntity> root = criteriaQuery.from(FactureAchatEntity.class);

		/*	Expression<String> path = root.get(PREDICATE_REFERENCE);
			Expression<String> upper = criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper, PERCENT + reference.toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));
*/
			
			
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_REFERENCE), reference));
			
			
			criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
			List<FactureAchatEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

			if (resultatEntite != null && resultatEntite.size()>0)
				resultat = FacturePersistanceUtilities.toValue(resultatEntite.get(FIRST_INDEX));
		}

		return resultat;
	}

	@Override
	public List<FactureAchatValue> getByClientId(Long clientId) {

		List<FactureAchatValue> resultat = new ArrayList<FactureAchatValue>();

		// Set clientId on whereClause if not null
		if (clientId != null) {

			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();

			CriteriaQuery<FactureAchatEntity> criteriaQuery = criteriaBuilder.createQuery(FactureAchatEntity.class);
			List<Predicate> whereClause = new ArrayList<Predicate>();

			Root<FactureAchatEntity> root = criteriaQuery.from(FactureAchatEntity.class);

			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CLIENT), clientId));

			criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
			List<FactureAchatEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

			if (resultatEntite != null) {

				for (FactureAchatEntity entity : resultatEntite) {

					resultat.add(FacturePersistanceUtilities.toValue(entity));
				}
			}
		}

		return resultat;
	}

	@Override
	public List<FactureAchatVue> getRefFactureByClientId(Long clientId) {

		List<FactureAchatVue> resultat = new ArrayList<FactureAchatVue>();

		// Set clientId on whereClause if not null
		if (clientId != null) {

			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();

			CriteriaQuery<FactureAchatEntity> criteriaQuery = criteriaBuilder.createQuery(FactureAchatEntity.class);
			List<Predicate> whereClause = new ArrayList<Predicate>();

			Root<FactureAchatEntity> root = criteriaQuery.from(FactureAchatEntity.class);

			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CLIENT), clientId));

			criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
			List<FactureAchatEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

			if (resultatEntite != null) {

				for (FactureAchatEntity entity : resultatEntite) {

					resultat.add(FacturePersistanceUtilities.toVue(entity));
				}
			}
		}

		return resultat;
	}

	@Override
	public List<FactureAchatValue> getProduitElementList(List<String> refFactureList) {

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<FactureAchatEntity> criteriaQuery = criteriaBuilder.createQuery(FactureAchatEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<FactureAchatEntity> root = criteriaQuery.from(FactureAchatEntity.class);

		whereClause.add(root.get(PREDICATE_REFERENCE).in(refFactureList));

		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));

		List<FactureAchatEntity> entityListResult = this.entityManager.createQuery(criteriaQuery).getResultList();

		List<FactureAchatValue> listDetLivraison = new ArrayList<FactureAchatValue>();

		for (FactureAchatEntity entity : entityListResult) {
			FactureAchatValue value = FacturePersistanceUtilities.toValue(entity);
			listDetLivraison.add(value);
		}

		return listDetLivraison;

	}

	@Override
	public boolean existeBL(String referenceBL) {

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<FactureAchatEntity> criteriaQuery = criteriaBuilder.createQuery(FactureAchatEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<FactureAchatEntity> root = criteriaQuery.from(FactureAchatEntity.class);

		Expression<String> path = root.get(PREDICATE_INFO_LIVRAISON);
		Expression<String> upper = criteriaBuilder.upper(path);
		Predicate predicate = criteriaBuilder.like(upper, PERCENT + referenceBL.toUpperCase() + PERCENT);

		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		criteriaQuery.where(criteriaBuilder.and(predicate));

		List<FactureAchatEntity> entityListResult = this.entityManager.createQuery(criteriaQuery).getResultList();

		if (entityListResult.size() != 0) {
			return true;
		}

		return false;
	}

	@Override
	public Double getSommeMontHT(Long PIId, Calendar dateMin, Calendar dateMax) {

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();

		CriteriaQuery<Double> criteriaQuery = criteriaBuilder.createQuery(Double.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();

		Root<FactureAchatEntity> root = criteriaQuery.from(FactureAchatEntity.class);

		if (PIId != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CLIENT), PIId));
		}

		if (dateMin != null && dateMax != null) {
			whereClause.add(criteriaBuilder.between(root.<Calendar> get(PREDICATE_DATE), dateMin, dateMax));
		}

		criteriaQuery.select(criteriaBuilder.sum(root.get("montantTTC").as(Double.class)))
				.where(whereClause.toArray(new Predicate[] {}));

		Double sommeMontHT = this.entityManager.createQuery(criteriaQuery).getSingleResult();

		if (sommeMontHT != null) {
			return sommeMontHT;
		}

		return 0D;

	}

	@Override
	public Double getSommeMontHTFactAvoir(Long PIId, Calendar dateMin, Calendar dateMax) {

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();

		CriteriaQuery<Double> criteriaQuery = criteriaBuilder.createQuery(Double.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();

		Root<FactureAchatEntity> root = criteriaQuery.from(FactureAchatEntity.class);

		if (PIId != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CLIENT), PIId));
		}

		if (dateMin != null && dateMax != null) {
			whereClause.add(criteriaBuilder.between(root.<Calendar> get(PREDICATE_DATE), dateMin, dateMax));
		}

		// TODO : Vérifier la syntaxe du type "AVOIR"
		whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_TYPE), "AVOIR"));
		criteriaQuery.select(criteriaBuilder.sum(root.get("montantTTC").as(Double.class)))
				.where(whereClause.toArray(new Predicate[] {}));

		Double sommeMontHT = this.entityManager.createQuery(criteriaQuery).getSingleResult();

		if (sommeMontHT != null) {
			return sommeMontHT;
		}

		return 0D;

	}

	public boolean existeNumero(FactureAchatValue pFacture) {

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<FactureAchatEntity> criteriaQuery = criteriaBuilder.createQuery(FactureAchatEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<FactureAchatEntity> root = criteriaQuery.from(FactureAchatEntity.class);

		Expression<String> path = root.get(PREDICATE_REFERENCE);
		Expression<String> upper = criteriaBuilder.upper(path);
		Predicate predicate = criteriaBuilder.like(upper, pFacture.getReference().toUpperCase());
		whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_TYPE), pFacture.getType()));
		whereClause.add(predicate);
		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		// criteriaQuery.where(criteriaBuilder.and(predicate));

		List<FactureAchatEntity> entityListResult = this.entityManager.createQuery(criteriaQuery).getResultList();

		if (entityListResult.size() != 0) {

			Long idFacture = entityListResult.get(0).getId();

			if (pFacture.getId() == null && idFacture.equals(pFacture.getId()))
				return false;

			return true;
		}

		return false;
	}

	@Override
	public List<FactureAchatValue> getByFournisseurId(Long fournisseurId) {


		List<FactureAchatValue> resultat = new ArrayList<FactureAchatValue>();

		// Set clientId on whereClause if not null
		if (fournisseurId != null) {

			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();

			CriteriaQuery<FactureAchatEntity> criteriaQuery = criteriaBuilder.createQuery(FactureAchatEntity.class);
			List<Predicate> whereClause = new ArrayList<Predicate>();

			Root<FactureAchatEntity> root = criteriaQuery.from(FactureAchatEntity.class);

			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CLIENT), fournisseurId));

			criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
			List<FactureAchatEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

			if (resultatEntite != null) {

				for (FactureAchatEntity entity : resultatEntite) {

					resultat.add(FacturePersistanceUtilities.toValue(entity));
				}
			}
		}

		return resultat;
	}

	@Override
	public List<FactureAchatValue> getByGroupeFournisseurId(Long groupeClientId) {
		List<FactureAchatValue> resultat = new ArrayList<FactureAchatValue>();

		// Set clientId on whereClause if not null
		if (groupeClientId != null) {
			
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			
			CriteriaQuery<FactureAchatEntity> criteriaQuery = criteriaBuilder.createQuery(FactureAchatEntity.class);
			List<Predicate> whereClause = new ArrayList<Predicate>();
			
			Root<FactureAchatEntity> root = criteriaQuery.from(FactureAchatEntity.class);
			
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_GROUPE_CLIENT), groupeClientId));

			criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		    List <FactureAchatEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
		    
		    if(resultatEntite != null){
		    	
		    	for(FactureAchatEntity entity : resultatEntite){
		    		
		    		resultat.add(FacturePersistanceUtilities.toValue(entity));
		    	}
		    }
		}

	    return resultat;
	}

	@Override
	public BLReportElementRecapValue getDepenseFacturebyMonth(RechercheMulticritereFactureAchatValue request) {
		
		Date dateDeb=new Date(request.getDateFactureMin().getTime().getYear(), request.getDateFactureMin().getTime().getMonth(), request.getDateFactureMin().getTime().getDate());
	
		
		Date dateA=new Date(request.getDateFactureMax().getTime().getYear(), request.getDateFactureMax().getTime().getMonth(), request.getDateFactureMax().getTime().getDate());
		
		
		//TODO
		String where="";
		
		if (estNonVide(request.getBoutiqueId())) {
			where=" and fac.boutiqueId="+request.getBoutiqueId();
		}
		
		
		 
		 Query vQuery = this.entityManager.createQuery(
			      "select sum(fac.montantTTC) as montantttc, sum(fac.montantHTaxe) as montantHTaxe"
			      +" from FactureAchatEntity fac "
			      + "where fac.date>= '"+dateDeb
			      +"' and fac.date<='"+dateA
			      +"' and fac.type= '"+request.getType()+"'"+where );
		 
		

			    List<Object[]> vResult = vQuery.getResultList();
			    
			
			   	
			    	BLReportElementRecapValue resultat=new BLReportElementRecapValue();

			    	resultat.setMontantTTC((Double)vResult.get(0)[0]);

			    	resultat.setMontantHTaxe((Double)vResult.get(0)[1]);
			    	
			    	
			    	if(resultat.getMontantTTC() == null)
			    		resultat.setMontantTTC(0d);
			    	
			    	
			    	if(resultat.getMontantHTaxe() == null)
			    		resultat.setMontantHTaxe(0d);

			   
		return resultat;
	}
	
	
	
	 
	   private boolean estNonVide(Long val) {
			return val != null && !"".equals(val) && !"undefined".equals(val) && !"null".equals(val);
		}
	   

}
