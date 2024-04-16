package com.gpro.consulting.tiers.logistique.rest.gc.achat.impl;

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
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ProduitReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.RechercheMulticritereProduitReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ResultatRechecheProduitReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.rest.utilities.ApiResponse;
import com.gpro.consulting.tiers.logistique.service.atelier.cache.IGestionnaireLogistiqueCacheService;
import com.gpro.consulting.tiers.logistique.service.gc.achat.reception.IProduitReceptionAchatService;

/**
 * The Class ReceptionAchatDetailRestImpl.
 */
@Controller
@RequestMapping("/receptionAchatDetail")
public class ReceptionAchatDetailRestImpl {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(ReceptionAchatDetailRestImpl.class);


	/** The Produit reception service. */
	@Autowired 
	private IProduitReceptionAchatService produitReceptionAchatService;

	/** The gestionnaire logistique cache service. */
	@Autowired
	private IGestionnaireLogistiqueCacheService gestionnaireLogistiqueCacheService;

	/**
	 * Rechercher multi critere.
	 *
	 * @param request
	 *            the request
	 * @return the resultat recheche bon reception achat value
	 */
	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResultatRechecheProduitReceptionAchatValue rechercherMultiCritere(
			@RequestBody RechercheMulticritereProduitReceptionAchatValue request) {

		//logger.info("rechercheMulticritere: Delegating request {} to service layer.", request);

		ResultatRechecheProduitReceptionAchatValue result = produitReceptionAchatService.rechercherMultiCritere(request);

		if (result != null) {

			Map<Long, PartieInteresseValue> mapClientById = gestionnaireLogistiqueCacheService.mapClientById();

			for (ProduitReceptionAchatValue produitReceptionValue : result.getListProduitReceptionAchat()) {
				if (produitReceptionValue.getReceptionAchatValue() != null ) {
					

					PartieInteresseValue client = mapClientById.get(produitReceptionValue.getReceptionAchatValue().getPartieIntersseId());

					if (client != null) {
						produitReceptionValue.getReceptionAchatValue().setPartieIntersseAbbreviation(client.getAbreviation());
					}
				}
			}
		}

		return result;
	}

	/**
	 * Creates the bon livraison.
	 *
	 * @param ReceptionAchatValue
	 *            the reception achat value
	 * @return the string
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody String createBonLivraison(@RequestBody ProduitReceptionAchatValue ReceptionAchatValue) {

		//logger.info("commandeVente - create: Delegating request to Service layer.");

		return this.produitReceptionAchatService.create(ReceptionAchatValue);
	}

	/**
	 * Gets the bon livraison by id.
	 *
	 * @param id
	 *            the id
	 * @return the bon livraison by id
	 */
	@RequestMapping(value = "/getById:{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ProduitReceptionAchatValue getBonLivraisonById(@PathVariable Long id) {

		//logger.info("commandeVente - getById: Delegating request id {} to service layer.", id);

		ProduitReceptionAchatValue ReceptionAchatValue = produitReceptionAchatService.getById(id);

		if (ReceptionAchatValue != null) {
			Map<Long, PartieInteresseValue> mapClientById = gestionnaireLogistiqueCacheService.mapClientById();

			PartieInteresseValue client = mapClientById.get(ReceptionAchatValue.getPartieIntersseId());
			if (client != null) {
				ReceptionAchatValue.setPartieIntersseAbbreviation(client.getAbreviation());
			}
		}

		return ReceptionAchatValue;
	}

	/**
	 * Update.
	 *
	 * @param bonReceptionValue
	 *            the bon reception value
	 * @return the string
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody String update(@RequestBody ProduitReceptionAchatValue bonReceptionValue) {

		return this.produitReceptionAchatService.update(bonReceptionValue);
	}

	/**
	 * Delete.
	 *
	 * @param id
	 *            the id
	 */
	@RequestMapping(value = "/delete:{id}", method = RequestMethod.DELETE)
	public @ResponseBody ApiResponse delete(@PathVariable Long id) {

		produitReceptionAchatService.delete(id);
		
		return new ApiResponse("OK");
	}

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<ProduitReceptionAchatValue> getAll() {

		//logger.info("getAll");

		return produitReceptionAchatService.getAll();
	}

}
