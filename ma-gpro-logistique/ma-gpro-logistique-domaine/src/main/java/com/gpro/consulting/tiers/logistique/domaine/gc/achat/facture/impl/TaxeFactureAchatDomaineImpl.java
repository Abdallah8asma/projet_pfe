package com.gpro.consulting.tiers.logistique.domaine.gc.achat.facture.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.TaxeFactureAchatValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.achat.facture.ITaxeFactureAchatDomaine;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.facture.ITaxeFactureAchatPersistance;

/**
 * Implementation of {@link ITaxeFactureAchatDomaine}
 * 
 * @author Hamdi etteieb
 * @since 24/09/2018
 *
 */

@Component
public class TaxeFactureAchatDomaineImpl implements ITaxeFactureAchatDomaine {

	@Autowired
	private ITaxeFactureAchatPersistance taxeFacturePersistance;

	@Override
	public String create(TaxeFactureAchatValue taxeFactureValue) {

		return taxeFacturePersistance.create(taxeFactureValue);
	}

	@Override
	public TaxeFactureAchatValue getById(Long id) {

		return taxeFacturePersistance.getById(id);
	}

	@Override
	public String update(TaxeFactureAchatValue taxeFactureValue) {

		return taxeFacturePersistance.update(taxeFactureValue);
	}

	@Override
	public void delete(Long id) {

		taxeFacturePersistance.delete(id);
	}

}
