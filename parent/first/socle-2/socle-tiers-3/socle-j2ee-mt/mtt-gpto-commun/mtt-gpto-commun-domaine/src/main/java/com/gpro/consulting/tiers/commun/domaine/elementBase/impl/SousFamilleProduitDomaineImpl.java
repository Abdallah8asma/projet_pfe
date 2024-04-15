package com.gpro.consulting.tiers.commun.domaine.elementBase.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.SousFamilleProduitValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.ISousFamilleProduitDomaine;
import com.gpro.consulting.tiers.commun.persistance.elementBase.ISousFamilleProduitPersistance;

/**
 * The Class SousFamilleProduitImpl.
 * @author med
 */

@Component
public class SousFamilleProduitDomaineImpl implements ISousFamilleProduitDomaine{
	
	/** The sous famille produit persistance. */
	@Autowired
	ISousFamilleProduitPersistance sousFamilleProduitPersistance;

	/* 
	 * creer sous famille produit
	 */
	@Override
	public String creerSousFamilleProduit(
			SousFamilleProduitValue pSousFamilleProduitValue) {
		return sousFamilleProduitPersistance.creerSousFamilleProduit(pSousFamilleProduitValue);
	}

	/* 
	 * supprimer sous famille produit
	 */
	@Override
	public void supprimerSousFamilleProduit(Long pSousFamilleProduitId) {
		sousFamilleProduitPersistance.supprimerSousFamilleProduit(pSousFamilleProduitId);		
	}

	/* 
	 * modifier sous famille produit
	 */
	@Override
	public String modifierSousFamilleProduit(
			SousFamilleProduitValue pSousFamilleProduitValue) {
		return sousFamilleProduitPersistance.modifierSousFamilleProduit(pSousFamilleProduitValue);
	}

	/* 
	 * recherche by id sous famille produit
	 */
	@Override
	public SousFamilleProduitValue rechercheSousFamilleProduitById(
			Long pSousFamilleProduitId) {
		return sousFamilleProduitPersistance.rechercheSousFamilleProduitById(pSousFamilleProduitId);
	}

	/* 
	 * liste sous famille produit
	 */
	@Override
	public List<SousFamilleProduitValue> listeSousFamilleProduit() {
		return sousFamilleProduitPersistance.listeSousFamilleProduit();
	}
	
}
