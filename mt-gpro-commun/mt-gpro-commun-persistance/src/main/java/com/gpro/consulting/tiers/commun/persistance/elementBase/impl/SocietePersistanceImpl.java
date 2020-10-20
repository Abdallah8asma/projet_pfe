package com.gpro.consulting.tiers.commun.persistance.elementBase.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.coordination.IConstante;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.SocieteValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticritereSocieteValue;
import com.gpro.consulting.tiers.commun.persistance.elementBase.ISocietePersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.SocieteEntite;
import com.gpro.consulting.tiers.commun.persistance.utilities.PersistanceUtilities;

@Component
public class SocietePersistanceImpl extends AbstractPersistance implements ISocietePersistance {

	/** EntityManager. **/
	@PersistenceContext
	private EntityManager entityManager;

	/** Logger */
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(SocietePersistanceImpl.class);

	/************************** Creation Societe *****************************/
	@Override
	public String creerSociete(SocieteValue pSocieteValue) {
		if (LOGGER.isDebugEnabled()) {
			// LOGGER.debug(" ** Creation du Societe " + pSocieteValue.getDesignation() + "
			// est en cours.");
		}
		SocieteEntite pSocieteEntite = (SocieteEntite) this.creer(PersistanceUtilities.toSocieteEntity(pSocieteValue));
		SocieteValue pSocieteValueResult = PersistanceUtilities.toSocieteValue(pSocieteEntite);
		return pSocieteValueResult.getId().toString();
	}

	/************************** Suppression Societe *****************************/
	@Override
	public void supprimerSociete(Long pId) {
		if (LOGGER.isDebugEnabled()) {
			// LOGGER.debug(" ** Suppression du Societe de l'ID=" + pId.longValue() + " est
			// en cours.");
		}
		this.supprimerEntite(SocieteEntite.class, pId.longValue());
	}

	/************************ Modification Societe ******************************/
	@Override
	public String modifierSociete(SocieteValue pSocieteValue) {
		if (LOGGER.isDebugEnabled()) {
			// LOGGER.debug(" ** Modification du Societe de l'ID=" +
			// pSocieteValue.getId().toString() + " est en cours.");
		}
		SocieteEntite pSocieteEntite = (SocieteEntite) this
				.modifier(PersistanceUtilities.toSocieteEntity(pSocieteValue));
		SocieteValue pSocieteValueResult = PersistanceUtilities.toSocieteValue(pSocieteEntite);
		return pSocieteValueResult.getId().toString();
	}

	/************************ Recherche Societe ******************************/
	@Override
	public SocieteValue rechercheSocieteParId(SocieteValue pSocieteValue) {
		if (LOGGER.isDebugEnabled()) {
			// LOGGER.debug(" ** Recherche du Societe de l'ID=" +
			// pSocieteValue.getId().toString() + " est en cours.");
		}
		SocieteEntite pSocieteEntite = (SocieteEntite) this.rechercherParId(pSocieteValue.getId().longValue(),
				SocieteEntite.class);
		SocieteValue pSocieteValueResult = PersistanceUtilities.toSocieteValue(pSocieteEntite);
		return pSocieteValueResult;

	}

	/************************ Liste Societe ******************************/

	@Override
	public List<SocieteValue> listeSociete() {
		List<SocieteEntite> vListeSocieteEntite = this.lister(SocieteEntite.class);
		List<SocieteValue> vListSocieteValues = new ArrayList<SocieteValue>();
		for (SocieteEntite vSocieteEntite : vListeSocieteEntite) {
			vListSocieteValues.add(PersistanceUtilities.toSocieteValue(vSocieteEntite));
		}
		return vListSocieteValues;
	}

	/***************************** Getter & Setter ********************************/
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<SocieteValue> rechercheMulticritere(RechercheMulticritereSocieteValue requestSociete) {
		CriteriaBuilder vBuilder = this.entityManager.getCriteriaBuilder();
		/** entity principale **/
		CriteriaQuery<SocieteEntite> vCriteriaQuery = vBuilder.createQuery(SocieteEntite.class);
		List<Predicate> vWhereClause = new ArrayList<Predicate>();

		/** create liste resyltat ***/

		/************ entity jointure *****************/
		Root<SocieteEntite> vRootParitieInteresse = vCriteriaQuery.from(SocieteEntite.class);

		if (estNonVide(requestSociete.getReference())) {
			vWhereClause.add(vBuilder.equal(vRootParitieInteresse.get("reference"), requestSociete.getReference()));
		}

		if (estNonVide(requestSociete.getRaisonSociale())) {
			vWhereClause
					.add(vBuilder.equal(vRootParitieInteresse.get("raisonSociale"), requestSociete.getRaisonSociale()));
		}

		if (estNonVide(requestSociete.getActivite())) {
			vWhereClause.add(vBuilder.equal(vRootParitieInteresse.get("activite"), requestSociete.getActivite()));
		}

		if (estNonVide(requestSociete.getActif())) {
			if (requestSociete.getActif().equals(IConstante.OUI)) {
				vWhereClause.add(vBuilder.equal(vRootParitieInteresse.get("actif"), true));
			} else if (requestSociete.getActif().equals(IConstante.NON)) {
				vWhereClause.add(vBuilder.equal(vRootParitieInteresse.get("actif"), false));
			}

		}

		/** execute query and do something with result **/

		vCriteriaQuery.select(vRootParitieInteresse).where(vWhereClause.toArray(new Predicate[] {}));
		vCriteriaQuery.orderBy(vBuilder.desc(vRootParitieInteresse.get("id")));

		List<SocieteEntite> resultatEntite = this.entityManager.createQuery(vCriteriaQuery).getResultList();

		/** Conversion de la liste en valeur */
		List<SocieteValue> vListeResultat = new ArrayList<SocieteValue>();

		for (SocieteEntite vPartieInteresseeEntite : resultatEntite) {
			SocieteValue vPv = PersistanceUtilities.toSocieteValue(vPartieInteresseeEntite);
			vListeResultat.add(vPv);
		}

		return vListeResultat;
	}

	@SuppressWarnings("unused")
	private boolean estNonVide(String val) {
		return val != null && !"".equals(val);

	}

	@Override
	public SocieteValue rechercheSocieteParId(Long id) {
		if (LOGGER.isDebugEnabled()) {
			// LOGGER.debug(" ** Recherche du Societe de l'ID=" +
			// pSocieteValue.getId().toString() + " est en cours.");
		}
		SocieteEntite pSocieteEntite = (SocieteEntite) this.rechercherParId(id.longValue(), SocieteEntite.class);
		SocieteValue pSocieteValueResult = PersistanceUtilities.toSocieteValue(pSocieteEntite);
		return pSocieteValueResult;

	}

}
