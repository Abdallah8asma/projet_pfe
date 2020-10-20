package com.gpro.consulting.tiers.logistique.rest.gc.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.TaxeLivraisonValue;
import com.gpro.consulting.tiers.logistique.service.gc.bonlivraison.ITaxeLivraisonService;

/**
 * TaxeLivraison Controller
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */

@Controller
@RequestMapping("/taxelivraison")
public class TaxeLivraisonRestImpl {

	private static final Logger logger = LoggerFactory.getLogger(TaxeLivraisonRestImpl.class);

	@Autowired
	private ITaxeLivraisonService taxeLivraisonService;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody String create(@RequestBody TaxeLivraisonValue taxeLivraisonValue) {

		//logger.info("create: Delegating request to Service layer.");

		return this.taxeLivraisonService.create(taxeLivraisonValue);
	}

	@RequestMapping(value = "/getById:{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody TaxeLivraisonValue getById(@PathVariable Long id) {

		//logger.info("getById: Delegating request id {} to service layer.", id);

		return taxeLivraisonService.getById(id);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody String update(@RequestBody TaxeLivraisonValue taxeLivraisonValue) {

		//logger.info("Update: Delegating request to service layer.");

		return this.taxeLivraisonService.update(taxeLivraisonValue);
	}

	@RequestMapping(value = "/delete:{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {

		//logger.info("delete: Delegating request id {} to service layer.", id);

		taxeLivraisonService.delete(id);
	}

}
