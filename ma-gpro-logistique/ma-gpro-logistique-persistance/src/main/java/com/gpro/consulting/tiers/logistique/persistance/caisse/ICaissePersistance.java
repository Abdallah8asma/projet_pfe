package com.gpro.consulting.tiers.logistique.persistance.caisse;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.caisse.value.CaisseValue;
import com.gpro.consulting.tiers.logistique.coordination.caisse.value.RechercheMulticritereCaisseValue;
import com.gpro.consulting.tiers.logistique.coordination.caisse.value.ResultatRechercheCaisseValue;

// TODO: Auto-generated Javadoc
/**
 * ICaissePersistance Persistance interface.
 *
 * @author Hamdi Etteieb
 * @since 16/08/2018
 */
public interface ICaissePersistance {

	/**
	 * Creates the.
	 *
	 * @param caisseValue
	 *            the caisse value
	 * @return the string
	 */
	public String create(CaisseValue caisseValue);

	/**
	 * Gets the by id.
	 *
	 * @param id
	 *            the id
	 * @return the by id
	 */
	public CaisseValue getById(Long id);

	/**
	 * Update.
	 *
	 * @param caisseValue
	 *            the caisse value
	 * @return the string
	 */
	public String update(CaisseValue caisseValue);

	/**
	 * Delete.
	 *
	 * @param id
	 *            the id
	 */
	public void delete(Long id);

	/**
	 * Rechercher multi critere.
	 *
	 * @param request
	 *            the request
	 * @return the resultat recherche caisse value
	 */
	public ResultatRechercheCaisseValue rechercherMultiCritere(RechercheMulticritereCaisseValue request);

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	public List<CaisseValue> getAll();

	/**
	 * Gets the caisse non cloturer.
	 *
	 * @return the caisse non cloturer
	 */
	public List<CaisseValue> getCaisseNonCloturer();

}
