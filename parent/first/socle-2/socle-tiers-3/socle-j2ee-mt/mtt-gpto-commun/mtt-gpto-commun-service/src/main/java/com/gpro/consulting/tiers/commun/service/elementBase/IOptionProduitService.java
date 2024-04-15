package com.gpro.consulting.tiers.commun.service.elementBase;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.OptionProduitValue;

public interface IOptionProduitService {

  /************************** ajouter UniteARticle ***************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String creerOptionProduit(OptionProduitValue pOptionProduitValue);

  /********************** supprimer OptionProduit *****************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public void supprimerOptionProduit(Long pId);

  /********************** modifier UniteARticle *****************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String modifierOptionProduit(OptionProduitValue pOptionProduitValue);

  /********************** recherche unite par Id *****************************/
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public OptionProduitValue rechercheOptionProduitParId(Long pOptionProduitValue);

  /********************** afficher liste unite *****************************/
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public List < OptionProduitValue > listeOptionProduit();
}
