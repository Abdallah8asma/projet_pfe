/**
 * 
 */
package com.gpro.consulting.tiers.logistique.domaine.atelier.bonReception;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.TraitementValue;

// TODO: Auto-generated Javadoc
/**
 * Interface des méthodes CRUD de traitements.
 *
 * @author Ameni
 */
public interface ITraitementDomaine {

	/**
	 * Méthode de création d'un traitement et de l'inserer dans la base de
	 * données retournant l'id de l'objet entité.
	 *
	 * @param pTraitementValue the traitement value
	 * @return vId
	 */
	public String creerTraitement(TraitementValue pTraitementValue);

	/**
	 * Méthode de suppression d'un traitement par id.
	 *
	 * @param pId the id
	 */
	public void supprimerTraitement(Long pId);

	/**
	 * Méthode de modification d'un traitement par id retournant l'id de l'objet
	 * entité.
	 *
	 * @param pTraitement the traitement
	 * @return vId
	 */
	public String modifierTraitement(TraitementValue pTraitement);

	/**
	 * Liste des Traitements.
	 *
	 * @return the list
	 */
	public List<TraitementValue> listeTraitement();
	
	/**
	 * 	Retrive Traitement by id.
	 *
	 * @param id the id
	 * @return the traitement par id
	 */
	public TraitementValue getTraitementParId(Long id);

}
