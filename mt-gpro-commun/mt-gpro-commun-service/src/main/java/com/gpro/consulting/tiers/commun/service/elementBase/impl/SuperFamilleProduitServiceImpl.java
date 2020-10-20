package com.gpro.consulting.tiers.commun.service.elementBase.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.SuperFamilleProduitValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.ISuperFamilleProduitDomaine;
import com.gpro.consulting.tiers.commun.service.elementBase.ISuperFamilleProduitService;

/**
 * The Class SuperFamilleProduitImpl.
 * @author med
 */
@Service
@Transactional
public class SuperFamilleProduitServiceImpl implements ISuperFamilleProduitService{
	
	/** The famille produit domaine. */
	@Autowired
	ISuperFamilleProduitDomaine superFamilleProduitDomaine;

	/* 
	 * creer famille produit
	 */
	@Override
	public String creerSuperFamilleProduit(SuperFamilleProduitValue pSuperFamilleProduitValue) {
		return superFamilleProduitDomaine.creerSuperFamilleProduit(pSuperFamilleProduitValue);
	}

	// supprimer
	@Override
	public void supprimerSuperFamilleProduit(Long pSuperFamilleProduitId) {
		superFamilleProduitDomaine.supprimerSuperFamilleProduit(pSuperFamilleProduitId);
	}

	//modifier 
	@Override
	public String modifierSuperFamilleProduit(
			SuperFamilleProduitValue pSuperFamilleProduitValue) {
		return superFamilleProduitDomaine.modifierSuperFamilleProduit(pSuperFamilleProduitValue);
	}

	//recherche par id
	@Override
	public SuperFamilleProduitValue rechercheSuperFamilleProduitById(
			Long pSuperFamilleProduitId) {
		return superFamilleProduitDomaine.rechercheSuperFamilleProduitById(pSuperFamilleProduitId);
	}

	//liste produit
	@Override
	public List<SuperFamilleProduitValue> listeSuperFamilleProduit() {
		return superFamilleProduitDomaine.listeSuperFamilleProduit();
	}
	
}
