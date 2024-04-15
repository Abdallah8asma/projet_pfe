package com.gpro.consulting.tiers.commun.persistance.partieInteressee.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.CategorieValue;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.ICategoriePartieInteresseePersistance;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.entity.CategorieEntite;
import com.gpro.consulting.tiers.commun.persistance.utilities.PersistanceUtilities;

/**
 * The Class CategoriePartieInteresseeImpl.
 * 
 * @author $mohamed
 */

@Component
public class CategoriePartieInteresseePersistanceImpl extends AbstractPersistance
		implements ICategoriePartieInteresseePersistance {

	/** EntityManager. */
	@PersistenceContext
	private EntityManager entityManager;

	/*
	 * methode de creation cathegorie Partie Interessée
	 */
	@Override
	public String creerCategoriePartieInteressee(CategorieValue pCategorieValue) {
		CategorieEntite pCategorieEntite = (CategorieEntite) this.creer(PersistanceUtilities.toEntity(pCategorieValue));
		CategorieValue pCategorieValueResult = PersistanceUtilities.toValue(pCategorieEntite);
		return pCategorieValueResult.getId().toString();
	}

	/**************** supprimer categorie partie interessee *******************/
	@Override
	public void supprimerCategoriePartieInteressee(Long pCategorieValue) {
		this.supprimerEntite(CategorieEntite.class, pCategorieValue);
	}

	/**************** modifier categorie partie interessee *******************/
	@Override
	public String modifierCategoriePartieInteressee(CategorieValue pCategorieValue) {
		CategorieEntite pCategorieEntite = (CategorieEntite) this
				.modifier(PersistanceUtilities.toEntity(pCategorieValue));
		CategorieValue pCategorieValueResult = PersistanceUtilities.toValue(pCategorieEntite);
		return pCategorieValueResult.getId().toString();
	}

	/****************
	 * recherche categorie partie interessee Par Id
	 *******************/

	@Override
	public CategorieValue rechercheCategoriePartieInteresseeParId(CategorieValue pCategorieValue) {
		CategorieEntite pCategorieEntite = this.rechercherParId(pCategorieValue.getId().longValue(),
				CategorieEntite.class);
		CategorieValue pCategorieValueResult = PersistanceUtilities.toValue(pCategorieEntite);
		return pCategorieValueResult;
	}

	/**************** liste categorie partie interessee *******************/

	@Override
	public java.util.List<CategorieValue> listeCategoriePartieInteressee() {
		List<CategorieEntite> vCategoriesEntity = this.lister(CategorieEntite.class);
		List<CategorieValue> vListCategoriesValues = new ArrayList<CategorieValue>();
		for (CategorieEntite vCategorieEntite : vCategoriesEntity) {
			vListCategoriesValues.add(PersistanceUtilities.toValue(vCategorieEntite));
		}
		return vListCategoriesValues;
	}

	/*
	 * get entity manager
	 */
	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
