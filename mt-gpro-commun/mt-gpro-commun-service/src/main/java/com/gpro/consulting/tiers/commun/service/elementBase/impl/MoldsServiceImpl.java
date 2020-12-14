package com.gpro.consulting.tiers.commun.service.elementBase.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.MoldsValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.IMoldsDomaine;
import com.gpro.consulting.tiers.commun.service.elementBase.IMoldsService;

@Service
@Transactional
public class MoldsServiceImpl implements IMoldsService {

	@Autowired
	IMoldsDomaine ebMoldsDomaine;

	/************************ Creation Molds *****************************/
	@Override
	public String creerMolds(MoldsValue pMoldsValue) {
		return ebMoldsDomaine.creerMolds(pMoldsValue);
	}

	/************************ suppression Molds ***************************/
	@Override
	public void supprimerMolds(Long pId) {
		ebMoldsDomaine.supprimerMolds(pId);
	}

	/************************ Modification Molds ***************************/
	@Override
	public String modifierMolds(MoldsValue pMoldsValue) {
		return ebMoldsDomaine.modifierMolds(pMoldsValue);
	}

	/************************** Recherche Molds *****************************/
	@Override
	public MoldsValue rechercheMoldsParId(MoldsValue pMoldsValue) {
		return ebMoldsDomaine.rechercheMoldsParId(pMoldsValue);
	}

	/************************** Liste des Moldss *****************************/
	@Override
	public List<MoldsValue> listeMolds() {
		return ebMoldsDomaine.listeMolds();
	}

}
