package com.gpro.consulting.tiers.commun.domaine.elementBase;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.MetrageValue;

public interface IMetrageDomaine {
	
	/**************************ajouter Metrage***************************/
	public  String creerMetrage(MetrageValue pMetrageValue);
	
	/**********************supprimer Metrage*****************************/
	public  void supprimerMetrage(Long pId);
	
	/**********************modifier Metrage *****************************/
	public String modifierMetrage(MetrageValue pMetrageValue);
	
	/**********************recherche  Metrage****************************/
	public MetrageValue rechercheMetrageParId(MetrageValue pMetrageValue);
	
	/******************afficher  liste  Metrage**************************/
	public List<MetrageValue> listeMetrage();
}
