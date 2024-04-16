package com.gpro.consulting.tiers.logistique.domaine.atelier.bonReception;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.BonReceptionValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.RechercheMulticritereBonReceptionValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.ResultatRechecheBonReceptionValue;


// TODO: Auto-generated Javadoc
/**
 * Interface des méthodes CRUD du Bon de reception.
 *
 * @author $Author: rkhaskhoussy $
 * @version $Revision: 0 $
 */
public interface IBonReceptionDomaine {
  
  /**
   * Méthode de création d'un bon de reception et de l'inserer dans la base de données avec toutes
   * ses associations.
   *
   * @param pBonReceptionValue the bon reception value
   * @return the string
   */
  public String creerBonReception(BonReceptionValue pBonReceptionValue);

  /**
   * Méthode de suppression d'un Bon de reception par id.
   *
   * @param pId the id
   */
  public void supprimerBonReception(Long pId);

  /**
   * Méthode de modification d'un bon de reception par id retournant un objet Entité.
   *
   * @param pBonReception the bon reception
   * @return the string
   */
  public String modifierBonReception(BonReceptionValue pBonReception);

  /**
   * Méthode de recherche d'un bon de reception par id retournant un objet Valeur.
   *
   * @param pBonReception the bon reception
   * @return the bon reception value
   */
  public BonReceptionValue rechercheBonReceptionParId(BonReceptionValue pBonReception);
  

  /**
   * Méthode de recherche des bons de reception par critères.
   *
   * @param pRechercheBonReceptionMulitCritere the recherche bon reception mulit critere
   * @return the resultat recheche bon reception value
   */
  public ResultatRechecheBonReceptionValue rechercherBonReceptionMultiCritere(
    RechercheMulticritereBonReceptionValue pRechercheBonReceptionMulitCritere);

  
  /**
   * Recherche bon reception by reference.
   *
   * @param reference the reference
   * @return the bon reception value
   */
  public BonReceptionValue rechercheBonReceptionByReference(String reference);

  /**
   * Get liste des bonReception.
   *
   * @return the all
   */

  public List<BonReceptionValue> getAll();
}
