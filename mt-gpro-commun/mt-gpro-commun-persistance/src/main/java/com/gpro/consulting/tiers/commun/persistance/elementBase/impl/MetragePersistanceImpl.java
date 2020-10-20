package com.gpro.consulting.tiers.commun.persistance.elementBase.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.MetrageValue;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IMetragePersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.MetrageEntite;
import com.gpro.consulting.tiers.commun.persistance.utilities.PersistanceUtilities;
import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;

@Component
public class MetragePersistanceImpl extends AbstractPersistance 
									implements IMetragePersistance {

	/** EntityManager. **/
	@PersistenceContext
	private EntityManager entityManager; 
	
	/** Loger */
	private static Logger log = Logger.getLogger(MatierePersistanceImpl.class);

	/************************** Creation Metrage *****************************/
	@Override
	public String creerMetrage (MetrageValue pMetrageValue) {
		if (log.isDebugEnabled()) {
		      //log.debug(" ** Creation du Metrafe " + pMetrageValue.getDesignation()+ " est en cours.");
		    }
		MetrageEntite pMaetrageEntite=(MetrageEntite) this.creer(PersistanceUtilities.toEntity(pMetrageValue));
		MetrageValue pMetrageValueResult=PersistanceUtilities.toValue(pMaetrageEntite);
		return pMetrageValueResult.getId().toString();
	}
	
	/************************** Suppression Metrage *****************************/
	@Override
	public void supprimerMetrage(Long pId) {
		if (log.isDebugEnabled()) {
		      //log.debug(" ** Suppression du Metrage de l'ID=" + pId.longValue() + " est en cours.");
		    }
		this.supprimerEntite(MetrageEntite.class,pId.longValue());
	}
	
	/************************ Modification Metrage ******************************/
	@Override
	public String modifierMetrage(MetrageValue pMetrageValue) {
		if (log.isDebugEnabled()) {
		      //log.debug(" ** Modification du Metrage de l'ID=" + pMetrageValue.getId().toString() + " est en cours.");
		    }
		MetrageEntite pMaetrageEntite=(MetrageEntite) this.modifier(PersistanceUtilities.toEntity(pMetrageValue));
		MetrageValue pMetrageValueResult=PersistanceUtilities.toValue(pMaetrageEntite);
		return pMetrageValueResult.getId().toString();
	}

	/************************ REchercher Metrage *******************************/
	@Override
	public MetrageValue rechercheMetrageParId(MetrageValue pMetrageValue) {
		if (log.isDebugEnabled()) {
		      //log.debug(" ** REcherche du Metrage de l'ID=" + pMetrageValue.getId().toString() + " est en cours.");
		    }
		MetrageEntite pMetrageEntite=(MetrageEntite) this.rechercherParId(pMetrageValue.getId().longValue(), MetrageEntite.class);
		MetrageValue pMetrageValueResult=PersistanceUtilities.toValue(pMetrageEntite);
		return pMetrageValueResult;
	}
	/**************************** Lister Metrage *******************************/
	@Override
	public List<MetrageValue> listeMetrage() {

		 List < MetrageEntite > vListeMetrageEntite = this.lister(MetrageEntite.class);
		    if(!vListeMetrageEntite.isEmpty()){
		    	 List <MetrageValue> ListMetrage= PersistanceUtilities.tolistMetrageValues(vListeMetrageEntite);
		    	 //log.debug("** liste  des metrages non vide");
				    return ListMetrage;
		    }else{
		    	 //log.debug("** liste  des metrages vide");
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
