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

import com.gpro.consulting.tiers.logistique.coordination.caisse.value.MouvementCaisseValue;
import com.gpro.consulting.tiers.logistique.coordination.caisse.value.RechercheMulticritereMouvementCaisseValue;
import com.gpro.consulting.tiers.logistique.coordination.caisse.value.ResultatRechercheMouvementCaisseValue;
import com.gpro.consulting.tiers.logistique.service.caisse.IMouvementCaisseService;

/**
 * Mouvement Caisse Controller
 * 
 * @author Hamdi Etteieb
 * @since 16/08/2018
 *
 */
@Controller
@RequestMapping("/mvtCaisse")
public class MouvementCaisseRestImpl {

	private static final Logger logger = LoggerFactory.getLogger(MouvementCaisseRestImpl.class);

	@Autowired
	IMouvementCaisseService mouvementCaisseService;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody String create(@RequestBody MouvementCaisseValue caisseValue) {

		//logger.info("create: Delegating request to Service layer.");

		String id = this.mouvementCaisseService.create(caisseValue);

		return id;
	}

	@RequestMapping(value = "/getById:{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody MouvementCaisseValue getById(@PathVariable Long id) {

		//logger.info("getById: Delegating request id {} to service layer.", id);

		return mouvementCaisseService.getById(id);
	}

	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<MouvementCaisseValue> getAll() {

		return mouvementCaisseService.getAll();
	}

	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResultatRechercheMouvementCaisseValue rechercherMultiCritereBonSortieFini(
			@RequestBody RechercheMulticritereMouvementCaisseValue request) {

		//logger.info("rechercheMulticritere: Delegating request {} to service layer.", request);

		ResultatRechercheMouvementCaisseValue vResultatRecherche = mouvementCaisseService
				.rechercherMultiCritere(request);

		return vResultatRecherche;

	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody String update(@RequestBody MouvementCaisseValue CaisseValue) {

		return this.mouvementCaisseService.update(CaisseValue);
	}

	@RequestMapping(value = "/delete:{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") String id) {

		mouvementCaisseService.delete(Long.valueOf(id));
	}

}
