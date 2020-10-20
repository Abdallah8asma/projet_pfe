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

	/** EntityManager. */
	@PersistenceContext
	private EntityManager entityManager;

	/** Utilitaire de persistance */
	@Autowired
	private MisePersistanceUtilities vPersistanceUtilities;

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
		/** Lancer la requete */
		vCriteriaQuery.select(vMiseRoot).where(
				vWhereClause.toArray(new Predicate[] {}));

		/** Récupération du résultat de la base */
		List<MiseEntity> vListResultatRechercheMise = this.entityManager
				.createQuery(vCriteriaQuery).getResultList();
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
		return val != null && !"".equals(val);
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
