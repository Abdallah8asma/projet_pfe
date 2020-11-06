package com.gpro.consulting.tiers.commun.persistance.elementBase.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.CompteComptableValue;
import com.gpro.consulting.tiers.commun.persistance.elementBase.ICompteComptablePersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.CompteComptableEntity;
import com.gpro.consulting.tiers.commun.persistance.utilities.PersistanceUtilities;

/**
* The Class uniteArticleImpl.
* @author mohamed
*/

@Component
public class CompteComptablePersistanceImpl  extends AbstractPersistance implements  ICompteComptablePersistance{
	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;
	
	/* (non-Javadoc)
	 * creer unite article
	 */
	@Override
	public String creerCompteComptable(CompteComptableValue pCompteComptableValue) {
		CompteComptableEntity impressionProduitEntity= (CompteComptableEntity) this.creer(PersistanceUtilities.toEntity(pCompteComptableValue));
		CompteComptableValue impressionProduitValueResult=PersistanceUtilities.toValue(impressionProduitEntity);
		return impressionProduitValueResult.getId().toString();	
	}

	/* (non-Javadoc)
	 * supprimer unite article
	 */
	@Override
	public void supprimerCompteComptable(Long pCompteComptableValueId) {
		this.supprimerEntite(CompteComptableEntity.class, pCompteComptableValueId);
	}

	/* (non-Javadoc)
	 * modifier unite article
	 */
	@Override
	public String modifierCompteComptable(CompteComptableValue pCompteComptableValue) {
		CompteComptableEntity impressionProduitEntity= (CompteComptableEntity) this.modifier(PersistanceUtilities.toEntity(pCompteComptableValue));
		CompteComptableValue impressionProduitValueResult=PersistanceUtilities.toValue(impressionProduitEntity);
		return impressionProduitValueResult.getId().toString();	
	}

	/* (non-Javadoc)
	 * recherche by id unite article
	 */
	@Override
	public CompteComptableValue rechercheCompteComptableById(Long pCompteComptableValueId) {
		CompteComptableEntity impressionProduitEntity= (CompteComptableEntity) this.rechercherParId(pCompteComptableValueId,CompteComptableEntity.class);
		CompteComptableValue impressionProduitValueResult=PersistanceUtilities.toValue(impressionProduitEntity);
		return impressionProduitValueResult;	
	}

	/* (non-Javadoc)
	 * liste unite article
	 */
	@Override
	public List<CompteComptableValue> listeCompteComptable() {
		List <CompteComptableEntity > vListeCompteComptableEntite = this.lister(CompteComptableEntity.class);
	    List < CompteComptableValue > vListeCompteComptableValues = new ArrayList < CompteComptableValue >();
	    for (CompteComptableEntity vCompteComptableEntite : vListeCompteComptableEntite) {
	    	vListeCompteComptableValues.add(PersistanceUtilities.toValue(vCompteComptableEntite));
	    }
	    return vListeCompteComptableValues;
	}
 
	/********** Getter & Setter ***********/
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	

}
