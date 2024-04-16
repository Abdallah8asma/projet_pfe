package com.gpro.consulting.tiers.logistique.service.gc.vente.facture.impl;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.TaxeFactureValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.vente.facture.ITaxeFactureDomaine;
import com.gpro.consulting.tiers.logistique.service.gc.vente.facture.ITaxeFactureService;

/**
 * implementation of {@link ITaxeFactureService}
 * 
 * @author Wahid Gazzah
 * @since 29/02/2016
 *
 */
@Service
@Transactional
public class TaxeFactureServiceImpl implements ITaxeFactureService{
	
	private static final Logger logger = LoggerFactory.getLogger(TaxeFactureServiceImpl.class);

	@Autowired
	private ITaxeFactureDomaine taxeFactureDomaine;
	
	@Override
	public String create(TaxeFactureValue taxeFactureValue) {
		
		return taxeFactureDomaine.create(taxeFactureValue);
	}

	@Override
	public TaxeFactureValue getById(Long id) {
		
		return taxeFactureDomaine.getById(id);
	}

	@Override
	public String update(TaxeFactureValue taxeFactureValue) {
		
		return taxeFactureDomaine.update(taxeFactureValue);
	}

	@Override
	public void delete(Long id) {
		
		taxeFactureDomaine.delete(id);
	}

}
