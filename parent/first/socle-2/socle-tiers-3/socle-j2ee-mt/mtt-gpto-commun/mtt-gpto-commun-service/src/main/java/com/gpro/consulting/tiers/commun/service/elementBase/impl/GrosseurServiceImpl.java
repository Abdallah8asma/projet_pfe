package com.gpro.consulting.tiers.commun.service.elementBase.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.GrosseurValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.IGrosseurDomaine;
import com.gpro.consulting.tiers.commun.service.elementBase.IGrosseurService;

@Service
@Transactional
public class GrosseurServiceImpl implements IGrosseurService{

	@Autowired
	IGrosseurDomaine ebGrosseurDomaine;
	
	/************************ Creation Matiere *****************************/
	@Override
	public String creerGrosseur(GrosseurValue pGrosseurValue) {
		return ebGrosseurDomaine.creerGrosseur(pGrosseurValue);
	}

	/************************ suppression Matiere ***************************/
	@Override
	public void supprimerGrosseur(Long pId) {
		ebGrosseurDomaine.supprimerGrosseur(pId);
	}

	/************************ Modification Matiere ***************************/
	@Override
	public String modifierGrosseur(GrosseurValue pGrosseurValue) {
		return ebGrosseurDomaine.modifierGrosseur(pGrosseurValue);
	}

	/************************** Recherche Matiere *****************************/
	@Override
	public GrosseurValue rechercheGrosseurParId(GrosseurValue pGrosseurValue) {
		return ebGrosseurDomaine.rechercheGrosseurParId(pGrosseurValue);
	}
	
	/************************** Liste des Matieres *****************************/
	@Override
	public List<GrosseurValue> listeGrosseur() {
		return ebGrosseurDomaine.listeGrosseur();
	}

}
