package com.gpro.consulting.tiers.commun.persistance.partieInteressee;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.TypeValue;

public interface ITypePartieInteresseePersistance {
	/****************** ajouter Type Parite Interesse partie interesse *************/
	public String creerTypePartieInteressee(TypeValue pTypeValue);

	/********************** supprimer Parite Interesse partie interesse *****************************/
	public void supprimerTypePartieInteressee(TypeValue pTypeValue);

	/********************** modifier Parite Interesse partie interesse *****************************/
	public String modifierTypePartieInteressee(TypeValue pTypeValue);

	/********************** recherche Parite Interesse partie interesse par Id *****************************/
	public TypeValue rechercheTypePartieInteresseeParId(TypeValue pTypeValue);

	/********************** afficher liste Parite Interesse partie interesse *****************************/
	public List<TypeValue> listeTypePartieIntPartieInteressee();

}
