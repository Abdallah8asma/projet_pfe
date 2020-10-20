package com.gpro.consulting.tiers.commun.persistance.elementBase.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.GrosseurValue;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IGrosseurPersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.GrosseurEntite;
import com.gpro.consulting.tiers.commun.persistance.utilities.PersistanceUtilities;
import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;

@Component
public class GrosseurPersistanceImpl extends AbstractPersistance 
									 implements IGrosseurPersistance{

	/** EntityManager. **/
	@PersistenceContext
	private EntityManager entityManager; 
	
	/** Logger*/
	private static Logger log = Logger.getLogger(MatierePersistanceImpl.class);

	/************************** Creation Grosseur *****************************/
	@Override
	public String creerGrosseur(GrosseurValue pGrosseurValue) {
		if (log.isDebugEnabled()) {
	      //log.debug(" ** Creation du Grosseur " + pGrosseurValue.getDesignation() + " est en cours.");
	    }
		GrosseurEntite pGrosseurEntite=(GrosseurEntite) this.creer(PersistanceUtilities.toEntity(pGrosseurValue));
		GrosseurValue pGrosseurValueResult=PersistanceUtilities.toValue(pGrosseurEntite);
		return pGrosseurValueResult.getId().toString();
	}
	/************************** Suppression Grosseur *****************************/
	@Override
	public void supprimerGrosseur(Long pId) {
		if (log.isDebugEnabled()) {
		      //log.debug(" ** Suppression du Grosseur de l'ID=" + pId.toString()  + " est en cours.");
		    }
		this.supprimerEntite(GrosseurEntite.class,pId.longValue());
	}
	/************************ Modification Grosseur ******************************/
	@Override
	public String modifierGrosseur(GrosseurValue pGrosseurValue) {
		if (log.isDebugEnabled()) {
		      //log.debug(" ** Modification du Grosseur de l'ID=" + pGrosseurValue.getId().toString() + " est en cours.");
		    }
		GrosseurEntite pGrosseurEntite=(GrosseurEntite) this.modifier(PersistanceUtilities.toEntity(pGrosseurValue));
		GrosseurValue pGrosseurValueResult=PersistanceUtilities.toValue(pGrosseurEntite);
		return pGrosseurValueResult.getId().toString();
	}
	/************************ Recherche Grosseur ******************************/
	@Override
	public GrosseurValue rechercheGrosseurParId(GrosseurValue pGrosseurValue) {
		if (log.isDebugEnabled()) {
		      //log.debug(" ** Recherche du Grosseur de l'ID=" + pGrosseurValue.getId().toString() + " est en cours.");
		    }
		GrosseurEntite pGrosseurEntite=(GrosseurEntite) this.rechercherParId(pGrosseurValue.getId().longValue(), GrosseurEntite.class);
		GrosseurValue pGrosseurValueResult=PersistanceUtilities.toValue(pGrosseurEntite);
		return pGrosseurValueResult;
		
	}
	/************************ Liste Grosseur ******************************/
	@Override
	public List<GrosseurValue> listeGrosseur() {

	    List < GrosseurEntite > vListeGrosseurEntite = this.lister(GrosseurEntite.class);
	    if(!vListeGrosseurEntite.isEmpty()){
	    	 List <GrosseurValue> ListGrosseur= PersistanceUtilities.tolistGrosseurValues(vListeGrosseurEntite);
	    	 //log.debug("** liste  des grosseurs non vide");
			    return ListGrosseur;
	    }else{
	    	 //log.debug("** liste  des grosseurs vide");
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
