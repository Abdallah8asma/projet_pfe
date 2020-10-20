package com.gpro.consulting.tiers.commun.service.partieInteressee;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.FamilleValue;

public interface IFamillePartieInteresseeService {

  /********************** recherche categorie partie interesse par Id *****************************/
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public FamilleValue rechercheFamillePartieInteresseeParId(FamilleValue pFamilleValue);

  /********************** afficher liste categorie partie interesse *****************************/
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public List < FamilleValue > listeFamillePartieInteressee();

  /****************** ajouter Famille partie interesse *************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String creerFamillePartieInteressee(FamilleValue pFamilleValue);

  /********************** supprimer Famille partie interesse *****************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public void supprimerFamillePartieInteressee(Long pFamilleValue);

  /********************** modifier Famille partie interesse *****************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String modifierFamillePartieInteressee(FamilleValue pFamilleValue);

}
