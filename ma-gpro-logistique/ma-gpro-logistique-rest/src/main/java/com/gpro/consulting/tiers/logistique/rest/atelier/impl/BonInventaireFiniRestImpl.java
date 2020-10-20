package com.gpro.consulting.tiers.logistique.rest.atelier.impl;

import java.util.ArrayList;
import java.util.List;

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

import com.gpro.consulting.tiers.logistique.coordination.atelier.boninventairefini.value.BonInventaireFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.boninventairefini.value.RechercheMulticritereBonInventaireFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.boninventairefini.value.ResultatRechecheBonInventaireFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.RouleauFiniValue;
import com.gpro.consulting.tiers.logistique.service.atelier.boninventairefini.IBonInventaireFiniService;

/**
 * BonSortieFini Controller
 * 
 * @author Ghazi Atroussi
 * @since 18/12/2016
 *
 */
@Controller
@RequestMapping("/boninventairefini")
public class BonInventaireFiniRestImpl {

	private static final Logger logger = LoggerFactory.getLogger(BonInventaireFiniRestImpl.class);

	@Autowired
	IBonInventaireFiniService bonInventaireFiniService;

	// Prepare the list of RouleauFiniValue
	@RequestMapping(value = "/validateBonInventaireFini", method = RequestMethod.POST)
	public @ResponseBody List<RouleauFiniValue> validateBonInventaireFini(@RequestBody List<String> barreCodeList,
			@RequestParam(value = "id", required = false) Long id) {

		//logger.info("validateBonInventaireFini");

		List<RouleauFiniValue> listRouleauFini = new ArrayList<RouleauFiniValue>();
		if (barreCodeList != null && barreCodeList.size() > 0) {
			listRouleauFini = bonInventaireFiniService.validateBonInventaireFini(barreCodeList, id);
		}

		return listRouleauFini;
	}

	@RequestMapping(value = "/createBonInventaireFini", method = RequestMethod.POST)
	public @ResponseBody String createBonInventaireFini(@RequestBody BonInventaireFiniValue BonInventaireFiniValue) {

		//logger.info("createBonInventaireFini: Delegating request to Service layer.");

		String id = this.bonInventaireFiniService.createBonInventaireFini(BonInventaireFiniValue);

		return id;
	}

	@RequestMapping(value = "/getBonInventaireFiniById:{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody BonInventaireFiniValue getBonInventaireFiniById(@PathVariable Long id) {

		//logger.info("getBonInventaireFiniById: Delegating request id {} to service layer.", id);

		return bonInventaireFiniService.getBonInventaireFiniById(id);
	}

	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResultatRechecheBonInventaireFiniValue rechercherMultiCritereBonSortieFini(
			@RequestBody RechercheMulticritereBonInventaireFiniValue request) {

		//logger.info("rechercheMulticritere: Delegating request {} to service layer.", request);

		ResultatRechecheBonInventaireFiniValue vResultatRecherche = bonInventaireFiniService
				.rechercherMultiCritere(request);

		// // Traitement : transformation de l'Id a sa propre Designation
		// for (BonInventaireFiniValue elementRechercheBonSortie :
		// vResultatRecherche.getList()) {
		//
		// // Client, TypeBS
		// Map<String, String> mapA = gestionnaireLogistiqueCacheService
		// .rechercherDesignationParId(elementRechercheBonSortie.getPartieInt(),null,
		// null, null);
		// // Client
		// elementRechercheBonSortie.setPartieIntDesignation(mapA.get("client"));
		// // TypeBS
		//
		// }

		return vResultatRecherche;

	}

	/**
	 * @return the bonInventaireFiniService
	 */
	public IBonInventaireFiniService getBonInventaireFiniService() {
		return bonInventaireFiniService;
	}

	/**
	 * @param bonInventaireFiniService
	 *            the bonInventaireFiniService to set
	 */
	public void setBonInventaireFiniService(IBonInventaireFiniService bonInventaireFiniService) {
		this.bonInventaireFiniService = bonInventaireFiniService;
	}

	@RequestMapping(value = "/updateBonInventaireFini", method = RequestMethod.POST)
	public @ResponseBody String updateBonInventaireFini(@RequestBody BonInventaireFiniValue BonInventaireFiniValue) {

		//logger.info("updateBonInventaireFini: Delegating request {} to service layer.", BonInventaireFiniValue.getId());

		return this.bonInventaireFiniService.updateBonInventaireFini(BonInventaireFiniValue);
	}

	@RequestMapping(value = "/deleteBonInventaireFini:{id}", method = RequestMethod.DELETE)
	public void deleteBonSortieFini(@PathVariable("id") String id) {

		//logger.info("deleteBonSortieFini: Delegating request id {} to service layer.", id);

		bonInventaireFiniService.deleteBonInventaireFini(Long.valueOf(id));
	}

}
