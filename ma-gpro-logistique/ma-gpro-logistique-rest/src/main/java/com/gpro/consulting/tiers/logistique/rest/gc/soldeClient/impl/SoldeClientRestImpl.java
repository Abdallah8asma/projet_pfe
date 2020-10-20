package com.gpro.consulting.tiers.logistique.rest.gc.soldeClient.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.service.partieInteressee.IPartieInteresseeService;
import com.gpro.consulting.tiers.logistique.coordination.gc.soldeClient.value.RechercheMulticritereSoldeClientValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.soldeClient.value.ResultatRechecheSoldeClientValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.soldeClient.value.SoldeClientValue;
import com.gpro.consulting.tiers.logistique.service.gc.soldeClient.ISoldeClientService;

/**
 * @author Ameni Berrich
 *
 */
@RestController
@RequestMapping("/soldeClient")
public class SoldeClientRestImpl {

	private static final Logger logger = LoggerFactory.getLogger(SoldeClientRestImpl.class);
	@Autowired
	private ISoldeClientService soldeClientService;
	
	@Autowired
	private IPartieInteresseeService partieInteresseeService;
	
	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST, produces = "application/json")
	public ResultatRechecheSoldeClientValue rechercherMultiCritere(@RequestBody RechercheMulticritereSoldeClientValue request) {
		 
		//logger.info(" Delegating request to service layer.");
		
		ResultatRechecheSoldeClientValue result = soldeClientService.rechercherMultiCritere(request);
    	
		if(result != null){
			
			for(SoldeClientValue element : result.getList()){
		    	
				if(element.getPartIntId() != null){
					
					PartieInteresseValue partieInteresse = partieInteresseeService.getById(element.getPartIntId());
					
					if(partieInteresse != null){
						
			    		element.setPartieIntAbreviation(partieInteresse.getAbreviation());
			    		element.setPartieIntReference(partieInteresse.getReference());
					}

		    	}
				
			}
		}

		return result;
	}
	
	@RequestMapping(value = "/getById:{id}", method = RequestMethod.GET, produces = "application/json")
	public SoldeClientValue getById(@PathVariable Long id) {
		  
		//logger.info("getById: Delegating request id {} to service layer.", id);
		  
		return soldeClientService.getById(id);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@RequestBody SoldeClientValue soldeClientValue) {
	    
		//logger.info("Create: Delegating request to service layer.");
		
		return soldeClientService.create(soldeClientValue);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public String update(@RequestBody SoldeClientValue soldeClientValue) {
	    
		//logger.info("Update: Delegating request to service layer.");
		
		return soldeClientService.update(soldeClientValue);
	}
	
	@RequestMapping(value = "/delete:{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		  
		//logger.info("delete: Delegating request id {} to service layer.", id);
		  
		soldeClientService.delete(id);
	}
	
	@RequestMapping(value = "/updateSoldeClientForAllClients", method = RequestMethod.GET)
	public boolean updateSoldeClientForAllClients() {
	    
		//logger.info("updateSoldeClientForAllClients: Delegating request to service layer.");
		
		return soldeClientService.updateSoldeClientForAllClients();
	}
	
}
