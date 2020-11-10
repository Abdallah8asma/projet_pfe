package com.gpro.consulting.tiers.commun.persistance.elementBase.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.OptionArticleValue;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IOptionArticlePersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.OptionArticleEntity;
import com.gpro.consulting.tiers.commun.persistance.utilities.PersistanceUtilities;

/**
* The Class uniteArticleImpl.
* @author mohamed
*/

@Component
public class OptionArticlePersistanceImpl  extends AbstractPersistance implements  IOptionArticlePersistance{
	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;
	
	/* (non-Javadoc)
	 * creer unite article
	 */
	@Override
	public String creerOptionArticle(OptionArticleValue pOptionArticleValue) {
		OptionArticleEntity optionArticleEntity= (OptionArticleEntity) this.creer(PersistanceUtilities.toEntity(pOptionArticleValue));
		OptionArticleValue optionArticleValueResult=PersistanceUtilities.toValue(optionArticleEntity);
		return optionArticleValueResult.getId().toString();	
	}

	/* (non-Javadoc)
	 * supprimer unite article
	 */
	@Override
	public void supprimerOptionArticle(Long pOptionArticleValueId) {
		this.supprimerEntite(OptionArticleEntity.class, pOptionArticleValueId);
	}

	/* (non-Javadoc)
	 * modifier unite article
	 */
	@Override
	public String modifierOptionArticle(OptionArticleValue pOptionArticleValue) {
		OptionArticleEntity optionArticleEntity= (OptionArticleEntity) this.modifier(PersistanceUtilities.toEntity(pOptionArticleValue));
		OptionArticleValue optionArticleValueResult=PersistanceUtilities.toValue(optionArticleEntity);
		return optionArticleValueResult.getId().toString();	
	}

	/* (non-Javadoc)
	 * recherche by id unite article
	 */
	@Override
	public OptionArticleValue rechercheOptionArticleById(Long pOptionArticleValueId) {
		OptionArticleEntity optionArticleEntity= (OptionArticleEntity) this.rechercherParId(pOptionArticleValueId,OptionArticleEntity.class);
		OptionArticleValue optionArticleValueResult=PersistanceUtilities.toValue(optionArticleEntity);
		return optionArticleValueResult;	
	}

	/* (non-Javadoc)
	 * liste unite article
	 */
	@Override
	public List<OptionArticleValue> listeOptionArticle() {
		List <OptionArticleEntity > vListeOptionArticleEntite = this.lister(OptionArticleEntity.class);
	    List < OptionArticleValue > vListeOptionArticleValues = new ArrayList < OptionArticleValue >();
	    for (OptionArticleEntity vOptionArticleEntite : vListeOptionArticleEntite) {
	    	vListeOptionArticleValues.add(PersistanceUtilities.toValue(vOptionArticleEntite));
	    }
	    return vListeOptionArticleValues;
	}
 
	/********** Getter & Setter ***********/
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	

}
