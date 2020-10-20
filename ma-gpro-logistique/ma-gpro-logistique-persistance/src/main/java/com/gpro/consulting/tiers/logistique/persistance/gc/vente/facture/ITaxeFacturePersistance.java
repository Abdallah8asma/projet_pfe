package com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.TaxeFactureValue;

/**
 * TaxeFacture Persistance interface
 * 
 * @author Wahid Gazzah
 * @since 29/02/2016
 *
 */
public interface ITaxeFacturePersistance {
	
	public String create(TaxeFactureValue taxeFactureValue);

	public TaxeFactureValue getById(Long id);

	public String update(TaxeFactureValue taxeFactureValue);

	public void delete(Long id);

	public List<TaxeFactureValue> getAllByFactureId(Long factureId);

}
