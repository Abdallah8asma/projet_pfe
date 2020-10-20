package com.gpro.consulting.tiers.commun.persistance.elementBase.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.FamilleProduitValue;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IFamilleProduitPersitance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.FamilleProduitEntity;
import com.gpro.consulting.tiers.commun.persistance.utilities.PersistanceUtilities;

/**
 * The Class FamilleProduitPersistanceImpl.
 * @author med
 */

@Component
public class FamilleProduitPersistanceImpl extends AbstractPersistance implements IFamilleProduitPersitance{

	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;
	
	/*
	 * creer famille prroduit
	 */
	@Override
	public String creerFamilleProduit(FamilleProduitValue pFamilleProduitValue) {
		 FamilleProduitEntity vFamilletity= (FamilleProduitEntity) this.creer(PersistanceUtilities.toEntity(pFamilleProduitValue));
		 FamilleProduitValue vFamilleValueResult=PersistanceUtilities.toValue(vFamilletity);
		 return vFamilleValueResult.getId().toString();	
	}

	/* 
	 * supprimer
	 */
	@Override
	public void supprimerSousFamilleProduit(Long pFamilleProduitId) {
		this.supprimerEntite(FamilleProduitEntity.class, pFamilleProduitId);		
	}

	/* 
	 * modifier
	 */
	@Override
	public String modifierFamilleProduit(
			FamilleProduitValue pFamilleProduitValue) {
		 FamilleProduitEntity vFamilletity= (FamilleProduitEntity) this.modifier(PersistanceUtilities.toEntity(pFamilleProduitValue));
		 FamilleProduitValue vFamilleValueResult=PersistanceUtilities.toValue(vFamilletity);
		 return vFamilleValueResult.getId().toString();	
	}

	/* (non-Javadoc)
	 * recherche by id
	 */
	@Override
	public FamilleProduitValue rechercheFamilleProduitById(
			Long pFamilleProduitId) {
		 FamilleProduitEntity vFamilletity= (FamilleProduitEntity) this.rechercherParId(pFamilleProduitId, FamilleProduitEntity.class);
		 FamilleProduitValue vFamilleValueResult=PersistanceUtilities.toValue(vFamilletity);
		 return vFamilleValueResult;
	}

	/* liste famille
	 */
	@Override
	public List<FamilleProduitValue> listeFamilleProduit() {
		 java.util.List<FamilleProduitEntity> vFamillesEntity = this.lister(FamilleProduitEntity.class);
		 if(!vFamillesEntity.isEmpty()){
		 List <FamilleProduitValue> ListFamille= PersistanceUtilities.tolistFamilleProduitValues(vFamillesEntity);
		  //log.info("liste  de la famille produit non vide");
			return ListFamille;
		 }else{
				//log.info("liste  de la famille produit est vide  ");
				return null;
		 }
	}

	/* (non-Javadoc)
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
