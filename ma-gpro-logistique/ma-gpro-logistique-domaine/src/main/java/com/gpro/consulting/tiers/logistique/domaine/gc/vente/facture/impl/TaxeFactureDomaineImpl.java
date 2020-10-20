package com.gpro.consulting.tiers.logistique.domaine.gc.vente.facture.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.TaxeFactureValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.vente.facture.ITaxeFactureDomaine;
import com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture.ITaxeFacturePersistance;


/**
 * Implementation of {@link ITaxeFactureDomaine}
 * 
 * @author Wahid Gazzah
 * @since 29/02/2016
 *
 */

@Component
public class TaxeFactureDomaineImpl implements ITaxeFactureDomaine{
	
	@Autowired
	private ITaxeFacturePersistance taxeFacturePersistance;


	@Override
	public String create(TaxeFactureValue taxeFactureValue) {
		
		return taxeFacturePersistance.create(taxeFactureValue);
	}

	@Override
	public TaxeFactureValue getById(Long id) {
		
		return taxeFacturePersistance.getById(id);
	}

	@Override
	public String update(TaxeFactureValue taxeFactureValue) {
		
		return taxeFacturePersistance.update(taxeFactureValue);
	}

	@Override
	public void delete(Long id) {
		
		taxeFacturePersistance.delete(id);
	}

}
