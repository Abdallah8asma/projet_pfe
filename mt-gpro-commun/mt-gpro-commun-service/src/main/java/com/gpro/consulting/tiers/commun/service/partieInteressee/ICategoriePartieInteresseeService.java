package com.gpro.consulting.tiers.commun.service.partieInteressee;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.CategorieValue;

public interface ICategoriePartieInteresseeService {
  /************** creer categorie partie interesse ***************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String creerCategoriePartieInteressee(CategorieValue pCategorieValue);

  /********************** supprimer categorie partie interesse *****************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public void supprimerCategoriePartieInteressee(Long pCategorieValue);

  /********************** modifier categorie partie interesse *****************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String modifierCategoriePartieInteressee(CategorieValue pCategorieValue);

  /********************** recherche categorie partie interesse par Id *****************************/
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public CategorieValue rechercheCategoriePartieInteresseeParId(CategorieValue pCategorieValue);

  /********************** afficher liste categorie partie interesse *****************************/
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public List < CategorieValue > listeCategoriePartieInteressee();

}
