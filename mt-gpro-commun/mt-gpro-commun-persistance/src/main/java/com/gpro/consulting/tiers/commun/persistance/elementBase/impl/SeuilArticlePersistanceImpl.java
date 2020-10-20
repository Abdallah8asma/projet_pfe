package com.gpro.consulting.tiers.commun.persistance.elementBase.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.SeuilArticleValue;
import com.gpro.consulting.tiers.commun.persistance.elementBase.ISeuilArticlePersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.SeuilArticleEntity;
import com.gpro.consulting.tiers.commun.persistance.utilities.PersistanceUtilities;
import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;

/**
* The Class SeuilArticleImpl.
* @author mohamed
*/

@Component
public class SeuilArticlePersistanceImpl extends AbstractPersistance implements  ISeuilArticlePersistance {
	/**entity manager ***/
	@PersistenceContext
	private EntityManager entityManager;

	/*****creer seuil article**/
	@Override
	public String creerSeuilArticle(SeuilArticleValue pSeuilValue) {
		SeuilArticleEntity seuilArticleEntity= (SeuilArticleEntity) this.creer(PersistanceUtilities.toEntity(pSeuilValue));
		SeuilArticleValue seuilArticleValueResult=PersistanceUtilities.toValue(seuilArticleEntity);
		return seuilArticleValueResult.getId().toString();
	}

	/***supprimer seuil article***/
	@Override
	public void supprimerSeuilArticle(Long pSeuilValueId) {
		this.supprimerEntite(SeuilArticleEntity.class, pSeuilValueId);
	}

	/***modifier seuil article**/
	@Override
	public String modifierSeuilArticle(SeuilArticleValue pSeuilValue) {
		SeuilArticleEntity seuilArticleEntity= (SeuilArticleEntity) this.modifier(PersistanceUtilities.toEntity(pSeuilValue));
		SeuilArticleValue seuilArticleValueResult=PersistanceUtilities.toValue(seuilArticleEntity);
		return seuilArticleValueResult.getId().toString();
	}

	/***recherche seuil article**/
	@Override
	public SeuilArticleValue rechercheSeuilArticleById(Long pFamilleArticleId) {
		SeuilArticleEntity seuilArticleEntity= (SeuilArticleEntity) this.rechercherParId(pFamilleArticleId,SeuilArticleEntity.class);
		SeuilArticleValue seuilArticleValueResult=PersistanceUtilities.toValue(seuilArticleEntity);
		return seuilArticleValueResult;
	}

	/***liste seuil article**/
	@Override
	public List<SeuilArticleValue> listeSeuilArticle() {
		List <SeuilArticleEntity > vListeSeuilArticleEntite = this.lister(SeuilArticleEntity.class);
	    List < SeuilArticleValue > vListeSeuilArticleValues = new ArrayList < SeuilArticleValue >();
	    for (SeuilArticleEntity vSeuilArticleValue : vListeSeuilArticleEntite) {
	    	vListeSeuilArticleValues.add(PersistanceUtilities.toValue(vSeuilArticleValue));
	    }
	    return vListeSeuilArticleValues;
	}

	/***get entity manager **/
	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
}
