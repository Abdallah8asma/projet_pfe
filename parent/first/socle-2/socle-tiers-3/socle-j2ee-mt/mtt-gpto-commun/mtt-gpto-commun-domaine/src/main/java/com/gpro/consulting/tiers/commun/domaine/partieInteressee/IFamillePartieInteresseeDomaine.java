package com.gpro.consulting.tiers.commun.domaine.partieInteressee;
import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.FamilleValue;


public interface IFamillePartieInteresseeDomaine {
		/**********************recherche  categorie partie interesse par Id*****************************/
	public FamilleValue rechercheFamillePartieInteresseeParId(FamilleValue pFamilleValue);
	
	/**********************afficher  liste  categorie partie interesse *****************************/
	public List<FamilleValue> listeFamillePartieInteressee();
	
	
	/****************** ajouter Famille partie interesse *************/
	public String creerFamillePartieInteressee(FamilleValue pFamilleValue);

	/********************** supprimer Famille partie interesse *****************************/
	public void supprimerFamillePartieInteressee(Long pFamilleValue);

	/********************** modifier Famille partie interesse *****************************/
	public String modifierFamillePartieInteressee(FamilleValue pFamilleValue);
	
	
}
