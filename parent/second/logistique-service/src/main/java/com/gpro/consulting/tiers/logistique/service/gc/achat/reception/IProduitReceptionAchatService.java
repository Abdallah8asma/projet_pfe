package com.gpro.consulting.tiers.logistique.service.gc.achat.reception;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ProduitReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.RechercheMulticritereProduitReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ResultatRechecheProduitReceptionAchatValue;

/**
 * The Interface IProduitReceptionAchatService.
 * 
 * @author Hamdi etteieb
 * @since 23/09/2018
 */
public interface IProduitReceptionAchatService {

	/**
	 * Creates the.
	 *
	 * @param ProduitReceptionAchatValue
	 *            the produit reception achat value
	 * @return the string
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String create(ProduitReceptionAchatValue ProduitReceptionAchatValue);

	/**
	 * Gets the by id.
	 *
	 * @param id
	 *            the id
	 * @return the by id
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ProduitReceptionAchatValue getById(Long id);

	/**
	 * Update.
	 *
	 * @param ProduitReceptionAchatValue
	 *            the produit reception achat value
	 * @return the string
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String update(ProduitReceptionAchatValue ProduitReceptionAchatValue);

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
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<ProduitReceptionAchatValue> getAll();

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ResultatRechecheProduitReceptionAchatValue rechercherMultiCritere(
			RechercheMulticritereProduitReceptionAchatValue request);
}
