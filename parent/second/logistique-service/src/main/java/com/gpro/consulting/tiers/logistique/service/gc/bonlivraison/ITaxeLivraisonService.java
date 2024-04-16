package com.gpro.consulting.tiers.logistique.service.gc.bonlivraison;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.TaxeLivraisonValue;

/**
 * TaxeLivraison Service Interface
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
public interface ITaxeLivraisonService {
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String create(TaxeLivraisonValue taxeLivraisonValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public TaxeLivraisonValue getById(Long id);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String update(TaxeLivraisonValue taxeLivraisonValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void delete(Long id);

}
