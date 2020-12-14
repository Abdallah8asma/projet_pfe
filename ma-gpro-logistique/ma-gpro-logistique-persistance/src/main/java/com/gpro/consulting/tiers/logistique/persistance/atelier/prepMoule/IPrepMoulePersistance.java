package com.gpro.consulting.tiers.logistique.persistance.atelier.prepMoule;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.atelier.PrepMoule.value.PrepMouleValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.PrepMoule.value.RechercheMulticriterePrepMouleValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.PrepMoule.value.ResultatRecherchePrepMouleValue;

/**
 * Interface des méthodes CRUD du PrepMoule
 * 
 * @author $Author: rkhaskhoussy $
 * @version $Revision: 0 $
 */
public interface IPrepMoulePersistance {

	/**
	 * Méthode de création d'une partie intéressée et de l'inserer dans la base
	 * de données avec toutes ses associations.
	 * 
	 * @param pPartieInteresseValue
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
	 * Méthode de modification d'un bon de reception par id retournant un objet
	 * Entité.
	 * 
	 * @param pBonReception
	 * @return
	 */
	public String modifierPrepMoule(PrepMouleValue pPrepMoule);

	/**
	 * Méthode de recherche d'un bon de reception par id retournant un objet
	 * Valeur.
	 * 
	 * @param pBonReception
	 * @return
	 */
	public PrepMouleValue recherchePrepMouleParId(Long pPrepMoule);
	
	public PrepMouleValue recherchePrepMouleParReference(Long pPrepMoule);
	

	public ResultatRecherchePrepMouleValue rechercherPrepMouleMultiCritere(
			RechercheMulticriterePrepMouleValue pRecherchePrepMouleMulitCritere);

	 public List<PrepMouleValue> listerPrepMoule();
	 
	 public List<PrepMouleValue> getPrepMouleByReference(String referencePrepMoule);
	 
	
	 public List<String> getListRefPrepMouleParRefBR(String referenceBR);

	 public List<PrepMouleValue> getReferencePrepMoule();
}
