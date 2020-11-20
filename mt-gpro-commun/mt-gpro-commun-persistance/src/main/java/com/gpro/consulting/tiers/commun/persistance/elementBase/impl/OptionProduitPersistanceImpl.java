package com.gpro.consulting.tiers.commun.persistance.elementBase.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.OptionProduitValue;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IOptionProduitPersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.OptionProduitEntity;
import com.gpro.consulting.tiers.commun.persistance.utilities.PersistanceUtilities;

/**
* The Class uniteProduitImpl.
* @author mohamed
*/

@Component
public class OptionProduitPersistanceImpl  extends AbstractPersistance implements  IOptionProduitPersistance{
	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;
	
	/* (non-Javadoc)
	 * creer unite article
	 */
	@Override
	public String creerOptionProduit(OptionProduitValue pOptionProduitValue) {
		OptionProduitEntity optionProduitEntity= (OptionProduitEntity) this.creer(PersistanceUtilities.toEntity(pOptionProduitValue));
		OptionProduitValue optionProduitValueResult=PersistanceUtilities.toValue(optionProduitEntity);
		return optionProduitValueResult.getId().toString();	
	}

	/* (non-Javadoc)
	 * supprimer unite article
	 */
	@Override
	public void supprimerOptionProduit(Long pOptionProduitValueId) {
		this.supprimerEntite(OptionProduitEntity.class, pOptionProduitValueId);
	}

	/* (non-Javadoc)
	 * modifier unite article
	 */
	@Override
	public String modifierOptionProduit(OptionProduitValue pOptionProduitValue) {
		OptionProduitEntity optionProduitEntity= (OptionProduitEntity) this.modifier(PersistanceUtilities.toEntity(pOptionProduitValue));
		OptionProduitValue optionProduitValueResult=PersistanceUtilities.toValue(optionProduitEntity);
		return optionProduitValueResult.getId().toString();	
	}

	/* (non-Javadoc)
	 * recherche by id unite article
	 */
	@Override
	public OptionProduitValue rechercheOptionProduitById(Long pOptionProduitValueId) {
		OptionProduitEntity optionProduitEntity= (OptionProduitEntity) this.rechercherParId(pOptionProduitValueId,OptionProduitEntity.class);
		OptionProduitValue optionProduitValueResult=PersistanceUtilities.toValue(optionProduitEntity);
		return optionProduitValueResult;	
	}

	/* (non-Javadoc)
	 * liste unite article
	 */
	@Override
	public List<OptionProduitValue> listeOptionProduit() {
		List <OptionProduitEntity > vListeOptionProduitEntite = this.lister(OptionProduitEntity.class);
	    List < OptionProduitValue > vListeOptionProduitValues = new ArrayList < OptionProduitValue >();
	    for (OptionProduitEntity vOptionProduitEntite : vListeOptionProduitEntite) {
	    	vListeOptionProduitValues.add(PersistanceUtilities.toValue(vOptionProduitEntite));
	    }
	    return vListeOptionProduitValues;
	}
 
	/********** Getter & Setter ***********/
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	

}
