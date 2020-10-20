package com.gpro.consulting.tiers.logistique.service.caisse;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.caisse.value.CaisseValue;
import com.gpro.consulting.tiers.logistique.coordination.caisse.value.RechercheMulticritereCaisseValue;
import com.gpro.consulting.tiers.logistique.coordination.caisse.value.ResultatRechercheCaisseValue;

// TODO: Auto-generated Javadoc
/**
 * ICaisseService Service Interface.
 *
 * @author Hamdi Etteieb
 * @since 16/08/2018
 */
public interface ICaisseService {

	/**
	 * Creates the.
	 *
	 * @param bonSortieFiniValue
	 *            the bon sortie fini value
	 * @return the string
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String create(CaisseValue bonSortieFiniValue);

	/**
	 * Gets the by id.
	 *
	 * @param id
	 *            the id
	 * @return the by id
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public CaisseValue getById(Long id);

	/**
	 * Rechercher multi critere.
	 *
	 * @param request
	 *            the request
	 * @return the resultat recherche caisse value
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public ResultatRechercheCaisseValue rechercherMultiCritere(RechercheMulticritereCaisseValue request);

	/**
	 * Update.
	 *
	 * @param bonSortieFiniValue
	 *            the bon sortie fini value
	 * @return the string
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String update(CaisseValue bonSortieFiniValue);

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
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public List<CaisseValue> getAll();

	/**
	 * Gets the c aisses non cloture.
	 *
	 * @return the c aisses non cloture
	 */
	List<CaisseValue> getCAissesNonCloture();

}
