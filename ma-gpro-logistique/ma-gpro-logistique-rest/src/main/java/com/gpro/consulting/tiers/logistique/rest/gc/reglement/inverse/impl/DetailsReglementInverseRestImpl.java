package com.gpro.consulting.tiers.logistique.rest.gc.reglement.inverse.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gpro.consulting.tiers.commun.service.partieInteressee.IPartieInteresseeService;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.DetailsReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.RechercheMulticritereReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ResultatRechecheElementReglementValue;
import com.gpro.consulting.tiers.logistique.service.gc.reglement.inverse.IDetailsReglementInverseService;
import com.gpro.consulting.tiers.logistique.service.gc.reglement.inverse.IReglementInverseService;

/**
 * Reglement Controller
 * 
 * @author Wahid Gazzah
 * @since 01/07/2016
 *
 */

@RestController
@RequestMapping("/detailsReglementInverse")
public class DetailsReglementInverseRestImpl {

	private static final Logger logger = LoggerFactory.getLogger(DetailsReglementInverseRestImpl.class);
	
	@Autowired
	private IReglementInverseService reglementInverseService;
	
	@Autowired
	private IPartieInteresseeService prtieInteresseeService;
	

	
	@Autowired
	private IDetailsReglementInverseService detailsReglementInverseService;
	
	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST, produces = "application/json")
	public ResultatRechecheElementReglementValue rechercherMultiCritere(@RequestBody RechercheMulticritereReglementValue request) {
		 
		//logger.info(" Delegating request to service layer.");
		
		//#front ma-gpro vente-detail-reglement
		
		
		ResultatRechecheElementReglementValue result = detailsReglementInverseService.rechercherMultiCritere(request);;
		

		
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

		
	       DetailsReglementValue detailsReglementValue = 	detailsReglementInverseService.getById(detailReglementId);
	
	       detailsReglementValue.setRegle(reglee);
	

		return detailsReglementInverseService.update(detailsReglementValue);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public String update(@RequestBody DetailsReglementValue detailsReglementValue) {
	    

		return detailsReglementInverseService.update(detailsReglementValue);
	}
	
	
	private boolean estNonVide(String val) {
		
	    return val != null && !"".equals(val) && !"undefined".equals(val) ;
	}


	
}
