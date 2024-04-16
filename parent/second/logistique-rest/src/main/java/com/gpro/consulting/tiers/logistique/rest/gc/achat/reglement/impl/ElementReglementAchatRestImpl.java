package com.gpro.consulting.tiers.logistique.rest.gc.achat.reglement.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gpro.consulting.tiers.commun.service.partieInteressee.IPartieInteresseeService;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.ElementReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.RechercheMulticritereReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.ResultatRechecheElementReglementAchatValue;
import com.gpro.consulting.tiers.logistique.service.gc.achat.reglement.IDetailsReglementAchatService;
import com.gpro.consulting.tiers.logistique.service.gc.achat.reglement.IElementReglementAchatService;
import com.gpro.consulting.tiers.logistique.service.gc.achat.reglement.IReglementAchatService;

/**
 * Reglement Controller
 * 
 * @author Wahid Gazzah
 * @since 01/07/2016
 *
 */

@RestController
@RequestMapping("/elementReglementAchat")
public class ElementReglementAchatRestImpl {

	private static final Logger logger = LoggerFactory.getLogger(ElementReglementAchatRestImpl.class);
	
	@Autowired
	private IReglementAchatService reglementAchatService;
	
	@Autowired
	private IPartieInteresseeService prtieInteresseeService;
	
	
	@Autowired
	private IElementReglementAchatService elementReglementAchatService;
	
	
	@Autowired
	private IDetailsReglementAchatService detailsReglementAchatService;
	
	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST, produces = "application/json")
	public ResultatRechecheElementReglementAchatValue rechercherMultiCritere(@RequestBody RechercheMulticritereReglementAchatValue request) {
		 
		//logger.info(" Delegating request to service layer.");
		
		//#front ma-gpro vente-detail-reglement
		
		
		ResultatRechecheElementReglementAchatValue result;
		
		
		if(estNonVide(request.getNumPiece()) || estNonVide(request.getReferenceDetailReglement()) )  {
			
	         result = detailsReglementAchatService.rechercherMultiCritere(request);
			
			
		}else
			
			
		{
			
			result = elementReglementAchatService.rechercherMultiCritere(request);
		}
		
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
	
	
	
	@RequestMapping(value = "/getByRefernceFacture:{reference}", method = RequestMethod.GET, produces = "application/json")
	public List<ElementReglementAchatValue> getById(@PathVariable String reference) {
		  
		//logger.info("Delegating request id: {} to service layer.", id);
		
		return elementReglementAchatService.getByRefernceFacture(reference);
	}
	
	
	private boolean estNonVide(String val) {
		
	    return val != null && !"".equals(val) && !"undefined".equals(val) ;
	}


	
}
