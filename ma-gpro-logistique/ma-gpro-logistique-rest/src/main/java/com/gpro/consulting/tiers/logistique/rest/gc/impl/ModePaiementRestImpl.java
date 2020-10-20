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

import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.ModePaiementValue;
import com.gpro.consulting.tiers.logistique.service.gc.bonlivraison.IModePaiementService;

/**
 * @author Wahid Gazzah
 * @since 19/02/2016
 * 
 */

@Controller
@RequestMapping("/modePaiement")
public class ModePaiementRestImpl {

	private static final Logger logger = LoggerFactory.getLogger(ModePaiementRestImpl.class);

	@Autowired
	private IModePaiementService modePaiementService;

	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<ModePaiementValue> getAll() {

		//logger.info("getAll: Delegating request to service layer.");

		return modePaiementService.getAll();
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody String create(@RequestBody ModePaiementValue modePaiementValue) {

		//logger.info("create: Delegating request to Service layer.");

		return modePaiementService.create(modePaiementValue);
	}

	@RequestMapping(value = "/getById:{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ModePaiementValue getById(@PathVariable Long id) {

		//logger.info("getById: Delegating request id {} to service layer.", id);

		return modePaiementService.getById(id);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody String update(@RequestBody ModePaiementValue modePaiementValue) {

		//logger.info("Update: Delegating request to service layer.");

		return modePaiementService.update(modePaiementValue);
	}

	@RequestMapping(value = "/delete:{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {

		//logger.info("delete: Delegating request id {} to service layer.", id);

		modePaiementService.delete(id);
	}

}
