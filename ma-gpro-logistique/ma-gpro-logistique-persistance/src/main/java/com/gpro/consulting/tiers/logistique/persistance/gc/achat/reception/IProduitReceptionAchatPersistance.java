package com.gpro.consulting.tiers.logistique.persistance.gc.achat.reception;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ProduitReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.RechercheMulticritereProduitReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ResultatRechecheProduitReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.RechercheMulticritereDetLivraisonValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.chart.value.ResultBestElementValue;

/**
 * The Interface IProduitReceptionAchatPersistance.
 * 
 * @author Hamdi Etteieb
 * @since 23/08/2018
 */
public interface IProduitReceptionAchatPersistance {

	/**
	 * Creates the.
	 *
	 * @param produitReceptionValue
	 *            the produit reception value
	 * @return the string
	 */
	public String create(ProduitReceptionAchatValue produitReceptionValue);

	/**
	 * Gets the by id.
	 *
	 * @param id
	 *            the id
	 * @return the by id
	 */
	public ProduitReceptionAchatValue getById(Long id);

	/**
	 * Update.
	 *
	 * @param produitReceptionValue
	 *            the produit reception value
	 * @return the string
	 */
	public String update(ProduitReceptionAchatValue produitReceptionValue);

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
	public List<ProduitReceptionAchatValue> getAll();

	public ResultatRechecheProduitReceptionAchatValue rechercherMultiCritere(
			RechercheMulticritereProduitReceptionAchatValue request);

	public List<ResultBestElementValue> getTop10ArticleAchat(RechercheMulticritereProduitReceptionAchatValue request);

	public List<ResultBestElementValue> getTop3Fournisseur(RechercheMulticritereProduitReceptionAchatValue request);

	public List<ResultBestElementValue> getDepenseFournisseur(RechercheMulticritereProduitReceptionAchatValue request);

}
