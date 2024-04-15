package com.gpro.consulting.tiers.commun.domaine.elementBase.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.PackageValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticriterePackageValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ResultatRechechePackageValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.IPackageDomaine;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IPackagePersistance;

@Component
public class PackageDomaineImpl implements IPackageDomaine{

	@Autowired
	IPackagePersistance ebPackagePersistance;
	
	/************************ Creation Package ***************************/
	@Override
	public String creerPackage(PackageValue pPackageValue) {
		return ebPackagePersistance.creerPackage(pPackageValue);
	}

	/*********************** suppression Package **************************/
	@Override
	public void supprimerPackage(Long pId) {
		ebPackagePersistance.supprimerPackage(pId);
	}

	/************************ Modifier Package ****************************/
	@Override
	public String modifierPackage(PackageValue pPackageValue) {
		return ebPackagePersistance.modifierPackage(pPackageValue);
	}

	/************************ Rechercher Package ***************************/
	@Override
	public PackageValue recherchePackageParId(PackageValue pPackageValue) {
		return ebPackagePersistance.recherchePackageParId(pPackageValue);
	}

	/************************ Liste des Package ***************************/
	@Override
	public List<PackageValue> listePackage() {
		return ebPackagePersistance.listePackage();
	}

	@Override
	public ResultatRechechePackageValue rechercheMulticritere(RechercheMulticriterePackageValue request) {
		// TODO Auto-generated method stub
		return ebPackagePersistance.rechercheMulticritere(request);
	}

}
