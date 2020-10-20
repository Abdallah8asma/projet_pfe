package com.gpro.consulting.tiers.commun.service.elementBase;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.GrosseurValue;


public interface IGrosseurService {

  /************************** ajouter Grosseur ***************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String creerGrosseur(GrosseurValue pGrosseurValue);

  /********************** supprimer Grosseur *****************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public void supprimerGrosseur(Long pId);

  /********************** modifier Grosseur *****************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String modifierGrosseur(GrosseurValue pGrosseurValue);

  /********************** recherche Grosseur ****************************/
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public GrosseurValue rechercheGrosseurParId(GrosseurValue pGrosseurValue);

  /****************** afficher liste Grosseur **************************/
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public List < GrosseurValue > listeGrosseur();

}
