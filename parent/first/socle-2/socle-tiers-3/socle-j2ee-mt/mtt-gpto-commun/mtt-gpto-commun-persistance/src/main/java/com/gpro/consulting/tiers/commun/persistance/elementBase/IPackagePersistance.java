package com.gpro.consulting.tiers.commun.persistance.elementBase;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.PackageValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticriterePackageValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ResultatRechechePackageValue;


public interface IPackagePersistance {

	/**************************ajouter Package***************************/
	public  String creerPackage(PackageValue pPackageValue);
	
	/**********************supprimer Package*****************************/
	public  void supprimerPackage(Long pId);
	
	/**********************modifier Package *****************************/
	public String modifierPackage(PackageValue pPackageValue);
	
	/**********************recherche  Package****************************/
	public PackageValue recherchePackageParId(PackageValue pPackageValue);
	
	/******************afficher  liste  Package**************************/
	public List<PackageValue> listePackage();
	
	
	public ResultatRechechePackageValue rechercheMulticritere(RechercheMulticriterePackageValue request);
	
}

