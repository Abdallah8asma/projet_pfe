package com.gpro.consulting.tiers.logistique.service.atelier.bonReception;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.BonReceptionValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.RechercheMulticritereBonReceptionValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.ResultatRechecheBonReceptionValue;

/**
 * Interface des méthodes CRUD du Bon de reception
 * 
 * @author $Author: rkhaskhoussy $
 * @version $Revision: 0 $
 */
public interface IBonReceptionService {

  /**
   * Méthode de création d'un bon de reception et de l'inserer dans la base de données avec toutes
   * ses associations.
   * 
   * @param pBonReceptionValue
   * @return
   */
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String creerBonReception(BonReceptionValue pBonReceptionValue);

  /**
   * Méthode de suppression d'un Bon de reception par id.
   * 
   * @param pId
   */
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public void supprimerBonReception(Long pId);

  /**
   * Méthode de modification d'un bon de reception par id
   * 
   * @param pBonReception
   * @return
   */
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String modifierBonReception(BonReceptionValue pBonReception);

  /**
   * Méthode de recherche d'un bon de reception par id retournant un objet Valeur.
   * 
   * @param pBonReception
   * @return
   */
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public BonReceptionValue rechercheBonReceptionParId(BonReceptionValue pBonReception);
  
  
  /**
   * Méthode de recherche des bons de reception par critères
   * 
   * @param BonReception
   * @return
   */
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public ResultatRechecheBonReceptionValue rechercherBonReceptionMultiCritere(
    RechercheMulticritereBonReceptionValue pRechercheBonReceptionMulitCritere);

  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public BonReceptionValue rechercheBonReceptionByReference(String reference);
  
  
  /**
   * Get liste des bonReception
   */

  public List<BonReceptionValue> getAll();

}
