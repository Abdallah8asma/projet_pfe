package com.gpro.consulting.tiers.logistique.rest.gs.impl;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.ListTraitFaconElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.BonInventaireValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.MagasinValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.RechercheMulticritereBonInventaireValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.ResultatRechecheBonInventaireValue;
import com.gpro.consulting.tiers.logistique.rest.utilities.ApiResponse;
import com.gpro.consulting.tiers.logistique.service.atelier.bonsortiefini.IBonSortieFiniService;
import com.gpro.consulting.tiers.logistique.service.atelier.cache.IGestionnaireLogistiqueCacheService;
import com.gpro.consulting.tiers.logistique.service.gc.bonlivraison.IMarcheService;
import com.gpro.consulting.tiers.logistique.service.gc.bonlivraison.IModePaiementService;
import com.gpro.consulting.tiers.logistique.service.gs.IBonInventaireService;
import com.gpro.consulting.tiers.logistique.service.gs.IMagasinService;

/**
 * Bon de livraison Controller
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 * 
 */

@Controller
@RequestMapping("/bonInventaire")
public class BonInventaireRestImpl {

	private static final Logger logger = LoggerFactory.getLogger(BonInventaireRestImpl.class);

	@Autowired
	private IBonInventaireService bonInventaireService;

	 
	 

	@Autowired
	private IMagasinService magasinService;

	
	

	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResultatRechecheBonInventaireValue rechercherMultiCritere(
			@RequestBody RechercheMulticritereBonInventaireValue request) {
		
		
 
		
		request.setOptimized(this.checkForOptimization(request));

		
		
		

		ResultatRechecheBonInventaireValue result = bonInventaireService.rechercherMultiCritere(request);

		if (result != null) {
 
	

			for (BonInventaireValue bonInventaireValue : result.getList()) {
				if (bonInventaireValue != null) {

 
					 
					 
					 

					if (bonInventaireValue.getIdDepot() != null) {
						MagasinValue magRecherche = new MagasinValue();
						magRecherche.setId(bonInventaireValue.getIdDepot());

						MagasinValue depot = magasinService.rechercheMagasinParId(magRecherche);
						if(depot != null)
							bonInventaireValue.setDepot(depot.getDesignation());

					}

				}
			}
		}

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody String createBonInventaire(@RequestBody BonInventaireValue bonInventaireValue) {
 
		return bonInventaireService.createBonInventaire(bonInventaireValue);
	}

	@RequestMapping(value = "/getById:{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody BonInventaireValue getBonInventaireById(@PathVariable Long id) {
 
		BonInventaireValue bonInventaireValue = bonInventaireService.getBonInventaireById(id);

		 

		return bonInventaireValue;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody String updateBonInventaire(@RequestBody BonInventaireValue bonInventaireValue) {
 
		return this.bonInventaireService.updateBonInventaire(bonInventaireValue);
	}

	@RequestMapping(value = "/delete:{id}", method = RequestMethod.DELETE)
	public @ResponseBody ApiResponse  deleteBonInventaire(@PathVariable Long id) {

		 
		bonInventaireService.deleteBonInventaire(id);
		
		return new ApiResponse("OK");
	}

	
	@RequestMapping(value = "/getCurrentReference", method = RequestMethod.GET, produces =  "application/json")
 	public @ResponseBody String getCurrentReference() {
 		
 		return  bonInventaireService.getCurrentReference(Calendar.getInstance(),false);
 	}
	

	public boolean checkForOptimization(RechercheMulticritereBonInventaireValue request) {

		return

		isNullOrEmpty(request.getDateDe())
		&& isNullOrEmpty(request.getDateA())
		&& isNullOrEmpty(request.getReference())
		 ;

	}

	public boolean isNullOrEmpty(Object criteria) {
		return criteria == null || criteria.toString().length() == 0;
	}
	
	
 
	

}
