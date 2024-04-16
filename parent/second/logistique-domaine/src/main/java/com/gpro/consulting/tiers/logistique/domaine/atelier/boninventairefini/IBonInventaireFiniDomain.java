package com.gpro.consulting.tiers.logistique.domaine.atelier.boninventairefini;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.atelier.boninventairefini.value.BonInventaireFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.boninventairefini.value.RechercheMulticritereBonInventaireFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.boninventairefini.value.ResultatRechecheBonInventaireFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.RouleauFiniValue;

// TODO: Auto-generated Javadoc
/**
 * BonSortieFini Domaine interface.
 *
 * @author Ghazi Atroussi
 * @since 18/12/2016
 */
public interface IBonInventaireFiniDomain {

	/**
	 * Validate bon inventaire fini.
	 *
	 * @param barreCodeList
	 *            the barre code list
	 * @param id
	 *            the id
	 * @return the list
	 */
	public List<RouleauFiniValue> validateBonInventaireFini(List<String> barreCodeList, Long id);

	/**
	 * Creates the bon inventaire fini.
	 *
	 * @param bonInventaireFiniValue
	 *            the bon inventaire fini value
	 * @return the string
	 */
	public String createBonInventaireFini(BonInventaireFiniValue bonInventaireFiniValue);

	/**
	 * Gets the bon inventaire fini by id.
	 *
	 * @param id
	 *            the id
	 * @return the bon inventaire fini by id
	 */
	public BonInventaireFiniValue getBonInventaireFiniById(Long id);

	/**
	 * Rechercher multi critere.
	 *
	 * @param request
	 *            the request
	 * @return the resultat recheche bon inventaire fini value
	 */
	public ResultatRechecheBonInventaireFiniValue rechercherMultiCritere(
			RechercheMulticritereBonInventaireFiniValue request);

	/**
	 * Update bon inventaire fini.
	 *
	 * @param bonSortieFiniValue
	 *            the bon sortie fini value
	 * @return the string
	 */
	public String updateBonInventaireFini(BonInventaireFiniValue bonSortieFiniValue);

	/**
	 * Delete bon inventaire.
	 *
	 * @param id
	 *            the id
	 */
	public void deleteBonInventaire(Long id);

}