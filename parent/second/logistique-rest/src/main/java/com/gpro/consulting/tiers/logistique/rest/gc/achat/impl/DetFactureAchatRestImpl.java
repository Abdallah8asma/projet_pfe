package com.gpro.consulting.tiers.logistique.rest.gc.achat.impl;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.DetFactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.RechercheMulticritereDetFactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.ResultatRechecheDetFactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.RechercheMulticritereDetFactureVenteValue;
import com.gpro.consulting.tiers.logistique.service.gc.achat.facture.IDetFactureAchatService;

@Controller
@RequestMapping("/detfactureAchat")
public class DetFactureAchatRestImpl {

	private static final Logger logger = LoggerFactory.getLogger(DetFactureAchatRestImpl.class);

	@Autowired
	private IDetFactureAchatService detFactureAchatService;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody String create(@RequestBody DetFactureAchatValue detFactureAchatValue) {

		//logger.info("create: Delegating request to Service layer.");

		return this.detFactureAchatService.create(detFactureAchatValue);
	}

	@RequestMapping(value = "/getById:{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody DetFactureAchatValue getById(@PathVariable Long id) {

		//logger.info("getById: Delegating request id {} to service layer.", id);

		return detFactureAchatService.getById(id);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody String update(@RequestBody DetFactureAchatValue detFactureAchatValue) {

		//logger.info("Update: Delegating request to service layer.");

		return this.detFactureAchatService.update(detFactureAchatValue);
	}

	@RequestMapping(value = "/delete:{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {

		//logger.info("delete: Delegating request id {} to service layer.", id);

		detFactureAchatService.delete(id);
	}
	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResultatRechecheDetFactureAchatValue rechercherMultiCritere(
			@RequestBody RechercheMulticritereDetFactureAchatValue request) {
		request.setOptimized(RechercheMulticritereDetFactureAchatValue.checkForOptimization(request));
		
		return  detFactureAchatService.rechercherMultiCritere(request);
	}
	

	
}
