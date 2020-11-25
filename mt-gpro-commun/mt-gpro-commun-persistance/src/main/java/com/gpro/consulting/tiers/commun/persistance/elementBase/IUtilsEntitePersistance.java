package com.gpro.consulting.tiers.commun.persistance.elementBase;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.UtilsValue;


public interface IUtilsEntitePersistance {

	/**************************ajouter Utils***************************/
	public  String creerUtils(UtilsValue pUtilsValue);
	
	/**********************supprimer Utils*****************************/
	public  void supprimerUtils(Long pId);
	
	/**********************modifier Utils *****************************/
	public String modifierUtils(UtilsValue pUtilsValue);
	
	/**********************recherche  Utils****************************/
	public UtilsValue rechercheUtilsParId(UtilsValue pUtilsValue);
	
	/******************afficher  liste  Utils**************************/
	public List<UtilsValue> listeUtils();
	
	public List<UtilsValue> listeUtilsParType(String type);
	
}

