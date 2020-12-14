package com.gpro.consulting.tiers.commun.domaine.elementBase;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.MoldsValue;

public interface IMoldsDomaine {

	/************************** ajouter Molds ***************************/
	public String creerMolds(MoldsValue pMoldsValue);

	/********************** supprimer Molds *****************************/
	public void supprimerMolds(Long pId);

	/********************** modifier Molds *****************************/
	public String modifierMolds(MoldsValue pMoldsValue);

	/********************** recherche Molds ****************************/
	public MoldsValue rechercheMoldsParId(MoldsValue pMoldsValue);

	/****************** afficher liste Molds **************************/
	public List<MoldsValue> listeMolds();

}
