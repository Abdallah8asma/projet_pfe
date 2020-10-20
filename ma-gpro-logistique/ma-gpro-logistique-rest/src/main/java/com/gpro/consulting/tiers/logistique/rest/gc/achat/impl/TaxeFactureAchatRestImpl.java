package com.gpro.consulting.tiers.logistique.rest.gc.achat.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.TaxeFactureAchatValue;
import com.gpro.consulting.tiers.logistique.service.gc.achat.facture.ITaxeFactureAchatService;

/**
 * Taxe Facture Controller
 * 
 * @author Wahid Gazzah
 * @since 29/02/2016
 *
 */

@Controller
@RequestMapping("/TaxeFactureAchat")
public class TaxeFactureAchatRestImpl {

	private static final Logger logger = LoggerFactory.getLogger(TaxeFactureAchatRestImpl.class);

	@Autowired
	private ITaxeFactureAchatService taxeFactureService;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody String create(@RequestBody TaxeFactureAchatValue TaxeFactureAchatValue) {

		//logger.info("create: Delegating request to Service layer.");

		return this.taxeFactureService.create(TaxeFactureAchatValue);
	}

	@RequestMapping(value = "/getById:{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody TaxeFactureAchatValue getById(@PathVariable Long id) {

		//logger.info("getById: Delegating request id {} to service layer.", id);

		return taxeFactureService.getById(id);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody String update(@RequestBody TaxeFactureAchatValue TaxeFactureAchatValue) {

		//logger.info("Update: Delegating request to service layer.");

		return this.taxeFactureService.update(TaxeFactureAchatValue);
	}

	@RequestMapping(value = "/delete:{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {

		//logger.info("delete: Delegating request id {} to service layer.", id);

		taxeFactureService.delete(id);
	}

}
