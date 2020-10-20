/**
 * 
 */
package com.gpro.consulting.tiers.logistique.rest.atelier.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.ChoixRouleauValue;
import com.gpro.consulting.tiers.logistique.service.atelier.rouleaufini.IChoixRouleauService;

/**
 * @author Ameni
 *
 */
@Controller
@RequestMapping("/choixRouleau")
public class ChoixRouleauRestImpl {

private static final Logger logger = LoggerFactory.getLogger(ChoixRouleauRestImpl.class);
	
	@Autowired
	IChoixRouleauService choixRouleauService;

	
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<ChoixRouleauValue> viewAllChoix() {
		//logger.info("ListeChoixRouleau: Delegating request {} to service layer.");
		
		return choixRouleauService.listeChoixRouleau();
	}
}
