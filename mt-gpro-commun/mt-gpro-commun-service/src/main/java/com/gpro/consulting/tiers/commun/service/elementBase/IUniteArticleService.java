package com.gpro.consulting.tiers.commun.service.elementBase;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.UniteArticleValue;

public interface IUniteArticleService {

  /************************** ajouter UniteARticle ***************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String creerUniteArticle(UniteArticleValue pUniteArticleValue);

  /********************** supprimer UniteArticle *****************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public void supprimerUniteArticle(Long pId);

  /********************** modifier UniteARticle *****************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String modifierUniteArticle(UniteArticleValue pUniteArticleValue);

  /********************** recherche unite par Id *****************************/
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public UniteArticleValue rechercheUniteArticleParId(Long pUniteArticleValue);

  /********************** afficher liste unite *****************************/
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public List < UniteArticleValue > listeUniteArticle();
}
