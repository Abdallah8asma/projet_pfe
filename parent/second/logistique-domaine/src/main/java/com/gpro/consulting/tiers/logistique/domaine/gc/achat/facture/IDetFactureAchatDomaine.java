package com.gpro.consulting.tiers.logistique.domaine.gc.achat.facture;

import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.DetFactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.RechercheMulticritereDetFactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.ResultatRechecheDetFactureAchatValue;

/**
 * DetFacture Achat Domaine interface.
 *
 * @author Hamdi Etteieb
 * @since 23/09/2018
 */
public interface IDetFactureAchatDomaine {

	/**
	 * Creates the.
	 *
	 * @param detFactureAchatValue
	 *            the det facture achat value
	 * @return the string
	 */
	public String create(DetFactureAchatValue detFactureAchatValue);

	/**
	 * Gets the by id.
	 *
	 * @param id
	 *            the id
	 * @return the by id
	 */
	public DetFactureAchatValue getById(Long id);

	/**
	 * Update.
	 *
	 * @param detFactureAchatValue
	 *            the det facture achat value
	 * @return the string
	 */
	public String update(DetFactureAchatValue detFactureAchatValue);

	/**
	 * Delete.
	 *
	 * @param id
	 *            the id
	 */
	public void delete(Long id);
	public ResultatRechecheDetFactureAchatValue rechercherMultiCritere(RechercheMulticritereDetFactureAchatValue request);


}
