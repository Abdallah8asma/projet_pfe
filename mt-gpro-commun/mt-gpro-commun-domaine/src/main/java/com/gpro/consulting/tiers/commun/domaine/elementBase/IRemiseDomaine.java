package com.gpro.consulting.tiers.commun.domaine.elementBase;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticritereRemiseValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RemiseValue;


public interface IRemiseDomaine {

	/**************************ajouter Remise***************************/
	public  String creerRemise(RemiseValue pRemiseValue);
	
	/**********************supprimer Remise*****************************/
	public  void supprimerRemise(Long pId);
	
	/**********************modifier Remise *****************************/
	public String modifierRemise(RemiseValue pRemiseValue);
	
	/**********************recherche  Remise****************************/
	public RemiseValue rechercheRemiseParId(RemiseValue pRemiseValue);
	
	/******************afficher  liste  Remise**************************/
	public List<RemiseValue> listeRemise();
	
	
	public List<RemiseValue> rechercheMulticritere(RechercheMulticritereRemiseValue request);
	
}

