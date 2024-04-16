package com.gpro.consulting.tiers.logistique.rest.gc.impl;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.logistique.coordination.gc.guichet.value.GuichetAnnuelValue;
import com.gpro.consulting.tiers.logistique.service.gc.guichet.IGuichetAnnuelService;

/**
 * @author Wahid Gazzah
 * @since 19/02/2016
 * 
 */

@Controller
@RequestMapping("/guichet")
public class GuichetRestImpl {

	private static final Logger logger = LoggerFactory.getLogger(GuichetRestImpl.class);

	@Autowired
	private IGuichetAnnuelService guichetAnnuelService;

	/*
	 * @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces =
	 * "application/json") public @ResponseBody List<ModePaiementValue> getAll()
	 * {
	 * 
	 * logger.info("getAll: Delegating request to service layer.");
	 * 
	 * return modePaiementService.getAll(); }
	 * 
	 * @RequestMapping(value = "/create", method = RequestMethod.POST)
	 * public @ResponseBody String create(@RequestBody ModePaiementValue
	 * modePaiementValue) {
	 * 
	 * logger.info("create: Delegating request to Service layer.");
	 * 
	 * return modePaiementService.create(modePaiementValue); }
	 */

	@RequestMapping(value = "/getById:{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody GuichetAnnuelValue getById(@PathVariable Long id) {

		//logger.info("getById: Delegating request id {} to service layer.", id);
		//System.out.println("==> Rest :getById: " + id);

		return guichetAnnuelService.getById(id);
	}
	@RequestMapping(value = "/getAnneCourant", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody GuichetAnnuelValue getAnneCourant() {

	

		Long idAnneCourant = (long)Calendar.getInstance().get(Calendar.YEAR)- 2016l+1l;
		
		
		return guichetAnnuelService.getById(idAnneCourant);
	}

	@RequestMapping(value = "/updateGuichet", method = RequestMethod.POST)
	public @ResponseBody String updateGuichet(@RequestBody GuichetAnnuelValue guichetAnnuelValue) {

		//logger.info("UpdateGuichet: Delegating request to service layer.");
		//System.out.println("inn rest update guichet..");

		return this.guichetAnnuelService.updateGuichetAnnuel(guichetAnnuelValue);
	}

	/*
	 * @RequestMapping(value = "/update", method = RequestMethod.POST)
	 * public @ResponseBody String update(@RequestBody ModePaiementValue
	 * modePaiementValue) {
	 * 
	 * logger.info("Update: Delegating request to service layer.");
	 * 
	 * return modePaiementService.update(modePaiementValue); }
	 */

	/*
	 * @RequestMapping(value = "/delete:{id}", method = RequestMethod.DELETE)
	 * public void delete(@PathVariable Long id) {
	 * 
	 * logger.info("delete: Delegating request id {} to service layer.", id);
	 * 
	 * modePaiementService.delete(id); }
	 */

}
