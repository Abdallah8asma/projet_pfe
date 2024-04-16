package com.gpro.consulting.tiers.logistique.rest.gc.vente.facture.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.TaxeFactureValue;
import com.gpro.consulting.tiers.logistique.service.gc.vente.facture.ITaxeFactureService;

/**
 * Taxe Facture Controller
 * 
 * @author Wahid Gazzah
 * @since 29/02/2016
 *
 */

@Controller
@RequestMapping("/TaxeFacture")
public class TaxeFactureRestImpl {
	
	private static final Logger logger = LoggerFactory.getLogger(TaxeFactureRestImpl.class);
	
	@Autowired
	private ITaxeFactureService taxeFactureService;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody String create(@RequestBody TaxeFactureValue TaxeFactureValue) {
		
		//logger.info("create: Delegating request to Service layer.");
		
		return this.taxeFactureService.create(TaxeFactureValue);
	}
	
	@RequestMapping(value = "/getById:{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody TaxeFactureValue getById(@PathVariable Long id) {
		  
		//logger.info("getById: Delegating request id {} to service layer.", id);
		  
		return taxeFactureService.getById(id);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody String update(@RequestBody TaxeFactureValue TaxeFactureValue) {
	    
		//logger.info("Update: Delegating request to service layer.");
		
		return this.taxeFactureService.update(TaxeFactureValue);
	}
	  
	@RequestMapping(value = "/delete:{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		  
		//logger.info("delete: Delegating request id {} to service layer.", id);
		  
		taxeFactureService.delete(id);
	}

}
