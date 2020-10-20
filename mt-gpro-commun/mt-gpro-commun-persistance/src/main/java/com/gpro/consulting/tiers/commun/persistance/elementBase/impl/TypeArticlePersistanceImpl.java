package com.gpro.consulting.tiers.commun.persistance.elementBase.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.TypeArticleValue;
import com.gpro.consulting.tiers.commun.persistance.elementBase.ITypeArticlePersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.TypeArticleEntity;
import com.gpro.consulting.tiers.commun.persistance.utilities.PersistanceUtilities;
import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;

/**
* The Class TypeArticleImpl.
* @author mohamed
*/

@Component
public class TypeArticlePersistanceImpl extends AbstractPersistance implements  ITypeArticlePersistance{

	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;
	
	
	/* 
	 * creer type article
	 */
	@Override
	public String creerTypeArticle(TypeArticleValue pTypeArticleValue) {
		TypeArticleEntity typeArticleEntity= (TypeArticleEntity) this.creer(PersistanceUtilities.toEntity(pTypeArticleValue));
		TypeArticleValue typeArticleValueResult=PersistanceUtilities.toValue(typeArticleEntity);
		return typeArticleValueResult.getId().toString();
	}

	/* 
	 * supprimer type article
	 */
	@Override
	public void supprimerTypeArticle(Long pTypeArticleValueId) {
		this.supprimerEntite(TypeArticleEntity.class, pTypeArticleValueId);
	}

	/* 
	 * modifier type article
	 */
	
	@Override
	public String modifierTypeArticle(TypeArticleValue pTypeArticleValue) {
		TypeArticleEntity typeArticleEntity= (TypeArticleEntity) this.modifier(PersistanceUtilities.toEntity(pTypeArticleValue));
		TypeArticleValue typeArticleValueResult=PersistanceUtilities.toValue(typeArticleEntity);
		return typeArticleValueResult.getId().toString();
	}

	/* 
	 * recherche by id type article
	 */
	@Override
	public TypeArticleValue rechercheTypeArticleById(Long pTypeArticleValueId) {
		TypeArticleEntity typeArticleEntity= (TypeArticleEntity) this.rechercherParId(pTypeArticleValueId,TypeArticleEntity.class);
		TypeArticleValue typeArticleValueResult=PersistanceUtilities.toValue(typeArticleEntity);
		return typeArticleValueResult;
	}

	/* 
	 * liste type article 
	 */
	@Override
	public List<TypeArticleValue> listeTypeArticle() {
		java.util.List<TypeArticleEntity> typeArticleEntitys = this.lister(TypeArticleEntity.class);
		List <TypeArticleValue> ListTypeArticle= PersistanceUtilities.tolistTypeArticleValues(typeArticleEntitys);
		return ListTypeArticle;
	}

	/* 
	 * get entitymanager
	 */
	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * Sets the entity manager.
	 *
	 * @param entityManager the new entity manager
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
