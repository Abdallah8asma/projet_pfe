package com.gpro.consulting.tiers.logistique.service.gc.vente.facture;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.TaxeFactureValue;

/**
 * TaxeFacture Service Interface
 * 
 * @author Wahid Gazzah
 * @since 29/02/2016
 *
 */
public interface ITaxeFactureService {
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String create(TaxeFactureValue taxeFactureValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public TaxeFactureValue getById(Long id);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String update(TaxeFactureValue taxeFactureValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void delete(Long id);

}
