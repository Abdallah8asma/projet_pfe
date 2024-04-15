package com.gpro.consulting.tiers.commun.service.elementBase.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.FamilleProduitValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.IFamilleProduitDomaine;
import com.gpro.consulting.tiers.commun.service.elementBase.IFamilleProduitService;

/**
 * The Class FamilleProduitImpl.
 * @author med
 */
@Service
@Transactional
public class FamilleProduitServiceImpl implements IFamilleProduitService{
	
	/** The famille produit domaine. */
	@Autowired
	IFamilleProduitDomaine familleProduitDomaine;

	/* 
	 * creer famille produit
	 */
	@Override
	public String creerFamilleProduit(FamilleProduitValue pFamilleProduitValue) {
		return familleProduitDomaine.creerFamilleProduit(pFamilleProduitValue);
	}

	// supprimer
	@Override
	public void supprimerFamilleProduit(Long pFamilleProduitId) {
		familleProduitDomaine.supprimerSousFamilleProduit(pFamilleProduitId);
	}

	//modifier 
	@Override
	public String modifierFamilleProduit(
			FamilleProduitValue pFamilleProduitValue) {
		return familleProduitDomaine.modifierFamilleProduit(pFamilleProduitValue);
	}

	//recherche par id
	@Override
	public FamilleProduitValue rechercheFamilleProduitById(
			Long pFamilleProduitId) {
		return familleProduitDomaine.rechercheFamilleProduitById(pFamilleProduitId);
	}

	//liste produit
	@Override
	public List<FamilleProduitValue> listeFamilleProduit() {
		return familleProduitDomaine.listeFamilleProduit();
	}
	
}
