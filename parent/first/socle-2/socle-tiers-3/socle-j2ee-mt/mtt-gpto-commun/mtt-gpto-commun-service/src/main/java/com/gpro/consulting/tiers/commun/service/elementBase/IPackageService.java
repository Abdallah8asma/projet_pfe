package com.gpro.consulting.tiers.commun.service.elementBase;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.PackageValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticriterePackageValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ResultatRechechePackageValue;


public interface IPackageService {
  /************************** ajouter Package ***************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String creerPackage(PackageValue pPackageValue);

  /********************** supprimer Package *****************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public void supprimerPackage(Long pId);

  /********************** modifier Package *****************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String modifierPackage(PackageValue pPackageValue);

  /********************** recherche Package ****************************/
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public PackageValue recherchePackageParId(PackageValue pPackageValue);

  /****************** afficher liste Package **************************/
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public List < PackageValue > listePackage();
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public ResultatRechechePackageValue rechercheMulticritere(RechercheMulticriterePackageValue request);
}
