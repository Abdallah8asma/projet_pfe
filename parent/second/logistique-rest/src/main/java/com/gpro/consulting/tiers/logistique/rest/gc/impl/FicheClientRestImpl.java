package com.gpro.consulting.tiers.logistique.rest.gc.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gpro.consulting.tiers.logistique.coordination.gc.ficheclient.value.RechercheMulticritereFicheClientValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.ficheclient.value.ResultatRechecheFicheClientValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.report.impl.GestionnaireReportGcDomaineImpl;
import com.gpro.consulting.tiers.logistique.service.gc.ficheclient.IFicheClientService;

/**
 * Fiche Client Controller
 * 
 * @author Wahid Gazzah
 * @since 17/08/2016
 *
 */

@RestController
@RequestMapping("/ficheClient")
public class FicheClientRestImpl {

	private static final Logger logger = LoggerFactory.getLogger(GestionnaireReportGcDomaineImpl.class);

	@Autowired
	private IFicheClientService ficheClientService;

	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST, produces = "application/json")
	public ResultatRechecheFicheClientValue rechercherMultiCritere(
			@RequestBody RechercheMulticritereFicheClientValue request) {

		//logger.info("rechercheMulticritere: Delegating request to service layer.");

		return ficheClientService.rechercherMultiCritere(request);
	}

}
