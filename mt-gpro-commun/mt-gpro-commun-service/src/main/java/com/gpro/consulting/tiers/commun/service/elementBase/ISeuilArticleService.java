package com.gpro.consulting.tiers.commun.service.elementBase;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.SeuilArticleValue;


public interface ISeuilArticleService {
  /************************** ajouter SeuilArticle ***************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String creerSeuilArticle(SeuilArticleValue pSeuilArticleValue);

  /********************** supprimer SeuilArticle *****************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public void supprimerSeuilArticle(Long pId);

  /********************** modifier SeuilArticle *****************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String modifierSeuilArticle(SeuilArticleValue pSeuilArticleValue);

  /********************** recherche SeuilArticle ****************************/
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public SeuilArticleValue rechercheSeuilArticleParId(Long pId);

  /****************** afficher liste SeuilArticle **************************/
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public List < SeuilArticleValue > listeSeuilArticle();

}
