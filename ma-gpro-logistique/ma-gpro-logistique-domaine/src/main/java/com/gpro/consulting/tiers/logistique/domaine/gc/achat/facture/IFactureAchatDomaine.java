package com.gpro.consulting.tiers.logistique.domaine.gc.achat.facture;

import java.util.Calendar;
import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.FactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.FactureAchatVue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.ListProduitAchatElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.RechercheMulticritereFactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.ResultatRechecheFactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.RechercheMulticritereBonReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.vente.facture.value.BLReportElementRecapValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ListProduitElementValue;

/**
 * Facture Domaine interface.
 *
 * @author Hamdi Etteieb
 * @since 23/09/2018
 */
public interface IFactureAchatDomaine {

	/**
	 * Rechercher multi critere.
	 *
	 * @param request
	 *            the request
	 * @return the resultat recheche facture achat value
	 */
	public ResultatRechecheFactureAchatValue rechercherMultiCritere(RechercheMulticritereFactureAchatValue request);

	/**
	 * Creates the facture.
	 *
	 * @param factureValue
	 *            the facture value
	 * @return the string
	 */
	public String createFacture(FactureAchatValue factureValue);

	/**
	 * Gets the facture by id.
	 *
	 * @param id
	 *            the id
	 * @return the facture by id
	 */
	public FactureAchatValue getFactureById(Long id);

	/**
	 * Update facture.
	 *
	 * @param factureValue
	 *            the facture value
	 * @return the string
	 */
	public String updateFacture(FactureAchatValue factureValue);

	/**
	 * Delete facture.
	 *
	 * @param id
	 *            the id
	 */
	public void deleteFacture(Long id);

	/**
	 * Gets the facture by reference.
	 *
	 * @param reference
	 *            the reference
	 * @return the facture by reference
	 */
	public FactureAchatValue getFactureByReference(String reference);

	/**
	 * Gets the list facture ref by client.
	 *
	 * @param idClient
	 *            the id client
	 * @return the list facture ref by client
	 */
	public List<FactureAchatVue> getListFactureRefByClient(Long idClient);

	/**
	 * Gets the produit element list.
	 *
	 * @param refFactureList
	 *            the ref facture list
	 * @param factureAchatId
	 *            the facture achat id
	 * @return the produit element list
	 */
	public ListProduitAchatElementValue getProduitElementList(List<String> refFactureList, Long factureAchatId);

	public String getCurrentReference(String type,Calendar instance, boolean b);

	public ListProduitElementValue getArticleAvoir(Long clientId);

	public BLReportElementRecapValue getDepenseFacturebyMonth(RechercheMulticritereFactureAchatValue request);

	public String getCurrentReferenceMensuel(String type, Calendar instance, boolean b);

	public String getCurrentReferenceMensuelDeclarer(String type, boolean declarer, Calendar instance, boolean b);

}
