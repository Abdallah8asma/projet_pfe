package com.gpro.consulting.tiers.logistique.domaine.gc.bonlivraison.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.TaxeValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.bonlivraison.ITaxeDomaine;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.ITaxePersistance;

/**
 * Implementation of {@link ITaxeDomaine}
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */

@Component
public class TaxeDomaineImpl implements ITaxeDomaine{
	
	private static final Logger logger = LoggerFactory.getLogger(TaxeDomaineImpl.class);
	
	@Autowired
	private ITaxePersistance taxePersistance;
	
	


	@Override
	public String create(TaxeValue taxeValue) {
		
		return taxePersistance.create(taxeValue);
	}

	@Override
	public TaxeValue getById(Long id) {
		
		return taxePersistance.getById(id);
	}

	@Override
	public String update(TaxeValue taxeValue) {
		
		return taxePersistance.update(taxeValue);
	}

	@Override
	public void delete(Long id) {
		
		taxePersistance.delete(id);
	}

	@Override
	public List<TaxeValue> getAll() {
		
		return taxePersistance.getAll();
	}

	@Override
	public List<TaxeValue> getTVA() {
		
		
		return taxePersistance.getTVA();
	}

}
