package com.gpro.consulting.tiers.logistique.rest.gc.reglement.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gpro.consulting.tiers.commun.service.partieInteressee.IPartieInteresseeService;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.ListProduitElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.DetailsReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ElementReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.RechercheMulticritereReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ResultatRechecheElementReglementValue;
import com.gpro.consulting.tiers.logistique.service.gc.reglement.IDetailsReglementService;
import com.gpro.consulting.tiers.logistique.service.gc.reglement.IReglementService;

/**
 * Reglement Controller
 * 
 * @author Wahid Gazzah
 * @since 01/07/2016
 *
 */

@RestController
@RequestMapping("/detailsReglement")
public class DetailsReglementRestImpl {

	private static final Logger logger = LoggerFactory.getLogger(DetailsReglementRestImpl.class);
	
	@Autowired
	private IReglementService reglementService;
	
	@Autowired
	private IPartieInteresseeService prtieInteresseeService;
	

	
	@Autowired
	private IDetailsReglementService detailsReglementService;
	
	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST, produces = "application/json")
	public ResultatRechecheElementReglementValue rechercherMultiCritere(@RequestBody RechercheMulticritereReglementValue request) {
		 
		//logger.info(" Delegating request to service layer.");
		
		//#front ma-gpro vente-detail-reglement
		
		
		ResultatRechecheElementReglementValue result = detailsReglementService.rechercherMultiCritere(request);;
		

		
	/*	if(result != null){

			for(ResultatRechecheReglementElementValue element : result.getList()){
		    	
				if(element.getPartieIntId() != null){
					
					PartieInteresseValue partieInteresse = prtieInteresseeService.getById(element.getPartieIntId());
					
					if(partieInteresse != null){
						
			    		element.setPartieIntAbreviation(partieInteresse.getAbreviation());
			    		element.setPartieIntReference(partieInteresse.getReference());
					}

		    	}
				
			}
		}
*/
		return result;
	}
	
	@RequestMapping(value = "/updateReglee", method = RequestMethod.GET)
	public @ResponseBody String updateReglee(
			@RequestParam(value = "detailReglementId", required = false) Long detailReglementId,
			@RequestParam(value = "reglee", required = false) Boolean reglee) {

		
	       DetailsReglementValue detailsReglementValue = 	detailsReglementService.getById(detailReglementId);
	
	       detailsReglementValue.setRegle(reglee);
	

		return detailsReglementService.update(detailsReglementValue);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public String update(@RequestBody DetailsReglementValue detailsReglementValue) {
	    

		return detailsReglementService.update(detailsReglementValue);
	}
	
	
	private boolean estNonVide(String val) {
		
	    return val != null && !"".equals(val) && !"undefined".equals(val) ;
	}


	
}
