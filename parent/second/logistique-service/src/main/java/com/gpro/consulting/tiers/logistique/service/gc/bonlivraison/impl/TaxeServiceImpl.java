package com.gpro.consulting.tiers.logistique.service.gc.bonlivraison.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.TaxeValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.bonlivraison.ITaxeDomaine;
import com.gpro.consulting.tiers.logistique.service.gc.bonlivraison.ITaxeService;

/**
 * implementation of {@link ITaxeService}
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
@Service
@Transactional
public class TaxeServiceImpl implements ITaxeService{
	
	private static final Logger logger = LoggerFactory.getLogger(TaxeServiceImpl.class);

	@Autowired
	private ITaxeDomaine taxeDomaine;
	
	@Override
	public String create(TaxeValue taxeValue) {
		
		return taxeDomaine.create(taxeValue);
	}

	@Override
	public TaxeValue getById(Long id) {
		
		return taxeDomaine.getById(id);
	}

	@Override
	public String update(TaxeValue taxeValue) {
		
		return taxeDomaine.update(taxeValue);
	}

	@Override
	public void delete(Long id) {
		
		taxeDomaine.delete(id);
	}

	@Override
	public List<TaxeValue> getAll() {
		
		return taxeDomaine.getAll();
	}

	@Override
	public List<TaxeValue> getTVA() {
		
		return taxeDomaine.getTVA();
	}

}
