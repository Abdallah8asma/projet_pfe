package com.gpro.consulting.tiers.logistique.domaine.gc.achat.facture;

import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.TaxeFactureAchatValue;

/**
 * TaxeFacture Domaine interface.
 *
 * @author Hamdi Etteieb
 * @since 23/09/2018
 */
public interface ITaxeFactureAchatDomaine {

	/**
	 * Creates the.
	 *
	 * @param taxeFactureValue
	 *            the taxe facture value
	 * @return the string
	 */
	public String create(TaxeFactureAchatValue taxeFactureValue);

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
	 * @param taxeFactureValue
	 *            the taxe facture value
	 * @return the string
	 */
	public String update(TaxeFactureAchatValue taxeFactureValue);

	/**
	 * Delete.
	 *
	 * @param id
	 *            the id
	 */
	public void delete(Long id);

}
