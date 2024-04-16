package com.gpro.consulting.tiers.logistique.persistance.gc.achat.facture;

import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.DetFactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.RechercheMulticritereDetFactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.ResultatRechecheDetFactureAchatValue;

/**
 * DetFactureAchat Persistance interface.
 *
 * @author Hamdi Etteieb
 * @since 23/09/2018
 */
public interface IDetFactureAchatPersistance {

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

	/**
	 * Gets the by facture achat and produit.
	 *
	 * @param FactureAchatId
	 *            the facture achat id
	 * @param produitId
	 *            the produit id
	 * @param choix
	 *            the choix
	 * @return the by facture achat and produit
	 */
	public DetFactureAchatValue getByFactureAchatAndProduit(Long FactureAchatId, Long produitId, String choix);
	public ResultatRechecheDetFactureAchatValue rechercherMultiCritere(RechercheMulticritereDetFactureAchatValue request);


}
