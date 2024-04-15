package com.gpro.consulting.tiers.commun.service.elementBase;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.SuperFamilleProduitValue;

/**
 * The Interface IProduitPersistance.
 * 
 * @author med
 */
public interface ISuperFamilleProduitService {
  /**
   * Creer famille produit.
   *
   * @param pSuperFamilleProduitValue
   *          the famille produit value
   * @return the string
   */
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String creerSuperFamilleProduit(SuperFamilleProduitValue pSuperFamilleProduitValue);

  /**
   * Supprimer sous famille produit.
   *
   * @param pFamilleProduitId
   *          the famille produit id
   */
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public void supprimerSuperFamilleProduit(Long pFamilleProduitId);

  /**
   * Modifier famille produit.
   *
   * @param pSuperFamilleProduitValue
   *          the famille produit value
   * @return the string
   */
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String modifierSuperFamilleProduit(SuperFamilleProduitValue pSuperFamilleProduitValue);

  /**
   * Recherche famille produit by id.
   *
   * @param pFamilleProduitId
   *          the famille produit id
   * @return the famille produit value
   */
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public SuperFamilleProduitValue rechercheSuperFamilleProduitById(Long pFamilleProduitId);

  /**
   * Liste famille produit.
   *
   * @return the list
   */
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public List < SuperFamilleProduitValue > listeSuperFamilleProduit();
}
