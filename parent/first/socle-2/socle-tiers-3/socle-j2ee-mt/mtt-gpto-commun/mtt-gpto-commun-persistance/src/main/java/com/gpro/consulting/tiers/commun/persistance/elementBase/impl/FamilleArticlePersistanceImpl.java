package com.gpro.consulting.tiers.commun.persistance.elementBase.impl;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.FamilleArticleValue;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IFamilleArticlePersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.FamilleArticleEntity;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.TypeArticleEntity;
import com.gpro.consulting.tiers.commun.persistance.utilities.PersistanceUtilities;
import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;


/**
 * The Class FamilleArticleImpl.
 * @author mohamed
 */

@Component
public class FamilleArticlePersistanceImpl extends AbstractPersistance implements  IFamilleArticlePersistance  {
	
	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;
	
	 private static Logger log =Logger.getLogger(FamilleArticlePersistanceImpl.class);
			/**ajouter  famille article**/
	@Override
	public String creerFamilleArticle(FamilleArticleValue pFamilleArticleValue) {
		if(log.isDebugEnabled()){
			//log.debug("creation de  famille  article   de designation =" + pFamilleArticleValue.getDesignation() + " est en cours.");
		}
		//log.debug(" test=" + pFamilleArticleValue.getDesignation() + " est en cours.");
		 TypeArticleEntity vTypeArt=new TypeArticleEntity();
			if(pFamilleArticleValue.getIdTypeArticle()!=null){
				   vTypeArt=this.rechercherParId(pFamilleArticleValue.getIdTypeArticle(), TypeArticleEntity.class);
			}
		FamilleArticleEntity familleArticleEntite= (FamilleArticleEntity) this.creer(PersistanceUtilities.toEntity(pFamilleArticleValue,vTypeArt));
		FamilleArticleValue familleArticleValueResult=PersistanceUtilities.toValue(familleArticleEntite);
		return familleArticleValueResult.getId().toString();
	}

	/**supprimer   famille article**/

	@Override
	public void supprimerFamilleArticle(Long pFamilleArticleId) {
		if (log.isDebugEnabled()) {

			//log.debug("Suppression de la famille article d'ID=" + pFamilleArticleId.toString() + " est en cours.");

           }
          this.supprimerEntite(FamilleArticleEntity.class, pFamilleArticleId);		
	}

	
	/**modifier  famille article**/
	@Override
	public String modifierFamilleArticle(FamilleArticleValue pFamilleArticleValue) {
		 TypeArticleEntity vTypeArt=new TypeArticleEntity();
		if(pFamilleArticleValue.getIdTypeArticle()!=null){
			   vTypeArt=this.rechercherParId(pFamilleArticleValue.getIdTypeArticle(), TypeArticleEntity.class);
		}
		FamilleArticleEntity familleArticleEntity= (FamilleArticleEntity) this.modifier(PersistanceUtilities.toEntity(pFamilleArticleValue,vTypeArt));
		FamilleArticleValue familleArticleResult=PersistanceUtilities.toValue(familleArticleEntity);
		return familleArticleResult.getId().toString();
	}

	/**recherche by id  famille article**/
	@Override
	public FamilleArticleValue rechercheFamilleArticleById(Long pFamilleArticleId) {
		FamilleArticleEntity fAmilleArticleEntite=this.rechercherParId(pFamilleArticleId,FamilleArticleEntity.class);
		FamilleArticleValue famileArticleResult =PersistanceUtilities.toValue(fAmilleArticleEntite);
		return famileArticleResult;
	}


	/**list  famille article**/
	@Override
	public List<FamilleArticleValue> listeFamilleArticle() {
		 java.util.List<FamilleArticleEntity> pFamilleArticleEntites = this.lister(FamilleArticleEntity.class);
		 if(!pFamilleArticleEntites.isEmpty()){
			 List <FamilleArticleValue> ListFamilleArticle= PersistanceUtilities.tolistFamilleArticleValues(pFamilleArticleEntites);
			 //log.debug("liste  de la famille article non vide");
				return ListFamilleArticle;
		 }else{
				//log.debug("liste  de la famille article est vide  ");
				return null;
		 }
	}

	/**get    entityManager**/
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
