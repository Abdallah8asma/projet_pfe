package com.gpro.consulting.tiers.commun.persistance.elementBase;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.SocieteValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticritereSocieteValue;


public interface ISocietePersistance {

	/**************************ajouter Societe***************************/
	public  String creerSociete(SocieteValue pSocieteValue);
	
	/**********************supprimer Societe*****************************/
	public  void supprimerSociete(Long pId);
	
	/**********************modifier Societe *****************************/
	public String modifierSociete(SocieteValue pSocieteValue);
	
	/**********************recherche  Societe****************************/
	public SocieteValue rechercheSocieteParId(SocieteValue pSocieteValue);
	
	/******************afficher  liste  Societe**************************/
	public List<SocieteValue> listeSociete();
	
	
	public List<SocieteValue> rechercheMulticritere(RechercheMulticritereSocieteValue request);
	
	
	public SocieteValue rechercheSocieteParId(Long id);
	
}

