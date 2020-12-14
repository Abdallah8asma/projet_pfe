package com.gpro.consulting.tiers.logistique.domaine.atelier.prepMoule;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.atelier.PrepMoule.value.PrepMouleValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.PrepMoule.value.RechercheMulticriterePrepMouleValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.PrepMoule.value.ResultatRecherchePrepMouleValue;



/**
 * Interface des méthodes CRUD du Bon de reception
 * 
 * @author $Author: rkhaskhoussy $
 * @version $Revision: 0 $
 */
public interface IPrepMouleDomaine {
  /**
   * Méthode de création d'un bon de reception et de l'inserer dans la base de données avec toutes
   * ses associations.
   * 
   * @param pPrepMouleValue
   * @return
   */
  public String creerPrepMoule(PrepMouleValue pPrepMouleValue);

  /**
   * Méthode de suppression d'un Bon de reception par id.
   * 
   * @param pId
   */
  public void supprimerPrepMoule(Long pId);

  /**
   * Méthode de modification d'un bon de reception par id retournant un objet Entité.
   * 
   * @param pPrepMoule
   * @return
   */
  public String modifierPrepMoule(PrepMouleValue pPrepMoule);

  /**
   * Méthode de recherche d'un bon de reception par id retournant un objet Valeur.
   * 
   * @param pPrepMoule
   * @return
   */
  public PrepMouleValue recherchePrepMouleParId(Long pId);
  
  public PrepMouleValue recherchePrepMouleParReference(Long pId);
  
  
  public ResultatRecherchePrepMouleValue rechercherPrepMouleMultiCritere(
			RechercheMulticriterePrepMouleValue pRecherchePrepMouleMulitCritere) ;

  public List<PrepMouleValue> listerPrepMoule();
  
  public List<PrepMouleValue> getPrepMouleByReference(String referencePrepMoule);
  

  public List<String> getListRefPrepMouleParRefBR(String referenceBR);
  
  public String listRefPrepMouleParRefBR(String referenceBR);
  
  public List<PrepMouleValue> getReferencePrepMoule();
  
}
