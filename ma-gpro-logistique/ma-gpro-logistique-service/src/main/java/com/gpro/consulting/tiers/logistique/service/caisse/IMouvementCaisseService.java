package com.gpro.consulting.tiers.logistique.service.caisse;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.caisse.value.MouvementCaisseValue;
import com.gpro.consulting.tiers.logistique.coordination.caisse.value.RechercheMulticritereMouvementCaisseValue;
import com.gpro.consulting.tiers.logistique.coordination.caisse.value.ResultatRechercheMouvementCaisseValue;

// TODO: Auto-generated Javadoc
/**
 * ICaisseService Service Interface.
 *
 * @author Hamdi Etteieb
 * @since 16/08/2018
 */
public interface IMouvementCaisseService {

	/**
	 * Creates the.
	 *
	 * @param mvtValue
	 *            the mvt value
	 * @return the string
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String create(MouvementCaisseValue mvtValue);

	/**
	 * Gets the by id.
	 *
	 * @param id
	 *            the id
	 * @return the by id
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public MouvementCaisseValue getById(Long id);

	/**
	 * Rechercher multi critere.
	 *
	 * @param request
	 *            the request
	 * @return the resultat recherche mouvement caisse value
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public ResultatRechercheMouvementCaisseValue rechercherMultiCritere(
			RechercheMulticritereMouvementCaisseValue request);

	/**
	 * Update.
	 *
	 * @param mvtValue
	 *            the mvt value
	 * @return the string
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String update(MouvementCaisseValue mvtValue);

	/**
	 * Delete.
	 *
	 * @param id
	 *            the id
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void delete(Long id);

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	public List<MouvementCaisseValue> getAll();

}
