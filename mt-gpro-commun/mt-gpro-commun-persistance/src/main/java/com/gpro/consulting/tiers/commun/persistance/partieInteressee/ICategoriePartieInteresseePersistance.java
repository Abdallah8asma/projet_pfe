package com.gpro.consulting.tiers.commun.persistance.partieInteressee;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.CategorieValue;

public interface ICategoriePartieInteresseePersistance {
	/****************** ajouter categorie partie interesse *************/
	public String creerCategoriePartieInteressee(CategorieValue pCategorieValue);

	/********************** supprimer categorie partie interesse *****************************/
	public void supprimerCategoriePartieInteressee(
			Long pCategorieValue);

	/********************** modifier categorie partie interesse *****************************/
	public String modifierCategoriePartieInteressee(
			CategorieValue pCategorieValue);

	/********************** recherche categorie partie interesse par Id *****************************/
	public CategorieValue rechercheCategoriePartieInteresseeParId(
			CategorieValue pCategorieValue);

	/********************** afficher liste categorie partie interesse *****************************/
	public List<CategorieValue> listeCategoriePartieInteressee();

}
