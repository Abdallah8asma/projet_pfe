package com.gpro.consulting.tiers.logistique.persistance.atelier.bonReception;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.BonReceptionValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.RechercheMulticritereBonReceptionValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.ResultatRechecheBonReceptionValue;

/**
 * Interface des méthodes CRUD du Bon de reception
 * 
 * @author $Author: rkhaskhoussy $
 * @version $Revision: 0 $
 */
public interface IBonReceptionPersistance {

  /**
   * Méthode de création d'une partie intéressée et de l'inserer dans la base de données avec toutes
   * ses associations.
   * 
   * @param pPartieInteresseValue
   * @return
   */
  public String creerBonReception(BonReceptionValue pBonReceptionValue);

  /**
   * Méthode de suppression d'un Bon de reception par id.
   * 
   * @param pId
   */
  public void supprimerBonReception(Long pId);

  /**
   * Méthode de modification d'un bon de reception par id retournant un objet Entité.
   * 
   * @param pBonReception
   * @return
   */
  public String modifierBonReception(BonReceptionValue pBonReception);

  /**
   * Méthode de recherche d'un bon de reception par id retournant un objet Valeur.
   * 
   * @param pBonReception
   * @return
   */
  public BonReceptionValue rechercheBonReceptionParId(BonReceptionValue pBonReception);
  
  
  public BonReceptionValue rechercheBonReceptionParId(Long id);
  
  
  /**
   * Méthode de recherche des bons de reception par critères
   * 
   * @param BonReception
   * @return
   */
  public ResultatRechecheBonReceptionValue rechercherBonReceptionMultiCritere(
    RechercheMulticritereBonReceptionValue pRechercheBonReceptionMulitCritere);

  
  public BonReceptionValue rechercheBonReceptionByReference(String reference);
  
  /**
   * Get liste des bonReception
   */

  public List<BonReceptionValue> getAll();
  
  
}

