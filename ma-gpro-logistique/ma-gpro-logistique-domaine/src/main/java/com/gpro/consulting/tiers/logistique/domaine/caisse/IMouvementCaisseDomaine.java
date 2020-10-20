package com.gpro.consulting.tiers.logistique.domaine.caisse;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.caisse.value.MouvementCaisseValue;
import com.gpro.consulting.tiers.logistique.coordination.caisse.value.RechercheMulticritereMouvementCaisseValue;
import com.gpro.consulting.tiers.logistique.coordination.caisse.value.ResultatRechercheMouvementCaisseValue;

// TODO: Auto-generated Javadoc
/**
 * caisseValue Domaine interface.
 *
 * @author Hamdi Etteieb
 * @since 16/08/2018
 */
public interface IMouvementCaisseDomaine {

	/**
	 * Creates the.
	 *
	 * @param caisseValue
	 *            the caisse value
	 * @return the string
	 */
	public String create(MouvementCaisseValue caisseValue);

	/**
	 * Gets the by id.
	 *
	 * @param id
	 *            the id
	 * @return the by id
	 */
	public MouvementCaisseValue getById(Long id);

	/**
	 * Rechercher multi critere.
	 *
	 * @param request
	 *            the request
	 * @return the resultat recherche mouvement caisse value
	 */
	public ResultatRechercheMouvementCaisseValue rechercherMultiCritere(
			RechercheMulticritereMouvementCaisseValue request);

	/**
	 * Update.
	 *
	 * @param caisseValue
	 *            the caisse value
	 * @return the string
	 */
	public String update(MouvementCaisseValue caisseValue);

	/**
	 * Delete.
	 *
	 * @param id
	 *            the id
	 */
	public void delete(Long id);

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	List<MouvementCaisseValue> getAll();

}