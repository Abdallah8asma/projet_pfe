package com.gpro.consulting.tiers.commun.persistance.partieInteressee.impl;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.FamilleValue;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.IFamillePartieInteresseePersistance;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.entity.FamilleEntite;
import com.gpro.consulting.tiers.commun.persistance.utilities.PersistanceUtilities;

/**
 * The Class FamillePartieInteresseeImpl.
 * 
 * @author $mohamed
 */

@Component
public class FamillePartieInteresseePersistanceImpl extends AbstractPersistance
		implements IFamillePartieInteresseePersistance {

	/** EntityManager. */
	@PersistenceContext
	private EntityManager entityManager;

	/*
	 * get entity manager
	 */
	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/******************************* create famille PI *************************************/
	@Override
	public String creerFamillePartieInteressee(FamilleValue pFamilleValue) {
		FamilleEntite vFamilletity= (FamilleEntite) this.creer(PersistanceUtilities.toEntity(pFamilleValue));
		 FamilleValue vFamilleValueResult=PersistanceUtilities.toValue(vFamilletity);
		 return vFamilleValueResult.getId().toString();	
	}

	@Override
	public void supprimerFamillePartieInteressee(Long pFamilleValue) {
		this.supprimerEntite(FamilleEntite.class, pFamilleValue);	
	}

	@Override
	public String modifierFamillePartieInteressee(FamilleValue pFamilleValue) {
		FamilleEntite vFamilletity= (FamilleEntite) this.modifier(PersistanceUtilities.toEntity(pFamilleValue));
		 FamilleValue vFamilleValueResult=PersistanceUtilities.toValue(vFamilletity);
		 return vFamilleValueResult.getId().toString();	
	}

	@Override
	public FamilleValue rechercheFamillePartieInteresseeParId(FamilleValue pFamilleValue) {
		FamilleEntite vFamilletity= (FamilleEntite) this.rechercherParId(pFamilleValue, FamilleEntite.class);
		 FamilleValue vFamilleValueResult=PersistanceUtilities.toFamilleValue(vFamilletity);
		 return vFamilleValueResult;
	}

	@Override
	public List<FamilleValue> listeFamillePartieInteressee() {
		 List < FamilleEntite > vListeFamilleEntite = this.lister(FamilleEntite.class);
		    List < FamilleValue > vListFamilleValues = new ArrayList < FamilleValue >();
		    for (FamilleEntite vFamilleEntite : vListeFamilleEntite) {
		    	vListFamilleValues.add(PersistanceUtilities.toFamilleValue(vFamilleEntite));
		    }
		    return vListFamilleValues;
	}

}
