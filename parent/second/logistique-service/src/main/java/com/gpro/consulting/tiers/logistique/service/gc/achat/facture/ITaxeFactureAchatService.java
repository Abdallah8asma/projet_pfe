package com.gpro.consulting.tiers.logistique.service.gc.achat.facture;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.TaxeFactureAchatValue;

/**
 * TaxeFacture Service Interface
 * 
 * @author Hamdi Etteieb
 * @since 24/09/2018
 *
 */
public interface ITaxeFactureAchatService {

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String create(TaxeFactureAchatValue TaxeFactureAchatValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public TaxeFactureAchatValue getById(Long id);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String update(TaxeFactureAchatValue TaxeFactureAchatValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void delete(Long id);

}
