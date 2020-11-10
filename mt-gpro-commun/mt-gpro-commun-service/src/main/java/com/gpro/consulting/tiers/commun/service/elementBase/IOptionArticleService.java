package com.gpro.consulting.tiers.commun.service.elementBase;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.OptionArticleValue;

public interface IOptionArticleService {

  /************************** ajouter UniteARticle ***************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String creerOptionArticle(OptionArticleValue pOptionArticleValue);

  /********************** supprimer OptionArticle *****************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public void supprimerOptionArticle(Long pId);

  /********************** modifier UniteARticle *****************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String modifierOptionArticle(OptionArticleValue pOptionArticleValue);

  /********************** recherche unite par Id *****************************/
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public OptionArticleValue rechercheOptionArticleParId(Long pOptionArticleValue);

  /********************** afficher liste unite *****************************/
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public List < OptionArticleValue > listeOptionArticle();
}
