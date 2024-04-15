package com.gpro.consulting.tiers.commun.service.partieInteressee;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.CompteComptablePIValue;

public interface ICompteComptablePIService {

  /************************** ajouter UniteARticle ***************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String creerCompteComptablePI(CompteComptablePIValue pCompteComptablePIValue);

  /********************** supprimer CompteComptablePI *****************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public void supprimerCompteComptablePI(Long pId);

  /********************** modifier UniteARticle *****************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String modifierCompteComptablePI(CompteComptablePIValue pCompteComptablePIValue);

  /********************** recherche unite par Id *****************************/
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public CompteComptablePIValue rechercheCompteComptablePIParId(Long pCompteComptablePIValue);

  /********************** afficher liste unite *****************************/
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public List < CompteComptablePIValue > listeCompteComptablePI();
  
  
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public CompteComptablePIValue rechercheCompteComptablePIParDesignation(String designation);
}
