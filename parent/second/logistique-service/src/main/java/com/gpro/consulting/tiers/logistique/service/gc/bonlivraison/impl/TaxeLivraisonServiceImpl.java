package com.gpro.consulting.tiers.logistique.service.gc.bonlivraison.impl;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.TaxeLivraisonValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.bonlivraison.ITaxeLivraisonDomaine;
import com.gpro.consulting.tiers.logistique.service.gc.bonlivraison.ITaxeLivraisonService;

/**
 * implementation of {@link ITaxeLivraisonService}
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
@Service
@Transactional
public class TaxeLivraisonServiceImpl implements ITaxeLivraisonService{
	
	private static final Logger logger = LoggerFactory.getLogger(TaxeLivraisonServiceImpl.class);

	@Autowired
	private ITaxeLivraisonDomaine taxeLivraisonDomaine;
	
	@Override
	public String create(TaxeLivraisonValue taxeLivraisonValue) {
		
		return taxeLivraisonDomaine.create(taxeLivraisonValue);
	}

	@Override
	public TaxeLivraisonValue getById(Long id) {
		
		return taxeLivraisonDomaine.getById(id);
	}

	@Override
	public String update(TaxeLivraisonValue taxeLivraisonValue) {
		
		return taxeLivraisonDomaine.update(taxeLivraisonValue);
	}

	@Override
	public void delete(Long id) {
		
		taxeLivraisonDomaine.delete(id);
	}

}
