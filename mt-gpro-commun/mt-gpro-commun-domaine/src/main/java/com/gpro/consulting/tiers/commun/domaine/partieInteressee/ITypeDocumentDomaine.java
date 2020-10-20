package com.gpro.consulting.tiers.commun.domaine.partieInteressee;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.TypeDocumentValue;


public interface ITypeDocumentDomaine {
	
	/**********************recherche  categorie partie interesse par Id*****************************/
	public TypeDocumentValue rechercheTypeDocumentPartieInteresseeParId(TypeDocumentValue pTypeDocumentValue);
	
	/**********************afficher  liste  categorie partie interesse *****************************/
	public List<TypeDocumentValue> listeTypeDocumentPartieInteressee();
	
	
	/****************** ajouter type document partie interesse *************/
	public String creerTypeDocumentPartieInteressee(
			TypeDocumentValue pTypeDocumentValue);

	/********************** supprimer type document partie interesse *****************************/
	public void supprimerTypeDocumentPartieInteressee(
			TypeDocumentValue pTypeDocumentValue);

	/********************** modifier type document partie interesse *****************************/
	public String modifierTypeDocumentPartieInteressee(
			TypeDocumentValue pTypeDocumentValue);

	
	
}
