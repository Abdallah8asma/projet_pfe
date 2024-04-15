package com.gpro.consulting.tiers.commun.service.elementBase;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.FamilleArticleValue;


// TODO: Auto-generated Javadoc
/**
 * The Interface IFamilleArticle.
 * 
 * @author $mohamed
 */
public interface IFamilleArticleService {

  /**
   * ajouter Famille Article*.
   *
   * @param pFamilleArticleValue
   *          the famille article value
   * @return the string
   */
  public String creerFamilleArticle(FamilleArticleValue pFamilleArticleValue);

  /**
   * supprimer Famille Article*.
   *
   * @param pFamilleArticleValue
   *          the famille article value
   */
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public void supprimerFamilleArticle(Long pFamilleArticleId);

  /**
   * modifier Famille Article*.
   *
   * @param pFamilleArticleValue
   *          the famille article value
   * @return the string
   */
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String modifierFamilleArticle(FamilleArticleValue pFamilleArticleValue);

  /**
   * recherche by id Famille Article*.
   *
   * @param pFamilleArticleValue
   *          the famille article value
   * @return the famille article value
   */
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public FamilleArticleValue rechercheFamilleArticleById(Long pFamilleArticleId);

  /**
   * afficher liste Famille Article*.
   *
   * @return the list
   */
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public List < FamilleArticleValue > listeFamilleArticle();
}
