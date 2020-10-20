package com.gpro.consulting.tiers.commun.service.elementBase;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.FamilleProduitValue;

/**
 * The Interface IProduitPersistance.
 * 
 * @author med
 */
public interface IFamilleProduitService {
  /**
   * Creer famille produit.
   *
   * @param pFamilleProduitValue
   *          the famille produit value
   * @return the string
   */
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String creerFamilleProduit(FamilleProduitValue pFamilleProduitValue);

  /**
   * Supprimer sous famille produit.
   *
   * @param pFamilleProduitId
   *          the famille produit id
   */
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public void supprimerFamilleProduit(Long pFamilleProduitId);

  /**
   * Modifier famille produit.
   *
   * @param pFamilleProduitValue
   *          the famille produit value
   * @return the string
   */
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String modifierFamilleProduit(FamilleProduitValue pFamilleProduitValue);

  /**
   * Recherche famille produit by id.
   *
   * @param pFamilleProduitId
   *          the famille produit id
   * @return the famille produit value
   */
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public FamilleProduitValue rechercheFamilleProduitById(Long pFamilleProduitId);

  /**
   * Liste famille produit.
   *
   * @return the list
   */
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public List < FamilleProduitValue > listeFamilleProduit();
}
