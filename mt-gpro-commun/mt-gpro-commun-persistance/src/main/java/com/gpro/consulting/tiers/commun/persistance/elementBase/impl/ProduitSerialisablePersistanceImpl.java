package com.gpro.consulting.tiers.commun.persistance.elementBase.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.BoutiqueValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitSerialisableValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticritereProduitSerialisableValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ResultatRechecheProduitSerialisableValue;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IBoutiquePersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IProduitSerialisablePersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.ProduitEntity;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.ProduitSerialisableEntity;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.IPartieInteresseePersistance;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.entity.PartieInteresseEntite;
import com.gpro.consulting.tiers.commun.persistance.utilities.PersistanceUtilities;

/**
 * The Class ProduitPersistanceImpl.
 * 
 * @author med
 */

@Component
public class ProduitSerialisablePersistanceImpl extends AbstractPersistance implements IProduitSerialisablePersistance {
	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Autowired
	IPartieInteresseePersistance partieInteresseePersistance;
	
	@Autowired
	private IBoutiquePersistance boutiquePersistance;
	
	 

	private String produitId = "produitId";
	private String fournisseurId = "fournisseurId";
	private String id = "id";
	private String clientId = "clientId";

	private String numBonReception = "numBonReception";
	private String numBonLivraison = "numBonLivraison";
	private String numFacture = "numFacture";

	private String prixAchat = "prixAchat";
	private String prixVente = "prixVente";

	private String dateAchat = "dateAchat";
	private String dateVente = "dateVente";

	private String dateFinGarantie = "dateFinGarantie";

	private String numSerie = "numSerie";
	
	private String boutiqueId = "boutiqueId";
	private String magasinId = "magasinId";
	private String historiqueBTsortie = "historiqueBTsortie";
	private String historiqueBTreception = "historiqueBTreception";

	private String historiqueBSsortie = "historiqueBSsortie";
	
	private String brRetour = "brRetour";
	
	private String numero="numero";
	
	private String referenceFournisseur="referenceFournisseur";
	
	
	// creer produit

	@Override
	public String creerProduitSerialisable(ProduitSerialisableValue pProduitSerialisableValue) {

		ProduitSerialisableEntity vProduitSerialisableEntity = (ProduitSerialisableEntity) this
				.creer(PersistanceUtilities.toEntity(pProduitSerialisableValue));
		ProduitSerialisableValue vProduitSerialisableValueResult = PersistanceUtilities
				.toValue(vProduitSerialisableEntity);
		return vProduitSerialisableValueResult.getId().toString();
	}

	// supprimer produit
	@Override
	public void supprimerProduitSerialisable(Long pProduitId) {
		if (log.isDebugEnabled()) {

			// log.debug("Suppression de la produit d'ID=" + pProduitId.toString() + " est
			// en cours.");

		}
		this.supprimerEntite(ProduitSerialisableEntity.class, pProduitId);
	}

	// modifier produit
	@Override
	public String modifierProduitSerialisable(ProduitSerialisableValue pProduitSerialisableValue) {

		ProduitSerialisableEntity vProduitSerialisableEntity = (ProduitSerialisableEntity) this
				.modifier(PersistanceUtilities.toEntity(pProduitSerialisableValue));
		ProduitSerialisableValue vProduitSerialisableValueResult = PersistanceUtilities
				.toValue(vProduitSerialisableEntity);
		return vProduitSerialisableValueResult.getId().toString();
	}

	// recherche par id
	@Override
	public ProduitSerialisableValue rechercheProduitSerialisableById(Long pProduitId) {
		if (log.isDebugEnabled()) {
			// log.debug("recherche de produit d'id =" +pProduitId + " est en cours.");
		}
		ProduitSerialisableEntity vProduitSerialisableEntity = this.rechercherParId(pProduitId,
				ProduitSerialisableEntity.class);
		ProduitSerialisableValue vProduitSerialisableValueResult = PersistanceUtilities
				.toValue(vProduitSerialisableEntity);
		return vProduitSerialisableValueResult;
	}

	// liste produit
	@Override
	public List<ProduitSerialisableValue> listeProduitSerialisable() {
		List<ProduitSerialisableEntity> vListeProduitsEntity = this.lister(ProduitSerialisableEntity.class);
		List<ProduitSerialisableValue> vListeProduitsValues = new ArrayList<ProduitSerialisableValue>();
		for (ProduitSerialisableEntity vProduitEntite : vListeProduitsEntity) {
			vListeProduitsValues.add(PersistanceUtilities.toValue(vProduitEntite));
		}
		return vListeProduitsValues;
	}

	// entity manager getter
	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * Sets the entity manager.
	 *
	 * @param entityManager the new entity manager
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/** rechereche multi critere */
	@Override
	public ResultatRechecheProduitSerialisableValue rechercherProduitSerialisableMultiCritere(
			RechercheMulticritereProduitSerialisableValue pRechercheProduitMulitCritere) {
		CriteriaBuilder vBuilder = this.entityManager.getCriteriaBuilder();
		/** entity principale **/
		CriteriaQuery<ProduitSerialisableEntity> vCriteriaQuery = vBuilder.createQuery(ProduitSerialisableEntity.class);
		List<Predicate> vWhereClause = new ArrayList<Predicate>();

		/** create liste resultat ***/

		/************ entity jointure *****************/
		Root<ProduitSerialisableEntity> vRootProduit = vCriteriaQuery.from(ProduitSerialisableEntity.class);

		/***************** Predicate *************/
		
		
		
		if (estNonVide(pRechercheProduitMulitCritere.getNumero())) {
			
			Subquery<Long> subQuery = vCriteriaQuery.subquery(Long.class);
			Root<ProduitEntity> subRoot = subQuery.from(ProduitEntity.class);

			subQuery.select(subRoot.<Long>get("id"));
			subQuery.where(vBuilder.equal(subRoot.get(numero), pRechercheProduitMulitCritere.getNumero()));
			vWhereClause.add(vBuilder.in(vRootProduit.get(produitId)).value(subQuery));
			
		}
		
		
		
		if (estNonVide(pRechercheProduitMulitCritere.getReferenceFournisseur())) {
			
			Subquery<Long> subQuery = vCriteriaQuery.subquery(Long.class);
			Root<ProduitEntity> subRoot = subQuery.from(ProduitEntity.class);

			subQuery.select(subRoot.<Long>get("id"));
			subQuery.where(vBuilder.equal(subRoot.get(referenceFournisseur), pRechercheProduitMulitCritere.getReferenceFournisseur()));
			vWhereClause.add(vBuilder.in(vRootProduit.get(produitId)).value(subQuery));
			
		}
		
		
		
		

		if (estNonVide(pRechercheProduitMulitCritere.getId())) {
			vWhereClause.add(vBuilder.equal(vRootProduit.get(id), pRechercheProduitMulitCritere.getId()));
		}

		if (estNonVide(pRechercheProduitMulitCritere.getProduitId())) {
			vWhereClause.add(vBuilder.equal(vRootProduit.get(produitId), pRechercheProduitMulitCritere.getProduitId()));
		}

		if (estNonVide(pRechercheProduitMulitCritere.getNumSerie())) {
			vWhereClause.add(vBuilder.equal(vRootProduit.get(numSerie), pRechercheProduitMulitCritere.getNumSerie()));
		}

		if (estNonVide(pRechercheProduitMulitCritere.getFournisseurId())) {
			vWhereClause.add(
					vBuilder.equal(vRootProduit.get(fournisseurId), pRechercheProduitMulitCritere.getFournisseurId()));
		}

		if (estNonVide(pRechercheProduitMulitCritere.getClientId())) {
			vWhereClause.add(vBuilder.equal(vRootProduit.get(clientId), pRechercheProduitMulitCritere.getClientId()));
		}

		if (estNonVide(pRechercheProduitMulitCritere.getNumBonLivraison())) {
			vWhereClause.add(vBuilder.equal(vRootProduit.get(numBonLivraison),
					pRechercheProduitMulitCritere.getNumBonLivraison()));
		}

		if (estNonVide(pRechercheProduitMulitCritere.getNumFacture())) {
			vWhereClause
					.add(vBuilder.equal(vRootProduit.get(numFacture), pRechercheProduitMulitCritere.getNumFacture()));
		}

		if (estNonVide(pRechercheProduitMulitCritere.getNumBonReception())) {
			vWhereClause.add(vBuilder.equal(vRootProduit.get(numBonReception),
					pRechercheProduitMulitCritere.getNumBonReception()));
		}

		if (estNonVide(pRechercheProduitMulitCritere.getPrixAchat())) {
			vWhereClause.add(vBuilder.equal(vRootProduit.get(prixAchat), pRechercheProduitMulitCritere.getPrixAchat()));
		}

		if (estNonVide(pRechercheProduitMulitCritere.getPrixVente())) {
			vWhereClause.add(vBuilder.equal(vRootProduit.get(prixVente), pRechercheProduitMulitCritere.getPrixVente()));
		}

		if (pRechercheProduitMulitCritere.getDateAchatDe() != null) {
			vWhereClause.add(vBuilder.greaterThanOrEqualTo(vRootProduit.<Calendar>get(dateAchat),
					pRechercheProduitMulitCritere.getDateAchatDe()));
		}

		// Set request.dateIntroductionAu on whereClause if not null
		if (pRechercheProduitMulitCritere.getDateAchatA() != null) {
			vWhereClause.add(vBuilder.lessThanOrEqualTo(vRootProduit.<Calendar>get(dateAchat),
					pRechercheProduitMulitCritere.getDateAchatA()));
		}

		if (pRechercheProduitMulitCritere.getDateVenteDe() != null) {
			vWhereClause.add(vBuilder.greaterThanOrEqualTo(vRootProduit.<Calendar>get(dateVente),
					pRechercheProduitMulitCritere.getDateVenteDe()));
		}

		// Set request.dateIntroductionAu on whereClause if not null
		if (pRechercheProduitMulitCritere.getDateVenteA() != null) {
			vWhereClause.add(vBuilder.lessThanOrEqualTo(vRootProduit.<Calendar>get(dateVente),
					pRechercheProduitMulitCritere.getDateVenteA()));
		}

		if (pRechercheProduitMulitCritere.getDateFinGarantieDe() != null) {
			vWhereClause.add(vBuilder.greaterThanOrEqualTo(vRootProduit.<Calendar>get(dateFinGarantie),
					pRechercheProduitMulitCritere.getDateFinGarantieDe()));
		}

		// Set request.dateIntroductionAu on whereClause if not null
		if (pRechercheProduitMulitCritere.getDateFinGarantieA() != null) {
			vWhereClause.add(vBuilder.lessThanOrEqualTo(vRootProduit.<Calendar>get(dateFinGarantie),
					pRechercheProduitMulitCritere.getDateFinGarantieA()));
		}

		if (estNonVide(pRechercheProduitMulitCritere.getCritereSpeciale())) {

			if (pRechercheProduitMulitCritere.getCritereSpeciale().equals(RechercheMulticritereProduitSerialisableValue.PRODUIT_NON_VENDUE)) {

				vWhereClause.add(vBuilder.isNull(vRootProduit.get(clientId)));
				vWhereClause.add(vBuilder.isNull(vRootProduit.get(historiqueBSsortie)));
				vWhereClause.add(vBuilder.isNull(vRootProduit.get(brRetour)));

			}

		}
		
		
		
		if (estNonVide(pRechercheProduitMulitCritere.getBoutiqueId())) {
			vWhereClause.add(vBuilder.equal(vRootProduit.get(boutiqueId), pRechercheProduitMulitCritere.getBoutiqueId()));
		}
		
		if (estNonVide(pRechercheProduitMulitCritere.getMagasinId())) {
			vWhereClause.add(vBuilder.equal(vRootProduit.get(magasinId), pRechercheProduitMulitCritere.getMagasinId()));
		}
		
		
		if (estNonVide(pRechercheProduitMulitCritere.getHistoriqueBTreception())) {
			vWhereClause.add(vBuilder.equal(vRootProduit.get(historiqueBTreception), pRechercheProduitMulitCritere.getHistoriqueBTreception()));
		}
		
		
		if (estNonVide(pRechercheProduitMulitCritere.getHistoriqueBTsortie())) {
			vWhereClause.add(vBuilder.equal(vRootProduit.get(historiqueBTsortie), pRechercheProduitMulitCritere.getHistoriqueBTsortie()));
		}
		
		

		/*
		 * if( pRechercheProduitMulitCritere.getPrix_inf()!=null ){ Expression<Double>
		 * rootValeur =vRootProduit.get(prixUnitaire);
		 * vWhereClause.add(vBuilder.ge(rootValeur,
		 * pRechercheProduitMulitCritere.getPrix_inf())); } if(
		 * pRechercheProduitMulitCritere.getPrix_sup()!=null ){ Expression<Double>
		 * rootValeur =vRootProduit.get(prixUnitaire);
		 * vWhereClause.add(vBuilder.le(rootValeur,
		 * pRechercheProduitMulitCritere.getPrix_sup())); }
		 */

		/** execute query and do something with result **/

		vCriteriaQuery.select(vRootProduit).where(vWhereClause.toArray(new Predicate[] {}));
		List<ProduitSerialisableEntity> resultatEntite = this.entityManager.createQuery(vCriteriaQuery).getResultList();

		/** Conversion de la liste en valeur */
		List<ProduitSerialisableValue> vListeResultat = new ArrayList<ProduitSerialisableValue>();

		for (ProduitSerialisableEntity vProduitEntite : resultatEntite) {
			ProduitSerialisableValue vPv = PersistanceUtilities.toValue(vProduitEntite);
			if(vProduitEntite.getBoutiqueId()!=null) {
				BoutiqueValue boutique=new BoutiqueValue();
				boutique.setId(vProduitEntite.getBoutiqueId());
				vPv.setBoutiqueAbreviation(boutiquePersistance.rechercheBoutiqueParId(boutique).getAbreviation());
			}
			
			vListeResultat.add(vPv);
		}

		/** retour de list de recherche et le nombre de resultat **/
		ResultatRechecheProduitSerialisableValue vResultatRechecheProduitSerialisableValue = new ResultatRechecheProduitSerialisableValue();

		vResultatRechecheProduitSerialisableValue.setNombreResultaRechercher(Long.valueOf(vListeResultat.size()));

		Collections.sort(vListeResultat);

		vResultatRechecheProduitSerialisableValue.setProduitSerialisableValues(new TreeSet<>(vListeResultat));

		return vResultatRechecheProduitSerialisableValue;

	}

	@SuppressWarnings("unused")
	private boolean estNonVide(String val) {
		return val != null && !"".equals(val);

	}

	@SuppressWarnings("unused")
	private boolean estNonVide(Long val) {
		return val != null;

	}

	@SuppressWarnings("unused")
	private boolean estNonVide(Double val) {
		return val != null;

	}

	/*
	 * @Override public ProduitSerialisableValue
	 * rechercheProduitSerialisableByNumeroSerie(String numSerie) { CriteriaBuilder
	 * vBuilder = this.entityManager.getCriteriaBuilder();
	 *//** entity principale **/
	/*
	 * CriteriaQuery < ProduitSerialisableEntity > vCriteriaQuery =
	 * vBuilder.createQuery(ProduitSerialisableEntity.class); List < Predicate >
	 * vWhereClause = new ArrayList < Predicate >();
	 * 
	 *//** create liste resultat ***/
	/*
	
	*//************ entity jointure *****************/
	/*
	 * Root < ProduitSerialisableEntity > vRootProduit =
	 * vCriteriaQuery.from(ProduitSerialisableEntity.class);
	 * 
	 *//***************** Predicate *************/
	/*
	 * 
	 * if (estNonVide(numSerie)) {
	 * vWhereClause.add(vBuilder.equal(vRootProduit.get(numSerie), numSerie)); }
	 * 
	 *//** execute query and do something with result **/

	/*
	 * 
	 * vCriteriaQuery.select(vRootProduit).where(vWhereClause.toArray(new
	 * Predicate[] {})); ProduitSerialisableEntity resultatEntite =
	 * this.entityManager.createQuery(vCriteriaQuery).getSingleResult();
	 * 
	 *//** Conversion de la liste en valeur *//*
												 * 
												 * ProduitSerialisableValue vPv = null;
												 * 
												 * if(resultatEntite != null) vPv =
												 * PersistanceUtilities.toValue(resultatEntite);
												 * 
												 * 
												 * return vPv;
												 * 
												 * 
												 * 
												 * }
												 */

	@Override
	public ProduitSerialisableValue rechercheProduitSerialisableByNumeroSerieAndProduitId(String numSerie,
			Long idProduit) {
		CriteriaBuilder vBuilder = this.entityManager.getCriteriaBuilder();
		/** entity principale **/
		CriteriaQuery<ProduitSerialisableEntity> vCriteriaQuery = vBuilder.createQuery(ProduitSerialisableEntity.class);
		List<Predicate> vWhereClause = new ArrayList<Predicate>();

		/** create liste resultat ***/

		/************ entity jointure *****************/
		Root<ProduitSerialisableEntity> vRootProduit = vCriteriaQuery.from(ProduitSerialisableEntity.class);

		/***************** Predicate *************/

		if (estNonVide(numSerie) && estNonVide(idProduit)) {

			vWhereClause.add(vBuilder.equal(vRootProduit.get(numSerie), numSerie));

			vWhereClause.add(vBuilder.equal(vRootProduit.get(produitId), idProduit));

			/** execute query and do something with result **/

			vCriteriaQuery.select(vRootProduit).where(vWhereClause.toArray(new Predicate[] {}));
			ProduitSerialisableEntity resultatEntite = this.entityManager.createQuery(vCriteriaQuery).getSingleResult();

			/** Conversion de la liste en valeur */

			ProduitSerialisableValue vPv = null;

			if (resultatEntite != null)
				vPv = PersistanceUtilities.toValue(resultatEntite);

			return vPv;

		}
		
		
		return null;

	}

	/*
	 * @Override public Set<ProduitSerialisableValue>
	 * getByNumeroSerieList(List<String> listNumeroSeries) { CriteriaBuilder
	 * criteriaBuilder = this.entityManager.getCriteriaBuilder();
	 * CriteriaQuery<ProduitSerialisableEntity> criteriaQuery =
	 * criteriaBuilder.createQuery(ProduitSerialisableEntity.class); List<Predicate>
	 * whereClause = new ArrayList<Predicate>(); Root<ProduitSerialisableEntity>
	 * root = criteriaQuery.from(ProduitSerialisableEntity.class);
	 * 
	 * whereClause.add(root.get(numSerie).in(listNumeroSeries));
	 * 
	 * criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	 * 
	 * List<ProduitSerialisableEntity> entityListResult =
	 * this.entityManager.createQuery(criteriaQuery).getResultList();
	 * 
	 * Set<ProduitSerialisableValue> listProduitSerialisableValue = new
	 * TreeSet<ProduitSerialisableValue>();
	 * 
	 * for (ProduitSerialisableEntity entity : entityListResult) {
	 * ProduitSerialisableValue value = PersistanceUtilities.toValue(entity);
	 * listProduitSerialisableValue.add(value); }
	 * 
	 * return listProduitSerialisableValue; }
	 */

	@Override
	public Set<ProduitSerialisableValue> getByNumeroSerieList(List<String> listNumeroSeries, Long idProduit) {
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<ProduitSerialisableEntity> criteriaQuery = criteriaBuilder
				.createQuery(ProduitSerialisableEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<ProduitSerialisableEntity> root = criteriaQuery.from(ProduitSerialisableEntity.class);

		whereClause.add(root.get(numSerie).in(listNumeroSeries));

		whereClause.add(criteriaBuilder.equal(root.get(produitId), idProduit));

		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));

		List<ProduitSerialisableEntity> entityListResult = this.entityManager.createQuery(criteriaQuery)
				.getResultList();

		Set<ProduitSerialisableValue> listProduitSerialisableValue = new TreeSet<ProduitSerialisableValue>();

		for (ProduitSerialisableEntity entity : entityListResult) {
			ProduitSerialisableValue value = PersistanceUtilities.toValue(entity);
			listProduitSerialisableValue.add(value);
		}

		return listProduitSerialisableValue;
	}
	
	@Override
	public Set<ProduitSerialisableValue> getByNumeroSerieListEnrichi(List<String> listNumeroSeries, Long idProduit) {
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<ProduitSerialisableEntity> criteriaQuery = criteriaBuilder
				.createQuery(ProduitSerialisableEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<ProduitSerialisableEntity> root = criteriaQuery.from(ProduitSerialisableEntity.class);

		whereClause.add(root.get(numSerie).in(listNumeroSeries));

		whereClause.add(criteriaBuilder.equal(root.get(produitId), idProduit));

		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));

		List<ProduitSerialisableEntity> entityListResult = this.entityManager.createQuery(criteriaQuery)
				.getResultList();

		Set<ProduitSerialisableValue> listProduitSerialisableValue = new TreeSet<ProduitSerialisableValue>();

		for (ProduitSerialisableEntity entity : entityListResult) {
			ProduitSerialisableValue value = PersistanceUtilities.toValue(entity);
			
			if(value.getClientId()!=null) {
				value.setPartieIntersseDesignation(partieInteresseePersistance.getPartieInteresseById(value.getClientId()).getAbreviation());
			}
			if(value.getFournisseurId()!=null) {
				value.setFournisseurAbreviation(partieInteresseePersistance.getPartieInteresseById(value.getFournisseurId()).getAbreviation());
			}
			listProduitSerialisableValue.add(value);
		}

		return listProduitSerialisableValue;
	}

}
