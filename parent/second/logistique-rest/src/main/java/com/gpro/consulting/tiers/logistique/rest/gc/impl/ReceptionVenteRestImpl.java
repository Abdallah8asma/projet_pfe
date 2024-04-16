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
import com.gpro.consulting.tiers.logistique.coordination.gc.reception.value.ReceptionVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reception.value.RechercheMulticritereBonReceptionValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reception.value.ResultatRechecheBonReceptionValue;
import com.gpro.consulting.tiers.logistique.service.atelier.cache.IGestionnaireLogistiqueCacheService;
import com.gpro.consulting.tiers.logistique.service.gc.reception.IReceptionService;

/**
 * Bon de livraison Controller
 * 
 * 
 */

@Controller
@RequestMapping("/receptionVente")
public class ReceptionVenteRestImpl {

	private static final Logger logger = LoggerFactory.getLogger(ReceptionVenteRestImpl.class);

	@Autowired
	private IReceptionService receptionService;

	@Autowired
	private IGestionnaireLogistiqueCacheService gestionnaireLogistiqueCacheService;

	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResultatRechecheBonReceptionValue rechercherMultiCritere(
			@RequestBody RechercheMulticritereBonReceptionValue request) {

		//logger.info("rechercheMulticritere: Delegating request {} to service layer.", request);

		ResultatRechecheBonReceptionValue result = receptionService.rechercherMultiCritere(request);

		if (result != null) {

			Map<Long, PartieInteresseValue> mapClientById = gestionnaireLogistiqueCacheService.mapClientById();

			for (ReceptionVenteValue bonReceptionValue : result.getListReceptionVente()) {
				if (bonReceptionValue != null) {

					PartieInteresseValue client = mapClientById.get(bonReceptionValue.getPartieIntersseId());

					if (client != null) {
						bonReceptionValue.setPartieIntersseAbbreviation(client.getAbreviation());
					}
				}
			}
		}

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody String createBonLivraison(@RequestBody ReceptionVenteValue ReceptionVenteValue) {

		//logger.info("commandeVente - create: Delegating request to Service layer.");

		return this.receptionService.create(ReceptionVenteValue);
	}

	@RequestMapping(value = "/getById:{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ReceptionVenteValue getBonLivraisonById(@PathVariable Long id) {

		//logger.info("commandeVente - getById: Delegating request id {} to service layer.", id);

		ReceptionVenteValue ReceptionVenteValue = receptionService.getById(id);

		if (ReceptionVenteValue != null) {
			Map<Long, PartieInteresseValue> mapClientById = gestionnaireLogistiqueCacheService.mapClientById();

			PartieInteresseValue client = mapClientById.get(ReceptionVenteValue.getPartieIntersseId());
			if (client != null) {
				ReceptionVenteValue.setPartieIntersseAbbreviation(client.getAbreviation());
			}
		}

		return ReceptionVenteValue;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody String update(@RequestBody ReceptionVenteValue bonReceptionValue) {

		return this.receptionService.update(bonReceptionValue);
	}

	@RequestMapping(value = "/delete:{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {

		receptionService.delete(id);
	}

	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<ReceptionVenteValue> getAll() {

		//logger.info("getAll");

		return receptionService.getAll();
	}

}
