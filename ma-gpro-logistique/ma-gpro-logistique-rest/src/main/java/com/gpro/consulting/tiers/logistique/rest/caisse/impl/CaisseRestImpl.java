package com.gpro.consulting.tiers.logistique.rest.caisse.impl;

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

import com.gpro.consulting.tiers.logistique.coordination.caisse.value.CaisseValue;
import com.gpro.consulting.tiers.logistique.coordination.caisse.value.RechercheMulticritereCaisseValue;
import com.gpro.consulting.tiers.logistique.coordination.caisse.value.ResultatRechercheCaisseValue;
import com.gpro.consulting.tiers.logistique.service.caisse.ICaisseService;

// TODO: Auto-generated Javadoc
/**
 * The Class CaisseRestImpl.
 * 
 * @author Hamdi Etteieb
 * @since 16/08/2018
 *
 */
@Controller
@RequestMapping("/caisse")
public class CaisseRestImpl {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(CaisseRestImpl.class);

	/** The caisse service. */
	@Autowired
	ICaisseService caisseService;

	/**
	 * Creates the.
	 *
	 * @param CaisseValue
	 *            the caisse value
	 * @return the string
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody String create(@RequestBody CaisseValue CaisseValue) {

		//logger.info("create: Delegating request to Service layer.");

		String id = this.caisseService.create(CaisseValue);

		return id;
	}

	/**
	 * Gets the by id.
	 *
	 * @param id
	 *            the id
	 * @return the by id
	 */
	@RequestMapping(value = "/getById:{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody CaisseValue getById(@PathVariable Long id) {

		//logger.info("getById: Delegating request id {} to service layer.", id);

		return caisseService.getById(id);
	}

	/**
	 * Rechercher multi critere bon sortie fini.
	 *
	 * @param request
	 *            the request
	 * @return the resultat recherche caisse value
	 */
	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResultatRechercheCaisseValue rechercherMultiCritere(
			@RequestBody RechercheMulticritereCaisseValue request) {

		//logger.info("rechercheMulticritere: Delegating request {} to service layer.", request);

		ResultatRechercheCaisseValue vResultatRecherche = caisseService.rechercherMultiCritere(request);

		return vResultatRecherche;

	}

	/**
	 * Update.
	 *
	 * @param CaisseValue
	 *            the caisse value
	 * @return the string
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody String update(@RequestBody CaisseValue CaisseValue) {

		return this.caisseService.update(CaisseValue);
	}

	/**
	 * Delete bon sortie fini.
	 *
	 * @param id
	 *            the id
	 */
	@RequestMapping(value = "/delete:{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") String id) {

		caisseService.delete(Long.valueOf(id));
	}

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public @ResponseBody List<CaisseValue> getAll() {

		return caisseService.getAll();
	}

	/**
	 * Gets the caisse.
	 *
	 * @return the caisse
	 */
	@RequestMapping(value = "/getCaisses", method = RequestMethod.GET)
	public @ResponseBody List<CaisseValue> getCaisse() {

		return caisseService.getCAissesNonCloture();
	}

}
