package com.gpro.consulting.tiers.logistique.persistance.gc.achat.facture;

import java.util.Calendar;
import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.FactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.FactureAchatVue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.RechercheMulticritereFactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.ResultatRechecheFactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.RechercheMulticritereBonReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.vente.facture.value.BLReportElementRecapValue;

/**
 * Facture Persistance interface.
 *
 * @author Hamdi Eteieb
 * @since 23/09/2018
 */
public interface IFactureAchatPersistance {

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
	 * Gets the all.
	 *
	 * @return the all
	 */
	public List<FactureAchatValue> getAll();

	/**
	 * Gets the facture by reference.
	 *
	 * @param reference
	 *            the reference
	 * @return the facture by reference
	 */
	public FactureAchatValue getFactureByReference(String reference);

	/**
	 * Gets the by client id.
	 *
	 * @param clientId
	 *            the client id
	 * @return the by client id
	 */
	public List<FactureAchatValue> getByClientId(Long clientId);

	/**
	 * Gets the ref facture by client id.
	 *
	 * @param clientId
	 *            the client id
	 * @return the ref facture by client id
	 */
	public List<FactureAchatVue> getRefFactureByClientId(Long clientId);

	/**
	 * Gets the produit element list.
	 *
	 * @param refFactureList
	 *            the ref facture list
	 * @return the produit element list
	 */
	public List<FactureAchatValue> getProduitElementList(List<String> refFactureList);

	/**
	 * Existe BL.
	 *
	 * @param referenceBL
	 *            the reference BL
	 * @return true, if successful
	 */
	public boolean existeBL(String referenceBL);

	/**
	 * Gets the somme mont HT.
	 *
	 * @param PIId
	 *            the PI id
	 * @param dateMin
	 *            the date min
	 * @param dateMax
	 *            the date max
	 * @return the somme mont HT
	 */
	// Added on 22/11/2016 , by Zeineb Medimagh
	public Double getSommeMontHT(Long PIId, Calendar dateMin, Calendar dateMax);

	/**
	 * Gets the somme mont HT fact avoir.
	 *
	 * @param PIId
	 *            the PI id
	 * @param dateMin
	 *            the date min
	 * @param dateMax
	 *            the date max
	 * @return the somme mont HT fact avoir
	 */
	public Double getSommeMontHTFactAvoir(Long PIId, Calendar dateMin, Calendar dateMax);
	
	
	public List<FactureAchatValue> getByFournisseurId(Long fournisseurId);

	public List<FactureAchatValue> getByGroupeFournisseurId(Long groupeClientId);

	public BLReportElementRecapValue getDepenseFacturebyMonth(RechercheMulticritereFactureAchatValue request);

}
