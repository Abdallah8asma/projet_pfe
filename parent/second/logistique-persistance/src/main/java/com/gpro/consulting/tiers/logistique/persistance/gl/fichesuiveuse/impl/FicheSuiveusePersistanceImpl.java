package com.gpro.consulting.tiers.logistique.persistance.gl.fichesuiveuse.impl;

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
import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.value.FicheSuiveuseValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.value.RechercheMulticritereFicheSuiveuseValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.value.ResultatRechecheFicheSuiveuseElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.value.ResultatRechecheFicheSuiveuseValue;
import com.gpro.consulting.tiers.logistique.persistance.gl.fichesuiveuse.IFicheSuiveusePersistance;
import com.gpro.consulting.tiers.logistique.persistance.gl.fichesuiveuse.entity.FicheSuiveuseEntity;
import com.gpro.consulting.tiers.logistique.persistance.gl.fichesuiveuse.utilities.FicheSuiveusePersistanceUtilities;

/**
 * Implementation of {@link IFicheSuiveusePersistance} interface.
 * 
 * @author Wahid Gazzah
 * @since 28/03/2016
 *
 */

@Component
public class FicheSuiveusePersistanceImpl extends AbstractPersistance implements IFicheSuiveusePersistance {

	private static final Logger logger = LoggerFactory.getLogger(FicheSuiveusePersistanceImpl.class);

	private String PREDICATE_CLIENT = "partieIntId";
	private String PREDICATE_DATE = "dateLancement";
	private String PREDICATE_POIDS = "poids";
	private String PREDICATE_PRODUIT = "produitId";
	private String PREDICATE_REFERENCE_MISE = "referenceMise";
	private String PREDICATE_TYPE_LIVRAISON = "typeLivraison";
	private String PREDICATE_RAPPORT_BAIN = "rapportBain";
	private String referenceMise = "referenceMise";
	private String PERCENT = "%";

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private FicheSuiveusePersistanceUtilities ficheSuiveusePersistanceUtilities;

	@Override
	public ResultatRechecheFicheSuiveuseValue rechercherMultiCritere(RechercheMulticritereFicheSuiveuseValue request) {

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<FicheSuiveuseEntity> criteriaQuery = criteriaBuilder.createQuery(FicheSuiveuseEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<FicheSuiveuseEntity> root = criteriaQuery.from(FicheSuiveuseEntity.class);

		// Set request.partieIntId on whereClause if not null
		if (request.getPartieIntId() != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CLIENT), request.getPartieIntId()));
		}

		// Set request.produitId on whereClause if not null
		if (request.getProduitId() != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_PRODUIT), request.getProduitId()));
		}

		// Set request.numMise on whereClause if not empty or null
		if (estNonVide(request.getNumMise())) {
			Expression<String> path = root.get(PREDICATE_REFERENCE_MISE);
			Expression<String> upper = criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getNumMise().toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));
		}

		// Set request.poidsSortieMin on whereClause if not null
		if (request.getPoidsSortieMin() != null) {
			whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Double> get(PREDICATE_POIDS),
					request.getPoidsSortieMin()));
		}

		// Set request.poidsSortieMin on whereClause if not null
		if (request.getPoidsSortieMax() != null) {
			whereClause.add(
					criteriaBuilder.lessThanOrEqualTo(root.<Double> get(PREDICATE_POIDS), request.getPoidsSortieMax()));
		}

		// Set request.dateEntreMin on whereClause if not null
		if (request.getDateEntreMin() != null) {
			whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Calendar> get(PREDICATE_DATE),
					request.getDateEntreMin()));
		}

		// Set request.poidsSortieMin on whereClause if not null
		if (request.getDateEntreMax() != null) {
			whereClause.add(
					criteriaBuilder.lessThanOrEqualTo(root.<Calendar> get(PREDICATE_DATE), request.getDateEntreMax()));
		}

		// Set request.typeLivraison on whereClause if not empty or null
		if (estNonVide(request.getTypeLivraison())) {
			Expression<String> path = root.get(PREDICATE_TYPE_LIVRAISON);
			Expression<String> upper = criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper,
					PERCENT + request.getTypeLivraison().toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));
		}

		// Set request.rapportBain on whereClause if not null
		if (request.getRapportBain() != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_RAPPORT_BAIN), request.getRapportBain()));
		}

		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		List<FicheSuiveuseEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

		List<ResultatRechecheFicheSuiveuseElementValue> list = new ArrayList<ResultatRechecheFicheSuiveuseElementValue>();

		for (FicheSuiveuseEntity entity : resultatEntite) {
			ResultatRechecheFicheSuiveuseElementValue dto = ficheSuiveusePersistanceUtilities
					.toResultatRechecheFicheSuiveuseElementValue(entity);
			list.add(dto);
		}

		ResultatRechecheFicheSuiveuseValue result = new ResultatRechecheFicheSuiveuseValue();
		Collections.sort(list);
		result.setNombreResultaRechercher(Long.valueOf(list.size()));
		result.setList(new TreeSet<>(list));

		return result;

	}

	@Override
	public String create(FicheSuiveuseValue request) {

		FicheSuiveuseEntity entity = (FicheSuiveuseEntity) this
				.creer(ficheSuiveusePersistanceUtilities.toEntity(request));

		return entity.getId().toString();
	}

	@Override
	public void delete(Long id) {

		this.supprimerEntite(FicheSuiveuseEntity.class, id.longValue());
	}

	@Override
	public String update(FicheSuiveuseValue request) {

		FicheSuiveuseEntity entity = (FicheSuiveuseEntity) this
				.modifier(ficheSuiveusePersistanceUtilities.toEntity(request));

		return entity.getId().toString();
	}

	@Override
	public FicheSuiveuseValue getById(Long id) {

		FicheSuiveuseEntity entity = this.rechercherParId(id, FicheSuiveuseEntity.class);

		return ficheSuiveusePersistanceUtilities.toValue(entity);
	}

	@Override
	public List<FicheSuiveuseValue> getAll() {

		List<FicheSuiveuseEntity> listEntity = this.lister(FicheSuiveuseEntity.class);

		List<FicheSuiveuseValue> listDTO = new ArrayList<FicheSuiveuseValue>();

		for (FicheSuiveuseEntity entity : listEntity) {

			listDTO.add(ficheSuiveusePersistanceUtilities.toValue(entity));
		}

		return listDTO;
	}

	// @Override
	// public FicheSuiveuseValue rechercheFSByRefMise(String referenceMise) {

	// CriteriaBuilder vBuilder = this.entityManager.getCriteriaBuilder();
	// CriteriaQuery<FicheSuiveuseEntity> vCriteriaQuery =
	// vBuilder.createQuery(FicheSuiveuseEntity.class);
	// List<Predicate> vWhereClause = new ArrayList<Predicate>();
	// Root<FicheSuiveuseEntity> vRootFicheSuiveuse =
	// vCriteriaQuery.from(FicheSuiveuseEntity.class);
	//
	// /***************** Predicate *************/
	//
	// if (estNonVide(referenceMise)) {
	// vWhereClause.add(vBuilder.equal(vRootFicheSuiveuse.get(referenceMise),
	// referenceMise));
	// }
	//
	// /** execute query and do something with result **/
	//
	// vCriteriaQuery.select(referenceMise).where(vWhereClause.toArray(new
	// Predicate[] {}));
	// List<FicheSuiveuseEntity> resultatEntite =
	// this.entityManager.createQuery(vCriteriaQuery).getResultList();
	//
	//
	// FicheSuiveuseValue resultat = new FicheSuiveuseValue();
	//
	// System.out.println("---rechercheFicheSuiveuseParRefMise :
	// resultatEntite--" + resultatEntite);
	//
	// if (resultatEntite.size()!=0) {
	//
	// System.out.println("---rechercheFicheSuiveuseParRefMise :
	// resultatEntite.get(0)--" + resultatEntite.get(0));
	//
	// if (resultatEntite.get(0) != null) {
	// resultat =
	// ficheSuiveusePersistanceUtilities.toValue(resultatEntite.get(0));
	//
	// }
	// }

	// System.out.println("---rechercheFicheSuiveuseParRefMise : resultat--" +
	// resultat);
	// return resultat;
	//
	// }

	// rechercheFSByRefMise
	@Override
	public FicheSuiveuseValue rechercheFSByRefMise(String referenceMise) {

		List<FicheSuiveuseValue> ListFicheSuiveuseValue = this.getAll();
		List<FicheSuiveuseValue> listFS = new ArrayList<FicheSuiveuseValue>();

		for (FicheSuiveuseValue ficheSuiveuse : ListFicheSuiveuseValue) {

			if (ficheSuiveuse.getReferenceMise().equals(referenceMise)) {

				listFS.add(ficheSuiveuse);

			}

		}
		return listFS.get(0);
	}

	private boolean estNonVide(String val) {
		return val != null && !"".equals(val);
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
