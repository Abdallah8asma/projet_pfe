package com.gpro.consulting.tiers.commun.service.elementBase;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitSerialisableValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticritereProduitSerialisableValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ResultatRechecheProduitSerialisableValue;

/**
 * The Interface IProduitSerialisablePersistance.
 * 
 * @author med
 */
public interface IProduitSerialisableService {
  /**
   * Creer produit.
   *
   * @param pProduitSerialisableValue
   *          the produit value
   * @return the string
   */
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String creerProduitSerialisable(ProduitSerialisableValue pProduitSerialisableValue);

  /**
   * Supprimer produit.
   *
   * @param pProduitSerialisableId
   *          the produit id
   */
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public void supprimerProduitSerialisable(Long pProduitSerialisableId);

  /**
   * Modifier produit.
   *
   * @param pProduitSerialisableValue
   *          the produit value
   * @return the string
   */
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String modifierProduitSerialisable(ProduitSerialisableValue pProduitSerialisableValue);

  /**
   * Recherche produit by id.
   *
   * @param pProduitSerialisableId
   *          the produit id
   * @return the produit value
   */
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public ProduitSerialisableValue rechercheProduitSerialisableById(Long pProduitSerialisableId);

  /**
   * Liste produit.
   *
   * @return the list
   */
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public List < ProduitSerialisableValue > listeProduitSerialisable();

  // recherche multi criteres
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public ResultatRechecheProduitSerialisableValue rechercherProduitSerialisableMultiCritere(
    RechercheMulticritereProduitSerialisableValue pRechercheProduitSerialisableMulitCritere);


}
