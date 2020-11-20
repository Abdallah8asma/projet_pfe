package com.gpro.consulting.tiers.commun.service.elementBase;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.OperationProduitValue;

public interface IOperationProduitService {

  /************************** ajouter UniteARticle ***************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String creerOperationProduit(OperationProduitValue pOperationProduitValue);

  /********************** supprimer OperationProduit *****************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public void supprimerOperationProduit(Long pId);

  /********************** modifier UniteARticle *****************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String modifierOperationProduit(OperationProduitValue pOperationProduitValue);

  /********************** recherche unite par Id *****************************/
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public OperationProduitValue rechercheOperationProduitParId(Long pOperationProduitValue);

  /********************** afficher liste unite *****************************/
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public List < OperationProduitValue > listeOperationProduit();
}
