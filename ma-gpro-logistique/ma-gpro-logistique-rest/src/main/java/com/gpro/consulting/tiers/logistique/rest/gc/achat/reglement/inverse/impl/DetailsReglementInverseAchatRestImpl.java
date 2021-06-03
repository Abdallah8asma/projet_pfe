package com.gpro.consulting.tiers.logistique.rest.gc.achat.reglement.inverse.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gpro.consulting.tiers.commun.service.partieInteressee.IPartieInteresseeService;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.DetailsReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.RechercheMulticritereReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.ResultatRechecheElementReglementAchatValue;
import com.gpro.consulting.tiers.logistique.service.gc.achat.reglement.inverse.IDetailsReglementInverseAchatService;
import com.gpro.consulting.tiers.logistique.service.gc.achat.reglement.inverse.IReglementInverseAchatService;

/**
 * Reglement Controller
 * 
 * @author Wahid Gazzah
 * @since 01/07/2016
 *
 */

@RestController
@RequestMapping("/detailsReglementInverseAchat")
public class DetailsReglementInverseAchatRestImpl {

	private static final Logger logger = LoggerFactory.getLogger(DetailsReglementInverseAchatRestImpl.class);
	
	@Autowired
	private IReglementInverseAchatService reglementInverseAchatService;
	
	@Autowired
	private IPartieInteresseeService prtieInteresseeService;
	

	@Autowired
	private IDetailsReglementInverseAchatService detailsReglementInverseAchatService;
	
	
	
	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST, produces = "application/json")
	public ResultatRechecheElementReglementAchatValue rechercherMultiCritere(@RequestBody RechercheMulticritereReglementAchatValue request) {
		 
		//logger.info(" Delegating request to service layer.");
		
		//#front ma-gpro vente-detail-reglement
		
		
		ResultatRechecheElementReglementAchatValue result = detailsReglementInverseAchatService.rechercherMultiCritere(request);;
		

		
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
	

	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public String update(@RequestBody DetailsReglementAchatValue detailsReglementValue) {
	    

		return detailsReglementInverseAchatService.update(detailsReglementValue);
	}
	
	
	private boolean estNonVide(String val) {
		
	    return val != null && !"".equals(val) && !"undefined".equals(val) ;
	}


	
}
