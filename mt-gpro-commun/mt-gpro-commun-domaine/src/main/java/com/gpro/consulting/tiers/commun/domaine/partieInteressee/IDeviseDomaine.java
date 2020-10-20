package com.gpro.consulting.tiers.commun.domaine.partieInteressee;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.DeviseValue;

public interface IDeviseDomaine {
	/************************** ajouter Devise ***************************/
	public String creerDevise(DeviseValue pDeviseValue);

	/********************** supprimer Devise *****************************/
	public void supprimerDevise(Long pId);

	/********************** modifier Devise *****************************/
	public String modifierDevise(DeviseValue pDeviseValue);

	/********************** recherche Devise ****************************/
	public DeviseValue rechercheDeviseParId(DeviseValue pDeviseValue);

	/****************** afficher liste Devise **************************/
	public List<DeviseValue> listeDevise();

}
