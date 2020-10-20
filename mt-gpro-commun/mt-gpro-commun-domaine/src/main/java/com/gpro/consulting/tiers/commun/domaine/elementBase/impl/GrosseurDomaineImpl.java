package com.gpro.consulting.tiers.commun.domaine.elementBase.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.GrosseurValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.IGrosseurDomaine;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IGrosseurPersistance;

@Component
public class GrosseurDomaineImpl implements IGrosseurDomaine{

	@Autowired
	IGrosseurPersistance ebGrosseurPersistance;
	
	/************************ Creation Grosseur ***************************/
	@Override
	public String creerGrosseur(GrosseurValue pGrosseurValue) {
		return ebGrosseurPersistance.creerGrosseur(pGrosseurValue);
	}

	/*********************** suppression Grosseur **************************/
	@Override
	public void supprimerGrosseur(Long pId) {
		ebGrosseurPersistance.supprimerGrosseur(pId);
	}

	/************************ Modifier Grosseur ****************************/
	@Override
	public String modifierGrosseur(GrosseurValue pGrosseurValue) {
		return ebGrosseurPersistance.modifierGrosseur(pGrosseurValue);
	}

	/************************ Rechercher Grosseur ***************************/
	@Override
	public GrosseurValue rechercheGrosseurParId(GrosseurValue pGrosseurValue) {
		return ebGrosseurPersistance.rechercheGrosseurParId(pGrosseurValue);
	}

	/************************ Liste des Grosseur ***************************/
	@Override
	public List<GrosseurValue> listeGrosseur() {
		return ebGrosseurPersistance.listeGrosseur();
	}

}
