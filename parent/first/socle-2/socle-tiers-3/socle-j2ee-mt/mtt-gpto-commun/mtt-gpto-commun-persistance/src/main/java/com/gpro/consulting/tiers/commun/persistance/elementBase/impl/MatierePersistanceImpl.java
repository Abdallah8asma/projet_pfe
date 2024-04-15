package com.gpro.consulting.tiers.commun.persistance.elementBase.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.MatiereArticleValue;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IMatiereArticlePersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.MatiereArticleEntite;
import com.gpro.consulting.tiers.commun.persistance.utilities.PersistanceUtilities;

@Component
public class MatierePersistanceImpl extends AbstractPersistance 
									implements IMatiereArticlePersistance{

	/** EntityManager. **/
	@PersistenceContext
	private EntityManager entityManager; 
	
	/** Loger */
	private static Logger log = Logger.getLogger(MatierePersistanceImpl.class);


	/************************** Creation Matiere *****************************/
	@Override
	public String creerMatiere(MatiereArticleValue pMatiereValue) {
		/*if (log.isDebugEnabled()) {
		      log.debug(" ** Creation de la MatitereArticle " + pMatiereValue.getDesignation()+ " est en cours.");
		    }*/
		MatiereArticleEntite pMatiereEntite=(MatiereArticleEntite) this.creer(PersistanceUtilities.toEntity(pMatiereValue));
		MatiereArticleValue pMatiereValueResult=PersistanceUtilities.toValue(pMatiereEntite);
		return pMatiereValueResult.getId().toString();
	}
	/************************** Suppression Matiere *****************************/
	@Override
	public void supprimerMatiere(Long pId) {
		  if (log.isDebugEnabled()) {
		      //log.debug(" ** Suppression de la MatitereArticle de l'ID=" + pId.longValue() + " est en cours.");
		    }
		this.supprimerEntite(MatiereArticleEntite.class,pId.longValue());
		
	}
	/************************ Modification Matiere ******************************/
	@Override
	public String modifierMatiere(MatiereArticleValue pMatiereValue) {
		if (log.isDebugEnabled()) {
		      //log.debug(" ** Modification de la MatitereArticle de l'ID=" + pMatiereValue.getId().toString() + " est en cours.");
		    }
		MatiereArticleEntite pMatiereEntite=(MatiereArticleEntite) this.modifier(PersistanceUtilities.toEntity(pMatiereValue));
		MatiereArticleValue pMatiereValueResult=PersistanceUtilities.toValue(pMatiereEntite);
		return pMatiereValueResult.getId().toString();
	}
	/**************************** Recherche Matiere ******************************/
	@Override
	public MatiereArticleValue rechercheMatiereParId(MatiereArticleValue pMatiereValue) {
		if (log.isDebugEnabled()) {
		      //log.debug(" ** REcherche de la MatitereArticle de l'ID=" + pMatiereValue.getId().toString() + " est en cours.");
		    }
		MatiereArticleEntite pMatiereEntite=(MatiereArticleEntite) this.rechercherParId(pMatiereValue.getId().longValue(), MatiereArticleEntite.class);
		MatiereArticleValue pMatiereValueResult=PersistanceUtilities.toValue(pMatiereEntite);
		return pMatiereValueResult;
	}
	/************************* Liste de toutes les Matieres *************************/
	@Override
	public List<MatiereArticleValue> listeMatiere() {
		
	    List < MatiereArticleEntite > vListeMatiereArticleEntite = this.lister(MatiereArticleEntite.class);
	    if(!vListeMatiereArticleEntite.isEmpty()){
	    	 List <MatiereArticleValue> ListMatiereArticle= PersistanceUtilities.tolistMatiereArticleValues(vListeMatiereArticleEntite);
	    	 //log.debug("** liste  des matieres article non vide");
			    return ListMatiereArticle;
	    }else{
	    	 //log.debug("** liste  des matieres article vide");
			    return null;
	    }
	   
	  }
		
	/***************************** Getter & Setter ********************************/
	public EntityManager getEntityManager() {
		return entityManager;
	}
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	
}
