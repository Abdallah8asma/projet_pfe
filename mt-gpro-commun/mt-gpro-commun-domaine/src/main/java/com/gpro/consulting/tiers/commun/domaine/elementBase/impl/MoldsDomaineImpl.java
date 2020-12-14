package com.gpro.consulting.tiers.commun.domaine.elementBase.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.MoldsValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.IMoldsDomaine;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IMoldsPersistance;

@Component
public class MoldsDomaineImpl implements IMoldsDomaine {

	@Autowired
	IMoldsPersistance ebMoldsPersistance;

	/************************ Creation Molds ***************************/
	@Override
	public String creerMolds(MoldsValue pMoldsValue) {
		return ebMoldsPersistance.creerMolds(pMoldsValue);
	}

	/*********************** suppression Molds **************************/
	@Override
	public void supprimerMolds(Long pId) {
		ebMoldsPersistance.supprimerMolds(pId);
	}

	/************************ Modifier Molds ****************************/
	@Override
	public String modifierMolds(MoldsValue pMoldsValue) {
		return ebMoldsPersistance.modifierMolds(pMoldsValue);
	}

	/************************ Rechercher Molds ***************************/
	@Override
	public MoldsValue rechercheMoldsParId(MoldsValue pMoldsValue) {
		return ebMoldsPersistance.rechercheMoldsParId(pMoldsValue);
	}

	/************************ Liste des Molds ***************************/
	@Override
	public List<MoldsValue> listeMolds() {
		return ebMoldsPersistance.listeMolds();
	}

}
