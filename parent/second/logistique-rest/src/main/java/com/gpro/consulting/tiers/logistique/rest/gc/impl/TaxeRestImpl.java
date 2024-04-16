package com.gpro.consulting.tiers.logistique.rest.gc.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.TaxeValue;
import com.gpro.consulting.tiers.logistique.service.gc.bonlivraison.ITaxeService;

/**
 * Taxe Controller
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */

@Controller
@RequestMapping("/taxe")
public class TaxeRestImpl {

	private static final Logger logger = LoggerFactory.getLogger(TaxeRestImpl.class);

	@Autowired
	private ITaxeService taxeService;

	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<TaxeValue> getAll() {

		//logger.info("getAll: Delegating request to service layer.");

		return taxeService.getAll();
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody String create(@RequestBody TaxeValue taxeValue) {

		//logger.info("create: Delegating request to Service layer.");

		return this.taxeService.create(taxeValue);
	}

	@RequestMapping(value = "/getById:{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody TaxeValue getById(@PathVariable Long id) {

		//logger.info("getById: Delegating request id {} to service layer.", id);

		return taxeService.getById(id);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody String update(@RequestBody TaxeValue taxeValue) {

		//logger.info("Update: Delegating request to service layer.");

		return this.taxeService.update(taxeValue);
	}

	@RequestMapping(value = "/delete:{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {

		//logger.info("delete: Delegating request id {} to service layer.", id);

		taxeService.delete(id);
	}

	@RequestMapping(value = "/getTVA", method = RequestMethod.GET)
	public @ResponseBody List<TaxeValue> getTVA() {

		//logger.info("getById: Delegating request id {} to service layer.");

		return taxeService.getTVA();
	}

}
