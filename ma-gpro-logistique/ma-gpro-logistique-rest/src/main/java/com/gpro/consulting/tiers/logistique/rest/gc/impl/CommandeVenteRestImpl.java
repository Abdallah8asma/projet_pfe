package com.gpro.consulting.tiers.logistique.rest.gc.impl;

import java.util.Calendar;
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
import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.CommandeVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.RechercheMulticritereBonCommandeValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.ResultatRechecheBonCommandeValue;
import com.gpro.consulting.tiers.logistique.service.atelier.cache.IGestionnaireLogistiqueCacheService;
import com.gpro.consulting.tiers.logistique.service.gc.bonCommande.IBonCommandeService;

/**
 * Bon de livraison Controller
 * 
 * @author Zeineb Medimagh
 * @since 16/11/2016
 *
 */

@Controller
@RequestMapping("/commandeVente")
public class CommandeVenteRestImpl {

	private static final Logger logger = LoggerFactory.getLogger(CommandeVenteRestImpl.class);

	@Autowired
	private IBonCommandeService bonCommandeService;

	@Autowired
	private IGestionnaireLogistiqueCacheService gestionnaireLogistiqueCacheService;

	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResultatRechecheBonCommandeValue rechercherMultiCritere(
			@RequestBody RechercheMulticritereBonCommandeValue request) {

		//logger.info("rechercheMulticritere: Delegating request {} to service layer.", request);

		ResultatRechecheBonCommandeValue result = bonCommandeService.rechercherMultiCritere(request);

		if (result != null) {

			Map<Long, PartieInteresseValue> mapClientById = gestionnaireLogistiqueCacheService.mapClientById();

			for (CommandeVenteValue bonCommandeValue : result.getListCommandeVente()) {
				if (bonCommandeValue != null) {

					PartieInteresseValue client = mapClientById.get(bonCommandeValue.getPartieIntersseId());

					if (client != null) {
						bonCommandeValue.setPartieIntersseAbbreviation(client.getAbreviation());
					}
				}
			}
		}

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody String createBonLivraison(@RequestBody CommandeVenteValue commandeVenteValue) {

		//logger.info("commandeVente - create: Delegating request to Service layer.");
		//System.out.println(" rest:liste taxe" + commandeVenteValue.getListProduitTaxes().size());

		return this.bonCommandeService.create(commandeVenteValue);
	}

	@RequestMapping(value = "/getById:{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody CommandeVenteValue getBonLivraisonById(@PathVariable Long id) {

		//logger.info("commandeVente - getById: Delegating request id {} to service layer.", id);

		CommandeVenteValue commandeVenteValue = bonCommandeService.getById(id);

		if (commandeVenteValue != null) {
			Map<Long, PartieInteresseValue> mapClientById = gestionnaireLogistiqueCacheService.mapClientById();

			PartieInteresseValue client = mapClientById.get(commandeVenteValue.getPartieIntersseId());
			if (client != null) {
				commandeVenteValue.setPartieIntersseAbbreviation(client.getAbreviation());
			}
		}

		return commandeVenteValue;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody String update(@RequestBody CommandeVenteValue bonCommandeValue) {

		return this.bonCommandeService.update(bonCommandeValue);
	}

	@RequestMapping(value = "/delete:{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {

		bonCommandeService.delete(id);
	}

	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<CommandeVenteValue> getAll() {

		//logger.info("getAll");

		return bonCommandeService.getAll();
	}

	@RequestMapping(value = "/getAvailableListBonCommandeRefByClient:{idClient}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<CommandeVenteValue> getListBonCommandeRefByClient(@PathVariable Long idClient) {

		//logger.info("getAvailableListBonCommandeRefByClient");
		return bonCommandeService.getListBonCommandeRefByClient(idClient);
	}
	

	
	@RequestMapping(value = "/getCurrentReferenceByType:{type}", method = RequestMethod.GET, produces =  "application/json")
 	public @ResponseBody String getCurrentReferenceByType(@PathVariable String type) {
 		
 		return  bonCommandeService.getCurrentReferenceByType(type,Calendar.getInstance(),false);
 	}

}
