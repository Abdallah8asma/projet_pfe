package com.gpro.consulting.tiers.commun.domaine.elementBase.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.SuperFamilleProduitValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.ISuperFamilleProduitDomaine;
import com.gpro.consulting.tiers.commun.persistance.elementBase.ISuperFamilleProduitPersitance;

// TODO: Auto-generated Javadoc
/**
 * The Class SuperFamilleProduitImpl.
 * @author med
 */

@Component
public class SuperFamilleProduitDomaineImpl implements ISuperFamilleProduitDomaine{
	
	/** The famille persistance. */
	@Autowired
	ISuperFamilleProduitPersitance superFamilleProduitPersistance;

	/* (non-Javadoc)
	 * creer  famille produit
	 */
	@Override
	public String creerSuperFamilleProduit(SuperFamilleProduitValue pSuperFamilleProduitValue) {
		return superFamilleProduitPersistance.creerSuperFamilleProduit(pSuperFamilleProduitValue);
	}

	/* (non-Javadoc)
	 * supprimer famille produit
	 */
	@Override
	public void supprimerSuperFamilleProduit(Long pSuperFamilleProduitId) {
		superFamilleProduitPersistance.supprimerSuperFamilleProduit(pSuperFamilleProduitId);
	}

	/* (non-Javadoc)
	 * modifier famille produit
	 */
	@Override
	public String modifierSuperFamilleProduit(
			SuperFamilleProduitValue pSuperFamilleProduitValue) {
		return superFamilleProduitPersistance.modifierSuperFamilleProduit(pSuperFamilleProduitValue);
	}

	/* (non-Javadoc)
	 * recherche by id famille produit
	 */
	@Override
	public SuperFamilleProduitValue rechercheSuperFamilleProduitById(
			Long pSuperFamilleProduitId) {
		return superFamilleProduitPersistance.rechercheSuperFamilleProduitById(pSuperFamilleProduitId);
	}

	/* (non-Javadoc)
	 * list famille produit
	 */
	@Override
	public List<SuperFamilleProduitValue> listeSuperFamilleProduit() {
	    return superFamilleProduitPersistance.listeSuperFamilleProduit();
	}

}
