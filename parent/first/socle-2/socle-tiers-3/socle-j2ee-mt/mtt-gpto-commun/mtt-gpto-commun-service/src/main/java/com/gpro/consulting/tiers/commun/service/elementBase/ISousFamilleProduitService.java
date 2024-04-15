package com.gpro.consulting.tiers.commun.service.elementBase;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.SousFamilleProduitValue;

/**
 * The Interface IProduitPersistance.
 * 
 * @author med
 */
public interface ISousFamilleProduitService {
  /**
   * Creer sous famille produit.
   *
   * @param pSousFamilleProduitValue
   *          the sous famille produit value
   * @return the string
   */
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String creerSousFamilleProduit(SousFamilleProduitValue pSousFamilleProduitValue);

  /**
   * Supprimer sous famille produit.
   *
   * @param pSousFamilleProduitId
   *          the sous famille produit id
   */
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public void supprimerSousFamilleProduit(Long pSousFamilleProduitId);

  /**
   * Modifier sous famille produit.
   *
   * @param pSousFamilleProduitValue
   *          the sous famille produit value
   * @return the string
   */
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String modifierSousFamilleProduit(SousFamilleProduitValue pSousFamilleProduitValue);

  /**
   * Recherche sous famille produit by id.
   *
   * @param pSousFamilleProduitId
   *          the sous famille produit id
   * @return the sous famille produit value
   */
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public SousFamilleProduitValue rechercheSousFamilleProduitById(Long pSousFamilleProduitId);

  /**
   * Liste sous famille produit.
   *
   * @return the list
   */
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public List < SousFamilleProduitValue > listeSousFamilleProduit();
}
