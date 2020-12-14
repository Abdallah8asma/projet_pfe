package com.gpro.consulting.tiers.commun.service.elementBase;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.MoldsValue;

public interface IMoldsService {
	/************************** ajouter Molds ***************************/
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String creerMolds(MoldsValue pMoldsValue);

	/********************** supprimer Molds *****************************/
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void supprimerMolds(Long pId);

	/********************** modifier Molds *****************************/
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String modifierMolds(MoldsValue pMoldsValue);

	/********************** recherche Molds ****************************/
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public MoldsValue rechercheMoldsParId(MoldsValue pMoldsValue);

	/****************** afficher liste Molds **************************/
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<MoldsValue> listeMolds();

}
