/**
 * 
 */
package com.gpro.consulting.tiers.logistique.service.atelier.bonReception;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.TraitementValue;

/**
 * Interface des méthodes CRUD de traitements
 *  
 * @author Ameni
 */
public interface ITraitementService {
	/**
	 * Méthode de création d'un traitement et de l'inserer dans la base de
	 * données retournant l'id de l'objet entité.
	 * 
	 * @param pTraitementValue
	 * @return vId
	 */
	public String creerTraitement(TraitementValue pTraitementValue);

	/**
	 * Méthode de suppression d'un traitement par id.
	 * 
	 * @param pId
	 */
	public void supprimerTraitement(Long pId);

	/**
	 * Méthode de modification d'un traitement par id retournant l'id de l'objet
	 * entité.
	 * 
	 * @param pTraitement
	 * @return vId
	 */
	public String modifierTraitement(TraitementValue pTraitement);

	/**
	 * Liste des Traitements.
	 *
	 * @return the list
	 */
	public List<TraitementValue> listeTraitement();

}
