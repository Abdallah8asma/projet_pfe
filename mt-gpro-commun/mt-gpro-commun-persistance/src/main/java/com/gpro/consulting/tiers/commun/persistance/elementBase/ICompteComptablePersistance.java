package com.gpro.consulting.tiers.commun.persistance.elementBase;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.CompteComptableValue;


/**
 * The Interface IUntieArticle.
 * @author $mohamed
 */

public interface ICompteComptablePersistance {
	/**
	 * ajouter    unite Article*.
	 *
	 * @return the string
	 */
	/** create CompteComptable */
	public  String creerCompteComptable(CompteComptableValue pCompteComptableValue);
	/**
	 * supprimer  unite Article*.
	 *
	 */
	public  void supprimerCompteComptable(Long pCompteComptableValueId);
	/**
	 * modifier unite Article*.
	 *
	 * @return the string
	 */
	public String modifierCompteComptable(CompteComptableValue pCompteComptableValue);
	/**
	 *  recherche by id unite Article*.
	 *
	 */
	public CompteComptableValue  rechercheCompteComptableById(Long  pCompteComptableValueId);
	/**
	 * afficher  liste unite   Article*.
	 *
	 * @return the list
	 */
	public List<CompteComptableValue> listeCompteComptable();

}
