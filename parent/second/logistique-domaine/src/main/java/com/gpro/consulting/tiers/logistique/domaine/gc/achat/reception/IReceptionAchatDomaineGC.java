package com.gpro.consulting.tiers.logistique.domaine.gc.achat.reception;

import java.util.Calendar;
import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.BonReceptionVue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ReceptionAchatFactureVue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.RechercheMulticritereBonReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ResultatRechecheBonReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ListProduitElementValue;

/**
 * The Interface IReceptionAchatDomaineGC.
 * 
 * @author Hamdi Etteieb
 * @since 23/09/2018
 */
public interface IReceptionAchatDomaineGC {

	/**
	 * Creates the.
	 *
	 * @param bonReceptionValue
	 *            the bon reception value
	 * @return the string
	 */
	public String create(ReceptionAchatValue bonReceptionValue);

	/**
	 * Gets the by id.
	 *
	 * @param id
	 *            the id
	 * @return the by id
	 */
	public ReceptionAchatValue getById(Long id);

	/**
	 * Update.
	 *
	 * @param bonReceptionValue
	 *            the bon reception value
	 * @return the string
	 */
	public String update(ReceptionAchatValue bonReceptionValue);

	/**
	 * Delete.
	 *
	 * @param id
	 *            the id
	 */
	public String delete(Long id);

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	public List<ReceptionAchatValue> getAll();

	/**
	 * Rechercher multi critere.
	 *
	 * @param request
	 *            the request
	 * @return the resultat recheche bon reception achat value
	 */
	public ResultatRechecheBonReceptionAchatValue rechercherMultiCritere(
			RechercheMulticritereBonReceptionAchatValue request);
	

	public List<BonReceptionVue> getListBonReceptionRefByFournisseur(Long idFournisseur);


	public List<ReceptionAchatFactureVue> getAllListBonReceptionRefByFournisseur(Long idFournisseur);

	public ListProduitElementValue getProduitElementList(List<String> refBonReceptionList, Long factureAchatId);

	public String getCurrentReference(Calendar instance, boolean b);

	public ReceptionAchatValue validerFactureAvoirRetour(String reference);

	public ReceptionAchatValue validerBL(String reference);

	public String getCurrentReferenceMensuel(Calendar instance, boolean b);

	public String getCurrentReferenceMensuelByType(String type, Calendar instance, boolean b);

	public List<BonReceptionVue> getListBonReceptionRefByFournisseurDeclarer(Long idFournisseur);

	public ListProduitElementValue getProduitElementListWithoutRegroupement(List<String> refBonReceptionList,
			Long factureAchatId);

	public ReceptionAchatValue getByReference(String reference);
}
