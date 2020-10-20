package com.gpro.consulting.tiers.logistique.rest.gc.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.DetLivraisonVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.RechercheMulticritereBonLivraisonValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.RechercheMulticritereDetLivraisonValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.ResultatRechecheDetBonLivraisonValue;
import com.gpro.consulting.tiers.logistique.service.gc.bonlivraison.IDetLivraisonVenteService;

/**
 * DetLivraison Vente Controller
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */

@Controller
@RequestMapping("/detlivraisonvente")
public class DetLivraisonVenteRestImpl {

	private static final Logger logger = LoggerFactory.getLogger(DetLivraisonVenteRestImpl.class);

	@Autowired
	private IDetLivraisonVenteService detLivraisonVenteService;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody String create(@RequestBody DetLivraisonVenteValue detLivraisonVenteValue) {

		//logger.info("create: Delegating request to Service layer.");

		return this.detLivraisonVenteService.create(detLivraisonVenteValue);
	}

	@RequestMapping(value = "/getById:{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody DetLivraisonVenteValue getById(@PathVariable Long id) {

		//logger.info("getById: Delegating request id {} to service layer.", id);

		return detLivraisonVenteService.getById(id);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody String update(@RequestBody DetLivraisonVenteValue detLivraisonVenteValue) {

		//logger.info("Update: Delegating request to service layer.");

		return this.detLivraisonVenteService.update(detLivraisonVenteValue);
	}

	@RequestMapping(value = "/delete:{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {

		//logger.info("delete: Delegating request id {} to service layer.", id);

		detLivraisonVenteService.delete(id);
	}

	@RequestMapping(value = "/rechercheMulticritereDetLivraison", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResultatRechecheDetBonLivraisonValue rechercherMultiCritereDetLivraison(
			@RequestBody RechercheMulticritereDetLivraisonValue request) {

		//logger.info("rechercheMulticritereDetLivraison: Delegating request {} to service layer.", request);

		//System.out.println("inn rest det liv");
		
		request.setOptimized(this.checkForOptimization(request));

		ResultatRechecheDetBonLivraisonValue result = detLivraisonVenteService
				.rechercherMultiCritereDetLivraison(request);
		//System.out.println("#### result: " + result.getListDetailLivraison().size());

		return result;
	}
	
	public boolean checkForOptimization(RechercheMulticritereDetLivraisonValue request) {

		return

		isNullOrEmpty(request.getDateDe())
		&& isNullOrEmpty(request.getDateA())
		&& isNullOrEmpty(request.getPartieIntId())
		&& isNullOrEmpty(request.getIdDepot())
		&& isNullOrEmpty(request.getIdProduit())
		&& isNullOrEmpty(request.getQteDe())
		&& isNullOrEmpty(request.getQteA())
		
		&& isNullOrEmpty(request.getPrixDe())
		&& isNullOrEmpty(request.getPrixA());

	}

	public boolean isNullOrEmpty(Object criteria) {
		return criteria == null || criteria.toString().length() == 0;
	}

}
