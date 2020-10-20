package com.gpro.consulting.tiers.commun.service.elementBase;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.SousFamilleArticleValue;

// TODO: Auto-generated Javadoc
/**
 * The Interface ISousFamilleArticleService.
 * 
 * @author mohamed
 */
public interface ISousFamilleArticleService {

  /**
   * Creer sous famille article.
   *
   * @param pSousFamilleArticleValue
   *          the sous famille article value
   * @return the string
   */
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String creerSousFamilleArticle(SousFamilleArticleValue pSousFamilleArticleValue);

  /**
   * Supprimer sous famille article.
   *
   * @param pSousFamilleArticleId
   *          the sous famille article id
   */
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public void supprimerSousFamilleArticle(Long pSousFamilleArticleId);

  /**
   * Modifier sous famille article.
   *
   * @param pSousFamilleArticleValue
   *          the sous famille article value
   * @return the string
   */
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String modifierSousFamilleArticle(SousFamilleArticleValue pSousFamilleArticleValue);

  /**
   * Recherche type article by id.
   *
   * @param pSousFamilleArticleId
   *          the sous famille article id
   * @return the sous famille article value
   */
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public SousFamilleArticleValue rechercheSousFamilleArticleById(Long pSousFamilleArticleId);

  /**
   * Liste sous famille article.
   *
   * @return the list
   */
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public List < SousFamilleArticleValue > listeSousFamilleArticle();

}
