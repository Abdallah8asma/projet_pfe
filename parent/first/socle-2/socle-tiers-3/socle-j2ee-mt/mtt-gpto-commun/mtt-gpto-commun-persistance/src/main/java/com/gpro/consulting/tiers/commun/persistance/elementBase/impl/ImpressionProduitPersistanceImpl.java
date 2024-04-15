package com.gpro.consulting.tiers.commun.persistance.elementBase.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ImpressionProduitValue;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IImpressionProduitPersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.ImpressionProduitEntity;
import com.gpro.consulting.tiers.commun.persistance.utilities.PersistanceUtilities;

/**
* The Class uniteArticleImpl.
* @author mohamed
*/

@Component
public class ImpressionProduitPersistanceImpl  extends AbstractPersistance implements  IImpressionProduitPersistance{
	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;
	
	/* (non-Javadoc)
	 * creer unite article
	 */
	@Override
	public String creerImpressionProduit(ImpressionProduitValue pImpressionProduitValue) {
		ImpressionProduitEntity impressionProduitEntity= (ImpressionProduitEntity) this.creer(PersistanceUtilities.toEntity(pImpressionProduitValue));
		ImpressionProduitValue impressionProduitValueResult=PersistanceUtilities.toValue(impressionProduitEntity);
		return impressionProduitValueResult.getId().toString();	
	}

	/* (non-Javadoc)
	 * supprimer unite article
	 */
	@Override
	public void supprimerImpressionProduit(Long pImpressionProduitValueId) {
		this.supprimerEntite(ImpressionProduitEntity.class, pImpressionProduitValueId);
	}

	/* (non-Javadoc)
	 * modifier unite article
	 */
	@Override
	public String modifierImpressionProduit(ImpressionProduitValue pImpressionProduitValue) {
		ImpressionProduitEntity impressionProduitEntity= (ImpressionProduitEntity) this.modifier(PersistanceUtilities.toEntity(pImpressionProduitValue));
		ImpressionProduitValue impressionProduitValueResult=PersistanceUtilities.toValue(impressionProduitEntity);
		return impressionProduitValueResult.getId().toString();	
	}

	/* (non-Javadoc)
	 * recherche by id unite article
	 */
	@Override
	public ImpressionProduitValue rechercheImpressionProduitById(Long pImpressionProduitValueId) {
		ImpressionProduitEntity impressionProduitEntity= (ImpressionProduitEntity) this.rechercherParId(pImpressionProduitValueId,ImpressionProduitEntity.class);
		ImpressionProduitValue impressionProduitValueResult=PersistanceUtilities.toValue(impressionProduitEntity);
		return impressionProduitValueResult;	
	}

	/* (non-Javadoc)
	 * liste unite article
	 */
	@Override
	public List<ImpressionProduitValue> listeImpressionProduit() {
		List <ImpressionProduitEntity > vListeImpressionProduitEntite = this.lister(ImpressionProduitEntity.class);
	    List < ImpressionProduitValue > vListeImpressionProduitValues = new ArrayList < ImpressionProduitValue >();
	    for (ImpressionProduitEntity vImpressionProduitEntite : vListeImpressionProduitEntite) {
	    	vListeImpressionProduitValues.add(PersistanceUtilities.toValue(vImpressionProduitEntite));
	    }
	    return vListeImpressionProduitValues;
	}
 
	/********** Getter & Setter ***********/
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	

}
