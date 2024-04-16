package com.gpro.consulting.tiers.logistique.rest.gs.impl;

import java.util.Calendar;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.ListProduitElementValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.ListTraitFaconElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.MarcheValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.ModePaiementValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.BonStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.MagasinValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.RechercheMulticritereBonStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.ResultatRechecheBonStockValue;
import com.gpro.consulting.tiers.logistique.rest.utilities.ApiResponse;
import com.gpro.consulting.tiers.logistique.service.atelier.bonsortiefini.IBonSortieFiniService;
import com.gpro.consulting.tiers.logistique.service.atelier.cache.IGestionnaireLogistiqueCacheService;
import com.gpro.consulting.tiers.logistique.service.gc.bonlivraison.IMarcheService;
import com.gpro.consulting.tiers.logistique.service.gc.bonlivraison.IModePaiementService;
import com.gpro.consulting.tiers.logistique.service.gs.IBonStockService;
import com.gpro.consulting.tiers.logistique.service.gs.IMagasinService;

/**
 * Bon de livraison Controller
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 * 
 */

@Controller
@RequestMapping("/bonStock")
public class BonStockRestImpl {

	private static final Logger logger = LoggerFactory.getLogger(BonStockRestImpl.class);

	@Autowired
	private IBonStockService bonStockService;

	@Autowired
	private IBonSortieFiniService bonSortieFiniService;

	@Autowired
	private IGestionnaireLogistiqueCacheService gestionnaireLogistiqueCacheService;

	@Autowired
	private IMarcheService marcheService;

	@Autowired
	private IModePaiementService modePaiementService;

	@Autowired
	private IMagasinService magasinService;

	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public @ResponseBody ListProduitElementValue validateBonSortieFini(@RequestBody List<String> refBonSortieList,
			@RequestParam(value = "livraisonVenteId", required = false) Long livraisonVenteId) {

		ListProduitElementValue list = new ListProduitElementValue();

		if (refBonSortieList != null && refBonSortieList.size() > 0) {
			list = bonSortieFiniService.getProduitElementList(refBonSortieList, livraisonVenteId);
		}

		return list;
	}

	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResultatRechecheBonStockValue rechercherMultiCritere(
			@RequestBody RechercheMulticritereBonStockValue request) {
		
		

		
		
		request.setOptimized(this.checkForOptimization(request));

		if (request.getDateLivraisonMin() == null) {

			Calendar date = Calendar.getInstance();
			date.set(Calendar.YEAR, 2020);
			date.set(Calendar.MONTH, 0);
			date.set(Calendar.DAY_OF_MONTH, 1);

			date.set(Calendar.HOUR, 0);
			date.set(Calendar.MINUTE, 0);
			date.set(Calendar.MILLISECOND, 0);

			request.setDateLivraisonMin(date);
		}
		
		// logger.info("rechercheMulticritere: Delegating request {} to service layer.",
		// request);

		// FACON
		// request.setNatureLivraison("FINI");

		ResultatRechecheBonStockValue result = bonStockService.rechercherMultiCritere(request);

		if (result != null) {

			Map<Long, PartieInteresseValue> mapClientById = gestionnaireLogistiqueCacheService.mapClientById();
			Map<Long, MarcheValue> mapMarcheById = new HashMap<Long, MarcheValue>();
			Map<Long, ModePaiementValue> mapModePaiementById = new HashMap<Long, ModePaiementValue>();

		/*	List<MarcheValue> marcheList = marcheService.getAll();
			List<ModePaiementValue> modePaiementList = modePaiementService.getAll();

			for (MarcheValue marche : marcheList) {
				Long key = marche.getId();
				if (mapMarcheById.get(key) == null) {
					mapMarcheById.put(marche.getId(), marche);
				}
			}

			for (ModePaiementValue modePaiement : modePaiementList) {
				Long key = modePaiement.getId();
				if (mapModePaiementById.get(key) == null) {
					mapModePaiementById.put(modePaiement.getId(), modePaiement);
				}
			}*/

			for (BonStockValue livraisonVenteValue : result.getList()) {
				if (livraisonVenteValue != null) {

					PartieInteresseValue client = mapClientById.get(livraisonVenteValue.getPartieIntId());
					MarcheValue marche = mapMarcheById.get(livraisonVenteValue.getMarcheId());
					ModePaiementValue modePaiement = mapModePaiementById.get(livraisonVenteValue.getModepaiementId());

					if (client != null) {
						livraisonVenteValue.setPartieIntAbreviation(client.getAbreviation());
					}
					if (marche != null) {
						livraisonVenteValue.setMarche(marche.getDesignation());
					}
					if (modePaiement != null) {
						livraisonVenteValue.setModepaiement(modePaiement.getDesignation());
					}

					if (livraisonVenteValue.getIdDepot() != null) {
						MagasinValue magRecherche = new MagasinValue();
						magRecherche.setId(livraisonVenteValue.getIdDepot());

						MagasinValue depot = magasinService.rechercheMagasinParId(magRecherche);
						if(depot != null)
						livraisonVenteValue.setDepot(depot.getDesignation());

					}

				}
			}
		}

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody String createBonStock(@RequestBody BonStockValue bonLivraisonValue) {

		// logger.info("createBonStock: Delegating request to Service layer.");

		return this.bonStockService.createBonStock(bonLivraisonValue);
	}

	@RequestMapping(value = "/getById:{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody BonStockValue getBonStockById(@PathVariable Long id) {

		// logger.info("getBonStockById: Delegating request id {} to service
		// layer.", id);

		BonStockValue livraisonVenteValue = bonStockService.getBonStockById(id);

		if (livraisonVenteValue != null) {
			Map<Long, PartieInteresseValue> mapClientById = gestionnaireLogistiqueCacheService.mapClientById();
			PartieInteresseValue client = mapClientById.get(livraisonVenteValue.getPartieIntId());
			if (client != null) {
				livraisonVenteValue.setPartieIntAbreviation(client.getAbreviation());
			}
		}

		return livraisonVenteValue;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody String updateBonStock(@RequestBody BonStockValue bonLivraisonValue) {

		// logger.info("UpdateRouleauFini: Delegating request to service layer.");

		return this.bonStockService.updateBonStock(bonLivraisonValue);
	}

	@RequestMapping(value = "/delete:{id}", method = RequestMethod.DELETE)
	public @ResponseBody ApiResponse  deleteBonStock(@PathVariable Long id) {

		// logger.info("deleteBonStock: Delegating request id {} to service layer.",
		// id);

		bonStockService.deleteBonStock(id);
		
		return new ApiResponse("OK");
	}

	@RequestMapping(value = "/getAvailableListBonStockRef", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<String> getAvailableBonStockRef() {

		// logger.info("getListBonStockRef");

		return bonStockService.getListBonStockRef();
	}





	// Added on 03/10/2016, by Zeineb Medimagh
	@RequestMapping(value = "/validateFacon", method = RequestMethod.POST)
	public @ResponseBody ListTraitFaconElementValue validateBonSortieFiniFacon(
			@RequestBody List<String> refBonSortieList,
			@RequestParam(value = "livraisonVenteId", required = false) Long livraisonVenteId) {

		ListTraitFaconElementValue list = new ListTraitFaconElementValue();

		if (refBonSortieList != null && refBonSortieList.size() > 0) {
			list = bonSortieFiniService.getTraitFaconElementList(refBonSortieList, livraisonVenteId);
		}

		return list;
	}




	
	@RequestMapping(value = "/getCurrentReference", method = RequestMethod.GET, produces =  "application/json")
 	public @ResponseBody String getCurrentReference() {
 		
 		return  bonStockService.getCurrentReference(Calendar.getInstance(),false);
 	}
	

	public boolean checkForOptimization(RechercheMulticritereBonStockValue request) {

		return

		isNullOrEmpty(request.getDateLivraisonMin())
		&& isNullOrEmpty(request.getDateLivraisonMax())
		&& isNullOrEmpty(request.getPartieIntId())
		&& isNullOrEmpty(request.getGroupeClientId());

	}

	public boolean isNullOrEmpty(Object criteria) {
		return criteria == null || criteria.toString().length() == 0;
	}
	
	
	@RequestMapping(value = "/validerFactureAvoirRetour:{reference}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody BonStockValue validerFactureAvoirRetour(@PathVariable String reference) {

		// logger.info("getAvailableListBonLivraisonRefByClient");
		return bonStockService.validerFactureAvoirRetour(reference);
	}
	

	@RequestMapping(value = "/validerByRefBR:{reference}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody BonStockValue validerBR(@PathVariable String reference) {

		// logger.info("getAvailableListBonLivraisonRefByClient");
		return bonStockService.validerBR(reference);
	}
	
	

}
