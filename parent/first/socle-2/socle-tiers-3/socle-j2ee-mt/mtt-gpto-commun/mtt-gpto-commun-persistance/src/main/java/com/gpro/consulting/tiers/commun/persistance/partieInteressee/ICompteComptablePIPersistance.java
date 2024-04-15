package com.gpro.consulting.tiers.commun.persistance.partieInteressee;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.CompteComptablePIValue;


/**
 * The Interface IUntieArticle.
 * @author $mohamed
 */

public interface ICompteComptablePIPersistance {
	/**
	 * ajouter    unite Article*.
	 *
	 * @return the string
	 */
	/** create CompteComptablePI */
	public  String creerCompteComptablePI(CompteComptablePIValue pCompteComptablePIValue);
	/**
	 * supprimer  unite Article*.
	 *
	 */
	public  void supprimerCompteComptablePI(Long pCompteComptablePIValueId);
	/**
	 * modifier unite Article*.
	 *
	 * @return the string
	 */
	public String modifierCompteComptablePI(CompteComptablePIValue pCompteComptablePIValue);
	/**
	 *  recherche by id unite Article*.
	 *
	 */
	public CompteComptablePIValue  rechercheCompteComptablePIById(Long  pCompteComptablePIValueId);
	/**
	 * afficher  liste unite   Article*.
	 *
	 * @return the list
	 */
	public List<CompteComptablePIValue> listeCompteComptablePI();
	public CompteComptablePIValue rechercheCompteComptablePIParDesignation(String designation);

}
