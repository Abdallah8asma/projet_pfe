package com.gpro.consulting.tiers.logistique.rest.gc.reglement.impl;

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
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ElementReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.RechercheMulticritereReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ResultatRechecheElementReglementValue;
import com.gpro.consulting.tiers.logistique.service.gc.reglement.IDetailsReglementService;
import com.gpro.consulting.tiers.logistique.service.gc.reglement.IElementReglementService;
import com.gpro.consulting.tiers.logistique.service.gc.reglement.IReglementService;

/**
 * Reglement Controller
 * 
 * @author Wahid Gazzah
 * @since 01/07/2016
 *
 */

@RestController
@RequestMapping("/elementReglement")
public class ElementReglementRestImpl {

	private static final Logger logger = LoggerFactory.getLogger(ElementReglementRestImpl.class);
	
	@Autowired
	private IReglementService reglementService;
	
	@Autowired
	private IPartieInteresseeService prtieInteresseeService;
	
	
	@Autowired
	private IElementReglementService elementReglementService;
	
	
	@Autowired
	private IDetailsReglementService detailsReglementService;
	
	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST, produces = "application/json")
	public ResultatRechecheElementReglementValue rechercherMultiCritere(@RequestBody RechercheMulticritereReglementValue request) {
		 
		//logger.info(" Delegating request to service layer.");
		
		//#front ma-gpro vente-detail-reglement
		
		
		ResultatRechecheElementReglementValue result;
		request.setOptimized(RechercheMulticritereReglementValue.checkForOptimization(request));

		if(estNonVide(request.getNumPiece()) || estNonVide(request.getReferenceDetailReglement()))  {
			
		
			
	         result = detailsReglementService.rechercherMultiCritere(request);
			
			
		}else
			
			
		{
			
			result = elementReglementService.rechercherMultiCritere(request);
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
	public List<ElementReglementValue> getById(@PathVariable String reference) {
		  
		//logger.info("Delegating request id: {} to service layer.", id);
		
		return elementReglementService.getByRefernceFacture(reference);
	}
	
	
	private boolean estNonVide(String val) {
		
	    return val != null && !"".equals(val) && !"undefined".equals(val) ;
	}


	
}
