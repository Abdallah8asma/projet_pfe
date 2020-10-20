package com.gpro.consulting.tiers.logistique.domaine.gc.bonlivraison;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.TaxeValue;

/**
 * Taxe Domaine interface
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
public interface ITaxeDomaine {
	
	public String create(TaxeValue taxeValue);

	public TaxeValue getById(Long id);

	public String update(TaxeValue taxeValue);

	public void delete(Long id);

	public List<TaxeValue> getAll();
	public List<TaxeValue> getTVA();

}
