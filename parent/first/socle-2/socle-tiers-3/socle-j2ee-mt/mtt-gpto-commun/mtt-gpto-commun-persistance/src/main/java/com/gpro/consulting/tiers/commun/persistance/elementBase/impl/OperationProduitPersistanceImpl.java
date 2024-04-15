package com.gpro.consulting.tiers.commun.persistance.elementBase.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.OperationProduitValue;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IOperationProduitPersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.OperationProduitEntity;
import com.gpro.consulting.tiers.commun.persistance.utilities.PersistanceUtilities;

/**
* The Class uniteProduitImpl.
* @author mohamed
*/

@Component
public class OperationProduitPersistanceImpl  extends AbstractPersistance implements  IOperationProduitPersistance{
	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;
	
	/* (non-Javadoc)
	 * creer unite article
	 */
	@Override
	public String creerOperationProduit(OperationProduitValue pOperationProduitValue) {
		OperationProduitEntity optionProduitEntity= (OperationProduitEntity) this.creer(PersistanceUtilities.toEntity(pOperationProduitValue));
		OperationProduitValue optionProduitValueResult=PersistanceUtilities.toValue(optionProduitEntity);
		return optionProduitValueResult.getId().toString();	
	}

	/* (non-Javadoc)
	 * supprimer unite article
	 */
	@Override
	public void supprimerOperationProduit(Long pOperationProduitValueId) {
		this.supprimerEntite(OperationProduitEntity.class, pOperationProduitValueId);
	}

	/* (non-Javadoc)
	 * modifier unite article
	 */
	@Override
	public String modifierOperationProduit(OperationProduitValue pOperationProduitValue) {
		OperationProduitEntity optionProduitEntity= (OperationProduitEntity) this.modifier(PersistanceUtilities.toEntity(pOperationProduitValue));
		OperationProduitValue optionProduitValueResult=PersistanceUtilities.toValue(optionProduitEntity);
		return optionProduitValueResult.getId().toString();	
	}

	/* (non-Javadoc)
	 * recherche by id unite article
	 */
	@Override
	public OperationProduitValue rechercheOperationProduitById(Long pOperationProduitValueId) {
		OperationProduitEntity optionProduitEntity= (OperationProduitEntity) this.rechercherParId(pOperationProduitValueId,OperationProduitEntity.class);
		OperationProduitValue optionProduitValueResult=PersistanceUtilities.toValue(optionProduitEntity);
		return optionProduitValueResult;	
	}

	/* (non-Javadoc)
	 * liste unite article
	 */
	@Override
	public List<OperationProduitValue> listeOperationProduit() {
		List <OperationProduitEntity > vListeOperationProduitEntite = this.lister(OperationProduitEntity.class);
	    List < OperationProduitValue > vListeOperationProduitValues = new ArrayList < OperationProduitValue >();
	    for (OperationProduitEntity vOperationProduitEntite : vListeOperationProduitEntite) {
	    	vListeOperationProduitValues.add(PersistanceUtilities.toValue(vOperationProduitEntite));
	    }
	    return vListeOperationProduitValues;
	}
 
	/********** Getter & Setter ***********/
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	

}
