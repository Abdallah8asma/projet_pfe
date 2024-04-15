package com.gpro.consulting.tiers.commun.service.elementBase.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.CouleurValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.ICouleurDomaine;
import com.gpro.consulting.tiers.commun.service.elementBase.ICouleurService;

@Service
@Transactional
public class CouleurServiceImpl implements ICouleurService{

	@Autowired
	ICouleurDomaine ebCouleurDomaine;
	
	/************************ Creation Couleur *****************************/
	@Override
	public String creerCouleur(CouleurValue pCouleurValue) {
		return ebCouleurDomaine.creerCouleur(pCouleurValue);
	}

	/************************ suppression Couleur ***************************/
	@Override
	public void supprimerCouleur(Long pId) {
		ebCouleurDomaine.supprimerCouleur(pId);
	}

	/************************ Modification Couleur ***************************/
	@Override
	public String modifierCouleur(CouleurValue pCouleurValue) {
		return ebCouleurDomaine.modifierCouleur(pCouleurValue);
	}

	/************************** Recherche Couleur *****************************/
	@Override
	public CouleurValue rechercheCouleurParId(CouleurValue pCouleurValue) {
		return ebCouleurDomaine.rechercheCouleurParId(pCouleurValue);
	}
	
	/************************** Liste des Couleurs *****************************/
	@Override
	public List<CouleurValue> listeCouleur() {
		return ebCouleurDomaine.listeCouleur();
	}

}
