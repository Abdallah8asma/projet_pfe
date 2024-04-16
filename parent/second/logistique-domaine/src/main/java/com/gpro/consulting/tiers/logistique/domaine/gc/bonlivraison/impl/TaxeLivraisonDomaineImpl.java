package com.gpro.consulting.tiers.logistique.domaine.gc.bonlivraison.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.TaxeLivraisonValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.bonlivraison.ITaxeLivraisonDomaine;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.ITaxeLivraisonPersistance;


/**
 * Implementation of {@link ITaxeLivraisonDomaine}
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */

@Component
public class TaxeLivraisonDomaineImpl implements ITaxeLivraisonDomaine{
	
	@Autowired
	private ITaxeLivraisonPersistance taxeLivraisonPersistance;


	@Override
	public String create(TaxeLivraisonValue taxeLivraisonValue) {
		
		return taxeLivraisonPersistance.create(taxeLivraisonValue);
	}

	@Override
	public TaxeLivraisonValue getById(Long id) {
		
		return taxeLivraisonPersistance.getById(id);
	}

	@Override
	public String update(TaxeLivraisonValue taxeLivraisonValue) {
		
		return taxeLivraisonPersistance.update(taxeLivraisonValue);
	}

	@Override
	public void delete(Long id) {
		
		taxeLivraisonPersistance.delete(id);
	}

}
