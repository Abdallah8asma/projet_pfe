package com.gpro.consulting.tiers.commun.persistance.elementBase.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.PackageValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticriterePackageValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ResultatRechechePackageValue;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IPackagePersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.PackageEntite;
import com.gpro.consulting.tiers.commun.persistance.utilities.PersistanceUtilities;

@Component
public class PackagePersistanceImpl extends AbstractPersistance implements IPackagePersistance {

	private String PREDICAT_REFERENCE = "reference";

	private String PREDICAT_NOM = "nom";

	private String PREDICAT_DATEDEBUT = "dateDebut";

	private String PREDICAT_DATEFIN = "dateFin";

	private String PREDICAT_CLIENTID = "clientId";

	private String PREDICAT_GROUPEID = "groupeId";

	private String PREDICAT_BOUTIQUEID = "boutiqueId";

	/** EntityManager. **/
	@PersistenceContext
	private EntityManager entityManager;

	/** Logger */
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(PackagePersistanceImpl.class);

	/************************** Creation Package *****************************/
	@Override
	public String creerPackage(PackageValue pPackageValue) {
		if (LOGGER.isDebugEnabled()) {
			// LOGGER.debug(" ** Creation du Package " + pPackageValue.getDesignation() + "
			// est en cours.");
		}
		PackageEntite pPackageEntite = (PackageEntite) this.creer(PersistanceUtilities.toPackageEntity(pPackageValue));
		PackageValue pPackageValueResult = PersistanceUtilities.toPackageValue(pPackageEntite);
		return pPackageValueResult.getId().toString();
	}

	/************************** Suppression Package *****************************/
	@Override
	public void supprimerPackage(Long pId) {
		if (LOGGER.isDebugEnabled()) {
			// LOGGER.debug(" ** Suppression du Package de l'ID=" + pId.longValue() + " est
			// en cours.");
		}
		this.supprimerEntite(PackageEntite.class, pId.longValue());
	}

	/************************ Modification Package ******************************/
	@Override
	public String modifierPackage(PackageValue pPackageValue) {
		if (LOGGER.isDebugEnabled()) {
			// LOGGER.debug(" ** Modification du Package de l'ID=" +
			// pPackageValue.getId().toString() + " est en cours.");
		}
		PackageEntite pPackageEntite = (PackageEntite) this
				.modifier(PersistanceUtilities.toPackageEntity(pPackageValue));
		PackageValue pPackageValueResult = PersistanceUtilities.toPackageValue(pPackageEntite);
		return pPackageValueResult.getId().toString();
	}

	/************************ Recherche Package ******************************/
	@Override
	public PackageValue recherchePackageParId(PackageValue pPackageValue) {
		if (LOGGER.isDebugEnabled()) {
			// LOGGER.debug(" ** Recherche du Package de l'ID=" +
			// pPackageValue.getId().toString() + " est en cours.");
		}
		PackageEntite pPackageEntite = (PackageEntite) this.rechercherParId(pPackageValue.getId().longValue(),
				PackageEntite.class);
		PackageValue pPackageValueResult = PersistanceUtilities.toPackageValue(pPackageEntite);
		return pPackageValueResult;

	}

	/************************ Liste Package ******************************/

	@Override
	public List<PackageValue> listePackage() {
		List<PackageEntite> vListePackageEntite = this.lister(PackageEntite.class);
		List<PackageValue> vListPackageValues = new ArrayList<PackageValue>();
		for (PackageEntite vPackageEntite : vListePackageEntite) {
			vListPackageValues.add(PersistanceUtilities.toPackageValue(vPackageEntite));
		}
		return vListPackageValues;
	}

	/***************************** Getter & Setter ********************************/
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public ResultatRechechePackageValue rechercheMulticritere(RechercheMulticriterePackageValue requestPackage) {
		CriteriaBuilder vBuilder = this.entityManager.getCriteriaBuilder();
		/** entity principale **/
		CriteriaQuery<PackageEntite> vCriteriaQuery = vBuilder.createQuery(PackageEntite.class);
		List<Predicate> vWhereClause = new ArrayList<Predicate>();

		/** create liste resyltat ***/

		/************ entity jointure *****************/
		Root<PackageEntite> vRootPackageEntite = vCriteriaQuery.from(PackageEntite.class);

		if (estNonVide(requestPackage.getReference())) {
			vWhereClause.add(vBuilder.equal(vRootPackageEntite.get(PREDICAT_REFERENCE), requestPackage.getReference()));
		}

		if (estNonVide(requestPackage.getNom())) {
			vWhereClause.add(vBuilder.equal(vRootPackageEntite.get(PREDICAT_NOM), requestPackage.getNom()));
		}
		

		if (estNonVide(requestPackage.getClientId())) {
			vWhereClause.add(vBuilder.equal(vRootPackageEntite.get(PREDICAT_CLIENTID), requestPackage.getClientId()));
		}
		
		if (estNonVide(requestPackage.getGroupeId())) {
			vWhereClause.add(vBuilder.equal(vRootPackageEntite.get(PREDICAT_GROUPEID), requestPackage.getGroupeId()));
		}

		
		if (estNonVide(requestPackage.getBoutiqueId())) {
			vWhereClause.add(vBuilder.equal(vRootPackageEntite.get(PREDICAT_BOUTIQUEID), requestPackage.getBoutiqueId()));
		}



		/** execute query and do something with result **/

		vCriteriaQuery.select(vRootPackageEntite).where(vWhereClause.toArray(new Predicate[] {}));
		vCriteriaQuery.orderBy(vBuilder.desc(vRootPackageEntite.get("id")));

		List<PackageEntite> resultatEntite = this.entityManager.createQuery(vCriteriaQuery).getResultList();

		/** Conversion de la liste en valeur */
		List<PackageValue> vListeResultat = new ArrayList<PackageValue>();

		for (PackageEntite vPartieInteresseeEntite : resultatEntite) {
			PackageValue vPv = PersistanceUtilities.toPackageValue(vPartieInteresseeEntite);
			vListeResultat.add(vPv);
		}
		
		
		
		ResultatRechechePackageValue res = new ResultatRechechePackageValue();
		
	
		res.setNombreResultaRechercher(Long.valueOf(vListeResultat.size()));
		
	  
	    
	    Collections.sort(vListeResultat);
	    
	    res.setList(new TreeSet<>(vListeResultat));

		return res;
	}

	@SuppressWarnings("unused")
	private boolean estNonVide(String val) {
		return val != null && !"".equals(val)  && !"undefined".equals(val);

	}
	
	@SuppressWarnings("unused")
	private boolean estNonVide(Long val) {
		return val != null ;

	}

}
