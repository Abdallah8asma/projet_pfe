package com.gpro.consulting.tiers.logistique.persistance.atelier.prepMoule.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.logistique.coordination.atelier.PrepMoule.value.PrepMouleValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.PrepMoule.value.RechercheMulticriterePrepMouleValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.PrepMoule.value.ResultatRecherchePrepMouleValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.PrepMoule.value.ElementRechechePrepMouleValue;
import com.gpro.consulting.tiers.logistique.persistance.atelier.prepMoule.IPrepMoulePersistance;
import com.gpro.consulting.tiers.logistique.persistance.atelier.prepMoule.entity.PrepMouleEntity;
import com.gpro.consulting.tiers.logistique.persistance.atelier.prepMoule.utilities.PrepMoulePersistanceUtilities;

// TODO: Auto-generated Javadoc
/**
 * Implémentation des méthodes CRUD du Bon de reception.
 *
 * @author $Author: rkhaskhoussy $
 * @version $Revision: 0 $
 */

@Component
public class PrepMoulePersistanceImpl extends AbstractPersistance implements IPrepMoulePersistance {


	/** EntityManager. */
	@PersistenceContext
	private EntityManager entityManager;

	/** Utilitaire de persistance. */
	@Autowired
	private PrepMoulePersistanceUtilities vPersistanceUtilities;

	/** Logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PrepMoulePersistanceImpl.class);

	/**
	 * {@inheritDoc}
	 */
	
	
	
	@Override
	public String creerPrepMoule(PrepMouleValue pPrepMouleValue) {
		PrepMouleEntity vPrepMouleEntity = (PrepMouleEntity) this.creer(vPersistanceUtilities.toEntity(pPrepMouleValue));

		return vPrepMouleEntity.getId().toString();
	}

	@Override
	public void supprimerPrepMoule(Long pId) {
		this.supprimerEntite(PrepMouleEntity.class, pId.longValue());
		
	}

	@Override
	public String modifierPrepMoule(PrepMouleValue pPrepMoule) {
		PrepMouleEntity vPrepMouleEntity = (PrepMouleEntity) this.modifier(vPersistanceUtilities.toEntity(pPrepMoule));

		return vPrepMouleEntity.getId().toString();
	}

	@Override
	public PrepMouleValue recherchePrepMouleParId(Long pPrepMoule) {
	
		PrepMouleEntity vPrepMouleEntity = this.rechercherParId(pPrepMoule.longValue(), PrepMouleEntity.class);

		PrepMouleValue vPrpMouleValueResultat = vPersistanceUtilities.toValue(vPrepMouleEntity);
		return vPrpMouleValueResultat;
	}

	@Override
	public PrepMouleValue recherchePrepMouleParReference(Long pPrepMoule) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultatRecherchePrepMouleValue rechercherPrepMouleMultiCritere(
			RechercheMulticriterePrepMouleValue pRecherchePrepMouleMulitCritere) {
		/** Création du Criteria */
		CriteriaBuilder vCriteriaBuilder = this.entityManager.getCriteriaBuilder();

		/** entity principale : PrepMoule **/
		CriteriaQuery<PrepMouleEntity> vCriteriaQuery = vCriteriaBuilder.createQuery(PrepMouleEntity.class);

		Root<PrepMouleEntity> vPrepMouleRoot = vCriteriaQuery.from(PrepMouleEntity.class);

		/** Liste des Prédicats : Date preparation , reference , emplacement , machine , designation  */
		List<Predicate> vWhereClause = new ArrayList<Predicate>();

	

		if (pRecherchePrepMouleMulitCritere.getReference() != null
				&& !pRecherchePrepMouleMulitCritere.getReference().equals("")) {
			vWhereClause.add(
					vCriteriaBuilder.equal(vPrepMouleRoot.get("reference"), pRecherchePrepMouleMulitCritere.getReference()));
		}
		
		
		if (pRecherchePrepMouleMulitCritere.getDatePrep() != null && !pRecherchePrepMouleMulitCritere.getDatePrep().toString().equals("")) {
			
		
			vWhereClause.add(vCriteriaBuilder.greaterThanOrEqualTo(vPrepMouleRoot.<Calendar> get("datePreparation"),
					pRecherchePrepMouleMulitCritere.getDatePrep()));
		}
		
		
		if (pRecherchePrepMouleMulitCritere.getDatePrepF() != null	&& !pRecherchePrepMouleMulitCritere.getDatePrepF().toString().equals("")) {
		
			vWhereClause.add(vCriteriaBuilder.lessThanOrEqualTo(vPrepMouleRoot.<Calendar> get("datePreparation"),
					pRecherchePrepMouleMulitCritere.getDatePrepF()));
		}

       
		
	

		if (pRecherchePrepMouleMulitCritere.getMachine() != null
				&& !pRecherchePrepMouleMulitCritere.getMachine().equals("")) {
			vWhereClause.add(
					vCriteriaBuilder.equal(vPrepMouleRoot.get("machine"), pRecherchePrepMouleMulitCritere.getMachine()));
		}
		

		
		if (pRecherchePrepMouleMulitCritere.getEmplacement() != null
				&& !pRecherchePrepMouleMulitCritere.getEmplacement().equals("")) {
			vWhereClause.add(
					vCriteriaBuilder.equal(vPrepMouleRoot.get("emplacement"), pRecherchePrepMouleMulitCritere.getEmplacement()));
		}

		if (pRecherchePrepMouleMulitCritere.getDesignation() != null
				&& !pRecherchePrepMouleMulitCritere.getDesignation().equals("")) {
			vWhereClause.add(
					vCriteriaBuilder.equal(vPrepMouleRoot.get("designation"), pRecherchePrepMouleMulitCritere.getDesignation()));
		}
		
		
		/** Lancer la requete */
		vCriteriaQuery.select(vPrepMouleRoot).where(vWhereClause.toArray(new Predicate[] {}));

		/** Récupération du résultat de la base */
		List<PrepMouleEntity> vListResultatRecherchePrepMoule = this.entityManager.createQuery(vCriteriaQuery).getResultList();
		/** Conversion de la liste en valeur */
		List<ElementRechechePrepMouleValue> vListeResultat = new ArrayList<ElementRechechePrepMouleValue>();
		for (PrepMouleEntity PrepMoule : vListResultatRecherchePrepMoule) {
			ElementRechechePrepMouleValue vEv = vPersistanceUtilities.ResultatRechechePrepMouleValue(PrepMoule);
			vListeResultat.add(vEv);
		}

		/** Construction de l'objet de retour de la recherche **/
		ResultatRecherchePrepMouleValue vResultatRechechePrepMouleValue = new ResultatRecherchePrepMouleValue();
		Collections.sort(vListeResultat);
		vResultatRechechePrepMouleValue.setListeElementRechechePrepMouleValeur(new TreeSet<>(vListeResultat));

		return vResultatRechechePrepMouleValue;
	}

	@Override
	public List<PrepMouleValue> listerPrepMoule() {
		List<PrepMouleEntity> vListPrepMouleEntity = this.lister(PrepMouleEntity.class);
		List<PrepMouleValue> vListPrepMouleValue = new ArrayList<PrepMouleValue>();
		for (PrepMouleEntity PrepMoule : vListPrepMouleEntity) {
			PrepMouleValue vPrepMouleValue = vPersistanceUtilities.toValue(PrepMoule);
			vListPrepMouleValue.add(vPrepMouleValue);
		}
		return vListPrepMouleValue;
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

	/**
	 * Gets the v persistance utilities.
	 *
	 * @return the v persistance utilities
	 */
	public PrepMoulePersistanceUtilities getvPersistanceUtilities() {
		return vPersistanceUtilities;
	}

	/**
	 * Sets the v persistance utilities.
	 *
	 * @param vPersistanceUtilities
	 *            the new v persistance utilities
	 */
	public void setvPersistanceUtilities(PrepMoulePersistanceUtilities vPersistanceUtilities) {
		this.vPersistanceUtilities = vPersistanceUtilities;
	}

	

	@Override
	public List<PrepMouleValue> getPrepMouleByReference(String referencePrepMoule) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getListRefPrepMouleParRefBR(String referenceBR) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PrepMouleValue> getReferencePrepMoule() {
		// TODO Auto-generated method stub
		return null;
	}




}
