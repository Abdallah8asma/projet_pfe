package com.gpro.consulting.tiers.commun.persistance.elementBase.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.UniteArticleValue;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IUniteArticlePersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.UniteArticleEntity;
import com.gpro.consulting.tiers.commun.persistance.utilities.PersistanceUtilities;
import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;

/**
* The Class uniteArticleImpl.
* @author mohamed
*/

@Component
public class UniteArticlePersistanceImpl  extends AbstractPersistance implements  IUniteArticlePersistance{
	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;
	
	/* (non-Javadoc)
	 * creer unite article
	 */
	@Override
	public String creerUniteArticle(UniteArticleValue pUniteArticleValue) {
		UniteArticleEntity uniteArticleEntity= (UniteArticleEntity) this.creer(PersistanceUtilities.toEntity(pUniteArticleValue));
		UniteArticleValue uniteArticleValueResult=PersistanceUtilities.toValue(uniteArticleEntity);
		return uniteArticleValueResult.getId().toString();	
	}

	/* (non-Javadoc)
	 * supprimer unite article
	 */
	@Override
	public void supprimerUniteArticle(Long pUniteArticleValueId) {
		this.supprimerEntite(UniteArticleEntity.class, pUniteArticleValueId);
	}

	/* (non-Javadoc)
	 * modifier unite article
	 */
	@Override
	public String modifierUniteArticle(UniteArticleValue pUniteArticleValue) {
		UniteArticleEntity uniteArticleEntity= (UniteArticleEntity) this.modifier(PersistanceUtilities.toEntity(pUniteArticleValue));
		UniteArticleValue uniteArticleValueResult=PersistanceUtilities.toValue(uniteArticleEntity);
		return uniteArticleValueResult.getId().toString();	
	}

	/* (non-Javadoc)
	 * recherche by id unite article
	 */
	@Override
	public UniteArticleValue rechercheUniteArticleById(Long pUniteArticleValueId) {
		UniteArticleEntity uniteArticleEntity= (UniteArticleEntity) this.rechercherParId(pUniteArticleValueId,UniteArticleEntity.class);
		UniteArticleValue uniteArticleValueResult=PersistanceUtilities.toValue(uniteArticleEntity);
		return uniteArticleValueResult;	
	}

	/* (non-Javadoc)
	 * liste unite article
	 */
	@Override
	public List<UniteArticleValue> listeUniteArticle() {
		List <UniteArticleEntity > vListeUniteArticleEntite = this.lister(UniteArticleEntity.class);
	    List < UniteArticleValue > vListeUniteArticleValues = new ArrayList < UniteArticleValue >();
	    for (UniteArticleEntity vUniteArticleEntite : vListeUniteArticleEntite) {
	    	vListeUniteArticleValues.add(PersistanceUtilities.toValue(vUniteArticleEntite));
	    }
	    return vListeUniteArticleValues;
	}
 
	/********** Getter & Setter ***********/
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	

}
