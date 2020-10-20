package com.gpro.consulting.tiers.logistique.persistance.caisse;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.caisse.value.MouvementCaisseValue;
import com.gpro.consulting.tiers.logistique.coordination.caisse.value.RechercheMulticritereMouvementCaisseValue;
import com.gpro.consulting.tiers.logistique.coordination.caisse.value.ResultatRechercheMouvementCaisseValue;

// TODO: Auto-generated Javadoc
/**
 * IMouvementCaissePersistance Persistance interface.
 *
 * @author Hamdi Etteieb
 * @since 16/08/2018
 */
public interface IMouvementCaissePersistance {

	/**
	 * Creates the.
	 *
	 * @param mvtCaisseValue
	 *            the mvt caisse value
	 * @return the string
	 */
	public String create(MouvementCaisseValue mvtCaisseValue);

	/**
	 * Delete.
	 *
	 * @param id
	 *            the id
	 */
	public void delete(Long id);

	/**
	 * Gets the by id.
	 *
	 * @param id
	 *            the id
	 * @return the by id
	 */
	public MouvementCaisseValue getById(Long id);

	/**
	 * Update.
	 *
	 * @param mvtCaisseValue
	 *            the mvt caisse value
	 * @return the string
	 */
	public String update(MouvementCaisseValue mvtCaisseValue);

	/**
	 * Recherche mutlicritere.
	 *
	 * @param rechercheValue
	 *            the recherche value
	 * @return the resultat recherche mouvement caisse value
	 */
	public ResultatRechercheMouvementCaisseValue rechercheMutlicritere(
			RechercheMulticritereMouvementCaisseValue rechercheValue);

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	List<MouvementCaisseValue> getAll();
}
