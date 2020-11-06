package com.gpro.consulting.tiers.commun.service.elementBase;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ImpressionProduitValue;

public interface IImpressionProduitService {

  /************************** ajouter UniteARticle ***************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String creerImpressionProduit(ImpressionProduitValue pImpressionProduitValue);

  /********************** supprimer ImpressionProduit *****************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public void supprimerImpressionProduit(Long pId);

  /********************** modifier UniteARticle *****************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String modifierImpressionProduit(ImpressionProduitValue pImpressionProduitValue);

  /********************** recherche unite par Id *****************************/
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public ImpressionProduitValue rechercheImpressionProduitParId(Long pImpressionProduitValue);

  /********************** afficher liste unite *****************************/
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public List < ImpressionProduitValue > listeImpressionProduit();
}
