package com.gpro.consulting.tiers.logistique.persistance.atelier.mise.impl;

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
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IProduitPersistance;
import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.ElementRechecheMiseValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.MiseValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.RechercheMulticritereMiseValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.ResultatRechercheMiseValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.TraitementMiseValue;
import com.gpro.consulting.tiers.logistique.persistance.atelier.mise.IMisePersistance;
import com.gpro.consulting.tiers.logistique.persistance.atelier.mise.entity.MiseEntity;
import com.gpro.consulting.tiers.logistique.persistance.atelier.mise.entity.TraitementMiseEntity;
import com.gpro.consulting.tiers.logistique.persistance.atelier.mise.utilities.MisePersistanceUtilities;

/**
 * Implémentation des méthodes CRUD du Bon de reception
 * 
 * @author $Author: rkhaskhoussy $
 * @version $Revision: 0 $
 */

@Component
public class MisePersistanceImpl extends AbstractPersistance implements
		IMisePersistance {
	
	private String PREDICATE_REF_MISE = "reference";
	private String PREDICATE_REF_BON_RECEPTION = "refBonreception";
	

	private String PREDICATE_MACHINE = "machine";
	private String PREDICATE_typeEtiquette = "typeEtiquette";

	/** EntityManager. */
	@PersistenceContext
	private EntityManager entityManager;

	/** Utilitaire de persistance */
	@Autowired
	private MisePersistanceUtilities vPersistanceUtilities;

	
	@Autowired
	private IProduitPersistance produitPersistance;
	
	
	/** Logger */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(MisePersistanceImpl.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String creerMise(MiseValue pMiseValue) {
		MiseEntity vMiseEntity = (MiseEntity) this.creer(vPersistanceUtilities
				.toEntity(pMiseValue));

		return vMiseEntity.getId().toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void supprimerMise(Long pId) {
		this.supprimerEntite(MiseEntity.class, pId.longValue());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String modifierMise(MiseValue pMise) {
		MiseEntity vMiseEntity = (MiseEntity) this
				.modifier(vPersistanceUtilities.toEntity(pMise));

		return vMiseEntity.getId().toString();

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MiseValue rechercheMiseParId(Long pId) {

		MiseEntity vMiseEntity = this.rechercherParId(pId.longValue(),
				MiseEntity.class);

		MiseValue vMiseValueResultat = vPersistanceUtilities
				.toValue(vMiseEntity);
		return vMiseValueResultat;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResultatRechercheMiseValue rechercherMiseMultiCritere(
			RechercheMulticritereMiseValue pRechercheMiseMulitCritere) {

		/** Création du Criteria */
		CriteriaBuilder vCriteriaBuilder = this.entityManager
				.getCriteriaBuilder();

		/** entity principale : Mise **/
		CriteriaQuery<MiseEntity> vCriteriaQuery = vCriteriaBuilder
				.createQuery(MiseEntity.class);

		Root<MiseEntity> vMiseRoot = vCriteriaQuery.from(MiseEntity.class);

		/** Liste des Prédicats : Date introduction et Client */
		List<Predicate> vWhereClause = new ArrayList<Predicate>();

		// Critère Client
		if (pRechercheMiseMulitCritere.getClient() != null) {
			vWhereClause.add(vCriteriaBuilder.equal(
					vMiseRoot.get("partieintId"),
					pRechercheMiseMulitCritere.getClient()));
		}

		// Critère Date introduction
		/** execute query and do something with result **/
		if (pRechercheMiseMulitCritere.getDateIntroduction() != null) {
			Expression<Calendar> dateIntroduction = vMiseRoot
					.get("dateIntroduction");

			vWhereClause.add(vCriteriaBuilder.equal(dateIntroduction,
					pRechercheMiseMulitCritere.getDateIntroduction()));
		}
		
		
		
		if (pRechercheMiseMulitCritere.getStatutList() != null
				&& pRechercheMiseMulitCritere.getStatutList().size() > 0) {

			vWhereClause.add(vMiseRoot.get("statut").in(pRechercheMiseMulitCritere.getStatutList()));

		}

		// machine

		if (estNonVide(pRechercheMiseMulitCritere.getMachine())) {
			vWhereClause.add(
					vCriteriaBuilder.equal(vMiseRoot.get(PREDICATE_MACHINE), pRechercheMiseMulitCritere.getMachine()));
		}

		// type etiquete

		if (estNonVide(pRechercheMiseMulitCritere.getTypeEtiquette())) {
			vWhereClause.add(vCriteriaBuilder.equal(vMiseRoot.get(PREDICATE_typeEtiquette),
					pRechercheMiseMulitCritere.getTypeEtiquette()));
		}

		// Critère Client

		if (pRechercheMiseMulitCritere.getProduitId() != null) {
			vWhereClause
					.add(vCriteriaBuilder.equal(vMiseRoot.get("produitId"), pRechercheMiseMulitCritere.getProduitId()));
		}

		if (pRechercheMiseMulitCritere.getClient() != null && !pRechercheMiseMulitCritere.getClient().equals("")) {
			vWhereClause
					.add(vCriteriaBuilder.equal(vMiseRoot.get("partieintId"), pRechercheMiseMulitCritere.getClient()));
		}

		if (pRechercheMiseMulitCritere.getReferenceProduit() != null
				&& !pRechercheMiseMulitCritere.getReferenceProduit().equals("")) {

			// System.out.println("#####################################"+pRechercheMiseMulitCritere.getReferenceProduit()
			// );

			ProduitValue produit = produitPersistance
					.rechercheProduitParReference(pRechercheMiseMulitCritere.getReferenceProduit());

			if (produit != null) {
				// System.out.println("#####################################ID ====
				// "+produit.getId());
				vWhereClause.add(vCriteriaBuilder.equal(vMiseRoot.get("produitId"), produit.getId()));
			} else {
				vWhereClause.add(vCriteriaBuilder.equal(vMiseRoot.get("produitId"), -1));
			}

		}
		if (pRechercheMiseMulitCritere.getDateIntroductionDE() != null
				&& !pRechercheMiseMulitCritere.getDateIntroductionDE().equals("")) {
			vWhereClause.add(vCriteriaBuilder.greaterThanOrEqualTo(vMiseRoot.<Calendar>get("dateIntroduction"),
					pRechercheMiseMulitCritere.getDateIntroductionDE()));
		}
		if (pRechercheMiseMulitCritere.getDateIntroductionA() != null
				&& !pRechercheMiseMulitCritere.getDateIntroductionA().equals("")) {
			vWhereClause.add(vCriteriaBuilder.lessThanOrEqualTo(vMiseRoot.<Calendar>get("dateIntroduction"),
					pRechercheMiseMulitCritere.getDateIntroductionA()));
		}
		if (pRechercheMiseMulitCritere.getDateLivraisonDE() != null
				&& !pRechercheMiseMulitCritere.getDateLivraisonDE().equals("")) {
			vWhereClause.add(vCriteriaBuilder.greaterThanOrEqualTo(vMiseRoot.<Calendar>get("dateFin"),
					pRechercheMiseMulitCritere.getDateLivraisonDE()));
		}
		if (pRechercheMiseMulitCritere.getDateLivraisonA() != null
				&& !pRechercheMiseMulitCritere.getDateLivraisonA().equals("")) {
			vWhereClause.add(vCriteriaBuilder.lessThanOrEqualTo(vMiseRoot.<Calendar>get("dateFin"),
					pRechercheMiseMulitCritere.getDateLivraisonA()));
		}

		if (pRechercheMiseMulitCritere.getQuantiteDE() != null
				&& !pRechercheMiseMulitCritere.getQuantiteDE().equals("")) {
			Expression<Double> rootValeur = vMiseRoot.get("quantite");
			vWhereClause.add(vCriteriaBuilder.ge(rootValeur, pRechercheMiseMulitCritere.getQuantiteDE()));

		}

		if (pRechercheMiseMulitCritere.getDateLivraisonA() != null
				&& !pRechercheMiseMulitCritere.getDateLivraisonA().equals("")) {

			Expression<Double> rootValeur = vMiseRoot.get("quantite");
			vWhereClause.add(vCriteriaBuilder.le(rootValeur, pRechercheMiseMulitCritere.getQuantiteDE()));
		}

		if (pRechercheMiseMulitCritere.getReferenceOF() != null
				&& !pRechercheMiseMulitCritere.getReferenceOF().equals("")) {
			vWhereClause.add(
					vCriteriaBuilder.equal(vMiseRoot.get("reference"), pRechercheMiseMulitCritere.getReferenceOF()));
		}
		// Ajout Ghazi 20/0/2017
		// Critère Reference Mise
		/** execute query and do something with result **/
		if (pRechercheMiseMulitCritere.getReferenceMise() != null
				&& !pRechercheMiseMulitCritere.getReferenceMise().equals("")) {
			vWhereClause.add(
					vCriteriaBuilder.equal(vMiseRoot.get("reference"), pRechercheMiseMulitCritere.getReferenceMise()));
		}

		if (pRechercheMiseMulitCritere.getType() != null && !pRechercheMiseMulitCritere.getType().equals("")) {
			vWhereClause.add(vCriteriaBuilder.equal(vMiseRoot.get("typeOF"), pRechercheMiseMulitCritere.getType()));
		}

		if (pRechercheMiseMulitCritere.getStatut() != null && !pRechercheMiseMulitCritere.getStatut().equals("")) {
			vWhereClause.add(vCriteriaBuilder.equal(vMiseRoot.get("statut"), pRechercheMiseMulitCritere.getStatut()));
		}

		if (pRechercheMiseMulitCritere.getDateDebutProductionDe() != null
				&& !pRechercheMiseMulitCritere.getDateDebutProductionDe().equals("")) {
			vWhereClause.add(vCriteriaBuilder.greaterThanOrEqualTo(vMiseRoot.<Calendar>get("dateDebutProduction"),
					pRechercheMiseMulitCritere.getDateDebutProductionDe()));
		}
		if (pRechercheMiseMulitCritere.getDateDebutProductionA() != null
				&& !pRechercheMiseMulitCritere.getDateDebutProductionA().equals("")) {
			vWhereClause.add(vCriteriaBuilder.lessThanOrEqualTo(vMiseRoot.<Calendar>get("dateDebutProduction"),
					pRechercheMiseMulitCritere.getDateDebutProductionA()));
		}

		if (pRechercheMiseMulitCritere.getDateFinProductionDe() != null
				&& !pRechercheMiseMulitCritere.getDateFinProductionDe().equals("")) {
			vWhereClause.add(vCriteriaBuilder.greaterThanOrEqualTo(vMiseRoot.<Calendar>get("dateFinProduction"),
					pRechercheMiseMulitCritere.getDateFinProductionDe()));
		}
		if (pRechercheMiseMulitCritere.getDateFinProductionA() != null
				&& !pRechercheMiseMulitCritere.getDateFinProductionA().equals("")) {
			vWhereClause.add(vCriteriaBuilder.lessThanOrEqualTo(vMiseRoot.<Calendar>get("dateFinProduction"),
					pRechercheMiseMulitCritere.getDateFinProductionA()));
		}

		if (estNonVide(pRechercheMiseMulitCritere.getEtatProduced())) {

			switch (pRechercheMiseMulitCritere.getEtatProduced()) {

			case "0":
				vWhereClause.add(vCriteriaBuilder.equal(vMiseRoot.<Long>get("qteProduite"), 0l));
				break;

			case "+":
				vWhereClause.add(
						vCriteriaBuilder.gt(vMiseRoot.<Long>get("qteProduite"), vMiseRoot.<Double>get("quantite")));
				break;

			case "-":
				vWhereClause.add(
						vCriteriaBuilder.lt(vMiseRoot.<Long>get("qteProduite"), vMiseRoot.<Double>get("quantite")));

				break;
				
				
			case "5PERCENT":
				//Expression<Long> path = vMiseRoot.get("qteProduite");
				
				//Expression<Long> upper = vCriteriaBuilder.g;
				
				
				
				vWhereClause.add(
						vCriteriaBuilder.lt(vMiseRoot.<Long>get("qteProduite"), vMiseRoot.<Double>get("quantite")));
				
				
				
				

				break;	
				

			case "=":
				vWhereClause.add(
						vCriteriaBuilder.equal(vMiseRoot.<Long>get("qteProduite"), vMiseRoot.<Double>get("quantite")));
				break;
			case "EN_COURS_PRODUCTION":
				vWhereClause.add(vCriteriaBuilder.gt(vMiseRoot.<Long>get("qteProduite"), 0));
				vWhereClause
						.add(vCriteriaBuilder.lt(vMiseRoot.<Long>get("qteProduite"), vMiseRoot.<Long>get("quantite")));
				break;

			}

		}

		if (estNonVide(pRechercheMiseMulitCritere.getEtatShipped())) {

			switch (pRechercheMiseMulitCritere.getEtatShipped()) {

			case "0":
				vWhereClause.add(vCriteriaBuilder.equal(vMiseRoot.get("qteExpedition"), 0));
				break;

			case "+":
				vWhereClause.add(
						vCriteriaBuilder.gt(vMiseRoot.<Long>get("qteExpedition"), vMiseRoot.<Long>get("quantite")));
				break;

			case "-":
				vWhereClause.add(
						vCriteriaBuilder.lt(vMiseRoot.<Long>get("qteExpedition"), vMiseRoot.<Long>get("quantite")));
				break;

			case "=":
				vWhereClause.add(
						vCriteriaBuilder.equal(vMiseRoot.<Long>get("qteExpedition"), vMiseRoot.<Long>get("quantite")));
				break;

			case "EN_COURS_EXPEDITION":
				vWhereClause.add(vCriteriaBuilder.gt(vMiseRoot.<Long>get("qteExpedition"), 0));
				vWhereClause.add(
						vCriteriaBuilder.lt(vMiseRoot.<Long>get("qteExpedition"), vMiseRoot.<Long>get("quantite")));
				break;

			}

		}
		
		
		
		if (pRechercheMiseMulitCritere.getRefCommande() != null
				&& !pRechercheMiseMulitCritere.getRefCommande().equals("")) {
			vWhereClause.add(
					vCriteriaBuilder.equal(vMiseRoot.get("refCommande"), pRechercheMiseMulitCritere.getRefCommande()));
		}
		
		
		
		/** Lancer la requete */
		vCriteriaQuery.select(vMiseRoot).where(
				vWhereClause.toArray(new Predicate[] {}));
		
		vCriteriaQuery.orderBy(vCriteriaBuilder.desc(vMiseRoot.get("id")));

		/** Récupération du résultat de la base */
		List<MiseEntity> vListResultatRechercheMise = null;
		
		
		if (pRechercheMiseMulitCritere.isOptimized()) {
			vListResultatRechercheMise = this.entityManager.createQuery(vCriteriaQuery).setMaxResults(65).getResultList();

		} else {
			vListResultatRechercheMise = this.entityManager.createQuery(vCriteriaQuery).getResultList();
		}
	    
		
		/** Conversion de la liste en valeur */
		List<ElementRechecheMiseValue> vListeResultat = new ArrayList<ElementRechecheMiseValue>();
		for (MiseEntity mise : vListResultatRechercheMise) {
			ElementRechecheMiseValue vEv = vPersistanceUtilities
					.ResultatRechecheMiseValue(mise);
			vListeResultat.add(vEv);
		}

		/** Construction de l'objet de retour de la recherche **/
		ResultatRechercheMiseValue vResultatRechecheMiseValue = new ResultatRechercheMiseValue();
		Collections.sort(vListeResultat);
		vResultatRechecheMiseValue
				.setListeElementRechecheMiseValeur(new TreeSet<>(vListeResultat));

		return vResultatRechecheMiseValue;

	}
	
	@Override
	public List<MiseValue> getMiseByReference(String referenceMise) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<MiseEntity> criteriaQuery = criteriaBuilder.createQuery(MiseEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<MiseEntity> root = criteriaQuery.from(MiseEntity.class);
		
		// Set referenceMise on whereClause if not null
		if (referenceMise != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_REF_MISE), referenceMise));
		}
		
		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		List<MiseEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

	    List<MiseValue> list = new ArrayList<MiseValue>();
	    
	    for (MiseEntity entity : resultatEntite) {
	    	MiseValue dto = vPersistanceUtilities.toValue(entity);
	    	list.add(dto);
	    }

	    
	    return list;

	}


	public List<MiseValue> listerMise(){
		List<MiseEntity> vListMiseEntity=this.lister(MiseEntity.class);
		List<MiseValue>  vListMiseValue=new ArrayList<MiseValue>();
	    for (MiseEntity mise:vListMiseEntity){
	    	MiseValue vMiseValue=vPersistanceUtilities.toValue(mise);
	    	vListMiseValue.add(vMiseValue);
	    }
	     return vListMiseValue;
	
	}
	
	
	@Override
	public List<MiseValue> getReferenceMise() {
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<MiseEntity> root = criteriaQuery.from(MiseEntity.class);

		criteriaQuery
				.select(criteriaBuilder.array(root.get("id").as(Long.class), root.get("reference").as(String.class)))
				.where(whereClause.toArray(new Predicate[] {}));

		List<Object[]> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

		List<MiseValue> vListeResultat = new ArrayList<MiseValue>();

		for (Object[] element : resultatEntite) {

			MiseValue vMiseValue = new MiseValue();

			vMiseValue.setId((Long) element[0]);
			vMiseValue.setReference((String) element[1]);
			vListeResultat.add(vMiseValue);
		}
		return vListeResultat;
	}
	
	
	@Override
	public MiseValue rechercheMiseParReference(String referenceMise) {

		
		if (estNonVide(referenceMise)) {
			
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			CriteriaQuery<MiseEntity> criteriaQuery = criteriaBuilder.createQuery(MiseEntity.class);
			List<Predicate> whereClause = new ArrayList<Predicate>();
			Root<MiseEntity> root = criteriaQuery.from(MiseEntity.class);

			// Set referenceMise on whereClause if not null
			
			
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_REF_MISE), referenceMise.toString()));
			
			criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
			List<MiseEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

			List<MiseValue> list = new ArrayList<MiseValue>();

			for (MiseEntity entity : resultatEntite) {
				MiseValue dto = vPersistanceUtilities.toValue(entity);
				list.add(dto);
			}

			if (list.size() >= 1)
				return list.get(0);
			
			
		}

	

		return null;
	}
	
	
	/**
	 * Accesseur en lecture de l'attribut <code>entityManager</code>.
	 * 
	 * @return EntityManager L'attribut entityManager à lire.
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * Accesseur en écriture de l'attribut <code>entityManager</code>.
	 *
	 * @param entityManager
	 *            L'attribut entityManager à modifier.
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public MisePersistanceUtilities getvPersistanceUtilities() {
		return vPersistanceUtilities;
	}

	public void setvPersistanceUtilities(
			MisePersistanceUtilities vPersistanceUtilities) {
		this.vPersistanceUtilities = vPersistanceUtilities;
	}
	
	public TraitementMiseValue getTraitementMiseById(Long id) {

		TraitementMiseEntity entity = this.rechercherParId(id, TraitementMiseEntity.class);

		TraitementMiseValue vMiseValueResultat = vPersistanceUtilities
				.toValue(entity);
		return vMiseValueResultat;
	}

		
	private boolean estNonVide(String val) {
		return val != null && !"".equals(val)  && !"undefined".equals(val);
	}

	@Override
	public List<String> getListRefMiseParRefBR(String referenceBR) {
		
	//	System.out.println("----getListRefMiseParRefBR  referenceBR"+referenceBR);
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<MiseEntity> criteriaQuery = criteriaBuilder.createQuery(MiseEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<MiseEntity> root = criteriaQuery.from(MiseEntity.class);
		
		// Set referenceMise on whereClause if not null
		if (referenceBR != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_REF_BON_RECEPTION), referenceBR));
		}
		
		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		List<MiseEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

	    List<String> listRefMise = new ArrayList<String>();
	    
	    for (MiseEntity entity : resultatEntite) {
	    	listRefMise.add(entity.getReference());
	    }
	//	System.out.println("----getListRefMiseParRefBR  listRefMise"+listRefMise);

	    return listRefMise;
	}

}
