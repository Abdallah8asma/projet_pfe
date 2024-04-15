package com.gpro.consulting.tiers.commun.persistance.elementBase.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.SousFamilleArticleValue;
import com.gpro.consulting.tiers.commun.persistance.elementBase.ISousFamilleArticlePersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.FamilleArticleEntity;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.SousFamilleArticleEntity;
import com.gpro.consulting.tiers.commun.persistance.utilities.PersistanceUtilities;

@Component
public class SousFamilleArticlePersistanceImpl   extends AbstractPersistance implements ISousFamilleArticlePersistance {
	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;
	/** creer sous famille article */
	@Override
	public String creerSousFamilleArticle(SousFamilleArticleValue pSousFamilleArticleValue) {
		FamilleArticleEntity vFamilleArt=new FamilleArticleEntity();
		if(pSousFamilleArticleValue.getIdFamilleArticle()!=null){
			   vFamilleArt=this.rechercherParId(pSousFamilleArticleValue.getIdFamilleArticle(), FamilleArticleEntity.class);
		}
		SousFamilleArticleEntity sousFamilleArticleEntite= (SousFamilleArticleEntity) this.creer(PersistanceUtilities.toEntity(pSousFamilleArticleValue,vFamilleArt));
		SousFamilleArticleValue sousFamilleArticleValueResult=PersistanceUtilities.toValue(sousFamilleArticleEntite);
		return sousFamilleArticleValueResult.getId().toString();
	}

	/** supprimer  sous famille article */

	@Override
	public void supprimerSousFamilleArticle(Long pSousFamilleArticleId) {
		this.supprimerEntite(SousFamilleArticleEntity.class, pSousFamilleArticleId);
		
	}

	/** modifier  sous famille article */
	@Override
	public String modifierSousFamilleArticle(SousFamilleArticleValue pSousFamilleArticleValue) {
		FamilleArticleEntity vFamilleArt=new FamilleArticleEntity();
		if(pSousFamilleArticleValue.getIdFamilleArticle()!=null){
			   vFamilleArt=this.rechercherParId(pSousFamilleArticleValue.getIdFamilleArticle(), FamilleArticleEntity.class);
		}
		SousFamilleArticleEntity sousFamilleArticleEntity= (SousFamilleArticleEntity) this.modifier(PersistanceUtilities.toEntity(pSousFamilleArticleValue,vFamilleArt));
		SousFamilleArticleValue SousFamilleArticleResult=PersistanceUtilities.toValue(sousFamilleArticleEntity);
		return SousFamilleArticleResult.getId().toString();	
		}

	/** recherche by id sous famille article */
	@Override
	public SousFamilleArticleValue rechercheSousFamilleArticleById(Long pSousFamilleArticleId) {
		SousFamilleArticleEntity sousFamilleArticleEntity=this.rechercherParId(pSousFamilleArticleId,SousFamilleArticleEntity.class);
		SousFamilleArticleValue sousSamileArticleResult =PersistanceUtilities.toValue(sousFamilleArticleEntity);
		return sousSamileArticleResult;
	}
	
	/** lister  sous famille article */
	@Override
	public List<SousFamilleArticleValue> listeSousFamilleArticle() {
		java.util.List<SousFamilleArticleEntity> pSousFamilleArticleEntites = this.lister(SousFamilleArticleEntity.class);
		List <SousFamilleArticleValue> ListSousFamilleArticle= PersistanceUtilities.tolistSousFamilleArticleValues(pSousFamilleArticleEntites);
		return ListSousFamilleArticle;
	}

	/**get entity manager */
	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	

}
