package com.gpro.consulting.tiers.logistique.rest.gc.achat.impl;

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
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.boncommande.value.CommandeAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.boncommande.value.RechercheMulticritereBonCommandeAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.boncommande.value.ResultatRechecheBonCommandeAchatValue;
import com.gpro.consulting.tiers.logistique.service.atelier.cache.IGestionnaireLogistiqueCacheService;
import com.gpro.consulting.tiers.logistique.service.gc.achat.bonCommande.IBonCommandeAchatService;

/**
 * Bon de livraison Controller
 * 
 * @author Hamdi Etteieb
 * @since 11/09/2016
 *
 */

@Controller
@RequestMapping("/commandeAchat")
public class CommandeAchatRestImpl {

	private static final Logger logger = LoggerFactory.getLogger(CommandeAchatRestImpl.class);

	@Autowired
	private IBonCommandeAchatService bonCommandeService;

	@Autowired
	private IGestionnaireLogistiqueCacheService gestionnaireLogistiqueCacheService;

	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResultatRechecheBonCommandeAchatValue rechercherMultiCritere(
			@RequestBody RechercheMulticritereBonCommandeAchatValue request) {

		//logger.info("rechercheMulticritere: Delegating request {} to service layer.", request);

		ResultatRechecheBonCommandeAchatValue result = bonCommandeService.rechercherMultiCritere(request);

		if (result != null) {

			Map<Long, PartieInteresseValue> mapClientById = gestionnaireLogistiqueCacheService.mapClientById();

			for (CommandeAchatValue bonCommandeValue : result.getListCommandeAchat()) {
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
	public @ResponseBody String createBonLivraison(@RequestBody CommandeAchatValue commandeAchatValue) {

		//logger.info("commandeAchat - create: Delegating request to Service layer.");
		//System.out.println(" rest:liste taxe" + commandeAchatValue.getListProduitTaxes().size());

		return this.bonCommandeService.create(commandeAchatValue);
	}

	@RequestMapping(value = "/getById:{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody CommandeAchatValue getBonLivraisonById(@PathVariable Long id) {

		//logger.info("commandeAchat - getById: Delegating request id {} to service layer.", id);

		CommandeAchatValue commandeAchatValue = bonCommandeService.getById(id);

		if (commandeAchatValue != null) {
			Map<Long, PartieInteresseValue> mapClientById = gestionnaireLogistiqueCacheService.mapClientById();

			PartieInteresseValue client = mapClientById.get(commandeAchatValue.getPartieIntersseId());
			if (client != null) {
				commandeAchatValue.setPartieIntersseAbbreviation(client.getAbreviation());
			}
		}

		return commandeAchatValue;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody String update(@RequestBody CommandeAchatValue bonCommandeValue) {

		return this.bonCommandeService.update(bonCommandeValue);
	}

	@RequestMapping(value = "/delete:{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {

		bonCommandeService.delete(id);
	}

	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<CommandeAchatValue> getAll() {

		//logger.info("getAll");

		return bonCommandeService.getAll();
	}

	@RequestMapping(value = "/getAvailableListBonCommandeRefByFournisseur:{idClient}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<CommandeAchatValue> getListBonCommandeRefByClient(@PathVariable Long idClient) {

		//logger.info("getAvailableListBonCommandeRefByClient");
		return bonCommandeService.getListBonCommandeRefByClient(idClient);
	}
	
	@RequestMapping(value = "/getCurrentReferenceByType:{type}", method = RequestMethod.GET, produces =  "application/json")
 	public @ResponseBody String getCurrentReferenceByType(@PathVariable String type) {
 		
 		return  bonCommandeService.getCurrentReferenceByType(type,Calendar.getInstance(),false);
 	}
	
	@RequestMapping(value = "/getCurrentReferenceMensuel", method = RequestMethod.GET, produces =  "application/json")
 	public @ResponseBody String getCurrentReferenceMensuel() {
 		
 		return  bonCommandeService.getCurrentReferenceMensuel(Calendar.getInstance(),false);
 	}

}
