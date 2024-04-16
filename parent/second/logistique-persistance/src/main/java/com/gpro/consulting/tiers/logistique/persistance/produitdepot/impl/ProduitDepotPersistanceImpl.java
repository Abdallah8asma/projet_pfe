package com.gpro.consulting.tiers.logistique.persistance.produitdepot.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.logistique.coordination.produitdepot.value.ProduitDepotValue;
import com.gpro.consulting.tiers.logistique.coordination.produitdepot.value.RechercheMulticritereProduitDepotValue;
import com.gpro.consulting.tiers.logistique.coordination.produitdepot.value.ResultatRechercheProduitDepotValue;
import com.gpro.consulting.tiers.logistique.persistance.produitdepot.IProduitDepotPersistance;
//import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.PrixClientEntity;
import com.gpro.consulting.tiers.logistique.persistance.produitdepot.entities.ProduitDepotEntity;
import com.gpro.consulting.tiers.logistique.persistance.produitdepot.utilities.PersistanceUtilitiesProduitDepot;

/**
 * The Class ProduitDepotImpl.
 * 
 * @author El Araichi Oussama
 */
@Component
public class ProduitDepotPersistanceImpl extends AbstractPersistance implements IProduitDepotPersistance {

	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;

	// @Autowired
	// private IProduitDepotPersistance produitdepotpersistance;

	private static Logger log = Logger.getLogger(ProduitDepotPersistanceImpl.class);

	private String idProduit = "idProduit";
	private String idDepot = "idDepot";

	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ProduitDepotPersistanceImpl.class);

	@Override
	public List<ProduitDepotValue> listeProduitDepot() {

		List<ProduitDepotEntity> vListeProduitsDepotEntity = new ArrayList<ProduitDepotEntity>();
		List<ProduitDepotValue> vListeProduitsDepotsValues = new ArrayList<ProduitDepotValue>();
		for (ProduitDepotEntity vProduitDepotEntite : vListeProduitsDepotEntity) {
			vListeProduitsDepotsValues.add(PersistanceUtilitiesProduitDepot.toValue(vProduitDepotEntite));
		}
		return vListeProduitsDepotsValues;

	}

	@Override
	public ResultatRechercheProduitDepotValue rechercheMulticritere(RechercheMulticritereProduitDepotValue request) {

		// System.out.println("persist: id depot: " + request.getIdDepot() + "
		// idproduit: " + request.getIdProduit());

		CriteriaBuilder vBuilder = this.entityManager.getCriteriaBuilder();
		/** entity principale **/
		CriteriaQuery<ProduitDepotEntity> vCriteriaQuery = vBuilder.createQuery(ProduitDepotEntity.class);
		List<Predicate> vWhereClause = new ArrayList<Predicate>();

		/** create liste resultat ***/

		/************ entity jointure *****************/
		Root<ProduitDepotEntity> vRootProduitDepot = vCriteriaQuery.from(ProduitDepotEntity.class);

		/***************** Predicate *************/
		// Set Iddepot on whereClause if not empty or null
		// if (request.getId != null) {
		// Subquery<Long> subQuery = criteriaQuery.subquery(Long.class);
		// Root<ProduitDepotEntity> subRoot =
		// subQuery.from(ProduitDepotEntity.class);

		if (request.getIdDepot() != null) {
			// System.out.println("request.getIddepot()" + request.getIdDepot());
			vWhereClause.add(vBuilder.equal(vRootProduitDepot.get(idDepot), request.getIdDepot()));

		}
		// Set request.idproduit on whereClause if not null
		if (request.getIdProduit() != null) {
			// System.out.println("request.getIdproduit()" + request.getIdProduit());
			vWhereClause.add(vBuilder.equal(vRootProduitDepot.get(idProduit), request.getIdProduit()));

		}

		/** execute query and do something with result **/

		vCriteriaQuery.select(vRootProduitDepot).where(vWhereClause.toArray(new Predicate[] {}));
		List<ProduitDepotEntity> resultatEntite = this.entityManager.createQuery(vCriteriaQuery).getResultList();

		/** Conversion de la liste en valeur */
		Set<ProduitDepotValue> vListeResultat = new HashSet<ProduitDepotValue>();

		for (ProduitDepotEntity vProduitDepotEntite : resultatEntite) {

			vListeResultat.add(PersistanceUtilitiesProduitDepot.toValue(vProduitDepotEntite));
		}
		ResultatRechercheProduitDepotValue result = new ResultatRechercheProduitDepotValue();
		result.setNombreResultatRecherche(Long.valueOf(vListeResultat.size()));
		result.setProduitdepotvalues(vListeResultat);

		return result;

	}

	@Override
	public String create(ProduitDepotValue pProduitDepotValue) {
		ProduitDepotEntity vProduitDepotEntity = (ProduitDepotEntity) this
				.creer(PersistanceUtilitiesProduitDepot.toEntity(pProduitDepotValue));

		return vProduitDepotEntity.getIdDepot().toString();
	}

	@Override
	public String modifier(ProduitDepotValue pProduitDepotValue) {
		ProduitDepotEntity vProduitEntity = (ProduitDepotEntity) this
				.modifier(PersistanceUtilitiesProduitDepot.toEntity(pProduitDepotValue));
		ProduitDepotValue vProduitValueResult = PersistanceUtilitiesProduitDepot.toValue(vProduitEntity);
		return vProduitValueResult.getId().toString();
	}

	@Override
	public ProduitDepotValue getProduitDepotById(Long pProduitId, Long pDepotId) {

		return null;
	}

	
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return entityManager;
	}

	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public ProduitDepotValue getByProduitAndDepot(Long pProduitId, Long pDepotId) {
		// TODO Auto-generated method stub
		// System.out.println("persist: id depot: " + request.getIdDepot() + "
		// idproduit: " + request.getIdProduit());

		CriteriaBuilder vBuilder = this.entityManager.getCriteriaBuilder();
		/** entity principale **/
		CriteriaQuery<ProduitDepotEntity> vCriteriaQuery = vBuilder.createQuery(ProduitDepotEntity.class);
		List<Predicate> vWhereClause = new ArrayList<Predicate>();

		/** create liste resultat ***/

		/************ entity jointure *****************/
		Root<ProduitDepotEntity> vRootProduitDepot = vCriteriaQuery.from(ProduitDepotEntity.class);

		/***************** Predicate *************/
		// Set Iddepot on whereClause if not empty or null
		// if (request.getId != null) {
		// Subquery<Long> subQuery = criteriaQuery.subquery(Long.class);
		// Root<ProduitDepotEntity> subRoot =
		// subQuery.from(ProduitDepotEntity.class);

		if (pDepotId != null) {
			// System.out.println("request.getIddepot()" + request.getIdDepot());
			vWhereClause.add(vBuilder.equal(vRootProduitDepot.get(idDepot), pDepotId));

		}
		// Set request.idproduit on whereClause if not null
		if (pProduitId != null) {
			// System.out.println("request.getIdproduit()" + request.getIdProduit());
			vWhereClause.add(vBuilder.equal(vRootProduitDepot.get(idProduit), pProduitId));

		}

		/** execute query and do something with result **/

		vCriteriaQuery.select(vRootProduitDepot).where(vWhereClause.toArray(new Predicate[] {}));

		ProduitDepotEntity resultatEntite = null;

		try {

			resultatEntite = this.entityManager.createQuery(vCriteriaQuery).getSingleResult();

			return PersistanceUtilitiesProduitDepot.toValue(resultatEntite);
			
		} catch (Exception ex) {

			return null;
		}
	}

}
