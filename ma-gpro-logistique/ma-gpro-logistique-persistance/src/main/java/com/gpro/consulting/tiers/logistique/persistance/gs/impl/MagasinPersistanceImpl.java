package com.gpro.consulting.tiers.logistique.persistance.gs.impl;

import java.util.ArrayList;
import java.util.List;

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
import com.gpro.consulting.tiers.commun.coordination.IConstante;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.BoutiqueValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.SocieteValue;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IBoutiquePersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.ISocietePersistance;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.entity.SiteEntite;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.MagasinValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.RechercheMulticritereMagasinValue;
import com.gpro.consulting.tiers.logistique.persistance.gs.IMagasinPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gs.entite.EmplacementEntite;
import com.gpro.consulting.tiers.logistique.persistance.gs.entite.MagasinEntite;
import com.gpro.consulting.tiers.logistique.persistance.gs.utilities.PersistanceUtilitiesGs;

// TODO: Auto-generated Javadoc
/**
 * The Class MagasinPersistanceImpl.
 */

@Component
public class MagasinPersistanceImpl extends AbstractPersistance implements IMagasinPersistance {
	
	
	
	@Autowired
	private IBoutiquePersistance boutiquePersistance;
	
	@Autowired
	private ISocietePersistance societePersistance;
	

	private String PERCENT = "%";

	/** EntityManager. */
	@PersistenceContext
	private EntityManager entityManager;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(EmplacementEntite.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gpro.consulting.tiers.gs.persitance.IMagasinPersistance#creerMagasin(com.
	 * gpro.consulting.tiers.gs.coordination.value.MagasinValue)
	 */
	@Override
	public String creerMagasin(MagasinValue pMagasinValue) {
		SiteEntite vSiteEntie = new SiteEntite();
		if (pMagasinValue.getPiComSiteId() != null) {
			vSiteEntie = this.rechercherParId(pMagasinValue.getPiComSiteId(), SiteEntite.class);
		}
		MagasinEntite vMagasinEntite = (MagasinEntite) this
				.creer(PersistanceUtilitiesGs.toEntity(pMagasinValue, vSiteEntie));
		if (LOGGER.isDebugEnabled()) {
			// LOGGER.debug("Emplacement d'ID=" + vMagasinEntite.getId().toString() + " est
			// en cours.");
		}
		return vMagasinEntite.getId().toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gpro.consulting.tiers.gs.persitance.IMagasinPersistance#supprimerMagasin(
	 * java.lang.Long)
	 */
	@Override
	public void supprimerMagasin(Long pId) {
		this.supprimerEntite(MagasinEntite.class, pId.longValue());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gpro.consulting.tiers.gs.persitance.IMagasinPersistance#modifierMagasin(
	 * com.gpro.consulting.tiers.gs.coordination.value.MagasinValue)
	 */
	@Override
	public String modifierMagasin(MagasinValue pMagasinValue) {
		SiteEntite vSiteEntie = new SiteEntite();
		if (pMagasinValue.getPiComSiteId() != null) {
			vSiteEntie = this.rechercherParId(pMagasinValue.getPiComSiteId(), SiteEntite.class);
		}
		MagasinEntite vMagasinEntite = (MagasinEntite) this
				.modifier(PersistanceUtilitiesGs.toEntity(pMagasinValue, vSiteEntie));
		if (LOGGER.isDebugEnabled()) {
			// LOGGER.debug("Emplacement d'ID=" + vMagasinEntite.getId().toString() + " est
			// en cours.");
		}
		return vMagasinEntite.getId().toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.gs.persitance.IMagasinPersistance#
	 * rechercheMagasinParId(com.gpro.consulting.tiers.gs.coordination.value.
	 * MagasinValue)
	 */
	@Override
	public MagasinValue rechercheMagasinParId(MagasinValue pMagasinValue) {
		MagasinEntite vMagasinEntite = this.rechercherParId(pMagasinValue.getId().longValue(), MagasinEntite.class);
		if (vMagasinEntite == null)
			return null;
		MagasinValue vMagasinValueResult = PersistanceUtilitiesGs.toValue(vMagasinEntite);
		return vMagasinValueResult;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gpro.consulting.tiers.gs.persitance.IMagasinPersistance#listeMagasin()
	 */
	@Override
	public List<MagasinValue> listeMagasin() {
		List<MagasinEntite> vListMagasinEntite = this.lister(MagasinEntite.class);
		List<MagasinValue> vListMagasinValue = new ArrayList<MagasinValue>();
		for (MagasinEntite vMagasinEntite : vListMagasinEntite) {
			vListMagasinValue.add(PersistanceUtilitiesGs.toValue(vMagasinEntite));
		}
		return vListMagasinValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance#getEntityManager
	 * ()
	 */
	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public List<MagasinValue> listeDepot() {
		List<MagasinEntite> vListMagasinEntite = this.lister(MagasinEntite.class);
		List<MagasinValue> vListMagasinValue = new ArrayList<MagasinValue>();
		for (MagasinEntite vMagasinEntite : vListMagasinEntite) {
			if (vMagasinEntite.getDepot() == true)
				vListMagasinValue.add(PersistanceUtilitiesGs.toValue(vMagasinEntite));
		}
		return vListMagasinValue;
	}

	@Override
	public List<MagasinValue> listePDV() {
		List<MagasinEntite> vListMagasinEntite = this.lister(MagasinEntite.class);
		List<MagasinValue> vListMagasinValue = new ArrayList<MagasinValue>();
		for (MagasinEntite vMagasinEntite : vListMagasinEntite) {
			if (vMagasinEntite.getPointVente() == true)
				vListMagasinValue.add(PersistanceUtilitiesGs.toValue(vMagasinEntite));
		}
		return vListMagasinValue;
	}

	@Override
	public List<MagasinValue> rechercheMulticritere(RechercheMulticritereMagasinValue request) {
		CriteriaBuilder vBuilder = this.entityManager.getCriteriaBuilder();
		/** entity principale **/
		CriteriaQuery<MagasinEntite> vCriteriaQuery = vBuilder.createQuery(MagasinEntite.class);
		List<Predicate> vWhereClause = new ArrayList<Predicate>();

		/** create liste resyltat ***/

		/************ entity jointure *****************/
		Root<MagasinEntite> vRootParitieInteresse = vCriteriaQuery.from(MagasinEntite.class);

		if (estNonVide(request.getBoutiqueId())) {
			vWhereClause.add(vBuilder.equal(vRootParitieInteresse.get("boutiqueId"), request.getBoutiqueId()));
		}
		
		
		if (estNonVide(request.getSocieteId())) {
			vWhereClause.add(vBuilder.equal(vRootParitieInteresse.get("societeId"), request.getSocieteId()));
		}

		if (estNonVide(request.getDesignation())) {
			Expression<String> path = vRootParitieInteresse.get("designation");
			Expression<String> upper = vBuilder.upper(path);
			Predicate predicate = vBuilder.like(upper, PERCENT + request.getDesignation().toUpperCase() + PERCENT);
			vWhereClause.add(vBuilder.and(predicate));
		}

		if (estNonVide(request.getDepot())) {
			if (request.getDepot().equals(IConstante.OUI)) {
				vWhereClause.add(vBuilder.equal(vRootParitieInteresse.get("depot"), true));
			} else if (request.getDepot().equals(IConstante.NON)) {
				vWhereClause.add(vBuilder.equal(vRootParitieInteresse.get("depot"), false));
			}

		}

		if (estNonVide(request.getPointVente())) {
			if (request.getPointVente().equals(IConstante.OUI)) {
				vWhereClause.add(vBuilder.equal(vRootParitieInteresse.get("pointVente"), true));
			} else if (request.getPointVente().equals(IConstante.NON)) {
				vWhereClause.add(vBuilder.equal(vRootParitieInteresse.get("pointVente"), false));
			}

		}

		/** execute query and do something with result **/

		vCriteriaQuery.select(vRootParitieInteresse).where(vWhereClause.toArray(new Predicate[] {}));
		vCriteriaQuery.orderBy(vBuilder.desc(vRootParitieInteresse.get("id")));

		List<MagasinEntite> resultatEntite = this.entityManager.createQuery(vCriteriaQuery).getResultList();

		/** Conversion de la liste en valeur */
		List<MagasinValue> vListeResultat = new ArrayList<MagasinValue>();

		for (MagasinEntite vPartieInteresseeEntite : resultatEntite) {
			MagasinValue vPv = PersistanceUtilitiesGs.toValue(vPartieInteresseeEntite);
			
			if(vPv.getBoutiqueId() != null ) {
				BoutiqueValue boutique = boutiquePersistance.rechercheBoutiqueParId(vPv.getBoutiqueId());
				
				if(boutique != null && boutique.getSocieteId() != null) {
					
					vPv.setBoutiqueAbreviation(boutique.getAbreviation());
					
					SocieteValue societe = societePersistance.rechercheSocieteParId(boutique.getSocieteId()) ;
					
					
					if(societe != null) {
						
						vPv.setSocieteAbreviation(societe.getAbreviation());
						
					}
					
				}

				
			}
			
			vListeResultat.add(vPv);
		}

		return vListeResultat;
	}

	@SuppressWarnings("unused")
	private boolean estNonVide(String val) {
		return val != null && !"".equals(val);

	}

	@SuppressWarnings("unused")
	private boolean estNonVide(Long val) {
		return val != null;

	}

	@Override
	public MagasinValue rechercheMagasinParId(Long id) {
		MagasinEntite vMagasinEntite = this.rechercherParId(id.longValue(), MagasinEntite.class);
		if (vMagasinEntite == null)
			return null;
		MagasinValue vMagasinValueResult = PersistanceUtilitiesGs.toValue(vMagasinEntite);
		return vMagasinValueResult;
	}
}
