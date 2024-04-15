package com.gpro.consulting.tiers.commun.domaine.elementBase.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.CouleurValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.ICouleurDomaine;
import com.gpro.consulting.tiers.commun.persistance.elementBase.ICouleurPersistance;

@Component
public class CouleurDomaineImpl implements ICouleurDomaine{

	@Autowired
	ICouleurPersistance ebCouleurPersistance;
	
	/************************ Creation Couleur ***************************/
	@Override
	public String creerCouleur(CouleurValue pCouleurValue) {
		return ebCouleurPersistance.creerCouleur(pCouleurValue);
	}

	/*********************** suppression Couleur **************************/
	@Override
	public void supprimerCouleur(Long pId) {
		ebCouleurPersistance.supprimerCouleur(pId);
	}

	/************************ Modifier Couleur ****************************/
	@Override
	public String modifierCouleur(CouleurValue pCouleurValue) {
		return ebCouleurPersistance.modifierCouleur(pCouleurValue);
	}

	/************************ Rechercher Couleur ***************************/
	@Override
	public CouleurValue rechercheCouleurParId(CouleurValue pCouleurValue) {
		return ebCouleurPersistance.rechercheCouleurParId(pCouleurValue);
	}

	/************************ Liste des Couleur ***************************/
	@Override
	public List<CouleurValue> listeCouleur() {
		return ebCouleurPersistance.listeCouleur();
	}

}
