package com.gpro.consulting.tiers.commun.service.elementBase.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.PackageValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticriterePackageValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ResultatRechechePackageValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.IPackageDomaine;
import com.gpro.consulting.tiers.commun.service.elementBase.IPackageService;

@Service
@Transactional
public class PackageServiceImpl implements IPackageService{

	@Autowired
	IPackageDomaine ebPackageDomaine;
	
	/************************ Creation Package *****************************/
	@Override
	public String creerPackage(PackageValue pPackageValue) {
		return ebPackageDomaine.creerPackage(pPackageValue);
	}

	/************************ suppression Package ***************************/
	@Override
	public void supprimerPackage(Long pId) {
		ebPackageDomaine.supprimerPackage(pId);
	}

	/************************ Modification Package ***************************/
	@Override
	public String modifierPackage(PackageValue pPackageValue) {
		return ebPackageDomaine.modifierPackage(pPackageValue);
	}

	/************************** Recherche Package *****************************/
	@Override
	public PackageValue recherchePackageParId(PackageValue pPackageValue) {
		return ebPackageDomaine.recherchePackageParId(pPackageValue);
	}
	
	/************************** Liste des Packages *****************************/
	@Override
	public List<PackageValue> listePackage() {
		return ebPackageDomaine.listePackage();
	}

	@Override
	public ResultatRechechePackageValue rechercheMulticritere(RechercheMulticriterePackageValue request) {
		// TODO Auto-generated method stub
		return ebPackageDomaine.rechercheMulticritere(request);
	}

}
