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

import com.gpro.consulting.logistique.coordination.gc.guichet.value.GuichetMensuelValue;
import com.gpro.consulting.logistique.coordination.gc.guichet.value.RechercheMulticritereGuichetMensuelValue;
import com.gpro.consulting.tiers.logistique.service.gc.guichet.IGuichetMensuelService;

/**
 * @author Wahid Gazzah
 * @since 19/02/2016
 * 
 */

@Controller
@RequestMapping("/guichet-mensuel")
public class GuichetMensuelRestImpl {

	private static final Logger logger = LoggerFactory.getLogger(GuichetMensuelRestImpl.class);

	@Autowired
	private IGuichetMensuelService guichetMensuelService;

	

	@RequestMapping(value = "/getById:{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody GuichetMensuelValue getById(@PathVariable Long id) {

		//logger.info("getById: Delegating request id {} to service layer.", id);
		//System.out.println("==> Rest :getById: " + id);

		return guichetMensuelService.getById(id);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody String create(@RequestBody GuichetMensuelValue guichetMensuelValue) {

		//logger.info("UpdateGuichet: Delegating request to service layer.");
		//System.out.println("inn rest update guichet..");

		return this.guichetMensuelService.create(guichetMensuelValue);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public @ResponseBody String update(@RequestBody GuichetMensuelValue guichetMensuelValue) {

		//logger.info("UpdateGuichet: Delegating request to service layer.");
		//System.out.println("inn rest update guichet..");

		return this.guichetMensuelService.update(guichetMensuelValue);
	}
	
	@RequestMapping(value = "/deleteById:{id}", method = RequestMethod.DELETE, produces = "application/json")
	public @ResponseBody String deleteById(@PathVariable Long id) {

		//logger.info("getById: Delegating request id {} to service layer.", id);
		//System.out.println("==> Rest :getById: " + id);

		return guichetMensuelService.deleteById(id);
	}
	

	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST)
	public @ResponseBody List<GuichetMensuelValue> rechercheMulticritere(@RequestBody RechercheMulticritereGuichetMensuelValue request) {

	
		return this.guichetMensuelService.rechercheMultiCritere(request);
	}

}
