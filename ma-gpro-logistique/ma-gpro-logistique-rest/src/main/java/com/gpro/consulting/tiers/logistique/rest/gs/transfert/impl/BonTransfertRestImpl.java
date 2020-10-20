package com.gpro.consulting.tiers.logistique.rest.gs.transfert.impl;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.logistique.coordination.gs.transfert.value.BonTransfertValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.transfert.value.RechercheMulticritereBonTransfertValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.transfert.value.ResultatRechecheBonTransfertValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.MagasinValue;
import com.gpro.consulting.tiers.logistique.rest.utilities.ApiResponse;
import com.gpro.consulting.tiers.logistique.service.gs.IMagasinService;
import com.gpro.consulting.tiers.logistique.service.gs.transfert.IBonTransfertService;

/**
 * Bon de livraison Controller
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 * 
 */

@Controller
@RequestMapping("/bonTransfert")
public class BonTransfertRestImpl {

	private static final Logger logger = LoggerFactory.getLogger(BonTransfertRestImpl.class);

	@Autowired
	private IBonTransfertService bonTransfertService;

	 
	 

	@Autowired
	private IMagasinService magasinService;

	
	

	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResultatRechecheBonTransfertValue rechercherMultiCritere(
			@RequestBody RechercheMulticritereBonTransfertValue request) {
		
		
 
		
		request.setOptimized(this.checkForOptimization(request));

		
		
		

		ResultatRechecheBonTransfertValue result = bonTransfertService.rechercherMultiCritere(request);

		if (result != null) {
 
	

			for (BonTransfertValue bonTransfertValue : result.getList()) {
				if (bonTransfertValue != null) {

 
					 
					 
					 

					if (bonTransfertValue.getIdDepotOrigine() != null) {
						MagasinValue magRecherche = new MagasinValue();
						magRecherche.setId(bonTransfertValue.getIdDepotOrigine());

						MagasinValue depot = magasinService.rechercheMagasinParId(magRecherche);
						if(depot != null)
							bonTransfertValue.setDepotOrigine(depot.getDesignation());

					}
					
					
					if (bonTransfertValue.getIdDepotDestination() != null) {
						MagasinValue magRecherche = new MagasinValue();
						magRecherche.setId(bonTransfertValue.getIdDepotDestination());

						MagasinValue depot = magasinService.rechercheMagasinParId(magRecherche);
						if(depot != null)
							bonTransfertValue.setDepotDestination(depot.getDesignation());

					}

				}
			}
		}

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody String createBonTransfert(@RequestBody BonTransfertValue bonTransfertValue) {
 
		return bonTransfertService.createBonTransfert(bonTransfertValue);
	}

	@RequestMapping(value = "/getById:{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody BonTransfertValue getBonTransfertById(@PathVariable Long id) {
 
		BonTransfertValue bonTransfertValue = bonTransfertService.getBonTransfertById(id);

		 

		return bonTransfertValue;
	}
	
	
	@RequestMapping(value = "/getByReference:{reference}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody BonTransfertValue getBonTransfertByReference(@PathVariable String reference) {
 
		BonTransfertValue bonTransfertValue = bonTransfertService.getBonTransfertByReference(reference);

		 

		return bonTransfertValue;
	}
	
	
	
	
	@RequestMapping(value = "/validerBTsortieByReference:{reference}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody BonTransfertValue validerBTsortie(@PathVariable String reference) {
 
		BonTransfertValue bonTransfertValue = bonTransfertService.validerBTsortieByReference(reference);

		 

		return bonTransfertValue;
	}
	
	

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody String updateBonTransfert(@RequestBody BonTransfertValue bonTransfertValue) {
 
		return this.bonTransfertService.updateBonTransfert(bonTransfertValue);
	}

	@RequestMapping(value = "/delete:{id}", method = RequestMethod.DELETE)
	public @ResponseBody ApiResponse  deleteBonTransfert(@PathVariable Long id) {

		 
		bonTransfertService.deleteBonTransfert(id);
		
		return new ApiResponse("OK");
	}

	
	@RequestMapping(value = "/getCurrentReferenceByType:{type}", method = RequestMethod.GET, produces =  "application/json")
 	public @ResponseBody String getCurrentReference(@PathVariable String type) {
 		
 		return  bonTransfertService.getCurrentReference(type,Calendar.getInstance(),false);
 	}
	

	public boolean checkForOptimization(RechercheMulticritereBonTransfertValue request) {

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
