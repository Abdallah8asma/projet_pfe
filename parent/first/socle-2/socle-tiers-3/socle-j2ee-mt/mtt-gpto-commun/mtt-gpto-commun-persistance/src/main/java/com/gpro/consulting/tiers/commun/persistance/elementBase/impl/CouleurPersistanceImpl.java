package com.gpro.consulting.tiers.commun.persistance.elementBase.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.CouleurValue;
import com.gpro.consulting.tiers.commun.persistance.elementBase.ICouleurPersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.CouleurEntite;
import com.gpro.consulting.tiers.commun.persistance.utilities.PersistanceUtilities;

@Component
public class CouleurPersistanceImpl extends AbstractPersistance 
									implements ICouleurPersistance{

	/** EntityManager. **/
	@PersistenceContext
	private EntityManager entityManager; 
	
	/** Logger*/
	  private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ArticlePersistanceImpl.class);

	
	/************************** Creation Couleur *****************************/
	@Override
	public String creerCouleur(CouleurValue pCouleurValue) {
		if (LOGGER.isDebugEnabled()) {
			//LOGGER.debug(" ** Creation du Couleur " + pCouleurValue.getDesignation() + " est en cours.");
		}
		CouleurEntite pCouleurEntite=(CouleurEntite) this.creer(PersistanceUtilities.toEntity(pCouleurValue));
		CouleurValue pCouleurValueResult=PersistanceUtilities.toValue(pCouleurEntite);
	return pCouleurValueResult.getId().toString();
	}
	
	/************************** Suppression Couleur *****************************/
	@Override
	public void supprimerCouleur(Long pId) {
		if (LOGGER.isDebugEnabled()) {
			//LOGGER.debug(" ** Suppression du Couleur de l'ID=" + pId.longValue() + " est en cours.");
		}
		this.supprimerEntite(CouleurEntite.class, pId.longValue());
	}
	
	/************************ Modification Couleur ******************************/
	@Override
	public String modifierCouleur(CouleurValue pCouleurValue) {
		if (LOGGER.isDebugEnabled()) {
			//LOGGER.debug(" ** Modification du Couleur de l'ID=" + pCouleurValue.getId().toString() + " est en cours.");
		}
		CouleurEntite pCouleurEntite=(CouleurEntite) this.modifier(PersistanceUtilities.toEntity(pCouleurValue));
		CouleurValue pCouleurValueResult=PersistanceUtilities.toValue(pCouleurEntite);
	return pCouleurValueResult.getId().toString();
	}
	
	/************************ Recherche Couleur ******************************/
	@Override
	public CouleurValue rechercheCouleurParId(CouleurValue pCouleurValue) {
		if (LOGGER.isDebugEnabled()) {
			//LOGGER.debug(" ** Recherche du Couleur de l'ID=" + pCouleurValue.getId().toString() + " est en cours.");
		}
		CouleurEntite pCouleurEntite=(CouleurEntite) this.rechercherParId(pCouleurValue.getId().longValue(), CouleurEntite.class);
		CouleurValue pCouleurValueResult=PersistanceUtilities.toValue(pCouleurEntite);
	return pCouleurValueResult;
	
	}
	
	/************************ Liste Couleur ******************************/
	
	@Override
	public List<CouleurValue> listeCouleur() {
		List < CouleurEntite > vListeCouleurEntite = this.lister(CouleurEntite.class);
		List < CouleurValue > vListCouleurValues = new ArrayList < CouleurValue >();
			for (CouleurEntite vCouleurEntite : vListeCouleurEntite) {
			vListCouleurValues.add(PersistanceUtilities.toValue(vCouleurEntite));
			}
	return vListCouleurValues;
	}
	
	/***************************** Getter & Setter ********************************/
	public EntityManager getEntityManager() {
	return entityManager;
	}
	public void setEntityManager(EntityManager entityManager) {
	this.entityManager = entityManager;
	}

	

}
