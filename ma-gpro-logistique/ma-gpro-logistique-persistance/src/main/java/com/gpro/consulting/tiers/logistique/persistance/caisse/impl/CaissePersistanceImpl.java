package com.gpro.consulting.tiers.logistique.persistance.caisse.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.logistique.coordination.caisse.value.CaisseValue;
import com.gpro.consulting.tiers.logistique.coordination.caisse.value.RechercheMulticritereCaisseValue;
import com.gpro.consulting.tiers.logistique.coordination.caisse.value.ResultatRechercheCaisseValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.MagasinValue;
import com.gpro.consulting.tiers.logistique.persistance.caisse.ICaissePersistance;
import com.gpro.consulting.tiers.logistique.persistance.caisse.entity.CaisseEntity;
import com.gpro.consulting.tiers.logistique.persistance.caisse.utilities.CaissePersistanceUtilities;
import com.gpro.consulting.tiers.logistique.persistance.gs.impl.MagasinPersistanceImpl;

/**
 * Implementation of {@link CaissePersistanceImpl} interface.
 * 
 * @author Hamdi etteieb
 * @since 16/08/2018
 *
 */

@Component
public class CaissePersistanceImpl extends AbstractPersistance implements ICaissePersistance {

	private static final String PREDICATE_MAGASINID = "magasinId";

	private static final String PREDICATE_DATECLOTURE = "dateCloture";

	private static final String PREDICATE_REFERENCE = "reference";

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private CaissePersistanceUtilities caissePersistanceUtilities;

	@Autowired
	private MagasinPersistanceImpl magasinPersistance;

	@Override
	public String create(CaisseValue caisseValue) {
		// caisseValue.setReference("C-" + caisseValue.getMagasinReference() +
		// "-" + caisseValue.getDate());
		CaisseEntity entity = (CaisseEntity) this.creer(caissePersistanceUtilities.toEntity(caisseValue));

		return entity.getId().toString();
	}

	@Override
	public CaisseValue getById(Long id) {

		CaisseEntity CaisseEntity = this.rechercherParId(id, CaisseEntity.class);
       
		if(CaisseEntity != null) return caissePersistanceUtilities.toValue(CaisseEntity);
		
           
           
           return null;
	}

	@Override
	public String update(CaisseValue CaisseValue) {

		CaisseEntity entity = (CaisseEntity) this.modifier(caissePersistanceUtilities.toEntity(CaisseValue));

		return entity.getId().toString();
	}

	@Override
	public void delete(Long id) {

		this.supprimerEntite(CaisseEntity.class, id);
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public ResultatRechercheCaisseValue rechercherMultiCritere(RechercheMulticritereCaisseValue request) {

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();

		CriteriaQuery<CaisseEntity> criteriaQuery = criteriaBuilder.createQuery(CaisseEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();

		Root<CaisseEntity> root = criteriaQuery.from(CaisseEntity.class);

		if (request.getReference() != null && request.getReference() != "") {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_REFERENCE), request.getReference()));
		}

		if (request.getDateClotureDe() != null) {
			whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Calendar> get(PREDICATE_DATECLOTURE),
					request.getDateClotureDe()));
		}

		if (request.getDateClotureDe() != null) {
			whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Calendar> get(PREDICATE_DATECLOTURE),
					request.getDateClotureDe()));
		}
		// Set request.type on whereClause if not empty or null
		if (request.getMagasinId() != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_MAGASINID), request.getMagasinId()));
		}

		/** Lancer la requete */
		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));

		List<CaisseEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

		Set<CaisseValue> list = new HashSet<CaisseValue>();

		for (CaisseEntity entity : resultatEntite) {
			CaisseValue dto = caissePersistanceUtilities.toValue(entity);
			if (dto.getMagasinId() != null) {
				MagasinValue pMagasinValue = new MagasinValue();
				pMagasinValue.setId(dto.getMagasinId());
				dto.setMagasinReference(magasinPersistance.rechercheMagasinParId(pMagasinValue).getDesignation());
			}
			list.add(dto);
		}

		ResultatRechercheCaisseValue resultat = new ResultatRechercheCaisseValue();
		resultat.setListe(list);

		return resultat;
	}

	@Override
	public List<CaisseValue> getAll() {

		List<CaisseEntity> CaisseEntity = this.lister(CaisseEntity.class);

		return caissePersistanceUtilities.toValue(CaisseEntity);
	}

	@Override
	public List<CaisseValue> getCaisseNonCloturer() {
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();

		CriteriaQuery<CaisseEntity> criteriaQuery = criteriaBuilder.createQuery(CaisseEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();

		Root<CaisseEntity> root = criteriaQuery.from(CaisseEntity.class);

		whereClause.add(criteriaBuilder.equal(root.get("cloture"), false));

		/** Lancer la requete */
		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));

		List<CaisseEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

		Set<CaisseValue> list = new HashSet<CaisseValue>();

		for (CaisseEntity entity : resultatEntite) {
			CaisseValue dto = caissePersistanceUtilities.toValue(entity);
			list.add(dto);
		}

		List<CaisseValue> resultat = new ArrayList<>();
		resultat.addAll(list);

		return resultat;
	}

}
