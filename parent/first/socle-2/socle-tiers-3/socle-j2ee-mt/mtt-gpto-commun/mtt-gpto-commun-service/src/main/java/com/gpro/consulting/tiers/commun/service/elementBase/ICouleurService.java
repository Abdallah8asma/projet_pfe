package com.gpro.consulting.tiers.commun.service.elementBase;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.CouleurValue;


public interface ICouleurService {
  /************************** ajouter Couleur ***************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String creerCouleur(CouleurValue pCouleurValue);

  /********************** supprimer Couleur *****************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public void supprimerCouleur(Long pId);

  /********************** modifier Couleur *****************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String modifierCouleur(CouleurValue pCouleurValue);

  /********************** recherche Couleur ****************************/
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public CouleurValue rechercheCouleurParId(CouleurValue pCouleurValue);

  /****************** afficher liste Couleur **************************/
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public List < CouleurValue > listeCouleur();

}
