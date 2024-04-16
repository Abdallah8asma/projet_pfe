package com.gpro.consulting.tiers.logistique.rest.gc.impl;

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

import com.gpro.consulting.logistique.coordination.gc.boncomptoir.value.ComptoirVenteValue;
import com.gpro.consulting.logistique.coordination.gc.boncomptoir.value.RechercheMulticritereBonComptoirValue;
import com.gpro.consulting.logistique.coordination.gc.boncomptoir.value.ResultatRechecheBonComptoirValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.ListProduitElementValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.ListTraitFaconElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.MarcheValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.ModePaiementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.vue.LivraisonVenteFactureVue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.vue.LivraisonVenteVue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.MagasinValue;
import com.gpro.consulting.tiers.logistique.service.atelier.bonsortiefini.IBonSortieFiniService;
import com.gpro.consulting.tiers.logistique.service.atelier.cache.IGestionnaireLogistiqueCacheService;
import com.gpro.consulting.tiers.logistique.service.gc.boncomptoir.IBonComptoirService;
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
@RequestMapping("/boncomptoir")
public class BonComptoirRestImpl {

	private static final Logger logger = LoggerFactory.getLogger(BonComptoirRestImpl.class);

	@Autowired
	private IBonComptoirService bonComptoirService;

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
	public @ResponseBody ResultatRechecheBonComptoirValue rechercherMultiCritere(
			@RequestBody RechercheMulticritereBonComptoirValue request) {

		//logger.info("rechercheMulticritere: Delegating request {} to service layer.", request);

		// FACON
		// request.setNatureComptoir("FINI");

		ResultatRechecheBonComptoirValue result = bonComptoirService.rechercherMultiCritere(request);

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

			for (ComptoirVenteValue livraisonVenteValue : result.getList()) {
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
						livraisonVenteValue.setDepot(depot.getDesignation());

					}

				}
			}
		}

		return result;
	}

	@RequestMapping(value = "/createBonComptoir", method = RequestMethod.POST)
	public @ResponseBody String createBonComptoir(@RequestBody ComptoirVenteValue bonComptoirValue) {

		//logger.info("createBonComptoir: Delegating request to Service layer.");

		return this.bonComptoirService.createBonComptoir(bonComptoirValue);
	}

	@RequestMapping(value = "/getBonComptoirById:{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ComptoirVenteValue getBonComptoirById(@PathVariable Long id) {

		//logger.info("getBonComptoirById: Delegating request id {} to service layer.", id);

		ComptoirVenteValue livraisonVenteValue = bonComptoirService.getBonComptoirById(id);

		if (livraisonVenteValue != null) {
			Map<Long, PartieInteresseValue> mapClientById = gestionnaireLogistiqueCacheService.mapClientById();
			PartieInteresseValue client = mapClientById.get(livraisonVenteValue.getPartieIntId());
			if (client != null) {
				livraisonVenteValue.setPartieIntAbreviation(client.getAbreviation());
			}
		}

		return livraisonVenteValue;
	}
	
	
	@RequestMapping(value = "/getBonComptoirByReference:{reference}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ComptoirVenteValue getBonComptoirByReference(@PathVariable String reference) {

		//logger.info("getBonComptoirById: Delegating request id {} to service layer.", reference);
		
		RechercheMulticritereBonComptoirValue request = new RechercheMulticritereBonComptoirValue();
		
		request.setReferenceBl(reference);
		
		ResultatRechecheBonComptoirValue res = bonComptoirService.rechercherMultiCritere(request);
		
		if(res == null || res.getList().size()!= 1) return null;

		ComptoirVenteValue livraisonVenteValue = bonComptoirService.getBonComptoirById(res.getList().iterator().next().getId());

		if (livraisonVenteValue != null) {
			Map<Long, PartieInteresseValue> mapClientById = gestionnaireLogistiqueCacheService.mapClientById();
			PartieInteresseValue client = mapClientById.get(livraisonVenteValue.getPartieIntId());
			if (client != null) {
				livraisonVenteValue.setPartieIntAbreviation(client.getAbreviation());
			}
		}

		return livraisonVenteValue;
	}

	@RequestMapping(value = "/updateBonComptoir", method = RequestMethod.POST)
	public @ResponseBody String updateBonComptoir(@RequestBody ComptoirVenteValue bonComptoirValue) {

		//logger.info("UpdateRouleauFini: Delegating request to service layer.");

		return this.bonComptoirService.updateBonComptoir(bonComptoirValue);
	}

	@RequestMapping(value = "/deleteBonComptoir:{id}", method = RequestMethod.DELETE)
	public void deleteBonComptoir(@PathVariable Long id) {

		//logger.info("deleteBonComptoir: Delegating request id {} to service layer.", id);

		bonComptoirService.deleteBonComptoir(id);
	}

	@RequestMapping(value = "/getAvailableListBonComptoirRef", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<String> getAvailableBonComptoirRef() {

		//logger.info("getListBonComptoirRef");

		return bonComptoirService.getListBonComptoirRef();
	}

	@RequestMapping(value = "/getAvailableListBonComptoirRefByClient:{idClient}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<LivraisonVenteVue> getListBonComptoirRefByClient(@PathVariable Long idClient) {

		//logger.info("getAvailableListBonComptoirRefByClient");
		return bonComptoirService.getListBonComptoirRefByClient(idClient);
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

	@RequestMapping(value = "/setNatureComptoir", method = RequestMethod.POST)
	public @ResponseBody void setNatureComptoir() {

		List<ComptoirVenteValue> liste = bonComptoirService.getAll();
		for (ComptoirVenteValue livraisonVenteValue : liste) {
			//livraisonVenteValue.setNatureComptoir("FINI");
			bonComptoirService.updateBonComptoir(livraisonVenteValue);
		}

	}

	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<ComptoirVenteValue> getAll() {

		//logger.info("getAll");

		return bonComptoirService.getAll();
	}

	@RequestMapping(value = "/getAllListBonComptoirRefByClient:{idClient}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<LivraisonVenteFactureVue> getAllBonComptoirRefByClient(@PathVariable Long idClient) {

		//logger.info("getAvailableListBonComptoirRefByClient");
		return bonComptoirService.getAllListBonComptoirRefByClient(idClient);
	}

}
