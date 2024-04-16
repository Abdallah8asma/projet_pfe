package com.gpro.consulting.tiers.logistique.persistance.atelier.rouleaufini.impl;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.ChoixRouleauValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.EntrepotValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.RechercheMulticritereEntrepotValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.ResultatRechecheEntrepotValue;
import com.gpro.consulting.tiers.logistique.persistance.atelier.rouleaufini.IEntrepotPersistance;
import com.gpro.consulting.tiers.logistique.persistance.atelier.rouleaufini.entity.ChoixRouleauEntity;
import com.gpro.consulting.tiers.logistique.persistance.atelier.rouleaufini.entity.EntrepotEntity;
import com.gpro.consulting.tiers.logistique.persistance.atelier.rouleaufini.utilities.RouleauFiniPersistanceUtilities;

/**
 * Implementation of {@link IEntrepotPersistance} interface.
 * 
 * @author Wahid Gazzah
 * @since 11/12/2015
 *
 */

@Component
public class EntrepotPersistanceImpl extends AbstractPersistance implements IEntrepotPersistance{
	
	private static final Logger logger = LoggerFactory.getLogger(RouleauFiniPersistanceImpl.class);
	
	private String PREDICATE_DESIGNATION = "designation";
	
	@PersistenceContext
	private EntityManager entityManager;
	  
	@Autowired
	private RouleauFiniPersistanceUtilities persistanceUtilities;

	@Override
	public List<EntrepotValue> listeEntrepot() {
		//logger.info("EntrepotPersistanceImpl --listeEntrepot");
		List<EntrepotEntity> vListeEntrepotEntite = this.lister(EntrepotEntity.class);
		List<EntrepotValue> vListEntrepotValues = new ArrayList<EntrepotValue>();
		for (EntrepotEntity vEntrepotEntity : vListeEntrepotEntite) {
			vListEntrepotValues.add(persistanceUtilities.toValue(vEntrepotEntity));
		}
		return vListEntrepotValues;	}

	@Override
	public ResultatRechecheEntrepotValue rechercherMultiCritere(RechercheMulticritereEntrepotValue request) {

		//logger.info("EntrepotPersistanceImpl --rechercherMultiCritere");

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();

		CriteriaQuery<EntrepotEntity> criteriaQuery = criteriaBuilder.createQuery(EntrepotEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();

		Root<EntrepotEntity> root = criteriaQuery.from(EntrepotEntity.class);
		
		// Set request.designation on whereClause if not empty or null
		if (estNonVide(request.getDesignation())) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_DESIGNATION), request.getDesignation()));
		}
		
		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    List < EntrepotEntity > resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

	    List<EntrepotValue> list = new ArrayList<EntrepotValue>();
	    
	    for (EntrepotEntity entity : resultatEntite) {
	    	EntrepotValue dto = persistanceUtilities.toValue(entity);
	    	list.add(dto);
	    }

	    ResultatRechecheEntrepotValue resultat = new ResultatRechecheEntrepotValue();
	    resultat.setNombreResultaRechercher(Long.valueOf(list.size()));
	    Collections.sort(list);
	    resultat.setList(new TreeSet<>(list));

	    return resultat;
	}

	@Override
	public String createEntrepot(EntrepotValue request) {

		EntrepotEntity entity = (EntrepotEntity) this.creer(persistanceUtilities.toEntity(request));

	    return entity.getId().toString();
	}

	@Override
	public void deleteEntrepot(Long id) {

		this.supprimerEntite(EntrepotEntity.class, id.longValue());
	}

	@Override
	public String updateEntrepot(EntrepotValue request) {
		
		EntrepotEntity entity = (EntrepotEntity) this.modifier(persistanceUtilities.toEntity(request));

	    return entity.getId().toString();
	}

	@Override
	public EntrepotValue getEntrepotById(Long id) {
		
		EntrepotEntity entity = this.rechercherParId(id, EntrepotEntity.class);

	    return persistanceUtilities.toValue(entity);
	}

	private boolean estNonVide(String val) {
		return val != null && !"".equals(val);
	}

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
