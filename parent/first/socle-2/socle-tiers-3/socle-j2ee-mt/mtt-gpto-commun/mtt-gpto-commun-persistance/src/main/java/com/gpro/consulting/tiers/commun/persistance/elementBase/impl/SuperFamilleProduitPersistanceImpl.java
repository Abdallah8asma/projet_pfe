package com.gpro.consulting.tiers.commun.persistance.elementBase.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.SuperFamilleProduitValue;
import com.gpro.consulting.tiers.commun.persistance.elementBase.ISuperFamilleProduitPersitance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.SuperFamilleProduitEntity;
import com.gpro.consulting.tiers.commun.persistance.utilities.PersistanceUtilities;

/**
 * The Class SuperFamilleProduitPersistanceImpl.
 * @author med
 */

@Component
public class SuperFamilleProduitPersistanceImpl extends AbstractPersistance implements ISuperFamilleProduitPersitance{

	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;
	
	/*
	 * creer famille prroduit
	 */
	@Override
	public String creerSuperFamilleProduit(SuperFamilleProduitValue pSuperFamilleProduitValue) {
		 SuperFamilleProduitEntity vFamilletity= (SuperFamilleProduitEntity) this.creer(PersistanceUtilities.toEntity(pSuperFamilleProduitValue));
		 SuperFamilleProduitValue vFamilleValueResult=PersistanceUtilities.toValue(vFamilletity);
		 return vFamilleValueResult.getId().toString();	
	}

	/* 
	 * supprimer
	 */
	@Override
	public void supprimerSuperFamilleProduit(Long pSuperFamilleProduitId) {
		this.supprimerEntite(SuperFamilleProduitEntity.class, pSuperFamilleProduitId);		
	}

	/* 
	 * modifier
	 */
	@Override
	public String modifierSuperFamilleProduit(
			SuperFamilleProduitValue pSuperFamilleProduitValue) {
		 SuperFamilleProduitEntity vFamilletity= (SuperFamilleProduitEntity) this.modifier(PersistanceUtilities.toEntity(pSuperFamilleProduitValue));
		 SuperFamilleProduitValue vFamilleValueResult=PersistanceUtilities.toValue(vFamilletity);
		 return vFamilleValueResult.getId().toString();	
	}

	/* (non-Javadoc)
	 * recherche by id
	 */
	@Override
	public SuperFamilleProduitValue rechercheSuperFamilleProduitById(
			Long pSuperFamilleProduitId) {
		 SuperFamilleProduitEntity vFamilletity= (SuperFamilleProduitEntity) this.rechercherParId(pSuperFamilleProduitId, SuperFamilleProduitEntity.class);
		 SuperFamilleProduitValue vFamilleValueResult=PersistanceUtilities.toValue(vFamilletity);
		 return vFamilleValueResult;
	}

	/* liste famille
	 */
	@Override
	public List<SuperFamilleProduitValue> listeSuperFamilleProduit() {
		 java.util.List<SuperFamilleProduitEntity> vFamillesEntity = this.lister(SuperFamilleProduitEntity.class);
		 if(!vFamillesEntity.isEmpty()){
		 List <SuperFamilleProduitValue> ListFamille= PersistanceUtilities.tolistSuperFamilleProduitValues(vFamillesEntity);
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
