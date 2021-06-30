package com.gpro.consulting.tiers.logistique.rest.gc.impl;

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
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.LivraisonVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.MarcheValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.ModePaiementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.RechercheMulticritereBonLivraisonValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.ResultatRechecheBonLivraisonValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.vue.LivraisonVenteFactureVue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.vue.LivraisonVenteVue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.MagasinValue;
import com.gpro.consulting.tiers.logistique.service.atelier.bonsortiefini.IBonSortieFiniService;
import com.gpro.consulting.tiers.logistique.service.atelier.cache.IGestionnaireLogistiqueCacheService;
import com.gpro.consulting.tiers.logistique.service.gc.bonCommande.IBonCommandeService;
import com.gpro.consulting.tiers.logistique.service.gc.bonlivraison.IBonLivraisonService;
import com.gpro.consulting.tiers.logistique.service.gc.bonlivraison.IMarcheService;
import com.gpro.consulting.tiers.logistique.service.gc.bonlivraison.IModePaiementService;
import com.gpro.consulting.tiers.logistique.service.gs.IMagasinService;

/**
 * Bon de livraison Controller
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 * 
 */

@Controller
@RequestMapping("/bonlivraison")
public class BonLivraisonRestImpl {

	private static final Logger logger = LoggerFactory.getLogger(BonLivraisonRestImpl.class);

	@Autowired
	private IBonLivraisonService bonLivraisonService;

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
	
	
	@Autowired
	private IBonCommandeService bonCommandeService;
	
	
	
	@RequestMapping(value = "/validateCommandes", method = RequestMethod.POST)
	public @ResponseBody ListProduitElementValue validateCommandes(@RequestBody List<String> refBonCommandesList,
			@RequestParam(value = "livraisonVenteId", required = false) Long livraisonVenteId) {

		ListProduitElementValue list = new ListProduitElementValue();

		if (refBonCommandesList != null && refBonCommandesList.size() > 0) {
			list = bonCommandeService.getProduitElementList(refBonCommandesList, livraisonVenteId);

		}

		return list;
	}
	
	

	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public @ResponseBody ListProduitElementValue validateBonSortieFini(@RequestBody List<String> refBonSortieList,
			@RequestParam(value = "livraisonVenteId", required = false) Long livraisonVenteId) {

		ListProduitElementValue list = new ListProduitElementValue();

		if (refBonSortieList != null && refBonSortieList.size() > 0) {
			list = bonSortieFiniService.getProduitElementList(refBonSortieList, livraisonVenteId);
		}

		return list;
	}
	
	
	@RequestMapping(value = "/validateByOF", method = RequestMethod.POST)
	public @ResponseBody ListProduitElementValue validateBonSortieFiniByOF(@RequestBody List<String> refBonSortieList,
			@RequestParam(value = "livraisonVenteId", required = false) Long livraisonVenteId) {

		ListProduitElementValue list = new ListProduitElementValue();

		if (refBonSortieList != null && refBonSortieList.size() > 0) {
			list = bonSortieFiniService.getProduitElementListByOF(refBonSortieList, livraisonVenteId);
		}

		return list;
	}

	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResultatRechecheBonLivraisonValue rechercherMultiCritere(
			@RequestBody RechercheMulticritereBonLivraisonValue request) {
		
		

		
		
		request.setOptimized(this.checkForOptimization(request));

	/*	if (request.getDateLivraisonMin() == null) {

			Calendar date = Calendar.getInstance();
			date.set(Calendar.YEAR, 2020);
			date.set(Calendar.MONTH, 0);
			date.set(Calendar.DAY_OF_MONTH, 1);

			date.set(Calendar.HOUR, 0);
			date.set(Calendar.MINUTE, 0);
			date.set(Calendar.MILLISECOND, 0);

			request.setDateLivraisonMin(date);
		}*/
		
		// logger.info("rechercheMulticritere: Delegating request {} to service layer.",
		// request);

		// FACON
		// request.setNatureLivraison("FINI");

		ResultatRechecheBonLivraisonValue result = bonLivraisonService.rechercherMultiCritere(request);

		if (result != null) {

			Map<Long, PartieInteresseValue> mapClientById = gestionnaireLogistiqueCacheService.mapClientById();
			Map<Long, MarcheValue> mapMarcheById = new HashMap<Long, MarcheValue>();
			Map<Long, ModePaiementValue> mapModePaiementById = new HashMap<Long, ModePaiementValue>();

			List<MarcheValue> marcheList = marcheService.getAll();
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
			}

			for (LivraisonVenteValue livraisonVenteValue : result.getList()) {
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

	@RequestMapping(value = "/createBonLivraison", method = RequestMethod.POST)
	public @ResponseBody String createBonLivraison(@RequestBody LivraisonVenteValue bonLivraisonValue) {

		// logger.info("createBonLivraison: Delegating request to Service layer.");

		return this.bonLivraisonService.createBonLivraison(bonLivraisonValue);
	}

	@RequestMapping(value = "/getBonLivraisonById:{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody LivraisonVenteValue getBonLivraisonById(@PathVariable Long id) {

		// logger.info("getBonLivraisonById: Delegating request id {} to service
		// layer.", id);

		LivraisonVenteValue livraisonVenteValue = bonLivraisonService.getBonLivraisonById(id);
		
		livraisonVenteValue.setRefAvantChangement(livraisonVenteValue.getReference());

		if (livraisonVenteValue != null) {
			Map<Long, PartieInteresseValue> mapClientById = gestionnaireLogistiqueCacheService.mapClientById();
			PartieInteresseValue client = mapClientById.get(livraisonVenteValue.getPartieIntId());
			if (client != null) {
				livraisonVenteValue.setPartieIntAbreviation(client.getAbreviation());
			}
		}

		return livraisonVenteValue;
	}

	@RequestMapping(value = "/updateBonLivraison", method = RequestMethod.POST)
	public @ResponseBody String updateBonLivraison(@RequestBody LivraisonVenteValue bonLivraisonValue) {

		// logger.info("UpdateRouleauFini: Delegating request to service layer.");

		return this.bonLivraisonService.updateBonLivraison(bonLivraisonValue);
	}

	@RequestMapping(value = "/deleteBonLivraison:{id}", method = RequestMethod.DELETE)
	public void deleteBonLivraison(@PathVariable Long id) {

		// logger.info("deleteBonLivraison: Delegating request id {} to service layer.",
		// id);

		bonLivraisonService.deleteBonLivraison(id);
	}

	@RequestMapping(value = "/getAvailableListBonLivraisonRef", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<String> getAvailableBonLivraisonRef() {

		// logger.info("getListBonLivraisonRef");

		return bonLivraisonService.getListBonLivraisonRef();
	}

	@RequestMapping(value = "/getAvailableListBonLivraisonRefByClient:{idClient}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<LivraisonVenteVue> getListBonLivraisonRefByClient(@PathVariable Long idClient) {

		// logger.info("getAvailableListBonLivraisonRefByClient");
		return bonLivraisonService.getListBonLivraisonRefByClient(idClient);
	}

	@RequestMapping(value = "/getAvailableListBonLivraisonRefByClientAndDeclare:{idClient}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<LivraisonVenteVue> getListBonLivraisonRefByClientAndDeclare(@PathVariable Long idClient) {

		// logger.info("getAvailableListBonLivraisonRefByClient");
		return bonLivraisonService.getListBonLivraisonRefByClientAndDeclare(idClient);
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

	@RequestMapping(value = "/setNatureLivraison", method = RequestMethod.POST)
	public @ResponseBody void setNatureLivraison() {

		List<LivraisonVenteValue> liste = bonLivraisonService.getAll();
		for (LivraisonVenteValue livraisonVenteValue : liste) {
			livraisonVenteValue.setNatureLivraison("FINI");
			bonLivraisonService.updateBonLivraison(livraisonVenteValue);
		}

	}

	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<LivraisonVenteValue> getAll() {

		// logger.info("getAll");

		return bonLivraisonService.getAll();
	}

	@RequestMapping(value = "/getAllListBonLivraisonRefByClient:{idClient}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<LivraisonVenteFactureVue> getAllBonLivraisonRefByClient(@PathVariable Long idClient) {

		// logger.info("getAvailableListBonLivraisonRefByClient");
		return bonLivraisonService.getAllListBonLivraisonRefByClient(idClient);
	}
	
	@RequestMapping(value = "/getCurrentReference", method = RequestMethod.GET, produces =  "application/json")
 	public @ResponseBody String getCurrentReference() {
 		
 		return  bonLivraisonService.getCurrentReference(Calendar.getInstance(),false);
 	}
	
	@RequestMapping(value = "/getCurrentReferenceByType:{type}", method = RequestMethod.GET, produces =  "application/json")
 	public @ResponseBody String getCurrentReferenceByType(@PathVariable String type) {
 		
 		return  bonLivraisonService.getCurrentReferenceByType(type,Calendar.getInstance(),false);
 	}
	

	@RequestMapping(value = "/getBonLivraisonByReference", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody LivraisonVenteValue getBonLivraisonByReference(	@RequestParam(value = "reference", required = false) String reference) {

		// logger.info("getBonLivraisonById: Delegating request id {} to service
		// layer.", id);

		LivraisonVenteValue livraisonVenteValue = bonLivraisonService.getByReference(reference);

		if (livraisonVenteValue != null) {
			Map<Long, PartieInteresseValue> mapClientById = gestionnaireLogistiqueCacheService.mapClientById();
			PartieInteresseValue client = mapClientById.get(livraisonVenteValue.getPartieIntId());
			if (client != null) {
				livraisonVenteValue.setPartieIntAbreviation(client.getAbreviation());
			}
		}

		return livraisonVenteValue;
	}
	

	public boolean checkForOptimization(RechercheMulticritereBonLivraisonValue request) {

		return

		isNullOrEmpty(request.getDateLivraisonMin())
		&& isNullOrEmpty(request.getDateLivraisonMax())
		&& isNullOrEmpty(request.getPartieIntId())
		&& isNullOrEmpty(request.getGroupeClientId());

	}

	public boolean isNullOrEmpty(Object criteria) {
		return criteria == null || criteria.toString().length() == 0;
	}

}
