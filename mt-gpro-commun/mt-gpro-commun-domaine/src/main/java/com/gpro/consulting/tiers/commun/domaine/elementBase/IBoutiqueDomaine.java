package com.gpro.consulting.tiers.commun.domaine.elementBase;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.BoutiqueValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticritereBoutiqueValue;


public interface IBoutiqueDomaine {

	/**************************ajouter Boutique***************************/
	public  String creerBoutique(BoutiqueValue pBoutiqueValue);
	
	/**********************supprimer Boutique*****************************/
	public  void supprimerBoutique(Long pId);
	
	/**********************modifier Boutique *****************************/
	public String modifierBoutique(BoutiqueValue pBoutiqueValue);
	
	/**********************recherche  Boutique****************************/
	public BoutiqueValue rechercheBoutiqueParId(BoutiqueValue pBoutiqueValue);
	
	/******************afficher  liste  Boutique**************************/
	public List<BoutiqueValue> listeBoutique();
	
	
	public List<BoutiqueValue> rechercheMulticritere(RechercheMulticritereBoutiqueValue request);
	
}

