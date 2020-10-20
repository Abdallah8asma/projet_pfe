package com.gpro.consulting.tiers.logistique.service.gc.bonlivraison;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.TaxeValue;

/**
 * Taxe Service Interface
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
public interface ITaxeService {
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String create(TaxeValue taxeValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public TaxeValue getById(Long id);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String update(TaxeValue taxeValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void delete(Long id);
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public List<TaxeValue> getAll();

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public List<TaxeValue> getTVA();

}
