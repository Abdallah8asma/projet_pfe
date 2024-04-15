package com.gpro.consulting.tiers.commun.service.elementBase;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.MatiereArticleValue;


public interface IMatiereService {

  /************************** ajouter Matiere ***************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String creerMatiere(MatiereArticleValue pMatiereValue);

  /********************** supprimer Matiere *****************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public void supprimerMatiere(Long long1);

  /********************** modifier Matiere *****************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String modifierMatiere(MatiereArticleValue pMatiereValue);

  /********************** recherche Matiere ****************************/
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public MatiereArticleValue rechercheMatiereParId(MatiereArticleValue pMatiereValue);

  /****************** afficher liste Matiere **************************/
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public List < MatiereArticleValue > listeMatiere();
}
