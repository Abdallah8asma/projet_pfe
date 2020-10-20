package com.gpro.consulting.tiers.logistique.persistance.gc.achat.facture;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.TaxeFactureAchatValue;

/**
 * TaxeFacture Persistance interface.
 *
 * @author Hamdi Etteieb
 * @since 23/09/2018
 */
public interface ITaxeFactureAchatPersistance {

	/**
	 * Creates the.
	 *
	 * @param TaxeFactureAchatValue
	 *            the taxe facture achat value
	 * @return the string
	 */
	public String create(TaxeFactureAchatValue TaxeFactureAchatValue);

	/**
	 * Gets the by id.
	 *
	 * @param id
	 *            the id
	 * @return the by id
	 */
	public TaxeFactureAchatValue getById(Long id);

	/**
	 * Update.
	 *
	 * @param TaxeFactureAchatValue
	 *            the taxe facture achat value
	 * @return the string
	 */
	public String update(TaxeFactureAchatValue TaxeFactureAchatValue);

	/**
	 * Delete.
	 *
	 * @param id
	 *            the id
	 */
	public void delete(Long id);

	/**
	 * Gets the all by facture id.
	 *
	 * @param factureId
	 *            the facture id
	 * @return the all by facture id
	 */
	public List<TaxeFactureAchatValue> getAllByFactureId(Long factureId);

}
