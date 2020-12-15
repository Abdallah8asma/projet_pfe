package com.gpro.consulting.tiers.logistique.service.atelier.prepMoule;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.atelier.PrepMoule.value.PrepMouleValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.PrepMoule.value.RechercheMulticriterePrepMouleValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.PrepMoule.value.ResultatRecherchePrepMouleValue;


/**
 * Interface des méthodes CRUD du Bon de reception
 * 
 * @author $Author: rkhaskhoussy $
 * @version $Revision: 0 $
 */

public interface IPrepMouleService {

  /**
   * Méthode de création d'un bon de reception et de l'inserer dans la base de données avec toutes
   * ses associations.
   * 
   * @param pPrepMouleValue
   * @return
   */
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String creerPrepMoule(PrepMouleValue pPrepMouleValue);

  /**
   * Méthode de suppression d'un Bon de reception par id.
   * 
   * @param pId
   */
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public void supprimerPrepMoule(Long pId);

  /**
   * Méthode de modification d'un bon de reception par id
   * 
   * @param pPrepMoule
   * @return
   */
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String modifierPrepMoule(PrepMouleValue pPrepMoule);

  /**
   * Méthode de recherche d'un bon de reception par id retournant un objet Valeur.
   * 
   * @param pPrepMoule
   * @return
   */
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public PrepMouleValue recherchePrepMouleParId(Long pPrepMoule);
  
  
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public PrepMouleValue recherchePrepMouleParReference(Long reference);
  
  
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public ResultatRecherchePrepMouleValue rechercherPrepMouleMultiCritere(
			RechercheMulticriterePrepMouleValue pRecherchePrepMouleMulitCritere) ;
  
  
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public List<PrepMouleValue> listerPrepMoule();
  
 
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public String listRefPrepMouleParRefBR(String referenceBR);
  
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public List<PrepMouleValue> getReferencePrepMoule();
}
