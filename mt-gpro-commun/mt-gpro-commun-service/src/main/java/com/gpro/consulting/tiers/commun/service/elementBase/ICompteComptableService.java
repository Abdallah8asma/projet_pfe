package com.gpro.consulting.tiers.commun.service.elementBase;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.CompteComptableValue;

public interface ICompteComptableService {

  /************************** ajouter UniteARticle ***************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String creerCompteComptable(CompteComptableValue pCompteComptableValue);

  /********************** supprimer CompteComptable *****************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public void supprimerCompteComptable(Long pId);

  /********************** modifier UniteARticle *****************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String modifierCompteComptable(CompteComptableValue pCompteComptableValue);

  /********************** recherche unite par Id *****************************/
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public CompteComptableValue rechercheCompteComptableParId(Long pCompteComptableValue);

  /********************** afficher liste unite *****************************/
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public List < CompteComptableValue > listeCompteComptable();
}
