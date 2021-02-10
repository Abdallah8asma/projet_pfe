package com.gpro.consulting.tiers.logistique.service.gc.achat.reception;

import java.util.Calendar;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.BonReceptionVue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ReceptionAchatFactureVue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.RechercheMulticritereBonReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ResultatRechecheBonReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ListProduitElementValue;

/**
 * BonCommande Service interface.
 *
 * @author Hamdi Etteieb
 * @since 23/09/2018
 */
public interface IReceptionAchatService {

	/**
	 * Creates the.
	 *
	 * @param bonReceptionValue
	 *            the bon reception value
	 * @return the string
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String create(ReceptionAchatValue bonReceptionValue);

	/**
	 * Gets the by id.
	 *
	 * @param id
	 *            the id
	 * @return the by id
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ReceptionAchatValue getById(Long id);

	/**
	 * Update.
	 *
	 * @param bonReceptionValue
	 *            the bon reception value
	 * @return the string
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String update(ReceptionAchatValue bonReceptionValue);

	/**
	 * Delete.
	 *
	 * @param id
	 *            the id
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String delete(Long id);

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<ReceptionAchatValue> getAll();

	/**
	 * Rechercher multi critere.
	 *
	 * @param request
	 *            the request
	 * @return the resultat recheche bon reception achat value
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ResultatRechecheBonReceptionAchatValue rechercherMultiCritere(
			RechercheMulticritereBonReceptionAchatValue request);
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<BonReceptionVue> getListBonReceptionRefByFournisseur(Long idFournisseur);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<ReceptionAchatFactureVue> getAllListBonReceptionRefByFournisseur(Long idFournisseur);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ListProduitElementValue getProduitElementList(List<String> refBonReceptionList, Long factureAchatId);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public String getCurrentReference(Calendar instance, boolean b);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ReceptionAchatValue validerFactureAvoirRetour(String reference);
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ReceptionAchatValue validerBL(String reference);
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public String getCurrentReferenceMensuel(Calendar instance, boolean b);

	@Transactional(readOnly = true, rollbackFor = Exception.class)

	public String getCurrentReferenceMensuelByType(String type, Calendar instance, boolean b);
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<BonReceptionVue> getListBonReceptionRefByFournisseurDeclarer(Long idFournisseur);
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ListProduitElementValue getProduitElementListWithoutRegroupement(List<String> refBonReceptionList,
			Long factureAchatId);
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ReceptionAchatValue getByReference(String reference);
}
