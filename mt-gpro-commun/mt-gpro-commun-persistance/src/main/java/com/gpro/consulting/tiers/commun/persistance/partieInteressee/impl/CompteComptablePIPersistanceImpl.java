package com.gpro.consulting.tiers.commun.persistance.partieInteressee.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.CompteComptablePIValue;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.ICompteComptablePIPersistance;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.entity.CompteComptablePIEntity;
import com.gpro.consulting.tiers.commun.persistance.utilities.PersistanceUtilities;

/**
* The Class uniteArticleImpl.
* @author mohamed
*/

@Component
public class CompteComptablePIPersistanceImpl  extends AbstractPersistance implements  ICompteComptablePIPersistance{
	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;
	
	/* (non-Javadoc)
	 * creer unite article
	 */
	@Override
	public String creerCompteComptablePI(CompteComptablePIValue pCompteComptablePIValue) {
		CompteComptablePIEntity impressionProduitEntity= (CompteComptablePIEntity) this.creer(PersistanceUtilities.toEntity(pCompteComptablePIValue));
		CompteComptablePIValue impressionProduitValueResult=PersistanceUtilities.toValue(impressionProduitEntity);
		return impressionProduitValueResult.getId().toString();	
	}

	/* (non-Javadoc)
	 * supprimer unite article
	 */
	@Override
	public void supprimerCompteComptablePI(Long pCompteComptablePIValueId) {
		this.supprimerEntite(CompteComptablePIEntity.class, pCompteComptablePIValueId);
	}

	/* (non-Javadoc)
	 * modifier unite article
	 */
	@Override
	public String modifierCompteComptablePI(CompteComptablePIValue pCompteComptablePIValue) {
		CompteComptablePIEntity impressionProduitEntity= (CompteComptablePIEntity) this.modifier(PersistanceUtilities.toEntity(pCompteComptablePIValue));
		CompteComptablePIValue impressionProduitValueResult=PersistanceUtilities.toValue(impressionProduitEntity);
		return impressionProduitValueResult.getId().toString();	
	}

	/* (non-Javadoc)
	 * recherche by id unite article
	 */
	@Override
	public CompteComptablePIValue rechercheCompteComptablePIById(Long pCompteComptablePIValueId) {
		CompteComptablePIEntity impressionProduitEntity= (CompteComptablePIEntity) this.rechercherParId(pCompteComptablePIValueId,CompteComptablePIEntity.class);
		CompteComptablePIValue impressionProduitValueResult=PersistanceUtilities.toValue(impressionProduitEntity);
		return impressionProduitValueResult;	
	}

	/* (non-Javadoc)
	 * liste unite article
	 */
	@Override
	public List<CompteComptablePIValue> listeCompteComptablePI() {
		List <CompteComptablePIEntity > vListeCompteComptablePIEntite = this.lister(CompteComptablePIEntity.class);
	    List < CompteComptablePIValue > vListeCompteComptablePIValues = new ArrayList < CompteComptablePIValue >();
	    for (CompteComptablePIEntity vCompteComptablePIEntite : vListeCompteComptablePIEntite) {
	    	vListeCompteComptablePIValues.add(PersistanceUtilities.toValue(vCompteComptablePIEntite));
	    }
	    return vListeCompteComptablePIValues;
	}
 
	/********** Getter & Setter ***********/
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public CompteComptablePIValue rechercheCompteComptablePIParDesignation(String designation) {
		
		List<CompteComptablePIEntity>  vList  = this.rechercherParAttribut("designation", designation, CompteComptablePIEntity.class);
		
		if(vList.size() >0)
			return PersistanceUtilities.toValue(vList.get(0));
		
		
		
		return null;
	}
	

}
