package com.gpro.consulting.tiers.commun.persistance.elementBase.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.SousFamilleProduitValue;
import com.gpro.consulting.tiers.commun.persistance.elementBase.ISousFamilleProduitPersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.FamilleProduitEntity;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.SousFamilleProduitEntity;
import com.gpro.consulting.tiers.commun.persistance.utilities.PersistanceUtilities;

// TODO: Auto-generated Javadoc
/**
 * The Class SousFamilleProduitPersistanceImpl.
 * @author med
 */

@Component
public class SousFamilleProduitPersistanceImpl extends AbstractPersistance implements ISousFamilleProduitPersistance{
	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;

	/* creer sous famille produit
	 */
	@Override
	public String creerSousFamilleProduit(
		SousFamilleProduitValue pSousFamilleProduitValue) {
		FamilleProduitEntity vFamilleProduit =new FamilleProduitEntity();
		if(log.isDebugEnabled()){
			//log.debug("creation de sous famille produit   de designation =" + pSousFamilleProduitValue.getDesignation() + " est en cours.");
		}
		if(pSousFamilleProduitValue.getFamilleProduitId()!=null){
			vFamilleProduit=this.rechercherParId(pSousFamilleProduitValue.getFamilleProduitId(), FamilleProduitEntity.class);
		}
	     SousFamilleProduitEntity vSousFamilletity= (SousFamilleProduitEntity) this.creer(PersistanceUtilities.toEntity(pSousFamilleProduitValue,vFamilleProduit));
		 SousFamilleProduitValue vSousFamilleValueResult=PersistanceUtilities.toValue(vSousFamilletity);
		 return vSousFamilleValueResult.getId().toString();	
     }

	/* supprimer  sous famille produit
	 */
	@Override
	public void supprimerSousFamilleProduit(Long pSousFamilleProduitId) {
			this.supprimerEntite(SousFamilleProduitEntity.class, pSousFamilleProduitId);
	}
	
	// modifier 
	@Override
	public String modifierSousFamilleProduit(
			SousFamilleProduitValue pSousFamilleProduitValue) {
		FamilleProduitEntity vFamilleProduit =new FamilleProduitEntity();
		if(log.isDebugEnabled()){
			//log.debug("creation de sous famille produit   de designation =" + pSousFamilleProduitValue.getDesignation() + " est en cours.");
		}
		if(pSousFamilleProduitValue.getFamilleProduitId()!=null){
			vFamilleProduit=this.rechercherParId(pSousFamilleProduitValue.getFamilleProduitId(), FamilleProduitEntity.class);
		}
	     SousFamilleProduitEntity vSousFamilletity= (SousFamilleProduitEntity) this.modifier(PersistanceUtilities.toEntity(pSousFamilleProduitValue,vFamilleProduit));
		 SousFamilleProduitValue vSousFamilleValueResult=PersistanceUtilities.toValue(vSousFamilletity);
		 return vSousFamilleValueResult.getId().toString();	
	}

	// recherche par Id
	@Override
	public SousFamilleProduitValue rechercheSousFamilleProduitById(
			Long pSousFamilleProduitId) {
		 SousFamilleProduitEntity vSousFamilletity= (SousFamilleProduitEntity) this.rechercherParId(pSousFamilleProduitId, SousFamilleProduitEntity.class);
		 SousFamilleProduitValue vSousFamilleValueResult=PersistanceUtilities.toValue(vSousFamilletity);
		 return vSousFamilleValueResult;
	}

	/* (non-Javadoc)
	 * liste sous famille produit
	 */
	

	@Override
	public List<SousFamilleProduitValue> listeSousFamilleProduit() {
		List<SousFamilleProduitEntity> vListeSousFamillesEntity = this.lister(SousFamilleProduitEntity.class);
	    List <SousFamilleProduitValue> vListSousFamilleValue=new ArrayList < SousFamilleProduitValue >();
	    for (SousFamilleProduitEntity vSousFamillesEntity : vListeSousFamillesEntity) {
	     vListSousFamilleValue.add(PersistanceUtilities.toValue(vSousFamillesEntity));
	     }
	    return vListSousFamilleValue;
	}

	/* (non-Javadoc)
	 * 
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
