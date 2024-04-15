package com.gpro.consulting.tiers.commun.service.elementBase;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.TypeArticleValue;

// TODO: Auto-generated Javadoc
/**
 * The Interface ITypeArticleService.
 * 
 * @author mohamed
 */
public interface ITypeArticleService {

  /**
   * Creer type article.
   *
   * @param pTypeArticleValue
   *          the type article value
   * @return the string
   */
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String creerTypeArticle(TypeArticleValue pTypeArticleValue);

  /**
   * Supprimer type article.
   *
   * @param pTypeArticleId
   *          the type article id
   */
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public void supprimerTypeArticle(Long pTypeArticleId);

  /**
   * Modifier type article.
   *
   * @param pTypeArticleValue
   *          the type article value
   * @return the string
   */
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String modifierTypeArticle(TypeArticleValue pTypeArticleValue);

  /**
   * Recherche type article by id.
   *
   * @param pTypeArticleId
   *          the type article id
   * @return the type article value
   */
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public TypeArticleValue rechercheTypeArticleById(Long pTypeArticleId);

  /**
   * Liste type article.
   *
   * @return the list
   */
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public List < TypeArticleValue > listeTypeArticle();
}
