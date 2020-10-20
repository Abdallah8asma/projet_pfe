package com.gpro.consulting.tiers.logistique.rest.gc.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.MarcheValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.RechercheMulticritereMarcheValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.ResultatRechecheMarcheValue;
import com.gpro.consulting.tiers.logistique.service.atelier.cache.IGestionnaireLogistiqueCacheService;
import com.gpro.consulting.tiers.logistique.service.gc.bonlivraison.IMarcheService;

/**
 * Marche Controller
 * 
 * @author Wahid Gazzah
 * @since 19/02/2016
 * 
 */

@Controller
@RequestMapping("/marche")
public class MarcheRestImpl {

	private static final Logger logger = LoggerFactory.getLogger(MarcheRestImpl.class);

	@Autowired
	private IMarcheService marcheService;

	@Autowired
	private IGestionnaireLogistiqueCacheService gestionnaireLogistiqueCacheService;

	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<MarcheValue> getAll() {

		// Map<Long, PartieInteresseValue> mapClientById =
		// gestionnaireLogistiqueCacheService.mapClientById();

		List<MarcheValue> list = marcheService.getAll();

		//logger.info("getAll: Delegating request to service layer.:::  " + list.size());

		// for(MarcheValue marcheValue : list){
		// if(marcheValue != null){
		// logger.info("getAll: Delegating testttttttttttttt:56666666::
		// "+marcheValue.getPartieInteresseId());
		// PartieInteresseValue client =
		// mapClientById.get(marcheValue.getPartieInteresseId());
		// logger.info("getAll: Delegating testttttttttttttt:::
		// "+client.getAbreviation());
		// if(client != null){
		// marcheValue.setPartieIntAbreviation(client.getAbreviation());
		// }
		// }
		// }

		return list;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody String create(@RequestBody MarcheValue marcheValue) {

		//logger.info("create: Delegating request to Service layer.");

		return marcheService.create(marcheValue);
	}

	@RequestMapping(value = "/getById:{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody MarcheValue getById(@PathVariable Long id) {

		//logger.info("getById: Delegating request id {} to service layer.", id);

		return marcheService.getById(id);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody String update(@RequestBody MarcheValue marcheValue) {

		//logger.info("Update: Delegating request to service layer.");

		return marcheService.update(marcheValue);
	}

	@RequestMapping(value = "/delete:{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {

		//logger.info("delete: Delegating request id {} to service layer.", id);

		marcheService.delete(id);
	}

	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResultatRechecheMarcheValue rechercherMultiCritere(
			@RequestBody RechercheMulticritereMarcheValue request) {

		//logger.info("rechercheMulticritere: Delegating request {} to service layer.", request);

		ResultatRechecheMarcheValue result = marcheService.rechercherMultiCritere(request);

		if (result != null) {

			Map<Long, PartieInteresseValue> mapClientById = gestionnaireLogistiqueCacheService.mapClientById();

			for (MarcheValue marcheValue : result.getListMarche()) {
				if (marcheValue != null) {

					PartieInteresseValue client = mapClientById.get(marcheValue.getPartieInteresseId());

					if (client != null) {
						marcheValue.setPartieIntAbreviation(client.getAbreviation());
					}
				}
			}
		}

		return result;
	}

	@RequestMapping(value = "/getListById:{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<MarcheValue> getListById(@PathVariable Long id) {

		//logger.info("getById: Delegating request id {} to service layer.", id);

		return marcheService.getListById(id);
	}

}
