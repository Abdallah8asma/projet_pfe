package com.gpro.consulting.tiers.commun.domaine.elementBase.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.FamilleProduitValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.IFamilleProduitDomaine;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IFamilleProduitPersitance;

// TODO: Auto-generated Javadoc
/**
 * The Class FamilleProduitImpl.
 * @author med
 */

@Component
public class FamilleProduitDomaineImpl implements IFamilleProduitDomaine{
	
	/** The famille persistance. */
	@Autowired
	IFamilleProduitPersitance familleProduitPersistance;

	/* (non-Javadoc)
	 * creer  famille produit
	 */
	@Override
	public String creerFamilleProduit(FamilleProduitValue pFamilleProduitValue) {
		return familleProduitPersistance.creerFamilleProduit(pFamilleProduitValue);
	}

	/* (non-Javadoc)
	 * supprimer famille produit
	 */
	@Override
	public void supprimerSousFamilleProduit(Long pFamilleProduitId) {
		familleProduitPersistance.supprimerSousFamilleProduit(pFamilleProduitId);
	}

	/* (non-Javadoc)
	 * modifier famille produit
	 */
	@Override
	public String modifierFamilleProduit(
			FamilleProduitValue pFamilleProduitValue) {
		return familleProduitPersistance.modifierFamilleProduit(pFamilleProduitValue);
	}

	/* (non-Javadoc)
	 * recherche by id famille produit
	 */
	@Override
	public FamilleProduitValue rechercheFamilleProduitById(
			Long pFamilleProduitId) {
		return familleProduitPersistance.rechercheFamilleProduitById(pFamilleProduitId);
	}

	/* (non-Javadoc)
	 * list famille produit
	 */
	@Override
	public List<FamilleProduitValue> listeFamilleProduit() {
	    return familleProduitPersistance.listeFamilleProduit();
	}

}
