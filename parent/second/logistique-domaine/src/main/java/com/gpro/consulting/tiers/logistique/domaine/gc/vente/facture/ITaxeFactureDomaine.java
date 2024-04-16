package com.gpro.consulting.tiers.logistique.domaine.gc.vente.facture;

import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.TaxeFactureValue;

/**
 * TaxeFacture Domaine interface
 * 
 * @author Wahid Gazzah
 * @since 29/02/2016
 *
 */
public interface ITaxeFactureDomaine {
	
	public String create(TaxeFactureValue taxeFactureValue);

	public TaxeFactureValue getById(Long id);

	public String update(TaxeFactureValue taxeFactureValue);

	public void delete(Long id);

}
